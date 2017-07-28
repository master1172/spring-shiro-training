package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleSalaryBase;
import com.wangzhixuan.service.*;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.SalaryCalculator;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by sterm on 2017/1/13.
 */
@Controller
@RequestMapping("/peopleSalary")
public class PeopleSalaryController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleSalaryController.class);

    @Autowired
    private PeopleSalaryService peopleSalaryService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PeopleTimesheetService peopleTimesheetService;

    @Autowired
    private ExamMonthlyService examMonthlyService;

    @Autowired
    private ExamYearlyService examYearlyService;

    @RequestMapping(value="/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/peopleSalary/people";
    }

    @RequestMapping(value="/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleSalaryBaseVo peopleSalaryBaseVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleSalaryBaseVo.CreateCondition(peopleSalaryBaseVo);
        pageInfo.setCondition(condition);
        peopleSalaryService.findDataGrid(pageInfo,request);

        return pageInfo;
    }

    @RequestMapping("/editPage")
    public String editPage(Integer id, Model model){
        PeopleSalaryBase peopleSalaryBase = peopleSalaryService.findPeopleSalaryBaseById(id);
        peopleSalaryService.updateJobSalaryAndRankSalary(peopleSalaryBase);
        model.addAttribute("peopleSalaryBase",peopleSalaryBase);
        return "/admin/peopleSalary/peopleSalaryBaseEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleSalaryBase peopleSalaryBase){
        Result result = new Result();
        try{
            peopleSalaryService.updateSalaryBase(peopleSalaryBase);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改工资失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping(value="/salaryListPage", method = RequestMethod.GET)
    public String salaryListPage(Integer id, Model model){

        People people = peopleService.findPeopleById(id);
        if (people != null){
            model.addAttribute("code",people.getCode());
            model.addAttribute("name",people.getName());
        }else{
            model.addAttribute("code","");
            model.addAttribute("name","");
        }
        return "/admin/peopleSalary/peopleSalaryList";
    }

    @RequestMapping(value="/salaryGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo salaryGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page,rows);
        String peopleCode = request.getParameter("code");
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);
        peopleSalaryService.findSalaryDataGrid(pageInfo,request);

        return pageInfo;
    }

    @RequestMapping("/addPage")
    public String addPage(String peopleCode, Model model){

        PeopleSalaryBase peopleSalaryBase = peopleSalaryService.findPeopleSalaryBaseByCode(peopleCode);

        if (peopleSalaryBase == null)
            peopleSalaryBase = new PeopleSalaryBase();

        PeopleSalary peopleSalary = new PeopleSalary();

        try{
            BeanUtils.copyProperties(peopleSalary, peopleSalaryBase);
        }catch (Exception exp){
            peopleSalary.setPeopleCode(peopleSalaryBase.getPeopleCode());;
        }

        //自动计算当月请假天数
        String firstDayOfCurrentMonth = DateUtil.GetFirstDayOfCurrentMonth();
        String lastDayOfCurrentMonth  = DateUtil.GetLastDayOfCurrentMonth();

        BigDecimal sumVacationPeriod = peopleTimesheetService.findVacationSumByCodeAndDate(
                peopleSalaryBase.getPeopleCode(),
                firstDayOfCurrentMonth,
                lastDayOfCurrentMonth);

        //根据请假天数计算交通补贴和高温补贴
        peopleSalary.setTimesheetStatus(sumVacationPeriod);
        SalaryCalculator.PeopleSalaryCalculateTrafficAllowance(peopleSalary);
        SalaryCalculator.PeopleSalaryCalculateTemperatureAllowance(peopleSalary);

        //计算当月考核情况
        String examResult = examMonthlyService.findPeopleExamMonthlyResultByCodeAndDate(
                peopleSalaryBase.getPeopleCode(),
                DateUtil.GetCurrentYearAndMonth()
        );

        peopleSalary.setExamResult(examResult);
        SalaryCalculator.GetPerformanceTotalByMonthlyExamResult(peopleSalary);

        //如果是春节之前的一个月，还需要根据年度表现来计算是否发放奖金。优秀和合格发放奖金，不合格不发奖金
        String examYearlyResult = examYearlyService.findPeopleExamYearlyResultByCodeAndYear(
                peopleSalaryBase.getPeopleCode(),
                DateUtil.GetCurrentYear()
        );

        BigDecimal yearlyBonus = SalaryCalculator.GetBonusByYearlyExamResult(examYearlyResult, peopleSalaryBase);
        peopleSalary.setYearlyBonus(yearlyBonus);


        model.addAttribute("examResult",examResult);
        model.addAttribute("peopleSalary", peopleSalary);

        return "/admin/peopleSalary/peopleSalaryAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleSalary peopleSalary,@RequestParam(value="fileName",required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleSalaryService.addSalary(peopleSalary);
            result.setSuccess(true);
            result.setMsg("添加成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("添加工资失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping(value="/editSalaryPage")
    public String editSalaryBase(Integer id, Model model){
        PeopleSalary peopleSalary = peopleSalaryService.findPeopleSalaryById(id);
        model.addAttribute("peopleSalary",peopleSalary);
        return "/admin/peopleSalary/peopleSalaryEdit";
    }

    @RequestMapping(value = "/editSalary")
    @ResponseBody
    public Result editSalary(PeopleSalary peopleSalary){
        Result result = new Result();
        try{
            peopleSalaryService.updateSalary(peopleSalary);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改工资失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/exportExcelForMonth")
    public void exportExcelForMonth(HttpServletResponse response, String ids, String payDate){

        if(StringUtils.isBlank(payDate)){
            payDate = DateUtil.GetCurrentYearAndMonth();
        }
        try{
            peopleSalaryService.exportExcelForMonth(response, payDate);
        }catch (Exception exp){
            return;
        }
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, String ids, String payDate){

        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择有效数据!");
            return;
        }
        try{
            peopleSalaryService.exportExcel(response,ids.split(","), payDate);
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }

    @RequestMapping("/exportSalaryBaseExcel")
    public void exportSalaryBaseExcel(HttpServletResponse response, String ids){
        if(StringUtils.isBlank(ids)){
            return;
        }
        try{
            peopleSalaryService.exportExcel2(response, ids.split(","));
        }catch (Exception exp){

        }
    }

    @RequestMapping("/selectPayDate")
    public String selectPayDate(){
        return "/admin/peopleSalary/selectPayDate";
    }

    @RequestMapping(value="/importExcelPage", method=RequestMethod.GET)
    public String importExcelPage(){
        return "admin/peopleSalary/importExcelPage";
    }

    @RequestMapping("/autoCalculateSalary")
    @ResponseBody
    public Result autoCalculateSalary(String payDate){
        Result result = new Result();
        if (StringUtils.isBlank(payDate)){
            result.setSuccess(false);
            result.setMsg("请选择正确的日期");
            return result;
        }

        try{
            StringBuilder processResult = new StringBuilder();
            boolean process = peopleSalaryService.autoCalculateSalary(payDate,processResult);
            result.setSuccess(process);
            result.setMsg(processResult.toString());
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.getMessage());
        }

        return result;
    }

    /**
     * 批量调入W
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
        Result result = new Result();
        if(files!=null&&files.length>0){
            boolean flag=peopleSalaryService.insertByImport(files);
            result.setSuccess(flag);
            if(!flag){
                result.setMsg("系统繁忙，请稍后再试！");
            }
        }else{
            result.setSuccess(false);
            result.setMsg("请选择附件！");
        }
        return result;
    }

    @RequestMapping("/salaryBaseEdit")
    @ResponseBody
    public Result salaryBaseEdit(PeopleSalaryBase peopleSalaryBase){
        Result result = new Result();
        try{
            peopleSalaryService.updateSalaryBase(peopleSalaryBase);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改工资基数失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        try{
            peopleSalaryService.deleteSalaryById(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.toString());
        }

        return result;
    }

    @RequestMapping("/advSearchPage")
    public String advSearchPage(){
        return "/admin/peopleSalary/salarySearch";
    }

    @RequestMapping("/calculateSalary")
    @ResponseBody
    public String calculateSalary(PeopleSalary peopleSalary){
        BigDecimal grossIncome = peopleSalaryService.CalculateGrossIncome(peopleSalary);
        return grossIncome.toString();
    }

    @RequestMapping("/exportSearchPage")
    public String exportSearchPage(){
        return "/admin/peopleSalary/salarySearch";
    }

    @RequestMapping("/exportSearch")
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, PeopleSalaryBaseVo peopleSalaryBaseVo){
        Result result = new Result();
        Map<String,Object> condition = PeopleSalaryBaseVo.CreateCondition(peopleSalaryBaseVo);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids = peopleSalaryService.findPeopleIDsByCondition(pageInfo);
        if (StringUtils.isBlank(ids)){
            result.setSuccess(false);
            result.setMsg("未找到有效数据");
            LOGGER.error("Excel:{}","无有效数据");
            return result;
        }
        try{
            result.setSuccess(true);
            result.setObj(ids);
        }catch(Exception exp){
            result.setSuccess(false);
            result.setMsg("导出Excel失败");
            LOGGER.error("导出Excel失败:{}",exp);
        }

        return result;
    }

    @RequestMapping("/exportCert")
    public void exportCert(HttpServletResponse response,String ids){
        if (StringUtils.isEmpty(ids)){
            LOGGER.error("导出Word:{}","请选择一条有效数据!");
        }
        try{
            peopleSalaryService.exportCert(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }
}

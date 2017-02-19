package com.wangzhixuan.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.model.*;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
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

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleSalaryVo;
import com.wangzhixuan.vo.PeopleVo;

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
        People people = peopleService.findPeopleByCode(peopleCode);
        PeopleSalaryBaseVo peopleSalaryBaseVo = peopleSalaryService.findPeopleSalaryBaseByCode(peopleCode);
        if (people == null)
            people = new People();
        if (peopleSalaryBaseVo == null)
            peopleSalaryBaseVo = new PeopleSalaryBaseVo();

        Integer currentMonth = DateUtil.GetCurrentMonth();
        Integer currentYear  = DateUtil.GetCurrentYear();

        List<PeopleTimesheet> peopleTimeSheetList = peopleTimesheetService.findPeopleTimesheetListByCodeAndDate(people.getCode(), currentYear, currentMonth);
        ExamMonthly peopleExamMonthlyResult = examMonthlyService.findPeopleExamMonthlyResultByCodeAndDate(people.getCode(), currentYear, currentMonth);

        BigDecimal sumVacationPeriod = BigDecimal.valueOf(0.0);

        if (peopleTimeSheetList != null && peopleTimeSheetList.size() > 0){
            for (int i=0; i<peopleTimeSheetList.size(); i++){
                PeopleTimesheet peopleTimesheet = peopleTimeSheetList.get(i);
                if (peopleTimesheet == null || StringUtils.isBlank(peopleTimesheet.getPeopleCode()))
                    continue;
                sumVacationPeriod = sumVacationPeriod.add(peopleTimesheet.getVacationPeriod());
            }
        }

        String examResult = "";

        if (peopleExamMonthlyResult != null){
            examResult = peopleExamMonthlyResult.getExamResult();
        }

        model.addAttribute("timesheetStatus", sumVacationPeriod);
        model.addAttribute("examResult",examResult);
        model.addAttribute("people",people);
        model.addAttribute("peopleSalaryBaseVo", peopleSalaryBaseVo);
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





    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, String ids){

        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择有效数据!");
        }
        try{
            peopleSalaryService.exportExcel(response,ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }

    @RequestMapping(value="/importExcelPage", method=RequestMethod.GET)
    public String importExcelPage(){
        return "admin/peopleSalary/importExcelPage";
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

    @RequestMapping("/salaryBasePage")
    public String salaryBasePage(String peopleCode, Model model){
        PeopleSalaryBaseVo peopleSalaryBaseVo = peopleSalaryService.findPeopleSalaryBaseByCode(peopleCode);

        if (peopleSalaryBaseVo == null){
            peopleSalaryBaseVo = new PeopleSalaryBaseVo();
            peopleSalaryBaseVo.setPeopleCode(peopleCode);

            People people = peopleService.findPeopleByCode(peopleCode);
            if (people != null){
                peopleSalaryBaseVo.setPeopleName(people.getName());
                peopleSalaryBaseVo.setJobId(people.getJobId());
            }
        }

        model.addAttribute("peopleSalaryBaseVo",peopleSalaryBaseVo);
        return "/admin/peopleSalary/peopleSalaryBase";
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
}

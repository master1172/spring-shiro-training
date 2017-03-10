package com.wangzhixuan.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.model.*;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.vo.PeopleContractSalaryBaseVo;
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

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import com.wangzhixuan.vo.PeopleContractVo;

/**
 */
@Controller
@RequestMapping("/peopleContractSalary")
public class PeopleContractSalaryController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContractSalaryService peopleContractSalaryService;

	@Autowired
	private PeopleContractService peopleContractService;

	@Autowired
	private PeopleTimesheetService peopleTimesheetService;

	@Autowired
	private ExamMonthlyService examMonthlyService;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/peopleContractSalary/people";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleContractSalaryBaseVo peopleContractSalaryBaseVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = PeopleContractSalaryBaseVo.CreateCondition(peopleContractSalaryBaseVo);
		condition.put("status", ConstUtil.PEOPLE_CONTRACT);
		pageInfo.setCondition(condition);
		peopleContractSalaryService.findDataGrid(pageInfo,request);
		return pageInfo;
	}

	@RequestMapping("/editPage")
	public String editPage(Integer id, Model model) {
		PeopleContractSalaryBase peopleContractBase = peopleContractSalaryService.findPeopleContractSalaryBaseById(id);
		model.addAttribute("peopleContractSalaryBase", peopleContractBase);
		return "/admin/peopleContractSalary/peopleSalaryBaseEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(PeopleContractSalaryBase peopleContractSalaryBase) {
		Result result = new Result();
		try {
			peopleContractSalaryService.updateSalaryBase(peopleContractSalaryBase);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		} catch (Exception e) {
			logger.error("修改工资失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/salaryListPage", method = RequestMethod.GET)
	public String salaryListPage(Integer id, Model model) {

		PeopleContract people = peopleContractService.findPeopleContractById(id);
		logger.info(JSON.toJSONString(people));

		if (people != null) {
			model.addAttribute("code", people.getCode());
			model.addAttribute("name", people.getName());
		} else {
			model.addAttribute("code", "");
			model.addAttribute("name", "");
		}
		return "/admin/peopleContractSalary/peopleSalaryList";
	}


	@RequestMapping(value = "/salaryGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo salaryGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		String peopleCode = request.getParameter("code");
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("peopleCode", peopleCode);
		pageInfo.setCondition(condition);
		peopleContractSalaryService.findSalaryDataGrid(pageInfo, request);

		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id) {
		Result result = new Result();
		try {
			peopleContractSalaryService.deleteSalaryById(id);
			
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			logger.error("删除工资记录失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/addPage")
	public String addPage(String peopleCode, Model model) {
		PeopleContractSalaryBase peopleContractSalaryBase = peopleContractSalaryService.findPeopleContractSalaryBaseByCode(peopleCode);
		if (peopleContractSalaryBase == null) {
			peopleContractSalaryBase = new PeopleContractSalaryBase();
		}

		String firstDayOfCurrentMonth = DateUtil.GetFirstDayOfCurrentMonth();
		String lastDayOfCurrentMonth  = DateUtil.GetLastDayOfCurrentMonth();

		BigDecimal sumVacationPeriod = peopleTimesheetService.findVacationSumByCodeAndDate(
				peopleContractSalaryBase.getPeopleCode(),
				firstDayOfCurrentMonth,
				lastDayOfCurrentMonth);

		if (sumVacationPeriod != null){
			Double temperatureAllowance = 100 - 100 / 21.75 * sumVacationPeriod.doubleValue();
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			peopleContractSalaryBase.setTemperatureAllowance(new BigDecimal(decimalFormat.format(temperatureAllowance)));
		}else{
			peopleContractSalaryBase.setTemperatureAllowance(new BigDecimal(100.00));
		}

		model.addAttribute("people", peopleContractSalaryBase);
		model.addAttribute("sumVacationPeriod",sumVacationPeriod);

		String examResult = examMonthlyService.findPeopleExamMonthlyResultByCodeAndDate(
				peopleContractSalaryBase.getPeopleCode(),
				firstDayOfCurrentMonth,
				lastDayOfCurrentMonth
		);

		model.addAttribute("examResult",examResult);

		BigDecimal jobExamSalaryTotal = new BigDecimal(0.00);

		if (StringUtils.isNoneBlank(examResult) && peopleContractSalaryBase.getJobExamSalary() != null){
			if (examResult.equals("A")){
				jobExamSalaryTotal = peopleContractSalaryBase.getJobExamSalary();
			}
			if (examResult.equals("B")){
				jobExamSalaryTotal = peopleContractSalaryBase.getJobExamSalary().multiply(new BigDecimal(0.8));
			}
			if (examResult.equals("C")){
				jobExamSalaryTotal = peopleContractSalaryBase.getJobExamSalary().multiply(new BigDecimal(0.5));
			}
			if (examResult.equals("D")){
				jobExamSalaryTotal = peopleContractSalaryBase.getJobExamSalary().multiply(new BigDecimal(0.2));
			}
			if (examResult.equals("E")){
				jobExamSalaryTotal = peopleContractSalaryBase.getJobExamSalary().multiply(new BigDecimal(0.0));
			}

			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			model.addAttribute("jobExamSalaryTotal", decimalFormat.format(jobExamSalaryTotal));
		}else{
			model.addAttribute("jobExamSalaryTotal", "0.00");
		}

		return "/admin/peopleContractSalary/peopleSalaryAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(PeopleContractSalary peopleContractSalary ,@RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
		Result result = new Result();
		try {
			peopleContractSalaryService.addSalary(peopleContractSalary);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		} catch (Exception e) {
			logger.error("添加工资失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping(value="/editSalaryPage")
	public String editSalaryBase(Integer id, Model model){
		PeopleContractSalary peopleContractSalary = peopleContractSalaryService.findPeopleContractSalaryById(id);
		model.addAttribute("peopleContractSalary",peopleContractSalary);
		return "/admin/peopleContractSalary/peopleSalaryEdit";
	}

	@RequestMapping(value = "editSalary")
	@ResponseBody
	public Result editSalary(PeopleContractSalary peopleContractSalary){
		Result result = new Result();
		try{
			peopleContractSalaryService.updateSalary(peopleContractSalary);
			result.setSuccess(true);
			result.setMsg("修改成功");
			return result;
		}catch(Exception exp){
			result.setMsg(exp.getMessage());
			return result;
		}
	}

	/**
	 * 批量调入W
	 */
	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "admin/peopleContractSalary/importExcelPage";
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			logger.info(JSON.toJSONString(files));
			boolean flag = peopleContractSalaryService.insertByImport(files);
			result.setSuccess(flag);
			if (!flag) {
				result.setMsg("系统繁忙，请稍后再试！");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("请选择附件！");
		}
		return result;
	}

	/**
	 * 导出Excel
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response, String ids) {

		if (StringUtils.isBlank(ids)) {
			logger.error("Excel:{}", "请选择有效数据!");
		}
		try {
			peopleContractSalaryService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			logger.error("导出Excel失败:{}", exp);
		}
	}

    @RequestMapping("/salaryBasePage")
    public String salaryBasePage(String peopleCode, Model model){
        PeopleContractSalaryBase peopleContractSalaryBase = peopleContractSalaryService.findPeopleContractSalaryBaseByCode(peopleCode);
        if(peopleContractSalaryBase == null){
            peopleContractSalaryBase = new PeopleContractSalaryBase();
            peopleContractSalaryBase.setPeopleCode(peopleCode);

            PeopleContract peopleContract = peopleContractService.findPeopleContractByCode(peopleCode);
            if (peopleContract != null){
                peopleContractSalaryBase.setJobId(peopleContract.getJobId());
            }
        }

        model.addAttribute("peopleContractSalaryBase", peopleContractSalaryBase);
        return "/admin/peopleContractSalary/peopleSalaryBase";
    }

    @RequestMapping("/salaryBaseEdit")
    @ResponseBody
	public Result salaryBaseEdit(PeopleContractSalaryBase peopleContractSalaryBase){
		Result result = new Result();
		try{
			peopleContractSalaryService.updateSalaryBase(peopleContractSalaryBase);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			return result;
		}catch (Exception exp){
			result.setMsg(exp.getMessage());
			return result;
		}
	}

	@RequestMapping("/advSearchPage")
	public String advSearchPage(){
		return "/admin/peopleContractSalary/salarySearch";
	}

	@RequestMapping("/calculateSalary")
	@ResponseBody
	public String calculateSalary(PeopleContractSalary peopleContractSalary){
		BigDecimal grossIncome = peopleContractSalaryService.CalculateGrossIncome(peopleContractSalary);
		return grossIncome.toString();
	}

	@RequestMapping("/exportCert")
	public void exportCert(HttpServletResponse response,String ids){
		if (StringUtils.isEmpty(ids)){

		}
		try{
			peopleContractSalaryService.exportCert(response,ids);
		}catch(Exception exp){

		}
	}
}

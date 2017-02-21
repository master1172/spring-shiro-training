package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.stat.TableStat;
import com.wangzhixuan.model.PeopleRetireSalaryBase;
import com.wangzhixuan.vo.PeopleRetireSalaryBaseVo;
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
import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.service.PeopleRetireSalaryService;
import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import com.wangzhixuan.vo.PeopleContractVo;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;

/**
 * Created by sterm on 2017/1/13.
 */
@Controller
@RequestMapping("/peopleRetireSalary")
public class PeopleRetireSalaryController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleRetireSalaryService peopleRetireSalaryService;

	@Autowired
	private PeopleRetireService peopleRetireService;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/peopleRetireSalary/people";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleRetireSalaryBaseVo peopleRetireSalaryBaseVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = PeopleRetireSalaryBaseVo.CreateCondition(peopleRetireSalaryBaseVo);
		pageInfo.setCondition(condition);

		peopleRetireSalaryService.findDataGrid(pageInfo,request);

		return pageInfo;
	}

	@RequestMapping("/editPage")
	public String editPage(Integer id, Model model) {
		PeopleRetireSalaryBase peopleRetireSalaryBase = peopleRetireSalaryService.findPeopleRetireSalaryBaseById(id);
		model.addAttribute("peopleRetireSalaryBase", peopleRetireSalaryBase);
		return "/admin/peopleRetireSalary/peopleSalaryBaseEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(PeopleRetireSalaryBase peopleRetireSalaryBase) {
		Result result = new Result();
		try {
			peopleRetireSalaryService.updateSalaryBase(peopleRetireSalaryBase);
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

		PeopleRetire people = peopleRetireService.findPeopleRetireById(id);

		if (people != null) {
			model.addAttribute("code", people.getCode());
			model.addAttribute("name", people.getName());
		} else {
			model.addAttribute("code", "");
			model.addAttribute("name", "");
		}
		return "/admin/peopleRetireSalary/peopleSalaryList";
	}

	@RequestMapping(value = "/salaryGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo salaryGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		String peopleCode = request.getParameter("code");
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("peopleCode", peopleCode);
		pageInfo.setCondition(condition);
		peopleRetireSalaryService.findSalaryDataGrid(pageInfo, request);
		return pageInfo;
	}

	@RequestMapping("/addPage")
	public String addPage(String peopleCode, Model model) {
		PeopleRetireSalaryBase peopleRetireSalaryBase = peopleRetireSalaryService.findPeopleRetireSalaryBaseByCode(peopleCode);
		if (peopleRetireSalaryBase == null) {
			peopleRetireSalaryBase = new PeopleRetireSalaryBase();
		}
		model.addAttribute("people", peopleRetireSalaryBase);
		return "/admin/peopleRetireSalary/peopleSalaryAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(PeopleRetireSalary peopleRetireSalary, @RequestParam(value = "fileName", required = false) CommonsMultipartFile file) {
		Result result = new Result();
		try {
			peopleRetireSalaryService.addSalary(peopleRetireSalary);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		} catch (Exception e) {
			logger.error("添加工资失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/editSalaryPage")
	public String editSalaryPage(Integer id, Model model){
		PeopleRetireSalary peopleRetireSalary = peopleRetireSalaryService.findPeopleRetireSalaryById(id);
		model.addAttribute("peopleRetireSalary", peopleRetireSalary);
		return "/admin/peopleRetireSalary/peopleSalaryEdit";
	}

	@RequestMapping(value="/editSalary")
	@ResponseBody
	public Result editSalary(PeopleRetireSalary peopleRetireSalary){
		Result result = new Result();
		try{
			peopleRetireSalaryService.updateSalary(peopleRetireSalary);
			result.setSuccess(true);
			result.setMsg("修改成功");
			return result;
		}catch (Exception exp){
			result.setMsg(exp.getMessage());
			return result;
		}
	}


	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id) {
		Result result = new Result();
		try {
			peopleRetireSalaryService.deleteSalaryById(id);

			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			logger.error("删除工资记录失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}






	
	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "admin/peopleRetireSalary/importExcelPage";
	}
	
	/**
	 * 批量调入W
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			boolean flag = peopleRetireSalaryService.insertByImport(files);
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
			peopleRetireSalaryService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			logger.error("导出Excel失败:{}", exp);
		}
	}
	@RequestMapping("/salaryBasePage")
	public String salaryBasePage(String peopleCode, Model model){
		PeopleRetireSalaryBase peopleRetireSalaryBase = peopleRetireSalaryService.findPeopleRetireSalaryBaseByCode(peopleCode);
		if(peopleRetireSalaryBase == null){
			peopleRetireSalaryBase = new PeopleRetireSalaryBase();
			peopleRetireSalaryBase.setPeopleCode(peopleCode);

			PeopleRetire peopleRetire = peopleRetireService.findPeopleRetireByCode(peopleCode);
//			if (peopleRetire != null){
//				peopleRetireSalaryBase.setRetireJobLevelId(peopleRetire.RetireJobLevelId());
//			}
		}

		model.addAttribute("peopleRetireSalaryBase", peopleRetireSalaryBase);
		return "/admin/peopleRetireSalary/peopleSalaryBase";
	}

	@RequestMapping("/salaryBaseEdit")
	@ResponseBody
	public Result salaryBaseEdit(PeopleRetireSalaryBase peopleRetireSalaryBase){
		Result result = new Result();
		try{
			peopleRetireSalaryService.updateSalaryBase(peopleRetireSalaryBase);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			return result;
		}catch (Exception exp){
			result.setMsg(exp.getMessage());
			return result;
		}
	}
}

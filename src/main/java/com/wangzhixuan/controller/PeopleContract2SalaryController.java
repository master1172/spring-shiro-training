package com.wangzhixuan.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.service.PeopleContract2SalaryService;
import com.wangzhixuan.service.PeopleContract2Service;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import com.wangzhixuan.vo.PeopleContractVo;
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
import java.util.Map;

/**
 */
@Controller
@RequestMapping("/peopleContract2Salary")
public class PeopleContract2SalaryController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContract2SalaryService peopleContract2SalaryService;

	@Autowired
	private PeopleContract2Service peopleContract2Service;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/peopleContract2Salary/people";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleContractVo peopleVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = PeopleContractVo.CreateCondition(peopleVo);
		pageInfo.setCondition(condition);
		peopleContract2Service.findDataGrid(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/salaryListPage", method = RequestMethod.GET)
	public String salaryListPage(Long id, Model model) {

		PeopleContract people = peopleContract2Service.findPeopleContractById(id);
		logger.info(JSON.toJSONString(people));

		if (people != null) {
			model.addAttribute("code", people.getCode());
			model.addAttribute("name", people.getName());
		} else {
			model.addAttribute("code", "");
			model.addAttribute("name", "");
		}
		return "/admin/peopleContract2Salary/peopleSalaryList";
	}


	@RequestMapping(value = "/salaryGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo salaryGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		String peopleCode = request.getParameter("code");
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("peopleCode", peopleCode);
		pageInfo.setCondition(condition);

		peopleContract2SalaryService.findDataGrid(pageInfo, request);

		logger.info("salaryGrid"+JSON.toJSONString(pageInfo));
		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			peopleContract2SalaryService.deleteSalaryById(id);
			
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
		PeopleContract peopleContract = peopleContract2Service.findPeopleContractByCode(peopleCode);
		if (peopleContract == null) {
			peopleContract = new PeopleContract();
		}
		model.addAttribute("people", peopleContract);
		return "/admin/peopleContract2Salary/peopleSalaryAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(PeopleContractSalary peopleContractSalary ,@RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
		Result result = new Result();
		try {
			peopleContract2SalaryService.addSalary(peopleContractSalary);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		} catch (Exception e) {
			logger.error("添加工资失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/editPage")
	public String editPage(Long id, Model model) {
		PeopleContractSalaryVo peopleContractVo = peopleContract2SalaryService.findPeopleContractSalaryVoById(id);
		model.addAttribute("peopleContract2Salary", peopleContractVo);
		
		logger.info("peopleContractSalary:"+JSON.toJSONString(peopleContractVo));
		return "/admin/peopleContract2Salary/peopleSalaryEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(PeopleContractSalary peopleContractSalary) {
		Result result = new Result();
		try {
			peopleContract2SalaryService.updateSalary(peopleContractSalary);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		} catch (Exception e) {
			logger.error("修改工资失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	/**
	 * 批量调入W
	 */
	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "admin/peopleContract2Salary/importExcelPage";
	}
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			logger.info(JSON.toJSONString(files));
			boolean flag = peopleContract2SalaryService.insertByImport(files);
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
			peopleContract2SalaryService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			logger.error("导出Excel失败:{}", exp);
		}
	}
}

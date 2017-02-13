package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.model.*;
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

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/peopleContractSalary/people";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleContractVo peopleVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = PeopleContractVo.CreateCondition(peopleVo);
		pageInfo.setCondition(condition);
		peopleContractService.findDataGrid(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/salaryListPage", method = RequestMethod.GET)
	public String salaryListPage(Long id, Model model) {

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

		peopleContractSalaryService.findDataGrid(pageInfo, request);

		logger.info("salaryGrid"+JSON.toJSONString(pageInfo));
		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
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
		PeopleContract peopleContract = peopleContractService.findPeopleContractByCode(peopleCode);
		if (peopleContract == null) {
			peopleContract = new PeopleContract();
		}
		model.addAttribute("people", peopleContract);
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

	@RequestMapping("/editPage")
	public String editPage(Long id, Model model) {
		PeopleContractSalaryVo peopleContractVo = peopleContractSalaryService.findPeopleContractSalaryVoById(id);
		model.addAttribute("peopleContractSalary", peopleContractVo);
		
		logger.info("peopleContractSalary:"+JSON.toJSONString(peopleContractVo));
		return "/admin/peopleContractSalary/peopleSalaryEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(PeopleContractSalary peopleContractSalary) {
		Result result = new Result();
		try {
			peopleContractSalaryService.updateSalary(peopleContractSalary);
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

}

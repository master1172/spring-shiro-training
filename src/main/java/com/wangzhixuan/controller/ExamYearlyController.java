package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.wangzhixuan.vo.PeopleVo;
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

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by mengfw on 2017/1/20.
 */
@Controller
@RequestMapping("/examYearly")
public class ExamYearlyController extends BaseController {
	private static Logger LOGGER = LoggerFactory
			.getLogger(ExamYearlyController.class);
	@Autowired
	private ExamYearlyService examYearlyService;
	@Autowired
	private PeopleService peopleService;

	/**
	 * 人员管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/examYearly/people";
	}

	@RequestMapping(value = "/contractmanager", method = RequestMethod.GET)
	public String contractManager(){
		return "/admin/examYearly/peopleContract";
	}

	@RequestMapping(value = "/contract2manager", method = RequestMethod.GET)
	public String contract2Manager(){
		return "/admin/examYearly/peopleContract2";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleVo peopleVo, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String,Object> condition = PeopleVo.CreateCondition(peopleVo);
		pageInfo.setCondition(condition);
		peopleService.findDataGrid(pageInfo,request);

		return pageInfo;
	}
	@RequestMapping(value="/examYearlyListPage", method = RequestMethod.GET)
	public String examYearlyListPage(Integer id, Model model){

		People people = peopleService.findPeopleById(id);
		if (people != null){
			model.addAttribute("code",people.getCode());
			model.addAttribute("name",people.getName());
		}else{
			model.addAttribute("code","");
			model.addAttribute("name","");
		}
		return "/admin/examYearly/examYearlyList";
	}

	@RequestMapping(value="/examYearlyGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo examYearlyGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page,rows);
		String peopleCode = request.getParameter("code");
		Map<String,Object> condition = Maps.newHashMap();
		condition.put("peopleCode",peopleCode);
		pageInfo.setCondition(condition);
		examYearlyService.findDataGrid(pageInfo);

		return pageInfo;
	}

	@RequestMapping("/editPage")
	public String editPage(Long id, Model model) {
		ExamYearly examYearly = examYearlyService.findExamYearlyById(id);
		model.addAttribute("examYearly", examYearly);
		return "admin/examYearly/examYearlyEdit";
	}
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(ExamYearly examYearly) {
		Result result = new Result();
		try {
			examYearlyService.update(examYearly);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		} catch (Exception e) {
			LOGGER.error("修改失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

//	@RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
//	@ResponseBody
//	public Result exportSearch(HttpServletResponse response,
//			ExamYearlyVo examYearlyVo) {
//
//		Result result = new Result();
//
//		Map<String, Object> condition = ExamYearlyVo
//				.CreateCondition(examYearlyVo);
//
//		PageInfo pageInfo = new PageInfo();
//		pageInfo.setCondition(condition);
//		String ids = examYearlyService.findIDsByCondition(pageInfo);
//
//		if (StringUtils.isBlank(ids)) {
//			result.setSuccess(false);
//			result.setMsg("未找到有效数据");
//			LOGGER.error("Excel:{}", "无有效数据");
//			return result;
//		}
//		try {
//			result.setSuccess(true);
//			result.setObj(ids);
//		} catch (Exception exp) {
//			result.setSuccess(false);
//			result.setMsg("导出Excel失败");
//			LOGGER.error("导出Excel失败:{}", exp);
//		}
//
//		return result;
//	}

	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
//	public String addPage() {
//		return "admin/examYearly/peopleAdd";
//	}
	public String addPage(String peopleCode, Model model){
		People people = peopleService.findPeopleByCode(peopleCode);
		if (people == null)
			people = new People();
		model.addAttribute("people",people);
		return "/admin/examYearly/examYearlyAdd";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(ExamYearly examYearly, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
		Result result = new Result();
		try{
			examYearlyService.add(examYearly);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		}catch(Exception e){
			LOGGER.error("添加失败：{}",e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			examYearlyService.deleteById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("删除失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}



	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response, String ids) {

		if (StringUtils.isBlank(ids)) {
			LOGGER.error("Excel:{}", "请选择有效数据!");
		}
		try {
			examYearlyService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			LOGGER.error("导出Excel失败:{}", exp);
		}
	}

	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "/admin/examYearly/importExcelPage";
	}
	
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(
			@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			boolean flag = examYearlyService.insertByImport(files);
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

//	@RequestMapping("/batchDel")
//	@ResponseBody
//	public Result batchDel(String ids) {
//		Result result = new Result();
//
//		if (StringUtils.isEmpty(ids)) {
//			result.setSuccess(true);
//			result.setMsg("请选择至少一条");
//			return result;
//		}
//
//		try {
//			String[] idList = ids.split(",");
//			examYearlyService.batchDeletePeopleByIds(idList);
//			result.setSuccess(true);
//			result.setMsg("批量删除成功");
//			return result;
//		} catch (Exception exp) {
//			LOGGER.error("批量删除失败:{}", exp);
//			result.setMsg(exp.getMessage());
//			return result;
//		}
//	}
}

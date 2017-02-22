package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.vo.PeopleSalaryVo;
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
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ExamMonthlyVo;

/**
 * Created by mengfw on 2017/1/21.
 */
@Controller
@RequestMapping("/examMonthly")
public class ExamMonthlyController extends BaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(ExamMonthlyController.class);

	@Autowired
	private ExamMonthlyService examMonthlyService;
	@Autowired
	private PeopleService peopleService;

	/**
	 * 月度考核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/examMonthly/people";
	}

	@RequestMapping(value = "/contractmanager", method = RequestMethod.GET)
	public String contractManager(){
		return "/admin/examMonthly/peopleContract";
	}

	@RequestMapping(value="/contract2manager", method = RequestMethod.GET)
	public String contract2Manager(){
		return "/admin/examMonthly/peopleContract2";
	}

	@RequestMapping(value="/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleVo peopleVo, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String,Object> condition = PeopleVo.CreateCondition(peopleVo);
		pageInfo.setCondition(condition);
		peopleService.findDataGrid(pageInfo,request);

		return pageInfo;
	}

	@RequestMapping(value="/examMonthlyListPage", method = RequestMethod.GET)
	public String examMonthlyListPage(Integer id, Model model){

		People people = peopleService.findPeopleById(id);
		if (people != null){
			model.addAttribute("code",people.getCode());
			model.addAttribute("name",people.getName());
		}else{
			model.addAttribute("code","");
			model.addAttribute("name","");
		}
		return "/admin/examMonthly/examMonthlyList";
	}


	@RequestMapping(value="/examMonthlyGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo examMonthlyGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page,rows);
		String peopleCode = request.getParameter("code");
		Map<String,Object> condition = Maps.newHashMap();
		condition.put("peopleCode",peopleCode);
		pageInfo.setCondition(condition);
		examMonthlyService.findDataGrid(pageInfo);

		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id){
		Result result = new Result();
		try{
			examMonthlyService.deleteById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
			LOGGER.error("删除工资记录失败：{}",e);
			result.setMsg(e.getMessage());
			return result;
		}
	}


	@RequestMapping("/addPage")
	public String addPage(String peopleCode, Model model){
		People people = peopleService.findPeopleByCode(peopleCode);
		if (people == null)
			people = new People();
		model.addAttribute("people",people);
		return "/admin/examMonthly/examMonthlyAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(ExamMonthly examMonthly, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
		Result result = new Result();
		try{
			examMonthlyService.add(examMonthly);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		}catch(Exception e){
			LOGGER.error("添加失败：{}",e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/editPage")
	public String editPage(Long id, Model model){
		ExamMonthly examMonthly = examMonthlyService.findExamMonthlyById(id);
		model.addAttribute("examMonthly",examMonthly);
		return "/admin/examMonthly/examMonthlyEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(ExamMonthly examMonthly){
		Result result = new Result();
		try{
			examMonthlyService.update(examMonthly);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		}catch(Exception e){
			LOGGER.error("修改失败：{}",e);
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
			examMonthlyService.exportExcel(response,ids.split(","));
		}catch(Exception exp){
			LOGGER.error("导出Excel失败:{}",exp);
		}
	}

	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage(){
		return "/admin/examMonthly/importExcelPage";
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
		Result result = new Result();
		if(files!=null&&files.length>0){
			boolean flag=examMonthlyService.insertByImport(files);
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


}

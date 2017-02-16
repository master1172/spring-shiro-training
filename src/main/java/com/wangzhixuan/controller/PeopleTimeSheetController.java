package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.PeopleService;
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

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTimesheetVo;

/**
 * fengjunfeng <br>
 * 考勤管理
 */

@Controller
@RequestMapping("/peopleTimesheet")
public class PeopleTimeSheetController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(PeopleTimeSheetController.class);

	@Autowired
	private PeopleTimesheetService timesheetService;

	@Autowired
	private PeopleService peopleService;

	/**
	 * 人员管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "admin/peopleTimesheet/people";
	}

	/**
	 * 人员管理列表
	 *
	 * @param
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, PeopleVo peopleVo, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String,Object> condition = PeopleVo.CreateCondition(peopleVo);
		pageInfo.setCondition(condition);
		peopleService.findDataGrid(pageInfo,request);

		return pageInfo;
	}

	@RequestMapping(value="/timesheetListPage", method = RequestMethod.GET)
	public String timesheetListPage(Integer id, Model model){

		People people = peopleService.findPeopleById(id);
		if (people != null){
			model.addAttribute("code",people.getCode());
			model.addAttribute("name",people.getName());
		}else{
			model.addAttribute("code","");
			model.addAttribute("name","");
		}
		return "/admin/peopleTimesheet/peopleTimesheetList";
	}

	@RequestMapping(value="/timesheetGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo timesheetGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
		PageInfo pageInfo = new PageInfo(page,rows);
		String peopleCode = request.getParameter("code");
		Map<String,Object> condition = Maps.newHashMap();
		condition.put("peopleCode",peopleCode);
		pageInfo.setCondition(condition);
		timesheetService.findDataGrid(pageInfo);

		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id){
		Result result = new Result();
		try{
			timesheetService.deleteByPrimaryKey(id.intValue());
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
			LOGGER.error("删除人员考勤记录失败：{}",e);
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
		return "/admin/peopleTimesheet/peopleTimesheetAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(PeopleTimesheet peopleTimesheet,@RequestParam(value="fileName",required=false)CommonsMultipartFile file){
		Result result = new Result();
		try{
			timesheetService.insert(peopleTimesheet);
			result.setSuccess(true);
			result.setMsg("添加成功!");
			return result;
		}catch(Exception e){
			LOGGER.error("添加人员考勤记录失败：{}",e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/editPage")
	public String editPage(Long id, Model model){
		PeopleTimesheetVo peopleTimesheetVo = timesheetService.findPeopleTimesheetVoById(id.intValue());
		model.addAttribute("peopleTimesheetVo",peopleTimesheetVo);
		return "/admin/peopleTimesheet/peopleTimesheetEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(PeopleTimesheet peopleTimesheet){
		Result result = new Result();
		try{
			timesheetService.updateByPrimaryKey(peopleTimesheet);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		}catch(Exception e){
			LOGGER.error("修改人员考勤失败：{}",e);
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
			timesheetService.exportExcel(response,ids.split(","));
		}catch(Exception exp){
			LOGGER.error("导出Excel失败:{}",exp);
		}
	}

	@RequestMapping(value="/importExcelPage", method=RequestMethod.GET)
	public String importExcelPage(){
		return "admin/peopleTimesheet/importExcelPage";
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
		Result result = new Result();
		if(files!=null&&files.length>0){
			boolean flag=timesheetService.insertByImport(files);
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

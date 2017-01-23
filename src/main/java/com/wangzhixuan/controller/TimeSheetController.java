package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.service.TimesheetService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.TimesheetVo;

/**
 * fengjunfeng <br>
 * 考勤管理
 */

@Controller
@RequestMapping("/timesheet")
public class TimeSheetController extends BaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(TimeSheetController.class);

	@Autowired
	private TimesheetService timesheetService;

	/**
	 * 人员管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "admin/timesheet/people";
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
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(HttpServletRequest request, TimesheetVo timesheetVo, Integer page, Integer rows, String sort,
			String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = TimesheetVo.CreateCondition(timesheetVo);
		
		pageInfo.setCondition(condition);
		timesheetService.findDataGrid(pageInfo);

		return pageInfo;
	}

	@RequestMapping(value = "/advSearchPage", method = RequestMethod.GET)
	public String advSearchPage() {
		return "admin/timesheet/peopleSearch";
	}

	@RequestMapping(value = "/exportSearchPage", method = RequestMethod.GET)
	public String exportSearchPage() {
		return "admin/timesheet/peopleSearch";
	}

	@RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
	@ResponseBody
	public Result exportSearch(HttpServletResponse response, TimesheetVo peoplevo) {

		Result result = new Result();

		// TODO
		Map<String, Object> condition = TimesheetVo.CreateCondition(peoplevo);

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCondition(condition);
//		String ids = timesheetService.findPeopleIDsByCondition(pageInfo);
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

		return result;
	}

	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "admin/timesheet/peopleAdd";
	}

	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "admin/timesheet/importExcelPage";
	}

	/**
	 * 添加用户
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(Timesheet timesheet) {
		Result result = new Result();
		try {

			timesheetService.insert(timesheet);

			result.setSuccess(true);
			result.setMsg("添加成功");
			return result;
		} catch (Exception e) {
			LOGGER.error("添加用户失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/editPage")
	public String editPage(Integer id, Model model) {
		Timesheet timesheet = timesheetService.selectByPrimaryKey(id);
		model.addAttribute("timesheet", timesheet);
		return "admin/timesheet/peopleEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(Timesheet timesheet,
			@RequestParam(value = "fileName", required = false) CommonsMultipartFile file) {
		Result result = new Result();
		try {
			timesheetService.updateByPrimaryKey(timesheet);
			LOGGER.info(JSON.toJSONString(timesheet));
			
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		} catch (Exception e) {
			LOGGER.error("修改人员失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/transferPage")
	public String transferPage(Long id, Model model) {
//		PeopleVo peopleVo = peopleService.findPeopleVoById(id);
//		model.addAttribute("peopleVo", peopleVo);
		return "/admin/timesheet/peopleTransfer";
	}

	@RequestMapping("/transfer")
	@ResponseBody
	public Result transfer(PeopleTransfer peopleTransfer) {
		Result result = new Result();
		try {
//			People people = peopleService.findPeopleById(peopleTransfer.getId());
//
//			// 更新人员状态
//			if (people != null) {
//				people.setStatus(ConstUtil.PEOPLE_TRANSFER);
//				peopleService.updatePeople(people);
//				// 添加一条人员调动记录
//				peopleTransferService.addPeopleTransfer(peopleTransfer, null);
//			}

			result.setSuccess(true);
			result.setMsg("调动成功");
			return result;
		} catch (Exception e) {
			LOGGER.error("调动人员失败:{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id) {
		Result result = new Result();
		try {
			timesheetService.deleteByPrimaryKey(id);
			
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("删除人员失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/batchRetire")
	@ResponseBody
	public Result batchRetire(String ids) {
		Result result = new Result();

		if (StringUtils.isEmpty(ids)) {
			result.setSuccess(true);
			result.setMsg("请选择至少一个人");
			return result;
		}

		try {
			String[] idList = ids.split(",");
//			peopleService.batchRetirePeopleByIds(idList);
			result.setSuccess(true);
			result.setMsg("批量转入退休人员成功");
			return result;
		} catch (Exception exp) {
			LOGGER.error("批量转入退休人员失败:{}", exp);
			result.setMsg(exp.getMessage());
			return result;
		}
	}

	@RequestMapping("/batchDeath")
	@ResponseBody
	public Result batchDeath(String ids) {
		Result result = new Result();

		if (StringUtils.isEmpty(ids)) {
			result.setSuccess(true);
			result.setMsg("请选择至少一个人");
			return result;
		}

		try {
			String[] idList = ids.split(",");
//			peopleService.batchDeathPeopleByIds(idList);
			result.setSuccess(true);
			result.setMsg("批量转入已故人员成功");
			return result;
		} catch (Exception exp) {
			LOGGER.error("批量转入已故人员失败:{}", exp);
			result.setMsg(exp.getMessage());
			return result;
		}
	}

	@RequestMapping("/batchDel")
	@ResponseBody
	public Result batchDel(String ids) {
		Result result = new Result();

		if (StringUtils.isEmpty(ids)) {
			result.setSuccess(true);
			result.setMsg("请选择至少一个人");
			return result;
		}

		try {
			String[] idList = ids.split(",");
//			peopleService.batchDeletePeopleByIds(idList);
			result.setSuccess(true);
			result.setMsg("批量删除人员成功");
			return result;
		} catch (Exception exp) {
			LOGGER.error("批量删除人员失败:{}", exp);
			result.setMsg(exp.getMessage());
			return result;
		}
	}

	/**
	 * 批量调入W
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			LOGGER.info(JSON.toJSONString(files));
			boolean flag = timesheetService.insertByImport(files);
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
			LOGGER.error("Excel:{}", "请选择有效数据!");
		}
		try {
			timesheetService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			LOGGER.error("导出Excel失败:{}", exp);
		}
	}

	/**
	 * 导出Word
	 */
	@RequestMapping("/exportWord")
	public void exportWord(HttpServletResponse response, String ids) {

		if (StringUtils.isEmpty(ids)) {
			LOGGER.error("导出Word:{}", "请选择一条有效数据!");
		}
		try {
//			peopleService.exportWord(response, ids);
		} catch (Exception exp) {
			LOGGER.error("导出Word:{}", exp);
		}
	}
}

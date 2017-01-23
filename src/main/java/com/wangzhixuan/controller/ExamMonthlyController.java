package com.wangzhixuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private static Logger LOGGER = LoggerFactory
			.getLogger(ExamMonthlyController.class);
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
		return "/admin/exammonthly/people";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(ExamMonthlyVo examMonthlyVo, Integer page,
			Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = ExamMonthlyVo
				.CreateCondition(examMonthlyVo);
		pageInfo.setCondition(condition);
		examMonthlyService.findDataGrid(pageInfo);

		return pageInfo;
	}

	@RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
	@ResponseBody
	public Result exportSearch(HttpServletResponse response,
			ExamMonthlyVo examMonthlyVo) {

		Result result = new Result();

		Map<String, Object> condition = ExamMonthlyVo
				.CreateCondition(examMonthlyVo);

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCondition(condition);
		String ids = examMonthlyService.findIDsByCondition(pageInfo);

		if (StringUtils.isBlank(ids)) {
			result.setSuccess(false);
			result.setMsg("未找到有效数据");
			LOGGER.error("Excel:{}", "无有效数据");
			return result;
		}
		try {
			result.setSuccess(true);
			result.setObj(ids);
		} catch (Exception exp) {
			result.setSuccess(false);
			result.setMsg("导出Excel失败");
			LOGGER.error("导出Excel失败:{}", exp);
		}

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result add(ExamMonthlyVo examMonthlyVo) {
		Result result = new Result();
		try {
			People people = peopleService.findPeopleByName(examMonthlyVo
					.getName());
			if (people == null || StringUtils.isBlank(people.getCode())) {
				throw new RuntimeException("用户不存在");
			}
			examMonthlyVo.setPeopleCode(people.getCode());
			examMonthlyService.add(examMonthlyVo);
			result.setSuccess(true);
			result.setMsg("添加成功");
			return result;
		} catch (Exception e) {
			LOGGER.error("添加失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			examMonthlyService.deleteById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("删除失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(ExamMonthlyVo examMonthlyVo) {
		Result result = new Result();
		try {
			examMonthlyService.update(examMonthlyVo);
			result.setSuccess(true);
			result.setMsg("修改成功!");
			return result;
		} catch (Exception e) {
			LOGGER.error("修改失败：{}", e);
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
			examMonthlyService.exportExcel(response, ids.split(","));
		} catch (Exception exp) {
			LOGGER.error("导出Excel失败:{}", exp);
		}
	}

	@RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
	public String importExcelPage() {
		return "admin/exammonthly/importExcelPage";
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Result importExcel(
			@RequestParam(value = "fileName", required = false) CommonsMultipartFile[] files) {
		Result result = new Result();
		if (files != null && files.length > 0) {
			boolean flag = examMonthlyService.insertByImport(files);
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

	@RequestMapping("/batchDel")
	@ResponseBody
	public Result batchDel(String ids) {
		Result result = new Result();

		if (StringUtils.isEmpty(ids)) {
			result.setSuccess(true);
			result.setMsg("请选择至少一条");
			return result;
		}

		try {
			String[] idList = ids.split(",");
			examMonthlyService.batchDeletePeopleByIds(idList);
			result.setSuccess(true);
			result.setMsg("批量删除成功");
			return result;
		} catch (Exception exp) {
			LOGGER.error("批量删除失败:{}", exp);
			result.setMsg(exp.getMessage());
			return result;
		}
	}
}

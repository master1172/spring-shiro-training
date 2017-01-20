package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ExamYearlyVo;
import com.wangzhixuan.vo.PeopleVo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by mengfw on 2017/1/20.
 */
@Controller
@RequestMapping("/examYearly")
public class ExamYearlyController extends BaseController{
  private static Logger LOGGER = LoggerFactory.getLogger(ExamYearlyController.class);
  @Autowired
  private ExamYearlyService examYearlyService;
  @Autowired
  private PeopleService peopleService;
  @RequestMapping(value="/dataGrid", method= RequestMethod.POST)
  @ResponseBody
  public PageInfo dataGrid( ExamYearlyVo examYearlyVo, Integer page, Integer rows, String sort, String order){
    PageInfo pageInfo = new PageInfo(page, rows);
    Map<String,Object> condition = ExamYearlyVo.CreateCondition(examYearlyVo);
    pageInfo.setCondition(condition);
    examYearlyService.findDataGrid(pageInfo);

    return pageInfo;
  }
  @RequestMapping(value="/exportSearch", method = RequestMethod.POST)
  @ResponseBody
  public Result exportSearch(HttpServletResponse response, ExamYearlyVo examYearlyVo) {

    Result result = new Result();

    Map<String,Object> condition = ExamYearlyVo.CreateCondition(examYearlyVo);

    PageInfo pageInfo = new PageInfo();
    pageInfo.setCondition(condition);
    String ids = examYearlyService.findIDsByCondition(pageInfo);

    if (StringUtils.isBlank(ids)){
      result.setSuccess(false);
      result.setMsg("未找到有效数据");
      LOGGER.error("Excel:{}","无有效数据");
      return result;
    }
    try{
      result.setSuccess(true);
      result.setObj(ids);
    }catch(Exception exp){
      result.setSuccess(false);
      result.setMsg("导出Excel失败");
      LOGGER.error("导出Excel失败:{}",exp);
    }

    return result;
  }
  @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
  @ResponseBody
  public Result add(ExamYearly examYearly) {
    Result result = new Result();
    try {
      People people = peopleService.findPeopleByName(examYearly.getName());
      if(people == null || StringUtils.isBlank(people.getCode())){
        throw new RuntimeException("用户不存在");
      }
      examYearly.setPeopleCode(people.getCode());
      examYearlyService.add(examYearly);
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
  public Result delete(Long id){
    Result result = new Result();
    try{
      examYearlyService.deleteById(id);
      result.setMsg("删除成功！");
      result.setSuccess(true);
      return result;
    }catch(RuntimeException e){
      LOGGER.error("删除失败：{}",e);
      result.setMsg(e.getMessage());
      return result;
    }
  }
  @RequestMapping("/edit")
  @ResponseBody
  public Result edit(ExamYearly examYearly){
    Result result = new Result();
    try{
      examYearlyService.update(examYearly);
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
  public void exportExcel(HttpServletResponse response,String ids){

    if (StringUtils.isBlank(ids)){
      LOGGER.error("Excel:{}","请选择有效数据!");
    }
    try{
      examYearlyService.exportExcel(response,ids.split(","));
    }catch(Exception exp){
      LOGGER.error("导出Excel失败:{}",exp);
    }
  }
  @RequestMapping("/exportWord")
  public void exportWord(HttpServletResponse response,String ids){

    if (StringUtils.isEmpty(ids)){
      LOGGER.error("导出Word:{}","请选择一条有效数据!");
    }
    try{
      examYearlyService.exportWord(response,ids);
    }catch(Exception exp){
      LOGGER.error("导出Word:{}",exp);
    }
  }
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
  @ResponseBody
  public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
    Result result = new Result();
    if(files!=null&&files.length>0){
      boolean flag=examYearlyService.insertByImport(files);
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
  @RequestMapping("/batchDel")
  @ResponseBody
  public Result batchDel(String ids){
    Result result = new Result();

    if (StringUtils.isEmpty(ids)){
      result.setSuccess(true);
      result.setMsg("请选择至少一条");
      return result;
    }

    try{
      String[] idList = ids.split(",");
      examYearlyService.batchDeletePeopleByIds(idList);
      result.setSuccess(true);
      result.setMsg("批量删除成功");
      return result;
    }catch(Exception exp){
      LOGGER.error("批量删除失败:{}",exp);
      result.setMsg(exp.getMessage());
      return result;
    }
  }
}

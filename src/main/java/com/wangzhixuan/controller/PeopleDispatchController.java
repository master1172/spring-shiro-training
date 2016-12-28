package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.service.PeopleDispatchService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDispatchVo;
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
 * Created by sterm on 2016/12/29.
 */
@Controller
@RequestMapping("/peopleDispatch")
public class PeopleDispatchController extends BaseController{

    private static Logger LOGGER = LoggerFactory.getLogger(PeopleDispatchController.class);

    @Autowired
    private PeopleDispatchService peopleDispatchService;

    @RequestMapping(value="/manager", method= RequestMethod.GET)
    public String manager(){
        return "admin/peopleDispatch/people";
    }

    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleDispatchVo peopleDispatchVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = PeopleDispatchVo.CreateCondition(peopleDispatchVo);
        pageInfo.setCondition(condition);
        peopleDispatchService.findDataGrid(pageInfo);

        return pageInfo;
    }

    @RequestMapping(value="/advSearchPage", method = RequestMethod.GET)
    public String advSearchPage(){
        return "admin/peopleDispatch/peopleSearch";
    }

    @RequestMapping(value="/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage(){
        return "admin/peopleDispatch/peopleSearch";
    }

    @RequestMapping(value="/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, PeopleDispatchVo peopleDispatchVo){
        Result result = new Result();
        Map<String, Object> condition = PeopleDispatchVo.CreateCondition(peopleDispatchVo);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);

        String ids = peopleDispatchService.findPeopleDispatchIDsByCondition(pageInfo);

        if(StringUtils.isBlank(ids)){
            result.setSuccess(false);
            result.setMsg("没找到有效数据");
            LOGGER.error("Excel:{}","无有效数据");
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

    @RequestMapping(value="/addPage",method = RequestMethod.GET)
    public String addPage(){
        return "admin/peopleDispatch/peopleAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleDispatch peopleDispatch, @RequestParam(value="fileName", required = false)CommonsMultipartFile file){

        Result result = new Result();
        try{
            peopleDispatchService.addPeopleDispatch(peopleDispatch,file);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;

        }catch(RuntimeException exp){
            LOGGER.error("添加日工资人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }

    }

    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        PeopleDispatch peopleDispatch = peopleDispatchService.findPeopleDispatchById(id);
        model.addAttribute("people",peopleDispatch);
        return "/admin/peopleDispatch/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleDispatch peopleDispatch, @RequestParam(value="fileName", required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleDispatchService.updatePeopleDispatch(peopleDispatch, file);
            result.setSuccess(true);
            result.setMsg("修改成功");
        }catch(RuntimeException exp){
            LOGGER.error("修改派遣人员失败:{}",exp);
            result.setMsg(exp.getMessage());
        }

        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            peopleDispatchService.deletePeopleDispatchById(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
        }catch(RuntimeException exp){
            LOGGER.error("删除派遣人员失败:{}",exp);
            result.setMsg(exp.getMessage());
        }
        return result;
    }

    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(String ids){
        Result result = new Result();
        if(StringUtils.isBlank(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleDispatchService.batchDeletePeopleDispatchByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量删除派遣人员成功");
        }catch(RuntimeException exp){
            LOGGER.error("批量删除派遣人员失败:{}",exp);
            result.setMsg(exp.getMessage());
        }

        return result;
    }

    @RequestMapping(value="/importExcelPage", method = RequestMethod.GET)
    public String importExcelPage(){
        return "admin/peopleDispatch/importExcelPage";
    }

    @RequestMapping(value="/importExcel", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
        Result result = new Result();
        if (files != null && files.length > 0){
            boolean flag = peopleDispatchService.insertByImport(files);
            result.setSuccess(flag);
            if(!flag){
                result.setMsg("导入过程中出现意外");
            }else{
                result.setMsg("导入成功");
            }
        }else{
            result.setSuccess(false);
            result.setMsg("请选择至少一个Excel文件");
        }

        return result;
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, String ids){
        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择至少一条有效数据");
        }
        try{
            peopleDispatchService.exportExcel(response, ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }

    @RequestMapping("/exportWord")
    public void exportWord(HttpServletResponse response, String ids){
        if(StringUtils.isBlank(ids)){
            LOGGER.error("导出Word:{}","请选择至少一条有效数据");
        }
        try{
            peopleDispatchService.exportWord(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }
}

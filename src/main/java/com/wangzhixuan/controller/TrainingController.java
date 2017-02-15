package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Training;
import com.wangzhixuan.service.TrainingService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.TrainingVo;
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
 * Created by sterm on 2017/2/14.
 */
@Controller
@RequestMapping("/training")
public class TrainingController {

    private static Logger LOGGER = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private TrainingService trainingService;


    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/training/people";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, TrainingVo trainingVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = trainingVo.CreateCondition(trainingVo);
        pageInfo.setCondition(condition);
        trainingService.findDataGrid(pageInfo, request);

        return pageInfo;
    }

    @RequestMapping(value = "/advSearchPage" , method = RequestMethod.GET)
    public String advSearchPage(){
        return "/admin/training/peopleSearch";
    }

    @RequestMapping(value = "/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage(){
        return "/admin/training/peopleSearch";
    }

    @RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, TrainingVo trainingVo){
        Result result = new Result();
        Map<String, Object> condition = TrainingVo.CreateCondition(trainingVo);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids= trainingService.findPeopleIDsByCondition(pageInfo);

        if(StringUtils.isBlank(ids)){
            result.setSuccess(false);
            result.setMsg("未找到数据");
            return result;
        }

        try{
            result.setSuccess(true);
            result.setObj(ids);
        }catch(Exception exp){
            result.setSuccess(false);
            result.setMsg("导出Excel失败");
        }

        return result;
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(){
        return "/admin/training/peopleAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public Result add(Training training){
        Result result = new Result();
        try{
            trainingService.add(training);
            result.setMsg("添加成功");
            result.setSuccess(true);
        }catch(Exception exp){
            result.setMsg(exp.getMessage());
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/editPage")
    public String editPage(Integer id, Model model){
        Training training = trainingService.findTrainingById(id);
        model.addAttribute("training", training);
        return "/admin/training/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Training training){
        Result result = new Result();
        try{
            trainingService.update(training);
            result.setSuccess(true);
            result.setMsg("修改成功");
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.getMessage());
        }

        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();

        try{
            trainingService.delete(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.getMessage());
        }

        return result;
    }

    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(String ids){
        Result result = new Result();

        if(StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请至少选择一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            trainingService.batchDel(idList);
            result.setSuccess(true);
            result.setMsg("批量删除成功");
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg("批量删除失败");
        }

        return result;
    }

    @RequestMapping(value = "importExcelPage")
    public String importExcelPage(){
        return "/admin/training/importExcelPage";
    }

    /**
     * 批量调入W
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
        Result result = new Result();
        if(files!=null&&files.length>0){
            boolean flag = trainingService.insertByImport(files);
            result.setSuccess(flag);
            if(!flag){
                result.setMsg("系统繁忙，请稍后再试！");
                result.setSuccess(false);
            }
        }else{
            result.setSuccess(false);
            result.setMsg("请选择附件！");
        }
        return result;
    }

    /**
     * 导出Excel
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,String ids){

        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择有效数据!");
        }
        try{
            trainingService.exportExcel(response,ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }
}

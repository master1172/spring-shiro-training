package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Abroad;
import com.wangzhixuan.service.AbroadService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.AbroadVo;
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
import java.util.List;
import java.util.Map;

/**
 * Created by sterm on 2017/2/14.
 */
@Controller
@RequestMapping("/abroad")
public class AbroadController {

    private static Logger LOGGER = LoggerFactory.getLogger(AbroadController.class);

    @Autowired
    private AbroadService abroadService;


    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(Model model){

        List<Abroad> abroadList = abroadService.findPeopleReturnedWithoutReturnPassport();

        String passportReturnPeopleNameList = "目前无人需要提醒";

        if (abroadList != null && abroadList.size()>0){
            passportReturnPeopleNameList = "请如下人员尽快归还护照：";
            for(int i=0; i<abroadList.size(); i++){
                Abroad abroad = abroadList.get(i);
                if (abroad == null || StringUtils.isBlank(abroad.getName()))
                    continue;
                passportReturnPeopleNameList = passportReturnPeopleNameList + abroad.getName() + ",";
            }
        }

        model.addAttribute("peopleNames",passportReturnPeopleNameList);

        return "/admin/abroad/people";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, AbroadVo abroadVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = AbroadVo.CreateCondition(abroadVo);
        pageInfo.setCondition(condition);
        abroadService.findDataGrid(pageInfo, request);

        return pageInfo;
    }

    @RequestMapping(value = "/advSearchPage" , method = RequestMethod.GET)
    public String advSearchPage(){
        return "/admin/abroad/peopleSearch";
    }

    @RequestMapping(value = "/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage(){
        return "/admin/abroad/peopleSearch";
    }

    @RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, AbroadVo abroadVo){
        Result result = new Result();
        Map<String, Object> condition = AbroadVo.CreateCondition(abroadVo);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids= abroadService.findPeopleIDsByCondition(pageInfo);

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
        return "/admin/abroad/peopleAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public Result add(Abroad abroad){
        Result result = new Result();
        try{
            abroadService.add(abroad);
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
        Abroad abroad = abroadService.findAbroadById(id);
        model.addAttribute("abroad", abroad);
        return "/admin/abroad/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Abroad abroad){
        Result result = new Result();
        try{
            abroadService.update(abroad);
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
            abroadService.delete(id);
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
            abroadService.batchDel(idList);
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
        return "/admin/abroad/importExcelPage";
    }

    /**
     * 批量调入W
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
        Result result = new Result();
        if(files!=null&&files.length>0){
            boolean flag = abroadService.insertByImport(files);
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
            abroadService.exportExcel(response,ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }

    @RequestMapping("/exportJuniorOfficalReview")
    public void exportJuniorOfficalReview(HttpServletResponse response, String ids){
        if (StringUtils.isEmpty(ids)){
            LOGGER.error("导出Word:{}","请选择一条有效数据!");
        }
        try{
            abroadService.exportJuniorOfficalReview(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

    @RequestMapping("/exportSeniorOfficalReview")
    public void exportSeniorOfficalReview(HttpServletResponse response, String ids){
        if (StringUtils.isEmpty(ids)){
            LOGGER.error("导出Word:{}","请选择一条有效数据!");
        }
        try{
            abroadService.exportSeniorOfficalReview(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

    @RequestMapping("/exportRetireOfficalReview")
    public void exportRetireOfficalReview(HttpServletResponse response, String ids){
        if (StringUtils.isEmpty(ids)){
            LOGGER.error("导出Word:{}","请选择一条有效数据!");
        }
        try{
            abroadService.exportRetireOfficalReview(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

    @RequestMapping("/exportRecordExcel")
    public void exportRecordExcel(HttpServletResponse response, String ids){
        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择有效数据!");
        }
        try{
            abroadService.exportRecordExcel(response,ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }
}

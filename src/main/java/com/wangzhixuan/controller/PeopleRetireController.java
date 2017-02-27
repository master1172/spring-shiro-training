package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireVo;
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
 * Created by administrator_cernet on 2016/11/22.
 */
@Controller
@RequestMapping("/peopleRetire")
public class PeopleRetireController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleRetireController.class);

    @Autowired
    private PeopleRetireService peopleRetireService;

    /**
     * 人员管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleRetire/people";
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
    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleRetireVo peopleRetirevo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleRetireVo.CreateCondition(peopleRetirevo);
        pageInfo.setCondition(condition);
        peopleRetireService.findDataGrid(pageInfo);

        return pageInfo;
    }

    @RequestMapping(value="/advSearchPage", method = RequestMethod.GET)
    public String advSearchPage(){
        return "admin/peopleRetire/peopleSearch";
    }

    @RequestMapping(value="/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage() { return "admin/peopleRetire/peopleSearch";}

    @RequestMapping(value="/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, PeopleRetireVo peopleRetirevo) {

        Result result = new Result();

        Map<String,Object> condition = PeopleRetireVo.CreateCondition(peopleRetirevo);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids = peopleRetireService.findPeopleRetireIDsByCondition(pageInfo);

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

    @RequestMapping(value="/addPage", method=RequestMethod.GET)
    public String addPage(){
        return "admin/peopleRetire/peopleAdd";
    }
    @RequestMapping(value="/importExcelPage", method=RequestMethod.GET)
    public String importExcelPage(){
        return "admin/peopleRetire/importExcelPage";
    }
    /**
     * 添加用户
     *
     * @param peopleRetire
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleRetire peopleRetire,@RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
        Result result = new Result();
        try {
            peopleRetireService.addPeopleRetire(peopleRetire,file);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(Integer id, Model model){
        PeopleRetire peopleRetire = peopleRetireService.findPeopleRetireById(id);
        model.addAttribute("peopleRetire",peopleRetire);
        return "/admin/peopleRetire/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleRetire peopleRetire, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleRetireService.updatePeopleRetire(peopleRetire,file);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(RuntimeException e){
            LOGGER.error("修改人员失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        try{
            peopleRetireService.deletePeopleRetireById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除人员失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleRetireService.batchDeletePeopleRetireByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量删除人员成功");
            return result;
        }catch(Exception exp){
            LOGGER.error("批量删除人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }
    /**
     * 批量调入W
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result importExcel(@RequestParam(value="fileName",required=false)CommonsMultipartFile[] files){
        Result result = new Result();
        if(files!=null&&files.length>0){
            boolean flag=peopleRetireService.insertByImport(files);
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
    /**
     * 导出Excel
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,String ids){

        if (StringUtils.isBlank(ids)){
            LOGGER.error("Excel:{}","请选择有效数据!");
        }
        try{
            peopleRetireService.exportExcel(response,ids.split(","));
        }catch(Exception exp){
            LOGGER.error("导出Excel失败:{}",exp);
        }
    }
    /**
     * 导出Word
     */
    @RequestMapping("/exportWord")
    public void exportWord(HttpServletResponse response,String ids){

        if (StringUtils.isEmpty(ids)){
            LOGGER.error("导出Word:{}","请选择一条有效数据!");
        }
        try{
            peopleRetireService.exportWord(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

    @RequestMapping("/batchNormal")
    @ResponseBody
    public Result batchNormal(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleRetireService.batchConvertFromRetireToNormalByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量正常人员成功");
            return result;
        }catch(Exception exp){
            LOGGER.error("批量正常人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/batchRehire")
    @ResponseBody
    public Result batchRehire(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleRetireService.batchConvertFromRetireToRehireByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量返聘人员成功");
            return result;
        }catch(Exception exp){
            LOGGER.error("批量返聘人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/batchDeath")
    @ResponseBody
    public Result batchDeath(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleRetireService.batchConvertFromRetireToDeathByIds(idList);
            result.setSuccess(true);
            result.setMsg("转为已故人员成功");
            return result;
        }catch(Exception exp){
            LOGGER.error("转为已故人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/retireReview")
    public void retireView(HttpServletResponse response,String ids){
        try{
            peopleRetireService.retireReview(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

}



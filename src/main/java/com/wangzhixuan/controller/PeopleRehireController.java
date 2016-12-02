package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.PeopleRehire;
import com.wangzhixuan.service.PeopleRehireService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRehireVo;
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
@RequestMapping("/peopleRehire")
public class PeopleRehireController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleRehireController.class);

    @Autowired
    private PeopleRehireService peopleRehireService;

    /**
     * 人员管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleRehire/people";
    }

    /**
     * 人员管理列表
     *
     * @param peopleRehire
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleRehireVo peopleRehirevo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleRehireVo.CreateCondition(peopleRehirevo);
        pageInfo.setCondition(condition);
        peopleRehireService.findDataGrid(pageInfo);

        return pageInfo;
    }

    @RequestMapping(value="/advSearchPage", method = RequestMethod.GET)
    public String advSearchPage(){
        return "admin/peopleRehire/peopleSearch";
    }

    @RequestMapping(value="/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage() { return "admin/peopleRehire/peopleSearch";}

    @RequestMapping(value="/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, PeopleRehireVo peopleRehirevo) {

        Result result = new Result();

        Map<String,Object> condition = PeopleRehireVo.CreateCondition(peopleRehirevo);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids = peopleRehireService.findPeopleRehireIDsByCondition(pageInfo);

        if (StringUtils.isBlank(ids)){
            result.setSuccess(false);
            result.setMsg("未找到有效数据");
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

    @RequestMapping(value="/addPage", method=RequestMethod.GET)
    public String addPage(){
        return "admin/peopleRehire/peopleAdd";
    }
    @RequestMapping(value="/importExcelPage", method=RequestMethod.GET)
    public String importExcelPage(){
        return "admin/peopleRehire/importExcelPage";
    }
    /**
     * 添加用户
     *
     * @param peopleRehire
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleRehire peopleRehire,@RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
        Result result = new Result();
        try {
            peopleRehireService.addPeopleRehire(peopleRehire,file);
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
    public String editPage(Long id, Model model){
        PeopleRehire peopleRehire = peopleRehireService.findPeopleRehireById(id);
        model.addAttribute("peopleRehire",peopleRehire);
        return "/admin/peopleRehire/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleRehire peopleRehire, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleRehireService.updatePeopleRehire(peopleRehire,file);
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
    public Result delete(Long id){
        Result result = new Result();
        try{
            peopleRehireService.deletePeopleRehireById(id);
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
            peopleRehireService.batchDeletePeopleRehireByIds(idList);
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
            boolean flag=peopleRehireService.insertByImport(files);
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
            peopleRehireService.exportExcel(response,ids.split(","));
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
            peopleRehireService.exportWord(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

}


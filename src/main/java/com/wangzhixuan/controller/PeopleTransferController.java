package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.service.PeopleTransferService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTransferVo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/22.
 */
@Controller
@RequestMapping("/peopleTransfer")
public class PeopleTransferController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleTransferController.class);

    @Autowired
    private PeopleTransferService peopleTransferService;

    @Autowired
    private PeopleService peopleService;

    /**
     * 人员管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleTransfer/people";
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
    public PageInfo dataGrid(HttpServletRequest request, PeopleTransferVo peopleTransferVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleTransferVo.CreateCondition(peopleTransferVo);

        String includeHistory = request.getParameter("includeHistory");
        String inQuery        = request.getParameter("inQuery");

        if (StringUtils.isBlank(includeHistory) && StringUtils.isBlank(inQuery))
            condition.put("status",ConstUtil.PEOPLE_TRANSFER);

        List<String> peopleCodeList = peopleTransferService.findPeopleTransferCodeListByCondition(condition);
        if (peopleCodeList != null && peopleCodeList.size() > 0){
            condition.put("codeList", peopleCodeList.toArray());
        }else{
            condition.put("sex", -1);
        }

        pageInfo.setCondition(condition);
        peopleService.findDataGrid2(pageInfo,request);
        return pageInfo;
    }

    @RequestMapping(value="/transferListGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo transferListGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        String peopleCode = request.getParameter("code");
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode", peopleCode);
        pageInfo.setCondition(condition);
        peopleTransferService.findTransferListDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value="/advSearchPage", method = RequestMethod.GET)
    public String advSearchPage(){
        return "admin/peopleTransfer/peopleSearch";
    }

    @RequestMapping(value="/exportSearchPage", method = RequestMethod.GET)
    public String exportSearchPage() { return "admin/peopleTransfer/peopleSearch";}

    @RequestMapping(value="/exportSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result exportSearch(HttpServletResponse response, PeopleTransferVo peopleTransfervo) {

        Result result = new Result();

        Map<String,Object> condition = PeopleTransferVo.CreateCondition(peopleTransfervo);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCondition(condition);
        String ids = peopleTransferService.findPeopleTransferIDsByCondition(pageInfo);

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

    @RequestMapping(value="/transferListPage", method = RequestMethod.GET)
    public String transferListPage(Integer id, Model model){

        People people = peopleService.findPeopleById(id);

        if (people != null){
            model.addAttribute("code",people.getCode());
        }else{
            model.addAttribute("code","");
        }
        return "/admin/peopleTransfer/peopleTransferList";
    }

    @RequestMapping(value="/transferPage", method=RequestMethod.GET)
    public String transferPage(Integer id, String code, Model model){

        People people = null;

        if (id != null)
            people = peopleService.findPeopleById(id);
        if (StringUtils.isNoneBlank(code))
            people = peopleService.findPeopleByCode(code);

        if (people != null){
            model.addAttribute("people",people);
        }else{
            people = new People();
            model.addAttribute("people",people);
        }

        return "admin/peopleTransfer/peopleTransfer";
    }

    /**
     * 添加用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/transfer", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleTransferVo peopleTransferVo,@RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
        Result result = new Result();
        try {
            peopleTransferService.addPeopleTransfer(peopleTransferVo,file);
            result.setSuccess(true);
            result.setMsg("人员调动成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("人员调动失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(Integer id, Model model){
        PeopleTransfer peopleTransfer = peopleTransferService.findPeopleTransferById(id);
        model.addAttribute("peopleTransfer",peopleTransfer);
        return "/admin/peopleTransfer/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleTransfer peopleTransfer, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleTransferService.updatePeopleTransfer(peopleTransfer,file);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(RuntimeException e){
            LOGGER.error("修改失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        try{
            peopleTransferService.delete(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除记录失败：{}",e);
            result.setMsg(e.getMessage());
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
            boolean flag=peopleTransferService.insertByImport(files);
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
        if (StringUtils.isNoneBlank(ids)){
            try{
                peopleTransferService.exportExcel(response,ids);
            }catch(Exception exp){
                LOGGER.error("导出Excel失败:{}",exp);
            }
        }else{
            LOGGER.error("Excel:{}","请选择有效数据!");
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
            peopleTransferService.exportWord(response,ids);
        }catch(Exception exp){
            LOGGER.error("导出Word:{}",exp);
        }
    }

    @RequestMapping("/transferBack")
    @ResponseBody
    public Result transferBack(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一个人");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            peopleService.batchTransferBackPeopleByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量调回人员成功");
            return result;
        }catch(Exception exp){
            LOGGER.error("批量调回人员失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/exportBusinessLetter")
    public void exportBusinessLetter(HttpServletResponse response, String ids){
        if(StringUtils.isEmpty(ids))
            return;

        try{
            peopleTransferService.exportBusinessLetter(response,ids);
        }catch (Exception exp){
        }
    }

    @RequestMapping("/exportExecutionLetter")
    public void exportExecutionLetter(HttpServletResponse response, String ids){
        if(StringUtils.isEmpty(ids))
            return;

        try{
            peopleTransferService.exportExecutionLetter(response,ids);
        }catch (Exception exp){
        }
    }

    @RequestMapping("/exportSalaryLetter")
    public void exportSalaryLetter(HttpServletResponse response, String ids){
        if(StringUtils.isEmpty(ids))
            return;
        try{
            peopleTransferService.exportSalaryLetter(response,ids);
        }catch (Exception exp){
        }
    }
}




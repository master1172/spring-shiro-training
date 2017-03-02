package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.model.PeopleTotal;
import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.service.SocialSecurityService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleVo;
import com.wangzhixuan.vo.SocialSecurityBaseVo;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sterm on 2017/2/26.
 */
@Controller
@RequestMapping("/socialSecurity")
public class SocialSecurityController extends BaseController{

    @Autowired
    SocialSecurityService socialSecurityService;

    @Autowired
    PeopleTotalMapper peopleTotalMapper;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/socialSecurity/people";
    }

    @RequestMapping(value = "/contractmanager", method = RequestMethod.GET)
    public String managercontract() {
        return "/admin/socialSecurity/contractPeople";
    }

    @RequestMapping(value = "/contract2manager", method = RequestMethod.GET)
    public String managercontract2() {
        return "/admin/socialSecurity/contract2People";
    }


    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, SocialSecurityBaseVo socialSecurityVo, Integer page, Integer rows, String sort, String order){

        socialSecurityVo.setStatus(ConstUtil.PEOPLE_NORMAL);
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = SocialSecurityBaseVo.CreateCondition(socialSecurityVo);
        pageInfo.setCondition(condition);
        socialSecurityService.findDataGrid(pageInfo, request);
        return pageInfo;
    }

    @RequestMapping(value="/contractDataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo contractDataGrid(HttpServletRequest request, SocialSecurityBaseVo socialSecurityVo, Integer page, Integer rows, String sort, String order){

        socialSecurityVo.setStatus(ConstUtil.PEOPLE_CONTRACT);
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = SocialSecurityBaseVo.CreateCondition(socialSecurityVo);
        pageInfo.setCondition(condition);
        socialSecurityService.findDataGrid(pageInfo, request);
        return pageInfo;
    }

    @RequestMapping(value="/contract2DataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo contract2DataGrid(HttpServletRequest request, SocialSecurityBaseVo socialSecurityVo, Integer page, Integer rows, String sort, String order){

        socialSecurityVo.setStatus(ConstUtil.PEOPLE_CONTRACT_2);
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = SocialSecurityBaseVo.CreateCondition(socialSecurityVo);
        pageInfo.setCondition(condition);
        socialSecurityService.findDataGrid(pageInfo, request);
        return pageInfo;
    }

    @RequestMapping(value="/editBasePage", method = RequestMethod.GET)
    public String editBasePage(Integer id, Model model){
        PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(id);
        model.addAttribute("people",peopleTotal);
        return "/admin/socialSecurity/base";
    }

    @RequestMapping(value="/editBase")
    @ResponseBody
    public Result editBase(SocialSecurityBase socialSecurityBase){
        Result result = new Result();
        try{
            socialSecurityService.updateBase(socialSecurityBase);
            result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping(value = "/socialSecurityListPage")
    public String socialSecurityListPage(Integer id, Model model){
        PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(id);
        model.addAttribute("people",peopleTotal);
        return "/admin/socialSecurity/socialSecurityList";
    }

    @RequestMapping(value = "/socialSecurityGrid")
    @ResponseBody
    public PageInfo socialSecurityGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page,rows);
        String peopleCode = request.getParameter("code");
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);
        socialSecurityService.findSocialSecurityGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage")
    public String addPage(String peopleCode, Model model){
        PeopleTotal peopleTotal = peopleTotalMapper.selectByCode(peopleCode);
        SocialSecurityBase socialSecurityBase = new SocialSecurityBase();

        if (peopleTotal != null){
            socialSecurityBase.setCode(peopleTotal.getCode());
            socialSecurityBase.setLifeInsuranceBase(peopleTotal.getLifeInsuranceBase());
            socialSecurityBase.setJobInsuranceBase(peopleTotal.getJobInsuranceBase());
            socialSecurityBase.setWoundInsuranceBase(peopleTotal.getWoundInsuranceBase());
            socialSecurityBase.setBirthInsuranceBase(peopleTotal.getBirthInsuranceBase());
            socialSecurityBase.setHealthInsuranceBase(peopleTotal.getHealthInsuranceBase());
            socialSecurityBase.setAnnuityBase(peopleTotal.getAnnuityBase());
        }

        model.addAttribute("socialSecurityBase",socialSecurityBase);

        return "/admin/socialSecurity/add";
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Result add(SocialSecurity socialSecurity){
        Result result = new Result();
        try{
            socialSecurityService.insert(socialSecurity);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping(value = "/editPage")
    public String editPage(Integer id, Model model){
        SocialSecurity socialSecurity = socialSecurityService.findSocialSecurityById(id);
        if (socialSecurity != null){
            model.addAttribute("socialSecurity",socialSecurity);
        }else{
            model.addAttribute("socialSecurity", new SocialSecurity());
        }

        return "/admin/socialSecurity/edit";
    }

    @RequestMapping(value="/edit")
    @ResponseBody
    public Result edit(SocialSecurity socialSecurity){
        Result result = new Result();
        try{
            socialSecurityService.update(socialSecurity);
            result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        try{
            socialSecurityService.delete(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
            return result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping(value="/exportExcel")
    public void exportExcel(HttpServletResponse response, String ids){
        if(StringUtils.isBlank(ids)){
            return;
        }

        try{
            socialSecurityService.exportExcel(response, ids.split(","));
        }catch (Exception exp){
            return;
        }
    }

}

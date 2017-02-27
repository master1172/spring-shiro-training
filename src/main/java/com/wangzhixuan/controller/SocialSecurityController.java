package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.model.PeopleTotal;
import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.service.SocialSecurityService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleVo;
import com.wangzhixuan.vo.SocialSecurityBaseVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, SocialSecurityBaseVo socialSecurityVo, Integer page, Integer rows, String sort, String order){
        String status = request.getParameter("status");

        if (StringUtils.isNoneBlank(status)){
            socialSecurityVo.setStatus(Integer.valueOf(status));
        }

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
}

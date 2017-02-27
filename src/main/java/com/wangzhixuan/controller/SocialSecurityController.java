package com.wangzhixuan.controller;

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
}

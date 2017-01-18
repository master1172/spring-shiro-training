package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleVo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sterm on 2017/1/13.
 */
@Controller
@RequestMapping("/peoplesalary")
public class PeopleSalaryController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleSalaryController.class);

    @Autowired
    private PeopleSalaryService peopleSalaryService;

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value="/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/peoplesalary/people";
    }

    @RequestMapping(value="/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleVo peopleVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleVo.CreateCondition(peopleVo);
        pageInfo.setCondition(condition);
        peopleService.findDataGrid(pageInfo,request);

        return pageInfo;
    }

    @RequestMapping(value="/salaryListPage", method = RequestMethod.GET)
    public String salaryListPage(Long id, Model model){

        People people = peopleService.findPeopleById(id);
        if (people != null){
            model.addAttribute("code",people.getCode());
            model.addAttribute("name",people.getName());
        }else{
            model.addAttribute("code","");
            model.addAttribute("name","");
        }
        return "/admin/peopleSalary/peopleSalaryList";
    }

    @RequestMapping(value="/salaryGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo salaryGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page,rows);
        String peopleCode = request.getParameter("code");
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);
        peopleSalaryService.findDataGrid(pageInfo,request);

        return pageInfo;
    }




}

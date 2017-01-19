package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleSalaryVo;
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
@RequestMapping("/peopleSalary")
public class PeopleSalaryController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleSalaryController.class);

    @Autowired
    private PeopleSalaryService peopleSalaryService;

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value="/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/peopleSalary/people";
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

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            peopleSalaryService.deleteSalaryById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除工资记录失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/addPage")
    public String addPage(String peopleCode, Model model){
        People people = peopleService.findPeopleByCode(peopleCode);
        if (people == null)
            people = new People();
        model.addAttribute("people",people);
        return "/admin/peopleSalary/peopleSalaryAdd";
    }

    @RequestMapping(value = "/add", headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleSalary peopleSalary){
        Result result = new Result();
        try{
            peopleSalaryService.addSalary(peopleSalary);
            result.setSuccess(true);
            result.setMsg("添加成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("添加工资失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        PeopleSalaryVo peopleSalaryVo = peopleSalaryService.findPeopleSalaryVoById(id);
        model.addAttribute("peopleSalaryVo",peopleSalaryVo);
        return "/admin/peopleSalary/peopleSalaryEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleSalary peopleSalary){
        Result result = new Result();
        try{
            peopleSalaryService.updateSalary(peopleSalary);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改工资失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}

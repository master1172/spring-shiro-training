package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleTotal;
import com.wangzhixuan.model.SalaryChangeRecord;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.service.SalaryChangeRecordService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sterm on 2017/2/21.
 */
@Controller
@RequestMapping("/salaryChangeRecord")
public class SalaryChangeRecordController extends BaseController{

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private SalaryChangeRecordService salaryChangeRecordService;

    @Autowired
    private PeopleTotalMapper peopleTotalMapper;

    @RequestMapping("/changeListPage")
    public String changeListPage(Integer id, Model model){
        People people = peopleService.findPeopleById(id);
        model.addAttribute("code",people.getCode());

        return "admin/salaryChangeRecord/salaryChangeList";
    }

    @RequestMapping("/changeGrid")
    @ResponseBody
    public PageInfo changeGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order ){
        PageInfo pageInfo = new PageInfo(page,rows);
        String peopleCode = request.getParameter("code");
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);

        salaryChangeRecordService.findSalaryChangeDataGrid(pageInfo,request);
        return pageInfo;
    }

    @RequestMapping("/addPage")
    public String addPage(String peopleCode, Model model){
        PeopleTotal peopleTotal = peopleTotalMapper.selectByCode(peopleCode);
        model.addAttribute("people", peopleTotal);
        return "admin/salaryChangeRecord/salaryChangeAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(SalaryChangeRecord salaryChangeRecord){
        Result result = new Result();
        try{
            salaryChangeRecordService.addSalaryChangeRecord(salaryChangeRecord);
            result.setMsg("添加成功");
            result.setSuccess(true);
            return result;
        }catch (Exception exp){
            result.setMsg(exp.toString());
            result.setSuccess(false);
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        try{
            salaryChangeRecordService.delete(id);
            result.setMsg("删除成功");
            result.setSuccess(true);
            return result;
        }catch (Exception exp){
            result.setMsg(exp.toString());
            result.setSuccess(false);
            return result;
        }
    }

    @RequestMapping("/editSalaryPage")
    public String editSalaryPage(Integer id, Model model){
        SalaryChangeRecord salaryChangeRecord = salaryChangeRecordService.findSalaryChangeRecordById(id);
        model.addAttribute("people", salaryChangeRecord);
        return "admin/salaryChangeRecord/salaryChangeEdit";
    }

    @RequestMapping("/editSalary")
    @ResponseBody
    public Result editSalary(SalaryChangeRecord salaryChangeRecord){
        Result result = new Result();
        try{
            salaryChangeRecordService.update(salaryChangeRecord);
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

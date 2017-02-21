package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.People;
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
}

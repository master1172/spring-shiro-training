package com.wangzhixuan.controller;

import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sterm on 2016/11/22.
 */
@Controller
@RequestMapping("/peopledaily")
public class PeopleDailyController extends BaseController{

    @Autowired
    private PeopleDailyService peopleDailyService;

    @RequestMapping(value="/manager", method=RequestMethod.GET)
    public String manager(){
        return "/admin/peopledaily/manager";
    }

    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest PeopleDailyVo peopleDailyVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = PeopleDailyVo.CreateCondition(peopleDailyVo);
        pageInfo.setCondition(condition);
        peopleDailyService.findDataGrid(pageInfo);

        return pageInfo;
    }

}

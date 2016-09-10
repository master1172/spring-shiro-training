package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by liushaoyang on 2016/9/8.
 * 人员信息管理
 */

@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

    @Autowired
    private PeopleService peopleService;

    /**
     * 人员管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/people/people";
    }

    /**
     * 人员管理列表
     *
     * @param people
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(People people, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(people.getName())){
            condition.put("name", people.getName());
        }

        if(StringUtils.isNoneBlank(people.getJob())){
            condition.put("job",people.getJob());
        }

        pageInfo.setCondition(condition);
        peopleService.findDataGrid(pageInfo);

        return pageInfo;
    }

    @RequestMapping(value="/addPage", method=RequestMethod.GET)
    public String addPage(){
        return "admin/people/peopleAdd";
    }

    /**
     * 添加用户
     *
     * @param people
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(People people) {
        Result result = new Result();
        try {
            peopleService.addPeople(people);
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
        People people = peopleService.findPeopleById(id);
        model.addAttribute("people",people);
        return "/admin/people/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(People people){
        Result result = new Result();
        try{
            peopleService.updatePeople(people);
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
            peopleService.deletePeopleById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除人员失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}

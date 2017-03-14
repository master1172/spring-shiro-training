package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Dict;
import com.wangzhixuan.service.DictService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sterm on 2017/3/11.
 */
@Controller
@RequestMapping("/dict/national")
public class NationalController {

    @Autowired
    private DictService dictService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/dict/national";
    }


    @RequestMapping(value="/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page,rows);
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("tableName","dict_national");
        pageInfo.setCondition(condition);
        dictService.findDataGrid(pageInfo,request);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(Model model){
        model.addAttribute("tableName","dict_national");
        return "admin/dict/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public Result add(Dict dict){
        Result result = new Result();
        try{
            dictService.add(dict);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(Integer id, Model model){
        Dict dict = new Dict();
        dict.setId(id);
        dict.setTableName("dict_national");
        Dict dict1 = dictService.findById(dict);
        model.addAttribute("dict",dict1);
        return "/admin/dict/edit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Dict dict){
        Result result = new Result();
        try{
            dict.setTableName("dict_national");
            dictService.update(dict);
            result.setSuccess(true);
            result.setMsg("修改成功");
            return  result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result result = new Result();
        Dict dict = new Dict();
        dict.setId(id);
        dict.setTableName("dict_national");
        try{
            dictService.delete(dict);
            result.setSuccess(true);
            result.setMsg("删除成功");
            return  result;
        }catch (Exception exp){
            result.setSuccess(false);
            result.setMsg(exp.toString());
            return result;
        }
    }
}

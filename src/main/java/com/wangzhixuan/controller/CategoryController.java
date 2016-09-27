package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Category;
import com.wangzhixuan.service.CategoryService;
import com.wangzhixuan.vo.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liushaoyang on 2016/9/18.
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    private static Logger Logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/manager")
    public String manager(){
        return "/admin/category/category";
    }


    @RequestMapping(value = "/treeGrid")
    @ResponseBody
    public List<Category> treeGrid(){
        List<Category> treeGrid = categoryService.findTreeGrid();
        return treeGrid;
    }

    @RequestMapping(value = "/tree", method= RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree(){
        List<Tree> trees = categoryService.findAllTrees();
        return trees;
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "/admin/category/categoryAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Category category){
        Result result = new Result();
        try{
            categoryService.addCategory(category);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        }catch(Exception exp){
            Logger.error("添加分类失败{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id){
        Category category = categoryService.findCategoryByid(id);
        request.setAttribute("category", category);
        return "/admin/category/categoryEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Category category){
        Result result = new Result();
        try{
            categoryService.updateCategory(category);
            result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
        }catch(Exception exp){
            Logger.error("修改分类失败{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            categoryService.deleteCategoryByid(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
            return result;
        }catch(Exception exp){
            Logger.error("删除分类失败{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

}

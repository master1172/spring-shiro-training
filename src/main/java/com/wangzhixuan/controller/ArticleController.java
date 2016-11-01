package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Article;
import com.wangzhixuan.service.ArticleService;
import com.wangzhixuan.service.CategoryService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ArticleVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liushaoyang on 2016/10/9.
 */

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController{
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/manager", method = RequestMethod.GET)
    public String manager(){
        return "/admin/article/article";
    }

    @RequestMapping(value="/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(ArticleVo articleVo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page,rows);
        Map<String,Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(articleVo.getTitle())){
            condition.put("title",articleVo.getTitle());
        }

        if(articleVo.getCategoryId() != null){

            Long categoryId = articleVo.getCategoryId();
            List<Long> categoryIdList   = categoryService.findAllSubCategoryIds(categoryId);
            if (categoryIdList == null){
                categoryIdList = new ArrayList<>();
            }
            categoryIdList.add(categoryId);
            condition.put("categoryIdList", categoryIdList);
        }

        condition.put("startTime",null);
        condition.put("endTime",null);

        pageInfo.setCondition(condition);
        articleService.findDataGrid(pageInfo);

        return pageInfo;

    }

    @RequestMapping(value="/addPage", method = RequestMethod.GET)
    public String addPage(){
        return "/admin/article/articleAdd";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Article article){
        Result result = new Result();
        try{
            if (article != null){
                article.setContent(StringEscapeUtils.unescapeHtml4(article.getContent()));
            }
            articleService.addArticle(article);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        }catch(Exception exp){
            Logger.error("添加文章失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping(value="/editPage", method = RequestMethod.GET)
    public String editPage(Long id, Model model){
        Article article = articleService.findArticleById(id);
        model.addAttribute("article",article);
        return "/admin/article/articleEdit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(Article article){
        Result result = new Result();
        try{
            if (article != null){
                article.setContent(StringEscapeUtils.unescapeHtml4(article.getContent()));
            }

            articleService.updateArticle(article);
            result.setSuccess(true);
            result.setMsg("修改文章成功");
            return result;
        }catch(Exception exp){
            Logger.error("修改文章失败{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            articleService.deleteArticleById(id);
            result.setSuccess(true);
            result.setMsg("删除文章成功");
            return result;
        }catch(Exception exp){
            Logger.error("删除文章失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(String ids){
        Result result = new Result();

        if (StringUtils.isEmpty(ids)){
            result.setSuccess(true);
            result.setMsg("请选择至少一篇文章");
            return result;
        }

        try{
            String[] idList = ids.split(",");
            articleService.batchDeleteArticleByIds(idList);
            result.setSuccess(true);
            result.setMsg("批量删除文章成功");
            return result;
        }catch(Exception exp){
            Logger.error("批量删除文章失败:{}",exp);
            result.setMsg(exp.getMessage());
            return result;
        }
    }

}

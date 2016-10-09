package com.wangzhixuan.controller;

import com.google.common.collect.Maps;
import com.wangzhixuan.service.ArticleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ArticleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            condition.put("categoryId", articleVo.getCategoryId());
        }

        pageInfo.setCondition(condition);
        articleService.findDataGrid(pageInfo);

        return pageInfo;

    }

}

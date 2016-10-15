package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.ArticleMapper;
import com.wangzhixuan.mapper.CategoryMapper;
import com.wangzhixuan.model.Article;
import com.wangzhixuan.model.Category;
import com.wangzhixuan.service.ArticleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ArticleVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liushaoyang on 2016/9/27.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Article findArticleByTitle(String title) {
        return articleMapper.findArticleByTitle(title);
    }

    @Override
    public Article findArticleById(Long id) {
        return articleMapper.findArticleById(id);
    }

    @Override
    public ArticleVo findArticleVoById(Long id) {
        Article article = articleMapper.findArticleById(id);

        if (article == null)
            return null;

        ArticleVo articleVo = new ArticleVo();

        try{
            PropertyUtils.copyProperties(articleVo,article);
        }catch (Exception exp){
            LOGGER.error("类型转换异常：{}",exp);
            throw new RuntimeException("类型转换异常：{}",exp);
        }

        Long categoryId = article.getCategoryId();
        Category category = categoryMapper.findCategoryById(categoryId);

        if (category != null){
            articleVo.setCategoryName(category.getName());
        }

        return articleVo;
    }



    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(articleMapper.findArticlePageCondition(pageInfo));
        pageInfo.setTotal(articleMapper.findArticlePageCount(pageInfo));
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public void batchDeleteArticleByIds(String[] ids){
        articleMapper.batchDeleteByIds(ids);
    }
}

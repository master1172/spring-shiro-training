package com.wangzhixuan.service;

import com.wangzhixuan.model.Article;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ArticleVo;

/**
 * Created by liushaoyang on 2016/9/27.
 */

public interface ArticleService {

    Article findArticleByTitle(String title);

    Article findArticleById(Long id);

    ArticleVo findArticleVoById(Long id);

    void findDataGrid(PageInfo pageInfo);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticleById(Long id);

    void batchDeleteArticleByIds(String[] ids);
}

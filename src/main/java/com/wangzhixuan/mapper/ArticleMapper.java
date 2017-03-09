package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Article;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ArticleVo;

import java.util.List;

/**
 * Created by liushaoyang on 2016/9/27.
 */
public interface ArticleMapper {

    int batchDeleteByIds(String[] ids);

    int deleteById(Long id);

    int insert(Article article);

    int updateArticle(Article article);

    Article findArticleByTitle(String title);

    Article findArticleById(Long id);

    List findArticlePageCondition(PageInfo pageInfo);

    int findArticlePageCount(PageInfo pageInfo);

    List<ArticleVo> findTop5Articles();
}

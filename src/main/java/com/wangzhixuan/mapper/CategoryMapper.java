package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Category;

import java.util.List;

/**
 * Created by liushaoyang on 2016/9/19.
 */
public interface CategoryMapper {

    int deleteCategoryById(Long id);

    int insert(Category category);

    int updateCategory(Category category);

    List<Category> findCategoryAllByPid(Long pid);

    List<Category> findAllCategory();

    Category findCategoryById(Long id);
}

package com.wangzhixuan.service;

import com.wangzhixuan.model.Category;
import com.wangzhixuan.vo.Tree;

import java.util.List;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public interface CategoryService {

    Category findCategoryByid(Long id);

    void addCategory(Category category);

    void deleteCategoryByid(Long id);

    void updateCategory(Category category);

    List<Tree> findAllTrees();

    List<Category> findTreeGrid();

    List<Category> findAllSubCategorys(Long parentId);

    List<Long> findAllSubCategoryIds(Long parentId);
}

package com.wangzhixuan.service.impl;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.CategoryMapper;
import com.wangzhixuan.model.Category;
import com.wangzhixuan.service.CategoryService;
import com.wangzhixuan.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryByid(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryByid(Long id) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> trees = Lists.newArrayList();

        List<Category> categoryRootNodes = categoryMapper.findCategoryAllByPid(null);

        if (categoryRootNodes != null){
            for(Category categoryNode: categoryRootNodes){
                Tree tree = new Tree();
                tree.setId(categoryNode.getId());
                tree.setText(categoryNode.getName());
                //tree.setIconCls();
            }
        }

        return trees;
    }
}

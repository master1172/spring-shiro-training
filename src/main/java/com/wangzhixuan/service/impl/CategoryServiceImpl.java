package com.wangzhixuan.service.impl;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.CategoryMapper;
import com.wangzhixuan.model.Category;
import com.wangzhixuan.service.CategoryService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushaoyang on 2016/9/18.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private static String ICON_FOLDER = "icon-folder";

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryByid(Long id) {
        return categoryMapper.findCategoryById(id);
    }

    @Override
    public void deleteCategoryByid(Long id) {
        List<Category> categorySubLists = categoryMapper.findCategoryAllByPid(id);

        if(categorySubLists != null){
            for(Category categoryNode: categorySubLists){
                deleteCategoryByid(categoryNode.getId());
            }
        }
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void addCategory(Category category){
        categoryMapper.insert(category);
    }

    @Override
    public List<Category> findTreeGrid() {
        return categoryMapper.findCategoryAll();
    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> trees = Lists.newArrayList();

        List<Category> categoryRootNodes = categoryMapper.findCategoryAllByPidNull();

        if (categoryRootNodes != null){
            for(Category categoryNode: categoryRootNodes){
                Tree tree = new Tree();
                tree.setId(categoryNode.getId());
                tree.setText(categoryNode.getName());
                tree.setIconCls(ICON_FOLDER);

                List<Tree> subTree = buildFullSubTree(tree);

                if (subTree != null){
                    tree.setChildren(subTree);
                }else{
                    tree.setState("closed");
                }
                trees.add(tree);
            }
        }

        return trees;
    }


    private List<Tree> buildFullSubTree(Tree rootNodeTree){
        if (rootNodeTree == null){
            return null;
        }

        if (rootNodeTree.getId() == null){
            return null;
        }

        List<Category> subTreeCategorys = categoryMapper.findCategoryAllByPid(rootNodeTree.getId());

        if(subTreeCategorys == null)
            return null;

        List<Tree> subTreeList = new ArrayList();

        for(Category category: subTreeCategorys){
            Tree subTree = new Tree();
            subTree.setId(category.getId());
            subTree.setText(category.getName());
            subTree.setIconCls(ICON_FOLDER);

            List<Tree> nextLevelTrees = buildFullSubTree(subTree);
            if(nextLevelTrees != null){
                subTree.setChildren(nextLevelTrees);
            }else{
                subTree.setState("closed");
            }

            subTreeList.add(subTree);
        }

        return subTreeList;
    }
}

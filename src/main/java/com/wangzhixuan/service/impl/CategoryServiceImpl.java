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
    public List<Category> findAllSubCategorys(Long parentId){

        List<Category> resultList = new ArrayList<>();

        List<Category> categoryList = categoryMapper.findCategoryAllByPid(parentId);

        for (Category categoryItem:categoryList){
            if (categoryItem == null || categoryItem.getId() == null)
                continue;

            resultList.add(categoryItem);

            List<Category> categorySubList = this.findAllSubCategorys(categoryItem.getId());

            if (categorySubList != null && categorySubList.size() > 0){
                for (Category categorySubItem : categorySubList){
                    resultList.add(categorySubItem);
                }
            }
        }

        return resultList;
    }

    @Override
    public List<Long> findAllSubCategoryIds(Long parentId){
        List<Category> categoryList = this.findAllSubCategorys(parentId);
        if (categoryList == null || categoryList.size() < 1)
            return null;

        List<Long> resultList = new ArrayList<Long>();

        for(Category categoryItem:categoryList){
            if (categoryItem == null || categoryItem.getId() == null)
                continue;

            resultList.add(categoryItem.getId());
        }

        return resultList;
    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> trees = Lists.newArrayList();

        List<Category> categoryRootNodes = categoryMapper.findCategoryAllByPidNull();

        if (categoryRootNodes != null){
            for(Category categoryNode: categoryRootNodes){
                Tree tree = buildSubTree(categoryNode);
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
            Tree subTree = buildSubTree(category);

            subTreeList.add(subTree);
        }

        return subTreeList;
    }

    private Tree buildSubTree(Category categoryNode) {
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
        return tree;
    }
}

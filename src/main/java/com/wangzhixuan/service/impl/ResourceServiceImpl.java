package com.wangzhixuan.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wangzhixuan.mapper.ResourceMapper;
import com.wangzhixuan.mapper.RoleMapper;
import com.wangzhixuan.mapper.UserRoleMapper;
import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.ResourceService;
import com.wangzhixuan.utils.Config;
import com.wangzhixuan.vo.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static String ICON_FOLDER = "icon-folder";
    private static String ICON_COMPANY = "icon-company";

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;


    private List<Tree> buildFullSubTree(Tree tree){
        if (tree == null)
            return null;

        List<Resource> subTreeResources = resourceMapper.findResourceAllByPid(tree.getId());

        if (subTreeResources == null){
            return null;
        }

        List<Tree> subTreeList = new ArrayList<>();

        for(Resource resource:subTreeResources){

            Tree subTree = new Tree();

            subTree.setId(resource.getId());
            subTree.setText(resource.getName());
            subTree.setIconCls(resource.getIcon());
            subTree.setAttributes(resource.getUrl());

            List<Tree> nextLevelTrees = buildFullSubTree(subTree);

            if (nextLevelTrees != null){
                subTree.setChildren(nextLevelTrees);
            }else{
                subTree.setState("closed");
            }

            subTreeList.add(subTree);
        }

        return subTreeList;
    }

    private List<Tree> buildSubTree(Tree tree, boolean displaySubTreeOnly){

        if (tree == null)
            return null;

        List<Resource> subTreeResources = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, tree.getId());

        if (subTreeResources == null){
            return null;
        }

        List<Tree> subTreeList = new ArrayList<>();

        for(Resource resource:subTreeResources){

            if (displaySubTreeOnly){
                if (resource.getIcon().equals(ICON_COMPANY) || resource.getIcon().equals(ICON_FOLDER)){
                    Tree subTree = new Tree();

                    subTree.setId(resource.getId());
                    subTree.setText(resource.getName());
                    subTree.setIconCls(resource.getIcon());
                    subTree.setAttributes(resource.getUrl());

                    List<Tree> nextLevelTrees = buildSubTree(subTree,displaySubTreeOnly);

                    if (nextLevelTrees != null){
                        subTree.setChildren(nextLevelTrees);
                    }else{
                        subTree.setState("closed");
                    }

                    subTreeList.add(subTree);
                }
            }else{
                Tree subTree = new Tree();

                subTree.setId(resource.getId());
                subTree.setText(resource.getName());
                subTree.setIconCls(resource.getIcon());
                subTree.setAttributes(resource.getUrl());

                List<Tree> nextLevelTrees = buildSubTree(subTree,displaySubTreeOnly);

                if (nextLevelTrees != null){
                    subTree.setChildren(nextLevelTrees);
                }else{
                    subTree.setState("closed");
                }

                subTreeList.add(subTree);
            }

        }

        return subTreeList;
    }


    @Override
    public List<Tree> findTree2(User user, boolean displayMenuOnly){
        List<Tree> trees = Lists.newArrayList();

            List<Resource> rootResourceList = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
            if (rootResourceList == null){
                return null;
            }

            for(Resource rootResource: rootResourceList){

                if (rootResource.getIcon().equals(ICON_COMPANY)  || rootResource.getIcon().equals(ICON_FOLDER)){
                    Tree rootTree = new Tree();
                    rootTree.setId(rootResource.getId());
                    rootTree.setText(rootResource.getName());
                    rootTree.setIconCls(rootResource.getIcon());
                    rootTree.setAttributes(rootResource.getUrl());

                    List<Tree> subTree = this.buildSubTree(rootTree,displayMenuOnly);

                    if (subTree != null){
                        rootTree.setChildren(subTree);
                    }
                    trees.add(rootTree);
                }
            }


        return trees;
    }

    @Override
    public List<Tree> findTree(User user) {
        List<Tree> trees = Lists.newArrayList();
        // 超级管理
        if (user.getLoginname().equals("admin")) {
            List<Resource> resourceFather = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }

            for (Resource resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

                if (resourceSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
            return trees;
        }

        // 普通用户
        Set<Resource> resourceIdList = Sets.newHashSet();
        List<Long> roleIdList = userRoleMapper.findRoleIdListByUserId(user.getId());
        for (Long i : roleIdList) {
            List<Resource> resourceIdLists = roleMapper.findResourceIdListByRoleIdAndType(i);
            for (Resource resource: resourceIdLists) {
                resourceIdList.add(resource);
            }
        }
        for (Resource resource : resourceIdList) {
                if (resource != null && resource.getPid() == null) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceIdList) {
                        if (resourceTwo.getPid() != null && resource.getId().longValue() == resourceTwo.getPid().longValue()) {
                            Tree treeTwo = new Tree();
                            treeTwo.setId(resourceTwo.getId());
                            treeTwo.setText(resourceTwo.getName());
                            treeTwo.setIconCls(resourceTwo.getIcon());
                            treeTwo.setAttributes(resourceTwo.getUrl());
                            tree.add(treeTwo);
                        }
                    }
                    treeOne.setChildren(tree);
                    trees.add(treeOne);
                }
        }
        return trees;
    }

    @Override
    public List<Resource> findResourceAll() {
        return resourceMapper.findResourceAll();
    }

    @Override
    public void addResource(Resource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public List<Tree> findAllTree() {
        List<Tree> trees = Lists.newArrayList();
        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon != null) {
                List<Tree> tree = Lists.newArrayList();
                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> treeOneList = Lists.newArrayList();

        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = Lists.newArrayList();

                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    /***************************************************/
                    List<Resource> resourceSons = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_BUTTON, resourceTwo.getId());

                    if (resourceSons == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = Lists.newArrayList();

                        for (Resource resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }

    @Override
    public List<Tree> findAllTrees2(){
        List<Tree> trees = Lists.newArrayList();

        List<Resource> rootResourceList = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (rootResourceList == null){
            return null;
        }

        for(Resource rootResource: rootResourceList){

            Tree rootTree = new Tree();
            rootTree.setId(rootResource.getId());
            rootTree.setText(rootResource.getName());
            rootTree.setIconCls(rootResource.getIcon());
            rootTree.setAttributes(rootResource.getUrl());

            List<Tree> subTree = this.buildFullSubTree(rootTree);

            if (subTree != null){
                rootTree.setChildren(subTree);
            }
            trees.add(rootTree);

        }

        return trees;
    }

    @Override
    public void updateResource(Resource resource) {
        int update = resourceMapper.updateResource(resource);
        if (update != 1) {
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        int delete = resourceMapper.deleteResourceById(id);
        if (delete != 1) {
            throw new RuntimeException("删除失败");
        }
    }

    public static void main(String[] args) {
        Long abc = 126L;
        Long def = 126L;
        System.out.println(abc == def);
    }

}

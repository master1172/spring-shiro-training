package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PeopleServiceImpl implements PeopleService{

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public People findPeopleById(Long id) {
        return peopleMapper.findPeopleById(id);
    }

    @Override
    public People findPeopleByName(String name) {
        return peopleMapper.findPeopleByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(peopleMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public void addPeople(People people) {
        peopleMapper.insert(people);
    }

    @Override
    public void updatePeople(People people) {
        peopleMapper.updatePeople(people);
    }

    @Override
    public void deletePeopleById(Long id) {
        peopleMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleByIds(String[] ids){
        peopleMapper.batchDeleteByIds(ids);
    }
}

package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleContract2Service {

    PeopleContract findPeopleContractById(Long id);

    PeopleContract findPeopleContractByName(String name);

    void findDataGrid(PageInfo pageInfo);

    void addPeopleContract(PeopleContract peopleContract,CommonsMultipartFile file);


    void updatePeopleContract(PeopleContract peopleContract, CommonsMultipartFile file);


    void deletePeopleContractById(Long id);

    void batchDeletePeopleContractByIds(String[] ids);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response,String[] idList);

    void exportWord(HttpServletResponse response,String id);


    String findPeopleContractIDsByCondition(PageInfo pageInfo);
    public PeopleContract findPeopleContractByCode(String code) ;
}


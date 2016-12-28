package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sterm on 2016/11/22.
 */
public interface PeopleDispatchService {

    PeopleDispatch findPeopleDispatchById(Long id);

    void findDataGrid(PageInfo pageInfo);

    void addPeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file);

    void updatePeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file);

    void deletePeopleDispatchById(Long id);

    void batchDeletePeopleDispatchByIds(String[] ids);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response, String[] idList);

    void exportWord(HttpServletResponse response, String id);

    String findPeopleDispatchIDsByCondition(PageInfo pageInfo);
}

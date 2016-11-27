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
public interface PeopleContractService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleContract findPeopleById(Long id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleContract findPeopleByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param people
     */
    void addPeople(PeopleContract people,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param people
     */
    void updatePeople(PeopleContract people, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleById(Long id);

    void batchDeletePeopleByIds(String[] ids);
    /**
     * 数据导入
     * @param list
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    /**
     * 导出Excel
     * @param response
     * @param id
     * @return
     */
    void exportExcel(HttpServletResponse response,String[] idList);
    /**
     * 导出Word
     * @param request
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response,String id);

    /**
     * 根据条件搜索ids
     * @param request
     * @param response
     * @param id
     * @return
     */
    String findPeopleIDsByCondition(PageInfo pageInfo);
}


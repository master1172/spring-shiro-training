package com.wangzhixuan.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleRetireService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleRetire findPeopleRetireById(Integer id);
    
    PeopleRetire findPeopleRetireByCode(String code);
    
    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleRetire findPeopleRetireByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peopleRetire
     */
    void addPeopleRetire(PeopleRetire peopleRetire,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peopleRetire
     */
    void updatePeopleRetire(PeopleRetire peopleRetire, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleRetireById(Integer id);

    void batchDeletePeopleRetireByIds(String[] ids);

    void batchConvertFromRetireToRehireByIds(String[] ids);
    /**
     * 数据导入
     * @param
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    /**
     * 导出Excel
     * @param response
     * @param
     * @return
     */
    void exportExcel(HttpServletResponse response,String[] idList);
    /**
     * 导出Word
     * @param
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response,String id);

    /**
     * 根据条件搜索ids
     * @param
     * @param
     * @param
     * @return
     */
    String findPeopleRetireIDsByCondition(PageInfo pageInfo);

    void batchConvertFromRetireToDeathByIds(String[] idList);
}




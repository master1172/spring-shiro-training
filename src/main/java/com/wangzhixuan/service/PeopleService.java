package com.wangzhixuan.service;

import com.wangzhixuan.model.People;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    People findPeopleById(Long id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    People findPeopleByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peoplevo
     */
    void addPeople(PeopleVo peoplevo,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peoplevo
     */
    void updatePeople(PeopleVo peoplevo, CommonsMultipartFile file);

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

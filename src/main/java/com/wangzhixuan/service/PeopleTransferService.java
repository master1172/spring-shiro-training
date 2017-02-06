package com.wangzhixuan.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleTransferService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleTransfer findPeopleTransferById(Long id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleTransfer findPeopleTransferByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    void findTransferListDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peopleTransfer
     */
    void addPeopleTransfer(PeopleTransfer peopleTransfer,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peopleTransfer
     */
    void updatePeopleTransfer(PeopleTransfer peopleTransfer, CommonsMultipartFile file);
    /**
     * 数据导入
     * @param files
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    /**
     * 导出Excel
     * @param response
     * @param ids
     * @return
     */
    void exportExcel(HttpServletResponse response,String ids);
    /**
     * 导出Word
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response,String id);

    /**
     * 根据条件搜索ids
     * @return
     */
    String findPeopleTransferIDsByCondition(PageInfo pageInfo);


    void delete(Long id);

    void exportBusinessLetter(HttpServletResponse response, String ids);
}





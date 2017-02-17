package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleTemp;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleTempService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleTemp findPeopleTempById(Integer id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleTemp findPeopleTempByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peopleTemp
     */
    void addPeopleTemp(PeopleTemp peopleTemp,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peopleTemp
     */
    void updatePeopleTemp(PeopleTemp peopleTemp, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleTempById(Integer id);

    void batchDeletePeopleTempByIds(String[] ids);
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
    String findPeopleTempIDsByCondition(PageInfo pageInfo);
}



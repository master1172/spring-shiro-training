package com.wangzhixuan.service;

import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.vo.PeopleRehireVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleRehire;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleRehireService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleRehire findPeopleRehireById(Integer id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleRehire findPeopleRehireByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peopleRehireVo
     */
    void addPeopleRehire(PeopleRehireVo peopleRehireVo, CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peopleRehireVo
     */
    void updatePeopleRehire(PeopleRehireVo peopleRehireVo, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleRehireById(Integer id);

    void batchDeletePeopleRehireByIds(String[] ids);
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
    String findPeopleRehireIDsByCondition(PageInfo pageInfo);
}



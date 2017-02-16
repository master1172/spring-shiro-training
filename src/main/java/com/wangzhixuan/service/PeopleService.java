package com.wangzhixuan.service;

import com.wangzhixuan.model.People;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTransferVo;
import com.wangzhixuan.vo.PeopleVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
    People findPeopleById(Integer id);

    PeopleVo findPeopleVoById(Integer id);

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
    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    /**
     * 添加人员
     *
     * @param peoplevo
     */
    void addPeople(PeopleVo peoplevo,CommonsMultipartFile file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 修改人员
     *
     * @param peoplevo
     */
    void updatePeople(PeopleVo peoplevo, CommonsMultipartFile file) throws InvocationTargetException, IllegalAccessException;

    void updatePeople(PeopleVo peopleVo);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleById(Integer id);

    void batchDeletePeopleByIds(String[] ids);

    void batchTransferBackPeopleByIds(String[] idList);

    void batchRetirePeopleByIds(String[] ids) throws InvocationTargetException, IllegalAccessException;

    void batchDeathPeopleByIds(String[] ids) throws InvocationTargetException, IllegalAccessException;
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
    String findPeopleIDsByCondition(PageInfo pageInfo);

    People findPeopleByCode(String code);

    List findPeopleListByIds(String[] idList);


    void transferPeople(PeopleTransferVo peopleTransferVo);
}

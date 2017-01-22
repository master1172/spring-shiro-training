package com.wangzhixuan.service;

import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleJobVo;
import com.wangzhixuan.vo.PeopleVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangwk on 2017/1/15.
 */
@Service
public interface PeopleJobService {

    /**
     * 人员职级列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    /**
     * 添加人员职级
     *
     * @param peoplejobvo
     */
    void addPeopleJob(PeopleJobVo peoplejobvo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;



    /**
     * 修改人员职级
     * @param peoplejobvo
     */
    void updatePeopleJob(PeopleJobVo peoplejobvo) throws InvocationTargetException, IllegalAccessException;

    void updatePeopleJob(PeopleJob peoplejob);
    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleJob findPeopleJobById(Long id);

    PeopleJobVo findPeopleJobVoById(Long id);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleJobById(Long id);

    void batchDeletePeopleJobByIds(String[] ids);

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
    void exportExcel(HttpServletResponse response, String[] idList);
    /**
     * 导出Word
     * @param request
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response, String id);


}

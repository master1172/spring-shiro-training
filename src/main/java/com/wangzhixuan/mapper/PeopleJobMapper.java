package com.wangzhixuan.mapper;

import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleJobVo;
import com.wangzhixuan.vo.PeopleVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by wangwk on 2017/1/15.
 */
public interface PeopleJobMapper {


    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleJobPageCondition(PageInfo pageInfo);
    int findPeopleJobPageCount(PageInfo pageInfo);
    /**
     * 添加人员职级
     *
     *
     * @param peoplejobvo
     *
     */
    void addPeopleJob(PeopleJobVo peoplejobvo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 添加人员
     *
     * @param people
     * @return
     */
    int insert(PeopleJob peoplejob);


    /**
     * 修改人员职级
     * @param peoplejobvo
     */
    void updatePeopleJob(PeopleJobVo peoplejobvo) throws InvocationTargetException, IllegalAccessException;
    void updatePeopleJob(PeopleJob peoplejob);

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

    int insertByImport(List<PeopleJob> list);
    /**
     * 导出Excel
     * @param response
     * @param id
     * @return
     */

    List selectPeopleJobVoByIds(String[] ids);
    void exportExcel(HttpServletResponse response, String[] idList);
    /**
     * 导出Word
     * @param request
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response, String id);

    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleJobVo findPeopleJobVoById(Long id);
    
}

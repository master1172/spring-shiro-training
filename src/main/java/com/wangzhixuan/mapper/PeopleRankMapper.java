package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.model.PeopleRank;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRankVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by wangwk on 2017/1/15.
 */
public interface PeopleRankMapper {

    /**
     * 人员薪级列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    /**
     * 添加人员薪级
     *
     * @param peopleRankVo
     */
    void addPeopleRank(PeopleRankVo peopleRankVo, CommonsMultipartFile file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 添加人员
     *
     * @param peopleRank
     * @return
     */
    int insert(PeopleRank peopleRank);

    /**
     * 修改人员薪级
     * @param peopleRankVo
     */
    void updatePeopleRank(PeopleRankVo peopleRankVo, CommonsMultipartFile file) throws InvocationTargetException, IllegalAccessException;

    void updatePeopleRank(PeopleRank peopleRank);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleRankById(Long id);

    void batchDeletePeopleRankByIds(String[] ids);
    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleRankPageCondition(PageInfo pageInfo);
    int findPeopleRankPageCount(PageInfo pageInfo);


    /**
     * 数据导入
     * @param list
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    int insertByImport(List<PeopleRank> list);
    /**
     * 导出Excel
     * @param response
     * @param id
     * @return
     */
    List selectPeopleRankVoByIds(String[] ids);
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
    PeopleRankVo findPeopleRankVoById(Long id);
    PeopleRank findPeopleRankById(Long id);
}

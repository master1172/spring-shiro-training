package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleRank;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRankVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangwk on 2017/1/15.
 */
@Service
public interface PeopleRankService {

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
    void addPeopleRank(PeopleRankVo peopleRankVo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleRank findPeopleRankById(Long id);

    PeopleRankVo findPeopleRankVoById(Long id);

    /**
     * 修改人员薪级
     *
     * @param peopleRankVo
     */
    void updatePeopleRank(PeopleRankVo peopleRankVo) throws InvocationTargetException, IllegalAccessException;

    void updatePeopleRank(PeopleRank peopleRank);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleRankById(Long id);

    void batchDeletePeopleRankByIds(String[] ids);

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

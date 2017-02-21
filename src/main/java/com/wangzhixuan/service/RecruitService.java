package com.wangzhixuan.service;

import com.wangzhixuan.model.Recruit;
import com.wangzhixuan.model.Training;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.RecruitVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sterm on 2017/2/14.
 */
public interface RecruitService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    String findPeopleIDsByCondition(PageInfo pageInfo);

    Recruit findRecruitById(Integer id);

    void add(RecruitVo recruitVo,CommonsMultipartFile file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    void update(RecruitVo peoplevo, CommonsMultipartFile file) throws InvocationTargetException, IllegalAccessException;

    void update(RecruitVo peopleVo);

    void delete(Integer id);

    void batchDel(String[] idList);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response, String[] split);

    RecruitVo findRecruitVoById(Integer id);
}

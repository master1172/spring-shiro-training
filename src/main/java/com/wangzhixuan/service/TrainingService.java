package com.wangzhixuan.service;

import com.wangzhixuan.model.Training;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sterm on 2017/2/14.
 */
public interface TrainingService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    String findPeopleIDsByCondition(PageInfo pageInfo);

    Training findTrainingById(Integer id);

    void add(Training training);

    void update(Training training);

    void delete(Integer id);

    void batchDel(String[] idList);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response, String[] split);
}

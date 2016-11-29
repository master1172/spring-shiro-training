package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleDaily;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sterm on 2016/11/22.
 */
public interface PeopleDailyService {

    PeopleDaily findPeopleDailyById(Long id);

    void findDataGrid(PageInfo pageInfo);

    void addPeopleDaily(PeopleDaily peopleDaily, CommonsMultipartFile file);

    void updatePeopleDaily(PeopleDaily peopleDaily, CommonsMultipartFile file);

    void deletePeopleDailyById(Long id);

    void batchDeletePeopleDailyByIds(String[] ids);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response, String[] idList);

    void exportWord(HttpServletResponse response, String id);

    String findPeopleDailyIDsByCondition(PageInfo pageInfo);
}

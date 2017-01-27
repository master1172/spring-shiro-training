package com.wangzhixuan.service;

import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.vo.PeopleTimesheetVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

/**
 * Created by fengjunfeng
 */
public interface PeopleTimesheetService {

    PeopleTimesheet selectByPrimaryKey(Integer id);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(PeopleTimesheet record);

    int updateByPrimaryKey(PeopleTimesheet record);
    
    void findDataGrid(PageInfo pageInfo);

	List<PeopleTimesheetVo> findPeopleTimeSheetListByCode(String code) ;

	boolean insertByImport(CommonsMultipartFile[] files);

	void exportExcel(HttpServletResponse response, String[] idList);

    PeopleTimesheetVo findPeopleTimesheetVoById(Integer id);
}


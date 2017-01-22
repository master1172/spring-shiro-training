package com.wangzhixuan.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by fengjunfeng
 */
public interface TimesheetService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Timesheet record);

    int insertSelective(Timesheet record);

    Timesheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Timesheet record);

    int updateByPrimaryKey(Timesheet record);
    
    void findDataGrid(PageInfo pageInfo);

	public Timesheet findTimeSheetByCode(String code) ;

	public boolean insertByImport(CommonsMultipartFile[] files);

	void exportExcel(HttpServletResponse response, String[] idList);
}


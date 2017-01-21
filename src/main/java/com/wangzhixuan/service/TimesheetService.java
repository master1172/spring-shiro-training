package com.wangzhixuan.service;

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

}


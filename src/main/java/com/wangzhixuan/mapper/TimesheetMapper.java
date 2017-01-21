package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.utils.PageInfo;

/**
 * @author fengjunfeng
 *
 */
public interface TimesheetMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Timesheet record);

	int insertSelective(Timesheet record);

	Timesheet selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Timesheet record);

	int updateByPrimaryKey(Timesheet record);

	List findDataGrid(PageInfo pageInfo);

	int findDataGridCount(PageInfo pageInfo);
}
package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTimesheetVo;

/**
 * @author fengjunfeng
 *
 */
public interface PeopleTimesheetMapper {

	PeopleTimesheet selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(PeopleTimesheet record);

	int updateByPrimaryKey(PeopleTimesheet record);

	List findPeopleTimesheetPageCondition(PageInfo pageInfo);

	int findPeopleTimesheetPageCount(PageInfo pageInfo);

	int insertByImport(List<PeopleTimesheet> list);

	List selectTimesheetVoByIds(String[] idList);

	List findTimesheetVoByPeopleCode(String peopleCode);

	PeopleTimesheetVo findTimesheetVoById(Integer id);

	List findTimesheetListByCodeAndDate(Map<String, Object> condition);

	List findPeopleTimesheetSumPageCondition(PageInfo pageInfo);

	int findPeopleTimesheetSumPageCount(PageInfo pageInfo);

	List findPeopleContractTimesheetSumPageCondition(PageInfo pageInfo);

	int findPeopleContractTimesheetSumPageCount(PageInfo pageInfo);

	List findPeopleContract2TimesheetSumPageCondition(PageInfo pageInfo);

	int findPeopleContract2TimesheetSumPageCount(PageInfo pageInfo);

	void deleteByPeopleCodeAndDate(Map<String, Object> condition);
}
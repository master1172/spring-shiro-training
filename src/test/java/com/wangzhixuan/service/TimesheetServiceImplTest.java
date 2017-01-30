package com.wangzhixuan.service;

import java.util.ArrayList;
import java.util.List;

import com.wangzhixuan.service.impl.PeopleTimesheetServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.model.PeopleTimesheet;


public class TimesheetServiceImplTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Test
	public void getPeopleInfoByExcel() {
		String path = "/Users/social-test/Downloads/timesheet.xlsx";
		List<PeopleTimesheet> list = new ArrayList<PeopleTimesheet>();
		PeopleTimesheetService timesheetServiceImpl = new PeopleTimesheetServiceImpl();
		//timesheetServiceImpl.getPeopleInfoByExcel(list, path);
		
		logger.info(JSON.toJSONString(list));
		
	}
	
	@Test
	public void name() {
		
	}
}

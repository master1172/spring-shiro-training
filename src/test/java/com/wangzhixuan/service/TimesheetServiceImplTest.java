package com.wangzhixuan.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.service.impl.TimesheetServiceImpl;

public class TimesheetServiceImplTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Test
	public void getPeopleInfoByExcel() {
		String path = "/Users/social-test/Downloads/timesheet.xlsx";
		List<Timesheet> list = new ArrayList<Timesheet>();
		TimesheetServiceImpl timesheetServiceImpl = new TimesheetServiceImpl();
		timesheetServiceImpl.getPeopleInfoByExcel(list, path);
		
		logger.info(JSON.toJSONString(list));
		
	}
	
	@Test
	public void name() {
		
	}
}

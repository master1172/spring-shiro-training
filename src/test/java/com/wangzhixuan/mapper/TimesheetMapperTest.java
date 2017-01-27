package com.wangzhixuan.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wangzhixuan.service.PeopleTimesheetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.service.impl.PeopleTimesheetServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TimesheetMapperTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PeopleTimesheetMapper timesheetMapper;
	
	@Test
	public void insertByImport() {
		List<PeopleTimesheet> list = new ArrayList<PeopleTimesheet>();
		PeopleTimesheet timesheet = new PeopleTimesheet();
		timesheet.setCheckDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		list.add(timesheet);
		timesheetMapper.insertByImport(list);
	}
	
	@Test
	public void getPeopleInfoByExcel() {
		String path = "/Users/social-test/Downloads/timesheet.xlsx";
		List<PeopleTimesheet> list = new ArrayList<PeopleTimesheet>();
		PeopleTimesheetService timesheetServiceImpl = new PeopleTimesheetServiceImpl();
		//timesheetServiceImpl.getPeopleInfoByExcel(list, path);
		
		logger.info(JSON.toJSONString(list));
		
	}
}

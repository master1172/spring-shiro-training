package com.wangzhixuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class TimesheetServiceImplDaoTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PeopleTimesheetService timesheetService;
	@Test
	public void name() {
	}
}

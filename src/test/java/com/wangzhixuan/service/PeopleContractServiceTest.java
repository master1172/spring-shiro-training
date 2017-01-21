package com.wangzhixuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PeopleContractServiceTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContractSalaryService peopleContractSalaryService;

	@Autowired
	private PeopleContractService peopleContractService;

	@Test
	public void findPeopleContractByCode() {
		
		String code = "d667db621e4f453f90b24422ef40bad1";
		logger.info(JSON.toJSONString(peopleContractService.findPeopleContractByCode(code), true));
	}
}

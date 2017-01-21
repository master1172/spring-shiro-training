package com.wangzhixuan.model;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangzhixuan.mapper.PeopleContractSalaryMapper;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.utils.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PeopleContractSalaryServiceTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private PeopleContractSalaryService peopleContractSalaryService;
    @Autowired PeopleContractSalaryMapper peopleContractSalaryMapper;
    
    @Test
    public void findDataGrid() {
        PageInfo pageInfo = new PageInfo(1,10);
        String peopleCode =  "d667db621e4f453f90b24422ef40bad1";
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);
        
        peopleContractSalaryService.findDataGrid(pageInfo, null);
        
        logger.info(JSON.toJSONString(pageInfo, true));
	}
    
    @Test
    public void findPeopleContractSalaryPageCount() {
    	PageInfo pageInfo = new PageInfo(1,10);
        String peopleCode =  "d667db621e4f453f90b24422ef40bad1";
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("peopleCode",peopleCode);
        pageInfo.setCondition(condition);
    	peopleContractSalaryMapper.findPeopleContractSalaryPageCount(pageInfo);
    	
    	 logger.info(JSON.toJSONString(pageInfo, true));
	}
    
}

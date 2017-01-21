package com.wangzhixuan.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.mapper.PeopleRetireSalaryMapper;
import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.service.PeopleRetireSalaryService;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleRetireSalaryServiceImpl implements PeopleRetireSalaryService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleRetireSalaryMapper peopleRetireSalaryMapper;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleRetireSalaryMapper.findPeopleRetireSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleRetireSalaryMapper.findPeopleRetireSalaryPageCount(pageInfo));
	}

	@Override
	public void addSalary(PeopleRetireSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetDate(new Date()));
		}
		logger.info("PeopleRetireSalary123:"+JSON.toJSONString(peopleSalary));

		peopleRetireSalaryMapper.insert(peopleSalary);

	}

	@Override
	public void updateSalary(PeopleRetireSalary peopleSalary) {
		peopleRetireSalaryMapper.updateByPrimaryKey(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Long id) {
		peopleRetireSalaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PeopleRetireSalaryVo findPeopleRetireSalaryVoById(Long id) {
		return peopleRetireSalaryMapper.findPeopleRetireSalaryVoById(id);
	}

}

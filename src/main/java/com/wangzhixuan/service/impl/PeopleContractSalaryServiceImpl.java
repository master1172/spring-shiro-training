package com.wangzhixuan.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.mapper.PeopleContractSalaryMapper;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
@Service
public class PeopleContractSalaryServiceImpl implements PeopleContractSalaryService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContractSalaryMapper peopleContractSalaryMapper;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleContractSalaryMapper.findPeopleContractSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleContractSalaryMapper.findPeopleContractSalaryPageCount(pageInfo));
	}

	@Override
	public void addSalary(PeopleContractSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetDate(new Date()));
		}

		peopleContractSalaryMapper.insert(peopleSalary);
	}

	@Override
	public void updateSalary(PeopleContractSalary peopleSalary) {
		peopleContractSalaryMapper.updateByPrimaryKeySelective(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Long id) {
		int count = peopleContractSalaryMapper.deleteByPrimaryKey(id);
		logger.info("delete:" + count);
	}

	@Override
	public PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id) {
		return peopleContractSalaryMapper.findPeopleContractSalaryVoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

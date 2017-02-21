package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.SalaryChangeRecordMapper;
import com.wangzhixuan.service.SalaryChangeRecordService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/21.
 */
@Service
public class SalaryChangeRecordServiceImpl implements SalaryChangeRecordService{

    @Autowired
    private SalaryChangeRecordMapper salaryChangeRecordMapper;

    @Override
    public void findSalaryChangeDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(salaryChangeRecordMapper.findSalaryChangeRecordPageCondition(pageInfo));
        pageInfo.setTotal(salaryChangeRecordMapper.findSalaryChangeRecordPageCount(pageInfo));
    }

}

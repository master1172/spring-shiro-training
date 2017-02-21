package com.wangzhixuan.service;

import com.wangzhixuan.utils.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/21.
 */
public interface SalaryChangeRecordService {
    void findSalaryChangeDataGrid(PageInfo pageInfo, HttpServletRequest request);
}

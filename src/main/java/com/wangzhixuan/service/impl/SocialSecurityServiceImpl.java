package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.SocialSecurityMapper;
import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.service.SocialSecurityService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/26.
 */
@Service
public class SocialSecurityServiceImpl implements SocialSecurityService {

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(socialSecurityMapper.findPageCondition(pageInfo));
        pageInfo.setTotal(socialSecurityMapper.findCount(pageInfo));
    }
}

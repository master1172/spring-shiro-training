package com.wangzhixuan.service;

import com.wangzhixuan.utils.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/26.
 */
public interface SocialSecurityService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);
}
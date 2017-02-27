package com.wangzhixuan.service;

import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.utils.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sterm on 2017/2/26.
 */
public interface SocialSecurityService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void findSocialSecurityGrid(PageInfo pageInfo);

    void updateBase(SocialSecurityBase socialSecurityBase);

    SocialSecurity findSocialSecurityById(Integer id);

    void insert(SocialSecurity socialSecurity);

    void update(SocialSecurity socialSecurity);

    void delete(Integer id);

    void exportExcel(HttpServletResponse response, String[] split);
}

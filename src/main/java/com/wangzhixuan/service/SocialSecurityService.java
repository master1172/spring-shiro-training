package com.wangzhixuan.service;

import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.utils.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/26.
 */
public interface SocialSecurityService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void findSocialSecurityGrid(PageInfo pageInfo);

    void updateBase(SocialSecurityBase socialSecurityBase);

    void insert(SocialSecurity socialSecurity);
}

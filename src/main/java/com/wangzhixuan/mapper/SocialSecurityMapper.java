package com.wangzhixuan.mapper;

import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

/**
 * Created by sterm on 2017/2/26.
 */
public interface SocialSecurityMapper {

    List findPageCondition(PageInfo pageInfo);

    int findCount(PageInfo pageInfo);

    List findSocialSecurityPageCondition(PageInfo pageInfo);

    int findSocialSecurityCount(PageInfo pageInfo);

    void insert(SocialSecurity socialSecurity);

    void update(SocialSecurity socialSecurity);

    void delete(Integer id);

    SocialSecurity findSocialSecurityById(Integer id);
}

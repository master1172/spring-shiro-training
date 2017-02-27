package com.wangzhixuan.mapper;

import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

/**
 * Created by sterm on 2017/2/26.
 */
public interface SocialSecurityMapper {

    List findPageCondition(PageInfo pageInfo);

    int findCount(PageInfo pageInfo);
}

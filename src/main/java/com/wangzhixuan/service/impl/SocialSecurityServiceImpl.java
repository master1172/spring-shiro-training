package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.mapper.SocialSecurityMapper;
import com.wangzhixuan.model.PeopleTotal;
import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.service.SocialSecurityService;
import com.wangzhixuan.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private PeopleTotalMapper peopleTotalMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(socialSecurityMapper.findPageCondition(pageInfo));
        pageInfo.setTotal(socialSecurityMapper.findCount(pageInfo));
    }

    @Override
    public void findSocialSecurityGrid(PageInfo pageInfo) {
        pageInfo.setRows(socialSecurityMapper.findSocialSecurityPageCondition(pageInfo));
        pageInfo.setTotal(socialSecurityMapper.findSocialSecurityCount(pageInfo));
    }

    @Override
    public void updateBase(SocialSecurityBase socialSecurityBase) {
        if (socialSecurityBase == null)
            return;
        if (StringUtils.isBlank(socialSecurityBase.getCode()))
            return;
        if (socialSecurityBase.getId() == null)
            return;

        Integer id = socialSecurityBase.getId();

        PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(id);

        if (peopleTotal != null){
            peopleTotal.setLifeInsuranceBase(socialSecurityBase.getLifeInsuranceBase());
            peopleTotal.setJobInsuranceBase(socialSecurityBase.getJobInsuranceBase());
            peopleTotal.setWoundInsuranceBase(socialSecurityBase.getWoundInsuranceBase());
            peopleTotal.setBirthInsuranceBase(socialSecurityBase.getBirthInsuranceBase());
            peopleTotal.setHealthInsuranceBase(socialSecurityBase.getHealthInsuranceBase());
            peopleTotal.setAnnuityBase(socialSecurityBase.getAnnuityBase());
        }

        peopleTotalMapper.updateByPrimaryKeySelective(peopleTotal);
    }

    @Override
    public void insert(SocialSecurity socialSecurity) {
        if (socialSecurity == null)
            return;
        socialSecurityMapper.insert(socialSecurity);
    }
}

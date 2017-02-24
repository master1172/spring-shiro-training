package com.wangzhixuan.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/2/23.
 */
public class SocialSecurityBase implements Serializable {

    private Integer id;

    private String peopleCode;

    private Integer departmentId;

    private BigDecimal lifeInsuranceBase;

    private BigDecimal jobInsuranceBase;

    private BigDecimal woundInsuranceBase;

    private BigDecimal birthInsuranceBase;

    private BigDecimal healthInsuranceBase;

    private BigDecimal annuityBase;


}

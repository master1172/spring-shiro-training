package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleRankVo implements Serializable {
    private Long id;

    public String getRank_level() {
        return rank_level;
    }

    public void setRank_level(String rank_level) {
        this.rank_level = rank_level;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    private String rank_level;
    private BigDecimal salary;
    public static Map<String,Object> CreateCondition(PeopleRankVo peoplerankvo) {
        Map<String, Object> condition = Maps.newHashMap();
        if(peoplerankvo.getRank_level() != null){
            condition.put("rank_level", peoplerankvo.getRank_level().split(","));
        }
        if(StringUtils.isNoneBlank(peoplerankvo.getSalary().toString())){
            condition.put("salary", peoplerankvo.getSalary());
        }
        return condition;
    }
}


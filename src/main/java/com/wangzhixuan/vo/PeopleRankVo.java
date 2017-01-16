package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleRankVo implements Serializable {
    private Long id;

    public String getRank_level() {
        return Rank_level;
    }

    public void setRank_level(String rank_level) {
        Rank_level = rank_level;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    private String Rank_level;
    private String salary;
    public static Map<String,Object> CreateCondition(PeopleRankVo peoplerankvo) {
        Map<String, Object> condition = Maps.newHashMap();
        if(peoplerankvo.getRank_level() != null){
            condition.put("Job_category", peoplerankvo.getRank_level().split(","));
        }
        if(StringUtils.isNoneBlank(peoplerankvo.getSalary())){
            condition.put("Job_level", peoplerankvo.getSalary());
        }
        return condition;
    }
}

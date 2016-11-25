package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Dict;

import java.util.List;

/**
 * Created by sterm on 2016/11/15.
 */
public interface DictMapper {
    Integer findDegreeIdByName(String degreeName);

    List<Dict> findDegreeDict();

    Integer findNationalIdByName(String nationalName);

    Integer findJobIdByName(String category);
}

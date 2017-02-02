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

    Integer findJobLevelIdByName(String job_level_name);

    Integer findRankLevelIdByName(String rank_level_name);

    List<Dict> findNationalDict();

    List<Dict> findNativeDict();

    Integer findNativeIdByName(String nativeName);

    List<Dict> findRankLevelDict();

    List<Dict> findJobLevelDict();

    Integer findMarriageIdByName(String marriageName);

    List<Dict> findMarriageDict();

    Integer findDepartmentIdByName(String DepartmentName);

    List<Dict> findDepartmentDict();

    Integer findJobIdByName(String jobName);

    List<Dict> findJobNameDict();

    List<Dict> findIdentityDict();

    Integer findIdentityIdByName(String identityName);

    Integer findBranchIdByName(String branchName);

    List<Dict> findBranchDict();

    List<Dict> findPartyStatusDict();

    Integer findPartyStatusIdByName(String partyStatusName);
}

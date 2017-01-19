package com.wangzhixuan.controller;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.model.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sterm on 2016/11/9.
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    private static Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictMapper dictMapper;

    @RequestMapping(value="/degree", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> degreeDict(){
        List<Dict> degreeList = dictMapper.findDegreeDict();
        return degreeList;
    }

    @RequestMapping(value="/national", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> nationalDict(){
        List<Dict> nationalList = dictMapper.findNationalDict();
        return nationalList;
    }

    @RequestMapping(value="/native", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> nativeDict(){
        List<Dict> nativeList = dictMapper.findNativeDict();
        return nativeList;
    }

    @RequestMapping(value="identity", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> identityDict(){
        List<Dict> identityList = dictMapper.findIdentityDict();
        return identityList;
    }

    @RequestMapping(value="/rank", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> RankLevelDict(){
        List<Dict> rankLevelList = dictMapper.findRankLevelDict();
        return rankLevelList;
    }

    @RequestMapping(value="/job", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> JobLevelDict(){
        List<Dict> jobLevelList = dictMapper.findJobLevelDict();
        return jobLevelList;
    }

    @RequestMapping(value="/marriage", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> MarriageDict(){
        List<Dict> marriageList = dictMapper.findMarriageDict();
        return marriageList;
    }

    @RequestMapping(value="/jobName", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> JobNameDict(){
        List<Dict> jobNameList = dictMapper.findJobNameDict();
        return jobNameList;
    }

    @RequestMapping(value="/department", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> departmentDict(){
        List<Dict> departmentList = dictMapper.findDepartmentDict();
        return departmentList;
    }

    @RequestMapping(value="/branch", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> branchDict(){
        List<Dict> branchList = dictMapper.findBranchDict();
        return branchList;
    }

    @RequestMapping(value="/partyStatus", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> partyStatusDict(){
        List<Dict> partyStatusList = dictMapper.findPartyStatusDict();
        return partyStatusList;
    }
}

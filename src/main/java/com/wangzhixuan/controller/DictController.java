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

    @RequestMapping(value="/job", method = RequestMethod.POST)
    @ResponseBody
    public List<Dict> JobLevelDict(){
        List<Dict> jobLevelList = dictMapper.findJobLevelDict();
        return jobLevelList;
    }
}

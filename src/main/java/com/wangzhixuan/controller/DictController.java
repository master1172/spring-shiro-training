package com.wangzhixuan.controller;

import com.google.common.collect.Lists;
import com.wangzhixuan.model.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sterm on 2016/11/9.
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    private static Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    @RequestMapping(value="/degree", method = RequestMethod.GET)
    public List<Dict> degreeDict(){
        List<Dict> degreeList = Lists.newArrayList();

        degreeList.add(new Dict(1,"本科"));
        degreeList.add(new Dict(2,"硕士"));
        degreeList.add(new Dict(3,"博士"));

        return degreeList;
    }


}

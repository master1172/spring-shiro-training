package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.model.Dict;
import com.wangzhixuan.service.DictService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/3/11.
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(dictMapper.findByCondition(pageInfo));
        pageInfo.setTotal(dictMapper.findCount(pageInfo));
    }

    @Override
    public Dict findById(Dict dict) {
        return dictMapper.findById(dict);
    }

    @Override
    public void add(Dict dict) {
        dictMapper.add(dict);
    }

    @Override
    public void update(Dict dict) {
        dictMapper.update(dict);
    }

    @Override
    public void delete(Dict dict) {
        dictMapper.delete(dict);
    }
}

package com.wangzhixuan.service;

import com.wangzhixuan.model.Dict;
import com.wangzhixuan.utils.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/3/13.
 */
public interface DictService {

    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    public Dict findById(Dict dict);

    public void add(Dict dict);

    public void update(Dict dict);

    public void delete(Dict id);
}

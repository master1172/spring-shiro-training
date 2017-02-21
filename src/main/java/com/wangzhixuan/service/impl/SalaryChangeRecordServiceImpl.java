package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.mapper.SalaryChangeRecordMapper;
import com.wangzhixuan.model.SalaryChangeRecord;
import com.wangzhixuan.service.SalaryChangeRecordService;
import com.wangzhixuan.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/2/21.
 */
@Service
public class SalaryChangeRecordServiceImpl implements SalaryChangeRecordService{

    @Autowired
    private SalaryChangeRecordMapper salaryChangeRecordMapper;

    @Override
    public void findSalaryChangeDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(salaryChangeRecordMapper.findSalaryChangeRecordPageCondition(pageInfo));
        pageInfo.setTotal(salaryChangeRecordMapper.findSalaryChangeRecordPageCount(pageInfo));
    }

    @Override
    public void addSalaryChangeRecord(SalaryChangeRecord salaryChangeRecord) {
        UpdateDate(salaryChangeRecord);
        salaryChangeRecordMapper.insert(salaryChangeRecord);
    }

    @Override
    public void delete(Integer id) {
        salaryChangeRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SalaryChangeRecord findSalaryChangeRecordById(Integer id) {
        if (id == null)
            return new SalaryChangeRecord();
        return salaryChangeRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(SalaryChangeRecord salaryChangeRecord) {
        UpdateDate(salaryChangeRecord);
        salaryChangeRecordMapper.updateByPrimaryKey(salaryChangeRecord);
    }

    private void UpdateDate(SalaryChangeRecord salaryChangeRecord){

        if (StringUtils.isBlank(salaryChangeRecord.getChangeDate()))
            salaryChangeRecord.setChangeDate(null);

        if (StringUtils.isBlank(salaryChangeRecord.getEffectDate())){
            salaryChangeRecord.setEffectDate(null);
        }

        if (StringUtils.isBlank(salaryChangeRecord.getPeopleCheckDate())){
            salaryChangeRecord.setPeopleCheckDate(null);
        }
    }

}

package com.wangzhixuan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.vo.PeopleTimesheetVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.utils.PageInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fengjunfeng
 */
public interface PeopleTimesheetService {

    PeopleTimesheet selectByPrimaryKey(Integer id);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(PeopleTimesheet record);

    int updateByPrimaryKey(PeopleTimesheet record);
    
    void findDataGrid(PageInfo pageInfo);

	List<PeopleTimesheetVo> findPeopleTimeSheetListByCode(String code) ;

	boolean insertByImport(CommonsMultipartFile[] files);

    boolean insertTimesheetByImport(CommonsMultipartFile[] files);

	void exportExcel(HttpServletResponse response, String[] idList);

    PeopleTimesheetVo findPeopleTimesheetVoById(Integer id);

    List<PeopleTimesheet> findPeopleTimesheetListByCodeAndDate(String code, String checkDateMin, String checkDateMax);

    BigDecimal findVacationSumByCodeAndDate(String code, String checkDateMin, String checkDateMax);

    void exportVacationResult(HttpServletResponse response, String checkDate);

    void findPeopleDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void findPeopleContractDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void findPeopleContract2DataGrid(PageInfo pageInfo, HttpServletRequest request);
}


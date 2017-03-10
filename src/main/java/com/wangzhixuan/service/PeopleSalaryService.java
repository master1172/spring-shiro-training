package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleSalaryBase;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
import com.wangzhixuan.vo.PeopleSalaryVo;
import com.wangzhixuan.vo.PeopleVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by sterm on 2017/1/13.
 */
public interface PeopleSalaryService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleSalary peopleSalary);

    void updateSalary(PeopleSalary peopleSalary);

    void deleteSalaryById(Integer id);

    void batchDeleteSalaryByIds(String[] ids);

    PeopleSalaryVo findPeopleSalaryVoById(Long id);



    void updateSalaryBase(PeopleSalaryBase peopleSalaryBase);

    void exportExcel(HttpServletResponse response, String[] idList);

    void exportExcel2(HttpServletResponse response, String[] idList);

    boolean insertByImport(CommonsMultipartFile[] files);

    void updateSalaryJobLevel(PeopleVo peopleVo);

    PeopleSalaryBase findPeopleSalaryBaseById(Integer id);

    PeopleSalaryBase findPeopleSalaryBaseByCode(String code);

    PeopleSalary findPeopleSalaryById(Integer id);


    BigDecimal CalculateGrossIncome(PeopleSalary peopleSalary);

    String findPeopleIDsByCondition(PageInfo pageInfo);

    void exportCert(HttpServletResponse response, String ids);
}

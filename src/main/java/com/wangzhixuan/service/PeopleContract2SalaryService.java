package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleContract2SalaryBase;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.model.PeopleContractSalaryBase;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
public interface PeopleContract2SalaryService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleContractSalary peopleSalary);

    void updateSalary(PeopleContractSalary peopleSalary);

    void deleteSalaryById(Integer id);

    PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id);

    boolean insertByImport(CommonsMultipartFile[] files);

    void exportExcel(HttpServletResponse response, String[] idList);

    PeopleContractSalaryBase findPeopleContractSalaryBaseByCode(String peopleCode);

    void updateSalaryBase(PeopleContractSalaryBase peopleContractSalaryBase);

    PeopleContractSalaryBase findPeopleContractSalaryBaseById(Integer id);

    void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request);

    PeopleContractSalary findPeopleContractSalaryById(Integer id);

    BigDecimal CalculateGrossIncome(PeopleContractSalary peopleContractSalary);

    void exportCert(HttpServletResponse response, String ids);

    boolean autoCalculateSalary(String payDate, StringBuilder processResult);

    void exportExcelForMonth(HttpServletResponse response, String payDate);

    void updateJobAndRankSalary(PeopleContractSalaryBase peopleContractBase);
}

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
import java.util.List;

/**
 * Created by sterm on 2017/1/13.
 */
public interface PeopleSalaryService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleSalary peopleSalary);

    void updateSalary(PeopleSalary peopleSalary);

    void deleteSalaryById(Long id);

    void batchDeleteSalaryByIds(String[] ids);

    PeopleSalaryVo findPeopleSalaryVoById(Long id);

    PeopleSalaryBaseVo findPeopleSalaryBaseByCode(String code);

    void updateSalaryBase(PeopleSalaryBase peopleSalaryBase);

    void exportExcel(HttpServletResponse response, String[] idList);

    boolean insertByImport(CommonsMultipartFile[] files);

    void updateSalaryJobLevel(PeopleVo peopleVo);

    PeopleSalaryBase findPeopleSalaryBaseById(Integer id);
}

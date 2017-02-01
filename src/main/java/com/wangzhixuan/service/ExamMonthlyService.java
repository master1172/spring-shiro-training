package com.wangzhixuan.service;

import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.ExamMonthlyVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by mengfw on 2017/1/21.
 */
public interface ExamMonthlyService {

  void findDataGrid(PageInfo pageInfo);

  void add(ExamMonthly examMonthly);

  void deleteById(Long id);

  void update(ExamMonthly examMonthly);

  void exportExcel(HttpServletResponse response, String[] ids);

  boolean insertByImport(CommonsMultipartFile[] files);

  void batchDeletePeopleByIds(String[] idList);

  ExamMonthly findExamMonthlyById(Long id);

  ExamMonthly findPeopleExamMonthlyResultByCodeAndDate(String code, Integer year, Integer month);
}

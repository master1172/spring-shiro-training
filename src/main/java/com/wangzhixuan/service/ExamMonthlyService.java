package com.wangzhixuan.service;

import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.utils.PageInfo;
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

  void exportExcel(HttpServletResponse response, String[] ids, String selectDate);

  boolean insertByImport(CommonsMultipartFile[] files);

  void batchDeletePeopleByIds(String[] idList);

  ExamMonthly findExamMonthlyById(Long id);

  String findPeopleExamMonthlyResultByCodeAndDate(String code, String yearAndMonth);
}

package com.wangzhixuan.service;

import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.utils.PageInfo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by mengfw on 2017/1/20.
 */
public interface ExamYearlyService {

  void findDataGrid(PageInfo pageInfo);

  void add(ExamYearly examYearly);

  void deleteById(Long id);

  void update(ExamYearly examYearly);

  void exportExcel(HttpServletResponse response, String[] ids);

  boolean insertByImport(CommonsMultipartFile[] files);

  void batchDeletePeopleByIds(String[] idList);

  ExamYearly findExamYearlyById(Long id);
}

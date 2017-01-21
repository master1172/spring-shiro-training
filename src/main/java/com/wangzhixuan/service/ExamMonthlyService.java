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

  String findIDsByCondition(PageInfo pageInfo);

  void add(ExamMonthlyVo vo);

  void deleteById(Long id);

  void update(ExamMonthlyVo vo);

  void exportExcel(HttpServletResponse response, String[] ids);

  boolean insertByImport(CommonsMultipartFile[] files);

  void batchDeletePeopleByIds(String[] idList);
}

package com.wangzhixuan.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangzhixuan.mapper.*;
import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.ExamMonthlyVo;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mengfw on 2017/1/21.
 */
@Service
public class ExamMonthlyServiceImpl implements ExamMonthlyService {
  @Autowired
  private PeopleMapper peopleMapper;

  @Autowired
  private ExamMonthlyMapper examMonthlyMapper;

  @Autowired
  private PeopleTotalMapper peopleTotalMapper;

  @Override
  public ExamMonthly findExamMonthlyById(Long id) {
    return examMonthlyMapper.selectByPrimaryKey(id.intValue());
  }

  @Override
  public String findPeopleExamMonthlyResultByCodeAndDate(String code, String startDate, String endDate) {
    Map<String, Object> condition = Maps.newHashMap();
    condition.put("peopleCode",code);
    condition.put("starDate",startDate);
    condition.put("endDate",endDate);
    ExamMonthly examMonthly =  examMonthlyMapper.findPeopleExamMonthlyResultByCodeAndDate(condition);

    if (examMonthly == null)
      return "A";

    if (StringUtils.isBlank(examMonthly.getExamResult()))
      return "A";

    return examMonthly.getExamResult();

  }

  @Override
  public void findDataGrid(PageInfo pageInfo) {
    pageInfo.setRows(examMonthlyMapper.findPageCondition(pageInfo));
    pageInfo.setTotal(examMonthlyMapper.findPageCount(pageInfo));
  }

  @Override
  public void add(ExamMonthly examMonthly){
    examMonthlyMapper.insert(examMonthly);
  }

  @Override
  public void update(ExamMonthly examMonthly) {
    examMonthlyMapper.updateByPrimaryKey(examMonthly);
  }

  @Override
  public void deleteById(Long id) {
    examMonthlyMapper.deleteByPrimaryKey(id.intValue());
  }

  @Override
  public void exportExcel(HttpServletResponse response, String[] ids) {
    List<PeopleVo> list = peopleMapper.selectPeopleVoByIds(ids);

    if(CollectionUtils.isEmpty(list)){
      return;
    }

    XSSFWorkbook workBook;
    OutputStream os;
    String newFileName="月度考核信息.xlsx";
    try {
      workBook = new XSSFWorkbook();
      XSSFSheet sheet = workBook.createSheet("月度考核信息");
      XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
      //创建表头
      XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getExamMonthlyHeaders());

      int count = 0;

      setBorder=WordUtil.setCellStyle(workBook,false);
      for(int i=0;i<list.size();i++) {
        PeopleVo peopleVo = list.get(i);
        if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
          continue;
        String peopleCode = peopleVo.getCode();

        List<ExamMonthlyVo> examMonthlyVoList = examMonthlyMapper.findExamMonthlyVoListByCode(peopleCode);

        if (examMonthlyVoList == null || examMonthlyVoList.size() < 1)
            continue;

        for(int j=0; j<examMonthlyVoList.size(); j++){
          row = sheet.createRow(count+1);
          ExamMonthlyVo examMonthlyVo = examMonthlyVoList.get(j);
          row.createCell(0).setCellValue(count+1);
          row.createCell(1).setCellValue(examMonthlyVo.getName());
          row.createCell(2).setCellValue(examMonthlyVo.getExamResult());
          row.createCell(3).setCellValue(examMonthlyVo.getExamOperation());
          row.createCell(4).setCellValue(examMonthlyVo.getExamDate());

          count++;

          for(int k=0; k<5; k++){
            row.getCell(k).setCellStyle(setBorder);
          }
          row.setHeight((short) 400);
        }
      }
      sheet.setDefaultRowHeightInPoints(21);
      response.reset();
      os = response.getOutputStream();
      response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
      workBook.write(os);
      os.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean insertByImport(CommonsMultipartFile[] files) {
    boolean flag=false;
    if(files==null || files.length<=0){
      return flag;
    }
    List<ExamMonthly> list = new ArrayList<ExamMonthly>();

    String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    for(int i=0; i<files.length; i++){

      String path= UploadUtil.fileUpload(filePath, files[i]);

      if( StringUtils.isNotBlank(path)){
        list=getExamMonthlyByExcel(list,path);
      }
    }
    if(list.size()>0){
      flag=examMonthlyMapper.insertByImport(list)>0;
    }
    return flag;
  }

  @Override
  public void batchDeletePeopleByIds(String[] idList) {
    examMonthlyMapper.batchDeleteByIds(idList);
  }


  private List<ExamMonthly> getExamMonthlyByExcel(List<ExamMonthly> list, String path) {
    try
    {
      XSSFWorkbook xwb = new XSSFWorkbook(path);
      XSSFSheet sheet = xwb.getSheetAt(0);

      XSSFRow row;
      for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
        row = sheet.getRow(i);
        ExamMonthly examMonthly=new ExamMonthly();
        //姓名
        if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
          continue;
        }
        String name=row.getCell(1).toString().trim();

        People people = peopleMapper.findFirstPeopleByName(name);

        if (people == null || StringUtils.isBlank(people.getCode()))
          continue;
        examMonthly.setPeopleCode(people.getCode());

        if(row.getCell(2)!=null && !row.getCell(2).toString().trim().equals("")){
          examMonthly.setExamResult(row.getCell(2).toString().trim());
        }

        if(row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
          examMonthly.setExamOperation(row.getCell(3).toString().trim());
        }

        if(row.getCell(4)!=null && !row.getCell(4).toString().trim().equals("")){
          examMonthly.setExamDate(row.getCell(4).toString().trim());
        }

        list.add(examMonthly);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    return list;
  }
}

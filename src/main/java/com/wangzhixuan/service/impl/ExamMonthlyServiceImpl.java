package com.wangzhixuan.service.impl;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.ExamMonthlyMapper;
import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.ExamMonthlyVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mengfw on 2017/1/21.
 */
@Service
public class ExamMonthlyServiceImpl implements ExamMonthlyService {
  @Autowired
  private ExamMonthlyMapper mapper;
  @Autowired
  private PeopleMapper peopleMapper;
  @Override
  public void findDataGrid(PageInfo pageInfo) {
    List<ExamMonthly> list = mapper.findPageCondition(pageInfo);
    List<ExamMonthlyVo> results = Lists.newArrayList();
    for (ExamMonthly monthly:list){
      ExamMonthlyVo vo = new ExamMonthlyVo();
      vo.setId(monthly.getId());
      vo.setExamResult(monthly.getExamResult());
      vo.setExamOperation(monthly.getExamOperation());
      vo.setPeopleCode(monthly.getPeopleCode());
      vo.setPeopleType(monthly.getPeopleType());
      vo.setName(monthly.getName());
      vo.setYearMonth(monthly.getYear() + "-" + monthly.getMonth());
      results.add(vo);
    }
    pageInfo.setRows(results);

  }

  @Override
  public String findIDsByCondition(PageInfo pageInfo) {
    String ids = "";
    pageInfo.setFrom(0);
    pageInfo.setSize(100000);
    List<ExamMonthly> list = mapper.findPageCondition(pageInfo);
    if(CollectionUtils.isEmpty(list)){
      return ids;
    }
    for(int i=0; i<list.size(); i++){
      ids = ids + list.get(i).getId().toString() + ",";
    }

    //刪除最後一個逗号
    ids = ids.substring(0, ids.lastIndexOf(','));
    return ids;
  }

  @Override
  public void add(ExamMonthlyVo vo) {
    ExamMonthly examMonthly = new ExamMonthly();
    String[]strArr = vo.getYearMonthMax().split("-");
    if(strArr.length < 2){
      throw new RuntimeException("时间格式不对");
    }
    examMonthly.setYear(Integer.parseInt(strArr[0]));
    examMonthly.setMonth(Integer.parseInt(strArr[1]));
    examMonthly.setId(vo.getId());
    examMonthly.setPeopleCode(vo.getPeopleType());
    examMonthly.setExamOperation(vo.getExamOperation());
    examMonthly.setExamResult(vo.getExamResult());
    mapper.insert(examMonthly);
  }

  @Override
  public void deleteById(Long id) {
    mapper.deleteByPrimaryKey(id.intValue());
  }

  @Override
  public void update(ExamMonthlyVo vo) {
    ExamMonthly examMonthly = new ExamMonthly();
    String[]strArr = vo.getYearMonthMax().split("-");
    if(strArr.length < 2){
      throw new RuntimeException("时间格式不对");
    }
    examMonthly.setYear(Integer.parseInt(strArr[0]));
    examMonthly.setMonth(Integer.parseInt(strArr[1]));
    examMonthly.setId(vo.getId());
    examMonthly.setPeopleCode(vo.getPeopleType());
    examMonthly.setExamOperation(vo.getExamOperation());
    examMonthly.setExamResult(vo.getExamResult());
    mapper.updateByPrimaryKey(examMonthly);
  }

  @Override
  public void exportExcel(HttpServletResponse response, String[] ids) {
    List<ExamMonthly> list=mapper.selectByIds(ids);
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
      XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getEaxmYearlyHeaders());
      setBorder=WordUtil.setCellStyle(workBook,false);
      for(int i=0;i<list.size();i++) {
        row = sheet.createRow(i + 1);
        ExamMonthly e = list.get(i);
        row.createCell(0).setCellValue(i+1);
        row.createCell(1).setCellValue(e.getName());
        row.createCell(2).setCellValue(e.getYear() +"-" + e.getMonth());
        row.createCell(3).setCellValue(e.getExamResult());
        row.createCell(4).setCellValue(e.getExamOperation());
        for(int j=0; j<5; j++){
          row.getCell(j).setCellStyle(setBorder);
        }
        row.setHeight((short) 400);
        sheet.setDefaultRowHeightInPoints(21);
        response.reset();
        os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
        workBook.write(os);
        os.close();
      }
    }catch (IOException e){
      e.printStackTrace();
    }finally {

    }
  }

  @Override
  public boolean insertByImport(CommonsMultipartFile[] files) {
    boolean flag=false;
    if(files==null || files.length<=0){
      return flag;
    }
    List<ExamMonthly > list = new ArrayList<ExamMonthly>();

    String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    for(int i=0; i<files.length; i++){

      String path= UploadUtil.fileUpload(filePath, files[i]);

      if( StringUtils.isNotBlank(path)){
        list=getExamMonthlyByExcel(list,path);
      }

    }
    if(list.size()>0){
      flag=mapper.insertByImport(list)>0;
    }
    return flag;
  }

  @Override
  public void batchDeletePeopleByIds(String[] idList) {
    mapper.batchDeleteByIds(idList);
  }

  private List< ExamMonthly > getExamMonthlyByExcel(List< ExamMonthly > list, String path) {
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
        examMonthly.setName(name);
        People p = peopleMapper.findPeopleByName(examMonthly.getName());
        if(p == null ){
          continue;
        }
        examMonthly.setPeopleCode(p.getCode());
        if(row.getCell(2)!=null && !row.getCell(2).toString().trim().equals("")){
          String[]strArr = row.getCell(2).toString().trim().split("-");
          if(strArr.length < 2){
            throw new RuntimeException("时间格式不对");
          }
          examMonthly.setYear(Integer.parseInt(strArr[0]));
          examMonthly.setMonth(Integer.parseInt(strArr[1]));
        }
        if(row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
          examMonthly.setExamResult(row.getCell(3).toString().trim());
        }
        if(row.getCell(4)!=null && !row.getCell(4).toString().trim().equals("")){
          examMonthly.setExamOperation(row.getCell(4).toString().trim());
        }
        examMonthly.setName(null);//不继续维护姓名，联查
        list.add(examMonthly);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    return list;
  }
}

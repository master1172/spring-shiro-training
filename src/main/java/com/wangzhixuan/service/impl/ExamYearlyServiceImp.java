package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.ExamYearlyMapper;
import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.ExamYearlyVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
 * Created by mengfw on 2017/1/20.
 */
@Service
public class ExamYearlyServiceImp implements ExamYearlyService {
  @Autowired
  private ExamYearlyMapper mapper;
  @Autowired
  private PeopleMapper peopleMapper;
  @Override
  public void findDataGrid(PageInfo pageInfo) {
    pageInfo.setRows(mapper.findPageCondition(pageInfo));

  }

  @Override
  public String findIDsByCondition(PageInfo pageInfo) {
    String ids = "";
    pageInfo.setFrom(0);
    pageInfo.setSize(100000);
    List<ExamYearly> list = mapper.findPageCondition(pageInfo);
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
  public void add(ExamYearly examYearly) {
    examYearly.setName(null);//不维护名称,联查
    mapper.insert(examYearly);
  }

  @Override
  public void deleteById(Long id) {
    mapper.deleteByPrimaryKey(id.intValue());
  }

  @Override
  public void update(ExamYearly examYearly) {
    mapper.updateByPrimaryKey(examYearly);
  }

  @Override
  public void exportExcel(HttpServletResponse response, String[] ids) {
    List<ExamYearly> list=mapper.selectByIds(ids);
    if(CollectionUtils.isEmpty(list)){
      return;
    }
    XSSFWorkbook workBook;
    OutputStream os;
    String newFileName="年度考核信息.xlsx";
    try {
      workBook = new XSSFWorkbook();
      XSSFSheet sheet = workBook.createSheet("年度考核信息");
      XSSFCellStyle setBorder=WordUtil.setCellStyle(workBook,true);
      //创建表头
      XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getEaxmYearlyHeaders());
      setBorder=WordUtil.setCellStyle(workBook,false);
      for(int i=0;i<list.size();i++) {
        row = sheet.createRow(i + 1);
        ExamYearly e = list.get(i);
        row.createCell(0).setCellValue(i+1);
        row.createCell(1).setCellValue(e.getName());
        row.createCell(2).setCellValue(e.getYear());
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
  public void exportWord(HttpServletResponse response, String id) {
    ExamYearly examYearly = mapper.selectByPrimaryKey(Integer.parseInt(id));
    if(examYearly == null){
      return;
    }
    XWPFDocument doc;
    OutputStream os;
    String filePath=this.getClass().getResource("/template/examYearly.docx").getPath();
    String newFileName="年度考核信息.docx";
    Map<String,Object> params = new HashMap<String,Object>();
    params.put("${name}",examYearly.getName());
    params.put("${year}",examYearly.getYear());
    params.put("${examResult}",examYearly.getExamResult());
    params.put("${examOperation}",examYearly.getExamOperation());
    WordUtil.OutputWord(response, filePath, newFileName, params);
  }

  @Override
  public boolean insertByImport(CommonsMultipartFile[] files) {
    boolean flag=false;
    if(files==null || files.length<=0){
      return flag;
    }
    List<ExamYearly > list = new ArrayList<ExamYearly>();

    String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    for(int i=0; i<files.length; i++){

      String path= UploadUtil.fileUpload(filePath, files[i]);

      if( StringUtils.isNotBlank(path)){
        list=getExamYearlyByExcel(list,path);
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

  private List< ExamYearly > getExamYearlyByExcel(List< ExamYearly > list, String path) {
    try
    {
      XSSFWorkbook xwb = new XSSFWorkbook(path);
      XSSFSheet sheet = xwb.getSheetAt(0);

      XSSFRow row;
      for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
        row = sheet.getRow(i);
        ExamYearly examYearly=new ExamYearly();
        //姓名
        if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
          continue;
        }
        String name=row.getCell(1).toString().trim();
        examYearly.setName(name);
        People p = peopleMapper.findPeopleByName(examYearly.getName());
        if(p == null ){
          continue;
        }
        examYearly.setPeopleCode(p.getCode());
        if(row.getCell(2)!=null && !row.getCell(2).toString().trim().equals("")){
          examYearly.setYear(Integer.parseInt(row.getCell(2).toString().trim()));
        }
        if(row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
          examYearly.setExamResult(row.getCell(3).toString().trim());
        }
        if(row.getCell(4)!=null && !row.getCell(4).toString().trim().equals("")){
          examYearly.setExamOperation(row.getCell(4).toString().trim());
        }
        examYearly.setName(null);//不继续维护姓名，联查
        list.add(examYearly);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    return list;
  }
}

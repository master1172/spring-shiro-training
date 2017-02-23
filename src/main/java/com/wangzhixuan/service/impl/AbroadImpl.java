package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.AbroadMapper;
import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.model.Abroad;
import com.wangzhixuan.service.AbroadService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.AbroadVo;
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
import java.util.List;

/**
 * Created by sterm on 2017/2/14.
 */
@Service
public class AbroadImpl implements AbroadService {

    @Autowired
    private AbroadMapper abroadMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(abroadMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(abroadMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<AbroadVo> trainingList = abroadMapper.findPeoplePageCondition(pageInfo);

        if (trainingList == null || trainingList.size() < 0)
            return ids;

        for(int i=0; i<trainingList.size(); i++){
            ids = ids + trainingList.get(i).getId().toString() + ",";
        }
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public Abroad findAbroadById(Integer id) {
        return abroadMapper.findAbroadById(id);
    }

    @Override
    public void add(Abroad abroad) {
        UpdateDate(abroad);
        abroad.setCode(StringUtilExtra.generateUUID());
        abroadMapper.insert(abroad);
    }

    @Override
    public void update(Abroad abroad) {
        UpdateDate(abroad);
        abroadMapper.update(abroad);
    }

    @Override
    public void delete(Integer id) {

        abroadMapper.delete(id);
    }

    @Override
    public void batchDel(String[] idList) {
        abroadMapper.batchDeleteByIds(idList);

    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag=false;
        if(files!=null && files.length>0){

            List<Abroad> list = new ArrayList<Abroad>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list = getPeopleInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=abroadMapper.insertByImport(list)>0;
            }
        }
        return flag;
    }





    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {
        List list=abroadMapper.selectAbroadVoByIds(idList);
        if(list != null && list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="因私出入境人员信息.xlsx";

            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("因私出入境人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);

                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getAbroadPeopleHeaders());
                setBorder=WordUtil.setCellStyle(workBook,false);

                for(int i=0; i<list.size(); i++){
                    row=sheet.createRow(i+1);
                    AbroadVo p = (AbroadVo)list.get(i);

                    row.createCell(0).setCellValue(i+1);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getDepartmentName());row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getJobName());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getAbroadDate()==null?"":(p.getAbroadDate().toString()));row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getCountry());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getPassportStatus());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getReason());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getFunding());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getIssueDate()==null?"":(p.getIssueDate().toString()));row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getPickPassportDate()==null?"":(p.getPickPassportDate().toString()));row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getReturnPassportDate()==null?"":(p.getReturnPassportDate().toString()));row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getComment());row.getCell(12).setCellStyle(setBorder);

                    for(int j=0; j<13; j++){
                        row.getCell(j).setCellStyle(setBorder);
                    }
                    row.setHeight((short)400);
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }finally{

            }
        }
    }

    private void UpdateDate(Abroad abroad) {
        if (StringUtils.isBlank(abroad.getAbroadDate()))
            abroad.setAbroadDate(null);
        if (StringUtils.isBlank(abroad.getPickPassportDate()))
            abroad.setPickPassportDate(null);
        if (StringUtils.isBlank(abroad.getReturnPassportDate()))
            abroad.setReturnPassportDate(null);
        if (StringUtils.isBlank(abroad.getIssueDate()))
            abroad.setIssueDate(null);
    }

    private List<Abroad> getPeopleInfoByExcel(List<Abroad> list, String path) {
        try
        {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                Abroad p = new Abroad();

                p.setCode(StringUtilExtra.generateUUID());

                //姓名
                if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
                    continue;
                }

                String name=row.getCell(1).toString().trim();
                p.setName(name);

                //所在部门
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String departmentName = row.getCell(2).toString().trim();
                    try{
                        Integer departmentId = dictMapper.findDepartmentIdByName(departmentName);
                        p.setDepartmentId(departmentId);
                    }catch(Exception exp){

                    }
                }
               //职级
                if(row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")){
                    String jobName = row.getCell(3).toString().trim();

                    try{
                        Integer jobId = dictMapper.findJobLevelIdByName(jobName);
                        p.setJobId(jobId);
                    }catch (Exception exp){

                    }
                }
                //出国境日期
                if(row.getCell(4) != null && !row.getCell(4).toString().trim().equals("")){
                    String abroadDate = row.getCell(4).toString().trim();
                    p.setAbroadDate(abroadDate);
                }
                //所赴国家
                if(row.getCell(5)!=null && !row.getCell(5).toString().trim().equals("")){
                    String abroadDate = row.getCell(5).toString().trim();
                    p.setCountry(abroadDate);
                }
                //申请因私证件情况
                if(row.getCell(6)!=null && !row.getCell(6).toString().trim().equals("")){
                    String passportStatus = row.getCell(6).toString().trim();
                    p.setPassportStatus(passportStatus);
                }

                //是由
                if(row.getCell(7) != null && !row.getCell(7).toString().trim().equals("")){
                    String reason = row.getCell(7).toString().trim();
                    p.setReason(reason);
                }

                //经费形式
                if(row.getCell(8) != null && !row.getCell(8).toString().trim().equals("")){
                    String funding = row.getCell(8).toString().trim();
                    p.setFunding(funding);
                }

                //办理日期
                if(row.getCell(90) != null && !row.getCell(9).toString().trim().equals("")){
                    String issueDate = row.getCell(9).toString().trim();
                    p.setIssueDate(issueDate);
                }

                //取证日期
                if(row.getCell(10) != null && !row.getCell(10).toString().trim().equals("")){
                    String pickPassportDate = row.getCell(10).toString().trim();
                    p.setPickPassportDate(pickPassportDate);
                }

                //还证日期
                if(row.getCell(11) != null && !row.getCell(11).toString().trim().equals("")){
                    String returnPassportDate = row.getCell(11).toString().trim();
                    p.setReturnPassportDate(returnPassportDate);
                }

                //备注
                if(row.getCell(12) != null && !row.getCell(12).toString().trim().equals("")){
                    String comment = row.getCell(12).toString().trim();
                    p.setComment(comment);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}

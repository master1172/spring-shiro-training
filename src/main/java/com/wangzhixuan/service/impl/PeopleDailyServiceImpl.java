package com.wangzhixuan.service.impl;


import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleDailyMapper;
import com.wangzhixuan.model.PeopleDaily;
import com.wangzhixuan.service.PeopleDailyService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleDailyVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sterm on 2016/11/22.
 */
public class PeopleDailyServiceImpl implements PeopleDailyService {

    @Autowired
    private PeopleDailyMapper peopleDailyMapper;

    @Autowired
    private DictMapper dictMapper;


    @Override
    public PeopleDaily findPeopleDailyById(Long id) {
        return null;
    }

    @Override
    public PeopleDaily findPeopleDailyByName(String name) {
        return peopleDailyMapper.findPeopleDailyByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleDailyMapper.findPeoplePageDailyCondition(pageInfo));
        pageInfo.setTotal(peopleDailyMapper.findPeopleDailyPageCount(pageInfo));
    }

    @Override
    public void addPeopleDaily(PeopleDaily peopleDaily, CommonsMultipartFile file) {
        if (peopleDaily != null){
            if(StringUtils.isEmpty(peopleDaily.getBirthday())){
                peopleDaily.setBirthday(null);
            }
            if(StringUtils.isEmpty(peopleDaily.getSchoolDate())){
                peopleDaily.setSchoolDate(null);
            }
        }

        if (file!=null){
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleDailyMapper.insert(peopleDaily);
            }
        }else{
            peopleDailyMapper.insert(peopleDaily);
        }
    }

    @Override
    public void updatePeopleDaily(PeopleDaily peopleDaily, CommonsMultipartFile file) {

        if (peopleDaily != null){
            if(StringUtils.isEmpty(peopleDaily.getBirthday())){
                peopleDaily.setBirthday(null);
            }
            if(StringUtils.isEmpty(peopleDaily.getSchoolDate())){
                peopleDaily.setSchoolDate(null);
            }
        }

        if(file != null){
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath, file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleDailyMapper.updatePeopleDaily(peopleDaily);
            }
        }else{
            peopleDailyMapper.updatePeopleDaily(peopleDaily);
        }

    }

    @Override
    public void deletePeopleDailyById(Long id) {
        peopleDailyMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleDailyByIds(String[] ids) {
        peopleDailyMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleDaily> list = new ArrayList<PeopleDaily>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleDailyInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleDailyMapper.insertByImport(list)>0;
            }
        }
        return flag;

    }

    private List<PeopleDaily> getPeopleDailyInfoByExcel(List<PeopleDaily> list, String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleDaily p=new PeopleDaily();

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                //姓名
                if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
                    continue;
                }
                String name=row.getCell(1).toString().trim();
                p.setName(name);

                //部门
                if(!StringUtilExtra.isBlank(row.getCell(2))) {
                    String departmentName = row.getCell(2).toString().trim();
                    p.setDepartmentName(departmentName);
                }

                //工种
                if(!StringUtilExtra.isBlank(row.getCell(3))) {
                    String jobName = row.getCell(3).toString().trim();
                    p.setJobName(jobName);
                }

                //性别
                if(!StringUtilExtra.isBlank(row.getCell(4))){
                    String sex=row.getCell(4).toString().trim();
                    p.setSex(sex.equals("女")?1:0);
                }

                //民族
                if(!StringUtilExtra.isBlank(row.getCell(5))) {
                    String nationalName = row.getCell(5).toString().trim();
                    try {
                        Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                        if (nationalId != null) {
                            p.setNationalId(nationalId);
                        }
                    } catch (Exception exp) {

                    }
                }

                //所在省
                if(!StringUtilExtra.isBlank(row.getCell(6))){
                    String province = row.getCell(6).toString().trim();
                    p.setProvince(province);
                }

                //所在市
                if(!StringUtilExtra.isBlank(row.getCell(7))){
                    String city = row.getCell(7).toString().trim();
                    p.setCity(city);
                }

                //生日
                if(!StringUtilExtra.isBlank(row.getCell(8))){
                    String birthday=row.getCell(8).toString().trim();
                    p.setBirthday(birthday);
                }

                //学历信息/文化程度
                if(!StringUtilExtra.isBlank(row.getCell(9))){
                    String educationName = row.getCell(9).toString().trim();
                    p.setEducationName(educationName);
                }

                //政治面目
                if(!StringUtilExtra.isBlank(row.getCell(10))){
                    String politicaName = row.getCell(10).toString().trim();
                    p.setPoliticalName(politicaName);
                }

                //到院时间
                if(!StringUtilExtra.isBlank(row.getCell(11))){
                    String schoolDate=row.getCell(11).toString().trim();
                    p.setSchoolDate(schoolDate);
                }

                //联系电话
                if(!StringUtilExtra.isBlank(row.getCell(12))){
                    String mobile=row.getCell(12).toString().trim();
                    p.setMobile(mobile);
                }

                //备注
                if(!StringUtilExtra.isBlank(row.getCell(13))){
                    String comment=row.getCell(13).toString().trim();
                    p.setComment(comment);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    //导出Excel
    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {

        List list=peopleDailyMapper.selectPeopleDailyVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="日工资人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("日工资人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");           row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("姓名");           row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("部门");           row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("工种");           row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("性别");           row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("民族");           row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("来自省(区,市)");   row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("出生年月");        row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("文化程度");         row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("政治面貌");         row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("来院日期");        row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("联系电话");        row.getCell(11).setCellStyle(setBorder);
                row.createCell(12).setCellValue("备注");              row.getCell(12).setCellStyle(setBorder);

                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleDailyVo p=(PeopleDailyVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);                    row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());            row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getDepartmentName());  row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getJobName());         row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女")); row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getNationalName());    row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getProvince() + p.getCity()); row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getBirthday());        row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getEducationName());   row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getPoliticalName());   row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getSchoolDate());      row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getMobile());         row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getComment());        row.getCell(12).setCellStyle(setBorder);

                    row.setHeight((short) 400);
                }
                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();
            }catch (IOException e) {
                e.printStackTrace();
            }finally{

            }
        }
    }

    @Override
    public void exportWord(HttpServletResponse response, String id) {

        PeopleDailyVo p= peopleDailyMapper.findPeopleDailyVoById(Long.valueOf(id));

        if(p!=null){

            String filePath=this.getClass().getResource("/template/peopleDailyInfo.docx").getPath();
            String newFileName="日工资人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${name}",           p.getName());
            params.put("${department}",     p.getDepartmentName());
            params.put("${jobName}",        p.getJobName());
            params.put("${sex}",            p.getSex()==0?"男":"女");
            params.put("${nationalName}",   p.getNationalName());
            params.put("${province}",       p.getProvince());
            params.put("${city}",           p.getCity());
            params.put("${birthday}",       p.getBirthday());
            params.put("${education}",      p.getEducationName());
            params.put("${political}",      p.getPoliticalName());
            params.put("${schoolDate}",     p.getSchoolDate());
            params.put("${mobile}",         p.getMobile());
            params.put("${comment}",        p.getComment());

            //判断是否有头像
            if(StringUtils.isNotBlank(p.getPhoto())){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }




    @Override
    public String findPeopleDailyIDsByCondition(PageInfo pageInfo) {
        return null;
    }

}

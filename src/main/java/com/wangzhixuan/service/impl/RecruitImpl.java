package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.RecruitMapper;
import com.wangzhixuan.model.Recruit;
import com.wangzhixuan.service.RecruitService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.RecruitVo;
import com.wangzhixuan.vo.TrainingVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sterm on 2017/2/14.
 */
@Service
public class RecruitImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(recruitMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(recruitMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<RecruitVo> recruitList = recruitMapper.findPeoplePageCondition(pageInfo);

        if (recruitList == null || recruitList.size() < 0)
            return ids;

        for (int i = 0; i < recruitList.size(); i++) {
            ids = ids + recruitList.get(i).getId().toString() + ",";
        }
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }
    @Override
    public void add(RecruitVo recruitVo,CommonsMultipartFile file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //当日期不为空，而是""的时候，需要修改为null，否则插入会有错误
        UpdateDate(recruitVo);

        Recruit recruit = new Recruit();
        BeanUtils.copyProperties(recruit,recruitVo);


        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                recruit.setPhoto(uploadPath);
                recruitMapper.insert(recruit);
            }
        }else{
            recruitMapper.insert(recruit);
        }


}

        @Override
    public Recruit findRecruitById(Integer id) {
        return recruitMapper.findRecruitById(id);
    }

    @Override
    public RecruitVo findRecruitVoById(Integer id){
        RecruitVo recruitVo = recruitMapper.findRecruitVoById(id);
        return recruitVo;
    }


    @Override
    public void update(RecruitVo recruitVo, CommonsMultipartFile file) throws InvocationTargetException, IllegalAccessException {
        UpdateDate(recruitVo);

        Recruit recruit = new Recruit();
        BeanUtils.copyProperties(recruit,recruitVo);

        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                recruit.setPhoto(uploadPath);
                recruitMapper.update(recruit);
            }
        }else{
            recruitMapper.update(recruit);
        }
    }

    @Override
    public void update(RecruitVo recruitVo){
        Recruit recruit = new Recruit();
        try {
            BeanUtils.copyProperties(recruit,recruitVo);
            recruitMapper.update(recruit);
        } catch (Exception e) {

        }
    }

    @Override
    public void delete(Integer id) {

        recruitMapper.delete(id);
    }

    @Override
    public void batchDel(String[] idList) {
        recruitMapper.batchDeleteByIds(idList);

    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag = false;

        if (files != null && files.length > 0) {

            List<Recruit> list = new ArrayList<Recruit>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for (int i = 0; i < files.length; i++) {

                String path = UploadUtil.fileUpload(filePath, files[i]);

                if (StringUtils.isNotBlank(path)) {
                    Recruit recruit = getPeopleInfoByExcel(path);

                    if (recruit != null && StringUtils.isNoneBlank(recruit.getName()){
                        list.add(recruit);
                    }
                }
            }
            
            if (list.size() > 0) {
                flag = recruitMapper.insertByImport(list) > 0;
            }
        }
        return flag;
    }

    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {
        List list = recruitMapper.selectRecruitVoByIds(idList);
        if (list != null && list.size() > 0) {
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName = "应聘人员信息.xlsx";

            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet = workBook.createSheet("应聘人员信息");
                XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);

                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getRecruitHeaders());
                setBorder = WordUtil.setCellStyle(workBook, false);

                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + 1);
                    RecruitVo p = (RecruitVo) list.get(i);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getSex() == null ? "" : (p.getSex() == 0 ? "男" : "女"));row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getAge() == null ? "" : p.getAge().toString());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getMajor());row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getApplyJob());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getOrigin());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getNationalName());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getBirthday());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getPoliticalName());row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getHealth());row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getGraduateSchool());row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getDegree());row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getDegreeOnTime() == null ? "" : (p.getDegreeOnTime() == 0 ? "是" : "否"));row.getCell(13).setCellStyle(setBorder);
                    row.createCell(14).setCellValue(p.getSchoolAddress());row.getCell(14).setCellStyle(setBorder);
                    row.createCell(15).setCellValue(p.getGraduateStatus());row.getCell(15).setCellStyle(setBorder);
                    row.createCell(16).setCellValue(p.getForeignLanguageLevel());row.getCell(16).setCellStyle(setBorder);
                    row.createCell(17).setCellValue(p.getMarriageStatus());row.getCell(17).setCellStyle(setBorder);
                    row.createCell(18).setCellValue(p.getCellphone());row.getCell(18).setCellStyle(setBorder);
                    row.createCell(19).setCellValue(p.getPhotoId());row.getCell(19).setCellStyle(setBorder);
                    row.createCell(20).setCellValue(p.getEmail());row.getCell(20).setCellStyle(setBorder);
                    row.createCell(21).setCellValue(p.getAddress());row.getCell(21).setCellStyle(setBorder);
                    row.createCell(22).setCellValue(p.getZipcode());row.getCell(22).setCellStyle(setBorder);
                    row.createCell(23).setCellValue(p.getStudyExperience());row.getCell(23).setCellStyle(setBorder);
                    row.createCell(24).setCellValue(p.getSpecialityAndAbility());row.getCell(24).setCellStyle(setBorder);
                    row.createCell(25).setCellValue(p.getSocialExperience());row.getCell(25).setCellStyle(setBorder);
                    row.createCell(26).setCellValue(p.getAward());row.getCell(26).setCellStyle(setBorder);
                    for (int j = 0; j < 27; j++) {
                        row.getCell(j).setCellStyle(setBorder);
                    }
                    row.setHeight((short) 400);
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            } catch (Exception exp) {
                exp.printStackTrace();
            } finally {

            }
        }
    }

    private void UpdateDate(RecruitVo recruitVo) {
        if (StringUtils.isBlank(recruitVo.getBirthday()))
            recruitVo.setBirthday(null);
    }

    private Recruit getPeopleInfoByExcel(String path) {
        Recruit p = new Recruit();

        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            if (pictureList != null && pictureList.size() > 0){
                XSSFPictureData picture = pictureList.get(0);
                String filePath = StringUtilExtra.getPictureUploadPath();
                String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                if (StringUtils.isNotBlank(uploadPath)){
                    p.setPhoto(uploadPath);
                }
            }//照片

            XSSFRow row = sheet.getRow(1);
            //姓名
            String name = row.getCell(1).toString().trim();
            p.setName(name);

            //性别
            String sex = row.getCell(3).toString().trim();
            p.setSex(sex.equals("女") ? 1 : 0);

            //年龄
            String age = row.getCell(5).toString().trim();
            try {
                p.setAge(Integer.valueOf(age));
            } catch (Exception exp) {

            }

            //专业
            String major = row.getCell(7).toString().trim();
            p.setMajor(major);

            //应聘岗位
            row = sheet.getRow(2);
            String applyJob = row.getCell(1).toString().trim();
            p.setApplyJob(applyJob);

            //生源地
            String origin = row.getCell(3).toString().trim();
            p.setOrigin(origin);

            //民族
            String nationalName = row.getCell(5).toString().trim();
            try {
                Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                if (nationalId != null) {
                    p.setNationalId(nationalId);
                }
            } catch (Exception exp) {

            }

            //出生日期
            row = sheet.getRow(3);
            String birthday = row.getCell(1).toString().trim();
            p.setBirthday(birthday);

            //政治面貌
            String politicalName = row.getCell(3).toString().trim();
            p.setPoliticalName(politicalName);

            //身体状况
            String health = row.getCell(5).toString().trim();
            p.setHealth(health);

            row = sheet.getRow(4);

            //毕业院校
            String graduateSchool = row.getCell(1).toString().trim();
            p.setGraduateSchool(graduateSchool);

            //学历
            String degree = row.getCell(3).toString().trim();
            p.setDegree(degree);

            //是否能按期获得学位
            String degreeOnTime = row.getCell(13).toString().trim();
            p.setDegreeOnTime(degreeOnTime.equals("否") ? 1 : 0);

            row = sheet.getRow(5);

            //院校所在地
            String schoolAddress = row.getCell(14).toString().trim();
            p.setSchoolAddress(schoolAddress);

            //毕业生性质
            String graduateStatus = row.getCell(15).toString().trim();
            p.setGraduateStatus(graduateStatus);

            row = sheet.getRow(6);
            //外语水平
            String foreignLanguageLevel = row.getCell(16).toString().trim();
            p.setForeignLanguageLevel(foreignLanguageLevel);

            //婚姻状况
            String marriageName = row.getCell(17).toString().trim();
            Integer marriageId = dictMapper.findMarriageIdByName(marriageName);
            if (marriageId != null){
                p.setMarriageId(marriageId);
            }

            //联系电话
            String cellphone = row.getCell(18).toString().trim();
            p.setCellphone(cellphone);


            row = sheet.getRow(7);


            //身份证号码
            if(row.getCell(19) != null && !row.getCell(19).toString().trim().equals("")){
                String photoId = row.getCell(19).toString().trim();
                p.setPhotoId(photoId);
            }

            //邮箱
            if(row.getCell(20) != null && !row.getCell(20).toString().trim().equals("")){
                String email = row.getCell(20).toString().trim();
                p.setEmail(email);
            }

            row = sheet.getRow(8);

            //联系地址
            if(row.getCell(21) != null && !row.getCell(21).toString().trim().equals("")){
                String address = row.getCell(21).toString().trim();
                p.setAddress(address);
            }
            //邮政编码
            if(row.getCell(22) != null && !row.getCell(22).toString().trim().equals("")){
                String zipcode = row.getCell(22).toString().trim();
                p.setZipcode(zipcode);
            }

            row = sheet.getRow(10);

            //主要学习工作经历
            if(row.getCell(23) != null && !row.getCell(23).toString().trim().equals("")){
                String studyExperience = row.getCell(23).toString().trim();
                p.setStudyExperience(studyExperience);
            }

            row = sheet.getRow(15);
            //特长及能力
            if(row.getCell(24) != null && !row.getCell(24).toString().trim().equals("")){
                String specialityAndAbility = row.getCell(24).toString().trim();
                p.setSpecialityAndAbility(specialityAndAbility);
            }

            row = sheet.getRow(20);
            //主要社会实践
            if(row.getCell(25) != null && !row.getCell(25).toString().trim().equals("")){
                String socialExperience = row.getCell(25).toString().trim();
                p.setSocialExperience(socialExperience);
            }

            row = sheet.getRow(23);
            //获奖情况
            if(row.getCell(26) != null && !row.getCell(26).toString().trim().equals("")){
                String award = row.getCell(26).toString().trim();
                p.setAward(award);
            }


        } catch (IOException e1) {
            e1.printStackTrace();

        }
        return p;
    }
}

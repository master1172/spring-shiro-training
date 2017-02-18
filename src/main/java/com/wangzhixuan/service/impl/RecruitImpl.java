package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.RecruitMapper;
import com.wangzhixuan.mapper.TrainingMapper;
import com.wangzhixuan.model.Abroad;
import com.wangzhixuan.model.Recruit;
import com.wangzhixuan.model.Training;
import com.wangzhixuan.service.RecruitService;
import com.wangzhixuan.service.TrainingService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.RecruitVo;
import com.wangzhixuan.vo.TrainingVo;
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
        List<TrainingVo> recruitList = recruitMapper.findPeoplePageCondition(pageInfo);

        if (recruitList == null || recruitList.size() < 0)
            return ids;

        for (int i = 0; i < recruitList.size(); i++) {
            ids = ids + recruitList.get(i).getId().toString() + ",";
        }
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public Recruit findRecruitById(Integer id) {
        return recruitMapper.findRecruitById(id);
    }

    @Override
    public void add(Recruit recruit) {
        UpdateDate(recruit);
        recruitMapper.insert(recruit);
    }


    @Override
    public void update(Recruit recruit) {
        UpdateDate(recruit);
        recruitMapper.update(recruit);
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
                    list = getPeopleInfoByExcel(list, path);
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
                    row.createCell(1).setCellValue(p.getName());
                    row.createCell(2).setCellValue(p.getSex() == null ? "" : (p.getSex() == 0 ? "男" : "女"));
                    row.createCell(3).setCellValue(p.getAge() == null ? "" : p.getAge().toString());
                    row.createCell(4).setCellValue(p.getMajor());
                    row.createCell(5).setCellValue(p.getApplyJob());
                    row.createCell(6).setCellValue(p.getOrigin());
                    row.createCell(7).setCellValue(p.getNationalName());
                    row.createCell(8).setCellValue(p.getBirthday());
                    row.createCell(9).setCellValue(p.getPoliticalName());
                    row.createCell(10).setCellValue(p.getHealth());
                    row.createCell(11).setCellValue(p.getGraduateSchool());
                    row.createCell(12).setCellValue(p.getDegree());
                    row.createCell(13).setCellValue(p.getDegreeOnTime() == null ? "" : (p.getDegreeOnTime() == 0 ? "是" : "否"));
                    row.createCell(14).setCellValue(p.getSchoolAddress());
                    row.createCell(15).setCellValue(p.getGraduateStatus());
                    row.createCell(16).setCellValue(p.getForeignLanguageLevel());
                    row.createCell(17).setCellValue(p.getMarriageId());
                    row.createCell(18).setCellValue(p.getCellphone());
                    row.createCell(19).setCellValue(p.getPhotoId());
                    row.createCell(10).setCellValue(p.getEmail());
                    row.createCell(21).setCellValue(p.getAddress());
                    row.createCell(22).setCellValue(p.getZipcode());
                    row.createCell(23).setCellValue(p.getStudyExperience());
                    row.createCell(24).setCellValue(p.getSpecialityAndAbility());
                    row.createCell(25).setCellValue(p.getSocialExperience());
                    row.createCell(26).setCellValue(p.getAward());
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

    private void UpdateDate(Recruit recruit) {
        if (StringUtils.isBlank(recruit.getBirthday()))
            recruit.setBirthday(null);
    }

    private List<Recruit> getPeopleInfoByExcel(List<Recruit> list, String path) {
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //

            XSSFRow row;
            for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                Recruit p = new Recruit();

//                p.setCode(StringUtilExtra.generateUUID());

                //姓名
                if (row.getCell(1) == null || row.getCell(1).toString().trim().equals("")) {
                    continue;
                }

                String name = row.getCell(1).toString().trim();
                p.setName(name);

                //性别
                if (row.getCell(2) != null && !row.getCell(2).toString().trim().equals("")) {
                    String sex = row.getCell(2).toString().trim();
                    p.setSex(sex.equals("女") ? 1 : 0);
                }

                //年龄
                if (row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")) {
                    String age = row.getCell(3).toString().trim();
                    try {
                        p.setAge(Integer.valueOf(age));
                    } catch (Exception exp) {

                    }
                } else {
                    Integer age = DateUtil.GetAgeByBirthday(p.getBirthday());
                    p.setAge(age);
                }

                //专业
                if (row.getCell(4) != null && !row.getCell(4).toString().trim().equals("")) {
                    continue;
                }
                String major = row.getCell(4).toString().trim();
                p.setMajor(major);
                //应聘岗位
                if (row.getCell(5) != null && !row.getCell(5).toString().trim().equals("")) {
                    String politicalName = row.getCell(5).toString().trim();
                    p.setPoliticalName(politicalName);
                }
                //生源地
                if (row.getCell(6) != null && !row.getCell(6).toString().trim().equals("")) {
                    String origin = row.getCell(6).toString().trim();
                    p.setOrigin(origin);
                }

                //民族
                if (row.getCell(7) != null && !row.getCell(7).toString().trim().equals("")) {
                    String nationalName = row.getCell(7).toString().trim();
                    try {
                        Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                        if (nationalId != null) {
                            p.setNationalId(nationalId);
                        }
                    } catch (Exception exp) {

                    }
                }

                //出生日期
                if (row.getCell(8) != null && !row.getCell(8).toString().trim().equals("")) {
                    String birthday = row.getCell(8).toString().trim();
                    p.setOrigin(birthday);
                }
                //政治面貌
                if (row.getCell(9) != null && !row.getCell(9).toString().trim().equals("")) {
                    String politicalName = row.getCell(9).toString().trim();
                    p.setPoliticalName(politicalName);
                }
                //身体状况
                if (row.getCell(10) != null && !row.getCell(10).toString().trim().equals("")) {
                    String health = row.getCell(10).toString().trim();
                    p.setHealth(health);
                }
                //毕业院校
                if (row.getCell(11) != null && !row.getCell(11).toString().trim().equals("")) {
                    String graduateSchool = row.getCell(11).toString().trim();
                    p.setGraduateSchool(graduateSchool);
                }
                //学历
                if (row.getCell(12) != null && !row.getCell(12).toString().trim().equals("")) {
                    String degree = row.getCell(12).toString().trim();
                    p.setDegree(degree);
                }
                //是否能按期获得学位
                if (row.getCell(13) != null && !row.getCell(13).toString().trim().equals("")) {
                    String sex = row.getCell(13).toString().trim();
                    p.setSex(sex.equals("是") ? 1 : 0);
                }
                //院校所在地
                if (row.getCell(14) != null && !row.getCell(14).toString().trim().equals("")) {
                    String schoolAddress = row.getCell(14).toString().trim();
                    p.setSchoolAddress(schoolAddress);
                }
                //毕业生性质
                if (row.getCell(15) != null && !row.getCell(15).toString().trim().equals("")) {
                    String graduateStatus = row.getCell(15).toString().trim();
                    p.setGraduateStatus(graduateStatus);
                }
                //外语水平
                if (row.getCell(16) != null && !row.getCell(16).toString().trim().equals("")) {
                    String foreignLanguageLevel = row.getCell(16).toString().trim();
                    p.setForeignLanguageLevel(foreignLanguageLevel);
                }
                //婚姻状况
                if(row.getCell(17) != null && !row.getCell(17).toString().trim().equals("")){
                    String marriageName = row.getCell(17).toString().trim();
                    Integer marriageId = dictMapper.findMarriageIdByName(marriageName);
                    if (marriageId != null){
                        p.setMarriageId(marriageId);
                    }
                }

                //联系电话
                if (row.getCell(18) != null && !row.getCell(18).toString().trim().equals("")) {
                    String cellphone = row.getCell(18).toString().trim();
                    p.setCellphone(cellphone);
                }
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
                //主要学习工作经历
                if(row.getCell(23) != null && !row.getCell(23).toString().trim().equals("")){
                    String studyExperience = row.getCell(23).toString().trim();
                    p.setStudyExperience(studyExperience);
                }
                //特长及能力
                if(row.getCell(24) != null && !row.getCell(24).toString().trim().equals("")){
                    String specialityAndAbility = row.getCell(24).toString().trim();
                    p.setSpecialityAndAbility(specialityAndAbility);
                }
                //主要社会实践
                if(row.getCell(25) != null && !row.getCell(25).toString().trim().equals("")){
                    String socialExperience = row.getCell(25).toString().trim();
                    p.setSocialExperience(socialExperience);
                }
                //获奖情况
                if(row.getCell(26) != null && !row.getCell(26).toString().trim().equals("")){
                    String award = row.getCell(26).toString().trim();
                    p.setAward(award);
                }


                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();

        }
        return list;
    }
}
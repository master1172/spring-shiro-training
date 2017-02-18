package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleDeathMapper;
import com.wangzhixuan.model.PeopleDeath;
import com.wangzhixuan.service.PeopleDeathService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleDeathVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/24.
 */
@Service
public class PeopleDeathServiceImpl implements PeopleDeathService {

    @Autowired
    private PeopleDeathMapper peopleDeathMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleDeath findPeopleById(Integer id) {
        return peopleDeathMapper.findPeopleById(id);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleDeathMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(peopleDeathMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public void addPeople(PeopleDeath people, CommonsMultipartFile file) {
        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (people != null){
            if (StringUtils.isEmpty(people.getBirthday())){
                people.setBirthday(null);
            }
            if (StringUtils.isEmpty(people.getSchool_date())){
                people.setSchool_date(null);
            }
            if (StringUtils.isEmpty(people.getDeath_date())){
                people.setDeath_date(null);
            }
        }

        people.setCode(StringUtilExtra.generateUUID());

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleDeathMapper.insert(people);
            }
        }else{
            peopleDeathMapper.insert(people);
        }
    }

    @Override
    public void updatePeople(PeopleDeath people, CommonsMultipartFile file) {
        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (people != null){
            if (StringUtils.isEmpty(people.getBirthday())){
                people.setBirthday(null);
            }
            if (StringUtils.isEmpty(people.getSchool_date())){
                people.setSchool_date(null);
            }
            if (StringUtils.isEmpty(people.getDeath_date())){
                people.setDeath_date(null);
            }
        }

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleDeathMapper.updatePeople(people);
            }
        }else{
            peopleDeathMapper.updatePeople(people);
        }
    }

    @Override
    public void deletePeopleById(Integer id) {
        peopleDeathMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleByIds(String[] ids) {
        peopleDeathMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleDeath> list = new ArrayList<PeopleDeath>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleDeathMapper.insertByImport(list)>0;
            }
        }
        return flag;
    }

    private List<PeopleDeath> getPeopleInfoByExcel(List<PeopleDeath> list, String path) {
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleDeath p=new PeopleDeath();

                p.setCode(StringUtilExtra.generateUUID());

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                //name
                if(row.getCell(1)!=null&&!row.getCell(1).toString().trim().equals("")){
                    String name=row.getCell(1).toString().trim();
                    p.setName(name);
                }

                //性别
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String sex=row.getCell(2).toString().trim();
                    p.setSex(sex.equals("女")?1:0);
                }

                //民族
                if(row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")){
                    String nationalName = row.getCell(3).toString().trim();

                    try{
                        Integer national = dictMapper.findNationalIdByName(nationalName);
                        if (national != null){
                            p.setNational(national);
                        }
                    }catch(Exception exp){

                    }
                }

                //生日
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String birthday=row.getCell(4).toString().trim();
                    p.setBirthday(birthday);
                }

                //到院工作日期
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String school_date=row.getCell(5).toString().trim();
                    p.setSchool_date(school_date);
                }

                //职务
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String category=row.getCell(6).toString().trim();
                    p.setJobName(category);
                }

                //职级
                if(row.getCell(7) != null && !row.getCell(7).toString().trim().equals("")){
                    String job_level_name = row.getCell(7).toString().trim();

                    try{
                        Integer job_level_id = dictMapper.findJobLevelIdByName(job_level_name);
                        if (job_level_id != null){
                            p.setJob_level_id(job_level_id);
                        }
                    }catch(Exception exp){

                    }
                }

                //部门
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String department=row.getCell(8).toString().trim();
                    Integer departmentId = dictMapper.findDepartmentIdByName(department);
                    p.setDepartment(departmentId);
                }

                //死亡日期
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String death_date=row.getCell(9).toString().trim();
                    p.setDeath_date(death_date);
                }

                //死亡原因
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String death_reason=row.getCell(10).toString().trim();
                    p.setDeath_reason(death_reason);
                }

                //备注
                if(row.getCell(11)!=null&&!row.getCell(11).toString().trim().equals("")){
                    String comment=row.getCell(11).toString().trim();
                    p.setComment(comment);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {
        List list=peopleDeathMapper.selectPeopleVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="已故人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("已故人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("人员姓名");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("性别");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("民族");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("出生日期");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("到院工作日期");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("职务");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("职级");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("部门");row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("死亡日期");row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("死亡原因");row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("备注");row.getCell(11).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleDeathVo p=(PeopleDeathVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getNationalName());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getBirthday()==null?"":(p.getBirthday().toString()));row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getSchool_date()==null?"":(p.getSchool_date().toString()));row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getJobName());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getJob_level_name());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getDepartment());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getDeath_date()==null?"":(p.getDeath_date().toString()));row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getDeath_reason());row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getComment());row.getCell(11).setCellStyle(setBorder);
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

    //导出word
    @Override
    public void exportWord(HttpServletResponse response,String id){
        PeopleDeathVo p= peopleDeathMapper.findPeopleVoById(Integer.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoDeath.docx").getPath();
            String newFileName="已故人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${code}",p.getCode());
            params.put("${name}",p.getName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${national}",p.getNationalName());
            params.put("${birthday}",p.getBirthday());
            params.put("${school_date}",p.getSchool_date());
            params.put("${category}",p.getJobName());
            params.put("${job_level_name}",p.getJob_level_name());
            params.put("${department}",p.getDepartment());
            params.put("${death_date}",p.getDeath_date());
            params.put("${death_reason}",p.getDeath_reason());
            params.put("${comment}",p.getComment());

            //判断是否有头像
            if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }else{
                params.put("${photo}","");
            }

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {

        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleDeathVo> peopleList = peopleDeathMapper.findPeoplePageCondition(pageInfo);
        if (peopleList == null || peopleList.size() < 1)
            return ids;


        for(int i=0; i<peopleList.size(); i++){
            ids = ids + peopleList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }
}

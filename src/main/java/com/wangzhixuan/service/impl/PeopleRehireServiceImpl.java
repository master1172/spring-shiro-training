package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleRehireMapper;
import com.wangzhixuan.model.PeopleRehire;
import com.wangzhixuan.service.PeopleRehireService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleRehireVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeopleRehireServiceImpl implements PeopleRehireService{


    @Autowired
    private PeopleRehireMapper peopleRehireMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleRehire findPeopleRehireById(Long id) {
        return peopleRehireMapper.findPeopleRehireById(id);
    }

    @Override
    public PeopleRehire findPeopleRehireByName(String name) {
        return peopleRehireMapper.findPeopleRehireByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleRehireMapper.findPeopleRehirePageCondition(pageInfo));
        pageInfo.setTotal(peopleRehireMapper.findPeopleRehirePageCount(pageInfo));
    }

    @Override
    public void addPeopleRehire(PeopleRehire peopleRehire,CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRehire != null){
            if (StringUtils.isEmpty(peopleRehire.getBirthday())){
                peopleRehire.setBirthday(null);
            }
        }

        //当rehireDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRehire != null){
            if (StringUtils.isEmpty(peopleRehire.getRetireDate())){
                peopleRehire.setRetireDate(null);
            }
        }

        peopleRehire.setCode(StringUtilExtra.generateUUID());

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleRehireMapper.insert(peopleRehire);
            }
        }else{
            peopleRehireMapper.insert(peopleRehire);
        }
    }

    @Override
    public void updatePeopleRehire(PeopleRehire peopleRehire, CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRehire != null){
            if (StringUtils.isEmpty(peopleRehire.getBirthday())){
                peopleRehire.setBirthday(null);
            }
        }

        //当retireDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRehire != null){
            if (StringUtils.isEmpty(peopleRehire.getRetireDate())){
                peopleRehire.setRetireDate(null);
            }
        }


        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleRehireMapper.updatePeopleRehire(peopleRehire);
            }
        }else{
            peopleRehireMapper.updatePeopleRehire(peopleRehire);
        }
    }

    @Override
    public void deletePeopleRehireById(Long id) {
        peopleRehireMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleRehireByIds(String[] ids){
        peopleRehireMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleRehire> list = new ArrayList<PeopleRehire>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleRehireInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleRehireMapper.insertByImport(list)>0;
            }
        }
        return flag;
    }
    /**
     * 文件读取
     * @param list
     * @param path
     * @return
     */
    private List<PeopleRehire> getPeopleRehireInfoByExcel(List<PeopleRehire> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleRehire p=new PeopleRehire();

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                p.setCode(StringUtilExtra.generateUUID());

                //姓名
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
                        Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                        if (nationalId != null){
                            p.setNationalId(nationalId);
                        }
                    }catch(Exception exp){

                    }
                }

                //籍贯
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String nativeName=row.getCell(4).toString().trim();
                    p.setNativeName(nativeName);
                }

                //出生地
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String birthPlace=row.getCell(5).toString().trim();
                    p.setBirthPlace(birthPlace);
                }

                //出生日期
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String birthday=row.getCell(6).toString().trim();
                    p.setBirthday(birthday);
                }

                //文化程度
                if(row.getCell(7)!=null&&!row.getCell(7).toString().trim().equals("")){
                    String educationName=row.getCell(7).toString().trim();
                    p.setEducationName(educationName);
                }

                //政治面貌
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String politicalName=row.getCell(8).toString().trim();
                    p.setPoliticalName(politicalName);
                }

                //健康状况
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String healthStatus=row.getCell(9).toString().trim();
                    p.setHealthStatus(healthStatus);
                }

                //退休日期
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String retireDate=row.getCell(10).toString().trim();
                    p.setRetireDate(retireDate);
                }

                //专业技术及专长
                if(row.getCell(11)!=null&&!row.getCell(11).toString().trim().equals("")){
                    String speciality=row.getCell(11).toString().trim();
                    p.setSpeciality(speciality);
                }

                //返聘前工作部门
                if(row.getCell(12)!=null&&!row.getCell(12).toString().trim().equals("")){
                    String beforeDepartment=row.getCell(12).toString().trim();

                    try{
                        Integer beforeDepartmentId = dictMapper.findDepartmentIdByName(beforeDepartment);
                        if (beforeDepartmentId != null){
                            p.setBeforeDepartmentId(beforeDepartmentId);
                        }
                    }catch(Exception exp){

                    }
                }

                //返聘前岗位
                if(row.getCell(13)!=null&&!row.getCell(13).toString().trim().equals("")){
                    String beforeJobName=row.getCell(13).toString().trim();
                    p.setBeforeJobName(beforeJobName);
                }

                //返聘前职级
                if(row.getCell(14) != null && !row.getCell(14).toString().trim().equals("")){
                    String beforeJobLevelName = row.getCell(14).toString().trim();

                    try{
                        Integer beforeJobLevelId = dictMapper.findJobIdByName(beforeJobLevelName);
                        if (beforeJobLevelId != null){
                            p.setBeforeJobLevelId(beforeJobLevelId);
                        }
                    }catch(Exception exp){

                    }
                }

                //拟返聘工作部门
                if(row.getCell(15) != null && !row.getCell(15).toString().trim().equals("")){
                    String afterDepartmentName = row.getCell(15).toString().trim();

                    try{
                        Integer afterDepartmentId = dictMapper.findDepartmentIdByName(afterDepartmentName);
                        if (afterDepartmentId != null){
                            p.setAfterDepartmentId(afterDepartmentId);
                        }
                    }catch(Exception exp){

                    }
                }

                //拟返聘岗位
                if(row.getCell(16)!=null&&!row.getCell(16).toString().trim().equals("")){
                    String afterJobName=row.getCell(16).toString().trim();
                    p.setAfterJobName(afterJobName);
                }

                //拟返聘职级
                if(row.getCell(17) != null && !row.getCell(17).toString().trim().equals("")){
                    String afterJobLevelName = row.getCell(17).toString().trim();

                    try{
                        Integer afterJobLevelId = dictMapper.findJobIdByName(afterJobLevelName);
                        if (afterJobLevelId != null){
                            p.setAfterJobLevelId(afterJobLevelId);
                        }
                    }catch(Exception exp){

                    }
                }

                //身份证号
                if(row.getCell(18)!=null&&!row.getCell(18).toString().trim().equals("")){
                    String photoId=row.getCell(18).toString().trim();
                    p.setPhotoId(photoId);
                }

                //家庭住址
                if(row.getCell(19)!=null&&!row.getCell(19).toString().trim().equals("")){
                    String address=row.getCell(19).toString().trim();
                    p.setAddress(address);
                }

                //户籍所在地
                if(row.getCell(20)!=null&&!row.getCell(20).toString().trim().equals("")){
                    String hukouAddress=row.getCell(20).toString().trim();
                    p.setHukouAddress(hukouAddress);
                }

                //返聘人员类型
                if(row.getCell(21)!=null&&!row.getCell(21).toString().trim().equals("")){
                    String category=row.getCell(21).toString().trim();
                    p.setCategory(category);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    //导出excel
    @Override
    public void exportExcel(HttpServletResponse response,String[] idList){
        List list=peopleRehireMapper.selectPeopleRehireVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="返聘人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("返聘人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("姓名");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("性别");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("民族");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("籍贯");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("出生地");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("出生日期");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("文化程度");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("政治面貌");row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("健康状况");row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("退休日期");row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("专业技术及专长");row.getCell(11).setCellStyle(setBorder);
                row.createCell(12).setCellValue("返聘前工作部门");row.getCell(12).setCellStyle(setBorder);
                row.createCell(13).setCellValue("返聘前岗位");row.getCell(13).setCellStyle(setBorder);
                row.createCell(14).setCellValue("返聘前职级");row.getCell(14).setCellStyle(setBorder);
                row.createCell(15).setCellValue("拟返聘工作部门");row.getCell(15).setCellStyle(setBorder);
                row.createCell(16).setCellValue("拟返聘岗位");row.getCell(16).setCellStyle(setBorder);
                row.createCell(17).setCellValue("拟返聘职级");row.getCell(17).setCellStyle(setBorder);
                row.createCell(18).setCellValue("身份证号");row.getCell(18).setCellStyle(setBorder);
                row.createCell(19).setCellValue("家庭住址");row.getCell(19).setCellStyle(setBorder);
                row.createCell(20).setCellValue("户籍所在地");row.getCell(20).setCellStyle(setBorder);
                row.createCell(21).setCellValue("返聘人员类型");row.getCell(21).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleRehireVo p=(PeopleRehireVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getNationalName());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getNativeName());row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getBirthPlace());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getBirthday());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getEducationName());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getPoliticalName());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getHealthStatus());row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getRetireDate());row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getSpeciality());row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getBeforeDepartmentName());row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getBeforeJobName());row.getCell(13).setCellStyle(setBorder);
                    row.createCell(14).setCellValue(p.getBeforeJobLevelName());row.getCell(14).setCellStyle(setBorder);
                    row.createCell(15).setCellValue(p.getAfterDepartmentName());row.getCell(15).setCellStyle(setBorder);
                    row.createCell(16).setCellValue(p.getAfterJobName());row.getCell(16).setCellStyle(setBorder);
                    row.createCell(17).setCellValue(p.getBeforeJobLevelName());row.getCell(17).setCellStyle(setBorder);
                    row.createCell(18).setCellValue(p.getPhotoId());row.getCell(18).setCellStyle(setBorder);
                    row.createCell(19).setCellValue(p.getAddress());row.getCell(19).setCellStyle(setBorder);
                    row.createCell(20).setCellValue(p.getHukouAddress());row.getCell(20).setCellStyle(setBorder);
                    row.createCell(21).setCellValue(p.getCategory());row.getCell(21).setCellStyle(setBorder);
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
        PeopleRehireVo p= peopleRehireMapper.findPeopleRehireVoById(Long.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoRehire.docx").getPath();
            String newFileName="返聘人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${code}",p.getCode());
            params.put("${name}",p.getName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${nationalName}",p.getNationalName());
            params.put("${nativeName}",p.getNativeName());
            params.put("${birthPlace}",p.getBirthPlace());
            params.put("${birthday}",p.getBirthday());
            params.put("${educationName}",p.getEducationName());
            params.put("${politicalName}",p.getPoliticalName());
            params.put("${healthStatus}",p.getHealthStatus());
            params.put("${retireDate}",p.getRetireDate());
            params.put("${speciality}",p.getSpeciality());
            params.put("${beforeDepartment}",p.getBeforeDepartmentName());
            params.put("${beforeJobName}",p.getBeforeJobName());
            params.put("${beforeJobLevelName}",p.getBeforeJobLevelName());
            params.put("${afterDepartmentName}",p.getAfterDepartmentName());
            params.put("${afterJobName}",p.getAfterJobName());
            params.put("${afterJobLevelName}",p.getAfterJobLevelName());
            params.put("${photoId}",p.getPhotoId());
            params.put("${address}",p.getAddress());
            params.put("${hukouAddress}",p.getHukouAddress());
            params.put("${category}",p.getCategory());

            //判断是否有头像
            if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }

    @Override
    public String findPeopleRehireIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleRehireVo> peopleRehireList = peopleRehireMapper.findPeopleRehirePageCondition(pageInfo);
        if (peopleRehireList == null || peopleRehireList.size() < 1)
            return ids;


        for(int i=0; i<peopleRehireList.size(); i++){
            ids = ids + peopleRehireList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }
}


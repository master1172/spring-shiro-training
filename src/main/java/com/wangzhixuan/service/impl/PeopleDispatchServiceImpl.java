package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleDispatchMapper;
import com.wangzhixuan.mapper.PeopleTempMapper;
import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.model.PeopleTemp;
import com.wangzhixuan.service.PeopleDispatchService;
import com.wangzhixuan.service.PeopleTempService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleDispatchVo;
import com.wangzhixuan.vo.PeopleTempVo;
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
public class PeopleDispatchServiceImpl implements PeopleDispatchService {


    @Autowired
    private PeopleDispatchMapper peopleDispatchMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleDispatch findPeopleDispatchById(Long id) {
        return peopleDispatchMapper.findPeopleDispatchById(id);
    }

    @Override
    public PeopleDispatch findPeopleDispatchByName(String name) {
        return peopleDispatchMapper.findPeopleDispatchByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleDispatchMapper.findPeopleDispatchPageCondition(pageInfo));
        pageInfo.setTotal(peopleDispatchMapper.findPeopleDispatchPageCount(pageInfo));
    }

    @Override
    public void addPeopleDispatch(PeopleDispatch peopleDispatch,CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleDispatch != null){
            if (StringUtils.isEmpty(peopleDispatch.getBirthday())){
                peopleDispatch.setBirthday(null);
            }
        }

        //当schoolDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleDispatch != null){
            if (StringUtils.isEmpty(peopleDispatch.getSchoolDate())){
                peopleDispatch.setSchoolDate(null);
            }
        }

        peopleDispatch.setCode(StringUtilExtra.generateUUID());

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleDispatchMapper.insert(peopleDispatch);
            }
        }else{
            peopleDispatchMapper.insert(peopleDispatch);
        }
    }

    @Override
    public void updatePeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleDispatch != null){
            if (StringUtils.isEmpty(peopleDispatch.getBirthday())){
                peopleDispatch.setBirthday(null);
            }
        }

        //当schoolDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleDispatch != null){
            if (StringUtils.isEmpty(peopleDispatch.getSchoolDate())){
                peopleDispatch.setSchoolDate(null);
            }
        }


        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleDispatchMapper.updatePeopleDispatch(peopleDispatch);
            }
        }else{
            peopleDispatchMapper.updatePeopleDispatch(peopleDispatch);
        }
    }

    @Override
    public void deletePeopleDispatchById(Long id) {
        peopleDispatchMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleDispatchByIds(String[] ids){
        peopleDispatchMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleDispatch> list = new ArrayList<PeopleDispatch>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleDispatchInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleDispatchMapper.insertByImport(list)>0;
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
    private List<PeopleDispatch> getPeopleDispatchInfoByExcel(List<PeopleDispatch> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleDispatch p=new PeopleDispatch();

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

                //来自省
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String province=row.getCell(4).toString().trim();
                    p.setProvince(province);
                }

                //来自市/区
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String city=row.getCell(5).toString().trim();
                    p.setCity(city);
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

                //特长
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String speciality=row.getCell(9).toString().trim();
                    p.setSpeciality(speciality);
                }

                //身高
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String height=row.getCell(10).toString().trim();
                    p.setHeight(height);
                }

                //婚姻状况
                if(row.getCell(11) != null && !row.getCell(11).toString().trim().equals("")){
                    String marriageName = row.getCell(11).toString().trim();

                    try{
                        Integer marriageId = dictMapper.findMarriageIdByName(marriageName);
                        if (marriageId != null){
                            p.setMarriageId(marriageId);
                        }
                    }catch(Exception exp){

                    }
                }

                //户籍
                if(row.getCell(12)!=null&&!row.getCell(12).toString().trim().equals("")){
                    String hukou=row.getCell(12).toString().trim();
                    p.setHukou(hukou.equals("农业")?1:0);
                }

                //来院日期
                if(row.getCell(13)!=null&&!row.getCell(13).toString().trim().equals("")){
                    String schoolDate=row.getCell(13).toString().trim();
                    p.setSchoolDate(schoolDate);
                }

                //联系电话
                if(row.getCell(14)!=null&&!row.getCell(14).toString().trim().equals("")){
                    String mobile=row.getCell(14).toString().trim();
                    p.setMobile(mobile);
                }

                //现住址
                if(row.getCell(15)!=null&&!row.getCell(15).toString().trim().equals("")){
                    String address=row.getCell(15).toString().trim();
                    p.setAddress(address);
                }

                //部门
                if(row.getCell(16)!=null&&!row.getCell(16).toString().trim().equals("")){
                    String departmentName=row.getCell(16).toString().trim();
                    p.setDepartmentName(departmentName);
                }

                //工种
                if(row.getCell(17)!=null&&!row.getCell(17).toString().trim().equals("")){
                    String jobName=row.getCell(17).toString().trim();
                    p.setJobName(jobName);
                }

                //备注
                if(row.getCell(18)!=null&&!row.getCell(18).toString().trim().equals("")){
                    String comment=row.getCell(18).toString().trim();
                    p.setComment(comment);
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
        List list=peopleDispatchMapper.selectPeopleDispatchVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="派遣人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("派遣人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("姓名");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("性别");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("民族");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("来自省");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("来自市/区");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("出生日期");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("文化程度");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("政治面貌");row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("特长");row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("身高");row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("婚姻状况");row.getCell(11).setCellStyle(setBorder);
                row.createCell(12).setCellValue("户籍");row.getCell(12).setCellStyle(setBorder);
                row.createCell(13).setCellValue("来院日期");row.getCell(13).setCellStyle(setBorder);
                row.createCell(14).setCellValue("联系电话");row.getCell(14).setCellStyle(setBorder);
                row.createCell(15).setCellValue("现住址");row.getCell(15).setCellStyle(setBorder);
                row.createCell(16).setCellValue("部门");row.getCell(16).setCellStyle(setBorder);
                row.createCell(17).setCellValue("工种");row.getCell(17).setCellStyle(setBorder);
                row.createCell(18).setCellValue("备注");row.getCell(18).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleDispatchVo p=(PeopleDispatchVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getNationalName());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getProvince());row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getCity());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getBirthday()==null?"":(p.getBirthday().toString()));row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getEducationName());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getPoliticalName());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getSpeciality());row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getHeight());row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getMarriageName());row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getHukou()==null?"":(p.getHukou()==0?"非农业":"农业"));row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getSchoolDate()==null?"":(p.getBirthday().toString()));row.getCell(13).setCellStyle(setBorder);
                    row.createCell(14).setCellValue(p.getMobile());row.getCell(14).setCellStyle(setBorder);
                    row.createCell(15).setCellValue(p.getAddress());row.getCell(15).setCellStyle(setBorder);
                    row.createCell(16).setCellValue(p.getDepartmentName());row.getCell(16).setCellStyle(setBorder);
                    row.createCell(17).setCellValue(p.getJobName());row.getCell(17).setCellStyle(setBorder);
                    row.createCell(18).setCellValue(p.getComment());row.getCell(18).setCellStyle(setBorder);
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
        PeopleDispatchVo p= peopleDispatchMapper.findPeopleDispatchVoById(Long.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoDispatch.docx").getPath();
            String newFileName="派遣人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${code}",p.getCode());
            params.put("${name}",p.getName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${nationalName}",p.getNationalName());
            params.put("${province}",p.getProvince());
            params.put("${city}",p.getCity());
            params.put("${birthday}",p.getBirthday());
            params.put("${educationName}",p.getEducationName());
            params.put("${politicalName}",p.getPoliticalName());
            params.put("${speciality}",p.getSpeciality());
            params.put("${height}",p.getHeight());
            params.put("${marriageName}",p.getMarriageName());
            params.put("${hukou}",p.getSex()==0?"非农业":"农业");
            params.put("${schoolDate}",p.getSchoolDate());
            params.put("${mobile}",p.getMobile());
            params.put("${address}",p.getAddress());
            params.put("${departmentName}",p.getDepartmentName());
            params.put("${jobName}",p.getJobName());
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
    public String findPeopleDispatchIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleDispatchVo> peopleDispatchList = peopleDispatchMapper.findPeopleDispatchPageCondition(pageInfo);
        if (peopleDispatchList == null || peopleDispatchList.size() < 1)
            return ids;


        for(int i=0; i<peopleDispatchList.size(); i++){
            ids = ids + peopleDispatchList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }
}


package com.wangzhixuan.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.mapper.*;
import com.wangzhixuan.model.*;
import com.wangzhixuan.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFChart;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.vo.PeopleRetireVo;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeopleRetireServiceImpl implements PeopleRetireService{

    @Autowired
    private PeopleTotalMapper peopleTotalMapper;

    @Autowired
    private PeopleRetireMapper peopleRetireMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private PeopleJobMapper peopleJobMapper;

    @Autowired
    private PeopleRankMapper peopleRankMapper;

    @Override
    public PeopleRetire findPeopleRetireById(Integer id) {
        return peopleRetireMapper.findPeopleRetireById(id);
    }

    @Override
    public PeopleRetire findPeopleRetireByName(String name) {
        return peopleRetireMapper.findPeopleRetireByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleRetireMapper.findPeopleRetirePageCondition(pageInfo));
        pageInfo.setTotal(peopleRetireMapper.findPeopleRetirePageCount(pageInfo));
    }

    @Override
    public void addPeopleRetire(PeopleRetire peopleRetire,CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getBirthday())){
                peopleRetire.setBirthday(null);
            }
        }

        //当workDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getWorkDate())){
                peopleRetire.setWorkDate(null);
            }
        }

        //当RetireDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getRetireDate())){
                peopleRetire.setRetireDate(null);
            }
        }

        //自动生成code
        peopleRetire.setCode(StringUtilExtra.generateUUID());

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleRetire.setPhoto(uploadPath);
                peopleRetireMapper.insert(peopleRetire);
            }
        }else{
            peopleRetireMapper.insert(peopleRetire);
        }
    }

    @Override
    public void updatePeopleRetire(PeopleRetire peopleRetire, CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getBirthday())){
                peopleRetire.setBirthday(null);
            }
        }

        //当workDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getWorkDate())){
                peopleRetire.setWorkDate(null);
            }
        }

        //当retireDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleRetire != null){
            if (StringUtils.isEmpty(peopleRetire.getRetireDate())){
                peopleRetire.setRetireDate(null);
            }
        }


        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleRetire.setPhoto(uploadPath);
                peopleRetireMapper.updatePeopleRetire(peopleRetire);
            }
        }else{
            peopleRetireMapper.updatePeopleRetire(peopleRetire);
        }
    }

    @Override
    public void deletePeopleRetireById(Integer id) {
        peopleRetireMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleRetireByIds(String[] ids){
        peopleRetireMapper.batchDeleteByIds(ids);
    }

    @Override
    public void batchConvertFromRetireToRehireByIds(String[] ids) {
        if (ids == null || ids.length < 1)
            return;

        for(int i=0; i<ids.length; i++){
            String id = ids[i];
            PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(Integer.valueOf(id));
            peopleTotal.setStatus(ConstUtil.PEOPLE_REHIRE);
            peopleTotalMapper.updateByPrimaryKeySelective(peopleTotal);
        }
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleRetire> list = new ArrayList<PeopleRetire>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleRetireInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleRetireMapper.insertByImport(list)>0;
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
    private List<PeopleRetire> getPeopleRetireInfoByExcel(List<PeopleRetire> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleRetire p=new PeopleRetire();

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                //code自动生成
                p.setCode(StringUtilExtra.generateUUID());
                p.setStatus(ConstUtil.PEOPLE_RETIRE);

                //人员姓名
                if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
                    continue;
                }
                String name=row.getCell(1).toString().trim();
                p.setName(name);

                //退休时职务
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String retireJobName=row.getCell(2).toString().trim();
                    p.setRetireJobName(retireJobName);
                }

                //退休时职级
                if(row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")){
                    String retireJobLevelName = row.getCell(3).toString().trim();

                    try{
                        Integer retireJobLevelId = dictMapper.findJobIdByName(retireJobLevelName);
                        if (retireJobLevelId != null){
                            p.setRetireJobId(retireJobLevelId);
                        }
                    }catch(Exception exp){

                    }
                }

                //性别
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String sex=row.getCell(4).toString().trim();
                    p.setSex(sex.equals("女")?1:0);
                }

                //民族
                if(row.getCell(5) != null && !row.getCell(5).toString().trim().equals("")){
                    String nationalName = row.getCell(5).toString().trim();

                    try{
                        Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                        if (nationalId != null){
                            p.setNationalId(nationalId);
                        }
                    }catch(Exception exp){

                    }
                }

                //学历
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String educationName=row.getCell(6).toString().trim();
                    p.setEducationName(educationName);
                }

                //出生日期
                if(row.getCell(7)!=null&&!row.getCell(7).toString().trim().equals("")){
                    String birthday=row.getCell(7).toString().trim();
                    p.setBirthday(DateUtil.GetDate(birthday));
                }

                //政治面貌
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String politicalName=row.getCell(8).toString().trim();
                    p.setPoliticalName(politicalName);
                }

                //工作日期
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String workDate=row.getCell(9).toString().trim();
                    p.setWorkDate(DateUtil.GetDate(workDate));
                }

                //退休日期
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String retireDate=row.getCell(10).toString().trim();
                    p.setRetireDate(DateUtil.GetDate(retireDate));
                }

                //家庭住址
                if(row.getCell(11)!=null&&!row.getCell(11).toString().trim().equals("")){
                    String address=row.getCell(11).toString().trim();
                    p.setAddress(address);
                }

                //联系电话
                if(row.getCell(12)!=null&&!row.getCell(12).toString().trim().equals("")){
                    String mobile=row.getCell(12).toString().trim();
                    p.setMobile(mobile);
                }

                //固定联系人
                if(row.getCell(13)!=null&&!row.getCell(13).toString().trim().equals("")){
                    String contact=row.getCell(13).toString().trim();
                    p.setContact(contact);
                }

                //联系人电话
                if(row.getCell(14)!=null&&!row.getCell(14).toString().trim().equals("")){
                    String contactNumber=row.getCell(14).toString().trim();
                    p.setContactNumber(contactNumber);
                }

                //备注
                if(row.getCell(15)!=null&&!row.getCell(15).toString().trim().equals("")){
                    String comment=row.getCell(15).toString().trim();
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
        List list=peopleRetireMapper.selectPeopleRetireVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="退休人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("退休人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("姓名");    row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("退休时职务");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("退休时职级");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("性别");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("民族");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("学历");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("出生日期");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("政治面貌");row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("工作日期");row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("退休日期");row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("家庭住址");row.getCell(11).setCellStyle(setBorder);
                row.createCell(12).setCellValue("联系电话");row.getCell(12).setCellStyle(setBorder);
                row.createCell(13).setCellValue("固定联系人");row.getCell(13).setCellStyle(setBorder);
                row.createCell(14).setCellValue("联系人电话");row.getCell(14).setCellStyle(setBorder);
                row.createCell(15).setCellValue("备注");row.getCell(15).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleRetireVo p=(PeopleRetireVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getRetireJobName());row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getRetireJobLevelName());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getNationalName());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getEducationName());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getBirthday());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getPoliticalName());row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getWorkDate());row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getRetireDate());row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getAddress());row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getMobile());row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getContact());row.getCell(13).setCellStyle(setBorder);
                    row.createCell(14).setCellValue(p.getContactNumber());row.getCell(14).setCellStyle(setBorder);
                    row.createCell(15).setCellValue(p.getComment());row.getCell(15).setCellStyle(setBorder);
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
        PeopleRetireVo p= peopleRetireMapper.findPeopleRetireVoById(Integer.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoRetire.docx").getPath();
            String newFileName="退休人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${code}",p.getCode());
            params.put("￥",p.getName());
            params.put("${retireJobName}",p.getRetireJobName());
            params.put("${retire}",p.getRetireJobLevelName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${nationalName}",p.getNationalName());
            params.put("${educationName}",p.getEducationName());
            params.put("${birth}",p.getBirthday());
            params.put("${politicalName}",p.getPoliticalName());
            params.put("${workDate}",p.getWorkDate());
            params.put("${retireDate}",p.getRetireDate());
            params.put("${address}",p.getAddress());
            params.put("${mobile}",p.getMobile());
            params.put("${contact}",p.getContact());
            params.put("${contactNumber}",p.getContactNumber());
            params.put("${status}","退休");
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
    public String findPeopleRetireIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleRetireVo> peopleRetireList = peopleRetireMapper.findPeopleRetirePageCondition(pageInfo);
        if (peopleRetireList == null || peopleRetireList.size() < 1)
            return ids;


        for(int i=0; i<peopleRetireList.size(); i++){
            ids = ids + peopleRetireList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public void batchConvertFromRetireToDeathByIds(String[] ids) {
        if (ids == null || ids.length < 1)
            return;

        for(int i=0; i<ids.length; i++){
            String id = ids[i];
            PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(Integer.valueOf(id));
            peopleTotal.setStatus(ConstUtil.PEOPLE_DEATH);
            peopleTotalMapper.updateByPrimaryKeySelective(peopleTotal);
        }
    }

    @Override
    public void batchConvertFromRetireToNormalByIds(String[] ids) {
        if (ids == null || ids.length < 1)
            return;

        for(int i=0; i<ids.length; i++){
            String id = ids[i];
            PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(Integer.valueOf(id));
            peopleTotal.setStatus(ConstUtil.PEOPLE_NORMAL);
            peopleTotalMapper.updateByPrimaryKeySelective(peopleTotal);
        }
    }

    @Override
    public void retireReview(HttpServletResponse response, String id) {
        PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(Integer.valueOf(id));
        PeopleRetireVo retireVo = peopleRetireMapper.findPeopleRetireVoById(Integer.valueOf(id));

        String filePath=this.getClass().getResource("/template/retireReview.xls").getPath();

        try{
            POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);

            HSSFCell cell1 = sheet.getRow(1).getCell(2);
            cell1.setCellValue(peopleTotal.getName());

            HSSFCell cell2 = sheet.getRow(1).getCell(4);
            String sex = "";
            if (peopleTotal.getSex() != null){
                sex = peopleTotal.getSex() == 0 ? "男" : "女";
            }
            cell2.setCellValue(sex);

            HSSFCell cell3 = sheet.getRow(1).getCell(6);
            cell3.setCellValue(retireVo.getNationalName());

            HSSFCell cell4 = sheet.getRow(2).getCell(2);
            cell4.setCellValue(peopleTotal.getBirthday());

            HSSFCell cell5 = sheet.getRow(2).getCell(4);
            cell5.setCellValue(peopleTotal.getPoliticalName());

            HSSFCell cell6 = sheet.getRow(2).getCell(6);
            cell6.setCellValue(retireVo.getEducationName());

            Integer departmentId = peopleTotal.getDepartmentId();
            String departmentName = "";
            if (departmentId != null){
                departmentName = dictMapper.findDepartmentNameById(departmentId);
            }
            HSSFCell cell7 = sheet.getRow(3).getCell(2);
            cell7.setCellValue(departmentName);

            HSSFCell cell8 = sheet.getRow(3).getCell(4);
            cell8.setCellValue(retireVo.getRetireJobLevelName());

            HSSFCell cell9 = sheet.getRow(3).getCell(6);
            cell9.setCellValue(peopleTotal.getWorkDate());

            HSSFCell cell10 = sheet.getRow(4).getCell(3);
            cell10.setCellValue(peopleTotal.getJobName());

            HSSFCell cell11 = sheet.getRow(5).getCell(3);
            cell11.setCellValue(peopleTotal.getAddress());

            HSSFCell cell12 = sheet.getRow(6).getCell(2);
            cell12.setCellValue(peopleTotal.getWorkAge()==null?"":peopleTotal.getWorkAge().toString());

            HSSFCell cell12_1 = sheet.getRow(8).getCell(0);
            HSSFCell cell12_2 = sheet.getRow(8).getCell(2);
            Integer jobId = peopleTotal.getJobId();
            String jobLevelName = "";
            String jobSalary = "";
            if (jobId != null){
                PeopleJob peopleJob =  peopleJobMapper.findPeopleJobById(Long.valueOf(jobId));
                if (peopleJob != null){
                    jobLevelName = peopleJob.getJobLevel();
                    jobSalary = peopleJob.getSalary().toString();
                }
            }
            cell12_1.setCellValue(jobLevelName);
            cell12_2.setCellValue(jobSalary);

            HSSFCell cell12_3 = sheet.getRow(8).getCell(3);
            HSSFCell cell12_4 = sheet.getRow(8).getCell(4);
            Integer rankId = peopleTotal.getRankId();
            String rankLevelName = "";
            String rankSalary = "";
            if (rankId != null){
                PeopleRank peopleRank = peopleRankMapper.findPeopleRankById(Long.valueOf(rankId));
                if (peopleRank != null){
                    rankLevelName = peopleRank.getRank_level();
                    rankSalary    = peopleRank.getSalary().toString();
                }
            }
            cell12_3.setCellValue(rankLevelName);
            cell12_4.setCellValue(rankSalary);


            HSSFCell cell13 = sheet.getRow(9).getCell(1);
            cell13.setCellValue(peopleTotal.getBaseSalary()==null?"":peopleTotal.getBaseSalary().toString());

            HSSFCell cell14 = sheet.getRow(10).getCell(3);
            cell14.setCellValue(peopleTotal.getBaseSalary()==null?"":peopleTotal.getBaseSalary().toString());

            HSSFCell cell15 = sheet.getRow(11).getCell(4);
            cell15.setCellValue(peopleTotal.getExtraAllowance()==null?"":peopleTotal.getExtraAllowance().toString());

            HSSFCell cell16 = sheet.getRow(12).getCell(4);
            cell16.setCellValue(peopleTotal.getRentAllowance()==null?"":peopleTotal.getRentAllowance().toString());

            HSSFCell cell17 = sheet.getRow(13).getCell(4);
            cell17.setCellValue(peopleTotal.getFoodAllowance()==null?"":peopleTotal.getFoodAllowance().toString());

            HSSFCell cell18 = sheet.getRow(14).getCell(4);
            cell18.setCellValue(peopleTotal.getHealthAllowance()==null?"":peopleTotal.getHealthAllowance().toString());

            HSSFCell cell19 = sheet.getRow(15).getCell(4);
            cell19.setCellValue(peopleTotal.getRetireAllowance()==null?"":peopleTotal.getRetireAllowance().toString());

            HSSFCell cell20 = sheet.getRow(16).getCell(4);
            cell20.setCellValue(peopleTotal.getPropertyAllowance()==null?"":peopleTotal.getPropertyAllowance().toString());

            HSSFCell cell21 = sheet.getRow(17).getCell(4);
            cell21.setCellValue(peopleTotal.getRetireFeeIncrease()==null?"":peopleTotal.getRetireFeeIncrease().toString());

            HSSFCell cell22 = sheet.getRow(19).getCell(3);
            cell22.setCellValue(DateUtil.GetTodayInWord());



            OutputStream os;
            String newFileName="退休人员审批.xls";
            response.reset();
            os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
            workbook.write(os);
            os.close();

        }catch (Exception exp){

        }


    }

    @Override
	public PeopleRetire findPeopleRetireByCode(String code) {
		return peopleRetireMapper.findPeopleRetireByCode(code);
	}
}



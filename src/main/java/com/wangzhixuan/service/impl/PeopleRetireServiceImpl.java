package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.mapper.PeopleRehireMapper;
import com.wangzhixuan.model.PeopleRehire;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleRetireMapper;
import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleRetireVo;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeopleRetireServiceImpl implements PeopleRetireService{

    @Autowired
    private PeopleRehireMapper peopleRehireMapper;

    @Autowired
    private PeopleRetireMapper peopleRetireMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleRetire findPeopleRetireById(Long id) {
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
                peopleRetireMapper.updatePeopleRetire(peopleRetire);
            }
        }else{
            peopleRetireMapper.updatePeopleRetire(peopleRetire);
        }
    }

    @Override
    public void deletePeopleRetireById(Long id) {
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
            PeopleRetire peopleRetire = peopleRetireMapper.findPeopleRetireById(StringUtilExtra.StringToLong(id));

            if (peopleRetire == null || StringUtils.isBlank(peopleRetire.getCode()) || StringUtils.isBlank(peopleRetire.getName()))
                continue;

            //首先删除原有people rehire表里对应的人员信息
            peopleRehireMapper.deleteByCode(peopleRetire.getCode());

            PeopleRehire peopleRehire = new PeopleRehire();
            peopleRehire.setCode(peopleRetire.getCode());
            peopleRehire.setName(peopleRetire.getName());
            peopleRehire.setSex(peopleRetire.getSex());
            peopleRehire.setNationalId(peopleRetire.getNationalId());
            peopleRehire.setBirthday(peopleRetire.getBirthday());
            peopleRehire.setEducationName(peopleRetire.getEducationName());
            peopleRehire.setPoliticalName(peopleRetire.getPoliticalName());
            peopleRehire.setRetireDate(peopleRetire.getRetireDate());
            peopleRehire.setBeforeJobName(peopleRetire.getRetireJobName());
            peopleRehire.setBeforeJobId(peopleRetire.getRetireJobLevelId());
            peopleRehire.setAddress(peopleRetire.getAddress());
            peopleRehire.setPhoto(peopleRetire.getPhoto());

            peopleRehireMapper.insert(peopleRehire);

            peopleRetire.setStatus(1);
            peopleRetireMapper.updatePeopleRetire(peopleRetire);
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
                //导入的都是退休的
                p.setStatus(ConstUtil.PEOPLE_RETIRE_RETIRE);

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
                            p.setRetireJobLevelId(retireJobLevelId);
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
                    p.setBirthday(birthday);
                }

                //政治面貌
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String politicalName=row.getCell(8).toString().trim();
                    p.setPoliticalName(politicalName);
                }

                //工作日期
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String workDate=row.getCell(9).toString().trim();
                    p.setWorkDate(workDate);
                }

                //退休日期
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String retireDate=row.getCell(10).toString().trim();
                    p.setRetireDate(retireDate);
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
                row.createCell(16).setCellValue("当前状态");row.getCell(16).setCellStyle(setBorder);
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
                    row.createCell(16).setCellValue(p.getStatus()==null?"":(p.getStatus()==0?"退休":"返聘"));row.getCell(16).setCellStyle(setBorder);
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
        PeopleRetireVo p= peopleRetireMapper.findPeopleRetireVoById(Long.valueOf(id));
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
            params.put("${status}",p.getStatus()==0?"退休":"返聘");
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
	public PeopleRetire findPeopleRetireByCode(String code) {
		return peopleRetireMapper.findPeopleRetireByCode(code);
	}
}



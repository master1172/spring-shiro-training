package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleDispatchMapper;
import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.service.PeopleDispatchService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleDispatchVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleDispatchMapper.findPeopleDispatchPageCondition(pageInfo));
        pageInfo.setTotal(peopleDispatchMapper.findPeopleDispatchPageCount(pageInfo));
    }

    @Override
    public void addPeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file) {
        if (peopleDispatch != null){
            if(StringUtils.isEmpty(peopleDispatch.getBirthday())){
                peopleDispatch.setBirthday(null);
            }
            if(StringUtils.isEmpty(peopleDispatch.getSchoolDate())){
                peopleDispatch.setSchoolDate(null);
            }
        }

        if (file!=null){
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleDispatch.setPhoto(uploadPath);
                peopleDispatchMapper.insert(peopleDispatch);
            }
        }else{
            peopleDispatchMapper.insert(peopleDispatch);
        }
    }

    @Override
    public void updatePeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file) {

        if (peopleDispatch != null){
            if(StringUtils.isEmpty(peopleDispatch.getBirthday())){
                peopleDispatch.setBirthday(null);
            }
            if(StringUtils.isEmpty(peopleDispatch.getSchoolDate())){
                peopleDispatch.setSchoolDate(null);
            }
        }

        if(file != null){
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath, file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleDispatch.setPhoto(uploadPath);
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
    public void batchDeletePeopleDispatchByIds(String[] ids) {
        peopleDispatchMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
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

    private List<PeopleDispatch> getPeopleDispatchInfoByExcel(List<PeopleDispatch> list, String path){
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
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet,setBorder,ConstUtil.getPeopleDailyHeaders());

                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleDispatchVo p=(PeopleDispatchVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);                    row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());            row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getDepartmentName());  row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getJobName());         row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女")); row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getNationalName());    row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getProvince());        row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getCity());            row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getBirthday());        row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getEducationName());   row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getPoliticalName());  row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getSchoolDate());     row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getMobile());         row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getComment());        row.getCell(13).setCellStyle(setBorder);

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
    public void exportWord(HttpServletResponse response, String ids) {

        PeopleDispatchVo p= peopleDispatchMapper.findPeopleDispatchVoById(Long.valueOf(ids));

        if(p!=null){

            String filePath=this.getClass().getResource("/template/custInfoDispatch.docx").getPath();
            String newFileName="派遣人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${name}",           p.getName());
            params.put("${departmentName}", p.getDepartmentName());
            params.put("${jobName}",        p.getJobName());
            params.put("${sex}",            p.getSex()==0?"男":"女");
            params.put("${nation}",         p.getNationalName());
            params.put("${province}",       p.getProvince());
            params.put("${city}",           p.getCity());
            params.put("${birthday}",       p.getBirthday());
            params.put("${educationName}",  p.getEducationName());
            params.put("${politicalName}",  p.getPoliticalName());
            params.put("${schoolDate}",     p.getSchoolDate());
            params.put("${mobile}",         p.getMobile());
            params.put("${comment}",        p.getComment());

            //判断是否有头像
            if(StringUtils.isNotBlank(p.getPhoto())){
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
        List<PeopleDispatchVo> peopleList = peopleDispatchMapper.findPeopleDispatchPageCondition(pageInfo);
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

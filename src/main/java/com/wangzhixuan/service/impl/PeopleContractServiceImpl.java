package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleContractMapper;
import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleContractVo;
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
public class PeopleContractServiceImpl implements PeopleContractService{


    @Autowired
    private PeopleContractMapper peopleContractMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleContract findPeopleById(Long id) {
        return peopleContractMapper.findPeopleById(id);
    }

    @Override
    public PeopleContract findPeopleByName(String name) {
        return peopleContractMapper.findPeopleByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleContractMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(peopleContractMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public void addPeople(PeopleContract people,CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (people != null){
            if (StringUtils.isEmpty(people.getBirthday())){
                people.setBirthday(null);
            }
        }

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleContractMapper.insert(people);
            }
        }else{
            peopleContractMapper.insert(people);
        }
    }

    @Override
    public void updatePeople(PeopleContract people, CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (people != null){
            if (StringUtils.isEmpty(people.getBirthday())){
                people.setBirthday(null);
            }
        }


        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleContractMapper.updatePeople(people);
            }
        }else{
            peopleContractMapper.updatePeople(people);
        }
    }

    @Override
    public void deletePeopleById(Long id) {
        peopleContractMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleByIds(String[] ids){
        peopleContractMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleContract> list = new ArrayList<PeopleContract>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleContractMapper.insertByImport(list)>0;
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
    private List<PeopleContract> getPeopleInfoByExcel(List<PeopleContract> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleContract p=new PeopleContract();

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

                //性别
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String sex=row.getCell(2).toString().trim();
                    p.setSex(sex.equals("女")?1:0);
                }

                //生日
                if(row.getCell(3)!=null&&!row.getCell(3).toString().trim().equals("")){
                    String birthday=row.getCell(3).toString().trim();
                    p.setBirthday(birthday);
                }

                //工作
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String job=row.getCell(4).toString().trim();
                    p.setJob(job);
                }

                //薪水
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String salary=row.getCell(5).toString().trim();
                    try {
                        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(salary));
                        p.setSalary(amount);
                    } catch (NumberFormatException e) {
                    }
                }

                //学历
                if(row.getCell(6) != null && !row.getCell(6).toString().trim().equals("")){
                    String degreeName = row.getCell(6).toString().trim();

                    try{
                        Integer degreeId = dictMapper.findDegreeIdByName(degreeName);
                        if (degreeId != null){
                            p.setDegreeId(degreeId);
                        }
                    }catch(Exception exp){

                    }
                }

                //住址
                if(row.getCell(7) != null && !row.getCell(7).toString().trim().equals("")){
                    String address = row.getCell(7).toString().trim();
                    p.setAddress(address);
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
        List list=peopleContractMapper.selectPeopleVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="在编人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("在编人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("姓名");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("性别");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("出生日期");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("工作");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("薪水");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("学历");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("住址");row.getCell(7).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleContractVo p=(PeopleContractVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getBirthday().toString());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getJob());row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getSalary().toString());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getDegreeName());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getAddress());row.getCell(7).setCellStyle(setBorder);
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
        PeopleContractVo p= peopleContractMapper.findPeopleVoById(Long.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfo.docx").getPath();
            String newFileName="在编人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${name}",p.getName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${birthday}",p.getBirthday());
            params.put("${job}",p.getJob());
            params.put("${salary}",p.getSalary()+"");
            params.put("${degree}",p.getDegreeName());
            params.put("${address}",p.getAddress());

            //判断是否有头像
            if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleContractVo> peopleList = peopleContractMapper.findPeoplePageCondition(pageInfo);
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

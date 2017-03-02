package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeoplePartyMapper;
import com.wangzhixuan.model.Dict;
import com.wangzhixuan.model.PeopleParty;
import com.wangzhixuan.service.PeoplePartyService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeoplePartyVo;
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
import java.util.*;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeoplePartyServiceImpl implements PeoplePartyService{


    @Autowired
    private PeoplePartyMapper peoplePartyMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleParty findPeoplePartyById(Long id) {
        return peoplePartyMapper.findPeoplePartyById(id);
    }

    @Override
    public PeopleParty findPeoplePartyByName(String name) {
        return peoplePartyMapper.findPeoplePartyByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peoplePartyMapper.findPeoplePartyPageCondition(pageInfo));
        pageInfo.setTotal(peoplePartyMapper.findPeoplePartyPageCount(pageInfo));
    }

    @Override
    public void addPeopleParty(PeopleParty peopleParty,CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getBirthday())){
                peopleParty.setBirthday(null);
            }
        }

        //当workDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getWorkDate())){
                peopleParty.setWorkDate(null);
            }
        }

        //当partyDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyDate())){
                peopleParty.setPartyDate(null);
            }
        }

        //当jobDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getJobDate())){
                peopleParty.setJobDate(null);
            }
        }

        //当partyInDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyInDate())){
                peopleParty.setPartyInDate(null);
            }
        }

        //当partyOutDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyOutDate())){
                peopleParty.setPartyOutDate(null);
            }
        }

        peopleParty.setPeopleCode(StringUtilExtra.generateUUID());

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peoplePartyMapper.insert(peopleParty);
            }
        }else{
            peoplePartyMapper.insert(peopleParty);
        }
    }

    @Override
    public void updatePeopleParty(PeopleParty peopleParty, CommonsMultipartFile file) {

        //当birthday不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getBirthday())){
                peopleParty.setBirthday(null);
            }
        }

        //当workDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getWorkDate())){
                peopleParty.setWorkDate(null);
            }
        }

        //当partyDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyDate())){
                peopleParty.setPartyDate(null);
            }
        }

        //当jobDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getJobDate())){
                peopleParty.setJobDate(null);
            }
        }

        //当partyInDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyInDate())){
                peopleParty.setPartyInDate(null);
            }
        }

        //当partyOutDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleParty != null){
            if (StringUtils.isEmpty(peopleParty.getPartyOutDate())){
                peopleParty.setPartyOutDate(null);
            }
        }

        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peoplePartyMapper.updatePeopleParty(peopleParty);
            }
        }else{
            peoplePartyMapper.updatePeopleParty(peopleParty);
        }
    }

    @Override
    public void deletePeoplePartyById(Long id) {
        peoplePartyMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeoplePartyByIds(String[] ids){
        peoplePartyMapper.batchDeleteByIds(ids);
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleParty> list = new ArrayList<PeopleParty>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeoplePartyInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peoplePartyMapper.insertByImport(list)>0;
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
    private List<PeopleParty> getPeoplePartyInfoByExcel(List<PeopleParty> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleParty p=new PeopleParty();

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                //人员编码
                p.setPeopleCode(StringUtilExtra.generateUUID());

                //人员姓名
                if(row.getCell(1)!=null&&!row.getCell(1).toString().trim().equals("")){
                    String peopleName=row.getCell(1).toString().trim();
                    p.setPeopleName(peopleName);
                }

                //所在支部
                if(row.getCell(2) != null && !row.getCell(2).toString().trim().equals("")){
                    String branchName = row.getCell(2).toString().trim();

                    try{
                        Integer branchId = dictMapper.findBranchIdByName(branchName);
                        if (branchId != null){
                            p.setBranchId(branchId);
                        }
                    }catch(Exception exp){

                    }
                }

                //部门
                if(row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")){
                    String departmentName = row.getCell(3).toString().trim();

                    try{
                        Integer departmentId = dictMapper.findDepartmentIdByName(departmentName);
                        if (departmentId != null){
                            p.setDepartmentId(departmentId);
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

                //出生日期
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String birthday=row.getCell(6).toString().trim();
                    p.setBirthday(birthday);
                }

                //籍贯
                if(row.getCell(7)!=null&&!row.getCell(7).toString().trim().equals("")){
                    String nativeName=row.getCell(7).toString().trim();
                    p.setNativeName(nativeName);
                }

                //党员状态
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String partyStatusName = row.getCell(8).toString().trim();

                    try{
                        Integer partyStatusId = dictMapper.findPartyStatusIdByName(partyStatusName);
                        if (partyStatusId != null){
                            p.setPartyStatusId(partyStatusId);
                        }
                    }catch(Exception exp){

                    }
                }

                //入党日期
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String partyDate=row.getCell(9).toString().trim();
                    p.setPartyDate(partyDate);
                }

                //学历情况
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String degreeName = row.getCell(10).toString().trim();

                    try{
                        Integer degreeId = dictMapper.findDegreeIdByName(degreeName);
                        if (degreeId != null){
                            p.setDegreeId(degreeId);
                        }
                    }catch(Exception exp){

                    }
                }

                //参加工作日期
                if(row.getCell(11)!=null&&!row.getCell(11).toString().trim().equals("")){
                    String workDate=row.getCell(11).toString().trim();
                    p.setWorkDate(workDate);
                }

                //职务岗位
                if(row.getCell(12)!=null&&!row.getCell(12).toString().trim().equals("")){
                    String jobName=row.getCell(12).toString().trim();
                    p.setJobName(jobName);
                }

                //职级
                if(row.getCell(13)!=null&&!row.getCell(13).toString().trim().equals("")){
                    String jobLevelName = row.getCell(13).toString().trim();

                    try{
                        Integer jobLevelId = dictMapper.findJobLevelIdByName(jobLevelName);
                        if (jobLevelId != null){
                            p.setJobLevelId(jobLevelId);
                        }
                    }catch(Exception exp){

                    }
                }

                //现任职级日期
                if(row.getCell(14)!=null&&!row.getCell(14).toString().trim().equals("")){
                    String jobDate=row.getCell(14).toString().trim();
                    p.setJobDate(jobDate);
                }

                //编制
                if(row.getCell(15)!=null&&!row.getCell(15).toString().trim().equals("")){
                    String formation=row.getCell(15).toString().trim();
                    p.setFormation(formation);
                }

                //党组织关系转入日期
                if(row.getCell(16) != null && !row.getCell(16).toString().trim().equals("")){
                    String partyInDate=row.getCell(16).toString().trim();
                    p.setPartyInDate(partyInDate);
                }

                //党组织关系转出日期
                if(row.getCell(17) != null && !row.getCell(17).toString().trim().equals("")){
                    String partyOutDate=row.getCell(17).toString().trim();
                    p.setPartyOutDate(partyOutDate);
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
        List list=peoplePartyMapper.selectPeoplePartyVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="党员人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("党员人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("人员姓名");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("所在支部");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("部门");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("性别");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("民族");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("出生日期");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("籍贯");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("党员状态");row.getCell(8).setCellStyle(setBorder);
                row.createCell(9).setCellValue("入党日期");row.getCell(9).setCellStyle(setBorder);
                row.createCell(10).setCellValue("学历情况");row.getCell(10).setCellStyle(setBorder);
                row.createCell(11).setCellValue("参加工作日期");row.getCell(11).setCellStyle(setBorder);
                row.createCell(12).setCellValue("职务岗位");row.getCell(12).setCellStyle(setBorder);
                row.createCell(13).setCellValue("职级");row.getCell(13).setCellStyle(setBorder);
                row.createCell(14).setCellValue("现任职级日期");row.getCell(14).setCellStyle(setBorder);
                row.createCell(15).setCellValue("编制");row.getCell(15).setCellStyle(setBorder);
                row.createCell(16).setCellValue("党组织关系转入日期");row.getCell(16).setCellStyle(setBorder);
                row.createCell(17).setCellValue("党组织关系转出日期");row.getCell(17).setCellStyle(setBorder);
                row.createCell(18).setCellValue("备注");row.getCell(18).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeoplePartyVo p=(PeoplePartyVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);                    row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getPeopleName());      row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getBranchName());      row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getDepartmentName());  row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getNationalName());    row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getBirthday());        row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getNativeName());      row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getPartyStatusName()); row.getCell(8).setCellStyle(setBorder);
                    row.createCell(9).setCellValue(p.getPartyDate());       row.getCell(9).setCellStyle(setBorder);
                    row.createCell(10).setCellValue(p.getDegreeName());     row.getCell(10).setCellStyle(setBorder);
                    row.createCell(11).setCellValue(p.getWorkDate());       row.getCell(11).setCellStyle(setBorder);
                    row.createCell(12).setCellValue(p.getJobName());        row.getCell(12).setCellStyle(setBorder);
                    row.createCell(13).setCellValue(p.getJobLevelName());   row.getCell(13).setCellStyle(setBorder);
                    row.createCell(14).setCellValue(p.getJobDate());        row.getCell(14).setCellStyle(setBorder);
                    row.createCell(15).setCellValue(p.getFormation());      row.getCell(15).setCellStyle(setBorder);
                    row.createCell(16).setCellValue(p.getPartyInDate());    row.getCell(16).setCellStyle(setBorder);
                    row.createCell(17).setCellValue(p.getPartyOutDate());   row.getCell(17).setCellStyle(setBorder);
                    row.createCell(18).setCellValue(p.getComment());        row.getCell(18).setCellStyle(setBorder);
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
        PeoplePartyVo p= peoplePartyMapper.findPeoplePartyVoById(Long.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoParty.docx").getPath();
            String newFileName="党员人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${peopleCode}",p.getPeopleCode());
            params.put("${peopleType}",p.getPeopleType());
            params.put("${peopleName}",p.getPeopleName());
            params.put("${branchName}",p.getBranchName()==null?"":p.getBranchName());
            params.put("${departmentName}",p.getDepartmentName()==null?"":p.getDepartmentName());
            params.put("${sex}",p.getSex()==0?"男":"女");
            params.put("${nationalName}",p.getNationalName()==null?"":p.getNationalName());
            params.put("${birthday}",p.getBirthday()==null?"":p.getBirthday());
            params.put("${nativeName}",p.getNativeName());
            params.put("${partyStatusName}",p.getPartyStatusName()==null?"":p.getPartyStatusName());
            params.put("${partyDate}",p.getPartyDate()==null?"":p.getPartyDate());
            params.put("${degreeName}",p.getDegreeName()==null?"":p.getDegreeName());
            params.put("${workDate}",p.getWorkDate()==null?"":p.getWorkDate());
            params.put("${jobName}",p.getJobName());
            params.put("${jobLevelName}",p.getJobLevelName()==null?"":p.getJobLevelName());
            params.put("${jobDate}",p.getJobDate()==null?"":p.getJobDate());
            params.put("${formation}",p.getFormation());
            params.put("${partyInDate}",p.getPartyInDate()==null?"":p.getPartyInDate());
            params.put("${partyOutDate}",p.getPartyOutDate()==null?"":p.getPartyOutDate());
            params.put("${comment}",p.getComment());

            //判断是否有头像
            if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }
            else
                params.put("${photo}","");

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }

    @Override
    public String findPeoplePartyIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeoplePartyVo> peoplePartyList = peoplePartyMapper.findPeoplePartyPageCondition(pageInfo);
        if (peoplePartyList == null || peoplePartyList.size() < 1)
            return ids;


        for(int i=0; i<peoplePartyList.size(); i++){
            ids = ids + peoplePartyList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public void exportPartyByEducation(HttpServletResponse response) {
        List peoplePartyVoList = peoplePartyMapper.selectAllPeoplePartyVo();

        List dictDegreeList = dictMapper.findDegreeDict();

        List dictBranchList = dictMapper.findBranchDict();

        int result[][] = new int[dictDegreeList.size()][dictBranchList.size()];
        int sum[] = new int[dictBranchList.size()];

        for(int i=0; i<dictDegreeList.size(); i++){
            for(int j=0; j<dictBranchList.size(); j++){
                result[i][j] = 0;
            }
        }

        for(int i=0; i<dictDegreeList.size(); i++){

            Dict degreeDict = (Dict) dictDegreeList.get(i);

            if (degreeDict == null)
                continue;

            Integer degreeId = degreeDict.getId();

            for(int j=0; j<dictBranchList.size(); j++){

                Dict branchDict = (Dict) dictBranchList.get(j);

                if (branchDict == null)
                    continue;

                Integer branchId = branchDict.getId();


                for(int k=0; k<peoplePartyVoList.size(); k++){
                    PeoplePartyVo peoplePartyVo = (PeoplePartyVo) peoplePartyVoList.get(k);

                    if (peoplePartyVo == null)
                        continue;

                    if (peoplePartyVo.getDegreeId() == degreeId && peoplePartyVo.getBranchId() == branchId){
                        result[i][j] = result[i][j] +1;
                        sum[j] = sum[j] + 1;
                        break;
                    }
                }
            }
        }


        XSSFWorkbook workBook;
        OutputStream os;
        String newFileName="党员学历情况统计.xlsx";
        try {
            workBook = new XSSFWorkbook();
            XSSFSheet sheet= workBook.createSheet("党员学历情况统计");
            XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);

            //创建表头
            XSSFRow row=sheet.createRow(0);
            row.createCell(0).setCellValue("分类");
            row.getCell(0).setCellStyle(setBorder);

            row.createCell(1).setCellValue("总计");
            row.getCell(1).setCellStyle(setBorder);
            sheet.autoSizeColumn(1);

            for(int i=0; i<dictDegreeList.size();i++){

                Dict degreeDict = (Dict) dictDegreeList.get(i);

                if (degreeDict == null)
                    continue;

                String dictName = degreeDict.getName();
                row.createCell(i+2).setCellValue(dictName);
                row.getCell(i+2).setCellStyle(setBorder);

                sheet.autoSizeColumn(i+2);

            }

            //创建列表内容
            for(int j=0; j<dictBranchList.size(); j++){

                Dict branch = (Dict) dictBranchList.get(j);

                row=sheet.createRow(j+1);
                row.createCell(0).setCellValue(branch.getName());
                row.getCell(0).setCellStyle(setBorder);


                row.createCell(1).setCellValue(sum[j]);
                row.getCell(1).setCellStyle(setBorder);

                for(int i=0; i<dictDegreeList.size(); i++){
                    row.createCell(i+2).setCellValue(result[i][j]);
                    row.getCell(i+2).setCellStyle(setBorder);
                }

                row.setHeight((short) 400);
            }

            sheet.setDefaultRowHeightInPoints(21);
            sheet.autoSizeColumn(0);

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

    @Override
    public void exportPartyByAge(HttpServletResponse response) {

        List peoplePartyVoList = peoplePartyMapper.selectAllPeoplePartyVo();

        List dictBranchList = dictMapper.findBranchDict();

        int sexResult[][] = new int[dictBranchList.size()][2];

        int preparePartyResult[] = new int[dictBranchList.size()];

        int minorNationResult[] = new int[dictBranchList.size()];

        int taiwanNationResult[] = new int[dictBranchList.size()];

        int ageResult[][] = new int[dictBranchList.size()][10];

        for(int i=0; i<dictBranchList.size(); i++){
            Dict branch = (Dict) dictBranchList.get(i);
            if (branch == null)
                continue;

            Integer branchId = branch.getId();

            for(int j=0; j<peoplePartyVoList.size(); j++){
                PeoplePartyVo peoplePartyVo = (PeoplePartyVo) peoplePartyVoList.get(j);
                if (peoplePartyVo == null)
                    continue;

                //性别统计
                if (peoplePartyVo.getBranchId() == branchId){
                    if (peoplePartyVo.getSex() == 0){
                        sexResult[i][0] = sexResult[i][0]+1;
                    }
                    if (peoplePartyVo.getSex() == 1){
                        sexResult[i][1] = sexResult[i][1]+1;
                    }
                }

                //预备党员统计
                if (StringUtils.isNoneBlank(peoplePartyVo.getPartyStatusName())){
                    if (peoplePartyVo.getPartyStatusName().contains("预备")){
                        preparePartyResult[i] = preparePartyResult[i] + 1;
                    }
                }

                //少数民族统计
                if (StringUtils.isNoneBlank(peoplePartyVo.getNationalName())){
                    if (!peoplePartyVo.getNationalName().contains("汉")){
                        minorNationResult[i] = minorNationResult[i] + 1;
                    }
                }

                //台湾统计
                if (StringUtils.isNoneBlank(peoplePartyVo.getNativeName())){
                    if (peoplePartyVo.getNativeName().contains("台湾")){
                        taiwanNationResult[i] = taiwanNationResult[i] + 1;
                    }
                }

                //年龄统计
                if (StringUtils.isNoneBlank(peoplePartyVo.getBirthday())){
                    String birthday = peoplePartyVo.getBirthday();
                    Integer age = DateUtil.GetAgeByBirthday(birthday);


                    if (age <= 30){
                        ageResult[i][0] = ageResult[i][0] + 1;
                    }

                    if (age>30 && age <=35){
                        ageResult[i][1] = ageResult[i][1] + 1;
                    }

                    if (age>35 && age <=40){
                        ageResult[i][2] = ageResult[i][2] + 1;
                    }

                    if (age>40 && age <=45){
                        ageResult[i][3] = ageResult[i][3] + 1;
                    }

                    if (age>45 && age <=50){
                        ageResult[i][4] = ageResult[i][4] + 1;
                    }

                    if (age>50 && age <=55){
                        ageResult[i][5] = ageResult[i][5] + 1;
                    }

                    if (age>55 && age <=60){
                        ageResult[i][6] = ageResult[i][6] + 1;
                    }

                    if (age>60 && age <=65){
                        ageResult[i][7] = ageResult[i][7] + 1;
                    }

                    if (age>65 && age <=70){
                        ageResult[i][8] = ageResult[i][8] + 1;
                    }

                    if (age>70){
                        ageResult[i][9] = ageResult[i][9] + 1;
                    }
                }

            }
        }


        XSSFWorkbook workBook;
        OutputStream os;
        String newFileName="党员基本情况统计.xlsx";
        try {
            workBook = new XSSFWorkbook();
            XSSFSheet sheet= workBook.createSheet("党员基本情况统计");
            XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);

            XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPartyExportHeaders());

            sheet.autoSizeColumn(1);


            //创建列表内容
            for(int i=0; i<dictBranchList.size(); i++){

                Dict branch = (Dict) dictBranchList.get(i);

                row=sheet.createRow(i+1);
                row.createCell(0).setCellValue(branch.getName());
                row.getCell(0).setCellStyle(setBorder);

                row.createCell(1).setCellValue(preparePartyResult[i]);
                row.getCell(1).setCellStyle(setBorder);

                row.createCell(2).setCellValue(sexResult[i][0]);
                row.getCell(2).setCellStyle(setBorder);

                

                row.setHeight((short) 400);
            }

            sheet.setDefaultRowHeightInPoints(21);
            sheet.autoSizeColumn(0);

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




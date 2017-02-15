package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.TrainingMapper;
import com.wangzhixuan.model.Training;
import com.wangzhixuan.service.TrainingService;
import com.wangzhixuan.utils.*;
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
public class TrainingImpl implements TrainingService {

    @Autowired
    private TrainingMapper trainingMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(trainingMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(trainingMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<TrainingVo> trainingList = trainingMapper.findPeoplePageCondition(pageInfo);

        if (trainingList == null || trainingList.size() < 0)
            return ids;

        for(int i=0; i<trainingList.size(); i++){
            ids = ids + trainingList.get(i).getId().toString() + ",";
        }
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public Training findTrainingById(Integer id) {
        return trainingMapper.findTrainingById(id);
    }

    @Override
    public void add(Training training) {
        UpdateDate(training);
        training.setCode(StringUtilExtra.generateUUID());
        trainingMapper.insert(training);
    }



    @Override
    public void update(Training training) {
        UpdateDate(training);
        trainingMapper.update(training);
    }

    @Override
    public void delete(Integer id) {

        trainingMapper.delete(id);
    }

    @Override
    public void batchDel(String[] idList) {
        trainingMapper.batchDeleteByIds(idList);

    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag=false;
        if(files!=null && files.length>0){

            List<Training> list = new ArrayList<Training>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list = getPeopleInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=trainingMapper.insertByImport(list)>0;
            }
        }
        return flag;
    }





    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {
        List list=trainingMapper.selectTrainingVoByIds(idList);
        if(list != null && list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="培训人员信息.xlsx";

            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("培训人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);

                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getTrainingPeopleHeaders());
                setBorder=WordUtil.setCellStyle(workBook,false);

                for(int i=0; i<list.size(); i++){
                    row=sheet.createRow(i+1);
                    TrainingVo p = (TrainingVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);
                    row.createCell(1).setCellValue(p.getName());
                    row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));
                    row.createCell(3).setCellValue(p.getDepartmentName());
                    row.createCell(4).setCellValue(p.getNationalName());
                    row.createCell(5).setCellValue(p.getPoliticalName());
                    row.createCell(6).setCellValue(p.getJobName());
                    row.createCell(7).setCellValue(p.getTrainingClass());
                    row.createCell(8).setCellValue(p.getDescription());
                    row.createCell(9).setCellValue(p.getTrainingCategory());
                    row.createCell(10).setCellValue(p.getStartDate());
                    row.createCell(11).setCellValue(p.getEndDate());
                    row.createCell(12).setCellValue(p.getSumTime());
                    row.createCell(13).setCellValue(p.getOffWork());
                    row.createCell(14).setCellValue(p.getClassHour());
                    row.createCell(15).setCellValue(p.getTrainingPlace());
                    row.createCell(16).setCellValue(p.getTrainingSchool());
                    row.createCell(17).setCellValue(p.getComment());

                    for(int j=0; j<18; j++){
                        row.getCell(j).setCellStyle(setBorder);
                    }
                    row.setHeight((short)400);
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }finally{

            }
        }
    }

    private void UpdateDate(Training training) {
        if (StringUtils.isBlank(training.getStartDate()))
            training.setStartDate(null);

        if (StringUtils.isBlank(training.getEndDate()))
            training.setEndDate(null);
    }

    private List<Training> getPeopleInfoByExcel(List<Training> list, String path) {
        try
        {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                Training p = new Training();

                p.setCode(StringUtilExtra.generateUUID());

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

                //所在部门
                if(row.getCell(3)!=null&&!row.getCell(3).toString().trim().equals("")){
                    String departmentName = row.getCell(3).toString().trim();
                    try{
                        Integer departmentId = dictMapper.findDepartmentIdByName(departmentName);
                        p.setDepartmentId(departmentId);
                    }catch(Exception exp){

                    }
                }

                //民族
                if (row.getCell(4)!=null && !row.getCell(4).toString().trim().equals("")){
                    String nationalName = row.getCell(4).toString().trim();
                    try{
                        Integer nationalId = dictMapper.findNationalIdByName(nationalName);
                        if (nationalId != null){
                            p.setNationalId(nationalId);
                        }
                    }catch(Exception exp){

                    }
                }

                //政治面貌
                if(row.getCell(5)!=null && !row.getCell(5).toString().trim().equals("")){
                    String politicalName = row.getCell(5).toString().trim();
                    p.setPoliticalName(politicalName);
                }

                //职级
                if(row.getCell(6) != null && !row.getCell(6).toString().trim().equals("")){
                    String jobName = row.getCell(6).toString().trim();

                    try{
                        Integer jobId = dictMapper.findJobLevelIdByName(jobName);
                        p.setJobId(jobId);
                    }catch (Exception exp){

                    }
                }

                //培训班名称
                if(row.getCell(7)!=null && !row.getCell(7).toString().trim().equals("")){
                    String trainingClass = row.getCell(7).toString().trim();
                    p.setTrainingClass(trainingClass);
                }

                //学习内容
                if(row.getCell(8) != null && !row.getCell(8).toString().trim().equals("")){
                    String description = row.getCell(8).toString().trim();
                    p.setDescription(description);
                }

                //培训类型
                if(row.getCell(9) != null && !row.getCell(9).toString().trim().equals("")){
                    String trainingCategory = row.getCell(9).toString().trim();
                    p.setTrainingCategory(trainingCategory);
                }

                //起始时间
                if(row.getCell(10) != null && !row.getCell(10).toString().trim().equals("")){
                    String startDate = row.getCell(10).toString().trim();
                    p.setStartDate(startDate);
                }

                //结束时间
                if(row.getCell(11) != null && !row.getCell(11).toString().trim().equals("")){
                    String endDate = row.getCell(11).toString().trim();
                    p.setEndDate(endDate);
                }

                //累计时间
                if(row.getCell(12) != null && !row.getCell(12).toString().trim().equals("")){
                    String sumTime = row.getCell(12).toString().trim();
                    p.setSumTime(sumTime);
                }

                //是否脱产
                if(row.getCell(13) != null && !row.getCell(13).toString().trim().equals("")){
                    String offWork = row.getCell(13).toString().trim();
                    p.setOffWork(offWork);
                }

                //培训学习时间
                if(row.getCell(14) != null && !row.getCell(14).toString().trim().equals("")){
                    String classHour = row.getCell(14).toString().trim();
                    p.setClassHour(classHour);
                }

                //培训地点
                if(row.getCell(15) != null && !row.getCell(15).toString().trim().equals("")){
                    String trainingPlace = row.getCell(15).toString().trim();
                    p.setTrainingPlace(trainingPlace);
                }

                //培训机构
                if(row.getCell(16) != null && !row.getCell(16).toString().trim().equals("")) {
                    String trainingSchool = row.getCell(16).toString().trim();
                    p.setTrainingSchool(trainingSchool);
                }

                //备注
                if(row.getCell(17) != null && !row.getCell(17).toString().trim().equals("")){
                    String comment = row.getCell(17).toString().trim();
                    p.setComment(comment);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}

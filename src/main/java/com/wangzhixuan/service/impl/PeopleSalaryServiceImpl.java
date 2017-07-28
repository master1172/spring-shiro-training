package com.wangzhixuan.service.impl;

import com.google.common.collect.Maps;
import com.wangzhixuan.mapper.*;
import com.wangzhixuan.model.*;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.ExamYearlyVo;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
import com.wangzhixuan.vo.PeopleSalaryVo;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleSalaryServiceImpl implements PeopleSalaryService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private PeopleSalaryMapper peopleSalaryMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private PeopleJobMapper peopleJobMapper;

    @Autowired
    private PeopleRankMapper peopleRankMapper;

    @Autowired
    private ExamYearlyMapper examYearlyMapper;

    @Autowired
    private PeopleTimesheetService peopleTimesheetService;

    @Autowired
    private ExamMonthlyService examMonthlyService;

    @Autowired
    private ExamYearlyService examYearlyService;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(peopleSalaryMapper.findPeopleSalaryBasePageCondition(pageInfo));
        pageInfo.setTotal(peopleSalaryMapper.findPeopleSalaryBasePageCount(pageInfo));
    }

    @Override
    public void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(peopleSalaryMapper.findPeopleSalaryPageCondition(pageInfo));
        pageInfo.setTotal(peopleSalaryMapper.findPeopleSalaryPageCount(pageInfo));
    }


    @Override
    public PeopleSalaryBase findPeopleSalaryBaseById(Integer id) {
        if (id == null)
            return new PeopleSalaryBase();
        return peopleSalaryMapper.selectPeopleSalaryBaseById(id);
    }

    @Override
    public PeopleSalaryBase findPeopleSalaryBaseByCode(String code) {

        if (StringUtils.isBlank(code))
            return new PeopleSalaryBase();

        return peopleSalaryMapper.selectPeopleSalaryBaseByCode(code);
    }

    @Override
    public PeopleSalary findPeopleSalaryById(Integer id) {
        if (id == null)
            return new PeopleSalary();
        return peopleSalaryMapper.findPeopleSalaryById(id);
    }

    @Override
    public BigDecimal CalculateGrossIncome(PeopleSalary peopleSalary) {
        if (SalaryCalculator.PeopleSalaryCalculator(peopleSalary))
            return peopleSalary.getGrossSalary();
        return new BigDecimal(0.00);
    }

    @Override
    public String findPeopleIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);

        List<PeopleSalaryBaseVo> peopleSalaryBaseVoList = peopleSalaryMapper.findPeopleSalaryBasePageCondition(pageInfo);

        if (peopleSalaryBaseVoList == null || peopleSalaryBaseVoList.size() < 1)
            return ids;

        for(int i=0; i<peopleSalaryBaseVoList.size(); i++){
            ids = ids + peopleSalaryBaseVoList.get(i).getId().toString() + ",";
        }

        ids = ids.substring(0,ids.lastIndexOf(','));

        return ids;
    }

    @Override
    public void exportCert(HttpServletResponse response, String ids) {

        PeopleVo peopleVo = peopleMapper.findPeopleVoById(Integer.valueOf(ids));

        if(peopleVo != null){
            PeopleSalary peopleSalary = peopleSalaryMapper.findLatestPeopleSalaryByCode(peopleVo.getCode());
            BigDecimal grossIncome = new BigDecimal(0.00);

            if (peopleSalary != null){
                grossIncome = peopleSalary.getGrossSalary();
            }

            String filePath=this.getClass().getResource("/template/peopleSalaryCert.docx").getPath();
            String newFileName="工资收入证明.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${name}", peopleVo.getName() == null ?"":peopleVo.getName());
            params.put("${department}", "");
            params.put("${jobName}", peopleVo.getJobName() == null?"":peopleVo.getJobName());
            params.put("${jobLevel}",peopleVo.getJobLevelName() == null? "":peopleVo.getJobLevelName());
            params.put("${photoId}", peopleVo.getPhotoId() == null? "":peopleVo.getPhotoId());
            params.put("${jobDate}", peopleVo.getJobLevelDate() == null? "": peopleVo.getJobLevelDate());
            params.put("${grossIncome}",grossIncome == null?"0.00":grossIncome.toString());
            params.put("${d}", DateUtil.GetTodayInWord());

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }

    }



    @Override
    public void addSalary(PeopleSalary peopleSalary){
        UpdatePeopleSalaryDate(peopleSalary);
        peopleSalaryMapper.insert(peopleSalary);
    }

    @Override
    public void updateSalary(PeopleSalary peopleSalary) {
        UpdatePeopleSalaryDate(peopleSalary);
        peopleSalaryMapper.update(peopleSalary);
    }

    @Override
    public void deleteSalaryById(Integer id) {
        peopleSalaryMapper.deleteSalaryById(id);
    }

    @Override
    public void batchDeleteSalaryByIds(String[] ids) {
        peopleSalaryMapper.batchDeleteByIds(ids);
    }

    @Override
    public PeopleSalaryVo findPeopleSalaryVoById(Long id) {
        return peopleSalaryMapper.findPeopleSalaryVoById(id);
    }

    @Override
    public void updateSalaryBase(PeopleSalaryBase peopleSalaryBase){
        if(peopleSalaryBase == null)
            return;

        if (StringUtils.isBlank(peopleSalaryBase.getLastUpdateDate())){
            peopleSalaryBase.setLastUpdateDate(DateUtil.GetToday());
        }

        if(peopleSalaryBase.getAnnuityBase() == null){
            peopleSalaryBase.setAnnuityBase(new BigDecimal(0.00));
        }

        peopleSalaryMapper.updateSalaryBase(peopleSalaryBase);
    }

    @Override
    public void exportExcel2(HttpServletResponse response, String[] idList){
        List list = peopleMapper.selectPeopleVoByIds(idList);
        if (list != null && list.size() > 0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName = "在编人员工资.xlsx";
            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("编制内人员工资表");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleSalaryHeaders2());

                int count = 0;

                setBorder = WordUtil.setCellStyle(workBook, false);
                for(int i=0; i<list.size(); i++){
                    PeopleVo peopleVo = (PeopleVo) list.get(i);
                    if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
                        continue;
                    String peopleCode = peopleVo.getCode();
                    List<PeopleSalaryVo> peopleSalaryVoList = peopleSalaryMapper.findPeopleSalaryVoListByCode(peopleCode);

                    List<ExamYearly> examYearlyList = examYearlyMapper.findExamYearlyVoListByCode(peopleCode);

                    if (peopleSalaryVoList == null || peopleSalaryVoList.size() < 1)
                        continue;

                    String examYearlyResult = "";
                    if (examYearlyList == null || examYearlyList.size() < 1){

                    }else{
                        ExamYearly examYearly = examYearlyList.get(0);
                        if (examYearly != null){
                            examYearlyResult = examYearly.getExamResult();
                        }
                    }

                    for(int j=0; j<peopleSalaryVoList.size(); j++){

                        row = sheet.createRow(count+1);
                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);

                        row.createCell(0).setCellValue(count+1);
                        row.createCell(1).setCellValue(peopleSalaryVo.getPeopleName());
                        row.createCell(2).setCellValue(peopleSalaryVo.getJobSalary()==null?"":peopleSalaryVo.getJobSalary().toString());
                        row.createCell(3).setCellValue(peopleSalaryVo.getRankSalary()==null?"":peopleSalaryVo.getRankSalary().toString());
                        row.createCell(4).setCellValue(peopleSalaryVo.getReserveSalary()==null?"":peopleSalaryVo.getReserveSalary().toString());
                        row.createCell(5).setCellValue(peopleSalaryVo.getExamResult());
                        row.createCell(6).setCellValue(peopleSalaryVo.getJobAllowance()==null?"":peopleSalaryVo.getJobAllowance().toString());
                        row.createCell(7).setCellValue(peopleSalaryVo.getPerformanceAllowance()==null?"":peopleSalaryVo.getPerformanceAllowance().toString());
                        row.createCell(8).setCellValue(peopleSalaryVo.getRentAllowance()==null?"":peopleSalaryVo.getRentAllowance().toString());
                        row.createCell(9).setCellValue(peopleSalaryVo.getHouseAllowance()==null?"":peopleSalaryVo.getHouseAllowance().toString());
                        row.createCell(10).setCellValue(peopleSalaryVo.getDutyAllowance()==null?"":peopleSalaryVo.getDutyAllowance().toString());
                        row.createCell(11).setCellValue(peopleSalaryVo.getExtraAllowance()==null?"":peopleSalaryVo.getExtraAllowance().toString());
                        row.createCell(12).setCellValue(peopleSalaryVo.getTelephoneAllowance()==null?"":peopleSalaryVo.getTelephoneAllowance().toString());
                        row.createCell(13).setCellValue(peopleSalaryVo.getTrafficAllowance()==null?"":peopleSalaryVo.getTrafficAllowance().toString());
                        row.createCell(14).setCellValue(peopleSalaryVo.getOnDutyFeeTotal()==null?"":peopleSalaryVo.getOnDutyFeeTotal().toString());
                        row.createCell(15).setCellValue(peopleSalaryVo.getPropertyAllowance()==null?"":peopleSalaryVo.getPropertyAllowance().toString());
                        row.createCell(16).setCellValue(peopleSalaryVo.getExtraJobAllowance()==null?"":peopleSalaryVo.getExtraJobAllowance().toString());
                        row.createCell(17).setCellValue(peopleSalaryVo.getTemperatureAllowance()==null?"":peopleSalaryVo.getTemperatureAllowance().toString());
                        row.createCell(18).setCellValue(peopleSalaryVo.getReissueFee()==null?"":peopleSalaryVo.getReissueFee().toString());
                        row.createCell(19).setCellValue(peopleSalaryVo.getMedicare()==null?"":peopleSalaryVo.getMedicare().toString());
                        row.createCell(20).setCellValue(peopleSalaryVo.getYearlyBonus()==null?"":peopleSalaryVo.getYearlyBonus().toString());
                        row.createCell(21).setCellValue(examYearlyResult == null?"":examYearlyResult);
                        row.createCell(22).setCellValue(peopleSalaryVo.getGrossSalary()==null?"":peopleSalaryVo.getGrossSalary().toString());

                        count++;

                        for(int k=0; k<23; k++){
                            row.getCell(k).setCellStyle(setBorder);
                        }
                        row.setHeight((short) 400);
                    }
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    @Override
    public void exportExcel(HttpServletResponse response, String[] idList, String payDate) {

        List list = peopleMapper.selectPeopleVoByIds(idList);

        if (list != null && list.size() > 0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName = "在编人员工资.xlsx";
            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("编制内人员工资表");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleSalaryHeaders());

                int count = 0;

                setBorder = WordUtil.setCellStyle(workBook, false);
                for(int i=0; i<list.size(); i++){
                    PeopleVo peopleVo = (PeopleVo) list.get(i);
                    if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
                        continue;
                    String peopleCode = peopleVo.getCode();
                    List<PeopleSalaryVo> peopleSalaryVoList = peopleSalaryMapper.findPeopleSalaryVoListByCode(peopleCode);
                    if (peopleSalaryVoList == null || peopleSalaryVoList.size() < 1)
                        continue;
                    for(int j=0; j<peopleSalaryVoList.size(); j++){

                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);

                        if (peopleSalaryVo != null && StringUtils.isNoneBlank(payDate)){
                            String peoplePayDate = peopleSalaryVo.getPayDate();
                            if (StringUtils.isBlank(peoplePayDate))
                                continue;
                            if (!peoplePayDate.equalsIgnoreCase(payDate))
                                continue;
                        }

                        row = sheet.createRow(count+1);

                        row.createCell(0).setCellValue(count+1);
                        row.createCell(1).setCellValue(peopleSalaryVo.getPeopleName());
                        row.createCell(2).setCellValue(peopleSalaryVo.getJobLevel());
                        row.createCell(3).setCellValue(peopleSalaryVo.getJobSalary()==null?"":peopleSalaryVo.getJobSalary().toString());
                        row.createCell(4).setCellValue(peopleSalaryVo.getRankLevel());
                        row.createCell(5).setCellValue(peopleSalaryVo.getRankSalary()==null?"":peopleSalaryVo.getRankSalary().toString());
                        row.createCell(6).setCellValue(peopleSalaryVo.getReserveSalary()==null?"":peopleSalaryVo.getReserveSalary().toString());
                        row.createCell(7).setCellValue(peopleSalaryVo.getExamResult());
                        row.createCell(8).setCellValue(peopleSalaryVo.getJobAllowance()==null?"":peopleSalaryVo.getJobAllowance().toString());
                        row.createCell(9).setCellValue(peopleSalaryVo.getPerformanceAllowanceTotal()==null?"":peopleSalaryVo.getPerformanceAllowanceTotal().toString());
                        row.createCell(10).setCellValue(peopleSalaryVo.getRentAllowance()==null?"":peopleSalaryVo.getRentAllowance().toString());
                        row.createCell(11).setCellValue(peopleSalaryVo.getHouseAllowance()==null?"":peopleSalaryVo.getHouseAllowance().toString());
                        row.createCell(12).setCellValue(peopleSalaryVo.getWorkDate());
                        row.createCell(13).setCellValue(peopleSalaryVo.getTimesheetStatus()==null?"":peopleSalaryVo.getTimesheetStatus().toString());
                        row.createCell(14).setCellValue(peopleSalaryVo.getDutyAllowance()==null?"":peopleSalaryVo.getDutyAllowance().toString());
                        row.createCell(15).setCellValue(peopleSalaryVo.getExtraAllowance()==null?"":peopleSalaryVo.getExtraAllowance().toString());
                        row.createCell(16).setCellValue(peopleSalaryVo.getTelephoneAllowance()==null?"":peopleSalaryVo.getTelephoneAllowance().toString());
                        row.createCell(17).setCellValue(peopleSalaryVo.getTrafficAllowance()==null?"":peopleSalaryVo.getTrafficAllowance().toString());
                        row.createCell(18).setCellValue(peopleSalaryVo.getOnDutyFee()==null?"":peopleSalaryVo.getOnDutyFee().toString());
                        row.createCell(19).setCellValue(peopleSalaryVo.getOnDutyDate()==null?"":peopleSalaryVo.getOnDutyDate().toString());
                        row.createCell(20).setCellValue(peopleSalaryVo.getOnDutyFeeTotal()==null?"":peopleSalaryVo.getOnDutyFeeTotal().toString());
                        row.createCell(21).setCellValue(peopleSalaryVo.getPropertyAllowance()==null?"":peopleSalaryVo.getPropertyAllowance().toString());
                        row.createCell(22).setCellValue(peopleSalaryVo.getExtraJobAllowance()==null?"":peopleSalaryVo.getExtraJobAllowance().toString());
                        row.createCell(23).setCellValue(peopleSalaryVo.getTemperatureAllowance()==null?"":peopleSalaryVo.getTemperatureAllowance().toString());
                        row.createCell(24).setCellValue(peopleSalaryVo.getReissueFee()==null?"":peopleSalaryVo.getReissueFee().toString());
                        row.createCell(25).setCellValue(peopleSalaryVo.getMedicare()==null?"":peopleSalaryVo.getMedicare().toString());
                        row.createCell(26).setCellValue(peopleSalaryVo.getYearlyBonus()==null?"":peopleSalaryVo.getYearlyBonus().toString());
                        row.createCell(27).setCellValue(peopleSalaryVo.getGrossSalary()==null?"":peopleSalaryVo.getGrossSalary().toString());
                        row.createCell(28).setCellValue(peopleSalaryVo.getLifeInsurance()==null?"":peopleSalaryVo.getLifeInsurance().toString());
                        row.createCell(29).setCellValue(peopleSalaryVo.getJobInsurance()==null?"":peopleSalaryVo.getJobInsurance().toString());
                        row.createCell(30).setCellValue(peopleSalaryVo.getHealthInsurance()==null?"":peopleSalaryVo.getHealthInsurance().toString());
                        row.createCell(31).setCellValue(peopleSalaryVo.getAnnuity()==null?"":peopleSalaryVo.getAnnuity().toString());
                        row.createCell(32).setCellValue(peopleSalaryVo.getHouseFund()==null?"":peopleSalaryVo.getHouseFund().toString());
                        row.createCell(33).setCellValue(peopleSalaryVo.getExpense()==null?"":peopleSalaryVo.getExpense().toString());
                        row.createCell(34).setCellValue(peopleSalaryVo.getTax()==null?"":peopleSalaryVo.getTax().toString());
                        row.createCell(35).setCellValue(peopleSalaryVo.getNetIncome()==null?"":peopleSalaryVo.getNetIncome().toString());
                        row.createCell(36).setCellValue(peopleSalaryVo.getPayDate());

                        count++;

                        for(int k=0; k<37; k++){
                            row.getCell(k).setCellStyle(setBorder);
                        }
                        row.setHeight((short) 400);
                    }
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }


    @Override
    public void exportExcelForMonth(HttpServletResponse response, String payDate) {
        List list = peopleMapper.findAllPeople();

        if (list != null && list.size() > 0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName = "在编人员工资.xlsx";
            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("编制内人员工资表");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleSalaryHeaders());

                int count = 0;

                setBorder = WordUtil.setCellStyle(workBook, false);
                for(int i=0; i<list.size(); i++){
                    People people = (People) list.get(i);
                    if (people == null || StringUtils.isBlank(people.getCode()))
                        continue;
                    String peopleCode = people.getCode();
                    Map<String, Object> condition = Maps.newHashMap();
                    condition.put("code", peopleCode);
                    condition.put("payDate", payDate);
                    List<PeopleSalaryVo> peopleSalaryVoList = peopleSalaryMapper.findPeopleSalaryVoListByCodeAndPayDate(condition);
                    if (peopleSalaryVoList == null || peopleSalaryVoList.size() < 1)
                        continue;
                    for(int j=0; j<peopleSalaryVoList.size(); j++){

                        row = sheet.createRow(count+1);
                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);
                        row.createCell(0).setCellValue(count+1);
                        row.createCell(1).setCellValue(peopleSalaryVo.getPeopleName());
                        row.createCell(2).setCellValue(peopleSalaryVo.getJobLevel());
                        row.createCell(3).setCellValue(peopleSalaryVo.getJobSalary()==null?"":peopleSalaryVo.getJobSalary().toString());
                        row.createCell(4).setCellValue(peopleSalaryVo.getRankLevel());
                        row.createCell(5).setCellValue(peopleSalaryVo.getRankSalary()==null?"":peopleSalaryVo.getRankSalary().toString());
                        row.createCell(6).setCellValue(peopleSalaryVo.getReserveSalary()==null?"":peopleSalaryVo.getReserveSalary().toString());
                        row.createCell(7).setCellValue(peopleSalaryVo.getExamResult());
                        row.createCell(8).setCellValue(peopleSalaryVo.getJobAllowance()==null?"":peopleSalaryVo.getJobAllowance().toString());
                        row.createCell(9).setCellValue(peopleSalaryVo.getPerformanceAllowanceTotal()==null?"":peopleSalaryVo.getPerformanceAllowanceTotal().toString());
                        row.createCell(10).setCellValue(peopleSalaryVo.getRentAllowance()==null?"":peopleSalaryVo.getRentAllowance().toString());
                        row.createCell(11).setCellValue(peopleSalaryVo.getHouseAllowance()==null?"":peopleSalaryVo.getHouseAllowance().toString());
                        row.createCell(12).setCellValue(peopleSalaryVo.getWorkDate());
                        row.createCell(13).setCellValue(peopleSalaryVo.getTimesheetStatus()==null?"":peopleSalaryVo.getTimesheetStatus().toString());
                        row.createCell(14).setCellValue(peopleSalaryVo.getDutyAllowance()==null?"":peopleSalaryVo.getDutyAllowance().toString());
                        row.createCell(15).setCellValue(peopleSalaryVo.getExtraAllowance()==null?"":peopleSalaryVo.getExtraAllowance().toString());
                        row.createCell(16).setCellValue(peopleSalaryVo.getTelephoneAllowance()==null?"":peopleSalaryVo.getTelephoneAllowance().toString());
                        row.createCell(17).setCellValue(peopleSalaryVo.getTrafficAllowance()==null?"":peopleSalaryVo.getTrafficAllowance().toString());
                        row.createCell(18).setCellValue(peopleSalaryVo.getOnDutyFee()==null?"":peopleSalaryVo.getOnDutyFee().toString());
                        row.createCell(19).setCellValue(peopleSalaryVo.getOnDutyDate()==null?"":peopleSalaryVo.getOnDutyDate().toString());
                        row.createCell(20).setCellValue(peopleSalaryVo.getOnDutyFeeTotal()==null?"":peopleSalaryVo.getOnDutyFeeTotal().toString());
                        row.createCell(21).setCellValue(peopleSalaryVo.getPropertyAllowance()==null?"":peopleSalaryVo.getPropertyAllowance().toString());
                        row.createCell(22).setCellValue(peopleSalaryVo.getExtraJobAllowance()==null?"":peopleSalaryVo.getExtraJobAllowance().toString());
                        row.createCell(23).setCellValue(peopleSalaryVo.getTemperatureAllowance()==null?"":peopleSalaryVo.getTemperatureAllowance().toString());
                        row.createCell(24).setCellValue(peopleSalaryVo.getReissueFee()==null?"":peopleSalaryVo.getReissueFee().toString());
                        row.createCell(25).setCellValue(peopleSalaryVo.getMedicare()==null?"":peopleSalaryVo.getMedicare().toString());
                        row.createCell(26).setCellValue(peopleSalaryVo.getYearlyBonus()==null?"":peopleSalaryVo.getYearlyBonus().toString());
                        row.createCell(27).setCellValue(peopleSalaryVo.getGrossSalary()==null?"":peopleSalaryVo.getGrossSalary().toString());
                        row.createCell(28).setCellValue(peopleSalaryVo.getLifeInsurance()==null?"":peopleSalaryVo.getLifeInsurance().toString());
                        row.createCell(29).setCellValue(peopleSalaryVo.getJobInsurance()==null?"":peopleSalaryVo.getJobInsurance().toString());
                        row.createCell(30).setCellValue(peopleSalaryVo.getHealthInsurance()==null?"":peopleSalaryVo.getHealthInsurance().toString());
                        row.createCell(31).setCellValue(peopleSalaryVo.getAnnuity()==null?"":peopleSalaryVo.getAnnuity().toString());
                        row.createCell(32).setCellValue(peopleSalaryVo.getHouseFund()==null?"":peopleSalaryVo.getHouseFund().toString());
                        row.createCell(33).setCellValue(peopleSalaryVo.getExpense()==null?"":peopleSalaryVo.getExpense().toString());
                        row.createCell(34).setCellValue(peopleSalaryVo.getTax()==null?"":peopleSalaryVo.getTax().toString());
                        row.createCell(35).setCellValue(peopleSalaryVo.getNetIncome()==null?"":peopleSalaryVo.getNetIncome().toString());
                        row.createCell(36).setCellValue(peopleSalaryVo.getPayDate());

                        count++;

                        for(int k=0; k<37; k++){
                            row.getCell(k).setCellStyle(setBorder);
                        }
                        row.setHeight((short) 400);
                    }
                }

                sheet.setDefaultRowHeightInPoints(21);
                response.reset();
                os = response.getOutputStream();
                response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                workBook.write(os);
                os.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    @Override
    public boolean autoCalculateSalary(String payDate, StringBuilder processResult) {
        boolean result = false;
        try{
            List<People> peopleList = peopleMapper.findAllPeople();

            if (peopleList == null || peopleList.size() < 1){
                result = true;
                processResult.append("目前无在编人员");
                return result;
            }

            for(int i=0; i<peopleList.size(); i++){
                People people = peopleList.get(i);
                if (people == null || StringUtils.isBlank(people.getCode()))
                    continue;

                String peopleCode = people.getCode();
                Map<String, Object> condition = Maps.newHashMap();
                condition.put("code", peopleCode);
                condition.put("payDate", payDate);
                List<PeopleSalaryVo> peopleSalaryVoList = peopleSalaryMapper.findPeopleSalaryVoListByCodeAndPayDate(condition);

                //每人每个月只能有一条薪水记录，因此要检查并删除掉重复的薪水记录
                if (peopleSalaryVoList != null && peopleSalaryVoList.size() > 0){
                    for(int j=0; j<peopleSalaryVoList.size(); j++){
                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);
                        if (peopleSalaryVo == null || peopleSalaryVo.getId() == null)
                            continue;
                        peopleSalaryMapper.deleteSalaryById(peopleSalaryVo.getId().intValue());
                    }
                }

                PeopleSalary peopleSalary = getPeopleSalary(payDate, people.getCode());

                peopleSalaryMapper.insert(peopleSalary);
            }

            result = true;
            processResult.append("工资自动计算完成");
        }catch (Exception exp){
            result  = false;
            processResult.append(exp.getMessage());
        }

        return result;
    }

    @Override
    public void updateJobSalaryAndRankSalary(PeopleSalaryBase peopleSalaryBase) {
        if (peopleSalaryBase == null)
            return;

        Integer jobId = peopleSalaryBase.getJobId();

        if (jobId != null){
            PeopleJob peopleJob = peopleJobMapper.findPeopleJobById(Long.valueOf(jobId));

            if (peopleJob != null)
                peopleSalaryBase.setJobSalary(peopleJob.getSalary());
        }

        Integer rankId = peopleSalaryBase.getRankId();

        if (rankId != null){
            PeopleRank peopleRank = peopleRankMapper.findPeopleRankById(Long.valueOf(rankId));

            if (peopleRank != null)
                peopleSalaryBase.setRankSalary(peopleRank.getSalary());
        }
    }

    private PeopleSalary getPeopleSalary(String payDate, String peopleCode){
        try{
            PeopleSalaryBase peopleSalaryBase = peopleSalaryMapper.selectPeopleSalaryBaseByCode(peopleCode);

            PeopleSalary peopleSalary = new PeopleSalary();
            BeanUtils.copyProperties(peopleSalary, peopleSalaryBase);

            peopleSalary.setId(null);
            peopleSalary.setPeopleCode(peopleCode);
            peopleSalary.setPayDate(payDate);


            //根据当月考勤情况计算交通补贴和降温补贴
            String firstDayOfSelectMonth = DateUtil.GetFirstDayOfSelectMonth(payDate);
            String lastDayOfSelectMonth  = DateUtil.GetLastDayOfSelectMonth(payDate);

            BigDecimal sumVacationPeriod = peopleTimesheetService.findVacationSumByCodeAndDate(
                    peopleCode,
                    firstDayOfSelectMonth,
                    lastDayOfSelectMonth);

            peopleSalary.setTimesheetStatus(sumVacationPeriod);

            SalaryCalculator.PeopleSalaryCalculateTemperatureAllowance(peopleSalary);
            SalaryCalculator.PeopleSalaryCalculateTrafficAllowance(peopleSalary);

            //根据月度考评计算绩效工资
            String examResult = examMonthlyService.findPeopleExamMonthlyResultByCodeAndDate(
                    peopleSalaryBase.getPeopleCode(),
                    payDate
            );

            peopleSalary.setExamResult(examResult);

            SalaryCalculator.GetPerformanceTotalByMonthlyExamResult(peopleSalary);

            //根据年度考评计算奖金
            String examYearlyResult = examYearlyService.findPeopleExamYearlyResultByCodeAndYear(
                    peopleSalary.getPeopleCode(),
                    DateUtil.GetCurrentYear()
            );

            BigDecimal yearlyBonus = SalaryCalculator.GetBonusByYearlyExamResult(examYearlyResult, peopleSalaryBase);
            peopleSalary.setYearlyBonus(yearlyBonus);

            SalaryCalculator.PeopleSalaryCalculator(peopleSalary);
            return peopleSalary;
        }catch (Exception exp){
            PeopleSalary peopleSalary = new PeopleSalary();
            peopleSalary.setPayDate(payDate);
            peopleSalary.setPeopleCode(peopleCode);

            return peopleSalary;
        }
    }


    @Override
    public boolean insertByImport(CommonsMultipartFile[] files) {
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleSalary> list = new ArrayList<PeopleSalary>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleSalaryInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleSalaryMapper.insertByImport(list)>0;
            }
        }
        return flag;
    }

    @Override
    public void updateSalaryJobLevel(PeopleVo peopleVo) {
        if (peopleVo == null || peopleVo.getJobId() == null)
            return;

        PeopleJob peopleJob = peopleJobMapper.findPeopleJobById((long) peopleVo.getJobId());

        if (peopleJob != null){
            Long jobId = peopleJob.getId();
            BigDecimal jobSalary = peopleJob.getSalary();

            Map<String,Object> condition = Maps.newHashMap();
            condition.put("peopleCode", peopleVo.getCode());
            condition.put("jobId", jobId);
            condition.put("jobSalary", jobSalary);
            peopleSalaryMapper.updateSalaryBaseJobLevel(condition);
        }
    }



    private void UpdatePeopleSalaryDate(PeopleSalary peopleSalary){
        if (peopleSalary == null)
            return;
        if (StringUtils.isBlank(peopleSalary.getWorkDate())){
            peopleSalary.setWorkDate(null);
        }
        if(StringUtils.isBlank(peopleSalary.getPayDate())){
            peopleSalary.setPayDate(DateUtil.GetCurrentYearAndMonth());
        }
    }

    private List<PeopleSalary> getPeopleSalaryInfoByExcel(List<PeopleSalary> list,String path){
        try
        {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleSalary p = new PeopleSalary();


                //姓名
                if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
                    continue;
                }

                String name=row.getCell(1).toString().trim();

                People people = peopleMapper.findFirstPeopleByName(name);

                if (people == null || StringUtils.isBlank(people.getCode()))
                    continue;
                p.setPeopleCode(people.getCode());

                //岗位名称
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String jobLevel = row.getCell(2).toString().trim();
                    p.setJobId(dictMapper.findJobLevelIdByName(jobLevel));
                }

                //岗位工资
                if (row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
                    String jobSalary = row.getCell(3).toString().trim();
                    p.setJobSalary(StringUtilExtra.StringToDecimal(jobSalary));
                }

                //薪级
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String rankLevel = row.getCell(4).toString().trim();
                    Integer rankId = dictMapper.findRankLevelIdByName(rankLevel);
                    p.setRankId(rankId);
                }

                //薪级工资
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String rankSalary = row.getCell(5).toString().trim();
                    p.setRankSalary(StringUtilExtra.StringToDecimal(rankSalary));
                }

                //工改保留工资
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String reserverSalary = row.getCell(6).toString().trim();
                    p.setReserveSalary(StringUtilExtra.StringToDecimal(reserverSalary));
                }

                //考核结果
                if(row.getCell(7)!=null&&!row.getCell(7).toString().trim().equals("")){
                    String examResult = row.getCell(7).toString().trim();
                    p.setExamResult(examResult);
                }

                //
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String jobAllowance = row.getCell(8).toString().trim();
                    p.setJobAllowance(StringUtilExtra.StringToDecimal(jobAllowance));
                }

                //
                if(row.getCell(9)!=null&&!row.getCell(9).toString().trim().equals("")){
                    String perfrmanceAllowance = row.getCell(9).toString().trim();
                    p.setPerformanceAllowance(StringUtilExtra.StringToDecimal(perfrmanceAllowance));
                }

                //
                if(row.getCell(10)!=null&&!row.getCell(10).toString().trim().equals("")){
                    String rentAllowance = row.getCell(10).toString().trim();
                    p.setRentAllowance(StringUtilExtra.StringToDecimal(rentAllowance));
                }

                //
                if(row.getCell(11)!=null&&!row.getCell(11).toString().trim().equals("")){
                    String houseAllowance = row.getCell(11).toString().trim();
                    p.setHouseAllowance(StringUtilExtra.StringToDecimal(houseAllowance));
                }

                //
                if(row.getCell(12)!=null&&!row.getCell(12).toString().trim().equals("")) {
                    String workDate = row.getCell(12).toString().trim();
                    p.setWorkDate(workDate);
                }

                //
                if(row.getCell(13)!=null&&!row.getCell(13).toString().trim().equals("")) {
                    String timeSheeStatus = row.getCell(13).toString().trim();
                    p.setTimesheetStatus(StringUtilExtra.StringToDecimal(timeSheeStatus));
                }

                //
                if(row.getCell(14)!=null&&!row.getCell(14).toString().trim().equals("")) {
                    String dutyAllowance = row.getCell(14).toString().trim();
                    p.setDutyAllowance(StringUtilExtra.StringToDecimal(dutyAllowance));
                }

                //
                if(row.getCell(15)!=null&&!row.getCell(15).toString().trim().equals("")) {
                    String extraAllowance = row.getCell(15).toString().trim();
                    p.setExtraAllowance(StringUtilExtra.StringToDecimal(extraAllowance));
                }

                //
                if(row.getCell(16)!=null&&!row.getCell(16).toString().trim().equals("")) {
                    String telephoneAllowance = row.getCell(16).toString().trim();
                    p.setTelephoneAllowance(StringUtilExtra.StringToDecimal(telephoneAllowance));
                }

                //
                if(row.getCell(17)!=null&&!row.getCell(17).toString().trim().equals("")) {
                    String trafficAllowance = row.getCell(17).toString().trim();
                    p.setTrafficAllowance(StringUtilExtra.StringToDecimal(trafficAllowance));
                }

                //
                if(row.getCell(18)!=null&&!row.getCell(18).toString().trim().equals("")) {
                    String onDutyFee = row.getCell(18).toString().trim();
                    p.setOnDutyDate(StringUtilExtra.StringToDecimal(onDutyFee));
                }

                //
                if(row.getCell(19)!=null&&!row.getCell(19).toString().trim().equals("")) {
                    String onDutyDate = row.getCell(19).toString().trim();
                    p.setOnDutyDate(StringUtilExtra.StringToDecimal(onDutyDate));
                }

                if(row.getCell(20)!=null&&!row.getCell(20).toString().trim().equals("")) {
                    String onDutyFeeTotal = row.getCell(20).toString().trim();
                    p.setOnDutyFeeTotal(StringUtilExtra.StringToDecimal(onDutyFeeTotal));
                }

                //
                if(row.getCell(21)!=null&&!row.getCell(21).toString().trim().equals("")) {
                    String propertyAllowance = row.getCell(21).toString().trim();
                    p.setPropertyAllowance(StringUtilExtra.StringToDecimal(propertyAllowance));
                }

                //
                if(row.getCell(22)!=null&&!row.getCell(22).toString().trim().equals("")) {
                    String extraJobAllowance = row.getCell(22).toString().trim();
                    p.setExtraJobAllowance(StringUtilExtra.StringToDecimal(extraJobAllowance));
                }

                if(row.getCell(23)!=null&&!row.getCell(23).toString().trim().equals("")) {
                    String temperatureAllowance = row.getCell(23).toString().trim();
                    p.setTemperatureAllowance(StringUtilExtra.StringToDecimal(temperatureAllowance));
                }

                if(row.getCell(24)!=null&&!row.getCell(24).toString().trim().equals("")) {
                    String reissueFee = row.getCell(24).toString().trim();
                    p.setReissueFee(StringUtilExtra.StringToDecimal(reissueFee));
                }

                if(row.getCell(25)!=null&&!row.getCell(25).toString().trim().equals("")) {
                    String medicare = row.getCell(25).toString().trim();
                    p.setMedicare(StringUtilExtra.StringToDecimal(medicare));
                }

                if(row.getCell(26)!=null&&!row.getCell(26).toString().trim().equals("")) {
                    String yearlyBonus = row.getCell(26).toString().trim();
                    p.setYearlyBonus(StringUtilExtra.StringToDecimal(yearlyBonus));
                }

                if(row.getCell(27)!=null&&!row.getCell(27).toString().trim().equals("")) {
                    String grossSalary = row.getCell(27).toString().trim();
                    p.setGrossSalary(StringUtilExtra.StringToDecimal(grossSalary));
                }

                if(row.getCell(28)!=null&&!row.getCell(28).toString().trim().equals("")) {
                    String lifeInsurance = row.getCell(28).toString().trim();
                    p.setLifeInsurance(StringUtilExtra.StringToDecimal(lifeInsurance));
                }

                if(row.getCell(29)!=null&&!row.getCell(29).toString().trim().equals("")) {
                    String jobInsurance = row.getCell(29).toString().trim();
                    p.setJobInsurance(StringUtilExtra.StringToDecimal(jobInsurance));
                }

                if(row.getCell(30)!=null&&!row.getCell(30).toString().trim().equals("")) {
                    String healthInsurance = row.getCell(30).toString().trim();
                    p.setHealthInsurance(StringUtilExtra.StringToDecimal(healthInsurance));
                }

                if(row.getCell(31)!=null&&!row.getCell(31).toString().trim().equals("")) {
                    String annuity = row.getCell(31).toString().trim();
                    p.setAnnuity(StringUtilExtra.StringToDecimal(annuity));
                }

                if(row.getCell(32)!=null&&!row.getCell(32).toString().trim().equals("")) {
                    String houseFund = row.getCell(32).toString().trim();
                    p.setHouseFund(StringUtilExtra.StringToDecimal(houseFund));
                }

                if(row.getCell(33)!=null&&!row.getCell(33).toString().trim().equals("")) {
                    String expense = row.getCell(33).toString().trim();
                    p.setExpense(StringUtilExtra.StringToDecimal(expense));
                }

                if(row.getCell(34)!=null&&!row.getCell(34).toString().trim().equals("")) {
                    String tax = row.getCell(34).toString().trim();
                    p.setTax(StringUtilExtra.StringToDecimal(tax));
                }

                if(row.getCell(35)!=null&&!row.getCell(35).toString().trim().equals("")) {
                    String netIncome = row.getCell(35).toString().trim();
                    p.setNetIncome(StringUtilExtra.StringToDecimal(netIncome));
                }

                if(row.getCell(36)!=null&&!row.getCell(36).toString().trim().equals("")) {
                    String payDate = row.getCell(36).toString().trim();
                    p.setPayDate(payDate);
                }

                list.add(p);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}

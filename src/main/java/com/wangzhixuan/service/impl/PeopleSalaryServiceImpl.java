package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.mapper.PeopleSalaryMapper;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.ExcelUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
import com.wangzhixuan.vo.PeopleSalaryVo;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleSalaryServiceImpl implements PeopleSalaryService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private PeopleSalaryMapper peopleSalaryMapper;


    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(peopleSalaryMapper.findPeopleSalaryPageCondition(pageInfo));
        pageInfo.setTotal(peopleSalaryMapper.findPeopleSalaryPageCount(pageInfo));
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
    public void deleteSalaryById(Long id) {
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
    public PeopleSalaryBaseVo findPeopleSalaryBaseByCode(String code) {
        return peopleSalaryMapper.findPeopleSalaryBaseVoByCode(code);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String[] idList) {
        List list = peopleMapper.selectPeopleVoByIds(idList);

        if (list != null && list.size() > 0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName = "在编人员工资.xlsx";
            try{
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("在编人员工资信息");
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

                        row = sheet.createRow(count+1);
                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);
                        row.createCell(0).setCellValue(count+1);
                        row.createCell(1).setCellValue(peopleSalaryVo.getPeopleName());
                        row.createCell(2).setCellValue(peopleSalaryVo.getJobLevel());
                        row.createCell(3).setCellValue(peopleSalaryVo.getJobSalary()==null?"":peopleSalaryVo.getJobSalary().toString());
                        row.createCell(4).setCellValue(peopleSalaryVo.getRankLevel());
                        row.createCell(5).setCellValue(peopleSalaryVo.getRankSalary()==null?"":peopleSalaryVo.getRankLevel().toString());
                        row.createCell(6).setCellValue(peopleSalaryVo.getReserveSalary()==null?"":peopleSalaryVo.getReserveSalary().toString());
                        row.createCell(7).setCellValue(peopleSalaryVo.getExamResult());
                        row.createCell(8).setCellValue(peopleSalaryVo.getJobAllowance()==null?"":peopleSalaryVo.getJobAllowance().toString());
                        row.createCell(9).setCellValue(peopleSalaryVo.getPerformanceAllowance()==null?"":peopleSalaryVo.getPerformanceAllowance().toString());
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
                    sheet.setDefaultRowHeightInPoints(21);
                    response.reset();
                    os = response.getOutputStream();
                    response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
                    workBook.write(os);
                    os.close();
                }
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    private void UpdatePeopleSalaryDate(PeopleSalary peopleSalary){
        if (peopleSalary == null)
            return;
        if (StringUtils.isBlank(peopleSalary.getWorkDate())){
            peopleSalary.setWorkDate(null);
        }
    }
}

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
                XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleHeaders());

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
                        row = sheet.createRow(j+1);
                        PeopleSalaryVo peopleSalaryVo = peopleSalaryVoList.get(j);
                        row.createCell(0).setCellValue(j+1);
                        row.createCell(1).setCellValue(peopleSalaryVo.getPeopleName());
                        row.createCell(2).setCellValue(peopleSalaryVo.getJobLevel());
                        row.createCell(3).setCellValue(peopleSalaryVo.getJobSalary()==null?"":peopleSalaryVo.getJobSalary().toString());
                        row.createCell(4).setCellValue(peopleSalaryVo.getRankLevel());
                        row.createCell(5).setCellValue(peopleSalaryVo.getRankSalary()==null?"":peopleSalaryVo.getRankLevel().toString());

                        for(int k=0; k<5; k++){
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

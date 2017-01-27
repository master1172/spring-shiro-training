package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.mapper.PeopleContractSalaryMapper;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.DateUtil;
import com.wangzhixuan.utils.ExcelUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
@Service
public class PeopleContractSalaryServiceImpl implements PeopleContractSalaryService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContractSalaryMapper peopleContractSalaryMapper;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleContractSalaryMapper.findPeopleContractSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleContractSalaryMapper.findPeopleContractSalaryPageCount(pageInfo));
	}

	@Override
	public void addSalary(PeopleContractSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetDate(new Date()));
		}

		peopleContractSalaryMapper.insert(peopleSalary);
	}

	@Override
	public void updateSalary(PeopleContractSalary peopleSalary) {
		peopleContractSalaryMapper.updateByPrimaryKeySelective(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Long id) {
		int count = peopleContractSalaryMapper.deleteByPrimaryKey(id);
		logger.info("delete:" + count);
	}

	@Override
	public PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id) {
		return peopleContractSalaryMapper.findPeopleContractSalaryVoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {
		// TODO Auto-generated method stub
		List list = peopleContractSalaryMapper.selectPeopleContractSalaryVoByIds(idList);
		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "合同人员工资.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("合同人员工资");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleContractSalaryHeaders());

				setBorder = WordUtil.setCellStyle(workBook, false);
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					PeopleContractSalaryVo peopleContractSalaryVo = (PeopleContractSalaryVo) list.get(i);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(peopleContractSalaryVo.getPeopleCode());
					row.createCell(2).setCellValue(peopleContractSalaryVo.getPeopleName());
					row.createCell(3).setCellValue(peopleContractSalaryVo.getJobId());
					row.createCell(4).setCellValue(peopleContractSalaryVo.getJobSalary()==null?"":peopleContractSalaryVo.getJobSalary().toString());
					row.createCell(5).setCellValue(peopleContractSalaryVo.getSchoolSalary()==null?"":peopleContractSalaryVo.getSchoolSalary().toString());
					row.createCell(6).setCellValue(peopleContractSalaryVo.getExamResult()==null?"":peopleContractSalaryVo.getExamResult().toString());
					row.createCell(7).setCellValue(peopleContractSalaryVo.getJobExamSalary()==null?"":peopleContractSalaryVo.getJobExamSalary().toString());
					row.createCell(8).setCellValue(peopleContractSalaryVo.getTelephoneAllowance()==null?"":peopleContractSalaryVo.getTelephoneAllowance().toString());
					row.createCell(9).setCellValue(peopleContractSalaryVo.getTrafficAllowance()==null?"":peopleContractSalaryVo.getTrafficAllowance().toString());
					row.createCell(10).setCellValue(peopleContractSalaryVo.getSpecialAllowance()==null?"":peopleContractSalaryVo.getSpecialAllowance().toString());
					row.createCell(11).setCellValue(peopleContractSalaryVo.getHeadAllowance()==null?"":peopleContractSalaryVo.getHeadAllowance().toString());
					row.createCell(12).setCellValue(peopleContractSalaryVo.getTemperatureAllowance()==null?"":peopleContractSalaryVo.getTemperatureAllowance().toString());
					row.createCell(13).setCellValue(peopleContractSalaryVo.getExtraWorkDate()==null?"":peopleContractSalaryVo.getExtraWorkDate().toString());
					row.createCell(14).setCellValue(peopleContractSalaryVo.getExtraWorkAllowance()==null?"":peopleContractSalaryVo.getExtraWorkAllowance().toString());
					row.createCell(15).setCellValue(peopleContractSalaryVo.getBonus()==null?"":peopleContractSalaryVo.getBonus().toString());
					row.createCell(16).setCellValue(peopleContractSalaryVo.getReissueFee()==null?"":peopleContractSalaryVo.getReissueFee().toString());
					row.createCell(17).setCellValue(peopleContractSalaryVo.getGrossIncome()==null?"":peopleContractSalaryVo.getGrossIncome().toString());
					row.createCell(18).setCellValue(peopleContractSalaryVo.getLifeInsurance()==null?"":peopleContractSalaryVo.getLifeInsurance().toString());
					row.createCell(19).setCellValue(peopleContractSalaryVo.getJobInsurance()==null?"":peopleContractSalaryVo.getJobInsurance().toString());
					row.createCell(20).setCellValue(peopleContractSalaryVo.getHealthInsurance()==null?"":peopleContractSalaryVo.getHealthInsurance().toString());
					row.createCell(21).setCellValue(peopleContractSalaryVo.getHouseFund()==null?"":peopleContractSalaryVo.getHouseFund().toString());
					row.createCell(22).setCellValue(peopleContractSalaryVo.getExpense()==null?"":peopleContractSalaryVo.getExpense().toString());
					row.createCell(23).setCellValue(peopleContractSalaryVo.getNetIncome()==null?"":peopleContractSalaryVo.getNetIncome().toString());
					row.createCell(24).setCellValue(peopleContractSalaryVo.getPayDate()==null?"":peopleContractSalaryVo.getPayDate().toString());
					
					for (int j = 0; j < 25; j++) {
						row.getCell(j).setCellStyle(setBorder);
					}
					row.setHeight((short) 400);
				}
				sheet.setDefaultRowHeightInPoints(21);
				response.reset();
				os = response.getOutputStream();
				response.setHeader("Content-disposition",
						"attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
				workBook.write(os);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("", e);
			} finally {

			}
		}
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (files != null && files.length > 0) {

			List<PeopleContractSalary> list = new ArrayList<PeopleContractSalary>();

			String filePath = this.getClass().getResource("/").getPath();// 文件临时路径

			for (int i = 0; i < files.length; i++) {

				String path = UploadUtil.fileUpload(filePath, files[i]);

				if (StringUtils.isNotBlank(path)) {
					list = this.getPeopleInfoByExcel(list, path);
				}
			}
			if (list.size() > 0) {
				flag = peopleContractSalaryMapper.insertByImport(list) > 0;
			}
		}
		return flag;
	}
	
	/**
	 * 文件读取
	 * 
	 * @param list
	 * @param path
	 * @return
	 */
	public List<PeopleContractSalary> getPeopleInfoByExcel(List<PeopleContractSalary> list, String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				PeopleContractSalary peopleContractSalary = new PeopleContractSalary();
				peopleContractSalary.setPeopleCode(getCellString(row.getCell(1)));
				peopleContractSalary.setJobId(toint(getCellString(row.getCell(3))));
				peopleContractSalary.setJobSalary(toDecimal(getCellString(row.getCell(4))));
				peopleContractSalary.setSchoolSalary(toDecimal(getCellString(row.getCell(5))));
				peopleContractSalary.setExamResult(getCellString(row.getCell(6)));
				peopleContractSalary.setJobExamSalary(toDecimal(getCellString(row.getCell(7))));
				peopleContractSalary.setTelephoneAllowance(toDecimal(getCellString(row.getCell(8))));
				peopleContractSalary.setTrafficAllowance(toDecimal(getCellString(row.getCell(9))));
				peopleContractSalary.setSpecialAllowance(toDecimal(getCellString(row.getCell(10))));
				peopleContractSalary.setHeadAllowance(toDecimal(getCellString(row.getCell(11))));
				peopleContractSalary.setExtraWorkFee(toDecimal("0"));
				peopleContractSalary.setTemperatureAllowance(toDecimal(getCellString(row.getCell(12))));
				peopleContractSalary.setExtraWorkDate(toDecimal(getCellString(row.getCell(13))));
				peopleContractSalary.setExtraWorkAllowance(toDecimal(getCellString(row.getCell(14))));
				peopleContractSalary.setBonus(toDecimal(getCellString(row.getCell(15))));
				peopleContractSalary.setReissueFee(toDecimal(getCellString(row.getCell(16))));
				peopleContractSalary.setGrossIncome(toDecimal(getCellString(row.getCell(17))));
				peopleContractSalary.setLifeInsurance(toDecimal(getCellString(row.getCell(18))));
				peopleContractSalary.setJobInsurance(toDecimal(getCellString(row.getCell(19))));
				peopleContractSalary.setHealthInsurance(toDecimal(getCellString(row.getCell(20))));
				peopleContractSalary.setHouseFund(toDecimal(getCellString(row.getCell(21))));
				peopleContractSalary.setExpense(toDecimal(getCellString(row.getCell(22))));
				peopleContractSalary.setNetIncome(toDecimal(getCellString(row.getCell(23))));
				peopleContractSalary.setPayDate(getCellString(row.getCell(24)));

				list.add(peopleContractSalary);
			}
		} catch (IOException e1) {
			logger.info("", e1);
			e1.printStackTrace();
		}
		return list;
	}
	
	private String getCellString(XSSFCell xs) {
		String cell = "";
		if (xs == null) {
			cell = "";
		} else {
			cell = xs.toString();
		}
		return cell;
	}
	
	private BigDecimal toDecimal(String str){
		if(StringUtils.isNotBlank(str)){
			return new BigDecimal(str);
		}else{
			return new BigDecimal("0.00");
		}
	}
	
	private int toint(String str){
		try{
			return Integer.valueOf(str);
		}catch(Exception ex){
			return 0;
		}
	}
}

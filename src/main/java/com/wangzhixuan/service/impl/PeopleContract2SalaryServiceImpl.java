package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.*;
import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.service.PeopleContract2SalaryService;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import com.wangzhixuan.vo.PeopleContractVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wangzhixuan.utils.WordUtil.getCellString;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
@Service
public class PeopleContract2SalaryServiceImpl implements PeopleContract2SalaryService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PeopleMapper peopleMapper;
	@Autowired
	private PeopleContract2SalaryMapper peopleContract2SalaryMapper;
	@Autowired
	private PeopleContract2Mapper peopleContract2Mapper;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleContract2SalaryMapper.findPeopleContractSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleContract2SalaryMapper.findPeopleContractSalaryPageCount(pageInfo));
	}

	@Override
	public void addSalary(PeopleContractSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetDate(new Date()));
		}

		peopleContract2SalaryMapper.insert(peopleSalary);
	}

	@Override
	public void updateSalary(PeopleContractSalary peopleSalary) {
		peopleContract2SalaryMapper.updateByPrimaryKeySelective(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Long id) {
		int count = peopleContract2SalaryMapper.deleteByPrimaryKey(id);
		logger.info("delete:" + count);
	}

	@Override
	public PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id) {
		return peopleContract2SalaryMapper.findPeopleContractSalaryVoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {
		// TODO Auto-generated method stub
		List list = peopleContract2Mapper.selectPeopleContractVoByIds(idList);
		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "无固定期合同制人员工资.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("无固定期合同制人员工资");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleContractSalaryHeaders());
				int count = 0;
				setBorder = WordUtil.setCellStyle(workBook, false);
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					PeopleContractVo peopleContractVo = (PeopleContractVo) list.get(i);
					if(peopleContractVo ==null || StringUtils.isBlank(peopleContractVo.getCode()))
						continue;
					String peopleCode = peopleContractVo.getCode();
					List<PeopleContractSalaryVo> peopleContractSalaryVoList = peopleContract2SalaryMapper.findPeopleContractSalaryVoListByCode(peopleCode);
					if(peopleContractSalaryVoList == null || peopleContractSalaryVoList.size()<1)
						continue;
					for(int j=0; j<peopleContractSalaryVoList.size();j++) {
						row = sheet.createRow(count+1);
						PeopleContractSalaryVo peopleContractSalaryVo = peopleContractSalaryVoList.get(j);
						row.createCell(0).setCellValue(count + 1);
						row.createCell(1).setCellValue(peopleContractSalaryVo.getPeopleName());
						row.createCell(2).setCellValue(peopleContractSalaryVo.getJobId());
						row.createCell(3).setCellValue(peopleContractSalaryVo.getJobSalary() == null ? "" : peopleContractSalaryVo.getJobSalary().toString());
						row.createCell(4).setCellValue(peopleContractSalaryVo.getSchoolSalary() == null ? "" : peopleContractSalaryVo.getSchoolSalary().toString());
						row.createCell(5).setCellValue(peopleContractSalaryVo.getExamResult() == null ? "" : peopleContractSalaryVo.getExamResult().toString());
						row.createCell(6).setCellValue(peopleContractSalaryVo.getJobExamSalary() == null ? "" : peopleContractSalaryVo.getJobExamSalary().toString());
						row.createCell(7).setCellValue(peopleContractSalaryVo.getTelephoneAllowance() == null ? "" : peopleContractSalaryVo.getTelephoneAllowance().toString());
						row.createCell(8).setCellValue(peopleContractSalaryVo.getTrafficAllowance() == null ? "" : peopleContractSalaryVo.getTrafficAllowance().toString());
						row.createCell(9).setCellValue(peopleContractSalaryVo.getSpecialAllowance() == null ? "" : peopleContractSalaryVo.getSpecialAllowance().toString());
						row.createCell(10).setCellValue(peopleContractSalaryVo.getHeadAllowance() == null ? "" : peopleContractSalaryVo.getHeadAllowance().toString());
						row.createCell(11).setCellValue(peopleContractSalaryVo.getTemperatureAllowance() == null ? "" : peopleContractSalaryVo.getTemperatureAllowance().toString());
						row.createCell(12).setCellValue(peopleContractSalaryVo.getExtraWorkFee() == null? "" : peopleContractSalaryVo.getExtraWorkFee().toString());
						row.createCell(13).setCellValue(peopleContractSalaryVo.getExtraWorkDate() == null ? "" : peopleContractSalaryVo.getExtraWorkDate().toString());
						row.createCell(14).setCellValue(peopleContractSalaryVo.getExtraWorkAllowance() == null ? "" : peopleContractSalaryVo.getExtraWorkAllowance().toString());
						row.createCell(15).setCellValue(peopleContractSalaryVo.getBonus() == null ? "" : peopleContractSalaryVo.getBonus().toString());
						row.createCell(16).setCellValue(peopleContractSalaryVo.getReissueFee() == null ? "" : peopleContractSalaryVo.getReissueFee().toString());
						row.createCell(17).setCellValue(peopleContractSalaryVo.getGrossIncome() == null ? "" : peopleContractSalaryVo.getGrossIncome().toString());
						row.createCell(18).setCellValue(peopleContractSalaryVo.getLifeInsurance() == null ? "" : peopleContractSalaryVo.getLifeInsurance().toString());
						row.createCell(19).setCellValue(peopleContractSalaryVo.getJobInsurance() == null ? "" : peopleContractSalaryVo.getJobInsurance().toString());
						row.createCell(20).setCellValue(peopleContractSalaryVo.getHealthInsurance() == null ? "" : peopleContractSalaryVo.getHealthInsurance().toString());
						row.createCell(21).setCellValue(peopleContractSalaryVo.getHouseFund() == null ? "" : peopleContractSalaryVo.getHouseFund().toString());
						row.createCell(22).setCellValue(peopleContractSalaryVo.getExpense() == null ? "" : peopleContractSalaryVo.getExpense().toString());
						row.createCell(23).setCellValue(peopleContractSalaryVo.getNetIncome() == null ? "" : peopleContractSalaryVo.getNetIncome().toString());
						row.createCell(24).setCellValue(peopleContractSalaryVo.getPayDate() == null ? "" : peopleContractSalaryVo.getPayDate().toString());
                        count ++;
						for (int k = 0; k < 25; k++) {
							row.getCell(k).setCellStyle(setBorder);
						}
						row.setHeight((short) 400);
					}
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
				flag = peopleContract2SalaryMapper.insertByImport(list) > 0;
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

				if (row.getCell(1) == null || StringUtils.isBlank(row.getCell(1).toString()))
					continue;

				String peopleName = row.getCell(1).toString().trim();
				PeopleContract peopleContract = peopleContract2Mapper.findFirstPeopleByName(peopleName);
				String peopleCode = peopleContract.getCode();
				if (peopleContract == null || StringUtils.isBlank(peopleCode))
					continue;
				peopleContractSalary.setPeopleCode(peopleCode);

				Double peopleCodeDoubleType = Double.parseDouble(getCellString(row.getCell(2)));

				peopleContractSalary.setJobId(peopleCodeDoubleType.intValue());
				peopleContractSalary.setJobSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(3))));
				peopleContractSalary.setSchoolSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(4))));
				peopleContractSalary.setExamResult(getCellString(row.getCell(5)));
				peopleContractSalary.setJobExamSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(6))));
				peopleContractSalary.setTelephoneAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(7))));
				peopleContractSalary.setTrafficAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(8))));
				peopleContractSalary.setSpecialAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(9))));
				peopleContractSalary.setHeadAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(10))));
				peopleContractSalary.setExtraWorkFee(StringUtilExtra.StringToDecimal("0"));
				peopleContractSalary.setTemperatureAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(11))));
				peopleContractSalary.setExtraWorkDate(StringUtilExtra.StringToDecimal(getCellString(row.getCell(12))));
				peopleContractSalary.setExtraWorkAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(13))));
				peopleContractSalary.setBonus(StringUtilExtra.StringToDecimal(getCellString(row.getCell(14))));
				peopleContractSalary.setReissueFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(15))));
				peopleContractSalary.setGrossIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(16))));
				peopleContractSalary.setLifeInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(17))));
				peopleContractSalary.setJobInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(18))));
				peopleContractSalary.setHealthInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(19))));
				peopleContractSalary.setHouseFund(StringUtilExtra.StringToDecimal(getCellString(row.getCell(20))));
				peopleContractSalary.setExpense(StringUtilExtra.StringToDecimal(getCellString(row.getCell(21))));
				peopleContractSalary.setNetIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(22))));
				peopleContractSalary.setPayDate(getCellString(row.getCell(23)));

				list.add(peopleContractSalary);
			}
		} catch (IOException e1) {
			logger.info("", e1);
			e1.printStackTrace();
		}
		return list;
	}
	

}

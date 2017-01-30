package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.mapper.PeopleRetireMapper;
import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleRetireVo;
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

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.mapper.PeopleRetireSalaryMapper;
import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.service.PeopleRetireSalaryService;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;

import static com.wangzhixuan.utils.WordUtil.getCellString;
//import com.wangzhixuan.vo.PeopleRetireSalaryVo;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleRetireSalaryServiceImpl implements PeopleRetireSalaryService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleRetireSalaryMapper peopleRetireSalaryMapper;

	@Autowired
	private PeopleRetireMapper peopleRetireMapper;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleRetireSalaryMapper.findPeopleRetireSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleRetireSalaryMapper.findPeopleRetireSalaryPageCount(pageInfo));
	}

	@Override
	public void addSalary(PeopleRetireSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetDate(new Date()));
		}
		logger.info("PeopleRetireSalary123:"+JSON.toJSONString(peopleSalary));

		peopleRetireSalaryMapper.insert(peopleSalary);

	}

	@Override
	public void updateSalary(PeopleRetireSalary peopleSalary) {
		peopleRetireSalaryMapper.updateByPrimaryKey(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Long id) {
		peopleRetireSalaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PeopleRetireSalaryVo findPeopleRetireSalaryVoById(Long id) {
		return peopleRetireSalaryMapper.findPeopleRetireSalaryVoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {

		List list = peopleRetireMapper.selectPeopleRetireVoByIds(idList);

		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "退休人员工资.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("退休人员工资");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleRetireSalaryHeaders());

				int count = 0;

				setBorder = WordUtil.setCellStyle(workBook, false);
				for (int i = 0; i < list.size(); i++) {
					PeopleRetireVo peopleRetireVo = (PeopleRetireVo) list.get(i);
					if (peopleRetireVo == null || StringUtils.isBlank(peopleRetireVo.getCode()))
						continue;

					String peopleCode = peopleRetireVo.getCode();
					List<PeopleRetireSalaryVo> peopleRetireSalaryVoList = peopleRetireSalaryMapper.findPeopleRetireSalaryVoListByCode(peopleCode);
					if (peopleRetireSalaryVoList == null || peopleRetireSalaryVoList.size() < 1)
						continue;

					for(int j=0; j<peopleRetireSalaryVoList.size(); j++){
						row = sheet.createRow(count+1);
						PeopleRetireSalaryVo peopleRetireSalaryVo = peopleRetireSalaryVoList.get(j);
						row.createCell(0).setCellValue(count+1);
						row.createCell(1).setCellValue(peopleRetireSalaryVo.getPeopleName());
						row.createCell(2).setCellValue(peopleRetireSalaryVo.getBaseSalary()==null?		"":peopleRetireSalaryVo.getBaseSalary().toString());
						row.createCell(3).setCellValue(peopleRetireSalaryVo.getExtraAllowance()==null?	"":peopleRetireSalaryVo.getExtraAllowance().toString());
						row.createCell(4).setCellValue(peopleRetireSalaryVo.getRentAllowance()==null?	"":peopleRetireSalaryVo.getRentAllowance().toString());
						row.createCell(5).setCellValue(peopleRetireSalaryVo.getRetireAllowance()==null?	"":peopleRetireSalaryVo.getRetireAllowance().toString());
						row.createCell(6).setCellValue(peopleRetireSalaryVo.getRetireFeeIncrease()==null?"":peopleRetireSalaryVo.getRetireFeeIncrease().toString());
						row.createCell(7).setCellValue(peopleRetireSalaryVo.getFoodAllowance()==null?	"":peopleRetireSalaryVo.getFoodAllowance().toString());
						row.createCell(8).setCellValue(peopleRetireSalaryVo.getHealthAllowance()==null?	"":peopleRetireSalaryVo.getHealthAllowance().toString());
						row.createCell(9).setCellValue(peopleRetireSalaryVo.getMedicareFee()==null?		"":peopleRetireSalaryVo.getMedicareFee().toString());
						row.createCell(10).setCellValue(peopleRetireSalaryVo.getPropertyAllowance()==null?"":peopleRetireSalaryVo.getPropertyAllowance().toString());
						row.createCell(11).setCellValue(peopleRetireSalaryVo.getHeatingFee()==null?		"":peopleRetireSalaryVo.getHeatingFee().toString());
						row.createCell(12).setCellValue(peopleRetireSalaryVo.getHandicapAllowance()==null?"":peopleRetireSalaryVo.getHandicapAllowance().toString());
						row.createCell(13).setCellValue(peopleRetireSalaryVo.getGrossIncome()==null?	"":peopleRetireSalaryVo.getGrossIncome().toString());
						row.createCell(14).setCellValue(peopleRetireSalaryVo.getExpense()==null?		"":peopleRetireSalaryVo.getExpense().toString());
						row.createCell(15).setCellValue(peopleRetireSalaryVo.getRentFee()==null?		"":peopleRetireSalaryVo.getRentFee().toString());
						row.createCell(16).setCellValue(peopleRetireSalaryVo.getNetIncome()==null?		"":peopleRetireSalaryVo.getNetIncome().toString());
						row.createCell(17).setCellValue(peopleRetireSalaryVo.getPayDate()==null?		"":peopleRetireSalaryVo.getPayDate().toString());

						count ++;

						for (int k = 0; k < 18; k++) {
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
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("", e);
			} finally {

			}
		}
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {

		boolean flag = false;
		if (files != null && files.length > 0) {

			List<PeopleRetireSalary> list = new ArrayList<PeopleRetireSalary>();

			String filePath = this.getClass().getResource("/").getPath();// 文件临时路径

			for (int i = 0; i < files.length; i++) {

				String path = UploadUtil.fileUpload(filePath, files[i]);

				if (StringUtils.isNotBlank(path)) {
					list = this.getPeopleInfoByExcel(list, path);
				}
			}
			if (list.size() > 0) {
				flag = peopleRetireSalaryMapper.insertByImport(list) > 0;
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
	public List<PeopleRetireSalary> getPeopleInfoByExcel(List<PeopleRetireSalary> list, String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {

				row = sheet.getRow(i);

				PeopleRetireSalary peopleRetireSalary = new PeopleRetireSalary();

				if (row.getCell(1) == null || StringUtils.isBlank(row.getCell(1).toString()))
					continue;

				String peopleName = row.getCell(1).toString().trim();

				PeopleRetire peopleRetire = peopleRetireMapper.findFirstPeopleByName(peopleName);

				if (peopleRetire == null || StringUtils.isBlank(peopleRetire.getCode()))
					continue;

				peopleRetireSalary.setPeopleCode(peopleRetire.getCode());
				peopleRetireSalary.setBaseSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(2))));
				peopleRetireSalary.setExtraAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(3))));
				peopleRetireSalary.setRentAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(4))));
				peopleRetireSalary.setRetireAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(5))));
				peopleRetireSalary.setRetireFeeIncrease(StringUtilExtra.StringToDecimal(getCellString(row.getCell(6))));
				peopleRetireSalary.setFoodAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(7))));
				peopleRetireSalary.setHealthAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(8))));
				peopleRetireSalary.setMedicareFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(9))));
				peopleRetireSalary.setPropertyAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(10))));
				peopleRetireSalary.setHeatingFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(11))));
				peopleRetireSalary.setHandicapAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(12))));
				peopleRetireSalary.setGrossIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(13))));
				peopleRetireSalary.setExpense(StringUtilExtra.StringToDecimal(getCellString(row.getCell(14))));
				peopleRetireSalary.setRentFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(15))));
				peopleRetireSalary.setNetIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(16))));
				peopleRetireSalary.setPayDate(getCellString(row.getCell(17)));

				list.add(peopleRetireSalary);
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return list;
	}
}

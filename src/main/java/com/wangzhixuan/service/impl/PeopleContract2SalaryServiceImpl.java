package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.wangzhixuan.mapper.*;
import com.wangzhixuan.model.*;
import com.wangzhixuan.service.ExamMonthlyService;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.service.PeopleContract2SalaryService;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleContractVo;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.vo.PeopleContractSalaryVo;

import static com.wangzhixuan.utils.WordUtil.getCellString;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
@Service
public class PeopleContract2SalaryServiceImpl implements PeopleContract2SalaryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleContract2SalaryMapper peopleContractSalaryMapper;
	@Autowired
	private PeopleContract2Mapper peopleContractMapper;
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private PeopleJobMapper peopleJobMapper;

	@Autowired
	private ExamMonthlyService examMonthlyService;

	@Autowired
	private ExamYearlyService examYearlyService;

	@Autowired
	private PeopleTimesheetService peopleTimesheetService;

	@Override
	public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleContractSalaryMapper.findPeopleContractSalaryBasePageCondition(pageInfo));
		pageInfo.setTotal(peopleContractSalaryMapper.findPeopleContractSalaryBasePageCount(pageInfo));
	}

	@Override
	public void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleContractSalaryMapper.findPeopleContractSalaryPageCondition(pageInfo));
		pageInfo.setTotal(peopleContractSalaryMapper.findPeopleContractSalaryPageCount(pageInfo));
	}

	@Override
	public PeopleContractSalary findPeopleContractSalaryById(Integer id) {
		return peopleContractSalaryMapper.findPeopleContractSalaryById(id);
	}

	@Override
	public PeopleContractSalaryBase findPeopleContractSalaryBaseById(Integer id) {
		return peopleContractSalaryMapper.findPeopleContractSalaryBaseById(id);
	}

	@Override
	public void addSalary(PeopleContractSalary peopleSalary) {

		// 设置默认日期今天
		if (peopleSalary != null && StringUtils.isBlank(peopleSalary.getPayDate())) {
			peopleSalary.setPayDate(DateUtil.GetCurrentYearAndMonth());
		}

		peopleContractSalaryMapper.insert(peopleSalary);
	}

	@Override
	public void updateSalary(PeopleContractSalary peopleSalary) {
		peopleContractSalaryMapper.updateByPrimaryKey(peopleSalary);
	}

	@Override
	public void deleteSalaryById(Integer id) {
		int count = peopleContractSalaryMapper.deleteByPrimaryKey(id);
		logger.info("delete:" + count);
	}

	@Override
	public PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id) {
		return peopleContractSalaryMapper.findPeopleContractSalaryVoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] idList, String payDate) {
		// TODO Auto-generated method stub
		List list = peopleContractMapper.selectPeopleContractVoByIds(idList);
		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "非固定期合同制人员工资.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("非固定期合同制人员工资");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleContractSalaryHeaders());

				int count = 0;

				setBorder = WordUtil.setCellStyle(workBook, false);
				for (int i = 0; i < list.size(); i++) {
					PeopleContractVo peopleContractVo = (PeopleContractVo) list.get(i);
					if(peopleContractVo ==null || StringUtils.isBlank(peopleContractVo.getCode()))
						continue;

					String peopleCode = peopleContractVo.getCode();
					List<PeopleContractSalaryVo> peopleContractSalaryVoList = peopleContractSalaryMapper.findPeopleContractSalaryVoListByCode(peopleCode);
					if(peopleContractSalaryVoList == null || peopleContractSalaryVoList.size()<1)
						continue;
					for(int j=0; j<peopleContractSalaryVoList.size();j++) {

						PeopleContractSalaryVo peopleContractSalaryVo = peopleContractSalaryVoList.get(j);

						if (peopleContractSalaryVo != null && StringUtils.isNoneBlank(payDate)){
							String peopleContractPayDate = peopleContractSalaryVo.getPayDate();
							if (StringUtils.isBlank(peopleContractPayDate))
								continue;
							if (!peopleContractPayDate.equalsIgnoreCase(payDate))
								continue;
						}

						row = sheet.createRow(count+1);

						row.createCell(0).setCellValue(count + 1);
						row.createCell(1).setCellValue(peopleContractSalaryVo.getPeopleName());
						row.createCell(2).setCellValue(peopleContractSalaryVo.getJobLevel());
						row.createCell(3).setCellValue(peopleContractSalaryVo.getJobSalary() == null ? "" : peopleContractSalaryVo.getJobSalary().toString());
						row.createCell(4).setCellValue(peopleContractSalaryVo.getSchoolSalary() == null ? "" : peopleContractSalaryVo.getSchoolSalary().toString());
						row.createCell(5).setCellValue(peopleContractSalaryVo.getExamResult() == null ? "" : peopleContractSalaryVo.getExamResult().toString());
						row.createCell(6).setCellValue(peopleContractSalaryVo.getJobExamSalaryTotal() == null ? "" : peopleContractSalaryVo.getJobExamSalaryTotal().toString());
						row.createCell(7).setCellValue(peopleContractSalaryVo.getTelephoneAllowance() == null ? "" : peopleContractSalaryVo.getTelephoneAllowance().toString());
						row.createCell(8).setCellValue(peopleContractSalaryVo.getTrafficAllowance() == null ? "" : peopleContractSalaryVo.getTrafficAllowance().toString());
						row.createCell(9).setCellValue(peopleContractSalaryVo.getSpecialAllowance() == null ? "" : peopleContractSalaryVo.getSpecialAllowance().toString());
						row.createCell(10).setCellValue(peopleContractSalaryVo.getHeadAllowance() == null ? "" : peopleContractSalaryVo.getHeadAllowance().toString());
						row.createCell(11).setCellValue(peopleContractSalaryVo.getTemperatureAllowance() == null ? "" : peopleContractSalaryVo.getTemperatureAllowance().toString());
						row.createCell(12).setCellValue(peopleContractSalaryVo.getTimesheetStatus() == null ? "" : peopleContractSalaryVo.getTimesheetStatus().toString());
						row.createCell(13).setCellValue(peopleContractSalaryVo.getOnDutyFee() == null? "" : peopleContractSalaryVo.getOnDutyFee().toString());
						row.createCell(14).setCellValue(peopleContractSalaryVo.getOnDutyDate() == null ? "" : peopleContractSalaryVo.getOnDutyDate().toString());
						row.createCell(15).setCellValue(peopleContractSalaryVo.getOnDutyFeeTotal() == null ? "" : peopleContractSalaryVo.getOnDutyFeeTotal().toString());
						row.createCell(16).setCellValue(peopleContractSalaryVo.getBonus() == null ? "" : peopleContractSalaryVo.getBonus().toString());
						row.createCell(17).setCellValue(peopleContractSalaryVo.getReissueFee() == null ? "" : peopleContractSalaryVo.getReissueFee().toString());
						row.createCell(18).setCellValue(peopleContractSalaryVo.getGrossIncome() == null ? "" : peopleContractSalaryVo.getGrossIncome().toString());
						row.createCell(19).setCellValue(peopleContractSalaryVo.getLifeInsurance() == null ? "" : peopleContractSalaryVo.getLifeInsurance().toString());
						row.createCell(20).setCellValue(peopleContractSalaryVo.getJobInsurance() == null ? "" : peopleContractSalaryVo.getJobInsurance().toString());
						row.createCell(21).setCellValue(peopleContractSalaryVo.getHealthInsurance() == null ? "" : peopleContractSalaryVo.getHealthInsurance().toString());
						row.createCell(22).setCellValue(peopleContractSalaryVo.getHouseFund() == null ? "" : peopleContractSalaryVo.getHouseFund().toString());
						row.createCell(23).setCellValue(peopleContractSalaryVo.getExpense() == null ? "" : peopleContractSalaryVo.getExpense().toString());
						row.createCell(24).setCellValue(peopleContractSalaryVo.getNetIncome() == null ? "" : peopleContractSalaryVo.getNetIncome().toString());
						row.createCell(25).setCellValue(peopleContractSalaryVo.getPayDate() == null ? "" : peopleContractSalaryVo.getPayDate().toString());
						count ++;
						for (int k = 0; k < 26; k++) {
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

				if (row.getCell(1) == null || StringUtils.isBlank(row.getCell(1).toString()))
					continue;

				String peopleName = row.getCell(1).toString().trim();
				PeopleContract peopleContract = peopleContractMapper.findFirstPeopleByName(peopleName);
				String peopleCodeString = peopleContract.getCode();
				if (peopleContract == null || StringUtils.isBlank(peopleCodeString))
					continue;

				peopleContractSalary.setPeopleCode(peopleCodeString);
				String jobName = getCellString(row.getCell(2));
				Integer jobId = null;
				try{
					jobId = dictMapper.findJobIdByName(jobName);
				}catch(Exception exp){
				}
				peopleContractSalary.setJobId(jobId);
				peopleContractSalary.setJobSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(3))));
				peopleContractSalary.setSchoolSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(4))));
				peopleContractSalary.setExamResult(getCellString(row.getCell(5)));
				peopleContractSalary.setJobExamSalary(StringUtilExtra.StringToDecimal(getCellString(row.getCell(6))));
				peopleContractSalary.setTelephoneAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(7))));
				peopleContractSalary.setTrafficAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(8))));
				peopleContractSalary.setSpecialAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(9))));
				peopleContractSalary.setHeadAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(10))));
				peopleContractSalary.setTemperatureAllowance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(11))));
				peopleContractSalary.setTimesheetStatus(StringUtilExtra.StringToDecimal(getCellString(row.getCell(12))));
				peopleContractSalary.setOnDutyFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(13))));
				peopleContractSalary.setOnDutyDate(StringUtilExtra.StringToDecimal(getCellString(row.getCell(14))));
				peopleContractSalary.setOnDutyFeeTotal(StringUtilExtra.StringToDecimal(getCellString(row.getCell(15))));
				peopleContractSalary.setBonus(StringUtilExtra.StringToDecimal(getCellString(row.getCell(16))));
				peopleContractSalary.setReissueFee(StringUtilExtra.StringToDecimal(getCellString(row.getCell(17))));
				peopleContractSalary.setGrossIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(18))));
				peopleContractSalary.setLifeInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(19))));
				peopleContractSalary.setJobInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(20))));
				peopleContractSalary.setHealthInsurance(StringUtilExtra.StringToDecimal(getCellString(row.getCell(21))));
				peopleContractSalary.setHouseFund(StringUtilExtra.StringToDecimal(getCellString(row.getCell(22))));
				peopleContractSalary.setExpense(StringUtilExtra.StringToDecimal(getCellString(row.getCell(23))));
				peopleContractSalary.setNetIncome(StringUtilExtra.StringToDecimal(getCellString(row.getCell(24))));
				peopleContractSalary.setPayDate(getCellString(row.getCell(25)));

				list.add(peopleContractSalary);
			}
		} catch (IOException e1) {
			logger.info("", e1);
			e1.printStackTrace();
		}
		return list;
	}

	@Override
	public PeopleContractSalaryBase findPeopleContractSalaryBaseByCode(String peopleCode) {

		if(StringUtils.isBlank(peopleCode))
			return null;

		return peopleContractSalaryMapper.findPeopleContractSalaryBaseByCode(peopleCode);
	}

	@Override
	public void updateSalaryBase(PeopleContractSalaryBase peopleContractSalaryBase) {
		if(peopleContractSalaryBase == null)
			return;

		if(StringUtils.isBlank(peopleContractSalaryBase.getLastUpdateDate())){
			peopleContractSalaryBase.setLastUpdateDate(DateUtil.GetToday());
		}

		if(peopleContractSalaryBase.getAnnuityBase() == null){
			peopleContractSalaryBase.setAnnuityBase(new BigDecimal(0.00));
		}

		peopleContractSalaryMapper.updateSalaryBase(peopleContractSalaryBase);
	}

	@Override
	public void exportCert(HttpServletResponse response, String ids) {

		PeopleContractVo peopleContractVo = peopleContractMapper.findPeopleContractVoById(Integer.valueOf(ids));

		if (peopleContractVo != null){
			PeopleContractSalary peopleContractSalary = peopleContractSalaryMapper.findLatestPeopleSalaryByCode(peopleContractVo.getCode());

			BigDecimal grossIncome = new BigDecimal("0.00");

			if (peopleContractSalary != null){
				grossIncome = peopleContractSalary.getGrossIncome();
			}

			String filePath=this.getClass().getResource("/template/salaryCert.docx").getPath();
			String newFileName="工资收入证明.docx";

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("${name}", peopleContractVo.getName());
			params.put("${department}",peopleContractVo.getDepartmentName()==null?"":peopleContractVo.getDepartmentName());
			params.put("jobname",peopleContractVo.getJobName()==null?"":peopleContractVo.getJobName());
			params.put("${photoId}", peopleContractVo.getPhotoId() == null? "": peopleContractVo.getPhotoId());
			params.put("${jobDate}", peopleContractVo.getJobDate()==null?"":peopleContractVo.getJobDate());
			params.put("${grossIncome}", grossIncome == null?"0.00":grossIncome.toString());
			params.put("${d}", DateUtil.GetTodayInWord());

			WordUtil.OutputWord(response, filePath, newFileName, params);
		}

	}

	@Override
	public BigDecimal CalculateGrossIncome(PeopleContractSalary peopleContractSalary) {
		if (SalaryCalculator.PeopleContractSalaryCalculator(peopleContractSalary)){
			return peopleContractSalary.getGrossIncome();
		}

		return new BigDecimal(0.00);
	}

	@Override
	public boolean autoCalculateSalary(String payDate, StringBuilder processResult) {
		boolean result = false;
		try{
			List<PeopleContract> peopleContractList = peopleContractMapper.findAllPeople();

			if(peopleContractList == null || peopleContractList.size() < 1){
				result = true;
				processResult.append("目前无合同制人员");
				return result;
			}

			for(int i=0; i<peopleContractList.size(); i++){
				PeopleContract peopleContract = peopleContractList.get(i);
				if (peopleContract == null || StringUtils.isBlank(peopleContract.getCode()))
					continue;


				String peopleCode = peopleContract.getCode();
				Map<String, Object> condition = Maps.newHashMap();
				condition.put("code", peopleCode);
				condition.put("payDate", payDate);
				List<PeopleContractSalary> peopleContractSalaryList = peopleContractSalaryMapper.findPeopleContractSalaryListByCodeAndPayDate(condition);

				//每人每个月只能有一条薪水记录，因此要删除掉其他薪水
				if (peopleContractSalaryList != null && peopleContractSalaryList.size() > 0){
					for(int j=0; j<peopleContractSalaryList.size(); j++){
						PeopleContractSalary peopleContractSalary = peopleContractSalaryList.get(j);
						if (peopleContractSalary == null || peopleContractSalary.getId() == null)
							continue;

						peopleContractSalaryMapper.deleteByPrimaryKey(peopleContractSalary.getId());
					}
				}

				PeopleContractSalary peopleContractSalary = getPeopleContractSalary(payDate, peopleContract.getCode());

				peopleContractSalaryMapper.insert(peopleContractSalary);
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
	public void exportExcelForMonth(HttpServletResponse response, String payDate) {
		List list = peopleContractMapper.findAllPeople();

		if (list != null && list.size() > 0){
			XSSFWorkbook workBook;
			OutputStream os;

			String newFileName = "非固定期合同制人员工资.xlsx";

			try{
				workBook = new XSSFWorkbook();
				XSSFSheet sheet= workBook.createSheet("非固定期合同制人员工资表");
				XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
				//创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleContractSalaryHeaders());

				int count = 0;

				setBorder = WordUtil.setCellStyle(workBook, false);

				for(int i=0; i<list.size(); i++){
					PeopleContract peopleContract = (PeopleContract) list.get(i);

					if (peopleContract == null || StringUtils.isBlank(peopleContract.getCode()))
						continue;
					String peopleCode = peopleContract.getCode();
					Map<String, Object> condition = Maps.newHashMap();
					condition.put("code", peopleCode);
					condition.put("payDate", payDate);

					List<PeopleContractSalaryVo> peopleContractSalaryVoList = peopleContractSalaryMapper.findPeopleContractSalaryVoListByCodeAndPayDate(condition);

					if (peopleContractSalaryVoList == null || peopleContractSalaryVoList.size() < 1)
						continue;

					for(int j=0; j<peopleContractSalaryVoList.size(); j++){
						row = sheet.createRow(count+1);

						PeopleContractSalaryVo peopleContractSalaryVo = peopleContractSalaryVoList.get(j);

						row.createCell(0).setCellValue(count+1);
						row.createCell(1).setCellValue(peopleContractSalaryVo.getPeopleName());
						row.createCell(2).setCellValue(peopleContractSalaryVo.getJobLevel());
						row.createCell(3).setCellValue(peopleContractSalaryVo.getJobSalary() == null?"":peopleContractSalaryVo.getJobSalary().toString());
						row.createCell(4).setCellValue(peopleContractSalaryVo.getSchoolSalary() == null? "": peopleContractSalaryVo.getSchoolSalary().toString());
						row.createCell(5).setCellValue(peopleContractSalaryVo.getExamResult() == null ?  "": peopleContractSalaryVo.getExamResult());
						row.createCell(6).setCellValue(peopleContractSalaryVo.getJobExamSalaryTotal() == null ? "": peopleContractSalaryVo.getJobExamSalaryTotal().toString());
						row.createCell(7).setCellValue(peopleContractSalaryVo.getTelephoneAllowance() == null ? "": peopleContractSalaryVo.getTelephoneAllowance().toString());
						row.createCell(8).setCellValue(peopleContractSalaryVo.getTrafficAllowance() == null? "": peopleContractSalaryVo.getTrafficAllowance().toString());
						row.createCell(9).setCellValue(peopleContractSalaryVo.getSpecialAllowance() == null ? "" : peopleContractSalaryVo.getSpecialAllowance().toString());
						row.createCell(10).setCellValue(peopleContractSalaryVo.getHeadAllowance() == null ? "" : peopleContractSalaryVo.getHeadAllowance().toString());
						row.createCell(11).setCellValue(peopleContractSalaryVo.getTimesheetStatus() == null? "" : peopleContractSalaryVo.getTimesheetStatus().toString());
						row.createCell(12).setCellValue(peopleContractSalaryVo.getOnDutyFee() == null? "" : peopleContractSalaryVo.getOnDutyFee().toString());
						row.createCell(13).setCellValue(peopleContractSalaryVo.getOnDutyDate() == null ? "" : peopleContractSalaryVo.getOnDutyDate().toString());
						row.createCell(14).setCellValue(peopleContractSalaryVo.getOnDutyFeeTotal() == null ? "" : peopleContractSalaryVo.getOnDutyFeeTotal().toString());
						row.createCell(15).setCellValue(peopleContractSalaryVo.getBonus() == null ? "" : peopleContractSalaryVo.getBonus().toString());
						row.createCell(16).setCellValue(peopleContractSalaryVo.getReissueFee() == null ? "" : peopleContractSalaryVo.getReissueFee().toString());
						row.createCell(17).setCellValue(peopleContractSalaryVo.getTemperatureAllowance() == null ? "" : peopleContractSalaryVo.getTemperatureAllowance().toString());
						row.createCell(18).setCellValue(peopleContractSalaryVo.getGrossIncome() == null ? "" : peopleContractSalaryVo.getGrossIncome().toString());
						row.createCell(19).setCellValue(peopleContractSalaryVo.getLifeInsurance() == null ? "" : peopleContractSalaryVo.getLifeInsurance().toString());
						row.createCell(20).setCellValue(peopleContractSalaryVo.getJobInsurance() == null ? "" : peopleContractSalaryVo.getJobInsurance().toString());
						row.createCell(21).setCellValue(peopleContractSalaryVo.getHealthInsurance() == null ? "" : peopleContractSalaryVo.getHealthInsurance().toString());
						row.createCell(22).setCellValue(peopleContractSalaryVo.getHouseFund() == null ? "" : peopleContractSalaryVo.getHouseFund().toString());
						row.createCell(23).setCellValue(peopleContractSalaryVo.getExpense() == null ? "" : peopleContractSalaryVo.getExpense().toString());
						row.createCell(24).setCellValue(peopleContractSalaryVo.getNetIncome() == null ? "" : peopleContractSalaryVo.getNetIncome().toString());
						row.createCell(25).setCellValue(peopleContractSalaryVo.getPayDate() == null ? "" : peopleContractSalaryVo.getPayDate().toString());
						
						count++;

						for(int k=0; k<26; k++){
							row.getCell(k).setCellStyle(setBorder);
							sheet.autoSizeColumn(k);
						}
						row.setHeight((short)400);
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
	public void updateJobAndRankSalary(PeopleContractSalaryBase peopleContractSalaryBase) {

		if (peopleContractSalaryBase == null)
			return;

		Integer jobId = peopleContractSalaryBase.getJobId();

		if (jobId != null){
			PeopleJob peopleJob = peopleJobMapper.findPeopleJobById(Long.valueOf(jobId));

			if (peopleJob != null)
				peopleContractSalaryBase.setJobSalary(peopleJob.getSalary());
		}
	}

	private PeopleContractSalary getPeopleContractSalary(String payDate, String peopleCode) {
		try {
			PeopleContractSalaryBase peopleContractSalaryBase = peopleContractSalaryMapper.findPeopleContractSalaryBaseByCode(peopleCode);

			PeopleContractSalary peopleContractSalary = new PeopleContractSalary();
			BeanUtils.copyProperties(peopleContractSalary, peopleContractSalaryBase);

			peopleContractSalary.setId(null);
			peopleContractSalary.setPeopleCode(peopleCode);
			peopleContractSalary.setPayDate(payDate);


			//根据当月考勤情况计算交通补贴和降温补贴
			String firstDayOfCurrentMonth = DateUtil.GetFirstDayOfSelectMonth(payDate);
			String lastDayOfCurrentMonth = DateUtil.GetLastDayOfSelectMonth(payDate);

			BigDecimal sumVacationPeriod = peopleTimesheetService.findVacationSumByCodeAndDate(
					peopleContractSalaryBase.getPeopleCode(),
					firstDayOfCurrentMonth,
					lastDayOfCurrentMonth);

			peopleContractSalary.setTimesheetStatus(sumVacationPeriod);

			SalaryCalculator.PeopleContractSalaryCalculateTemperatureAllowance(peopleContractSalary);

			//根据月度考评计算绩效工资
			String examResult = examMonthlyService.findPeopleExamMonthlyResultByCodeAndDate(
					peopleContractSalary.getPeopleCode(),
					payDate
			);

			peopleContractSalary.setExamResult(examResult);

			SalaryCalculator.GetPerformanceTotalByMonthlyExamResult(peopleContractSalary);

			//根据年度考评计算奖金
			String examYearlyResult = examYearlyService.findPeopleExamYearlyResultByCodeAndYear(
					peopleContractSalary.getPeopleCode(),
					DateUtil.GetCurrentYear()
			);

			BigDecimal bonus = SalaryCalculator.GetBonusByYearlyExamResult(examYearlyResult, peopleContractSalaryBase);
			peopleContractSalary.setBonus(bonus);

			SalaryCalculator.PeopleContractSalaryCalculator(peopleContractSalary);

			return peopleContractSalary;
		} catch (Exception exp) {
			PeopleContractSalary peopleContractSalary = new PeopleContractSalary();

			peopleContractSalary.setPeopleCode(peopleCode);
			peopleContractSalary.setPayDate(payDate);
			return peopleContractSalary;
		}
	}
}

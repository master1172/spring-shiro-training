package com.wangzhixuan.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.People;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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

import com.wangzhixuan.mapper.PeopleTimesheetMapper;
import com.wangzhixuan.model.PeopleTimesheet;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.service.PeopleRehireService;
import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.service.PeopleTimesheetService;
import com.wangzhixuan.vo.PeopleTimesheetVo;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeopleTimesheetServiceImpl implements PeopleTimesheetService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleTimesheetMapper peopleTimesheetMapper;

	@Autowired
	private PeopleMapper peopleMapper;

	@Override
	public PeopleTimesheet selectByPrimaryKey(Integer id) {
		return peopleTimesheetMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		return peopleTimesheetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insert(PeopleTimesheet record) {
		if (StringUtils.isBlank(record.getCheckDate())){
			record.setCheckDate(null);
		}
		return peopleTimesheetMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(PeopleTimesheet record) {
		if (StringUtils.isBlank(record.getCheckDate())){
			record.setCheckDate(null);
		}
		return peopleTimesheetMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PeopleTimesheetVo> findPeopleTimeSheetListByCode(String code) {
		if (StringUtils.isBlank(code)){
			return new ArrayList<PeopleTimesheetVo>();
		}
		return peopleTimesheetMapper.findTimesheetVoByPeopleCode(code);
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(peopleTimesheetMapper.findPeopleTimesheetPageCondition(pageInfo));
		pageInfo.setTotal(peopleTimesheetMapper.findPeopleTimesheetPageCount(pageInfo));
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {

		boolean flag = false;

		if (files != null && files.length > 0) {

			List<PeopleTimesheet> list = new ArrayList<PeopleTimesheet>();

			String filePath = this.getClass().getResource("/").getPath();// 文件临时路径

			for (int i = 0; i < files.length; i++) {

				try{
					String path = UploadUtil.fileUpload(filePath, files[i]);
					if (StringUtils.isNotBlank(path)) {
						list = getPeopleInfoByExcel(list, path);
					}
				}catch (Exception exp){
					continue;
				}

			}
			if (list.size() > 0) {
				flag = peopleTimesheetMapper.insertByImport(list) > 0;
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
	public List<PeopleTimesheet> getPeopleInfoByExcel(List<PeopleTimesheet> list, String path) throws IOException {

			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {

				try {
					PeopleTimesheet timesheet = new PeopleTimesheet();

					row = sheet.getRow(i);

					if (row.getCell(1) == null || row.getCell(1).toString().trim().equals(""))
						continue;
					String peopleName = row.getCell(1).toString().trim();

					People people = peopleMapper.findFirstPeopleByName(peopleName);

					if (people == null || StringUtils.isBlank(people.getCode()))
						continue;

					timesheet.setPeopleCode(people.getCode());

					//日期
					if (row.getCell(2) != null && !row.getCell(2).toString().trim().equals(""))
						timesheet.setCheckDate(row.getCell(2).toString().trim());

					//请假原因
					if (row.getCell(3) != null && !row.getCell(3).toString().trim().equals(""))
						timesheet.setStatus(row.getCell(3).toString().trim());

					if (row.getCell(4) != null && !row.getCell(4).toString().trim().equals(""))
						timesheet.setStatusExtra(row.getCell(4).toString().trim());

					//请假时长
					if (row.getCell(5) != null && !row.getCell(5).toString().trim().equals(""))
						timesheet.setVacationPeriod(StringUtilExtra.StringToDecimal(row.getCell(5).toString().trim()));

					list.add(timesheet);

				}catch(Exception exp) {
					continue;
				}
			}

		return list;
	}

	// 导出excel
	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {

		List list = peopleMapper.selectPeopleVoByIds(idList);

		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "考勤信息.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("考勤信息");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getTimesheetHeader());
				setBorder = WordUtil.setCellStyle(workBook, false);

				int count = 0;

				for (int i = 0; i < list.size(); i++) {

					PeopleVo peopleVo = (PeopleVo) list.get(i);
					if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
						continue;
					String peopleCode = peopleVo.getCode();

					List<PeopleTimesheetVo> timesheetList = peopleTimesheetMapper.findTimesheetVoByPeopleCode(peopleCode);

					if (timesheetList == null || timesheetList.size() < 1)
						continue;

					for(int j=0; j<timesheetList.size(); j++){
						row = sheet.createRow(count + 1);
						PeopleTimesheetVo timesheetVo = timesheetList.get(j);

						row.createCell(0).setCellValue(count+1);
						row.createCell(1).setCellValue(timesheetVo.getPeopleName());
						row.createCell(2).setCellValue(timesheetVo.getCheckDate());
						row.createCell(3).setCellValue(timesheetVo.getStatus());
						row.createCell(4).setCellValue(timesheetVo.getStatusExtra());
						row.createCell(5).setCellValue(StringUtilExtra.DecimalToString(timesheetVo.getVacationPeriod()));

						count++;

						for (int k = 0; k < 6; k++) {
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

			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}

	@Override
	public PeopleTimesheetVo findPeopleTimesheetVoById(Integer id) {
		if (id == null)
			return new PeopleTimesheetVo();

		return peopleTimesheetMapper.findTimesheetVoById(id);
	}

	@Override
	public List<PeopleTimesheet> findPeopleTimesheetListByCodeAndDate(String code, String checkDateMin, String checkDateMax) {
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("peopleCode",code);
		condition.put("checkDateMin",checkDateMin);
		condition.put("checkDateMax",checkDateMax);
		return peopleTimesheetMapper.findTimesheetListByCodeAndDate(condition);
	}

	@Override
	public BigDecimal findVacationSumByCodeAndDate(String code, String checkDateMin, String checkDateMax) {
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("peopleCode",code);
		condition.put("checkDateMin",checkDateMin);
		condition.put("checkDateMax",checkDateMax);
		List<PeopleTimesheet> peopleTimesheetList = peopleTimesheetMapper.findTimesheetListByCodeAndDate(condition);

		BigDecimal sumVacationPeriod = BigDecimal.valueOf(0.0);

		if (peopleTimesheetList == null || peopleTimesheetList.size()<1)
			return sumVacationPeriod;

		for (int i=0; i<peopleTimesheetList.size(); i++){
			PeopleTimesheet peopleTimesheet = peopleTimesheetList.get(i);
			if (peopleTimesheet == null || StringUtils.isBlank(peopleTimesheet.getPeopleCode()))
			            continue;
			if (peopleTimesheet.getVacationPeriod() == null)
				continue;
			sumVacationPeriod = sumVacationPeriod.add(peopleTimesheet.getVacationPeriod());
		}

		return sumVacationPeriod;
	}

	@Override
	public void exportVacationResult(HttpServletResponse response, String checkDate) {

		XSSFWorkbook workBook;
		OutputStream os;
		String newFileName = "考勤统计信息.xlsx";
		try {
			String filePath=this.getClass().getResource("/template/vacationResult.xlsx").getPath();
			workBook = new XSSFWorkbook(filePath);

			List<People> peopleList = peopleMapper.findAllPeople();

			int count = 0;
			XSSFRow row;
			XSSFSheet sheet= workBook.getSheetAt(0);
			XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);

			if (peopleList != null && peopleList.size() > 0){

				for(int i=0; i<peopleList.size(); i++){
					People people = peopleList.get(i);

					if (people == null || StringUtils.isBlank(people.getCode()) || StringUtils.isBlank(people.getName()))
						continue;

					String name = people.getName();
					String code = people.getCode();

					row = ExcelUtil.insertRow(sheet,i+5);
					row.createCell(0).setCellValue(name);

					List<PeopleTimesheetVo> peopleTimesheetVoList = peopleTimesheetMapper.findTimesheetVoByPeopleCode(code);

					for(int j=0; j<31;j++){
						row.createCell(j+1).setCellValue("√");
						if (peopleTimesheetVoList == null)
							continue;
						for(int k=0; k<peopleTimesheetVoList.size(); k++){
							PeopleTimesheetVo peopleTimesheetVo = peopleTimesheetVoList.get(k);
							if (peopleTimesheetVo == null)
								continue;
							//比较两个日期
							if (DateUtil.CompareTwoDate(checkDate, j, peopleTimesheetVo.getCheckDate())){
								row.createCell(j+1).setCellValue("×");
							}else{
								row.createCell(j+1).setCellValue("√");
							}
						}
					}

					row.setHeight((short) 400);
				}

				sheet.setDefaultRowHeightInPoints(21);
			}

			response.reset();
			os = response.getOutputStream();
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
			workBook.write(os);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void findPeopleDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleTimesheetMapper.findPeopleTimesheetSumPageCondition(pageInfo));
		pageInfo.setTotal(peopleTimesheetMapper.findPeopleTimesheetSumPageCount(pageInfo));
	}

	@Override
	public void findPeopleContractDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleTimesheetMapper.findPeopleContractTimesheetSumPageCondition(pageInfo));
		pageInfo.setTotal(peopleTimesheetMapper.findPeopleContractTimesheetSumPageCount(pageInfo));
	}

	@Override
	public void findPeopleContract2DataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleTimesheetMapper.findPeopleContract2TimesheetSumPageCondition(pageInfo));
		pageInfo.setTotal(peopleTimesheetMapper.findPeopleContract2TimesheetSumPageCount(pageInfo));
	}
}

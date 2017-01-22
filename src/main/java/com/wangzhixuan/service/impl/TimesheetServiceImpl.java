package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import com.wangzhixuan.mapper.TimesheetMapper;
import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.service.PeopleRehireService;
import com.wangzhixuan.service.PeopleRetireService;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.service.TimesheetService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.ExcelUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleVo;
import com.wangzhixuan.vo.TimesheetVo;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimesheetMapper timesheetMapper;
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private PeopleRehireService peopleRehireService;
	@Autowired
	private PeopleContractService peopleContractService;
	@Autowired
	private PeopleRetireService peopleRetireService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return timesheetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Timesheet record) {
		return timesheetMapper.insert(record);
	}

	@Override
	public int insertSelective(Timesheet record) {
		return timesheetMapper.insert(record);
	}

	@Override
	public Timesheet selectByPrimaryKey(Integer id) {
		return timesheetMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Timesheet record) {
		return timesheetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Timesheet record) {
		return timesheetMapper.updateByPrimaryKey(record);
	}

	@Override
	public Timesheet findTimeSheetByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(timesheetMapper.findDataGrid(pageInfo));
		pageInfo.setTotal(timesheetMapper.findDataGridCount(pageInfo));
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {
		boolean flag = false;
		if (files != null && files.length > 0) {

			List<Timesheet> list = new ArrayList<Timesheet>();

			String filePath = this.getClass().getResource("/").getPath();// 文件临时路径

			for (int i = 0; i < files.length; i++) {

				String path = UploadUtil.fileUpload(filePath, files[i]);

				if (StringUtils.isNotBlank(path)) {
					list = this.getPeopleInfoByExcel(list, path);
				}
			}
			if (list.size() > 0) {
				flag = timesheetMapper.insertByImport(list) > 0;
			}
		}
		return flag;
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

	/**
	 * 文件读取
	 * 
	 * @param list
	 * @param path
	 * @return
	 */
	public List<Timesheet> getPeopleInfoByExcel(List<Timesheet> list, String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				Timesheet timesheet = new Timesheet();
				timesheet.setPeopleCode(getCellString(row.getCell(1)));
				timesheet.setPeopleType(getCellString(row.getCell(2)));
				timesheet.setCheckDate(getCellString(row.getCell(3)));
				timesheet.setStatus(getCellString(row.getCell(4)));
				String vacationPeriod = getCellString(row.getCell(5));
				if (StringUtils.isNotBlank(vacationPeriod)) {
					try {
						timesheet.setVacationPeriod(new BigDecimal(vacationPeriod));
					} catch (Exception e) {
						logger.error("", e);
					}
				}

				list.add(timesheet);
			}
		} catch (IOException e1) {
			logger.info("", e1);
			e1.printStackTrace();
		}
		return list;
	}

	// 导出excel
	@Override
	public void exportExcel(HttpServletResponse response, String[] idList) {
		List list = timesheetMapper.selectTimesheetVoByIds(idList);
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
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					TimesheetVo timesheetVo = (TimesheetVo) list.get(i);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(timesheetVo.getPeopleCode());
					row.createCell(2).setCellValue(timesheetVo.getPeopleType());
					row.createCell(3).setCellValue(timesheetVo.getCheckDate());
					row.createCell(4).setCellValue(timesheetVo.getStatus());
					BigDecimal va = timesheetVo.getVacationPeriod();
					if (va == null) {
						row.createCell(5).setCellValue("");
					} else{
						row.createCell(5).setCellValue(va.toString());
					}
					

					for (int j = 0; j < 6; j++) {
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
}

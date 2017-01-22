package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

	/**
	 * 文件读取
	 * 
	 * @param list
	 * @param path
	 * @return
	 */
	private List<Timesheet> getPeopleInfoByExcel(List<Timesheet> list,
			String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				Timesheet p = new Timesheet();

				for (int j = 0; j < 6; j++) {
					logger.info(i+","+j+":"+row.getCell(j).toString().trim());
				}

				list.add(p);
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
		List list = timesheetMapper.selectPeopleVoByIds(idList);
		if (list != null && list.size() > 0) {
			XSSFWorkbook workBook;
			OutputStream os;
			String newFileName = "在编人员信息.xlsx";
			try {
				workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("在编人员信息");
				XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
				// 创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder,
						ConstUtil.getPeopleHeaders());

				setBorder = WordUtil.setCellStyle(workBook, false);
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					PeopleVo p = (PeopleVo) list.get(i);
					row.createCell(0).setCellValue(i + 1);
					row.createCell(1).setCellValue(p.getName());
					row.createCell(2).setCellValue(
							p.getSex() == null ? "" : (p.getSex() == 0 ? "男"
									: "女"));
					row.createCell(3).setCellValue(p.getNationalName());
					row.createCell(4).setCellValue(p.getBirthday().toString());
					row.createCell(5).setCellValue(p.getNativeName());
					row.createCell(6).setCellValue(p.getEducationName());
					row.createCell(7).setCellValue(p.getDegreeName());
					row.createCell(8).setCellValue(p.getPoliticalName());
					row.createCell(9).setCellValue(p.getPartyDate());
					row.createCell(10).setCellValue(p.getWorkDate());
					row.createCell(11).setCellValue(p.getSchoolDate());
					row.createCell(12).setCellValue(p.getJobName());
					row.createCell(13).setCellValue(p.getJobCategory());
					row.createCell(14).setCellValue(p.getJobLevelName());
					row.createCell(15).setCellValue(p.getJobDate());
					row.createCell(16).setCellValue(p.getJobLevelDate());
					row.createCell(17).setCellValue(
							p.getAge() == null ? "" : p.getAge().toString());
					row.createCell(18).setCellValue(
							p.getVirtualAge() == null ? "" : p.getVirtualAge()
									.toString());
					row.createCell(19).setCellValue(
							p.getWorkAge() == null ? "" : p.getWorkAge()
									.toString());
					row.createCell(20).setCellValue(p.getFormation());
					row.createCell(21).setCellValue(p.getMobile());
					row.createCell(22).setCellValue(p.getMarriageName());
					row.createCell(23).setCellValue(p.getPhotoId());
					row.createCell(24).setCellValue(p.getAddress());
					row.createCell(25).setCellValue(p.getHukou());
					row.createCell(26).setCellValue(p.getHukouAddress());
					row.createCell(27).setCellValue(p.getFinalEducationName());
					row.createCell(28).setCellValue(p.getMajor());
					row.createCell(29).setCellValue(p.getGraduateSchool());
					row.createCell(30).setCellValue(p.getContact());
					row.createCell(31).setCellValue(p.getRelationship());
					row.createCell(32).setCellValue(p.getContactNumber());
					row.createCell(33).setCellValue(p.getFamilyInfo1());
					row.createCell(34).setCellValue(p.getFamilyInfo2());
					row.createCell(35).setCellValue(p.getFamilyInfo3());
					row.createCell(36).setCellValue(p.getFamilyInfo4());
					row.createCell(37).setCellValue(p.getIdentityName());

					for (int j = 0; j < 38; j++) {
						row.getCell(j).setCellStyle(setBorder);
					}
					row.setHeight((short) 400);
				}
				sheet.setDefaultRowHeightInPoints(21);
				response.reset();
				os = response.getOutputStream();
				response.setHeader(
						"Content-disposition",
						"attachment; filename="
								+ new String(newFileName.getBytes("GBK"),
										"ISO-8859-1"));
				workBook.write(os);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
	}
}

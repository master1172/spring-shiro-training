package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.ExamYearlyMapper;
import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.ExamYearlyService;
import com.wangzhixuan.utils.*;

import com.wangzhixuan.vo.ExamYearlyVo;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mengfw on 2017/1/20.
 */
@Service
public class ExamYearlyServiceImp implements ExamYearlyService {
	@Autowired
	private ExamYearlyMapper examYearlyMapper;
	@Autowired
	private PeopleMapper peopleMapper;

	@Autowired
	private DictMapper dictMapper;

	@Override
	public ExamYearly findExamYearlyById(Long id) {
		return examYearlyMapper.selectByPrimaryKey(id.intValue());
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(examYearlyMapper.findPageCondition(pageInfo));
		pageInfo.setTotal(examYearlyMapper.findPageCount(pageInfo));
	}

	@Override
	public void add(ExamYearly examYearly) {
		if (examYearly.getYear() == null){
			examYearly.setYear(DateUtil.GetCurrentYear());
		}
		examYearlyMapper.insert(examYearly);
	}

	@Override
	public void deleteById(Long id) {
		examYearlyMapper.deleteByPrimaryKey(id.intValue());
	}

	@Override
	public void update(ExamYearly examYearly) {
		if (examYearly.getYear() == null){
			examYearly.setYear(DateUtil.GetCurrentYear());
		}
		examYearlyMapper.updateByPrimaryKey(examYearly);
	}

	@Override
	public void exportExcel(HttpServletResponse response, String[] ids) {
		List<PeopleVo> list = peopleMapper.selectPeopleVoByIds(ids);

		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		XSSFWorkbook workBook;
		OutputStream os;
		String newFileName = "年度考核信息.xlsx";
		try {
			workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("年度考核信息");
			XSSFCellStyle setBorder = WordUtil.setCellStyle(workBook, true);
			// 创建表头
			XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder,
					ConstUtil.getEaxmYearlyHeaders());
			int count = 0;
			setBorder = WordUtil.setCellStyle(workBook, false);

			for(int i=0;i<list.size();i++) {
				PeopleVo peopleVo = list.get(i);
				if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
					continue;

				String peopleCode = peopleVo.getCode();

				List<ExamYearlyVo> examYearlyVoList = examYearlyMapper.findExamYearlyVoListByCode(peopleCode);

				if (examYearlyVoList == null || examYearlyVoList.size() < 1)
					continue;

				for(int j=0; j<examYearlyVoList.size(); j++){
					row = sheet.createRow(count+1);
					ExamYearlyVo examYearlyVo = examYearlyVoList.get(j);
					row.createCell(0).setCellValue(count+1);
					row.createCell(1).setCellValue(examYearlyVo.getName());
					row.createCell(2).setCellValue(examYearlyVo.getYear());
					row.createCell(3).setCellValue(examYearlyVo.getExamResult());
					row.createCell(4).setCellValue(examYearlyVo.getExamOperation());

					count++;

					for(int k=0; k<5; k++){
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

	}catch (IOException e) {
		e.printStackTrace();
	}
	}

	@Override
	public boolean insertByImport(CommonsMultipartFile[] files) {
		boolean flag = false;
		if (files == null || files.length <= 0) {
			return flag;
		}
		List<ExamYearly> list = new ArrayList<ExamYearly>();

		String filePath = this.getClass().getResource("/").getPath();// 文件临时路径

		for (int i = 0; i < files.length; i++) {

			String path = UploadUtil.fileUpload(filePath, files[i]);

			if (StringUtils.isNotBlank(path)) {
				list = getExamYearlyByExcel(list, path);
			}

		}
		if (list.size() > 0) {
			flag = examYearlyMapper.insertByImport(list) > 0;
		}
		return flag;
	}

	@Override
	public void batchDeletePeopleByIds(String[] idList) {
		examYearlyMapper.batchDeleteByIds(idList);
	}

	private List<ExamYearly> getExamYearlyByExcel(List<ExamYearly> list,
			String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);

			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				ExamYearly examYearly = new ExamYearly();
				// 姓名
				if (row.getCell(1) == null || row.getCell(1).toString().trim().equals("")) {
					continue;
				}

				String name = row.getCell(1).toString().trim();

				People people = peopleMapper.findFirstPeopleByName(name);

				if (people == null || StringUtils.isBlank(people.getCode())) {
					continue;
				}

				examYearly.setPeopleCode(people.getCode());

				if (row.getCell(2) != null && !row.getCell(2).toString().trim().equals("")) {
					String yearValue = row.getCell(2).toString().trim();
					examYearly.setYear(StringUtilExtra.StrToInteger(yearValue));
				}else{
					examYearly.setYear(DateUtil.GetCurrentYear());
				}


				if(row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
					examYearly.setExamResult(row.getCell(3).toString().trim());
				}

				if (row.getCell(4) != null && !row.getCell(4).toString().trim().equals("")) {
					examYearly.setExamOperation(row.getCell(4).toString().trim());
				}

				list.add(examYearly);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}

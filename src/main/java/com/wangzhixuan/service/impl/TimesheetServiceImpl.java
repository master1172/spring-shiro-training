package com.wangzhixuan.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleContractMapper;
import com.wangzhixuan.mapper.TimesheetMapper;
import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.model.Timesheet;
import com.wangzhixuan.service.PeopleContractService;
import com.wangzhixuan.service.TimesheetService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleContractVo;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimesheetMapper timesheetMapper;
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
   
}

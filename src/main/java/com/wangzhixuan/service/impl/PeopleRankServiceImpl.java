package com.wangzhixuan.service.impl;


import com.wangzhixuan.mapper.PeopleRankMapper;
import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.model.PeopleRank;
import com.wangzhixuan.service.PeopleRankService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleJobVo;
import com.wangzhixuan.vo.PeopleRankVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PeopleRankServiceImpl implements PeopleRankService {

	@Resource
    private PeopleRankMapper peopleRankMapper;


    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleRankMapper.findPeopleRankPageCondition(pageInfo));
		pageInfo.setTotal(peopleRankMapper.findPeopleRankPageCount(pageInfo));

    }

	@Override
    public void addPeopleRank(PeopleRankVo peopleRankVo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		PeopleRank peoplerank = new PeopleRank();
		BeanUtils.copyProperties(peoplerank, peopleRankVo);
        peopleRankMapper.insert(peoplerank);

    }

	@Override
    public void updatePeopleRank(PeopleRankVo peopleRankVo) throws InvocationTargetException, IllegalAccessException {
		PeopleRank peoplerank = new PeopleRank();
		BeanUtils.copyProperties(peoplerank, peopleRankVo);
		peopleRankMapper.updatePeopleRank(peoplerank);

    }

	@Override
	public void updatePeopleRank(PeopleRank peopleRank){
		peopleRankMapper.updatePeopleRank(peopleRank);
	}

	@Override
    public void deletePeopleRankById(Long id) {
        peopleRankMapper.deletePeopleRankById(id);
    }

    @Override
    public void batchDeletePeopleRankByIds(String[] ids){
        peopleRankMapper.batchDeletePeopleRankByIds(ids);
    }
	@Override
	public PeopleRankVo findPeopleRankVoById(Long id){
		return peopleRankMapper.findPeopleRankVoById(id);
	}
	@Override
	public PeopleRank findPeopleRankById(Long id) {
		return peopleRankMapper.findPeopleRankById(id);
	}

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
    	boolean flag=false;
    	if(files!=null && files.length>0){

			List<PeopleRank> list = new ArrayList<PeopleRank>();

			String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    		for(int i=0; i<files.length; i++){

    			String path= UploadUtil.fileUpload(filePath, files[i]);

				if( StringUtils.isNotBlank(path)){
        			list=getPeopleRankInfoByExcel(list,path);
    			}
    		}
    		if(list.size()>0){
        		flag=peopleRankMapper.insertByImport(list)>0;
    		}
    	}
    	return flag;
    }
    /**
     * 文件读取
     * @param list
     * @param path
     * @return
     */
    private List<PeopleRank> getPeopleRankInfoByExcel(List<PeopleRank> list,String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			//
			List<XSSFPictureData> pictureList = xwb.getAllPictures();

			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				PeopleRank p = new PeopleRank();

				//薪级
				if (row.getCell(1) == null || row.getCell(1).toString().trim().equals("")) {
					continue;
				}
				String rank_level = row.getCell(1).toString().trim();
				p.setRank_level(rank_level);

				//薪级工资
				if (row.getCell(2) != null && !row.getCell(2).toString().trim().equals("")) {
					String salary = row.getCell(2).toString().trim();
					p.setSalary(StringUtilExtra.StringToDecimal(salary));
				}

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	//导出excel
    @Override
    public void exportExcel(HttpServletResponse response,String[] idList){
    	List list=peopleRankMapper.selectPeopleRankVoByIds(idList);
    	if(list!=null&&list.size()>0){
    		XSSFWorkbook workBook;
    		OutputStream os;
        	String newFileName="在编人员薪级.xlsx";
    		try {
    			workBook = new XSSFWorkbook();
    			XSSFSheet sheet= workBook.createSheet("在编人员薪级信息");
    			XSSFCellStyle setBorder=WordUtil.setCellStyle(workBook,true);
    			//创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleHeaders());

    			setBorder=WordUtil.setCellStyle(workBook,false);
        		for(int i=0;i<list.size();i++){
        			row=sheet.createRow(i+1);
        			PeopleRankVo p=(PeopleRankVo)list.get(i);
        			row.createCell(0).setCellValue(i+1);
        			row.createCell(1).setCellValue(p.getRank_level());
        			row.createCell(2).setCellValue(p.getSalary().floatValue());
					for(int j=0; j<38; j++){
						row.getCell(j).setCellStyle(setBorder);
					}
        			row.setHeight((short) 400);
        		}
        		sheet.setDefaultRowHeightInPoints(21);
    			response.reset();
    	        os = response.getOutputStream();
    	        response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
    			workBook.write(os);
    		    os.close();
    		}catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    			
    		}
    	}
    }


	//导出word
    @Override
    public void exportWord(HttpServletResponse response,String id){
		PeopleRankVo p= peopleRankMapper.findPeopleRankVoById(Long.valueOf(id));
    	if(p!=null){
        	XWPFDocument doc;
    		OutputStream os;
        	String filePath=this.getClass().getResource("/template/custRankInfo.docx").getPath();
        	String newFileName="在编人员薪级信息.docx";

			Map<String,Object> params = new HashMap<String,Object>();

			params.put("${rank_level}",p.getRank_level());
			params.put("${salary}",p.getSalary());


			WordUtil.OutputWord(response, filePath, newFileName, params);
    	}
    }


}

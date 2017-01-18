package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleJobMapper;
import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.service.PeopleJobService;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleJobVo;
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
public class PeopleJobServiceImpl implements PeopleJobService {

	@Resource
    private PeopleJobMapper peopleJobMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
		pageInfo.setRows(peopleJobMapper.findPeopleJobPageCondition(pageInfo));
		pageInfo.setTotal(peopleJobMapper.findPeopleJobPageCount(pageInfo));

    }

	@Override
    public void addPeopleJob(PeopleJobVo peoplejobvo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		PeopleJob peoplejob = new PeopleJob();
		BeanUtils.copyProperties(peoplejob,peoplejobvo);
        peopleJobMapper.insert(peoplejob);

    }

	@Override
    public void updatePeopleJob(PeopleJobVo peoplejobvo) throws InvocationTargetException, IllegalAccessException {


		PeopleJob peoplejob = new PeopleJob();
		BeanUtils.copyProperties(peoplejob,peoplejobvo);
		peopleJobMapper.updatePeopleJob(peoplejob);

    }

	@Override
	public void updatePeopleJob(PeopleJob peopleJob){
		peopleJobMapper.updatePeopleJob(peopleJob);
	}

	@Override
    public void deletePeopleJobById(Long id) {
        peopleJobMapper.deletePeopleJobById(id);
    }

    @Override
    public void batchDeletePeopleJobByIds(String[] ids){
        peopleJobMapper.batchDeletePeopleJobByIds(ids);
    }


    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
    	boolean flag=false;
    	if(files!=null && files.length>0){

			List<PeopleJob> list = new ArrayList<PeopleJob>();

			String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    		for(int i=0; i<files.length; i++){

    			String path= UploadUtil.fileUpload(filePath, files[i]);

				if( StringUtils.isNotBlank(path)){
        			list=getPeopleJobInfoByExcel(list,path);
    			}
    		}
    		if(list.size()>0){
        		flag=peopleJobMapper.insertByImport(list)>0;
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
    private List<PeopleJob> getPeopleJobInfoByExcel(List<PeopleJob> list,String path) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			//
			List<XSSFPictureData> pictureList = xwb.getAllPictures();

			XSSFRow row;
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				PeopleJob p = new PeopleJob();

				//人员类别
				if (row.getCell(1) == null || row.getCell(1).toString().trim().equals("")) {
					continue;
				}
				String job_catagary = row.getCell(1).toString().trim();
				p.setJob_category(job_catagary);

				//职级
				if (row.getCell(2) != null && !row.getCell(2).toString().trim().equals("")) {
					String Job_level = row.getCell(2).toString().trim();
					p.setJob_level(Job_level);
				}

				//岗位薪资
				if (row.getCell(3) != null && !row.getCell(3).toString().trim().equals("")) {
					String salary = row.getCell(3).toString().trim();
					p.setSalary(salary);
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
    	List list=peopleJobMapper.selectPeopleJobVoByIds(idList);
    	if(list!=null&&list.size()>0){
    		XSSFWorkbook workBook;
    		OutputStream os;
        	String newFileName="在编人员职级.xlsx";
    		try {
    			workBook = new XSSFWorkbook();
    			XSSFSheet sheet= workBook.createSheet("在编人员职级信息");
    			XSSFCellStyle setBorder=WordUtil.setCellStyle(workBook,true);
    			//创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleHeaders());

    			setBorder=WordUtil.setCellStyle(workBook,false);
        		for(int i=0;i<list.size();i++){
        			row=sheet.createRow(i+1);
        			PeopleJobVo p=(PeopleJobVo)list.get(i);
        			row.createCell(0).setCellValue(i+1);
        			row.createCell(1).setCellValue(p.getJob_category());
        			row.createCell(2).setCellValue(p.getJob_level());
        			row.createCell(3).setCellValue(p.getSalary().floatValue());

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
		PeopleJobVo p= peopleJobMapper.findPeopleJobVoById(Long.valueOf(id));
    	if(p!=null){
        	XWPFDocument doc;
    		OutputStream os;
        	String filePath=this.getClass().getResource("/template/custJobInfo.docx").getPath();
        	String newFileName="在编人员职级信息.docx";

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("${job_catagory}",p.getJob_category());
			params.put("${job_level}",p.getJob_level());
			params.put("${salary}",p.getSalary());


			WordUtil.OutputWord(response, filePath, newFileName, params);
    	}
    }


}

package com.wangzhixuan.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.People;
import com.wangzhixuan.service.PeopleService;
import com.wangzhixuan.utils.CustomXWPFDocument;
import com.wangzhixuan.utils.PageInfo;


@Service
public class PeopleServiceImpl implements PeopleService{

	private final String UPLOAD_PATH = "/static/upload/head/";
    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public People findPeopleById(Long id) {
        return peopleMapper.findPeopleById(id);
    }

    @Override
    public People findPeopleByName(String name) {
        return peopleMapper.findPeopleByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleMapper.findPeoplePageCondition(pageInfo));
        pageInfo.setTotal(peopleMapper.findPeoplePageCount(pageInfo));
    }

    @Override
    public void addPeople(People people,CommonsMultipartFile file) {
    	if(file!=null){//上传附件
    		if(fileUpLoad(people,file)){
    			peopleMapper.insert(people);
    		}
    	}else{
            peopleMapper.insert(people);
    	}
    }

    @Override
    public void updatePeople(People people, CommonsMultipartFile file) {
		if (file != null){
			if(fileUpLoad(people,file)){
				peopleMapper.insert(people);
			}
		}else{
			peopleMapper.updatePeople(people);
		}
    }

    @Override
    public void deletePeopleById(Long id) {
        peopleMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleByIds(String[] ids){
        peopleMapper.batchDeleteByIds(ids);
    }
    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
    	boolean flag=false;
    	if(files!=null&&files.length>0){
        	List<People> list=new ArrayList<People>();
    		for(int i=0;i<files.length;i++){
    			String path=fileUpload(files[i]);
    			if(path!=null&&path.length()>0){
        			list=getPeopleInfoByExcel(list,path);
    			}
    		}
    		if(list.size()>0){
        		flag=peopleMapper.insertByImport(list)>0;
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
    private List<People> getPeopleInfoByExcel(List<People> list,String path){
    	XSSFWorkbook xwb=null;
    	XSSFSheet sheet=null;
		try {
			xwb = new XSSFWorkbook(path);
	    	sheet = xwb.getSheetAt(0);
	    	XSSFRow row;
	    	for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
	    	    row = sheet.getRow(i);
	    	    People p=new People();
	    	    if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
	    			break;
	    		}
	    		String name=row.getCell(1).toString().trim();
	    		p.setName(name);
	    		if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
	    			String sex=row.getCell(2).toString().trim();
	    			p.setSex(sex.equals("女")?1:0);
	    		}
	    		if(row.getCell(3)!=null&&!row.getCell(3).toString().trim().equals("")){
	    			String birthday=row.getCell(3).toString().trim();
	    			p.setBirthday(birthday);
	    		}
	    		if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
	    			String job=row.getCell(4).toString().trim();
	    			p.setJob(job);
	    		}
	    		if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
	    			String salary=row.getCell(5).toString().trim();
	    			try {
						Double amount=Double.valueOf(salary);
		    			p.setSalary(amount);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
	    		}
	    		list.add(p);
	    	}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	return list;
    }
    //附件上传--批量导入
    private String fileUpload(CommonsMultipartFile file){
    	OutputStream os=null;
    	InputStream in=null;
		StringBuffer upLoadFilePath=new StringBuffer();
		try {
			String filePath=this.getClass().getResource("/").getPath();//文件临时路径
			String oldFileName = file.getOriginalFilename();//原始文件名称
			if(!file.isEmpty()){
				String fileFix=oldFileName.substring(oldFileName.lastIndexOf(".")+1);//文件后缀
				if(fileFix.equals("xlsx")){
					StringBuffer newFileName=new StringBuffer(UUID.randomUUID().toString().replaceAll("-", "")+"."+fileFix);//新文件名称
					upLoadFilePath=new StringBuffer(filePath+"/temporary");//上传附件路径
					File f = new File(upLoadFilePath.toString());
					if (!f.exists()) {
						f.mkdirs();
					}
					upLoadFilePath.append("/").append(newFileName);
					os = new FileOutputStream(upLoadFilePath.toString());
					//拿到上传文件的输入流
					in = file.getInputStream();
					//写入文件
					int b = 0;
					while((b=in.read()) != -1){
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
				}
			}
		}catch (IOException e) {
			
		}finally{
			
		}
		return upLoadFilePath.toString();
    }
    @Override
    public void exportExcel(HttpServletResponse response,String ids){
    	List list=peopleMapper.selectPeopleByIds(ids.split(","));
    	if(list!=null&&list.size()>0){
    		XSSFWorkbook workBook = null;
    		OutputStream os = null;
        	String newFileName="在编人员信息.xlsx";
    		try {
    			workBook = new XSSFWorkbook();
    			XSSFSheet sheet= workBook.createSheet("在编人员信息");
    			XSSFCellStyle setBorder=setCellStyle(workBook,true);
    			//创建表头
    			XSSFRow row=sheet.createRow(0);
    			row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
    			row.createCell(1).setCellValue("姓名");row.getCell(1).setCellStyle(setBorder);
    			row.createCell(2).setCellValue("性别");row.getCell(2).setCellStyle(setBorder);
    			row.createCell(3).setCellValue("出生日期");row.getCell(3).setCellStyle(setBorder);
    			row.createCell(4).setCellValue("工作");row.getCell(4).setCellStyle(setBorder);
    			row.createCell(5).setCellValue("薪水");row.getCell(5).setCellStyle(setBorder);
    			setBorder=setCellStyle(workBook,false);
        		for(int i=0;i<list.size();i++){
        			row=sheet.createRow(i+1);
        			People p=(People)list.get(i);
        			row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
        			row.createCell(1).setCellValue(p.getName());row.getCell(1).setCellStyle(setBorder);
        			row.createCell(2).setCellValue(p.getSex()==0?"男":"女");row.getCell(2).setCellStyle(setBorder);
        			row.createCell(3).setCellValue(p.getBirthday());row.getCell(3).setCellStyle(setBorder);
        			row.createCell(4).setCellValue(p.getJob());row.getCell(4).setCellStyle(setBorder);
        			row.createCell(5).setCellValue(p.getSalary());row.getCell(5).setCellStyle(setBorder);
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
    @Override
    public void exportWord(HttpServletResponse response,String id){
    	People p=peopleMapper.findPeopleById(Long.valueOf(id));
    	if(p!=null){
        	XWPFDocument doc = null;
    		OutputStream os = null;
        	String filePath=this.getClass().getResource("/template/custInfo.docx").getPath();
        	String newFileName="在编人员信息.docx";
    		try {
    			Map<String,Object> params = new HashMap<String,Object>();
                params.put("Ⅰ",p.getName());
                params.put("Ⅱ",p.getSex()==0?"男":"女");
                params.put("Ⅲ",p.getBirthday());
                params.put("Ⅳ",p.getJob());
                params.put("Ⅴ",p.getSalary()+"");
                //判断是否有头像
                if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                	String headPath=this.getClass().getResource("/").getPath();
                	File file=new File(headPath+p.getPhoto());
                	if(file.exists()){
                		headPath=headPath+p.getPhoto();
                		Map<String,Object> header = new HashMap<String, Object>();  
                        header.put("width", 100);  
                        header.put("height", 150);
                        header.put("type", "jpg");
                        header.put("content", inputStream2ByteArray(new FileInputStream(headPath), true));
                        params.put("Ⅵ",header);  
                	}
                }
                doc = generateWord(params, filePath);
    			response.reset();
    	        os = response.getOutputStream();
    	        response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
    			os.flush();
    			doc.write(os);
    		    os.close();
    		}catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    		}
    	}
    }
    /**
     * 单元格样式-excel
     * @param workBook
     * @return
     */
	private static XSSFCellStyle setCellStyle(XSSFWorkbook workBook,boolean blob){
		XSSFCellStyle setBorder = workBook.createCellStyle();
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中 
		XSSFFont font = workBook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);//设置字体大小
		if(blob){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		}
		setBorder.setFont(font);//选择需要用到的字体格式
		return setBorder;
	}
	/**
	 * 附件上传-头像
	 * @param people
	 * @param file
	 * @return
	 */
	private boolean fileUpLoad(People people,CommonsMultipartFile file){
		OutputStream os=null;
    	InputStream in=null;

		try {
			//获取头像上传路径
			String classPath = this.getClass().getResource("/").getPath();//路径
			String filePath = classPath.substring(0,classPath.lastIndexOf("WEB-INF")) ;

			String oldFileName = file.getOriginalFilename();//原始文件名称

			if(!file.isEmpty()){

				String fileExt=oldFileName.substring(oldFileName.lastIndexOf(".")+1);//文件后缀

				StringBuffer newFileName=new StringBuffer(UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt);//新文件名称
				StringBuffer upLoadFilePath=new StringBuffer(filePath + UPLOAD_PATH);//上传附件路径
				StringBuffer downLoadFilePath=new StringBuffer(UPLOAD_PATH);//下载附件路径
				File f = new File(upLoadFilePath.toString());
				if (!f.exists()) {
					f.mkdirs();
				}
				upLoadFilePath.append(newFileName);
				downLoadFilePath.append(newFileName);
				os = new FileOutputStream(upLoadFilePath.toString());
				//拿到上传文件的输入流
				in = file.getInputStream();
				//写入文件
				int b = 0;
				while((b=in.read()) != -1){
					os.write(b);
				}
				os.flush();
				os.close();
				in.close();
				people.setPhoto(downLoadFilePath.toString());
				return true;
			}
		}catch (IOException e) {
			return false;
		}finally{
			
		}
		return true;
	}
	/** 
     * 将输入流中的数据写入字节数组 
     * @param in 
     * @return 
     */  
    public static byte[] inputStream2ByteArray(InputStream in,boolean isClose){  
        byte[] byteArray = null;  
        try {  
            int total = in.available();  
            byteArray = new byte[total];  
            in.read(byteArray);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(isClose){  
                try {  
                    in.close();  
                } catch (Exception e2) {  
                    System.out.println("关闭流失败");  
                }  
            }  
        }  
        return byteArray;  
    }
    /**
     * 根据指定的参数值、模板，生成 word 文档
     * @param param 需要替换的变量
     * @param template 模板
     */
    private CustomXWPFDocument generateWord(Map<String, Object> param, String template) {
        CustomXWPFDocument doc = null;
        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);
            doc = new CustomXWPFDocument(pack);
            if (param != null && param.size() > 0) {
                //处理段落
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);
                //处理表格
                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    XWPFTable table = it.next();
                    int rcount = table.getNumberOfRows();
                    for(int i =0 ;i < rcount;i++){
                    	XWPFTableRow row = table.getRow(i);
                    	List<XWPFTableCell> cells =  row.getTableCells();
                    	for (XWPFTableCell cell : cells){
                            List<XWPFParagraph> paragraphListTable =  cell.getParagraphs();
                            processParagraphs(paragraphListTable, param, doc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    /** 
     * 处理段落
     * @param paragraphList
     */
    public  void processParagraphs(List<XWPFParagraph> paragraphList,Map<String, Object> param,CustomXWPFDocument doc){  
        if(paragraphList != null && paragraphList.size() > 0){  
            for(XWPFParagraph paragraph:paragraphList){  
                List<XWPFRun> runs = paragraph.getRuns();  
                for (XWPFRun run : runs) {  
                    String text = run.getText(0);  
                    if(text != null){  
                        boolean isSetText = false;  
                        for (Entry<String, Object> entry : param.entrySet()) {  
                            String key = entry.getKey();  
                            if(text.indexOf(key) != -1){  
                                isSetText = true;  
                                Object value = entry.getValue();  
                                if (value instanceof String) {//文本替换  
                                    text = text.replace(key, value.toString());  
                                } else if (value instanceof Map) {//图片替换  
                                    text = text.replace(key, "");  
                                    Map pic = (Map)value;  
                                    int width = Integer.parseInt(pic.get("width").toString());  
                                    int height = Integer.parseInt(pic.get("height").toString());  
                                    int picType = getPictureType(pic.get("type").toString());  
                                    byte[] byteArray = (byte[]) pic.get("content");  
                                    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);  
                                    try {
                                        int ind = doc.addPicture(byteInputStream,picType);  
                                        doc.createPicture(ind, width , height,paragraph);  
                                    } catch (Exception e) {  
                                        e.printStackTrace();  
                                    }
                                }
                            }
                        }
                    	if(text.equals("Ⅵ")&&!isSetText){
                    		run.setText("",0);
                    	}
                        if(isSetText){  
                            run.setText(text,0);  
                        }
                    }
                }
            }
        }
    }
    /**
     * 根据图片类型，取得对应的图片类型代码
     * @param picType
     * @return int
     */
    private int getPictureType(String picType){
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if(picType != null){
            if(picType.equalsIgnoreCase("png")){
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            }else if(picType.equalsIgnoreCase("dib")){
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            }else if(picType.equalsIgnoreCase("emf")){
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            }else if(picType.equalsIgnoreCase("wmf")){
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }
}

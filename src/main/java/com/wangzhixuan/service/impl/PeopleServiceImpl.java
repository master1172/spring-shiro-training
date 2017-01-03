package com.wangzhixuan.service.impl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.utils.*;
import com.wangzhixuan.vo.PeopleVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.*;
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

import static com.wangzhixuan.utils.WordUtil.generateWord;


@Service
public class PeopleServiceImpl implements PeopleService{

	@Autowired
    private PeopleMapper peopleMapper;

	@Autowired
	private DictMapper dictMapper;

    @Override
    public People findPeopleById(Long id) {
        return peopleMapper.findPeopleById(id);
    }

	@Override
	public PeopleVo findPeopleVoById(Long id){
		PeopleVo peopleVo = peopleMapper.findPeopleVoById(id);
		SplitFamilyInfo(peopleVo);
		return peopleVo;
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
    public void addPeople(PeopleVo peoplevo,CommonsMultipartFile file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		//当日期不为空，而是""的时候，需要修改为null，否则插入会有错误
		UpdatePeopleDate(peoplevo);

		People people = new People();
		BeanUtils.copyProperties(people,peoplevo);
		//将peoplevo里分散的familyInfo放入people实体中
		people.setCode(StringUtilExtra.generateUUID());
		UpdatePeopleFamilyInfo(peoplevo,people);
		if(file!=null){//上传附件
			//获取头像上传路径
			String filePath = StringUtilExtra.getPictureUploadPath();
			String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
    		if(StringUtils.isNotEmpty(uploadPath) ){
				people.setPhoto(uploadPath);
    			peopleMapper.insert(people);
    		}
    	}else{
            peopleMapper.insert(people);
    	}
    }

    @Override
    public void updatePeople(PeopleVo peoplevo, CommonsMultipartFile file) throws InvocationTargetException, IllegalAccessException {

		//当日期不为空，而是""的时候，需要修改为null，否则插入会有错误
		UpdatePeopleDate(peoplevo);
		People people = new People();
		BeanUtils.copyProperties(people,peoplevo);
		//将peoplevo里分散的familyInfo放入people实体中
		UpdatePeopleFamilyInfo(peoplevo,people);
		if (file != null){
			//获取头像上传路径
			String filePath = StringUtilExtra.getPictureUploadPath();
			String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
			if(StringUtils.isNotEmpty(uploadPath)){
				people.setPhoto(uploadPath);
				peopleMapper.updatePeople(people);
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
    	if(files!=null && files.length>0){

			List<People> list = new ArrayList<People>();

			String filePath = this.getClass().getResource("/").getPath();//文件临时路径

    		for(int i=0; i<files.length; i++){

    			String path= UploadUtil.fileUpload(filePath, files[i]);

				if( StringUtils.isNotBlank(path)){
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
		try
		{
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			XSSFSheet sheet = xwb.getSheetAt(0);
			//
			List<XSSFPictureData> pictureList = xwb.getAllPictures();

	    	XSSFRow row;
	    	for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
	    	    row = sheet.getRow(i);
	    	    People p=new People();

				p.setCode(StringUtilExtra.generateUUID());

				//将Excel中的图片插入到数据库中
				if (pictureList != null && pictureList.size() > 0){
					XSSFPictureData picture = pictureList.get(0);
					String filePath = StringUtilExtra.getPictureUploadPath();
					String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

					if (StringUtils.isNotBlank(uploadPath)){
						p.setPhoto(uploadPath);
					}
				}

				//姓名
				if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
	    			continue;
	    		}
	    		String name=row.getCell(1).toString().trim();
	    		p.setName(name);

				//性别
	    		if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
	    			String sex=row.getCell(2).toString().trim();
	    			p.setSex(sex.equals("女")?1:0);
	    		}

				//民族
				if (row.getCell(3)!=null && !row.getCell(3).toString().trim().equals("")){
					String nationalName = row.getCell(3).toString().trim();
					try{
						Integer nationalId = dictMapper.findNationalIdByName(nationalName);
						if (nationalId != null){
							p.setNationalId(nationalId);
						}
					}catch(Exception exp){

					}
				}

				//生日
	    		if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
	    			String birthday=row.getCell(4).toString().trim();
	    			p.setBirthday(birthday);
	    		}

				//籍贯
				if(row.getCell(5)!=null && !row.getCell(5).toString().trim().equals("")){
					String nativeName = row.getCell(5).toString().trim();
					try{
						Integer nativeId = dictMapper.findNativeIdByName(nativeName);
						if (nativeId != null){
							p.setNativeId(nativeId);
						}
					}catch (Exception exp){

					}
				}

				//学历
				if(row.getCell(6)!=null && !row.getCell(6).toString().trim().equals("")){
					String educationName = row.getCell(6).toString().trim();
					p.setEducationName(educationName);
				}

				//学位
				if(row.getCell(7) != null && !row.getCell(7).toString().trim().equals("")){
					String degreeName = row.getCell(7).toString().trim();

					try{
						Integer degreeId = dictMapper.findDegreeIdByName(degreeName);
						if (degreeId != null){
							p.setDegreeId(degreeId);
						}
					}catch(Exception exp){

					}
				}

				//政治面貌
				if(row.getCell(8) != null && !row.getCell(8).toString().trim().equals("")){
					String politicalName = row.getCell(8).toString().trim();
					p.setPoliticalName(politicalName);
				}

				//入党日期
				if(row.getCell(9) != null && !row.getCell(9).toString().trim().equals("")){
					String partyDate = row.getCell(9).toString().trim();
					p.setPartyDate(partyDate);
				}

				//参加工作日期
				if(row.getCell(10) != null && !row.getCell(10).toString().trim().equals("")){
					String workDate = row.getCell(10).toString().trim();
					p.setWorkDate(workDate);
				}

				//来院日期
				if(row.getCell(11) != null && !row.getCell(11).toString().trim().equals("")){
					String schoolDate = row.getCell(11).toString().trim();
					p.setSchoolDate(schoolDate);
				}

				//职务
				if(row.getCell(12) != null && !row.getCell(12).toString().trim().equals("")){
					String jobName = row.getCell(12).toString().trim();
					p.setJobName(jobName);
				}

				//人员类别
				if(row.getCell(13) != null && !row.getCell(13).toString().trim().equals("")){
					String jobCategory = row.getCell(13).toString().trim();
					p.setJobCategory(jobCategory);
				}

				//职级
				if(row.getCell(14) != null && !row.getCell(14).toString().trim().equals("")){
					String jobLevelName = row.getCell(14).toString().trim();

					Integer jobLevelId = dictMapper.findJobLevelIdByName(jobLevelName);
					if (jobLevelId != null)
						p.setJobLevelId(jobLevelId);
				}

				//现职务时间
				if(row.getCell(15) != null && !row.getCell(15).toString().trim().equals("")){
					String jobDate = row.getCell(15).toString().trim();
					p.setJobDate(jobDate);
				}

				//现职级时间
				if(row.getCell(16) != null && !row.getCell(16).toString().trim().equals("")){
					String jobLevelDate = row.getCell(16).toString().trim();
					p.setJobLevelDate(jobLevelDate);
				}

				//年龄
				if(row.getCell(17) != null && !row.getCell(17).toString().trim().equals("")){
					String age = row.getCell(17).toString().trim();
					try{
						p.setAge(Integer.valueOf(age));
					}catch (Exception exp){

					}
				}else{
					Integer age = DateUtil.GetAgeByBirthday(p.getBirthday());
					p.setAge(age);
				}

				//虚岁
				if(row.getCell(18) != null && !row.getCell(18).toString().trim().equals("")){
					String virtualAge = row.getCell(18).toString().trim();
					try{
						p.setVirtualAge(Integer.valueOf(virtualAge));
					}catch (Exception exp){

					}
				}else{
					Integer virtualAge = DateUtil.GetVirtualAgeByBirthday(p.getBirthday());
					p.setVirtualAge(virtualAge);
				}

				//工龄
				if(row.getCell(19) != null && !row.getCell(19).toString().trim().equals("")){
					String workAge = row.getCell(19).toString().trim();
					try{
						p.setWorkAge(Integer.valueOf(workAge));
					}catch(Exception exp){

					}
				}else{
					Integer workAge = DateUtil.GetWorkAgeByWorkDate(p.getWorkDate());
					p.setWorkAge(workAge);
				}

				//编制
				if(row.getCell(20) != null && !row.getCell(20).toString().trim().equals("")){
					String formation = row.getCell(20).toString().trim();
					p.setFormation(formation);
				}

				//手机号
				if(row.getCell(21) != null && !row.getCell(21).toString().trim().equals("")){
					String mobile = row.getCell(21).toString().trim();
					p.setMobile(mobile);
				}

				//婚姻状况
				if(row.getCell(22) != null && !row.getCell(22).toString().trim().equals("")){
					String marriageName = row.getCell(22).toString().trim();
					Integer marriageId = dictMapper.findMarriageIdByName(marriageName);
					if (marriageId != null){
						p.setMarriageId(marriageId);
					}
				}

				//身份证号码
				if(row.getCell(23) != null && !row.getCell(23).toString().trim().equals("")){
					String photoId = row.getCell(23).toString().trim();
					p.setPhotoId(photoId);
				}

				//现家庭住址
				if(row.getCell(24) != null && !row.getCell(24).toString().trim().equals("")){
					String address = row.getCell(24).toString().trim();
					p.setAddress(address);
				}

				//户籍
				if(row.getCell(25) != null && !row.getCell(25).toString().trim().equals("")){
					String hukou = row.getCell(25).toString().trim();
					p.setHukou(hukou);
				}

				//户籍地址
				if(row.getCell(26) != null && !row.getCell(26).toString().trim().equals("")){
					String hukouAddress = row.getCell(26).toString().trim();
					p.setHukouAddress(hukouAddress);
				}

				//最终学历
				if(row.getCell(27) != null && !row.getCell(27).toString().trim().equals("")){
					String finalEducationName = row.getCell(27).toString().trim();
					p.setFinalEducationName(finalEducationName);
				}

				//所学专业
				if(row.getCell(28) != null && !row.getCell(28).toString().trim().equals("")){
					String major = row.getCell(28).toString().trim();
					p.setMajor(major);
				}

				//毕业院校
				if(row.getCell(29) != null && !row.getCell(29).toString().trim().equals("")){
					String graduateSchool = row.getCell(29).toString().trim();
					p.setGraduateSchool(graduateSchool);
				}

				//紧急联系人
				if(row.getCell(30) != null && !row.getCell(30).toString().trim().equals("")){
					String contact = row.getCell(30).toString().trim();
					p.setContact(contact);
				}

				//与本人关系
				if(row.getCell(31) != null && !row.getCell(31).toString().trim().equals("")){
					String relationship = row.getCell(31).toString().trim();
					p.setRelationship(relationship);
				}

				//联系人电话
				if(row.getCell(32) != null && !row.getCell(32).toString().trim().equals("")){
					String contactNumber = row.getCell(32).toString().trim();
					p.setContactNumber(contactNumber);
				}

				//家庭成员信息1
				if(row.getCell(33) != null && !row.getCell(33).toString().trim().equals("")){
					String familyInfo1 = row.getCell(33).toString().trim();
					p.setFamilyInfo1(familyInfo1);
				}

				//家庭成员信息2
				if(row.getCell(34) != null && !row.getCell(34).toString().trim().equals("")){
					String familyInfo2 = row.getCell(34).toString().trim();
					p.setFamilyInfo2(familyInfo2);
				}

				//家庭成员信息3
				if(row.getCell(35) != null && !row.getCell(35).toString().trim().equals("")){
					String familyInfo3 = row.getCell(35).toString().trim();
					p.setFamilyInfo3(familyInfo3);
				}

				//家庭成员信息4
				if(row.getCell(36) != null && !row.getCell(36).toString().trim().equals("")){
					String familyInfo4 = row.getCell(36).toString().trim();
					p.setFamilyInfo4(familyInfo4);
				}

				//身份
				if (row.getCell(37) != null && !row.getCell(37).toString().trim().equals("")){
					String identityName = row.getCell(37).toString().trim();
					Integer identityId = dictMapper.findIdentityIdByName(identityName);
					p.setIdentityId(identityId);
				}

	    		list.add(p);
	    	}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	return list;
    }

	//导出excel
    @Override
    public void exportExcel(HttpServletResponse response,String[] idList){
    	List list=peopleMapper.selectPeopleVoByIds(idList);
    	if(list!=null&&list.size()>0){
    		XSSFWorkbook workBook;
    		OutputStream os;
        	String newFileName="在编人员信息.xlsx";
    		try {
    			workBook = new XSSFWorkbook();
    			XSSFSheet sheet= workBook.createSheet("在编人员信息");
    			XSSFCellStyle setBorder=WordUtil.setCellStyle(workBook,true);
    			//创建表头
				XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getPeopleHeaders());

    			setBorder=WordUtil.setCellStyle(workBook,false);
        		for(int i=0;i<list.size();i++){
        			row=sheet.createRow(i+1);
        			PeopleVo p=(PeopleVo)list.get(i);
        			row.createCell(0).setCellValue(i+1);
        			row.createCell(1).setCellValue(p.getName());
        			row.createCell(2).setCellValue(p.getSex()==null?"":(p.getSex()==0?"男":"女"));
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
					row.createCell(17).setCellValue(p.getAge()==null?"":p.getAge().toString());
					row.createCell(18).setCellValue(p.getVirtualAge()==null?"":p.getVirtualAge().toString());
					row.createCell(19).setCellValue(p.getWorkAge()==null?"":p.getWorkAge().toString());
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
		PeopleVo p= peopleMapper.findPeopleVoById(Long.valueOf(id));
    	if(p!=null){
        	XWPFDocument doc;
    		OutputStream os;
        	String filePath=this.getClass().getResource("/template/custInfo.docx").getPath();
        	String newFileName="在编人员信息.docx";

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("${name}",p.getName());
			params.put("${sex}",p.getSex()==0?"男":"女");
			params.put("${nationalName}",p.getNationalName());
			params.put("${birthday}",p.getBirthday());
			params.put("${nativeName}",p.getNativeName());
			params.put("${educationName}",p.getEducationName());
			params.put("${degreeName}",p.getDegreeName());
			params.put("${political}",p.getPoliticalName());
			params.put("${partyDate}",p.getPartyDate());
			params.put("${workDate}",p.getWorkDate());
			params.put("${schoolDate}",p.getSchoolDate());
			params.put("${jobName}",p.getJobName());
			params.put("${jobCategory}",p.getJobCategory());
			params.put("${jobLevelName}",p.getJobLevelName());
			params.put("${jobDate}",p.getJobDate());
			params.put("${age}",p.getAge()==null?"":p.getAge().toString());
			params.put("${virtualAge}",p.getVirtualAge()==null?"":p.getVirtualAge().toString());
			params.put("${workAge}",p.getWorkAge()==null?"":p.getWorkAge().toString());
			params.put("${formation}",p.getFormation());
			params.put("${mobile}",p.getMobile());
			params.put("${marriage}",p.getMarriageName());
			params.put("${photoId}",p.getPhotoId());
			params.put("${address}",p.getAddress());
			params.put("${hukou}",p.getHukou());
			params.put("${hukouAddress}",p.getHukouAddress());
			params.put("${finalEducationName}",p.getFinalEducationName());
			params.put("${major}",p.getMajor());
			params.put("${graduateSchool}",p.getGraduateSchool());
			params.put("${contact}",p.getContact());
			params.put("${relationship}",p.getRelationship());
			params.put("${number}",p.getContactNumber());

			params.put("${familyInfo1Title}",p.getFamilyInfo1Title());
			params.put("${familyInfo1Name}",p.getFamilyInfo1Name());
			params.put("${familyInfo1Job}",p.getFamilyInfo1Job());
			params.put("${familyInfo1Contact}",p.getFamilyInfo1Contact());

			params.put("${familyInfo2Title}",p.getFamilyInfo2Title());
			params.put("${familyInfo2Name}",p.getFamilyInfo2Name());
			params.put("${familyInfo2Job}",p.getFamilyInfo2Job());
			params.put("${familyInfo2Contact}",p.getFamilyInfo2Contact());

			params.put("${familyInfo3Title}",p.getFamilyInfo3Title());
			params.put("${familyInfo3Name}",p.getFamilyInfo3Name());
			params.put("${familyInfo3Job}",p.getFamilyInfo3Job());
			params.put("${familyInfo3Contact}",p.getFamilyInfo3Contact());

			params.put("${familyInfo4Title}",p.getFamilyInfo4Title());
			params.put("${familyInfo4Name}",p.getFamilyInfo4Name());
			params.put("${familyInfo4Job}",p.getFamilyInfo4Job());
			params.put("${familyInfo4Contact}",p.getFamilyInfo4Contact());

			params.put("${identityName}",p.getIdentityName());

			//判断是否有头像
			if(p.getPhoto()!=null&&p.getPhoto().length()>0){
				Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
				params.put("${photo}",header);
			}else{
				params.put("${photo}","");
			}

			WordUtil.OutputWord(response, filePath, newFileName, params);
    	}
    }

	@Override
	public String findPeopleIDsByCondition(PageInfo pageInfo) {
		String ids = "";
		pageInfo.setFrom(0);
		pageInfo.setSize(100000);
		List<PeopleVo> peopleList = peopleMapper.findPeoplePageCondition(pageInfo);
		if (peopleList == null || peopleList.size() < 1)
			return ids;


		for(int i=0; i<peopleList.size(); i++){
			ids = ids + peopleList.get(i).getId().toString() + ",";
		}

		//刪除最後一個逗号
		ids = ids.substring(0, ids.lastIndexOf(','));

		return ids;
	}

	private void UpdatePeopleDate(PeopleVo people) {
		if (people != null){
			if (StringUtils.isBlank(people.getBirthday())){
				people.setBirthday(null);
			}
			if (StringUtils.isBlank(people.getPartyDate())){
				people.setPartyDate(null);
			}
			if (StringUtils.isBlank(people.getWorkDate())){
				people.setWorkDate(null);
			}
			if (StringUtils.isBlank(people.getSchoolDate())){
				people.setSchoolDate(null);
			}
			if (StringUtils.isBlank(people.getJobDate())){
				people.setJobDate(null);
			}
			if (StringUtils.isBlank(people.getJobLevelDate())){
				people.setJobLevelDate(null);
			}
		}
	}

	private void SplitFamilyInfo(PeopleVo peopleVo){
		if (peopleVo == null)
			return;

		String familyInfo1 = peopleVo.getFamilyInfo1();
		String familyInfo2 = peopleVo.getFamilyInfo2();
		String familyInfo3 = peopleVo.getFamilyInfo3();
		String familyInfo4 = peopleVo.getFamilyInfo4();

		if (StringUtils.isNoneBlank(familyInfo1)){
			String[] familyInfo1List = familyInfo1.split("\\|");
			if (familyInfo1List != null){
				if (familyInfo1List.length > 0)
					peopleVo.setFamilyInfo1Title(familyInfo1List[0]);
				if (familyInfo1List.length > 1)
					peopleVo.setFamilyInfo1Name(familyInfo1List[1]);
				if (familyInfo1List.length > 2)
					peopleVo.setFamilyInfo1WorkAddress(familyInfo1List[2]);
				if (familyInfo1List.length > 3)
					peopleVo.setFamilyInfo1Job(familyInfo1List[3]);
				if (familyInfo1List.length > 4)
					peopleVo.setFamilyInfo1Contact(familyInfo1List[4]);
			}
		}

		if (StringUtils.isNoneBlank(familyInfo2)){
			String[] familyInfo2List = familyInfo2.split("\\|");
			if (familyInfo2List != null){
				if (familyInfo2List.length > 0)
					peopleVo.setFamilyInfo2Title(familyInfo2List[0]);
				if (familyInfo2List.length > 1)
					peopleVo.setFamilyInfo2Name(familyInfo2List[1]);
				if (familyInfo2List.length > 2)
					peopleVo.setFamilyInfo2WorkAddress(familyInfo2List[2]);
				if (familyInfo2List.length > 3)
					peopleVo.setFamilyInfo2Job(familyInfo2List[3]);
				if (familyInfo2List.length > 4)
					peopleVo.setFamilyInfo2Contact(familyInfo2List[4]);
			}
		}

		if (StringUtils.isNoneBlank(familyInfo3)){
			String[] familyInfo3List = familyInfo3.split("\\|");
			if (familyInfo3List != null && familyInfo3.length() > 4){
				if (familyInfo3List.length > 0)
					peopleVo.setFamilyInfo3Title(familyInfo3List[0]);
				if (familyInfo3List.length > 1)
					peopleVo.setFamilyInfo3Name(familyInfo3List[1]);
				if (familyInfo3List.length > 2)
					peopleVo.setFamilyInfo3WorkAddress(familyInfo3List[2]);
				if (familyInfo3List.length > 3)
					peopleVo.setFamilyInfo3Job(familyInfo3List[3]);
				if (familyInfo3List.length > 4)
					peopleVo.setFamilyInfo3Contact(familyInfo3List[4]);
			}
		}

		if (StringUtils.isNoneBlank(familyInfo4)){
			String[] familyInfo4List = familyInfo4.split("\\|");
			if (familyInfo4List != null && familyInfo4.length() > 4){
				if (familyInfo4List.length > 0)
					peopleVo.setFamilyInfo4Title(familyInfo4List[0]);
				if (familyInfo4List.length > 1)
					peopleVo.setFamilyInfo4Name(familyInfo4List[1]);
				if (familyInfo4List.length > 2)
					peopleVo.setFamilyInfo4WorkAddress(familyInfo4List[2]);
				if (familyInfo4List.length > 3)
					peopleVo.setFamilyInfo4Job(familyInfo4List[3]);
				if (familyInfo4List.length > 4)
					peopleVo.setFamilyInfo4Contact(familyInfo4List[4]);
			}
		}
	}

	private void UpdatePeopleFamilyInfo(PeopleVo peopleVo, People people){
		if (people == null || peopleVo == null)
			return;
		String familyInfo1 = peopleVo.getFamilyInfo1Title() + "|" + peopleVo.getFamilyInfo1Name() + "|" +
							 peopleVo.getFamilyInfo1WorkAddress() + "|" + peopleVo.getFamilyInfo1Job()   + "|" +
							 peopleVo.getFamilyInfo1Contact() ;

		String familyInfo2 = peopleVo.getFamilyInfo2Title()   + "|" + peopleVo.getFamilyInfo2Name() + "|" +
							 peopleVo.getFamilyInfo2WorkAddress()  + "|" + peopleVo.getFamilyInfo2Job() + "|" +
							 peopleVo.getFamilyInfo2Contact();

		String familyInfo3 = peopleVo.getFamilyInfo3Title()   + "|" + peopleVo.getFamilyInfo3Name() + "|" +
							 peopleVo.getFamilyInfo3WorkAddress()  + "|" + peopleVo.getFamilyInfo3Job() + "|" +
							 peopleVo.getFamilyInfo3Contact();

		String familyInfo4 = peopleVo.getFamilyInfo4Title()   + "|" + peopleVo.getFamilyInfo4Name() + "|" +
							 peopleVo.getFamilyInfo4WorkAddress()  + "|" + peopleVo.getFamilyInfo4Job() + "|" +
							 peopleVo.getFamilyInfo4Contact();

		people.setFamilyInfo1(familyInfo1);
		people.setFamilyInfo2(familyInfo2);
		people.setFamilyInfo3(familyInfo3);
		people.setFamilyInfo4(familyInfo4);
	}
}

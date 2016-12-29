package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.DictMapper;
import com.wangzhixuan.mapper.PeopleTransferMapper;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.service.PeopleTransferService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.StringUtilExtra;
import com.wangzhixuan.utils.UploadUtil;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.PeopleTransferVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
@Service
public class PeopleTransferServiceImpl implements PeopleTransferService{


    @Autowired
    private PeopleTransferMapper peopleTransferMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public PeopleTransfer findPeopleTransferById(Long id) {
        return peopleTransferMapper.findPeopleTransferById(id);
    }

    @Override
    public PeopleTransfer findPeopleTransferByName(String name) {
        return peopleTransferMapper.findPeopleTransferByName(name);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(peopleTransferMapper.findPeopleTransferPageCondition(pageInfo));
        pageInfo.setTotal(peopleTransferMapper.findPeopleTransferPageCount(pageInfo));
    }

    @Override
    public void addPeopleTransfer(PeopleTransfer peopleTransfer,CommonsMultipartFile file) {

        //当TransferDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleTransfer != null){
            if (StringUtils.isEmpty(peopleTransfer.getTransferDate())){
                peopleTransfer.setTransferDate(null);
            }
        }

        //当partyTransferDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleTransfer != null){
            if (StringUtils.isEmpty(peopleTransfer.getPartyTransferDate())){
                peopleTransfer.setPartyTransferDate(null);
            }
        }

        if(file!=null){//上传附件
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath) ){
                peopleTransferMapper.insert(peopleTransfer);
            }
        }else{
            peopleTransferMapper.insert(peopleTransfer);
        }
    }

    @Override
    public void updatePeopleTransfer(PeopleTransfer peopleTransfer, CommonsMultipartFile file) {

        //当TransferDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleTransfer != null){
            if (StringUtils.isEmpty(peopleTransfer.getTransferDate())){
                peopleTransfer.setTransferDate(null);
            }
        }

        //当partyTransferDate不为空，而是""的时候，需要修改为null，否则插入会有错误
        if (peopleTransfer != null){
            if (StringUtils.isEmpty(peopleTransfer.getPartyTransferDate())){
                peopleTransfer.setPartyTransferDate(null);
            }
        }


        if (file != null){
            //获取头像上传路径
            String filePath = StringUtilExtra.getPictureUploadPath();
            String uploadPath = UploadUtil.pictureUpLoad(filePath,file);
            if(StringUtils.isNotEmpty(uploadPath)){
                peopleTransferMapper.updatePeopleTransfer(peopleTransfer);
            }
        }else{
            peopleTransferMapper.updatePeopleTransfer(peopleTransfer);
        }
    }

    @Override
    public boolean insertByImport(CommonsMultipartFile[] files){
        boolean flag=false;
        if(files!=null && files.length>0){

            List<PeopleTransfer> list = new ArrayList<PeopleTransfer>();

            String filePath = this.getClass().getResource("/").getPath();//文件临时路径

            for(int i=0; i<files.length; i++){

                String path= UploadUtil.fileUpload(filePath, files[i]);

                if( StringUtils.isNotBlank(path)){
                    list=getPeopleTransferInfoByExcel(list,path);
                }
            }
            if(list.size()>0){
                flag=peopleTransferMapper.insertByImport(list)>0;
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
    private List<PeopleTransfer> getPeopleTransferInfoByExcel(List<PeopleTransfer> list,String path){
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //
            List<XSSFPictureData> pictureList = xwb.getAllPictures();

            XSSFRow row;
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                PeopleTransfer p=new PeopleTransfer();

                //将Excel中的图片插入到数据库中
                if (pictureList != null && pictureList.size() > 0){
                    XSSFPictureData picture = pictureList.get(0);
                    String filePath = StringUtilExtra.getPictureUploadPath();
                    String uploadPath = UploadUtil.pictureUpLoad(filePath,picture);

                    if (StringUtils.isNotBlank(uploadPath)){
                        p.setPhoto(uploadPath);
                    }
                }

                //人员编码
                if(row.getCell(1)==null||row.getCell(1).toString().trim().equals("")){
                    continue;
                }
                String peopleCode=row.getCell(1).toString().trim();
                p.setPeopleCode(peopleCode);

                //人员类型
                if(row.getCell(2)!=null&&!row.getCell(2).toString().trim().equals("")){
                    String peopleType=row.getCell(2).toString().trim();
                    p.setPeopleType(peopleType);
                }

                //调出前单位
                if(row.getCell(3)!=null&&!row.getCell(3).toString().trim().equals("")){
                    String fromSchool=row.getCell(3).toString().trim();
                    p.setFromSchool(fromSchool);
                }

                //调往单位
                if(row.getCell(4)!=null&&!row.getCell(4).toString().trim().equals("")){
                    String toSchool=row.getCell(4).toString().trim();
                    p.setToSchool(toSchool);
                }

                //调入调出日期
                if(row.getCell(5)!=null&&!row.getCell(5).toString().trim().equals("")){
                    String transferDate=row.getCell(5).toString().trim();
                    p.setTransferDate(transferDate);
                }

                //干部介绍信编号
                if(row.getCell(6)!=null&&!row.getCell(6).toString().trim().equals("")){
                    String refLetterNo=row.getCell(6).toString().trim();
                    p.setRefLetterNo(refLetterNo);
                }

                //工资止薪日期
                if(row.getCell(7)!=null&&!row.getCell(7).toString().trim().equals("")){
                    String salaryEndDate=row.getCell(7).toString().trim();
                    p.setSalaryEndDate(salaryEndDate);
                }

                //党组织转接日期
                if(row.getCell(8)!=null&&!row.getCell(8).toString().trim().equals("")){
                    String partyTransferDate=row.getCell(8).toString().trim();
                    p.setPartyTransferDate(partyTransferDate);
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
        List list=peopleTransferMapper.selectPeopleTransferVoByIds(idList);
        if(list!=null&&list.size()>0){
            XSSFWorkbook workBook;
            OutputStream os;
            String newFileName="调动人员信息.xlsx";
            try {
                workBook = new XSSFWorkbook();
                XSSFSheet sheet= workBook.createSheet("调动人员信息");
                XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
                //创建表头
                XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("序号");row.getCell(0).setCellStyle(setBorder);
                row.createCell(1).setCellValue("人员编码");row.getCell(1).setCellStyle(setBorder);
                row.createCell(2).setCellValue("人员类型");row.getCell(2).setCellStyle(setBorder);
                row.createCell(3).setCellValue("调出前单位");row.getCell(3).setCellStyle(setBorder);
                row.createCell(4).setCellValue("调往单位");row.getCell(4).setCellStyle(setBorder);
                row.createCell(5).setCellValue("调入调出日期");row.getCell(5).setCellStyle(setBorder);
                row.createCell(6).setCellValue("干部介绍信编号");row.getCell(6).setCellStyle(setBorder);
                row.createCell(7).setCellValue("工资止薪日期");row.getCell(7).setCellStyle(setBorder);
                row.createCell(8).setCellValue("党组织转接日期");row.getCell(8).setCellStyle(setBorder);
                setBorder=WordUtil.setCellStyle(workBook,false);
                for(int i=0;i<list.size();i++){
                    row=sheet.createRow(i+1);
                    PeopleTransferVo p=(PeopleTransferVo)list.get(i);
                    row.createCell(0).setCellValue(i+1);row.getCell(0).setCellStyle(setBorder);
                    row.createCell(1).setCellValue(p.getPeopleCode());row.getCell(1).setCellStyle(setBorder);
                    row.createCell(2).setCellValue(p.getPeopleType());row.getCell(2).setCellStyle(setBorder);
                    row.createCell(3).setCellValue(p.getFromSchool());row.getCell(3).setCellStyle(setBorder);
                    row.createCell(4).setCellValue(p.getToSchool());row.getCell(4).setCellStyle(setBorder);
                    row.createCell(5).setCellValue(p.getTransferDate());row.getCell(5).setCellStyle(setBorder);
                    row.createCell(6).setCellValue(p.getRefLetterNo());row.getCell(6).setCellStyle(setBorder);
                    row.createCell(7).setCellValue(p.getSalaryEndDate());row.getCell(7).setCellStyle(setBorder);
                    row.createCell(8).setCellValue(p.getPartyTransferDate());row.getCell(8).setCellStyle(setBorder);
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
        PeopleTransferVo p= peopleTransferMapper.findPeopleTransferVoById(Long.valueOf(id));
        if(p!=null){
            XWPFDocument doc;
            OutputStream os;
            String filePath=this.getClass().getResource("/template/custInfoTransfer.docx").getPath();
            String newFileName="退休人员信息.docx";

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("${peopleCode}",p.getPeopleCode());
            params.put("${peopleType}",p.getPeopleType());
            params.put("${fromSchool}",p.getFromSchool());
            params.put("${toSchool}",p.getToSchool());
            params.put("${transferDate}",p.getTransferDate());
            params.put("${refLetterNo}",p.getRefLetterNo());
            params.put("${salaryEndDate}",p.getSalaryEndDate());
            params.put("${partyTransferDate}",p.getPartyTransferDate());

            //判断是否有头像
            if(p.getPhoto()!=null&&p.getPhoto().length()>0){
                Map<String, Object> header = WordUtil.PutPhotoIntoWordParameter(p.getPhoto());
                params.put("${photo}",header);
            }

            WordUtil.OutputWord(response, filePath, newFileName, params);
        }
    }

    @Override
    public String findPeopleTransferIDsByCondition(PageInfo pageInfo) {
        String ids = "";
        pageInfo.setFrom(0);
        pageInfo.setSize(100000);
        List<PeopleTransferVo> peopleTransferList = peopleTransferMapper.findPeopleTransferPageCondition(pageInfo);
        if (peopleTransferList == null || peopleTransferList.size() < 1)
            return ids;


        for(int i=0; i<peopleTransferList.size(); i++){
            ids = ids + peopleTransferList.get(i).getId().toString() + ",";
        }

        //刪除最後一個逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        return ids;
    }
}



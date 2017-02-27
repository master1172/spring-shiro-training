package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.mapper.PeopleTotalMapper;
import com.wangzhixuan.mapper.SocialSecurityMapper;
import com.wangzhixuan.model.PeopleTotal;
import com.wangzhixuan.model.SocialSecurity;
import com.wangzhixuan.model.SocialSecurityBase;
import com.wangzhixuan.service.SocialSecurityService;
import com.wangzhixuan.utils.ConstUtil;
import com.wangzhixuan.utils.ExcelUtil;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.WordUtil;
import com.wangzhixuan.vo.ExamMonthlyVo;
import com.wangzhixuan.vo.PeopleVo;
import com.wangzhixuan.vo.SocialSecurityVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by sterm on 2017/2/26.
 */
@Service
public class SocialSecurityServiceImpl implements SocialSecurityService {

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Autowired
    private PeopleTotalMapper peopleTotalMapper;

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(socialSecurityMapper.findPageCondition(pageInfo));
        pageInfo.setTotal(socialSecurityMapper.findCount(pageInfo));
    }

    @Override
    public void findSocialSecurityGrid(PageInfo pageInfo) {
        pageInfo.setRows(socialSecurityMapper.findSocialSecurityPageCondition(pageInfo));
        pageInfo.setTotal(socialSecurityMapper.findSocialSecurityCount(pageInfo));
    }

    @Override
    public void updateBase(SocialSecurityBase socialSecurityBase) {
        if (socialSecurityBase == null)
            return;
        if (StringUtils.isBlank(socialSecurityBase.getCode()))
            return;
        if (socialSecurityBase.getId() == null)
            return;

        Integer id = socialSecurityBase.getId();

        PeopleTotal peopleTotal = peopleTotalMapper.selectByPrimaryKey(id);

        if (peopleTotal != null){
            peopleTotal.setLifeInsuranceBase(socialSecurityBase.getLifeInsuranceBase());
            peopleTotal.setJobInsuranceBase(socialSecurityBase.getJobInsuranceBase());
            peopleTotal.setWoundInsuranceBase(socialSecurityBase.getWoundInsuranceBase());
            peopleTotal.setBirthInsuranceBase(socialSecurityBase.getBirthInsuranceBase());
            peopleTotal.setHealthInsuranceBase(socialSecurityBase.getHealthInsuranceBase());
            peopleTotal.setAnnuityBase(socialSecurityBase.getAnnuityBase());
        }

        peopleTotalMapper.updateByPrimaryKeySelective(peopleTotal);
    }

    @Override
    public void insert(SocialSecurity socialSecurity) {
        if (socialSecurity == null)
            return;
        if (StringUtils.isBlank(socialSecurity.getPayDate()))
            socialSecurity.setPayDate(null);
        socialSecurityMapper.insert(socialSecurity);
    }

    @Override
    public void update(SocialSecurity socialSecurity) {
        if (socialSecurity == null)
            return;
        if (StringUtils.isBlank(socialSecurity.getPayDate()))
            socialSecurity.setPayDate(null);

        socialSecurityMapper.update(socialSecurity);
    }

    @Override
    public void delete(Integer id) {
        if (id == null)
            return;
        socialSecurityMapper.delete(id);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String[] ids) {
        List<PeopleVo> list = peopleMapper.selectPeopleVoByIds(ids);

        XSSFWorkbook workBook;
        OutputStream os;
        String newFileName="社保信息.xlsx";
        try {
            workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("社保信息");
            XSSFCellStyle setBorder= WordUtil.setCellStyle(workBook,true);
            //创建表头
            XSSFRow row = ExcelUtil.CreateExcelHeader(sheet, setBorder, ConstUtil.getSocialSecurityHeader());

            int count = 0;

            setBorder=WordUtil.setCellStyle(workBook,false);

            for(int i=0;i<list.size();i++) {
                PeopleVo peopleVo = list.get(i);
                if (peopleVo == null || StringUtils.isBlank(peopleVo.getCode()))
                    continue;
                String peopleCode = peopleVo.getCode();

                List<SocialSecurityVo> socialSecurityVoList = socialSecurityMapper.findSocialSecurityVoListByCode(peopleCode);

                if (socialSecurityVoList == null || socialSecurityVoList.size() < 1)
                    continue;

                for(int j=0; j<socialSecurityVoList.size(); j++){
                    row = sheet.createRow(count+1);
                    SocialSecurityVo socialSecurityVo = socialSecurityVoList.get(j);
                    row.createCell(0).setCellValue(count+1);
                    row.createCell(1).setCellValue(socialSecurityVo.getPeopleName());
                    row.createCell(2).setCellValue(socialSecurityVo.getLifeInsuranceBase() == null?"":socialSecurityVo.getLifeInsuranceBase().toString());
                    row.createCell(3).setCellValue(socialSecurityVo.getLifeInsuranceSchool() == null?"":socialSecurityVo.getLifeInsuranceSchool().toString());
                    row.createCell(4).setCellValue(socialSecurityVo.getLifeInsurancePeople() == null?"":socialSecurityVo.getLifeInsurancePeople().toString());
                    row.createCell(5).setCellValue(socialSecurityVo.getJobInsuranceBase() == null?"":socialSecurityVo.getJobInsuranceBase().toString());
                    row.createCell(6).setCellValue(socialSecurityVo.getJobInsuranceSchool() == null?"":socialSecurityVo.getJobInsuranceSchool().toString());
                    row.createCell(7).setCellValue(socialSecurityVo.getJobInsurancePeople() == null?"":socialSecurityVo.getJobInsurancePeople().toString());
                    row.createCell(8).setCellValue(socialSecurityVo.getWoundInsuranceBase() == null?"":socialSecurityVo.getWoundInsuranceBase().toString());
                    row.createCell(9).setCellValue(socialSecurityVo.getWoundInsuranceSchool() == null?"":socialSecurityVo.getWoundInsuranceSchool().toString());
                    row.createCell(10).setCellValue(socialSecurityVo.getBirthInsuranceBase() == null?"":socialSecurityVo.getBirthInsuranceBase().toString());
                    row.createCell(11).setCellValue(socialSecurityVo.getBirthInsuranceSchool() == null?"":socialSecurityVo.getBirthInsuranceSchool().toString());
                    row.createCell(12).setCellValue(socialSecurityVo.getHealthInsuranceBase() == null?"":socialSecurityVo.getHealthInsuranceBase().toString());
                    row.createCell(13).setCellValue(socialSecurityVo.getHealthInsuranceSchool() == null?"":socialSecurityVo.getHealthInsuranceSchool().toString());
                    row.createCell(14).setCellValue(socialSecurityVo.getHealthInsurancePeople() == null?"":socialSecurityVo.getHealthInsurancePeople().toString());
                    row.createCell(15).setCellValue(socialSecurityVo.getAnnuityBase() == null? "":socialSecurityVo.getAnnuityBase().toString());
                    row.createCell(16).setCellValue(socialSecurityVo.getAnnuitySchool() == null?"":socialSecurityVo.getAnnuitySchool().toString());
                    row.createCell(17).setCellValue(socialSecurityVo.getAnnuityPeople() == null?"":socialSecurityVo.getAnnuityPeople().toString());
                    row.createCell(18).setCellValue(socialSecurityVo.getPayDate());
                    count++;

                    for(int k=0; k<19; k++){
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
    public SocialSecurity findSocialSecurityById(Integer id) {
        if (id == null)
            return null;
        return socialSecurityMapper.findSocialSecurityById(id);
    }
}

package com.wangzhixuan.utils;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by sterm on 2016/12/5.
 */
public class ExcelUtil {

    public static XSSFRow CreateExcelHeader(XSSFSheet sheet, XSSFCellStyle setBorder,String[] headerList) {

        XSSFRow row=sheet.createRow(0);

        for(int i=0; i<headerList.length; i++){
            row.createCell(i).setCellValue(headerList[i]);
            row.getCell(i).setCellStyle(setBorder);
        }

        return row;
    }
}

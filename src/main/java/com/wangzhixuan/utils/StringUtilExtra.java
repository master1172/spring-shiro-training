package com.wangzhixuan.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class StringUtilExtra {
    public static String Date2String(Date date){
        if (date == null)
            return "";

        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        }catch(Exception exp){
            return "";
        }
    }

    public static String getPictureUploadPath(){
        String classPath = StringUtilExtra.class.getClass().getResource("/").getPath();//路径
        String filePath = classPath.substring(0,classPath.lastIndexOf("WEB-INF")) ;

        return filePath;
    }

    public static boolean isBlank(XSSFCell cell){
        if (cell == null)
            return true;

        String cellValue = cell.toString();
        return StringUtils.isBlank(cellValue);
    }
}

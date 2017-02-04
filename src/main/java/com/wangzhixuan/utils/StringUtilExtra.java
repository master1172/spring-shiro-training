package com.wangzhixuan.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class StringUtilExtra {


    public static Long StringToLong(String value){
        if (StringUtils.isBlank(value))
            return Long.valueOf(-1);

        try{
            return Long.valueOf(value);
        }catch (Exception exp){
            return Long.valueOf(-1);
        }
    }

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
        String classPath = StringUtilExtra.class.getResource("/").getPath();//路径
        String filePath = classPath.substring(0,classPath.lastIndexOf("WEB-INF")) ;

        return filePath;
    }

    public static boolean isBlank(XSSFCell cell){
        if (cell == null)
            return true;

        String cellValue = cell.toString();
        return StringUtils.isBlank(cellValue);
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static BigDecimal StringToDecimal(String str){
        if (StringUtils.isBlank(str))
                return null;
        BigDecimal output = new BigDecimal(str);
        return output;

    }

    public static String DecimalToString(BigDecimal decimal){
        if (decimal == null)
            return null;

        try{
            return decimal.toString();
        }catch (Exception exp){
            return null;
        }
    }
}

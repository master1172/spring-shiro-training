package com.wangzhixuan.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sterm on 2016/12/2.
 */
public class DateUtil {
    public static Integer GetAgeByBirthday(String birthday){
        if (StringUtils.isBlank(birthday))
            return null;

        try{
            LocalDate now = new LocalDate();
            String birthYear  = birthday.substring(0,4);
            String birthMonth = birthday.substring(5,7);
            String birthDate  = birthday.substring(8);

            LocalDate localBirthday = new LocalDate(birthYear,birthMonth,birthDate);

            Years age =  Years.yearsBetween(localBirthday,now);
            return age.getYears();
        }catch (Exception exp){
            return null;
        }

    }

    public static Integer GetVirtualAgeByBirthday(String birthday){
        if (StringUtils.isBlank(birthday))
            return null;

        try{
            LocalDate now = new LocalDate();
            String birthYear  = birthday.substring(0,4);

            LocalDate localBirthday = new LocalDate(birthYear,1,1);

            Years age =  Years.yearsBetween(localBirthday,now);
            return age.getYears();
        }catch (Exception exp){
            return null;
        }
    }

    public static Integer GetWorkAgeByWorkDate(String workDate){
        if (StringUtils.isBlank(workDate))
            return null;

        try{
            Integer currentYear   = Calendar.getInstance().get(Calendar.YEAR);
            Integer startWorkYear = Integer.valueOf(workDate.substring(0,4));
            return currentYear - startWorkYear + 1;
        }catch (Exception exp){
            return null;
        }
    }
}

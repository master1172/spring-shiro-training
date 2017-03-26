package com.wangzhixuan.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sterm on 2016/12/2.
 */
public class DateUtil {


    public static boolean IsSprintFestivalPrevMonth(){
        if (GetCurrentMonth() == 1)
            return true;
        return false;
    }

    public static String GetCurrentYearAndMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());
        try{
            return today.substring(0,7);
        }catch (Exception exp){
            return "2017-01";
        }
    }

    public static String GetTodayInWord(){
        String today = GetToday();
        String year = GetYear(today);
        String month = GetMonth(today);
        String day = GetDay(today);

        return year + " 年 " + month + " 月 " + day + " 日";
    }

    public static String GetDayMinus(String date, Integer shift){

        if (StringUtils.isBlank(date) || shift == null)
            return GetToday();

        try{
            Date date1 = new Date(date);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date1);
            gc.add(5,shift);
            return gc.toString();
        }catch (Exception exp){
            return GetToday();
        }
    }

    public static String GetToday(){
        return GetDate(new Date());
    }

    public static String GetDate(Date date){

        if (date == null)
            return "";
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        }catch (Exception exp){
            return "";
        }
    }

    public static String GetYear(String date){
        try{
            return date.substring(0,4);
        }catch (Exception exp){
            return "";
        }
    }

    public static String GetMonth(String date){
        try{
           return date.substring(5,7);
        }catch (Exception exp){
            return "";
        }
    }

    public static String GetDay(String date){
        try{
            return date.substring(8);
        }catch (Exception exp){
            return "";
        }
    }

    public static Integer GetAgeByBirthday(String birthday){
        if (StringUtils.isBlank(birthday))
            return null;

        try{
            LocalDate now = new LocalDate();
            String birthYear  = birthday.substring(0,4);
            String birthMonth = birthday.substring(5,7);
            String birthDate  = birthday.substring(8);

            LocalDate localBirthday = new LocalDate(Integer.valueOf(birthYear),Integer.valueOf(birthMonth),Integer.valueOf(birthDate));

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
            LocalDate localBirthday = new LocalDate(Integer.valueOf(birthYear),1,1);

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

    public static Integer GetCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static Integer GetCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    public static String GetFirstDayOfSelectMonth(String date){
        return date + "-01";
    }

    public static String GetLastDayOfSelectMonth(String date){
        return date + "-31";
    }

    public static String GetFirstDayOfCurrentMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar   today = Calendar.getInstance();//获取当前日期
        today.add(Calendar.MONTH, 0);
        today.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return format.format(today.getTime());
    }

    public static String GetLastDayOfCurrentMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, today.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(today.getTime());
    }

    public static String GetCurrnetYearAndMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar today = Calendar.getInstance().getInstance();
        return format.format(today.getTime());
    }

    //
    public static boolean lessThan(String partyDate, String secondDate){
        if (StringUtils.isBlank(partyDate))
            return false;
        if (StringUtils.isBlank(secondDate))
            return false;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date party = dateFormat.parse(partyDate);
            Date second = dateFormat.parse(secondDate);

            return party.getTime() <= second.getTime();

        }catch (Exception exp){
            return false;
        }

    }

    public static boolean greaterThan(String partyDate, String secondDate){
        if (StringUtils.isBlank(partyDate))
            return false;
        if (StringUtils.isBlank(secondDate))
            return false;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date party = dateFormat.parse(partyDate);
            Date second = dateFormat.parse(secondDate);

            return party.getTime() >= second.getTime();

        }catch (Exception exp){
            return false;
        }
    }

    public static boolean inRange(String partyDate, String firstDate, String secondDate) {
        if (StringUtils.isBlank(partyDate))
            return false;
        if (StringUtils.isBlank(secondDate))
            return false;
        if (StringUtils.isBlank(firstDate))
            return false;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date party = dateFormat.parse(partyDate);
            Date first  = dateFormat.parse(firstDate);
            Date second = dateFormat.parse(secondDate);

            return (party.getTime() >= first.getTime()) && (party.getTime() <= second.getTime());

        }catch (Exception exp){
            return false;
        }
    }

    public static boolean CompareTwoDate(String checkYearAndMonth, int date, String resultDate) {
        if (StringUtils.isBlank(checkYearAndMonth))
            return false;

        if (StringUtils.isBlank(resultDate))
            return false;

        String resultYear = GetYear(resultDate);
        String resultMonth = GetMonth(resultDate);
        String resultDay   = GetDay(resultDate);

        String checkYear = GetYear(checkYearAndMonth);
        String checkMonth = GetMonth(checkYearAndMonth);

        if (resultYear != checkYear)
            return false;

        if (resultMonth != checkMonth)
            return false;

        if (Integer.valueOf(resultDay).intValue() !=  date)
            return false;

        return true;
    }

    public static String GetDateByDay(Integer j) {
        if (j == null)
            return null;

        if (j < 10)
            return "0" + j.toString();

        return j.toString();
    }
}

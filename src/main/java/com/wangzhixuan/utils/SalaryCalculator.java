package com.wangzhixuan.utils;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleSalaryBase;
import com.wangzhixuan.model.PeopleTimesheet;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sterm on 2017/3/29.
 */
public class SalaryCalculator {

    public static BigDecimal PeopleSalaryCalculateTrafficAllowance(PeopleSalary peopleSalary){
        if (peopleSalary == null)
            return new BigDecimal(0.00);

        BigDecimal sumVacationPeriod = peopleSalary.getTimesheetStatus();

        if (sumVacationPeriod != null){
            Double trafficAllowance = 300.0 - 300 / 21.75 * sumVacationPeriod.doubleValue();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            peopleSalary.setTrafficAllowance(new BigDecimal(decimalFormat.format(trafficAllowance)));
        }else{
            peopleSalary.setTrafficAllowance(new BigDecimal(300.00));
        }

        return peopleSalary.getTrafficAllowance();
    }

    public static BigDecimal PeopleSalaryCalculateTemperatureAllowance(PeopleSalary peopleSalary){

        if (peopleSalary == null)
            return new BigDecimal(0.00);

        BigDecimal sumVacationPeriod = peopleSalary.getTimesheetStatus();

        if (sumVacationPeriod != null){
            Double temperatureAllowance = 100 - 100 / 21.75 * sumVacationPeriod.doubleValue();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            peopleSalary.setTemperatureAllowance(new BigDecimal(decimalFormat.format(temperatureAllowance)));
        }else{
            peopleSalary.setTemperatureAllowance(new BigDecimal(100.00));
        }

        return peopleSalary.getTemperatureAllowance();
    }


    public static BigDecimal GetBonusByYearlyExamResult(String examYearlyResult, PeopleSalaryBase peopleSalaryBase){

        BigDecimal yearlyBonus = new BigDecimal(0.00);

        if (peopleSalaryBase == null)
            return yearlyBonus;

        if (StringUtils.isNoneBlank(examYearlyResult)){
            if (DateUtil.IsSprintFestivalPrevMonth()){
                if (examYearlyResult.equals(ConstUtil.EXCELENT) || examYearlyResult.equals(ConstUtil.AVERAGE)){
                    if (peopleSalaryBase.getYearlyBonus() != null){
                        yearlyBonus = peopleSalaryBase.getYearlyBonus();
                    }
                }
            }
        }

        return yearlyBonus;
    }

    public static BigDecimal GetPerformanceTotalByMonthlyExamResult(PeopleSalary peopleSalary){

        if (peopleSalary == null){
            return new BigDecimal(0.00);
        }

        BigDecimal performanceAllowance = peopleSalary.getPerformanceAllowance();

        String examResult = peopleSalary.getExamResult();

        BigDecimal performanceAllowanceTotal = new BigDecimal(0.00);

        if (StringUtils.isNoneBlank(examResult) && performanceAllowance != null){
            if (examResult.equals("A")){
                performanceAllowanceTotal = performanceAllowance;
            }
            if (examResult.equals("B")){
                performanceAllowanceTotal = performanceAllowance.multiply(new BigDecimal(0.8));
            }
            if (examResult.equals("C")){
                performanceAllowanceTotal = performanceAllowance.multiply(new BigDecimal(0.5));
            }
            if (examResult.equals("D")){
                performanceAllowanceTotal = performanceAllowance.multiply(new BigDecimal(0.2));
            }
            if (examResult.equals("E")){
                performanceAllowanceTotal = performanceAllowance.multiply(new BigDecimal(0.0));
            }
        }

        peopleSalary.setPerformanceAllowanceTotal(performanceAllowanceTotal);

        return performanceAllowanceTotal;
    }

    public static boolean PeopleSalaryCalculator(PeopleSalary peopleSalary){
        if (peopleSalary == null){
            return false;
        }

        BigDecimal grossIncome = new BigDecimal(0.00);

        try{
            if (peopleSalary.getJobSalary() != null)
                grossIncome = grossIncome.add(peopleSalary.getJobSalary());

            if (peopleSalary.getRankSalary() != null)
                grossIncome = grossIncome.add(peopleSalary.getRankSalary());

            if (peopleSalary.getReserveSalary() != null)
                grossIncome = grossIncome.add(peopleSalary.getReserveSalary());

            if (peopleSalary.getPerformanceAllowanceTotal() != null){
                grossIncome = grossIncome.add(peopleSalary.getPerformanceAllowanceTotal());
            }else{
                BigDecimal performanceAllowanceTotal = GetPerformanceTotalByMonthlyExamResult(peopleSalary);
                grossIncome = grossIncome.add(performanceAllowanceTotal);
            }

            if (peopleSalary.getJobAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getJobAllowance());

            if (peopleSalary.getRentAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getRentAllowance());

            if (peopleSalary.getHouseAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getHouseAllowance());

            if (peopleSalary.getDutyAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getDutyAllowance());

            if (peopleSalary.getExtraAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getExtraAllowance());

            if (peopleSalary.getTelephoneAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getTelephoneAllowance());

            if (peopleSalary.getTrafficAllowance() != null){
                grossIncome = grossIncome.add(peopleSalary.getTrafficAllowance());
            }

            if (peopleSalary.getOnDutyFee() != null && peopleSalary.getOnDutyDate() != null){
                grossIncome = grossIncome.add(peopleSalary.getOnDutyFee().multiply(peopleSalary.getOnDutyDate()));
            }

            if (peopleSalary.getPropertyAllowance() != null){
                grossIncome = grossIncome.add(peopleSalary.getPropertyAllowance());
            }

            if (peopleSalary.getExtraJobAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getExtraJobAllowance());

            if (peopleSalary.getTemperatureAllowance() != null)
                grossIncome = grossIncome.add(peopleSalary.getTemperatureAllowance());

            peopleSalary.setGrossSalary(grossIncome);

        }catch (Exception exp){
            peopleSalary.setGrossSalary(new BigDecimal(0.00));
            return false;
        }

        return true;
    }
}

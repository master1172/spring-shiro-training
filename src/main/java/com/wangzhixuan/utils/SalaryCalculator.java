package com.wangzhixuan.utils;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.model.PeopleTimesheet;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sterm on 2017/3/29.
 */
public class SalaryCalculator {

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

            if (peopleSalary.getPerformanceAllowance() != null){
                if (StringUtils.isNoneBlank(peopleSalary.getExamResult())){
                    BigDecimal performanceTotal = peopleSalary.getPerformanceAllowance();
                    if (peopleSalary.getExamResult().equals("A")){
                        performanceTotal = performanceTotal.multiply(new BigDecimal(1.0));
                    }else if (peopleSalary.getExamResult().equals("B")){
                        performanceTotal = performanceTotal.multiply(new BigDecimal(0.8));
                    }else if (peopleSalary.getExamResult().equals("C")){
                        performanceTotal = performanceTotal.multiply(new BigDecimal(0.5));
                    }else if (peopleSalary.getExamResult().equals("C")){
                        performanceTotal = performanceTotal.multiply(new BigDecimal(0.2));
                    }else if (peopleSalary.getExamResult().equals("C")){
                        performanceTotal = new BigDecimal(0.00);
                    }else{
                        performanceTotal = new BigDecimal(0.00);
                    }
                    grossIncome = grossIncome.add(performanceTotal);
                }
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

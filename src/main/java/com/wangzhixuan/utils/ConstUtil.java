package com.wangzhixuan.utils;

/**
 * Created by sterm on 2016/12/5.
 */
public class ConstUtil {
	
	
	public static String[] getTimesheetHeader(){
        return new  String[]{
                "序号","人员姓名","请假日期","请假原因","假期长度"
        };
    }
	
    public static String[] getPeopleHeaders(){
        return new  String[]{
                "序号","姓名","性别","民族","出生日期","籍贯","学历","学位","政治面貌","入党日期","参加工作日期",
                "来院日期","职务","人员类别","职级","任现职日期","任现职级日期","年龄","年龄(虚岁)","工龄",
                "编制","手机号","婚姻状况","身份证号码","现家庭住址","户籍","户籍地址","最终学历","所学专业",
                "毕业院校","紧急联系人","与本人关系","联系人电话","家庭成员1","家庭成员2","家庭成员3","家庭成员4",
                "身份"
        };
    }

    public static String[] getPeopleDailyHeaders(){
        return new String[]{
                "序号","姓名","部门","工种","性别","民族","来自省","来自市(区)","出生日期","文化程度","政治面貌",
                "来院日期","联系电话","备注"
        };
    }

    public static String[] getPeopleSalaryHeaders(){
        return new String[]{
                "序号","姓名","岗位","岗位工资","薪级","薪级工资","工改保留工资","岗位考核结果","岗位津贴","绩效津贴","提租补贴",
                "购房补贴","参加工作日期","考勤请假情况","职务补贴","适当补贴","通讯补贴","交通补贴","日值班费","值班天数","值班费合计",
                "物业补贴","挂职补贴","降温补贴","补发","药费报销","一次性年终奖励","应发合计","养老保险","失业保险","医疗保险","职业年金",
                "住房公积金","扣款","代扣税","实发合计","发工资日期"
        };
    }
    public static String[] getPeopleContractSalaryHeaders(){
        return new String[]{
                "序号","人员姓名","岗位","岗位工资","院龄工资","考核结果","岗位考核工资","通信补贴","交通补贴","特殊补贴",
                "领班补贴","每日加班费","加班天数","加班补贴","奖金","补发","降温补贴","应发工资","养老保险","失业保险","医疗保险","住房公积金",
                "其他扣款","实发工资","月份"
        };
    }

    public static String[] getPeopleRetireSalaryHeaders(){
        return new String[]{
                "序号","人员姓名","基本工资","适当补贴","提租补贴","退休补贴","退休费调增","肉食补助","卫生补贴","医药费报销",
                "物业补贴","供暖费","残疾补助","应发合计","扣款","代扣房租","实发合计","月份"
        };
    }

    public static String[] getTrainingPeopleHeaders(){
        return new String[]{
                "序号","人员姓名","人员性别","所在部门","民族","政治面貌","职级","培训班名称","学习内容","培训类型",
                "起始时间","结束时间","累计时间","是否脱产","培训学时","培训地点","培训机构","备注"
        };
    }

    public static String[] getAbroadPeopleHeader(){
        return new String[]{
                "序号","人员姓名","人员所在部门","职级","出国境日期","所赴国家","申请因私证件情况","是由","经费形式","办理日期","取证日期","还证日期","备注"
        };
    }

    public static int PEOPLE_NORMAL = 0;
    public static int PEOPLE_RETIRE = 1;
    public static int PEOPLE_DEATH  = 2;
    public static int PEOPLE_TRANSFER = 3;

    public static int PEOPLE_RETIRE_RETIRE = 0;
    public static int PEOPLE_RETIRE_REHIRE = 1;

    public static String[] getExamMonthlyHeaders(){
        return new String[]{
                "序号","姓名","年份","月份","考核结果","考核运用"
        };
    }

    public static String[] getEaxmYearlyHeaders() {
        return new  String[]{
            "序号","姓名","年份","考核结果","考核运用"
        };
    }
}

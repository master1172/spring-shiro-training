package com.wangzhixuan.utils;

/**
 * Created by sterm on 2016/12/5.
 */
public class ConstUtil {
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

    public static int PEOPLE_NORMAL = 0;
    public static int PEOPLE_RETIRE = 1;
    public static int PEOPLE_DEATH  = 2;
    public static int PEOPLE_TRANSFER = 3;

    public static int PEOPLE_RETIRE_RETIRE = 0;
    public static int PEOPLE_RETIRE_REHIRE = 1;

}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleAddForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleSearchForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${recruit.name}"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <td>年龄</td>
                    <td>
                        <input type="text" id="age" name="age" value="${recruit.age}">
                    </td>
                    <td>专业</td>
                    <td>
                        <input type="text" id="major" name="major" value="${recruit.major}">
                    </td>
                </tr>
                <tr>
                    <td>应聘岗位</td>
                    <td>
                        <input type="text" id="applyJob" name="applyJob" value="${recruit.applyJob}">
                    </td>
                    <td>生源地</td>
                    <td>
                        <input class="text" id="origin" name="origin" value="${recruit.origin}">
                        </input>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出生日期</td>
                    <td>
                        <input id="birthday" name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${recruit.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" id="politicalName" name="politicalName" value="${recruit.politicalName}">
                    </td>
                    <td>身体状况</td>
                    <td>
                        <input type="text" id="health" name="health" value="${recruit.health}">
                    </td>
                    <td>毕业院校</td>
                    <td>
                        <input type="text" id="graduateSchool" name="graduateSchool" value="${recruit.graduateSchool}">
                    </td>
                    <td>学历</td>
                    <td>
                        <input type="text" id="degree" name="degree"  value="${recruit.degree}">
                    </td>
                </tr>
                <tr>
                    <td>是否能按期获得学位</td>
                    <td>
                        <select name="degreeOnTime" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">是</option>
                            <option value="1" >否</option>
                        </select>
                    </td>
                    <td>院校所在地</td>
                    <td>
                        <input type="text" id="schoolAddress" name="schoolAddress" value="${recruit.schoolAddress}">
                    </td>
                    <td>毕业生性质</td>
                    <td>
                        <select name="graduateStatus" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="统分" selected="selected">统分</option>
                            <option value="委培">委培</option>
                            <option value="定向">定向</option>
                        </select>
                    </td>
                    <td>外语水平</td>
                    <td>
                        <input type="text" id="foreignLanguageLevel" name="foreignLanguageLevel" value="${recruit.foreignLanguageLevel}">
                    </td>
                </tr>
                <tr>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>联系电话</td>
                    <td>
                        <input type="text" id="cellphone" name="cellphone" value="${recruit.cellphone}">
                    </td>
                    <td>身份证号码</td>
                    <td>
                        <input type="text" name="photoId" value="${recruit.photoId}">
                    </td>


                    <td>邮箱</td>
                    <td>
                        <input type="text" id="email" name="email" value="${recruit.email}">
                    </td>
                </tr>
                <tr>
                    <td>联系地址</td>
                    <td>
                        <input type="text" id="address" name="address" value="${recruit.address}">
                    </td>
                    <td>邮政编码</td>
                    <td>
                        <input type="text" id="zipcode" name="zipcode" value="${recruit.zipcode}">
                    </td>
                    <td>主要学习工作经历</td>
                    <td>
                        <input type="text" id="studyExperience" name="studyExperience" value="${recruit.studyExperience}">
                    </td>
                    <td>特长及能力</td>
                    <td>
                        <input type="text" id="specialityAndAbility" name="specialityAndAbility" value="${recruit.specialityAndAbility}">
                    </td>
                </tr>
                <tr>
                    <td>主要社会实践</td>
                    <td>
                        <input type="text" id="socialExperience" name="socialExperience" value="${recruit.socialExperience}">
                    </td>
                    <td>获奖情况</td>
                    <td>
                        <input type="text" id="award" name="award" value="${recruit.award}">
                    </td>
                </tr>
                <tr>
                    <td>头像上传</td>
                    <td colspan="3">
                        <div id="imgdiv" style="height:100px;width:100px;">
                            <img id="imgShow" style="height:100px;width:100px;"/>
                        </div>
                        <input type="file" id="up_img" name="fileName"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
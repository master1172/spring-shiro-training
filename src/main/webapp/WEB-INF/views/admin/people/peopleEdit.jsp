<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${people.sex}');
        $('#nationalId').val('${people.nationalId}');
        $('#nativeId').val('${people.nativeId}');
        $('#degreeId').val('${people.degreeId}');
        $('#jobCategory').val('${people.jobCategory}');
        $('#jobLevelId').val('${people.jobLevelId}');
        $('#marriageId').val('${people.marriageId}');
        $('#identityId').val('${people.identityId}');
        $('#imgShow').attr('src','${staticPath}/${people.photo}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${people.id}">
            <table class="grid" border="1">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${people.name}">
                    </td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>籍贯</td>
                    <td>
                        <input class="easyui-combobox" id="nativeId" name="nativeId" url="${path}/dict/native" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.birthday}"/>
                    </td>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName" value="${people.politicalName}">
                    </td>
                    <td>学历</td>
                    <td>
                        <input type="text" name="educationName" value="${people.educationName}">
                    </td>
                    <td>学位</td>
                    <td>
                        <input class="easyui-combobox" id="degreeId" name="degreeId" url="${path}/dict/degree" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>入党日期</td>
                    <td>
                        <input name="partyDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.partyDate}"/>
                    </td>
                    <td>参加工作日期</td>
                    <td>
                        <input name="workDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.workDate}"/>
                    </td>
                    <td>来院日期</td>
                    <td>
                        <input name="schoolDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.schoolDate}"/>
                    </td>
                    <td>职务</td>
                    <td>
                        <input type="text" name="jobName" value="${people.jobName}">
                    </td>
                </tr>
                <tr>
                    <td>人员类别</td>
                    <td>
                        <select id="jobCategory" name="jobCategory" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="管理类">管理类</option>
                            <option value="专业类">专业类</option>
                            <option value="工勤类">工勤类</option>
                        </select>
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobLevelId" name="jobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>任现职日期</td>
                    <td>
                        <input name="jobDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.jobDate}"/>
                    </td>
                    <td>任现职级日期</td>
                    <td>
                        <input name="jobLevelDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.jobLevelDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td>
                        <input type="text" name="age" value="${people.age}">
                    </td>
                    <td>虚岁</td>
                    <td>
                        <input type="text" name="virtualAge" value="${people.virtualAge}">
                    </td>
                    <td>工龄</td>
                    <td>
                        <input type="text" name="workAge" value="${people.workAge}">
                    </td>
                    <td>编制</td>
                    <td>
                        <input type="text" name="formation" value="${people.formation}">
                    </td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td>
                        <input type="text" name="mobile" value="${people.mobile}">
                    </td>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>身份证号码</td>
                    <td>
                        <input type="text" name="photoId" value="${people.photoId}">
                    </td>
                </tr>
                <tr>
                    <td>现家庭住址</td>
                    <td>
                        <input type="text" name="address" value="${people.address}">
                    </td>
                    <td>户籍</td>
                    <td>
                        <input type="text" name="hukou" value="${people.hukou}">
                    </td>
                </tr>
                <tr>
                    <td>户籍地址</td>
                    <td>
                        <input type="text" name="hukouAddress" value="${people.hukouAddress}">
                    </td>
                    <td>最终学历</td>
                    <td>
                        <input type="text" name="finalEducationName" value="${people.finalEducationName}">
                    </td>
                    <td>所学专业</td>
                    <td>
                        <input type="text" name="major" value="${people.major}">
                    </td>
                    <td>毕业院校</td>
                    <td>
                        <input type="text" name="graduateSchool" value="${people.graduateSchool}">
                    </td>
                </tr>
                <tr>
                    <td>紧急联系人</td>
                    <td>
                        <input type="text" name="contact" value="${people.contact}">
                    </td>
                    <td>与本人关系</td>
                    <td>
                        <input type="text" name="relationship" value="${people.relationship}">
                    </td>
                    <td>联系人电话</td>
                    <td>
                        <input type="text" name="contactNumber" value="${people.contactNumber}">
                    </td>
                    <td>身份</td>
                    <td>
                        <input class="easyui-combobox" id="identityId" name="identityId" url="${path}/dict/identity" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>家庭成员1</td>
                    <td>
                        <input type="text" name="familyInfo1" value="${people.familyInfo1}">
                    </td>
                    <td>家庭成员2</td>
                    <td>
                        <input type="text" name="familyInfo2" value="${people.familyInfo2}">
                    </td>
                    <td>家庭成员3</td>
                    <td>
                        <input type="text" name="familyInfo3" value="${people.familyInfo3}">
                    </td>
                    <td>家庭成员4</td>
                    <td>
                        <input type="text" name="familyInfo4" value="${people.familyInfo4}">
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
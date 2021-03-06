<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${people.sex}');
        $('#nationalId').val('${people.nationalId}');
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
            <input type="hidden" name="code" value="${people.code}">
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" class="easyui-validatebox" data-options="required:true" value="${people.name}"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>

                </tr>
                <tr>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${people.birthday}"/>
                    </td>
                    <td>来院日期</td>
                    <td>
                        <input name="schoolDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${people.schoolDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>来自省</td>
                    <td>
                        <input type="text" name="province" value="${people.province}">
                    </td>
                    <td>来自市(区)</td>
                    <td>
                        <input type="text" name="city" value="${people.city}">
                    </td>
                </tr>
                <tr>
                    <td>文化程度</td>
                    <td>
                        <input type="text" name="educationName" value="${people.educationName}">
                    </td>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName" value="${people.politicalName}">
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td>
                        <input type="text" name="departmentName" value="${people.departmentName}">
                    </td>
                    <td>工种</td>
                    <td>
                        <input type="text" name="jobName" value="${people.jobName}">
                    </td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td>
                        <input type="text" name="mobile" value="${people.mobile}" class="easyui-validatebox" data-options="validType:'length[1,11]'">
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment" value="${people.comment}">
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
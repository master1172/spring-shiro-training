<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath}/static/citySelect/jquery.cityselect.js"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${peopleDispatch.sex}');
        $('#hukou').val('${peopleDispatch.hukou}');
        $('#marriageId').val('${peopleDispatch.marriageId}');
        $('#nationalId').val('${peopleDispatch.nationalId}');
        $('#imgShow').attr('src','${staticPath}/${peopleDispatch.photo}');
    });
    $(function(){
        if("${peopleDispatch.province}" == ""){
            $("#city_1").citySelect({
                nodata: "none",
                required: false
            });
        } else if("${peopleDispatch.city}" == ""){
            $("#city_1").citySelect({
                prov: "${peopleDispatch.province}",
                required:false
            });
        } else {
            $("#city_1").citySelect({
                prov: "${peopleDispatch.province}",
                city: "${peopleDispatch.city}"
            });
        }
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
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${peopleDispatch.id}">
            <input type="hidden" name="code" value="${peopleDispatch.code}">
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" value="${peopleDispatch.name}"></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr id="city_1">
                    <td>来自省</td>
                    <td>
                        <select name="province" class="prov" ></select>
                    </td>
                    <td>来自市/区</td>
                    <td>
                        <select name="city" class="city" disabled="disabled"></select>
                    </td>
                </tr>
                <tr>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleDispatch.birthday}"/>
                    </td>
                    <td>文化程度</td>
                    <td>
                        <input type="text" name="educationName" value="${peopleDispatch.educationName}">
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName" value="${peopleDispatch.politicalName}">
                    </td>
                    <td>特长</td>
                    <td>
                        <input type="text" name="speciality" value="${peopleDispatch.speciality}"/>
                    </td>
                </tr>
                <tr>
                    <td>身高</td>
                    <td>
                        <input type="text" name="height" value="${peopleDispatch.height}"/>
                    </td>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>户籍</td>
                    <td>
                        <select name="hukou" id="hukou" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" >非农业</option>
                            <option value="1" >农业</option>
                        </select>
                    </td>
                    <td>到院工作日期</td>
                    <td>
                        <input name="schoolDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleDispatch.schoolDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td>
                        <input type="text" name="mobile" value="${peopleDispatch.mobile}" class="easyui-validatebox" data-options="validType:'length[1,11]'">
                    </td>
                    <td>现住址</td>
                    <td>
                        <input type="text" name="address" value="${peopleDispatch.address}"/>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td>
                        <input type="text" name="departmentName" value="${peopleDispatch.departmentName}">
                    </td>
                    <td>工种</td>
                    <td>
                        <input type="text" name="jobName" value="${peopleDispatch.jobName}"/>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="3">
                        <input type="text" name="comment" value="${peopleDispatch.comment}"/>
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

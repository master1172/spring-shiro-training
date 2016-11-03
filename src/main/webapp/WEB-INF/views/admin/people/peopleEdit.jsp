<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${people.sex}');
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
                    <td><select name="sex" id="sex"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select></td>
                </tr>
                <tr>
                    <td>职业</td>
                    <td>
                        <input name="job" type="text" value="${people.job}">
                    </td>
                    <td>薪资</td>
                    <td>
                        <input type="text" name="salary" value="${people.salary}" class="easyui-numberbox"/>
                    </td>
                </tr>
                <tr>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td>头像上传</td>
                    <td colspan="3">
                        <div id="imgdiv" style="height:100px;width:100px;">
                            <img id="imgShow" style="height:100px;width:100px;" src="${staticPath}/${people.photo}"/>
                        </div>
                        <input type="file" id="up_img" name="fileName"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
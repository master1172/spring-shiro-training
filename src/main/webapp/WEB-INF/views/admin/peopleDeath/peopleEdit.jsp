<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${people.sex}');
        $('#national').val('${people.national}');
        $('#job_level_id').val('${people.job_level_id}');
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
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${people.id}">
            <table class="grid" border="1">
                <tr>
                    <td>人员编码</td>
                    <td><input name="code" type="text" placeholder="请输入人员编码" value="${people.code}"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">男</option>
                        <option value="1">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="national" name="national" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td>到院工作日期</td>
                    <td>
                        <input name="school_date" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.school_date}"/>
                    </td>
                    <td>职务</td>
                    <td>
                        <input type="text" name="category" placeholder="请输入职务" value="${people.category}"/>
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="job_level_id" name="job_level_id" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>部门</td>
                    <td><input type="text" name="department" value="${people.department}"></td>
                </tr>
                <tr>
                    <td>死亡日期</td>
                    <td>
                        <input name="death_date" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${people.death_date}"/>
                    </td>
                    <td>死亡原因</td>
                    <td>
                        <input type="text" name="death_reason" value="${people.death_reason}"/>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment" value="${people.comment}"/>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${peopleRetire.sex}');
        $('#nationalId').val('${peopleRetire.nationalId}');
        $('#retireJobName').val('${peopleRetire.retireJobName}');
        $('#retireJobLevelId').val('${peopleRetire.retireJobLevelId}');
        $('#status').val('${peopleRetire.status}');
        $('#imgShow').attr('src','${staticPath}/${peopleRetire.photo}');
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
            <input type="hidden" name="id" value="${peopleRetire.id}">
            <table class="grid" border=1>
                <tr>
                    <td>人员编码</td>
                    <td><input name="code" type="text" value="${peopleRetire.code}"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>退休时职务</td>
                    <td>
                        <input class="easyui-combobox" id="retireJobName" name="retireJobName" url="${path}/dict/jobName" valueField="name" textField="name" editable="false">
                        </input>
                    </td>
                    <td>退休时职级</td>
                    <td>
                        <input class="easyui-combobox" id="retireJobLevelId" name="retireJobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>生日</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleRetire.birthday}"/>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>学历</td>
                    <td>
                        <input type="text" name="educationName" value="${peopleRetire.educationName}">
                    </td>
                    <td>政治面貌</td>
                    <td><input type="text" name="politicalName" value="${peopleRetire.politicalName}"></td>
                </tr>
                <tr>
                    <td>工作日期</td>
                    <td>
                        <input name="workDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleRetire.workDate}"/>
                    </td>
                    <td>退休日期</td>
                    <td>
                        <input name="retireDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleRetire.retireDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>家庭住址</td>
                    <td>
                        <input type="text" name="address" value="${peopleRetire.address}"/>
                    </td>
                    <td>联系电话</td>
                    <td>
                        <input type="text" name="mobile" value="${peopleRetire.mobile}"/>
                    </td>
                </tr>

                <tr>
                    <td>固定联系人</td>
                    <td>
                        <input type="text" name="contact" value="${peopleRetire.contact}">
                    </td>
                    <td>联系人电话</td>
                    <td>
                        <input type="text" name="contactNumber" value="${peopleRetire.contactNumber}"/>
                    </td>
                </tr>
                <tr>
                    <td>当前状态</td>
                    <td>
                        <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" >退休</option>
                            <option value="1" >返聘</option>
                        </select>
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment" value="${peopleRetire.comment}">
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">

    $(function(){
        $("#sex").val('${training.sex}');
        $("#nationalId").val('${training.nationalId}');
        $("#departmentId").val('${training.departmentId}');
        $("#jobId").val('${training.jobId}');
        $("#trainingCategory").val('${training.trainingCategory}');
        $("#offWork").val('${training.offWork}');

    });

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
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${training.id}"/>
            <input type="hidden" name="code" value="${training.code}"/>
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${training.name}"></td>
                    <td>部门</td>
                    <td>
                        <input class="easyui-combobox" id="departmentId" name="departmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出国境日期</td>
                    <td>
                        <input type="text" name="abroadDate" value="${abroad.abroadDate}">
                    </td>
                    <td>所赴国家</td>
                    <td>
                        <input type="text" name="country" value="${abroad.country}">
                    </td>
                </tr>
                <tr>
                    <td>申请因私证件情况</td>
                    <td>
                        <select name="passportStatus" id="passportStatus" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="首次申办证件">首次申办证件</option>
                            <option value="领用已办证件">领用已办证件</option>
                        </select>
                    </td>
                    <td>是由</td>
                    <td>
                        <select name="reason" id="reason" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="个人旅游">个人旅游</option>
                            <option value="探亲">探亲</option>
                            <option value="处理个人事务">处理个人事务</option>
                        </select>
                    </td>
                    <td>经费形势</td>
                    <td>
                        <select name="funding" id="funding" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="自费">自费</option>
                            <option value="其它">其它</option>
                        </select>
                    </td>
                    <td>办理日期</td>
                    <td>
                        <input id="issueDate" name="issueDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.issueDate}"/>
                    </td>
                    <td>取证日期</td>
                    <td>
                        <input id="pickPassportDate" name="pickPassportDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.pickPassportDate}"/>
                    </td>

                    <td>还证日期</td>
                    <td>
                        <input id="returnPassportDate" name="returnPassportDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.returnPassportDate}"/>
                    </td>
                <tr>
                    <td>备注</td>
                    <td>
                        <input type="text" id="comment" name="comment" value="${abroad.comment}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
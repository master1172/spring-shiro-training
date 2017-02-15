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
        <form id="peopleAddForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>部门</td>
                    <td>
                        <input class="easyui-combobox" id="departmentId" name="departmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName">
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>培训班名称</td>
                    <td>
                        <input type="text" name="trainingClass">
                    </td>
                    <td>学习内容</td>
                    <td>
                        <input type="text" name="description">
                    </td>
                </tr>
                <tr>
                    <td>培训类型</td>
                    <td>
                        <select name="trainingCategory" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="组织调研" selected="selected">组织调研</option>
                            <option value="网络培训">网络培训</option>
                            <option value="继续教育">继续教育</option>
                            <option value="专题讲座">专题讲座</option>
                            <option value="干部选学">干部选学</option>
                            <option value="中心组（扩大）学习">中心组（扩大）学习</option>
                            <option value="旁听培训班课程">旁听培训班课程</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                    <td>培训起始日期</td>
                    <td>
                        <input id="startDate" name="startDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>培训结束日期</td>
                    <td>
                        <input id="endDate" name="endDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>累计时间</td>
                    <td>
                        <input type="text" name="sumTime">
                    </td>
                </tr>
                <tr>
                    <td>是否脱产</td>
                    <td>
                        <select name="offWork" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="是" selected="selected">是</option>
                            <option value="否">否</option>
                        </select>
                    </td>
                    <td>培训学时</td>
                    <td>
                        <input type="text" id="classHour" name="classHour">
                    </td>
                    <td>培训地点</td>
                    <td>
                        <input type="text" id="trainingPlace" name="trainingPlace">
                    </td>
                    <td>培训机构</td>
                    <td>
                        <input type="text" id="trainingSchool" name="trainingSchool">
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td>
                        <input type="text" id="comment" name="comment">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
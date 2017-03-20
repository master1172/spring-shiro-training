<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
        var isValid = $("#peopleQueryForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <div style="width:600px">
        <form id="peopleSearchForm" method="post">
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" value=""></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalIdList" name="nationalIdList" url="${path}/dict/national" valueField="id" textField="name" editable="false" multiple="true">
                        </input>
                    </td>
                    <td>籍贯</td>
                    <td>
                        <input class="easyui-combobox" id="nativeIdList" name="nativeIdList" url="${path}/dict/native" valueField="id" textField="name" editable="false" multiple="true">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">出生日期范围</td>
                    <td colspan="3">
                        <input name="birthdayMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="birthdayMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName">
                    </td>
                </tr>
                <tr>
                    <td>学历</td>
                    <td>
                        <input type="text" name="educationName">
                    </td>
                    <td>学位</td>
                    <td>
                        <input class="easyui-combobox" id="degreeIdList" name="degreeIdList" url="${path}/dict/degree" valueField="id" textField="name" editable="false" multiple="true">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">入党日期范围</td>
                    <td colspan="3">
                        <input name="partyDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="partyDateyMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">参加工作日期范围</td>
                    <td colspan="3">
                        <input name="workDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="workDateyMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">来院日期范围</td>
                    <td colspan="3">
                        <input name="schoolDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="schoolDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>职务</td>
                    <td>
                        <input type="text" name="jobName">
                    </td>
                </tr>
                <tr>
                    <td>人员类别</td>
                    <td>
                        <select name="jobCategoryList" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'" multiple="true">
                            <option value="" selected="selected"></option>
                            <option value="管理类">管理类</option>
                            <option value="专业类">专业类</option>
                            <option value="工勤类">工勤类</option>
                        </select>
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobIdList" name="jobIdList" url="${path}/dict/job" valueField="id" textField="name" editable="false" multiple="true">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">任现职日期范围</td>
                    <td colspan="3">
                        <input name="jobDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="jobDateyMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">任现职级日期范围</td>
                    <td colspan="3">
                        <input name="jobLevelDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="jobLevelDateyMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
        </div>
    </div>
</div>
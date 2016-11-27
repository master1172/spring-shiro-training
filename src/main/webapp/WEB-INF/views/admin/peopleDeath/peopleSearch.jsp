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
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleSearchForm" method="post">
            <table class="grid" border=1>
                <tr>
                    <td>人员编码</td>
                    <td><input name="code" type="text" placeholder="请输入人员编码"></td>
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
                        <input class="easyui-combobox" id="national" name="national" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>职务</td>
                    <td>
                        <input type="text" name="category" placeholder="请输入职务" />
                    </td>
                </tr>
                <tr>
                    <td>出生日期范围</td>
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
                    <td>到院工作日期范围</td>
                    <td colspan="3">
                        <input name="school_dateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="school_dateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="job_level_id" name="job_level_id" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>部门</td>
                    <td><input type="test" name="department"></td>
                </tr>
                <tr>
                    <td>死亡日期范围</td>
                    <td colspan="3">
                        <input name="death_dateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="death_dateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>死亡原因</td>
                    <td>
                        <input type="text" name="death_reason"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
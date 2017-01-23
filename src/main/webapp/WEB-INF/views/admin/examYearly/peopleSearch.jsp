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
                    <td>人员编号</td>
                    	<td><input name="peopleCode" type="text" placeholder="请输入人员编号" class="easyui-validatebox" value=""></td>
                    <td>人员类型</td>
                    <td>
                       <td><input name="peopleType" type="text" placeholder="请输入人员类型" class="easyui-validatebox" value=""></td>
                    </td>
                    <td>考勤日期范围</td>
                    <td colspan="3">
                        <input name="checkDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="checkDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>考勤结果</td>
                    <td>
                        <input type="text" name="status" id="status">
                    </td>
                    <td>假期单位</td>
                    <td>
                        <input type="text" name="vacationPeriod">
                    </td>
                 </tr>
            </table>
        </form>
    </div>
</div>
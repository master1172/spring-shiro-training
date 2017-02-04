<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#checkDate").val('${peopleTimesheetVo.checkDate}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#timesheetEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="timesheetEditForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="id"         value="${peopleTimesheetVo.id}">
                <input type="hidden" name="peopleCode" value="${peopleTimesheetVo.peopleCode}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="peopleName" type="text" value="${peopleTimesheetVo.peopleName}" class="easyui-validatebox" data-options="required:true" readonly="readonly">
                    </td>
                    <td>请假日期</td>
                    <td>
                        <input id="checkDate" name="checkDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>请假原因</td>
                    <td>
                        <input type="text" name="status" value="${peopleTimesheetVo.status}">
                    </td>
                    <td>假期长度</td>
                    <td>
                        <input name="vacationPeriod" id="vacationPeriod" type="text" value="${peopleTimesheetVo.vacationPeriod}" class="easyui-numberbox" precision="1" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
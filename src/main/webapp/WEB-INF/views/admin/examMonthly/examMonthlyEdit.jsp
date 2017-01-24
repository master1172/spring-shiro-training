<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
        var isValid = $("#salaryAddForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="examMonthlyEditForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="id" value="${examMonthly.id}">
                <input type="hidden" name="peopleCode" value="${examMonthly.peopleCode}">
                <tr>
                    <td>年份信息</td>
                    <td>
                        <input name="year" id="year" type="text" value="${examMonthly.year}" class="easyui-numberbox" precision="0" style="text-align:right;"/>
                    </td>
                    <td>月份信息</td>
                    <td>
                        <input name="month" id="month" type="text" value="${examMonthly.month}" class="easyui-numberbox" precision="0" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>考核结果</td>
                    <td>
                        <input type="text" name="examResult" value="${examMonthly.examResult}">
                    </td>
                    <td>考核运用</td>
                    <td>
                        <input type="text" name="examOperation" value="${examMonthly.examOperation}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
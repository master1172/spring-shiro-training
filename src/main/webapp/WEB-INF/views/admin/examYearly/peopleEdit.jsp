<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
       /*  var isValid = $("#peopleEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        } */
        return true;
    }

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${examYearly.id}">
            <table class="grid" border="1">
                <tr>
                    <td>人员编号 </td>
                    <td>
                        <input name="peopleCode" id="peopleCode" type="text" value="${examYearly.peopleCode}" class="easyui-validatebox" data-options="required:true" >
                    </td>
                    <td>人员类型 </td>
                    <td>
                        <input name="peopleType" id="peopleType" type="text" value="${examYearly.peopleType}" class="easyui-validatebox" data-options="required:true" >
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>年份信息 </td>
                    <td>
		              <input type="text" name="year"  id="year" value="${examYearly.year}">
                    </td>
          
                    <td>考勤结果 </td>
                    <td>
                        <input type="text" name="examResult"  id="status" value="${examYearly.examResult}">
                    </td>
                    <td>考核运用 </td>
                    <td>
                        <input type="text" name="examOperation" id="vacationPeriod" value="${examYearly.examOperation}">
                    </td>
                </tr>
              
            </table>
        </form>
    </div>
</div>
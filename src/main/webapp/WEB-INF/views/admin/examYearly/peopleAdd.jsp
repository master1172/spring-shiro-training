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
                    <td>人员编号 </td>
                    <td>
                        <input name="peopleCode" id="peopleCode" type="text"  >
                    </td>
                    <td>人员类型 </td>
                    <td>
                        <input name="peopleType" id="peopleType" type="text"  >
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>年份信息 </td>
                    <td>
		              <input type="text" name="year"  id="year" >
                    </td>
          
                    <td>考勤结果 </td>
                    <td>
                        <input type="text" name="examResult"  id="status" >
                    </td>
                    <td>考核运用 </td>
                    <td>
                        <input type="text" name="examOperation" id="vacationPeriod" >
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
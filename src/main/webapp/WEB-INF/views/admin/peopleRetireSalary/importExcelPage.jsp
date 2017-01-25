<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    });
    function checkForm(){
    	progressLoad();
    	var isValid = $("#importExcelForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
		return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="importExcelForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <tr>
                    <td>
                    	<input type="file" name="fileName" multiple="multiple" placeholder="请选择文件" class="easyui-validatebox" data-options="required:true">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
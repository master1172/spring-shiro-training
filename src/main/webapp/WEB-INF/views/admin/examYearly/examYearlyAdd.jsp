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
        <form id="examMonthlyAddForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${people.code}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${people.name}" class="easyui-validatebox" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td>年份信息</td>
                    <td>
                        <input name="year" id="year" type="text" class="easyui-numberbox" precision="0" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>考核结果</td>
                    <td>
                        <select name="examResult" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="优秀" selected="selected">优秀</option>
                            <option value="合格" >合格</option>
                        </select>
                    </td>
                    <td>考核运用</td>
                    <td>
                        <input type="text" name="examOperation">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
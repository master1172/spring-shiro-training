<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
        var isValid = $("#salarySearchForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }


</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salarySearchForm" method="post">
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="" class="easyui-validatebox" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobIdList" name="jobIdList" url="${path}/dict/job" valueField="id" textField="name" editable="false" multiple="true">
                        </input>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
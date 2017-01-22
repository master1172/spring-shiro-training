<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
        var isValid = $("#peopleRankForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleRankForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border="1">
                <tr>
                    <td>薪级</td>
                    <td>
                        <input type="text" name="rank_level" data-options="required:true">
                    </td>
                    <td>薪级工资</td>
                    <td>
                        <input name="salary" id="salary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;" data-options="required:true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
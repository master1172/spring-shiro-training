<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow:scroll;padding: 3px;">
        <div style="width:300px">
            <form id="addForm" method="post" enctype=”multipart/form-data”>
                <table class="grid" border=1>
                    <input type="hidden" name="tableName" value="${tableName}">
                    <tr>
                        <td width="50">名称</td>
                        <td>
                            <input name="name" type="text" class="easyui-validatebox" data-options="required:true" value="">
                        </td>
                </table>
            </form>
        </div>
    </div>
</div>
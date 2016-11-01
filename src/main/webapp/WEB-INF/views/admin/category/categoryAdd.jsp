<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {
        $('#pid').combotree({
            url : '${path}/category/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto'
        });

        $('#categoryAddForm').form({
            url : '${path}/category/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });

    });
</script>
<div style="padding: 3px;">
    <form id="categoryAddForm" method="post">
        <table class="grid">
            <tr>
                <td>分类名称</td>
                <td>
                    <input name="name" type="text" placeholder="请输入分类名称" class="easyui-validatebox" data-options="required:true" >
                </td>
                <td>排序</td>
                <td>
                    <input name="seq"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false" value="0">
                </td>
            </tr>
            <tr>
                <td>菜单图标</td>
                <td>
                    <input name="icon" value="icon-folder" readonly="readonly"/>
                </td>
                <td>上级分类</td>
                <td colspan="3"><select id="pid" name="pid" style="width:200px;height: 29px;"></select>
                    <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
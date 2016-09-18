<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#peopleEditForm').form({
            url : '${path }/people/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        $("#sex").val('${people.sex}');

    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleEditForm" method="post">
            <input type="hidden" name="id" value="${people.id}">
            <table class="grid">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${people.name}">
                    </td>
                    <td>性别</td>
                    <td><select name="sex" id="sex"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select></td>
                </tr>
                <tr>
                    <td>工作</td>
                    <td>
                        <input name="job" type="text" value="${people.job}">
                    </td>
                    <td>薪资</td>
                    <td>
                        <input type="text" name="salary" value="${people.salary}" class="easyui-numberbox"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
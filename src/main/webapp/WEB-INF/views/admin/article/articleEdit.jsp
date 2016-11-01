<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    $(function() {

        $('#categoryId').combotree({
            url : '${path}/category/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${article.categoryId}'
        });


        $('#articleEditForm').form({
            url : '${path}/article/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为article.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="articleEditForm" method="post">
            <input name="id" type="hidden"  value="${article.id}">
            <table class="grid">
                <tr>
                    <td>文章标题</td>
                    <td>
                        <input name="title"  type="text" placeholder="请输入文章标题"    class="easyui-validatebox" data-options="required:true" value="${article.title}">
                    </td>
                    <td>文章作者</td>
                    <td>
                        <input name="author" type="text" placeholder="请输入文章作者姓名" class="easyui-validatebox" data-options="required:true" value="${article.author}">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">文章内容</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <script id="container" name="content" type="text/plain">
                            ${article.content}
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>所属分类</td>
                    <td colspan="3">
                        <select id="categoryId" name="categoryId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<script>
    var editor = UE.getEditor("container");
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在编人员管理</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/training/dataGrid',
                fit: true,
                striped: true,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                idField: 'id',
                singleSelect: false,
                selectOnCheck: false,
                checkOnSelect: true,
                sortName: 'id',
                sortOrder: 'asc',
                pageSize: 20,
                pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],

                onLoadSuccess: function (data) {
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '编辑', plain: true, iconCls: 'icon-edit'});
                    $('.user-easyui-linkbutton-del').linkbutton({text: '删除', plain: true, iconCls: 'icon-del'});
                },
                toolbar: '#toolbar'
            });
        });

        function advSearch() {
            parent.$.modalDialog({
                title: '高级查询',
                width: 1000,
                height: 600,
                href: '${path}/training/advSearchPage',
                buttons: [{
                    text: '提交',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        if (parent.checkForm()) {
                            parent.progressClose();
                            var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                            dataGrid.datagrid("load", $.serializeObject(f));
                            parent.$.modalDialog.handler.dialog("close");
                        }
                    }
                }]
            });
        }

        function exportSearch() {
            parent.$.modalDialog({
                title: '导出',
                width: 1000,
                height: 600,
                maximizable:true,
                href: '${path}/training/exportSearchPage',
                buttons: [{
                    text: '导出',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/training/exportSearch", function (data) {
                                if (!data["success"]) {
                                    parent.progressClose();
                                    parent.$.modalDialog.handler.dialog("close");
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                } else {
                                    parent.progressClose();
                                    parent.$.modalDialog.handler.dialog("close");
                                    var ids = data["obj"];
                                    var form = $("#downLoadForm");
                                    form.find("input[name='ids']").val(ids);
                                    form.attr("action", '${path}' + "/training/exportExcel");
                                    $("#downLoadForm").submit();
                                }
                            });
                        }
                    }
                }]
            });
        }

        function addFun() {
            parent.$.modalDialog({
                title: '添加',
                width: 1500,
                height: 600,
                maximizable:true,
                href: '${path}/training/addPage',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleAddForm");
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/training/add", function (data) {
                                if (!data["success"]) {
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                } else {
                                    parent.progressClose();
                                    dataGrid.datagrid("reload");
                                    parent.$.modalDialog.handler.dialog("close");
                                }
                            });
                        }
                    }
                }]
            });
        }

        function editFun(id) {
            if (id == undefined) {
                var rows = dataGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }

            parent.$.modalDialog({
                title: '修改',
                width: 1500,
                height: 600,
                maximizable:true,
                href: '${path}/training/editPage?id=' + id,
                buttons: [{
                    text: '修改',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleEditForm");
                        //f.submit();
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/training/edit", function (data) {
                                if (!data["success"]) {
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                } else {
                                    parent.progressClose();
                                    dataGrid.datagrid("reload");
                                    parent.$.modalDialog.handler.dialog("close");
                                }
                            });
                        }
                    }
                }]
            });
        }

        function deleteFun(id) {
            if (id == undefined) {//点击右键菜单才会触发这个
                var rows = dataGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {//点击操作里面的删除图标会触发这个
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
            parent.$.messager.confirm('询问', '您是否要删除当前人员？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/training/delete', {
                        id: id
                    }, function (result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }

        function batchDel() {
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function (index, item) {
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要删除所选人员？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/training/batchDel', {
                        ids: ids.join(",")
                    }, function (result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }

        function searchFun() {
            dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        }

        function cleanFun() {
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {});
        }

        //导入Excel
        function importExcel() {
            parent.$.modalDialog({
                title: '数据导入',
                width: 500,
                height: 300,
                maximizable:true,
                href: '${path}/training/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/training/importExcel", function (data) {
                                if (!data["success"]) {
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                } else {
                                    parent.progressClose();
                                    dataGrid.datagrid("reload");
                                    parent.$.modalDialog.handler.dialog("close");
                                }
                            });
                        }
                    }
                }]
            });
        }

        //导出Excel
        function exportExcel() {
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if (checkedItems.length > 0) {
                var ids = "";
                $.each(checkedItems, function (index, item) {
                    if (ids.length > 0)ids += ",";
                    ids += item["id"];
                });
                var form = $("#downLoadForm");
                form.find("input[name='ids']").val(ids);
                form.attr("action", '${path}' + "/training/exportExcel");
                $("#downLoadForm").submit();
            } else {
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function sexFormatter(value, row, index) {
            switch (value) {
                case 0:
                    return '男';
                case 1:
                    return '女';
            }
        }

        function operateFormatter(value, row, index) {
            var str = '';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
            return str;
        }
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
    <form id="searchForm">
        <table>
            <tr>
                <th>姓名:</th>
                <td>
                    <input name="name" placeholder="请输入人员姓名"/>
                </td>
                <th>性别:</th>
                <td>
                    <select name="sex">
                        <option value="" selected>请选择</option>
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div data-options="region:'center',border:true,title:'培训人员列表'">
    <table id="dataGrid" data-options="fit:true,border:false">
        <thead>
        <tr>
            <th field="ck"              data-options="checkbox:true"></th>
            <th field="name"            data-options="sortable:true" width="80">姓名</th>
            <th field="sex"             data-options="sortable:true,formatter:sexFormatter" width="40">性别</th>
            <th field="departmentName"  data-options="sortable:true" width="80">所在部门</th>
            <th field="nationalName"    data-options="sortable:true" width="80">民族</th>
            <th field="politicalName"   data-options="sortable:true" width="80">政治面貌</th>
            <th field="jobName"         data-options="sortable:true" width="80">职级</th>
            <th field="trainingClass"   data-options="sortable:true" width="80">培训班名称</th>
            <th field="startDate"       data-options="sortable:true" width="80">起始时间</th>
            <th field="endDate"         data-options="sortable:true" width="80">结束时间</th>
            <th field="offWork"         data-options="sortable:true" width="80">是否脱产</th>
            <th field="classHour"       data-options="sortable:true" width="80">培训学时</th>
            <th field="trainingPlace"   data-options="sortable:true" width="80">培训地点</th>
            <th field="trainingSchool"  data-options="sortable:true" width="80">培训机构</th>
            <th field="id" data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
        </tr>
        </thead>
    </table>
</div>

<div id="toolbar" style="display: none;">

    <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">添加</a>

    <a onclick="batchDel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-del'">批量删除</a>

    <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导入</a>

    <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导出Excel</a>

    <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">高级查询</a>

    <a onclick="exportSearch();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">查询导出</a>
    <!-- 附件下载使用 -->
    <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
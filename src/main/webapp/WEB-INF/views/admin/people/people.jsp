<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员管理</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/people/dataGrid',
                fit: true,
                striped: true,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                idField: 'id',
                sortName: 'id',
                sortOrder: 'asc',
                pageSize: 20,
                pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
                columns: [[{
                    width: '80',
                    title: '姓名',
                    field: 'name',
                    sortable: true
                }, {
                    width: '40',
                    title: '性别',
                    field: 'sex',
                    sortable: true,
                    formatter: function (value, row, index) {
                        switch (value) {
                            case 0:
                                return '男';
                            case 1:
                                return '女';
                        }
                    }
                }, {
                    width: '130s',
                    title: '生日',
                    field: 'birthday',
                    sortable: true
                }, {
                    width: '80',
                    title: '工作',
                    field: 'job',
                    sortable: true
                }, {
                    width: '80',
                    title: '薪资',
                    field: 'salary',
                    sortable: true
                }, {
                    field: 'action',
                    title: '操作',
                    width: '130',
                        formatter: function (value, row, index) {
                            var str = '';
                            <shiro:hasPermission name="/people/edit">
                                str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/people/delete">
                                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                                str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                            </shiro:hasPermission>
                            return str;
                        }
                    }]],
                    onLoadSuccess: function (data) {
                        $('.user-easyui-linkbutton-edit').linkbutton({text: '编辑', plain: true, iconCls: 'icon-edit'});
                        $('.user-easyui-linkbutton-del').linkbutton({text: '删除', plain: true, iconCls: 'icon-del'});
                },
                toolbar: '#toolbar'
            });
        });

        function addFun() {
            parent.$.modalDialog({
                title: '添加',
                width: 500,
                height: 300,
                href: '${path}/people/addPage',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#peopleAddForm');
                        f.submit();
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
                    $.post('${path}/people/delete',{
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

        function editFun(id) {
            if (id == undefined) {
                var rows = dataGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }

            parent.$.modalDialog({
                title: '编辑',
                width: 500,
                height: 300,
                href: '${path }/people/editPage?id=' + id,
                buttons: [{
                    text: '确定',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#peopleEditForm');
                        f.submit();
                    }
                }]
            });
        }

        function searchFun() {
            dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        }

        function cleanFun() {
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {});
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
                    </td>
                    <th>出生日期</th>
                    <td>
                        <input name="birthdayStart" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>至
                        <input name="birthdayEnd" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                            data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div data-options="region:'center',border:true,title:'人员列表'">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>

    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/people/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
    </div>
</body>
</html>
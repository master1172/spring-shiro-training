<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文章管理</title>
    <script type="text/javascript">
        var dataGrid;
        var categoryTree;

        $(function () {

            categoryTree = $('#categoryTree').tree({
                url: '${path }/category/tree',
                parentField: 'pid',
                lines: true,
                onClick: function (node) {
                    dataGrid.datagrid('load', {
                        categoryId: node.id
                    });
                }
            });

            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/article/dataGrid',
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
                columns: [[{
                    field: 'ck',
                    checkbox:true
                }, {
                    width: '80',
                    title: '文章标题',
                    field: 'title',
                    sortable: true
                }, {
                    width: '80',
                    title: '文章作者',
                    field: 'author',
                    sortable: true
                }, {
                    width: '80',
                    title: '所属分类',
                    field: 'categoryName',
                    sortable: true
                }, {
                    width: '130',
                    title: '发布时间',
                    field: 'publishTime',
                    sortable: true
                }, {
                    field: 'action',
                    title: '操作',
                    width: '130',
                    formatter: function (value, row, index) {
                            var str = '';
                            <shiro:hasPermission name="/article/edit">
                                str += $.formatString('<a href="javascript:void(0)" class="article-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/article/delete">
                                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                                str += $.formatString('<a href="javascript:void(0)" class="article-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                            </shiro:hasPermission>
                            return str;
                        }
                    }]],
                    onLoadSuccess: function (data) {
                        $('.article-easyui-linkbutton-edit').linkbutton({text: '编辑', plain: true, iconCls: 'icon-edit'});
                        $('.article-easyui-linkbutton-del').linkbutton({text: '删除', plain: true, iconCls: 'icon-del'});
                    },
                    toolbar: '#toolbar'
                });
        });

        function addFun() {
            parent.$.modalDialog({
                title: '添加',
                width: 1000,
                height: 450,
                maximizable:true,
                href: '${path}/article/addPage',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#articleAddForm');
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
            parent.$.messager.confirm('询问', '您是否要删除当前文章？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/article/delete', {
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
                width: 1000,
                height: 450,
                maximizable:true,
                href: '${path}/article/editPage?id=' + id,
                buttons: [{
                    text: '确定',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#articleEditForm');
                        f.submit();
                    }
                }]
            });
        }

        function batchDel(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index,item){
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要删除所选文章？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/article/batchDel', {
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
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
    <form id="searchForm">
        <table>
            <tr>
                <th>文章标题:</th>
                <td><input name="name" placeholder="请输入文章标题"/></td>
                <th>创建时间:</th>
                <td>
                    <input name="createTimeStart" placeholder="点击选择时间"
                           onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           readonly="readonly"/>至
                    <input name="creatTimeEnd" placeholder="点击选择时间"
                           onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
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
<div data-options="region:'center',border:true,title:'文章列表'">
    <table id="dataGrid" data-options="fit:true,border:false"></table>
</div>
<div data-options="region:'west',border:true,split:false,title:'文章分类'" style="width:150px;overflow: hidden; ">
    <ul id="categoryTree" style="width:160px;margin: 10px 10px 10px 10px">
    </ul>
</div>
<div id="toolbar" style="display: none;">
    <shiro:hasPermission name="/article/add">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/article/batchDel">
        <a onclick="batchDel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-del'">批量删除</a>
    </shiro:hasPermission>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title>机构管理</title>
    <script type="text/javascript">
        var treeGrid;
        $(function() {
            treeGrid = $('#treeGrid').treegrid({
                url : '${path }/category/treeGrid',
                idField : 'id',
                treeField : 'name',
                parentField : 'pid',
                fit : true,
                fitColumns : false,
                border : false,
                frozenColumns : [ [ {
                    title : 'id',
                    field : 'id',
                    width : 40,
                    hidden : true
                } ] ],
                columns : [ [ {
                    field : 'name',
                    title : '分类名称',
                    width : 180
                }, {
                    field : 'seq',
                    title : '排序',
                    width : 40
                }, {
                    field : 'iconCls',
                    title : '图标',
                    width : 100
                },  {
                    field : 'pid',
                    title : '上级资源ID',
                    width : 150,
                    hidden : true
                },  {
                    field : 'action',
                    title : '操作',
                    width : 130,
                    formatter : function(value, row, index) {
                        var str = '';
                        <shiro:hasPermission name="/category/edit">
                        str += $.formatString('<a href="javascript:void(0)" class="category-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/category/delete">
                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="category-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                        return str;
                    }
                } ] ],
                onLoadSuccess:function(data){
                    $('.category-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                    $('.category-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
                },
                toolbar : '#toolbar'
            });
        });

        function editFun(id) {
            if (id != undefined) {
                treeGrid.treegrid('select', id);
            }
            var node = treeGrid.treegrid('getSelected');
            if (node) {
                parent.$.modalDialog({
                    title : '编辑',
                    width : 500,
                    height : 300,
                    href : '${path }/category/editPage?id=' + node.id,
                    buttons : [ {
                        text : '编辑',
                        handler : function() {
                            parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                            var f = parent.$.modalDialog.handler.find('#categoryEditForm');
                            f.submit();
                        }
                    } ]
                });
            }
        }

        function deleteFun(id) {
            if (id != undefined) {
                treeGrid.treegrid('select', id);
            }
            var node = treeGrid.treegrid('getSelected');
            if (node) {
                parent.$.messager.confirm('询问', '您是否要删除当前分类？删除当前分类会连同子分类一起删除!', function(b) {
                    if (b) {
                        progressLoad();
                        $.post('${path}/category/delete', {
                            id : node.id
                        }, function(result) {
                            if (result.success) {
                                parent.$.messager.alert('提示', result.msg, 'info');
                                treeGrid.treegrid('reload');
                            }else{
                                parent.$.messager.alert('提示', result.msg, 'info');
                            }
                            progressClose();
                        }, 'JSON');
                    }
                });
            }
        }

        function addFun() {
            parent.$.modalDialog({
                title : '添加',
                width : 500,
                height : 300,
                href : '${path }/category/addPage',
                buttons : [ {
                    text : '添加',
                    handler : function() {
                        parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#categoryAddForm');
                        f.submit();
                    }
                } ]
            });
        }
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false"  style="overflow: hidden;">
        <table id="treeGrid"></table>
    </div>

    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/category/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
    </div>
</div>
</body>
</html>
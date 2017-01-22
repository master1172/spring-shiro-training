<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员薪级</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/peopleRank/dataGrid',
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

        function addFun() {
            debugger
            parent.$.modalDialog({
                title: '添加',
                width: 800,
                height: 400,
                href: '${path}/peopleRank/addPage',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        debugger
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleRankForm");
                        if(parent.checkForm()){
                        	parent.SYS_SUBMIT_FORM(f,"/peopleRank/add",function(data){
                    			if(!data["success"]){
                    				parent.$.messager.alert("提示", data["msg"], "warning");
                    			}else{
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
                width: 800,
                height: 400,
                href: '${path}/peopleRank/editPage?id='+id,
                buttons: [{
                    text: '修改',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleRankForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/peopleRank/edit",function(data){
                                if(!data["success"]){
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                }else{
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
            parent.$.messager.confirm('询问', '您是否要删除当前职级？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/peopleRank/delete',{
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

        function operateFormatter(value,row,index){
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
    </div>

    <div data-options="region:'center',border:true,title:'职级列表'">
        <table id="dataGrid" data-options="fit:true,border:false">
        	<thead>
            <tr>
                <th field="ck"            data-options="checkbox:true"></th>
                <th field="rank_level"    data-options="sortable:true" width="80">薪级</th>
                <th field="salary"        data-options="sortable:true" width="80">薪级薪资</th>
                <th field="id"            data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
            </tr>
            </thead>
        </table>
    </div>

    <div id="toolbar" style="display: none;">

        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">添加</a>

        <!-- 附件下载使用 -->
    	<form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/gray/easyui.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/icon.css" />
<script type="text/javascript" src="${staticPath }/static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- [扩展JS] -->
<script type="text/javascript" src="${staticPath }/static/extJs.js" charset="utf-8"></script>
<!-- [扩展样式] -->
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/dreamlu.css" />


<script type="text/javascript">
    var transferListGrid;

    $(function () {
        transferListGrid = $('#transferListGrid').datagrid({
            url: '${path}/peopleTransfer/transferListGrid',
            fit: true,
            striped: true,
            queryParams: {code : '${code}'},
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
            toolbar: '#transferListToolbar'
        });
    });

    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = transferListGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            transferListGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前调动记录？', function (b) {
            if (b) {
                progressLoad();
                $.post('${path}/peopleTransfer/delete',{
                    id: id
                }, function (result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        transferListGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }

    function editFun(id) {
        if (id == undefined) {
            var rows = transferListGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            transferListGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }

        parent.$.modalDialog({
            title: '修改',
            width: 800,
            height: 600,
            href: '${path}/peopleTransfer/editPage?code='+id,
            buttons: [{
                text: '修改',
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = transferListGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find("#peopleTransferEditForm");
                    //f.submit();
                    if(parent.checkForm()){
                        parent.SYS_SUBMIT_FORM(f,"/peopleTransfer/edit",function(data){
                            if(!data["success"]){
                                parent.progressClose();
                                parent.$.messager.alert("提示", data["msg"], "warning");
                            }else{
                                parent.progressClose();
                                transferListGrid.datagrid("reload");
                                parent.$.modalDialog.handler.dialog("close");
                            }
                        });
                    }
                }
            }]
        });
    }

    function addTransferFun(){
            parent.$.modalDialog({
                title: '人员调动',
                width: 700,
                height: 250,
                href: '${path}/peopleTransfer/transferPage?code=${code}',
                buttons: [{
                    text: '调动',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = transferListGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleTransferForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/peopleTransfer/transfer",function(data){
                                if(!data["success"]){
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                }else{
                                    parent.progressClose();
                                    transferListGrid.datagrid("reload");
                                    parent.$.modalDialog.handler.dialog("close");
                                }
                            });
                        }
                    }
                }]
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

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:true,title:'调动列表'">
        <table id="transferListGrid" data-options="fit:true,border:false">
            <thead>
                <tr>
                    <th field="ck"            data-options="checkbox:true"></th>
                    <th field="peopleName"    data-options="sortable:false" width="80">姓名</th>
                    <th field="fromSchool"    data-options="sortable:false" width="80">调出前单位</th>
                    <th field="toSchool"      data-options="sortable:false" width="80">调往单位</th>
                    <th field="transferDate"  data-options="sortable:false" width="80">调动日期</th>
                    <th field="id"            data-options="sortable:false,formatter:operateFormatter" width="200">操作</th>
                </tr>
            </thead>
        </table>
    </div>

    <div id="transferListToolbar" style="display: none;">
        <a onclick="addTransferFun()" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">添加调动记录</a>
        <a onclick="" href="javascript:void(0)" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">生成通知书</a>
    </div>
</div>
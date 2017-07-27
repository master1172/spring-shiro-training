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
    var timesheetGrid;

    $(function () {
        timesheetGrid = $('#timesheetGrid').datagrid({
            url: '${path}/peopleTimesheet/timesheetGrid',
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
            toolbar: '#timesheettoolbar'
        });
    });

    function addFun(){
        parent.$.modalDialog({
            title: '添加',
            width: 1000,
            height: 600,
            maximizable:true,
            href: '${path}/peopleTimesheet/addPage?peopleCode=${code}',
            buttons: [{
                text: '添加',
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = timesheetGrid;//因为添加成功之后，需要刷新这个timesheetGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find("#timesheetAddForm");
                    if(parent.checkForm()) {
                        parent.SYS_SUBMIT_FORM(f, "/peopleTimesheet/add", function (data) {
                            if (!data["success"]) {
                                parent.progressClose();
                                parent.$.messager.alert("提示", data["msg"], "warning");
                            } else {
                                parent.progressClose();
                                timesheetGrid.datagrid("reload");
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
            var rows = timesheetGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            timesheetGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }

        parent.$.modalDialog({
            title: '修改',
            width: 1000,
            height: 600,
            maximizable:true,
            href: '${path}/peopleTimesheet/editPage?id='+id,
            buttons: [{
                text: '修改',
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = timesheetGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find("#timesheetEditForm");

                    if(parent.checkForm()){
                        parent.SYS_SUBMIT_FORM(f,"/peopleTimesheet/edit",function(data){
                            if(!data["success"]){
                                parent.progressClose();
                                parent.$.messager.alert("提示", data["msg"], "warning");
                            }else{
                                parent.progressClose();
                                timesheetGrid.datagrid("reload");
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
            var rows = timesheetGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            timesheetGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前工资记录？', function (b) {
            if (b) {
                progressLoad();
                $.post('${path}/peopleTimesheet/delete',{
                    id: id
                }, function (result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        timesheetGrid.datagrid('reload');
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

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:true,title:'考勤列表'">
        <table id="timesheetGrid" data-options="fit:true,border:false">
            <thead>
            <tr>
                <th field="ck"              data-options="checkbox:true"></th>
                <th field="checkDate"       data-options="sortable:true" width="80">请假日期</th>
                <th field="status"          data-options="sortable:true" width="80">请假原因</th>
                <th field="statusExtra"     data-options="sortable:false" width="80">其他原因</th>
                <th field="vacationPeriod"  data-options="sortable:false" width="80">假期长度</th>
                <th field="id"              data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="timesheettoolbar" style="display: none;">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">添加</a>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>无固定期合同制人员薪资</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/socialSecurity/contract2DataGrid',
                fit: true,
                striped: true,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                singleSelect: false,
                selectOnCheck: false,
                checkOnSelect: true,
                sortName: 'id',
                sortOrder: 'asc',
                pageSize: 20,
                pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],

                onLoadSuccess: function (data) {
                    $('.user-easyui-linkbutton-add').linkbutton({text: '社保月度明细', plain: true, iconCls: 'icon-add'});
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '编辑', plain: true, iconCls: 'icon-edit'});
                },
                toolbar: '#toolbar'
            });
        });

        function editFunc(id) {
            if (id == undefined) {
                var rows = dataGrid.datagrid('getSelections');
                id = rows[0].id;
            }else{
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }

            parent.$.modalDialog({
                title: '修改',
                width: 600,
                height: 300,
                maximizable:true,
                href: "${path}/socialSecurity/editBasePage?id="+id,
                buttons: [{
                    text: '修改',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#baseEditForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/socialSecurity/editBase",function(data){
                                if(!data["success"]){
                                    parent.progressClose();
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
            })
        }

        function advSearch(){
            parent.$.modalDialog({
                title: '高级查询',
                width: 1000,
                height: 600,
                maximizable:true,
                href: '${path}/people/advSearchPage',
                buttons:[{
                    text: '提交',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        if(parent.checkForm()){
                            parent.progressClose();
                            var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                            dataGrid.datagrid("load",$.serializeObject(f));
                            parent.$.modalDialog.handler.dialog("close");
                        }
                    }
                }]
            });
        }

        function exportSearch(){
            parent.$.modalDialog({
                title: '导出',
                width: 1000,
                height: 600,
                maximizable:true,
                href: '${path}/examMonthly/exportSearchPage',
                buttons:[{
                    text:'导出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f, "/examMonthly/exportSearch",function(data){
                                if(!data["success"]){
                                    parent.progressClose();
                                    parent.$.modalDialog.handler.dialog("close");
                                    parent.$.messager.alert("提示",data["msg"],"warning");
                                }else{
                                    parent.progressClose();
                                    parent.$.modalDialog.handler.dialog("close");
                                    var ids = data["obj"];
                                    var form=$("#downLoadForm");
                                    form.find("input[name='ids']").val(ids);
                                    form.attr("action",'${path}'+"/examMonthly/exportExcel");
                                    $("#downLoadForm").submit();
                                }
                            });
                        }
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

        //导入Excel
        function importExcel(){
            parent.$.modalDialog({
                title: '数据导入',
                width: 500,
                height: 300,
                maximizable:true,
                href: '${path}/examMonthly/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/examMonthly/importExcel",function(data){
                                if(!data["success"]){
                                    parent.progressClose();
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

        //导出Excel
        function exportExcel(){
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if(checkedItems.length>0){
                var ids="";
                $.each(checkedItems, function(index,item){
                    if(ids.length>0)ids+=",";
                    ids+=item["id"];
                });
                var form=$("#downLoadForm");
                form.find("input[name='ids']").val(ids);
                form.attr("action",'${path}'+"/socialSecurity/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function socialSecurityList(id) {
            parent.$.modalDialog({
                title:'社保列表',
                width:1000,
                height:600,
                maximizable:true,
                href:'${path}/socialSecurity/socialSecurityListPage?id='+id,
            });
        }

        function sexFormatter(value,row,index){
            switch (value) {
                case 0:
                    return '男';
                case 1:
                    return '女';
            }
        }

        function operateFormatter(value,row,index){
            var str = '';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-add" data-options="plain:true,iconCls:\'icon-add\'" onclick="socialSecurityList(\'{0}\');" >社保月度明细</a>', row.id);
            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFunc(\'{0}\');" >编辑</a>', row.id);
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
                <td>
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
    <table id="dataGrid" data-options="fit:true,border:false">
        <thead>
        <tr>
            <th field="ck"            data-options="checkbox:true"></th>
            <th field="name"          data-options="sortable:true" width="80">姓名</th>
            <th field="lifeInsuranceBase"  data-options="sortable:true" width="100">养老保险基数</th>
            <th field="jobInsuranceBase"      data-options="sortable:true" width="100">失业保险基数</th>
            <th field="woundInsuranceBase"    data-options="sortable:true" width="100">工伤保险基数</th>
            <th field="birthInsuranceBase" data-options="sortable:true" width="100">生育保险基数</th>
            <th field="healthInsuranceBase"    data-options="sortable:true" width="100">医疗保险基数</th>
            <th field="annuityBase"       data-options="sortable:true" width="100">职业年金基数</th>
            <th field="id"          data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
        </tr>
        </thead>
    </table>
</div>

<div id="toolbar" style="display: none;">
    <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导出Excel</a>
    <!-- 附件下载使用 -->
    <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
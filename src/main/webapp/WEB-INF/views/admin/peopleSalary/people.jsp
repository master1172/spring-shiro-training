<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在编人员薪资</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/peopleSalary/dataGrid',
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
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '编辑', plain: true, iconCls: 'icon-edit'});
                    $('.user-easyui-linkbutton-add').linkbutton({text: '工资明细', plain: true, iconCls: 'icon-add'});
                    $('.user-easyui-linkbutton-query').linkbutton({text: '工资变动记录', plain: true, iconCls: 'icon-add'});
                },
                toolbar: '#toolbar'
            });
        });

        //导出Word
        function exportCert() {
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if (checkedItems.length == 1) {
                var id = checkedItems[0]["id"];
                var form = $("#downLoadForm");
                form.find("input[name='ids']").val(id);
                form.attr("action", '${path}' + "/peopleSalary/exportCert");
                $("#downLoadForm").submit();
            } else {
                parent.$.messager.alert("提示", "请选择一条有效数据", "warning");
            }
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
                width: 1000,
                height: 600,
                href: '${path}/peopleSalary/editPage?id=' + id,
                buttons: [{
                    text: '修改',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#salaryBaseEditForm");
                        //f.submit();
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/peopleSalary/edit", function (data) {
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

        function advSearch(){
            parent.$.modalDialog({
                title: '高级查询',
                width: 1000,
                height: 600,
                href: '${path}/peopleSalary/advSearchPage',
                buttons:[{
                    text: '提交',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        if(parent.checkForm()){
                            parent.progressClose();
                            var f = parent.$.modalDialog.handler.find("#salarySearchForm");
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
                href: '${path}/peopleSalary/exportSearchPage',
                buttons:[{
                    text:'导出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#salarySearchForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f, "/peopleSalary/exportSearch",function(data){
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
                                    form.attr("action",'${path}'+"/peopleSalary/exportSalaryBaseExcel");
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

        function autoCalculateSalary(){
            parent.$.modalDialog({
                title: '选择计算工资月份',
                width: 500,
                height: 300,
                href: '${path}/peopleSalary/selectPayDate',
                buttons: [{
                    text: '计算',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid; //因为调出成功后，需要刷新这个datagrid
                        var f = parent.$.modalDialog.handler.find("#selectDateForm");
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/peopleSalary/autoCalculateSalary", function (data) {
                                if (!data["success"]) {
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                } else {
                                    parent.progressClose();
                                    parent.$.message.alert("提示","计算完成","info");
                                    dataGrid.datagrid("reload");
                                    parent.$.modalDialog.handler.dialog("close");
                                }
                            });
                        }
                    }
                }]
            });
        }

        //导入Excel
        function importExcel(){
            parent.$.modalDialog({
                title: '数据导入',
                width: 500,
                height: 300,
                href: '${path}/peopleSalary/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/peopleSalary/importExcel",function(data){
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
                form.attr("action",'${path}'+"/peopleSalary/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function exportExcelForMonth(){
            var payDate = $("#payDate").val();
            var form=$("#downLoadForm");
            form.find("input[name='ids']").val(payDate);
            form.attr("action",'${path}'+"/peopleSalary/exportExcelForMonth");
            $("#downLoadForm").submit();
        }

        function salaryList(id) {
            parent.$.modalDialog({
                title:'工资列表',
                width:1000,
                height:600,
                maximizable:true,
                href:'${path}/peopleSalary/salaryListPage?id='+id,
            });
        }

        function changeList(id){
            parent.$.modalDialog({
                title:'工资变动记录',
                width:1000,
                height:600,
                maximizable:true,
                href:'${path}/salaryChangeRecord/changeListPage?id='+id,
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
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-add" data-options="plain:true,iconCls:\'icon-edit\'" onclick="salaryList(\'{0}\');" >工资明细</a>', row.id);
            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-query" data-options="plain:true,iconCls:\'icon-edit\'" onclick="changeList(\'{0}\');" >工资变动记录</a>', row.id);
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
                    <input name="peopleName" placeholder="请输入人员姓名"/>
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
                    <input name="birthdayMin" placeholder="点击选择起始时间"
                           onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                           readonly="readonly"/>至
                    <input name="birthdayMax" placeholder="点击选择结束时间"
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
    <table id="dataGrid" data-options="fit:true,border:false">
        <thead>
        <tr>
            <th field="ck"            data-options="checkbox:true"></th>
            <th field="peopleName"    data-options="sortable:true" width="80">姓名</th>
            <th field="jobSalary"     data-options="sortable:true" width="80">职级工资</th>
            <th field="rankSalary"    data-options="sortable:true" width="80">薪级工资</th>
            <th field="reserveSalary"   data-options="sortable:false" width="80">工改保留工资</th>
            <th field="jobAllowance"    data-options="sortable:false" width="80">岗位津贴</th>
            <th field="performanceAllowance" data-options="sortable:false" width="80">绩效津贴</th>
            <th field="rentAllowance"   data-options="sortable:false" width="80">提租补贴</th>
            <th field="houseAllowance"  data-options="sortable:false" width="80">购房补贴</th>
            <th field="dutyAllowance"   data-options="sortable:false" width="80">职务补贴</th>
            <th field="extraAllowance"  data-options="sortable:false" width="80">适当补贴</th>
            <th field="telephoneAllowance" data-options="sortable:false" width="80">通讯补贴</th>
            <th field="propertyAllowance" data-options="sortable:false" width="80">物业补贴</th>
            <th field="extraJobAllowance" data-options="sortable:false" width="80">挂职补贴</th>
            <th field="id"            data-options="sortable:true,formatter:operateFormatter" width="300">操作</th>
        </tr>
        </thead>
    </table>
</div>

<div id="toolbar" style="display: none;">
        <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">导出Excel</a>
        <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">导入Excel</a>
        <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">高级查询</a>
        <a onclick="exportSearch();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">工资汇总表格</a>
        <a onclick="exportCert();" href="javascript:void(0);" class="easyui-linkbutton"
            data-options="plain:true,iconCls:'icon-add'">生成收入证明</a>
        <a onclick="autoCalculateSalary();" href="javascript:void(0);" class="easyui-linkbutton"
            data-options="plain:true,iconCls:'icon-add'">工资自动计算</a>
        <a onclick="exportExcelForMonth();" href="javascript:void(0);" class="easyui-linkbutton"
            data-options="plain:true,iconCls:'icon-add'">导出所选月份所有人工资信息</a>
        <input id="payDate" name="payDate" placeholder="点击选择时间"
           onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM',
                                maxDate:'%y-%M-%d',
                                })"
           readonly="readonly"/>
        <!-- 附件下载使用 -->
        <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
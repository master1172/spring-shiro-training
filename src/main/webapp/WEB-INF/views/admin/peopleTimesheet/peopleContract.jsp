<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在编人员考勤</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/peopleTimesheet/peopleContractdataGrid',
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
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '考勤明细', plain: true, iconCls: 'icon-edit'});
                },
                toolbar: '#toolbar'
            });
        });

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
                href: '${path}/peopleTimesheet/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/peopleTimesheet/importExcel",function(data){
                                if(!data["success"]){
                                    parent.progressClose();
                                    parent.$.messager.alert("提示", data["msg"], "warning");
                                }else{
                                    parent.progressClose();
                                    parent.$.messager.alert('提示', data["msg"], 'info');
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
            var selectDate = $("#selectDate").val();
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if(checkedItems.length>0){
                var ids="";
                $.each(checkedItems, function(index,item){
                    if(ids.length>0)ids+=",";
                    ids+=item["id"];
                });
                var form=$("#downLoadForm");
                form.find("input[name='ids']").val(ids);
                form.find("input[name='selectDate']").val(selectDate);
                form.attr("action",'${path}'+"/peopleTimesheet/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function vacationSum(){
            parent.$.modalDialog({
                title: '导出',
                width: 600,
                height: 400,
                maximizable:true,
                href: '${path}/peopleTimesheet/dateRangePage',
                buttons: [{
                    text: '导出',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#timesheetQueryForm");
                        if (parent.checkForm()) {
                            parent.SYS_SUBMIT_FORM(f, "/peopleTimesheet/exportVacationSum", function (data) {
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
                                    form.attr("action", '${path}' + "/peopleTimesheet/peopleContractExportVacationResult");
                                    $("#downLoadForm").submit();
                                }
                            });
                        }
                    }
                }]
            });
        }


        function timesheetList(id) {
            parent.$.modalDialog({
                title:'考勤列表',
                width:1000,
                height:600,
                maximizable:true,
                href:'${path}/peopleTimesheet/timesheetListPage?id='+id,
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

            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="timesheetList(\'{0}\');" >考勤明细</a>', row.id);

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
            <th field="name"          data-options="sortable:true" width="80">姓名</th>
            <th field="vacationSum"  data-options="sortable:true" width="80">请假累计</th>
            <th field="id"            data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
        </tr>
        </thead>
    </table>
</div>

<div id="toolbar" style="display: none;">
    <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导出所选月份的Excel</a>
    <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导入Excel</a>
    <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">高级查询</a>
    <a onclick="vacationSum();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">假期使用和剩余统计</a>
    <input id="selectDate" name="selectDate" placeholder="点击选择时间"
           onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM',
                                maxDate:'%y-%M-%d',
                                })"
           readonly="readonly"/>
    <!-- 附件下载使用 -->
    <form id="downLoadForm" method="GET" action="">
        <input type="hidden" name="ids"/>
        <input type="hidden" name="selectDate"/>
    </form>
</div>
</body>
</html>
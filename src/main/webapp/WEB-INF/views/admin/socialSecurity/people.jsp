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
                url: '${path}/socialSecurity/dataGrid',
                fit: true,
                striped: true,
                queryParams: {status : '0'},
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
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '月度考评明细', plain: true, iconCls: 'icon-edit'});
                },
                toolbar: '#toolbar'
            });
        });

        function advSearch(){
            parent.$.modalDialog({
                title: '高级查询',
                width: 1000,
                height: 600,
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
                form.attr("action",'${path}'+"/examMonthly/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function examMonthlyList(id) {
            parent.$.modalDialog({
                title:'工资列表',
                width:1000,
                height:600,
                href:'${path}/examMonthly/examMonthlyListPage?id='+id,
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

            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="examMonthlyList(\'{0}\');" >月度考核明细</a>', row.id);

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
    <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">导入Excel</a>
    <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
       data-options="plain:true,iconCls:'icon-add'">高级查询</a>
    <!-- 附件下载使用 -->
    <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
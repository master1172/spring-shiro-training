<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合同制人员管理</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/peopleTransfer/dataGrid',
                fit: true,
                striped: true,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                idField: 'peopleCode',
                singleSelect: false,
                selectOnCheck: false,
                checkOnSelect: true,
                sortName: 'id',
                sortOrder: 'asc',
                pageSize: 20,
                pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],

                onLoadSuccess: function (data) {
                    $('.user-easyui-linkbutton-edit').linkbutton({text: '调动记录列表', plain: true, iconCls: 'icon-edit'});
                },
                toolbar: '#toolbar'
            });
        });

        function advSearch(){
            parent.$.modalDialog({
                title: '高级查询',
                width: 500,
                height: 350,
                href: '${path}/peopleTransfer/advSearchPage',
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
                width: 500,
                height: 350,
                href: '${path}/peopleTransfer/exportSearchPage',
                buttons:[{
                    text:'导出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f, "/peopleTransfer/exportSearch",function(data){
                                if(!data["success"]){
                                    parent.$.messager.alert("提示",data["msg"],"warning");
                                }else{
                                    parent.progressClose();
                                    parent.$.modalDialog.handler.dialog("close");
                                    var ids = data["obj"];
                                    var form=$("#downLoadForm");
                                    form.find("input[name='ids']").val(ids);
                                    form.attr("action",'${path}'+"/peopleTransfer/exportExcel");
                                    $("#downLoadForm").submit();
                                }
                            });
                        }
                    }
                }]
            });
        }

        function exportExcel(){
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if(checkedItems.length > 0){
                var ids=[];
                $.each(checkedItems,function(index,item){
                    ids.push(item.id);
                });
                var form=$("#downLoadForm");
                form.find("input[name='ids']").val(ids.join(","));
                form.attr("action",'${path}'+"/peopleTransfer/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择至少一条记录", "warning");
            }
        }

        function transferBack(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index,item){
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要调回所选人员？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/peopleTransfer/transferBack', {
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

        function transferFun(){
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if(checkedItems.length==1){
                var id = checkedItems[0]["id"];
                parent.$.modalDialog({
                    title: '人员调动',
                    width: 700,
                    height: 250,
                    href: '${path}/peopleTransfer/transferPage?id='+id,
                    buttons: [{
                        text: '调动',
                        handler: function () {
                            parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                            var f = parent.$.modalDialog.handler.find("#peopleTransferForm");
                            //f.submit();
                            if(parent.checkForm()){
                                parent.SYS_SUBMIT_FORM(f,"/peopleTransfer/transfer",function(data){
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
            }else{
                parent.$.messager.alert("提示","请选择一条有效数据","warning");
            }
        }

        function transferList(id) {
            parent.$.modalDialog({
                title:'调动列表',
                width:1000,
                height:600,
                href:'${path}/peopleTransfer/transferListPage?id='+id,
            });
        }

        function searchFun() {
            dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        }

        function cleanFun() {
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {});
        }

        //导出Excel
        function exportExcel(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');

            alert(checkedItems.length);

            if(checkedItems.length > 0){

                var ids=[];
                $.each(checkedItems, function(index,item){
                    ids.push(item.id);
                });

                alert(ids.join(","));

                var form=$("#downLoadForm");
                form.find("input[name='ids']").val(ids.join(","));
                form.attr("action",'${path}'+"/peopleTransfer/exportExcel");
                $("#downLoadForm").submit();
            }else{
                parent.$.messager.alert("提示", "请选择有效数据", "warning");
            }
        }

        function transferedPeople(){
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {"transfer":"1"});
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
                str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="transferList(\'{0}\');" >调动记录列表</a>', row.id);
            return str;
        }
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
    <form id="searchForm">
        <table>
            <tr>
                <th>人员姓名:</th>
                <td>
                    <input name="peopleName"/>
                </td>
                <th>调入调出日期</th>
                <td>
                    <input name="transferDateMin" placeholder="点击选择起始时间"
                           onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                           readonly="readonly"/>至
                    <input name="transferDateMax" placeholder="点击选择结束时间"
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
            <th field="sex"           data-options="sortable:true,formatter:sexFormatter" width="40">性别</th>
            <th field="nationalName"  data-options="sortable:true" width="80">民族</th>
            <th field="birthday"      data-options="sortable:true" width="130">生日</th>
            <th field="nativeName"    data-options="sortable:true" width="80">籍贯</th>
            <th field="educationName" data-options="sortable:true" width="80">学历</th>
            <th field="degreeName"    data-options="sortable:true" width="80">学位</th>
            <th field="jobName"       data-options="sortable:true" width="80">职务</th>
            <th field="jobCategory"   data-options="sortable:true" width="80">人员类别</th>
            <th field="jobLevelName"  data-options="sortable:true" width="80">职级</th>
            <th field="peopleCode"    data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
        </tr>
        </thead>
    </table>
</div>

<div id="toolbar" style="display: none;">
        <a onclick="transferFun();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">人员调动</a>
        <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">导出Excel</a>
        <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">高级查询</a>
        <a onclick="exportSearch();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">查询导出</a>
        <a onclick="transferedPeople();" href="javascript:void(0)" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">目前调出人员</a>
        <a onclick="transferBack();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">调回</a>
    <!-- 附件下载使用 -->
    <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
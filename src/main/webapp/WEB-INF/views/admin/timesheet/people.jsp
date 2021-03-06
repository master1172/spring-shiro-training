<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考勤管理</title>
    <script type="text/javascript">
        var dataGrid;

        $(function () {
            dataGrid = $('#dataGrid').datagrid({
                url: '${path}/timesheet/dataGrid',
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
                        $('.user-easyui-linkbutton-del').linkbutton({text: '删除', plain: true, iconCls: 'icon-del'});
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
                href: '${path}/timesheet/advSearchPage',
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
                href: '${path}/timesheet/exportSearchPage',
                buttons:[{
                    text:'导出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f, "/timesheet/exportSearch",function(data){
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
                                    form.attr("action",'${path}'+"/timesheet/exportExcel");
                                    $("#downLoadForm").submit();
                                }
                            });
                        }
                    }
                }]
            });
        }

        function goTransfer(){
            var checkedItems = $("#dataGrid").datagrid("getChecked");
            if(checkedItems.length==1){
                var id=checkedItems[0]["id"];
            }else{
                parent.$.messager.alert("提示", "请选择一条有效数据", "warning");
            }

            parent.$.modalDialog({
                title: '调出本单位',
                width: 800,
                height: 400,
                maximizable:true,
                href: '${path}/timesheet/transferPage?id='+checkedItems[0]["id"],
                buttons:[{
                    text: '调出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid; //因为调出成功后，需要刷新这个datagrid
                        var f = parent.$.modalDialog.handler.find("#peopleTransferForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/timesheet/transfer",function(data){
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

        function addFun() {
            parent.$.modalDialog({
                title: '添加',
                width: 1200,
                height: 500,
                maximizable:true,
                href: '${path}/timesheet/addPage',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleAddForm");
                        if(parent.checkForm()){
                        	parent.SYS_SUBMIT_FORM(f,"/timesheet/add",function(data){
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
                width: 1200,
                height: 500,
                maximizable:true,
                href: '${path}/timesheet/editPage?id='+id,
                buttons: [{
                    text: '修改',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#peopleEditForm");
                        //f.submit();
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f,"/timesheet/edit",function(data){
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
            parent.$.messager.confirm('询问', '您是否要删除当前人员？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/timesheet/delete',{
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

        function batchDel(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index,item){
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要删除所选人员？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/timesheet/batchDel', {
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

        function goRetire(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index,item){
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要将所选人员转入退休库？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/timesheet/batchRetire', {
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

        function goDeath(){
            var checkedItems = $('#dataGrid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index,item){
                ids.push(item.id);
            });

            parent.$.messager.confirm('询问', '您是否要将所选人员转入逝世库？', function (b) {
                if (b) {
                    progressLoad();
                    $.post('${path}/timesheet/batchDeath', {
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

        function nearRetire(){
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {"nearRetire":"1"});
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
                href: '${path}/timesheet/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if(parent.checkForm()){
                        	parent.SYS_SUBMIT_FORM(f,"/timesheet/importExcel",function(data){
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
				form.attr("action",'${path}'+"/timesheet/exportExcel");
				$("#downLoadForm").submit();
			}else{
				parent.$.messager.alert("提示", "请选择有效数据", "warning");
			}
        }
        //导出Word
        function exportWord(){
			var checkedItems = $("#dataGrid").datagrid("getChecked");
			if(checkedItems.length==1){
				var id=checkedItems[0]["id"];
				var form=$("#downLoadForm");
				form.find("input[name='ids']").val(id);
				form.attr("action",'${path}'+"/timesheet/exportWord");
				$("#downLoadForm").submit();
			}else{
				parent.$.messager.alert("提示", "请选择一条有效数据", "warning");
			}
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
             <shiro:hasPermission name="/timesheet/edit">
                 str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
             </shiro:hasPermission>
             <shiro:hasPermission name="/timesheet/delete">
                 str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                 str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
             </shiro:hasPermission>
             return str;
        }
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
        <form id="searchForm">
            <table>
 				<tr>
                    <td>人员编号</td>
                    	<td><input name="peopleCode" type="text" placeholder="请输入人员编号" class="easyui-validatebox" value=""></td>
                    <td>人员类型</td>
                    <td>
                       <td><input name="peopleType" type="text" placeholder="请输入人员类型" class="easyui-validatebox" value=""></td>
                    </td>
                    <td>考勤日期范围</td>
                    <td colspan="3">
                        <input name="checkDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="checkDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>考勤结果</td>
                    <td>
                        <input type="text" name="status" id="status">
                    </td>
                    <td>假期单位</td>
                    <td>
                        <input type="text" name="vacationPeriod">
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

    <div data-options="region:'center',border:true,title:'考勤列表'">
        <table id="dataGrid" data-options="fit:true,border:false">
        	<thead>
            <tr>
                <th field="ck"             data-options="checkbox:true"></th>
                <th field="peopleCode"     data-options="sortable:true" width="80">人员编号</th>
                <th field="peopleType"     data-options="sortable:true" width="80">人员类型</th>
                <th field="checkDate"     placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"
                                 data-options="sortable:true" width="130">考勤日期</th>
                <th field="status"         data-options="sortable:true" width="80">考勤结果</th>
                <th field="vacationPeriod" data-options="sortable:true" width="80">假期单位</th>
                <th field="id"      data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
            </tr>
            </thead>
        </table>
    </div>

    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/timesheet/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/timesheet/importExcel">
            <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">导入</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/timesheet/exportExcel">
            <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">导出Excel</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/timesheet/exportWord">
            <a onclick="exportWord();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">导出Word</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/timesheet/advSearch">
            <a onclick="advSearch();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">高级查询</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/timesheet/exportSearch">
            <a onclick="exportSearch();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">查询导出</a>
        </shiro:hasPermission>

        <!-- 附件下载使用 -->
    	<form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在编人员职级</title>
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

        function advSearch(){
            parent.$.modalDialog({
                title: '高级查询',
                width: 1000,
                height: 600,
                href: '${path}/peopleRank/advSearchPage',
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
                href: '${path}/peopleRank/exportSearchPage',
                buttons:[{
                    text:'导出',
                    handler: function(){
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find("#peopleSearchForm");
                        if(parent.checkForm()){
                            parent.SYS_SUBMIT_FORM(f, "/peopleRank/exportSearch",function(data){
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
                                    form.attr("action",'${path}'+"/peopleRank/exportExcel");
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
                href: '${path}/peopleRank/importExcelPage',
                buttons: [{
                    text: '导入',
                    handler: function () {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#importExcelForm");
                        //f.submit();
                        if(parent.checkForm()){
                        	parent.SYS_SUBMIT_FORM(f,"/peopleRank/importExcel",function(data){
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
				form.attr("action",'${path}'+"/peopleRank/exportExcel");
				$("#downLoadForm").submit();
			}else{
				parent.$.messager.alert("提示", "请选择有效数据", "warning");
			}
        }
		
        function jobList(value,row,index){//薪级列表，please pay more attention about this funtion
		parent.$.modalDialog({
                title:'薪级列表',
                width:1000,
                height:600,
                href:'${path}/peopleRank/jobListPage?id='+id,
            });
        	
        }
        
        function operateFormatter(value,row,index){
        	 var str = '';

            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="jobList(\'{0}\');" >职级明细</a>', row.id);

            return str;
        }
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden; background-color: #fff">
        <form id="searchForm">
            <table>
                <tr>
                    <th>人员类别:</th>
                    <td>
                        <input name="sarlay" placeholder="请输入工资"/>
                    </td>
                    <th>职级:</th>
                    <td>
                        <select name="">
                            <option value="rank_level" selected>请选择</option>
                            <option value="1">1级</option>
                            <option value="2">2级</option>
                            <option value="3">3级</option>
                            <option value="4">4级</option>
                            <option value="5">5级</option>
                            <option value="6">6级</option>
                            <option value="7">7级</option>
                            <option value="8">8级</option>
                            <option value="9">9级</option>
                            <option value="10">10级</option>
                            <option value="11">11级</option>
                            <option value="12">12级</option>
                            <option value="13">13级</option>
                            <option value="14">14级</option>
                            <option value="15">15级</option>
                            <option value="16">16级</option>
                            <option value="17">17级</option>
                            <option value="18">18级</option>
                            <option value="19">19级</option>
                            <option value="20">20级</option>
                            <option value="21">21级</option>
                            <option value="22">22级</option>
                            <option value="23">23级</option>
                            <option value="24">24级</option>
                            <option value="25">25级</option>
                            <option value="26">26级</option>
                            <option value="27">27级</option>
                            <option value="28">28级</option>
                            <option value="29">29级</option>
                            <option value="30">30级</option>
                            <option value="31">31级</option>
                            <option value="32">32级</option>
                            <option value="33">33级</option>
                            <option value="34">34级</option>
                            <option value="35">35级</option>
                            <option value="36">36级</option>
                            <option value="37">37级</option>
                            <option value="38">38级</option>
                            <option value="39">39级</option>
                            <option value="40">40级</option>
                            <option value="41">41级</option>
                            <option value="42">42级</option>
                            <option value="43">43级</option>
                            <option value="44">44级</option>
                            <option value="45">45级</option>
                            <option value="46">46级</option>
                            <option value="47">47级</option>
                            <option value="48">48级</option>
                            <option value="49">49级</option>
                            <option value="50">50级</option>
                            <option value="51">51级</option>
                            <option value="52">52级</option>
                            <option value="53">53级</option>
                            <option value="54">54级</option>
                            <option value="55">55级</option>
                            <option value="56">56级</option>
                            <option value="57">57级</option>
                            <option value="57">58级</option>
                            <option value="59">59级</option>
                            <option value="60">60级</option>

                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div data-options="region:'center',border:true,title:'职级列表'">
        <table id="dataGrid" data-options="fit:true,border:false">
        	<thead>
            <tr>
                <th field="ck"            data-options="checkbox:true"></th>
                <th field="rank_level"          data-options="sortable:true,formatter:categoryFormatter" width="80">人员类别</th>
                <th field="salary"  data-options="sortable:true" width="80">薪级工资</th>
                <th field="id"            data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
            </tr>
            </thead>
        </table>
    </div>

    <div id="toolbar" style="display: none;">
        <a onclick="importExcel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">导入</a>
        <a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">导出Excel</a>
        <a onclick="exportSearch();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">查询导出</a>
        <!-- 附件下载使用 -->
        <form id="downLoadForm" method="GET" action=""><input type="hidden" name="ids"/></form>
</div>
</body>
</html>
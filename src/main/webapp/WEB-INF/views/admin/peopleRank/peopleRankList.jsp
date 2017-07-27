<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<script type="text/javascript">
    var salaryGrid;

    $(function () {
        rankGrid = $('#rankGrid').datagrid({
            url: '${path}/peopleRank/rankGrid',
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
            toolbar: '#salarytoolbar'
        });
    });

    function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }

        parent.$.modalDialog({
            title: '修改',
            width: 1500,
            height: 600,
            maximizable:true,
            href: '${path}/peopleRank/editPage?id='+id,
            buttons: [{
                text: '修改',
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = salaryGrid;//因为修改成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find("#peopleRankEditForm");
                    //f.submit();
                    if(parent.checkForm()){
                        parent.SYS_SUBMIT_FORM(f,"/peopleRank/edit",function(data){
                            if(!data["success"]){
                                parent.$.messager.alert("提示", data["msg"], "warning");
                            }else{
                                parent.progressClose();
                                salaryGrid.datagrid("reload");
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
            var rows = rankGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            rankGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前薪级记录？', function (b) {
            if (b) {
                progressLoad();
                $.post('${path}/peopleRank/delete',{
                    id: id
                }, function (result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        rankGrid.datagrid('reload');
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
    <div data-options="region:'center',border:true,title:'薪级列表'">
        <table id="salaryGrid" data-options="fit:true,border:false">
            <thead>
            <tr>
                <th field="ck"              data-options="checkbox:true"></th>
                <th field="ran_level"        data-options="sortable:false" width="80">薪级</th>
                <th field="salary"       data-options="sortable:false" width="80">薪级工资</th>
                <th field="id"              data-options="sortable:true,formatter:operateFormatter" width="200">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
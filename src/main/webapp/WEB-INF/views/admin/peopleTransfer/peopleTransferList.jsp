<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

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
        });
    });
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:true,title:'调动列表'">
        <table id="transferListGrid" data-options="fit:true,border:false">
            <thead>
                <tr>
                    <th field="peopleName"    data-options="sortable:false" width="80">姓名</th>
                    <th field="fromSchool"    data-options="sortable:false" width="80">调出前单位</th>
                    <th field="toSchool"      data-options="sortable:false" width="80">调往单位</th>
                    <th field="transferDate"  data-options="sortable:false" width="80">调动日期</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
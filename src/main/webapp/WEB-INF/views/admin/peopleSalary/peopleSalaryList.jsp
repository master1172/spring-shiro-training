<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<script type="text/javascript">
    var salaryGrid;

    $(function () {
        salaryGrid = $('#salaryGrid').datagrid({
            url: '${path}/peopleSalary/salaryGrid',
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
    <div data-options="region:'center',border:true,title:'工资发放列表'">
        <table id="salaryGrid" data-options="fit:true,border:false">
            <thead>
            <tr>
                <th field="ck"              data-options="checkbox:true"></th>
                <th field="payDate"         data-options="sortable:false" width="80">发放日期</th>
                <th field="jobCategory"     data-options="sortable:false" width="80">岗位分类</th>
                <th field="jobLevel"        data-options="sortable:false" width="80">职级</th>
                <th field="jobSalary"       data-options="sortable:false" width="80">岗位工资</th>
                <th field="rank"            data-options="sortable:false" width="80">薪级</th>
                <th field="rankSalary"      data-options="sortable:false" width="80">薪级工资</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
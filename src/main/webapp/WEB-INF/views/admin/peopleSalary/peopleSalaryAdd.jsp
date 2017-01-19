<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">

    function checkForm(){
        progressLoad();
        var isValid = $("#salaryAddForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }

</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryAddForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${people.code}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${people.name}" class="easyui-validatebox" data-options="required:true" value="">
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>薪级</td>
                    <td>
                        <input class="easyui-combobox" id="rankId" name="rankId" url="${path}/dict/rank" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>薪级工资</td>
                    <td>
                        <input name="rankSalary" id="rankSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>工改保留工资</td>
                    <td>
                        <input name="reserveSalary" id="reserveSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>岗位考核结果</td>
                    <td>
                        <input type="text" name="examResult">
                    </td>
                    <td>岗位津贴</td>
                    <td>
                        <input name="jobAllowance" id="jobAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>绩效津贴</td>
                    <td>
                        <input name="performanceAllowance" id="performanceAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>购房补贴</td>
                    <td>
                        <input name="houseAllowance" id="houseAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
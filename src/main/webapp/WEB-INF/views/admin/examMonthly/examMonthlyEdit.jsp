<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {
        $("#examResult").val('${examMonthly.examResult}')
    });

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
        <form id="examMonthlyEditForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="id" value="${examMonthly.id}">
                <input type="hidden" name="peopleCode" value="${examMonthly.peopleCode}">
                <tr>
                    <td>考核日期</td>
                    <td>
                        <input id="examDate" name="examDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                    readOnly:true,
                                    dateFmt:'yyyy-MM',
                                    maxDate:'%y-%M-%d'
                                })"
                               readonly="readonly"
                               value="${examMonthly.examDate}"
                        />
                    </td>
                </tr>
                <tr>
                    <td>考核结果</td>
                    <td>
                        <select id="examResult" name="examResult" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="A" >A</option>
                            <option value="B" >B</option>
                            <option value="C" >C</option>
                            <option value="D" >D</option>
                            <option value="E" >E</option>
                        </select>
                    </td>
                    <td>考核运用</td>
                    <td>
                        <input type="text" name="examOperation" value="${examMonthly.examOperation}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
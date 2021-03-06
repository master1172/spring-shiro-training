<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#jobId").val('${peopleContractSalaryBase.jobId}');

        $("#jobId").combobox({
            onChange:function(newValue,oldValue){
                if (newValue == oldValue)
                    return;

                $.post('${path}/peopleJob/getValue', {id:newValue},
                        function(data){
                            $("#jobSalary").numberbox('setValue',data);
                        });
            }
        });

    });

    function checkForm(){
        progressLoad();
        var isValid = $("#salaryBaseEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryBaseEditForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="id" value="${peopleContractSalaryBase.id}">
                <input type="hidden" name="peopleCode" value="${peopleContractSalaryBase.peopleCode}">
                <input type="hidden" name="name" value="${peopleContractSalaryBase.peopleName}">
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" value="${peopleContractSalaryBase.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>院龄工资</td>
                    <td>
                        <input name="schoolSalary" id="schoolSalary" type="text" value="${peopleContractSalaryBase.schoolSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>岗位考核工资</td>
                    <td>
                        <input name="jobExamSalary" id="jobExamSalary" type="text"  value="${peopleContractSalaryBase.jobExamSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>通讯补贴</td>
                    <td>
                        <input name="telephoneAllowance" id="telephoneAllowance" type="text"  value="${peopleContractSalaryBase.telephoneAllowance}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>交通补贴</td>
                    <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text"  value="${peopleContractSalaryBase.trafficAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>特殊补贴</td>
                    <td>
                        <input name="specialAllowance" id="specialAllowance" type="text"  value="${peopleContractSalaryBase.specialAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>领班补贴</td>
                    <td>
                        <input name="headAllowance" id="headAllowance" type="text"  value="${peopleContractSalaryBase.headAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>每日加班费</td>
                    <td>
                        <input name="onDutyFee" id="onDutyFee" type="text"  value="${peopleContractSalaryBase.onDutyFee}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>奖金</td>
                    <td>
                        <input name="bonus" id="bonus" type="text"  value="${peopleContractSalaryBase.bonus}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text"  value="${peopleContractSalaryBase.reissueFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>降温补贴</td>
                    <td>
                        <input name="temperatureAllowance" id="temperatureAllowance" type="text"  value="${peopleContractSalaryBase.temperatureAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text"  value="${peopleContractSalaryBase.lifeInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text"  value="${peopleContractSalaryBase.jobInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text"  value="${peopleContractSalaryBase.healthInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text"  value="${peopleContractSalaryBase.houseFund}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#jobId").val('${peopleSalaryBase.jobId}');
        $("#rankId").val('${peopleSalaryBase.rankId}');

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

        $("#rankId").combobox({
            onChange:function (newValue,oldValue) {
                if (newValue == oldValue)
                    return;
                $.post('${path}/peopleRank/getValue',{id:newValue},
                        function(data){
                            $("#rankSalary").numberbox('setValue',data);
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
                <input type="hidden" name="id" value="${peopleSalaryBase.id}">
                <input type="hidden" name="peopleCode" value="${peopleSalaryBase.peopleCode}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${peopleSalaryBase.peopleName}" class="easyui-validatebox" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" value="${peopleSalaryBase.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>薪级</td>
                    <td>
                        <input class="easyui-combobox" id="rankId" name="rankId" url="${path}/dict/rank" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>薪级工资</td>
                    <td>
                        <input name="rankSalary" id="rankSalary" type="text" value="${peopleSalaryBase.rankSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>工改保留工资</td>
                    <td>
                        <input name="reserveSalary" id="reserveSalary" type="text" value="${peopleSalaryBase.reserveSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>岗位津贴</td>
                    <td>
                        <input name="jobAllowance" id="jobAllowance" type="text" value="${peopleSalaryBase.jobAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>绩效津贴</td>
                    <td>
                        <input name="performanceAllowance" id="performanceAllowance" type="text" value="${peopleSalaryBase.performanceAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" type="text" value="${peopleSalaryBase.rentAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>购房补贴</td>
                    <td>
                        <input name="houseAllowance" id="houseAllowance" type="text" value="${peopleSalaryBase.houseAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>职务补贴</td>
                    <td>
                        <input name="dutyAllowance" id="dutyAllowance" type="text" value="${peopleSalaryBase.dutyAllowance}"class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>

                    <td>适当补贴</td>
                    <td>
                        <input name="extraAllowance" id="extraAllowance" type="text" value="${peopleSalaryBase.extraAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>通讯补贴</td>
                    <td>
                        <input name="telephoneAllowance" id="telephoneAllowance" type="text" value="${peopleSalaryBase.telephoneAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>交通补贴</td>
                    <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text" value="${peopleSalaryBase.trafficAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>单日值班费</td>
                    <td>
                        <input name="onDutyFee" id="onDutyFee" type="text" value="${peopleSalaryBase.onDutyFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>物业补贴</td>
                    <td>
                        <input name="propertyAllowance" id="propertyAllowance" type="text" value="${peopleSalaryBase.propertyAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>挂职补贴</td>
                    <td>
                        <input name="extraJobAllowance" id="extraJobAllowance" type="text" value="${peopleSalaryBase.extraJobAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>降温补贴</td>
                    <td>
                        <input name="temperatureAllowance" id="temperatureAllowance" type="text" value="${peopleSalaryBase.temperatureAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text" value="${peopleSalaryBase.reissueFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>药费报销</td>
                    <td>
                        <input name="medicare" id="medicare" type="text" value="${peopleSalaryBase.medicare}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>一次性年终奖励</td>
                    <td>
                        <input name="yearlyBonus" id="yearlyBonus" type="text" value="${peopleSalaryBase.yearlyBonus}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>养老保险基数</td>
                    <td>
                        <input name="lifeInsuranceBase" id="lifeInsuranceBase" type="text" value="${peopleSalaryBase.lifeInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险基数</td>
                    <td>
                        <input name="jobInsuranceBase" id="jobInsuranceBase" type="text" value="${peopleSalaryBase.jobInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险基数</td>
                    <td>
                        <input name="healthInsuranceBase" id="healthInsuranceBase" type="text" value="${peopleSalaryBase.healthInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>职业年金基数</td>
                    <td>
                        <input name="annuityBase" id="annuityBase" type="text" value="${peopleSalaryBase.annuityBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text" value="${peopleSalaryBase.lifeInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text" value="${peopleSalaryBase.jobInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text" value="${peopleSalaryBase.healthInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>职业年金</td>
                    <td>
                        <input name="annuity" id="annuity" type="text" value="${peopleSalaryBase.annuity}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text" value="${peopleSalaryBase.houseFund}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
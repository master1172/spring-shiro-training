<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $('#jobId').val('${peopleContractSalary.jobId}');
        $("#examResult").val('${peopleContractSalary.examResult}');

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

        $("#timesheetStatus").numberbox({
            "onChange" : function(newValue, oldValue){
                if(newValue == oldValue)
                    return;

                if (isNaN(newValue)) {
                    alert("请输入一个数字");
                    return;
                }

                var temperatureAllowance = 100.00-((100.00/21.75)*newValue);
                $("#temperatureAllowance").numberbox('setValue',temperatureAllowance.toFixed(2));
            }
        });

        $("#examResult").combobox({
            onChange:function(newValue, oldValue){
                if (newValue == oldValue)
                    return;
                var jobExamSalary = $("#jobExamSalary").numberbox('getValue');
                var jobExamSalaryTotal = 0.00;

                if (newValue == 'A'){
                    jobExamSalaryTotal = jobExamSalary * 1.0;
                }
                if (newValue == 'B'){
                    jobExamSalaryTotal = jobExamSalary * 0.8;
                }
                if (newValue == 'C'){
                    jobExamSalaryTotal = jobExamSalary * 0.5;
                }
                if (newValue == 'D'){
                    jobExamSalaryTotal = jobExamSalary * 0.2;
                }
                if (newValue == 'E'){
                    jobExamSalaryTotal = jobExamSalary * 0.0;
                }
                $("#jobExamSalaryTotal").numberbox("setValue",jobExamSalaryTotal.toFixed(2));
            }
        });

        $("#jobExamSalary").numberbox({
            onChange:function(newValue, oldValue){
                if (newValue == oldValue)
                    return;
                var examResult = $("#examResult").combobox("getValue");

                var jobExamSalaryTotal = 0.00;
                if (examResult == 'A'){
                    jobExamSalaryTotal = newValue * 1.0;
                }
                if (examResult == 'B'){
                    jobExamSalaryTotal = newValue * 0.8;
                }
                if (examResult == 'C'){
                    jobExamSalaryTotal = newValue * 0.5;
                }
                if (examResult == 'D'){
                    jobExamSalaryTotal = newValue * 0.2;
                }
                if (examResult == 'E'){
                    jobExamSalaryTotal = newValue * 0.0;
                }
                $("#jobExamSalaryTotal").numberbox("setValue",jobExamSalaryTotal.toFixed(2));
            }
        });

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

    function calculateSalary(){
        $.post('${path}/peopleContractSalary/calculateSalary',
                $.serializeObject($('#salaryAddForm')),
                function(data){
                    $("#grossIncome").numberbox('setValue',data);
                });
    }

</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryAddForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${peopleContractSalary.peopleCode}">
                <tr>
                    <td colspan="3">
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'icon-cancel',plain:true" onclick="calculateSalary();">自动计算工资</a>
                    </td>
                </tr>
                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false" data-options="required:true"></input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" value="${people.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>院龄工资</td>
                    <td>
                        <input name="schoolSalary" id="schoolSalary" type="text" value="${people.schoolSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                     <td>岗位考核结果</td>
                    <td>
                        <select id="examResult" name="examResult" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="E">E</option>
                        </select>
                    </td>
                    <td>岗位考核工资基数</td>
                    <td>
                        <input name="jobExamSalary" id="jobExamSalary" value="${people.jobExamSalary}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>岗位考核工资</td>
                    <td>
                        <input name="jobExamSalaryTotal" id="jobExamSalaryTotal" value="${jobExamSalaryTotal}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                     <td>交通补贴</td>
                     <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text" value="${people.trafficAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                     <td>特殊补贴</td>
                     <td>
                        <input name="specialAllowance" id="specialAllowance" type="text" value="${people.specialAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                     <td>领班补贴</td>
                     <td>
                        <input name="headAllowance" id="headAllowance" type="text" value="${people.headAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
				 </tr>
                 <tr>
                     <td>通讯补贴</td>
                     <td>
                         <input name="telephoneAllowance" id="telephoneAllowance" type="text" value="${people.telephoneAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                     <td>考勤结果</td>
                     <td>
                         <input name="timesheetStatus" id="timesheetStatus" type="text" value="${sumVacationPeriod}" class="easyui-numberbox" precision="1" style="text-align:right;"/>
                     </td>
                     <td>降温补贴</td>
                     <td>
                         <input name="temperatureAllowance" id="temperatureAllowance" type="text" value="${people.temperatureAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                 </tr>
                 <tr>
                     <td>每日加班费</td>
                     <td>
                         <input name="onDutyFee" id="onDutyFee" type="text" value="${people.onDutyFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                     <td>加班天数</td>
                     <td>
                         <input name="onDutyDate" id="onDutyDate" type="text" class="easyui-numberbox" precision="1" style="text-align:right;"/>
                     </td>
                     <td>加班补贴</td>
                     <td>
                         <input name="onDutyFeeTotal" id="onDutyFeeTotal" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                     </td>
                 </tr>
                 <tr>
                    <td>奖金</td>
                    <td>
                        <input name="bonus" id="bonus" type="text" value="${bonus}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                     <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text" value="${people.reissueFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>

                 </tr>
                 <tr>
                    <td>应发工资</td>
                    <td>
                        <input name="grossIncome" id="grossIncome" type="text"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text" value="${people.lifeInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text" value="${people.jobInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text" value="${people.healthInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text" value="${people.houseFund}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>发薪月份</td>
                    <td>
                        <input id="payDate" name="payDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
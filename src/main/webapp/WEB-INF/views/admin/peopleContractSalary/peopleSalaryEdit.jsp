<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#jobId").val('${peopleContractSalary.jobId}');
        $("#payDate").val('${peopleContractSalary.payDate}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#salaryEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryEditForm" method="post">
            <table class="grid" border=1>
               <input type="hidden" name="id" value="${peopleContractSalary.id}">
               <input type="hidden" name="peopleCode" value="${peopleContractSalary.peopleCode}">
               <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${peopleContractSalary.peopleName}" class="easyui-validatebox" data-options="required:true">
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" value="${peopleContractSalary.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>院龄工资</td>
                    <td>
                        <input name="jobSalary" id="schoolSalary" type="text" value="${peopleContractSalary.schoolSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    <td>岗位考核结果</td>
                    <td>
                        <input type="text" name="examResult"  value="${peopleContractSalary.examResult}" >
                    </td>
                    <td>岗位考核工资</td>
                    <td>
                        <input name="jobExamSalary" id="jobExamSalary" type="text"  value="${peopleContractSalary.jobExamSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>通讯补贴</td>
                    <td>
                        <input name="telephoneAllowance" id="telephoneAllowance" type="text"  value="${peopleContractSalary.telephoneAllowance}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>交通补贴</td>
                    <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text"  value="${peopleContractSalary.trafficAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>                    
                    <td>特殊补贴</td>
                    <td>
                        <input name="specialAllowance" id="specialAllowance" type="text"  value="${peopleContractSalary.specialAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>领班补贴</td>
                    <td>
                        <input name="headAllowance" id="headAllowance" type="text"  value="${peopleContractSalary.headAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>每日加班费</td>
                    <td>
                        <input name="extraWorkFee" id="extraWorkFee" type="text"  value="${peopleContractSalary.extraWorkFee}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>加班天数</td>
                    <td>
                        <input name="extraWorkDate" id="extraWorkDate" type="text"  value="${peopleContractSalary.extraWorkDate}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
				</tr>
                 <tr>
                    <td>加班补贴</td>
                    <td>
                        <input name="extraWorkAllowance" id="extraWorkAllowance"  value="${peopleContractSalary.extraWorkAllowance}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>奖金</td>
                    <td>
                        <input name="bonus" id="bonus" type="text"  value="${peopleContractSalary.bonus}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                     <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text"  value="${peopleContractSalary.reissueFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>降温补贴</td>
                    <td>
                        <input name="temperatureAllowance" id="temperatureAllowance" type="text"  value="${peopleContractSalary.temperatureAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>应发工资</td>
                    <td>
                        <input name="grossIncome" id="grossIncome" type="text"  value="${peopleContractSalary.grossIncome}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text"  value="${peopleContractSalary.lifeInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text"  value="${peopleContractSalary.jobInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text"  value="${peopleContractSalary.healthInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text"  value="${peopleContractSalary.houseFund}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text"  value="${peopleContractSalary.expense}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text"  value="${peopleContractSalary.netIncome}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>发薪日</td>
                    <td>
                        <input id="payDate" name="payDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
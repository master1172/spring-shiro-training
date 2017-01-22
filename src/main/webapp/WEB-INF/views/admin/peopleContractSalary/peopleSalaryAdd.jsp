<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
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
        <form id="salaryAddForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${people.code}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${people.name}" class="easyui-validatebox" data-options="required:true">
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input name="jobSalary" id="jobSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>院龄工资</td>
                   <td>
                        <input name="jobSalary" id="schoolSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                     <td>岗位考核结果</td>
                    <td>
                        <input type="text" name="examResult">
                    </td>
                    <td>岗位考核工资</td>
                    <td>
                        <input name="jobEaxmSalary" id="jobExamSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>通讯补贴</td>
                    <td>
                        <input name="telephoneAllowance" id="telephoneAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>交通补贴</td>
                 </tr>
                 <tr>                    
                    <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>特殊补贴</td>
                    <td>
                        <input name="specialAllowance" id="specialAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>领班补贴</td>
                    <td>
                        <input name="headAllowance" id="headAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>每日加班费</td>
                    <td>
                        <input name="extraWorkFee" id="extraWorkFee" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>加班天数</td>
                    <td>
                        <input name="extraWorkDate" id="extraWorkDate" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
				</tr>
                 <tr>
                    <td>加班补贴</td>
                    <td>
                        <input name="extraWorkAllowance" id="extraWorkAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>奖金</td>
                    <td>
                        <input name="bonus" id="bonus" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                     <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>降温补贴</td>
                    <td>
                        <input name="temperatureAllowance" id="temperatureAllowance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>应发工资</td>
                    <td>
                        <input name="grossIncome" id="grossIncome" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
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
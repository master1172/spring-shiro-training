<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#jobId").val('${peopleContractSalaryBase.jobId}');
        $("#lastChangeDate").val('${peopleContractSalaryBase.lastChangeDate}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#salaryBaseForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryBaseForm" method="post">
            <table class="grid" border=1>
               <input type="hidden" name="id" value="${peopleRetireSalaryBase.id}">
               <input type="hidden" name="peopleCode" value="${peopleRetireSalaryBase.peopleCode}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${peopleRetireSalaryBaseVo.peopleName}" class="easyui-validatebox" data-options="required:true">
                    </td>
                </tr>
               <tr>
                    <td>基本工资</td>
                    <td>
                        <input name="baseSalary" id="baseSalary" type="text" value="${peopleRetirealaryBase.baseSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>适当补贴</td>
                    <td>
                        <input name="extraAllowance" id="extraAllowance" type="text" value="${peopleRetireSalaryBase.extraAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" type="text"  value="${peopleRetireSalaryBase.rentAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>退休补贴</td>
                    <td>
                        <input name="retireAllowance" id="retireAllowance" type="text"  value="${peopleRetiretSalaryBase.retireAllowance}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>退休费调增</td>
                    <td>
                        <input name="retireFeeIncrease" id="retireFeeIncrease" type="text"  value="${peopleRetireSalaryBase.retireFeeIncrease}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>                    
                    <td>肉食补助</td>
                    <td>
                        <input name="foodAllowance" id="foodAllowance" type="text"  value="${peopleRetireSalaryBase.foodAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>卫生补贴</td>
                    <td>
                        <input name="healthAllowance" id="healthAllowance" type="text"  value="${peopleRetireSalaryBase.healthAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医药费报销</td>
                    <td>
                        <input name="medicareFee" id="medicareFee" type="text"  value="${peopleRetireSalaryBase.medicareFee}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
				</tr>
                 <tr>
                    <td>物业补贴</td>
                    <td>
                        <input name="propertyAllowance" id="propertyAllowance"  value="${peopleRrtireSalaryBase.propertyAllowance}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>供暖费</td>
                    <td>
                        <input name="heatingFee" id="heatingFee" type="text"  value="${peopleRetireSalaryBase.heatingFee}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>残疾补助</td>
                    <td>
                        <input name="handicapAllowance" id="handicapAllowance" type="text"  value="${peopleRetireSalaryBase.handicapAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>应发合计</td>
                    <td>
                        <input name="grossIncome" id="grossIncome" type="text"  value="${peopleRetireSalaryBase.grossIncome}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text"  value="${peopleRetireSalaryBase.expense}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>代扣房租</td>
                    <td>
                        <input name="rentFee" id="rentFee" type="text"  value="${peopleRetireSalaryBase.rentFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text"  value="${peopleRetireSalaryBase.netIncome}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>最后更新日期</td>
                    <td>
                        <input id="lastChangeDate" name="lastChangeDate" placeholder="点击选择时间"
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
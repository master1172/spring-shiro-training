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
               <input type="hidden" name="id"  value="${people.id}">
               <input type="hidden" name="peopleCode" value="${people.code}" >
               <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" value="${people.name}"  class="easyui-validatebox" data-options="required:true">
                    </td>
                    <td>基本工资</td>
                    <td>
                        <input name="baseSalary" id="baseSalary" type="text"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>适当补贴</td>
                    <td>
                        <input name="extraAllowance" id="extraAllowance" type="text"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                     <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    
                    <td>退休补贴</td>
                    <td>
                        <input name="retireAllowance" id="retireAllowance" type="text"    class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>退休费调增</td>
                    <td>
                        <input name="retireFeeIncrease" id="retireFeeIncrease" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>肉食补助</td>
                    <td>
                        <input name="foodAllowance" id="foodAllowance" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>卫生补贴</td>
                    <td>
                        <input name="heathAllowance" id="heathAllowance" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                 </tr>
                 <tr>
                    <td>医药费报销</td>
                    <td>
                        <input name="medicareFee" id="medicareFee" type="text"    class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>物业补贴</td>
                    <td>
                        <input name="propertyAllowance" id="propertyAllowance" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>供暖费</td>
                    <td>
                        <input name="heatingFee" id="heatingFee"   type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>残疾补助</td>
                    <td>
                        <input name="handicapAllowance" id="handicapAllowance" type="text"    class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                 <tr>
                     <td>应发合计</td>
                    <td>
                        <input name="grossIncome" id="grossIncome" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>代扣房租</td>
                    <td>
                        <input name="rentFee" id="netIncome" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text"   class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                 <tr>
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
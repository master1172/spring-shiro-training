<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){

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
                <input type="hidden" name="id" value="${peopleRetireSalaryBase.id}">
                <input type="hidden" name="peopleCode" value="${peopleRetireSalaryBase.peopleCode}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="peopleName" type="text" value="${peopleRetireSalaryBase.peopleName}" class="easyui-validatebox" data-options="required:true">
                    </td>
                    <td>基本工资</td>
                    <td>
                        <input name="baseSalary" id="baseSalary" type="text" value="${peopleRetireSalaryBase.baseSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>适当补贴</td>
                    <td>
                        <input name="extraAllowance" id="extraAllowance" type="text" value="${peopleRetireSalaryBase.extraAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" type="text"  value="${peopleRetireSalaryBase.rentAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>退休补贴</td>
                    <td>
                        <input name="retireAllowance" id="retireAllowance" type="text"  value="${peopleRetireSalaryBase.retireAllowance}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>退休费调增</td>
                    <td>
                        <input name="retireFeeIncrease" id="retireFeeIncrease" type="text"  value="${peopleRetireSalaryBase.retireFeeIncrease}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>肉食补助</td>
                    <td>
                        <input name="foodAllowance" id="foodAllowance" type="text"  value="${peopleRetireSalaryBase.foodAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>卫生补贴</td>
                    <td>
                        <input name="healthAllowance" id="healthAllowance" type="text"  value="${peopleRetireSalaryBase.healthAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>医药费报销</td>
                    <td>
                        <input name="medicare" id="medicare" type="text"  value="${peopleRetireSalaryBase.medicare}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>物业补贴</td>
                    <td>
                        <input name="propertyAllowance" id="propertyAllowance" type="text"  value="${peopleRetireSalaryBase.propertyAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>供暖费</td>
                    <td>
                        <input name="heatingFee" id="heatingFee"  value="${peopleRetireSalaryBase.heatingFee}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>残疾补助</td>
                    <td>
                        <input name="handicapAllowance" id="handicapAllowance" type="text"  value="${peopleRetireSalaryBase.handicapAllowance}"  class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>

                </tr>
                <tr>
                    <td>代扣房租</td>
                    <td>
                        <input name="rentFee" id="rentFee" type="text"  value="${peopleRetireSalaryBase.rentFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
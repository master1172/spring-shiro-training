<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#jobId").val('${peopleSalary.jobId}');
        $("#rankId").val('${peopleSalary.rankId}');
        $("#examResult").val('${examResult}');

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

        $("#examResult").combobox({
            onChange:function(newValue, oldValue){

                if (newValue == oldValue)
                    return;

                var performanceAllowance = $("#performanceAllowance").numberbox('getValue');

                var performanceAllowanceTotal = 0.00;

                if (newValue == 'A'){
                    performanceAllowanceTotal = performanceAllowance * 1.0;
                }
                if (newValue == 'B'){
                    performanceAllowanceTotal = performanceAllowance * 0.8;
                }
                if (newValue == 'C'){
                    performanceAllowanceTotal = performanceAllowance * 0.5;
                }
                if (newValue == 'D'){
                    performanceAllowanceTotal = performanceAllowance * 0.2;
                }
                if (newValue == 'E'){
                    performanceAllowanceTotal = performanceAllowance * 0.0;
                }
                $("#performanceAllowanceTotal").numberbox("setValue",performanceAllowanceTotal.toFixed(2));
            }
        });

        $("#performanceAllowance").numberbox({
            onChange:function(newValue, oldValue){
                if (newValue == oldValue)
                    return;
                var examResult = $("#examResult").combobox("getValue");

                var performanceAllowanceTotal = 0.00;
                if (examResult == 'A'){
                    performanceAllowanceTotal = newValue * 1.0;
                }
                if (examResult == 'B'){
                    performanceAllowanceTotal = newValue * 0.8;
                }
                if (examResult == 'C'){
                    performanceAllowanceTotal = newValue * 0.5;
                }
                if (examResult == 'D'){
                    performanceAllowanceTotal = newValue * 0.2;
                }
                if (examResult == 'E'){
                    performanceAllowanceTotal = newValue * 0.0;
                }
                $("#performanceAllowanceTotal").numberbox("setValue",performanceAllowanceTotal.toFixed(2));
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
                var trafficAllowance = 300.00-((300.00/21.75)*newValue);
                var temperatureAllowance = 100.00-((100.00/21.75)*newValue);

                $("#trafficAllowance").numberbox('setValue',trafficAllowance.toFixed(2));
                $("#temperatureAllowance").numberbox('setValue',temperatureAllowance.toFixed(2));
            }
        });

        $("#onDutyFee").numberbox({
            "onChange" : function(newValue, oldValue){
                if (newValue == oldValue)
                   return;

                var onDutyDate = $("#onDutyDate").numberbox("getValue");

                if (isNaN(onDutyDate)){
                    $("#onDutyDate").numberbox('setValue',0.00);
                    $("#onDutyFeeTotal").numberbox('setValue',0.00);
                    return;
                }

                var onDutyFeeTotal = onDutyDate * newValue;
                $("#onDutyFeeTotal").numberbox('setValue',onDutyFeeTotal.toFixed(2));
            }
        });

        $("#onDutyDate").numberbox({
            "onChange" : function(newValue, oldValue){
                if (newValue == oldValue)
                    return;

                var onDutyFee = $("#onDutyFee").numberbox("getValue");
                var onDutyFeeTotal = newValue * onDutyFee;
                $("#onDutyFeeTotal").numberbox('setValue',onDutyFeeTotal.toFixed(2));
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
        $.post('${path}/peopleSalary/calculateSalary',
                $.serializeObject($('#salaryAddForm')),
                function(data){
                    $("#grossSalary").numberbox('setValue',data);
                });
    }

</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryAddForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${peopleSalary.peopleCode}">
                <tr>
                    <td colspan="3">
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'icon-cancel',plain:true" onclick="calculateSalary();">自动计算工资</a>
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
                        <input name="jobSalary" id="jobSalary" type="text" value="${peopleSalary.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>薪级</td>
                    <td>
                        <input class="easyui-combobox" id="rankId" name="rankId" url="${path}/dict/rank" valueField="id" textField="name" editable="false" data-options="required:true">
                        </input>
                    </td>
                    <td>薪级工资</td>
                    <td>
                        <input name="rankSalary" id="rankSalary" type="text" value="${peopleSalary.rankSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>工改保留工资</td>
                    <td>
                        <input name="reserveSalary" id="reserveSalary" type="text" value="${peopleSalary.reserveSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>

                    <td>岗位津贴</td>
                    <td>
                        <input name="jobAllowance" id="jobAllowance" type="text" value="${peopleSalary.jobAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
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
                    <td>绩效津贴基数</td>
                    <td>
                        <input name="performanceAllowance" id="performanceAllowance" type="text" value="${peopleSalary.performanceAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>绩效津贴</td>
                    <td>
                        <input name="performanceAllowanceTotal" id="performanceAllowanceTotal" type="text" value="${peopleSalary.performanceAllowanceTotal}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>提租补贴</td>
                    <td>
                        <input name="rentAllowance" id="rentAllowance" value="${peopleSalary.rentAllowance}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>购房补贴</td>
                    <td>
                        <input name="houseAllowance" id="houseAllowance" value="${peopleSalary.houseAllowance}" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>考勤情况</td>
                    <td>
                        <input name="timesheetStatus" id="timesheetStatus" type="text" value="${peopleSalary.timesheetStatus}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>职务补贴</td>
                    <td>
                        <input name="dutyAllowance" id="dutyAllowance" type="text" value="${peopleSalary.dutyAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>适当补贴</td>
                    <td>
                        <input name="extraAllowance" id="extraAllowance" type="text" value="${peopleSalary.extraAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>通讯补贴</td>
                    <td>
                        <input name="telephoneAllowance" id="telephoneAllowance" type="text" value="${peopleSalary.telephoneAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>交通补贴</td>
                    <td>
                        <input name="trafficAllowance" id="trafficAllowance" type="text" value="${peopleSalary.trafficAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>单日值班费</td>
                    <td>
                        <input name="onDutyFee" id="onDutyFee" type="text" value="${peopleSalary.onDutyFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>值班天数</td>
                    <td>
                        <input name="onDutyDate" id="onDutyDate" type="text" value="0.00" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>值班费合计</td>
                    <td>
                        <input name="onDutyFeeTotal" id="onDutyFeeTotal" type="text" value="0.00" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>物业补贴</td>
                    <td>
                        <input name="propertyAllowance" id="propertyAllowance" type="text" value="${peopleSalary.propertyAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>挂职补贴</td>
                    <td>
                        <input name="extraJobAllowance" id="extraJobAllowance" type="text" value="${peopleSalary.extraJobAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>降温补贴</td>
                    <td>
                        <input name="temperatureAllowance" id="temperatureAllowance" type="text" value="${peopleSalary.temperatureAllowance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>补发</td>
                    <td>
                        <input name="reissueFee" id="reissueFee" type="text" value="${peopleSalary.reissueFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>药费报销</td>
                    <td>
                        <input name="medicare" id="medicare" type="text" value="${peopleSalary.medicare}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>一次性年终奖励</td>
                    <td>
                        <input name="yearlyBonus" id="yearlyBonus" type="text" value="${peopleSalary.yearlyBonus}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>应发合计</td>
                    <td>
                        <input name="grossSalary" id="grossSalary" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>养老保险</td>
                    <td>
                        <input name="lifeInsurance" id="lifeInsurance" type="text" value="${peopleSalary.lifeInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险</td>
                    <td>
                        <input name="jobInsurance" id="jobInsurance" type="text" value="${peopleSalary.jobInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险</td>
                    <td>
                        <input name="healthInsurance" id="healthInsurance" type="text" value="${peopleSalary.healthInsurance}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>职业年金</td>
                    <td>
                        <input name="annuity" id="annuity" type="text" value="${peopleSalary.annuity}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>住房公积金</td>
                    <td>
                        <input name="houseFund" id="houseFund" type="text" value="${peopleSalary.houseFund}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>扣款</td>
                    <td>
                        <input name="expense" id="expense" type="text" value="" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>代扣税</td>
                    <td>
                        <input name="tax" id="tax" type="text" value="" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>实发合计</td>
                    <td>
                        <input name="netIncome" id="netIncome" type="text" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>发薪日</td>
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
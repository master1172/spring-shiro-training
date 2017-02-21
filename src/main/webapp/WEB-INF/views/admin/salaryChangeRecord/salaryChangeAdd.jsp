<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function(){
        $("#prevJobId").val('${people.jobId}');
        $("#prevRankId").val('${people.rankId}');

        $("#prevJobId").combobox({
            onChange:function(newValue,oldValue){
                if (newValue == oldValue)
                    return;

                $.post('${path}/peopleJob/getValue', {id:newValue},
                        function(data){
                            $("#prevJobSalary").numberbox('setValue',data);
                        });
            }
        });

        $("#newJobId").combobox({
            onChange:function(newValue,oldValue){
                if (newValue == oldValue)
                    return;

                $.post('${path}/peopleJob/getValue', {id:newValue},
                        function(data){
                            $("#newJobSalary").numberbox('setValue',data);
                        });
            }
        });

        $("#prevRankId").combobox({
            onChange:function (newValue,oldValue) {
                if (newValue == oldValue)
                    return;
                $.post('${path}/peopleRank/getValue',{id:newValue},
                        function(data){
                            $("#prevRankSalary").numberbox('setValue',data);
                        });
            }
        });

        $("#newRankId").combobox({
            onChange:function (newValue,oldValue) {
                if (newValue == oldValue)
                    return;
                $.post('${path}/peopleRank/getValue',{id:newValue},
                        function(data){
                            $("#newRankSalary").numberbox('setValue',data);
                        });
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


</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="salaryAddForm" method="post">
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${people.code}">
                <tr>
                    <td>原职级</td>
                    <td>
                        <input class="easyui-combobox" id="prevJobId" name="prevJobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>原职级工资</td>
                    <td>
                        <input name="prevJobSalary" id="prevJobSalary" type="text" value="${people.jobSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>新职级</td>
                    <td>
                        <input class="easyui-combobox" id="newJobId" name="newJobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>新职级工资</td>
                    <td>
                        <input name="newJobSalary" id="newJobSalary" type="text" value="" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>原薪级</td>
                    <td>
                        <input class="easyui-combobox" id="prevRankId" name="prevRankId" url="${path}/dict/rank" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>原薪级工资</td>
                    <td>
                        <input name="prevRankSalary" id="prevRankSalary" type="text" value="${people.rankSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>新薪级</td>
                    <td>
                        <input class="easyui-combobox" id="newRankId" name="newRankId" url="${path}/dict/rank" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>新薪级工资</td>
                    <td>
                        <input name="newRankSalary" id="newRankSalary" type="text" value="" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>原工改保留工资</td>
                    <td>
                        <input name="prevReserveSalary" id="prevReserveSalary" type="text" value="${people.reserveSalary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>新工改保留工资</td>
                    <td>
                        <input name="newReserveSalary" id="newReserveSalary" type="text" value="" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>工资变化原因</td>
                    <td>
                        <input name="changeReason" id="changeReason" value="" type="text" style="text-align:right;"/>
                    </td>
                    <td>工资变化日期</td>
                    <td>
                        <input id="changeDate" name="changeDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                    <td>变化生效日期</td>
                    <td>
                        <input id="effectiveDate" name="effectiveDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>本人核对意见</td>
                    <td>
                        <input name="peopleOpinion" id="peopleOpinion" type="text" value="" style="text-align:right;"/>
                    </td>
                    <td>本人核对日期</td>
                    <td>
                        <input id="peopleCheckDate" name="peopleCheckDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                })"
                               readonly="readonly"/>
                    </td>
                    <td>备注</td>
                    <td>
                        <input name="comment" id="comment" type="text" value="" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
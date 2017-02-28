<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {

    });

    function checkForm(){
        progressLoad();
        var isValid = $("#deathFeeReceiveForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="deathFeeReceiveForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${peopleDeath.id}">
            <table class="grid" border="1">
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入人员姓名" value=""></td>
                    <td>与逝者关系</td>
                    <td><input name="name" type="text" placeholder="请输入人员姓名" value=""></td>
                    <td>领取金额</td>
                    <td>
                        <input name="receiveFee" id="receiveFee" type="text" value="${peopleDeath.deathFee}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>部门意见</td>
                    <td><input name="departmentReview" type="text" placeholder="请输入人员姓名" value=""></td>
                    <td>组织部门意见</td>
                    <td><input name="organizationReview" type="text" placeholder="请输入人员姓名" value=""></td>
                    <td>院领导审批</td>
                    <td><input name="leaderReview" type="text" placeholder="请输入人员姓名" value=""></td>
                </tr>
                <tr>
                    <td>领取日期</td>
                    <td>
                        <input name="receiveDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
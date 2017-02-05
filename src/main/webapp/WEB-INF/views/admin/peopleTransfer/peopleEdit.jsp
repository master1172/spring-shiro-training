<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">

    $(function(){
        $('#transferDate').val('${peopleTransfer.transferDate}');
        $('#partyTransferDate').val('${peopleTransfer.partyTransferDate}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleTransferEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleTransferEditForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <input type="hidden" name="peopleCode" value="${peopleTransfer.code}">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="peopleName" value="${peopleTransfer.peopleName}">
                    </td>
                </tr>
                <tr>
                    <td>调出前单位</td>
                    <td>
                        <input type="text" name="fromSchool" value="${peopleTransfer.fromSchool}">
                    </td>
                    <td>调往单位</td>
                    <td>
                        <input type="text" name="toSchool" value="${peopleTransfer.toSchool}">
                    </td>
                </tr>
                <tr>
                    <td>调入调出日期</td>
                    <td>
                        <input id="transferDate" name="transferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>党组织转接日期</td>
                    <td>
                        <input id="partyTransferDate" name="partyTransferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>干部介绍信编号</td>
                    <td>
                        <input type="text" name="refLetterNo" value="${peopleTransfer.refLetterNo}">
                    </td>
                    <td>工资止薪日期</td>
                    <td>
                        <input type="text" name="salaryEndDate" placeholder="XX年XX月" value="${peopleTransfer.salaryEndDate}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

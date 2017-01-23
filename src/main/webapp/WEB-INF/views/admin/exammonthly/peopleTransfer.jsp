<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {

    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleTransferForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${peopleVo.id}">
            <input type="hidden" name="peopleCode" value="${peopleVo.code}">
            <table class="grid" border="1">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="peopleName" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true"  readonly="readonly" value="${peopleVo.name}">
                    </td>
                    <td>调出前单位</td>
                    <td>
                        <input name="fromSchool" type="text" placeholder="请输入单位" class="easyui-validatebox" data-options="required:false" value="中央民族干部学院">
                    </td>
                    <td>调往单位</td>
                    <td>
                        <input name="toSchool" type="text" placeholder="请输入单位" class="easyui-validatebox" data-options="required:false" value="">
                    </td>
                </tr>
                <tr>
                    <td>调入调出日期</td>
                    <td>
                        <input name="transferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value=""/>
                    </td>
                    <td>党组织转接日期</td>
                    <td>
                        <input name="partyTransferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value=""/>
                    </td>
                    <td>工资止薪日期</td>
                    <td>
                        <input name="salaryEndDate" type="text" placeholder="" class="easyui-validatebox" data-options="required:false" value="">
                    </td>
                </tr>
                <tr>
                    <td>干部介绍信编号</td>
                    <td>
                        <input name="refLetterNo" type="text" placeholder="" class="easyui-validatebox" data-options="required:false" value="">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/citySelect/jquery.cityselect.js"></script>
<script type="text/javascript">
    $(function(){
        $("#city_1").citySelect({
            nodata:"none",
            required:false
        });
    });
    function checkForm(){
        progressLoad();
        var isValid = $("#peopleQueryForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleSearchForm" method="post">
            <table class="grid" border=1>
                <tr>
                    <td>人员姓名</td>
                    <td><input name="peopleName" type="text"></td>
                </tr>
                <tr>
                    <td>调出前单位</td>
                    <td>
                        <input type="text" name="fromSchool">
                    </td>
                    <td>调往单位</td>
                    <td>
                        <input type="text" name="toSchool">
                    </td>
                </tr>
                <tr>
                    <td>调入调出日期</td>
                    <td colspan="3">
                        <input name="transferDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="transferDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>党组织转接日期</td>
                    <td colspan="3">
                        <input name="partyTransferDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="partyTransferDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

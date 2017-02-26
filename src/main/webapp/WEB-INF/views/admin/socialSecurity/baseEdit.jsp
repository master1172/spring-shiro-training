<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${people.sex}');
        $('#nationalId').val('${people.nationalId}');
        $('#imgShow').attr('src','${staticPath}/${people.photo}');
    });
    function checkForm(){
        progressLoad();
        var isValid = $("#baseEditForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="baseEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${people.id}">
            <input type="hidden" name="code" value="${people.code}">
            <table class="grid" border=1>
                <tr>
                    <td>养老保险基数</td>
                    <td>
                        <input name="lifeInsuranceBase" id="lifeInsuranceBase" type="text"  value="${people.lifeInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险基数</td>
                    <td>
                        <input name="jobInsuranceBase" id="jobInsuranceBase" type="text"  value="${people.jobInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>工伤保险基数</td>
                    <td>
                        <input name="woundInsuranceBase" id="woundInsuranceBase" type="text"  value="${people.woundInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>生育保险基数</td>
                    <td>
                        <input name="healthInsuranceBase" id="healthInsuranceBase" type="text"  value="${people.healthInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>职业年金基数</td>
                    <td>
                        <input name="annuityBase" id="annuityBase" type="text"  value="${people.annuityBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    function checkForm(){
        progressLoad();
        var isValid = $("#editForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="editForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id"         value="${socialSecurity.id}">
            <input type="hidden" name="peopleCode" value="${socialSecurity.peopleCode}">
            <table class="grid" border=1>
                <tr>
                    <td>养老保险基数</td>
                    <td>
                        <input name="lifeInsuranceBase" id="lifeInsuranceBase" type="text"  value="${socialSecurity.lifeInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>养老保险单位缴纳</td>
                    <td>
                        <input name="lifeInsuranceSchool" id="lifeInsuranceSchool" type="text"  value="${socialSecurity.lifeInsuranceSchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>养老保险个人缴纳</td>
                    <td>
                        <input name="lifeInsurancePeople" id="lifeInsurancePeople" type="text"  value="${socialSecurity.lifeInsurancePeople}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>

                <tr>
                    <td>失业保险基数</td>
                    <td>
                        <input name="jobInsuranceBase" id="jobInsuranceBase" type="text"  value="${socialSecurity.jobInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险单位缴纳</td>
                    <td>
                        <input name="jobInsuranceSchool" id="jobInsuranceSchool" type="text"  value="${socialSecurity.jobInsuranceSchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>失业保险个人缴纳</td>
                    <td>
                        <input name="jobInsurancePeople" id="jobInsurancePeople" type="text"  value="${socialSecurity.jobInsurancePeople}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>

                <tr>
                    <td>工伤保险基数</td>
                    <td>
                        <input name="woundInsuranceBase" id="woundInsuranceBase" type="text"  value="${socialSecurity.woundInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>工伤保险单位缴纳</td>
                    <td>
                        <input name="woundInsuranceSchool" id="woundInsuranceSchool" type="text"  value="${socialSecurity.woundInsuranceSchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>

                <tr>
                    <td>生育保险基数</td>
                    <td>
                        <input name="birthInsuranceBase" id="birthInsuranceBase" type="text"  value="${socialSecurity.birthInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>生育保险单位缴纳</td>
                    <td>
                        <input name="birthInsuranceSchool" id="birthInsuranceSchool" type="text"  value="${socialSecurity.birthInsuranceSchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>

                <tr>
                    <td>医疗保险基数</td>
                    <td>
                        <input name="healthInsuranceBase" id="healthInsuranceBase" type="text"  value="${socialSecurity.healthInsuranceBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险单位缴纳</td>
                    <td>
                        <input name="healthInsuranceSchool" id="healthInsuranceSchool" type="text"  value="${socialSecurity.healthInsuranceSchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>医疗保险个人缴纳</td>
                    <td>
                        <input name="healthInsurancePeople" id="healthInsurancePeople" type="text"  value="${socialSecurity.healthInsurancePeople}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>

                <tr>
                    <td>职业年金基数</td>
                    <td>
                        <input name="annuityBase" id="annuityBase" type="text"  value="${socialSecurity.annuityBase}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>职业年金单位缴纳</td>
                    <td>
                        <input name="annuitySchool" id="annuitySchool" type="text"  value="${socialSecurity.annuitySchool}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                    <td>职业年金个人缴纳</td>
                    <td>
                        <input name="annuityPeople" id="annuityPeople" type="text"  value="${socialSecurity.annuityPeople}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
                <tr>
                    <td>发放日期</td>
                    <td>
                        <input id="payDate" name="payDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                    readOnly:true,
                                    dateFmt:'yyyy-MM',
                                    maxDate:'%y-%M-%d'
                                })"
                               readonly="readonly",
                               value="${socialSecurity.payDate}"
                        />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
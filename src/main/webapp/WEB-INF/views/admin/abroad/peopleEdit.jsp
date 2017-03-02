<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">

    $(function(){
        $("#departmentId").val('${abroad.departmentId}');
        $("#jobId").val('${abroad.jobId}');
        $("#passportStatus").val('${abroad.passportStatus}');
        $("#reason").val('${abroad.reason}');
        $("#funding").val('${abroad.funding}');

    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleAddForm").form("validate");
        if (!isValid) {
            progressClose();
            return false;
        }
        return true;
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${abroad.id}"/>
            <input type="hidden" name="code" value="${abroad.code}"/>
            <table class="grid" border=1>
                <tr>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${abroad.name}"></td>
                    <td>部门</td>
                    <td>
                        <input class="easyui-combobox" id="departmentId" name="departmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出国境日期</td>
                    <td>
                        <input id="abroadDate" name="abroadDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.abroadDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>所赴国家</td>
                    <td>
                        <input type="text" name="country" value="${abroad.country}">
                    </td>
                    <td>返回日期</td>
                    <td>
                        <input id="returnDate" name="returnDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd'
                                })"
                               readonly="readonly" value="${abroad.returnDate}"/>
                    </td>
                    <td>停留时间</td>
                    <td>
                        <input type="text" name="stayPeriod" value="${abroad.stayPeriod}">
                    </td>
                    <td>所用假期</td>
                    <td>
                        <input type="text" name="vacation" value="${abroad.vacation}">
                    </td>

                </tr>
                <tr>
                    <td>证件类型</td>
                    <td>
                        <input type="text" name="passportType" value="${abroad.passportType}">
                    </td>
                    <td>身份证号码</td>
                    <td>
                        <input type="text" name="photoId" value="${abroad.photoId}">
                    </td>
                    <td>证件号码</td>
                    <td>
                        <input type="text" name="passportNumber" value="${abroad.passportNumber}">
                    </td>
                    <td>探望者姓名</td>
                    <td>
                        <input type="text" name="visitPeople" value="${abroad.visitPeople}">
                    </td>
                </tr>
                <tr>
                    <td>与本人关系</td>
                    <td>
                        <input type="text" name="relationship" value="${abroad.relationship}">
                    </td>
                    <td>居住城市</td>
                    <td>
                        <input type="text" name="city" value="${abroad.city}">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="office" value="${abroad.office}">
                    </td>
                    <td>处理个人事务具体说明</td>
                    <td>
                        <input type="text" name="personalIssue" value="${abroad.personalIssue}">
                    </td>
                </tr>
                <tr>
                    <td>申请因私证件情况</td>
                    <td>
                        <select name="passportStatus" id="passportStatus" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="首次申办证件">首次申办证件</option>
                            <option value="领用已办证件">领用已办证件</option>
                        </select>
                    </td>
                    <td>是由</td>
                    <td>
                        <select name="reason" id="reason" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="个人旅游">个人旅游</option>
                            <option value="探亲">探亲</option>
                            <option value="处理个人事务">处理个人事务</option>
                        </select>
                    </td>
                    <td>经费形势</td>
                    <td>
                        <select name="funding" id="funding" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="自费">自费</option>
                            <option value="其它">其它</option>
                        </select>
                    </td>
                    <td>经费来源</td>
                    <td>
                        <input type="text" name="fundingSource" value="${abroad.fundingSource}">
                    </td>
                    </tr>
                <tr>
                    <td>办理日期</td>
                    <td>
                        <input id="issueDate" name="issueDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.issueDate}"/>
                    </td>
                    <td>取证日期</td>
                    <td>
                        <input id="pickPassportDate" name="pickPassportDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${abroad.pickPassportDate}"/>
                    </td>

                    <td>还证日期</td>
                    <td>
                    <td>
                        <input id="returnPassportDate" name="returnPassportDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd'
                                })"
                               readonly="readonly" value="${abroad.returnPassportDate}"/>
                    </td>
                    </td>
                    <td>登记备案人员类型</td>
                    <td>
                        <input type="text" name="recordType" value="${abroad.recordType}">
                    </td>
                </tr>
                 <tr>
                    <td>报备情况</td>
                    <td>
                        <input type="text" name="recordStatus" value="${abroad.recordStatus}">
                    </td>
                    <td>登记证件类型</td>
                    <td>
                        <input type="text" name="recordIdType" value="${abroad.recordIdType}">
                    </td>
                    <td>登记证件号码</td>
                    <td>
                        <input type="text" name="recordIdNumber" value="${abroad.recordIdNumber}">
                    </td>
                 </tr>
                <tr>
                    <td>
                        <input id="recordIdExpire" name="recordIdExpire" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd'
                                })"
                               readonly="readonly" value="${abroad.recordIdExpire}"/>
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" id="comment" name="comment" value="${abroad.comment}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
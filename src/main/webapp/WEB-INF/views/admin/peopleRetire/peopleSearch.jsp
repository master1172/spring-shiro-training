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
                    <td>人员编码</td>
                    <td><input name="code" type="text"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>退休时职务</td>
                    <td>
                        <input class="easyui-combobox" id="retireJobName" name="retireJobName" url="${path}/dict/jobName" valueField="name" textField="name" editable="false">
                        </input>
                    </td>
                    <td>退休时职级</td>
                    <td>
                        <input class="easyui-combobox" id="retireJobLevelId" name="retireJobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>出生日期范围</td>
                    <td colspan="3">
                        <input name="birthdayMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="birthdayMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>民族</td>
                    <td colspan="3">
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>学历</td>
                    <td>
                        <input type="text" name="educationName">
                    </td>
                    <td>政治面貌</td>
                    <td><input type="text" name="politicalName"></td>
                </tr>
                <tr>
                    <td>工作日期范围</td>
                    <td colspan="3">
                        <input name="workDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="workDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>退休日期范围</td>
                    <td colspan="3">
                        <input name="retireDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="retireDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>家庭住址</td>
                    <td>
                        <input type="text" name="address"/>
                    </td>
                    <td>联系电话</td>
                    <td>
                        <input type="text" name="mobile"/>
                    </td>
                </tr>

                <tr>
                    <td>固定联系人</td>
                    <td>
                        <input type="text" name="contact">
                    </td>
                    <td>联系人电话</td>
                    <td>
                        <input type="text" name="contactNumber"/>
                    </td>
                </tr>
                <tr>
                    <td>当前状态</td>
                    <td>
                        <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0" >退休</option>
                            <option value="1" >返聘</option>
                        </select>
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

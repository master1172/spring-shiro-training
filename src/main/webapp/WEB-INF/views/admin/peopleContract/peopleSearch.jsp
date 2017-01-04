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
                    <td>姓名</td>
                    <td><input type="text" name="name"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr id="city_1">
                    <td>来自省</td>
                    <td>
                        <select name="province" class="prov" ></select>
                    </td>
                    <td>来自市/区</td>
                    <td>
                        <select name="city" class="city" disabled="disabled"></select>
                    </td>
                    <td>户籍</td>
                    <td>
                        <select name="hukou" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0" >非农业</option>
                            <option value="1" >农业</option>
                        </select>
                    </td>
                    <td>文化程度</td>
                    <td><input type="test" name="educationName"></td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td><input type="text" name="politicalName"></td>
                    <td>特长</td>
                    <td>
                        <input type="text" name="speciality"/>
                    </td>
                    <td>身高</td>
                    <td>
                        <input type="text" name="height"/>
                    </td>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
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
                    <td>到院工作日期范围</td>
                    <td colspan="3">
                        <input name="schoolDateMin" placeholder="点击选择起始时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                        ~
                        <input name="schoolDateMax" placeholder="点击选择结束时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td><input type="text" name="mobile"></td>
                    <td>现住址</td>
                    <td>
                        <input type="text" name="address"/>
                    </td>
                    <td>部门</td>
                    <td><input type="text" name="departmentName"></td>
                    <td>工种</td>
                    <td>
                        <input type="text" name="jobName"/>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="3">
                        <input type="text" name="comment"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

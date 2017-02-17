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
                </tr>
                <tr>
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
                <tr>
                    <td>籍贯</td>
                    <td>
                        <input class="easyui-combobox" id="nativeId" name="nativeId" url="${path}/dict/native" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出生地</td>
                    <td>
                        <input name="birthPlace"/>
                    </td>
                </tr>
                <tr>
                    <td>文化程度</td>
                    <td>
                        <input type="text" name="educationName">
                    </td>
                    <td>专业技术及专长</td>
                    <td>
                        <input type="text" name="speciality"/>
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
                    <td>退休日期</td>
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
                    <td>政治面貌</td>
                    <td><input type="text" name="politicalName"></td>
                    <td>健康状况</td>
                    <td>
                        <input type="text" name="healthStatus"/>
                    </td>
                </tr>
                <tr>
                    <td>返聘前工作部门</td>
                    <td>
                        <input class="easyui-combobox" id="beforeDepartmentId" name="beforeDepartmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>返聘前岗位</td>
                    <td>
                        <input type="text" name="beforeJobName">
                    </td>
                </tr>
                <tr>
                    <td>返聘前职级</td>
                    <td>
                        <input class="easyui-combobox" id="beforeJobLevelId" name="beforeJobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>拟返聘工作部门</td>
                    <td>
                        <input class="easyui-combobox" id="afterDepartmentId" name="afterDepartmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>拟返聘岗位</td>
                    <td>
                        <input type="text" name="afterJobName">
                    </td>
                    <td>拟返聘职级</td>
                    <td>
                        <input class="easyui-combobox" id="afterJobLevelId" name="afterJobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>身份证号</td>
                    <td>
                        <input type="text" name="photoId">
                    </td>
                    <td>家庭住址</td>
                    <td>
                        <input type="text" name="address"/>
                    </td>
                </tr>
                <tr>
                    <td>户籍所在地</td>
                    <td>
                        <input type="text" name="hukouAddress">
                    </td>
                    <td>返聘人员类型</td>
                    <td>
                        <input type="text" name="rehireCategory"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

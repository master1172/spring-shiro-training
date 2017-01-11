<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $('#branchId').val('${peopleParty.branchId}');
        $('#departmentId').val('${peopleParty.departmentId}');
        $("#sex").val('${peopleParty.sex}');
        $('#nationalId').val('${peopleParty.nationalId}');
        $('#partyStatusId').val('${peopleParty.partyStatusId}');
        $('#degreeId').val('${peopleParty.degreeId}');
        $('#jobLevelId').val('${peopleParty.jobLevelId}');
        $('#formation').val('${peopleParty.formation}');
        $('#imgShow').attr('src','${staticPath}/${peopleParty.photo}');
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
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${peopleParty.id}">
            <input type="hidden" name="peopleCode" value="${peopleParty.peopleCode}">

            <table class="grid" border=1>

                <tr>
                    <td>人员姓名</td>
                    <td>
                        <input type="text" name="peopleName" value="${peopleParty.peopleName}">
                    </td>
                    <td>所在支部</td>
                    <td>
                        <input class="easyui-combobox" id="branchId" name="branchId" url="${path}/dict/branch" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td>
                        <input class="easyui-combobox" id="departmentId" name="departmentId" url="${path}/dict/department" valueField="name" textField="name" editable="false">
                        </input>
                    </td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" >男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td>籍贯</td>
                    <td>
                        <input type="text" name="nativeName" value="${peopleParty.nativeName}">
                    </td>
                    <td>党员状态</td>
                    <td>
                        <input class="easyui-combobox" id="partyStatusId" name="partyStatusId" url="${path}/dict/partyStatus" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>入党日期</td>
                    <td>
                        <input name="partyDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.partyDate}"/>
                    </td>
                    <td>学历情况</td>
                    <td>
                        <input class="easyui-combobox" id="degreeId" name="degreeId" url="${path}/dict/degree" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>参加工作日期</td>
                    <td>
                        <input name="workDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.workDate}"/>
                    </td>
                    <td>职务岗位</td>
                    <td>
                        <input type="text" name="jobName" value="${peopleParty.jobName}"/>
                    </td>
                </tr>

                <tr>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobLevelId" name="jobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>现任职级日期</td>
                    <td>
                        <input name="jobDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.jobDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>编制</td>
                    <td>
                        <select name="formation" id="formation" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="学校" >学校</option>
                            <option value="招待所" >招待所</option>
                        </select>
                    </td>
                    <td>党组织关系转入日期</td>
                    <td>
                        <input name="partyInDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.partyInDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>党组织关系转出日期</td>
                    <td>
                        <input name="partyOutDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly" value="${peopleParty.partyOutDate}"/>
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment" value="${peopleParty.comment}">
                    </td>
                </tr>
                <tr>
                    <td>头像上传</td>
                    <td colspan="3">
                        <div id="imgdiv" style="height:100px;width:100px;">
                            <img id="imgShow" style="height:100px;width:100px;"/>
                        </div>
                        <input type="file" id="up_img" name="fileName"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

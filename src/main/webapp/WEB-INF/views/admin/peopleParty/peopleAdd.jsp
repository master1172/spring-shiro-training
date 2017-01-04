<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
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
    <div data-options="region:'center',border:false" title="" style="overflow: scroll;padding: 3px;">
        <form id="peopleAddForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <tr>
                    <td>人员姓名</td>
                    <td>
                        <input type="text" name="peopleName">
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
                        <input class="easyui-combobox" id="departmentId" name="departmentId" url="${path}/dict/department" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
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
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>籍贯</td>
                    <td>
                        <input type="text" name="nativeName">
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
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
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
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>职务岗位</td>
                    <td>
                        <input type="text" name="jobName"/>
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
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>编制</td>
                    <td>
                        <select name="formation" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="学校" >学校</option>
                            <option value="招待所" >招待所</option>
                        </select>
                    </td>
                    <td>党组织关系转入日期</td>
                    <td>
                        <input name="partyInDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>党组织关系转出日期</td>
                    <td>
                        <input name="partyOutDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>备注</td>
                    <td>
                        <input type="text" name="comment">
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

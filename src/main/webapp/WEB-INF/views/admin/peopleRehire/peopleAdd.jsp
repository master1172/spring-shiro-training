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
                    <td>姓名</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
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
                        <input name="nativeName"/>
                    </td>
                    <td>出生地</td>
                    <td>
                        <input name="birthPlace"/>
                    </td>
                </tr>
                <tr>
                    <td>生日</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>文化程度</td>
                    <td>
                        <input type="text" name="educationName">
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
                    <td>退休日期</td>
                    <td>
                        <input name="retireDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>专业技术及专长</td>
                    <td>
                        <input type="text" name="speciality"/>
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
                        <input type="text" name="photoId" class="easyui-validatebox" data-options="validType:'length[18,18]'">
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
                        <input type="text" name="category"/>
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

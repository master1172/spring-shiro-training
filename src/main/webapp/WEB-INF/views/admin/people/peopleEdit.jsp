<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});
        $("#sex").val('${peopleVo.sex}');
        $('#nationalId').val('${peopleVo.nationalId}');
        $('#nativeId').val('${peopleVo.nativeId}');
        $('#degreeId').val('${peopleVo.degreeId}');
        $('#jobCategory').val('${peopleVo.jobCategory}');
        $('#jobLevelId').val('${peopleVo.jobLevelId}');
        $('#marriageId').val('${peopleVo.marriageId}');
        $('#identityId').val('${peopleVo.identityId}');
        $('#imgShow').attr('src','${staticPath}/${peopleVo.photo}');
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
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="peopleEditForm" method="post" enctype=”multipart/form-data”>
            <input type="hidden" name="id" value="${peopleVo.id}">
            <input type="hidden" name="code" value="${peopleVo.code}">
            <table class="grid" border="1">
                <tr>
                    <td>姓名</td>
                    <td>
                        <input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${peopleVo.name}">
                    </td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </td>
                    <td>民族</td>
                    <td>
                        <input class="easyui-combobox" id="nationalId" name="nationalId" url="${path}/dict/national" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>籍贯</td>
                    <td>
                        <input class="easyui-combobox" id="nativeId" name="nativeId" url="${path}/dict/native" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>出生日期</td>
                    <td>
                        <input name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName" value="${peopleVo.politicalName}">
                    </td>
                    <td>学历</td>
                    <td>
                        <input type="text" name="educationName" value="${peopleVo.educationName}">
                    </td>
                    <td>学位</td>
                    <td>
                        <input class="easyui-combobox" id="degreeId" name="degreeId" url="${path}/dict/degree" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>入党日期</td>
                    <td>
                        <input name="partyDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.partyDate}"/>
                    </td>
                </tr>
                <tr>
                    <td>参加工作日期</td>
                    <td>
                        <input name="workDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.workDate}"/>
                    </td>
                    <td>来院日期</td>
                    <td>
                        <input name="schoolDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.schoolDate}"/>
                    </td>
                    <td>职务</td>
                    <td>
                        <input type="text" name="jobName" value="${peopleVo.jobName}">
                    </td>
                    <td>人员类别</td>
                    <td>
                        <select id="jobCategory" name="jobCategory" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="管理类">管理类</option>
                            <option value="专业类">专业类</option>
                            <option value="工勤类">工勤类</option>
                        </select>
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobLevelId" name="jobLevelId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>任现职日期</td>
                    <td>
                        <input name="jobDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.jobDate}"/>
                    </td>
                    <td>任现职级日期</td>
                    <td>
                        <input name="jobLevelDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" value="${peopleVo.jobLevelDate}"/>
                    </td>
                    <td>年龄</td>
                    <td>
                        <input type="text" name="age" value="${peopleVo.age}">
                    </td>
                    <td>虚岁</td>
                    <td>
                        <input type="text" name="virtualAge" value="${peopleVo.virtualAge}">
                    </td>
                    <td>工龄</td>
                    <td>
                        <input type="text" name="workAge" value="${peopleVo.workAge}">
                    </td>
                </tr>
                <tr>
                    <td>编制</td>
                    <td>
                        <input type="text" name="formation" value="${peopleVo.formation}">
                    </td>
                    <td>手机号</td>
                    <td>
                        <input type="text" name="mobile" value="${peopleVo.mobile}" class="easyui-validatebox" data-options="validType:'length[1,11]'">
                    </td>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>身份证号码</td>
                    <td>
                        <input type="text" name="photoId" value="${peopleVo.photoId}" class="easyui-validatebox" data-options="validType:'length[18,18]'">
                    </td>
                    <td>现家庭住址</td>
                    <td>
                        <input type="text" name="address" value="${peopleVo.address}">
                    </td>
                </tr>
                <tr>
                    <td>户籍</td>
                    <td>
                        <input type="text" name="hukou" value="${peopleVo.hukou}">
                    </td>
                    <td>户籍地址</td>
                    <td>
                        <input type="text" name="hukouAddress" value="${peopleVo.hukouAddress}">
                    </td>
                    <td>最终学历</td>
                    <td>
                        <input type="text" name="finalEducationName" value="${peopleVo.finalEducationName}">
                    </td>
                    <td>所学专业</td>
                    <td>
                        <input type="text" name="major" value="${peopleVo.major}">
                    </td>
                    <td>毕业院校</td>
                    <td>
                        <input type="text" name="graduateSchool" value="${peopleVo.graduateSchool}">
                    </td>
                </tr>
                <tr>
                    <td>紧急联系人</td>
                    <td>
                        <input type="text" name="contact" value="${peopleVo.contact}">
                    </td>
                    <td>与本人关系</td>
                    <td>
                        <input type="text" name="relationship" value="${peopleVo.relationship}">
                    </td>
                    <td>联系人电话</td>
                    <td>
                        <input type="text" name="contactNumber" value="${peopleVo.contactNumber}">
                    </td>
                    <td>身份</td>
                    <td>
                        <input class="easyui-combobox" id="identityId" name="identityId" url="${path}/dict/identity" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo1Title" value="${peopleVo.familyInfo1Title}">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo1Name" value="${peopleVo.familyInfo1Name}">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo1WorkAddress" value="${peopleVo.familyInfo1WorkAddress}">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo1Job" value="${peopleVo.familyInfo1Job}">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo1Contact" value="${peopleVo.familyInfo1Contact}">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo2Title" value="${peopleVo.familyInfo2Title}">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo2Name" value="${peopleVo.familyInfo2Name}">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo2WorkAddress" value="${peopleVo.familyInfo2WorkAddress}">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo2Job" value="${peopleVo.familyInfo2Job}">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo2Contact" value="${peopleVo.familyInfo2Contact}">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo3Title" value="${peopleVo.familyInfo3Title}">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo3Name" value="${peopleVo.familyInfo3Name}">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo3WorkAddress" value="${peopleVo.familyInfo3WorkAddress}">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo3Job" value="${peopleVo.familyInfo3Job}">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo3Contact" value="${peopleVo.familyInfo3Contact}">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo4Title" value="${peopleVo.familyInfo4Title}">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo4Name" value="${peopleVo.familyInfo4Name}">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo4WorkAddress" value="${peopleVo.familyInfo4WorkAddress}">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo4Job" value="${peopleVo.familyInfo4Job}">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo4Contact" value="${peopleVo.familyInfo4Contact}">
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
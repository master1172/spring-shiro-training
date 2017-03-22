<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        new uploadPreview({UpBtn:"up_img",DivShow:"imgdiv",ImgShow: "imgShow"});

        $('#jobCategory').combobox({
            onChange:function(newValue, oldValue){
                if (newValue == oldValue)
                    return;

                $('#jobId').combobox({
                    url:'${path}/dict/job?jobCategory='+newValue,
                    valueField:'id',
                    textField:'name'
                }).combobox('clear');
            }
        });

        $("#nationalId").combobox({
           editable:true,
            filter:function(q,row){
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) == 0;
            }
        });

        $("#nativeId").combobox({
            editable:true,
            filter:function(q,row){
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) == 0;
            }
        });
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
    function birthdayChange(){
        var birthday = $("#birthday").val();
        var currnetYear = new Date().getFullYear();
        var age =currnetYear - birthday.substr(0,4);
        $("#age").val(age);
        $("#virtualAge").val(age+1);
    }
    function workDateChange(){
        var workDate = $("#workDate").val();
        var currentYear = new Date().getFullYear();
        var workAge = currentYear - workDate.substr(0,4);
        $("#workAge").val(workAge);
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow:scroll;padding: 3px;">
        <div style="width:1500px">
        <form id="peopleAddForm" method="post" enctype=”multipart/form-data”>
            <table class="grid" border=1>
                <tr>
                    <td width="50">姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td width="50">性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <td width="50">民族</td>
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
                        <input id="birthday" name="birthday" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                    readOnly:true,
                                    dateFmt:'yyyy-MM-dd',
                                    maxDate:'%y-%M-%d',
                                    onpicked:birthdayChange
                                })"
                               readonly="readonly"
                        />
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td>
                        <input type="text" name="politicalName">
                    </td>
                    <td>学位</td>
                    <td>
                        <input type="text" name="educationName">
                    </td>
                    <td>学历</td>
                    <td>
                        <input class="easyui-combobox" id="degreeId" name="degreeId" url="${path}/dict/degree" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>入党日期</td>
                    <td>
                        <input name="partyDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>参加工作日期</td>
                    <td>
                        <input id="workDate" name="workDate" placeholder="点击选择时间"
                               onclick="WdatePicker({
                                readOnly:true,
                                dateFmt:'yyyy-MM-dd',
                                maxDate:'%y-%M-%d',
                                onpicked:workDateChange})"
                               readonly="readonly"/>
                    </td>
                    <td>来院日期</td>
                    <td>
                        <input name="schoolDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>职务</td>
                    <td>
                        <input type="text" name="jobName">
                    </td>
                    <td>人员类别</td>
                    <td>
                        <select name="jobCategory" id="jobCategory" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value=""> </option>
                            <option value="管理类">管理类</option>
                            <option value="专业类">专业类</option>
                            <option value="工勤类">工勤类</option>
                        </select>
                    </td>
                    <td>职级</td>
                    <td>
                        <input class="easyui-combobox" id="jobId" name="jobId" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>任现职日期</td>
                    <td>
                        <input name="jobDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>任现职级日期</td>
                    <td>
                        <input name="jobLevelDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                               readonly="readonly"/>
                    </td>
                    <td>年龄</td>
                    <td>
                        <input type="text" id="age" name="age">
                    </td>
                    <td>虚岁</td>
                    <td>
                        <input type="text" id="virtualAge" name="virtualAge">
                    </td>
                    <td>工龄</td>
                    <td>
                        <input type="text" id="workAge" name="workAge">
                    </td>
                </tr>
                <tr>
                    <td>编制</td>
                    <td>
                        <input type="text" name="formation">
                    </td>
                    <td>手机号</td>
                    <td>
                        <input type="text" name="mobile" class="easyui-validatebox" data-options="validType:'length[1,11]'">
                    </td>
                    <td>婚姻状况</td>
                    <td>
                        <input class="easyui-combobox" id="marriageId" name="marriageId" url="${path}/dict/marriage" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                    <td>身份证号码</td>
                    <td>
                        <input type="text" name="photoId" class="easyui-validatebox" data-options="validType:'length[18,18]'">
                    </td>
                    <td>现家庭住址</td>
                    <td>
                        <input type="text" name="address">
                    </td>
                </tr>
                <tr>
                    <td>户籍</td>
                    <td>
                        <input type="text" name="hukou">
                    </td>
                    <td>户籍地址</td>
                    <td>
                        <input type="text" name="hukouAddress">
                    </td>
                    <td>最终学历</td>
                    <td>
                        <input type="text" name="finalEducationName">
                    </td>
                    <td>所学专业</td>
                    <td>
                        <input type="text" name="major">
                    </td>
                    <td>毕业院校</td>
                    <td>
                        <input type="text" name="graduateSchool">
                    </td>
                </tr>
                <tr>
                    <td>紧急联系人</td>
                    <td>
                        <input type="text" name="contact">
                    </td>
                    <td>与本人关系</td>
                    <td>
                        <input type="text" name="relationship">
                    </td>
                    <td>联系人电话</td>
                    <td>
                        <input type="text" name="contactNumber">
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
                        <input type="text" name="familyInfo1Title">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo1Name">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo1WorkAddress">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo1Job">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo1Contact">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo2Title">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo2Name">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo2WorkAddress">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo2Job">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo2Contact">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo3Title">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo3Name">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo3WorkAddress">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo3Job">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo3Contact">
                    </td>
                </tr>
                <tr>
                    <td>称谓</td>
                    <td>
                        <input type="text" name="familyInfo4Title">
                    </td>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="familyInfo4Name">
                    </td>
                    <td>工作单位</td>
                    <td>
                        <input type="text" name="familyInfo4WorkAddress">
                    </td>
                    <td>职务及职位</td>
                    <td>
                        <input type="text" name="familyInfo4Job">
                    </td>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="familyInfo4Contact">
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
</div>
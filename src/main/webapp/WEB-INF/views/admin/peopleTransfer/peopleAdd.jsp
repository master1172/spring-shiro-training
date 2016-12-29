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
                    <td>人员编码</td>
                    <td><input name="peopleCode" type="text" placeholder="" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td>人员类型</td>
                    <td>
                        <input type="text" name="peopleType">
                    </td>
                </tr>
                <tr>
                    <td>调出前单位</td>
                    <td>
                        <input type="text" name="fromSchool">
                    </td>
                    <td>调往单位</td>
                    <td>
                        <input type="text" name="toSchool">
                    </td>
                </tr>
                <tr>
                    <td>调入调出日期</td>
                    <td>
                        <input name="transferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                    <td>党组织转接日期</td>
                    <td>
                        <input name="partyTransferDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>干部介绍信编号</td>
                    <td>
                        <input type="text" name="refLetterNo">
                    </td>
                    <td>工资止薪日期</td>
                    <td>
                        <input type="text" name="salaryEndDate" placeholder="XX年XX月">
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

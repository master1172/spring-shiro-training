<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {        
        $("#jobCategory").val('${peopleJobVo.jobCategory}');
        $('#jobLevel').val('${peopleVo.jobLevel}');
        $('#salary').val('${peopleVo.salary}');      
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleJobForm").form("validate");
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
            <input type="hidden" name="id" value="${peopleJobVo.id}">
            <input type="hidden" name="jobCategory" value="${peopleJobVo.jobCategory}">
			<input type="hidden" name="jobLevel" value="${peopleJobVo.jobLevel}">
            <table class="grid" border="1">
                <tr>
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
					<td>岗位薪资</td>
                    <td>
                        <input class="easyui-combobox" id="salary" name="salary" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>
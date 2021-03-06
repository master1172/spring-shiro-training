<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {        
        $("#jobCategory").val('${peopleJobVo.jobCategory}');
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
        <form id="peopleJobEditForm" method="post" >
            <input type="hidden" name="id" value="${peopleJobVo.id}">
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
                        <input type="text" name="jobLevel" id="jobLevel" value="${peopleJobVo.jobLevel}" data-options="required:true">
                    </td>
                    <td>岗位工资</td>
                    <td>
                        <input type="text" name="salary" id="salary" value="${peopleJobVo.salary}" class="easyui-numberbox" precision="2" style="text-align:right;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
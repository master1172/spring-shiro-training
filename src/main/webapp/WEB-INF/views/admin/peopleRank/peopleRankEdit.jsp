<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath}/static/easyui/plugins/uploadPreview.min.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {        
        $('#rank_level').val('${peopleRankVo.rank_level}');
        $('#salary').val('${peopleRankVo.salary}');
    });

    function checkForm(){
        progressLoad();
        var isValid = $("#peopleRankForm").form("validate");
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
            <input type="hidden" name="id" value="${peopleRankVo.id}">
            <input type="hidden" name="rank_level" value="${peopleRankVo.rank_level}">
            <table class="grid" border="1">
                <tr>
  				    <td>人员类别</td>
                    <td>
                        <select id="rank_level" name="rank_level" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">1级</option>
                            <option value="2">2级</option>
                            <option value="3">3级</option>
                            <option value="4">4级</option>
                            <option value="5">5级</option>
                            <option value="6">6级</option>
                            <option value="7">7级</option>
                            <option value="8">8级</option>
                            <option value="9">9级</option>
                            <option value="10">10级</option>
                            <option value="11">11级</option>
                            <option value="12">12级</option>
                            <option value="13">13级</option>
                            <option value="14">14级</option>
                            <option value="15">15级</option>
                            <option value="16">16级</option>
                            <option value="17">17级</option>
                            <option value="18">18级</option>
                            <option value="19">19级</option>
                            <option value="20">20级</option>
                            <option value="21">21级</option>
                            <option value="22">22级</option>
                            <option value="23">23级</option>
                            <option value="24">24级</option>
                            <option value="25">25级</option>
                            <option value="26">26级</option>
                            <option value="27">27级</option>
                            <option value="28">28级</option>
                            <option value="29">29级</option>
                            <option value="30">30级</option>
                            <option value="31">31级</option>
                            <option value="32">32级</option>
                            <option value="33">33级</option>
                            <option value="34">34级</option>
                            <option value="35">35级</option>
                            <option value="36">36级</option>
                            <option value="37">37级</option>
                            <option value="38">38级</option>
                            <option value="39">39级</option>
                            <option value="40">40级</option>
                            <option value="41">41级</option>
                            <option value="42">42级</option>
                            <option value="43">43级</option>
                            <option value="44">44级</option>
                            <option value="45">45级</option>
                            <option value="46">46级</option>
                            <option value="47">47级</option>
                            <option value="48">48级</option>
                            <option value="49">49级</option>
                            <option value="50">50级</option>
                            <option value="51">51级</option>
                            <option value="52">52级</option>
                            <option value="53">53级</option>
                            <option value="54">54级</option>
                            <option value="55">55级</option>
                            <option value="56">56级</option>
                            <option value="57">57级</option>
                            <option value="57">58级</option>
                            <option value="59">59级</option>
                            <option value="60">60级</option>
                        </select>
                    </td>
					<td>薪级工资</td>
                    <td>
                        <input class="easyui-combobox" id="salary" name="salary" url="${path}/dict/job" valueField="id" textField="name" editable="false">
                        </input>
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>
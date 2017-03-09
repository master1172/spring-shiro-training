<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>

    <link href="/static/style/css/global.css" rel="stylesheet"/>

    <style type="text/css">
    	.panel.window{z-index: 999 !important;}
    	.window-shadow{z-index: 998 !important;}
    	.window-mask{z-index: 996 !important;}
    </style>
	<script type="text/javascript" src="${staticPath }/static/easyui/plugins/jquery-form.js" charset="utf-8"></script>
    <script type="text/javascript">
        var index_layout;
        var index_tabs;
        var index_tabsMenu;
        var layout_west_tree;

        $(function () {

            $.messager.show({
                title:'提示',
                msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+'目前系统中有${retirePeopleCount}名将要退休的人员'+'</div></div>',
                showType:'show'
            });

            index_layout = $('#index_layout').layout({
                fit: true
            });

            index_tabs = $('#index_tabs').tabs({
                fit: true,
                border: false,
                tools: [{
                    iconCls: 'icon-home',
                    handler: function () {
                        index_tabs.tabs('select', 0);
                    }
                }, {
                    iconCls: 'icon-refresh',
                    handler: function () {
                        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                    }
                }, {
                    iconCls: 'icon-del',
                    handler: function () {
                        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        var tab = index_tabs.tabs('getTab', index);
                        if (tab.panel('options').closable) {
                            index_tabs.tabs('close', index);
                        }
                    }
                }]
            });

            layout_west_tree = $('#layout_west_tree').tree({
                url: '${path}/resource/tree',
                parentField: 'pid',
                lines: true,
                onClick: function (node) {
                    if (node.attributes.indexOf("http") >= 0) {
                        var url = node.attributes;
                        addTab({
                            url: url,
                            title: node.text,
                            iconCls: node.iconCls
                        });
                    } else if (node.attributes) {
                        var url = '${path}' + node.attributes;
                        addTab({
                            url: url,
                            title: node.text,
                            iconCls: node.iconCls
                        });
                    }
                }
            });
        });

        function addTab(params) {
            var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
            var t = $('#index_tabs');
            var opts = {
                title: params.title,
                closable: true,
                iconCls: params.iconCls,
                content: iframe,
                border: false,
                fit: true
            };
            if (t.tabs('exists', opts.title)) {
                t.tabs('select', opts.title);
            } else {
                t.tabs('add', opts);
            }
        }

        function logout() {
            $.messager.confirm('提示', '确定要退出?', function (r) {
                if (r) {
                    progressLoad();
                    $.post('${path }/logout', function (result) {
                        if (result.success) {
                            progressClose();
                            window.location.href = '${path }';
                        }
                    }, 'json');
                }
            });
        }

        function editUserPwd() {
            parent.$.modalDialog({
                title: '修改密码',
                width: 300,
                height: 250,
                href: '${path }/user/editPwdPage',
                buttons: [{
                    text: '确定',
                    handler: function () {
                        var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                        f.submit();
                    }
                }]
            });
        }
        //异步提交表单-可携带文件提交
        function SYS_SUBMIT_FORM(form,url,fn){
        	url='${path}'+url;
        	$(function() {
        		var ajaxFormOption = {
        			type : "post", // 提交方式
        			dataType : "json", // 数据类型
        			url : url, // 请求url
        			success : function(data) { // 提交成功的回调函数
        				if(fn!=null&&typeof(fn)=="function"){
        					fn(data);
        				}
        			},
        			error : function(data, s) {

        			}
        		};
        		$(form).ajaxSubmit(ajaxFormOption);
        		return false;
        	});
        }
    </script>
</head>
<body>
<div id="loading"
     style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
    <img src="${staticPath}/static/style/images/ajax-loader.gif"
         style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
</div>
<div id="index_layout">
    <div data-options="region:'north',border:false" style=" overflow: hidden; ">
        <div>
            <span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">欢迎 <b>
                <shiro:principal></shiro:principal></b>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton" plain="true"
                    icon="icon-edit">修改密码</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton" plain="true" icon="icon-clear">安全退出</a>
            </span>
            <span class="header"></span>
        </div>
    </div>

    <div data-options="region:'west',split:true" title="菜单"
         style="width: 160px; overflow: hidden;overflow-y:auto; padding:0px">
        <div class="well well-small" style="padding: 5px 5px 5px 5px;">
            <ul id="layout_west_tree"></ul>
        </div>
    </div>

    <div data-options="region:'center'" style="overflow: hidden;">
        <div id="index_tabs" style="overflow: hidden;">
            <div title="首页" data-options="border:false" style="overflow: hidden;">
                <ul class="txtlist">
                    <c:forEach items="${articleVoList}" var="articleVo" varStatus="status">
                        <li>
                            <h3>
                                <a href="/article/displayPage?id=${articleVo.id}" title="${articleVo.title}" aria-label="${articleVo.title}" data-pjax="0" target="_blank">
                                    ${articleVo.title}
                                </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span>作者:${articleVo.author}</span>
                                <span>分类:${articleVo.categoryName}</span>
                            </h3>
                        </li>

                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

    <div data-options="region:'south',border:false"
         style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee">Copyright ©
        2015 power by <a href="http://www.yzjkeji.com/" target="_blank">亿中景</a></div>
</div>

<!--[if lte IE 7]>
<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a
        href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a
            href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a
            href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/"
                                                                                   target="_blank">Opera</a></p></div>
<![endif]-->

<style>
    /*ie6提示*/
    #ie6-warning {
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        background: #fae692;
        padding: 5px 0;
        font-size: 12px
    }

    #ie6-warning p {
        width: 960px;
        margin: 0 auto;
    }
</style>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
    <title>绵阳银行信贷管理系统</title>
	<%@include file="/common/nui/common.jsp"%>  
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
    <div title="常用快捷功能" region="west" width="200" showHeader="true" expanded="false">
		<div id="panel_west" class="nui-panel" title="" style="width:100%;height:auto;" 
			showToolbar="false" showCollapseButton="false" showHeader="false" showFooter="false" allowResize="false"
			visible="true" borderStyle="border:0px;">
			<ul id="tree1" class="nui-tree" style="padding:5px;" 
    			showTreeIcon="true" textField="funcname" idField="id" onnodeclick="onNodeClick">
			</ul>
		</div>
    </div>
    <div title="" region="east" showCloseButton="false" showHeader="false"
    	width="250" showCollapseButton="true">
    	<div id="eastRegion" style="border:0px; margin:0px; padding:0px;"></div>
    </div>
    <div title="center" region="center" style="padding-left:0.1%">
    	<div id="centerRegion" style="border:0px; margin:0px; padding:0px; overflow-x: hidden;"></div>
    </div>
</div>

<script type="text/javascript" src="<%=contextPath %>/pub/operconfig/portals_config.js"></script>
<script type="text/javascript">
    nui.parse();
    
function initPage(){
	nui.get("panel_west").mask();
	//获取快捷菜单
	$.ajax({
	            url: "com.bos.pub.sys.getOperShortcut.biz.ext",
	            type: 'POST',
	            data: {},
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                var o = nui.decode(mydata);
	                var its = o.items;
	                its = its.sort(function (a, b){
	                	return parseInt(a.orderno) - parseInt(b.orderno);
	                });
	                
	                var mis = top.getMenuItems ? top.getMenuItems() : [];
	                its = $.grep(its, function (n) {
	                	for (var i=0; i<mis.length; i++) {
	                		if (mis[i].url == n.funcaction)
	                			return true;
	                	}
	                	return false;
	                });
	                nui.get("tree1").loadData(its);
	                nui.get("panel_west").unmask();
	            }
	});
	
	//初始化首页面板配置
	initIndex();
	//处理portal
	$.ajax({
	            url: "com.bos.pub.operConfig.getAcOperconfigList.biz.ext",
	            type: 'POST',
	            data: {},
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                var o = nui.decode(mydata);
	                var its = o.acOperconfigs;
	                its = its.sort(function (a, b){
	                	return parseInt(a.configvalue) - parseInt(b.configvalue);
	                });
	                
	                var eastRegion=$('#eastRegion');
					var centerRegion=$('#centerRegion');
					eastRegion.html('');
					centerRegion.html('');
					
					if (its.length >= 0) {
						<%--//已配置个性化portal(暂不适用)
						for (var i=0,len=its.length; i<len; i++) {
							var portal=null;
							$.each(portals, function(idx, v) {
			                	if (v && v.id == its[i].configname) {
			                		portal=v;
			                		return false;
			                	}
			                });
			                if (portal == null)
			                	continue;
			                	
			                portal.inited=true;
			                var html='<div class="nui-panel" title="'+portal.text+'" style="width:'+portal.width+';" '
								+ ' showToolbar="false" showCloseButton="false" showFooter="false" showCollapseButton="true" '
								+ ' url="<%=contextPath %>'+portal.url+'" borderStyle="border:0px;" onbuttonclick=""></div>';
							var div=$(html);
							if (portal.isSmall==true) {
								div.appendTo(eastRegion);
								div[0].style.width = '98%';
								div[0].style.marginLeft = '1%';
							} else {
								div.appendTo(centerRegion);
								div[0].style.float = 'left';
								div[0].style.margin = '1px';
								div.attr('borderStyle', 'border:solid 1px;');
							}
							if (portal.height) {
								div[0].style.height=portal.height;
							}
						}--%>
						
						for (var i=0,len=portals.length; i<len; i++) {//处理必选项
							if (portals[i].isRequired !== true || portals[i].inited) {
								continue;
							}
							portals[i].inited=true;
							var html='<div class="nui-panel" title="'+portals[i].text+'" style="width:'+portals[i].width+';" '
								+ ' showToolbar="false" showCloseButton="false" showFooter="false" showCollapseButton="true" '
								+ ' url="<%=contextPath %>'+portals[i].url+'" borderStyle="border:0px;" onbuttonclick=""></div>';
							var div=$(html);
							if (portals[i].isSmall==true) {
								div.appendTo(eastRegion);
								div[0].style.width = '98%';
								div[0].style.marginLeft = '1%';
							} else {
								div.appendTo(centerRegion);
								div[0].style.float = 'left';
								div[0].style.margin = '1px';
								div.attr('borderStyle', 'border:solid 0px;');
							}
							if (portals[i].height) {
								div[0].style.height=portals[i].height;
							}
						}
					} else {
						//未配置个性化portal
						for (var i=0,len=portals.length; i<len; i++) {
							if (portals[i].isDefault !== true) {
								continue;//非默认的不显示
							}
							
							portals[i].inited=true;
							var html='<div class="nui-panel" title="'+portals[i].text+'" style="width:'+portals[i].width+';" '
								+ ' showToolbar="false" showCloseButton="false" showFooter="false" showCollapseButton="true" '
								+ ' url="<%=contextPath %>'+portals[i].url+'" borderStyle="border:0px;" onbuttonclick=""></div>';
							var div=$(html);
							if (portals[i].isSmall==true) {
								div.appendTo(eastRegion);
								div[0].style.width = '98%';
								div[0].style.marginLeft = '1%';
							} else {
								
									div.appendTo(centerRegion);
									div[0].style.float = 'left';
									div[0].style.margin = '1px';
									
									div.attr('borderStyle', 'border:solid 1px;');
								
							}
							if (portals[i].height) {
								div[0].style.height=portals[i].height;
							}
						}
						
					}
					
					
					//行长处理
						var html;
						
						if(GovernorFlag=="true"){
							//Box 为了居中
							var divBoxHtml = '<div align="center" ><div/>';
							var divBox = $(divBoxHtml);
							divBox.appendTo(centerRegion);
							
							//默认背景 
							var bgImgHtml = '<img border="0" usemap="#Map" />'
							var bgImg = $(bgImgHtml);
							bgImg[0].src="<%=contextPath %>"+index_bg_src; 
							bgImg.appendTo(divBox);
							
						
							//区域映射Map
							var mapHtml = '<map name="Map" id="Map"></map>';
							var map=$(mapHtml);
							map.appendTo(centerRegion);
							
							for(var i=0,len=GovernorPortals.length; i<len; i++){
									var html = '<area shape="rect" />';
									var area=$(html);
									area.appendTo(map);
									area[0].href = "javascript:void(0)";//取消默认href 为了出现手势
									area[0].coords = GovernorPortals[i].coords;
									area[0].linkUrl = GovernorPortals[i].linkUrl;
									area[0].textTitle = GovernorPortals[i].text;
									area[0].id = GovernorPortals[i].id;
									var obj = $('#'+GovernorPortals[i].id);
									//点击open窗口
									obj[0].onclick=function(){
										nui.open({
									        url: '<%=contextPath %>'+this.linkUrl,
									        title: this.textTitle||"查看", 
									        width: 0,
											height:0,
											state:"max",
									        allowResize:true,
											showMaxButton: true,
									        onload: function () {
									            var iframe = this.getIFrameEl();
									            //iframe.contentWindow.SetData(data);
									        },
									        ondestroy: function (action) {
									            if(action=="ok"){
									       	 	}
									        }
									    });
									}
									
							}
						}
					
					
					nui.parse(eastRegion[0]);
					nui.parse(centerRegion[0]);
					
					
					for (var key in mini.uids) {
						var obj=mini.uids[key];
						if (obj.type!='panel')
							continue;
						console.log('正在处理：'+obj.title);
						var btn={"name":"maxIt","cls":"mini-tools-max","style":"","visible":true,"enabled":true,"html":""};
						obj.buttons.push(btn);
						obj._doTools();
						var maxBtn=obj._toolsEl.children[0];
						maxBtn.onclick=function() {//点击最大化按钮时的处理
							var iframe=this.parentNode.parentNode.parentNode.nextSibling.getElementsByTagName('iframe')[0];
							var title=this.parentNode.parentNode.innerText;
							nui.open({
						        url: iframe.src,
						        title: title||"查看", 
						        width: 0,
								height:0,
						        allowResize:true,
								showMaxButton: true,
						        onload: function () {
						            var iframe = this.getIFrameEl();
						            //iframe.contentWindow.SetData(data);
						            this.max();
						        },
						        ondestroy: function (action) {
						            if(action=="ok"){
						       	 	}
						        }
						    });
						    console.log(maxBtn.onclick);
						}
						
					}
	            }
	});
};

$(function(){
	setTimeout(initPage, 1);
});
   	function onNodeClick(e){
   		var item = e.node;
   		if (!item.funcaction)
   			return;
   		//self.location.href=nui.context + item.url;
   		if (item.funcaction.indexOf('/')!=0)
   			item.funcaction = '/' + item.funcaction;
   		git.go(nui.context + item.funcaction);
   	}
</script>
</body>
</html>
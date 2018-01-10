<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
    <title>绵阳银行信贷管理系统</title>
	<%@include file="/common/nui/common.jsp"%>  
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
    <div title="常用快捷功能" region="west" width="200" showHeader="true">
		<div id="panel_west" class="nui-panel" title="" style="width:100%;height:auto;" 
			showToolbar="false" showCollapseButton="false" showHeader="false" showFooter="false" allowResize="false"
			expanded="true" visible="true" borderStyle="border:0px;">
			<ul id="tree1" class="nui-tree" style="padding:5px;" 
    			showTreeIcon="true" textField="funcname" idField="id" onnodeclick="onNodeClick">
			</ul>
		</div>
    </div>
    <div title="east" region="east" showCloseButton="false" showHeader="false"
    	width="300">
        <div class="nui-panel" id="panel_notice" title="公共栏" iconCls="" style="width:98%;height:280px;" 
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="color:red;">
    		<marquee align="left" behavior="scroll" direction="up" 
    			loop="-1" scrollamount="5" scrolldelay="100" 
    			onMouseOut="this.start()" onMouseOver="this.stop()"
    			style="" height="100%">
    			<ul id="noticeul">
	    		<li><a href="#" onclick="return false;">正在加载…</a></li>
    			</ul>
    		</marquee>
        </div>
        <br/>
        <div class="nui-panel" id="panel_market" title="市场信息" iconCls="" style="width:98%;height:150px;" 
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="color:red;">
    		<marquee align="left" behavior="scroll" direction="up" 
    			loop="-1" scrollamount="5" scrolldelay="100" 
    			onMouseOut="this.start()" onMouseOver="this.stop()"
    			style="" height="100%">
	    		<ul id="marketul">
	    		<li><a href="#" onclick="return false;">正在加载…</a></li>
	    		</ul>
    		</marquee>
        </div>
    </div>
    <div title="center" region="center" >
    	<table style="width:100%;height:100%;table-layout:fixed;" class="nui-form-table" >
			<tr style="height:48%;"><td style="width:33%;">
		<div id="panel_workCount" class="nui-panel" title="流程任务" style="width:98%;height:98%;" 
			showToolbar="false" showCollapseButton="false" showHeader="true" showFooter="false" allowResize="false"
			expanded="true" visible="true" borderStyle="">
			<ul id="workCount">
    		</ul>
		</div>
			</td><td style="width:33%;">
        <div class="nui-panel" title="统计分析" iconCls=""
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="" style="width:98%;height:98%;">
    		<ul>
    		<li><a href="<%=request.getContextPath() %>/xx7" onclick="">小企业结算贷业务季报统计表</a></li>
    		<li><a href="<%=request.getContextPath() %>/xx8" onclick="">贷款投向分布表</a></li>
    		<li><a href="<%=request.getContextPath() %>/x8x" onclick="">表外业务趋势表</a></li>
    		</ul>
        </div>
			</td><td style="width:33%;">
        <div class="nui-panel" title="贷后管理" iconCls=""
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="" style="width:98%;height:98%;">
    		<ul>
    		<li><a href="<%=request.getContextPath() %>/xx9" onclick="">日常检查</a></li>
    		<li><a href="<%=request.getContextPath() %>/xx" onclick="">分类</a></li>
    		<li><a href="<%=request.getContextPath() %>/xx" onclick="">预警</a></li>
    		</ul>
        </div>
			</td>
			</tr>
			<tr style="height:48%;"><td style="width:33%;">
        <div class="nui-panel" title="渠道业务" iconCls=""
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="" style="width:98%;height:98%;">
    		<ul>
    		<li><a href="<%=request.getContextPath() %>/xx0" onclick="">网银业务</a></li>
    		<li><a href="<%=request.getContextPath() %>/x0x" onclick="">渠道商（如供应链合作方）</a></li>
    		<li><a href="<%=request.getContextPath() %>/x1x" onclick="">阿里巴巴</a></li>
    		</ul>
        </div>
			</td><td style="width:33%;">
        <div class="nui-panel" title="移动办公" iconCls=""
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="" style="width:98%;height:98%;">
    		<ul>
    		<li><a href="<%=request.getContextPath() %>/x2x" onclick="">离线数据同步</a></li>
    		<li><a href="<%=request.getContextPath() %>/x3x" onclick="">客户端软件下载</a></li>
    		<li><a href="<%=request.getContextPath() %>/x4x" onclick="">客户端配置文档</a></li>
    		</ul>
        </div>
			</td><td style="width:33%;">
        <div class="nui-panel" title="常用工具" iconCls=""
    		showToolbar="false" showCloseButton="false" showFooter="false"
    		headerStyle="" style="width:98%;height:98%;">
    		<ul>
    		<li><a href="<%=request.getContextPath()%>/acc/accinitindex/acc_index_init.jsp" onclick="">财务分析</a></li>
    		<li><a href="<%=request.getContextPath() %>/x6x" onclick="">贷款定价</a></li>
    		<li><a href="<%=request.getContextPath() %>/x7x" onclick="">计量</a></li>
    		<li><a href="#" onclick="toHuiLv()">汇率</a></li>
    		<li><a href="#" onclick="toLiLv()">利率</a></li>
    		</ul>
        </div>
			</td>
			</tr>
		</table>
    </div>
</div>

<script type="text/javascript">
	//var funcShortcut=[];
    nui.parse();
    
    //nui.get("tree1").loadData(funcShortcut);
$(function(){
	nui.get("panel_west").mask();
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
	                
	                var mis = top.getMenuItems() || [];
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
	
	nui.get("panel_market").mask();
	var json = nui.encode({item:{infoStatus:"1"}});
	$.ajax({
	            url: "com.bos.pub.marketinfo.getItemListAll.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                var o = nui.decode(mydata);
	                var its = o.items;
	                //its = its.sort(function (a, b){
	                //	return parseInt(a.orderno) - parseInt(b.orderno);
	                //});
	                
	                var marketul = $("#marketul");
	                marketul.html("");
	                $.each(its, function(idx,ele){
	                	//alert(nui.encode(ele));
	                	$("<li><a href='#' onclick='viewMarketInfo(\""+ele.INFO_ID+"\")' title='"
	                		+ele.INFO_CONTENT+"'>"
	                		+ele.INFO_TITLE+"</a></li>").appendTo(marketul);
	                });
	                nui.get("panel_market").unmask();
	            }
	});
	
	nui.get("panel_notice").mask();
	var json = nui.encode({item:{infoStatus:"1"}});
	$.ajax({
	            url: "com.bos.pub.noticeinfo.getItemListAll.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                var o = nui.decode(mydata);
	                var its = o.items;
	                
	                var noticeul = $("#noticeul");
	                noticeul.html("");
	                $.each(its, function(idx,ele){
	                	//alert(nui.encode(ele));
	                	$("<li><a href='#' onclick='viewNoticeInfo(\""+ele.INFO_ID+"\")' title='"
	                		+ele.INFO_CONTENT+"'>"
	                		+ele.INFO_TITLE+"</a></li>").appendTo(noticeul);
	                });
	                nui.get("panel_notice").unmask();
	            }
	});
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
   	
   	function initWorkCount() {
   		nui.get("panel_workCount").mask();
   		var json = nui.encode({o:1});
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.workinglistCount.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
            	if(text.flows){
            		var flows=text.flows;
            		//nui.alert(nui.encode(flows));
            		var html="";
            		for (var i=0; i<flows.length; i++) {
            			var t=nui.getDictText("XD_WFCD0001",flows[i].flowTypeCd);
            			if(null == t || '' == t){
            				t = "其它";
            			}
            			html +='<li><a href="#" onclick="git.go(\'<%=request.getContextPath() %>/csm/workdesk/mywork.jsp?flowTypeCd='
            				+ flows[i].flowTypeCd
            				+ '\');return false;">'
            				+ t
            				+ '&nbsp;('
            				+ flows[i].cnt
            				+ ')</a></li>';
            		}
            		document.getElementById("workCount").innerHTML=html;
            		nui.get("panel_workCount").unmask();
    		//<li><a href="<%=request.getContextPath() %>/csm/workdesk/mywork.jsp" onclick="">评级申请 2</a></li>
            	} else {
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
   	}
   	initWorkCount();
   	
   	
function viewMarketInfo(id) {
    nui.open({
        url: nui.context+"/pub/marketinfo/item_edit.jsp?itemId="+id+"&view=1",
        title: "查看", 
        width: 800,
		height: 500,
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
function viewNoticeInfo(id) {
    nui.open({
        url: nui.context+"/pub/noticeinfo/item_edit.jsp?itemId="+id+"&view=1",
        title: "查看", 
        width: 800,
		height: 500,
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

function toHuiLv(){

 nui.open({
        url: nui.context+"/pub/exchangeRate/item_list.jsp",
        title: "汇率", 
        width: 1000,
		height: 530,
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

function toLiLv(){

 nui.open({
        url: nui.context+"/pub/basicRate/basicRate_list.jsp",
        title: "利率", 
        width: 1000,
		height: 530,
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
</script>
</body>
</html>
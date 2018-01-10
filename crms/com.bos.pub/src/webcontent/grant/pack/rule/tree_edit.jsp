<!-- 
作者：王世春
日期：2014-3-27
Email：wsc.hi@163.com
功能：决策树编辑

决策树：
	节点只能横向拖动；
	节点间连线自动设置，不能修改；
	节点间连线可设置名称、判断条件（“如果……那么”的如果部分的条件）；
	节点分为：决策结果节点、计算节点；
		结果节点是一个规则；
		计算节点是一个规则；


 -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn">
<head>
  <meta content="text/html; charset=utf-8" http-equiv="content-type">
  <meta http-equiv="X-UA-Compatible" content="IE=7" />
  <meta charset="utf-8">
  <title>决策树编辑</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/pub/decision/themes/cupertino/jquery-ui.min.css">
  <script src="<%=request.getContextPath() %>/pub/decision/json3.min.js"></script>
  <script src="<%=request.getContextPath() %>/pub/decision/jquery-1.9.1.js"></script><%-- /common/nui/jquery/jquery-1.6.2.min.js --%>
  <script src="<%=request.getContextPath() %>/pub/decision/jquery-ui.js"></script>
  <script src="<%=request.getContextPath() %>/pub/decision/rule_interface.js"></script>
  <script src="<%=request.getContextPath() %>/pub/decision/rule.js"></script>
  <style>
  *{font-size:12.5px;}
  td, .border1 {border-color:#dddddd;border-style:solid;border-width:1px;}
  .border2 {border-color:#999;border-style:dashed;border-width:1px;}
  .expr>div{
	display:inline;
  }
  .expr>span{
	display:inline;
  }
  .exprParam{
  	/*border-bottom:solid blue 1px;
  	*/
  	background-color:#eee;
	margin-right:15px;
  }
  .ifthen{
	float:none;
  }
  .indent{
	margin-left:30px;
  }
  .ui-menu {
    width: 200px;
  }
  .ui-sortable > div,.ui-sortable > span {
	cursor:pointer;margin-right:5px;
  }
  #treeDiv p { width: 100px; height: auto; padding: 6px; float: left;}
  .tree_node_selected { border: solid 1px purple;}
  .ui-widget-content {position: absolute;}
  </style>
</head>
<body>
<div id="leftBar1" style="position:absolute;left:2px;top:2px;z-index:33;height:300px;width:80px;">
决策树：右键单击任意节点，查看可进行的操作。<br/><br/>
	<a href="#" style="cursor:pointer;" id="btnRefresh" onclick="initTreeLines()">刷新（重新绘制线条）</a><br/><br/>
	<a href="#" style="cursor:pointer;" id="btnValid" onclick="validMainXML()">校验</a><br/>
	<a href="#" style="cursor:pointer;" id="btnSave" onclick="saveMainXML()">保存</a><br/>
	<a href="#" style="cursor:pointer;" id="btnTest" onclick="testRule()">测试（保存后的内容）</a><br/><br/>
	<a href="#" style="cursor:pointer;" id="btnCopy" onclick="copyExpr()">复制</a><br/>
	<a href="#" style="cursor:pointer;" id="btnPaste" onclick="pasteExpr()">粘贴</a><br/>
	<a href="#" style="cursor:pointer;" id="btnClose" onclick="if (self.CloseOwnerWindow)self.CloseOwnerWindow();else self.close();">关闭</a>
</div>

<ul id="menu" style="position:absolute;">
</ul>

<div id="treeDiv" style="position:absolute;left:105px;height:5000px;border-left:solid 1px;">&nbsp;
	<!--<p id="x" class="ui-widget-content">xxxxxxxxx</p>
	<p class="ui-widget-content">ddddd</p>-->
</div>
<div style="height:500px;">
</div>

<script type="text/javascript">
function moveCurrentDOMElement(left, top) {
	//移动当前元素
	if (!currentDOMElement)
		return;
	
	var cur=$(currentDOMElement);
	cur=cur[0];
	
	var preLeft=cur.style.left || '0px';
	preLeft=preLeft.replace('px','');
	var preTop=cur.style.top || '0px';
	preTop=preTop.replace('px','');
	//alert(preLeft+','+preTop);
	cur.style.left=(preLeft*1+left)+'px';
	cur.style.top=(preTop*1+top)+'px';
	
	//initTreeLines();
}
function preventDefault(e) {
	if (!e)
		return;
	var event2=e.originalEvent || window.event;
	if (!event2)
		return;
	if (event2.stopPropagation)
		event2.stopPropagation();//取消事件冒泡
	if (event2.preventDefault)
		event2.preventDefault();//阻止默认行为
	if (window.event) {
		window.event.returnValue=false;//阻止默认行为:IE
		window.event.cancelBubble=true;//取消事件冒泡:IE
	}
}
self.onscroll = function() {
	$('#leftBar1')[0].style.top=((document.body.scrollTop || document.documentElement.scrollTop) + 10)+'px';
}
document.oncontextmenu = function(e){//屏蔽右键菜单
	if(window.event){
		e=window.event;
		e.returnValue=false;
	}else{
		e.preventDefault();
	}
};
$(document.body).click(function (e) {
	if (e && e.button == 2)
		hideMenu();//IE only
});
$(document.body).keydown(function(e) {
	var keyCode=e.keyCode || e.which;
	if (keyCode == 27) {
		//ESC键
		hideMenu();
		return;
	}
	var movePix=15;
	if (!currentDOMElement)
		return;
	var event2=e.originalEvent || self.event;
	if (keyCode == 37) {
		//左方向键
		moveCurrentDOMElement(-1*movePix, 0);
		preventDefault(e);
		return;
	}
	if (keyCode == 38) {
		//上方向键
		moveCurrentDOMElement(0, -1*movePix);
		preventDefault(e);
		return;
	}
	if (keyCode == 39) {
		//右方向键
		moveCurrentDOMElement(movePix, 0);
		preventDefault(e);
		return;
	}
	if (keyCode == 40) {
		//下方向键
		moveCurrentDOMElement(0, movePix);
		preventDefault(e);
		return;
	}
});
//弹出对话框
function dialog(dom, btns, p) {
	self.dialogdom=dom;
	$('<div style="height:100px;width:20px;"><br/></div>').appendTo($(dom));
	$(dom).dialog($.extend({},{
		autoOpen: true, 
		draggable: true, 
		modal: true,
		resizable: true,
		/*show: {
				effect: "blind",
				duration: 1000
		},
		hide: {
			effect: "explode",
			duration: 1000
		},*/
		width:'800',height:'400',
		buttons: btns ? btns : [ { text: "确定", click: function() { $( this ).dialog( "close" );}}] },p));
}

var tree = $('#treeDiv');
var ruleitem=null;//决策树保存在数据库的内容
$(function (){
	$('#menu').hide();
	if ('1' == '<%=request.getParameter("view") %>') {
		//查看模式
		$('#btnSave').hide();
		$('#btnPaste').hide();
	}
	
	var rules={"rules":[
		{"type":"assign","text":"金额_万元","expr":[{"text":"金额","type":"数值型", "numbertype":"double"},{"text":"/","name":"/","type":"op"},{"type":"const","text":"10000"}]},
		{"type":"ifthen",
			"ifexpr":[{"type":"expr","text":"金额_万元","paramName":"金额_万元"},{"text":"&gt;=","name":">=","type":"op"},{"type":"const","text":"1000"},
				{"text":"并且","name":"&&","type":"op"},
				{"text":"包含文字……","ptext":"“{1}”包含“{2}”","funcname":"contains","type":"是否型",
					"args":[
						{"type":"文本型","text":"进行判断的文本","val":[{"text":"客户行业","type":"文本型"}]},
						{"type":"文本型","text":"被包含的文本","val":[{"type":"const","text":"钢铁"}]}]}],
			"thenexpr":{"rules":[
				{"type":"expr","expr":[{"type":"const","text":"总行审批"}]}]}},{"type":"expr","expr":[{"type":"const","text":"分行审批"}]}]};
	rules={"rules":[]};//上面是示例，此处清空示例
	
	$.ajax({
       url: "com.bos.pub.decision.getRuleLob.biz.ext",
       type: 'POST',
       data: JSON.stringify({rid:"<%=request.getParameter("rid") %>", lob:"rcontent"}),
       cache: false,
       contentType:'text/json',
       success: function (text) {
	       	if(text.msg){
	       		alert(text.msg);
	       	} else {
	       		var rcontent=text.item.rcontent;
				if (!rcontent || rcontent.length < 15) {
					rcontent='<rules></rules>';
				}
				//console.log('数据库后台获取的xml：'+rcontent);
				var x={};
				XML2JSON(rcontent,x);
				self.ruleitem = x;
				
	       		//console.log('转换为JSON对象后：'+JSON.stringify(self.ruleitem));
				initTree(self.ruleitem);
	       	}
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
       }
    });
});

var currentDOMElement=null;//记录当前选中的树节点
var menuShowed = false;//判断当前菜单是否已显示
var treeNodesCache={};//缓存树节点信息
var treeLinesCache=[];//缓存树的连线信息，主要用于initTreeNodes中的时候先去掉线，再重新划线
var draggableParam={cursor: "move", stop: dragStop};//自由拖动节点时的参数 //, axis: "x"
var MARGIN_Y = 80;
function dragStop(event, ui) {
	//alert(ui.helper[0].id);
	//alert(ui.position.left);
	initTreeNodes();//采用就jQueryUI时才需要此行代码
	currentDOMElement=$('#'+ui.helper[0].id)[0];
	selectCurrentElement();
	preventDefault(event);
}
function initTree(ruleitem) {
	//初始化
	tree.html('');
	treeNodesCache={};
	treeNodesCache.length=0;
	
	ruleitem = ruleitem || self.ruleitem;
	//top.x=ruleitem;
	
	var id='treeNode'+treeNodesCache.length;
	treeNodesCache.length += 1;
	
	var node=$('<p class="ui-widget-content" id="'+id+'" type="treeNode">开始</p>');
	node.appendTo($('#treeDiv'));
	
	treeNodesCache[id]={level:0, name:'开始', children:[], rule:[]};
	if (!ruleitem || (ruleitem.rules && ruleitem.rules.length < 1)) {
		//决策树是新建的，没有内容
		//console.log('决策树是新建的，没有内容');
	} else {
		//初始化各个节点
		var parentId=null;
		if (ruleitem && ruleitem.rules){
			for (var i=0, len=ruleitem.rules.length; i<len; i++) {
				var item = ruleitem.rules[i];
				initRuleItem(item, id);
			}
		}
	}
	initTreeNodes();//首次初始化有问题
	//setTimeout(initTreeNodes, 100);
}
function initRuleItem(rule, parentId) {
	//ifthen如果那么 assign设置参数 expr规则结果为
	var id='treeNode'+treeNodesCache.length;
	treeNodesCache.length += 1;
	//console.log('rule:'+JSON.stringify(rule));
	
	var node=$('<p class="ui-widget-content" id="'+id+'" type="treeNode">&nbsp;</p>');
	node.appendTo($('#treeDiv'));
	node.html(rule.nodename);
	
	var offset=node.offset();
	if (rule.style && rule.style.left) {
		if (rule.style.left < 0)
			rule.style.left = 0;
		var x=(node[0].style.left||'0px').replace('px','')*1;
		x=x - (offset.left-rule.style.left);
		node[0].style.left=x + 'px';
	}
	if (rule.style && rule.style.top) {
		if (rule.style.top < 0)
			rule.style.top=0;
		var y=(node[0].style.top||'0px').replace('px','')*1;
		y=y - (offset.top-rule.style.top);
		node[0].style.top=y + 'px';
	}
	//offset=node.offset();
	//console.log(offset.left);
	
	var parentNodeCache=treeNodesCache[parentId];
	parentNodeCache.children[parentNodeCache.children.length]={id:id};
	treeNodesCache[id]={level:(parentNodeCache.level+1),parentId:parentId, children:[], rule:[]};
	
	if (rule.type == 'expr') {
		//规则结果为：叶子节点
		treeNodesCache[id].func=rule.ifexpr||[];
		treeNodesCache[id].rule=rule.expr||[];
		treeNodesCache[id].name=rule.nodename;
		return;
	} else if (rule.type == 'assign') {
		//设置参数：非叶子节点
		treeNodesCache[id].rule=rule.expr||[];
		treeNodesCache[id].func=rule.ifexpr||[];
		treeNodesCache[id].name=rule.nodename;
		
		var rules=rule.rules||[];//子节点
		for (var i=0,len=rules.length; i<len; i++) {
			var item = rules[i];
			initRuleItem(item, id);
		}
	} else if (rule.type == 'ifthen') {
		//如果那么：需要处理进入条件，及那么部分的多条规则（即子节点树）
		treeNodesCache[id].rule=rule.expr||[];//所调用的规则
		treeNodesCache[id].func=rule.ifexpr||[];//进入条件
		treeNodesCache[id].name=rule.nodename; 
		
		
		
		var thenexpr=rule.thenexpr||{};
		thenexpr=thenexpr.rules||[];
		//if (thenexpr.length != 1) {
		//	alert('决策树内容格式错误！');
		//	return;
		//}
		//treeNodesCache[id].func=rule.ifexpr||[];//进入条件
		for (var i=0,len=thenexpr.length; i<len; i++) {
			var item = thenexpr[i];
			initRuleItem(item, id);//子节点
		}
	}
}

//-------------------------数据库相关处理 start
//获取决策树内容
function getMainExpr(obj, ismain) {
	obj = obj ? obj : document.getElementById('treeNode0');//treeDiv
	obj = $(obj);
	var expr={rules:[]};
	
	var node=treeNodesCache[obj[0].id];//从开始节点找，所有下级节点为rule元素
	//console.log('获取节点'+obj[0].id+'内容：'+JSON.stringify(node));
	
	var chs = node.children||[];//子节点
	var func = node.func||[];//进入条件
	if (obj[0].id == 'treeNode0' || ismain == true) {//开始节点
		for (var i=0,len=chs.length;i<len;i++) {
			var child=chs[i];
			var childData=treeNodesCache[child.id] || {};
			if (childData.func && childData.func.length > 0) {//处理如果……那么
				expr.rules[expr.rules.length]=getMainExpr(document.getElementById(child.id)).rules[0];
			}
		}
		for (var i=0,len=chs.length;i<len;i++) {
			var child=chs[i];
			var childData=treeNodesCache[child.id] || {};
			if (!childData.func || childData.func.length < 1) {//处理规则结果为：叶子节点
				expr.rules[expr.rules.length]=getMainExpr(document.getElementById(child.id)).rules[0];
			}
		}
		return expr;
	}
	
	if ((!chs || chs.length < 1) && obj[0].id != 'treeNode0') {//无子节点，判定为叶子节点
		if (!func || func.length < 1) {
			expr.rules[expr.rules.length]={
				'type':'expr',
				'nodename':treeNodesCache[obj[0].id].name,
				'style':{left:obj.offset().left, top:obj.offset().top},
				'expr':treeNodeExpr2DBConvert(node.rule||[])
			};//规则结果为
		} else {//有进入条件
			//如果……那么……
			expr.rules[expr.rules.length]={
				'type':'ifthen',
				'nodename':treeNodesCache[obj[0].id].name,
				'style':{left:obj.offset().left, top:obj.offset().top},
				'ifexpr':func,
				'thenexpr':{rules:[]},
				'expr':treeNodeExpr2DBConvert(node.rule||[])
			};
			return expr;
		}
		return expr;
	}
	if (!func || func.length < 1) {
		//参数定义或设置：没有进入条件
		var rules = [];
		expr.rules[expr.rules.length]={
			'type':'assign',
			'nodename':treeNodesCache[obj[0].id].name,
			'style':{left:obj.offset().left, top:obj.offset().top},
			'expr':treeNodeExpr2DBConvert(node.rule||[]),//当前节点所调用的规则
			'rules':rules
		};
		
		for (var i=0,len=chs.length;i<len;i++) {
			var child=chs[i];
			rules[rules.length]=getMainExpr(document.getElementById(child.id)).rules[0];
		}
		//alert(JSON.stringify(rules));
		return expr;
	} else {
		//如果……那么……
		var rules = [];
		expr.rules[expr.rules.length]={
			'type':'ifthen',
			'nodename':treeNodesCache[obj[0].id].name,
			'style':{left:obj.offset().left, top:obj.offset().top},
			'ifexpr':func,
			'thenexpr':{rules:[]},
			'expr':treeNodeExpr2DBConvert(node.rule||[])//当前节点所调用的规则
		};
		//var thenexpr=expr.rules[expr.rules.length-1].thenexpr.rules;
		//for (var i=0,len=chs.length;i<len;i++) {
		//	var child=chs[i];
		//	thenexpr[thenexpr.length]=getMainExpr(document.getElementById(child.id)).rules[0];
		//}
		//console.log('xx:'+JSON.stringify(node));
		expr.rules[expr.rules.length-1].thenexpr=getMainExpr(document.getElementById(obj[0].id), true);
		return expr;
	}
}
function treeNodeExpr2DBConvert(expr) {
	//console.log(JSON.stringify(expr));
	if (!expr || expr.length < 1)
		return [];
	
	expr = expr[0];//目前每个树节点只能调用一个规则或授权表
	if (expr.funcname && expr.funcname.indexOf('invoke') == 0) {
		return [expr];
	}
	if ('rule' == expr.type) {
		//调用的是规则
		var t=expr.resulttype;
		var arr=[];
		if ('3' == t) {//是否型
			arr[arr.length]={
				"text":"调用规则（是否型）",
				"type":"是否型",
				"ptext":"调用规则“{1}”",
				"funcname":"invokeRuleBool",
				"args":[{
						"text":"调用的规则标识",
						"type":"文本型",
						"val":[{"text":expr.rind,"type":"const"}]
				}]
			};
			return arr;
		} else if ('2' == t) {//文本型
			arr[arr.length]={
				"text":"调用规则（文本型）",
				"type":"文本型",
				"ptext":"调用规则“{1}”",
				"funcname":"invokeRuleStr",
				"args":[{
						"text":"调用的规则标识",
						"type":"文本型",
						"val":[{"text":expr.rind,"type":"const"}]
				}]
			};
			return arr;
		} else if ('1' == t) {//数值型
			arr[arr.length]={
				"text":"调用规则（数值型）",
				"type":"数值型",
				"numbertype":"double",
				"ptext":"调用规则“{1}”",
				"funcname":"invokeRuleDouble",
				"args":[{
						"text":"调用的规则标识",
						"type":"文本型",
						"val":[{"text":expr.rind,"type":"const"}]
				}]
			};
			return arr;
		} else if ('4' == t) {//整数型
			arr[arr.length]={
				"text":"调用规则（整数型）",
				"type":"整数型",
				"numbertype":"long",
				"ptext":"调用规则“{1}”",
				"funcname":"invokeRuleLong",
				"args":[{
						"text":"调用的规则标识",
						"type":"文本型",
						"val":[{"text":expr.rind,"type":"const"}]
				}]
			};
			return arr;
		} else {
			alert('出错！规则结果类型不正确！');
			return arr;
		}
	}
	
	
	   //1数值型 2   文本型 3 是否型 4   整数型 
	return [];
}

//校验规则
function validMainXML() {
	var jsonObject=getMainExpr();
	var html=JSON2XML(jsonObject,$('<rules/>'));
	var json=JSON.stringify({'rid':"<%=request.getParameter("rid") %>",'rcontent':html});
	//alert(json);
	$.ajax({
       url: "com.bos.pub.decision.validRule.biz.ext",
       type: 'POST',
       data: json,
       cache: false,
       contentType:'text/json',
       success: function (text) {
       	if(text.msg){
       		alert(text.msg);
       	} else {
       		alert("校验通过");
       	}
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
       }
    });
}
// 弹出窗口测试规则
function testRule() {
	var rid='<%=request.getParameter("rid") %>';
	if (rid == 'null') {
		rid=ruleitem.rid;
	}
	var pid="<%=request.getParameter("pid") %>";
	if ("null" == pid) {
		pid=ruleitem.pid;
	}
	if (top && top.nui) {
		top.nui.open({
	        url: "<%=request.getContextPath() %>/pub/grant/pack/rule/rule_test.jsp?type=<%=request.getParameter("type") %>"
	        	+"&rid="
	        	+rid
	        	+"&modelid=<%=request.getParameter("modelid") %>&pid="+pid,
	        title: "测试", 
	        width: 800,
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	    	showModal: true,
	        ondestroy: function (action) {
	            if(action=="ok"){
	            }
	        }
	    });
	} else {
		var dom=$('<div/>');
		var ifr = $('<iframe frameborder="0" style="width:98%;height:700px;" border="0"/>');
		ifr[0].src="<%=request.getContextPath() %>/pub/grant/pack/rule/rule_test.jsp?type=grant"
	        	+"&rid="
	        	+rid
	        	+"&pid="+pid;
		dom.append(ifr);
		dialog(dom,null,{width:'90%',height:'750'});
	}
}
//复制规则
var RULE_TREE_FLAG='jsonRuleTree:';
function copyExpr(obj) {
	var jsonObject=getMainExpr();
	var str=RULE_TREE_FLAG+JSON.stringify(jsonObject);
	
	copy2Clipboard(str);
	alert('复制成功');
}
//粘贴规则
function pasteExpr(obj) {
	var str=getClipboardText();
	if (null == str || str.length<1) {
		//alert('无复制内容');
		return;
	}
	var jsonObject=JSON.parse(str.substr(RULE_TREE_FLAG.length));
	self.ruleitem = jsonObject;
	//console.log('粘贴的JSON对象：'+JSON.stringify(self.ruleitem));
	initTree(self.ruleitem);
}
//保存规则内容到数据库
function saveMainXML() {
	var jsonObject=getMainExpr();
	var html=JSON2XML(jsonObject,$('<rules/>'));
	var json=JSON.stringify({'rid':"<%=request.getParameter("rid") %>",'rcontent':html});
	//alert(json);
	$.ajax({
       url: "com.bos.pub.decision.saveRuleContent.biz.ext",
       type: 'POST',
       data: json,
       cache: false,
       contentType:'text/json',
       success: function (text) {
       	if(text.msg){
       		alert(text.msg);
       	} else {
       		alert('保存成功');
       		//window.CloseOwnerWindow("ok");
       	}
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
       }
    });
}


//-------------------------数据库相关处理 end


function initTreeNodes() {
	saveCurrentNodeStatus();
	tree.html(tree.html()); //主要是防止draggable多次绑定事件
	var nodes=$('p', tree);
	
	nodes.draggable(draggableParam);
	restoreCurrentNodeStatus();
	//改用jqueruUI的拖动则取消如下所有的“/**/”注释，并注释如上的2行
	nodes.unbind('mouseup');
	nodes.mouseup(function(e){
		currentDOMElement=this;
		selectCurrentElement();
		
		if (e.button != 2)
			return;
			
		showNodeMenu();
	});
	
	// 实现拖动
	/*nodes.unbind('mousedown');
	nodes.mousedown(function(e){
		if (e.button != 1) {
			window.currentMoveDOM=null;
			return;
		}
		window.currentMoveDOM=this;
		window.currentMoveDOMOffset={clientX: e.clientX, clientY: e.clientY};
	});
	$(document).unbind('mousemove');
	$(document).unbind('mouseup');
	$(document).mouseup(function(e){
		if (e.button != 1) {
			window.currentMoveDOM=null;
			return;
		}
		if (null != window.currentMoveDOM) {
			initTreeLines();
			window.currentMoveDOM=null;
		}
		//preventDefault(e);
	});
	$(document).mousemove(function(e){
		if (e.button != 1) {
			window.currentMoveDOM=null;
			return;
		}
		if (window.currentMoveDOM != null) {
			var x=e.clientX - tree.offset().left;
			if (e.clientX > window.currentMoveDOMOffset.clientX) {
				// 右移
				x=x - 50;
			} else {
				x=x - 50;
			}
			
			var y=(e.clientY - tree.offset().top);
			if (e.clientY > window.currentMoveDOMOffset.clientY) {
				// 下移
				y=y - 10;
			} else {
				y=y - 10;
			}
			if (x<0)x=0;
			if (y<0)y=0;
			//console.log('left:'+window.currentMoveDOM.style.left + ','+x+'px');
			//console.log('top :'+window.currentMoveDOM.style.top + y+'px');
			window.currentMoveDOM.style.left=x+'px';
			window.currentMoveDOM.style.top=y+'px';
		} else {
		}
	});*/
	
	initTreeLines();
	
	//整个树区域的初始化
	tree.unbind('mouseup');
}
function toText(func) {
	var text='';
	func = func||[];
	for (var i=0; i<func.length; i++) {
		//console.log(JSON.stringify(func[i]));
		if (func[i].type=='const')
			text += '“';
		text += func[i].text || func[i].name;
		if (func[i].type=='const')
			text += '”';
		text += ' ';
	}
	return text;
}
function initTreeLines() {
	var cache=treeNodesCache;
	for (var i=0, len=treeLinesCache.length; i<len; i++) {
		//先删除所有的线
		var x=$(treeLinesCache[i]).remove();
		delete x;
	}
	//alert(cache.length);
	
	//根据父子关系，重新画线
	for (var node in cache) {//var i=0, len=cache.length; i<len; i++
		if (node == 'length')
			continue;
		
		var parentId=node;
		var parentNode=$('#'+parentId);
		//console.log('准备画线：'+parentId);
		if (parentNode && parentNode.length>0) {
			//重新生成节点的HTML
			var data = treeNodesCache[parentId];
			if (data) {
				var html='';
				var title='';
				var rule=cache[parentId].rule || [];
				//console.log('准备重新生成节点的HTML:'+JSON.stringify(rule));
				if (rule.length > 0) {
					rule=rule[0];
					if (rule.funcname.indexOf('invokeRule') >= 0) {
						title += '调用规则（';
						title += rule.type+'）：';
					} else {
						title += '授权表：';
					}
					title += rule.args[0].val[0].text || '';
					//console.log('title:'+title);
					//html += '<span style="width:100%;border-top:solid 1px blue;display:block;">' +text +'</span>';
					parentNode[0].title=title;
				}
				if (data.func && data.func.length>0) {
					var ppid=data.parentId;
					var functext=toText(data.func);
					if (ppid && cache[ppid] && (functext == cache[ppid].name + ' 等于 “是” ')) {
						functext='是';
					} else if (ppid && cache[ppid] && (functext == cache[ppid].name + ' 等于 “否” ')) {
						functext='否';
					}
					html += '<span style="width:100%;border-bottom:solid 1px blue;display:block;">'
						+functext
						+'</span>';
				}
				html += data.name || '';
				
				
				//console.log('html:'+html);
				if (html && html.length > 0) {
					parentNode.html(html);
				}
			}
		}
		
		var childs = cache[parentId].children;
		if (!childs || childs.length == 0)
			continue;
			
		//给每个父节点和子节点间画线
		var offset=parentNode.offset();
		var width=parentNode.outerWidth();
		var height=parentNode.outerHeight();
		var hflag = 0;
		var margin=30;//30个像素
		for (var j=0, lenj=childs.length; j<lenj; j++) {
			var childId=childs[j].id;
			var childNode=$('#'+childId);
			var childOffset=childNode.offset();
			var childWidth=childNode.outerWidth();
			if (childOffset.left > offset.left + width + margin) {
				hflag += 1;//在父节点右边
			} else if (childOffset.left + childWidth < offset.left + margin) {
				hflag -= 1;//在父节点左边
			}
			//在中间区域时保持0状态
		}
		if (hflag == childs.length) {
			//全在父节点右边
			hflag = true;
		} else if (hflag == -1 * childs.length) {
			//全在父节点左边
			hflag = false;
		} else {
			hflag = null;
		}
		for (var j=0, lenj=childs.length; j<lenj; j++) {
			var childId=childs[j].id;
			initTreeLineBetween(parentId, childId, hflag);
		}
	}
}
function initTreeLineBetween(parentId, childId, hflag) {
	//取两个元素
	var parentNode=$('#'+parentId);
	var childNode=$('#'+childId);
	if (!parentNode || !childNode || parentNode.length > 0 == false || childNode.length > 0 == false)
		return;
	
	//先计算竖线位置
	var offset=parentNode.offset();
	var width=parentNode.outerWidth();//innerWidth含内边距，outerWidth包含内边举、边框
	var height=parentNode.outerHeight();
	var childOffset=childNode.offset();
	var childWidth=childNode.outerWidth();
	var childHeight=childNode.outerHeight();
	var A={
		left:offset.left + width / 2,
		top:offset.top + height
	};//父节点下边框中点
	var B={
		left:A.left,
		top:A.top + Math.abs(childOffset.top - A.top)/3
	};
	
	if ((childOffset.top <= offset.top+height && childOffset.left > offset.left + width) || hflag === true) {
		// 子节点全在父节点右方，或子节点在在父节点右上方
		A={
			left:offset.left + width,
			top:offset.top + height/2
		};//父节点右边框中点
		B={
			left:A.left + Math.abs(childOffset.left - A.left)/3,
			top:A.top
		};//往右的横线距离为“父节点、子节点”间距离的三分之一
		drawTreeLineH(A, B);//横线
		
		var C={
			left:B.left,
			top:childOffset.top + childHeight / 2
		};//横向对齐子节点左边框中点，纵向对齐横线末端
		if (Math.abs(C.top - B.top) < 10)
			C.top = B.top;//微调
		var D={
			left:childOffset.left,
			top:C.top
		};//子节点左边框中点
		
		drawTreeLineV(C, B);//画竖线
		drawTreeLineH(D, C, 'right');//画横线
	} else if ((childOffset.top <= offset.top+height && childOffset.left + childWidth < offset.left) || hflag === false) {
		// 子节点全在父节点左方，或子节点在在父节点左上方
		A={
			left:offset.left,
			top:offset.top + height/2
		};//父节点左边框中点
		B={
			left:A.left - Math.abs(A.left - (childOffset.left + childWidth))/3,
			top:A.top
		};//往左的横线距离为“父节点左边框、子节点右边框”间距离的三分之一
		drawTreeLineH(A, B);//横线
		
		var C={
			left:B.left,
			top:childOffset.top + childHeight / 2
		};//横向对齐子节点右边框中点，纵向对齐横线末端
		if (Math.abs(C.top - B.top) < 10)
			C.top = B.top;//微调
		var D={
			left:childOffset.left + childWidth,
			top:C.top
		};//子节点右边框中点
		
		drawTreeLineV(C, B);//画竖线
		drawTreeLineH(D, C, 'left');//画横线
	} else if (childOffset.top >= offset.top+height) {//子节点在在父节点下方
		//画竖线
		drawTreeLineV(A, B);
		
		//计算拐点的位置
		var C={
			left:childOffset.left + childWidth / 2,
			top:B.top
		};
		if (Math.abs(C.left - B.left) < 10)
			C.left = B.left;
		var D={
			left:C.left,
			top:childOffset.top
		};
		
		drawTreeLineH(B, C);//画横线
		drawTreeLineV(C, D, 'down');//画竖线
	} else {//子节点在在父节点上方，或与父节点重合
		A={
			left:offset.left + width / 2,
			top:offset.top
		};//父节点上边框中点
		var B={
			left:A.left,
			top:A.top - Math.abs(childOffset.top - A.top)/3
		};
	
		//画竖线
		drawTreeLineV(A, B);
		
		//计算拐点的位置
		var C={
			left:childOffset.left + childWidth / 2,
			top:B.top
		};//left对齐子节点左边框中点
		if (Math.abs(C.left - B.left) < 10)
			C.left = B.left;
		var D={
			left:C.left,
			top:childOffset.top + childHeight
		};//子节点下边框
		
		drawTreeLineH(B, C);//画横线
		drawTreeLineV(C, D, 'up');//画竖线
	}
}
function drawTreeLineV(A, B, E) {
	var color='black';
	var line=$('<div style="border:solid 1px '
		+color+';position:absolute;width:1px;background-color:'+color+';"></div>');
		
	if (B.top > A.top) {
		line[0].style.left=A.left+'px';//console.log(A.left);
		line[0].style.top=A.top+'px';
		line[0].style.height=Math.abs(B.top - A.top)+'px';
	} else {
		line[0].style.left=B.left+'px';
		line[0].style.top=B.top+'px';
		line[0].style.height=Math.abs(A.top - B.top)+'px';
	}
	
	line.appendTo(document.body);
	treeLinesCache[treeLinesCache.length]=line;
	
	var img = null;
	if (E == 'down' ) {
		img=$('<img style="position:absolute; " src="../../../decision/down.png"></img>');
		img[0].style.left=(line.offset().left - 6)+'px';
		img[0].style.top=(line.offset().top + line.height() - 16)+'px';
		img.appendTo(document.body);
		treeLinesCache[treeLinesCache.length]=img;
	} else if (E == 'up' ) {
		img=$('<img style="position:absolute; " src="../../../decision/up.png"></img>');
		img[0].style.left=(line.offset().left - 6)+'px';
		img[0].style.top=(line.offset().top + 0)+'px';
		img.appendTo(document.body);
		treeLinesCache[treeLinesCache.length]=img;
	}
}
function drawTreeLineH(A, B, E) {
	var color='black';
	var line=$('<div style="border:solid 1px '
		+color+';position:absolute;height:1px;background-color:'+color+';"></div>');
		
	if (B.left > A.left) {
		line[0].style.left=A.left+'px';
		line[0].style.top=A.top+'px';
		line[0].style.width=Math.abs(B.left - A.left)+'px';
	} else {
		line[0].style.left=B.left+'px';
		line[0].style.top=B.top+'px';
		line[0].style.width=Math.abs(A.left - B.left)+'px';
	}
	line.appendTo(document.body);
	treeLinesCache[treeLinesCache.length]=line;
	
	var img = null;
	if (E == 'right' ) {
		img=$('<img style="position:absolute; " src="../../../decision/right.png"></img>');
		img[0].style.left=(line.offset().left + line.width() - 16)+'px';
		img[0].style.top=(line.offset().top - 6)+'px';
		img.appendTo(document.body);
		treeLinesCache[treeLinesCache.length]=img;
	} else if (E == 'left' ) {
		img=$('<img style="position:absolute; " src="../../../decision/left.png"></img>');
		img[0].style.left=(line.offset().left + 0)+'px';
		img[0].style.top=(line.offset().top - 6)+'px';
		img.appendTo(document.body);
		treeLinesCache[treeLinesCache.length]=img;
	}
}

function refreshTreeNodesCache(id) {
	var cache=treeNodesCache;
	var cacheNew={};
	cacheNew.length=0;
	
	if (id) {
		//删除特定叶子节点
		var parentId=null;
		for (var nodeId in cache) {
			if (nodeId == 'length')
				continue;
			
			var nodeData=cache[nodeId];
			if (nodeId == id) {//删除当前节点
				parentId=nodeData.parentId;
				continue;
			}
			cacheNew[nodeId]=nodeData;//非当前节点保留
			cacheNew.length += 1;
		}//更新所有节点
		for (var nodeId in cacheNew) {
			if (nodeId == 'length')
				continue;
			
			var nodeData=cacheNew[nodeId];
			if (nodeId != parentId)
				continue;
			
			//处理父节点的子节点缓存信息
			if (nodeData.children && nodeData.children.length > 0) {
				nodeData.children=$.grep(nodeData.children,function(val, idx){
					if (val.id == id)
						return false;
					return true;
				});
			}//更新子节点信息
		}//更新所有节点
		treeNodesCache=cacheNew;
		return;
	}
	//删除特定叶子节点
	for (var nodeId in cache) {
		//已删除的节点不再保留缓存信息
		if (nodeId == 'length' || document.getElementById(nodeId) == null)
			continue;
			
		cacheNew[nodeId]=cache[nodeId];
		cacheNew.length += 1;

		var nodeData=cache[nodeId];
		if (nodeData.children && nodeData.children.length > 0) {
			nodeData.children=$.grep(nodeData.children,function(val, idx){
				if (document.getElementById(val.id) == null)
					return false;////已删除的子节点不再保留缓存信息
				return true;
			});
		}//更新子节点信息
	}//更新所有节点
	treeNodesCache=cacheNew;
}

function showNodeMenu() {
	var items=[
		{text:'取消', func:hideMenu},
		{text:'移除', func:menuNodeRemove},
		{text:'增加下级节点', func:addSubNode},
		{text:'设置父节点', func:editParentNode},
		{text:'设置所用规则', func:menuNodeRuleSet},
		/*{text:'设置所用授权表', func:addSubNode},*/
		{text:'设置进入条件', func:menuNodeEnter},
		{text:'修改规则内容', func:menuNodeRule},
		{text:'修改节点名称', func:menuNodeRename}
	];
	if ('1' == '<%=request.getParameter("view") %>') {
		items=[
			{text:'取消', func:hideMenu},
			{text:'查看规则内容', func:menuNodeRule}
		];
	}
	menuShowed = true;
	showMenu(items, true);
}
//设置父节点
function editParentNode() {
	hideMenu();
	if (!currentDOMElement)
		return;
	if (currentDOMElement.id == 'treeNode0') {
		alert('不能操作开始节点');
		return;
	}
	if (self.editParentNodeParam) {
		//已选择当前节点及父节点
		var newParentId=currentDOMElement.id;//父元素的id
		var id=self.editParentNodeParam.childId;//子元素id
		var oldParentId=treeNodesCache[id].parentId;//原来的父元素id
		var conf=confirm('确定要把“'+treeNodesCache[id].name+'”节点的父节点设置为“'+treeNodesCache[newParentId].name+'”吗？');
		
		self.editParentNodeParam=null;
		if (conf) {
			treeNodesCache[id].parentId=newParentId;//将子节点连接到新的parent
			treeNodesCache[newParentId].children[treeNodesCache[newParentId].children.length]={"id":id}; //更新新的父节点的children
			if (oldParentId) {//原来有父节点
				treeNodesCache[oldParentId].children=
					$.grep(treeNodesCache[oldParentId].children, function(v,i){
						if(v.id==id)
							return false;
						else 
							return true;
					});//去掉原父节点的children中的当前节点
			}
			alert('操作成功！');
			initTreeLines();//刷新（重新绘制线条）
		}
		return;
	}
	var id=currentDOMElement.id;//当前元素的id
	self.editParentNodeParam={childId: id};
	alert('已选择好子节点，请右键点击新的父节点并选择“设置父节点”菜单即可完成或取消操作。');
}
//修改规则/授权表内容
function menuNodeRule() {
	hideMenu();
	if (!currentDOMElement)
		return;
	if (currentDOMElement.id == 'treeNode0') {
		alert('开始节点不能修改');
		return;
	}
	var id=currentDOMElement.id;
	var rule=treeNodesCache[id].rule[0];
	var rind='';
	//console.log(JSON.stringify(rule));
	if (!rule) {
		//没有配置规则
		return;
	}
	if (rule.funcname && rule.funcname.indexOf('invokeRule') >= 0) {
		rind=rule.args[0].val[0].text;
	} else {
		rind=rule.rind;
	}
	var div=$('<div><span>规则内容</span>&nbsp;<br/></div>');
	var iframe=$('<iframe src="<%=request.getContextPath()%>/pub/grant/pack/rule/rule_edit.jsp?type=rind'
    	+"&rind="+rind
    	+'&view=<%=request.getParameter("view") %>" frameborder="0" style="width:98%;height:95%;" border="0"></iframe>');
	iframe.appendTo(div);
	var btns=[
		{ text: "确定", click: function() {
			var iframe=$('iframe', this)[0];
			var win=iframe.contentWindow;
			
			this.ok=true;
			$( this ).dialog( "close" );
		}}, 
		{ text: "取消", click: function() { $( this ).dialog( "close" );} }];
	dialog(div,btns,{width:'100%',height:'600', close: function( event, ui ) {
		//initTreeLines();
		
		if (this.ok === true)
			return;
	} });
}

//设置所用规则
function menuNodeRuleSet() {
	hideMenu();
	
	if (!currentDOMElement)
		return;
	if (currentDOMElement.id == 'treeNode0') {
		alert('开始节点不能设置规则');
		return;
	}
	
	var div=$('<div><span>设置所用规则</span>&nbsp;<br/></div>');
	var iframe=$('<iframe src="<%=request.getContextPath()%>/pub/grant/pack/rule/rule_select.jsp" frameborder="0" '
		+'style="width:98%;height:95%;" border="0"></iframe>');
	iframe.appendTo(div);
	var btns=[ 
		{ text: "确定", click: function() {
			var iframe=$('iframe', this)[0];
			var win=iframe.contentWindow;
			var result=win.select();//通过select函数与对话框交换数据
			if (false === result) {
				return false;
			}
			result=JSON.parse(JSON.stringify(result));
			result.resulttype = result.resulttype || '3';//是否型
			//console.log('所选规则：'+JSON.stringify(result));
			//var type=win.nui.getDictText('pub_grant_param',result.resulttype);
			
			if (!currentDOMElement)
				return;
			//设置所选规则
			var id=$(currentDOMElement)[0].id;
			treeNodesCache[id].name=result.rname;
			treeNodesCache[id].rule=[{
				type:'rule',
				rid:result.rid,
				rname:result.rname,
				rind:result.rind,
				resulttype:result.resulttype
			}];
			treeNodesCache[id].rule=treeNodeExpr2DBConvert(treeNodesCache[id].rule);
			
			this.ok=true;
			$( this ).dialog( "close" );
		}}, 
		{ text: "取消", click: function() { $( this ).dialog( "close" );} }];
	dialog(div,btns,{width:'800',height:'400', close: function( event, ui ) {
		initTreeLines();
		
		if (this.ok === true)
			return;
	} });
}
//移除当前选中的节点
function menuNodeRemove(){
	hideMenu();
	if ($('p', tree).length < 2) {
		alert('最后一个节点不能删除');
		return;
	}
	if (!currentDOMElement)
		return;
	var id=$(currentDOMElement)[0].id;
	var nodeData=treeNodesCache[id];
	if (nodeData.children && nodeData.children.length>0) {
		alert('有下级节点时，不能删除');
		return;
	}
	//alert('在这里需要移除缓存中的children也需要更新');
	if (confirm('确认删除吗？') == false) {
		return;
	}
	saveCurrentNodeStatus();
	removeCurrent();
	restoreCurrentNodeStatus();
	refreshTreeNodesCache(id);
	initTreeLines();
}
function restoreCurrentNodeStatus() {
	var cache = treeNodesCache;
	for (var nodeId in cache) {
		if (nodeId == 'length')
			continue;
			
		var nodeData=cache[nodeId];
		var node=$('#'+nodeId);
		if (node.length < 1)
			continue;
		
		var offset=node.offset();
		if (nodeData.offsetLeft != offset.left) {
			var x=(node[0].style.left||'0px').replace('px','')*1;
			x=x - (offset.left-nodeData.offsetLeft);
			node[0].style.left = x + 'px';//校正位置
		}
		if (nodeData.offsetTop != offset.top) {
			var x=(node[0].style.top||'0px').replace('px','')*1;
			x=x - (offset.top-nodeData.offsetTop);
			node[0].style.top = x + 'px';//校正位置
		}
	}
}
function saveCurrentNodeStatus() {
	var cache = treeNodesCache;
	for (var nodeId in cache) {
		if (nodeId == 'length')
			continue;
			
		var nodeData=cache[nodeId];
		var node=$('#'+nodeId);
		if (node.length < 1)
			continue;
		
		var offset=node.offset();
		nodeData.offsetLeft=offset.left;
		nodeData.offsetTop=offset.top;
	}
}
//获取所有的中间结算结果名称
function getAssignNames() {
	var names=[];
	var arr=$('p[type="treeNode"]');
	if (!arr || arr.length < 1)
		return names;
	for (var i=0,len=arr.length; i<len; i++) {
		if (arr[i].id == 'treeNode0')
			continue;
		//var d=$.extend(true,{},treeNodesCache[arr[i].id]);
		var node=treeNodesCache[arr[i].id];
		var d={name:node.name};
		if (!d.name || d.name.length < 1)
			continue;
		var flag=false;//是否已存在
		$.each(names, function(index, value) {
			//console.log(index);
			if (value && value.name == d.name)
				flag = true;
		});
		if (flag === true)
			continue;
		names[names.length]=d;
		d.text=d.name;
		d.type='expr';
		d.func=insertExprParam;
	}
	return names;
}
//设置进入条件
function menuNodeEnter() {
	hideMenu();
	if (!currentDOMElement)
		return;
	if (currentDOMElement.id == 'treeNode0') {
		alert('开始节点不需要进入条件');
		return;
	}
	var data=treeNodesCache[$(currentDOMElement)[0].id] || {};

	var div=$('<div type="func"><span>设置“'+data.name+'”的进入条件</span><br/></div>');
	var menu=$('<ul style="position:absolute;" class="menu"></ul>');
	menu.appendTo($('span:eq(0)',div));
	currentDOMElementArray.push(currentDOMElement);//显示编辑对话框时入栈
	currentDOMElementMenu=menu;
	currentDOMElementMenuArray.push(currentDOMElementMenu);//显示编辑对话框时入栈
	
	var rulediv=$('<div class="indent expr"></div>');
	rulediv.appendTo(div);
	
	var op = $('<span type="exprOp" style="cursor:pointer;">&nbsp;&nbsp;@&nbsp;&nbsp;</span>');
	op.appendTo(rulediv);
	op.click(function(e){
		currentDOMElement=this;
		//currentDOMElementMenu=$('>ul', $(this).parent());//currentDOMElementMenuArray[currentDOMElementMenuArray.length-1];
		showExprMenu(e);
		preventDefault(e);
	});
	
	currentDOMElement=op;
	setExprExpr(rulediv, data.func);
	
	var btns=[ 
		{ text: "确定", click: function() { 
			currentDOMElement=currentDOMElementArray.pop();//隐藏编辑对话框时出栈
			if (currentDOMElement.length || currentDOMElement[0]) 
				currentDOMElement=currentDOMElement[0];
			currentDOMElementMenu=currentDOMElementMenuArray.pop();//隐藏编辑对话框时出栈
			if (currentDOMElementMenu.length || currentDOMElementMenu[0]) 
				currentDOMElementMenu=currentDOMElementMenu[0];
			//this指代要关闭的窗口dom
			data.func=getExprExpr($('>div:eq(0)',this));
			//console.log('getExprExpr:--' +JSON.stringify(data.func));
			
			this.ok=true;
			$( this ).dialog( "close" );
		}}, 
		{ text: "取消", click: function() { $( this ).dialog( "close" );} }];
	dialog(div,btns,{width:'800',height:'400', close: function( event, ui ) {
		initTreeLines();
		
		if (this.ok === true)
			return;
		currentDOMElement=currentDOMElementArray.pop();//隐藏编辑对话框时出栈
		if (currentDOMElement.length || currentDOMElement[0]) 
			currentDOMElement=currentDOMElement[0];
		currentDOMElementMenu=currentDOMElementMenuArray.pop();//隐藏编辑对话框时出栈
		if (currentDOMElementMenu.length || currentDOMElementMenu[0]) 
			currentDOMElementMenu=currentDOMElementMenu[0];
	} });
}
//重命名当前节点
function menuNodeRename() {
	hideMenu();
	var div=$('<div><span>修改名称为：</span>&nbsp;<input /></div>');
	
	if (!currentDOMElement)
		return;
	if (currentDOMElement.id == 'treeNode0') {
		alert('开始节点不能修改名称');
		return;
	}
	
	var btns=[ 
		{ text: "确定", click: function() { 
			var val=$('input', this)[0].value;
			
			var data=treeNodesCache[$(currentDOMElement)[0].id] || {};
			data.name=val;
			
			this.ok=true;
			$( this ).dialog( "close" );
		}}, 
		{ text: "取消", click: function() { $( this ).dialog( "close" );} }];
	dialog(div,btns,{width:'800',height:'400', close: function( event, ui ) {
		initTreeLines();
		
		if (this.ok === true)
			return;
	} });
}
var SUB_NOD_TYPE_TABLE='table';
var SUB_NOD_TYPE_RULE='rule';
function addSubNode() {//增加子节点
	hideMenu();
	if (!currentDOMElement)
		return;
	
	var parentId=currentDOMElement.id;
	var parentNode=$('#'+parentId);
	{
		var data=treeNodesCache[$(currentDOMElement)[0].id] || {};
		if (data.rule && data.rule.type==SUB_NOD_TYPE_TABLE) {
			alert('调用授权表的节点不能有下级节点');
			return;
		}
	}
	
	var id='treeNode'+treeNodesCache.length;
	var idZH_CN='节点'+treeNodesCache.length;
	treeNodesCache.length += 1;
	
	var node=$('<p class="ui-widget-content" id="'+id+'" type="treeNode"></p>');//子节点
	//var node=document.createElement('p');
	//node.id=id;
	//node=$(node);
	//node.addClass('ui-widget-content');
	node.appendTo($('#treeDiv'));
	node.html(idZH_CN);
	
	//所有节点都有name属性。func属性表示进入条件、rule属性表示所调用的规则或授权表
	{
		var tmp=$(currentDOMElement);//父节点
		var y=tmp.position().top + tmp.height() + MARGIN_Y;
		//var x=tmp.position().left - tmp.width() - 14;
		var x=(tmp[0].style.left||'0px').replace('px','')*1;
		node[0].style.left = x + 'px';
		node[0].style.top = y + 'px';
		//alert(node.offset().left);
		//alert(tmp.offset().left);
		
		x=x - (node.offset().left-tmp.offset().left);
		node[0].style.left = x + 'px';//校正位置：子节点新增后处于上级节点的正下方
		//alert(node.offset().left);
		//alert(tmp.offset().left);
	}
	
	var parentNodeCache=treeNodesCache[parentId];
	treeNodesCache[id]={'level':(parentNodeCache.level+1), 'name':idZH_CN, 'parentId':parentId, 'children':[], 'rule':[]};
	parentNodeCache.children[parentNodeCache.children.length]={id:id};
	
	
	initTreeNodes();
}
function selectCurrentElement() {
	$('p', tree).removeClass('tree_node_selected');//所有节点取消选中状态
	
	if (!currentDOMElement)
		return;
	
	$(currentDOMElement).addClass('tree_node_selected');//当前节点设置为选中状态
}
</script>

</body></html>
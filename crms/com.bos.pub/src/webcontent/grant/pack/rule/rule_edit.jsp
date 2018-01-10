<!-- 
作者：王世春
日期：2013-12-01
Email：wsc.hi@163.com
功能：规则可视化编辑

规则编辑：
		规则编辑是可嵌套的，因此用递归方式实现。
		大类分为：
			1、如果……那么……
			2、规则结果为
			3、定义参数
		表达式编辑：
			提供业务参数、操作符（数学、逻辑运算）、函数、可调整参数（常量）
			编辑时可通过鼠标拖动，随意改变表达式中各参数、操作符、函数的位置
			参数类型：数值型（整数、小数）、文本型、是否型、常量
				常量值为汉字“是、否”时为是否型；0开头的非小数为文本型，其他字符全为数字的常量为数值型；否则为文本型。
		其中，
			“如果”部分为表达式编辑器。
			“那么”部分为“规则结果为”，或为嵌套的如果……那么，使用大类编辑器。
			“规则结果为”可为常量或业务参数或表达式，使用表达式编辑器。
			“定义参数”部分为表达式，使用表达式编辑器。
			
		大类可被嵌套，
			可调整顺序、删除、增加
			“那么”部分可嵌套大类。大类被嵌套时，以缩进的div表示。

		提供编辑模式、常量修改模式、复制功能、测试功能。


 -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn">
<head>
  <meta content="text/html; charset=utf-8" http-equiv="content-type">
  <meta http-equiv="X-UA-Compatible" content="IE=7" />
  <meta charset="utf-8">
  <title>规则编辑器</title>
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
	border-top:dashed 1px;
	margin-top:25px;
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
  /*
  .ui-selecting {
    background: #ccc;
  }
  .ui-selected {
    background: #999;
  }
  .menu {
  	height:auto;
  	overflow-y:auto;
  }
  .menu li {
	cursor:pointer;
  }
  .menu li:hover {
	background-color:#ccc;
  }
  .menu ul {
	overflow-y:auto;
	list-style-type:circle;
  }*/
  </style>
</head>
<body>

<ul id="menu" style="position:absolute;"><!--  class="menu"  -->
  <!--<li><a href="#">Item 1Item 1Item 1Item 1Item 1Item 1Item 1Item 1Item 1</a></li>
  <li><a href="#">Item 2</a></li>
  <li><a href="#">Item 3</a>
    <ul>< !--  style="height:100px;overflow-y:scroll;overflow-x:hidden;" -- >
      <li><a href="#">Item 3-1</a></li>
      <li><a href="#">Item 3-2</a></li>
      <li><a href="#">Item 3-3</a></li>
      <li><a href="#">Item 3-5</a></li>
      <li><a href="#">Item 3-51</a></li>
      <li><a href="#">Item 3-52</a></li>
      <li><a href="#">Item 3-53</a></li>
    </ul>
  </li>
  <li><a href="#">Item 4</a></li>
  <li><a href="#">Item 5</a></li>-->
</ul>

<div id="mainDiv">
</div>
<br/>
<!-- <span style="cursor:pointer;" onclick="alert(JSON.stringify(getMainExpr()))">生成JSON表达式</span>-->
<a href="#" style="cursor:pointer;" id="btnUpdateParam" onclick="updateParams()">更新参数</a>
<a href="#" style="cursor:pointer;" id="btnValid" onclick="validMainXML()">校验</a>
<a href="#" style="cursor:pointer;" id="btnSave" onclick="saveMainXML()">保存</a>
<a href="#" style="cursor:pointer;" id="btnTest" onclick="testRule()">测试（保存后的内容）</a>
<a href="#" style="cursor:pointer;" id="btnCopy" onclick="copyExpr()">复制</a>
<a href="#" style="cursor:pointer;" id="btnPaste" onclick="pasteExpr()">粘贴</a>
<a href="#" style="cursor:pointer;" id="btnClose" onclick="if (self.CloseOwnerWindow)self.CloseOwnerWindow();else self.close();">关闭</a>

<div style="height:300px;">&nbsp;</div>
<script>
var dialogdom = null;
function dialog(dom, btns, p) {
	dialogdom=dom;
	$('<div style="height:1000px;"><br/></div>').appendTo($(dom));
	$(dom).dialog($.extend({},{
		autoOpen: true, 
		draggable: true, 
		modal: true,
		resizable: true,width:'90%',height:'600',
		buttons: btns ? btns : [ { text: "确定", click: function() { $( this ).dialog( "close" );}}] },p));
}
//dialog($('<div><span onclick="dialog($(\'<div>bbb</div>\'))">aaaaaa</span</div>'));

var view = "<%=request.getParameter("view") %>";

//复制规则
var EXPR_FLAG='jsonRuleExpr:';
var MAIN_FLAG='jsonRuleMain:';
function copyExpr(obj) {
	var exprFlag=false;
	if (obj) {
		var ty = $(obj).attr('type');
		if (ty == 'mainOp' || ty == 'exprOp') {
			if (ty == 'exprOp') exprFlag = true;
			obj = $(obj).parent();
			if (obj.length > 0) obj = obj[0];
		} else {
			obj = $(obj)[0];
		}
	} else {
		obj = document.getElementById('mainDiv');
	}
	var jsonObject=null;
	var str=null;
	if (exprFlag) {
		jsonObject=getExprExpr(obj);
		str=EXPR_FLAG+JSON.stringify(jsonObject);
	} else {
		jsonObject=getMainExpr(obj);
		str=MAIN_FLAG+JSON.stringify(jsonObject);
	}
	
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
	var exprFlag=false;
	if (obj) {
		var ty = $(obj).attr('type');
		var ty2 = $(obj).parent().attr('type');
		var clsexpr = $(obj).parent().attr('class') || ' ';
		if (ty == 'mainOp' || ty == 'exprOp' || ty2 == 'assign' || ty2 == 'expr' || clsexpr.indexOf('expr') >= 0) {
			if (ty == 'exprOp' 
				|| ty2 == 'assign' || ty2 == 'expr'
				|| clsexpr.indexOf('expr') >= 0) {
				exprFlag = true;
			}
			obj = $(obj).parent();
			if (obj.length > 0) obj = obj[0];
		} else {
			obj = $(obj)[0];
		}
	} else {
		obj = document.getElementById('mainDiv');
	}

	if (exprFlag) {
		if (str.indexOf(EXPR_FLAG) != 0) {
			alert('不可粘贴在此处');
			return;
		}
		var jsonObject=JSON.parse(str.substr(EXPR_FLAG.length));
		//self.pasteExpr=true;
		setExprExpr(obj,jsonObject);
		//self.pasteExpr=false;
	} else {
		if (str.indexOf(MAIN_FLAG) != 0) {
			alert('不可粘贴在此处!');
			return;
		}
		var jsonObject=JSON.parse(str.substr(MAIN_FLAG.length));
		setMainExpr(obj,jsonObject);
	}
}
//根据参数名称更新参数ID，或根据参数ID更新参数名称
function updateParams() {
	var jsonObject=getMainExpr();
	var rules=JSON2XML(jsonObject,$('<rules/>'));
	var dom=$('<rules/>');
	dom.css({display:'none'});
	dom.appendTo(document.body);
	dom.html(rules);
	var items=$('item', dom);
	console.log('参数更新前：'+rules);
	for (var i=0,len=items.length; i<len; i++) {
		var item=items[i];
		if (!!$(item).attr('entext') && !$(item).attr('paramid')) {
			//根据英文名称更新参数ID
			var entext=$(item).attr('entext');
			for (var j=0,lenj=params.length; j<lenj; j++) {
				var param=params[j];
				if (param.entext == entext && !!param.paramid) {
					$(item).attr('paramid', param.paramid);
					break;
				}
			}
			if (!$(item).attr('paramid')) {
				alert('参数“'+$(item).attr('text')+'”无法更新：英文名称或参数ID未找到');
			}
		}
		if ($(item).attr('paramid')) {
			//根据参数ID更新名称
			var paramid=$(item).attr('paramid');
			for (var j=0,lenj=params.length; j<lenj; j++) {
				var param=params[j];
				if (paramid == param.paramid) {
					if (param.entext)
						$(item).attr('entext', param.entext);
					if (param.text)
						$(item).attr('text', param.text);
					break;
				}
			}
		}
	}
	console.log('参数更新后：'+dom.html());
	
	var mainDiv=$('#mainDiv');
	//mainDiv.html('');
	$('>div', mainDiv).remove();//删除所有规则大类
	
	var json={};
	XML2JSON(dom[0].firstChild,json);
	console.log('参数更新后：'+JSON.stringify(json));
	setMainExpr(null,json);
	dom.remove();
	alert('更新成功，请校验后再保存');
}
//校验规则
function validMainXML() {
	saveScrollPosition();
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
       	restoreScrollPosition();
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
           restoreScrollPosition();
       }
    });
}

//保存规则内容到数据库
function saveMainXML() {
	saveScrollPosition();
	var jsonObject=getMainExpr();
	var html=JSON2XML(jsonObject,$('<rules/>'));
	var data={'rid':"<%=request.getParameter("rid") %>",'rcontent':html};
	if (data.rid=='null' && self.ruleitem)
		data.rid=self.ruleitem.rid||'';
	var json=JSON.stringify(data);
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
       	restoreScrollPosition();
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
           restoreScrollPosition();
       }
    });
}

// 弹出窗口测试规则
var contextPath="<%=request.getContextPath() %>";
function testRule() {
	saveScrollPosition();
	var rid='<%=request.getParameter("rid") %>';
	if (rid == 'null') {
		rid=ruleitem.rid;
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
	        ondestroy: function (action) {
	            if(action=="ok"){
	            }
	            restoreScrollPosition();
	        }
	    });
	} else {
		var dom=$('<div/>');
		var ifr = $('<iframe frameborder="0" style="width:100%;height:100%;" border="0"/>');
		ifr[0].src="<%=request.getContextPath() %>/pub/grant/pack/rule/rule_test.jsp?type=grant"
	        	+"&rid="
	        	+rid
	        	+"&pid="+pid;
		dom.append(ifr);
		dialog(dom,null,{width:'90%',height:'450'});
	}
}

var ruleitem = null; //用于获取rule表记录的rcontent字段
var pid = null;
function getRule() {
	var data={item:{rid:"<%=request.getParameter("rid") %>"}};
	if (data.item.rid == 'null') {
		data.item.rid=null;
		data.item.rind="<%=request.getParameter("rind") %>";
	}
	$.ajax({
	       url: "com.bos.pub.decision.getRule.biz.ext",
	       type: 'POST',
	       data: JSON.stringify(data),
	       cache: false,
	       contentType:'text/json',
	       async:false,
	       success: function (text) {
	       	if(text.msg){
	       	} else {
	       		var rule=text.item || {};
	       		pid=rule.pid;
	       		ruleitem=rule;
	       		//initRuleEdit();
	       	}
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	           alert(jqXHR.responseText);
	       }
	    });
	return ruleitem;
}

//初始化业务参数
function initParams() {
	var data={};
	data.item={};
	data.item.tbPubGrantPackage={'pid':pid};
	var json=JSON.stringify(data);
	//alert(json);
	var type = "<%=request.getParameter("type") %>";
	if (type == 'indexgrade') {
		params=[
   			{text:'取消', func:hideMenu},
			{text:'移除', func:removeExprItem},
			{text:'----',func:hideMenu},
			{text:'指标值', type:'数值型', numbertype:'double', func:insertExprParam}
   		];
	} else if (type == 'modelgrade') {
		params=[
   			{text:'取消', func:hideMenu},
			{text:'移除', func:removeExprItem},
			{text:'----',func:hideMenu},
			{text:'总分', type:'数值型', numbertype:'double', func:insertExprParam}
   		];
	} else if (type == 'model') {
		// 模型总分计算
		var modelid="<%=request.getParameter("modelid") %>";
		$.ajax({
	       url: "com.bos.pub.model.model.getModelBaseIndexes.biz.ext",
	       type: 'POST',
	       data: JSON.stringify({itemId:modelid}),
	       cache: false,
	       contentType:'text/json',
	       success: function (text) {
	       	if(text.msg){
	       	} else {
	       		//params=tbPubGrantParam2param(text.items) || [];
	       		text.items=text.items || [];
	       		for (var i=0,len=text.items.length; i<len; i++) {
	       			text.items[i].paramname=text.items[i].indexName;
	       			text.items[i].paramtype='1';
	       		}
	       		params=[
	       			{text:'取消', func:hideMenu},
					{text:'移除', func:removeExprItem},
					{text:'----',func:hideMenu}
	       		].concat(tbPubGrantParam2param(text.items) || []);
	       	}
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	           alert(jqXHR.responseText);
	       }
	    });
    } else {
		$.ajax({
	       url: "com.bos.pub.decision.getParamList.biz.ext",
	       type: 'POST',
	       data: json,
	       cache: false,
	       contentType:'text/json',
	       success: function (text) {
	       	if(text.msg){
	       	} else {
	       		//params=tbPubGrantParam2param(text.items) || [];
	       		params=[
	       			{text:'取消', func:hideMenu},
					{text:'移除', func:removeExprItem},
					{text:'----',func:hideMenu}
	       		].concat(tbPubGrantParam2param(text.items) || []);
	       		//console.log('参数值：'+JSON.stringify(params));
	       	}
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	           alert(jqXHR.responseText);
	       }
	    });
    }
    
    if (view == 'const') {
		showMainMenu=removeParent=removeCurrent=
		insertExprConst=showExprParamMenu=
		showExprOpMenu=
		showExprFuncMenu=function() {
			return false;
		};//取消移除、常量插入、业务参数插入、操作符插入、函数插入
	}
}

function initRuleEdit() {
	var type = "<%=request.getParameter("type") %>";
	//(JSON.stringify(ruleitem));
	//if (type == 'grant') {
		//初始化规则内容
		var rcontent=ruleitem.rcontent;
		if (!rcontent || rcontent.length < 15) {
			rcontent='<rules></rules>';
		}
		var x={};
		XML2JSON(rcontent,x);
		//alert(JSON.stringify(x));
		setMainExpr(null,x);
		
		//初始化参数params
		initParams();
	    return;
	//}
}
$(function (){
	//$( "#menu" ).menu();
	//$( "#menu" ).show();
	$( "#menu" ).hide();
	createMainDiv();
	if (view == '1') {
		$('#btnValid').hide();
		$('#btnSave').hide();
		$('#btnPaste').hide();
		$('#btnTest').hide();
		$('span[type="mainOp"]').hide();
		$('span[type="exprOp"]').hide();
		showMainMenu=showExprMenu=function() {
			return false;
		};
	} else if (view == 'const') {
		//改到initParams函数中进行函数的修改，否则印象规则初始化。
		//showMainMenu=removeParent=removeCurrent=
		//insertExprConst=showExprParamMenu=
		//showExprOpMenu=
		//showExprFuncMenu=function() {
		//	return false;
		//};//取消移除、常量插入、业务参数插入、操作符插入、函数插入
		$('#btnPaste').hide();
		$('#btnUpdateParam').hide();
		window.old_showExprMenu=showExprMenu;
		showExprMenu=function() {
			if (!currentDOMElement)
				return false;
			var t=$(currentDOMElement).attr('type');
			if (t != 'exprConst')
				return false;
			window.old_showExprMenu();
		};
	}

	//var rules={"rules":[{"type":"assign","text":"参数1",
	//	"expr":[{"type":"const","text":123},{"text":"+","name":"+","type":"op"},{"text":"金额","type":"数值型"}]},
	//	{"type":"ifthen",
	//		"ifexpr":[{"text":"金额","type":"数值型"},{"text":"&gt;=","name":">=","type":"op"},{"type":"const","text":"11"}],
	//		"thenexpr":{"rules":[{"type":"expr","expr":[{"type":"const","text":"22"}]}]}},{"type":"expr","expr":[{"type":"const","text":"33"}]}]};
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
	
	pid=!!pid || "<%=request.getParameter("pid") %>";
	if (!pid || pid == 'null') {
		ruleitem=getRule();
	}
	var data={rid:"<%=request.getParameter("rid") %>", lob:"rcontent"};
	if (data.rid == 'null') {
		data.rid=ruleitem.rid;
	}
	
	$.ajax({
       url: "com.bos.pub.decision.getRuleLob.biz.ext",
       type: 'POST',
       data: JSON.stringify(data),
       cache: false,
       contentType:'text/json',
       success: function (text) {
       	if(text.msg){
       		alert(text.msg);
       	} else {
       		ruleitem = text.item;
       		
	       	initRuleEdit();//查询规则内容成功时，才做初始化规则可视化编辑界面操作
       	}
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
       }
    });
});
</script>

</body></html>
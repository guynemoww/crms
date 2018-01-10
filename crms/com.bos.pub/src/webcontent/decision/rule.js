/* 
作者：王世春
日期：2013-12-01
Email：wsc.hi@163.com
功能：规则可视化编辑
*/
if (!window.console)
	window.console={log:function(str){
		//有些版本的浏览器没有console对象
	}};
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
//复制文本到剪贴板
function copy2Clipboard(str) {
	if(window.clipboardData){
		window.clipboardData.clearData();
		window.clipboardData.setData("Text",str);
	} else {
		top.clipboardStr=str;
	}
}
//从剪贴板获取文本
function getClipboardText() {
	if(window.clipboardData){
		return window.clipboardData.getData("Text");
	} else {
		return top.clipboardStr;
	}
}

 //创建大类编辑器
var currentDOMElement = null;
function createMainDiv(obj) {
	obj = (obj && obj.tagName) ? obj : document.getElementById('mainDiv');
	obj = $(obj);
	obj.hide();
	obj.sortable({ distance: 5,cursor: "pointer",items:'>div' });//selectable({filter:'div'});
	$('>div', obj).unbind('mousedown');
	$('>div', obj).mousedown(function(){
		$('>div', obj).removeClass('border1');//ui-selected');
		$(this).addClass('border1');//ui-selected');
	});
	$('>div', obj).unbind('click');
	/*$('>div', obj).click(function(){
		currentDOMElement=this;
		showMainMenu();
	});*/
	$('<span style="clear:both;"></span>').appendTo(obj);
	var op = $('<span type="mainOp" style="display:block;width:20px;color:blue;font-weight:700;">&nbsp;+&nbsp;</span>');//#
	op.appendTo(obj);
	op.click(function(){
		currentDOMElement=this;
		showMainMenu();
	});
	
	obj.show();
	
}
//设置大类编辑器的表达式
function setMainExpr(obj, expr) {
	obj = obj ? obj : document.getElementById('mainDiv');
	obj = $(obj);
	var mainOp=$('>span[type="mainOp"]',obj);
	if (!mainOp || mainOp.length < 1 || !expr || !expr.rules)
		return;
	for (var i=0,len=expr.rules.length;i<len;i++) {
		var rule=expr.rules[i];
		mainOp=$('>span[type="mainOp"]',obj);
		currentDOMElement=mainOp[0];
		if (rule.type=='assign') {
			//参数定义或设置
			var div=insertMainItemAssign();//插入后当前选中元素变为@	
			setExprExpr(div, rule.expr);
			currentDOMElement=$('>span:eq(1)', div);//选中参数名称
			if(!rule.paramName)
				rule.paramName=rule.text;
			updateAssignParamName(rule.text);
			$(div)[0].data=rule;
			continue;
		}
		if (rule.type=='ifthen') {
			//如果……那么……
			var div=insertMainItemIfthen();
			setExprExpr($('>div:eq(1)', div), rule.ifexpr);//第二个div是如果的条件
			setMainExpr($('>div[type="then"]', div), rule.thenexpr);//第三个div是那么的条件
			div[0].data=rule;
			continue;
		}
		if (rule.type=='expr') {
			//规则结果为
			var div=insertMainItemExpr();//插入后当前选中元素变为@	
			setExprExpr(div, rule.expr);
			div[0].data=rule;
			continue;
		}
		if (rule.type=='line') {
			//空行
			var div=insertMainItemLine();
			continue;
		}
	}
}
//获取大类编辑器的表达式
function getMainExpr(obj) {
	obj = obj ? obj : document.getElementById('mainDiv');
	obj = $(obj);
	var expr={rules:[]};
	
	var mainOp=$('>span[type="mainOp"]',obj);
	if (!mainOp || mainOp.length < 1)
		return expr;
	var divs=$('>div',obj);
	for (var i=0,len=divs.length;i<len;i++) {
		var div=divs[i];
		var type=$(div).attr('type');
		if (type=='assign') {
			//参数定义或设置
			expr.rules[expr.rules.length]={'type':type,text:div.data.paramName,expr:getExprExpr(div)};
			continue;
		}
		if (type=='line') {
			//空行
			expr.rules[expr.rules.length]={'type':type,text:"",expr:[]};
			continue;
		}
		if (type=='ifthen') {
			//如果……那么……
			expr.rules[expr.rules.length]={
				'type':type,
				'ifexpr':getExprExpr($('>div:eq(1)',div)),
				'thenexpr':getMainExpr($('>div[type="then"]',div))
			};
			continue;
		}
		if (type=='expr') {
			//规则结果为
			expr.rules[expr.rules.length]={
				'type':type,
				expr:getExprExpr(div)
			};
			continue;
		}
		
		alert('获取大类编辑器的表达式时出错：' + $(divs[i]).html());
	}
	//window.expr=expr;
	return expr;
}
function JSON2XML(obj, dom) {
	if (!obj || !dom)
		return null;
	if ($.isArray(obj)) {
		var tagName='item';
		//alert(dom[0].tagName.toLowerCase());
		if (dom[0].tagName.toLowerCase() == 'rules')
			tagName='rule';
		if (dom[0].tagName.toLowerCase() == 'args')
			tagName='arg';
		for (var i=0,len=obj.length; i<len; i++) {
			var item=$('<'+tagName+'/>');
			item.appendTo(dom);
			JSON2XML(obj[i],item);
		}
	} else if (typeof obj=='object') {
		for (p in obj) {
			var tmp=obj[p];
			if(!tmp || p == 'func')
				continue;
			if (typeof tmp=='string' || typeof tmp=='number') {
				dom.attr(p, tmp);
			} else {
				var item=$('<'+p+'/>');
				item.appendTo(dom);
				JSON2XML(tmp, item);
			}
		}
	}
	
	return dom.html().replace(/&nbsp;/g,' ').replace(/&quot;/g,' ').replace(/\n/g,' ').replace(/\r/g,' ');
}
function loadXML(xmlString){
		xmlString=xmlString||'';
		xmlString=xmlString.replace(/&nbsp;/g,' ').replace(/&quot;/g,' ');
        var xmlDoc=null;
        //判断浏览器的类型
        //支持IE浏览器 
        if(!window.DOMParser && window.ActiveXObject){   //window.DOMParser 判断是否是非ie浏览器
            var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM','Msxml2.DOMDocument.6.0'];
            //Msxml2.DOMDocument.6.0 是IE7 win7 x64
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                    xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                    xmlDoc.async = false;
                    xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
                    break;
                }catch(e){
                }
            }
        }
        //支持Mozilla浏览器
        else if(window.DOMParser && document.implementation && document.implementation.createDocument){
            try{
                /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
                 * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
                 * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
                 * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
                 */
                domParser = new  DOMParser();
                xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
            }catch(e){
            }
        }
        else{
            return null;
        }

        return xmlDoc;
}
function XML2JSON(dom, obj) {
	if (!obj)
		obj = {};
	if (!dom)
		return obj;
	if (typeof dom == 'string') {
		dom=loadXML(dom);
		dom=dom.firstChild;
	}
	if (!dom || !dom.tagName)
		return obj;
	var tagName=dom.tagName.toLowerCase();
	if (tagName == 'rules') {
		//大类规则处理
		var arr=[];
		obj.rules=arr;
		for (var i=0,len=dom.childNodes.length; i<len; i++) {
			var rule=dom.childNodes[i];
			var json={};
			arr[arr.length] = json;
			XML2JSON(rule, json);
		}
		return obj;
	}
	if (tagName == 'rule') {
		//规则处理
		var type=$(dom).attr('type');
		if (type == 'assign') {
			//参数定义或设置
			obj.type=type;
			obj.text=$(dom).attr('text');
			obj.expr=[];
			var items=$('>expr>item', dom);//dom.childNodes[0].childNodes;
			for (var i=0,len=items.length; i<len; i++) {
				var item=items[i];
				var json={};
				obj.expr[obj.expr.length]=json;
				XML2JSON(item, json);
			}
			{//决策树节点
				obj.nodename=$(dom).attr('nodename');
				var style=$('>style',dom);
				obj.style={};
				obj.style.left=style.attr('left');
				obj.style.top =style.attr('top');
				obj.rules=[];
				
				//var rules=( flag == 'thenexpr' ? dom.childNodes[1].childNodes[0].childNodes : dom.childNodes[0].childNodes[0].childNodes);
				var rules=$('>rules:eq(0)>rule', dom)
				for (var i=0,len=rules.length; i<len; i++) {
					var rule=rules[i];
					var json={};
					obj.rules[obj.rules.length]=json;
					XML2JSON(rule, json);
				}
			}
			return obj;
		}
		if (type == 'expr') {
			//规则结果为
			obj.type=type;
			obj.expr=[];
			var items=$('>expr>item', dom);//dom.childNodes[0].childNodes;
			for (var i=0,len=items.length; i<len; i++) {
				var item=items[i];
				var json={};
				obj.expr[obj.expr.length]=json;
				XML2JSON(item, json);
			}
			if ($(dom).attr('nodename')) {//决策树节点
				obj.nodename=$(dom).attr('nodename');
				var style=$('>style',dom);
				obj.style={};
				obj.style.left=style.attr('left');
				obj.style.top =style.attr('top');
			}
			return obj;
		}
		if (type == 'line') {
			//空行
			obj.type=type;
			obj.expr=[];
			return obj;
		}
		if (type == 'ifthen') {
			//规则结果为
			obj.type=type;
			obj.thenexpr={};
			obj.thenexpr.rules=[];
			//var flag=dom.childNodes[1].tagName.toLowerCase();
			
			//var rules=( flag == 'thenexpr' ? dom.childNodes[1].childNodes[0].childNodes : dom.childNodes[0].childNodes[0].childNodes);
			var rules=$('>thenexpr>rules>rule', dom);
			for (var i=0,len=rules.length; i<len; i++) {
				var rule=rules[i];
				var json={};
				obj.thenexpr.rules[obj.thenexpr.rules.length]=json;
				XML2JSON(rule, json);
			}
			
			obj.ifexpr=[];
			//var items=( flag == 'thenexpr' ? dom.childNodes[0].childNodes : dom.childNodes[1].childNodes);
			var items=$('>ifexpr>item', dom);
			for (var i=0,len=items.length; i<len; i++) {
				var item=items[i];
				var json={};
				obj.ifexpr[obj.ifexpr.length]=json;
				XML2JSON(item, json);
			}
			
			if ($(dom).attr('nodename')) {//决策树节点
				obj.nodename=$(dom).attr('nodename');
				var style=$('>style',dom);
				obj.style={};
				obj.style.left=style.attr('left');
				obj.style.top =style.attr('top');
				
				items=$('>expr>item', dom);
				obj.expr=[];
				for (var i=0,len=items.length; i<len; i++) {
					var item=items[i];
					var json={};
					obj.expr[obj.expr.length]=json;
					XML2JSON(item, json);
				}
				//console.log('转换为JSON后：'+JSON.stringify(obj));
			}
			return obj;
		}
	}
	if (tagName == 'item' || tagName == 'arg') {
		//表达式项、函数参数处理
		var d=$(dom);
		var type=$(dom).attr('type');
		obj.text=d.attr('text')||'';
		if (d.attr('entext'))
			obj.entext=d.attr('entext');
		if (d.attr('paramid'))
			obj.paramid=d.attr('paramid');
		obj.type=type;
		if (d.attr('numbertype'))
			obj.numbertype=d.attr('numbertype');
		if (d.attr('name'))
			obj.name=d.attr('name');
		if (d.attr('ptext'))
			obj.ptext=d.attr('ptext');
		if (d.attr('paramName'))
			obj.paramName=d.attr('paramName');
		if (d.attr('dicttypeid'))
			obj.dictTypeId=d.attr('dicttypeid');//常量有时是业务字典
		//处理函数参数
		var args=$('>args',d);//d[0].childNodes[0];
		if (args.length > 0) {
			args=args[0];
		} else {
			args=$('>val',d)[0];
		}
		if (d.attr('funcname')) {
			obj.funcname=d.attr('funcname');
		}
		if (args && args.tagName.toLowerCase() == 'args' && args.childNodes.length > 0) {
			var arr=[];
			obj.args=arr;
			for (var i=0,len=args.childNodes.length; i<len; i++) {
				//处理参数（按个数）
				var arg=args.childNodes[i];
				var json={};
				arr[arr.length]=json;
				XML2JSON(arg, json);
			}
		} else if (args && args.tagName.toLowerCase() == 'val' && args.childNodes.length > 0) {
			var arr=[];
			obj.val=arr;
			for (var i=0,len=args.childNodes.length; i<len; i++) {
				//处理参数（实际参数表达式项）
				var arg=args.childNodes[i];
				var json={};
				arr[arr.length]=json;
				XML2JSON(arg, json);
			}
		}
		return obj;
	}
	
	return obj;
}
/*
function getMainXML(obj) {
	obj = obj ? obj : document.getElementById('mainDiv');
	obj = $(obj);
	//var expr={rules:[]};
	var rules=$('<rules></rules>');
	
	var mainOp=$('>span[type="mainOp"]',obj);
	if (!mainOp || mainOp.length < 1)
		return expr;
	var divs=$('>div',obj);
	for (var i=0,len=divs.length;i<len;i++) {
		var div=divs[i];
		var type=$(div).attr('type');
		if (type=='assign') {
			//参数定义
			//expr.rules[expr.rules.length]={'type':type,text:div.data.paramName,expr:getExprExpr(div)};
			var rule=$('<rule></rule>');
			rule.attr('type',type);
			rule.attr('text',div.data.paramName);
			getExprXML(div).appendTo(rule);
			rule.appendTo(rules);
			continue;
		}
		if (type=='ifthen') {
			//如果……那么……
			//expr.rules[expr.rules.length]={
			//	'type':type,
			//	'ifexpr':getExprExpr($('>div:eq(1)',div)),
			//	'thenexpr':getMainExpr($('>div[type="then"]',div))
			//};
			var rule=$('<rule></rule>');
			rule.attr('type',type);
			
			//var ifexpr=$('<ifexpr></ifexpr>');
			//ifexpr.appendTo(rule);
			getExprXML($('>div:eq(1)',div)).appendTo(rule);
			getMainXML($('>div[type="then"]',div)).appendTo(rule);
			rule.appendTo(rules);
			continue;
		}
		if (type=='expr') {
			//规则结果为
			//expr.rules[expr.rules.length]={
			//	'type':type,
			//	expr:getExprExpr(div)
			//};
			var rule=$('<rule></rule>');
			rule.attr('type',type);
			getExprXML(div).appendTo(rule);
			rule.appendTo(rules);
			continue;
		}
		
		alert('获取大类编辑器的表达式时出错：' + $(divs[i]).html());
	}
	//window.rules=rules;//for debug
	return rules;
}
function getExprXML(obj) {
	var arr=$('<expr></expr>');
	if (!obj)
		return arr;
	var divs=$('>div',obj);
	for (var i=0,len=divs.length;i<len;i++) {
		var div=divs[i];
		var da={};
		da=$.extend(true,da,div.data);
		var item=$('<item></item>');
		item.attr('text',da.text);
		item.attr('type',da.type);
		if (da.name)
			item.attr('name',da.name.replace(/\</g,'&lt;'));
		if (da.funcname) {
			item.attr('funcname',da.funcname);
			//getFuncXML(item,da);
		}
		item.appendTo(arr);
	}
	return arr;
}*/
//获取表达式编辑器的表达式
function getExprExpr(obj) {
	var arr=[];
	if (!obj)
		return arr;
	var divs=$('>div',obj);
	for (var i=0,len=divs.length;i<len;i++) {
		var div=divs[i];
		var da={};
		da=$.extend(true,da,div.data);
		arr[arr.length]=da;
	}
	return arr;
}
function moveCurrentPos2nextExpr() {
	if (!currentDOMElement)
		return;
	if ($(currentDOMElement).attr('type') == 'exprOp') {
		//移动到末尾
		currentDOMElement=$('>div:last',$(currentDOMElement).parent())[0];
		return;
	};
	if ($(currentDOMElement).next().length > 0) {
		currentDOMElement=$(currentDOMElement).next()[0];
		return;
	}
}
//将JSON初始化到div中
function setExprExpr(obj, arr) {
	if (!obj || !arr || arr.length < 1)
		return;
	
	var cur=$('>span[type="exprOp"]', obj);
	//粘贴时，如果当前选中某一节点时，则直接插入该节点后，否则插入所有节点之后
	//非粘贴时，插入所有节点之后
	//if (cur.length>0 && !self.pasteExpr)//!currentDOMElement && cur.length>0
	if (cur.length>0)
		currentDOMElement=cur[0];
	for (var i=0,len=arr.length;i<len;i++) {
		var item=arr[i];
		var da={};
		da=$.extend(true,da,item);
		//console.log(JSON.stringify(da) + ':da.type==\'const\':' + (da.type=='const'));
		if (da.funcname) {
			insertExprItem({data:da});
			//moveCurrentPos2nextExpr();
			continue;
		}
		if (da.type=='op') {
			insertExprItem({data:da});
			//moveCurrentPos2nextExpr();
			continue;
		}
		if (da.type=='const') {
			insertExprConst({'data':da, 'text':da.text});
			//moveCurrentPos2nextExpr();
			continue;
		}
		insertExprParam({data:da});
		//moveCurrentPos2nextExpr();
	}
}

// 插入新的“定义参数”到大类编辑器中
function insertMainItemAssign(e) {
	hideMenu();
	saveScrollPosition();
	
	//var paramName = prompt("请为此运算命名","xx参数");
	//if (!paramName)	{//未输入参数名称时，返回
	//	return;
	//}
	//paramName=paramName.replace(/\s/g,'');//去空白字符
	//if (isParamNameExist(paramName)) {
	//	alert('该参数名称已存在');
	//	return;
	//}
	var paramName=generateAssignParamName();
	var div=$('<div class="expr"><span>设置“'+paramName+'”为：</span></div>');
	div.attr('type','assign');
	var data={'paramName':paramName, 'type':'assign'};
	div[0].data=data;
	
	var op = $('<span type="exprOp" style="color:blue;font-weight:700;">&nbsp;&nbsp;>>&nbsp;&nbsp;</span>');
	$('>span',div).before(op);
	
	var par=$(currentDOMElement).parent();
	$(currentDOMElement).prev().remove();
	$(currentDOMElement).before(div);
	$(currentDOMElement).remove();
	createMainDiv(par[0]);
	op.click(function(){
		currentDOMElement=this;
		showExprMenu();
	});
	
	var span=$('>span:eq(1)',div);
	span.click(function(e) {
			hideMenu();
			currentDOMElement=this;
			var ele=$('<li><a href="javascript:hideMenu();">取消</a></li><li>修改参数名称：<input/><span onclick="updateAssignParamName($(this).prev().val())" style="cursor:pointer;">&nbsp;&nbsp;确定&nbsp;&nbsp;</span></li>');
			showMenuWithDOMElement(ele);
	});
	currentDOMElement=op;
	
	restoreScrollPosition();
	return div[0];
}
//插入新的“空行”到大类编辑器中
function insertMainItemLine(e) {
	hideMenu();
	saveScrollPosition();
	
	var div=$('<div class="line" style="cursor:pointer;width:80%;height:100px;">&nbsp;</div>');
	div.attr('type','line');
	
	var par=$(currentDOMElement).parent();
	$(currentDOMElement).prev().remove();
	$(currentDOMElement).before(div);
	$(currentDOMElement).remove();
	createMainDiv(par[0]);
	div.click(function(){
		currentDOMElement=this;
		showExprMenu();
	});
	currentDOMElement=div;
	
	restoreScrollPosition();
	return div;
}
//插入新的“规则结果为”到大类编辑器中
function insertMainItemExpr(e) {//alert(e.tagName);return;
	hideMenu();
	saveScrollPosition();
	
	var div=$('<div class="expr"><span style="">规则结果为：</span></div>');
	div.attr('type','expr');
	//div.html('');
	var op = $('<span type="exprOp"style="color:blue;font-weight:700;">&nbsp;&nbsp;>>&nbsp;&nbsp;</span>');
	//op.appendTo(div);
	$('>span',div).before(op);
	
	var par=$(currentDOMElement).parent();
	$(currentDOMElement).prev().remove();
	$(currentDOMElement).before(div);
	$(currentDOMElement).remove();
	createMainDiv(par[0]);
	op.click(function(){
		currentDOMElement=this;
		showExprMenu();
	});
	currentDOMElement=op;
	
	restoreScrollPosition();
	return div;
}
function saveScrollPosition() {
	self.cache=self.cache||{};
	self.cache.scrollTop=document.documentElement.scrollTop;
}
function restoreScrollPosition() {
	self.cache=self.cache||{};
	if (!!self.cache.scrollTop || self.cache.scrollTop===0)
		document.documentElement.scrollTop=self.cache.scrollTop;
}
//插入新的“如果……那么……”到大类编辑器中
function insertMainItemIfthen(e) {
	hideMenu();
	saveScrollPosition();
	
	var div=$('<div type="ifthen" class="ifthen"><div><span>如果：</span></div><div class="expr indent" style=""><span type="exprOp" style="float:left;color:blue;font-weight:700;">&nbsp;&nbsp;>>&nbsp;&nbsp;</span></div><br/><div>那么：</div><div type="then" class="border2 indent" style=""></div></div>');
	var par=$(currentDOMElement).parent();
	$(currentDOMElement).prev().remove();
	$(currentDOMElement).before(div);				
	$(currentDOMElement).remove();
	createMainDiv(par[0]);
	
	var exprOp=$('span:eq(1)',div);//alert(exprOp.html());
	exprOp.click(function(){
		currentDOMElement=this;
		showExprMenu();
	});
	var th=$('>div[type="then"]',div);
	//alert(th.attr('type'));
	createMainDiv(th[0]);
	
	var f=$('>div:first>span',div);
	//alert(f.html());
	f.click(function(){
		currentDOMElement=this;
		showMainMenu();
	});
	
	restoreScrollPosition();
	return div;
}
//大类编辑器中，最后的#操作
function showMainMenu() {
	if (!currentDOMElement)
		return;
	
	var items=[
		{text:'取消', func:hideMenu}
	];
	if (currentDOMElement.type == 'mainOp' || $(currentDOMElement).attr('type') == 'mainOp') {
		//items[items.length]={text:'----', func:hideMenu};
		items[items.length]={//定义或设置中间计算结果
			text:'设置参数', func: insertMainItemAssign
		};
		items[items.length]={
			text:'规则结果为', func: insertMainItemExpr
		};
		items[items.length]={
			text:'如果……那么……', func: insertMainItemIfthen
		};
		items[items.length]={
			text:'空行', func: insertMainItemLine
		};
		items[items.length]={text:'复制', func: function() {
			hideMenu();
			copyExpr($(currentDOMElement)[0]);
		}};
		items[items.length]={text:'粘贴', func: function() {
			hideMenu();
			pasteExpr($(currentDOMElement)[0]);
		}};
	}
	items[items.length]={text:'移除', func:removeMainItem};
	
	showMenu(items);
}
function removeMainItem() {
	hideMenu();
	if (!currentDOMElement)
		return;
	
	var cur=$(currentDOMElement);
	var ppar=cur.parent().parent();
	if (cur.attr('type') == 'mainOp') {
		return;
	}
	if(confirm('确认移除吗？')!=true) {
		return;
	}
	if (ppar && ppar.attr('type') == 'ifthen') {
		currentDOMElement=null;
		ppar.remove();
		return;
	}
	
	cur.remove();
	currentDOMElement=null;

}
//隐藏菜单
function hideMenu(){
	var menu=$('#menu');
	if (menu[0].style.display!='none') {
		menu.hide();
	}
	//menu.html('');
	if (currentDOMElementMenu) {
		currentDOMElementMenu=$(currentDOMElementMenu);
		currentDOMElementMenu.hide();
		currentDOMElementMenu.html('');
		//currentDOMElementMenu=null;
	}
}
//显示菜单
var currentDOMElementMenu=null;
function showMenu(items, absolutePosition){
	if (!currentDOMElement)
		return;
		
	var menu=$('#menu');
	if (currentDOMElementArray.length>0) {
		menu=currentDOMElementMenu;
	}
	menu.hide();
	{
		var parentnode=menu[0].parentNode;
		var oldmenu=menu;
		var str = '';
		if (menu[0].id)
			str='<ul id="'+menu[0].id+'" style="position:absolute;display:none;"></ul>';// class="menu"
		else
			str='<ul style="position:absolute;display:none;"></ul>';
		menu=$(str);
		menu.appendTo(parentnode);
		
		if (currentDOMElementMenu==oldmenu) {
			currentDOMElementMenu=menu;
		}
		oldmenu.remove();
	}
	
	if (items) {
		//console.log(JSON.stringify(items));
		
		menu.html('');
		for (var i=0,len=items.length; i<len; i++) {
			var li = $('<li>');
			li.appendTo(menu);
			li.html('<a href="javascript:void(0);">'+items[i].text+'</a>');
			//li.html(items[i].text);
			li[0].data=items[i];
			
			var its = items[i].items;
			if(its && its.length > 0) {//子菜单
				//li.html(items[i].text);
				var ul = $('<ul style="max-height:500px;overflow-y:auto;overflow-x:hidden;"></ul>');// style="height:100px;overflow-y:scroll;overflow-x:hidden;" class="menu"
				ul.appendTo(li);
				for (var j=0,len2=its.length; j<len2; j++) {
					if (j == 3 && its[j].func == insertExprParam && len2 > 15) {
						//增加业务参数过滤
						//console.log('增加业务参数过滤:'+JSON.stringify(its[j]));
						var it=$('<li/>');
						it.appendTo(ul);
						it.html('过滤：');
						filterMenuItemDOM.appendTo(it);
						filterMenuItemDOM.keyup(function(e){
							filterMenuItem(e);
						});
						if (filterMenuItemDOM.val())
							filterMenuItemDOM.attr('preValue','');
						
						it[0].data={};
					}
					
					var it=$('<li>');
					it.appendTo(ul);
					//it.html(its[j].text);
					it.html('<a href="javascript:void(0);">'+its[j].text+'</a>');
					it[0].data=its[j];
				}
			}//子菜单
		}//alert(menu.html());
	}
	$('li', menu).unbind('click');
	$('li', menu).click(function(e){
		if(this.data && typeof this.data.func == 'function'){
			this.data.func(this);
			return;
		}
		preventDefault(e);
	});
	var offset=$(currentDOMElement).position();//offset();
	//offset是基于可视区域左上角，position是相对位置
	if (absolutePosition === true)
		offset=$(currentDOMElement).offset();
	offset.left += 5;
	offset.top += 15;
	menu.css(offset);
	//if (!menu[0].isMenuAlready) {
		menu.menu({});
		/*select: function( event, ui ) {//top.x=event;
			var li=ui.item[0];
			if(li.data && typeof li.data.func == 'function') {
				li.data.func(li, event);
				return;
			}
			return;
		}*/
	//	menu[0].isMenuAlready=true;
	//}
	menu.show();
	
	filterMenuItem();
}
var filterMenuItemDOM=$('<input style="width:auto;height:auto;"/>');
function filterMenuItem(e) {
	//业务参数过滤
	var par=filterMenuItemDOM.parent();
	if (!par || par.length < 1 || par[0].tagName!='LI')
		return;
	var parpar=par.parent();
	if (!parpar || parpar.length < 1)
		return;
	
	var val=filterMenuItemDOM.val();
	
	if (filterMenuItemDOM.attr('preValue') != val) {
		filterMenuItemDOM.attr('preValue',val);
	} else {
		return;//处理中文输入法
	}
	//console.log('过滤参数:'+val);
	var lis = $('>li',parpar);
	for (var i=4,len=lis.length; i<len; i++) {
		var li=lis[i];
		var text=li.innerText;
		//console.log('参数名称:'+text);
		if (!val || text.indexOf(val) >=0) {
			li.style.display='';
		} else {
			li.style.display='none';
		}
	}
}
function showMenuWithDOMElement(ele) {
	if (!currentDOMElement || !ele) 
		return;
	var menu=$('#menu');
	if (currentDOMElementArray.length>0) {
		menu=currentDOMElementMenu;
	}
	menu.hide();
	//menu.html('');
	{
		var parentnode=menu[0].parentNode;
		var oldmenu=menu;
		var str = '';
		if (menu[0].id)
			str='<ul id="'+menu[0].id+'" style="position:absolute;display:none;"></ul>';// class="menu"
		else
			str='<ul style="position:absolute;display:none;"></ul>';
		menu=$(str);
		menu.appendTo(parentnode);
		
		if (currentDOMElementMenu==oldmenu) {
			currentDOMElementMenu=menu;
		}
		oldmenu.remove();
	}
	$(ele).appendTo(menu);
	
	var offset=$(currentDOMElement).position();//offset();
	offset.left += 5;
	offset.top += 10;
	menu.css(offset);
	menu.menu();
	menu.show();
}
//移除当前选中元素的上级
function removeParent() {
	if (!currentDOMElement)
		return;
	
	if(confirm('确认移除吗？')!=true) {
		return;
	}
	var par = $(currentDOMElement).parent();//the expr or main
	par.remove();
	currentDOMElement=null;
}
//移除当前选中元素
function removeCurrent() {
	if (!currentDOMElement)
		return;
	var item = $(currentDOMElement);//the expr item or main item
	item.remove();
	currentDOMElement=null;
}
//生成ID
var assignParamSeq=1;
function generateAssignParamName() {
	return '参数'+(assignParamSeq++);
}
//修改当前选中的参数名称
function updateAssignParamName(paramName) {
	if (!currentDOMElement || !paramName || (paramName=paramName.replace(/\s/g,'')).length<1)
		return;//无值或未选中
	
				
	var cur = $(currentDOMElement);
	var par = cur.parent();
	if(par.attr('type')!='assign')
		return;
	cur.html('设置“'+paramName+'”为：');
	par[0].data.paramName=paramName;
	hideMenu();
}
//显示表达式编辑器菜单
function showExprMenu(event) {
	if (!currentDOMElement)
		return;
	
	var items=[];
	items[0]={
		text:'取消', func:function(e) {hideMenu();}
	};
	var t=$(currentDOMElement).attr('type');
	var funcname=currentDOMElement.data ? currentDOMElement.data.funcname : null;
	if (t == 'exprConst') {
		items[items.length]={
			text:'编辑该值', func:function(e) {
				hideMenu();
				var cur=$(currentDOMElement);
				var t=cur.attr('type')
				if (t!='exprConst')
					return;
				var dictTypeId=cur.attr('dictTypeId');
				if (!dictTypeId) {
					var ele=$('<li><a href="javascript:hideMenu()">取消</a></li><li><input/>&nbsp;<span onclick="updateConstValue($(this).prev().val())" style="cursor:pointer;">确定</span></li>');
					showMenuWithDOMElement(ele);
					if (cur[0].data && cur[0].data.text)
						$('input',ele).val(cur[0].data.text);
					return;
				}
				if (dictTypeId=='org') {//机构选择
					openDialog(contextPath+'/utp/org/employee/selectRoleOrg.jsp', function(w){
						if (!!w.tagName) {
							w=$('iframe',w)[0].contentWindow;//document.getElementById('iframe').contentWindow;
						}
						var data=w.GetData();
						if (data)
							data=JSON.parse(JSON.stringify(data));
						//console.log(JSON.stringify(data));
						if (!data) {
							alert('请选择正确的值');
							return;
						}
						var orgid=data.orgid;
						var orgname=data.orgname;
						updateConstValue(orgid);
					});
					return;
				}
				if (dictTypeId=='product') {//业务品种选择
					openDialog(contextPath+'/pub/product/product/select_product_tree.jsp', function(w){
						if (!!w.tagName) {
							w=$('iframe',w)[0].contentWindow;//document.getElementById('iframe').contentWindow;
						}
						var data=w.currentNode;
						if (data)
							data=JSON.parse(JSON.stringify(data));
						if (!data || data.children) {
							alert('请选择正确的值');
							return;
						}
						//console.log(JSON.stringify(data));
						var productCd=data.productCd;
						var productName=data.productName;
						updateConstValue(productCd);
					});
					return;
				}
				if (dictTypeId=='YP_GLCD0117'||dictTypeId=='XD_DBCD4003'||dictTypeId=='XD_DBCD4002') {//押品类型选择
					openDialog(contextPath+'/grt/collateralparameter/colllsortparameter/selectCollateralSortInfoTree.jsp', function(w){
						if (!!w.tagName) {
							w=$('iframe',w)[0].contentWindow;//document.getElementById('iframe').contentWindow;
						}
						var data=w.GetData();
						if (data)
							data=JSON.parse(JSON.stringify(data));
						if (!data || !data.sortType) {
							alert('请选择正确的值');
							return;
						}
						//console.log(JSON.stringify(data));
						var sortType=data.sortType;
						var rankTypeName=data.rankTypeName;
						updateConstValue(sortType);
					});
					return;
				}
				if ('XD_CDKH2011,CDKH0095,XD_KHCD0092,CDKH0051,XD_KHCD0051,NationCd,CD000003,XD_KHCD0087,'
					+'XD_KH2D0098,CD000001,XD_KHCD0090,XD_DBCD4002,XD_DBCD4003,CDKH0004,CDKH0002,XD_KHCD0004,'
					+'XD_GG29382,YP_GLCD0127,XD_KHCD0002,YP_GLCD0078,CDKH0008,XD_KHCD0008,CDKH0046,XD_KHCD0046,'
					+'CDKH0023,XD_KHCD0211,XD_KHCD0029'.indexOf(dictTypeId) >= 0) {
					alert('请在rule.js文件中增加相应的处理（使用弹出窗口选择）');
					return;
				}
				
				//普通业务字典
				var value="";
				if (cur[0].data && cur[0].data.text)
					value=cur[0].data.text;
				var ele=$('<li><a href="javascript:hideMenu()">取消</a></li><li><select/>&nbsp;<span onclick="updateConstValue($(this).prev().val())" style="cursor:pointer;">确定</span></li>');
				var dicts=decisionGetDictText(dictTypeId, value);
				//console.log('xx:'+JSON.stringify(dicts));
				var select=$('select',ele);//multiple
				$.each(dicts.list||[],function(idx,val){
					//console.log(val);
					var option=$('<option></option>');
					option.text(val.dictName);
					option.val(val.dictID);
					option.appendTo(select);
				});
				showMenuWithDOMElement(ele);
				if (cur[0].data && cur[0].data.text) {
					//select.val(cur[0].data.text);
				}
			}
		};
	}
	if (funcname) {//函数编辑
		items[items.length]={
			text:'编辑函数内容', func:function(e) {
				hideMenu();
				showFuncEdit();
			}
		};
	}
	//items[items.length]={text:'----', func:hideMenu};
	items[items.length]={
		text:'可调整参数', func:function(e) {//alert(e.tagName);return;
			//hideMenu();
			insertExprConst({text:123});
		}
	};
	items[items.length]={
		text:'业务参数'/*, func:function(e) {//alert(e.tagName);return;
			//有子菜单
			showExprParamMenu();
		}*/, items:[].concat(params,getAssignNames())
	};
	var dictParams=[];
	$.grep(params||[],function(v){
		if(!!v && !!v.dictTypeId){
			dictParams[dictParams.length]=$.extend({}, v, {func:insertExprConst});
		}
	} );
	items[items.length]={
		text:'业务字典', items:[].concat(dictParams)
	};
	items[items.length]={
		text:'操作'/*, func:function(e) {//alert(e.tagName);return;
			//有子菜单
			showExprOpMenu();
		}*/, items:getExprOpItems()
	};
	items[items.length]={
		text:'函数'/*, func:function(e) {
			showExprFuncMenu();
		}*/, items:getExprFuncItems()
	};
	if (t == 'exprOp') {
		items[items.length]={
			text:'移除', func:function(e) {
				hideMenu();
				var cur=$(currentDOMElement);
				//alert(cur.parent().parent().attr('type'));
				if (cur.parent().parent().attr('type')=='ifthen')
					return;
				if (cur.parent().parent().attr('type')=='func')
					return;
				removeParent();
			}
		};
	} else {
		items[items.length]={
			text:'移除', func:function(e) {
				hideMenu();
				removeCurrent();
			}
		};
	}
	items[items.length]={text:'复制', func: function() {
		hideMenu();
		copyExpr($(currentDOMElement)[0]);
	}};
	items[items.length]={text:'粘贴', func: function() {
		hideMenu();
		pasteExpr($(currentDOMElement)[0]);
	}};
	showMenu(items);
	preventDefault(event);
}
//显示表达式编辑器操作子菜单
function showExprOpMenu() {
	hideMenu();
	
	showMenu(getExprOpItems());
}
//显示表达式编辑器函数子菜单
function showExprFuncMenu() {
	hideMenu();
	
	showMenu(getExprFuncItems());
}
//显示表达式编辑器中，业务参数子菜单
var params=[
	{text:'取消', func:hideMenu},
	{text:'移除', func:removeExprItem},
	{text:'----',func:hideMenu}/*,
	{text:'金额', type:'数值型', numbertype:'double', func:insertExprParam},
	{text:'利率', type:'数值型', numbertype:'double', func:insertExprParam},
	{text:'费率', type:'数值型', numbertype:'double', func:insertExprParam},
	{text:'期限', type:'数值型', numbertype:'long',func:insertExprParam},
	{text:'授信品种', type:'文本型', func:insertExprParam},
	{text:'客户行业', type:'文本型', func:insertExprParam},
	{text:'是否重点客户', type:'是否型', func:insertExprParam}*/
];
function tbPubGrantParam2param(ps) {
	if (!ps)
		return [];

	if ($.isArray(ps)) {
		var arr = [];
		for (var i=0,len=ps.length; i<len; i++) {
			arr[i]=tbPubGrantParam2param(ps[i]);
		}
		return arr;
	}
	
	var p={};
	p.text=ps.paramname;
	if (ps.enname)
		p.entext=ps.enname;
	if (ps.paramid)
		p.paramid=ps.paramid;
	p.func=insertExprParam;
	if (ps.paramtype == '2') {
		p.type='文本型';
	} else if (ps.paramtype == '3') {
		p.type='是否型';
	} else {
		p.type='数值型';
		if (ps.paramtype == '1') {
			p.numbertype='double';
		} else {
			p.numbertype='long';
		}
	}
	if (ps.paramdicttype)
		p.dictTypeId=ps.paramdicttype;
	return p;
}
//判断参数名称是否已存在
function isParamNameExist(paramName) {
	for (var i=0,len=params.length; i<len; i++) {
		if (params[i].text == paramName || params[i].entext == paramName)
			return params[i];
	}
	
	return null;
}
//获取所有的中间结算结果名称
function getAssignNames() {
	var names=[];
	var arr=$('div[type="assign"]');
	if (!arr || arr.length < 1)
		return names;
	for (var i=0,len=arr.length; i<len; i++) {
		var d=$.extend(true,{},arr[i].data);
		names[names.length]=d;
		d.text=d.paramName;
		d.type='expr';
		d.func=insertExprParam;
	}
	return names;
}

//显示业务参数子菜单
function showExprParamMenu() {
	hideMenu();
	showMenu( [].concat(params,getAssignNames()) );
}
var currentDOMElementArray=[];
var currentDOMElementMenuArray=[];
function showFuncEdit() {
	if (!currentDOMElement)
		return;
	hideMenu();
	//alert(currentDOMElement.data.args);
	//dialog(dom,null,{width:'70%',height:'500'})
	var functext=currentDOMElement.data.text;
	var args=currentDOMElement.data.args;
	var div=$('<div type="func"><span>函数“'+functext+'”</span><br/></div>');
	var menu=$('<ul style="position:absolute;" class="menu"></ul>');
	menu.appendTo(div);
	currentDOMElementArray.push(currentDOMElement);//显示编辑对话框时入栈
	currentDOMElementMenu=menu;
	currentDOMElementMenuArray.push(currentDOMElementMenu);//显示编辑对话框时入栈
	for (var i=0; i<args.length; i++) {
		var arg=args[i];
		var tips=$('<span>&nbsp;&nbsp;&nbsp;&nbsp;'+arg.text+'('+arg.type+')：</span>');
		var op = $('<span type="exprOp" style="cursor:pointer;color:blue;font-weight:700;">&nbsp;&nbsp;>>&nbsp;&nbsp;</span>');
		var argdiv=$('<div class="indent expr"></div>');
		tips.appendTo(div);
		argdiv.appendTo(div);
		
		op.appendTo(argdiv);
		op.click(function(){
			currentDOMElement=this;
			currentDOMElementMenu=$('>ul', $(this).parent().parent());
			showExprMenu();
		});
		
		currentDOMElement=op;
		setExprExpr(argdiv, arg.val);
	}
	
	var btns=[ 
		{ text: "确定", click: function() { 
			currentDOMElement=currentDOMElementArray.pop();//隐藏编辑对话框时出栈
			if (currentDOMElement.length || currentDOMElement[0]) 
				currentDOMElement=currentDOMElement[0];
			currentDOMElementMenu=currentDOMElementMenuArray.pop();//隐藏编辑对话框时出栈
			if (currentDOMElementMenu.length || currentDOMElementMenu[0]) 
				currentDOMElementMenu=currentDOMElementMenu[0];
			//this指代要关闭的窗口dom
			var args=currentDOMElement.data.args;
			for (var i=0; i<args.length; i++) {
				args[i].val=getExprExpr($('>div:eq('+i+')',this));
			}
			updateFuncText();
			
			this.ok=true;
			$( this ).dialog( "close" );
		}}, 
		{ text: "取消", click: function() { $( this ).dialog( "close" );} }];
	dialog(div,btns,{width:'90%',height:'600', close: function( event, ui ) {
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

//更新函数的显示
function updateFuncText(div) {
	div = div || currentDOMElement;
	if (div && div.length>0)
		div=div[0];
	if (!div || !div.data || !div.data.args)
		return;
	var ptext=div.data.ptext;
	var args=div.data.args;
	for(var i=0; i<args.length; i++) {
		ptext = ptext.replace('{'+(i+1)+'}',exprArr2String(args[i].val) );
	}
	$(div).html(ptext);
}

//显示表达式编辑器中，表达式项的删除
function removeExprItem() {
	if (!currentDOMElement)
		return;
	hideMenu();
	if ($(currentDOMElement).attr('type') == 'exprItem' || $(currentDOMElement).attr('type') == 'exprConst') {
		$(currentDOMElement).remove();
		currentDOMElement=null;
		return;
	}
	if ($(currentDOMElement).attr('type') == 'exprParam') {
		$(currentDOMElement).remove();
		currentDOMElement=null;
		return;
	}
	alert($(currentDOMElement).attr('type'));
}
//将表达式转换为字符串描述
function exprArr2String(arr) {
	var str='';
	if (!arr || !arr.length)
		return str;
	for (var i=0;i<arr.length; i++) {
		var obj=arr[i];
		if (obj.funcname && obj.args && obj.ptext) {
			var text=obj.ptext;
			for(var j=0; j<obj.args.length; j++) {
				text = text.replace('{'+(j+1)+'}',exprArr2String(obj.args[j].val) );
			}
			str += '' + text + '';
		} else if (obj.type=='op') {
			str += ' ' + obj.text + ' ';
		} else if (obj.text) {
			str += '' + obj.text + '';
		} else {
			str += '' + obj.name + '';
		}
	}
	return str;
}

//插入表达式项（操作符）到表达式编辑器
function insertExprItem(ele) {
	//alert(ele.tagName);
	//hideMenu();
	if (!ele)
		return;
		
	var div=$('<div></div>');
	div.attr('type','exprItem');
	div[0].data=$.extend(true, {},ele.data);//ele.data;
	if (!div[0].data.type)
		div[0].data.type='op';
	//alert(div.data);
	if (ele.data.funcname) {
		//处理函数
		//var ptext=ele.data.ptext;
		//var args=ele.data.args;
		//for(var i=0; i<args.length; i++) {
		//	ptext = ptext.replace('{'+(i+1)+'}',exprArr2String(args[i].val) );
		//}
		//div.html(ptext);
		updateFuncText(div);
	} else {
		div.html(ele.data.text);
	}
	
	var cur = $(currentDOMElement);
	var par=cur.parent();
	par.hide();
	
	if (currentDOMElement.type == 'exprOp' || $(currentDOMElement).attr('type') == 'exprOp') {
		//cur.before(div);
		div.appendTo(par);
	} else {
		cur.after(div);
		currentDOMElement=div[0];
	}
	par.sortable({ distance: 5,cursor: "pointer",items:'>div' });
	$('>div', par).unbind('mousedown');
	$('>div', par).mousedown(function(){
		$('>div', par).removeClass('border1');
		$(this).addClass('border1');
	});
	
	$('>div', par).unbind('click');
	$('>div', par).click(function(e) {
		//alert(this.tagName);
		currentDOMElement=this;
		showExprMenu();//showExprOpMenu();
	});
	par.show();
}
//插入表达式项（业务参数）到表达式编辑器
function insertExprParam(ele) {
	//alert(ele.tagName);
	//hideMenu();
	if (!ele)
		return;
		
	var div=$('<div></div>');
	div.attr('type','exprParam');
	div.html(ele.data.text);
	div[0].data=$.extend({},ele.data);
	div.addClass('exprParam');
	//console.log('insertExprParam:'+JSON.stringify(div[0].data));
	
	var cur = $(currentDOMElement);
	var par=cur.parent();
	par.hide();
	
	if (currentDOMElement.type == 'exprOp' || $(currentDOMElement).attr('type') == 'exprOp') {
		//cur.before(div);
		div.appendTo(par);
	} else {
		cur.after(div);
		currentDOMElement=div[0];//位置后移
	}
	par.sortable({ distance: 5,cursor: "pointer",items:'>div' });
	$('>div', par).unbind('mousedown');
	$('>div', par).mousedown(function(){
		$('>div', par).removeClass('border1');
		$(this).addClass('border1');
	});
	
	$('>div', par).unbind('click');
	$('>div', par).click(function(e) {
		//alert(this.tagName);
		currentDOMElement=this;
		showExprMenu();//showExprParamMenu();
	});
	par.show();
}
function formatCurrency(a) {
    if (a === null || a === undefined) {
        a = "";
    }
    a = String(a).replace(/\$|\,/g, "");
    if (isNaN(a)) {
        a = "0"
    }
    sign = (a == (a = Math.abs(a)));
    a = Math.floor(a * 100 + 0.50000000001);
    cents = a % 100;
    a = Math.floor(a / 100).toString();
    if (cents < 10) {
        cents = "0" + cents
    }
    for (var b = 0; b < Math.floor((a.length - (1 + b)) / 3); b++) {
        a = a.substring(0, a.length - (4 * b + 3)) + "," + a.substring(a.length - (4 * b + 3))
    }
    return (((sign) ? "": "-") + a + "." + cents)
};
//修改当前选中的常量的值
function updateConstValue(value) {
	if (!currentDOMElement || !value || (value=new String(value).replace(/\s/g,'')).length<1)
		return;//无值或未选中
	
	var cur = $(currentDOMElement);
	if(cur.attr('type')!='exprConst')
		return;
		
	cur[0].data.text=value;
	if (!value || value.length < 1) {
		if (cur.attr('dictTypeId'))
			value = '-- 请选择 --';
	}
	var dictTypeId=cur.attr('dictTypeId');
	if (dictTypeId) {
		if (!value || value.length < 1) {
			value = '-- 请选择 --';
			cur.attr('title','代码值：'+value);
		} else {
			value = decisionGetDict(dictTypeId, value);
			cur.attr('title','代码值：'+value);
		}
	}
	cur.html('“'+value+'”');
	{
		if (isNaN(value) == false && value.indexOf && value.indexOf('0') != 0) {//首位字符不为0
			cur[0].title=formatCurrency(value);
			cur.html('“'+value+'”');
		} else {
			cur.html('“'+value+'”');
		}
	}
	hideMenu();
}
//插入表达式项（常量）到表达式编辑器
function insertExprConst(ele) {
	//alert(ele.tagName);
	//hideMenu();
	if (!ele)
		return;
	ele.data=ele.data||{};
		
	var div=$('<div></div>');
	div.attr('type','exprConst');
	//div.html('“<input value="'+ele.text+'" size="10"/>”');
	div[0].data={type:'const',text:ele.text,dictTypeId:ele.data.dictTypeId};
	{
		var value=ele.text||'';
		if (div[0].data.dictTypeId) {
			div.attr('dictTypeId',div[0].data.dictTypeId);
			if (!value || value.length < 1) {
				value = '-- 请选择 --';
				div.attr('title',div[0].data.text);
			} else {
				div.attr('title','代码值：'+value);
				value = decisionGetDict(div[0].data.dictTypeId, value);
			}
		}
		if (isNaN(value) == false && value.indexOf && value.indexOf('0') != 0) {//首位字符不为0
			div[0].title=formatCurrency(value);
			div.html('“'+value+'”');
		} else {
			div.html('“'+value+'”');
		}
	}
	
	//与函数“updateConstValue”关联
	var cur = $(currentDOMElement);
	var par=cur.parent();
	par.hide();
	
	var t=currentDOMElement.type || cur.attr('type');
	if (t == 'exprOp') {
		//cur.before(div);
		div.appendTo(par);
	} else {
		cur.after(div);
		currentDOMElement = div[0];
	}
	par.sortable({ distance: 5,cursor: "pointer",items:'>div' });
	$('>div', par).unbind('mousedown');
	$('>div', par).mousedown(function(){
		$('>div', par).removeClass('border1');
		$(this).addClass('border1');
	});
	
	$('>div', par).unbind('click');
	$('>div', par).click(function(e) {
		//alert(this.tagName);
		currentDOMElement=this;
		showExprMenu();//showExprParamMenu();
	});
	
	par.show();
}
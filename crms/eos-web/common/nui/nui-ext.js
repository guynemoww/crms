/**
 *@刘子良
 *@liuzl@primeton.com
  @date:2014/04/24
 * 执行资源权限控制
 */

var GLOBAL_CONTEXT = '_globalContext';//
var AUTH_RES_LIST = '_authResList';//资源权限列表Key
var USER_ID = '_userId';//用户ID上下文Key
var USER_STATE_LIST="_userStateList";
var AUTH_RES_LIST2 = new Array();//资源权限列表数组
var userStateList= new Array();//当前用户状态列表
var userId2="";


var flag=1;
var flag2=1;



// 程序开关 //
nui.enableModelVal = true; // 是否启用模型校验，为false则关闭所有的表单校验
nui.enableResAuth = true; // 是否启用资源权限控制，为false则关闭所有的权限控制

/**
 * 根据传入的window对象，依次遍历parent直到top，查找属性值
 * @param win window对象
 * @param propName 属性名称
 * @return 属性值
 */
nui.findPropertyValue = function(win, propName) {
	if(!win) {
		return null;
	}
	
	if(win[propName]) {
		return win[propName];
	}
	
	if(isTop(win)) { 
		return null;
	} 
	return this.findPropertyValue(win.top, propName);
};



/**
 * 获取全局数据上下文
 */
nui.getGlobalContext = function() {
	return this.findPropertyValue(window, GLOBAL_CONTEXT);
};



/**
 * 判断是否为顶层窗口
 * @param win window对象
 */
function isTop(win) {
	return win.parent === win; // 顶层窗口的parent等于自身窗口
}



/**
 * 根据当前登录用户的userId加载（同步方式）已授权的资源列表。<br/>
 * 该方法只需要在Top中调用一次，以后子页面直接通过nui.getAuthResList()方法获取
 * 
 * 
 * 此方法可以修改为异步，如果改为异步，可参考  loadValidateRules方法
   SessionStorage：
　　将数据保存在session对象中，所谓session是指用户在浏览某个网站时，从进入网站到浏览器关闭所经过的这段时间会话，
    也就是用户浏览这个网站所花费的时间就是session的生命周期。session对象可以用来保存在这段时间内所要求保存的任何数据。
    此对象主要有两个方法：
    保存数据：sessionStorage.setItem(Key, value);
    读取数据：sessionStorage.getItem(Key);
	Key：表示你要存入的键名称，此名称可以随便命名，可以按照变量的意思来理解。
	Value：表示值，也就是你要存入Key中的值，可以按照变量赋值来理解。
 */
nui.loadAuthResList = function(userId) {
	/**首次登录清除ecache信息	begin*/
	var json2 ="";
	 nui.ajax({
		type: 'POST',
		url: 'com.bos.utp.framework.ResourceManager.clearCache.biz.ext',
		data: json2,
		cache:false,
		async: false,
		success: function(text) {
						
		}
      });
    /**首次登录清除ecache信息	end*/	
	var globalContext = this.getGlobalContext();
	if(!globalContext) {
		return;
	}
    userId2=globalContext[USER_ID];//从缓存全局权限容器获取到当前登录的用户ID
    $.cookies.set('userId2',userId2);//把当前登录用户ID设置到jquery cookie里面去
	var result = null;
	var json = nui.encode({userId: userId2});
	/*优先级：首先判断当前登录用户在页面资源里面存不存在，如果存在，就直接去判断这个当前登录用户的权限操作状态，就不管后面的这个用户所属的角色了
	  如果当前登录用户不在页面用户资源里面，就去判断当前用户所属的角色是否在这个里面	
	*/
	 //查询当前登录用户页面资源存不存在，然后在查看该用户所属于的操作状态，以当前登录用户的优先级为最高
	    nui.ajax({
		type: 'POST',
		url: 'com.bos.utp.framework.ResourceManager.selectResourceUserList.biz.ext',
		data: json,
		cache:false,
		async: false,
		success: function(text) {
			var obj = nui.decode(text.retCode);
			var obj2 = nui.decode(text);
			userStateList=obj2.resourceUsers;
			if(obj=='1') {
				if(userStateList.length>0){
				  result = obj2.resourceUsers;
				}else{
				  result=null;
				}			
			}else {
				//nui.alert('获取页面资源人员权限失败！');
			}			
		}
	   });
		return result;
	};



/**
 * 根据当前登录用户的userId获取已授权的资源列表，获取前需要确保在Top中已经调用过loadAuthResList()方法
 */
nui.getAuthResList = function() {	
    var result = null;
    var json = nui.encode({userId:$.cookies.get('userId2')});  
	nui.ajax({
		type: 'POST',
		url: 'com.bos.utp.framework.ResourceManager.selectResourceUserList.biz.ext',
		data: json,
		cache:false,
		async: false,
		success: function(text) {
			var obj = nui.decode(text.retCode);
			var obj2 = nui.decode(text);
			userStateList=obj2.resourceUsers;
			if(obj=='1') {
				if(userStateList.length>0){
				  result = obj2.resourceUsers;
				}else{
				  result=null;
				}			
			}else {
				
			}			
		}
	   });
    return result;
};



/**
 *@刘子良
 *@liuzl@primeton.com
  @date:2014/04/24
 * 执行资源权限控制
 * @param authResList 授权资源列表
 */
nui.applyResAuth = function(authResList) {
  	if(authResList!=null){
  		$('*[resId]').each(function() {
	 		var $this = $(this);
	 		var authResListLength = eval('(' + nui.encode(authResList) + ')').length;
	 		var tempLength = 0;
			$.each(eval('(' +nui.encode(authResList) + ')'),function(n,value) {//value.RESOUCEID
				if(value.RESOURCEID==$this.attr('resId')){
					// 根据res.resourcestate设置控件的read、write、disabled属性
		          	if(value.resourcestate=='show'){	         		
		          	 	$this.show();
		          	}
		          	else{
						$this.remove();//或者用$this.hide();
					}
					return false;
				}
				tempLength++;
			});
			if(authResListLength<=tempLength) {
				$this.remove();//或者用$this.hide();
			}
 		});
  	}
  	else{//说明不在这个角色里面,就都给隐藏掉
	  	 $('*[resId]').each(function() {
	 		var $this = $(this);
	 		if($this.attr('resId')!=""){
	 			$this.remove();
	 		}
		 });
  	}
};
//重写_doParse初始化开始校验
function valRequire(o){
    var e = {
       value: o.getValue(),
       errorText: "",
       isValid: true
    };

    if (o.required) {
     	if (mini.isNull(e.value) || String(e.value).trim() === "") {
         	e.isValid = false;
         	e.errorText = o.requiredErrorText;
     	}
     	if(o.setIsValid){
     		o.errorText = e.errorText;
     		o.setIsValid(e.isValid);
     	}
   	}
}


mini._doParse = function (el) {
    if (!el) return;
    var nodeName = el.nodeName.toLowerCase();
    if (!nodeName) return;
    var className = el.className;
    if (className) {
        var control = mini.get(el);        
        if (!control) {
            var classes = className.split(" ");
            for (var i = 0, l = classes.length; i < l; i++) {
                var cls = classes[i];
                var clazz = mini.getClassByUICls(cls);
                if (clazz) {
                    mini.removeClass(el, cls);
                    var ui = new clazz();
                    
                    mini.applyTo.call(ui, el);
                    el = ui.el;
		    		valRequire(ui);
                    break;
                }
            }
        }
    }

    if (nodeName == "select"
            || mini.hasClass(el, "mini-menu")
            || mini.hasClass(el, "mini-datagrid")
            || mini.hasClass(el, "mini-treegrid")
            || mini.hasClass(el, "mini-tree")
            || mini.hasClass(el, "mini-button")
            || mini.hasClass(el, "mini-textbox")
            || mini.hasClass(el, "mini-buttonedit")
        ) {
        return;
    }

    var children = mini.getChildNodes(el, true);
    for (var i = 0, l = children.length; i < l; i++) {
        var node = children[i];
        if (node.nodeType == 1) {
            if (node.parentNode == el) {
                mini._doParse(node);
            }
        }
    }
}

//扩展原nui.parse方法，增加模型校验和权限控制的功能
var old_nuiParse = nui.parse;
var isApplied = false; // 是否执行了模型校验和权限控制
nui.parse = function(el) {
	if(!isApplied) {
		// 执行资源权限控制
		if(nui.enableResAuth) {
			if($.cookies.get('userId2')!=""){//sessionStorage.getItem("userId2")!="" $.cookies.get('userId2')!=""
				var authResList = this.getAuthResList();
				this.applyResAuth(authResList);	
			}
		}
		isApplied = true;
	}
	old_nuiParse.call(this, el);
};




(function(window, undefined) { 
		var cacheData = {}, // 用来存储数据的对象 
		win = window, // 把window缓存给一个变量 
		uuid = 0, 
		// 声明随机数(8位) 
		// 注意+new Date()生成的随机数是Number类型，与一个空字符串连接后(或使用toString方法转型后)变成字符串，才可使用slice方法。 
		expando = "cacheData" + (+new Date() + "").slice(-8); 
		// (+new Date()).toString().slice(-8)等价于expando 
		// 写入缓存 
		var data = function(elem, name, value) { 
		// 或使用原生方法验证字符串Object.prototype.toString.call(elem) === "[object String]" 
		// 如果elem为字符串 
		if (typeof elem === "string") { 
			// 如果传入name参数，则为写入缓存 
			if (name !== undefined) { 
			cacheData[elem] = name; 
			} 
			// 返回缓存数据 
			return cacheData[elem]; 
			// 如果elem为DOM节点 
		} else if (typeof elem === "object") { 
			var id, 
			thisCache; 
			// 如果elem不存在expando属性，则添加一个expando属性(第一次给元素设置缓存)，否则直接获取已有的expando和id值 
			if (!elem[expando]) { 
			id = elem[expando] = ++uuid; 
			thisCache = cacheData[id] = {}; 
			} else { 
			id = elem[expando]; 
			thisCache = cacheData[id]; 
			} 
			// 把一个随机数作为当前缓存对象的一个属性，利用该随机数就能找到该缓存对象 
			if (!thisCache[expando]) { 
			thisCache[expando] = {}; 
			} 
			if (value !== undefined) { 
			// 将数据存到缓存对象中 
			thisCache[expando][name] = value; 
			} 
			// 返回DOM元素存储的数据 
			return thisCache[expando][name]; 
			} 
		}; 
			// 删除缓存 
			var removeData = function(elem, name) { 
			// 如果elem为字符串，则直接删除该属性值 
			if (typeof elem === "string") { 
			delete cacheData[elem]; 
			// 如果key为DOM节点 
			} else if (typeof elem === "object") { 
			// 如果elem不存在expando属性，则终止执行，不用删除缓存 
			if (!elem[expando]) { 
			return; 
			} 
			// 检测对象是否为空 
			var isEmptyObject = function(obj) { 
			var name; 
			for (name in obj) { 
			return false; 
			} 
			return true; 
			} 
			removeAttr = function() { 
			try { 
			// IE8即标准浏览器可以直接使用delete来删除属性 
			delete elem[expando]; 
			} catch (e) { 
			// IE6/IE7使用removeAttribute方法来删除属性 
			elem.removeAttribute(expando); 
			} 
			}, 
			id = elem[expando]; 
			if (name) { 
				// 只删除指定的数据 
				delete cacheData[id][expando][name]; 
				// 如果是空对象，id所对应的数据对象全部删除 
				if (isEmptyObject(cacheData[id][expando])) { 
					delete cacheData[id]; 
					removeAttr(); 
				} 
			} else { 
				// 删除DOM元素存到缓存中的所有数据 
				delete cacheData[id]; 
				removeAttr(); 
			} 
			} 
		}; 
		// 把data和removeData挂在window全局对象下，这样在外部也能访问到这两个函数 
		win.expando = expando; 
		win.data = data; 
		win.removeData = removeData; 
})(window, undefined);




function CloseWindow(op) {            
    if (window.CloseOwnerWindow) 
    	return window.CloseOwnerWindow(op);
    else 
    	window.close();            
}
//以上： 普元提供

//金额转换
function ConvertMoney(num){
	var strOutput = "";  
	var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
	var strFlg = "负";
	num += "";
	var intPos = num.indexOf('.');
	var intFLg1 = num.indexOf('-');
	var dot = num.substr(intPos+1).length;
	if(intPos==-1){
		num += "00";
    }else{
    	if(dot==1){
    		num += "0";
    	}else{
			num += "";
		}
    }
	var strSymbol="";
	var intFlg="";
	if (intFLg1>=0){
		num=num.substr(1);
		intPos = num.indexOf('.');
		strSymbol = "-";
	}
	if (intPos >= 0)  
 		num = num.substring(0, intPos) + num.substr(intPos+1, 2); 
	strUnit = strUnit.substr(strUnit.length - num.length); 
	
	for (var i=0; i < num.length; i++) {
 		strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
 	}
 	strOutput = strSymbol+strOutput;
 	return strOutput.replace(/-/, strFlg)
 			.replace(/零角零分$/, '整')
 			.replace(/零[仟佰拾]/g, '零')
 			.replace(/零{2,}/g, '零')
 			.replace(/零([亿|万])/g, '$1')	
 			.replace(/零+元/, '元')
 			.replace(/亿零{0,3}万/, '亿')
 			.replace(/^元/, "零元")
 			.replace(/零分$/, "")
 			.replace(/零角/, "零"); 
}
//--------------------------------------

//以下： 王世春
/*给下拉选择框等使用*/
NULL_ARRAY=[];

if (!window.console)
	window.console={log:function(str){
		//有些版本的浏览器没有console对象
	}};
//jquery-1.6.2.min.js中：“success = ajaxConvert(s, response);”语句之后增加了如下判断语句，判断登录超时的情况并处理。
/*
                            if (success && success.exception) {//王世春：判断登录超时
                            	var code=success.exception.code;
//{"exception":{"code":"12101001","message":"ErrCode: 12101001\nMessage: session失效或者用户未登陆.\n","invalid":true,"loginPage":"\/default\/login.jsp"}}
                            	var invalid=success.exception.invalid;
                            	var message=success.exception.message;
                            	if (invalid===true && message && code=='12101001') {
                            		alert(message);
                            		window.location.replace(nui.context);
                            		return;
                            	}
                            }
*/

document.oncontextmenu_no = function(e){//暂不屏蔽右键菜单，上线时需启用
	if(window.event){
		e=window.event;
		e.returnValue=false;
	}else{
		e.preventDefault();
	}
};
//回车变Tab键、空格弹出窗口
function documentKeydown(e){
	top.x=e;
	e.originalEvent=e.originalEvent||{};
	var obj = e.target||e.srcElement;
	var tagName=obj.tagName;
	var tagType=obj.type;
	if (e.which == 13) {//处理回车
		if (
			(tagName!='INPUT'||tagType!='text')
			&&
			(tagName!='TEXTAREA'||e.shiftKey!=true)
			&&
			(tagName!='A'||e.shiftKey!=true)
			&&
			(tagName!='SELECT')
			) {
			return;
		}
		//e.which=9;
		e.originalEvent.keyCode=9;
		return;
	}
	if (e.which == 32) {//下拉框按空格时弹出
		if (tagName=='INPUT'&&tagType=='text'&&obj.className.indexOf('mini-buttonedit-input')>=0) {
			var uid=obj.parentNode.parentNode.uid;
			var nuiobj=nui.getbyUID(uid);
			if (nuiobj && nuiobj.type.indexOf('combobox')>=0) {
				//nui的下拉框控件
				if (typeof nuiobj.showPopup=='function')
					nuiobj.showPopup();
				if (nuiobj.allowInput !== true) {
					e.preventDefault();
				}
				return;
			}
			if (nuiobj && nuiobj.type.indexOf('buttonedit')>=0) {
				//nui的弹出选择框控件
				if (typeof nuiobj._OnButtonClick=='function') {
					nuiobj._OnButtonClick(e.originalEvent);
				}
				e.preventDefault();
				return;
			}
		} else if (tagName=='SELECT') {
			//html原生的下拉框
			e.originalEvent.keyCode=e.shiftKey ? 38 : 40;
			return;
		}
	}
}
$(document).unbind('keydown', documentKeydown);
$(document).bind('keydown', documentKeydown);

mini.formatFloat = function (value, decimalPlaces) {
	value=value||'';
	value=value.replace(/,/g,'');
	if (isNaN(value)) {
		if (value.indexOf('.') >= 0) {
			var idx = value.indexOf('.',value.indexOf('.')+1)
			value = value.substr(0, idx);
		}
		if (value.indexOf('.') == 0)
			value = '0' + value;
			
		if (isNaN(value))
			return '0';
	}
	if (!decimalPlaces)
		decimalPlaces=2;
	if (decimalPlaces<0) {
		//百分比、千分比等
		//value=parseFloat(value)*Math.pow(10,-1*decimalPlaces);
		return new String(value);
	}
	var text = mini.formatCurrency(value);
	text = text.substr(0, text.indexOf('.'));
	if (value.indexOf('.')>0) {
		var dec='0.'+value.substr(value.indexOf('.')+1);
		dec=parseFloat(dec).toFixed(decimalPlaces);
		if (dec.indexOf('.')>=0) {
			text += '.' + dec.substr(dec.indexOf('.')+1);
			for (var i=0; i<decimalPlaces; i++) {
				if (text.charAt(text.length-1) != '0')
					break;
				text = text.substr(0, text.length-1);
			}
			if (text.indexOf('.')==text.length-1) {
				if (decimalPlaces==0)
					text = text.substr(0, text.length-1);//防止最后一位是小数点
			}
		}
	}
	return text;
}
function returnFalse() {
        return false;
}

     
	/**
	 *@刘子良
	 *@liuzl@primeton.com
	  @date:2014/08/06
	 * 单行文本框(获取光标位置 )
	 */
	function getPositionForInput(ctrl){ 
		var CaretPos = 0; 
		if (document.selection) { // IE Support 
			ctrl.focus(); 
		var Sel = document.selection.createRange(); 
		Sel.moveStart('character', -ctrl.value.length); 
			CaretPos = Sel.text.length; 
		}else if(ctrl.selectionStart || ctrl.selectionStart == '0'){// Firefox support 
			CaretPos = ctrl.selectionStart; 
		} 
		return (CaretPos); 
	}
	
	/**
	 *@刘子良
	 *@liuzl@primeton.com
	  @date:2014/08/06
	 * 为nui-textbox文本框bind一个事件
	 */
	 var input;
	 $(document).ready(function(){
	   $('input').click(function(){
	   		 //清空cookie里面的内容
			 var obj$ =$($(this)) ; //jQuery对象 
			 input=obj$[0]; //DOM对象
			 $.cookies.set('num',getPositionForInput(input));//把得到的nui-textbox文板块索引值放置cookie
		})
	 });
	 
	 //去掉前面的空格
	 function LTrim(str){ 
	   var i; 
	   for(i=0;i<str.length;i++){
	     if(str.charAt(i)!=" ") 
	       break; 
	   } 
	   str = str.substring(i,str.length); 
	   return str; 
	 }
	 //去掉后面的空格
	 function RTrim(str){ 
	   var i; 
	   for(i=str.length-1;i>=0;i--){ 
	     if(str.charAt(i)!=" ") 
	       break; 
	   } 
	   str = str.substring(0,i+1); 
	   return str; 
	 }	 

// 增加document的鼠标单击事件
function onclick_document(e) {
 
	// 隐藏输入框的金额、百分比等显示
	var menuid='_hidden_tips';
	if (null==mini.get(menuid)) {
		var html='<ul id="'+menuid+'" class="nui-contextmenu" style="display:none;"><li onclick="returnFalse">tips</li></ul>';
		$(html).appendTo(document.body);
		mini.parse($('#'+menuid)[0]);
		mini.get(menuid).getItems()[0]._iconEl.style.display='none';
		mini.get(menuid).getItems()[0]._innerEl.style.paddingLeft='0';
		$(mini.get(menuid).el).removeClass('mini-menu');
    	mini.get(menuid).el.color='#201F35';
	}
	//top.x=e;
	var keyCode=e.keyCode||e.which;
	if (keyCode==37 || keyCode==39) {
		//左右方向箭头
		mini.get(menuid).hide();
		return;
	}
	var obj=e.fromElement||e.target||(e.htmlEvent).target;
	if (obj == null) {
		mini.get(menuid).hide();
		return;
	}
	if (!obj.contentId2 && !obj.uid && !obj.id) {
		mini.get(menuid).hide();
		return;
	}
	if (!!obj.contentId2) {
		obj = mini.get(obj.contentId2);
	} else if (!!obj.uid) {
		obj = mini.getbyUID(obj.uid);
	} else if (!!obj.id) {
		var id=obj.id;
		if (id.indexOf('$')>0) {
			id=id.substr(0,id.indexOf('$'));
		}
		obj = mini.get(id);
	}
	if (obj == null || !obj.id) {
		mini.get(menuid).hide();
		return;
	}
	var type=obj.type;
	//console.log('type:'+type);
	if (type != 'textbox' && type != 'text') {
		mini.get(menuid).hide();
		return;
	}
	//top.d=obj;
	if (obj.value!=obj._valueEl.value.replace(/,/g,''))
		obj.value=obj._valueEl.value.replace(/,/g,'');
	if (!obj.value || obj.value.length < 1 || ( obj.dataType != "currency" && obj.dataType != "currency1" && obj.dataType!='float')
		|| isNaN(obj.value.replace(/,/g,''))
		|| (obj.dataType=='float' && obj.decimalPlaces<0)
		) {
		mini.get(menuid).hide();
		return;
	}
	
	var oldText = obj.value;//金额大写转换注入代码
	
	var text=obj.value;
	if (obj.dataType == "currency") {
   		text = mini.formatCurrency(obj.value, obj.currencyUnit);
        //nui.get("currey").setValue("999,9999");//--------------------------------
	}else if (obj.dataType == "currency1") {
   		text = mini.formatCurrencyNew(obj.value, obj.currencyUnit);
        //nui.get("currey").setValue("999,9999");//--------------------------------
	}else if (obj.dataType=='float') {
		obj.decimalPlaces=obj.decimalPlaces||2;
		text = mini.formatFloat(obj.value, obj.decimalPlaces);
	}
	if (text == obj.value) {
		mini.get(menuid).hide();
		return;
	}
	if (obj.value.indexOf('.')>=0 && obj.value.substr(obj.value.indexOf('.')+1).length > obj.decimalPlaces) {
		//输入的小数点位数超过限制
		//obj.setValue(parseFloat(obj.value).toFixed(obj.decimalPlaces));
	}
	//if (!!keyCode && 1==2) {
	if (!!keyCode) {
		try {//键入字符时，重新格式化字符
			var indexTest = obj.value.length;
			if(indexTest==1) {
				obj.setValue(text);
				//设置光标位置
				var range=obj._textEl.createTextRange();
				range.collapse(true);
				range.moveStart('character',1);
				range.select();
			} else {
				var workRange=document.selection.createRange();
				obj._textEl.select();
				var allRange=document.selection.createRange();
				workRange.setEndPoint('StartToStart',allRange);
				var len=workRange.text.length;
				workRange.collapse(false);
				workRange.select();
				
				var oldLen = len;
				
				//console.log('ori:'+len);//原光标位置
				len += (text.length-obj._textEl.value.length);
				//console.log('now:'+len);
				//obj._textEl.value=text;//此语句执行后，输入框光标位置会漂移到最前面或最后面
				//obj._textEl.innerHTML=text;
				
				if(text.substr(0,len).lastIndexOf('.')>=0) {
					len += 1;
				}
				
				obj.setValue(text);
				
				//设置光标位置
				var range=obj._textEl.createTextRange();
				range.collapse(true);
				range.moveStart('character',len);
				range.select();
			} 
		} catch(e){};
	} else {
		//设置光标位置
		obj.setValue(text);
		var range=obj._textEl.createTextRange();
		//刘子良20140806 修改 从新动态获取光标索引位置，进行光标定位
		
		var clickNum = $.cookies.get('num');
		//var newText = text.substr(0,clickNum);
		//var array = newText.split(',');
		//range.moveStart('character',clickNum + array.length - 1);//text.length
		
		range.moveStart('character',clickNum);//text.length
		range.collapse(true);
		range.select();
	}
	
	//nui.get(menuid).getItems()[0].setText(text);
	nui.get(menuid).getItems()[0].setText(ConvertMoney(oldText));//金额大写转换注入代码
	
	nui.get(menuid).showAtEl(obj.id, {
		xAlign:'right',
		yAlign:'below'
	});
	
	//$.cookie('num', null); //删除 cookie
}

$(document).unbind('click', onclick_document);//click
$(document).click(onclick_document);

//增加窗口大小改变的控制
function onresize_window(e) {
	var uids=mini.uids;
	for (var key in uids) {
		var uiElement=uids[key];
		if (uiElement.type!='datagrid')
			continue;
			
		uiElement.autoSetWidth();
	}
}
$(window).unbind('resize', onresize_window);
$(window).resize(onresize_window);


function getDictText(dictTypeId, a) {
	var text = "";
	if ('org' == dictTypeId) {
		return git.getOrgName(a);
	} else if ('user' == dictTypeId)
		return git.getUserName(a);
	else if ('product' == dictTypeId)
		return git.getProductName(a);
	else if ('batchOrg' == dictTypeId)//没有使用的财务机构
		return git.getBatchOrgName(a);
	else if ('acctOrg' == dictTypeId)
		return git.getAcctOrgName(a);
	else
		text = mini.getDictText(dictTypeId, a);
	if (!text)
		return a;
	return text;
}

/*控件宽度定义*/
mini.TextBox.prototype.width='80%';
mini.TextArea.prototype.width='80%';
mini.DictComboBox.prototype.width='80%';
mini.TextBoxList.prototype.width='80%';
mini.ComboBox.prototype.width='80%';
mini.ButtonEdit.prototype.width='80%';
mini.DatePicker.prototype.width='80%';

//控件模型属性设置
mini.Window.prototype.showMaxButton=true;

/*提示信息中文化*/
mini.TextBox.prototype.requiredErrorText = "必填项";
mini.copyTo(mini.VTypes, {
    uniqueErrorText: "字段值已存在",
    requiredErrorText: "必填项",
    emailErrorText: "请输入正确的Email地址",
    urlErrorText: "请输入正确的URL",
    floatErrorText: "请输入正确的数字",
    intErrorText: "请输入正确的整数",
    dateErrorText: "请输入正确的日期，日期格式为： {0}",
    maxLengthErrorText: "不能超过 {0} 个字符",
    minLengthErrorText: "不能少于 {0} 个字符",
    maxErrorText: "不能大于 {0}.",
    minErrorText: "不能小于 {0}.",
    rangeLengthErrorText: "字符数必须在 {0} 和 {1} 之间",
    rangeCharErrorText: "字符数必须在 {0} 和 {1} 之间",
    rangeErrorText: "输入值应在 {0} 和 {1} 之间"
});
mini.copyTo(mini.Calendar.prototype,{
	todayText: "今天",
    clearText: "清除",
    okText: "确认",
    cancelText: "取消",
    daysShort: ["日", "一", "二", "三", "四", "五", "六"]
});
/*解决textarea在IE中无法按设定高度显示的问题*/
mini.TextArea.prototype.canLayout=function(){
	if (mini.isIE)
		return true;
	
	return mini.TextArea.superclass.canLayout.call(this);
}
/*定制：pageSize列表每页显示记录默认条数修改为20，未指定pageSize时为20.
nui-min-debug.js Line 19594
mini.extend(mini.Pager, mini.Control, {
    pageIndex: 0,
    pageSize: 10 -> 20
 */

/*给消息提示框增加回车键及Esc键操作*/
mini.MessageBox.show = function(e) {
        e = mini.copyTo({
            width: "auto",
            height: "auto",
            showModal: true,
            minWidth: 150,
            maxWidth: 800,
            minHeight: 100,
            maxHeight: 350,
            showHeader: true,
            title: "",
            titleIcon: "",
            iconCls: "",
            iconStyle: "",
            message: "",
            html: "",
            spaceStyle: "margin-right:15px",
            showCloseButton: true,
            buttons: null,
            buttonWidth: 58,
            callback: null
        },
        e);
        var f = e.callback;
        var m = new mini.Window();
        m.setBodyStyle("overflow:hidden");
        m.setShowModal(e.showModal);
        m.setTitle(e.title || "");
        m.setIconCls(e.titleIcon);
        m.setShowHeader(e.showHeader);
        m.setShowCloseButton(e.showCloseButton);
        var d = m.uid + "$table",
        b = m.uid + "$content";
        var v = '<div class="' + e.iconCls + '" style="' + e.iconStyle + '"></div>';
        var o = '<table class="mini-messagebox-table" id="' + d + '" style="" cellspacing="0" cellpadding="0"><tr><td>' 
        	+ v + '</td><td id="' + b + '" class="mini-messagebox-content-text">' + (e.message || "") + "</td></tr></table>";
        var c = '<div class="mini-messagebox-content"></div><div class="mini-messagebox-buttons"></div>';
        m._bodyEl.innerHTML = c;
        var g = m._bodyEl.firstChild;
        if (e.html) {
            if (typeof e.html == "string") {
                g.innerHTML = e.html
            } else {
                if (mini.isElement(e.html)) {
                    g.appendChild(e.html)
                }
            }
        } else {
            g.innerHTML = o
        }
        m._Buttons = [];
        var w = m._bodyEl.lastChild;
        if (e.buttons && e.buttons.length > 0) {
            for (var t = 0,
            r = e.buttons.length; t < r; t++) {
                var a = e.buttons[t];
                var n = mini.MessageBox.buttonText[a];
                if (!n) {
                    n = a
                }
                var j = new mini.Button();
                j.setText(n);
                j.setWidth(e.buttonWidth);
                j.render(w);
                j.action = a;
                j.on("click",
                function(l) {
                    var i = l.sender;
                    if (f) {
                        f(i.action)
                    }
                    mini.MessageBox.hide(m)
                });
                if (t != r - 1) {
                    j.setStyle(e.spaceStyle)
                }
                m._Buttons.push(j)
            }
        } else {
            w.style.display = "none"
        }
        m.setMinWidth(e.minWidth);
        m.setMinHeight(e.minHeight);
        m.setMaxWidth(e.maxWidth);
        m.setMaxHeight(e.maxHeight);
        m.setWidth(e.width);
        m.setHeight(e.height);
        m.show();
        var q = m.getWidth();
        m.setWidth(q);
        var p = m.getHeight();
        m.setHeight(p);
        var k = document.getElementById(d);
        if (k) {
            k.style.width = "100%"
        }
        var h = document.getElementById(b);
        if (h) {
            h.style.width = "100%"
        }
        var u = m._Buttons[0];
        if (u) {
            u.focus()
        } else {
            m.focus()
        }
        m.on("beforebuttonclick",
        function(i) {
            if (f) {
                f("close")
            }
            i.cancel = true;
            mini.MessageBox.hide(m)
        });
        mini.on(m.el, "keydown",
        function(i) {
            if (i.keyCode == 27) {
                if (f) {
                    f("close")
                }
                i.cancel = true;
                mini.MessageBox.hide(m)
            }
            if (i.keyCode == 32 || i.keyCode == 13) {
                if (f) {
                    f("ok")
                }
                i.ok = true;
                mini.MessageBox.hide(m)
            }
        });
        return m.uid
}; // 结束： mini.MessageBox.show
if (!window._alert) {
	mini.alert = function(a, b, c) {
	    return top.mini.MessageBox.show({
	        minWidth: 250,
	        title: b || mini.MessageBox.alertTitle,
	        buttons: ["ok"],
	        message: a,
	        iconCls: "mini-messagebox-warning",
	        callback: c
	    })
	};
	window._alert=window.alert;
	window.alert=function(msg){
		if (top && top.mini) {
			mini.alert(msg);
			return;
		}
		if (msg)
			msg=msg.replace(/<br\/>/g,'\n');
		window._alert(msg);
	};
	mini.confirm = function(a, b, c) {
		if (!c || typeof c != 'function')
			return window._confirm(a, b);
	    return top.mini.MessageBox.show({
	        minWidth: 250,
	        title: b || mini.MessageBox.confirmTitle,
	        buttons: ["ok", "cancel"],
	        message: a,
	        iconCls: "mini-messagebox-question",
	        callback: c
	    })
	};
	window._confirm=window.confirm;
	window.confirm=mini.confirm;
}

mini.Form.prototype.setData = function(d, e, a) {
    if (mini.isNull(a)) {
        a = true
    }
    if (typeof d != "object") {
        d = {}
    }
    var g = this.getFieldsMap();
    for (var c in g) {
        var f = g[c];
        if (!f) {
            continue
        }
        if (f.setValue) {
            var b = d[c];
            if (a == true) {
                b = mini._getMap(c, d)
            }
            if (b === undefined && e === false) {
                continue
            }
            if (b === null) {
                b = ""
            }
            f.setValue(b)
        }
        if (f.setText && f.textName) {
            var h = d[f.textName];
            if (a == true) {
                h = mini._getMap(f.textName, d)
            }
            if (mini.isNull(h)) {
                h = ""
            }
            f.setText(h)
        }
    }
    //增加了如下一行，用于判断表单数据是否发生变化
    self['formData' + this.id]=nui.clone(this.getData());
    
    //增加了如下一行，用于验证表单信息
    this.validate()
};
mini.Form.prototype.isDataChanged = function() {
    var d=self['formData' + this.id];
    var d2=this.getData();
    d=nui.encode(d);
    d2=nui.encode(d2);
    //console.log(d);
    //console.log(d2);
    if (d == d2)
    	return false;
    return true;
};

mini.ValidatorBase.prototype.setRequired = function(a) {
    this.required = a;
    if (this.required) {
        this.addCls(this._requiredCls)
    } else {
        this.removeCls(this._requiredCls)
    }
    this.validate();
}

//获取业务字典的数据
mini.getDictData = function (dicttypeid) {
	var result = [];
	top.map = top.map ||{};//cache in top
	if (top.map[dicttypeid]) {
		//return mini.decode(top.map[dicttypeid]);
	}
	mini.ajax({
	                    url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
	                    data: {
	                        "dictTypeId": dicttypeid
	                    },
	                    type: "POST",
	                    async: false,
	                    success: function(f) {
	                        var e = f.dictList || [];
	                        for (var i=0; i<e.length; i++) {
	                        }
	                        result = e;
	                        top.map[dicttypeid]=mini.encode(e);
	                    }
	});
	return result || [];
};
mini.getDictDataForTree = function (dicttypeid, callbackFunc) {
	var result = [];
	mini.ajax({
	                    url: "com.bos.utp.dict.DictManager.getDictData.biz.ext",
	                    data: {
	                        "dicttypeid": dicttypeid
	                    },
	                    type: "POST",
	                    async: false,
	                    success: function(f) {
	                        var e = f.dictList || [];
	                        result = e;
	                        if (callbackFunc && typeof callbackFunc=='function') {
	                        	callbackFunc(result);
	                        }
	                    }
	});
	return result || [];
}

/*处理菜单中， 短横杠用来表示分隔符*/
mini.Menu.prototype.setItems = function(b) {
        if (!mini.isArray(b)) {
            b = []
        }
        this.removeAll();
        var d = new Date();
        for (var c = 0,
        a = b.length; c < a; c++) {
        	if(b[c].text=='-'){this.addItem('-');continue;}
            this.addItem(b[c])
        }
};

/*原nui-min.js文件中，中文乱码*/
mini.dateInfo = {
    monthsLong: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
    monthsShort:["1月", "2月","3月", "4月","5月", "6月","7月", "8月","9月", "10月","11月", "12月"],
    daysLong:["星期日", "星期一", "星期二","星期三", "星期四","星期五", "星期六"],
    daysShort:["日", "一", "二","三", "四","五", "六"],
    quarterLong:["一季度","二季度", "三季度","四季度"],
    quarterShort: ["Q1", "Q2", "Q2", "Q4"],
    halfYearLong: ["上半年","下半年" ],
    patterns: {
        d: "yyyy-M-d",
        D: "yyyy年M月d日",
        f:"yyyy年M月d日 H: mm ",
        F:"yyyy年M月d日 H: mm: ss ",
        g:"yyyy - M - d H: mm ",
        G:"yyyy - M - d H: mm: ss ",
        m:"MMMdd日",
        o: "yyyy-MM-ddTHH:mm:ss.fff",
        s: "yyyy-MM-ddTHH:mm:ss",
        t: "H:mm",
        T: "H:mm:ss",
        U: "yyyy年M月d日 HH:mm:ss tt",
        y: "yyyy年MM月"
    },
    tt:{AM:"上午",PM:"下午"},
    ten:{Early:"上旬",Mid:"中旬",Late:"下旬"},
    today:"今天",clockType:24
};

/*处理文本输入框格式化*/
mini.TextBox.prototype.setValue = function(a) {
        if (a === null || a === undefined) {
            a = ""
        }
        a = String(a);
        a = LTrim(RTrim(a));
        
        if (a.length > this.maxLength) {
            a = a.substring(0, this.maxLength)
        }
        if (this.value !== a) {
            this.value = a;
            this._valueEl.value = this._textEl.value = a;
            
            if (typeof a=='string' && a.length>0) {
            	
            	if (!this.dateFormat && this.dataType =='date')
            		this.dateFormat='yyyy-MM-dd';
            	if (!!this.dateFormat && this.dateFormat.length > 1)
            		this._textEl.value=mini.formatDate(mini.parseDate(a), this.dateFormat);
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            	if (this.dataType == "currency") {
            		this._textEl.value = mini.formatCurrency(a, this.currencyUnit);
            		if(this._textEl.value=='0.00'){//小数位后再加小数点处理--20160727 pc
            			this.value = this._textEl.value;
            		}
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//this._valueEl.value = this.value = mini.formatCurrency(a, this.currencyUnit).replace(/,/g,'');
        		}
            	if (this.dataType == "currency1") {
            		this._textEl.value = mini.formatCurrencyNew(a, this.currencyUnit);
            		//if(this._textEl.value=='0.00'){//小数位后再加小数点处理--20160727 pc
            			this.value = this._textEl.value;
            		//}
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//this._valueEl.value = this.value = mini.formatCurrency(a, this.currencyUnit).replace(/,/g,'');
        		}
        		if (this.dataType=='float') {
        			if (this.decimalPlaces!==0 && null == this.decimalPlaces)
        				this.decimalPlaces=2;
        			this._textEl.value = mini.formatFloat(a, this.decimalPlaces);
        			if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//if (this.decimalPlaces>=0)
            			//this._valueEl.value = this.value = mini.formatFloat(a, this.decimalPlaces).replace(/,/g,'');
        			//parseFloat(a).toFixed(this.decimalPlaces);
        		}
        	}
        	
        	if (typeof a=='number') {
            	if (this.dataType == "currency") {
            		this._textEl.value = mini.formatCurrency(a, this.currencyUnit);
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
        		}
            	if (this.dataType == "currency1") {
            		this._textEl.value = mini.formatCurrencyNew(a, this.currencyUnit);
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
        		}
        		if (this.dataType=='float') {
        			if (this.decimalPlaces!==0 && null == this.decimalPlaces)
        				this.decimalPlaces=2;
        			this._textEl.value = mini.formatFloat(a, this.decimalPlaces);
        			if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//if (this.decimalPlaces>=0)
            			//this._valueEl.value = this.value = mini.formatFloat(a, this.decimalPlaces).replace(/,/g,'');
        			//parseFloat(a).toFixed(this.decimalPlaces);
        		}
        	}
            this._doEmpty()
        }else{//在小数位后再加小数点的处理--20160727 pc
        	if (typeof a=='string' && a.length>0) {
            	if (this.dataType == "currency") {
            		this._textEl.value = mini.formatCurrency(a, this.currencyUnit);
            		if(this._textEl.value=='0.00'){
            			this.value = this._textEl.value;
            		}
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//this._valueEl.value = this.value = mini.formatCurrency(a, this.currencyUnit).replace(/,/g,'');
            		this._doEmpty()
        		}
            	
            	if (this.dataType == "currency1") {
            		this._textEl.value = mini.formatCurrencyNew(a, this.currencyUnit);
            			this.value = this._textEl.value;
            		if (this._textEl.tagName.toUpperCase()=='SPAN')
        				this._textEl.innerHTML = this._textEl.value;
            		//this._valueEl.value = this.value = mini.formatCurrency(a, this.currencyUnit).replace(/,/g,'');
            		this._doEmpty()
        		}
        	}
        }
};

mini.TextBox.prototype.getAttrs = function(b) {
        var a = mini.TextBox.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        mini._ParseString(b, a, ["value", "text", "emptyText", "inputStyle", "onenter", "onkeydown", "onkeyup", 
        	"onkeypress", "maxLengthErrorText", "minLengthErrorText", "onfocus", "onblur", "vtype", "emailErrorText", 
        	"urlErrorText", "floatErrorText", "intErrorText", "dateErrorText", "minErrorText", "maxErrorText", "rangeLengthErrorText", 
        	"rangeErrorText", "rangeCharErrorText", 
        	"dateFormat", "dataType", "currencyUnit", "dictTypeId"]);
        mini._ParseBool(b, a, ["allowInput", "selectOnFocus"]);
        mini._ParseInt(b, a, ["maxLength", "minLength", "minHeight", "minWidth"
        , "decimalPlaces"]);
        return a
};
mini.TextBox.prototype._initEvents = function() {
	mini._BindEvents(function() {
        mini_onOne(this._textEl, "drop", this.__OnDropText, this);
        mini_onOne(this._textEl, "change", this.__OnInputTextChanged, this);
        mini_onOne(this._textEl, "focus", this.__OnFocus, this);
        mini_onOne(this.el, "mousedown", this.__OnMouseDown, this);
        $(this._textEl).keyup(onclick_document);
        if (this.dataType=='float'||this.dataType=='currency'||this.dataType=='currency1') {
        	var d=this;
        	//$(this._textEl).blur(function(){
        		//var value=d._textEl.value;
        		//d.value='';
        		//d.setValue(value.replace(/,/g,''));
        	//});
        }
        var a = this.value;
        this.value = null;
        this.setValue(a)
    },
    this);
    this.on("validation", this.__OnValidation, this)
};
mini.TextBox.prototype.__OnValidation = function(a) {
	if (a.isValid == false) {
        return;
    }
    if (this.dataType=='float'||this.dataType=='currency'||this.dataType=='currency1') {
    	var value = a.value;
    	mini._ValidateVType(this.vtype, value.replace(/,/g,''), a, this);
    } else {
    	mini._ValidateVType(this.vtype, a.value, a, this);
    }
};
mini.TextBox.prototype.getValue = function() {
    if (this.dataType=='float'||this.dataType=='currency'||this.dataType=='currency1') {
    	var value = this.value;
    	return value.replace(/,/g,'');
    }
    return this.value
}

/*增加文本反显控件*/
mini.Text = function() {
    mini.Text.superclass.constructor.call(this)
};
mini.extend(mini.Text, mini.TextBox, {
    uiCls: "mini-text",
    _create: function() {
    	var a = '<span/>';
        a = '<span class="mini-textbox-border">' + a + "</span>";
        a += '<input type="hidden"/>';
        this.el = document.createElement("span");
        this.el.className = "mini-textbox mini-textbox-disabled mini-textbox-readOnly";
        this.el.innerHTML = a;
        this._borderEl = this.el.firstChild;
        this._textEl = this._borderEl.firstChild;
        this._valueEl = this._borderEl.lastChild;
        this._doEmpty()
    },
    setText: function(t) {
    	//by wangshichun
    	t=t||'';
    	t=t.replace(/,/g,'');
    	if (isNaN(t) || t.length < 4) {
    		this._textEl.innerHTML=t;
    		return;
    	}
    	if (this.dataType == "currency") {
       		this._textEl.innerHTML = mini.formatCurrency(t, this.currencyUnit);
    		return;
   		}
    	if (this.dataType == "currency1") {
       		this._textEl.innerHTML = mini.formatCurrencyNew(t, this.currencyUnit);
    		return;
   		}
   		if (this.dataType=='float') {
  			if (this.decimalPlaces!==0 && null == this.decimalPlaces)
  				this.decimalPlaces=2;
  			this._textEl.innerHTML = mini.formatFloat(t, this.decimalPlaces);
    		return;
  		}
  		this._textEl.innerHTML=t;
    },
    getText: function(t) {
    	//by wangshichun
    	return this._textEl.innerText;
    },
    setValue: function(a) {
        if (a === null || a === undefined) {
            a = ""
        }
        a = String(a);
        if (a.length > this.maxLength) {
            a = a.substring(0, this.maxLength)
        }
		if (this._textEl.tagName.toLowerCase()=='span') {
			this._textEl.innerHTML = this._textEl.value = a;
		}
		if (!!this.dictTypeId && this.dictTypeId.length > 0) {
   			var text = getDictText(this.dictTypeId, a);
   			if (!!text) {
   				this._textEl.innerHTML = this._textEl.value = text;
   			}
   		}
        if (this.value !== a) {
            this.value = a;
            this._valueEl.value = this._textEl.innerHTML = this._textEl.value = a;
            if (typeof a=='string' && a.length>0) {
            	if (!this.dateFormat && this.dataType =='date')
            		this.dateFormat='yyyy-MM-dd';
            	if (!!this.dateFormat && this.dateFormat.length > 1)
            		this._textEl.innerHTML = this._textEl.value = mini.formatDate(mini.parseDate(a), this.dateFormat);
            	if (this.dataType == "currency") {
            		this._textEl.innerHTML = this._textEl.value = mini.formatCurrency(a, this.currencyUnit)
        		}
            	if (this.dataType == "currency1") {
            		this._textEl.innerHTML = this._textEl.value = mini.formatCurrencyNew(a, this.currencyUnit)
        		}
        		if (this.dataType=='float') {
        			if (this.decimalPlaces!==0 && null == this.decimalPlaces)
        				this.decimalPlaces=2;
        			this._textEl.innerHTML = this._textEl.value = mini.formatFloat(this.value, this.decimalPlaces);
        		}
        		if (!!this.dictTypeId && this.dictTypeId.length > 0) {
        			var text = getDictText(this.dictTypeId, a);
        			if (!!text) {
        				this._textEl.innerHTML = this._textEl.value = text;
        			}
        		}
        	}
        	if (typeof a=='number') {
            	if (this.dataType == "currency") {
            		this._textEl.innerHTML = this._textEl.value = mini.formatCurrency(a, this.currencyUnit)
        		}
            	if (this.dataType == "currency1") {
            		this._textEl.innerHTML = this._textEl.value = mini.formatCurrencyNew(a, this.currencyUnit)
        		}
        		if (this.dataType=='float') {
        			if (this.decimalPlaces!==0 && null == this.decimalPlaces)
        				this.decimalPlaces=2;
        			this._textEl.innerHTML = this._textEl.value = mini.formatFloat(this.value, this.decimalPlaces);
        		}
        	}
            	
            this._doEmpty()
        }
    }
});
mini.regClass(mini.Text, "text");

/*动态panel面板*/
mini.DynPanel = function() {
    mini.DynPanel.superclass.constructor.call(this)
};
mini.extend(mini.DynPanel, mini.ValidatorBase, {
    _clearBorder: false,
    formField: false,
    width: "100%",
    height: "auto",
    tableLayout: "fixed",
    columns: 4,
    tds:[],
    uiCls: "mini-dynpanel",
    _create: function() {
        this.el = document.createElement("table");
        this.el.className = "mini-dynpanel nui-form-table";
        this.el.style.width = this.width;
        this.el.style.height = this.height;
        this.el.style.tableLayout = this.tableLayout;
        var tb = document.createElement("tbody");
        this.el.appendChild(tb);
    },
    getAttrs: function(b) {
        var a = mini.DynPanel.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        //mini._ParseString(b, a, ["width", "height", "tableLayout"]);
        mini._ParseString(b, a, ["colWidth", "colAlign"]);
        //mini._ParseBool(b, a, ["allowInput", "selectOnFocus"]);
        mini._ParseInt(b, a, ["columns"]);
        return a
    },
    _afterApply: function(c) {
    	var arr = c.children;
    	var cnt = 0, len = arr.length, colcnt = 0;
    	var tr = null;
    	this.tds=[];
    	var colWidthes = (this.colWidth||'').split(',');
    	var colAlignes = (this.colAlign||'').split(',');
    	for (var i=0; i<arr.length; ) {//i不做加1操作，因为arr.length变小了
    		//console.log('----tagName:'+arr[i].tagName + ',nodeType:' + arr[i].nodeType);
    		if (!arr[i].tagName)
    			continue;//注释节点的tagName为感叹号
    		if (arr[i].nodeType != 1 || arr[i].tagName.charAt(0) == '/') {//非注释节点的nodeType为1
    			len--;//忽略非element节点
    			i++;//非element节点保留在arr中
    			continue;
    		}
    		cnt++; //已处理单元格数
    		if (cnt > len)
    			break; //已处理完毕
    		if (colcnt % this.columns == 0) {
    			tr = document.createElement("tr");
    			this.el.firstChild.appendChild(tr);
    		}
    		var td = document.createElement("td");
    		this.tds[this.tds.length]=td;
    		
    		var s = $(arr[i]).attr('colspan');
    		if (s > 1) {
    			colcnt += parseInt(s);
    			$(td).attr('colspan', s);
    		} else {
    			$(td).attr('colspan', s);
    			colcnt += 1;
	    		if (colcnt % 2 == 0) {
	    			//偶数列
	    			if (this.columns==4){
	    				td.width = '30%';
	    			}else if(this.columns==2){
	    				td.width = '25%';
	    			}
	    			td.align = 'left';
	    		} else {
	    			//奇数列
	    			if (this.columns==4){
	    				td.width = '20%';
	    			}else if(this.columns==2){
	    				td.width = '20%';
	    			}
	    			td.align = 'right';
	    		}
    		}
    		if (colWidthes.length > 0 && colWidthes.length >= colcnt % this.columns && colWidthes[colcnt % this.columns]) {
    			td.width = colWidthes[colcnt % this.columns];
    		}
    		if (colAlignes.length > 0 && colAlignes.length >= colcnt % this.columns && colAlignes[colcnt % this.columns]) {
    			td.align = colAlignes[colcnt % this.columns];
    		}
    		
    		if (arr[i].id && arr[i].id.trim().length > 0) {
    			td.contentId=arr[i].id.trim();
    			td.contentId2=arr[i].id.trim();
    		}
    		if (arr[i].id2 && arr[i].id2.trim().length > 0) {
    			td.contentId2=arr[i].id2.trim();//用于记录div下嵌套的元素
    		} else {
    			var tmp = arr[i].childNodes;
    			if (tmp && tmp.length > 0) {
    				for (var k=0; k<tmp.length; k++) {
    					var tmp2 = tmp[k];
    					if ($(tmp2).attr('class') && $(tmp2).attr('class').indexOf('nui-') == 0 && tmp2.id) {
    						td.contentId2=tmp2.id;
    					}
    				}//遍历子元素
    			}
    		}
    		td.appendChild(arr[i]);
    		tr.appendChild(td);
    		mini.parse(td);
    	}
    	while (colcnt % this.columns != 0) {
    		var td = document.createElement("td");
    		td.innerHTML = "&nbsp;";
    		tr.appendChild(td);
    		colcnt++;
    	}
    },
    notuse_refreshTable: function() {//已不使用
    	var obj=this;
    	var f=this._refreshTable;
    	setTimeout(function(){f(obj);}, 100);
    },
    refreshTable: function(obj) {
    	if (!obj)
    		obj=this;
    	var arr=obj.tds;
    	if (arr.length < 1)
    		return;
    	// 重新调整单元格的位置
    	/*
document.getElementById('corp/customerTypeCd2').style.display='none';
nui.get('corp/customerTypeCd').hide();
nui.get('table1').refreshTable();

document.getElementById('corp/customerTypeCd2').style.display='';
nui.get('corp/customerTypeCd').show();
nui.get('table1').refreshTable();
    	*/
        var hiddenDiv=document.getElementById('nui_hiddenDiv_ext');//用于保存隐藏的TD节点
        if (!hiddenDiv) {
        	hiddenDiv=document.createElement("div");
        	hiddenDiv.id='nui_hiddenDiv_ext';
        	hiddenDiv.style.display='none';
        	document.body.appendChild(hiddenDiv);
        }
        var hiddenDiv2=document.getElementById('nui_hiddenDiv_ext2');//用于临时保存原tbody节点
        if (!hiddenDiv2) {
        	hiddenDiv2=document.createElement("div");
        	hiddenDiv2.id='nui_hiddenDiv_ext2';
        	hiddenDiv2.style.display='none';
        	document.body.appendChild(hiddenDiv2);
        }
    	obj.el.style.display='none';
    	hiddenDiv2.innerHTML='';
    	hiddenDiv2.appendChild(obj.el.firstChild);
    	//$(obj.el.firstChild).remove(); //不能直接删除，否则导致该dom节点下的元素失效
    	var tbody = document.createElement("tbody");
        obj.el.appendChild(tbody);
        var colcnt = 0;
        var tr = null;
    	for (var i=0,len=arr.length; i<len; i++) {
    		var td=arr[i];
    		if (colcnt % obj.columns == 0 && (!tr || tr.childNodes.length != 0)) {
    			tr = document.createElement("tr");
    			tbody.appendChild(tr);
    		}
    		if (td.childNodes.length==1) {
    			//td.appendChild(td.firstChild);
    		}
    		if (td.contentId&&(td.firstChild.style.display=='none' || td.style.display=='none')) {
    			hiddenDiv.appendChild(td);
    			continue;
    		}
    		var s = $(td).attr('colspan')||1;
    		colcnt += parseInt(s);
    		tr.appendChild(td);
    		if (td.contentId2 && nui.get(td.contentId2) && nui.get(td.contentId2).hide) {
    			nui.get(td.contentId2).hide();
    			nui.get(td.contentId2).show();
    		}
    	}
    	if (tr && tr.childNodes.length == 0) {
    		$(tr).remove();
    		tr = null;
    	}
    	if (tr) {
	    	while (colcnt % obj.columns != 0 && colcnt < 100) {
	    		var td = document.createElement("td");
	    		td.innerHTML = "&nbsp;";
	    		tr.appendChild(td);
	    		colcnt++;
	    	}
    	}
    	obj.el.style.display='';
    },
    setName: function(a) {
        this.name = a;
        this.el.name = a
    }
});
mini.regClass(mini.DynPanel, "dynpanel");


/*动态panel面板2*/
mini.DynPanel2 = function() {
    mini.DynPanel2.superclass.constructor.call(this)
};
mini.extend(mini.DynPanel2, mini.Control, {
    _clearBorder: false,
    formField: false,
    width: "100%",
    height: "auto",
    tableLayout: "fixed",
    textField: "text",
    multiSelect: true,
    dataField: "data",
    dataValueField: "id",
    dataTextField: "text",
    columnValueFields: "data1,data2",
    editableFields: "data1",
    commentText:"data3",
    columns: 2,
    tds:[],
    uiCls: "mini-dynpanel2",
    _create: function() {
        this.el = document.createElement("table");
        this.el.className = "mini-dynpanel nui-form-table";
        this.el.style.width = this.width;
        this.el.style.height = this.height;
        this.el.style.tableLayout = '';//this.tableLayout
    },
    getAttrs: function(b) {
        var a = mini.DynPanel.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        mini._ParseString(b, a, ["colWidth", "colAlign", "textField", "dataField", "dataValueField", "dataTextField", "columnValueFields", "editableFields","commentText"]);
        mini._ParseBool(b, a, ["multiSelect"]);
        //mini._ParseInt(b, a, ["columns"]);
        return a
    },
    _afterApply: function(c) {
    },
    setValue: function(a) {
    	var tempId = a["id"];
        this.value = a
        if (typeof a != 'object')
        	this.value={};
        $(this.el.firstChild).remove();
        var tb = document.createElement("tbody");
        this.el.appendChild(tb);
        var tr = document.createElement("tr");
        var titleTr = document.createElement("tr");
        var titleTd1 = document.createElement("td");
        var titleTd2 = document.createElement("td");
        titleTd1.innerHTML="本期";	
        titleTd2.innerHTML="上期";
        titleTd1.className='nui-title';
        titleTd2.className='nui-title';
        tb.appendChild(titleTr);
        tb.appendChild(tr);
        titleTr.appendChild(titleTd1);
        titleTr.appendChild(titleTd2);
        var td = document.createElement("td");
        $(td).attr('colspan', '2');
        tr.appendChild(td);
        if (this.columnValueFields.indexOf(',') >= 0) {
        	this.columns=this.columnValueFields.split(',').length;
        } else {
        	this.columns=2;
        }
        td.innerHTML=this.value[this.textField]; // 第一行： 标题
        
        // 第二行：多选时的是否
	    this.multiSelect = (this.multiSelect == true || this.multiSelect == 'true');
        if (this.multiSelect == true || this.multiSelect == 'true') {
        	var inputType=(this.multiSelect==true ? 'checkbox' : 'radio');
        	tr = document.createElement("tr");
        	tb.appendChild(tr);
        	var vs=this.columnValueFields.split(',');
	       	this.colWidth=this.colWidth||'';
	       	this.colAlign=this.colAlign||'';
	       	var colWidthes=this.colWidth.split(',');
	       	var colAlignes=this.colAlign.split(',');
        	for (var i=0; i<vs.length; i++) {
        		td = document.createElement("td");
		        tr.appendChild(td); 
		        {
	      			var tmp=i % this.columns;
	      			if (colWidthes.length > 0 && colWidthes[tmp])
	      				$(td).width(colWidthes[tmp]);
	      			if (colAlignes.length > 0 && colAlignes[tmp])
	      				td.align=colAlignes[tmp];
	      		}
		        var flg= (this.value[vs[i]]) ? '是' : '否';
		        if (this.editableFields.split(',').indexOf(vs[i]) >= 0) {
		        	//可编辑
		        	td.innerHTML='<input type="radio" value="1" id="'+tempId+'" name="r'+i+'_'+this.uid+'" '+((!!this.value[vs[i]]) ? 'checked' : '')+((this.enabled==false) ? ' disabled' : '')+'/>是'
		        		+ '<input type="radio" value="2" name="r'+i+'_'+this.uid+'" '+((!!this.value[vs[i]]) ? '' : 'checked')+((this.enabled==false) ? ' disabled' : '')+'/>否';
		        	$('input', td).click(function(event){
		        		var uid=event.target.name.substr(event.target.name.indexOf('_')+1);
		        		if ('1' == this.value) {
		        			//是：enable 所有的复选框
		        			var chs=$('input[type="'+inputType+'"]', nui.getbyUID(uid).el);
		        			chs.each(function (e){
		        				this.disabled=false;
		        			});
		        		} else {
		        			var chs=$('input[type="'+inputType+'"]', nui.getbyUID(uid).el);
		        			chs.each(function (e){
		        				this.disabled=true;
		        				this.checked=false;
		        			});
		        		}
		        	});
		        } else {
		        	//不可编辑
			        td.innerHTML=""//flg; 更改上期不显示否。
		        }
        	}
        }
        
        // 第三行：具体的选择项
        tr = document.createElement("tr");
       	tb.appendChild(tr);
       	var vs=this.columnValueFields.split(',');
       	this.colWidth=this.colWidth||'';
       	this.colAlign=this.colAlign||'';
       	var colWidthes=this.colWidth.split(',');
       	var colAlignes=this.colAlign.split(',');
       	for (var i=0; i<vs.length; i++) {
       		td = document.createElement("td");
	        tr.appendChild(td);
        	ul = document.createElement("ul");//选项列表
      		td.appendChild(ul);
      		{
      			var tmp=i % this.columns;
      			if (colWidthes.length > 0 && colWidthes[tmp])
      				$(td).width(colWidthes[tmp]);
      			if (colAlignes.length > 0 && colAlignes[tmp])
      				td.align=colAlignes[tmp];
      		}
	        if (this.editableFields.split(',').indexOf(vs[i]) >= 0) {
	        	//可编辑
	        	var data=this.value[this.dataField]||[];
	        	var val=this.value[vs[i]] || '';
	        	val=val.split(',');
	        	var inputType=(this.multiSelect==true ? 'checkbox' : 'radio');
	        	for (var j=0; j<data.length; j++) {
        			var li=document.createElement('li');
        			li.innerHTML='<input type="'+inputType+'" id="'+tempId+'" name="c'+i+'_'+this.uid+'" value="'+(data[j][this.dataValueField])+'"'+((this.enabled==false) ? ' disabled' : '')+'/>' 
        				+ (data[j][this.dataTextField] || data[j][this.dataValueField] || '');
        			li.style.listStyleType='none';
        			ul.appendChild(li);
	        		if (val.indexOf(data[j][this.dataValueField]) >= 0) {
	        			//已选择
	        			$('input', li)[0].checked=true;
	        		}
	        	}
	        	if (this.multiSelect==true) {
		        	$('input[type="'+inputType+'"]', ul).click(function(event){
		        		var uid=event.target.name.substr(1);
		        		var chs=$('input[type="'+inputType+'"]', nui.getbyUID(uid.substr(uid.indexOf('_')+1)).el);
		        		var flg=false;
	        			chs.each(function (e){
	        				if (this.name.indexOf('c') != 0)
	        					return;
	        				if (this.checked)
	        					flg=true;
	        			});
	        			if (flg == false) {
	        				var rs=document.getElementsByName('r' + uid);//否
	        				if (rs[1].checked == false)
		        				rs[1].checked=true;
	        			} else {
	        				var rs=document.getElementsByName('r' + uid);//是
	        				if (rs[0].checked == false)
		        				rs[0].checked=true;
	        			}
		        	});
	        	}
	        } else {
	        	//不可编辑
	        	var data=this.value[this.dataField]||[];
	        	var val=this.value[vs[i]] || '';
	        	val=val.split(',');
	        	for (var j=0; j<data.length; j++) {
	        		if (val.indexOf(data[j][this.dataValueField]) >= 0) {
	        			//已选择
	        			var li=document.createElement('li');
	        			li.style.listStyleType='none';
	        			li.innerHTML=data[j][this.dataTextField] || data[j][this.dataValueField] || '';
	        			ul.appendChild(li);
	        		}
	        	}
	        }
       	}
       	// 第四行：文本框
       	tr = document.createElement("tr");
       	tb.appendChild(tr);
       	td = document.createElement("td");
       	tr.appendChild(td);
       	var text="";
       	if(this.value[this.commentText]!=null&&this.value[this.commentText]!=undefined){
       		text=this.value[this.commentText];
       	}
       	td.innerHTML='<label>相关描述：</label><textarea name="tt_1_'+this.uid+'" cols="60"' +((this.enabled==false) ? ' disabled' : '')+'>'+text+'</textarea>';
    },
    getValue: function() {
    	var val=mini.clone(this.value);
    	val.uid=this.uid;
    	var vs=this.columnValueFields.split(',');
       	for (var i=0; i<vs.length; i++) {
       		if (this.editableFields.split(',').indexOf(vs[i]) >= 0) {
	        	//可编辑
	        	var chs=document.getElementsByName('c' + i + '_' + this.uid);
	        	var chsValue='';
	        	$.each(chs, function(idx, ele) {
	        		if (ele.checked == false)
	        			return;
	        		if (chsValue.length > 0)
	        			chsValue += ',';
	        		chsValue += ele.value;
	        	});
	        	val[vs[i]]=chsValue;
	        }
       	}
      
       		var textvalues=document.getElementsByName('tt_1_'+this.uid);
       		var tvs='';
       		$.each(textvalues,function(idx,ele){
       			if (tvs.length > 0)
	        			tvs += ',';
	        	tvs+=ele.value;
       		});
       		val[this.commentText]= tvs;
       
       	return val;
    },
    isChanged: function() {
    	var val=mini.clone(this.value);
    	var vs=this.columnValueFields.split(',');
       	for (var i=0; i<vs.length; i++) {
       		if (this.editableFields.split(',').indexOf(vs[i]) >= 0) {
	        	//可编辑
	        	var chs=document.getElementsByName('c' + i + '_' + this.uid);
	        	var chsValue='';
	        	if (val[vs[i]] == null) {
	        		val[vs[i]]='';
	        	}
	        	var values=val[vs[i]].split(',');
	        	var flg=false;
	        	$.each(chs, function(idx, ele) {
	        		if (ele.checked == false)
	        			return;
	        		if (values.indexOf(ele.value) < 0) {
	        			flg=true;//新勾选了值
	        			return;
	        		}
	        		values=$.grep(values,function(v){
	        			if (v==ele.value)
	        				return false;//移除
	        			return true;
	        		});
	        	});
	        	if (flg) {
	        		//新勾选了值
	        		return true;
	        	}
	        	if (values.length > 0) {
	        		//删除了原勾选的值
	        		return true;
	        	}
	        }
       	}
       	return false;
    },
    setName: function(a) {
        this.name = a;
        this.el.name = a
    }
});
mini.regClass(mini.DynPanel2, "dynpanel2");


/*简单列表：未完成*/
mini.SimpleGrid = function() {
    mini.SimpleGrid.superclass.constructor.call(this)
};
mini.extend(mini.SimpleGrid, mini.Control, {
    _clearBorder: false,
    formField: false,
    width: "100%",
    height: "auto",
    tableLayout: "auto", //fixed
    multiSelect: false,
    dataField: "data",
    uiCls: "mini-simplegrid",
    ajaxAsync: true,
    pageSize: 10,
    totalField: "total",
    pageIndexField: "pageIndex",
    pageSizeField: "pageSize",
    _pagers: [],
    _create: function() {
        this.el = document.createElement("table");
        this.el.className = "mini-grid-table";
        this.el.border = '0';
        this.el.cellSpacing = '0';
        this.el.cellPadding = '0';
        this.el.style.width = this.width;
        this.el.style.height = this.height;
        this.el.style.tableLayout = this.tableLayout;
        
        this._contentEl = this.el;//数据显示区，包含标题和数据内容
        this.el = document.createElement("div");
        this.el.className = "mini-simplegrid mini-panel-border";
        this.el.appendChild(this._contentEl);
        
        this._bottomPagerEl = document.createElement("div");
        this._bottomPagerEl.className = "mini-grid-pager";
        this.el.appendChild(this._bottomPagerEl);
    },
    getAttrs: function(b) {
        var a = mini.SimpleGrid.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        mini._ParseString(b, a, ["dataField", "url"]);
        mini._ParseBool(b, a, ["multiSelect", "ajaxAsync"]);
        mini._ParseInt(b, a, ["pageSize"]);
        return a
    },
    _afterApply: function(c) {
    	var arr = c.children[0].children;
    	this.columns = [];
    	var tbody = document.createElement('tbody');
    	this._contentEl.appendChild(tbody);
    	var title = document.createElement('tr');
    	tbody.appendChild(title);
    	for (var i=0,len=arr.length; i<len; i++) {
    		var obj = $(arr[i]);
    		this.columns[i] = {
    			field: obj.attr('field'),
    			autoEscape: obj.attr('autoEscape')=='true',
    			width: obj.attr('width'),
    			dictTypeId: obj.attr('dictTypeId'),
    			type: obj.attr('type'),
    			renderer: obj.attr('renderer')
    		};
    		var td = document.createElement('td');
    		title.appendChild(td);
    		td.className = 'mini-grid-headerCell    mini-grid-bottomCell';
    		td.style.textAlign = 'center';
    		var div = document.createElement('div');
    		td.appendChild(div);
    		div.className = 'mini-grid-headerCell-inner';
    		div.innerHTML = obj.text();
    	}//标题行创建完毕
    	
    	this._createPagers();//创建分页工具条
    	
    },
    setData: function(a) {
    	if (mini.isArray(a) == false)
    		return;
    	this.data=a;
    	
    	var tbody=this._contentEl.firstChild;
    	var trs=tbody.children;
    	for (var i=trs.length-1; i>0; i--) {//不修改标题行
    		$(trs[i]).remove();//移除所有行
    	}
    	for (var i=0,len=a.length; i<len; i++) {//生成数据行
    		var tr=document.createElement('tr');
    		tbody.appendChild(tr);
    		tr.className='mini-grid-row';
    		tr.id=this._id+'$row$'+i;
    		
    		var row=a[i]||{};
    		for (var j=0,lenj=this.columns.length; j<lenj; j++) {
    			var td=document.createElement('td');
    			tr.appendChild(td);
    			
    			var col=this.columns[j];
    			var val=row[col.field]||'';
    			if (col.dictTypeId) {
    				val=getDictText(col.dictTypeId, val) || val;
    				td.innerHTML=val||'&nbsp;';
    			} else if (col.type=='checkcolumn') {
    				var id=this._id+'$checkcolumn$'+i;
    				val='<INPUT hideFocus style="OUTLINE-STYLE: none; OUTLINE-COLOR: invert; OUTLINE-WIDTH: medium" id="'
    					+id
    					+'" name="'
    					+(this._id+'$checkcolumn')
    					+'" type='
    					+(this.multiSelect===true ? 'checkbox' : 'radio')
    					+'>';
    				td.innerHTML=val||'&nbsp;';
    				td.style.textAlign = 'center';
    			} else {
    				td.innerHTML=val||'&nbsp;';
    			}
    			td.className = 'mini-grid-cell';
    		}
    		/*this.columns[i] = {
    			field: obj.attr('field'),
    			autoEscape: obj.attr('autoEscape')==true,
    			width: obj.attr('width'),
    			dictTypeId: obj.attr('dictTypeId'),
    			renderer: obj.attr('renderer')
    		};*/
    	}
    	//alert($('>tr:gt(0)', tbody).length);
    	$('>tr:gt(0)', tbody).unbind('click', this._onRowClick);
    	$('>tr:gt(0)', tbody).click(this._onRowClick);
    	$('>tr:gt(0)', tbody).unbind('hover');
    	$('>tr:gt(0)', tbody).hover(function(e){
    		$(this).addClass('mini-grid-row-hover');
    	}, function(e){
    		$(this).removeClass('mini-grid-row-hover');
    	});
    	
    	this._updatePagesInfo();
    },
    getData: function() {
    	var d=this.data||[];
    	
    	return mini.decode(mini.encode(d));
    },
    _onRowClick: function(e) {
    	//top.x=e; //事件：target源
    	//top.a=this; //事件源
    	$('>tr:gt(0)', this.parentNode).removeClass('mini-grid-row-selected');
    	$(this).addClass('mini-grid-row-selected');
    	var grid=mini.getbyUID(this.id.substr(0,this.id.indexOf('$')));
    	if (grid.columns[0].type=='checkcolumn') {
    		if (grid.multiSelect!==true) {
    			var td=this.firstChild;
    			document.getElementById(this.id.replace('$row$','$checkcolumn$')).checked=true;
    		}
    	}
    },
    _createPagers: function() {
    	this._pagers = [];
        var a = new mini.Pager();
        a.showReloadButton=false;
        this._setBottomPager(a)
    },
    _updatePagesInfo: function() {
        var a = this.pageIndex;
        var e = this.pageSize;
        var d = this.totalCount;
        var f = this._pagers;
        for (var g = 0, c = f.length; g < c; g++) {
            var b = f[g];
            b.update(a, e, d)
        }
    },
    _setBottomPager: function(a) {
        a = mini.create(a);
        if (!a) {
            return
        }
        if (this._bottomPager) {
            this.unbindPager(this._bottomPager);
            this._bottomPagerEl.removeChild(this._bottomPager.el)
        }
        this._bottomPager = a;
        a.render(this._bottomPagerEl);
        this.bindPager(a)
    },
    unbindPager: function(a) {
        if (!a) {
            return
        }
        this._pagers.remove(a);
        a.un("pagechanged", this.__OnPageChanged, this)
    },
    bindPager: function(a) {
        if (!a) {
            return
        }
        this.unbindPager(a);
        this._pagers.add(a);
        a.on("beforepagechanged", this.__OnPageChanged, this)
    },
    __OnPageChanged: function(a) {
    	a.cancel = true;
    	this.gotoPage(a.pageIndex, a.pageSize);
    },
    gotoPage: function(a, b) {
        var c = this.loadParams || {};
        if (mini.isNumber(a)) {
            c.pageIndex = a
        }
        if (mini.isNumber(b)) {
            c.pageSize = b
        }
        this.load(c);
    },
    reload: function(c, b, a) {
        this.load(this.loadParams, c, b, a)
    },
    load: function(e, d, b, a) {
        if (typeof e == "string") {
            this.url=e;
        }
        if (this._loadTimer) {
            clearTimeout(this._loadTimer)
        }
        this.loadParams = e || {};
        if (e.url)
        	this.url = e.url;
        if (this.ajaxAsync) {
            var c = this;
            this._loadTimer = setTimeout(function() {
                c._doLoadAjax(c.loadParams, d, b, a);
                c._loadTimer = null;
            },
            1)
        } else {
            this._doLoadAjax(this.loadParams, d, b, a);
        }
    },
    _evalUrl: function() {
        var url = this.url;
        if (typeof url == "function") {
            url = url()
        } else {
            try {
                url = eval(url)
            } catch(ex) {
                url = this.url
            }
            if (!url) {
                url = this.url
            }
        }
        return url
    },
    _doLoadAjax: function(d, m, l, b, i) {// l: 出错处理，i： ajax为success时调用，b: ajax状态为complete时调用
        d = d || {};
        if (mini.isNull(d.pageIndex)) {
            d.pageIndex = 0
        }
        if (mini.isNull(d.pageSize)) {
            d.pageSize = this.pageSize
        }
        this.loadParams = d;
        var a = this._evalUrl();
        var h = {
            url: a,
            async: this.ajaxAsync,
            type: 'post',
            data: d,
            params: d,
            cache: false,
            cancel: false
        };
        if (h.data != h.params && h.params != d) {
            h.data = h.params
        }
        var c = {};
        c[this.pageIndexField] = d.pageIndex;
        c[this.pageSizeField] = d.pageSize;
        if (d.sortField) {
            c[this.sortFieldField] = d.sortField
        }
        if (d.sortOrder) {
            c[this.sortOrderField] = d.sortOrder
        }
        mini.copyTo(d, c);
        //var f = this.getSelected();
        //this._selectedValue = f ? f[this.idField] : null;
        var j = this;
        j._resultObject = null;
        mini.copyTo(h, {
            success: function(t, u, s) {
                var e = null;
                try {
                    e = mini.decode(t)
                } catch(n) {
                    if (mini_debugger == true) {
                        alert(a + "\n json is error:无法解析表格返回的结果数据")
                    }
                }
                if (e && !mini.isArray(e)) {
                    e.total = parseInt(mini._getMap(j.totalField, e));
                    e.data = mini._getMap(j.dataField, e)
                } else {
                    if (e == null) {
                        e = {};
                        e.data = [];
                        e.total = 0
                    } else {
                        if (mini.isArray(e)) {
                            var p = {};
                            p.data = e;
                            p.total = e.length;
                            e = p
                        }
                    }
                }
                if (!e.data) {
                    e.data = []
                }
                if (!e.total) {
                    e.total = 0
                }
                j._resultObject = e;
                if (!mini.isArray(e.data)) {
                    e.data = [e.data]
                }
                var n = {
                    xhr: s,
                    text: t,
                    textStatus: u,
                    result: e,
                    total: e.total,
                    data: e.data.clone(),
                    pageIndex: d[j.pageIndexField],
                    pageSize: d[j.pageSizeField]
                };
                if (mini.isNumber(e.error) && e.error != 0) {
                    n.textStatus = "servererror";
                    n.errorCode = e.error;
                    n.stackTrace = e.stackTrace;
                    n.errorMsg = e.errorMsg;
                    if (mini_debugger == true) {
                        alert(a + "\n" + n.textStatus + "\n" + n.stackTrace)
                    }
                    j.fire("loaderror", n);
                    if (l) {
                        l.call(j, n)
                    }
                } else {
                    if (i) {
                        i(n)
                    } else {
                        j.pageIndex = n.pageIndex;
                        j.pageSize = n.pageSize;
                        j.totalCount=n.total;
                        //j._OnPreLoad(n);
                        j.setData(n.data);
                        //if (j._selectedValue && j.checkSelectOnLoad) {
                        //    var q = j.getbyId(j._selectedValue);
                        //    if (q) {
                        //        j.select(q)
                        //    }
                        //}
                        //if (j.getSelected() == null && j.selectOnLoad && j.getDataView().length > 0) {
                        //    j.select(0)
                        //}
                        //j.fire("load", n);
                        //if (m && typeof m=='function') {
                        //    m.call(j, n)
                        //}
                    }
                }
            },
            error: function(o, p, n) {
                var e = {
                    xhr: o,
                    text: o.responseText,
                    textStatus: p
                };
                e.errorMsg = o.responseText;
                e.errorCode = o.status;
                if (mini_debugger == true) {
                    alert(a + "\n" + e.errorCode + "\n" + e.errorMsg)
                }
                j.fire("loaderror", e);
                if (l) {
                    l.call(j, e)
                }
            },
            complete: function(n, o) {
                var e = {
                    xhr: n,
                    text: n.responseText,
                    textStatus: o
                };
                j.fire("loadcomplete", e);
                if (b) {
                    b.call(j, e)
                }
                j._xhr = null
            }
        });
        if (this._xhr) {}
        this._xhr = mini.ajax(h)
    }
});
mini.regClass(mini.SimpleGrid, "simplegrid");

/*树形选择*/
mini.TreeAsSelect = function() {
    mini.TreeAsSelect.superclass.constructor.call(this)
};
mini.extend(mini.TreeAsSelect, mini.Control, {
    _clearBorder: false,
    formField: true,
    dictTypeId: "",
    valueField: "DICTID",
    idField: "DICTID",
    textField: "DICTNAME",
    parentField: "PARENTID",
    multiSelectSibling: false,
    multiSelect: true,
    autoCheckParent: false,
    showFolderCheckBox: false,
    uiCls: "mini-treeasselect",
    _create: function() {
        this.el = document.createElement("div");
    },
    _afterApply: function(c) {
        if (!this.dictTypeId) {
        	this.el.innerHTML = "未设置dictTypeId属性";
        	return;
        }
        
        this.tree = new mini.Tree();
        this.tree.setStyle("border:0;width:100%;height:quto;overflow:auto;");
        this.tree.setShowTreeIcon(false);
        this.tree.setShowFolderCheckBox(this.showFolderCheckBox);
        this.tree.setShowCheckBox(true);
        this.idField = this.idField || this.valueField;
        this.tree.setIdField(this.idField);
        this.tree.setTextField(this.textField);
        this.tree.setParentField(this.parentField);
        this.tree.setAllowSelect("false");
        this.tree.setExpandOnLoad("true");
        this.tree.setAutoCheckParent(this.autoCheckParent);
        
        //this.tree.loadData(mini.getDictDataForTree(this.dictTypeId));
        var t=this.tree;
        var tid=t.idFild;
        var tpid=t.parentField;
        var tel=t.el;
        mini.getDictDataForTree(this.dictTypeId,
        	function(data) {
        		if (!t)
        			return;
        		var d=tel.style.display;
        		tel.style.display='none';
        		t.loadList(data, tid, tpid);
        		t.expandAll();
        		tel.style.display=d || '';
        	}
        );
        this.tree.render(this.el);
        this.tree.expandAll();
    },
    getAttrs: function(b) {
        var a = mini.TreeAsSelect.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        mini._ParseString(b, a, ["dictTypeId", "parentField", "textField", "valueField", "idField"]);
        mini._ParseBool(b, a, ["autoCheckParent", "showFolderCheckBox", "multiSelect", "multiSelectSibling"]);
        //mini._ParseInt(b, a, ["columns"]);
        return a
    },
    getValue: function(haveParent) {
    	if (!this.tree)
    		return "";
    	var arr = this.tree.getCheckedNodes(haveParent) || [];
    	if (arr.length == 0)
    		return "";
    	if (this.multiSelect == false) {
    		this.tree.uncheckNodes(arr);
    		this.tree.checkNodes([arr[0]]);
    		return arr[0][this.idField];
    	}
    	if (this.multiSelectSibling == false) {
    		var map = {};
    		var result = "";
    		for (var i=0; i<arr.length; i++) {
    			if (map['id_' + arr[i][this.parentField]]) {
    				this.tree.uncheckNodes([arr[i]]);
    				continue;
    			} 
    			map['id_' + arr[i][this.parentField]]=true;
    			result += result.length > 0 ? "," : "";
    			result += arr[i][this.idField];
    		}
    		return result;
    	}
    	//multiSelectSibling 为true multiSelect也为true
    	return this.tree.getValue(haveParent) || [];
    },
    getFormValue: function(haveParent) {
    	return this.getValue(haveParent);
    },
    setValue: function(v) {
    	if (!this.tree)
    		return;
    	if (mini.isNull(v))
    		v = "";
    	var arr = v.split(",");
    	var nodes = this.tree.getAllChildNodes(this.tree.getRootNode());
    	var checknodes = [];
    	for (var i=0; i<nodes.length; i++) {
    		for (var j=0; j<arr.length; j++) {
    			if (arr[j] == nodes[i][this.idField]) {
    				checknodes[checknodes.length] = nodes[i];
    				break;
    			}
    		}
    	}
    	if (checknodes.length > 0) 
    		this.tree.checkNodes(checknodes);
    },
    setEnabled: function(b){
    	if (this.tree)
    		this.tree.setEnabled(b);
    	var arr = this.el.getElementsByTagName('input');
    	for (var i=0; i<arr.length; i++) {
    		if (!arr[i].id || arr[i].id.indexOf('mini') == -1) 
    			continue;
    		arr[i].disabled = !b;
    	}
    }
});
mini.regClass(mini.TreeAsSelect, "treeasselect");

//----------Button 定制 start
mini.Button.prototype._initEvents = function() {
    mini._BindEvents(function() {
        mini_onOne(this.el, "mousedown", this.__OnMouseDown, this);
        mini_onOne(this.el, "click", this.__OnClick, this)
    },
    this);
    this.onClick(function (){
    	if(this.disableOnClick) {
    		this.setEnabled(false);
    		var _this=this;
    		setTimeout(function(){_this.setEnabled(true);}, 3000);
    	}
    });
}
mini.Button.prototype.getAttrs = function(b) {
    var a = mini.Button.superclass.getAttrs.call(this, b);
    a.text = b.innerHTML;
    mini._ParseString(b, a, ["text", "href", "iconCls", "iconStyle", "iconPosition", "groupName", "menu", "onclick", "oncheckedchanged", "target"]);
    mini._ParseBool(b, a, ["plain", "checkOnClick", "checked", "disableOnClick"]);
    return a
}
//----------Button 定制 end

//----------TextBoxList 定制 start
//空格键、斜杠（键盘上的问号）键时弹出选择框
mini.TextBoxList.prototype.__OnInputKeyDown = function(h) {
		if (this._inputEl.value == this.textPlaceHolder) {
			this._inputEl.value = "";
		}
		//以上的if是增加的
		
        this._selectOnLoad = null;
        if (this.isReadOnly() || this.allowInput == false) {
            return false
        }
        h.stopPropagation();
        if (this.isReadOnly() || this.allowInput == false) {
            return
        }
        var f = mini.getSelectRange(this._inputEl);
        var a = f[0],
        b = f[1],
        i = this._inputEl.value.length;
        var c = a == b && a == 0;
        var d = a == b && b == i;
        if (this.isReadOnly() || this.allowInput == false) {
            h.preventDefault()
        }
        if (h.keyCode == 9) {
            this.hidePopup();
            return
        }
        if (h.keyCode == 16 || h.keyCode == 17 || h.keyCode == 18) {
            return
        }
        switch (h.keyCode) {
        case 13:
            if (this.isShowPopup) {
                h.preventDefault();
                if (this._loading) {
                    this._selectOnLoad = true;
                    return
                }
                this.__doSelectValue()
            }
            break;
        case 27:
            h.preventDefault();
            this.hidePopup();
            break;
        case 8:
            if (c) {
                h.preventDefault()
            }
        case 37:
            if (c) {
                if (this.isShowPopup) {
                    this.hidePopup()
                } else {
                    if (this.editIndex > 0) {
                        var g = this.editIndex - 1;
                        if (g < 0) {
                            g = 0
                        }
                        if (g >= this.data.length) {
                            g = this.data.length - 1
                        }
                        this.showInput(false);
                        this.select(g)
                    }
                }
            }
            break;
        case 39:
            if (d) {
                if (this.isShowPopup) {
                    this.hidePopup()
                } else {
                    if (this.editIndex <= this.data.length - 1) {
                        var g = this.editIndex;
                        this.showInput(false);
                        this.select(g)
                    }
                }
            }
            break;
        case 38:
            h.preventDefault();
            if (this.isShowPopup) {
                var g = -1;
                var j = this._listbox.getFocusedItem();
                if (j) {
                    g = this._listbox.indexOf(j)
                }
                g--;
                if (g < 0) {
                    g = 0
                }
                this._listbox._focusItem(g, true)
            }
            break;
        case 40:
            h.preventDefault();
            if (this.isShowPopup) {
                var g = -1;
                var j = this._listbox.getFocusedItem();
                if (j) {
                    g = this._listbox.indexOf(j)
                }
                g++;
                if (g < 0) {
                    g = 0
                }
                if (g >= this._listbox.getCount()) {
                    g = this._listbox.getCount() - 1
                }
                this._listbox._focusItem(g, true)
            } else {
                this._startQuery(true)
            }
            break;
        //以下两行是增加的：空格键、斜杠（键盘上的问号）键时弹出选择框
        case 32:
        case 191:h.preventDefault();this.showPopup();this._startQuery(true); break;
        default:
            break
        }
}; //mini.TextBoxList.prototype.__OnInputKeyDown 结束

//定制：支持业务字典dictTypeId属性、multiSelect属性
mini.TextBoxList.prototype.getAttrs = function(b) {
        var a = mini.TextBox.superclass.getAttrs.call(this, b);
        var c = jQuery(b);
        mini._ParseString(b, a, ["value", "text", "valueField", "textField", "url", "popupHeight", "textName", "onfocus", 
        	"onbeforeload", "onload", "searchField",
        	"dictTypeId"]);//dictTypeId是新增的，用于业务字典
        mini._ParseBool(b, a, ["allowInput"
        	,"multiSelect"]); //multiSelect是新增的，用于判断是否多选，默认值为false
        mini._ParseInt(b, a, ["popupMinHeight", "popupMaxHeight"]);
        return a
};

//定制：支持placeholder
mini.TextBoxList.prototype._create = function() {
		//以下1行中修改了mini-textboxlist-border为mini-textbox-border
        var a = '<table class="mini-textboxlist" cellpadding="0" cellspacing="0"><tr ><td class="mini-textbox-border"><ul></ul><a href="#"></a><input type="hidden"/></td></tr></table>';
        var b = document.createElement("div");
        b.innerHTML = a;
        this.el = b.firstChild;
        var c = this.el.getElementsByTagName("td")[0];
        this.ulEl = c.firstChild;
        this._valueEl = c.lastChild;
        this.focusEl = c.childNodes[1]
        //以下1行是增加的
        this.textPlaceHolder = "按空格键或问号输入...";
}
mini.TextBoxList.prototype.doUpdate = function() {
        if (this._ValueChangeTimer) {
            clearInterval(this._ValueChangeTimer)
        }
        if (this._inputEl) {
            mini.un(this._inputEl, "keydown", this.__OnInputKeyDown, this)
        }
        var g = [];
        var a = this.uid;
        for (var d = 0,
        c = this.data.length; d < c; d++) {
            var b = this.data[d];
            var j = a + "$text$" + d;
            var h = mini._getMap(this.textField, b);
            if (mini.isNull(h)) {
                h = ""
            }
            g[g.length] = '<li id="' + j + '" class="mini-textboxlist-item">';
            g[g.length] = h;
            g[g.length] = '<span class="mini-textboxlist-close"></span></li>'
        }
        var f = a + "$input";
        //以下1行：增加了style="width:100%;"
        g[g.length] = '<li id="' + f + '" class="mini-textboxlist-inputLi" style="width:100%;">'
        	+'<input class="mini-textboxlist-input" type="text" autocomplete="off" style="width:100%;"></li>';
        this.ulEl.innerHTML = g.join("");
        this.editIndex = this.data.length;
        if (this.editIndex < 0) {
            this.editIndex = 0
        }
        this.inputLi = this.ulEl.lastChild;
        this._inputEl = this.inputLi.firstChild;
        //以下2行是增加的
        this._inputEl.value = this.textPlaceHolder;
        this._inputEl.value = this.textPlaceHolder;
        
        mini.on(this._inputEl, "keydown", this.__OnInputKeyDown, this);
        var e = this;
        this._inputEl.onkeyup = function() {
            e._syncInputSize()
        };
        e._ValueChangeTimer = null;
        e._LastInputText = e._inputEl.value;
        this._inputEl.onfocus = function() {
            e._ValueChangeTimer = setInterval(function() {
                if (e._LastInputText != e._inputEl.value) {
                    e._startQuery();
                    e._LastInputText = e._inputEl.value
                }
            },
            10);
            e.addCls(e._focusCls);
            e._focused = true;
            e.fire("focus")
        };
        this._inputEl.onblur = function() {
            clearInterval(e._ValueChangeTimer);
            e.fire("blur")
        }
};


//定制：支持业务字典
mini.TextBoxList.prototype._doQuery = function() {
        if (this.isDisplay() == false) {
            return
        }
        var j = this.getInputText();
        var d = this;
        var a = this._listbox.getData();
        var c = {
            value: this.getValue(),
            text: this.getText()
        };
        c[this.searchField] = j;
        var b = this.url;
        var i = typeof b == "function" ? b: window[b];
        if (typeof i == "function") {
            b = i(this)
        }
        if (!b) {
        	//start custom
        	if (!this.dictTypeId) {
        		return;
        	}
        	if (this._dictData) {
        		var tmp = mini.clone(d._dictData);
        		var filter = d._inputEl.value;
        		if (filter) {
        			var result = [];
        			for (var i=0; i<tmp.length; i++) {
        				var text = tmp[i][d.textField];
        				var value = tmp[i][d.valueField];
        				if (text.indexOf(filter) < 0)
        					continue;
        					
        				var flag = false;
        				for (var j=0; j<d.data.length; j++) {
							var obj = d.data[j];
							if (obj[d.valueField] == value && obj[d.textField] == text) {
								//当前已存在该选择项
								flag = true;
								break;
							}
						}
						if (flag)
							continue;
        					
        				result[result.length] = tmp[i];
                    }
        			d._listbox.setData(result);
        		} else {
        			var result = [];
        			for (var i=0; i<tmp.length; i++) {
        				var text = tmp[i][d.textField];
        				var value = tmp[i][d.valueField];
        				var flag = false;
        				for (var j=0; j<d.data.length; j++) {
							var obj = d.data[j];
							if (obj[d.valueField] == value && obj[d.textField] == text) {
								//当前已存在该选择项
								flag = true;
								break;
							}
						}
						if (flag)
							continue;
        					
        				result[result.length] = tmp[i];
                    }
        			
        			
        			d._listbox.setData(result);
        		}
        		d.showPopup();
                d._listbox._focusItem(0, true);
                d.fire("load");
                d._loading = false;
                return;
        	}
        	var e=mini.getDictData(this.dictTypeId);
       		for (var i=0; i<e.length; i++) {
            	e[i][d.valueField] = e[i][d.valueField] || e[i].dictID;
            	e[i][d.textField] = e[i][d.textField] || e[i].dictName;
            }
            d._dictData = e;
            d._listbox.setData(e);
       		d.showPopup();
       		d._listbox._focusItem(0, true);
       		d.fire("load");
       		d._loading = false;
       		if (d._selectOnLoad) {
           		d.__doSelectValue();
           		d._selectOnLoad = null
       		}
        	/*d._ajaxer = mini.ajax({
                    url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
                    data: {
                        dictTypeId: this.dictTypeId
                    },
                    type: "POST",
                    async: true,
                    success: function(f) {
                        var e = f.dictList || [];
                        for (var i=0; i<e.length; i++) {
                        	e[i][d.valueField] = e[i][d.valueField] || e[i].dictID;
                        	e[i][d.textField] = e[i][d.textField] || e[i].dictName;
                        }
                        d._dictData = e;
                        d._listbox.setData(e);
                		d.showPopup();
                		d._listbox._focusItem(0, true);
                		d.fire("load");
                		d._loading = false;
                		if (d._selectOnLoad) {
                    		d.__doSelectValue();
                    		d._selectOnLoad = null
                		}
                    },
            		error: function(e, l, k) {
                		d.showPopup("error")
            		}
            });*/
        	//end custom
            return
        }
        var g = "post";
        if (b) {
            if (b.indexOf(".txt") != -1 || b.indexOf(".json") != -1) {
                g = "get"
            }
        }
        var f = {
            url: b,
            async: true,
            params: c,
            data: c,
            type: this.ajaxType ? this.ajaxType: g,
            cache: false,
            cancel: false
        };
        this.fire("beforeload", f);
        if (f.cancel) {
            return
        }
        var h = this;
        mini.copyTo(f, {
            success: function(l) {
                var k = mini.decode(l);
                if (mini.isNumber(k.error) && k.error != 0) {
                    var e = {};
                    e.stackTrace = k.stackTrace;
                    e.errorMsg = k.errorMsg;
                    if (mini_debugger == true) {
                        alert(b + "\n" + e.textStatus + "\n" + e.stackTrace)
                    }
                    return
                }
                if (h.dataField) {
                    k = mini._getMap(h.dataField, k)
                }
                if (!k) {
                    k = []
                }
                d._listbox.setData(k);
                d.showPopup();
                d._listbox._focusItem(0, true);
                d.fire("load");
                d._loading = false;
                if (d._selectOnLoad) {
                    d.__doSelectValue();
                    d._selectOnLoad = null
                }
            },
            error: function(e, l, k) {
                d.showPopup("error")
            }
        });
        d._ajaxer = mini.ajax(f)
}; // mini.TextBoxList.prototype._doQuery结束

//定制：不能增加重复项
mini.TextBoxList.prototype.insertItem = function(a, b) {
		this.data = this.data || [];
		b = b || {}; //待增加的项
		for (var i=0; i<this.data.length; i++) {
			var obj = this.data[i];
			if (obj[this.valueField] == b[this.valueField] && obj[this.textField] == b[this.textField]) {
				//当前已存在该选择项
				//this.showPopup();
				return;
			}
		}
		if (!this.multiSelect && a > 0) {
			//单选，且已有选项时，不允许再新增
			return;
		}
        this.data.insert(a, b);
        var d = this.getText();
        var c = this.getValue();
        this.setValue(c, false);
        this.setText(d, false);
        this._createData();
        this.doUpdate();
        this.showInput(a + 1);
        this._OnValueChanged()
};

//定制：适用于Form.setValue从后台AJAX加载的数据
mini.TextBoxList.prototype.setValue = function(a) {
        if (mini.isNull(a)) {
            a = ""
        }
        if (this.value != a) {
        	//start custom
        	if (this.dictTypeId) {
        		if (!this._dictData) {
        			var d = this;
        			/*mini.ajax({
	                    url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
	                    data: {
	                        dictTypeId: this.dictTypeId
	                    },
	                    type: "POST",
	                    async: false,
	                    success: function(f) {
	                        var e = f.dictList || [];
	                        for (var i=0; i<e.length; i++) {
	                        	e[i][d.valueField] = e[i][d.valueField] || e[i].dictID;
	                        	e[i][d.textField] = e[i][d.textField] || e[i].dictName;
	                        }
	                        d._dictData = e;
	                    }
            		});*/
            		var e=mini.getDictData(this.dictTypeId);
            		for (var i=0; i<e.length; i++) {
                    	e[i][d.valueField] = e[i][d.valueField] || e[i].dictID;
                    	e[i][d.textField] = e[i][d.textField] || e[i].dictName;
                    }
                    d._dictData = e;
        		}
        		if (this._dictData) {
        			var e = this._dictData;
        			var arr = a.split(",");
        			var values = "", textes = "";
        			for (var i=0; i<e.length; i++) {
        				for (var j=0; j<arr.length; j++) {
        					if (e[i][this.valueField] == arr[j]) {
        						if (values.length > 0) {
        							values += ",";
        							textes += ",";
        						}
        						values += e[i][this.valueField];
        						textes += e[i][this.textField];
        					}
        				}
        			}
        			
    				this.value = values;
        			this._valueEl.value = values;
    				this.text = textes;
        			this._createData();
        			this.doUpdate();
        		}
        	}
        	//end custom
        	
            this.value = a;
            this._valueEl.value = a;
            this._createData();
            this.doUpdate()
        }
}
//字典表时，通过setValue清空text。
mini.TextBoxList.prototype.setText = function(a) {
        if (mini.isNull(a)) {
            a = ""
        }
        if (this.dictTypeId && !a) {
        	return;//字典表时，通过setValue清空。
        }
        if (this.text !== a) {
            this.text = a;
            this._createData();
            this.doUpdate()
        }
}
//----------TextBoxList 定制 end

mini.BoolCheckBox = function() {
    mini.BoolCheckBox.superclass.constructor.call(this)
};
mini.extend(mini.BoolCheckBox, mini.CheckBox, {
	trueValue: "1",
	falseValue: "0",
    uiCls: "mini-boolcheckbox"
});
mini.regClass(mini.BoolCheckBox, "boolcheckbox");


//----------Panel定制 start
mini.Panel.prototype.getAttrs = function(d) {
        var a = mini.Panel.superclass.getAttrs.call(this, d);
        mini._ParseString(d, a, ["title", "iconCls", "iconStyle", "headerCls", "headerStyle", "bodyCls", "bodyStyle", "footerCls", "footerStyle", 
        	"toolbarCls", "toolbarStyle", "footer", "toolbar", "url", "closeAction", "loadingMsg", "onbeforebuttonclick", "onbuttonclick", "onload",
        	"borderStyle"]); //新增对边框的控制
        mini._ParseBool(d, a, ["allowResize", "showCloseButton", "showHeader", "showToolbar", "showFooter", "showCollapseButton", 
        	"refreshOnExpand", "maskOnLoad", "expanded"]);
        var c = mini.getChildNodes(d, true);
        for (var b = c.length - 1; b >= 0; b--) {
            var e = c[b];
            var f = jQuery(e).attr("property");
            if (!f) {
                continue
            }
            f = f.toLowerCase();
            if (f == "toolbar") {
                a.toolbar = e
            } else {
                if (f == "footer") {
                    a.footer = e
                }
            }
        }
        a.body = c;
        return a
}
mini.Panel.prototype._create = function() {
        this.el = document.createElement("div");
        this.el.className = "mini-panel";
        var b = '<div class="mini-panel-border"><div class="mini-panel-header" ><div class="mini-panel-header-inner" ><span class="mini-panel-icon"></span><div class="mini-panel-title" ></div><div class="mini-tools" ></div></div></div><div class="mini-panel-viewport"><div class="mini-panel-toolbar"></div><div class="mini-panel-body" ></div><div class="mini-panel-footer"></div><div class="mini-resizer-trigger"></div></div></div>';
        this.el.innerHTML = b;
        this._borderEl = this.el.firstChild;
        this._headerEl = this._borderEl.firstChild;
        this._viewportEl = this._borderEl.lastChild;
        this._toolbarEl = mini.byClass("mini-panel-toolbar", this.el);
        this._bodyEl = mini.byClass("mini-panel-body", this.el);
        this._footerEl = mini.byClass("mini-panel-footer", this.el);
        this._resizeGridEl = mini.byClass("mini-resizer-trigger", this.el);
        var a = mini.byClass("mini-panel-header-inner", this.el);
        this._iconEl = mini.byClass("mini-panel-icon", this.el);
        this._titleEl = mini.byClass("mini-panel-title", this.el);
        this._toolsEl = mini.byClass("mini-tools", this.el);
        mini.setStyle(this._bodyEl, this.bodyStyle);
        mini.setStyle(this._borderEl, this.borderStyle);//新增对边框样式的控制
        this._doTitle()
}
//----------Panel定制 end

//----------ComboBox定制 start
mini.ComboBox.prototype.setValue = function(b) {
        if (this.value !== b) {
            var a = this._listbox.getValueAndText(b);
            this.value = b;
            this._valueEl.value = this.value;
            this.text = this._textEl.value = a[1];
            this._doEmpty();
            if(typeof this._OnValueChanged == 'function')this._OnValueChanged();
        } else {
            var a = this._listbox.getValueAndText(b);
            this.text = this._textEl.value = a[1]
        }
}
//----------ComboBox定制 end

//----------DictRadioGroup 定制 start
mini.DictRadioGroup.prototype.getAttrs= function(d) {
            var c = mini.DictRadioGroup.superclass.getAttrs.call(this, d);
            var e = jQuery(d);
            mini._ParseString(d, c, ["dictTypeId","filter","filterType","dValue"]);//defaultValue无法设置
            return c
};
mini.DictRadioGroup.prototype._setDictData= function(d) {
            this.setValueField(this.valueField);
            this.setTextField(this.textField);
            if (!this.filterType) {
            	this.filterType = 'idpre';
            }
            if (this.filter) {
	            var arr = [];
	            for (var i=0; i<d.length; i++) {
	            	if (this.filterType == 'idpre' && d[i][this.valueField].indexOf(this.filter) == 0) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            	if (this.filterType == 'idlen' && d[i][this.valueField].length == parseInt(this.filter)) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            }
	            d=arr;
	        }
            this.setData(d);
	        {//封停使用的不在下拉列表显示
	        	for (var i=0; i<d.length; i++) {
	        		if (d[i].status==0) {
	        			//已封停
	        			if (this.getItemEl(i))
		        			this.getItemEl(i).disabled=true;//不起作用啊！
	        			continue;
	        		}
	        	}
	        }
            
            if (this.value) {
                var c = this.value;
                this.value = "";
                this.setValue(c)
            }
}
//----------DictRadioGroup 定制 end

//----------DictCheckboxGroup 定制 start
mini.DictCheckboxGroup.prototype.getAttrs= function(d) {
            var c = mini.DictCheckboxGroup.superclass.getAttrs.call(this, d);
            var e = jQuery(d);
            mini._ParseString(d, c, ["dictTypeId","filter","filterType","dValue"]);//defaultValue无法设置
            return c
};
mini.DictCheckboxGroup.prototype._setDictData= function(d) {
            this.setValueField(this.valueField);
            this.setTextField(this.textField);
            if (!this.filterType) {
            	this.filterType = 'idpre';
            }
            if (this.filter) {
	            var arr = [];
	            for (var i=0; i<d.length; i++) {
	            	if (this.filterType == 'idpre' && d[i][this.valueField].indexOf(this.filter) == 0) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            	if (this.filterType == 'idlen' && d[i][this.valueField].length == parseInt(this.filter)) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            }
	            d=arr;
	        }
            this.setData(d);
	        {//封停使用的不在下拉列表显示
	        	for (var i=0; i<d.length; i++) {
	        		if (d[i].status==0) {
	        			//已封停
	        			if (this.getItemEl(i))
		        			this.getItemEl(i).disabled=true;//不起作用啊！
	        			continue;
	        		}
	        	}
	        }
            
            if (this.value) {
                var c = this.value;
                this.value = "";
                this.setValue(c)
            }
}
//----------DictCheckboxGroup 定制 end

//----------DictComboBox 定制 start
mini.DictComboBox.prototype.getAttrs= function(d) {
            var c = mini.DictComboBox.superclass.getAttrs.call(this, d);
            var e = jQuery(d);
            mini._ParseString(d, c, ["dictTypeId","filter","filterType","dValue"]);//defaultValue无法设置
            return c
}
mini.DictComboBox.prototype._afterApply = function(c) {
		mini.DictComboBox.superclass._afterApply.call(this, c);
		this._initData();
		if(!this.value && this.dValue)
			this.setValue(this.dValue);
}
mini.DictComboBox.prototype.setValue = function(b) {
		if (!b && this.dValue && (!this.value || this.value==this.dValue)) {
			b=this.dValue;//增加设置默认值
		}
        if (this.value !== b) {
            var a = this._listbox.getValueAndText(b);
            this.value = b;
            this._valueEl.value = this.value;
            this.text = this._textEl.value = a[1];
            this._doEmpty();
            if(typeof this._OnValueChanged == 'function')this._OnValueChanged();
        } else {
            var a = this._listbox.getValueAndText(b);
            this.text = this._textEl.value = a[1]
        }
        //console.log('==============xxx'+mini.encode(this.getSelected()));
        if (!this.getSelected()){ 
            this._listbox.select(this.getItem(0));
            //var a = this._listbox.getValueAndText('');
            this.text = this._textEl.value = '--请选择--';
            this._valueEl.value = this.value = '';
        }/* else if (!!this.getSelected() && this.getSelected()[this.valueField]==b) {
        	//选择默认值
            var a = this._listbox.getValueAndText(b);
        	//console.log(2 + ','+nui.encode(this.getItem(this.getSelected()))+'----'+a[1]);
            this.text = this._textEl.value = a[1];
            
            var d=this, t=a[1];
            setTimeout(function(){
            	d.setText(t);
            }, 100);
        }*/
}
//更换业务字典代码
mini.DictComboBox.prototype.changeDictTypeId= function(s) {
	if (!s) {
		this._setDictData([]);
		return;
	}
	this._setDictData(mini.getDictData(s));
}
mini.DictComboBox.prototype._setDictData= function(d) {
            this.setValueField(this.valueField);
            this.setTextField(this.textField);
            if (!this.filterType) {
            	this.filterType = 'idpre';
            }
            if (this.filter) {
	            var arr = [];
	            for (var i=0; i<d.length; i++) {
	            	if (this.filterType == 'idpre' && d[i][this.valueField].indexOf(this.filter) == 0) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            	if (this.filterType == 'idlen' && d[i][this.valueField].length == parseInt(this.filter)) {
	            		arr[arr.length] = d[i];
	            		continue;
	            	}
	            }
	            d=arr;
	        }
	        {//封停使用的不在下拉列表显示
	        	var arr = [];
	        	for (var i=0; i<d.length; i++) {
	        		if (d[i].status==0)
	        			continue;
	        		arr[arr.length] = d[i];
	        	}
	        	d=arr;
	        }
	        
	        if (!this.dValue)//无默认值时，增加请选择
	        if (d.length < 1 || !!d[0][this.valueField]) {
	        	//增加请选择
	        	for (var i=d.length; i>0; i--) {
	        		d[i]=d[i-1];
	        	}
	        	d[0]={};
	            d[0][this.valueField]="";
	            d[0][this.textField]="--请选择--";
	        }
            
            this.setData(d);
            
            if (!!this.value) {
                var c = this.value;
                this.value = "";
                this.setValue(c);
            } else {
            }
            //console.log('==============\n\n\n\n\n\n'+mini.encode(d));
            if (!this.getSelected()) {
            	this.select(0);
            	this._valueEl.value = this.value = '';
            }
}
//----------DictComboBox 定制 end

mini.ButtonEdit.prototype.setValue = function(b) {
	if (b === null || b === undefined) {
	    b = ""
	}
	var a = this.value !== b;
	this.value = b;
	this._valueEl.value = this.getFormValue();
	
	if (this.dictTypeId) {
		var text=getDictText(this.dictTypeId, b);
		if (text) 
			this.setText(text);
	}
	
	if(this.validate) {
		this.validate()
	}
}
mini.ButtonEdit.prototype.setText = function(b) {
        if (b === null || b === undefined) {
            b = ""
        }
        if (this.dictTypeId) {
			var text=git.map.org['id'+b];
			if (this.value == b && this.text == text) 
				return;
		}
        var a = this.text !== b;
        this.text = b;
        this._textEl.value = b;
        this._doEmpty()
}
mini.ButtonEdit.prototype.getAttrs= function(b) {
	var a = mini.ButtonEdit.superclass.getAttrs.call(this, b);
	var c = jQuery(b);
	mini._ParseString(b, a, ["value", "text", "textName", "emptyText", "inputStyle", "onenter", "onkeydown", "onkeyup", 
		"onkeypress", "onbuttonclick", "onbuttonmousedown", "ontextchanged", "onfocus", "onblur", "oncloseclick",
		"dictTypeId"]);
	mini._ParseBool(b, a, ["allowInput", "inputAsValue", "selectOnFocus", "showClose"]);
	mini._ParseInt(b, a, ["maxLength", "minLength"]);
	return a
}


//----------DataGrid定制 start
mini.DataGrid.prototype.setScrollBarPaddingAfterload = function() {
    var _this=this;
    //setTimeout(function() {
	    if ($(_this._rowsEl).width()-$(_this._rowsViewEl).width() == git.getScrollBarWidth()) {
	    	//标题行增加右边距
	    	//_this._columnsViewEl.style.marginRight=git.getScrollBarWidth()+'px';
	    	_this._columnsViewEl.style.paddingRight=git.getScrollBarWidth()+'px';
	    }
    //}, 1000);
}
if (!mini.DataGrid.prototype._set || typeof mini.DataGrid.prototype._set != 'function')
	mini.DataGrid.prototype._set=mini.DataGrid.prototype.set;
mini.DataGrid.prototype.set = function(e) {
	var d=this._set(e);
	
	//以下：自动设置未定义宽度的列宽度
	this.autoSetWidth(d);
	
	return d;
}
mini.DataGrid.prototype.hideColumn = function(a) {
    this.updateColumn(a, {
        visible: false
    });
    this.autoSetWidth();
}
mini.DataGrid.prototype.autoSetWidth = function(e) {
	var d=e||this;
	
	//以下：自动设置未定义宽度的列宽度
	//var cols=d._columnModel.columns;
	var cols=d.getVisibleColumns();
	//console.log('cols:'+mini.encode(cols));
	var totalWidth=$(d._columnsViewEl).width();
	var sum=0;//已有的宽度
	var cnt=0;//未定义宽度的列
	for(var i=0,len=cols.length; i<len; i++) {
		var col=cols[i];
		if (!col.width || col.width.length<1) {
			cnt++;
			continue;
		}
		if (col.isAutoSet===true) {//虽然设置了宽度，但该值是自动计算的
			cnt++;
			continue;
		}
		if (d._columnModel.isVisibleColumn(col) == false) {
			continue;
		}
		if (col.width.indexOf('%')>0) {
			sum += Math.floor(totalWidth*parseInt(col.width.replace('%',''))*1.0/100);
		} else if (col.width.indexOf('px')>0) {
			sum += parseInt(col.width.replace('px',''));
		} else {
			cnt=0;
			sum=totalWidth;
			break;
		}
	}
	if (cnt<1)
		return d;
	
	totalWidth = totalWidth - sum - 10;//如果总宽度稍大于表格宽度，则表头的其中一些列的边框可能被隐藏不显示。
	var scrollBarWidth= git.getScrollBarWidth();
	var width=Math.floor(totalWidth/cnt);
	var lastColWidth=width+totalWidth-width*cnt;
	if ($(d._columnsViewEl).width() < $(d._columnsViewEl.parentNode).width() - 5) { //有滚动条
		width=Math.floor((totalWidth-scrollBarWidth)/cnt);
		lastColWidth=Math.floor(width+totalWidth-scrollBarWidth-Math.floor(width*cnt));
	}
	//console.log('totalWidth:'+totalWidth);
	//console.log('width:'+width);
	//console.log('scrollBarWidth:'+scrollBarWidth);
	//console.log('lastColWidth:'+lastColWidth);
	var autoSetColWidthCnt=0;
	var ths=d._columnsViewEl.firstChild.firstChild.firstChild.childNodes;
	var tds=d._rowsViewEl.firstChild.firstChild.firstChild.firstChild.childNodes;
	//console.log('cnt,sum:'+cnt+','+sum);
	for(var i=0,len=cols.length; i<len; i++) {
		var col=cols[i];
		if (!!col.width && col.width.length>0 && col.isAutoSet !==true) {
			continue;
		}
		if (d._columnModel.isVisibleColumn(col) == false) {
			continue;
		}
		//console.log(i);
		autoSetColWidthCnt++;
		col.isAutoSet = true;
		if (autoSetColWidthCnt == cnt) {
			//最后一列
			col.width=lastColWidth+'px';
			$(ths[i]).width(lastColWidth);
			$(tds[i]).width(lastColWidth);
		} else {
			col.width=width+'px';
			$(ths[i]).width(width);
			$(tds[i]).width(width);
		}
	}
	
	return d;
}
//----------DataGrid定制 end
//----------DataGrid ScrollGridView FrozenGridView GridView定制 start
mini.DataGrid.superclass._OnDrawCell = mini.GridView.prototype._OnDrawCell = function(a, c, h, b) {
        var g = this._createDrawCellEvent(a, c, h, b);
        var f = g.value;
        if (c.dateFormat) {
            if (mini.isDate(g.value)) {
                g.cellHtml = mini.formatDate(f, c.dateFormat)
            } else if (mini.isDate(mini.parseDate(g.value))) {
                g.cellHtml = mini.formatDate(mini.parseDate(g.value), c.dateFormat)
            } else {
                g.cellHtml = f
            }
        }
        if (c.dataType == "currency") {
            g.cellHtml = mini.formatCurrency(g.value, c.currencyUnit)
        }
        if (c.dataType == "currency1") {
            g.cellHtml = mini.formatCurrencyNew(g.value, c.currencyUnit)
        }
        //定制
        if (c.dictTypeId) {
        	var f=getDictText(c.dictTypeId, f) || f;
        	g.cellHtml = f;//nui.alert(c.dictTypeId);
        }
        //定制
        if (c.displayField) {
            g.cellHtml = a[c.displayField]
        }
        if (g.autoEscape == true) {
            g.cellHtml = mini.htmlEncode(g.cellHtml)
        }
        var d = c.renderer;
        if (d) {
            fn = typeof d == "function" ? d: mini._getFunctoin(d);
            if (fn) {
                g.cellHtml = fn.call(c, g)
            }
        }
        this.fire("drawcell", g);
        if (g.cellHtml && !!g.cellHtml.unshift && g.cellHtml.length == 0) {
            g.cellHtml = "&nbsp;"
        }
        if (g.cellHtml === null || g.cellHtml === undefined || g.cellHtml === "") {
            g.cellHtml = "&nbsp;"
        }
        return g
}
mini.ScrollGridView.superclass._createRowsHTML = mini.GridView.prototype._createRowsHTML = function(a, n, d, i) {
        d = d || this.getVisibleRows();
        var h = ['<table class="mini-grid-table" cellspacing="0" cellpadding="0" border="0">'];
        h.push(this._createTopRowHTML(a));
        var l = this.uid + "$emptytext" + n;
        h.push('<tr id="' + l + '" style="display:none;"><td class="mini-grid-emptyText" colspan="' + a.length + '">' + this.emptyText + "</td></tr>");
        var m = 0;
        if (d.length > 0) {
            var f = d[0];
            m = this.getVisibleRows().indexOf(f)
        }
        //定制开始
        //d表示行数据记录
        var cols=this.getColumns() || [];//列格式
        var orgs = [];
        var users = [];
        var products = [];
        var batchOrgs=[];
        var acctOrgs=[];
        for (var idx=0,len=cols.length; idx<len; idx++) {
        	var col = cols[idx];
        	if (col.dictTypeId == 'org') {
        		for (var j=0; j<d.length; j++) {
        			var orgid=d[j][col.field];
        			if ($.inArray(orgid, orgs) >= 0 || !orgid || git.map.org['id'+orgid])
        				continue;
        			orgs[orgs.length]=orgid;
        		}
        		continue;
        	}
        	if (col.dictTypeId == 'batchOrg') {
        		for (var j=0; j<d.length; j++) {
        			var orgid=d[j][col.field];
        			if ($.inArray(orgid, batchOrgs) >= 0 || !orgid || git.map.batchOrg['id'+orgid])
        				continue;
        			batchOrgs[batchOrgs.length]=orgid;
        		}
        		continue;
        	}
        	if (col.dictTypeId == 'user') {
        		for (var j=0; j<d.length; j++) {
        			var userid=d[j][col.field];
        			if ($.inArray(userid, users) >= 0 || !userid || git.map.user['id'+userid])
        				continue;
        			users[users.length]=userid;
        		}
        		continue;
        	}
        	if (col.dictTypeId == 'product') {
        		for (var j=0; j<d.length; j++) {
        			var productid=d[j][col.field];
        			if ($.inArray(productid, products) >= 0 || !productid || git.map.product['id'+productid])
        				continue;
        			products[products.length]=productid;
        		}
        		continue;
        	}
        	if (col.dictTypeId == 'acctOrg') {
        		for (var j=0; j<d.length; j++) {
        			var acctOrgId=d[j][col.field];
        			if ($.inArray(acctOrgId, acctOrgs) >= 0 || !acctOrgId || git.map.acctOrg['id'+acctOrgId])
        				continue;
        			acctOrgs[acctOrgs.length]=acctOrgId;
        		}
        		continue;
        	}
        }
        if (orgs.length > 0) {
        	var orgids=orgs.join(',');//alert(orgids);
        	if (orgids)
        		git.getOrgsInfo(orgids);
        }
          if (batchOrgs.length > 0) {
        	var Borgids=batchOrgs.join(',');//alert(orgids);
        	if (Borgids)
        		git.getBatchOrgsInfo(Borgids);
        }
        if (users.length > 0) {
        	var userids=users.join(',');
        	if (userids)
        		git.getUsersInfo(userids);
        }
        if (products.length > 0) {
        	var productids=products.join(',');
        	if (productids)
        		git.getProductsInfo(productids);
        }
        if (acctOrgs.length > 0) {
        	var acctOrgs=acctOrgs.join(',');
        	if (acctOrgs)
        		git.getAcctOrgsInfo(acctOrgs);
        }
        //定制结束
        for (var c = 0,
        b = d.length; c < b; c++) {
            var g = m + c;
            var e = d[c];
            this._createRowHTML(e, g, a, n, h)
        }
        if (i) {
            h.push(i)
        }
        h.push("</table>");
        return h.join("")
}

mini.DataGrid.prototype.getCellEditor = function(b, c) {
        b = this.getColumn(b);
        if (!b) {
            return
        }
        if (this.allowCellEdit) {
            var a = b.__editor;
            if (!a) {
                a = mini.getAndCreate(b.editor)
            }
            if (a && a != b.editor) {
                b.editor = a
            }
            return a
        } else {
            c = this.getRow(c);
            b = this.getColumn(b);
            if (!c) {
                c = this.getEditingRow()
            }
            if (!c || !b) {
                return null
            }
            var d = this.uid + "$" + c._uid + "$" + b._id + "$editor";
            return mini.get(d)
        }
}

mini.ColumnModel.prototype._getDataTypeByField = function(e) {
    var f = "string";
    var c = this.getBottomColumns();
    for (var b = 0,
    a = c.length; b < a; b++) {
        var d = c[b];
        if (d.field == e) {
            if (d.dataType) {
                f = d.dataType.toLowerCase()
            } else {
            	if(d.dateFormat) {
            		f = "date";
            	}
            }
            break
        }
    }
    return f
}

//增加解析dictTypeId属性
mini._ParseColumns = function(el) {
    var columns = [];
    var cs = mini.getChildNodes(el);
    for (var i = 0,
    l = cs.length; i < l; i++) {
        var node = cs[i];
        var jq = jQuery(node);
        var column = {};
        var editor = null,
        filter = null;
        var subCs = mini.getChildNodes(node);
        if (subCs) {
            for (var ii = 0,
            li = subCs.length; ii < li; ii++) {
                var subNode = subCs[ii];
                var property = jQuery(subNode).attr("property");
                if (!property) {
                    continue
                }
                property = property.toLowerCase();
                if (property == "columns") {
                    column.columns = mini._ParseColumns(subNode);
                    jQuery(subNode).remove()
                }
                if (property == "editor" || property == "filter") {
                    var className = subNode.className;
                    var classes = className.split(" ");
                    for (var i3 = 0,
                    l3 = classes.length; i3 < l3; i3++) {
                        var cls = classes[i3];
                        var clazz = mini.getClassByUICls(cls);
                        if (clazz) {
                            var ui = new clazz();
                            if (property == "filter") {
                                filter = ui.getAttrs(subNode);
                                filter.type = ui.type
                            } else {
                                editor = ui.getAttrs(subNode);
                                editor.type = ui.type
                            }
                            break
                        }
                    }
                    jQuery(subNode).remove()
                }
            }
        }
        column.header = node.innerHTML;
        mini._ParseString(node, column, ["name", "header", "field", "editor", "filter", "renderer", "width", "type", "renderer", "headerAlign", 
        	"align", "headerCls", "cellCls", "headerStyle", "cellStyle", "displayField", "dateFormat", "listFormat", "mapFormat", "trueValue", 
        	"falseValue", "dataType", "vtype", "currencyUnit", "summaryType", "summaryRenderer", "groupSummaryType", "groupSummaryRenderer", 
        	"defaultValue", "defaultText", "decimalPlaces", "data-options",
        	"dictTypeId"]);
        mini._ParseBool(node, column, ["visible", "readOnly", "allowSort", "allowResize", "allowMove", "allowDrag", "autoShowPopup", "unique", "autoEscape"]);
        if (editor) {
            column.editor = editor
        }
        if (filter) {
            column.filter = filter
        }
        if (column.dataType) {
            column.dataType = column.dataType.toLowerCase()
        }
        if (column.defaultValue === "true") {
            column.defaultValue = true
        }
        if (column.defaultValue === "false") {
            column.defaultValue = false
        }
        columns.push(column);
        var options = column["data-options"];
        if (options) {
            options = eval("(" + options + ")");
            if (options) {
                mini.copyTo(column, options)
            }
        }
    }
    return columns
};
//----------DataGrid ScrollGridView FrozenGridView GridView定制 end

//----------mini.Pager更换页面大小时查询3次后台的bug修改 start
mini.Pager.prototype._initEvents=function() {
        mini.Pager.superclass._initEvents.call(this);
        this.firstButton.on("click",
        function(c) {
            this._OnPageChanged(0)
        },
        this);
        this.prevButton.on("click",
        function(c) {
            this._OnPageChanged(this.pageIndex - 1)
        },
        this);
        this.nextButton.on("click",
        function(c) {
            this._OnPageChanged(this.pageIndex + 1)
        },
        this);
        this.lastButton.on("click",
        function(c) {
            this._OnPageChanged(this.totalPage)
        },
        this);
        this.reloadButton.on("click",
        function(c) {
            this._OnPageChanged()
        },
        this);
        function a() {
            if (b) {
                return
            }
            b = true;
            var c = parseInt(this.numInput.value);
            if (isNaN(c)) {
                this.update()
            } else {
                this._OnPageChanged(c - 1)
            }
            setTimeout(function() {
                b = false
            },
            100)
        }
        var b = false;
        mini.on(this.numInput, "change",
        function(c) {
            a.call(this)
        },
        this);
        mini.on(this.numInput, "keydown",
        function(c) {
            if (c.keyCode == 13) {
                a.call(this);
                c.stopPropagation()
            }
        },
        this);
        this.sizeCombo.on("itemclick", this.__OnPageSelectChanged, this);
        //原来为valuechanged，改为itemclick，因为itemclick会触发valuechanged
};
//----------mini.Pager更换页面大小时查询3次后台的bug修改 end

window.git = window.git || {};
git.isOpera = Object.prototype.toString.call(window.opera) == "[object Opera]";
git.isIE = !!window.attachEvent && !git.isOpera;
git.isIE10 = git.isIE && document.documentMode == 10;
git.go = function(url, ifr) {
	if (url.indexOf("/") == 0 && url.indexOf(nui.context) != 0)
		url = nui.context + url;
	
	if (ifr) {
		if (typeof ifr == "string") {
			ifr = document.getElementById(ifr);
		}
		if (ifr && ifr.contentWindow && ifr.contentWindow.location) {
			ifr.contentWindow.location.replace(url);
			return;
		}
		if (ifr && ifr.location) {
			ifr.location.replace(url);
			return;
		}
	}
	window.location.replace(url);
}

/*回首页【我的工作：待办列表】*/
git.gohome = function() {
	//top.location.href="com.bos.utp.auth.Login.flow";
	top.location.replace(nui.context+"/com.bos.utp.auth.Login.flow");
}

/*计算规则表达式，str为规则前置参数的var定义语句，expr为规则表达式*/
git.ruleEval = function(str, expr) {
	str = "var math=Math;" + (str||"");
	var arr=expr.split('\n');
	for (var i=0; i<arr.length; i++) {
		if (!arr[i])
			continue;
		
		var pos = arr[i].indexOf("def ");
		if (pos == 0) {
			var tmp = arr[i].indexOf("=");
			var key = arr[i].substr(4, tmp - pos).trim();
			var value = eval(str + arr[i].substr(tmp));
			str += "var " + key + "=" + value + ";";
			continue;
		}
		pos = arr[i].indexOf("定义：");
		if (pos == 0) {
			var tmp = arr[i].indexOf("=");
			var key = arr[i].substr(4, tmp - pos).trim();
			var value = eval(str + arr[i].substr(tmp));
			str += "var " + key + "=" + value + ";";
			continue;
		}
		var result = eval(str + arr[i]);
		if (result)
			return result;
	}
}

/*获取机构、产品、用户信息*/
top.git = top.git || {};
top.git.map = top.git.map || {};
git.map = top.git.map;
git.map.dict = git.map.dict || {};
git.map.org = git.map.org || {};
git.map.batchOrg = git.map.batchOrg || {};
git.map.acctOrg = git.map.acctOrg || {};
git.map.user = git.map.user || {};
git.map.product = git.map.product || {};
git.map.orguser = git.map.orguser || {};
git.getOrgsInfo = function(orgids) {
	if (!orgids)
		return;
	var result=null;
	mini.ajax({
        url: "com.primeton.components.nui.DictLoader2.getOrgInfo.biz.ext",
        data: {
            "orgids": orgids
        },
        type: "POST",
        async: false,
        success: function(f) {
            var e = f.orgs || [];
            for (var i=0; i<e.length; i++) {
            	git.map.org['id'+e[i].orgid]=e[i].orgname;
            	git.map.org['id'+e[i].orgcode]=e[i].orgname;
            	git.map.org['idorgcode'+e[i].orgid]=e[i].orgcode;
            	git.map.org['idorg'+e[i].orgid]=e[i];
            }
         
            result=e;
            return e;
        }
	});
	//alert(nui.encode(result));
}
//出账机构中文反显方法
git.getBatchOrgsInfo = function(orgids) {
	if (!orgids)
		return;
	var result=null;
	mini.ajax({
        url: "com.primeton.components.nui.DictLoader2.getBatchOrgInfo.biz.ext",
        data: {
            "orgids": orgids
        },
        type: "POST",
        async: false,
        success: function(f) {
            var e = f.batchOrgs || [];
            for (var i=0; i<e.length; i++) {
            	git.map.batchOrg['id'+e[i].branchCode]=e[i].branchCnAbbrName;
            	git.map.batchOrg['idorg'+e[i].branchCnAbbrName]=e[i];
            }
            result=e;
            return e;
        }
	});
}
//会计机构中文反显方法
git.getAcctOrgsInfo = function(orgids) {
	if (!orgids)
		return;
	var result=null;
	mini.ajax({
        url: "com.primeton.components.nui.DictLoader2.getAcctOrgInfo.biz.ext",
        data: {
            "orgids": orgids
        },
        type: "POST",
        async: false,
        success: function(f) {
            var e = f.acctOrgs || [];
            for (var i=0; i<e.length; i++) {
            	git.map.acctOrg['id'+e[i].orgNum]=e[i].orgName;
            	git.map.acctOrg['idorg'+e[i].orgName]=e[i];
            }
            result=e;
            return e;
        }
	});
}


git.getOrg = function(orgid) {
	if (git.map.org['idorg'+orgid])
		return git.map.org['idorg'+orgid];
	git.getOrgsInfo(orgid);//alert('---'+orgid);
	return git.map.org['idorg'+orgid];
}
git.getOrgName = function(orgid) {
	if (git.map.org['id'+orgid])
		return git.map.org['id'+orgid];
	git.getOrgsInfo(orgid);//alert('---'+orgid);
	return git.map.org['id'+orgid];
}
//出账机构中文名称反显
git.getBatchOrgName = function(orgid) {
	if (git.map.batchOrg['id'+orgid])
		return git.map.batchOrg['id'+orgid];
	git.getBatchOrgsInfo(orgid);//alert('---'+orgid);
	return git.map.batchOrg['id'+orgid];
}
//会计机构中文名称反显
git.getAcctOrgName = function(orgid) {
	var name = git.map.acctOrg['id'+orgid];
	if (name)
		return name;
	git.getAcctOrgsInfo(orgid);//alert('---'+orgid);
	return git.map.acctOrg['id'+orgid];
}
//通过orgId获得真实的机构号
git.getOrgCode = function(orgid){
	if (git.map.org['idorgcode'+orgid])
		return git.map.org['idorgcode'+orgid];
	git.getOrgsInfo(orgid);
	return git.map.org['idorgcode'+orgid];
}
git.getUsersInfo = function(userids) {
	if (!userids)
		return;
	mini.ajax({
        url: "com.primeton.components.nui.DictLoader2.getUserInfo.biz.ext",
        data: {
            "userids": userids
        },
        type: "POST",
        async: false,
        success: function(f) {
            var e = f.users || [];
            for (var i=0; i<e.length; i++) {
            	git.map.user['id'+e[i].operatorid]=e[i].empname;
            	git.map.user['id'+e[i].userid]=e[i].empname;
            }
            return e;
        }
	});
}
git.getUserName = function(userid) {
	if (git.map.user['id'+userid])
		return git.map.user['id'+userid];
	git.getUsersInfo(userid);
	return git.map.user['id'+userid];
}
git.getProductsInfo = function(productids) {
	if (!productids)
		return;
	mini.ajax({
        url: "com.primeton.components.nui.DictLoader2.getProductInfo.biz.ext",
        data: {
            "productids": productids
        },
        type: "POST",
        async: false,
        success: function(f) {
            var e = f.products || [];
            for (var i=0; i<e.length; i++) {
            	git.map.product['id'+e[i].productId]=e[i].productName;
            	git.map.product['id'+e[i].productCd]=e[i].productName;
            }
            return e;
        }
	});
}
git.getProductName = function(productid) {
	if (git.map.product['id'+productid])
		return git.map.product['id'+productid];
	git.getProductsInfo(productid);
	return git.map.product['id'+productid];
}

//根据机构号和角色编号去查询该机构下对应的操作人员
git.getUserNameByOrgIdRoles=function(orgId,roles,func){
	mini.ajax({
		url:"com.bos.pub.organization.getUserNameByOrgIdRoles.biz.ext",
		data:{"orgId":orgId,"roles":roles},
		type:"POST",
		cache:false,
		async:false,
		success:function(f){
		 if(typeof func == 'function')func(f);
		}
	});
}

//调用逻辑流并返回结果：json表示传入参数，biz表示逻辑流名称（带biz.ext后缀），func为回调函数
git.getByBizLogic = function(biz, json, func) {
	debugger;
	mini.ajax({
        url: biz,
        data: json,
        type: "POST",
        success: function(f) {
            if(typeof func == 'function')func(f);
        }
	});
}
//调用逻辑流并返回结果：json表示传入参数，biz表示逻辑流名称（带biz.ext后缀），func为回调函数
git.getByBizLogicEcif = function(biz, json, func) {
	debugger;
	mini.ajax({
        url: biz,
        data: json,
        type: "POST",
        success: function(f) {
            if(typeof func == 'function')func(f);
        }
	});
}
//根据上级编号获取行政区划
git.getDistrictsByParentid = function(parentid,func) {
	debugger;
	if (!parentid)
		return;
	git.getByBizLogic('com.primeton.components.nui.DictLoader2.getDistrictsByParentCd.biz.ext', 
		nui.encode({"parentid":parentid}), func);
}
//根据上级编号获取行政区划
git.getDistrictsByParentidEcif = function(parentid,func) {
	debugger;
	if (!parentid)
		return;
	git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictsByParentCdEcif.biz.ext', 
		nui.encode({"parentid":parentid}), func);
}
//根据上级编号获取行政区划
git.setDistrictsByIds = function(ids) {
	var arr=ids.split(',');
	var dictids = '';
	for (var i=0; i<arr.length; i++) {
		var id=arr[i];
		var obj = nui.get(id);
		if (!obj)
			continue;
		var val = obj.getValue();
		if (!val)
			continue;
		if (dictids.length > 0)
			dictids += ',';
		dictids += val;
	};
	git.getByBizLogic('com.primeton.components.nui.DictLoader2.getDistrictsByIds.biz.ext', 
		nui.encode({"dictids":dictids}), function(data){
			var arr=ids.split(',');
			var items=data.items || [];
			for (var i=0; i<arr.length; i++) {
				var id=arr[i];
				var obj = nui.get(id);
				if (!obj)
					continue;
				
				var val=obj.getValue();//alert(val);alert(nui.encode(items));
				for (var j=0; j<items.length; j++) {
					if (items[j].dictid == val) {
						var text = items[j].dictname;
						obj.setText(text);
						break;
					}
				}
			}
		});
}

//将字符串中的中文转义为url字符串
git.toUrlParam = function(str) {
	str = str || "";
	return encodeURI(str);
}
//页面遮罩
git.mask = function(el) {
	mini.mask({el:el||document.body,html:'<div style="height: 50px; width: 100px;background:#ffffff;border:#176371 2px solid;"><span style="height: 30px; margin-top: 10px;"><img src="'+nui.context+'/common/nui/themes/default/images/grid/loading.gif"/> &nbsp;&nbsp;loading...</span></div>'});
}
git.unmask = function(el) {
	mini.unmask(el||document.body);
}
git.getScrollBarWidth = function() {
	if (git.scrollBarWidth && git.scrollBarWidth>0)
		return git.scrollBarWidth;
	var d=document.createElement('div');
	d.style.height='20px';
	d.style.width='30px';
	d.style.overflowY='auto';
	d.style.overflowX='auto';
	d.innerHTML='中国中国中国中国中国';
	document.body.appendChild(d);
	if (d.offsetWidth-d.clientWidth > 5)
		git.scrollBarWidth=d.offsetWidth-d.clientWidth;
	else
		git.scrollBarWidth=17;
	$(d).remove();
	return git.scrollBarWidth;
}

git._doParse = function (el) {
    if (!el) return;
    var nodeName = el.nodeName.toLowerCase();
    if (!nodeName) return;
    var className = el.className;
    if (className) {
        var control = mini.get(el);        
        if (!control) {
            var classes = className.split(" ");
            for (var i = 0, l = classes.length; i < l; i++) {
                var cls = classes[i];
                var clazz = mini.getClassByUICls(cls);
                if (clazz) {
                    mini.removeClass(el, cls);
                    var ui = new clazz();
                    
                    mini.applyTo.call(ui, el);
                    el = ui.el;
                    break;
                }
            }
        }
    }

    if (nodeName == "select"
            || mini.hasClass(el, "mini-menu")
            || mini.hasClass(el, "mini-datagrid")
            || mini.hasClass(el, "mini-treegrid")
            || mini.hasClass(el, "mini-tree")
            || mini.hasClass(el, "mini-button")
            || mini.hasClass(el, "mini-textbox")
            || mini.hasClass(el, "mini-buttonedit")
        ) {
        return;
    }

    var children = mini.getChildNodes(el, true);
    for (var i = 0, l = children.length; i < l; i++) {
        var node = children[i];
        if (node.nodeType == 1) {
            if (node.parentNode == el) {
                mini._doParse(node);
            }
        }
    }
}

/**
 * 过滤字典选项，以获取到想要的字典项。
 * dictId:字典编号
 * str:过滤值（想要留下来的值，多个用，分隔）
 */
git.getDictDataFilter=function(dictId,str){
	//返回值
	var arr =[];
	//从缓存中获取字典项
	var  dict = nui.getDictData(dictId);
	//数组下标
	j=0;
	//循环字典项，将要留下来的值，赋至数组，返回
	for(var i=0;i<dict.length;i++){
	
		var item = dict[i];
		if(str.indexOf(item.dictID)!='-1'){
			arr[j]=item;
			j++;
		}
	}
	return arr;
}
/**
 * cc 2016-05-04
 * 过滤字典选项，以获取到想要的字典项。
 * dictId:字典编号
 * str:过滤值（想要过滤掉的值，多个用，分隔）
 */
git.getDictDataUnFilter=function(dictId,str){
	//返回值
	var arr =[];
	//从缓存中获取字典项
	var  dict = nui.getDictData(dictId);
	//数组下标
	j=0;
	//循环字典项，将要留下来的值，赋至数组，返回
	for(var i=0;i<dict.length;i++){
		var item = dict[i];
		if(str.indexOf(item.dictID)=='-1'){
			arr[j]=item;
			j++;
		}
	}
	return arr;
}

/**
 * 根据产品编号，初始化产品页面控件
 * productId:产品编号
 * paraType:参数类型（1：业务申请，2：合同签订，3：出账放款）
 */

git.initParamOfProduct=function(productId,paraType){
	var json =nui.encode({"productId":productId,"paraType":paraType});
	git.exeAjax("com.bos.pub.productParam.queryParamByProductId.biz.ext",json,function(text){
		
		items = text.items;
		if(null!=items && items.length>0){
			for(var i=0;i<items.length;i++){
				var item = items[i];
				if('include'==item.paraCountSign){
					
					if(null == nui.get(item.paraColumn) ||'' == nui.get(item.paraColumn) || 'undefined' == nui.get(item.paraColumn)){
						continue;
					}else{
						
						nui.get(item.paraColumn).setData(git.getDictDataFilter(item.paraContrlLeftval,item.paraContrlRigthval));
					}
				}else if('unclude'==item.paraCountSign){
					if(null == nui.get(item.paraColumn) ||'' == nui.get(item.paraColumn) || 'undefined' == nui.get(item.paraColumn)){
						continue;
					}else{
						
						nui.get(item.paraColumn).setData(git.getDictDataUnFilter(item.paraContrlLeftval,item.paraContrlRigthval));
					}
				}
			}
		}
	});
}

/**
 * 配合初始化产品控件，如果参数只有一个值时，则默认，并灰显
 * 这个只能在页面初始化完成后调用， 不然会被覆盖掉
 * name:控件名称
 * val:参数值
 */
git.setPtDefaultValue=function(){
	
		if(null!=items && items.length>0){
			for(var i=0;i<items.length;i++){
				var item = items[i];
				if('unclude'==item.paraCountSign||'include'==item.paraCountSign){
					
					if(null == nui.get(item.paraColumn) ||'' == nui.get(item.paraColumn) || 'undefined' == nui.get(item.paraColumn)){
						continue;
					}else{
						var _val = nui.get(item.paraColumn).getValue();
						if(null==_val || ''==_val){//只有当没值时，才可以默认
							var val =item.paraContrlRigthval;
							if(null != val){
								if(val.indexOf(",")==-1){
									nui.get(item.paraColumn).setValue(item.paraContrlRigthval);
									nui.get(item.paraColumn).setEnabled(false);
								}
							}	
						}else{
							var val =item.paraContrlRigthval;
							if(null != val){
								if(val.indexOf(",")==-1){
									nui.get(item.paraColumn).setEnabled(false);
								}
							}	
						}
					}
				}
			}
		}
}

/**
 * 根据产品编号，校验产品页面控件
 * productId:产品编号
 * paraType:参数类型（1：业务申请，2：合同签订，3：出账放款）
 */
git.validParamOfProduct=function(productId,paraType){
	var json =nui.encode({"productId":productId,"paraType":paraType});
	git.exeAjax("com.bos.pub.productParam.queryParamByProductId.biz.ext",json,function(text){
		
		var items = text.items;
		if(null!=items && items.length>0){
			for(var i=0;i<items.length;i++){
				var item = items[i];
				//该字段不存在，则不处理
				if(null ==nui.get(item.paraColumn) || 'undefined' == nui.get(item.paraColumn)){
					
					continue;
				}
				//字段里没有值，则不处理
				if(null == nui.get(item.paraColumn).getValue() ||'' == nui.get(item.paraColumn).getValue()){
					
					continue;
				}
				var val = nui.get(item.paraColumn).getValue();
				var json = {"paraName":item.paraColunmName,"targetVal":item.paraContrlLeftval,"paraColumn":val};
				if('='==item.paraCountSign){
					
			   	   	var msg = exeRule("PUB_0023","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
					
				}else if('>'==item.paraCountSign){
					
					var msg = exeRule("PUB_0024","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
			   	    
				}else if('<'==item.paraCountSign){

					var msg = exeRule("PUB_0025","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
					
				}else if('>='==item.paraCountSign){
					
					var msg = exeRule("PUB_0026","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
					
				}else if('<='==item.paraCountSign){
					
					var msg = exeRule("PUB_0027","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
					
				}else if('between'==item.paraCountSign){
					
					json["startVal"] = item.paraContrlLeftval;
					json["endVal"] = item.paraContrlRigthval;
					var msg = exeRule("PUB_0028","1",json);
			   	    if(null != msg && '' != msg){
						nui.get(item.paraColumn).errorText=msg;
						nui.get(item.paraColumn).setIsValid(false);
					}
				}
			}
		}
	});
}

/**
 * 根据产品编号，初始化产品页面控件
 * url: 请求路径
 * json: 传入参数
 * callback :回调函数
 */
git.exeAjax=function(url,json,callback){
	$.ajax({
        url: url,
        type: 'POST',
        data: json,
        cache: false,
        async: false,
        contentType:'text/json',
        success: function (text) {
        	if(typeof callback == "function"){
                callback(text);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}

//弹出审批意见页面
function openSubmitView(node){
	var url = nui.context + "/bps/mywork/work_flow_advice.jsp?processInstId="+node.processInstId+"&processDefName="+node.processDefName;
		url+="&activityDefId="+node.activityDefId+"&activityInstId="+node.activityInstId+"&activityInstName="+git.toUrlParam(node.activityInstName);
		url+="&workItemId="+node.workItemId+"&ruleID="+node.ruleID+"&selectType="+node.selectType+"&conclusion="+git.toUrlParam(node.conclusion);
		url+="&isSrc=1&workitemMappingId="+node.workitemMappingId+"&templateVersion="+node.templateVersion+"&startTime="+node.startTime+"&doUrl="+node.doUrl;;
	nui.open({
               url: url,
               title: "提交审批", 
               width: 550, 
               height: 260,
               onload: function () {
                   //var iframe = this.getIFrameEl();
                   //var data = {action: "submit"};
                   //iframe.contentWindow.SetData(data);
               },
               ondestroy: function (action) {
               	 if("submit" == action){
               	 	git.gohome();
               	 }
               }
           });

}

/**
 * 执行规则或规则组
 * ruleId：规则或规则组ID
 * etype: 1代表规则，2代表规则组
 * paraMap：执行规则所需参数：如var  json={"a":1,"b":2};
 */
function exeRule(ruleId,etype,paraMap) {
	var msg =null;
	var json=nui.encode({"rid":ruleId,"eType":etype,"paraMap":paraMap});
	$.ajax({
            url: "com.bos.rule.ruleMgr.runRule.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (text) {
            	if(null != text.msg){
            		msg =  text.msg;
            	} 
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
	return msg;
}

//业务数据信息查看
function bizView3231(bizNum){
	var changeId = "";
	if(arguments.length>1)changeId = arguments[1];
	var json = nui.encode({"bizNum":bizNum});
	var jspName;
	$.ajax({
        url: "com.bos.bizPro.BizView.getBizView.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
			var o = nui.decode(mydata);
			if(""==o.bizId || null==o.bizId){
				return;
			}
			if("BIZ" == o.bizFlag){
				jspName="/biz/biz_info/biz_tree.jsp?applyId="+o.bizId+"&processInstId=0&proFlag=-1&changeId="+changeId;
			}
			if("CON" == o.bizFlag){
				jspName="/crt/con_info/con_tree.jsp?contractId="+o.bizId+"&processInstId=0&proFlag=-1";
			}
			if("LOAN" == o.bizFlag){
				jspName="/pay/payout_info/pay_tree.jsp?loanId="+o.bizId+"&bizNum="+bizNum+"&processInstId=0&proFlag=-1";
			}
			if("CONSUB01" == o.bizFlag){
				  jspName="/crt/con_grt/con_dy_tab.jsp?subcontractId="+o.bizId+"&view=1";
			}
			if("CONSUB02" == o.bizFlag){
				 jspName="/crt/con_grt/con_zy_tab.jsp?subcontractId="+o.bizId+"&view=1";
			}
			if("CONSUB03" == o.bizFlag){
				 jspName="/crt/con_grt/con_bzj_tab.jsp?subcontractId="+o.bizId+"&view=1";
			}
			if("CONSUB04" == o.bizFlag){
				jspName="/crt/con_grt/con_bzr_tab.jsp?subcontractId="+o.bizId+"&view=1";
			}
			if("GRT" == o.bizFlag){
				jspName="/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+o.bizId+"&view=1&isManage=1&collType="+o.collType+"&sortType="+o.sortType;
			}
			nui.open({
				url:nui.context + jspName,
				showMaxButton:true,
				title:"查看",
				width: 1024,
	            height: 768,
	            state:"max",
	            ondestroy: function(e) {
	            }
			});
		}
	});
}

//根据参与者ID，跟转到客户详细页面
function toGoCustDetail(partyId){
	
	var json=nui.encode({"partyId":partyId});
	$.ajax({
            url: "com.bos.pub.common.getCustType.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (text) {
            	if(null != text.item){
            		var url;
            		var partyTypeCd = text.item.partyTypeCd;
            		partyId = text.item.partyId;
            		var partyNum = text.item.partyNum;
            		var custType  = text.item.custType;
            		var naturalType = text.item.naturalType;
            		//对公客户
            		if("01"==partyTypeCd){
            			url = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="+partyId+"&qote=1&cusType="+custType+"&partyNum="+partyNum;
            		}else if("02"==partyTypeCd){
            			//自然人
        				url = nui.context +  "/csm/natural/natural_tree.jsp?partyId="+partyId+"&qote=1&partyNum="+partyNum;;
//            			if("1"==naturalType){
//            				url = nui.context +  "/csm/natural/natural_tree.jsp?partyId="+partyId+"&qote=1&partyNum="+partyNum;;
//            			}else{
//            				//小贷
//            				url=nui.context +  "/csm/loan/loan_tree.jsp?partyId="+partyId+"&qote=1&partyNum="+partyNum;;
//            			}
            		}else if("04"==partyTypeCd){
            			
            			url= nui.context + "/csm/guar/guarGroup_tree.jsp?partyId="+partyId + "&qote=1&wflow=1&partyNum=" + partyNum;

            		}else if("05"==partyTypeCd){
            			
            			url = nui.context + "/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="+ partyId+"&qote=1&partyNum="+partyNum;

            		}else if("06"==partyTypeCd){
            			
            			url = nui.context + "/csm/company/groupCompany_tree.jsp?partyId="+partyId + "&qote=1&partyNum=" + partyNum;

            		}else if("07"==partyTypeCd){
            			
            			url = nui.context+ "/csm/thirdParty/csm_thirdParty_tree.jsp?partyId="+partyId + "&qote=1";
            		}
            		
            		//弹出页面
            		nui.open({
        	            url: url,
        	            showMaxButton: true,
        	            title: "查看客户信息",
        	            width: 1024,
        	            height: 768,
        	            state:"max",
        	            onload: function(e){
        	            	var iframe = this.getIFrameEl();
        	            	var text = iframe.contentWindow.document.body.innerText;
        	            	//alert(text);
        	            },
        	            ondestroy: function (action) {
        	                //queryInit();
        	            }
              	 	 });
            	} 
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
	
}
//查看评级结果
function viewGeneralAdjustRatingCd(iraApplyId){
	//弹出页面
	nui.open({
        url: nui.context + "/irm/financialCustom/financial_view_report_jj.jsp?bizId="+iraApplyId,
        showMaxButton: true,
        title: "查看评级结果",
        width: 1024,
        height: 768,
        state:"max",
        onload: function(e){
        	var iframe = this.getIFrameEl();
        	var text = iframe.contentWindow.document.body.innerText;
        },
        ondestroy: function (action) {
            //queryInit();
        }
	});
	
}

//查看贷后信息
function aftView(aftId,aftType){
	//aftType：0-贷后变更;1-首次检查;2-日常检查;3-授信到期前跟踪检查;4-重点检查;
	var json = nui.encode({"aftId":aftId,"aftType":aftType});
	var jspName;
	$.ajax({
        url: "com.bos.aft.conLoanChange.findAftInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (mydata) {
        	//nui.alert("=====");
			var o = nui.decode(mydata);
			if("0" == aftType){
				jspName="/aft/conLoanChange/loanChange_tree.jsp?changeId="+mydata.tbConLoanChange.changeId+"&contractId="+mydata.tbConLoanChange.contractId+"&partyId="+mydata.tbConLoanChange.partyId+"&summaryId="+mydata.tbConLoanChange.summaryId+"&loanChangeType="+mydata.tbConLoanChange.loanChangeType+"&processInstId=0&proFlag=-1";
			}
			if("1" == aftType){
				if("1" == mydata.tbAftFirstCheck.isSmall) {
					//alert("11111111111111");
					jspName="/aft/firstCheck/firstCheck_small_tree.jsp?firstCheckId="+mydata.tbAftFirstCheck.firstCheckId+"&partyId="+mydata.tbAftFirstCheck.partyId+"&isSmall=1&flag=3";
				}else {
					//alert("00000000000000");
					jspName="/aft/firstCheck/firstCheck_tree.jsp?firstCheckId="+mydata.tbAftFirstCheck.firstCheckId+"&partyId="+mydata.tbAftFirstCheck.partyId+"&isSmall="+mydata.tbAftFirstCheck.isSmall+"&processInstId=0&proFlag=-1";
				}
			}
			if("2" == aftType){
				if("1" == mydata.tbAftNormalCheck.isSmall) {
					//alert("11111111111111");
					jspName="/aft/normalCheck/normalCheck_small_tree.jsp?normalCheckId="+mydata.tbAftNormalCheck.normalCheckId+"&partyId="+mydata.tbAftNormalCheck.partyId+"&checkType="+mydata.tbAftNormalCheck.checkType+"&isSmall=1&flag=3";
				}else {
					//alert("00000000000000");
					jspName="/aft/normalCheck/normalCheck_tree.jsp?normalCheckId="+mydata.tbAftNormalCheck.normalCheckId+"&partyId="+mydata.tbAftNormalCheck.partyId+"&checkType="+mydata.tbAftNormalCheck.checkType+"&processInstId=0&proFlag=-1";
				}
			}
			if("3" == aftType){
				jspName="/aft/otherCheck/check_tree.jsp?checkId="+mydata.tbAftExpireCheck.checkId+"&partyId="+mydata.tbAftExpireCheck.partyId+"&checkType=e&processInstId=0&proFlag=-1";
			}
			if("4" == aftType){
				jspName="/aft/otherCheck/check_tree.jsp?checkId="+mydata.tbAftPointCheck.checkId+"&partyId="+mydata.tbAftPointCheck.partyId+"&checkType=p&processInstId=0&proFlag=-1";
			}
			nui.open({
				url:nui.context + jspName,
				showMaxButton:true,
				title:"查看",
				width: 1024,
	            height: 768,
	            state:"max",
	            ondestroy: function(e) {
	            }
			});
		}
	});
}


/*新建多选框控件，重设宽度--begin（解决required验证后控件消失的问题）*/
mini.NewCheckBoxGroup = function() {
    mini.NewCheckBoxGroup.superclass.constructor.call(this)
};
mini.extend(mini.NewCheckBoxGroup, mini.DictCheckboxGroup, {
    uiCls: "mini-newcheckbox",
    width:"100%"
});
mini.regClass(mini.NewCheckBoxGroup, "newcheckbox");
/*新建多选框控件，重设宽度--end */

//单选框
mini.DictRadioGroup.prototype.width="100%";
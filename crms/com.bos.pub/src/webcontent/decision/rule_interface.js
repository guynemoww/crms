/*
	获取业务参数数组的函数写在rule_edit.jsp中了，用全局变量params保存
*/

window.git = window.git || {};
top.git = top.git || {};
top.git.map = top.git.map || {};
git.map = top.git.map;
git.map.dict = git.map.dict || {};
git.map.org = git.map.org || {};
git.map.user = git.map.user || {};
git.map.product = git.map.product || {};
git.getOrgsInfo = function(orgids) {
	if (!orgids)
		return;
	var result=null;
	$.ajax({
        url: "com.primeton.components.nui.DictLoader2.getOrgInfo.biz.ext",
        data: JSON.stringify({
            "orgids": orgids
        }),
        type: "POST",
        contentType:'text/json',
        async: false,
        success: function(f) {
            var e = f.orgs || [];
            for (var i=0; i<e.length; i++) {
            	git.map.org['id'+e[i].orgid]=e[i].orgname;
            	git.map.org['id'+e[i].orgcode]=e[i].orgname;
            	git.map.org['idorgcode'+e[i].orgid]=e[i].orgcode;
            }
            result=e;
            return e;
        }
	});
	//alert(nui.encode(result));
}
git.getOrgName = function(orgid) {
	if (git.map.org['id'+orgid])
		return git.map.org['id'+orgid];
	git.getOrgsInfo(orgid);//alert('---'+orgid);
	return git.map.org['id'+orgid];
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
	$.ajax({
        url: "com.primeton.components.nui.DictLoader2.getUserInfo.biz.ext",
        data: JSON.stringify({
            "userids": userids
        }),
        type: "POST",
        contentType:'text/json',
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
	$.ajax({
        url: "com.primeton.components.nui.DictLoader2.getProductInfo.biz.ext",
        data: JSON.stringify({
            "productids": productids
        }),
        type: "POST",
        contentType:'text/json',
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

function decisionGetDictName(f, d) {//alert(f+','+d);
    var e = [];
    d = d||'';
    d = ',' + d + ',';
    for (var g = 0, c = f.length; g < c; g++) {
        var h = f[g];
        if (d.indexOf(','+h.dictID+',') >= 0) {
            e.push(h.dictName);
        }
    }
    return e.join(",")
}
function decisionGetDictText(c, e) {
	top.map = top.map ||{};//cache in top+
	var dict=top.map[c];
    var d = null;
    if (dict)
    	d = JSON.parse(dict);//cache in top
    if (d) {
        return {name:decisionGetDictName(d, e),list:d};
    }
    var f = "";
    $.ajax({
        url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
        data: {
            dictTypeId: c
        },
        type: "POST",
        async: false,
        success: function(h) {
            var g = h.dictList||[];
            f = decisionGetDictName(g, e);
            top.map = top.map ||{};//cache in top+
            top.map[c] = JSON.stringify(g);//cache in top+
        }
    });
    return {name:f,list:JSON.parse(top.map[c])};
}
function decisionGetDict(dictTypeId, a) {
	var text = "";
	if ('org' == dictTypeId) {
		return git.getOrgName(a);
	} else if ('user' == dictTypeId)
		return git.getUserName(a);
	else if ('product' == dictTypeId)
		return git.getProductName(a);
	else
		text = decisionGetDictText(dictTypeId, a).name;
	if (!text)
		return a;
	return text;
}
function openDialog(url, okCallBack) {
	saveScrollPosition();
	if (top && top.nui) {
		top.nui.open({
	        url: url,
	        title: "窗口", 
	        width: 800,
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	            if(action=="ok"){top.x=this;
	            	if (typeof okCallBack=='function')
						okCallBack(this.getIFrameEl().contentWindow);
	            }
	            restoreScrollPosition();
	        }
	    });
	} else {
		var dom=$('<div/>');
		var ifr = $('<iframe name="iframe" id="iframe" frameborder="0" style="width:100%;height:100%;" border="0"/>');
		ifr[0].src=url;
		dom.append(ifr);
		dialog(dom,null,{width:'90%',height:'450', buttons: [ { text: "确定", click: function() {
				if (typeof okCallBack=='function')
					okCallBack(this);
				
				$( this ).dialog( "close" );
				restoreScrollPosition();
			}}, { text: "取消", click: function() {
				$( this ).dialog( "close" );
				restoreScrollPosition();
			}}]
		});
	}
}

//获取操作符数组
function getExprOpItems() {
	var its=[
		{text:'取消', func:hideMenu},
		{text:'移除', func:removeExprItem},
		{text:'----',func:hideMenu},
		{text:'+',name:'+',func:insertExprItem},
		{text:'-',name:'-',func:insertExprItem},
		{text:'*',name:'*',func:insertExprItem},
		{text:'/',name:'/',func:insertExprItem},
		{text:'(',name:'(',func:insertExprItem},
		{text:')',name:')',func:insertExprItem},
		{text:'&gt;',name:'>',func:insertExprItem},
		{text:'&gt;=',name:'>=',func:insertExprItem},
		{text:'&lt;',name:'&lt;',func:insertExprItem},
		{text:'&lt;=',name:'&lt;=',func:insertExprItem},
		{text:'等于',name:'==',func:insertExprItem},//数值、文本
		{text:'不等于',name:'!=',func:insertExprItem},//数值、文本
		//2014.08.30 wangshichun start
		{text:'整数部分等于',name:'~~0==',func:insertExprItem},
		{text:'取2位小数等于',name:'~~2==',func:insertExprItem},
		{text:'取6位小数等于',name:'~~6==',func:insertExprItem},
		{text:'整数部分不等于',name:'~~0!=',func:insertExprItem},
		{text:'取2位小数不等于',name:'~~2!=',func:insertExprItem},
		{text:'取6位小数不等于',name:'~~6!=',func:insertExprItem},
		{text:'取6位小数大于',name:'~~6>',func:insertExprItem},
		{text:'取6位小数大于等于',name:'~~6>=',func:insertExprItem},
		{text:'取6位小数小于',name:'~~6&lt;',func:insertExprItem},
		{text:'取6位小数小于等于',name:'~~6&lt;=',func:insertExprItem},
		//2014.08.30 wangshichun end
		{text:'并且',name:'&&',func:insertExprItem},
		{text:'或者',name:'||',func:insertExprItem},
		{text:'包含（字典）',name:'containsDict',func:insertExprItem},
		{text:'包含文字',name:'contains',func:insertExprItem},
		{text:'不包含文字',name:'notContains',func:insertExprItem},
		{text:'文字开始于',name:'startsWith',func:insertExprItem},
		{text:'文字不开始于',name:'notStartsWith',func:insertExprItem},
		{text:'文字结尾于',name:'endsWith',func:insertExprItem},
		{text:'文字不结尾于',name:'notEndsWith',func:insertExprItem}//,
		//{text:'----',func:hideMenu}
	];
	var funcs = [
	//	{text:'包含文字……',ptext:'“{1}”包含“{2}”',funcname:'contains',type:'是否型', func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'被包含的文本',val:[{type:'const',text:'b'}]}] },//文本
	//	{text:'不包含文字……',ptext:'“{1}”不包含“{2}”',funcname:'notContains',type:'是否型',func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'被包含的文本',val:[{type:'const',text:'b'}]}]},//文本
	//	{text:'以……开始',ptext:'“{1}”以“{2}”开始',funcname:'startsWith',type:'是否型',func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'开始的文本',val:[{type:'const',text:'a'}]}]},//文本
	//	{text:'不以……开始',ptext:'“{1}”不以“{2}”开始',funcname:'notStartsWith',type:'是否型',func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'开始的文本',val:[{type:'const',text:'a'}]}]},//文本
	//	{text:'以……结尾',ptext:'“{1}”以“{2}”结尾',funcname:'endsWith',type:'是否型',func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'结尾的文本',val:[{type:'const',text:'c'}]}]},//文本
	//	{text:'不以……结尾',ptext:'“{1}”不以“{2}”结尾',funcname:'notEndsWith',type:'是否型',func:insertExprItem, 
	//		args:[{type:'文本型',text:'进行判断的文本',val:[{type:'const',text:'abc'}]},{type:'文本型',text:'结尾的文本',val:[{type:'const',text:'c'}]}]}//文本
		{
			"text":"参数未获取到",
			"type":"是否型",
			"ptext":"参数“{1}”未获取到",
			"funcname":"paramNotExist",
			"func":insertExprItem,
			"args":[{
					"text":"参数英文名称",
					"type":"文本型",
					"val":[{"text":"无","type":"const"}]
			}]
		},{
			"text":"参数无值",
			"type":"是否型",
			"ptext":"参数“{1}”无值",
			"funcname":"paramNull",
			"func":insertExprItem,
			"args":[{
					"text":"参数英文名称",
					"type":"文本型",
					"val":[{"text":"无","type":"const"}]
			}]
		},{
			"text":"调用规则（是否型）",
			"type":"是否型",
			"ptext":"调用规则“{1}”",
			"funcname":"invokeRuleBool",
			"func":insertExprItem,
			"args":[{
					"text":"调用的规则标识",
					"type":"文本型",
					"val":[{"text":"无","type":"const"}]
			}]
		},{
			"text":"调用规则（文本型）",
			"type":"文本型",
			"ptext":"调用规则“{1}”",
			"funcname":"invokeRuleStr",
			"func":insertExprItem,
			"args":[{
					"text":"调用的规则标识",
					"type":"文本型",
					"val":[{"text":"无","type":"const"}]
			}]
		},{
			"text":"调用规则（数值型）",
			"type":"数值型",
			"numbertype":"double",
			"ptext":"调用规则“{1}”",
			"funcname":"invokeRuleDouble",
			"func":insertExprItem,
			"args":[{
					"text":"调用的规则标识",
					"type":"文本型",
					"val":[{"text":"无","type":"const"}]
			}]
		}
	];
	return [].concat(its, funcs);
}
//获取函数数组
function getExprFuncItems() {
	var funcs = [
		{text:'取消', func:hideMenu},
		{text:'移除', func:removeExprItem},
		{text:'----',func:hideMenu},
		{text:'转换为整数',ptext:'取整数(“{1}”)',funcname:'toLong',type:'数值型',numbertype:'long', func:insertExprItem, 
			args:[{type:'数值型',text:'要转换的数值',val:[{type:'const',text:'123.45'}]}] },
		{text:'取整数绝对值abs',ptext:'取整数绝对值(“{1}”)',funcname:'absLong',type:'数值型',numbertype:'long', func:insertExprItem, 
			args:[{type:'数值型',text:'整数值',val:[{type:'const',text:'-123'}]}] },
		{text:'取数值绝对值abs',ptext:'取数值绝对值(“{1}”)',funcname:'absDouble',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'-123.45'}]}] },
		{text:'开平方根sqrt',ptext:'取(“{1}”)的平方根',funcname:'Math.sqrt',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'400.23'}]}] },
		{text:'acos',ptext:'acos(“{1}”)',funcname:'Math.acos',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'asin',ptext:'asin(“{1}”)',funcname:'Math.asin',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'atan',ptext:'atan(“{1}”)',funcname:'Math.atan',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'atan2',ptext:'atan2(“{1}”,“{2}”)',funcname:'Math.atan2',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值1',val:[{type:'const',text:'2.0'}]},{type:'数值型',text:'数值2',val:[{type:'const',text:'3.0'}]}] },
		{text:'cbrt',ptext:'cbrt(“{1}”)',funcname:'Math.cbrt',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'ceil',ptext:'ceil(“{1}”)',funcname:'Math.ceil',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'cos',ptext:'cos(“{1}”)',funcname:'Math.cos',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'cosh',ptext:'cosh(“{1}”)',funcname:'Math.cosh',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'exp',ptext:'exp(“{1}”)',funcname:'Math.exp',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'expm1',ptext:'expm1(“{1}”)',funcname:'Math.expm1',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'floor',ptext:'floor(“{1}”)',funcname:'Math.floor',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'log',ptext:'log(“{1}”)',funcname:'Math.log',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'log10',ptext:'log10(“{1}”)',funcname:'Math.log10',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'log1p',ptext:'log1p(“{1}”)',funcname:'Math.log1p',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'rint',ptext:'rint(“{1}”)',funcname:'Math.rint',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'round',ptext:'round(“{1}”)',funcname:'Math.round',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'signum',ptext:'signum(“{1}”)',funcname:'Math.signum',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'sin',ptext:'sin(“{1}”)',funcname:'Math.sin',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'sinh',ptext:'sinh(“{1}”)',funcname:'Math.sinh',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'tan',ptext:'tan(“{1}”)',funcname:'Math.tan',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'tanh',ptext:'tanh(“{1}”)',funcname:'Math.tanh',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'toDegrees',ptext:'toDegrees(“{1}”)',funcname:'Math.toDegrees',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'toRadians',ptext:'toRadians(“{1}”)',funcname:'Math.toRadians',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'ulp',ptext:'ulp(“{1}”)',funcname:'Math.ulp',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'1.0'}]}] },
		{text:'max（数值）',ptext:'max(“{1}”,“{2}”)',funcname:'Math.max',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值1',val:[{type:'const',text:'2.0'}]},{type:'数值型',text:'数值2',val:[{type:'const',text:'3.0'}]}] },
		{text:'max（整数）',ptext:'max(“{1}”,“{2}”)',funcname:'Math.max',type:'数值型',numbertype:'long', func:insertExprItem, 
			args:[{type:'数值型',text:'数值1',val:[{type:'const',text:'2'}]},{type:'数值型',text:'数值2',val:[{type:'const',text:'3'}]}] },
		{text:'min（数值）',ptext:'min(“{1}”,“{2}”)',funcname:'Math.min',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值1',val:[{type:'const',text:'2.0'}]},{type:'数值型',text:'数值2',val:[{type:'const',text:'3.0'}]}] },
		{text:'min（整数）',ptext:'min(“{1}”,“{2}”)',funcname:'Math.min',type:'数值型',numbertype:'long', func:insertExprItem, 
			args:[{type:'数值型',text:'数值1',val:[{type:'const',text:'2'}]},{type:'数值型',text:'数值2',val:[{type:'const',text:'3'}]}] },
		{text:'pow',ptext:'pow(“{1}”,“{2}”)',funcname:'Math.pow',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'数值型',text:'数值',val:[{type:'const',text:'2.0'}]},{type:'数值型',text:'指数',val:[{type:'const',text:'3'}]}] },
			
		{text:'如果满足……那么（整数数值）',ptext:'如果满足[ {1} ]那么[ {2} ]否则[ {3} ]',funcname:'ifLong',type:'数值型',numbertype:'long', func:insertExprItem, 
			args:[{type:'是否型',text:'要满足的条件',val:[{type:'const',text:'456'},{"text":">","name":">","type":"op"},{type:'const',text:'123'}]},
				{type:'数值型',numbertype:'long',text:'满足条件时的整数结果',val:[{type:'const',text:'100'}]},
				{type:'数值型',numbertype:'long',text:'不满足条件时的整数结果',val:[{type:'const',text:'200'}]}
			]},
		{text:'如果满足……那么（数值）',ptext:'如果满足[ {1} ]那么[ {2} ]否则[ {3} ]',funcname:'ifDouble',type:'数值型',numbertype:'double', func:insertExprItem, 
			args:[{type:'是否型',text:'要满足的条件',val:[{type:'const',text:'456'},{"text":">","name":">","type":"op"},{type:'const',text:'123'}]},
				{type:'数值型',numbertype:'double',text:'满足条件时的数值结果',val:[{type:'const',text:'6.8'}]},
				{type:'数值型',numbertype:'double',text:'不满足条件时的数值结果',val:[{type:'const',text:'7.5'}]}
			]},
		{text:'如果满足……那么（文本）',ptext:'如果满足[ {1} ]那么[ {2} ]否则[ {3} ]',funcname:'ifString',type:'文本型',numbertype:'double', func:insertExprItem, 
			args:[{type:'是否型',text:'要满足的条件',val:[{type:'const',text:'456'},{"text":">","name":">","type":"op"},{type:'const',text:'123'}]},
				{type:'文本型',text:'满足条件时的文本型结果',val:[{type:'const',text:'AAA'}]},
				{type:'文本型',text:'不满足条件时的文本型结果',val:[{type:'const',text:'AA'}]}
			]}
	];
	return [].concat(funcs);
}
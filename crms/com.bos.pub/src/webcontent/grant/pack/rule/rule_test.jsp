<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_RULE, com.bos.pub.decision.TbPubGrantRule
-->
<head>
<%@include file="/common/nui/common.jsp" %>
  <script src="<%=request.getContextPath() %>/pub/decision/rule.js"></script>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div id="panel1" class="nui-dynpanel" columns="4">
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">测试</a>
	<a class="nui-button"  iconCls="icon-close" onclick="closeMe()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
//假定：url中肯定有type、rid、pid
var type="<%=request.getParameter("type") %>";
var pid="<%=request.getParameter("pid") %>";
var rid="<%=request.getParameter("rid") %>";
var params=[];

//初始化规则分类中的业务参数
function initParams() {
	//if (type == 'grant') {
	var data={};
	data.item={};
	data.item.tbPubGrantPackage={};
	data.item.tbPubGrantPackage.pid=pid;
	data.item.pid=pid;
	var json=nui.encode(data);
	//alert(json);
	if (type == 'indexgrade') {
		params=[
			{paramname:'指标值', paramtype:'1'}
   		];
	    initParamValue();
	} else if (type == 'modelgrade') {
		params=[
			{paramname:'总分', paramtype:'1'}
   		];
	    initParamValue();
	} else if (type == 'model') {
		// 模型总分计算
		var modelid="<%=request.getParameter("modelid") %>";
		$.ajax({
	       url: "com.bos.pub.model.model.getModelBaseIndexes.biz.ext",
	       type: 'POST',
	       data: nui.encode({itemId:modelid}),
	       cache: false,
	       contentType:'text/json',
	       success: function (text) {
	       	if(text.msg){
	       		alert(text.msg);
	       	} else {
	       		//params=tbPubGrantParam2param(text.items) || [];
	       		text.items=text.items || [];
	       		for (var i=0,len=text.items.length; i<len; i++) {
	       			text.items[i].paramname=text.items[i].indexName;
	       			text.items[i].paramtype='1';
	       		}
	       		params=text.items || [];
	       		initParamValue();
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
	       		alert(text.msg);
	       	} else {
	       		//params=tbPubGrantParam2param(text.items) || [];
	       		params=text.items || [];
	       		initParamValue();
	       	}
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	           alert(jqXHR.responseText);
	       }
	    });
    }
	//}
}
initParams();

//获取业务参数上次录入的测试值
function initParamValue() {
	var json = nui.encode({rid:"<%=request.getParameter("rid") %>", lob:"rtest"});
	$.ajax({
       url: "com.bos.pub.decision.getRuleLob.biz.ext",
       type: 'POST',
       data: json,
       cache: false,
       contentType:'text/json',
       success: function (data) {
       	if(data.msg){
       		alert(data.msg);
       	} else {
       		//alert("x:"+nui.encode(data.item));
       		initParamDom((data.item || {}).rtest);
       	}
       },
       error: function (jqXHR, textStatus, errorThrown) {
           alert(jqXHR.responseText);
       }
    });
}

//将业务参数解析为dom
function initParamDom(item) {
	//var panel1 = $('#panel1');
	var panel1 = $('<div id="panel1" class="nui-dynpanel" columns="4"></div>');
	var form=$('#form1');
	form.html('');
	form.append(panel1);
	panel1.html('');
	for (var i=0,len=params.length; i<len; i++) {
		var p=params[i];
		var label=$('<label/>');
		label.html(p.paramname+"：");
		panel1.append(label);
		//console.log(nui.encode(p));
		var input='<input name="'
			+(p.enname||p.paramname)
			+'" required="false" class="nui-textbox" vtype="'
			//去掉数字验证
			//+(p.paramtype == '1' ? 'float' : p.paramtype == '4' ? 'int' : '')
			+'" ></input>';
		if (p.paramdicttype=='org') {
			input='<input name="'
				+(p.enname||p.paramname)
				+'" required="false" dictTypeId="'
				+p.paramdicttype
				+'" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg"></input>';
		} else if (p.paramdicttype=='product') {
			input='<input name="'
				+(p.enname||p.paramname)
				+'" required="false" dictTypeId="'
				+p.paramdicttype
				+'" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectProduct"></input>';
		} else if (p.paramdicttype && p.paramdicttype.length > 0) {
			input='<input name="'
				+(p.enname||p.paramname)
				+'" required="false" dictTypeId="'
				+p.paramdicttype
				+'" class="nui-dictcombobox"></input>';
		}
		input=$(input);
		panel1.append(input);
		//alert(nui.encode(p));
	}
	//alert(panel1.html());
	nui.parse();
	
	if (typeof item == 'string') {
		item = nui.decode(item);
	}
	if (!item) {
		return;
	}
	form = new nui.Form("#form1");
	form.setData(item);
}
//选择机构
function selectEmpOrg(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/utp/org/employee/selectRoleOrg.jsp",
        showMaxButton: true,
        title: "选择",
        width: 350,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.orgid);
                    btnEdit.setText(data.orgname);
                }
            }
        }
    });            
}
//选择品种
function selectProduct(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/pub/product/product/select_product_tree.jsp",
        showMaxButton: true,
        title: "选择",
        width: 350,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.currentNode;
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.productCd);
                    btnEdit.setText(data.productName);
                }
            }
        }
    });            
}
//关闭窗口
function closeMe() {
	if (window.CloseOwnerWindow) {
		window.CloseOwnerWindow();
		return;
	}
	parent.$( parent.dialogdom ).dialog( "close" );
}
//保存规则参数值，然后调用测试函数进行测试
function save() {
	var form = new nui.Form("#form1");
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请填写正确的值");
		return;
	}
	var dat = form.getData();
	var json = nui.encode({"rtest":nui.encode(dat), "rid":rid});
	//alert(json); return;
	$.ajax({
	    url: "com.bos.pub.decision.saveRuleTestContent.biz.ext",
	    type: 'POST',
	    data: json,
	    cache: false,
	    contentType:'text/json',
	    success: function (text) {
	    	if(text.msg){
	    		alert(text.msg);
	    	} else {
	    		//alert('保存成功！');
	    		test();
	    	}
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        nui.alert(jqXHR.responseText);
	    }
	});
}
//保存规则参数后进行测试
function test() {
	var form = new nui.Form("#form1");
	var dat = form.getData();
	var arr=[];
	for (p in dat) {
		var type='';
		for (var i=0,len=params.length; i<len; i++) {
			if (p == params[i].paramname || p == params[i].enname) {
				type = params[i].paramtype;
				break;
			}
		}
		if (!type)
			continue;
		arr[arr.length]={name: p, val: dat[p], type: type};
	}
	var json = nui.encode({items:arr, rid: rid});
	//alert(json);
	$.ajax({
	    url: "com.bos.pub.decision.testRule.biz.ext",
	    type: 'POST',
	    data: json,
	    cache: false,
	    contentType:'text/json',
	    success: function (text) {
	    	if(text.msg){
	    		var msg=text.msg;
	    		if (text.path)
	    			msg += '\n<br/>执行路径：' + text.path;
	    		alert(msg);
	    	} else {
	    		alert('出现错误！');
	    	}
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        nui.alert(jqXHR.responseText);
	    }
	});
}
	</script>
</body>
</html>

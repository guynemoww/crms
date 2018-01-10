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
	<a class="nui-button" id="btnSave" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
var params=[];

//初始化规则分类中的业务参数
function initParams() {
	var data={};
	data.item={};
	// data.item.tbPubGrantPackage={};
	// data.item.tbPubGrantPackage.pid=pid;
	// data.item.pid=pid;
		$.ajax({
	       url: "com.bos.pub.fieldflefeed.getInterfaceValue.biz.ext",
	       type: 'POST',
	       data: nui.encode({"field":{"recordeType":"<%=request.getParameter("ids") %>","isShow":"1"}}),
	       cache: false,
	       contentType:'text/json',
	       success: function (text) {
	       	if(text.msg){
	       		alert(text.msg);
	       	} else {
	       		text.fields=text.fields || [];
	       		params=text.fields || [];
	       		if(params==""){
	       		  nui.get("btnSave").hide();
	       		  alert("没有补入的字段");
	       		}else{
	       		 initParamDom((params || {}));
	       		}
	       		
	       	}
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	           alert(jqXHR.responseText);
	       }
	    });
}
initParams();


//将业务参数解析为dom
function initParamDom(item) {
	//var panel1 = $('#panel1');
	var panel1 = $('<div id="panel1" class="nui-dynpanel" columns="4"></div>');
	var form=$('#form1');
	form.html('');
	form.append(panel1);
	panel1.html('');
	for (var i=0,len=item.length; i<len; i++) {
		var p=params[i];
		var label=$('<label/>');
		label.html(p.recordeName+"：");
		panel1.append(label);
		//console.log(nui.encode(p));
		
		var input='<input id="'+p.recordeField+'" name="'
			+(p.recordeField+":"+p.recordeName)
			+'" required="false" class="nui-textbox" vtype="'
			+(p.fildType == '1' ? 'float' : p.fildType == '4' ? 'int' : '')
			+'" ></input>';
		if (p.fildProperty=='org') {
			input='<input id="'+p.recordeField+'"  name="'
				+(p.recordeField+":"+p.recordeName)
				+'" required="false" dictTypeId="'
				+p.fildProperty
				+'" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg" dictTypeId="org" ></input>';
		} else if (p.fildProperty=='product') {
			input='<input id="'+p.recordeField+'"  name="'
				+(p.recordeField+":"+p.recordeName)
				+'" required="false" dictTypeId="'
				+p.fildProperty
				+'" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectProduct" dictTypeId="product" ></input>';
		} else if (p.fildProperty && p.fildProperty.length > 0) {
			input='<input id="'+p.recordeField+'"  name="'
				+(p.recordeField+":"+p.recordeName)
				+'" required="false" dictTypeId="'
				+p.fildProperty
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
var ids="<%=request.getParameter("ids") %>";
var fieldIds="";
function  SetData(data){
  if(data!=null){
	  if(ids=="01"){//客户id
		   for(var i=0;i<data.length;i++){
	         fieldIds+=data[i].partyId+":";
	     }
	  }
	  if(ids=="02"){//合同id
		   for(var i=0;i<data.length;i++){
	         fieldIds+=data[i].contractId+":";
	     }
	  }
	  if(ids=="03"){//借据id
		   for(var i=0;i<data.length;i++){
	         fieldIds+=data[i].loanSummaryId+":";
	     }
	  }
    
  }
}
function save() {
	var form = new nui.Form("#form1");
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var dat = form.getData();
	var arr=[];
	for (p in dat) {
		var type='';
		for (var i=0,len=params.length; i<len; i++) {
			if (p.split(":")[0] == params[i].recordeName || p.split(":")[0] == params[i].recordeField) {
				type = params[i].fildType;
				break;
			}
		}
		if (!type)
			continue;
		arr[arr.length]={name: p.split(":")[0],chName: p.split(":")[1],val: dat[p], type: type,recordeType:ids};
	}
	var json = nui.encode({items:arr,fids:fieldIds,recordeType:ids});
	$.ajax({
	    url: "com.bos.pub.fieldflefeed.addDifIdsValues.biz.ext",
	    type: 'POST',
	    data: json,
	    cache: false,
	    contentType:'text/json',
	    success: function (text) {
	    	if(text.msg){
	    		alert(msg);
	    	} else {
	    	  CloseWindow("ok");
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

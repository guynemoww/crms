<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:
-->
<head>
<title>授权维护</title>
</head>
<body>
<div id="form1"  style="width:100%;height:auto;overflow:hidden; text-align:left">
		<input id="item.grantId" name="item.grantId" class="nui-hidden">
		<div class="nui-dynpanel" columns="4">
			<label>机构名称：</label>
			<input id="item.orgcdoe" name="item.orgcode" required="true" allowInput="false" 
			class="nui-buttonEdit nui-form-input" onbuttonclick="selectOrg"  />
			<label>授信品种：</label>
			<input id="item.productType" name="item.productType" required="true" allowInput="false" 
			class="nui-buttonEdit nui-form-input" onbuttonclick="selectProduct"/>
			<label>是否低：</label>
			<input id="item.isLow" name="item.isLow" required="true"  dictTypeId="XD_0002" class="nui-dictcombobox"/>
			<label>担保方式：</label>
			<input id="item.guarType" name="item.guarType" required="true"  class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--"/>
			<label>权限金额(万元)：</label>
			<input id="item.maxAmt" name="item.maxAmt" required="true"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			<label>权限级别：</label>
			<input id="item.authLv" name="item.authLv" required="true"  class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--"/>
			<label>权限岗位名称：</label>
			<input id="item.posicode" name="item.posicode"  class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--"/>
<!-- 			<label>审批官级别：</label> -->
<!-- 			<input id="item.personLv" name="item.personLv" required="true"  class="nui-combobox" textField="text" valueField="id"  emptyText="--请选择--"/> -->
		</div>
		<div class="nui-toolbar" style="border:0;text-align:right;">
	    	<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save">保存</a>
	    	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1"); 
	
	var grantId = "<%=request.getParameter("grantId")%>";
	var v = "<%=request.getParameter("v")%>";
	
// 	var personLvs =[
// 		{"id":"1","text":"一级审批官"},
// 		{"id":"2","text":"二级审批官"},
// 		{"id":"3","text":"三级审批官"},
// 		{"id":"4","text":"四级审批官"},
// 		{"id":"5","text":"五级审批审"},
// 		{"id":"6","text":"六级审批官"},
// 		{"id":"7","text":"七级审批官"},
// 		{"id":"8","text":"八级审批官"}
// 		];
	var authLvs =[
		{"id":"1","text":"总行审批"},
		{"id":"2","text":"分行审批"},
		{"id":"3","text":"支行审批"}
		];	
	var guarTypes =[
		{"id":"1","text":"信用"},
		{"id":"2","text":"优质担保"},
		{"id":"3","text":"普通担保"},
		{"id":"4","text":"保证"},
		{"id":"5","text":"全部"}
		];		
	var posicodes =[
		{"id":"P1258","text":"总行个贷业务终审岗"},
		{"id":"P1253","text":"贴现业务审查岗"},
		{"id":"P1254","text":"贴现业务审批岗岗"},
		{"id":"P1255","text":"贴现业务终审岗"}
		];			
// 	nui.get("item.personLv").setData(personLvs);
	nui.get("item.authLv").setData(authLvs);
	nui.get("item.guarType").setData(guarTypes);
	nui.get("item.posicode").setData(posicodes);
	
	//初始化页面
    $(document).ready(function(){
    	if('2'==v){
    	
    		var json = nui.encode({"item":{"grantId":grantId},"sqlName":"com.bos.pub.grantManage.grant.select_grant_id"});
			$.ajax({
		        url: "com.bos.csm.pub.ibatis.getItem.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
	               	nui.get("item.orgcdoe").setValue(text.items[0].orgcode);
	               	nui.get("item.orgcdoe").setText(text.items[0].orgname);
	               	nui.get("item.productType").setValue(text.items[0].productType);
	               	nui.get("item.productType").setText(text.items[0].productName);
	               	nui.get("item.grantId").setValue(text.items[0].grantId);
	               	nui.get("item.isLow").setValue(text.items[0].isLow);
	               	nui.get("item.guarType").setValue(text.items[0].guarType);
	               	nui.get("item.maxAmt").setValue(text.items[0].maxAmt);
	               	nui.get("item.authLv").setValue(text.items[0].authLv);
	               	nui.get("item.posicode").setValue(text.items[0].posicode);
// 	               	nui.get("item.personLv").setValue(text.items[0].personLv);
	               	
		        }
    		});
    	}
	});
	
	//保存事件
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		
		if('2'!=v){
			//校验是否已经加入授权
			var json = {"orgcode":o.item.orgcode,"productType":o.item.productType,"isLow":o.item.isLow,"guarType":o.item.guarType,"maxAmt":o.item.maxAmt};
	   	   	var msg = exeRule("RGRANT_0002","1",json);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     return;
	   	     }
		}
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.pub.grantManage.grantManage.saveGrant.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
	}
	
	//机构选择
	function selectOrg(){
	
		var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp",
            title: "选择",
            width: 800,
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
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}

</script>
</body>
</html>
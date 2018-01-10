<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:28:18
  - Description:集团客户个人发起业务选择贷种
-->
<head>
<title>品种添加页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label" id="creditProduct">业务品种：</label>
			<input id="amountDetail.productType" name="amountDetail.productType" class="nui-buttonEdit nui-form-input"
			allowInput="false" onbuttonclick="selectProduct" required="true" emptyText="--请选择--" dictTypeId="product"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	   		<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="create">确定</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var productType = '';
	var partyId="<%=request.getParameter("partyId")%>";
	//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyId="+partyId,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                    	//本行员工才能选员工保证贷款
                        if(data.productCd=='02003003'){
                        	var json = {"partyId":partyId};
					   	    var msg = exeRule("RBIZ_0060","1",json);
					   	    if(null != msg && '' != msg){
					   	    	btnEdit.setValue("");
	            				btnEdit.setText("");
								nui.alert(msg);
								return;
					   	    }
                        }
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
	function create(){
		form.validate();
		if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
		CloseWindow("ok");
	}
	function getData(){
    	productType = nui.get("amountDetail.productType").getValue();
      	if (productType) {
            return productType;
        } else {
            return null;
        }
    }
</script>
</body>
</html>
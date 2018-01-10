<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:28:18
  - Description:
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
			<label>币种：</label>
			<input name="amountDetail.currencyCd" id="amountDetail.currencyCd" required="true" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" emptyText="--请选择--"/>
			
			<input class="nui-hidden nui-form-input" id="amountDetail.amountId" name ="amountDetail.amountId" >
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	   		<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="create">确定</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var bizType = "<%=request.getParameter("bizType") %>";
	var bizHappenType = "<%=request.getParameter("bizHappenType") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum") %>";
	var productType = "<%=request.getParameter("productType") %>";
	nui.get("amountDetail.amountId").setValue("<%=request.getParameter("amountId") %>");
	nui.get("amountDetail.currencyCd").setValue("CNY");
	nui.get("amountDetail.currencyCd").setEnabled(false);//业务申请的币种---只能是人民币by shendl
	if(productType!=null &&productType!='' && productType!='null'){
		nui.get("amountDetail.productType").setValue(productType);
		nui.get("amountDetail.productType").setEnabled(false);
		nui.get("amountDetail.productType").validate();
	}
	//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyId="+partyId+"&bizType="+bizType,
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
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
		nui.get("btnCreate").setEnabled(false);
		//规则校验---
		//同一业务品种和币种不能录多笔
		var productType = nui.get("amountDetail.productType").getValue();
		var currencyCd = nui.get("amountDetail.currencyCd").getValue();
		var amountId = nui.get("amountDetail.amountId").getValue();
		var json1 = {"productType":productType,"currencyCd":currencyCd,"amountId":amountId};
   	    var msg = exeRule("RBIZ_0011","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		//小微企业不能发起综合授信业务
		if(bizType =='02' && productType != null){
			var json2 = {"productCd":productType};
			var msg = exeRule("RBIZ_0073","1",json2);
			if(null != msg && '' != msg){
				nui.alert(msg);
				nui.get("btnCreate").setEnabled(true);
				return;
			}
		}
		
		//联保小组成员才能申请联保贷款
		if(productType == '01001002'){
			var json2 = {"partyId":partyId};
	   	    var msg = exeRule("RBIZ_0017","1",json2);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	   			nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
		}
		
		//某些品种只有单笔低风险才能申请--汇票贴现/委托贷款
		if(productType == '01006001'|| productType == '01006002'|| productType == '01006010'  //村镇银行贴现产品
		){
			var json2 = {"amountId":amountId};
	   	    var msg = exeRule("RBIZ_0018","1",json2);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	   			nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
		}
		//综合授信下不能做银承通业务
		if(bizType=="02" && productType=='01008002'){
			alert("综合授信下不能做银承通业务");
			nui.get("btnCreate").setEnabled(true);
			return;
		}
		//国际福费廷产品不允许发起综合授信业务
		if(bizType=='02' && productType=='01007012'){
			nui.get("btnCreate").setEnabled(true);
			return alert("福费廷产品只能发起低风险业务");
			 	
		}
        var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.addProductDetail.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            		//在此跳转页面
            		nui.open({
			            url: nui.context + "/com.bos.bizProductDetail.getProductDetail.flow?amountDetailId="+text.amountDetail.amountDetailId
			            +"&productType="+text.amountDetail.productType+"&modelFlag=01&bizType="+bizType+"&proFlag="+proFlag
			            +"&bizHappenType="+bizHappenType+"&ecifPartyNum="+ecifPartyNum+"&partyId="+partyId,
			            showMaxButton: true,
			            title: "业务品种明细信息",
			            width: 800,
			            height: 500,
			            state:"max",
			            ondestroy: function(e) {
			            	CloseWindow("ok");
			            }
			        });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
        //CloseWindow("ok");
	}
</script>
</body>
</html>
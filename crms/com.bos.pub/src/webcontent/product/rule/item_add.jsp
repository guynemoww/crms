<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.product.TbSysTechProduct" class="nui-hidden" />
	<div class="nui-dynpanel" columns="2">
		<!--<label>授信产品控制规则代码：</label>
		<input name="item.productRuleCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />-->

		<label>授信产品控制规则名称：</label>
		<input id="productRuleName" name="item.productRuleName" required="true"  class="nui-buttonEdit" onbuttonclick="selectProduct" vtype="maxLength:100" dictTypeId="product"/>

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
						var json=nui.encode(o);
						//nui.alert(json);return;
						$.ajax({
					            url: "com.bos.pub.product.addRule.biz.ext",
					            type: 'POST',
					            data: json,
					            cache: false,
					            contentType:'text/json',
					            success: function (text) {
					            	if(text.msg){
					            		nui.alert(text.msg);
					            	} else {
					            		CloseWindow("ok");
					            	}
					            },
					            error: function (jqXHR, textStatus, errorThrown) {
					                nui.alert(jqXHR.responseText);
					            }
						});
	
}
function selectProduct(){

		 var btnEdit = this;
		        nui.open({
		            url: nui.context + "/pub/product/product/select_product_tree.jsp",
		            showMaxButton: true,
		            title: "选择产品",
		            width: 250,
		            height: 450,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.currentNode;
		                    data = nui.clone(data);
		                    if (data) {
		                       btnEdit.setValue(data.productCd);
		                    }
		                }
		            }
		        });            
}
	</script>
</body>
</html>

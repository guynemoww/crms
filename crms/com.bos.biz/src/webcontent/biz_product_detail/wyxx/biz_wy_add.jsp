<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description:票据信息新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="formwy" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbBizWyxxApply.wyId" id="tbBizWyxxApply.wyId" class="nui-hidden" />
	<input name="tbBizWyxxApply.amountDetailId" id="tbBizWyxxApply.amountDetailId" class="nui-hidden" />
	<fieldset>
  		<legend>
    		<span>物业信息</span>
    	</legend>
    	<div class="nui-dynpanel" columns="4" id="tableForm">
    		<label>物业类型：</label>
			<input name="tbBizWyxxApply.wylx" id="tbBizWyxxApply.wylx" required="true" data="data" valueField="dictID" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0201"   emptyText="--请选择--"/>
			<label>物业名称：</label>
			<input id="tbBizWyxxApply.wymc" name="tbBizWyxxApply.wymc" class="nui-textbox nui-form-input" required="true" />
			<label>物业所有权人名称：</label>
			<input id="tbBizWyxxApply.wysyqrmc" name="tbBizWyxxApply.wysyqrmc"  class="nui-textbox nui-form-input" required="true"  />
			<label>物业用途：</label>
			<input id="tbBizWyxxApply.wyyt" name="tbBizWyxxApply.wyyt" class="nui-textbox nui-form-input" required="true" />
			<label>物业面积（平方米）：</label>
			<input id="tbBizWyxxApply.wymj" name="tbBizWyxxApply.wymj" class="nui-textbox nui-form-input" required="true" />
			<label>物业经营方式：</label>
			<input name="tbBizWyxxApply.wyjyfs" id="tbBizWyxxApply.wyjyfs" required="true" data="data" valueField="dictID" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0202"   emptyText="--请选择--" onvaluechanged="chagefs"/>
		</div>
		<div class="nui-dynpanel" columns="4" id="nsr">	
			<label>物业租金年收入：</label>
			<input id="tbBizWyxxApply.wyzjnsr" name="tbBizWyxxApply.wyzjnsr" dataType="currency" class="nui-textbox nui-form-input" required="true" />
		</div>
		<div class="nui-dynpanel" columns="4">	
			<label>物业地点：</label>
			<input id="tbBizWyxxApply.wydd" name="tbBizWyxxApply.wydd"  class="nui-textarea nui-form-input" required="true"  />
			<label>备注：</label>
			<input id="tbBizWyxxApply.bz" name="tbBizWyxxApply.bz"  class="nui-textarea nui-form-input" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var formwy = new nui.Form("#formwy");
	var amountDetailId = "<%=request.getParameter("amountDetailId") %>";
	if (amountDetailId) {
		nui.get("tbBizWyxxApply.amountDetailId").setValue(amountDetailId); 
	}
	$("#nsr").css("display","none");
	function chagefs(){
		var fs = nui.get("tbBizWyxxApply.wyjyfs").getValue();
		if(fs=='03'){//出租才显示年收入
			$("#nsr").css("display","block");
		}else{
			$("#nsr").css("display","none");
		}
	}
	function save() {
		formwy.validate();
		if (formwy.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("formwy");
		var o=formwy.getData();
		var fs = nui.get("tbBizWyxxApply.wyjyfs").getValue();
		if(fs!='03'){
			o.tbBizWyxxApply.wyzjnsr= '';
		}
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.bizProductDetail.bizWyxx.insertWyxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("formwy");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("formwy");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
</script>
</body>
</html>

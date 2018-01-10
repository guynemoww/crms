<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lpc
  - Date: 2015-6-3
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="wyed" style="width:99.5%;height:auto;overflow:hidden;">
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
			<input id="tbBizWyxxApply.wymc" name="tbBizWyxxApply.wymc" required="true"  class="nui-textbox nui-form-input" />
			<label>物业所有权人名称：</label>
			<input id="tbBizWyxxApply.wysyqrmc" name="tbBizWyxxApply.wysyqrmc" required="true"  class="nui-textbox nui-form-input" />
			<label>物业用途：</label>
			<input id="tbBizWyxxApply.wyyt" name="tbBizWyxxApply.wyyt" required="true"  class="nui-textbox nui-form-input" />
			<label>物业面积（平方米）：</label>
			<input id="tbBizWyxxApply.wymj" name="tbBizWyxxApply.wymj" required="true"  class="nui-textbox nui-form-input" />
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
			<input id="tbBizWyxxApply.wydd" name="tbBizWyxxApply.wydd" required="true"  class="nui-textarea nui-form-input" />
			<label>备注：</label>
			<input id="tbBizWyxxApply.bz" name="tbBizWyxxApply.bz"  class="nui-textarea nui-form-input" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask("wyed");
    var form = new nui.Form("#wyed");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	function chagefs(){
		var fs = nui.get("tbBizWyxxApply.wyjyfs").getValue();
		if(fs=='03'){//出租才显示年收入
			$("#nsr").css("display","block");
		}else{
			$("#nsr").css("display","none");
		}
	}
	var wyId = "<%=request.getParameter("wyId") %>";
	function initForm() {
		var json=nui.encode({"wyId":wyId});
		$.ajax({
	            url: "com.bos.bizProductDetail.bizWyxx.getWyxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("wyed");
	            	form.setData(text);
	            	chagefs();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("wyed");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		var fs = nui.get("tbBizWyxxApply.wyjyfs").getValue();
		if(fs!='03'){
			o.tbBizWyxxApply.wyzjnsr= '';
		}
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.bizProductDetail.bizWyxx.updateWyxx.biz.ext",
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
</script>
</body>
</html>

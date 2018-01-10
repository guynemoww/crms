<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmBondInfo" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>发行日期：</label>
		<input name="item.issueDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>

		<label>债券类型：</label>
		<input name="item.bondTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0017"  />

		<label>币种：</label>
		<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" dvalue="CNY"/>

		<label>发行金额：</label>
		<input name="item.issueAmt" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

		<label>备注：</label>
		<input name="item.remark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />
		
		<label>登记日期：</label>
		<input name="item.createTime" required="false" Enabled="false" class="nui-datepicker nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>
	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    
    var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
	}
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.insertItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
</script>
</body>
</html>

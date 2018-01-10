<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21

  - Description:TB_CSM_OTHER_RELATED_PARTY, com.bos.dataset.csm.TbCsmOtherRelatedParty-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="rela.partyId" id="rela.partyId" class="nui-hidden" />
	<input name="rela.relFrom" id="rela.relFrom" class="nui-hidden" value="2"/>
	<input name="rela.otherRelatedPartyId" id="rela.otherRelatedPartyId" class="nui-hidden" />
	<input name="rela.relaPartyId" id="rela.relaPartyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>关联客户类型：</label>
		<input id="rela.custType"name="rela.custType" value="<%=request.getParameter("custType") %>" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0034" enabled="false" />
		
		<label>关联方名称：</label>
		<input id="rela.partyName"name="rela.partyName" required="true" class="nui-buttonEdit"  allowInput="false"  onbuttonclick="selectCust" enabled="false" />
		
		<label>证件类型：</label>
		<input id="rela.certType" name="rela.certType" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZJLX00021" enabled="false" />
		
		<label>证件号码：</label>
		<input id="rela.certNum" name="rela.certNum" enabled="false"="true" class="nui-textbox nui-form-input" enabled="false"/>

		<label>关联关系：</label>
		<input id="rela.relatedCd" name="rela.relatedCd" required="true" class="nui-buttonEdit nui-form-input" allowInput="false"dictTypeId="XD_GLGX0004" enabled="false" />

		<label>备注：</label>
		<input id="rela.remark" name="rela.remark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" enabled="false"/>

	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>	    
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var o = new Object();
	o.rela = window.Owner.grid.getSelected(); 
	form.setData(o);
	nui.get('rela.partyName').setText(o.rela.partyName);
</script>
</body>
</html>

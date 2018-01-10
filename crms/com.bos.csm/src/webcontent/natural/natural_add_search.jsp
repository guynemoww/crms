<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description: 
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="cxlist" style="width: 99.5%; height: auto; overflow: hidden;">
		<fieldset>
			<legend>
				<span>客户信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4" id="tableForm">
				<label>查询方式 ：</label>
				<input name="param1.RESOLVE_TYPE" id="param1.RESOLVE_TYPE" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XDECIFCXFS" vtype="maxLength:100" />
				<label>证件类型 ：</label>
				<input name="param1.CERT_TYPE" id="param1.CERT_TYPE" required="true" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  vtype="maxLength:100" />
				<label>证件号码 ：</label>
				<input name="param1.CERT_NO" id="param1.CERT_NO" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			    <label>客户名称：</label>
				<input name="param1.PARTY_NAME" id="param1.partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		</div>
	</dir>
	<div>
		<div id="dplist" class="nui-datagrid" style="width: 100%; height: auto"
			url="com.bos.csm.natural.natural.getEcifPerson.biz.ext" dataField="responseOut.tbBizTxxxApply"
			allowResize="false" showReloadButton="false" allowCellEdit="false" sizeList="[10,15,20,30]" multiSelect="true"
			pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="ECIF_CUST_NO" headerAlign="center" allowSort="true">ECIF客户编号</div>
					<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
					<div field="CERT_TYPE" headerAlign="center"  dictTypeId="CDKH0002" allowSort="true">证件类型</div>
					<div field="CERT_NO" headerAlign="center" allowSort="true">证件号码</div>
					<div field="OPEN_ORG" headerAlign="center" allowSort="true">开户机构</div>
					<div field="OPEN_DATE" headerAlign="center" allowSort="true">开户日期</div>
					<div field="OPEN_TELLER" headerAlign="center" allowSort="true">开户柜员</div>
				</div>
			</div>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		</div>
	</div>
	<script type="text/javascript">
	 		nui.parse();
	var form = new nui.Form("#cxlist");
	var grid = nui.get("dplist");	
    function search() {
		var o = form.getData();//逻辑流必须返回total
		grid.load(o);
	}
	</script>
</body>
</html>

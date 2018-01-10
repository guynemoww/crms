<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-11 19:35:01
  - Description:
-->
<head>
<title>集团客户额度</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<fieldset>
  	<legend>
    	<span>集团客户信息</span>
    </legend>
	<div id="form" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">集团客户编号：</label>
			<input name="party.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">集团客户名称：</label>
			<input name="party.partyName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">集团客户内部授信额度（元）：</label>
			<input id="party.creditLimit" name="party.creditLimit" class="nui-text nui-form-input" vtype="float;maxLength:20"  dataType="currency"/>
			
			<label class="nui-form-label">集团客户可用额度（元）：</label>
			<input id="party.availableAmt" name="party.availableAmt" class="nui-text nui-form-input" vtype="float;maxLength:20"  dataType="currency"/>
		</div>
	</div>
	</fieldset>
		<!--<label>注：若要发起评级，请先在财务报表模块录入客户年度财务报表；若确实无年报，请继续评级发起流程。</label>-->
		<fieldset>
			<legend>
				<span>集团成员内部授信额度</span>
			</legend>
			<div id="grid" class="nui-datagrid"   sortMode="client"
			    url="com.bos.crdApply.crdApply.getJtCrd.biz.ext" dataField="memberLimits"
			    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
			    emptyText="没有查到数据" showReloadButton="false"
			    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
			    sizeList="[10,20,50,100]" pageSize="10">
			    <div property="columns">
			        <div field="PARTY_NUM" allowSort="true" width="" headerAlign="center">成员编号</div>
			        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">成员名称</div>
			        <div field="PARTY_TYPE_CD" allowSort="true" width="" dictTypeId="XD_KHCD1001" headerAlign="center">成员类型</div>
			        <div field="CREDIT_AMT" allowSort="true" width="" headerAlign="center" dataType="currency">成员客户额度</div>
			        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center" dataType="currency">成员可用额度</div>
			        <div field="CREDIT_USED" allowSort="true" width="" headerAlign="center" dataType="currency">成员已用额度</div>
			        <div field="CREDIT_TERM" allowSort="true" width="" headerAlign="center">期限</div>
			        <div field="BEGIN_DATE" allowSort="true" width="" headerAlign="center">起始日</div>
			        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日</div>
			        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
			        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">经办机构</div>
			    </div>
			</div>
		</fieldset>
<script type="text/javascript">
	nui.parse();
	var partyId="<%=request.getParameter("corpid") %>";//参与人id
	var form = new nui.Form("#form");
	var grid = nui.get("#grid");
	initPage();
	function initPage(){
		var json = nui.encode({"partyId":partyId});
      	nui.ajax({
	        url: "com.bos.crdApply.crdApply.getGroupPartyLimit.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        async:false,
	        contentType:'text/json',
	        success: function (text) {
	       		var o = nui.decode(text);
	            form.setData(o);
	            grid.load({"partyId":partyId});
	        }
	    });
	}
</script>
</body>
</html>
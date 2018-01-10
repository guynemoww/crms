<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<fieldset>
<legend>
    	<span>ewa测算模板</span>
    </legend>
	<div>
	       <a>案例（加权系数）</a>
	       <input name="partyNum" required="false"  class="nui-combobox nui-form-input" style="width:25%;" />
	</div>
	<div>
	       <div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client" url="com.bos.aft.util.querySingleCsm.biz.ext" dataField="csmWarnings"
	            allowAlternating="true" multiSelect="false" style="width:100%;" showPager="false">
	            <div property="columns">
	            <div field="partyName" allowSort="true" width="10%" headerAlign="center" >变量（系数）</div> 
	            <div field="partyNum" allowSort="true" width="10%" headerAlign="center">加权资产(万元)</div>
	            <div field="orgNum" allowSort="true" width="10%" headerAlign="center" dictTypeId="org" >信用资本成本（万元）</div>
	            <div header="经济资本调整后实际收益测算" headerAlign="center" >     
	                 <div property="columns">
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >利率</div>
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >FTP价格</div>
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >资本成本率</div>
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >税率</div>
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >测算收益率</div>
	                      <div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user" >测算收益（万元）</div>
	                 </div>
	            </div>
	            </div>
	       </div>
    </div>
	
</fieldset>
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-node" onclick="queryCheckReportInfo">保存</a>
</div>

<script type="text/javascript">
	nui.parse();
    
    
    
</script>
</body>
</html>
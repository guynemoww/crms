<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 8:26:27
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<%@page
	import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<div id="form1" class="nui-form"
		style="width: 99.5%; height: auto; overflow: hidden;">
		<input name="sqlName" class="nui-hidden nui-form-input"value="com.bos.account.credit.groupCompanypifu" />
		<input name="item.partyId" class="nui-hidden nui-form-input"value="<%=request.getParameter("partyId") %>" />
	</div>
	
	<div id="datagrid1" class="nui-datagrid"
		style="width: 99.5%; height: auto"
		url="com.bos.csm.pub.ibatis.getItemDataObject.biz.ext" dataField="items"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
		onselectionchanged="selectPo" sortMode="client">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div field="PARTY_NAME" allowSort="true"  headerAlign="center">集团客户名称</div>
			<div field="APPROVAL_NUM" allowSort="true" headerAlign="center">批复编号</div>
			<div field="BZ" allowSort="true"  headerAlign="center"dictTypeId="CD000001">币种</div>
			<div field="CREDIT_AMT" allowSort="true"  headerAlign="center"dataType="currency">批复额度(元)</div>
			<div field="CREDIT_USED" allowSort="true" headerAlign="center" dataType="currency">已用批复额度(元)</div>
			<div field="AVAILABLE_AMT" allowSort="true" headerAlign="center" dataType="currency">可用批复额度（元）</div>
			<div field="VALID_DATE" allowSort="true" dateFormat="yyyy-MM-dd" headerAlign="center" >批复起始日</div>
			<div field="END_DATE" allowSort="true" dateFormat="yyyy-MM-dd" headerAlign="center" >批复到期日</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        } 
		
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
				e.data[i]['APPROVAL_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].APPROVAL_NUM+ '\');">'+e.data[i]['APPROVAL_NUM']+'</a>';
			}
		});
	</script>
</body>
</html>
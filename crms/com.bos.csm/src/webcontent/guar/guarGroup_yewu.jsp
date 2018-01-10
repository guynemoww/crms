<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-7-3 8:26:27
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
		<input name="sqlName" class="nui-hidden nui-form-input"value="com.bos.account.credit.guarGroupContract" />
		<input name="item.partyId" class="nui-hidden nui-form-input"value="<%=request.getParameter("partyId") %>" />
	</div>
	<div id="datagrid1" class="nui-datagrid"
		style="width: 99.5%; height: auto" allowAlternating="true"
		url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
		onselectionchanged="selectPo" sortMode="client">
		<div property="columns">
			<div field="partyName" allowSort="true"  headerAlign="center">成员名称</div>
			<div field="contractNum" allowSort="true" headerAlign="center">合同编号</div>
			<div field="contractAmt" allowSort="true"  headerAlign="center"dataType="currency">合同金额</div>
			<div field="conYuE" allowSort="true" headerAlign="center" dataType="currency">合同已用金额</div>
			<div field="beginDate" allowSort="true"  headerAlign="center" >合同起期</div>
			<div field="endDate" allowSort="true" headerAlign="center" >合同止期</div>
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
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
			}
		});
	</script>
</body>
</html>
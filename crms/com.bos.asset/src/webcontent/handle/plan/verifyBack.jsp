<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-10 15:01:01
  - Description:
-->
<head>
<title>核销收回</title>
    <%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>核销收回</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
		<input id="map.flg" name="map.flg" class="nui-hidden nui-form-input" />
					<label>客户名称：</label>
					<input name="map.partyName" required="false" class="nui-textbox" id="map.partyName"/>
					<label>合同编号：</label>
					<input id="map.contractNum" class="nui-textbox" name="map.contractNum" />
					<label>借据编号：</label>
					<input id="map.summaryNum" class="nui-textbox" name="map.summaryNum" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
					<a class="nui-button" iconCls="icon-reset" onclick="verfyback()">核销收回</a>
				</div>
		
				<div id="datagrid1" class="nui-datagrid" sortMode="client"
				    url="com.bos.pub.dao.queryOffCust.biz.ext" dataField="items"
				    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
				    emptyText="没有查到数据" showReloadButton="false"
				    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
				    sizeList="[10,20,50,100]" pageSize="10">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="ORGNAME" width="17%" headerAlign="center" align="center">机构名称</div>
						<div field="PARTY_NAME" width="17%"  headerAlign="center" align="center" >客户名称</div>
						<div field="PRODUCT_TYPE" width="17%"  headerAlign="center" align="center"  dictTypeId="product">业务品种</div>
						<div field="CONTRACT_NUM" width="17%"  headerAlign="center" align="center" >合同编号</div>
						<div field="SUMMARY_NUM" width="17%"  headerAlign="center" align="center" >借据编号</div>
						<div field="USER_NUM" width="17%"  headerAlign="center" align="center" dictTypeId="user">经办人</div>
					</div>
				</div>
	</div>
</fieldset>
</center>
	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form");
		var grid = nui.get("datagrid1");
		var flg="<%=request.getParameter("flg") %>";
		nui.get("map.flg").setValue(flg);
		query();
    	function query(){
			form.validate();
			var o = form.getData();
			grid.load(o);
			git.unmask();
    	}
    	function reset() {
			form.reset();
			query();
		}
		function verfyback(){
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一条数据");
				return false;
			}
			nui.open({
						url : nui.context
								+ '/asset/handle/plan/verifyBackQuery.jsp?summaryNum='+row.SUMMARY_NUM+'&nftNo='+row.NFT_NO+'&loanOrg='+row.LOAN_ORG,
						showMaxButton : true,
						title : "客户核销收回",
						width : 900,
						height : 400,
						onload : function(e) {
							var iframe = this.getIFrameEl();
							var text = iframe.contentWindow.document.body.innerText;
						},
						ondestroy : function(action) {
							if (action == "ok") {
								search();
							}
						}
					});
		}
		
		
    </script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-29 17:16:31
  - Description:
-->
<head>
<title>合同打印</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="form" class="nui-form">
		<input name="map.partyId" id="partyId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4" style="margin-bottom: 0; margin-top: 0;">
			<label>合同编号：</label>
			<input name="map.conNum" id="map.conNum" required="false" class="nui-textbox nui-form-input" />
			<label>合同类型：</label>
			<input name="map.printType" id="printType" class="nui-combobox" data="printType" />
		</div>
		<div class="nui-toolbar" style="text-align: right; padding-right: 25px; border: 0;">
			<a class="nui-button" iconCls="icon-search" onclick="query">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		<div class="nui-toolbar" style="border-bottom: none; text-align: left; margin-top: 7px">
			<a class="nui-button" iconCls="icon-download" id="printBtn" onclick="print">合同打印</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="grid" class="nui-datagrid" sortMode="client" style="height: 100%;" url="com.bos.conApply.conApply.getPrintApproveCons.biz.ext" dataField="conInfos" allowAlternating="true" multiSelect="false">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div type="indexcolumn" width="50px;">序号</div>
				<div field="NUM" allowSort="true" width="" headerAlign="center">合同编号</div>
				<div field="CON_TYPE_CN" allowSort="true" width="" headerAlign="center">合同类型</div>
				<div field="PRODUCT_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="product">业务品种</div>
				<div field="AMT" allowSort="true" width="" headerAlign="center" dataType="currency">合同金额</div>
				<div field="BEGIN_DATE" allowSort="true" width="" headerAlign="center">合同起期</div>
				<div field="END_DATE" allowSort="true" width="" headerAlign="center">合同止期</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var printType = [{ id: '01', text: '抵押合同' }, { id: '02', text: '质押合同'},{ id: '04', text: '保证合同'},{ id: '03', text: '保证金合同'},{ id: '05', text: '综合授信协议合同'},{ id: '06', text: '展期合同'},{ id: '00', text: '业务合同'}];
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("grid");  //批复列表
		//获取法人代码
		var legOrg ="<%=((UserObject) session.getAttribute("userObject")).getAttributes().get("legorg")%>";
		//非绵商行(9999的不能打印合同)
		if(legOrg != "9999"){
			nui.get("printBtn").hide();
		}
		var partyId="<%=request.getParameter("partyId")%>";
		nui.get("partyId").setValue(partyId);

		query();

		function query() {
			var o = form.getData();
			grid.load(o);
		}

		function reset() {
			nui.get("map.conNum").setValue("");
			nui.get("printType").setValue("");
		}
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1)
				return;
			out: for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				for (var j = 0; j < printType.length; j++) {
					if (printType[j].id == e.data[i].CON_TYPE) {
						e.data[i].CON_TYPE_CN = printType[j].text;
						continue out;
					}
				}
			}
		});
		/* 
		 //合同编号链接
		function clickConNum(contractId) {
			git.go(nui.context + "/crt/con_info/con_tree.jsp?contractId="
					+ contractId + "&contractType=02&proFlag=-1", parent);
		} */

		function print() {
			var row = grid.getSelected();
			if (!row) {
				alert("请选择需要打印的合同信息!");
				return;
			}
			if (row.CURRENCY_CD != null && '' != row.CURRENCY_CD && row.CURRENCY_CD != 'CNY') {
				nui.alert("外币合同不支持打印");
				return;
			}
			form.loading("正在加载合同...");
			var printType = row.CON_TYPE;
			//打印相应的合同类型
			var json = nui.encode({
				"contractNum" : row.NUM,
				"printType" : printType,
				"productType" : row.PRODUCT_TYPE
			});
			$
					.ajax({
						url : "com.bos.conApply.conApply.ContractPrintService.biz.ext",
						type : 'POST',
						data : json,
						contentType : 'text/json',
						cache : false,
						success : function(mydata) {
							form.unmask();
							if (mydata.msg) {
								alert(mydata.msg);
							} else if (mydata.swfPath) {
								nui
										.open({
											url : nui.context
													+ "/biz/biz_report/contract_view.jsp?filePath="
													+ mydata.swfPath,
											title : "合同信息预览",
											width : 1000,
											height : 500
										});
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							form.unmask();
							alert(jqXHR.responseText);
						}
					});
		}
	</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-7-15 16:17:00
  - Description:联保小组客户引入对公成员
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1"class="nui-form"style="width: 100%; height: auto; overflow: hidden;">
		<input id="item.partyTypeCd"name="item.partyTypeCd" value="01" class="nui-hidden nui-form-input" />
		<div class="nui-dynpanel" columns="4">
			<label>客户性质：</label>
			<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" />
			<label>客户名称：</label> 
			<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" /> 
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum"  name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input"/>
			<label>营业执照：</label>
			<input id="item.registrCd" name="item.registrCd" class="nui-textbox nui-form-input" />
			<label>组织机构代码：</label> 
			<input id="item.orgRegisterCd" name="item.orgRegisterCd" class="nui-textbox nui-form-input" /> 
<!-- 			<label>中征码：</label>  -->
<!-- 			<input id="item.middelCode" name="item.middelCode" class="nui-textbox nui-form-input" />  -->
		</div>
	</div>

	<div class="nui-toolbar" style="text-align: right; border: none">
		<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a> 
		<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>


		<div id="datagrid1" class="nui-datagrid"
			style="width: 99.5%; height: auto;" sortMode="client"
			url="com.bos.csm.pub.getNaturalAndCorp.getCorpAndNatural.biz.ext"
			dataField="items" allowAlternating="true" multiSelect="false"
			showReloadButton="false" sizeList="[10,20,50,100]" pageSize="10">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				  <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >CRMS客户编号</div> 
	        		<div field="partyName" allowSort="true" width="" headerAlign="center" >客户名称</div> 
			       	<div field="unifySocietyCreditNum"name="unifySocietyCreditNum" allowSort="true" headerAlign="center" >统一社会信用代码</div> 
			        <div field="registrCd" name="registrCd" allowSort="true" width="" headerAlign="center" >营业执照</div> 
			        <div field="orgRegisterCd" name="orgRegisterCd" allowSort="true" width="" headerAlign="center" >组织机构代码</div>
			        <div field="orgNum"allowSort="true" headerAlign="center" dictTypeId="org">机构名称</div>
			       	<div field="userNum"allowSort="true" headerAlign="center" dictTypeId="user">管户客户经理</div>
<!-- 					<div field="partyTypeCd" allowSort="true" width=""headerAlign="center" dictTypeId="XD_KHCD1001">客户性质</div> -->
			</div>
		</div>
		<div style="width: 99.5%">
			<div id="dataConfirm" class="nui-toolbar"
				style="border: 0; text-align: right;">
				<a class="nui-button" onclick="selected()">选中</a>
			</div>
		</div>

		<script type="text/javascript">
			nui.parse();
			var form = new nui.Form("#form1");
			var grid = nui.get("datagrid1");

			nui.get("datagrid1").hide();
			nui.get("dataConfirm").hide();
		<%--nui.get("cxresult").hide();--%>
			grid.on("preload", function(e) {
				if (!e.data || e.data.length < 1) {
					return;
				}
				for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
					if (e.data[i]['partyName'] == null) {
						e.data[i]['partyName'] = e.data[i]['englishName'];
					}
				}
			});

			//query();

			function query() {
				git.mask();
				form.validate();
				if (form.isValid() == false) {
					git.unmask();
					return;
				}

				var o = form.getData();
				grid.load(o, function(text) {
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						nui.get("datagrid1").show();
						nui.get("dataConfirm").show();
			}
				});
				git.unmask();
			}

			function selected() {
				var row = grid.getSelected();

				if (row) {
					/*  if(!row.contryRegionCd||!row.certType||!row.certCode||!row.partyTypeCd){
					 	alert("此客户必要信息不全,请重新选择");
						return;
					 }else{ */
					CloseWindow("ok");
					/* } */
				} else {
					alert("请选中一条记录");
				}
			}

			function getData() {
				var row = grid.getSelected();
				if (row) {
					return row;
				} else {
					return null;
				}
			}

			function reset() {
				form.reset();
				nui.get("item.partyTypeCd").setValue(stockHolderTypeCd);
				query();
			}
		</script>
</body>
</html>
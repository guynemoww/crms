<%@page pageEncoding="UTF-8"%>
<html>

<head>
<title>担保合同管理</title>
<%@include file="/common/nui/common.jsp"%>
 
</head>
<body>
<fieldset><legend> <span>生效担保合同信息</span> </legend>

	<div id="form" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		
		<div class="nui-dynpanel" columns="6">
<!-- 					<label>借款合同编号：</label>  -->
<!-- 					<input name="map.contractNum" id="map.contractNum" class="nui-textbox nui-form-input" /> -->
					<label>担保人名称：</label> 
					<input name="map.partyName" id="map.partyName" class="nui-textbox nui-form-input" />
					<label>担保合同编号：</label>
					<input name="map.subContractNum" id="map.subContractNum"class="nui-textbox nui-form-input" /> 
					<label>担保合同类型：</label> 
					<input name="map.subContractType" id="map.subContractType" valueField="dictid"
						textField="dictname" class="nui-dictcombobox nui-form-input"  /> 
		</div>
		
		<div class="nui-toolbar"style="text-align: right; padding-top: 5px; padding-right: 25px;"borderStyle="border:0;">
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a id="editCust" class="nui-button" iconCls="icon-zoomin"onclick="edit(-1)">查看</a>
		<a id="editCust" class="nui-button"iconCls="icon-edit" onclick="edit(1)">调整</a>
		<a class="nui-button"iconCls="icon-remove" onclick="remove()" id="rmove">失效</a>
		</div>
		
			<div id="grid" class="nui-datagrid" sortMode="client"
				url="com.bos.grt.subContractManage.subContract.findSubContractList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"
				onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
				sizeList="[10,20,50,100]" pageSize="10">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true">担保合同编号</div>
					<div field="PARTY_NAME" headerAlign="center"width="15%" allowSort="true">担保人名称</div>
					<div field="BZ" allowSort="true" headerAlign="center"dictTypeId="CD000001">币种</div>
					<div field="SUBCONTRACT_AMT" headerAlign="center"dataType="currency">担保合同金额</div>
					<div field="SUBCONTRACT_TYPE_NAME" allowSort="true"headerAlign="center">担保合同类型</div>
					<div field="IF_TOP_SUBCON" allowSort="true"headerAlign="center"dictTypeId="YesOrNo">是否最高额</div>
					<div field="BEGIN_DATE" headerAlign="center" allowSort="true">担保合同起期</div>
					<div field="END_DATE" headerAlign="center" allowSort="true">担保合同止期</div>
					<div field="USER_NUM" headerAlign="center" allowSort="true"dictTypeId="user">经办人</div>
					<div field="ORG_NUM" headerAlign="center" dictTypeId="org">经办机构</div>
				</div>
			</div>
</fieldset>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("grid");
		query();
		function query() {
			var clsDict = [
		        	{"dictname":"--请选择--","dictid":""},
		        	{"dictname":"抵押","dictid":"01"},
		        	{"dictname":"质押","dictid":"02"},
		        	{"dictname":"保证金","dictid":"03"},
		        	{"dictname":"保证","dictid":"04"}
		        ];
		    nui.get("map.subContractType").setData(clsDict);
			var o = form.getData();
			o.map.partyId="<%=request.getParameter("partyId")%>";
			grid.load(o);
		}

	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
			}
		});

	function edit(v) {
		var row = grid.getSelected();
		if (row) {
				var json = nui.encode({
					"subcontractId" : row.SUBCONTRACT_ID,
					"op" : v
				});
				if (v == "1") {
					msg = exeRule("XFE_0006", "1", {"subcontractId" : row.SUBCONTRACT_ID});
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
					//校验是否已经有在途的担保合同调整
					var json1 = {"subcontractId" : row.SUBCONTRACT_ID};
					msg = exeRule("SUBCON_FLOW", "1", json1);
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
					//校验是否已经有在途的主合同调整
					var json1 = {"subcontractId" : row.SUBCONTRACT_ID};
					msg = exeRule("RGRT_0006", "1", json1);
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
					
				}
				$.ajax({
					url : "com.bos.grt.subContractManage.subContract.createFlow.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						if (text.msg) {
							alert(text.msg);
							return;
						}
						openSubmitView(text.response, v,row.SUBCONTRACT_TYPE);
					},
					error : function() {
						nui.alert("操作失败！");
					}
				});

			} else {
				alert("请选中一条记录");
			}
		}

		function remove() {
			var row = grid.getSelected();
			if (row) {
				//保证人担保范围为本金，所关联的业务合同本金均结清时，担保合同可通过从合同调整手工失效
				var para = {"subcontractId" : row.SUBCONTRACT_ID};
				msg = exeRule("SUBCON_0007", "1", para);
				if (null != msg && '' != msg) {//担保范围为本金
					nui.alert(msg);
					return;
				}
				//有在途的担保合同调整流程
				var json1 = {"subcontractId" : row.SUBCONTRACT_ID};
				msg = exeRule("SUBCON_FLOW", "1", json1);
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
				
				nui.confirm("确定将该从合同失效吗？", "确认", function(action) {
					if (action != "ok")
						return;
					var json = nui.encode({"subcontractId" : row.SUBCONTRACT_ID});
					$.ajax({
						url : "com.bos.grt.subContractManage.subContract.delSubcontract.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg);
								return;
							}
							query();
						},
						error : function() {
							nui.alert("操作失败！");
						}
					});
				});
			} else {
				nui.alert("请选中一条记录");
			}
		}
		//弹出审批意见页面
		function openSubmitView(response, v,type) {
			var url = "/grt/subContractManage/sub_con_tree.jsp?bizId=" + response.bizId + "&processInstId="
					+ response.processInstId + "&proFlag=" + v + "&isSrc=2&subconractType="+type;
			nui.open({
				url : nui.context + url,
				showMaxButton : true,
				title : "担保合同调整",
				state : "max",
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					query();
				}
			});
		}

		//重置
		function reset() {
			form.reset();
		}
	</script>
</body>
</html>
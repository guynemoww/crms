<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-6
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<title>对公客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="公司客户">
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<div class="nui-dynpanel" columns="6">
					<label>客户名称：</label>
					<input name="item.partyName" required="false" class="nui-textbox nui-form-input" />

					<label>统一社会信用代码：</label>
					<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false" />

					<label>营业执照：</label>
					<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" required="false" />

					<label>组织机构代码：</label>
					<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input" />

					<label>中征码：</label>
					<input id="item.middelCode" name="item.middelCode" required="false" class="nui-textbox nui-form-input" />

				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div style="width: 99.5%">
				<div class="nui-toolbar" style="width: 99.5% border-top:1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
					<a id="addCust" style="margin-left: 5px" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
					<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit(2)">编辑</a>
					<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="query(1)">查看</a>
					<!-- <a class="nui-button" iconCls="icon-addnew" onclick="createIdentifyFlow">企业规模认定</a> -->
					<!-- <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
				</div>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto;" sortMode="client" url="com.bos.csm.corporation.corporation.getCorporationList.biz.ext" dataField="items" allowResize="true" showReloadButton="false" allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="partyName" headerAlign="center" allowSort="true" autoEscape="false" width="20%">客户名称</div>
					<div field="areaType" headerAlign="center" allowSort="true" align="center" dictTypeId="XD_KHCD0210">区域类型</div>
					<div field="corpCustomerTypeCd" headerAlign="center" align="center" allowSort="true" dictTypeId="XD_KHCD0252">客户性质</div>
					<div field="registerCd" headerAlign="center" align="center" allowSort="true" width="10%">营业执照</div>
					<div field="unifySocietyCreditNum" headerAlign="center" align="center" allowSort="true" width="10%">统一社会信用代码</div>
					<div field="orgRegisterCd" headerAlign="center" align="center" allowSort="true">组织机构代码</div>
					<div field="middelCode" headerAlign="center" align="center" allowSort="true" width="10%">中征码</div>
					<div field="isPotentialCust" headerAlign="center" align="center" allowSort="true" dictTypeId="YesOrNo">是否信贷客户</div>
					<div field="isThirdCust" headerAlign="center" align="center" allowSort="true" dictTypeId="YesOrNo">是否第三方客户</div>
					<div field="thirdCustTypeCd" headerAlign="center" align="center" allowSort="true" dictTypeId="XD_KHCD7001">第三方客户类型</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");

		function search() {
			var data = form.getData(); //获取表单多个控件的数据
			grid.load(data);
		}
		search();

		function reset() {
			form.reset();
		}

		grid
				.on(
						"preload",
						function(e) {
							if (!e.data || e.data.length < 1) {
								return;
							}
							for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
								e.data[i].partyNameReal = e.data[i].partyName;
								e.data[i]['partyName'] = '<a href="#" onclick="toGoCustDetail(\''
										+ e.data[i].partyId
										+ '\');">'
										+ e.data[i]['partyName'] + '</a>';
							}
						});

		function add() {
			nui
					.open({
						url : nui.context
								+ '/csm/corporation/csm_key_messages_add.jsp',
						showMaxButton : true,
						title : "添加公司客户",
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

		//qote=2 修改权限
		function edit(v) {
			var row = grid.getSelected();
			if (row) {
				var url = "/csm/corporation/csm_corporation_tree.jsp?partyId="
						+ row.partyId + "&qote=" + v + "&partyNum="
						+ row.partyNum;
				//客户类型为企业客户
				var custType = row.corpCustomerTypeCd;
				url += "&cusType=" + custType;
				nui
						.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "修改客户信息",
							width : 1024,
							height : 768,
							state : "max",
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
								//alert(text);
							},
							ondestroy : function(action) {
								search();
							}
						});

			} else {
				alert("请选中一条记录");
			}

		}

		//qote=1 查看权限
		function query(v) {
			var row = grid.getSelected();
			if (row) {
				var url = "/csm/corporation/csm_corporation_tree.jsp?partyId="
						+ row.partyId + "&qote=" + v + "&partyNum="
						+ row.partyNum;
				//客户类型为企业客户
				var custType = row.corpCustomerTypeCd;
				url += "&cusType=" + custType;
				//git.go(url);取消页面跳转
				nui
						.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "查看客户信息",
							width : 1024,
							height : 768,
							state : "max",
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
								//alert(text);
							},
							ondestroy : function(action) {
								//search();
							}
						});
			} else {
				alert("请选中一条记录");
			}
		}

		//ECIF同步接口
		function SynchronizationEcif() {
			var row = grid.getSelected();
			if (row) {
				var json = nui.encode({
					"partyId" : row.partyId,
					"ecifPartyNum" : row.ecifPartyNum
				});
				//校验法人信息是否填写完整
				//      		 var json1 = {"partyId" : row.partyId};
				//      		msg = exeRule("CUS_FR", "1", json1);
				// 				if (null != msg && '' != msg) {
				// 					nui.alert(msg);
				// 					return;
				// 			}
				$
						.ajax({
							url : "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
							type : 'POST',
							data : json,
							cache : false,
							contentType : 'text/json',
							success : function(text) {
								if (text.errMsg) {
									alert(text.errMsg);
								} else {
									if (text.ecifNum) {
										alert("同步成功!当前客户ECIF编号为:"
												+ text.ecifNum);
									} else {
										alert("当前客户暂无ECIF信息");
									}
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								git.unmask();
								nui.alert(jqXHR.responseText);
							}
						});
			} else {
				alert("请选中一条记录");
			}
		}
		
		function createIdentifyFlow() {
			var row = grid.getSelected();
			if (row) {
				//规模认定时，判断客户是否已保存行业类别
				 var json1 = {"partyId" : row.partyId};
			   		msg = exeRule("RCSM_1007", "1", json1);
					if (null != msg && '' != msg) {
						nui.alert(msg);
			 			return;
					}
				nui.confirm("是否确定创建客户["+row.partyNameReal+"]的企业规模认定流程？","询问",function(action){
					if(action!='ok'){
						return;
					}
					var json = nui.encode({"partyId":row.partyId});
					$
						.ajax({
							url : "com.bos.csm.corporation.corporation.createIdentifyCorpScale.biz.ext",
							type : 'POST',
							data : json,
							cache : false,
							async : false,
							contentType : 'text/json',
							success : function(data) {
								if (data.scale && !data.scale.msg) {
									nui.open({
										url : nui.context + "/csm/corporation/identify/identifyCorpScaleTree.jsp?bizId="
												+ data.scale.id + "&wflow=2&edit=1&setValue=true&processInstId="
												+ data.scale.processId,
										title : "企业规模认定 ",
										width : 1024,
										height : 768
									});
								} else {
									alert(data.scale.msg);
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								nui.alert(jqXHR.responseText);
							}
						});
				});
			}
		}
	</script>
</body>
</html>

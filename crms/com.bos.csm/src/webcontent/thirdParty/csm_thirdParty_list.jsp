<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
-->
<head>
<title>合作中介客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="合作中介客户">
				<div id="form1" class="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
					<div class="nui-dynpanel" columns="6">
					<label>合作中介类型：</label>
					<input id="item.corpCustomerTypeCd"name="item.corpCustomerTypeCd" dictTypeId="XD_KHCD7002"
						class="nui-dictcombobox nui-form-input" required="false"/> 
					
					<label>客户名称：</label> 
					<input id="item.partyName" name="item.partyName" required="false"class="nui-textbox nui-form-input" />

					<label>统一社会信用代码：</label>
					<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false"/>
								
					<label>营业执照：</label>
					<input id="item.registrCd" name="item.registrCd" required="false" class="nui-textbox nui-form-input" /> 
				
					<label>组织机构代码：</label> 
					<input id="item.orgRegisterCd"name="item.orgRegisterCd" required="false"class="nui-textbox nui-form-input" />
					
					<label>中征码：</label>
					<input id="item.middelCode" name="item.middelCode"
						required="false" class="nui-textbox nui-form-input" />
					</div>
					
					<div class="nui-toolbar" style="text-align: right; border: none">
						<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
					 <a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
					</div>
				</div>
				<div style="width: 99.5%">
					<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
						<a id="addCust" style="margin-left: 5px" class="nui-button" iconCls="icon-add" onclick="add()">新增</a>
						<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a> 
						<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit(2)">编辑</a> 
<!-- 						<a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto;" sortMode="client"
					url="com.bos.csm.thirdParty.thirdParty.getThirdPartyList.biz.ext"
					dataField="items" allowAlternating="true" multiSelect="false"
					showEmptyText="true" showPager="true" emptyText="没有查到数据"
					showReloadButton="false" showColumnsMenu="true" onrowdblclick=""
					allowCellEdit="true" allowCellSelect="true"
					sizeList="[10,20,50,100]" pageSize="10">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="corpCustomerTypeCd" headerAlign="center" allowSort="true"dictTypeId="XD_KHCD7002">合作中介类型</div>
						<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
						<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true">统一社会信用代码</div>
						<div field="registrCd" headerAlign="center" allowSort="true">营业执照</div>
						<div field="orgRegisterCd" headerAlign="center" allowSort="true">组织机构代码</div>
						<div field="middelCode" headerAlign="center" allowSort="true">中征码</div>
					</div>
				</div>
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");

		function search() {
			if (form.isValid() == false) {
				return;
			}
			var data = form.getData(); //获取表单多个控件的数据
			grid.load(data);
		}
		search();

	 grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				e.data[i]['PARTYNAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTYID+ '\');">'+e.data[i]['PARTYNAME']+'</a>';
			}
		});

		function reset() {
			form.reset();
			search();
		}

		function add() {
			nui.open({
				url : nui.context + '/csm/thirdParty/csm_thirdParty_add.jsp',
				showMaxButton : true,
				title : "添加合作中介客户",
				width : 800,
				height : 300,
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
				var url = "/csm/thirdParty/csm_thirdParty_tree.jsp?partyId="
						+ row.partyId + "&qote="+v+"&partyNum="+row.partyNum;
				nui.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "修改客户信息",
							width : 1024,
							height : 768,
							state : "max",
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

			} else {
				alert("请选中一条记录");
			}

		}


		function remove() {
			var row = grid.getSelected();

			if (row) {
				nui
						.confirm(
								"确定删除吗？",
								"确认",
								function(action) {
									if (action != "ok")
										return;
									git.mask();
									var json = nui.encode({
										"partyId" : row.partyId
									});
									$
											.ajax({
												url : "com.bos.csm.thirdParty.thirdParty.delThirdParty.biz.ext",
												type : 'POST',
												data : json,
												cache : false,
												contentType : 'text/json',
												success : function(text) {
													git.unmask();
													if (text.msg) {
														nui.alert(text.msg);
														return;
													}
													grid.reload();
												},
												error : function() {
													git.unmask();
													nui.alert("操作失败！");
												}
											});
								});
			} else {
				nui.alert("请选中一条记录");
			}
		}
		
   //ECIF同步接口
     function SynchronizationEcif(){
     		 var row = grid.getSelected();
     		 var json = nui.encode({"partyId":row.partyId,"ecifPartyNum":row.ecifPartyNum});
	     	$.ajax({
				url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				if (text.errMsg) {
					alert(text.errMsg);
				} else {
					if (text.ecifNum) {
						alert("同步成功!当前客户ECIF编号为:" + text.ecifNum);
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
	}
	</script>
</body>
</html>

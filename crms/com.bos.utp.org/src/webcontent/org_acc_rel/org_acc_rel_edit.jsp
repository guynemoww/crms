<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="formOrg" style="width:99.5%;height:auto;overflow:hidden;">
	<fieldset>
		<input id="orgAccRel.orgRelId" name="orgAccRel.orgRelId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4" id="div_2">
			<label>机构名称：</label>
			<input id="orgAccRel.oprOrgId" name="orgAccRel.oprOrgId" class="nui-buttonEdit" allowInput="false" required="true" dictTypeId="org" onbuttonclick="selectOprOrg" />
			<label>机构编号：</label>
			<input id="orgAccRel.oprOrgNo" name="orgAccRel.oprOrgNo" class="nui-text" allowInput="false" required="true" enabled="false" />
			<label>账务机构名称：</label>
			<input id="orgAccRel.accOrgId" name="orgAccRel.accOrgId" class="nui-buttonEdit" allowInput="false" required="true" dictTypeId="org" onbuttonclick="selectAccOrg" />
			<label>账务机构编号：</label>
			<input id="orgAccRel.accOrgNo" name="orgAccRel.accOrgNo" class="nui-text" allowInput="false" required="true" enabled="false" />
			<label>产品：</label>
			<input id="orgAccRel.productId" name="orgAccRel.productId" class="nui-buttonEdit" allowInput="false" dictTypeId="product" onbuttonclick="selectProduct(this,'orgAccRel.productNo')" />
			<label>产品编号：</label>
			<input id="orgAccRel.productNo" name="orgAccRel.productNo" class="nui-text"/>
			<label>入账类型：</label>
			<input id="orgAccRel.col2" name="orgAccRel.col2" required="true" dictTypeId="XD_RZLX" class="nui-dictcombobox nui-form-input" />
			<label>是否手工数据：</label>
			<input id="orgAccRel.col1" name="orgAccRel.col1" required="true" dictTypeId="XD_0002" class="nui-dictcombobox nui-form-input" />
			<label>是否启用：</label>
			<input id="orgAccRel.status" name="orgAccRel.status" required="true" dictTypeId="XD_0002" class="nui-dictcombobox nui-form-input" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;padding-top:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>

<script type="text/javascript">
	debugger;
 	nui.parse();
	var form = new nui.Form("#formOrg");
	var orgAccRelId = "<%=request.getParameter("orgAccRelId") %>";
	nui.get("orgAccRel.orgRelId").setValue(orgAccRelId);
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	initForm();
	
	function initForm() {
		var json=nui.encode({"orgAccRelId":orgAccRelId});
		$.ajax({
			url : "com.bos.utp.org.OrgAccRelManager.getOrgAccRel.biz.ext",
			type : 'POST',
			data : json,
			contentType : 'text/json',
			cache : false,
			success : function(mydata) {
				var o = nui.decode(mydata);
				o.orgAccRel.accOrgId2 = o.orgAccRel.accOrgId;
				form.setData(o);
			}
		});
	}
	
	function selectOprOrg() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 800,
			height : 500,
			ondestroy : function(action) {
				debugger;
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					if (data) {
						btnEdit.setText(data.orgname);
						btnEdit.setValue(data.orgid);
						nui.get("orgAccRel.oprOrgNo").setValue(data.orgcode);
					}
				}
			}
		});
	}
	
	function selectAccOrg() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 800,
			height : 500,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					if (data) {
						btnEdit.setText(data.orgname);
						btnEdit.setValue(data.orgid);
						nui.get("orgAccRel.accOrgNo").setValue(data.orgcode);
					}
				}
			}
		});
	}
			//产品树
	function selectProduct(e, noName) {
		var btnEdit = e;
		nui
				.open({
					url : nui.context
							+ "/pub/product/product/select_product_tree.jsp?selectEvery=1&searchMode=random",
					title : "选择",
					width : 800,
					height : 450,
					ondestroy : function(action) {
						if (action == "ok") {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							if (data) {
								btnEdit.setText(null);
								btnEdit.setValue(data.productId);
								nui.get(noName).setValue(data.productCd);
							}
						}
					}
				});
		}
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请按规则填写信息");
			return;
		}
		form.loading("正在保存数据");
		var o = form.getData();
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.utp.org.OrgAccRelManager.saveOrgAccRel.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(data) {
				if (data.msg) {
					alert(data.msg);
					return;
				}
				alert("保存成功！");
				CloseWindow("ok");
			}
		});
	}
</script>
</body>
</html>

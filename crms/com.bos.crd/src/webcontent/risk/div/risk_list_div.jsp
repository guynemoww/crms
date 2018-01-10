<%@page pageEncoding="UTF-8"%>
<%
	String edit = request.getParameter("edit");
	boolean enabled = !"2".equals(edit);
	boolean editByGroup = "3".equals(edit);
	boolean showToolbar = enabled && !editByGroup;
%>
<div class="nui-fit">
	<div id="riskForm" style="padding-left: 10px">
		<%
			if (editByGroup) {
		%>
		<a class="nui-button" onclick="editRisk(3)">添加新类型</a>
		<%
			}
		%>
		<label>限额类型：</label>
		<input name="item.limitType" id="item.limitType" style="width: 150px" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0238" />
		<label>限额內容：</label>
		<input name="item.limitCodeText" id="item.limitCodeText" style="width: 150px" required="false" class="nui-textbox nui-form-input" />
		<a class="nui-button" onclick="searchRisk">搜索</a>
		<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.crd.LimitService.searchRiskLimit" />
	</div>
	<%
		if (showToolbar) {
	%>
	<div class="nui-toolbar">
		<a class="nui-button" iconCls="icon-add" enabled="<%=enabled%>" onclick="editRisk(0)">添加</a>
		<a class="nui-button" iconCls="icon-edit" enabled="<%=enabled%>" onclick="editRisk(1)">编辑</a>
		<a class="nui-button" iconCls="icon-node" enabled="<%=enabled%>" onclick="editRisk(2)">查看</a>
		<!-- <a class="nui-button" id="" iconCls="icon-remove" enabled="<%=enabled%>" onclick="removeRisk()">删除</a> -->
	</div>
	<%
		}
	%>
	<div class="nui-fit">
		<div id="riskGrid" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false" pageSize="50" sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="LIMIT_TYPE" dictTypeId="XD_SXYW0238">限额类型</div>
				<div field="LIMIT_CODE_TEXT" renderer="rendererCode">限额内容</div>
				<div field="LIMIT_AMT" headerAlign="center" dataType="currency">分配额度</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchRisk() {
		var riskForm = new nui.Form("#riskForm");
		var riskGrid = nui.get("riskGrid");
		riskGrid.load(riskForm.getData());
	}
<%if (enabled) {%>
	function editRisk(type) {
		var riskGrid = nui.get("riskGrid");
		var riskId;
		if (type != 0 && type != 3) {
			var row = riskGrid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔限额信息!");
				return false;
			}
			riskId = row.LIMIT_ID;
		}
		nui.open({
			url : nui.context + "/crd/risk/risk_limit_edit.jsp?edit=" + type
					+ (riskId ? "&limitId=" + riskId : ""),
			showMaxButton : true,
			title : "提示：可点击最大化按钮放大此窗口",
			width : "800",
			height : "400",
			ondestroy : function(e) {
				riskGrid.reload();
				top.bizConWin = this;
			}
		})
	}
	function removeRisk() {
		var riskGrid = nui.get("riskGrid");
		var row = riskGrid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔限额信息!");
			return false;
		}
		var json = nui.encode({
			"limitId" : row.LIMIT_ID
		});
		$.ajax({
			url : "com.bos.crd.LimitService.removeRiskLimit.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				alert("删除成功！");
				searchRisk();
			}
		});
	}
<%}%>
	
</script>

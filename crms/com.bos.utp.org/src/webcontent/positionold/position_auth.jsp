<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-13 20:19:14
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>岗位授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
	<style type="text/css">
		#positionToolBar{
			width: 100%;
			margin: 0px;
			border: 0px none transparent;
			border-collapse: collapse;
		}
		#positionToolBar td{
			padding: 0px;
			border: 1px solid transparent;
		}
	</style>
</head>
<body>
	<div class="nui-fit" style="padding:10px;">
		<div id="positionForm">
			<div class="nui-toolbar" style="text-align:left;line-height:30px;padding:5px 0px 5px 10px;" borderStyle="border-bottom:0;">
				<table id="positionToolBar">
					<tr>
						<td style="width:1px;"></td>
						<td style="width:60px;"><a class="nui-button" iconCls="icon-save" onclick="saving();">保存</a></td>
						<td></td>
						<td style="width:60px; text-align:right;">机构名称：</td>
						<td style="width:100px;">
							<input class="nui-textbox" name="criteria._expr[0].Posiname" style="width:100px;" />
							<input class="nui-hidden" name="criteria._expr[0]._op" value="like" />
							<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" />
							<input class="nui-hidden" name="roleId" value="<%= request.getParameter("roleId") %>" />
						</td>
						<td style="width:70px; text-align:right;"><input class="nui-button" iconCls="icon-search" text="查询" onclick="searchPosition" /></td>
						<td style="width:10px;"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="nui-fit">
			<div id="positionGrid" class="nui-datagrid" style="width:100%;height:100%;"
				url="com.bos.utp.org.positionAuth.getPositionAuth.biz.ext"
				dataField="data" idField="positionid" allowResize="false" sizeList="[10,20,30]" pageSize="20" multiSelect="true" allowCellEdit="true">
			    <div property="columns">
			        <div field="posicode" width="120" headerAlign="center" >岗位编号</div>
			        <div field="posiname" width="120" headerAlign="center" >岗位名称</div>
			        <div type="checkboxcolumn" field="auth" width="120" headerAlign="center" trueValue="1" falseValue="">是否授权</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var positionGrid = nui.get("positionGrid");
	var pageSize = positionGrid.getPageSize();
	<% if(null != request.getParameter("roleId") && !"".equals(request.getParameter("roleId"))){ %>
		var roleIdData = "<%= request.getParameter("roleId") %>";
		var sendData = {"page":{"begin":0,"length":pageSize},"roleId":roleIdData};
		positionGrid.load(sendData);
	<% } %>

	function saving(){
		var positionWithAuth = [];
		var positionNoAuth = [];
		var positionData = positionGrid.getData();
		for(var i = 0; i < positionData.length; i++){
			var fieldNode = {partyTypeID:"position", id:positionData[i].positionid, code:positionData[i].posicode, name:positionData[i].posiname};
			if(positionData[i].auth == "1"){
				positionWithAuth.push(fieldNode);
			}else{
				positionNoAuth.push(fieldNode);
			}
		}
		var sendData = {roleId:"<%= request.getParameter("roleId") %>", partyWithAuth:positionWithAuth, partyNoAuth:positionNoAuth};
		$.ajax({
			url:"com.bos.utp.org.authForParty.storePartyAuth.biz.ext",
			type: "POST",
			data: nui.encode(sendData),
			cache: false,
			contentType: "text/json",
			success: function(text){
				var returnJson = nui.decode(text);
				if(returnJson.saveResult){
					nui.alert("权限设置成功");
				}else{
					nui.alert("权限设置失败");
				}
			},
			error: function(jqXHR, textStatus, errorThrown){}
		});
	}

	function searchPosition(){
		var positionForm = new nui.Form("#positionForm");
		var positionFormData = positionForm.getData(false, true);
        positionGrid.load(positionFormData);
	}
</script>

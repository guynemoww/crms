<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): guyan
  - Date: 2014-11-29 14:00:00
  - Description:
-->
<head>
<title>机构角色列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<body>
<div class="nui-fit" style="width:100%;height:100%;">
	<div class="nui-splitter" style="width:100%;height:100%;">
		<div showCollapseButton="true" size="40%" style="height:100%;">
			<div id="orgpanel" class="mini-panel" title="机构" style="width:100%;height:100%;" 
			    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
				<ul id="tree" class="nui-tree" url="com.bos.utp.org.organization.queryOrgNodeOfSelect.biz.ext" style="width:100%;height:100%;" 
					showTreeIcon="false" textField="nodeName" idField="nodeId" dataField="treeNodes" checkRecursive="false" 
					onnodeclick="nodeClick" >
			    </ul>
			</div>
		</div>
		<div showCollapseButton="true" style="height:100%">
			<div id="posipanel" class="mini-panel" title="角色" style="width:100%;height:100%;" 
			    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
			    <div id="form2">
					<div id="posiGrid" class="nui-datagrid" url="com.bos.utp.org.employee.queryRolesByOrgId.biz.ext"
						idField="ROLEID" multiSelect="true" allowAlternating="true"  pageSize="50" 
						sortMode="client" >
						<div property="columns">
							<div type="checkcolumn" width="8%">选择</div>
							<div field="ROLEID" width="30%" headerAlign="center" allowSort="true">角色编码</div>
							<div field="ROLENAME" width="62%" headerAlign="center" allowSort="true">角色名称</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-save" onclick="save" >保存</a>
</div>
<script type="text/javascript">
	var empid = '<%=request.getParameter("empid") %>';
	var inorgid = '<%=request.getParameter("inorgid") %>';
	nui.parse();
	var orgid = '';
	
	var posiGrid = nui.get("posiGrid");
	
	function nodeClick(e) {
		orgid = e.node.nodeId;
		if (orgid == inorgid) {
			nui.alert('这是该用户主归属机构，请重新选择!');
			orgid = '';
		} else {
			posiGrid.load({'orgId':orgid});
		}
	}
	
	function save() {
		if(orgid=="") {
			nui.alert("请先选择所属机构!");
			return;
		}
		
		var gridSelVals = posiGrid.getSelecteds();
		var posis = [];
    	//所有选中角色
    	for(var i = 0; i < gridSelVals.length; i++){
    		posis.push(gridSelVals[i].ROLEID);
    	}
    	
    	var json = nui.encode({empId:empid,orgId:orgid,roles:posis});
    	$.ajax({
            url: "com.bos.utp.org.employee.saveEmpOrgRoles.biz.ext",
            data:json,
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if(text.res==1){
                	nui.ajax({
                     	url: "com.bos.utp.org.collService.CollServiceImplServiceService.addCollRoleByWebService.biz.ext",
                    	type: "post",
                     	data: "{selroleids:"+posis+",empId:"+empid+",orgId:"+orgid+"}", 
                     	cache: false,
                     	contentType: 'text/json',
                     	success: function (text) {
	                       	nui.alert('分配成功！', '系统提示', function(action){
	                			window.CloseOwnerWindow('ok');
							}); 
                     	},
                    	error: function () {
                     	}
                 	});
                } else {
                	nui.alert("保存失败");
                }
            },
            error: function () {
            }
        });
	}
	
</script>
</body>
</html>
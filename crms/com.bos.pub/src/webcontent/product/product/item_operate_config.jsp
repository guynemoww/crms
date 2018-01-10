<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>品种权限管理</title>
</head>
<body>
<div class="nui-splitter" style="width:100%;height:100%;">
<div size="50%" showCollapseButton="true" >
    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
        <table>
            <tr>
                <td>
                    <a class="nui-button" iconCls="icon-add" onclick="addRow()" plain="true">增加</a>
                    <a class="nui-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                    <span class="separator"></span>
                    <a class="nui-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>   
                    <span class="separator"></span>         
                </td>
                <td style="white-space:nowrap;">
                    <input id="orgname" class="nui-textbox" emptyText="请输入机构名称" style="width:150px;" onenter="onKeyEnter"/> 
                    <a class="nui-button" onclick="search()">查询</a>
                </td>
            </tr>
        </table>           
    </div>
    <div id="datagrid1" class="nui-datagrid" style="width:100%;height:95%;"
        url="com.bos.pub.product.queryProductOperAuth.biz.ext" idField="id" dataField="poas"
        pageSize="15" allowCellValid="true" selectOnLoad="true"  onselectionchanged="selectedAuths"
        allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true" >
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="authOrgNum" name="authOrgNum" dictTypeId="org" width="30%" headerAlign="center" allowSort="true">机构名称
				<input id="authOrgNum" property="editor" class="nui-buttonEdit" onbuttonclick="selectOrg" allowInput="false" required="true"/>
			</div>
			<div field="roleId" name="roleId" type="comboboxcolumn"  width="30%" headerAlign="center" allowSort="true">角色名称
				<input property="editor" class="nui-combobox"  textField="rolename" valueField="roleid" 
		       url="com.bos.utp.resourcerights.RoleManager.queryRole.biz.ext" showNullItem="false" required="true" allowInput="true"
		        dataField="data"/>  
			</div>
			<div field="isUsed" type="checkboxcolumn"  trueValue="1" falseValue="0" width="10%" headerAlign="center" required="true">是否启用</div>
		</div>
	</div>
</div>
<div showCollapseButton="true">
    <div id="tabs1" class="nui-tabs" activeIndex="0" style="height:100%;width:100%" bodyStyle="padding:0;border:0;">
		<div id="region1" region="west" title="授信产品树" showHeader="true" class="sub-sidebar" style="width:100%;height:98%;" allowResize="true">
			<ul id="tree1" class="nui-tree" url="com.bos.pub.product.queryProductWithOperRela.biz.ext" style="width:100%;padding:5px;height:91%;" 
		        showTreeIcon="true" textField="productName" idField="productId" parentField="superiorId" resultAsTree="false"  
		        showCheckBox="true" checkRecursive="true" dataField="products" onbeforenodecheck="onBeforeNodeCheck" 
		        expandOnLoad="false" allowSelect="false" enableHotTrack="false">
		    </ul>
			<div class="nui-toolbar" style="text-align:center;padding-top:1px;padding-bottom:8px;"  borderStyle="border-left:0;border-bottom:0;border-right:0;">
		        <a class="nui-button" iconCls="icon-save" onclick="onRoleOk()">保存</a>
		   	</div>
		</div>
	</div>
</div>	  

</div>
</body>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var tree1 = nui.get("tree1");
	//加载角色
	grid.load();
	
	//查询操作授权
	function search(){
		var orgname = nui.get("orgname").getValue();
		grid.load({orgname:orgname});
	}
	
	var authId = "" ;
    //产品授权菜单查询(包括初始查询第一个角色菜单查询)
	function selectedAuths(e){
		var grid = e.sender;
	    var record = grid.getSelected() ;
	    //  alert(record.roleid);
	    if (record) {
	    	authId= record.orgOperateAuthorizationId ;
        	tree1.load({authId:authId});
	    } else {
	    	authId = "" ;
	    }
	}
	
	//在列表中新增一空白行
	function addRow() {          
        var newRow = { name: "New Row" };
        grid.addRow(newRow, 0);
    }
    //删除列表中一行记录
    function removeRow() {
        var rows = grid.getSelecteds();
        if (rows.length > 0) {
            grid.removeRows(rows, true);
        }
    }
    //保存列表中记录
    function saveData() {
        var data = {poas:grid.getChanges()};
        var json = nui.encode(data);
        
        grid.loading("保存中，请稍后......");
        nui.ajax({
            url: "com.bos.pub.product.saveProductOperAuth.biz.ext",
            type: 'POST',
            data: json,
            success: function (text) {
                grid.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }
    
    //选中机构树节点
	function onRoleOk() {
	    var node = tree1.getCheckedNodes();
	    if (node && tree1.isLeaf(node) == false) {
	        alert("不能只选中父菜单节点！");
	        return;
	    } else {
	    	var record = grid.getSelected() ;
	    	if (record==null || record=="") {
				nui.alert("请选择一条机构、角色信息！", "系统提示");
				return true ;
	    	}
	    	if(record.orgOperateAuthorizationId ==null ||record.orgOperateAuthorizationId ==""){
	    	
	    		nui.alert("请先保存机构、角色信息！", "系统提示");
				return true ;
	    	}
			var sendData = nui.encode({pors:node,authId:record.orgOperateAuthorizationId});
			tree1.loading("产品授权保存中,请稍等...");
			nui.ajax({
				url:"com.bos.pub.product.saveProductOperRela.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text);
					if(returnJson.exception == null){
						if (returnJson.retCode == "-1") {
							nui.alert("产品授权保存失败！", "系统提示");
						} else {
							nui.alert("产品授权保存成功！", "系统提示", function(action){
								grid.reload();
							});
						}
					}else{
						nui.alert("产品授权保存失败！", "系统提示");
					}
				}
			});
	    }
	}
    
    //机构选择
	function selectOrg(){
	
		var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	//菜单树节点点击处理
	function onBeforeNodeCheck(e) {
        var tree = e.sender;
        var node = e.node;
        if (tree.hasChildren(node)) {}
    }	
</script>	
</html>

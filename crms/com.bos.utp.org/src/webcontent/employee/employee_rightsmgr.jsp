<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): guyan
  - Date: 2014-04-21 18:36:52
  - Description:
-->
<head>
<title>人员权限管理</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-fit" style="padding:1px 1px 1px 1px;" >
<div class="nui-splitter" style="width:100%;height:100%;">
	<div showCollapseButton="true" size="40%" style="height:100%;overflow:auto;">
		<div id="orgpanel" class="mini-panel" title="所属机构" style="width:100%;height:100%;" 
		    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
			<ul id="tree" class="nui-tree" onbeforeload="onBeforeTreeLoad" style="width:100%;height:100%;" 
				showTreeIcon="false" textField="nodeName" idField="nodeId" dataField="treeNodes" showCheckBox="true" checkRecursive="false"
				onnodecheck="nodecheck" onload="onLoad" >
		    </ul>
		</div>
	</div>
	<div showCollapseButton="true" style="height:100%;overflow:auto;">
		<div id="rolepanel" class="mini-panel" title="所属角色" style="width:100%;height:100%;" 
		    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
		    <div  style="padding:1px 1px 1px 1px;" id="form2">
				<div id="roleGrid" class="nui-datagrid" style="width:100%;" url="com.bos.utp.org.employee.loadOrgRoles.biz.ext"
					idField="roleid" multiSelect="true" allowAlternating="true" showPager="false" sizeList="[15,20,30]" pageSize="20" 
					sortMode="client" onload="gridcheckdel" >
					<div property="columns">
						<div type="checkcolumn"></div>
						<div field="roleid" width="20%" headerAlign="center" allowSort="true">角色编码</div>
						<div field="rolename" width="30%" headerAlign="center" allowSort="true">角色名称</div>
						<div field="roletype" width="30%" headerAlign="center" dictTypeId="ABF_ROLETYPE" allowSort="true">角色类型</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;" borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save">保存</a>
</div>
<script type="text/javascript">
	var data=nui.decode('<%=request.getParameter("data") %>');
	nui.parse();
	//
	var tree = nui.get("tree");
	var roleGrid = nui.get("roleGrid");
	var datas = null;
	datas = data;
	//roleGrid.load();
	tree.load("com.bos.utp.org.organization.queryOrgNodeOfSelect.biz.ext");
	//标准方法接口定义
	/**
   	function SetData(data) {
    	if (data.action == "rigthsmgr") {
			//跨页面传递的数据对象，克隆后才可以安全使用
            data = nui.clone(data);
            datas = data;
           //  alert(nui.encode(datas));
			tree.load({orgids:data.orgids});
		}
	}
	**/
	//取消
    function cancel(e) {
        CloseWindow("cancel");
    }
    
    //树结点选择触发
    function nodecheck(e){
		var orgjson = nui.encode(getCheckedValues());
    	//主机构不允许除去
    	if(datas["inorgid"]==e.node.orgid) {
    		nui.alert("主归属机构不可移除！","提醒",function(action) {e.cancel=false;});
    		e.node.checked=true;
    	} else {
    		currentNode = e.node;
			nui.get("roleGrid").load({'orgids':orgjson});
    	}
    }
    
    //树加载前设置打勾的结点
    function onBeforeTreeLoad(e) {
    	if(null!=datas){
    		e.params.orgids = datas["orgids"];
    		var orgs = datas["orgids"].split(',');
    		setTimeout(function(){
	    		for (var i=0,len=orgs.length; i<len; i++) {
	    			tree.checkNode(tree.getNode(orgs[i]));
	    		}
    		},1500);
    	}
    }
    
    //树加载完后加载grid
    function onLoad(){
    	if(null!=datas){
	    	var orgjson = nui.encode(datas["orgids"]);
			nui.get("roleGrid").load({'orgids':orgjson});
    	}
    	/**setTimeout(function(){
	    	var rootNode = tree.getRootNode();
	    	if(null != rootNode) {
	    		var childNode = rootNode.children;
	    		if(null != childNode) {
	    			tree.loadNode(childNode[0]);
	    		}
	    	}
    	},500);**/
    }
    
    //树结点处理
    function treecheckdel(){
    	//获取所有树结点
		//var treenodes = nui.get("tree").getList();
		//用户拥有机构
		//var userorgids = datas["orgids"].split(",");
		/*for(var i = 0; i < treenodes.length; i++){
			if(userorgids.contains(treenodes[i].nodeId)){
				treenodes[i].checked=true;
			}else{
				treenodes[i].checked=false;
			}
		}*/
    }
    
    //表格行处理
    function gridcheckdel(){
    	//所有角色信息
    	var allgrid = roleGrid.getData();
    	 // alert("allgrid="+nui.encode(datas.roleids));
    	//用户的角色
    	if(datas.roleids==null){
    	// alert("wuquanxian");
    	}else{
		    	var userroleids = datas.roleids.split(",");
		    	//alert(nui.encode(userroleids));
		    	for(var i = 0; i < allgrid.length; i++){
		    		if(userroleids.contains(allgrid[i].roleid)){
		    			roleGrid.select(roleGrid.getRowByUID(allgrid[i]._uid));
		    		}else{
		    			roleGrid.deselect(roleGrid.getRowByUID(allgrid[i]._uid),false);
		    		}
		    	}
    	}
    	
    }
    
    //保存
    function save(){
    	//获取树选中的结点
		var orgjson = getCheckedValues();
		//获取所有树结点
		var treenodes = nui.get("tree").getList();
		//获取选中的角色
    	var gridSelVals = roleGrid.getSelecteds();
    	//所有角色信息
    	var allgrid = roleGrid.getData();
    	//操作员id
    	var operatorid = datas["operatorid"];
    	var empid = datas["empid"];
    	//主机构id
    	var inorgid = datas["inorgid"];
		var delorgjson = [];
		for(var i = 0; i < treenodes.length ; i++){
			if(!treenodes[i].checked){
				delorgjson.push(treenodes[i].nodeId);
			}
		}
    	
    	var delroleids = [];
    	var roleids = [];
    	//所有选中角色
    	for(var i = 0; i < gridSelVals.length; i++){
    		roleids.push(gridSelVals[i].roleid);
    	}
    	//所有未选中角色
    	for(var i = 0; i < allgrid.length; i++){
    		if(!roleids.contains(allgrid[i].roleid)){
    			delroleids.push(allgrid[i].roleid);
    		}
    	}
    	
    	var roleidsjson = "\""+roleids+"\"";
    	delroleids = ("\""+delroleids+"\"");
    	orgjson = ("\""+orgjson+"\"");
    	delorgjson = ("\""+delorgjson+"\"");
    	
    	$.ajax({
            url: "com.bos.utp.org.employee.saveUserRoleAndOrg.biz.ext",
            data:"{roleids:"+roleidsjson+",operatorid:"+operatorid+",empid:"+empid
            	+",inorgid:"+inorgid+",orgjson:"+orgjson+",delroleids:"+delroleids
            	+",delorgjson:"+delorgjson+"}",
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if(text.res==1){  
                	nui.alert("保存成功！", "系统提示", function(action){
					 top["win"].closeWin();
					});      			
                }else{
                	nui.alert("保存失败,请展开机构节点再进行保存");
                }
            },
            error: function () {
            }
        });
    }
    
    //获取树选中的结点
    function getCheckedValues(){
    	var nodes = tree.getCheckedNodes(false);
		var nodeids = [];
		for(var i = 0,l = nodes.length;i < l;i++){
			if(nodes[i].checked==true){
				nodeids.push(nodes[i].nodeId);
			}
		}
		//alert('nodeids='+nodeids);
		return nodeids;
    }

</script>
</body>
</html>
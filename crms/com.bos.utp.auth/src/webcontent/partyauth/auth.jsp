<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>权限信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-fit" style="padding:1px;" id="form1">
	<input id="nodeType2" name="nodeType2" class="nui-hidden" value=""   />
	<input id="partytype" class="nui-hidden" name="oPartyrole.partytype" allowInput="false" />
	<input id="partyid" class="nui-hidden" name="oPartyrole.partyid" allowInput="false" />
	<input class="nui-hidden" name="rolefunc._expr[1].roleid" id="rolefunRoleid" />
	<table style="height:99%;">
	   <tr>
	    <td style="width:120px;height:100%;">
	    	<div id="listbox1" class="nui-listbox" style="width:250px;height:100%;"
	             ajaxData="getAjaxData" multiSelect="true" textField="rolename" valueField="roleid"
	           	 dataField="oPartyroles2" onload="onAuthorizedLoad" onvaluechanged="onListBoxValueChaged" showCheckBox="true"
	           	 onitemclick="onitemDBclick" selectOnLoad="true" 
	           	>
	         	<div property="columns">
	               <div header="已授予角色" field="rolename" ></div>
	            </div>
	        </div>
	    </td>
	    <td style="width:100%;height:100%;text-align:center;">
	        <input id="cal" class="nui-button" text="取消" onclick="remove()" style="width:100px;margin-top:5px;"/><br />
	        <input id="calall" class="nui-button" text="全部取消" onclick="removeAll()" style="width:100px;margin-top:5px;"/><br />
	        <input id="graall" class="nui-button" text="全部授权" onclick="addAll()" style="width:100px;margin-top:5px;"/><br />
	        <input id="gra" class="nui-button" text="授权" onclick="add()" style="width:100px;margin-top:5px;"/><br />
	    </td>
	    <td style="width:120px;height:100%;">
		   <div id="listbox2" class="nui-listbox" style="width:250px;height:100%;"
			    textField="rolename" valueField="roleid" showCheckBox="true" ajaxData="getAjaxData" multiSelect="true"
			   dataField="omPartyroles2" onitemclick="onitemDBclick" >
				 <div property="columns">
				      <div header="未授予角色" field="rolename"></div>
				 </div>
			</div>
	    </td>
	    
	    <!-- 刘子良   添加：角色功能列表  createtime by 2013/09/06 -->
	    <td style="width:120px;height:100%;">
		   <div id="dgRoleList" class="nui-datagrid" style="width:250px;height:100%;" 
			    url="com.bos.utp.auth.partyauth.queryPartyRolefuncView.biz.ext" dataField="rolefuncs"  idField="funcname" allowResize="false"
			    sizeList="[10,20,50,100]">
			    <div property="columns">
			         <div type="indexcolumn" field="indexcolumn" width="50" headerAlign="center" allowSort="true">序号</div>
			        <div field="funcname" width="120" headerAlign="center" allowSort="true">功能名称</div>                   
			    </div>
			</div>   
	    </td>
	    
	    </tr>
	</table>
</div>
<%--<div id="toolBar" class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;visibility:hidden;" 
    borderStyle="border:0;">
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;" onclick="cancel">关闭</a>
</div>--%>
<script type="text/javascript">
	 nui.parse();
	 var form = new nui.Form("form1");
	 var listbox1 = nui.get("listbox1");
     var listbox2 = nui.get("listbox2");
     //var url1="org.gocom.components.coframe.auth.partyauth.partyauth.getAuthorizedRoleList.biz.ext";
     //var url2="org.gocom.components.coframe.auth.partyauth.partyauth.getUnauthorizedRoleList.biz.ext";
     var nodetype="";
     var url1="com.bos.utp.auth.partyauth.PartyRightManitain.biz.ext";
     var url2="com.bos.utp.auth.partyauth.PartyRightManitain.biz.ext";
    
    /*$(function(){
		if(window.parent.getCurrentNode){ // 从Tab页进入时
			var node = window.parent.getCurrentNode();
			nodetype=node.nodeType;
			var parentNode = node;
			window['parentNode'] = parentNode;
			loadAuth();
		}
	});*/
	
	//刘子良 20141120 修改
	$(function(){
		if(top["win"].getCurrentNode()){ // 从Tab页进入时
			var node = top["win"].getCurrentNode();
			nodetype=node.nodeType;
			var parentNode = node;
			window['parentNode'] = parentNode;
			loadAuth();
	}
	});
	
	function loadAuth(){
		//var party = getPartyByNode(window['parentNode']);
		var party = getPartyByNode(top["win"].getCurrentNode());//刘子良 20141120 修改
		//alert('dd='+nui.encode(party)+"=="+nodetype);
		if(party && party.id && party.partyTypeID) {
			listbox1.setUrl("com.bos.utp.auth.partyauth.PartyRightManitain.biz.ext?partytype="+party.partyTypeID+"&partyid="+party.id+"&nodeType2="+nodetype);
			listbox2.setUrl("com.bos.utp.auth.partyauth.PartyRightManitain.biz.ext?partytype="+party.partyTypeID+"&partyid="+party.id+"&nodeType2="+nodetype);
		}
		
	}
	
	//授权
    var items;
	function add() {
	    items=listbox2.getSelecteds();
	    if(items && items.length > 0){
		   	saveData(items, function(){
		   	});
	   	}else{
	    	nui.alert("至少选择一条记录");
	    }
	}
	
	
	//取消【删除】
	function remove() {
	    var items = convertToData(listbox1.getSelecteds());
	    if(items && items.length > 0){
		    deleteData(items, function(){
		    	listbox1.removeItems(items);
		        listbox2.addItems(items);
		        onAuthorizedLoad();
		    });
	    }else{
	    	nui.alert("至少选择一条记录");
	    }
	}
	
	//全部授权
	 function addAll() {
        var items = convertToData(listbox2.getData());
        if(items && items.length > 0){
	        saveData(items, function(){
		        listbox2.removeItems(items);
		        listbox1.addItems(items);
		        onAuthorizedLoad();
	        });
        }else{
	    	nui.alert("至少选择一条记录");
	    }
    }
    
    function removeAll() {
        var items = convertToData(listbox1.getData());
        if(items && items.length > 0){
	        deleteData(items, function(){
		        listbox1.removeItems(items);
		        listbox2.addItems(items);
		        onAuthorizedLoad();
	        });
        }else{
	    	nui.alert("至少选择一条记录");
	    }
    }
    
    function convertToData(items){
    	var datas = [];
    	if(!items || items.length == 0){
    		return datas;
    	}
    	for(var i=0,len=items.length;i<len;i++){
    		if(!noSelectStores[items[i].id]){
    			var item = items[i];
    			delete item.isManaged;
    			datas.push(item);
    		}
    	}
    	return datas;
    }
    //刘子良 修改 2013/10/17
    function saveData(items, fn){
		//var node = window.parentNode;
		var node = top["win"].getCurrentNode();//刘子良 20141120日 修改
		var nodeType2=nui.get("nodeType2").getValue();
		var json="";
	    if(nodeType2=="OrgEmployee"){
		    json = nui.encode({
		    	party2:getPartyByNode(node),
				roleList2:items,
				nodeType2:nodeType2   	
		    });
	    }else{
	        json = nui.encode({
		    	party:getPartyByNode(node),
				roleList:items  	
	    	});
	    }

	    $.ajax({ 
	    	url: "com.bos.utp.auth.partyauth.addPartyRole.biz.ext",
	    	cache: false,
	    	data: json,
	    	type: 'POST',
	    	contentType:'text/json',
	    	success: function (text) {
	    		if(text.retCode=='1'){
		    		nui.alert("授权成功");
		    		listbox2.removeItems(items);
			   	    listbox1.addItems(items);
			   	    onAuthorizedLoad();
	    		}else{
	    			nui.alert("授权失败!");
	    		}
            },
            error: function () {
            }
	    });
    }
	
	
	//点击 取消 调用
	//刘子良 修改 2013/10/17
	function deleteData(items, fn){
		//var node = window.parentNode;
		var node = top["win"].getCurrentNode();//刘子良 20141120日 修改
		var nodeType2=nui.get("nodeType2").getValue();
		var json="";
	    if(nodeType2=="OrgEmployee"){
		    json = nui.encode({
		    	party2:getPartyByNode(node),
				roleList2:items,
				nodeType2:nodeType2   	
		    });
	    }else{
	        json = nui.encode({
		    	party:getPartyByNode(node),
				roleList:items  	
	    	});
	    }
	    
	    /*var json = nui.encode({
	    	party:getPartyByNode(node),
	    	roleList:items
	    });*/
	    
	    $.ajax({
	    	url: "com.bos.utp.auth.partyauth.deletePartyRole.biz.ext",
	    	cache: false,
	    	data: json,
	    	type: 'POST',
	    	contentType:'text/json',
	    	success: function (text) {
	    		if(text.retCode=='1'){
		    		nui.alert("取消授权成功");
		    		fn();
	    		}else{
	    			nui.alert("取消授权失败!");
	    		}
            },
            error: function () {
            }
	    });
	}
	
	// 弹出窗口时调用
	function SetData(data){
		// 显示关闭按钮
		$("#toolBar").css("visibility", "visible");
		data = nui.clone(data);
		nui.get("nodeType2").setValue(data.nodeType);
		if(data && data.parentNode){
			window['parentNode'] = data.parentNode;
			loadAuth();
		}
	}
	
	function getPartyByNode(node){
		var party = {};
		if(!node) return party;
		if(node.nodeType=="OrgOrganization"){
	    	party = {
	    		id:node.orgid,
	    		partyTypeID:"org"
	    	}
	    }
	    if(node.nodeType=="OrgPosition"){
	    	party = {
	    		id:node.positionid,
	    		partyTypeID:"position"
	    	}
	    }
	    if(node.nodeType=="OrgEmployee"){
	    	party = {
	    		id:node.empid,
	    		operatorid:node.operatorid,
	    		partyTypeID:"emp"
	    	}
	    }
	    if(node.nodeType=="user"){
	        party = {
	            id:node.userId,
	            partyTypeID:"user"
	        }
	    }	
	    return party;	
	}
	
	function getAjaxData(){
		//var node = window.parentNode;
		var node = top["win"].getCurrentNode();//刘子良 20141120日 修改
		var party = getPartyByNode(node);
		var data = {
			partyId:party.id,
			partyType:party.partyTypeID
		};
		return data;
	}
	
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    
    function cancel() {
    	CloseWindow("cancel");
    }
    
    var noSelectStores = {};
    
    function renderAuthorizedRoleList(e) {
       //alert(e.record.acRole.rolename);
    	/*if(e.record.isManaged && e.record.isManaged != "true") {
    		noSelectStores[e.record.id] = e.record.id;
    		return '<span style="color:#CFCFCF">' + e.record.acRole.rolename + '</span>';
    	} else {
    		var item = e.item;
	    	delete item.isManaged;
	    	e.item = item;
    		return e.record.acRole.rolename;
    	}*/
    	//return e.record.name;
    }
    
     function onListBoxValueChaged(e) {
        var listbox = e.sender;
        var items = listbox.getSelecteds();
        for(var i=0,len=items.length;i<len;i++){
        	if(noSelectStores[items[i].id]){
	       	 	listbox.deselect(items[i]);
       	 	}
        }
    }
    
    function onAuthorizedLoad(e){
    	var checkboxs = $('#listbox1 .mini-listbox-checkbox :input');
    	checkboxs.each(function(){
    		if(existsRole(this)){
	    		$(this).hide();
    		}
    	});
    }
    
    function existsRole(checkbox){
    	var td = $(checkbox).parent();
    	var nextTd = td.next();
    	var span = nextTd.find("span")[0];
    	if(span){
    		return true;
    	}
    	return false;
    }
    
    
    //点击授权角色的时候,查询该角色对应的功能列表
    function onitemDBclick(e){
     var dgRoleList = nui.get("dgRoleList");
     var lb1 = e.sender;
     if(lb1.isSelected){
       var record=lb1.getSelected();
       if (record) {
           nui.get("rolefunRoleid").setValue(record.roleid);
          	//校验
			form.validate();
	        if (form.isValid()==false) return;
			var formData = form.getData(false, true);
    	    dgRoleList.load(formData);
    	}
     }
  
    }
    
    
</script>
</body>
</html>
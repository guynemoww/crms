<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 
  - Description:
-->
<head>
<title>查询用户</title>
<%@page
	import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>

<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
	<fieldset>
			<legend>
				<span>用户信息</span>
	 </legend>
		<div class="nui-dynpanel" columns="4">
			<label>用户编号：</label>
			<input id="" name="map.userNum"  class="nui-textbox"/>
			<label>用户名称：</label>
			<input id="" name="map.userName"  class="nui-textbox"/>
			<label>原所在机构：</label> 
     <input id="map.orgNum" name="map.orgNum" 
	text="<%=((UserObject) session.getAttribute("userObject"))
					.getUserOrgName()%>"
	value="<%=((UserObject) session.getAttribute("userObject"))
					.getUserOrgId()%>"
					
	allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg" />
	<% UserObject user = (UserObject)session.getAttribute("userObject");
						String manage = "";
						DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
									"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))){
										manage="true";
									}else{
										continue;
									}
								}	        			
						}
					%>
		</div>
			</fieldset>
		</div>
	
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search"   onclick="search()">查询</a>
    <a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" iconCls="icon-save" id="dataConfirm"  onclick="selected()">保存</a>
</div>


<div id="grid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.userMove.userMove.findUserList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false" pageSize="10"
	    sizeList="[10,20,50,100]" >
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="USERID" allowSort="true" width="20%" headerAlign="center"  align="center">用户编号</div>                
	        <div field="EMPNAME" allowSort="true" width="" headerAlign="center"  align="center">用户名称</div> 
	        <div field="ORGCODE" allowSort="true" width="" headerAlign="center" dictTypeId="org" align="center">机构名称</div>   
	     </div>
	</div>
	
<script type="text/javascript">
	nui.parse();
	 var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
     		var o = form.getData();
			grid.load(o);
    }
    search();

 

function selectEmpOrg(e) {
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
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
    
    function selected() {
		var row = grid.getSelected();
        if (row) {

			//判断是否有生效的业务
		    var json1 = {"userNum":row.USERID,"orgNum":row.ORGCODE};
		  	msg = exeRule("PUB_BIZ_USER","1",json1);
		  	   if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	  	     	return;
	  	     }
	  	     //判断用户是否有流程中的业务
        	var json1 = {"userNum":row.USERID,"orgNum":row.ORGCODE};
		  	msg = exeRule("PUB_0004_USER","1",json1);
	 	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	  	     	return;
	  	     }
	  	     
	  	 
			CloseWindow("ok");
		} else {
			alert("请选中一条记录");
		}
	}

	//机构选择
	function selectOrg() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					data = nui.clone(data);
					if (data) {
						self.orglevel = data.orglevel;
						btnEdit.setValue(data.orgid);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}

	function onSelectionChanged() {
		var row = grid.getSelected();
		//判断在途业务
		if (row) {
			alert(row.USERID);

			// 	        var json1 = {"partyid":row.partyid};
			// 	   	    msg = exerule("pub_0002","1",json1);
			// 	   	    if(null != msg && '' != msg){
			// 		   	     nui.alert(msg);
			// 		   	     grid.deselect (row);
			// 		   	     return;
		}

	}

	// 	}

	function getData() {
		var row = grid.getSelected();
		if (row) {
			return row;
		} else {
			return null;
		}
	}
	function reset() {
		form.reset();
	}
</script>
  
  
</body>
</html>
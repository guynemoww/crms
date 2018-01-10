<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):陈川
  - Date: 2016-05-10
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="客户列表" >
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="roleType" id="roleType" required="true"  class="nui-hidden" value="2"/>
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.transfer.transfer.customerShareList"/>
	<div class="nui-dynpanel" columns="6">
		<label class="nui-form-label">机构名称：</label>
		<input id="item.orgNum" name="item.orgNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrgQuery"/>
		<label>客户经理：</label>
		<input id="item.userNum" name="item.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
		<label>客户名称：</label>
		<input name="item.partyName" id="item.partyName" required="false" class="nui-textbox nui-form-input"/>
		<label>客户类型：</label>
		<input id="item.partyTypeCd" name="item.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001"  allowInput="false" />
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" required="false" />
		<label>中征码：</label>
		<input id="item.middleCode" name="item.middleCode" class="nui-textbox nui-form-input" required="false" />
	</div>
	<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;"  borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
    	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	</div>
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-add" onclick="move()">客户共享</a>
			<a class="nui-button" iconCls="icon-zoomin" onclick="moveHistory()">历史共享记录</a>		
	</div>

	<div id="grid1" class="nui-datagrid" class="nui-datagrid" style="width:99.5%;height:auto;"sortMode="client"
				url="com.bos.csm.pub.ibatis.getItem.biz.ext"
				dataField="items" allowAlternating="true" 
				allowResize="true" showReloadButton="false" 
				sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client" >
		<div property="columns">
		<div type="checkcolumn" ></div>
		<div field="orgNum" allowSort="true" width="" headerAlign="center" dictTypeId="org">机构名称</div> 
		<div field="partyNumLink" allowSort="true" width="" headerAlign="center" >客户编号</div>      
		<div field="partyNameLink" allowSort="true" width="" headerAlign="center" >客户名称</div>                
		<div field="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>   
		<div field="certNum" allowSort="true" width="" headerAlign="center" dictTypeId="">证件号码</div> 
		<div field="middleCode" allowSort="true" width="" headerAlign="center" dictTypeId="">中征码</div> 
		<div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div> 
	</div>
	</div>
</div>
</div>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	<%  UserObject user = (UserObject)session.getAttribute("userObject");
						String manage = "";
						DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
										"R1008".equals(role.get("roleid"))||"R1049".equals(role.get("roleid"))||
										"R1051".equals(role.get("roleid"))){
										manage="1";//客户经理
									}else if("R1010".equals(role.get("roleid"))){
										manage="2";//支行行长
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("1")){
					%>
						init("1");
					<% 
						}else if(manage.equals("2")){
					%>
						init("2");
					<%
						}
					%>
	function init(v){//客户经理进入页面时
		nui.get("item.orgNum").setEnabled(false);
		if(v=="1"){
			nui.get("item.userNum").setValue('<%=((UserObject)session.getAttribute("userObject")).getUserId() %>');
			nui.get("item.userNum").setText('<%=((UserObject)session.getAttribute("userObject")).getUserName() %>');
			nui.get("item.userNum").setEnabled(false);
		}
		nui.get('roleType').setValue(v);
	}
	
	query();
	function query() {
		if(nui.get("item.orgNum").getValue()==""){
			nui.get("item.orgNum").setValue("<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>");
			nui.get("item.orgNum").setText("<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>");
		}
		 //字典过滤
		var arr = git.getDictDataFilter("XD_KHCD1001", '01,02,05');
		nui.get("item.partyTypeCd").setData(arr);
		var o = form.getData();
		grid.load(o);
	}
	
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['partyNumLink']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyNum']+'</a>';	
       			e.data[i]['partyNameLink']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';	
       		}
       });
	function move() {
		var rows=grid.getSelecteds();
		var row = grid.getSelected();
		if (rows.length <= 0) {
			alert("至少选择一条记录");
			return;
		}
		for (var i = 0; i < rows.length; i++) {
			if(rows[0].userNum!=rows[i].userNum){
				alert("请选择同一客户经理的客户");
				grid.deselect(row);
				return;
			}
		}
			nui.open({
	        url: nui.context + "/csm/share/customer_share_add.jsp?oldUserNum="+rows[0].userNum+"&oldOrgNum="+rows[0].orgNum,
	        title: "客户共享", 
	        width: 800,
			height: 400,
	        allowResize:true,
			showMaxButton: true,
	        ondestroy: function (action) {
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = nui.clone(data);
	                if (data) {
	                	for(var i=0; i<rows.length;i++){
		            		var json1 = {"partyId":rows[i].partyId,"newOrgNum":data.newOrgNum,"newUserNum":data.newUserNum};
	   	   					msg = exeRule("PUB_CUS_SHARE","1",json1);
	   	    				if(null != msg && '' != msg){
								alert(msg);
	   	   		 				grid.deselect(rows[i]);
	   	   		 				return;
	   	    				}   
	   	 				 }
	                	var o= form.getData();
	                	o.newUserNum =data.newUserNum;
	                	o.newOrgNum = data.newOrgNum;
	                	o.transPerson= data.transPerson;
						o.oldOrgNum = rows[0].orgNum;
						o.oldUserNum =rows[0].userNum;
	    				var json = nui.encode({"item" : o,"rows" : rows});
						$.ajax({
							url : "com.bos.csm.transfer.share.addCustomerShare.biz.ext",
					        type: 'POST',
					        data: json,
					        contentType:'text/json',
					        cache: false,
					        success: function (text) {
				            		git.unmask("form1");
									if(text.msg){
										nui.alert(text.msg);
										query();
									} else {
										openSubmitView(text.response);
									}
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            alert(jqXHR.responseText);
					        }
				        });
	                }
	            }
	        }
	    });
	}
	
	//客户共享历史记录
     function moveHistory() {
        nui.open({
            url:  nui.context+"/csm/share/customer_share_history.jsp",
            title: "共享历史记录", 
            width: 1300, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
            }
        });
    }
	
	
	//弹出审批意见页面
	function openSubmitView(response){
		debugger;
		var url = nui.context + "/csm/share/customer_share_confirm_tree.jsp?bizId="
					+ response.bizId + "&processInstId="+response.processInstId+"&isSrc=2";
		nui.open({
	               url: url,
	               title: "客户共享", 
	               width: 550, 
	               height: 260,
	               state:"max",
	               onload: function () {
	               },
	               ondestroy: function (action) {
	               		CloseWindow('ok');
	               }
	           });
	}
	
	//查询机构选择
	function selectOrgQuery() {
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
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}
	// 客户经理
	function selectCustManegers(e) {
		var orgNum = nui.get("item.orgNum").getValue();
		if (orgNum == "") {
			nui.alert("请选择机构");
			return;
		} else {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum=" + orgNum,
				showMaxButton : true,
				title : "选择客户经理",
				width : 850,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						data = nui.clone(data);
						if (data) {
							btnEdit.setValue(data.userId);
							btnEdit.setText(data.empName);
						}
					}
				}
			});
		}
	}
	
	function reset(){
		if (nui.get("roleType").getValue() == "2") {
			nui.get("item.orgNum").setValue("<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>");
			nui.get("item.orgNum").setText("<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>");
			nui.get("item.userNum").setValue();
			nui.get("item.userNum").setText();
		}
		nui.get("item.partyName").setValue();
		nui.get("item.partyTypeCd").setValue();
		nui.get("item.certType").setValue();
		nui.get("item.certNum").setValue();
		nui.get("item.middleCode").setValue();
	}
</script>
</body>
</html>
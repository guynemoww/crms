<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):chenchuan
  - Date: 2016-04-15
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="transferWay" name="transferWay" required="true" allowInput="false"  class="nui-hidden nui-form-input" value="2" />
	<input name="roleType" id="roleType" required="true"  class="nui-hidden nui-form-input" value="2"/>
	
	<div class="nui-dynpanel" columns="4">
		<label>原所在机构：</label>
		<input name="oldOrgNum" id="oldOrgNum" required="true" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"  /> 
		
		<label>原客户经理：</label>
		<input name="oldUserNum" id="oldUserNum" required="true"  class="nui-buttonEdit" onbuttonclick="selectCustManeger" />

 		<label>变更后所在机构：</label> 
 		<%-- <input id="newOrgNum" name="newOrgNum" required="true"  text="<%=request.getParameter("oldOrgNum") %>" dvalue="<%=request.getParameter("oldOrgNum") %>"  enabled="false" allowInput="false"  class="nui-dictcombobox nui-form-input" dictTypeId="org"/>  --%>
 		<input id="newOrgNum" name="newOrgNum" required="true" class="nui-buttonEdit"  onbuttonclick="selectEmpOrgs" vtype="maxLength:32"   enabled="false" allowInput="false" /> 
 		
		<label>变更后客户经理：</label>
		<input id="newUserNum" name="newUserNum" required="true" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32" />

		<label>移交原因：</label>
		<input id="transPerson" name="transPerson" required="true" class="nui-textbox nui-form-input"  />
		<label>经办人：</label>
		<input name="userNum" required="true" value="<%=((UserObject)session.getAttribute("userObject")).getUserName() %>"   class="nui-textbox nui-form-input" Enabled="false" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div> 
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
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
					
	function init(v){
		nui.get('oldOrgNum').setValue('<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode") %>');
		nui.get('oldOrgNum').setText('<%=((UserObject)session.getAttribute("userObject")).getUserOrgName() %>');
		nui.get('newOrgNum').setValue('<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode") %>');
		nui.get('newOrgNum').setText('<%=((UserObject)session.getAttribute("userObject")).getUserOrgName() %>');
		nui.get('oldOrgNum').setEnabled(false);
		if(v=="1"){
			nui.get('oldUserNum').setValue('<%=((UserObject)session.getAttribute("userObject")).getUserId() %>');
			nui.get('oldUserNum').setText('<%=((UserObject)session.getAttribute("userObject")).getUserName() %>');
			nui.get("oldUserNum").setEnabled(false);
		}
		nui.get('roleType').setValue(v);
	}
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o = form.getData();
		var newOrgNum, oldOrgNum, oldUserNum, newUserNum;
		newOrgNum = nui.get("newOrgNum").getValue();
		oldOrgNum = nui.get("oldOrgNum").getValue();
		oldUserNum = nui.get("oldUserNum").getValue();
		newUserNum = nui.get("newUserNum").getValue();
		if (newOrgNum == oldOrgNum && oldUserNum == newUserNum) {
			alert("移交机构和用户不能相同");
			return;
		}  
			nui.confirm(
							"全部移交会将原客户经理下的业务全部移交，确认吗？",
							"业务移交提醒",
							function(action) {
								if (action == "ok") {
									git.mask("form1");
										var json = nui.encode( {"item" : o});
										$.ajax({
													url : "com.bos.csm.transfer.transfer.BusinessMoving.biz.ext",
													type : 'POST',
													data : json,
													cache : false,
													contentType : 'text/json',
													success : function(text) {
														git.unmask("form1");
														if("00000"==text.msgCod){
															openSubmitView(text.response);
														} else {
															nui.alert(text.msg);
															query();
														}
													},
													error : function(jqXHR,
															textStatus,
															errorThrown) {
													 nui.alert(jqXHR.responseText);
													}
												});
									
								} else {
									git.unmask("form1");
								}
							});
	}

	
		// 原机构信息
	function selectEmpOrg(e) {
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
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
						nui.get("newOrgNum").setValue(data.orgcode);
						nui.get("newOrgNum").setText(data.orgname);
						nui.get("oldUserNum").setValue();
						nui.get("oldUserNum").setText();
					}
				}
			}
		});
	}
	// 新机构信息
	function selectEmpOrgs(e) {
		var btnEdit = this;
		var url= "/pub/orgDemolition/creditMove/select_all_org_tree.jsp";
		if(nui.get("roleType").getValue()=="2"){//非客户经理
			url= "/pub/sys/select_org_tree.jsp";
		}
		nui.open({
			url : nui.context +url,
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
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
						nui.get("newUserNum").setValue();
						nui.get("newUserNum").setText();
					}
				}
			}
		});
	}
	// 原客户经理
	function selectCustManeger(e) {
		var oldOrgNum;
		oldOrgNum = nui.get("oldOrgNum").getValue();
		if (oldOrgNum == "") {
			alert("请先选择机构");
			return;
		} else {
			var orgId;
			orgId = nui.get("oldOrgNum").getValue();
			var btnEdit = this;
			nui.open({
						url : nui.context+ "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="+ orgId,
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

	// 新客户经理
	function selectCustManegers(e) {
		var newOrgNum;
		newOrgNum = nui.get("newOrgNum").getValue();
		if (newOrgNum == "") {
			alert("请选择变更后所在机构");
			return;
		} else {
			var orgIds;
			orgIds = nui.get("newOrgNum").getValue();
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="
								+ orgIds,
						showMaxButton : true,
						title : "选择客户经理",
						width : 800,
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

	
	//弹出审批意见页面
	function openSubmitView(response){
		var url = nui.context + "/csm/transfer/business_move_confirm_tree.jsp?bizId="
					+ response.bizId + "&processInstId="+response.processInstId+"&isSrc=2";
		nui.open({
	               url: url,
	               title: "业务移交", 
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
	
</script>
</body>
</html>

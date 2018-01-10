<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):陈川
  - Date: 2016-04-11
-->
<head>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="roleType" id="roleType" required="true"  class="nui-hidden" value="2"/>
	<div class="nui-dynpanel" columns="4">
		<label>变更后所在机构：</label>
		<input id="newOrgNum" name="newOrgNum" required="true" allowInput="false" class="nui-buttonEdit"onbuttonclick="selectEmpOrgs" />
		<label>变更后客户经理：</label>
		<input id="newUserNum" name="newUserNum" required="true" allowInput="false" class="nui-buttonEdit" dictTypeId="user"  onbuttonclick="selectCustManegers" vtype="maxLength:32" />
		<label>是否保留业务权：</label>
		<input id="isKeepBusinessPower" name="isKeepBusinessPower" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
		<label>移交原因：</label>
		<input id="transPerson" name="transPerson" required="true" class="nui-textbox nui-form-input"  />
		<label>经办人：</label>
		<input name="handlingUserNum" required="true" value="<%=((UserObject)session.getAttribute("userObject")).getUserName() %>" class="nui-textbox nui-form-input" Enabled="false" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div> 
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var oldUserNum="<%=request.getParameter("oldUserNum") %>";
	var oldOrgNum="<%=request.getParameter("oldOrgNum") %>";
	var isKeepBusinessPower="<%=request.getParameter("isKeepBusinessPower") %>";
	if (isKeepBusinessPower=="false") {
		nui.get('isKeepBusinessPower').select(0);
		nui.get('isKeepBusinessPower').setEnabled(true);
	} else {
		nui.get('isKeepBusinessPower').select(2);
		nui.get('isKeepBusinessPower').setEnabled(false);
	}	
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
		nui.get('roleType').setValue(v);
	}
	function GetData(){
		var data = form.getData();
		return data;
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        if(oldUserNum==nui.get("newUserNum").getValue()&&oldOrgNum==nui.get("newOrgNum").getValue()){
        	nui.alert("原客户经理不能和变更后客户经理相同");
        	return;
        }
        nui.confirm("确定移交这些客户吗？", "确认", function(action) {
			if (action != "ok"){
				return;
			}
		GetData();
		CloseWindow("ok");
		return;
        });
	}
	function GetData(){
		var data = form.getData();
		return data;
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
			height : 400,
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
	// 新客户经理
	function selectCustManegers(e) {
		var newOrgNum= nui.get("newOrgNum").getValue();
		if (newOrgNum == "") {
			nui.alert("请选择变更后所在机构");
			return;
		} else {
			var btnEdit = this;
			nui.open({
						url : nui.context+ "/pub/user/select_user.jsp?orgNum="+ newOrgNum,
						showMaxButton : true,
						title : "选择客户经理",
						width : 850,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								debugger;
								if (data) {
									btnEdit.setText(null);
									btnEdit.setValue(data.userNum);
								}
							}
						}
					});
		}
	}
</script>
</body>
</html>

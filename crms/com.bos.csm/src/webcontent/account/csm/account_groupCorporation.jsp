<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>集团客户查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="集团客户查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.csm.groupCorporation" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>集团客户编号：</label>
						<input name="item.partyNum" class="nui-textbox nui-form-input"/>
						<label>集团客户名称：</label>
						<input name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>成员客户名称：</label>
						<input name="item.memberName" class="nui-textbox nui-form-input"/>
						<label>成员营业执照：</label>
						<input name="item.memberRegistrCd" class="nui-textbox nui-form-input"/>
						<label>成员组织机构代码：</label>
						<input name="item.memberOrgRegisterCd" class="nui-textbox nui-form-input"/>
						<label>成员证件类型：</label>
						<input id="item.certType" name="item.memberCertType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
						<label>成员证件号码：</label>			
						<input id="item.memberCertNum"  class="nui-textbox nui-form-input" name="item.memberCertNum"/>
						<label>成员中征码：</label>
						<input id="item.memberMiddelCode" name="item.memberMiddelCode" required="false" class="nui-textbox nui-form-input" vtype=""/>
						
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
							if(manage.equals("true")){
						%>
							<input id="item.userNum"  class="nui-hidden nui-form-input" name="item.userNum"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
						<% 
							}
						%>
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client" allowAlternating="true">
					<div property="columns">
						<div type="indexcolumn">序号</div>
						<div field="partyNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false">集团客户编号</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">集团客户名称</div>
						<div field="groupManageWayCd" allowSort="true" width=""
							headerAlign="center" dictTypeId="XD_KHCD0204">集团客户管理方式</div>
						<div field="status" allowSort="true" width="" headerAlign="center"
							dictTypeId="XD_KHCD0231">认定状态</div>
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="org">主办行</div>
						<div field="userNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">主办管户经理</div>
					</div>
				</div>
			</center>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        } 
		//alert(nui.get("item.orgId").getValue());
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
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
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgid);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
		});
	</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>客户额度台账</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="客户额度台账">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.edu" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgcode" name="item.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>客户名称：</label>
						<input name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
		
						<label>经办人：</label> 
						
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
						<input id="item.userNum"  class="nui-buttonEdit" name="item.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
					<% 
						}else{
					%>
						<input id="item.userNum" name="item.userNum" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
					<%}%>
						
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					</div>
					<div class="nui-toolbar"
						style="text-align: left; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="view()">查看</a>
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false" allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>          
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="currencyCd" allowSort="true" width=""
							headerAlign="center" dictTypeId="CD000001">币种</div>
						<div field="creditAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">额度金额</div>
						<div field="availableAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">额度余额</div>
						<div field="startDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">起始日</div>
						<div field="endDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">到期日</div>
						<div field="userNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">经办人</div>
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
			form.validate();
	        if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        }  
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		//alert(nui.get("item.orgcode").getValue());
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
	                        btnEdit.setValue(data.orgcode);
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
		
		
		function view() {
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一条记录");
				return;
			}
			var url = nui.context + "/csm/account/credit/account_edu_view.jsp?partyId="
					+ row.partyId;
			//编辑
			nui.open({
				url : url,
				showMaxButton : true,
				title : "查看",
				width : 1024,
				height : 768,
				state : "max",
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
					//alert(text);
				},
				ondestroy : function(action) {
					//queryInit();
				}
			});
		}
		
		function selectCustManegers(e) {
		var newOrgNum;
		newOrgNum = nui.get("item.orgcode").getValue();
		
		if (newOrgNum == "") {
			alert("请先选择机构");
			return;
		}else {
			var orgIds;
			orgIds = nui.get("item.orgcode").getValue();
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
									//  alert(nui.encode(data));
									btnEdit.setValue(data.userId);
									btnEdit.setText(data.empName);
								}
							}
						}
					});
		}

	}
		
	</script>
</body>
</html>
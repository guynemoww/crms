<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>担保公司业务管理台账</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="担保公司业务管理台账">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.danbao" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>客户名称：</label>
						<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>统一社会信用代码：</label>
						<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" required="false" class="nui-textbox nui-form-input" />
						
						<label>营业执照：</label>
						<input id="item.registrCd" name=item.registrCd class="nui-textbox nui-form-input" required="false"/>
						<label>组织机构代码：</label>
						<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input"/>
						<label>中征码：</label>
						<input id="item.middleCode" name="item.middleCode" required="false" class="nui-textbox nui-form-input" />
						
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
					allowResize="true" showReloadButton="false" allowAlternating="true" 
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="unifySocietyCreditNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">统一社会信用代码</div>
						<div field="fdbs" allowSort="true" width="" headerAlign="center"
							dictTypeId="">担保放大倍数</div>
						<div field="guarantOrgReal" allowSort="true" width="" headerAlign="center" dataType="currency" 
							dictTypeId="">担保机构实收资本</div>
						<div field="ydbje" allowSort="true" width="" headerAlign="center" dataType="currency"
							dictTypeId="">已担保金额</div>
						<div field="dealDate" allowSort="true" width="" headerAlign="center" 
							dictTypeId="">起始日</div>
						<div field="endDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">到期日</div>
						<div field="zhzh1" allowSort="true" width="" headerAlign="center"
							dictTypeId="">专业担保机构合作协议</div>
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
		
		function bizViewGur(bizNum){
			var json = nui.encode({"bizNum":bizNum});
			var jspName;
			nui.open({
				url:nui.context + "/crt/con_info/con_tree_db.jsp?contractId="+bizNum+"&proFlag=-1",
				showMaxButton:true,
				title:"查看",
				width: 1024,
	            height: 768,
	            state:"max",
	            ondestroy: function(e) {
	            }
			});
		}
		
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['zhzh1']='<center><a href="#" onclick="bizViewGur(\''+ e.data[i].contractId+ '\');">详情</a></center>';
			
			}
		});
		
	</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>同业客户查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="同业客户查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.csm.financial" />
					<div class="nui-dynpanel" columns="8">
						<label>机构名称：</label>
						<input name="item.orgId" allowInput="false" class="nui-buttonEdit" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" required="true" onbuttonclick="selectOrg" />
						<label>区域类型：</label>
						<input id="item.areaType" name="item.areaType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0210"  />
						<label>金融机构类型：</label>
					 	<input  id="item.financeEnterpriseType" name="item.financeEnterpriseType"  class="nui-dictcombobox nui-form-input" 
					 			allowInput="false"  dictTypeId="ECIF_JRJGLX01" />
						<label>客户名称:</label>
						<input id="item.partyName" class="nui-textbox nui-form-input" name="item.partyName"/>
						<label>统一社会信用代码：</label>
						<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input"/>
			   	    	<label>营业执照：</label>
						<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" />
						<label>组织机构代码：</label>
						<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input"/>
			   	    	<label>Swift码:</label>
			   	        <input id="item.swiftBicNum" class="nui-textbox" name="item.swiftBicNum">
			   	        
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
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client"  allowAlternating="true">
					<div property="columns">
						<%--<div type="checkcolumn" >选择</div>--%>
						<div type="indexcolumn">序号</div>
						<div field="orgname" headerAlign="center" allowSort="true" >机构名称</div>
						<div field="areaType" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0210" >区域类型</div>
						<div field="financeEnterpriseType" headerAlign="center" allowSort="true" dictTypeId="ECIF_JRJGLX01">金融机构类型</div>
						<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
						<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true" >统一社会信用代码</div>
						<%--<div field="englishCustomerName" headerAlign="center" allowSort="true" >客户英文名称</div>--%>
						<div field="registerCode" headerAlign="center" allowSort="true" >营业执照</div>
						<div field="orgRegisterCd" headerAlign="center" allowSort="true" >组织机构代码</div>
						<div field="swiftBicNum" headerAlign="center" allowSort="true" >Swift码</div>
						<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">管户经理</div>
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
		
		//金融机构类型
      function selectFinancialType(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=ECIF_JRJGLX01",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    
                    if (data) {
                    	btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                        
                    }
                    
                    /*
                    if (data) {
                    	nui.get("financialInstitution.subjectTypeCd").setValue(data.dictid);
                        nui.get("financialInstitution.subjectTypeCd").setText(data.dictname);
                        checkAreaType();
                        
                    }
                    **/
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
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
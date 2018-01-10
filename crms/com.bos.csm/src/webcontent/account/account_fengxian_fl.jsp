<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>分类查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="分类查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.pj.fxfl" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>客户名称：</label>
						<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>
						<input id="item.certNum" name="item.certNum" required="false" class="nui-textbox nui-form-input"/>
						 
						<label>分类种类：</label>
						<input id="item.claMethod" name="item.claMethod" class="nui-dictcombobox nui-form-input" dictTypeId="claMethod" required="false"/>					
						
						<!-- 
						<label>分类种类：</label>
						<input id="item.clsResult" name="item.clsResult" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001" required="false"/>
						-->
						
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
					<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
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
						<div field="acApplyNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">分类申请编号</div>
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="certType" allowSort="true" width=""
							headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">证件号码</div>
						 
						<div field="claMethod" allowSort="true" width="" headerAlign="center"
							dictTypeId="claMethod">分类种类</div>
						<!-- 
						<div field="clsResult" allowSort="true" width="" headerAlign="center"
							dictTypeId="XD_FLCD0001">分类种类</div>
						-->
						<div field="createTime" allowSort="true" width="" headerAlign="center"
							dictTypeId="">分类日期</div>
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
		
		
		function init(){
			var arr = git.getDictDataFilter("claMethod",'quar,usual');
			nui.get("item.claMethod").setData(arr);
		}
		init();
		
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
				e.data[i]['acApplyNum']='<a href="#" onclick="viewLoan(\''+e.data[i]['acApplyId']+'\',\''+e.data[i]['claMethod']+'\');">'+e.data[i]['acApplyNum']+'</a>';
			}
		});
		
		function viewLoan(acApplyId,claMethod) {
	        var row = grid.getSelected();
	        if(row) {
	        	var url = nui.context+ "/risk/sort/risk_sort_tree.jsp?bizId="+acApplyId+"&bizType="+claMethod+"&wflow=1";
				nui.open({
	            url: url,
	            title: "分类申请编号", 
	            width: 1000,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	            }});
				return;
	        }else {
	            alert("请选中一条记录");
	        }
	        
	    }
		
	</script>
</body>
</html>
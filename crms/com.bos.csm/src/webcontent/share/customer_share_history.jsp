<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2015-06-10
  - Description:

-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@include file="/common/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.share.share.shareList"/>
	<input name="roleType" id="roleType" required="true"  class="nui-hidden" value="2"/>
	<div class="nui-dynpanel" columns="6">
	<label>机构名称：</label>
		<input id="item.orgNum" name="item.orgNum"text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>"value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectOrgQuery"/>
		<label>客户经理：</label>
		<input id="item.userNum" name="item.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32"/>
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input"/>
		<label>客户类型：</label>
		<input id="item.partyTypeCd" name="item.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001"  allowInput="false" />
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" required="false" />
		</div>

				
<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext"
	dataField="items" allowAlternating="true" 
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="indexcolumn" headerAlign="center" allowSort="true" >序号</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
		<div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="operaterOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">所在机构</div>
		<div field="operaterUserNum" headerAlign="center" allowSort="true" dictTypeId="user">客户经理</div>
		<div field="receiveShareOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">申请共享机构</div>
		<div field="receiveShareUserNum" headerAlign="center" allowSort="true" dictTypeId="user">申请共享客户经理</div>
		<div field="remark" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD322830">共享原因</div>
		<div field="operaterUserNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
		<div field="updateTime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">共享时间</div>
		<div field="appStatus" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD7493">审批状态</div>
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
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
									"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))||"R1008".equals(role.get("roleid"))||"R1049".equals(role.get("roleid"))||"R1051".equals(role.get("roleid"))){
										manage="true";
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("true")){
					%>
						init();
					<% 
						}
					%>
	
	function init(){//客户经理进入页面时
		nui.get("item.orgNum").setEnabled(false);
		nui.get("item.userNum").setValue('<%=((UserObject)session.getAttribute("userObject")).getUserId() %>');
		nui.get("item.userNum").setText('<%=((UserObject)session.getAttribute("userObject")).getUserName() %>');
		nui.get("item.userNum").setEnabled(false);
		nui.get("roleType").setValue("1");
	}
    
    function search() {
    	git.mask();
		var data = form.getData(); //获取表单多个控件的数据
		
				 //字典过滤
		var arr = git.getDictDataFilter("XD_KHCD1001", '01,02,05');
		nui.get("item.partyTypeCd").setData(arr);
        grid.load(data,function(){
        git.unmask();
        });
        
    }
    search();
    
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       				
       			e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''
	       				+ e.data[i].partyId+'\');return false;" value="'
	       				+ e.data[i].partyId
	       				+ '">'+e.data[i]['partyName']+'</a>';
       			
       		}
    });
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
			nui.get("item.orgNum").setValue();
			nui.get("item.orgNum").setText();
			nui.get("item.userNum").setValue();
			nui.get("item.userNum").setText();
		}
		nui.get("item.partyName").setValue();
		nui.get("item.partyTypeCd").setValue();
		nui.get("item.certType").setValue();
		nui.get("item.certNum").setValue();
	}
	</script>
</body>
</html>

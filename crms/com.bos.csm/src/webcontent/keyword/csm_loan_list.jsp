<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10 8:26:27
  - Description:
-->
<head>
<title>关键信息维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<% 
	UserObject tempUser = (UserObject)session.getAttribute("userObject"); 
	%>
<div id="tabs1" class="nui-tabs" activeIndex="0"style="width: 100%; height: auto;">
<div title="关键信息维护" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
<input id="item.userNum" name="item.userNum"  class="nui-hidden" value="<%=tempUser.getUserId() %>"/>
	<div class="nui-dynpanel" columns="6">
		<label class="nui-form-label">机构名称：</label>
		<input id="item.orgNum" name="item.orgNum" class="nui-buttonEdit" enabled="false" onbuttonclick="selectOrg"/>
		
		<label>客户名称：</label>
		<input name="item.partyName" class="nui-textbox nui-form-input"/>
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  />
		
		<label>证件号码：</label>			
		<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
		
		<label>中征码：</label>			
		<input id="item.middleCode"  class="nui-textbox nui-form-input" name="item.middleCode" onvalidation=""  />
		
		<label>是否农户：</label>
		<input id="item.isFarmer" name="item.isFarmer" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
	</div>
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
	    <a class="nui-button"  iconCls="icon-search" onclick="queryInit()">查询</a>
	    <a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
	</div>
</div>

<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px;">
	    <a id="editCust"  class="nui-button" iconCls="icon-edit" onclick="view()" >编辑</a>
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.natural.natural.getLoanListByOrgId.biz.ext" dataField="items"allowAlternating="true"
	allowResize="true" showReloadButton="false"  
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>     
	        <div field="orgNum" allowSort="true" width="" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>          
	        <div field="partyName" allowSort="true" width="" headerAlign="center" autoEscape="false">客户名称</div>
	        <div field="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>       
	        <div field="certNum" allowSort="true" width="" headerAlign="center" >证件号码</div>
	        <div field="middleCode" allowSort="true" width="" headerAlign="center" >中征码</div>
	        <div field="isFarmer" allowSort="true" width="" headerAlign="center" dictTypeId="YesOrNo">是否农户</div>
	        <div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user" >管户客户经理</div>
	        <div field="createTime" allowSort="true" width="" headerAlign="center">创建时间</div>
	     </div>
</div>
</div>
</div>
<script type="text/javascript">
	nui.parse();
	git.mask();	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");	
	initPage();
    function initPage(){
    	var orgCode = <%="\""+tempUser.getAttributes().get("orgcode")+"\"" %>;
	    var orgName = <%="\""+tempUser.getUserOrgName() +"\"" %>;
    	nui.get("item.orgNum").setValue(orgCode);
    	nui.get("item.orgNum").setText(orgName);
    }
	
  	queryInit();
	function queryInit() {
		//校验
		var o = form.getData();//逻辑流必须返回total
		grid.load(o);
		//字典过滤，过滤掉202的证件类型
		var arr = git.getDictDataUnFilter("CDKH0002", '202');
		nui.get("item.certType").setData(arr);
		git.unmask();
	}
	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
	});  

	function reset() {
		form.reset();
	}

	function view() {
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		var url = nui.context + "/csm/keyword/csm_loan_keyword.jsp?partyId=" + row.partyId + "&qote=2&partyNum="
				+ row.partyNum;
		//编辑
		nui.open({
			url : url,
			showMaxButton : true,
			title : "查看客户信息",
			width : 800,
			height : 400,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
				//alert(text);
			},
			ondestroy : function(action) {
				queryInit();
			}
		});
	}

	function query() {
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		var url = nui.context + "/csm/loan/loan_tree.jsp?partyId=" + row.partyId + "&qote=1&partyNum=" + row.partyNum;
		//查看
		nui.open({
			url : url,
			showMaxButton : true,
			title : "查看客户信息",
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

	function add() {
		//查看
		nui.open({
			url : nui.context + "/csm/loan/loan_add.jsp",
			showMaxButton : true,
			title : "添加小贷中心",
			width : 800,
			height : 500,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
				//alert(text);
			},
			ondestroy : function(action) {
				queryInit();
			}
		});
	}

	function onSelectionChanged() {
		var row = grid.getSelected();
		var json = nui.encode({
			"partyId" : row.partyId
		});
		if (row) {
			nui.ajax({
				url : "com.bos.csm.pub.getGeneralityInfo.verifyManageRight.biz.ext",//上部分需要的逻辑流
				type : 'POST',
				data : json,
				contentType : 'text/json',
				success : function(text) {
					if (text.flag) {
						nui.get("editCust").show();
					} else {
						nui.get("editCust").hide();
					}
				},
				error : function() {
					alert("操作失败！");
				}

			});
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
</script>
</body>
</html>
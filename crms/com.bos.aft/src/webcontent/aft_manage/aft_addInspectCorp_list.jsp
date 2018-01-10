<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 15:57:52
  - Description:贷后重点客户的管理
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>移入抽样检查客户</title>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
	<div title="移入抽样检查客户" >
	<center>
	<div id="queryForm" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6" id="queryCsm">
			
			<label>客户名称：</label>
			<input name="partyName"  class="nui-textbox nui-form-input" value=""/>
			<label>客户编号：</label>
			<input name="partyNum"  class="nui-textbox nui-form-input"  value=""/>
			<!-- <label>T24客户编号：</label>
			<input name="ecifPartyNum"  class="nui-textbox nui-form-input"  value=""/> -->
			<label>客户类型：</label>
			<input name="partyTypeCd"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001" value=""/>
			<!-- <input id="partyId" class="nui-hidden nui-form-input" name="partyId"/> -->
			
		</div>	
		<div class="nui-toolbar" style="text-align:right;border:none">
		<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="queryCsm()">查询</a>
		</div>
	</div>
	<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<!-- 
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">新增</a>
		<a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		<a id="smallCorp" class="nui-button" iconCls="icon-upload" onclick="smallCorpIdentify(0)">发起小企业认定</a>
		 -->
	</div>
	</div>
	<div id="datagrid" class="nui-datagrid" style="width:99.5%;height:auto;" 
		     allowAlternating="true"  showReloadButton="false" pageSize="10"
		    sizeList="[10,20,50,100]" url="com.bos.aft.aft_manage.querySampleCsmCorp.biz.ext" dataField="csmCorps">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="orgNum" headerAlign="center" dictTypeId="org" allowSort="true" >所属机构</div>
			<!--  <div field="orgName" headerAlign="center" allowSort="true">组织机构代码</div>-->
			<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD1001">客户类型</div>
		</div>
	 </div>
	 <div class="nui-toolbar" style="text-align:right;border:none" id="saveCsmBtn">
		<a class="nui-button" style="margin-right:5px;" onclick="saveCsm()" enable="false">移入</a>
	 </div>
	</center>
	</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		
		//git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。		
		var queryForm=new nui.Form("#queryCsm");
		var grid=nui.get("datagrid");
		function queryCsm(){//查询当前机构下符合条件的客户信息
			var csmData=queryForm.getData();
			//alert(nui.encode(csmData));
			queryForm.validate();
			if(queryForm.isValid()==false){
				return;
			}
			$("#datagrid").show();
			$("#saveCsmBtn").show();
			grid.load({"csmData":csmData});
		}
		queryCsm();
		function saveCsm(){
			var rows=grid.getSelected();
			//alert(nui.encode(rows));
			if(rows==undefined||rows.length==0){
				nui.alert("请选择要添加的客户");
				return;
			}
			var json=nui.encode({"item":{"partyId":rows.partyId}});
           		$.ajax({
					url: "com.bos.aft.aft_manage.addInspectCorp.biz.ext",
            		type: 'POST',
            		data: json,
            		cache: false,
            		contentType:'text/json',
           			success: function (data) {
	           				if(data.reapyCount==1){
							alert("客户已存在！");
	           				return;
	           			}else{
	           				alert(data.msg);
	           				CloseWindow();
	           			}
            	
           		},
           		error: function (jqXHR, textStatus, errorThrown) {
                	nui.alert(jqXHR.responseText);
                	
            		}
			});
			
		}
		
		
	</script>
</body>
</html>
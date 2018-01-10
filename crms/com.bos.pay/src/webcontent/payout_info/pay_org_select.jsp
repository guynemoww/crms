<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-31 17:05:15
  - Description:
-->
<head>
<title>选择对应的会计机构</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="form">
		<div style="padding-left: 10px">
			<input name="item.oprOrgId" id="item.oprOrgId" style="width: 150px" class="nui-hidden nui-form-input"/>
			<input name="item.status" id="item.status" value="1" class="nui-hidden"/>
			<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.utp.org.organization.searchOrgAccRelList" />
		</div>
		<div class="nui-toolbar">
			<a class="nui-button" iconCls="icon-add" onclick="add()">添加</a>
			<a class="nui-button" iconCls="icon-add" onclick="cancel()">关闭</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="grid" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search1.biz.ext" dataField="items" multiSelect="false" pageSize="50" sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="ACC_ORG_NO">财务机构编号</div>
				<div field="ACC_ORG_ID" dictTypeId="org">财务机构名称</div>
				<div field="COL2" dictTypeId="XD_RZLX">入账类型</div>
				<div field="OPR_ORG_NO">机构编号</div>
				<div field="OPR_ORG_ID" dictTypeId="org">机构名称</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    	nui.parse();
    	var orgCode = "<%=request.getParameter("orgCode") %>";
    	var productId = <%=JspUtil.getParameterHaveSign(request, "productId")%>;
    	var pjzl = <%=JspUtil.getParameterHaveSign(request, "pjzl")%>;
    	nui.get("item.oprOrgId").setValue(orgCode);
    	var form = new nui.Form("#form");
		var grid = nui.get("grid");
		query();
		function query() {
			var data = form.getData();
			if(productId=="01006002"||productId=="01006010"||productId=="01006001"){//贴现
				data.item.col2=pjzl == "01"?"4":"5";	
			}else if(productId=="01008"||productId=="01008001"||productId=="01008010"){
				data.item.col2=pjzl == "01"?"2":"3";
			}else if(productId=="01009022"||productId=="01007014"||productId=="01009001"||productId=="01009002"||productId=="01009010"){//保函
				data.item.col2="6";
			}else{
				data.item.col2="1";
			}
			grid.load(data);
			/* grid.load(form.getData(),function (gridTemp){
				productData = [];
				productData.length = 0;
				orgData = [];
				orgData.length = 0;
				for(var i=0;i<gridTemp.data.length;i++){
					if(!gridTemp.data[i].PRODUCT_ID){
						orgData[orgData.length] = gridTemp.data[i];
					}else if(gridTemp.data[i].PRODUCT_ID == productId){
						productData[productData.length] = gridTemp.data[i];
					}
				}
				grid.setData(productData.length == 0?orgData:productData);
			}); */
			form.unmask();
		}
		
		var selectRow;
		function getData() {
			return nui.clone(selectRow);
		}
		
		function add(){
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一条信息!");
				return false;
			}
			selectRow = row;
			CloseWindow("ok");
		}
		
		function cancel(){
			selectRow = null;
			CloseWindow("cancel");
		}
    </script>
    
    
</body>
</html>
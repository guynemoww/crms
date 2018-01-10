<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-04-03 09:20:03
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>选择出库押品</title>
</head>
<body>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:540px;"
		url="com.bos.grt.regmanage.collateralout.queryCollContractRegListEntityOut.biz.ext" showColumnsMenu="true"
		dataField="collContractRegListEntitys"  allowResize="false" showReloadButton="false" 
		showEmptyText="true" emptyText="没有相关押品数据！" 
		sizeList="[1000]" multiSelect="true"  pageSize="1000" showPager="true">
		<div property="columns">
			<div type="checkcolumn" name="chooise" id="chooise" ></div>
			<div field="partyName" headerAlign="center" allowSort="true">抵质押人名称</div>
			<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品类别</div>
			<div field="guarantyName" headerAlign="center" allowSort="true">押品名称</div>
			<div field="cardType" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0140">权证类型</div>
			<div field="cardName" headerAlign="center" allowSort="true">权证名称</div>
			<div field="cardNum" headerAlign="center" allowSort="true">权证编号</div>
			<div field="cardState" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0008">权证状态</div>
			<div field="guaranteeRightMoney" headerAlign="center" allowSort="true">押品本次占用价值</div>
			<div field="mailerNum" headerAlign="center" allowSort="true" name="mailerNum">信封编号</div>
			<div field="surelyOrg" headerAlign="center" allowSort="true" name="surelyOrg" dictTypeId="org" >保管机构</div>
			<div field="contractId" headerAlign="center" allowSort="true" name="contractId" >合同编号</div>
        </div>
	</div>	
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-ok" onclick="sureok()" id="sureok">确定</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>	
		
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid1");
	var contractId = "<%=request.getParameter("contractId")%>";
	//隐藏合同编号
	grid.hideColumn(grid.getColumn("contractId"));
	//隐藏保管机构
	grid.hideColumn(grid.getColumn("surelyOrg"));
	//隐藏信封编号
	grid.hideColumn(grid.getColumn("mailerNum"));
	
    function search(){
      	var data  = {"collContractRegListEntity":{"contractId":contractId}};
      	var json = nui.decode(data);
      	grid.load(json);
      	<%--if(grid.getRow(0)==null){
      		alert("没有相关押品信息！");
      	}--%>
    }
    search();
    
	function sureok(){
		var rows = grid.getSelecteds();
		if(rows&&nui.encode(rows)!="[]"){
			CloseWindow("ok");
		}else{
			nui.alert("请选中一条或多条记录");
		}
	}
	
	/**
	 * 获得选中的数据
	 */
	function getThisData(){
		var row = grid.getSelecteds();
		var data = {collContractRegListEntitys:row};
		return data;
	}

	function CloseWindow(action) {            
   		 window.CloseOwnerWindow("ok");
	}
	
	/**
	 * 点击关闭按钮，关闭窗口	
	 */
	function closeok(){
		CloseWindow("ok");
	}
</script>
</body>
</html>
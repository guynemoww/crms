<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-04-25 13:52:31
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>打印押品信息</title>
</head>
<body>
	<!-- 
		经办机构：*****           ****年**月**日      第****号（手工编号）
保存机构：****
	
	  -->
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input type="hidden" name="collTbConContractEntitys" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>经办机构：</label>
			<input class="nui-textbox nui-form-input" vtype="maxLength:200"
			id="orgNum" enabled="false" />
			<label>保存机构：</label>
			<input class="nui-textbox nui-form-input" vtype="maxLength:200"
			id="saveReg" enabled="false" dictTypeId="product" style="width:200px" />
			<label>日期：</label>
			<input class="nui-datepicker nui-form-input" vtype="maxLength:200"
			id="nowdate" enabled="false" value="<%=com.bos.pub.GitUtil.getBusiDate()%>" style="width:200px" />
			<label>号：</label>
			<input name="" required="false" class="nui-text nui-form-input" vtype="maxLength:200"
			id="num" enabled="false" />
    	</div>
	</div>
	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.grtprint.queryPrintInfo.queryConOfGuaranty.biz.ext"
		dataField="conOfGuarantys" allowResize="false" showReloadButton="false" onrowclick="hangdianji"
		sizeList="[1000]" multiSelect="true" pageSize="1000" sortMode="client" showPager="false">
		<div property="columns">
			<div type="checkcolumn" name="chooise" id="chooise" ></div>
			<div field="guaranterName" headerAlign="center" allowSort="true" >借款人名称</div>
			<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">抵质押类型</div>
			<div field="contractManualNum" headerAlign="center" allowSort="true" name="contractId" >贷款合同编号</div>
			<div field="subcontractManualNum" headerAlign="center" allowSort="true" name="contractId" >抵质押合同编号</div>
			<div field="guarantyName" headerAlign="center" allowSort="true">抵质押品名称</div>
			<div field="suretyId" headerAlign="center" allowSort="true">抵质押品编号</div>
			<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" name="orgNum">经办机构</div>
			<div field="saveReg" headerAlign="center" allowSort="true" dictTypeId="org" name="saveReg">保存机构</div>
			<div field="laidUpId" headerAlign="center" allowSort="true" width="200px">入库ID</div>
        </div>
	</div>	
	
	<div class="nui-toolbar" style="border-bottom:0;">
		<a class="nui-button" iconCls="icon-print" onclick="inprint()">打印</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>

<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid1");
	grid.hideColumn(grid.getColumn("chooise"));
	grid.hideColumn(grid.getColumn("orgNum"));
	grid.hideColumn(grid.getColumn("saveReg"));
	var bizId = "<%=request.getParameter("bizId")%>";
	
    function search(){
      	var data  = {"conOfGuaranty":{"laidUpId":bizId}};
      	var json = nui.decode(data);
      	grid.load(json);
    }
    search();
    	
    function initrows(){
    	sall();
      	var rows = grid.getSelecteds();
      	alert(rows);
      	if(rows!=""){
			nui.get("orgNum").setValue(git.getOrgName(rows[0].orgNum));
			nui.get("saveReg").setValue(git.getOrgName(rows[0].saveReg));
      	}
    }
    
    /**
     * 选择所有行
     */
    function sall(){
    	grid.selectAll();
    }
    
    /**
	 * 行点击
	 */	
	function hangdianji(e){
		var row = grid.getSelected();
		grid.selectAll();
	}
	
	/**
	 * 打印
	 */
	function inprint(){
		initrows();
		alert("打印");
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
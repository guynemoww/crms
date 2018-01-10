<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-27 15:01:53
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>押品信息</title>
</head>
<body>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
		url="com.bos.grt.guaranMainManager.guaranteeContract.getGuaranteeBasicInfoList.biz.ext"
		dataField="guarantyInfos" allowCellEdit="true" allowCellSelect="true"
		    allowAlternating="true" multiSelect="false" pageSize="15" showEmptyText="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true">
		        <div property="columns">
		        <div type="checkcolumn">选择</div> 
		        <div field="partyName" headerAlign="center" allowSort="true">保证人名称</div>
				<div field="guaranteeType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4001">保证类别</div>
				</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-ok" onclick="sureok()" id="sureok">确定</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid1");
	var subcontractId = "<%=request.getParameter("subcontractId") %>";
	function search(){
      	var json = nui.decode({"guarantyInfo":{"subcontractId":subcontractId,
      	"subcontractNum":"<%=request.getParameter("subcontractNum") %>","subcontractId":"<%=request.getParameter("subcontractId") %>"}});
      	grid.load(json);
    }
    search();
    /**
	 * 获得选中的数据
	 */
	function getThisData(){
		var row = grid.getSelecteds();
		var data = {guarantyInfos:row};
		return data;
	}
    function sureok(){
		var rows = grid.getSelecteds();
		var json = nui.encode({"tbConSubGrtRelations":rows})+nui.encode({"subcontractId":"<%=request.getParameter("subcontractId") %>"});
		$.ajax({
       		url: "com.bos.grt.guaranMainManager.guaranteeContract.addTbGrtGuaranteeBasicList.biz.ext",
       		type: 'POST',
       		data: json,
       		cache: false,
       		contentType:'text/json',
       		success: function (text) {
       			nui.alert(text.msg);
       			CloseWindow("ok");
       		},
       		error: function (jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
       		}
		});
		/**
		if(rows&&nui.encode(rows)!="[]"){
			CloseWindow("ok");
		}else{
			nui.alert("请选中一条或多条记录");
		}
		*/
	}
	
	/**
	 * 关闭
	 */
	function CloseWindow(action) {            
		window.CloseOwnerWindow("ok");
	}
	function closeok(){
		CloseWindow("ok");
	}
</script>
</body>
</html>
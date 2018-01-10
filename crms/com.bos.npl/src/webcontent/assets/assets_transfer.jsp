<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:10:10
  - Description:
-->
<head>
<title>待移交页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="panel1" class="nui-panel" title="待移交信息" expanded="true" 
				style="width:99.5%;height:auto;" showToolbar="false"
				showCollapseButton="true" showFooter="false" allowResize="false">
			<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
					<div class="nui-dynpanel" columns="4">
						<label>客户名称：</label>
						<input name="tbNplAssetsTransfer.partyName" class="nui-textbox nui-form-input"/>
						<label>机构名称：</label>
						<input name="tbNplAssetsTransfer.orgName" class="nui-textbox nui-form-input"/>
					</div>
			</div>
			<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
			    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
			</div>
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.npl.assets.AssetsTransfer.getAssetsTransfer.biz.ext" dataField="tbNplAssetsTransfers" allowResize="false" 
			 showReloadButton="false" multiSelect="true" pageSize="5" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">	 
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="aa" headerAlign="center" type="indexcolumn"  allowSort="false">序号</div>
				<div field="partyNum" headerAlign="center" allowSort="false">客户编号</div>
				<div field="partyName" headerAlign="center" allowSort="false">客户名称</div>
				<div field="partyType" headerAlign="center" allowSort="false">客户类型</div>
				<div field="creditAmt" headerAlign="center" allowSort="false">授信额度</div>
				<div field="balanceAmt" headerAlign="center" allowSort="false">授信余额</div>
				<div field="partyCreditRating" headerAlign="center" allowSort="false">客户评级</div>
				<div field="warningLevelCd" headerAlign="center" allowSort="false">预警级别</div>
				<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
				<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
				<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
				<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
				<div field="status" headerAlign="center" allowSort="false">状态</div>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="add">引入</a>
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="move">移交</a>
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="view">查看</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	
	//查询
	query();
	function query(){
       	var form = new nui.Form("#form");
		var o = form.getData();
      	grid.load(o);
	}
	//移交
	function move(){
		var rows = grid.getSelecteds();
        if (rows.length > 0){
        	var json = nui.encode({"tbNplAssetsTransfers":rows});
	        $.ajax({
	            url: "com.bos.npl.assets.AssetsTransfer.moveTransferToPerforming.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	                alert("移交成功！");
	                query();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
        }else{
        	alert("请选择资产信息");
        }
	}
	//查看待移交资产明细
	function view(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		if (1==rows.length){
			nui.open({
				url:nui.context+"/npl/assets/assets_transfer_detail.jsp?transferId="+rows[0].transferId,
				showMaxButton:true,
				title:"提示：可点击最大化按钮放大此窗口",
				width:"1200",
	            height:"600",
	            ondestroy: function(e) {
	            	
	            }
			});
        }else{
        	alert("请选择一条待移交资产信息！");
        }
	}
</script>
</body>
</html>
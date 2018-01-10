<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:10:38
  - Description:
-->
<head>
<title>已移交页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="panel1" class="nui-panel" title="已移交信息" expanded="true" 
				style="width:99.5%;height:auto;" showToolbar="false"
				showCollapseButton="true" showFooter="false" allowResize="false">
			<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
					<div class="nui-dynpanel" columns="4">
						<label>客户名称：</label>
						<input name="tbNplAssetsPerforming.partyName" class="nui-textbox nui-form-input"/>
						<label>机构名称：</label>
						<input name="tbNplAssetsPerforming.orgName" class="nui-textbox nui-form-input"/>
					</div>
			</div>
			<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
			    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
			</div>
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.npl.assets.AssetsPerforming.getAssetsPerforming.biz.ext" dataField="tbNplAssetsPerformings" allowResize="false" 
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
				<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
				<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
				
				<div field="overdueInterest" headerAlign="center" allowSort="false">表内外应收未收利息</div>
				<div field="overduedays" headerAlign="center" allowSort="false">逾期或垫款天数</div>
				<div field="intoDate" headerAlign="center" allowSort="false">转入保全时间</div>
				<div field="clearDate" headerAlign="center" allowSort="false">结清日期</div>
				<div field="responsibleUserNum" headerAlign="center" allowSort="false">保全责任人</div>
				<div field="responsibleOrgNum" headerAlign="center" allowSort="false">保全责任机构</div>
				
				<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
				<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
				<div field="assetsStatus" headerAlign="center" allowSort="false">状态</div>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="cre">处置方案发起</a>
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="move">逆移交</a>
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
	//逆移交
	function move(){
		var rows = grid.getSelecteds();
        if (rows.length > 0){
        	var json = nui.encode({"tbNplAssetsPerformings":rows});
	        $.ajax({
	            url: "com.bos.npl.assets.AssetsPerforming.movePerformingToTransfer.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	                alert("逆移交成功！");
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
	//发起资产保全
	function cre(){
		json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
		$.ajax({
            url: "com.bos.npl.assets.AssetsPerforming.createDispositionScheme.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	//跳往资产保全树页面
				git.go(nui.context+"/npl/assets/assets_tree.jsp?schemeId="+mydata.tbNplDispositionScheme.schemeId+"&processInstId="+mydata.processInstId+"&flag=1",parent);
            }
        });
        
	}
	//查看不良资产移入明细
	function view(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		if (1==rows.length){
			nui.open({
				url:nui.context+"/npl/assets/assets_performing_detail.jsp?performingId="+rows[0].performingId,
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
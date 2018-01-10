<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-23 15:36:30
  - Description:
-->
<head>
<title>资产处置明细信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="grid" class="nui-datagrid" style="width:100%;height:auto;margin-bottom:20px;"  sortMode="client"
	    url="com.bos.npl.assets.AssetsDispositionSchemeDetail.getSchemeDetailBySchemeId.biz.ext" dataField="tbNplDispostionSchemeDetails"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
			 <div type="checkcolumn">选择</div>      
			 <div field="productType" allowSort="true" headerAlign="center" dictTypeId="product">授信业务种类</div> 
			 <div field="contractNum" allowSort="true" headerAlign="center">合同编号</div>   
			 <div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001">币种</div> 
			 <div field="startDate" allowSort="true" headerAlign="center">合同起始日期</div>
			 <div field="expirationDate" allowSort="true" headerAlign="center">合同终止日期</div>
			 <div field="balanceAmt" allowSort="true" headerAlign="center">授信余额</div>
			 <div field="contractTotalAmt" allowSort="true" headerAlign="center">合同金额</div>
<!--			 <div field="overdueInterest" allowSort="true" headerAlign="center">表内外应收未收利息</div>
			 <div field="overdueDays" allowSort="true" headerAlign="center">逾期或垫款天数</div>-->
			 <div field="riskClassify" allowSort="true" headerAlign="center">五级分类状态</div>
			 <div field="dispostionType" allowSort="true" headerAlign="center" dictTypeId="XD_BQ0001">处置类型</div>
			 <div field="datailSchemeFlag" allowSort="true" headerAlign="center" dictTypeId="XD_BQ0002">是否添加明细</div>
      	</div>
	</div>
	<div  class="nui-toolbar" style="border-bottom:0;">
	    <a class="nui-button" iconCls="icon-add" id="biz_detail_add" onclick="add">引入</a>
	    <a class="nui-button" iconCls="icon-edit" id="biz_detail_update" onclick="edit">添加明细</a>
	    <a class="nui-button" iconCls="icon-node" id="biz_detail_view" onclick="view">查看</a>
	    <a class="nui-button" iconCls="icon-remove" id="biz_detail_remove" onclick="del">删除</a>
	</div>
	
<script type="text/javascript">
	nui.parse();
	query();
	
	//查询方案明细
	function query(){
		var grid = nui.get("grid");
		grid.load({"schemeId":"<%=request.getParameter("schemeId")%>"});
	}
	
	//添加
	function add(){
		var schemeId = "<%=request.getParameter("schemeId")%>";
		if(null==schemeId||""==schemeId){
			return;
		}
		nui.open({
			url:nui.context+"/npl/assets/assets_disposition_scheme_detail_add.jsp?schemeId="+schemeId,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"1200",
            height:"600",
            ondestroy: function(e) {
            	query();
            }
		});
	}
	//编辑手段信息
	function edit(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		if (1==rows.length){
			nui.open({
				url:nui.context+"/com.bos.npl.assets.schemeDispositionDetail.flow?schemeDetailId="+rows[0].schemeDetailId+"&editFlag=1",
				showMaxButton:true,
				title:"提示：可点击最大化按钮放大此窗口",
				width:"1200",
	            height:"600",
	            ondestroy: function(e) {
	            	query();
	            }
			});
        }else{
        	alert("请选择要一条编辑的资产明细信息");
        }
	}
	function view(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		if (1==rows.length){
			nui.open({
				url:nui.context+"/com.bos.npl.assets.schemeDispositionDetail.flow?schemeDetailId="+rows[0].schemeDetailId+"&editFlag=0",
				showMaxButton:true,
				title:"提示：可点击最大化按钮放大此窗口",
				width:"1200",
	            height:"600",
	            ondestroy: function(e) {
	            	query();
	            }
			});
        }else{
        	alert("请选择要一条编辑的资产明细信息");
        }
	}
	function del(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();		
        if (rows.length == 1){
        	var json = nui.encode({"tbNplDispositionSchemeDetail":rows[0]});
	        $.ajax({
	            url: "com.bos.npl.assets.AssetsDispositionSchemeDetail.delSchemeDetail.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	if(mydata.msg){
	            		alert(msg);
	            	}else{
	            		alert("删除成功！");
	                	query();
	            	}
	            }
	        });
        }else{
        	alert("请选择资产信息");
        }
	}
</script>
</body>
</html>
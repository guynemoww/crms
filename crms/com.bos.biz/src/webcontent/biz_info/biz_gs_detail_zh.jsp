<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:37:08
  - Description:
-->
<head>
<title>公司客户业务申请明细信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div class="nui-toolbar" style="text-align:left;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="biz_gs_detail_add" iconCls="icon-add" onclick="add()">增加</a>
	    <a class="nui-button" id="biz_gs_detail_update" iconCls="icon-edit" onclick="update('datagrid')">编辑</a>
	    <a class="nui-button" id="biz_gs_detail_view" iconCls="icon-node" onclick="view('datagrid')">查看</a>
		<a class="nui-button" id="biz_gs_detail_del" iconCls="icon-remove" onclick="del('datagrid')">删除</a>
	</div>
	<div id="datagrid" class="nui-datagrid" style="width:99.5%;"  sortMode="client"
	    url="com.bos.bizInfo.bizDetail.getAmountDetailsByApplyId.biz.ext" dataField="tbBizAmountDetails"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" allowResize="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>             
	        <div field="productType" allowSort="true" width="" headerAlign="center"  dictTypeId="product">业务品种</div> 
	        <div field="currencyCd" allowSort="true" width="" dictTypeId="CD000001" headerAlign="center">币种</div>
	        <div field="detailAmt" allowSort="true" width="" headerAlign="center" dataType="currency">金额</div>   
	    </div>
	</div>
	<input id="tbBizApply.applyId" class="nui-hidden nui-form-input" name ="tbBizApply.applyId"/>
	<input id="tbBizApply.partyId" class="nui-hidden nui-form-input" name ="tbBizApply.partyId"/>
	<input id="tbBizApply.ecifPartyNum" class="nui-hidden nui-form-input" name ="tbBizApply.ecifPartyNum"/>
	<input id="tbBizAmountApply.amountId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.amountId"/>
</center>
<script type="text/javascript">
	nui.parse();
	var applyId ="<%=request.getParameter("applyId")%>";
	var bizType ="<%=request.getParameter("bizType")%>";
	var proFlag ="<%=request.getParameter("proFlag")%>";
	var grid = nui.get("datagrid");
	var productType='';
	var bizHappenType='01';
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>","ratingType":'3'});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getAmountInfoByApplyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	productType = o.tbBizApply.productType;
            	bizHappenType = o.tbBizApply.bizHappenType;
            	nui.get("tbBizApply.applyId").setValue(o.tbBizApply.applyId);
            	nui.get("tbBizApply.partyId").setValue(o.tbBizApply.partyId);
            	nui.get("tbBizApply.ecifPartyNum").setValue(o.party.ecifPartyNum);
            	nui.get("tbBizAmountApply.amountId").setValue(o.tbBizAmountApply.amountId);
            	if(null==o.tbBizAmountApply.createTime||""==o.tbBizAmountApply.createTime){
					nui.get("biz_gs_detail_add").hide();
					nui.get("biz_gs_detail_del").hide();
					nui.get("biz_gs_detail_update").hide();
					nui.get("biz_gs_detail_view").hide();
				}
            	grid.load({"applyId":applyId});
			}
        });
		
		nui.get("biz_gs_detail_add").hide();
		nui.get("biz_gs_detail_del").hide();
		if("-1"!=proFlag){
			nui.get("biz_gs_detail_update").show();
		}
	}
	
	function add(){
		var oGrid=grid.getData();
		var amountId=nui.get("tbBizAmountApply.amountId").getValue();
		
		if("01"==bizType && ""!= oGrid.length && 0<oGrid.length){
			alert("单笔业务已存在明细信息！");
			return;
		}
		if(null==amountId||""==amountId){
			return;
		}
		var partyId = nui.get("tbBizApply.partyId").getValue();
		var ecifPartyNum = nui.get("tbBizApply.ecifPartyNum").getValue();
		nui.open({
			url:nui.context + "/biz/biz_product_detail/biz_product_add.jsp?amountId="+amountId+"&bizType="+bizType+"&proFlag="+proFlag+"&partyId="+partyId+"&productType="+productType+"&bizHappenType="+bizHappenType+"&ecifPartyNum="+ecifPartyNum,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	top.bizConWin = this;
            	grid.load({"applyId":applyId});
            }
		});
	}
	
	//删除业务明细
	function del(gr){
		var row = nui.get(gr).getSelected();
		if (row) {
			//规则校验：有合同状态不为06的合同不能删-调整发起合同还没挂过来所以用oldDetailId
			/* var json1 = {"oldDetailId":row.oldDetailId};
	   	    var msg = exeRule("RBIZ_0047","1",json1);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    } */
			//规则校验更改--如果该批复下没有生效的借据，可以删除该批复，并且将批复下的未失效的主合同变成失效--二期需求
			var json1 = {"oldDetailId":row.oldDetailId};
	   	    var msg = exeRule("RBIZ_0050","1",json1);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
		   	}
			nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.amountDetailId){
            		var json = nui.encode({"amountDetailId":row.amountDetailId});
	            	$.ajax({
			            url: "com.bos.bizInfo.bizInfo.deleteBizInfoLinkRate.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
			            	grid.load({"applyId":applyId});
			            	nui.alert("删除成功");
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
		}else{
			nui.alert("请选中一条记录");
		}
	}
	function update(gr){
		var row = nui.get(gr).getSelected();
		if (row) {
			var amountDetailId = row.amountDetailId;
			var partyId = nui.get("tbBizApply.partyId").getValue();
			var ecifPartyNum = nui.get("tbBizApply.ecifPartyNum").getValue();
    		nui.open({
	            url: nui.context + "/com.bos.bizProductDetail.getProductDetail.flow?amountDetailId="+amountDetailId+"&productType="+row.productType+"&modelFlag=01&bizType="+bizType+"&proFlag="+2+"&bizHappenType="+bizHappenType+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	            showMaxButton: true,
	            title: "业务品种明细信息",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	top.bizConWin = this;
            		grid.load({"applyId":applyId});
	            }
	        });
    	}else{
    		nui.alert("请选中一条记录");
    	}
	}
	function view(gr){
		var row = nui.get(gr).getSelected();
		if (row) {
			var amountDetailId = row.amountDetailId;
    		nui.open({
	            url: nui.context + "/com.bos.bizProductDetail.getProductDetail.flow?amountDetailId="+amountDetailId+"&productType="+row.productType+"&modelFlag=01&bizType="+bizType+"&proFlag=0",
	            showMaxButton: true,
	            title: "业务品种明细信息",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	top.bizConWin = this;
            		grid.load({"applyId":applyId});
	            }
	        });
    	}else{
    		nui.alert("请选中一条记录");
    	}
	}
</script>	
</body>
</html>
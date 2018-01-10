<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>业务明细页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>

<% 
  System.out.println("pAddress==========/biz/biz_product_detail/pjxx/biz_tx_all.jsp");
  System.out.println("ecifPartyNum=========="+request.getParameter("ecifPartyNum"));
 %>
 
<div id="panel1" class="nui-panel" 
	style="width:99.5%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<legend>
		   		<span> </span>
		</legend>
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="tx_view" iconCls="icon-node" onclick="edit(1)">查看</a>
				<a class="nui-button" id="tx_add_zp" iconCls="icon-add" onclick="add_zp()">新增纸票</a>
				<a class="nui-button" id="tx_add_dp" iconCls="icon-add" onclick="add_dp()">新增电票</a>
				<a class="nui-button" id="tx_edit" iconCls="icon-edit" onclick="edit(0)">编辑</a>
				<a class="nui-button" id="tx_remove" iconCls="icon-remove" onclick="remove()">删除</a>
				<a class="nui-button" id="tx_refuse" iconCls="icon-add" onclick="refuse()">拒绝电票</a>
				<a class="nui-button" id="tx_import" iconCls="icon-add" onclick="importExcel()">Excel 批量导入</a>
			</div>
			
			<div id="DataGrid" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizPjxx.GetTxList.biz.ext" dataField="txxxs"
				allowResize="false" showReloadButton="false"  allowCellEdit="false" 
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="takeoutacname" headerAlign="center" allowSort="true">出票人名称</div>
					<div field="benename" headerAlign="center" allowSort="true">收款人名称</div>
					<div field="billtype" headerAlign="center" allowSort="true"  dictTypeId="XD_SXYW0203">汇票类型</div>
					<div field="billmodel" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票模式</div>
					<div field="billno" headerAlign="center" allowSort="true">汇票号码</div>
					<div field="currsign" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="billamt" headerAlign="center" allowSort="true" >票面金额</div>
					<div field="billbegindate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">出票日期</div>
					<div field="billenddate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日期</div>
					<div field="forbidflag" headerAlign="center" allowSort="true" dictTypeId="XD_JZBSBJ01">禁止背书标记</div>
				</div>
			</div>
		</div>
	</div>
 </div>

<script type="text/javascript">

	nui.parse();
	var DataGrid = nui.get("DataGrid");
	// 隐藏按钮
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	if (proFlag != 1){
 		nui.get("tx_add_zp").hide();
		nui.get("tx_add_dp").hide();
		nui.get("tx_edit").hide();
		nui.get("tx_remove").hide();
		nui.get("tx_refuse").hide();
		nui.get("tx_import").hide(); 
	}
	var contractNum = "<%=request.getParameter("contractNum") %>"; // 合同编号
	var contractId = "<%=request.getParameter("contractId") %>"; // 合同ID
	var productType = "<%=request.getParameter("productType") %>"; // 产品编号
	var applyId = "<%=request.getParameter("applyId") %>"; // 业务申请编号
	var amountDetailId="";
	var ecifPartyNum = "";
	var partyId ="";
	function getDeailId(){
		var json = nui.encode({"applyId":applyId}); 
			 $.ajax({
		        url: "com.bos.bizInfo.bizInfo.getAmountDetailByApplyId.biz.ext",
		        type: 'POST',
		        data: json,
		        contentType:'text/json',
		        cache: false,
		        async: false,
		        success: function (data) {
		        debugger;
		        amountDetailId=data.txDetail.amountDetailId;
		        ecifPartyNum=data.party.ecifPartyNum;
		        partyId=data.party.partyId;
		        }
	        });
	}
	getDeailId();
	
	if(amountDetailId){
		// 查询票据信息
		init();
	}else{
		nui.alert("无业务明细信息，请检查！");
	}
	function init(){
		var json = nui.decode({"amountDetailId":amountDetailId}); 
		DataGrid.load(json);
	}
	
	// 添加贴现纸票
	function add_zp(){
		var jsonxx = {"amountDetailId":amountDetailId,"hpxs":"02"}; // 不能存在电票
		var msg = exeRule("RBIZ_0069", "1", jsonxx);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_tx_add_zp1.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum+"&productType="+productType,
	        title: "新增纸票信息", 
	        width: 1000, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
					init();
	           	 }
	        }
	    });
	}
	
	// 添加贴现电票
	function add_dp(){
		var jsonxx = {"amountDetailId":amountDetailId,"hpxs":"01"}; // 不能存在纸票
		var msg = exeRule("RBIZ_0069", "1", jsonxx);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		nui.open({
			url: nui.context + "/biz/biz_product_detail/pjxx/biz_tx_add_dp1.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum+"&productType="+productType+"&contractId="+contractId,
			title: "新增电票信息", 
			width: 1000, 
			height: 500,
			allowResize:true,
			showMaxButton: true,
			ondestroy: function (action) {
				if(action=="ok"){
					init();
				}
			}
		});
	}
	
	// 编辑
	function edit(v) {
		var row = DataGrid.getSelected();
		if (row) {
			if(v=="0"){
				if(row.billmodel=="02"){
					alert("电票信息不能修改！");
					return false;
				}else{
					var json = nui.decode({"applyDetailId":row.applyDetailId}); 
					var msg = exeRule("RBIZ_0076", "1", json);
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
				}
			}
			nui.open({
				url: nui.context+"/biz/biz_product_detail/pjxx/biz_tx_edit.jsp?applyDetailId="+row.applyDetailId+"&view="+v,
				title: "编辑票据信息", 
				width: 800,
				height: 500,
				allowResize:true,
				showMaxButton: true,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = row;
				},
				ondestroy: function (action) {
					if(action=="ok"){
						init();
					}
				}
			});
		}else{
			alert("请选择贴现信息！");
		}
	}
	
	// 删除
	function remove() {
		var rows = DataGrid.getSelected();
		if (null == rows) {
			nui.alert("请选择贴现信息！");
			return false;
		}
		// 删除前查询是否已经出票,防止出票后合同调整删除票据
		var json = nui.decode({"applyDetailId":rows.applyDetailId}); 
		var msg = exeRule("RBIZ_0076", "1", json);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		var json = nui.encode({"tbBizTxxxApply":rows});
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
			$.ajax({
				url: "com.bos.bizProductDetail.bizPjxx.DelTxxx.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					if (text.msg) {
						nui.alert(text.msg);
						return;
					}
					init();
				},
				error: function () {
					nui.alert("操作失败！");
				}
			});
		});
	}
	
	// 电票拒绝
	function refuse(){
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_tx_refuse_dp.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	        title: "拒绝电票", 
	        width: 1000, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
					init();
	           	 }
	        }
	    });
	}
	
	//Excel批量导入
 	function importExcel(){
	 	nui.open({
		        url: nui.context +"/grt/grtImportExcel/import_BizTxxx.jsp?amountDetailId="+amountDetailId+"&contractId="+contractId,
		        title: "Excel批量导入", 
		        width: 800, 
		    	height: 500,
		    	allowResize:true,
		    	showMaxButton: true,
		        ondestroy: function (action) {
		       		  if(action=="ok"){
						init();
		           	 } 
		        }
		    });
 	}
</script>
</body>
</html>
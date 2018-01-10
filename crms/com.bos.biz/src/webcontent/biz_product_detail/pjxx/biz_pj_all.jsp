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
  System.out.println("pAddress==========/biz/biz_product_detail/pjxx/biz_pj.jsp");
  System.out.println("ecifPartyNum=========="+request.getParameter("ecifPartyNum"));
 %>
 
<div id="panel1" class="nui-panel" 
	style="width:99.5%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amountDetail.amountDetailId" class="nui-hidden nui-form-input" name="amountDetail.amountDetailId"/>
	<input id="amountDetail.amountId" class="nui-hidden nui-form-input" name="amountDetail.amountId"/>
	<input id="amountDetail.oldDetailId" class="nui-hidden nui-form-input" name="amountDetail.oldDetailId"/>
	<input id="productDetail.applyDetailId" class="nui-hidden nui-form-input" name="productDetail.applyDetailId"/>
	<input id="productDetail.wtxmId" class="nui-hidden nui-form-input" name="productDetail.wtxmId"/>
	<input id="tbBizProductInfo.entityName" class="nui-hidden nui-form-input" name="tbBizProductInfo.entityName"/>
	<input id="tbBizProductInfo.productCd" class="nui-hidden nui-form-input" name="tbBizProductInfo.productCd"/>
		<legend>
		   		<span> </span>
		</legend>
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="pj_view" iconCls="icon-node" onclick="edit(1)">查看</a>
				<a class="nui-button" id="pj_add_zp" iconCls="icon-add" onclick="add_zp()">新增纸票</a>
				<a class="nui-button" id="pj_add_dp" iconCls="icon-add" onclick="add_dp()">新增电票</a>
				<a class="nui-button" id="pj_edit" iconCls="icon-edit" onclick="edit(0)">编辑</a>
				<a class="nui-button" id="pj_remove" iconCls="icon-remove" onclick="remove()">删除</a>
				<a class="nui-button" id="pj_refuse" iconCls="icon-add" onclick="refuse()">拒绝电票</a>
				<a class="nui-button" id="pj_import" iconCls="icon-add" onclick="importExcel()">Excel 批量导入</a>
			</div>
			<div id="DataGrid" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizPjxx.getPjList.biz.ext" dataField="pjxxs"
				allowResize="false" showReloadButton="false"  allowCellEdit="false" 
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="cprqc" headerAlign="center" allowSort="true" width="15%">出票人全称</div>
					<div field="skrqc" headerAlign="center" allowSort="true">收款人全称</div>
					<div field="payeebankname" headerAlign="center" allowSort="true">收款人开户行全称</div>
					<div field="pjhm" headerAlign="center" allowSort="true">票据号码</div>
					<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="hpje" headerAlign="center" allowSort="true" >汇票金额</div>
					<div field="hpxs" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票形式</div>
					<div field="hpcprq" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">汇票出票日期</div>
					<div field="hpdqrq" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">汇票到期日期</div>
				</div>
			</div>
		</div>
	</div>
 </div>

<script type="text/javascript">

	/* 票据查询与插入需要同时带入合同编号,否则综合授信项下会查询到其他合同的票据信息 */

	nui.parse();
	var DataGrid = nui.get("DataGrid");
	// 隐藏按钮
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	if (proFlag != 1){
	 	nui.get("pj_add_zp").hide();
		nui.get("pj_add_dp").hide();
		nui.get("pj_edit").hide();
		nui.get("pj_remove").hide();
		nui.get("pj_refuse").hide();
		nui.get("pj_import").hide();
	}
	var partyId = "<%=request.getParameter("partyId") %>"; // 客户编号
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum") %>"; // ECIF客户编号
	var contractNum = "<%=request.getParameter("contractNum") %>"; // 合同编号
	var amountDetailId = "<%=request.getParameter("amountDetailId") %>"; // 额度明细编号
	var contractId = "<%=request.getParameter("contractId") %>"; // 合同
	// 查询票据信息
	init();
	function init(){
		var json = nui.decode({"contractId":contractId,"amountDetailId":amountDetailId,"contractNum":contractNum}); 
		DataGrid.load(json);
	}
	
	// 添加银承纸票
	function add_zp(){
		// 检查是否已有电票 
		var jsonxx = {"amountDetailId":amountDetailId,"contractNum":contractNum,"hpxs":"02"}; 
		var msg = exeRule("RBIZ_0068", "1", jsonxx);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
 		nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_add_zp.jsp?contractId="+contractId+"&amountDetailId="+amountDetailId+"&partyId="+partyId+"&contractNum="+contractNum,
	        title: "新增纸票信息", 
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
	
	// 添加银承电票
	function add_dp(){
		// 检查是否已有纸票
		var json = {"amountDetailId":amountDetailId,"contractNum":contractNum,"hpxs":"01"};
		var msg = exeRule("RBIZ_0068", "1", json);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_add_dp.jsp?contractId="+contractId+"&amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum+"&contractNum="+contractNum,
	        title: "新增电票信息", 
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
	
	// 电票拒绝
	function refuse(){
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_refuse_dp.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	        title: "拒绝电票", 
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
	
	//编辑
	function edit(v) {
		var grid = nui.get("DataGrid");
	    var row = grid.getSelected();
	    if (row) {
	    	if(v=="0"){
	    		if(row.hpxs=="02"){
					alert("电票信息不能修改！");
		    		return false;
	    		}else{
		    		var json = nui.decode({"amountDetailId":row.amountDetailId,"contractNum":contractNum});
					var msg = exeRule("RBIZ_0072", "1", json);
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
	    		}
	    	}
	        nui.open({
	            url: nui.context+"/biz/biz_product_detail/pjxx/biz_pj_edit.jsp?applyDetailId="+row.applyDetailId+"&view="+v,
	            title: "编辑票据信息", 
	            width: 800,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                //iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                if(action=="ok"){
						init();
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择票据信息！");
	    }
	}
	
	//Excel批量导入
 	function importExcel(){
		nui.open({
			url: nui.context+"/grt/grtImportExcel/import_Biz_Pjxx.jsp?contractId="+contractId+"&amountDetailId="+amountDetailId+"&contractNum="+contractNum,
			title: "Excel 批量导入", 
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
	
	// 删除票据信息
	function remove() {
		var rows = DataGrid.getSelected();
		if (null == rows) {
			nui.alert("请选择票据信息！");
			return false;
		}
		// 删除前查询是否已经出票,防止出票后合同调整删除票据
		var json = nui.decode({"amountDetailId":rows.amountDetailId,"contractNum":contractNum}); 
		var msg = exeRule("RBIZ_0072", "1", json);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		var json = nui.encode({"tbBizPjxxApply":rows});
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action != "ok") {
	    		return;
	    	}
	    	 $.ajax({
	            url: "com.bos.bizProductDetail.bizPjxx.delPjxx.biz.ext",
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
</script>
</body>
</html>
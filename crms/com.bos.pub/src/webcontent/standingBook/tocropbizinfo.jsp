<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>对公授信基本数据查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="对公授信基本数据查询" >
<center>
<form id="form1" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="map/cp" required="false" class="nui-textbox nui-form-input"  />
		<label>借据号：</label>
		<input name="map/loanNum" required="false" class="nui-textbox nui-form-input"  />
		<label>合同号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input"  />
		<label>业务品种：</label>
		<input name="map/productType" required="false" class="nui-buttonEdit  nui-form-input" dictTypeId="product" onbuttonclick="selectProduct" emptyText="--请选择--"/>
		<label>行业：</label>
		<input name="map/industrialTypeCd" required="false" class="nui-buttonEdit nui-form-input" dictTypeId="XD_CDKH2011" onbuttonclick="selectTrade" emptyText="--请选择--" />
		<label>地区：</label>
		<input name="map/contryRegionCd" required="false" class="nui-dictcombobox  nui-form-input" dictTypeId="XD_KHCD0087" />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:45px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.bizinfo.tocropbiz.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="cp" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="creditAmt" headerAlign="center" allowSort="true" dataType="currency">授信额度</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">已投放金额</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001" >币种</div>
			<div field="startDate" headerAlign="center" allowSort="true" >放款日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
			<div field="yearRate" headerAlign="center" allowSort="true" dataType="currency">利率 </div>
			<div field="loanNum" headerAlign="center" allowSort="true" >借据号 </div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同号</div>
			<div field="dp" headerAlign="center" allowSort="true" >担保人</div>
			<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">抵质押物</div>
			<div field="industrialTypeName" headerAlign="center" allowSort="true" dictTypeId="XD_CDKH2011">行业</div>
			<div field="contryRegionName" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0087">地区</div>
			<div field="productName" headerAlign="center" allowSort="true" dictTypeId="product">业务品种</div>
			<div field="overdueFlag" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">逾期情况</div>
			<div field="loanFiveLevelClassification" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">五级分类</div>
			<div field="fourrEnterpriseSizeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0026">企业规模</div>
			<div field="economicCategoriesCode" headerAlign="center" allowSort="true" dictTypeId="CDKH0024">企业性质</div>
			<div field="userName" headerAlign="center" allowSort="true"  dictTypeId="user">主办客户经理</div>
			<div field="policyDecisionName" headerAlign="center" allowSort="true"  dictTypeId="user">审批人</div>
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
    	git.mask();
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask();
        });
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	
	function selectTrade(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_CDKH2011",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=product",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	
	//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.cp != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=228";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
	</script>
</body>
</html>
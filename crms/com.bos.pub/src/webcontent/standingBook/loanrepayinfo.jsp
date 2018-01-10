<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>贷款收回清单</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="贷款收回清单" >
<center>
<form id="form1" class="nui-form"action=""style="width:99.1%;overflow:hidden;margin-bottom:5px;" method="post" enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		<label>借据号：</label>
		<input name="map/businessNum" required="false" class="nui-textbox nui-form-input"  />
		<label>借款人：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />

		<label>经办机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
		
		<label>发生日期：</label>
		<div id="UsdAmt2">
		<input name="map/repayDate1" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"  style="width: 36%;"/>
		~<input name="map/repayDate2" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"  style="width: 36%;"/>
		</div>
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px" 
		url="com.bos.pub.standingbook.loaninfo.loanrepayinfo.biz.ext"
		dataField="loanrepayinfos"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="repayDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">发生日期</div>
			<div field="businessNum" headerAlign="center" allowSort="true" >借据号</div>
			<div field="cp" headerAlign="center" allowSort="true" >借款人</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">业务品种</div>
			<div field="mainSuretyMode" headerAlign="center" allowSort="true" dictTypeId="CDZC0005">主担保方式</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="yearRate" headerAlign="center" allowSort="true" >基准利率</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">借据金额</div>
			<div field="repayAmt" headerAlign="center" allowSort="true" dataType="currency">贷款收回金额</div>
			<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">贷款余额</div>
			<div field="extensionFlag" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">展期标志</div>
			<div field="loanActualPaymentDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">借款日</div>
			<div field="loanActualMaturity" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
			<div field="orgname" headerAlign="center" allowSort="true" >经办机构</div>	
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
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    //search();
    
    function reset(){
		form.reset();
		search();
	}
	    //导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.contractNum != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=231";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
    
 	  	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }

	//导出
    function exportEmp()
    {
    	
        var rows = grid.findRows(function(row){
   	 	if(row.contractNum != null) return true;
	    });
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=231";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }   
	</script>
</body>
</html>
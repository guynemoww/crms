<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>放款流水台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="放款流水台账" >
<center>
<form id="form1" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
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
		<a class="nui-button"  iconCls="icon-search" onclick="getBatch()">还款计划</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.loaninfo.payoutldinfo.biz.ext"
		dataField="payoutldInfos"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn" >序号</div>
			<div field="startDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">发生日期</div>
			<div field="loanNum" headerAlign="center" allowSort="true" >借据号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >借款人</div>
			<div field="productCd" headerAlign="center" allowSort="true" dictTypeId="product">业务品种</div>
			<div field="mainSuretyMode" headerAlign="center" allowSort="true" dictTypeId="CDZC0005">主担保方式</div>
			<div field="yearRate" headerAlign="center" allowSort="true" >基准利率</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">发放金额</div>
			<div field="loanActualPaymentDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">借款日</div>
			<div field="loanActualMaturity" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div>
			<div field="extensionFlag" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">展期标志</div>
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
    	git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
   // search();
    
    function reset(){
		form.reset();
		search();
	}
	
	function getBatch(){
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		nui.open({
	            url:nui.context+"/pub/standingBook/openRepaymentPlan.jsp?loanDetailId="+row.loanDetailId,
	            showMaxButton: true,
	            title: "还款计划",
	            width: 800,
	            height: 500,
	            ondestroy: function(e) {
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
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=227";
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
	</script>
</body>
</html>
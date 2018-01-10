<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>外币流动资金贷款查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:hidden;">
<div title="外币流动资金贷款查询" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;margin-bottom:5px">
	<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="map/partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<!-- <input name="map/partyNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/> -->
			<label>合同编号：</label>
			<input name="map/contractNum" required="false" id="laidstart" class="nui-textbox nui-form-input" vtype="maxLength:32" onvaluechanged="onregDueDate"/>~
			<label>客户编号：</label>
			<input name="map/partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>借据编号：</label>
			<input name="map/loanNum" required="false" id="laidend" class="nui-textbox nui-form-input" vtype="maxLength:32" onvaluechanged="onDueDate"/>
			<!-- 
			<label>经办机构：</label>
			<input name="map/orgNum" required="false" id="laidstart" class="nui-datepicker nui-form-input" vtype="maxLength:32" onvaluechanged="onregDueDate"/>~
			<label>放款日期：</label>
			<input name="map/userNum" required="false" id="laidend" class="nui-datepicker nui-form-input" vtype="maxLength:32" onvaluechanged="onDueDate"/>
			 -->
	</div>
	<div class="nui-toolbar" style="padding-right:85px;text-align:right;padding-top:5px;padding-bottom:5px;height:auto" 
    borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  iconCls="icon-download" onclick="downloadExcel()" type="submit">导出</a>		--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetForeignCurrencyLiquidityList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
	<%--	<div type="checkcolumn" >选择</div>--%>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>	
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>	
		<div field="contractNum" headerAlign="center" allowSort="true" >合同号码	</div>	
		<div field="loanNum" headerAlign="center" allowSort="true" >借据编号</div>	
		<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">贷款余额</div>	
		<div field="usdloanBalance" headerAlign="center" allowSort="true" dataType="currency">折美元贷款余额</div>	
		<div field="handlingDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">放款日</div>	
		<div field="orgname" headerAlign="center" allowSort="true">放款行</div>
		<div field="yearRate" headerAlign="center" allowSort="true"  dataType="currency">实际利率</div>
		<div field="fkcurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="loanAmt" headerAlign="center" allowSort="true"  dataType="currency">放款金额</div>
		<div field="startIntrestDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">起息日</div>
		<div field="loanActualMaturity" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div>
		<div field="repayDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">还款日</div>
		<div field="repayAmt" headerAlign="center" allowSort="true" dataType="currency">还款金额</div>
		<div field="guapartyName" headerAlign="center" allowSort="true" >担保人名称</div>	
		<div field="loanType" headerAlign="center" allowSort="true" dictTypeId="CDZC0005">担保方式</div>		
		<div field="lendAccount" headerAlign="center" allowSort="true" >放款账户</div>	
		<div field="repaymentAccount" headerAlign="center" allowSort="true">还款账户</div>	
		<div field="loanDirection" headerAlign="center" allowSort="true" dictTypeId="CDKH0095">行业分类</div>	
		<div field="loandays" headerAlign="center" allowSort="true" dataType="currency">贷款天数</div>	
		<div field="interestreceivable" headerAlign="center" allowSort="true" dataType="currency">付息金额</div>	
		<div field="loantotalamt" headerAlign="center" allowSort="true">本息合计</div>
		<div field="loanFiveLevelClassification" headerAlign="center" allowSort="true" >贷款质量</div>		
		<div field="creditType" headerAlign="center" allowSort="true" dictTypeId="CDXY0185">贷款类型</div>
		<div field="rateFloatMember" headerAlign="center" allowSort="true" dataType="currency">加点数</div>
		<div field="" headerAlign="center" allowSort="true" >备注</div>
	</div>
</div>
	</center>
</div>
</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	    function search() {
	    git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
    //search();
	
    
    function reset(){
		form.reset();
	}
     
    
	//选择经办机构
	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择经办机构",
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
    
        
//选择人员（无权限）
function selectEmp(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/pub/standingBook/select_employee.jsp",
        showMaxButton: true,
        title: "选择经办人",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
}
    	
	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
////////////////////日期判断结束
			//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.loanNum != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=211";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	</script>
</body>
</html>

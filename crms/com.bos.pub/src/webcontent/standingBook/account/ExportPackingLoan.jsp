<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>出口打包贷款业务台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="出口打包贷款业务台账查询" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;margin-bottom:5px;">
	<div class="nui-dynpanel" columns="6">
			<label>合同编号：</label>
			<input name="map/contractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>信用证号：</label>
			<input name="map/lcNo" allowInput="false" class="nui-textbox nui-form-input" vtype="maxLength:32"/>
			<label>借款人名称：</label>
			<input name="map/partyName" required="false" id="laidstart" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<!-- <label>：</label>
			<input name="" required="false" id="laidend" class="nui-datepicker nui-form-input" vtype="maxLength:32" onvaluechanged="onDueDate"/>
			 -->
	</div>
	<div class="nui-toolbar" style="padding-right:45px;text-align:right;padding-top:5px;padding-bottom:5px;height:auto" 
    borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<%--	<a class="nui-button"  iconCls="icon-download" onclick="downloadExcel()" type="submit">导出</a>--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetExportPackingLoanList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>
		<div field="partyName" headerAlign="center" allowSort="true">借款人名称</div>		
		<div field="acceptingCreditBank" headerAlign="center" allowSort="true" >开证行</div>
		<div field="lcNo" headerAlign="center" allowSort="true" >信用证号</div>			
		<div field="lcAmount" headerAlign="center" allowSort="true" dataType="currency">信用证余额</div>	
		<div field="startDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">借款日</div>	
		<div field="expirationDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">到期日</div>	
		<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">金额</div>	
		<div field="repayAmt" headerAlign="center" allowSort="true" dataType="currency">已还款额</div>	
		<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">贷款余额	</div>	
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
	   	 		if(row.partyName != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=213";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	</script>
</body>
</html>



<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>进口保理业务台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="进口保理业务台账查询" >
<center>
 <form id="form1" action="" method="post" enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
			<label>客户编号：</label>
			<input name="map/partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>客户名称：</label>
			<input name="map/partyName"  class="nui-textbox nui-form-input" onbuttonclick="selectEmp"/>
			<label>合同编号：</label>
			<input name="map/contractNum" required="false" id="laidstart" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>合同币种：</label>
			<input name="map/dcurrencyCd" allowInput="false" required="false" class="nui-buttonEdit" vtype="maxLength:32"  onbuttonclick="selectDictItems"/>
			<label>放款币种：</label>
			<input name="map/currencyCd" allowInput="false" required="false"  class="nui-buttonEdit" vtype="maxLength:32"  onbuttonclick="selectDictItems"/>
					
			<label>放款金额：</label>
			<div>
			<input name="map/loanAmt1" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />~
			<input name="map/loanAmt2" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			</div>
			<label>担保起始日：</label>
			<div>
			<input name="map/assureStartDate1" id="laidstart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate" dateFormat="yyyy-mm-dd"/>~
			<input name="map/assureStartDate2" id="laidend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate" dateFormat="yyyy-mm-dd"/>
			</div>
			
			<label>担保到期日：</label>
			<div>
			<input name="map/expirationDate1" id="removestart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate1" dateFormat="yyyy-mm-dd"/>~
			<input name="map/expirationDate2" id="removeend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate1" dateFormat="yyyy-mm-dd"/>
			</div>					
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>	--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetTheImportFactorList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>	
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>	
		<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>	
		<div field="productName" headerAlign="center" allowSort="true" >业务品种</div>	
		<div field="concurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">合同币种</div>		
		<div field="contractTotalAmt" headerAlign="center" allowSort="true" dataType="currency">合同金额</div>	
		<div field="bcurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">放款币种</div>	
		<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">放款金额</div>	
		<div field="assureStartDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">担保起始日</div>	
		<div field="assureExpireDate" headerAlign="center" allowSort="true"  dateformat="yyyy-MM-dd">担保到期日</div>
		<div field="ainvoiceCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">发票币种</div>
		<div field="invoiceAmount" headerAlign="center" allowSort="true" dataType="currency">发票金额</div>
		<div field="orgname" headerAlign="center" allowSort="true">出账机构</div>		
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
    
			   
		function selectDictItems(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择字典",
            width: 300,
            height: 500,
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
			
function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/rule/product.jsp",
            title: "选择业务品种",
            width: 200,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.split(":")[1]);//alert(nui.encode(data));
                        btnEdit.setText(data.split(":")[1]);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	},
        	onload: function () {
                        var iframe = this.getIFrameEl();
                        //iframe.contentWindow.save();
                        //this.max();//最大化窗口
                    }
        	
        });            
}

	//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.partyNum != null) return true;
		});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=218";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }		
	</script>
</body>
</html>

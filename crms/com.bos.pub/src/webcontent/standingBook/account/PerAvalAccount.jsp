<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>保付加签业务台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="保付加签业务台账查询" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;margin-bottom:5px">
	<div class="nui-dynpanel" columns="6">
			<label>申请人：</label>
			<input name="map/perAvalUse" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

			<label>保付币种：</label>
			<input name="map/issuedCurrency" allowInput="false" required="false"  class="nui-buttonEdit" vtype="maxLength:32"  onbuttonclick="selectDictItems"/>

			<div >
			<label>保付金额：</label>
			</div>
			<div  id="ChnAmt2">
			<input name="map/issuedAmount1" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
			<input name="map/cmap/issuedAmount2hnAmtMax"  class="nui-textbox nui-form-input"  style="width: 38%;"/>
			</div>
					
			<div >
			<label>保付日期：</label>
			</div>
			<div >
			<input name="map/issuedStartDate" id="laidstart"  class="nui-datepicker nui-form-input" vtype="maxLength:40"  onvaluechanged="onregDueDate" />-
			<input name="map/issuedEndDate" id="laidend" class="nui-datepicker nui-form-input" vtype="maxLength:32"  onvaluechanged="onDueDate"/>
			</div>
			

	</div>
	<div class="nui-toolbar" style="padding-right:85px;text-align:right;padding-top:5px;padding-bottom:5px;height:auto" 
    borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  iconCls="icon-download" onclick="downloadExcel()" type="submit">导出</a>--%>			
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetPerAvalAccountList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="issuedDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">保付日期</div>
		<div field="orgname" headerAlign="center" allowSort="true">经办行</div>
		<div field="perAvalUse" headerAlign="center" allowSort="true">申请人名称</div>			
		<div field="aissuedCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">保付币种</div>	
		<div field="issuedAmount" headerAlign="center" allowSort="true" dataType="currency">保付金额</div>	
		<div field="poundageRatio" headerAlign="center" allowSort="true"  dataType="currency">手续费</div>	
		<div field="guacurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">保证金币种</div>
		<div field="accBalance" headerAlign="center" allowSort="true" dataType="currency">保证金金额</div>	
		<div field="issuedEndDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">对外付款日期</div>	
		<div field="aissuedCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">汇票币种</div>	
		<div field="issuedAmount" headerAlign="center" allowSort="true"  dataType="currency">汇票金额</div>	
		<div field="issuedEndDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">汇票到期日</div>	
		<div field="" headerAlign="center" allowSort="true" >汇票编号</div>	
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
	   	 		if(row.perAvalUse != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");//accountManager.flow
				//com.primeton.example.excel.empManager.flow
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=212";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	</script>
</body>
</html>

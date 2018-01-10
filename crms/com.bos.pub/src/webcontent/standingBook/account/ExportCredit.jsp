<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>出口代付业务台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="出口代付业务台账查询" >
<center>
 <form id="form1" action="" method="post" enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
	
			<label>发票编号：</label>
			<input name="map/invoiceNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			
			<label>发票币种：</label>
			<input name="map/invoiceCurrency" allowInput="false" class="nui-buttonEdit"  onbuttonclick="selectCurrcyCd" />
			
			<label>融资币种：</label>
			<input name="map/currencyCd" required="false" id="" class="nui-buttonEdit" onbuttonclick="selectCurrcyCd" vtype="maxLength:32"/>
			
			<label>融资金额：</label>
			<div>
			<input name="map/loanAmt1" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />~
			<input name="map/loanAmt2" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			</div>
			
			<label>融资利率：</label>
			<div>
			<input name="map/yearRate1" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />~
			<input name="map/yearRate2" required="false" id="" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			</div>

			<label>起息日：</label>
			<div>
			<input name="map/startDate1" id="laidstart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate"/>~
			<input name="map/startDate2" id="laidend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate"/>
			</div>
			
			<label>到期日：</label>
			<div>
			<input name="map/endDate1" id="removestart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate1"/>~
			<input name="map/endDate2" id="removeend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate1"/>
			</div>					
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetExportCreditList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="invoiceNo" headerAlign="center" allowSort="true" >发票编号</div>
		<div field="ainvoiceCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">发票币种</div>	
		<div field="invoiceAmount" headerAlign="center" allowSort="true" dataType="currency">发票金额</div>	
		<div field="bcurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">融资币种</div>	
		<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">融资金额</div>	
		<div field="yearRate" headerAlign="center" allowSort="true" dataType="currency">融资利率</div>
		<div field="exportCreditProduct" headerAlign="center" allowSort="true">出口代付品种</div>
		<div field="startDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">起息日</div>	
		<div field="expirationDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div>
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
    
     //币种
function selectCurrcyCd(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择币种",
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
	function onregDueDate1(){
		var laidstart = nui.get("removestart").getValue();//起始日期
		var laidend = nui.get("removeend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("removeend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removestart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate1(){
		var laidstart = nui.get("removestart").getValue();//起始日期
		var laidend = nui.get("removeend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removeend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}	
////////////////////日期判断结束
	//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.invoiceNo != null) return true;
		});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=214";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
	</script>
</body>
</html>

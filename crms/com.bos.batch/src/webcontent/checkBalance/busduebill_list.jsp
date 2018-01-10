<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="总分对账明细" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
<input name="subjectno" required="false" class="nui-hidden" vtype="maxLength:20" value="<%=request.getParameter("subjectno") %>"/>
	<div class="nui-dynpanel" columns="6">
		
		<label>机构号：</label>
		<input name="loan.mforgid" 
		required="false" class="nui-text"
		 value="<%=request.getParameter("orgNum") %>"/>
		
		<label>币种：</label>
		<input name="loan.currencyCd" 
		required="false" class="nui-text"  dictTypeId="CD000001"
		vtype="maxLength:10" value="<%=request.getParameter("businesscurrency") %>"/>
	
		<label>会计科目：</label>
		<input
		id="subjectname" required="false" 
		class="nui-text" vtype="maxLength:20" 
		value="<%=request.getParameter("subjectname") %>" />
		
		<label>余额类型：</label>
		<input name="balancetype" class="nui-text" 
		 value="<%=request.getParameter("balancetype") %>"
		 dictTypeId="XD_RZCD0009"/>
		
	</div>
</div>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
	</div>
</div>			
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.checkResultQry.getDueBills.biz.ext"
	dataField="loans" allowAlternating="true" 
	allowResize="false" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div field="businessNum" headerAlign="center" allowSort="true">T24业务标识号</div>
		<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">发放金额</div>
		<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">本金总余额</div>
		<div field="normalbalance" headerAlign="center" allowSort="true" dataType="currency">正常余额</div>
		<div field="overduebalance1" headerAlign="center" allowSort="true" dataType="currency" >三个月以内逾期余额</div>
		<div field="overduebalance2" headerAlign="center" allowSort="true" dataType="currency" >三个月以上逾期余额 </div>
		<div field="dullbalance" headerAlign="center" allowSort="true" dataType="currency">呆滞余额</div>
		<div field="badbalance" headerAlign="center" allowSort="true" dataType="currency">呆账余额</div>
		<div field="discountsum" headerAlign="center" allowSort="true" dataType="currency">贴现实贴金额</div>
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
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
       
    }
    search();
    
    function reset(){
		form.reset();
	}


	</script>
</body>
</html>

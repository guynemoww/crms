<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25

  - Description:TB_ACC_CUSTOMER_FINANCE, AccCustomerFinance--> <head>
<%@include file="/common/nui/common.jsp"%> </head> <body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;"> <input
	type="hidden" id="reportType" name="reportType"
	value="<%=request.getParameter("reportType") %>" class="nui-hidden" />
<input type="hidden" id="financeId" name="financeId"
	value="<%=request.getParameter("financeId") %>" class="nui-hidden" />
<input type="hidden" id="financeTypeCd" name="financeTypeCd"
	value="<%=request.getParameter("financeTypeCd") %>" class="nui-hidden" />

<input type="hidden" name="reportStatus" value="1" value=""
	class="nui-hidden" /> <div id="balanceDiv"> </div> </div> <script
	type="text/javascript">

nui.parse();
var form = new nui.Form("#form1");
var financeId="<%=request.getParameter("financeId") %>";//客户财务信息表ID
var reportType="<%=request.getParameter("reportType") %>";//财报类别
var financeTypeCd="<%=request.getParameter("financeTypeCd") %>";//财报类型
var view="<%=request.getParameter("view") %>";//页面状态：查看1、修改0
var url="<%=request.getContextPath()%>";
var logData=[];
function initForm() {
//判断导入跳转时取新生成的ID；
var uploadFinanceId="<%=request.getAttribute("financeId")%>";
var uploadReportType="<%=request.getAttribute("reportType")%>";
var uploadfinanceTypeCd="<%=request.getAttribute("financeTypeCd")%>";
if (financeId ==null || financeId =='null'||financeId ==''){
   financeId = uploadFinanceId;
   nui.get("financeId").value=financeId;
}
if (reportType ==null || reportType =='null'||reportType ==''){
    reportType = uploadReportType;
     nui.get("reportType").value=reportType;
}
if (financeTypeCd ==null || financeTypeCd =='null'||financeTypeCd ==''){

    financeTypeCd = uploadfinanceTypeCd;
     nui.get("financeTypeCd").value=financeTypeCd;
     
}
    var o=form.getData();
	var json=nui.encode(o);
	$.ajax({
        url: "com.bos.acc.accnfdsheet.queryAccNfdSheet.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		$('#balanceDiv').html('<h2>模版没有配置，请配置模版</h2>');
        	} else {
        		initLogs(text.accNfdSheets);
        		showBalanceSheet();
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
	});
}
initForm();
function initLogs(logs) {
	//解析日志的meta信息及数据
	if (!logs||logs.length<1){
	   return;
	}
	for(var i=0,len=logs.length; i<len; i++) {
		var log=logs[i];
		logData[logData.length]=log;
	}
}


function showBalanceSheet() {
  var html='<div class="nui-tabs  bg-toolbar"  activeIndex="0" id="balanceDiv" refreshOnClick="true" style="width:auto;height:600px;">';
  html += '<div title="报表附注信息"  url="'+url+'/acc/accnfdsheet/acc_note_information.jsp?financeId='+financeId+'&view='+view+'&sheetCode=01"></div>';
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		 if(log.sheetCode=="01" || log.sheetCode=="02" || log.sheetCode=="03"|| log.sheetCode=="05"){
		    if(view=='1'){
		      html += '<div title="'+log.sheetName+'" refreshOnClick="true" url="'+url+'/acc/accnfdsheet/acc_finance_sheet_view.jsp?financeId='+financeId+'&view='+view+'&sheetCode='+log.sheetCode+'&reportType='+reportType+'&financeTypeCd='+financeTypeCd+'" ></div>';
		    }else{
		      html += '<div title="'+log.sheetName+'" refreshOnClick="true" url="'+url+'/acc/accnfdsheet/acc_finance_sheet_edit.jsp?financeId='+financeId+'&view='+view+'&sheetCode='+log.sheetCode+'&reportType='+reportType+'&financeTypeCd='+financeTypeCd+'" ></div>';
		    }
		 }
	}
	  html += '<div title="财务指标" refreshOnClick="true" url="'+url+'/acc/accnfdsheet/acc_index_calculation.jsp?financeId='+financeId+'&view='+view+'&sheetCode=06&reportType='+reportType+'"></div>';
	 if (view!='1' ) {
	   html += '<div title="正式确认" url="'+url+'/acc/accnfdsheet/acc_official_confirm.jsp?financeId='+financeId+'&view='+view+'&sheetCode=07&reportType='+reportType+'"></div>';
	 }
	html +='</div>';
	$('#balanceDiv').html(html);
	nui.parse($('#balanceDiv')[0]);
}

</script> </body> </html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-页签(资产负债表、损益表、现金流量表、利润表、收入支出表)
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title></title> </head> <style>
           td{
		border:1px solid black;	
		text-align:left;
	}
</style> <body> <center> <br> <div id="form1"
	style="width:98%;height:auto;overflow:hidden;text-align:center;margin-top:0px;">

<input name="finanysisProgramId"
	value="<%=request.getParameter("finanysisProgramId") %>"
	class="nui-hidden" /> <div id="balanceDiv"
	style="width:98%;height:auto;overflow:hidden; text-align:left;border:1px solid black">

</div> </fieldset> </div></center> <script type="text/javascript">
nui.parse();
var logForm=new nui.Form('#balanceDiv');
var logData=[];
var statementData=[];
var form = new nui.Form("#form1");
var moduleId="<%=request.getParameter("moduleId") %>";//模块明细id
var finanysisProgramId="<%=request.getParameter("finanysisProgramId") %>";//分析方案id

var indexData=null;
var preFlag ="";
function initForm() {
    git.mask("form1"); 
	var json=nui.encode({"moduleId": moduleId,"finanysisProgramId":finanysisProgramId});
	$.ajax({
        url: "com.bos.acc.analy.getAccAnalysisData.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        		git.unmask("form1");
        	} else {
        	    initLogs(text.accAnyModuleDetails);
        	    indexData=text.indexData;
        		showBalanceSheet();
        		git.unmask("form1");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            git.unmask("form1");
        }
	});
}
initForm();


function initLogs(logs) {
	//解析模块信息
	if (!logs||logs.length<1){
	   return;
	}
	for(var i=0,len=logs.length; i<len; i++) {
		var log=logs[i];
		logData[logData.length]=log;
	}
}

//展示资产负债表
function showBalanceSheet() {
  var html='';
	 //展示模块信息
	if (logData.length<1) {
		$('#balanceDiv').html('<h2>模版没有配置，请配置模版</h2>');
		return;
	}
	//构造表单

	   html += '<div class="nui-dynpanel" columns="4" colWidth="25%,25%,25%,25%">';
	   html += '<label></label>';
	   html += '<label>'+indexData['a'+'FINANCE_DEADLINE']+'</label>';
	   html += '<label>'+indexData['b'+'FINANCE_DEADLINE']+'</label>';
	   html += '<label>'+indexData['c'+'FINANCE_DEADLINE']+'</label>';
		
	
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		 html += '<div style="width:100%">'
		 html += '<label>'+log.indexName+'</label>';
		 html += '</div>';
		 //第一期
		if(indexData['a'+log.indexCode]==null){
          html += '<label></label>';
        }else{
          html += '<label>'+formatMoney(indexData['a'+log.indexCode].toFixed(6))+'</label>';
        }
	     //第二期
		if(indexData['b'+log.indexCode]==null){
          html += '<label></label>';
        }else{
          html += '<label>'+formatMoney(indexData['b'+log.indexCode].toFixed(6))+'</label>';
        }
         //第三期
		if(indexData['c'+log.indexCode]==null){
          html += '<label></label>';
        }else{
          html += '<label>'+formatMoney(indexData['c'+log.indexCode].toFixed(6))+'</label>';
        }
        
	}
	
	html += '</div>';
	$('#balanceDiv').html(html);
	nui.parse($('#balanceDiv')[0]);
}
function formatMoney(s) {
    if (s == null || s == "") return "0.00";
    s = s.toString().replace(/^(\d*)$/, "$1.");
    s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
    s = s.replace(".", ",");
    var re = /(\d)(\d{3},)/;
    while (re.test(s))
      s = s.replace(re, "$1,$2");
      s = s.replace(/,(\d\d)$/, ".$1");
    return s;
}

</script> </body> </html>

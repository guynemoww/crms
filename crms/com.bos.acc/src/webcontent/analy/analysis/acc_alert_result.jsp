<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:预警结果
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title>预警结果</title> </head> <style>
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
var modelId="<%=request.getParameter("modelId") %>";//模块明细id
var finanysisProgramId="<%=request.getParameter("finanysisProgramId") %>";//分析方案id
var finanysisDetailId="<%=request.getParameter("finanysisDetailId") %>";//分析方案明细id

var indexData=null;
var preFlag ="";
function initForm() {
    git.mask("form1"); 
	var json=nui.encode({"modelId": modelId,"finanysisProgramId":finanysisProgramId,"finanysisDetailId":finanysisDetailId});
	$.ajax({
        url: "com.bos.acc.analy.getAccAlertData.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        		git.unmask("form1");
        	} else {
        	    initLogs(text.accAlertRules);
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

	   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';

	   html += '<label>'+'检查日期: '+indexData['CREATE_TIME']+'</label>';
	   html += '<label></label>';
	   html += '<label></label>';
	   html += '<div style="width:100%;background-color: #cccccc;font-weight: bold">';
	   html += '<label>财务粉饰预警说明</label>';
	   html += '</div>';
	   html += '<div style="width:100%;background-color: #cccccc;font-weight: bold">';	   
	   html += '<label>检查值</label>';
	   html += '</div>';
	   html += '<div style="width:100%;background-color: #cccccc;font-weight: bold">';
	   html += '<label>检查结果</label>';
	   html += '</div>';
	   
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		//预警说明
		 html += '<div style="width:100%">'
		 html += '<label>'+log.alertDesc+'</label>';
		 html += '</div>';
		 //检查值
		if(indexData[log.indexCode]==null){
          html += '<label></label>';
        }else{
          if(indexData[log.indexCode]=='9999'){
          html += '<label>'+NaN+'</label>';
          }else{
          html += '<label>'+formatMoney(indexData[log.indexCode].toFixed(6)*100)+'%'+'</label>';
          }
        }
	     //检查结果
		if(indexData['a'+log.indexCode]==null){
          html += '<label></label>';
        }else{
          if(indexData['a'+log.indexCode] =='未通过'){
          html += '<div style="width:100%;background-color: red;font-weight: bold">';
          html += '<label>'+indexData['a'+log.indexCode]+'</label>';
          html += '</div>';
          }
          else{
          html += '<label>'+indexData['a'+log.indexCode]+'</label>';
          }
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

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-页签(资产负债表、损益表、现金流量表、利润表、收入支出表)
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title></title> </head> <div id="dw"
	style="height:auto;overflow:hidden;text-align:right;"> <label>金额单位：元</label>
</div> <style>
           td{
		border:1px solid black;	
		text-align:left;
	}
</style> <body> <div id="form1"
	style="width:98%;height:auto;overflow:hidden;text-align:center;margin-top:20px;">
<input name="financeId" value="<%=request.getParameter("financeId") %>"
	class="nui-hidden" /> <input name="financeStatementId" value=""
	class="nui-hidden" id="financeStatementId" /> <div id="balanceDiv"
	style="width:98%;height:auto;overflow:hidden; text-align:left;border:1px solid black">

</div> </div> <script type="text/javascript">
nui.parse();
var logForm=new nui.Form('#balanceDiv');
var logData=[];
var statementData=[];
var form = new nui.Form("#form1");
var view="<%=request.getParameter("view") %>";//页面状态：查看1、修改0
var reportType="<%=request.getParameter("reportType") %>";//财报类别
var financeTypeCd="<%=request.getParameter("financeTypeCd") %>";//财报类型
var sheetCode="<%=request.getParameter("sheetCode") %>";
var reportStatus="1";
var financeId="<%=request.getParameter("financeId") %>";
var financeStatementId="";
var financeData=null;
function initForm() {
    git.mask("form1");
	var json=nui.encode({"reportType": reportType,"sheetCode":sheetCode,"reportStatus":reportStatus,"financeId":financeId});
	$.ajax({
        url: "com.bos.acc.accnfdsheet.queryCustomerFinanceData.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        		git.unmask("form1");
        	} else {
        	    initLogs(text.accNfdItems);
        	    initStatementDatas(text.accFinanceStatementDatas);
        	    financeStatementId=text.financeStatementId;
        	    nui.get("financeStatementId").value=financeStatementId;
        	    financeData=text.financeData;
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

function initStatementDatas(statementDatas){
 if (!statementDatas||statementDatas.length<1){
	    return;
   }
   for(var i=0,len=statementDatas.length; i<len; i++) {
	   var statement=statementDatas[i];
	   statementData[statementData.length]=statement;
   }
}

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

//展示资产负债表
function showBalanceSheet() {
  var html='';
	 //展示日志详细信息
	if (logData.length<1) {
		$('#balanceDiv').html('<h2>模版没有配置，请配置模版</h2>');
		return;
	}
	//构造表单
	if(sheetCode=='02'||sheetCode=='03'){
		if(financeTypeCd =='1'){//年报
		   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
		   html += '<label>项目</label>';
		   html += '<label>本年累计</label>';
		   html += '<label>上年累计</label>'; 
		}else if(financeTypeCd =='4'){//月报
		   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
		   html += '<label>项目</label>';
		   html += '<label>本月数</label>';
		   html += '<label>本年累计 </label>';
	   }else if(financeTypeCd =='4'){//季报
		   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
		   html += '<label>项目</label>';
		   html += '<label>本季数</label>';
		   html += '<label>本年累计 </label>';
	   }else{
		   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
		   html += '<label>项目</label>';
		   html += '<label>本期数</label>';
		   html += '<label>本年累计 </label>';
	   }
	}else if(sheetCode=='05'){
	   html += '<div class="nui-dynpanel" columns="2">';
	}else{
		if(financeTypeCd =='1'){//年报
		  html += '<div class="nui-dynpanel" columns="6" colWidth="14%,22%,14%,14%,22%,14%">';
		  html += '<label>项目</label>';
		  html += '<label>年初数</label>';
		  html += '<label>年末数 </label>';
		  html += '<label>项目</label>';
		  html += '<label>年初数</label>';
		  html += '<label>年末数</label>';
	  }else{
	  	  html += '<div class="nui-dynpanel" columns="6" colWidth="14%,22%,14%,14%,22%,14%">';
		  html += '<label>项目</label>';
		  html += '<label>年初数</label>';
		  html += '<label>期末数 </label>';
		  html += '<label>项目</label>';
		  html += '<label>年初数</label>';
		  html += '<label>期末数</label>';
	  }
	}
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		if(log.itemType=='1'){
			html += '<div style="width:100%;background-color: #cccccc;font-weight: bold">';
		    html += '<label>'+log.itemName+'</label>';
		    html += '</div>';
		    html += '<label> </label>';
		    html += '<label> </label>';
		    
		}else if(log.itemType=='4'){
		
			 html += '<div style="width:100%;background-color: #cccccc;text-align:left;font-weight: bold">'+log.itemName;
			 html += '</div>';
			 html += '<div style="width:100%;background-color: #cccccc;">';
		     html += '<label> </label>';
		     html += '</div>';
		
		}else{
		if(log.itemType=='3'){
		 html += '<div style="width:100%;background-color: yellow;font-weight: bold">'
		 html += '<label>'+"　　　　"+log.itemName+'</label>';
		 html += '</div>';
		 }else{
		 html += '<label>'+"　　"+log.itemName+'</label>';
		 }
		    var projectValue='';
		    var preTotalValue='';
		      for (var j=0,lenj=statementData.length; j<lenj; j++) {
		        var statement=statementData[j];
		        if(statement.projectCd == log.itemCode){
		           projectValue=statement.projectValue;
		           preTotalValue=statement.preTotalValue;
		        }
		      }
		      if(sheetCode=='02'||sheetCode=='03'){//利润表、现金流量表
		      if(financeTypeCd =='1'){//年报
		      	if(projectValue==0){
			         html += '<label>0.00</label>';
			      }else if(projectValue==null || projectValue==''){
			         html += '<label></label>';
			      }else{
			         html += '<label>'+formatMoney(projectValue.toFixed(2))+'</label>';
			      }
			      if(preTotalValue == null ||preTotalValue == ''){
			          if(financeData['e'+log.itemCode]==null){
						          html += '<label></label>';
						        }else{
						          html += '<label>'+formatMoney(financeData['e'+log.itemCode].toFixed(2))+'</label>';
						        }
			        }else{
			          html += '<label>'+formatMoney(preTotalValue.toFixed(2))+'</label>';
			        }
			      	
		      }
		      else{//非年报
				      if(projectValue==0){
				         html += '<label>0.00</label>';
				      }else if(projectValue==null || projectValue==''){
				         html += '<label></label>';
				      }else{
				         html += '<label>'+formatMoney(projectValue.toFixed(2))+'</label>';
				      } 
				      if(preTotalValue == null || preTotalValue==''){
				          html += '<label></label>';
				        }else{
				          html += '<label>'+formatMoney(preTotalValue.toFixed(2))+'</label>';
				        }
			      }
			      
		      }else if(sheetCode=='01'){//资产负债
			      	 if(preTotalValue == null ||preTotalValue == ''){
			      				if(financeData['e'+log.itemCode]==null){
						          html += '<label></label>';
						        }else{
						          html += '<label>'+formatMoney(financeData['e'+log.itemCode].toFixed(2))+'</label>';
						        }
						      
			        }else{
			          html += '<label>'+formatMoney(preTotalValue.toFixed(2))+'</label>';
			        }
			      if(projectValue==0){
			         html += '<label>0.00</label>';
			      }else if(projectValue==null || projectValue==''){
			         html += '<label></label>';
			      }else{
			         html += '<label>'+formatMoney(projectValue.toFixed(2))+'</label>';
			      }
		      
		     }
		  else{//补充资料
		     		if(projectValue==0){
				         html += '<label>0.00</label>';
				      }else if(projectValue==null || projectValue==''){
				         html += '<label></label>';
				      }else{
				         html += '<label>'+formatMoney(projectValue.toFixed(2))+'</label>';
				      }
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

//保存
function save() {
	var cc = logForm.getFields();
	var msg='';
	var o=form.getData();
	var json=nui.encode({financeStatementData:o});
    for(var i=0;i<cc.length;i++){
       var value=cc[i].value;
       var name=cc[i].id;
       if(value==''||value==null){
           if(msg==''){
              msg=msg+name;
           }else{
              msg=msg+','+name;
           }
        }
    }
   if(msg!='' && msg!=null){
      msg=msg+'为空，';
      nui.confirm(msg+'您确定要保存数据嘛？','确认', function(e){
    	if(e=='ok'){
    	   $.ajax({
        url: 'com.bos.acc.accnfdsheet.saveBalanceSheet.biz.ext',
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        	} else {
        		alert('保存成功');
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
	   });
    	}
    });
  }else{
         $.ajax({
        url: 'com.bos.acc.accnfdsheet.saveBalanceSheet.biz.ext',
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        	} else {
        		alert('保存成功');
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
	   });
  }
  
}
</script> </body> </html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <head> <%@include
	file="/common/nui/common.jsp"%> </head> <style>
    td{
		border:1px solid black;	
		text-align:left;
	}
</style> <body> <div id="form1"
	style="width:98%;height:auto;overflow:hidden;text-align:center;margin-top:0px;">
<div id="dw" style="height:auto;overflow:hidden;text-align:right;">
<label>金额单位：元</label> </div> <div id="dwa"
	style="height:auto;overflow:hidden;text-align:left;color:red">
<label id="xs" style="display: none;">百分数请换算成小数</label> </div> <input
	name="financeId" value="<%=request.getParameter("financeId") %>"
	class="nui-hidden" /> <input name="financeStatementId" value=""
	class="nui-hidden" id="financeStatementId" /> <div id="balanceDiv"
	style="width:98%;height:auto;overflow:hidden; text-align:left;border:1px solid black">

</div> <div id="btnSave_div" class="nui-toolbar"
	style="width:98%;height:auto;overflow:hidden; text-align:right">
<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">保存</a>
<!-- <a class="nui-button" iconCls="icon-close" id="btnCloseWindow" onclick="CloseWindow()">关闭</a> -->
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
var preFlag ="";
var viewFlag = '/>';
if(reportType=="019"&&sheetCode=="02"){
	$("#btnSave_div").hide();
}

if(view == '1'){//查看
	nui.get("btnSave").hide();
	viewFlag = 'enabled="false"'+viewFlag;
}

function initForm() {
    git.mask("form1"); 
	var json=nui.encode({"reportType": reportType,"sheetCode":sheetCode,"reportStatus":reportStatus,"financeId":financeId});
	$.ajax({
        url: "com.bos.acc.accnfdsheet.getAccNfdItem.biz.ext",
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
        	    nui.get("financeStatementId").setValue(financeStatementId);
        	    financeData=text.financeData;
        	    financeTypeCd=text.financeTypeCd;
        	    preFlag=text.preFlag;
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
	if(sheetCode=='02'){//利润
		if(reportType == '019'){
			if(financeTypeCd =='1'){//年报
			   html += '<div class="nui-dynpanel" columns="2" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本年加权平均数</label>';
			}else if(financeTypeCd =='4'){//月报
			   html += '<div class="nui-dynpanel" columns="2" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本月加权平均数</label>';
		   }else if(financeTypeCd =='4'){//季报
			   html += '<div class="nui-dynpanel" columns="2" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本季加权平均数</label>';
		   }else{
			   html += '<div class="nui-dynpanel" columns="2" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本期加权平均数</label>';
		   }
		}else{
			if(financeTypeCd =='1'){//年报
			   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本年累计数</label>';
			   html += '<label>上年同期数</label>';
			}else if(financeTypeCd =='4'){//月报
			   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本月累计数</label>';
			   html += '<label>本年累计数 </label>';
		   }else if(financeTypeCd =='4'){//季报
			   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本季累计数</label>';
			   html += '<label>本年累计数 </label>';
		   }else{
			   html += '<div class="nui-dynpanel" columns="3" colWidth="25%,50%,25%">';
			   html += '<label>项目</label>';
			   html += '<label>本期累计数</label>';
			   html += '<label>本年累计数 </label>';
		   }
		}
	}else if(sheetCode=='03'){//现金流量表
		html += '<div class="nui-dynpanel" columns="2" colWidth="25%,50%,25%">';
		html += '<label>项目</label>';
		html += '<label>数值</label>';
	}else if(sheetCode=='05'){
	   html += '<div class="nui-dynpanel" columns="2">';
	}else{
		if(financeTypeCd =='1'){//年报
		  html += '<div class="nui-dynpanel" columns="6" colWidth="14%,22%,14%,14%,22%,14%">';
		  html += '<label>项目</label>';
		  html += '<label>期初数</label>';
		  html += '<label>期末数 </label>';
		  html += '<label>项目</label>';
		  html += '<label>期初数</label>';
		  html += '<label>期末数</label>';
	  }else{
	  	  html += '<div class="nui-dynpanel" columns="6" colWidth="14%,22%,14%,14%,22%,14%">';
		  html += '<label>项目</label>';
		  html += '<label>期初数</label>';
		  html += '<label>期末数 </label>';
		  html += '<label>项目</label>';
		  html += '<label>期初数</label>';
		  html += '<label>期末数</label>';
	  }
	}
	
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		if(log.itemType=='1'){
			html += '<div style="width:100%;background-color: #cccccc;font-weight: bold">';
		    html += '<label >'+log.itemName+'</label>';
		    html += '</div>';
		    html += '<div style="width:100%;background-color: #cccccc;">';
		    html += '<label> </label>';
		    html += '</div>';
			if(sheetCode != '03'){//现金流量表（03）
				html += '<div style="width:100%;background-color: #cccccc;">';
			    html += '<label> </label>';
			    html += '</div>';
			}
		}else if(log.itemType=='4'){
			 html += '<div style="width:100%;background-color: #cccccc;text-align:left;font-weight: bold">'+log.itemName;
			 html += '</div>';
			 html += '<div style="width:100%;background-color: #cccccc;">';
		     html += '<label> </label>';
		     html += '</div>';
		
		}else{
		 	if(log.itemType=='3'){
		 	html += '<div style="width:100%;background-color: yellow;text-align:left;font-weight: bold">'+"　　　　"+log.itemName;
//		 html += '<label>'+log.itemName+'</label>';
		 	html += '</div>';
		 }else{
		 	html += '<div style="width:100%">'
		 	html += '<label>'+"　　"+log.itemName+'</label>';
		 	html += '</div>';
		 }
		    var projectValue='';
		    var preTotalValue='';
		      for (var j=0,lenj=statementData.length; j<lenj; j++) {
		        var statement=statementData[j];
		        if(statement.projectCd == log.itemCode){
		           projectValue=statement.projectValue;//科目值
		           preTotalValue=statement.preTotalValue;//期初值、累计值
		        }
		      }
		   if(sheetCode=='02'){//利润表
		   		html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" enabled="false" required="true" decimalPlaces="2"'+viewFlag;
		    }else if(sheetCode=='03'){//现金流量表
		    	html += '<input  name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
		    }else if(sheetCode=='01'){//资产负债表
		    	if(preFlag=='1'){//上期值取到且未保存过期初值
				      //期初
				      if(financeData['e'+log.itemCode]==0){ 
				         html += '<input name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+financeData['e'+log.itemCode]+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else if(financeData['e'+log.itemCode]==null||financeData['e'+log.itemCode]==''){
				         html += '<input name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else{
				      	 html += '<input name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+financeData['e'+log.itemCode]+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag; 
				      }
				       //本期值
				      if(projectValue==0){ 
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else if(projectValue==null||projectValue==''){
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else{
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }
		    	}else{//上期值未取到或者本期已保存期初值
		    		  //期初
		    		  if(preTotalValue==0){ 
				         html += '<input name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+preTotalValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else if(preTotalValue==null||preTotalValue==''){
				         html += '<input  name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else{
				      	 html += '<input  name="EM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+preTotalValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag; 
				      }
				       //本期值
				      if(projectValue==0){ 
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else if(projectValue==null||projectValue==''){
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }else{
				         html += '<input  name="KM'+log.itemCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" vtype="maxLength:20" dataType="float" required="true" decimalPlaces="2"'+viewFlag;
				      }
		      	}
		    }
		 }
	}
	html += '</div>';
	$('#balanceDiv').html(html);
	nui.parse($('#balanceDiv')[0]);
}

//保存
function save() {
	var cc = logForm.getFields();
	var msg='';
	var o=form.getData();
	o.reportType = reportType;
	o.sheetCode = sheetCode;

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
   git.mask("form1"); 
   if(msg!='' && msg!=null){
      msg=msg+'为空，';
      nui.confirm('有部分科目未填'+'您确定要保存数据吗？','确认', function(e){
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
        		git.unmask("form1");
        	} else {
        		alert('保存成功');
        		git.unmask("form1");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            git.unmask("form1");
        }
	   });
    	}else{
    		git.unmask("form1");
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
        		git.unmask("form1");
        	} else {
        		alert('保存成功');
        		git.unmask("form1");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            git.unmask("form1");
        }
	   });
  }
}
</script> </body> </html>

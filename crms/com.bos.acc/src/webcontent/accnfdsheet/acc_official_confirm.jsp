<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-正式确认-页签
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title>正式确认</title> </head> <body> <div id="form1" align="center">
<input type="hidden" name="financeId"
	value="<%=request.getParameter("financeId") %>" class="nui-hidden" />
<input type="hidden" name="reportType"
	value="<%=request.getParameter("reportType") %>" class="nui-hidden" />
<div style="height:150px;"> </div> <lable>请确认录入信息已核对正确：</lable> <lable></lable>
<a class="nui-button" onclick="officialConfirm();" disableOnClick="true">
确 认 </a> </div> <script type="text/javascript">
var form = new nui.Form("#form1");
var reportType="<%=request.getParameter("reportType") %>";
var financeId="<%=request.getParameter("financeId") %>";

function officialConfirm() {
   git.mask("form1");
    var iframe = this.parent.frameElement;
	var o=form.getData();
	var json=nui.encode(o);
	
	var url = "com.bos.acc.accnfdsheet.officialConfirm.biz.ext";
	if(reportType=="019"){
		url = "com.bos.acc.accnfdsheet.financeDataConfirm.biz.ext";
	}
	$.ajax({
        url: url,
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        		git.unmask("form1");
        	} else {
        		alert("正式确认成功");
               var url = nui.context +"/acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+financeId+"&view=1&reportType="+reportType;
                git.go(url,iframe);
                git.unmask("form1");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            git.unmask("form1");
        }
	});
        
    }
</script> </body> </html>
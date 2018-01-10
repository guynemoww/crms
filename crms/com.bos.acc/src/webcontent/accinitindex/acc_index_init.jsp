<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-指标计算-页签
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title>财务指标初始化</title> </head> <body> <form id="form1"
	style="width:100%;height:auto;overflow:hidden;"> <label>初始化日期选择：</label>
<input name="paramDate" id="paramDate"
	class="nui-datepicker nui-form-input" style="width:200px;" /> <label
	style="color:red;">注意：暂时只支持年报，请按2011.12、2012.12、2013.12顺序初始化</label> <div
	class="nui-toolbar" style="border-bottom:0;text-align:center;"> <a
	class="nui-button" iconCls="icon-save" onclick="initIndex()"
	id="btnSave" disableOnClick="true">财报指标初始化</a> <!-- <a class="nui-button" iconCls="icon-close" id="btnCloseWindow" onclick="CloseWindow()">关闭</a> -->
</div> </form> <script type="text/javascript">
nui.parse();
var form = new nui.Form("#form1");
//计算
function initIndex() {
if(!nui.get("paramDate").value){
			nui.alert("请填写初始化日期，暂时只支持年报");
			return;
		}
    git.mask();
	var json=nui.encode({"paramDate":nui.get("paramDate").value});
	$.ajax({
        url: "com.bos.acc.accnfdsheet.runTask.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.indexMsg){
        		alert(text.indexMsg);
        		git.unmask();
        	}else{
        		alert('初始化成功');
        		git.unmask();
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            git.unmask();
        }
	});
}

</script> </body> </html>
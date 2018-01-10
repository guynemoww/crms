<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-13
  - Description:TB_REPORT_MENU, com.bos.pub.sys.TbReportMenu
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input id="pId" name="tbReportMenu.menuPresent"  class="nui-hidden"  />
	<div class="nui-dynpanel" columns="2">

		<label>报表清单名称：</label>
		<input name="tbReportMenu.menuName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>报表清单路径：</label>
		<input id="path" name="tbReportMenu.menuPath" required="false" class="nui-textArea" vtype="maxLength:500" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var c="<%=request.getParameter("c") %>";
        var pId="<%=request.getParameter("pId") %>";
	    if(c=="2"){
	      nui.get("pId").setValue(pId);
	    }else{
	     nui.get("path").setEnabled(false);
	    }
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.reportList.addTbReportMenu.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
	</script>
</body>
</html>

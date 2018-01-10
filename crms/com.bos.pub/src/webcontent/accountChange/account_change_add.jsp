<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date:2016-05-13
  - Description:
-->
<head>
<title>账务调整</title>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body> 
<div id="form1" style="width:99.5%;height:99.5%;overflow:hidden;">
	<fieldset>
  	<legend>
    	<span>账务调整基本信息</span>
    </legend>
		<div class="nui-dynpanel" id="table1" columns="4" >
			<label>业务编号：</label>
			<input id="item.summaryNum" name="item.summaryNum" required="true"enabled="false"  class="nui-textbox nui-form-input"  />
			
			<label>退还多收利息金额：</label>
			<input id="item.amt" name="item.amt" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0.001,999999999999999999"dataType="currency"/>
				
			<label>调整原因：</label>
			<input id="item.changeReason" name="item.changeReason" required="true"   class="nui-textarea nui-form-input" vtype="maxLength:200"/>
			
			<label>经办日期：</label>
			<input id="item.createTime" name="item.createTime"  enabled="false"required="true" class="nui-textbox nui-form-input"value="<%=GitUtil.getBusiDateStr()%>"  />
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
	    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="add">保存</a>
    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var summaryNum = "<%=request.getParameter("summaryNum") %>";
	if (summaryNum) {
		nui.get("item.summaryNum").setValue(summaryNum); 
	}
	//------------------事件操作区-----------------//
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) {
       	 	return alert("请按规则填写信息");
        }
         git.mask("form1");
        var o = form.getData();
        var json = nui.encode(o);
        nui.confirm("本次退还多收客户利息金额为"+o.item.amt+"元，请再次确认！", "确认", function(action) {
		if (action != "ok"){
		    git.unmask("form1");
			return;
		}
        $.ajax({
            url: "com.bos.pub.accountChange.accountChangeAdd.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
            	if (text.msg) {
            		alert(text.msg);
               		git.unmask("form1");
            		return;
            	}else{
	            	alert("调整成功");
	            	CloseWindow();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
       	 });
        });
	}
   	
</script>
</body>
</html>


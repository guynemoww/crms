<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-17
  - Description:TB_PUNISH_STANDARD_MESSAGE, com.bos.pub.sys.TbPunishStandardMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="2" >
		<label>累计类型：</label>
		<input name="tbPunishStandardMessage.addUpType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" dictTypeId="XD_L23J3" emptyText="请选择"/>

		<label>积分截止数值：</label>
		<input name="tbPunishStandardMessage.integralEndValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />

		<label>积分开始数值：</label>
		<input name="tbPunishStandardMessage.integralStartValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />

		<label>处罚措施：</label>
		<input  colspan="3" name="tbPunishStandardMessage.punishMeasure"  required="true" class="nui-textarea" vtype="maxLength:300" width="500"/>


	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
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
        url: "com.bos.pub.punish.addTbPunishStandardMessage.biz.ext",
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

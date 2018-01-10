<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21
  - Description:TB_PUB_MARKET_INFO, com.bos.pub.sys.TbPubMarketInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.sys.TbPubMarketInfo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>信息标题：</label>
		<input name="item.infoTitle" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>信息适用范围：</label>
		<input name="item.infoRangeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_range" emptyText="请选择"/>

		<label>信息类型：</label>
		<input colspan="3" name="item.infoType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_type" emptyText="请选择" />

		<label>信息内容：</label>
		<input colspan="3" name="item.infoContent" required="true" class="nui-textarea nui-form-input" vtype="maxLength:4000" 
			style="width:400px;height:200px;" />
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
	o.item.orgNum=nui.userOrgId;
	o.item.userNum=nui.userId;
	o.item.infoStatus="0";
	
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.marketinfo.addItem.biz.ext",
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

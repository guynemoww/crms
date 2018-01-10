<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21

  - Description:TB_PUB_MARKET_INFO, com.bos.pub.sys.TbPubMarketInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" /><input type="hidden" name="item._entity" value="com.bos.pub.sys.TbPubMarketInfo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>创建机构：</label>
		<input name="item.orgNum" required="true" class="nui-text nui-form-input" dictTypeId="org" />

		<label>创建人：</label>
		<input name="item.userNum" required="true" class="nui-text nui-form-input" dictTypeId="user" />

		<label>创建时间：</label>
		<input name="item.createTime" required="true" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd HH:mm:ss" />

		<label>状态：</label>
		<input colspan="1" name="item.infoStatus" required="true" class="nui-text nui-form-input" dictTypeId="pub_market_info_status" />
	</div>
	<br/>
	<div class="nui-dynpanel" columns="4">
		<label>信息标题：</label>
		<input colspan="3" name="item.infoTitle" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>信息适用范围：</label>
		<input name="item.infoRangeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_range" />

		<label>信息类型：</label>
		<input name="item.infoType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_type" />

		<label>信息内容：</label>
		<input colspan="3" name="item.infoContent" required="true" class="nui-textarea nui-form-input" vtype="maxLength:4000" 
			style="width:400px;height:200px;" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}

function initForm() {
	var json=nui.encode({"item":{"infoId":"<%=request.getParameter("itemId") %>",
		"_entity":"com.bos.pub.sys.TbPubMarketInfo"}});
	$.ajax({
            url: "com.bos.pub.systechproduct.getItem.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		form.setData(text);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
initForm();

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
            url: "com.bos.pub.systechproduct.saveItem.biz.ext",
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

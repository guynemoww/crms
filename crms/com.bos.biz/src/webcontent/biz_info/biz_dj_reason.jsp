<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>冻结原因</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbBizAttached.attachedId" name="tbBizAttached.attachedId" class="nui-hidden nui-form-input" />
	<div class="nui-dynpanel" columns="4">
		<label>冻结原因：</label>
		<input id="tbBizAttached.attachedDesc" name="tbBizAttached.attachedDesc" class="nui-textarea nui-form-input"
			 required="true"/>
	</div>	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId = "<%=request.getParameter("applyId") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	/* initPage();
	function initPage(){
        var json = nui.encode({"applyId":applyId});
		$.ajax({
            url: "com.bos.bizProductDetail.bizTx.getXwTx.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	selectFs(1);
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if(proFlag=="0"){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
		}
	} */
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
        o.tbBizAttached.applyId=applyId;
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.saveDjyy.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	alert("保存成功!");
	            CloseWindow("ok");
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
</script>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" id="item._entity" value="com.bos.dataset.pub.TbPubImageDocument" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>文档名称：</label>
		<input colspan="3" name="item.imageDocumentName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>文档描述：</label>
		<input colspan="3"  name="item.imageDocumentDesc" required="false" class="nui-textarea nui-form-input" vtype="maxLength:4000" />
	</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	var _entity = nui.get("item._entity").value;
		
	function initForm() {
		var json=nui.encode({"item":{"imageDocumentId":"<%=request.getParameter("itemId") %>","_entity":_entity}});
		$.ajax({
	            url: "com.bos.pub.image.getImageDocument.biz.ext",
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
	        url: "com.bos.pub.image.saveImageDocument.biz.ext",
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

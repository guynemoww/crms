<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-5-21 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1"  style="width:99.5%;height:auto;overflow:hidden; text-align:left">
			<fieldset>
				<legend>
					<span>附加信息</span>
				</legend>
				<input type="hidden" name="item._entity"value="com.bos.dataset.csm.TbCsmAdditiveInfo" class="nui-hidden" />
				<input name="item.partyId" id="item.partyId" value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
				<div class="nui-dynpanel" columns="2">
						<label>标题</label>
						<input id="item.title"required="true" class="nui-textbox nui-form-input"name="item.title" vtype="maxLength:150"/> 
						
						<label>详细信息</label> 
						<input id="item.detailInfo" name="item.detailInfo"class="nui-textarea nui-form-input" 
						style="width:80%;height:150px"required="true"vtype="maxLength:2500"/>
				</div>
			</fieldset>
			<div class="nui-toolbar"
				style="border: 0; text-align: right; ">
				<a class="nui-button" id="btnSave"iconCls="icon-save" onclick="save()">保存</a>
				<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var partyId = "<%=request.getParameter("partyId")%>";
		var id = "<%=request.getParameter("id")%>";
		var qote = "<%=request.getParameter("qote")%>";

		if(qote==0){
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		if(id!='null'){
			initForm();
		}
		function initForm(){
	  		var json=nui.encode({"item":{"id":
            		"<%=request.getParameter("id")%>","_entity":"com.bos.dataset.csm.TbCsmAdditiveInfo"}});
			$.ajax({
				url : "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(mydata) {
					git.unmask("form1");
					var o = nui.decode(mydata);
					form.setData(o);
					oldData = form.getData();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o = form.getData();
			if(id!='null'){
				o.item.id=id;
			}
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.csm.pub.crudCustInfo.saveUpdateItem.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
					} else {
						alert("保存成功!");
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
	</script>

</body>
</html>
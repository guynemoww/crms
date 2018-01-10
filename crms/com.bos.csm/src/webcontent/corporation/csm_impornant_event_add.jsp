<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-25
  - Description:TB_CSM_IMPORNANT_EVENT, com.bos.dataset.csm.TbCsmImpornantEvent
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmImpornantEvent" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>事件类型：</label>
		<input id="item.eventTypeCd" dictTypeId="<%=request.getParameter("dic") %>" name="item.eventTypeCd" required="true" class="nui-buttonEdit nui-form-input" 
			allowInput="false" onbuttonclick="selectEnevt"/>
			
		<label>发生日期：</label>
		<input name="item.happenDate" required="true" maxDate="<%=com.bos.pub.GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>

		<label>事件描述及原因：</label>
		<input name="item.eventState" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000" />
<%--
		<label>登记人：</label>
		<input name="item.handlingUserId" required="false" Enabled="false" dictTypeId="user" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" class="nui-textbox nui-form-input"  />
		
		<label>登记机构：</label>
		<input name="item.handlingOrgId" required="false" Enabled="false" dictTypeId="org" value="<%=com.bos.pub.GitUtil.getCurrentOrgId()%>" class="nui-textbox nui-form-input"  />
		--%>
		<label>登记日期：</label>
		<input name="item.createTime" required="false" Enabled="false" class="nui-datepicker nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>

	</div>
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>				
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	
	var partyId = "<%=request.getParameter("partyId") %>";
	var dic = "<%=request.getParameter("dic") %>";
	
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
	}
	    
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.insertItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	function selectEnevt(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid="+dic,
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
</script>
</body>
</html>

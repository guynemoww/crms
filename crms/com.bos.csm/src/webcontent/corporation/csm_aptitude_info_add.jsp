<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_APTITUDE_INFO, com.bos.dataset.csm.TbCsmAptitudeInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
<%--	<fieldset>
	  <legend>
	    <span>企业资质信息</span>
	   </legend>--%>
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAptitudeInfo" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>认证名称：</label>
		<input name="item.authenticationName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>资质等级：</label>
		<input name="item.aptitudeLevelCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>资质颁发单位名称：</label>
		<input name="item.aptitudeAuthenticationUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>资质颁发日期：</label>
		<input name="item.aptitudeStartDate" required="true" class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" format="yyyy-MM-dd"/>

		<label>资质到期日期：</label>
		<input name="item.aptitudeEndDate" required="true" class="nui-datepicker nui-form-input" minDate="<%=GitUtil.getBusiDateStr()%>" format="yyyy-MM-dd"/>
		
		<label>备注：</label>
		<input name="item.state" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<%--<label>登记人：</label>
		<input name="item.handlingUserId" required="false" Enabled="false" dictTypeId="user" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" class="nui-textbox nui-form-input"  />
		
		<label>登记机构：</label>
		<input name="item.handlingOrgId" required="false" Enabled="false" dictTypeId="org" value="<%=com.bos.pub.GitUtil.getCurrentOrgId()%>" class="nui-textbox nui-form-input"  />
		--%>
		<label>登记日期：</label>
		<input name="item.createTime" required="false" Enabled="false" class="nui-datepicker nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>

	</div>
		<%--</fieldset>--%>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
	    
			
<script type="text/javascript">
 	nui.parse();
 	
    var form = new nui.Form("#form1");
	
	var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
	}
	
	function selectCodeList(e) {
	
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0036",
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
</script>
</body>
</html>

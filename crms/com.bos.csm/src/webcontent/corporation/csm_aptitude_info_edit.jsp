<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22

  - Description:TB_CSM_APTITUDE_INFO, com.bos.dataset.csm.TbCsmAptitudeInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
	<input name="item.aptitudeId" id="item.aptitudeId" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAptitudeInfo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>认证名称：</label>
		<input name="item.authenticationName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		<label>资质等级：</label>
		<input id="aptitudeLevelCd" name="item.aptitudeLevelCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20"/>

		<label>资质颁发单位名称：</label>
		<input name="item.aptitudeAuthenticationUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>资质颁发日期：</label>
		<input name="item.aptitudeStartDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>

		<label>资质到期日期：</label>
		<input name="item.aptitudeEndDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
		
		<label>备注：</label>
		<input name="item.remark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />
	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-left:25px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
</cenetr>				

	    
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	function initForm() {
		var json=nui.encode({"item":{"aptitudeId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.csm.TbCsmAptitudeInfo"}});
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
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
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           	 	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
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

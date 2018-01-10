<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08

  - Description:TB_PUB_GRANT_TABLE_RULE, com.bos.pub.decision.TbPubGrantTableRule-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbPubGrantTableRule" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>规则标识：</label>
		<input name="tbPubGrantTableRule.rind" class="nui-text"/>

		<label>规则顺序优先级：</label>
		<input name="tbPubGrantTableRule.rno" class="nui-textbox nui-form-input" vtype="maxLength:4" />
		
		<label>创建机构：</label>
		<input name="tbPubGrantTableRule.orgNum" class="nui-text" dictTypeId="org" vtype="maxLength:20" />

		<label>创建时间：</label>
		<input name="tbPubGrantTableRule.createTime" class="nui-text" />

		<label>创建人：</label>
		<input name="tbPubGrantTableRule.userNum" required="false" class="nui-text" dictTypeId="user"/>
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
	var json=nui.encode({"tbPubGrantTableRule":
		{"rpid":
		"<%=request.getParameter("rpid") %>"}});
	$.ajax({
        url: "com.bos.pub.decision.getTbPubGrantTableRule.biz.ext",
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
        url: "com.bos.pub.decision.updateTbPubGrantTableRule.biz.ext",
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

function selectRule(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/grant/pack/table_rule/rule_list.jsp",
            showMaxButton: false,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.grid.getSelected();
                    data = nui.clone(data);
                    if (data) {
                    
                        btnEdit.setValue(data.rind);
                        btnEdit.setText(data.rname);
                        // 在此也可做其他操作
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	// 在此也可做其他操作
            	}
        	}
        });            
}
	</script>
</body>
</html>

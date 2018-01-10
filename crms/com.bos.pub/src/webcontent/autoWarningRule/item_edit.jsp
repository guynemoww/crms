<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-23

  - Description:TB_REW_AUTO_WARNING_RULE, com.bos.dataset.irm.TbRewAutoWarningRule-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbRewAutoWarningRule" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>行业代码：</label>
		<input name="tbRewAutoWarningRule.industrialTypeCd" class="nui-buttonEdit nui-form-input" allowInput="false" dictTypeId="XD_KHCD0092" onbuttonclick="selectIndustrial" required="false" vtype="maxLength:32" />

		<label>预警信号代码：</label>
		<input name="tbRewAutoWarningRule.signalCode" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectSignal" vtype="maxLength:10" />
		<label>规则SQL语句：</label>
		<div colspan="3">
		<input  name="tbRewAutoWarningRule.ruleSql" required="false" class="nui-textArea nui-form-input" vtype="maxLength:1000" />
		</div>
		<label>参数1：</label>
		<input name="tbRewAutoWarningRule.parmA" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数2：</label>
		<input name="tbRewAutoWarningRule.parmB" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数3：</label>
		<input name="tbRewAutoWarningRule.parmC" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />
	
		<label>参数4：</label>
		<input name="tbRewAutoWarningRule.parmD" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数5：</label>
		<input name="tbRewAutoWarningRule.parmE" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />
		

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
	var json=nui.encode({"tbRewAutoWarningRule":
		{"autoWarningId":
		"<%=request.getParameter("autoWarningId") %>"}});
	$.ajax({
        url: "com.bos.pub.autoWarningRule.getTbRewAutoWarningRule.biz.ext",
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
        url: "com.bos.pub.autoWarningRule.updateTbRewAutoWarningRule.biz.ext",
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
	function selectSignal(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_KH2D0098",
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
	
	function selectIndustrial(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_KHCD0092",
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

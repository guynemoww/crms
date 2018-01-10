<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16

  - Description:TB_PUB_GRANT_RULE, com.bos.pub.decision.TbPubGrantRule-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" id="pid" name="item.pid" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<!--<label>规则分类：</label>
		<input name="item.tbPubGrantPackage.pname" required="false" class="nui-text nui-form-input" vtype="maxLength:32" />-->

		<label>规则编号：</label>
		<input id="rid" name="item.rid" required="false" class="nui-text nui-form-input" vtype="maxLength:32" />

		<label>规则名称：</label>
		<input name="item.rname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则引用标识：</label>
		<input name="item.rind" required="false" class="nui-text nui-form-input" vtype="maxLength:200" />

		<label>规则类别：</label>
		<input id="rtype" name="item.rtype" required="true" class="nui-text nui-form-input" dictTypeId="pub_grant_rule_type" />

		<label>规则结果类型：</label>
		<input name="item.resulttype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_param" />

		<label>引用规则引用标识：</label>
		<input name="item.rrind" required="false" class="nui-text nui-form-input" vtype="maxLength:200" />

		<label>规则状态：</label>
		<input name="item.rstatus" required="true" class="nui-text nui-form-input" dictTypeId="put_grant_pack_status" />

		<label>版本号：</label>
		<input name="item.rversion" required="false" class="nui-text nui-form-input" vtype="maxLength:8" />

		<!--<label>规则优先级(整数)：</label>
		<input name="item.rlevel" required="false" class="nui-text nui-form-input" vtype="int" />-->

		<label>规则内容：</label>
		<div>
			<input id="rcontent" style="display:none;" 
				name="item.rcontent" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4000" />
			<a href="#" onclick="editRule();return false;">请点击此处</a>
		</div>
	</div>
	<br/>
	<div class="nui-dynpanel" columns="4">
		<label>创建人：</label>
		<input name="item.userNum" required="false" class="nui-text nui-form-input" dictTypeId="user" />

		<label>创建机构：</label>
		<input name="item.orgNum" required="false" class="nui-text nui-form-input" dictTypeId="org" />

		<label>创建时间：</label>
		<input name="item.createTime" required="false" class="nui-text nui-form-input" />

		<label>适用机构：</label>
		<input name="item.rorgNum" required="false" class="nui-text nui-form-input" dictTypeId="org" />

		<label>规则说明：</label>
		<input colspan="3" style="width:500px;" 
			name="item.rnote" required="false" class="nui-textarea nui-form-input" vtype="maxLength:4000" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var view="<%=request.getParameter("view") %>";
		if (view=="1" || view=="const") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}

function initForm() {
	var json=nui.encode({"item":{"rid":"<%=request.getParameter("itemId") %>"}});
	$.ajax({
            url: "com.bos.pub.decision.getRule.biz.ext",
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
            url: "com.bos.pub.decision.saveRule.biz.ext",
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

function editRule() {
	var url="/pub/grant/pack/rule/rule_edit.jsp";
	if (nui.get("rtype").getValue() == "04") {
		//决策表
		url="/pub/grant/pack/rule/tree_edit.jsp";
	}
	url = nui.context+url+"?type=grant&pid="
    	+nui.get("pid").getValue()
    	+"&rid="+nui.get("rid").getValue()
    	+"&rtype="+nui.get("rtype").getValue()
    	+"&view="+view;
	nui.open({
            url: url,
            title: (view == 1 ? "查看" : "编辑"), 
            width: 800, 
        	height: 500,
        	allowResize: false,
        	showMaxButton: false,
        	onload: function () {
	            //var iframe = this.getIFrameEl();
	            this.max();
	        },
            ondestroy: function (action) {
                if(action=="ok"){
                    //initForm();
                }
            }
    });
}
	</script>
</body>
</html>

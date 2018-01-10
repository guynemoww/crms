<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_RULE, com.bos.pub.decision.TbPubGrantRule
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.decision.TbPubGrantRule" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<!-- <label>规则编号：</label>
		<input name="item.rid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>规则分类编号：</label>
		<input name="item.pid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" /> -->

		<label>规则名称：</label>
		<input name="item.rname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则引用标识：</label>
		<input name="item.rind" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则类别：</label>
		<input name="item.rtype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_rule_type" />

		<label>规则结果类型：</label>
		<input name="item.resulttype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_param" />
				<label>适用机构：</label>
		<input name="item.rorgNum" required="true" class="nui-buttonEdit nui-form-input"   onbuttonclick="selectEmpOrg"/>
		<!--<label>规则优先级(整数)：</label>
		<input colspan="3" name="item.rlevel" required="true" class="nui-textbox nui-form-input" vtype="int" />-->

		<!-- <label>规则状态：</label>
		<input name="item.rstatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="put_grant_pack_status" />

		<label>版本号：</label>
		<input name="item.rversion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8" />-->

		<label>规则说明：</label>
		<input colspan="3" style="width:182px;" 
			name="item.rnote" required="false" class="nui-textarea nui-form-input" vtype="maxLength:4000" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }

function save() {
	form.validate();
	if (form.isValid() == false) {
		alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	o.item.pid="<%=request.getParameter("pid") %>";
	o.item.rstatus="1";
	o.item.rversion="1";
	o.item.rcontent='<rules></rules>';
	if (o.item.rtype == '10') {
		alert('用于模型、指标的规则是自动管理的，不能进行新增操作！');
		return;
	}
	
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.decision.addRule.biz.ext",
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

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-14
  - Description:TB_MATTER_BASE_MESSAGE, com.bos.pub.sys.TbMatterBaseMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>test字段：</label>
		<input id="test" name=""  class="nui-dictcombobox nui-form-input" vtype="maxLength:4" dictTypeId="CsmYesOrNo" />
				<input id="" name="" required="true"  class="nui-buttonEdit" onbuttonclick="selectBatchOrg" vtype="maxLength:100" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">提交</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var test;
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	test=nui.get("test").getValue();
	var json=nui.encode({"parameterMap":{"test":9,"parmeterId":"10100101"}});
	//nui.alert(json);return;
	// com.bos.pub.productVerification.baseParameter {"parameterMap":{"test":test,"parmeterId":"10100101"}} //是否循环
	//com.bos.pub.productVerification.controlParameter {"parameterMap":{"test":"0201,02s02","parmeterId":"10100101"}} // 担保方式
	// com.bos.pub.productVerification.rateParameter  {"parameterMap":{"test":9,"parmeterId":"10100101"}} // 利率浮动点数
	//com.bos.pub.productVerification.contractParameter {"parameterMap":{"test":1000,"parmeterId":"10100101"}}//MIN_RETURN_ADVANCE_AMOUNT //最低提前还本金额（元）
	$.ajax({
        url: "com.bos.pub.productVerification.rateParameter.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg.value){
        		alert("字段："+text.msg.key+" 错误消息："+text.msg.value);
        	} else {
        		// CloseWindow("ok");alert(text.msg);
        		alert("可以申请");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
	});
}

function selectBatchOrg(){

		 var btnEdit = this;
		        nui.open({
		            url: nui.context + "/comm/pub/select_batch_org_tree.jsp",
		            showMaxButton: true,
		            title: "选择机构",
		            width: 300,
		            height: 450,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.currentNode;
		                    data = nui.clone(data);
		                    if (data) {
		                       btnEdit.setValue(data.branchCode);
		                       btnEdit.setText(data.branchCnAbbrName)
		                    }
		                } else if (action =="clear") {
		                	btnEdit.setValue("");
		                    btnEdit.setText("");
		                }
		            }
		        });            
}
	</script>
</body>
</html>

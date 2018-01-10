<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-06-13 14:04:16
  - Description:
-->
<head>
<title>外部押品评估信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form2" style="width:100%;height:auto;overflow:hidden;">
		<input name="item.suretyId"  class="nui-hidden"  id="item.suretyId"  value="<%=request.getParameter("suretyId") %>"/>
		<input name="item.sortType"  class="nui-hidden" id="item.sortType"  value="<%=request.getParameter("sortType") %>"/>
		<input name="item._entity" class="nui-hidden" id="item.suretyId"  value="com.bos.dataset.grt.TbGrtOuterAssess"/>
		<div id="panel1" class="nui-dynpanel" columns="4">
			<!-- <label>评估方式：</label>
			<input name="item.assessWay" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" id="assessWay"  dictTypeId="XD_YWDB0129"/> -->
										
			<label>评估机构：</label>
			<input name="item.assessOrg" required="true" class="nui-buttonEdit nui-form-input"  id="assessOrg"  onbuttonclick="selectEmpOrg" allowInput="false"/>
									
			<label>币种：</label>
			<input name="item.currencyCd" id="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="CD000001" enabled="false"/>
									
			<label>评估价值：</label>
			<input name="item.assessValue" id = "item.assessValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>成本价值：</label>
			<input name="item.costValue" required="false" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" /> 
			
			<label>评估日期：</label>
			<input name="item.assessDate" required="true" class="nui-datepicker nui-form-input" allowinput="false" id="assessDate" format="yyyy-MM-dd" maxDate="<%=GitUtil.getBusiDateStr()%>" />
			
			<label>备注：</label>
			<input name="item.remark"  class="nui-textarea nui-form-input" vtype="maxLength:300" id="remark" />
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	</div>
	
	<script type="text/javascript">
	 	nui.parse();
	 	var form = new nui.Form("#form2");
	 	
	 	init();
	 	
	 	function init(){
	 		nui.get("item.currencyCd").setValue("CNY");
	 	}
	 	function save(){
	 		form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtApply.addApplyTbAssess.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		nui.alert(text.msg);
	        		CloseWindow("ok");
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
	 	}
	 	
	 	//选择机构
	function selectEmpOrg(e){
		var btnEdit = this;
		 nui.open({
            url:  nui.context + "/utp/org/employee/select_assess_tree.jsp",
            showMaxButton: false,
            title: "选择评估机构",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
            	if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("assessOrg").setValue(data.PARTY_NAME);
                    	nui.get("assessOrg").setText(data.PARTY_NAME);
                	}
            	}
            }
        });
	}
	</script>
</body>
</html>

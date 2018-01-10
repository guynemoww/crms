<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>保证金比例不低于(%)：</label>
	<input id="productDetail.bzjblbdy" name="productDetail.bzjblbdy" required="true"  class="nui-textbox nui-form-input" vtype="int;range:0,100"/>
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>承兑人全称：</label>
	<input id="conDetail.cdrqc" name="conDetail.cdrqc" required="true" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg" dictTypeId="org" />
	<label>保证金比例(%)：</label>
	<input id="conDetail.bzjbl" name="conDetail.bzjbl" required="true" class="nui-textbox nui-form-input" onblur="bzjblchange" vtype="int;range:0,100"/>%
	<label>保证金金额：</label>
	<input id="conDetail.bzjje" name="conDetail.bzjje" required="true"  class="nui-text nui-form-input"  dataType="currency" vtype="float;maxLength:20"/>
	<label>提供发票等材料期限(月)：</label>
	<input id="conDetail.tgfpdclqx" name="conDetail.tgfpdclqx" required="true" class="nui-textbox nui-form-input" vtype="int" />
	<input id="conDetail.bzjblbdy" name="conDetail.bzjblbdy" class="nui-hidden nui-form-input"  />
</div>
<div id='fk' class="nui-dynpanel"  columns="4" style="width:99.5%;">
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht").css("display","none");
	$("#fk").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht").css("display","block");
	}
	if(stepFlag=='pay'){
		$("#fk").css("display","block");
	}
	//选择机构
// 	 function selectEmpOrg(e) {
// 	    var btnEdit = this;
// 	    nui.open({
// 	        url: nui.context + "/pub/sys/select_org_tree.jsp",
// 	        showMaxButton: true,
// 	        title: "选择机构",
// 	        width: 800,
// 	        height: 500,
// 	        ondestroy: function (action) {   
// 	            if (action == "ok") {
// 	                var iframe = this.getIFrameEl();
// 	                var data = iframe.contentWindow.GetData();
// 	                data = nui.clone(data);
	                
// 	                if (data) {
// 	                	self.orglevel=data.orglevel;
// 	                    btnEdit.setValue(data.orgcode);
// 	                    btnEdit.setText(data.orgname);
// 	                }
// 	            }
// 	        }
// 	    });            
// 	}
	
		 function selectEmpOrg(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    
	                    if (data) {
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });            
	    }
</script>
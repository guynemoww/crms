<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-12 21:03:30
  - Description:
-->
<head>
<title>协议信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbConGuarantOrgInfo.contractId" class="nui-hidden nui-form-input" name ="tbConGuarantOrgInfo.contractId"/>
	<fieldset>
		<legend>
	    	<span>协议信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">担保额度起期：</label>
			<input id="thirdPartyLimit.beginDate" name="thirdPartyLimit.beginDate" required="true"  class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">担保额度止期：</label>	
			<input id="thirdPartyLimit.endDate" class="nui-text nui-form-input"   required="true" name="thirdPartyLimit.endDate"/>	
				
			<label class="nui-form-label">担保放大倍数：</label>
			<input id="tbConGuarantOrgInfo.fdbs" class="nui-text nui-form-input" name="tbConGuarantOrgInfo.fdbs"  required="true"/>
			
			<label class="nui-form-label">保证金比例(%)：</label>
			<input id="tbConGuarantOrgInfo.bzjbl" class="nui-textbox nui-form-input" name="tbConGuarantOrgInfo.bzjbl" vtype="range:0,100" required="true" onblur="validatebl"/>
	    </div>
	</fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//业务合同ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//业务合同ID
	var partyId = '';
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId});
		$.ajax({
            url: "com.bos.conApply.conApply.getThirdTypeLimit.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	nui.get("tbConGuarantOrgInfo.fdbs").setValue(o.thirdPartyLimit.amplifyInd);
            	nui.get("tbConGuarantOrgInfo.fdbs").validate();
            	partyId = o.thirdPartyLimit.partyId;
			}
        });
        if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_contract_info_save").setEnabled(false);
        
		var o = form.getData();
		o.tbConGuarantOrgInfo.statusCd = '03';
		o.tbConGuarantOrgInfo.partyId = partyId;
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conApply.conApply.saveThirdPartyLimit.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
	        nui.get("con_contract_info_save").setEnabled(true);
        }});
	}
	
	function selectTrade4(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300",
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
                        var ditc = data.dictid.substring(0,1);
                        if(ditc == 'K' || ditc == 'k'){
                        	/* nui.get("contractBaseInfo.bankHouseClassType").setRequired(true);
                        	nui.get("contractBaseInfo.bankSupervisorHouClassType").setRequired(true);
                        	if(data.dictid == "K70220"){
                        		nui.get("contractBaseInfo.bankSupervisorHouClassType").setRequired(false);
                        		
                        	} */
                        }else{
                        	/* nui.get("contractBaseInfo.bankHouseClassType").setRequired(false);
                        	nui.get("contractBaseInfo.bankSupervisorHouClassType").setRequired(false); */
                        }
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	//选择机构
	 function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
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
    function validatebl(){
    	var bl = this.value;
    	var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{1,5})?$/;
    	if(bl!='' && bl!=null){
	    	if(!mny.test(bl)){
	    		nui.get("tbConGuarantOrgInfo.bzjbl").setValue('');
	    		nui.alert("保证金比例格式不正确");
	    	}
    	}
    }
</script>
</body>
</html>
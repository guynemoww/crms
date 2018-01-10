<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2013-11-21
  - Description:TB_CSM_OTHER_RELATED_PARTY, com.bos.dataset.csm.TbCsmOtherRelatedParty
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath %>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="rela.partyId" id="rela.partyId" class="nui-hidden"/>
	<input name="rela.relFrom" id="rela.relFrom" class="nui-hidden" value="2"/>
	<input name="rela.otherRelatedPartyId" id="rela.otherRelatedPartyId" class="nui-hidden" />
	<input name="rela.relaPartyId" id="rela.relaPartyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		<label>关联方国别：</label>
		<input id="rela.legalContry" name="rela.legalContry" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dValue="CHN"/>
			
		<label>是否我行客户：</label>
		<input id="rela.isCout" name="rela.isCout" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="" onvaluechanged="isOwnCust" />
		
		<label>关联方名称：</label>
		<input id="rela.relaPartyName"name="rela.relaPartyName" required="true" class="nui-buttonEdit"  allowInput="false"  onbuttonclick="selectCust"  />

		<label>关联关系：</label>
		<input id="rela.relatedCd" name="rela.relatedCd" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList"/>
		
		<label>关联客户类型：</label>
		<input id="rela.custType" name="rela.custType" required="true" class="nui-dictcombobox"  dictTypeId="CDKH0034" onfocus="mustSelect" onvaluechanged="clearControlInfo"/>

		<label>证件类型：</label>
		<input id="rela.certType" name="rela.certType" enabled="true" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZJLX0001"  />
		
		<label>证件号码：</label>
		<input id="rela.certNum" name="rela.certNum" enabled="true" required="true" class="nui-textbox nui-form-input" />

		<label>证件签发日期：</label>
		<input id="rela.certBeginDate" name="rela.certBeginDate" required="true" class="nui-datepicker nui-form-input" />
		
		<label>证件到期日期：</label>
		<input id="rela.certEndDate" name="rela.certEndDate" required="true" class="nui-datepicker nui-form-input" />
		
		<label>长期</label>
		<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />
		
		<!--<label>实际控制人标识：</label>
		<input id="rela.isController" name="rela.isController" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
		-->
		
		<label>成为关系人日期：</label>
		<input id="rela.becomeRelatedTime" name="rela.becomeRelatedTime" required="true" class="nui-datepicker nui-form-input" />
		
		<label>描述说明：</label>
		<input id="rela.remark" name="rela.remark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    
    var partyId = "<%=request.getParameter("partyId") %>";
    var custTyp = "<%=request.getParameter("custType") %>";
	if (partyId) {
		nui.get("rela.partyId").setValue(partyId); 
	}
	var arr = git.getDictDataFilter("CDKH0034",'6');
		nui.get("rela.custType").setData(arr);
	if(custTyp === '6'){
		nui.get("rela.custType").setValue(custTyp);
		nui.get("rela.custType").setEnabled(false);
	}else{
			nui.get("rela.custType").setData(getDictData("CDKH0034","str","1,2,3,4,5"));
		}
	function isOwnCust(){
		var isCout= nui.get("rela.isCout").getValue();
  		if(isCout=="0"){ //不是我行客户，允许“手工输入”
  			nui.get("rela.certType").setEnabled(true);
  			nui.get("rela.certNum").setEnabled(true);
  			nui.get("rela.relaPartyName").setAllowInput(true);
  		}else{
  		  	nui.get("rela.certType").setEnabled(false);
  		  	nui.get("rela.certNum").setEnabled(false);
  		  	nui.get("rela.relaPartyName").setAllowInput(false);
  		}
		nui.get("rela.relaPartyName").setValue("");
		nui.get("rela.relaPartyName").setText("");
		nui.get("rela.certType").setValue("");
		nui.get("rela.certNum").setValue("");
	}
	function mustSelect(e){
		var isCout= nui.get("rela.isCout").getValue();
		!!!isCout&&nui.alert('请先选择是否我行客户！');
		if(e&&e.stopPropagation){
			e.stopPropagation();
		}else{
			window.event.cancelBubble = true;
		}
	}
	/**
	function selectCodeList(e) {
        var btnEdit = this;
        var custType=nui.get("rela.custType").getValue();
        if(custType==""){
        	return alert("请先选择关联客户类型");
        }
		var url = nui.context + "/csm/corporation/csm_other_related_reltree.jsp?sqlName=com.bos.csm.corporation.tbCsmRelatedInfo.otherRelatedReltree";
		if(custType=="6"){//自然人的关联关系
			 url = nui.context + "/csm/corporation/csm_other_related_reltree.jsp?sqlName=com.bos.csm.corporation.tbCsmRelatedInfo.otherRelatedReltreeN";
		}
	        nui.open({
            url:url,
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
	**/
	//对公关系
	function selectCodeList(e) {
		var btnEdit = this;
		var custType = custTyp;
		if(custType=='6'){
				nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GLGX0003",
			title : "选择字典项",
			width : 800,
			height : 450,
			ondestroy : function(action) {
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
		}else{
				nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GLGX0004",
			title : "选择字典项",
			width : 800,
			height : 450,
			ondestroy : function(action) {
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
	}
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		var custType= nui.get("rela.custType").getValue();  
		if((custType=="1"||custType=="2"||custType=="3"||custType=="4"||custType=="5")&&!(nui.get("rela.registrCd").getValue()||nui.get("rela.orgRegisterCd").getValue()||nui.get("rela.orgCreditCode").getValue())){
			nui.alert("登记注册号码、组织机构代码、机构信用代码至少填写一个");
			return;
		}
		
		var json1 = {
			"partyId" : nui.get("rela.partyId").getValue(),
			"relaPartyId" : nui.get("rela.relaPartyId").getValue()
		};
		if(nui.get("rela.partyId").getValue()== nui.get("rela.relaPartyId").getValue()){
			nui.alert("关系人不能是自己");
			return;
		}
		msg = exeRule("CUS_RELATION", "1", json1);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.corporation.TbCsmRelatedParty.saveTbCsmRelatedPartyEcif.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if("AAAAAAA"!=text.map.msg){
	                 	nui.alert(text.map.msgg);
	            	}else{
	            	      	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	          		git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	
	function clearControlInfo(){
		var custType= nui.get("rela.custType").getValue();
		//(custTyp !== '6')&&(custType === '6')&&nui.alert('请选择非自然人客户类型！');
  		if(custType=="2"||custType=="3"||custType=="4"||custType=="5"){//所有客户类型允许“手工输入”
  			nui.get("rela.certType").setEnabled(true);
  			nui.get("rela.certNum").setEnabled(true);
  			nui.get("rela.relaPartyName").setAllowInput(true);
  		}else{
  		  	nui.get("rela.certType").setEnabled(false);
  		  	nui.get("rela.certNum").setEnabled(false);
  		  	nui.get("rela.relaPartyName").setAllowInput(false);
  		}
		nui.get("rela.relaPartyName").setValue("");
		nui.get("rela.relaPartyName").setText("");
		nui.get("rela.certType").setValue("");
		nui.get("rela.certNum").setValue("");
	
	}
	//实际控制人选择	
	function selectCust(e) {
		var isCout= nui.get("rela.isCout").getValue();
		if (null == isCout || '' == isCout) {
			nui.alert("请先选择是否我行客户！");
			return;
		}
  		if(isCout=="0"){//不是我行客户，允许“手工输入”
  			nui.alert("是否我行客户选择了‘否’，请在此手工录入！");
  			return;
  		}
  		
		// 01:法人；02：自然人
		var custType = nui.get("rela.custType").getValue();
		if(null == custType || ''==custType){
			nui.alert("请先选择关联客户类型！");
			return;
		}
		nui.open({
	        url: nui.context + "/csm/pub/queryCorpAndNartual.jsp?stockholderTypeCd=" + custType,
	        showMaxButton: true,
	        title: "选择企业或者自然人",
	        width: 1000,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
	                //此处需要分对公对私
	                	if(data.partyTypeCd=='01'){
	                		nui.get("rela.certType").setValue('202');
	                    	nui.get("rela.certNum").setValue(data.orgRegisterCd);
	                    	nui.get("rela.relaPartyId").setValue(data.partyId);
		                    nui.get("rela.relaPartyName").setText(data.partyName);
		                    nui.get("rela.relaPartyName").setValue(data.partyName);
	                	}else{
	                		nui.get("rela.certType").setValue(data.certType);
	                    	nui.get("rela.certNum").setValue(data.certNum);
	                    	nui.get("rela.relaPartyId").setValue(data.partyId);
		                    nui.get("rela.relaPartyName").setText(data.partyName);
		                    nui.get("rela.relaPartyName").setValue(data.partyName);
	                	}
	                	if(nui.get("rela.partyId").getValue()== nui.get("rela.relaPartyId").getValue()){
							nui.alert("关系人不能是自己");
							return;
						}
                    	
	                }
	            }
	        }
	    }); 
    }
    	//设置证件到期日长期
	function setDate(e){
		if(e.checked==true){
		nui.get("rela.certEndDate").setValue("9999-12-31");
		nui.get("rela.certEndDate").setEnabled(false);
		}else{
		nui.get("rela.certEndDate").setEnabled(true);
		nui.get("rela.certEndDate").setValue("");
		}
	}
    
</script>
</body>
</html>

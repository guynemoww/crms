<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-05-05
  - Description:TB_CSM_OTHER_RELATED_PARTY, com.bos.dataset.csm.TbCsmOtherRelatedParty-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="rela.partyId" id="rela.partyId" class="nui-hidden" />
	<input name="rela.otherRelatedPartyId" id="rela.otherRelatedPartyId" class="nui-hidden" />
	<input name="rela.relaPartyId" id="rela.relaPartyId" class="nui-hidden" />
	<input name="rela.relCustNo" id="rela.relCustNo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		<label>关联方国别：</label>
		<input id="rela.legalContry" name="rela.legalContry" required="true" enabled="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dValue="CHN"/>
			
		<label>是否我行客户：</label>
		<input id="rela.iscout" name="rela.iscout" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="" onvaluechanged="isOwnCust" />
		
		<label>关联客户类型：</label>
		<input id="rela.custType"name="rela.custType" enabled="false"  required="true" class="nui-dictcombobox"  dictTypeId="CDKH0034" onvaluechanged="clearControlInfo"/>
		
		<label>关联方名称：</label>
		<input id="rela.relaPartyName"name="rela.relaPartyName" enabled="false"  required="true" class="nui-buttonEdit"  allowInput="false"  onbuttonclick="selectCust"  />

		<label>关联关系：</label>
		<input id="rela.relatedCd" name="rela.relatedCd" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList"  />
		
		<label>证件类型：</label>
		<input id="rela.certType" name="rela.certType" enabled="false" class="nui-dictcombobox nui-form-input"   dictTypeId="XD_ZJLX0002" />
		
		<label>证件签发日期：</label>
		<input id="rela.certBeginDate" name="rela.certBeginDate" required="true" class="nui-datepicker nui-form-input" />
		
		<label>证件号码：</label>
		<input id="rela.certNum" name="rela.certNum" enabled="false"="true" class="nui-textbox nui-form-input" />

		<label>证件到期日期：</label>
		<input id="rela.certEndDate" name="rela.certEndDate" required="true" class="nui-datepicker nui-form-input" />

		<label>长期</label>
		<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />

		<label>成为关系人日期：</label>
		<input id="rela.becomeRelatedTime" name="rela.becomeRelatedTime"required="true" class="nui-datepicker nui-form-input" /> 

		<label>描述说明：</label>
		<input id="rela.remark" name="rela.remark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>	    
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");

	var itemId = "<%=request.getParameter("itemId") %>";
	var custType = "<%=request.getParameter("custType") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		if(qote==0){
		form.setEnabled(false);//不能修改
		nui.get("btnSave").hide();
		}
	function initForm() {
		var json=nui.encode({"itemId":itemId,"custType":custType});
		$.ajax({
	            url: "com.bos.csm.corporation.TbCsmRelatedParty.getTbCsmRelatedParty.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
                	form.setData(text);
                		debugger;
                	if(custType!=6){nui.get("rela.relatedCd").setText(nui.getDictText("XD_GLGX0004",text.rela.relatedCd));}else{
                	nui.get("rela.relatedCd").setText(nui.getDictText("XD_GLGX0003",text.rela.relatedCd));}
            		nui.get("rela.relaPartyName").setText(text.rela.relaPartyName);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
/* 	function selectCodeList(e) {
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
            url: url,
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
	} */
	
	
	function isOwnCust(){
		var iscout= nui.get("rela.iscout").getValue();
  		
  		if(iscout=="0"){ //不是我行客户，允许“手工输入”
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
			//对公关系
	function selectCodeList(e) {
		var btnEdit = this;

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
		
// 		var json1 = {
// 			"relaPartyId" : nui.get("rela.relaPartyId").getValue(),
// 			"partyId" : nui.get("rela.partyId").getValue()
// 		};
// 		msg = exeRule("CUS_RELATION", "1", json1);
// 		if (null != msg && '' != msg) {
// 			nui.alert(msg);
// 			return;
// 		}
		
		
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
	            	         if(text.map.msg!="AAAAAAA"){
                            nui.alert(text.map.msgg);
                          }
                 	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
                
	    
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.mask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	function clearControlInfo(){
	
	var custType= nui.get("rela.custType").getValue();
  		if(custType=="2"||custType=="3"||custType=="4"||custType=="5"){//如果选择其他，股东名称允许“手工输入”
  			nui.get("rela.certType").setEnabled(true);
  			nui.get("rela.certNum").setEnabled(true);
  			nui.get("rela.relaPartyName").setAllowInput(true);
  		}else{
  		  	nui.get("rela.certType").setEnabled(false);
  		  	nui.get("rela.certNum").setEnabled(false);
  		  	nui.get("rela.relaPartyName").setAllowInput(false);
  		}
		//nui.get("rela.relaPartyName").setValue("");
		//nui.get("rela.relaPartyName").setText("");
		//nui.get("rela.certType").setValue("");
		//nui.get("rela.certNum").setValue(""); 
	
	}
	//关联人选择	
	function selectCust(e) {
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
                    	nui.get("rela.certType").setValue(data.certType);
                    	nui.get("rela.certNum").setValue(data.certNum);
                    /* 	nui.get("rela.orgRegisterCd").setValue(data.orgRegisterCd); */
                    	nui.get("rela.relaPartyId").setValue(data.partyId);
	                    nui.get("rela.relaPartyName").setText(data.partyName);
	                    nui.get("rela.relaPartyName").setValue(data.partyName);
	                }
	            }
	        }
	    }); 
    }
</script>
</body>
</html>

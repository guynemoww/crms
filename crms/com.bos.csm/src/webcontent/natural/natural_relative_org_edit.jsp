<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-6 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<fieldset>
	  <legend>
	    <span>自然人关联关系</span>
	   </legend>
<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>
<input name="item.relativeidPartyId" id="item.relativeidPartyId" class="nui-hidden" />
<input name="item.custType" id="item.custType" class="nui-hidden nui-form-input" />
		<div class="nui-dynpanel" columns="4">
			<label>关联方国别：</label>
			<input id="item.legalContry" name="item.legalContry" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dValue="CHN"/>
			
			<label>是否我行客户：</label>
			<input id="item.iscout" name="item.iscout" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"  />	
		
			<label>关系人名称：</label>
			<input id="item.partyName" required="true" name="item.partyName"  class="nui-buttonEdit" onbuttonclick="selectCust" />
		
			<label>客户类型：</label>
			<input id="item.custType" required="true" name="item.custType" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD1001"  />
			
			<label>证件类型：</label>
			<input id="item.certificateTypeCd" required="true"  Enabled="false" name="item.certificateTypeCd" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_ZJLX0002" />
			
			<label>证件号码：</label>
			<input id="item.certId" required="true" name="item.certId" class="nui-textbox nui-form-input" allowInput="false" />
			
			<label>证件签发日期：</label>
			<input id="item.certBeginDate" name="item.certBeginDate" required="true" class="nui-datepicker nui-form-input" />
			
			<label>关联关系：</label>
			<input id="item.appellation" required="true" class="nui-buttonEdit nui-form-input" name="item.appellation"  onbuttonclick="selectCodeList" allowInput="false"/>
			
			<label>证件到期日期：</label>
			<input id="item.certEndDate" name="item.certEndDate" required="true" class="nui-datepicker nui-form-input" />
		
			<label>长期</label>
			<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />
			
			<label>工作单位：</label>
			<input id="item.partnercompany" class="nui-textbox nui-form-input" name="item.partnercompany"  required="true"  vtype="String;maxLength:100;"/>
			
			<label>联系电话：</label>
			<input id="item.partnerphonenum" class="nui-textbox nui-form-input" name="item.partnerphonenum"  required="true"  vtype="int;maxLength:11;minLength:11"/>
			
			<label>备注：</label>
			<input id="item.remark" name="item.remark"  class="nui-textbox nui-form-input"  />
		</div>
		 </fieldset>
		<div class="nui-toolbar" style="border:0;text-align:right;padding-right:">
			<a class="nui-button" iconCls="icon-save" id="saveOK" onclick="save()">保存</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>	
	</div>  

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	
	var naturalRelativeId = "<%=request.getParameter("naturalRelativeId") %>";
	var custType = "<%=request.getParameter("partyTypeCd") %>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		if(qote==0){
		form.setEnabled(false);//不能修改
		nui.get("saveOK").hide();
		}
		nui.get("item.certId").setEnabled(false);
		
		function initForm(){
			//企业
			var arr = git.getDictDataFilter("XD_KHCD1001",'01');
			nui.get("item.custType").setData(arr);
			
	  		var json=nui.encode({"item":{"naturalRelativeId":naturalRelativeId}});
			$.ajax({
				url : "com.bos.csm.pub.crudCustInfo.getNaturalPsnRelative.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(mydata) {
					git.unmask("form1");
					var o = nui.decode(mydata);
					form.setData(o); 
					nui.get("item.partyName").setValue(o.item.partyName);
					nui.get("item.partyName").setText(o.item.partyName);
					nui.get("item.appellation").setText(nui.getDictText("XD_GLGX0002",o.item.appellation));
					//初始化证件到期日
	               	if(o.item.certEndDate=="9999-12-31"){
	             	nui.get("longTime").setChecked(true);
	             	nui.get("item.certEndDate").setEnabled(false);
	               	}else{
	               	nui.get("longTime").setChecked(false);
	               	nui.get("item.certEndDate").setEnabled(true);
	               	}
					oldData = form.getData();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
		
		if(naturalRelativeId!='null'){
			initForm();
		}
		
		function save() {
           form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			
			
			//var appellation =nui.get("item.appellation").getValue();
			//var partyId=nui.get("item.partyId").getValue();
// 			if(appellation=="30101"){//如果关联关系选择配偶，校验唯一性
// 				var json1 = {"partyId":partyId};
// 				msg = exeRule("RCSM_PO", "1", json1);
// 				if (null != msg && '' != msg) {
// 					nui.alert(msg);
// 					return;
// 				}
// 			}
			git.mask("form1");
			var o = form.getData();
			var json=nui.encode(o)+nui.encode({"item":{"naturalRelativeId":naturalRelativeId,
					"_entity":"com.bos.dataset.csm.TbCsmNaturalRelative","partyId":partyId,"custType":custType}});
			$.ajax({
		            url: "com.bos.csm.pub.crudCustInfo.addNaturalPersonRelative.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		            	if(text.msg){
		            		alert(text.msg);
		            	} else {
		            		alert("保存成功!");
		            		CloseWindow("ok");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		             	git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		            }
			});
		}

  	//对私关系个人
	function selectCodeList(e) {
		var btnEdit = this;

		if(custType=="02"){
				nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GLGX0001",
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
		//对私关系企业
			nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GLGX0002",
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
	
	 function selectCust(e) {
     	var isCout= nui.get("item.iscout").getValue();
     	
     	if (null == isCout || '' == isCout) {
			nui.alert("请先选择是否我行客户！");
			return;
		}
		
     	if(isCout=="0"){//不是我行客户，允许“手工输入”
  			nui.alert("是否我行客户选择了‘否’，请在此手工录入！");
  			return;
  		}
		nui.open({
	        url: nui.context + "/csm/pub/queryCorpAndNartualEcif.jsp?remark="+custType,
	        showMaxButton: true,
	        title: "选择",
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
	                		nui.get("item.certificateTypeCd").setValue('202');
	                    	nui.get("item.certId").setValue(data.orgRegisterCd);
	                    	nui.get("item.relativeidPartyId").setValue(data.partyId);
		                    nui.get("item.partyName").setText(data.partyName);
		                    nui.get("item.partyName").setValue(data.partyName);
		                    nui.get("item.custType").setValue(data.partyTypeCd);
	                	}else{
	                		nui.get("item.certificateTypeCd").setValue(data.certType);
	                    	nui.get("item.certId").setValue(data.certNum);
	                    	nui.get("item.relativeidPartyId").setValue(data.partyId);
		                    nui.get("item.partyName").setText(data.partyName);
		                    nui.get("item.partyName").setValue(data.partyName);
		                    nui.get("item.custType").setValue(data.partyTypeCd);
	                	}
	                }
	            }
	        }
	    }); 
    }
	
	//设置证件到期日长期
	function setDate(e){
		if(e.checked==true){
		nui.get("item.certEndDate").setValue("9999-12-31");
		nui.get("item.certEndDate").setEnabled(false);
		}else{
		nui.get("item.certEndDate").setEnabled(true);
		nui.get("item.certEndDate").setValue("");
		}
	}
</script>

</body>
</html>
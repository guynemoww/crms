<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
-->
<head>
<title>合作中介客户新增</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"src="<%=contextPath%>/csm/js/csmValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>
<body>
   <div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
			<fieldset>
				<legend>
					<span>合作中介客户信息</span>
				</legend>
				<div class="nui-dynpanel" id="table1" columns="4">
				
					<label>合作中介类型：</label>
					<input id="thirdParty.corpCustomerTypeCd"name="thirdParty.corpCustomerTypeCd" dictTypeId="XD_KHCD7002"
						class="nui-dictcombobox nui-form-input" required="true"/> 
					
					<label>客户名称：</label> 
					<input id="party.partyName" name="party.partyName" required="true" onvalidation="checkpartyName"
						class="nui-textbox nui-form-input" />
					
					<label>统一社会信用代码：</label>
					<input id="thirdParty.unifySocietyCreditNum"name="thirdParty.unifySocietyCreditNum"  onblur="dataCheck" required="false" onvalidation="checkUnifySocietyCreditNum" 
						vtype="minLength:18;maxLength:18"class="nui-textbox nui-form-input"/>
					
					<label id="hide1">营业执照：</label>
					<input id="thirdParty.registrCd" name="thirdParty.registrCd"  vtype="minLength:13;maxLength:25" required="true" onvalidation="checkBusinessLicenseNumUnique"  class="nui-textbox nui-form-input" /> 
				
					<label id="hide2">组织机构代码：</label> 
					<input id="thirdParty.orgRegisterCd"name="thirdParty.orgRegisterCd"  required="true" onvalidation="checkOrgRegisterCdUnique" 
						class="nui-textbox nui-form-input" />
					
					<label id="partyName">中征码：</label>
					<input id="thirdParty.middelCode" name="thirdParty.middelCode"
						required="false" onvalidation="checkMiddelCodeUnique" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16" />
				</div>
			</fieldset>
			<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0">
   					<a class="nui-button" iconCls="icon-save" onclick="add()">保存</a>
					<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		   </div>
		</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
		function add() {
			//校验
			form.validate();
			if (form.isValid() == false){
				alert("请按规则填写数据");
				return;
			}
						   	    debugger;
			git.mask("form1");
			var o = form.getData();
			   	    var partyName=o.party.partyName;
   	         var json = nui.encode({"thirdParty":o.thirdParty,"partyName":partyName});
		        $.ajax({
            url: "com.bos.csm.corporation.corporation.GetThirdEcifCustNo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (text) {
               if("AAAAAAA"!=text.map.msg){
		             	nui.alert("调用ECIF接口失败:"+text.map.msgg);
		               git.unmask("form1");
		             
		             	   return ;
            	} else {
            	  ecifPartyNum=text.map.ecifPartyNum;
            	  if(ecifPartyNum!=""&&ecifPartyNum!=null){
            	   var json1 = nui.encode({"thirdParty":o.thirdParty,"ecifPartyNum":ecifPartyNum,"partyName":partyName});
   					$.ajax({
						url : "com.bos.csm.thirdParty.thirdParty.addThirdParty.biz.ext",
						type : 'POST',
						data : json1,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							git.unmask("form1");
							if (text.msg) {
								return alert(text.msg);
							} 
							var url = nui.context+ "/csm/thirdParty/csm_thirdParty_tree.jsp?partyId="
										+ text.response.partyId + "&partyNum="
										+ text.response.partyNum + "&qote=2";
								nui.open({
											url : url,
											showMaxButton : true,
											title : "编辑客户信息",
											state : "max",
											onload : function(e) {
												var iframe = this.getIFrameEl();
												var text = iframe.contentWindow.document.body.innerText;
											},
											ondestroy : function(action) {
												CloseWindow('ok')
												this.getParentBox().search();
											}
										});
						},
						error : function(jqXHR, textStatus, errorThrown) {
							git.unmask("form1");
							nui.alert(jqXHR.responseText);
						}
					});
        }else{
		       		             	nui.alert("调用ECIF接口失败:"+text.map.msgg);
		       		             	git.unmask("form1");
		       		             	 return ;
		        }
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
			
		}
		function selectType(e) {
			if (e.value == "1") {
				nui.get("thirdParty.corpCustomerTypeCd").setData(
						nui.getDictData('XD_KHCD7001'));
			} else {
				nui.get("thirdParty.corpCustomerTypeCd").setData(
						nui.getDictData('XD_KHCD7002'));
			}
		}
		
		//---------------合法校验区--------------------//
		function dataCheck(){
			var unifySocietyCreditNum = nui.get("thirdParty.unifySocietyCreditNum").getValue();//统一社会信用代码
			if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
				nui.get("thirdParty.registrCd").setRequired(false);
				nui.get("thirdParty.orgRegisterCd").setRequired(false);
			}else{
				nui.get("thirdParty.registrCd").setRequired(true);
				nui.get("thirdParty.orgRegisterCd").setRequired(true);
			}
		}
		//校验营业执照号码是否唯一
		function checkBusinessLicenseNumUnique(e) {

			var registerCode = nui.get("thirdParty.registrCd").getValue();
			if (null != registerCode && '' != registerCode) {
				if (e.isValid) {
					var json = {
						"certno" : registerCode,"legorg":legorg
					};
					msg = exeRule("RCSM_0001", "1", json);
					if (null != msg && '' != msg) {
						e.errorText = msg;
						e.isValid = false;
					}
				}
			}
		}
		       	//校验公司客户名称是否唯一 checkpartyName
	function checkpartyName(e){
		var partyName =e.value;
	 	if(null != partyName && '' != partyName){
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	   	      	var json = {"partyName":partyName,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0012","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	 }
	 }
}
		
		//校验组织机构号码是否唯一
		function checkOrgRegisterCdUnique(e) {
			var registerCode = e.value;
			if (null != registerCode && '' != registerCode) {
				if (registerCode.length != 10) {
					//alert("组织机构代码总长度不符");
					//return;
					e.errorText = "组织机构代码总长度不符";
					e.isValid = false;
				}

				if (registerCode.indexOf("-") == -1) {
					//alert("组织机构代码格式校验不合法");
					//return;
					e.errorText = "组织机构代码格式校验不合法";
					e.isValid = false;
				}
			
				//校验组织机构代码有效
				isValidCompID(e);
				//如果有效，接着校验是否唯一
				if (e.isValid) {
					var json = {
						"certno" : registerCode,"legorg":legorg
					};
					msg = exeRule("RCSM_0011", "1", json);
					if (null != msg && '' != msg) {
						e.errorText = msg;
						e.isValid = false;
					}
				}
			}
		}
		
		//校验中征码是否唯一
		function checkMiddelCodeUnique(e) {
			var registerCode = e.value;
			if (null != registerCode && '' != registerCode) {
				if (e.isValid) {
					var json = {
						"certno" : registerCode,"legorg":legorg
					};
					msg = exeRule("RCSM_0111", "1", json);
					if (null != msg && '' != msg) {
						e.errorText = msg;
						e.isValid = false;
					}
				}
			}
		}
		
		//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e){
		var unifySocietyCreditNum = e.value;
	 	if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	   	      	var json = {"certno":unifySocietyCreditNum,"legorg":legorg};
	   	      	msg = exeRule("RCSM_unifySocietyCreditNum","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	 	}
	 	}
	}
	</script>
</body>
</html>


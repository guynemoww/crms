<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-05-21 11:01:34
  - Description:
-->
<head>
<title>家庭财务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<center>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<input id="natural.partyId" class="nui-hidden nui-form-input" name="natural.partyId" value="<%=request.getParameter("partyId")%>" />
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">客户名称：</label> 
					<input id="party.partyName"
						class="nui-textbox nui-form-input" name="party.partyName"
						required="true" readonly="true"
						Enabled="false"/> 
					<label class="nui-form-label">证件类型：</label> 
					<input id="natural.certType" name="natural.certType" valueField="dictID"
						textField="dictName" class="nui-dictcombobox nui-form-input"
						dictTypeId="CDKH0002" required="true" readonly="true"
						Enabled="false"/> 
					<label class="nui-form-label">证件号码：</label> 
					<input id="natural.certNum"
						class="nui-textbox nui-form-input" name="natural.certNum"
						required="true" readonly="true"
						Enabled="false" /> 
					<label class="nui-form-label">是否信贷客户：</label> 
					<input id="party.isPotentialCust" name="party.isPotentialCust"
						valueField="dictID" textField="dictName"
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"
						required="true" readonly="true"
						Enabled="false" />
				</div>
			</fieldset>
			<fieldset>
				<legend>
					<span>家庭财务信息</span>
				</legend>
				<input id="family.partyId" class="nui-hidden nui-form-input" name="family.partyId" value="<%=request.getParameter("partyId")%>"/>
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">日期：</label>
					<input id="family.createDate" class="nui-datepicker nui-form-input" name="family.createDate"
						required="true" format="yyyy-MM-dd" onvalidation="onBirthdayvalidate"/>
					<label class="nui-form-label">个人月收入：</label>
					<input id="family.personMonthlyIncome" name="family.personMonthlyIncome"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label id="nui-form-label">家庭稳定收入（月）：</label>
					<input id="family.familySteadyIncome" name="family.familySteadyIncome" 
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency" required="true" />
					<label class="nui-form-label">家庭租金收入（月）：</label>
					<input id="family.familyRentIncome" name="family.familyRentIncome"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">家庭投资分红（月）：</label> 
					<input id="family.familyInvestmentShare" name="family.familyInvestmentShare"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">家庭其它收入（月）：</label> 
					<input id="family.familyOtherIncome" name="family.familyOtherIncome"
						class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">金融资产：</label> 
					<input id="family.financialAssets" name="family.financialAssets"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true"/>
					<label class="nui-form-label">固定资产：</label> 
					<input id="family.fixedAssets" name="family.fixedAssets"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />	
					<label class="nui-form-label">其它资产及投资：</label> 
					<input id="family.otherAsset" name="family.otherAsset"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true"/>	
					<label class="nui-form-label">负债及或有负债：</label>
					<input id="family.incurDebts"
						class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"
						name="family.incurDebts" required="true"/>
					<label class="nui-form-label">每月其它债务支出：</label>
					<input id="family.monthDebtPay" name="family.monthDebtPay"
						class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20" dataType="currency"/>  
					<label class="nui-form-label">备注：</label>
					<input id="family.remark" name="family.remark"
						class="nui-textbox nui-form-input" required="false"/>
 				</div>
			</fieldset>
			<fieldset>
				<legend>
					<span>系统信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">经办机构：</label> <input id="item.orgNum"
						class="nui-text nui-form-input" name="item.orgNum"
						dictTypeId="org" /> <label class="nui-form-label">经办人：</label> <input
						id="item.userNum" class="nui-text nui-form-input"
						name="item.userNum" dictTypeId="user" /> <label
						class="nui-form-label">经办日期：</label> <input
						id="natural.createTime" class="nui-text nui-form-input"
						name="natural.createTime" dateFormat="yyyy-MM-dd hh:mm:ss" />
				</div>
			</fieldset>
			<div style="text-align: right; padding-right: 20px; border: 0">
				<a id="btnSave" class="nui-button" iconCls="icon-save"
					onclick="update">保存</a>
			</div>
		</div>

		<script type="text/javascript">
    nui.parse();
    git.mask("form1");
    var form = new nui.Form("#form1");
    var qote = "<%=request.getParameter("qote")%>" ;
	if(qote==1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
	  var json = nui.encode({"natural":{"partyId":"<%=request.getParameter("partyId")%>"}});
	  $.ajax({
			  url: "com.bos.csm.natural.natural.getNaturalInfo.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	          		 git.unmask("form1");
	                 var o = nui.decode(mydata);
	                 
	                 form.setData(o);
	            }, 
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }     
	  });
	 }
	initForm();
	function update(){
			form.validate();
	        if (form.isValid()==false)
	        return;      
	        git.mask("form1"); 
	       var o = form.getData();
	        var json = nui.encode(o);
	        $.ajax({
	            url: "com.bos.csm.natural.natural.updateNaturalInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
	            		alert("保存成功！");           			
	            		
	            	}
	            	
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
	        });
	        
		}	
		
	function onIDCardsValidation(e) {
	         var certificateTypeCd = nui.get("natural.natural.certificateTypeCdTWO").getValue();
	         var el = document.getElementById("cNCheckText");
	           if(certificateTypeCd == "10100"||certificateTypeCd == "10200"){	            
	                var pattern = /\d*/;
	                if(e.value.length < 15 || e.value.length > 18 || pattern.test(e.value) == false) {	                                       
			             el.innerHTML = "必须输入15~18位数字!";
	                     e.isValid = false;
	                }else{
		             el.innerHTML = "";
	                 } 
		        }else{
		             el.innerHTML = "";
	                 } 
	           
	        }
	        
	function onBirthdayvalidate(e){
		 if (e.isValid){
		 	if(null != e.value && "" != e.value){
		 		var curDate="<%=GitUtil.getBusiDateStr()%> 00:00:00";
		 		if(e.value > curDate){
		 			e.errorText = "出生日期不能大于当前日期<%=GitUtil.getBusiDateStr()%>";
	                e.isValid = false;
		 		}
		 	}
		 }
	}

	function selectMarriageCd(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0043",
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
	
	//校验贷款卡号
	function checkLoanCardNumUnique(e){
	        var flag = "true";
	 		var loanCardNum = nui.get("natural.loanCardCd").getValue();
	 		if(loanCardNum){
	 		if(checkDKK(loanCardNum)=="false"){
	 				e.isValid=false;
		 			//e.errorText="贷款卡号不符合规则";
	 				return;
	 		}
	 	//验证贷款卡号唯一性
   	      if(loanCardNum){
   	      	var json = {"loanCardNum":loanCardNum};
   	      	$.ajax({
   	      	  	url:"com.bos.csm.pub.pubMethod.checkLoanCardNum.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	if(text.errMsg){
   	          			alert("贷款卡号已经存在");
   	          			flag = "false" ;
   	          			//return flag;
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		nui.alert(jqXHR.responseText);
   	          }
   	     });
   	    }
	 	}
	 		
   	    return flag; 
   	  }
   	   //贷款卡的校验规则	  
		function checkDKK(loanCardNum) {
		   
		   var financecode = loanCardNum.trim();
		 
		   if(financecode == ""){
		 		alert("贷款卡号不能为空!");
	 			return "false";
		   }
		 
		    var code = financecode;
		    if(code.length!=16){
		 		if(code.length!=18){
	 				return "false";
		 		}
	 		}
		    
		    if (code.match(/[A-Z0-9]{16}/) == null) {
		       alert("贷款卡号不符合规则");
		        return "false";
		    }
		 
		    var w_i = new Array(14);
		    var c_i = new Array(14);
		    var j, s = 0;
		    var checkid = 0;
		    var c, i;
		 
		    w_i[0] = 1;
		    w_i[1] = 3;
		    w_i[2] = 5;
		    w_i[3] = 7;
		    w_i[4] = 11;
		    w_i[5] = 2;
		    w_i[6] = 13;
		    w_i[7] = 1;
		    w_i[8] = 1;
		    w_i[9] = 17;
		    w_i[10] = 19;
		    w_i[11] = 97;
		    w_i[12] = 23;
		    w_i[13] = 29;
		 
		    for (j = 0; j < 14; j++) {
		     if (financecode.charAt(j) >= '0' && financecode.charAt(j) <= '9') {
		           c_i[j] = financecode.charCodeAt(j) - '0'.charCodeAt(0);
		        }
		        else if (financecode.charAt(j) >= 'A' && financecode.charAt(j) <= 'Z') {
		        c_i[j] = financecode.charCodeAt(j) - 'A'.charCodeAt(0) + 10;
		        }
		        else{
		            alert("贷款卡号不符合规则");
		            return "false";
		        }
		       s = s + w_i[j] * c_i[j];
		    }
		 
		    c = 1 + (s % 97);
		    checkid = (financecode.charCodeAt(14) - '0'.charCodeAt(0))*10 + financecode.charCodeAt(15) - '0'.charCodeAt(0);
		 
		    if (c != checkid) {
		     alert("贷款卡号不符合规则");
		        return "false";
		    }
		    return "true";
		}
</script>
</body>
</html>
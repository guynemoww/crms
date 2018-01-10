<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<!-- 
  - Author(s): cc
  - Date: 2016-5-9 14:38:54
  - Description:同业客户的新建向导
-->
<head>
<title>同业客户新建</title>

</head>
<body>
   <div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
   		<fieldset>
		  <legend>
		    <span>同业客户新建</span>
		   </legend>
   		<div class="nui-dynpanel" columns="4">
   			<label>区域类型：</label>
   	    	<input id="financial.areaType" name="financial.areaType" class="nui-dictcombobox" 
   	    	 dictTypeId="XD_KHCD0210" required="true" onvaluechanged="checkAreaType()">
			
			<label>国家或地区：</label>
			<input id="financial.countryBasic" name="financial.countryBasic" required="true" class="nui-dictcombobox nui-form-input" 
				dictTypeId="CD000003"   dValue="CHN"/>
			
   	    	<label>客户名称：</label>
   	    	<input id="financial.partyName" name="financial.partyName" onvalidation="checkpartyName" required="true"  class="nui-textbox" vtype="maxLength:100">
   	    	
			<label>金融机构类型：</label>
		 	<input id="financial.financeEnterpriseType" name="financial.financeEnterpriseType"  class="nui-dictcombobox nui-form-input" 
		 			required="true" allowInput="false"  dictTypeId="ECIF_JRJGLX01"  />
		 			
   	    	<label>金融许可证机构编码：</label>
   	    	<input id="financial.financialPermitNum" name="financial.financialPermitNum"   onvalidation="checkBusinessLicenseNumUnique" vtype="maxLength:14"class="nui-textbox nui-form-input"/>
   	    	
   	    	<label>统一社会信用代码：</label>
			<input id="financial.unifySocietyCreditNum" onvalidation="checkUnifySocietyCreditNum" name="financial.unifySocietyCreditNum" required="true"
			vtype="minLength:18;maxLength:18"class="nui-textbox nui-form-input"/>
   	    	
   	    	<label>营业执照：</label>
   	    	<input id="financial.registerCode" name="financial.registerCode"   class="nui-textbox" onvalidation="checkBusinessLicenseNumUnique" /> 
   	    	
   	  		<label>组织机构代码：</label>
   	        <input id="financial.orgRegisterCd"  name="financial.orgRegisterCd" class="nui-textbox" enabled="false"  onvalidation="checkOrgRegisterCdUnique" />
   	       
   	        <label>swift码：</label>
   	        <input id="financial.swiftBicNum" name="financial.swiftBicNum"  class="nui-textbox" onvalidation="checkSwift" vtype="int;maxLength:11;minLength:8"/>
   	        
   	        <label>中征码：</label>
			<input id="financial.middleCode" name="financial.middleCode"  class="nui-textbox nui-form-input"onvalidation="checkMiddelCodeUnique" vtype="int;minLength:16;maxLength:16" />
		    
		    <label>是否信贷客户：</label>
			<input id="financial.isPotentialCust"  name="financial.isPotentialCust" required="true" 
				class="nui-dictcombobox nui-form-input"   dictTypeId="YesOrNo" />	
   	        
   	       	</div>
   	       	</fieldset>
   			<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0">
   					<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
					<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		   </div>
   </div>
   <script type="text/javascript">
   	   nui.parse();
   	   var form = new nui.Form("#form1");
			var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
	 //检查地区类型
      function checkAreaType(){
        var areaType=nui.get("financial.areaType").getValue();
        var ispc=nui.get("financial.isPotentialCust").getValue();
        //境外
        if(areaType==2){
			 nui.get("financial.registerCode").setRequired(true);//注册登记号码非必输
			 nui.get("financial.orgRegisterCd").setRequired(false);//组织机构代码必输
			 nui.get("financial.middleCode").setRequired(false);//中征码非必输
       	//境内
        }else if(areaType==1){
			 nui.get("financial.orgRegisterCd").setRequired(true);//组织机构代码必输
			 nui.get("financial.registerCode").setRequired(true);//注册登记号码必输
			 nui.get("financial.middleCode").setRequired(false);//中征码非必输
        }
     } 
   	   function save(){
   	      form.validate();//校验
   	      if(form.isValid()==false) {
   	      	 return;
   	      }
   	      git.mask("form1");
   	      var o = form.getData();
   	     partyName=nui.get("financial.partyName").getValue().trim();
   	         var json = nui.encode({"financial":o.financial,"partyName":partyName});
   	            $.ajax({
            url: "com.bos.csm.corporation.corporation.GetFinancialEcifCustNo.biz.ext",
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
            	   var json1 = nui.encode({"financial":o.financial,"partyName":partyName,"ecifPartyNum":ecifPartyNum});
         	      $.ajax({
   	      	 url:"com.bos.csm.financialinstitution.financialinstitutioninfo.addTbCsmFinancialInstitution.biz.ext",
   	      	 type:"POST",
   	         data:json1,
   	         cache:false, 
   	         contentType:'text/json',
   	         success:function(text){
   	         	git.unmask("form1");
   	         	if(text.msg){
   	         		nui.alert(text.msg);
   	         		return ;
   	         	}else{
   	         		var url =nui.context+"/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="+text.partyId+"&qote=3&partyNum="+text.partyNum;
   	         		url = git.toUrlParam(url);
   	         		nui.open({
			            url:url,
			            showMaxButton: true,
			            title: "编辑客户信息",
			            width: 600,
			            height: 450,
			            state:"max",
			            onload: function(e){
			            	var iframe = this.getIFrameEl();
			            	var text = iframe.contentWindow.document.body.innerText;
			            	//alert(text);
			            },
			            ondestroy: function (action) {
			            	CloseWindow('ok')
			                this.getParentBox().search();
			            }
			        });
   	         	}
   	         },
   	         error:function(jqXHR, textStatus, errorThrown){
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
   	   
	//校验组织机构号码是否唯一
	function checkOrgRegisterCdUnique(e){
		var registerCode = e.value;
	 	var areaType = nui.get("financial.areaType").getValue();
	 	if(null != registerCode && '' != registerCode){
		 	//校验组织机构代码有效
		   	isValidCompID(e);
		   	//如果有效，接着校验是否唯一
		 	if (e.isValid) {
			 	if(areaType=="1"){
		   	      	var json = {"certno":registerCode,"legorg":legorg};
		   	      	msg = exeRule("RCSM_00021","1",json);
		   	      	if(null != msg && '' != msg){
			   	      e.errorText = msg;
		              e.isValid = false;
		   	      	}
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
	 //校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e){
	 	var registerCode = nui.get("financial.registerCode").getValue();
	 	var areaType = nui.get("financial.areaType").getValue();
	 	if(null != registerCode && '' != registerCode && null != areaType && '' != areaType){
	 	if (e.isValid) {
		 	if(areaType=="1"){
	   	      	var json = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0002","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
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
		 		nui.get("financial.orgRegisterCd").setValue(e.value);
	   	      	var json = {"certno":unifySocietyCreditNum,"legorg":legorg};
	   	      	msg = exeRule("RCSM_unifySocietyCreditNum","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	 	}else{
	   	 		nui.get("financial.orgRegisterCd").setValue(null);
	   	 	}
	 	}
	}
      //校验中征码是否唯一
	function checkMiddelCodeUnique(e){
		var registerCode = e.value;
	 	 if (e.isValid) {
	   	      	var json = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0111","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	    }
	}
	//当区域为境外,金融机构为银行时,需要swift码
	function checkSwift(e){
		var areaType = nui.get("financial.areaType").getValue();
		var orgType = nui.get("financial.financeEnterpriseType").getValue();
		if(areaType=='2'){
			if(orgType=='01'||orgType=='02'||orgType=='03'||orgType=='04'||orgType=='05'||orgType=='06'||orgType=='13' ){
				e.errorText = '境外银行机构需要swift码';
				e.isValid = false;
			}
		}
	}
	
   </script>
</body>
</html>



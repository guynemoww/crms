<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): cc
  - Date:20160510
  - Description:
-->
<head>
<title>新增对公客户向导</title>
</head>
<body> 
<div id="form1" style="width:99.5%;height:99.5%;overflow:hidden;">
	<fieldset>
  	<legend>
    	<span>对公客户信息</span>
    </legend>
		<div class="nui-dynpanel" id="table1" columns="4" >
		
			<label>统一社会信用代码：</label>
			<input id="tbCsmCorporation.unifySocietyCreditNum" onblur="dataCheck" onvalidation="checkUnifySocietyCreditNum" name="tbCsmCorporation.unifySocietyCreditNum" 
			vtype="minLength:18;maxLength:18"class="nui-textbox nui-form-input"/>
		
			<label>组织机构代码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" name="tbCsmCorporation.orgRegisterCd" required="true"  onvalidation="checkOrgRegisterCdUnique"  class="nui-textbox nui-form-input" vtype="maxLength:32"  />
		
			<label>客户名称：</label>
			<input id="tbCsmCorporation.partyName" name="tbCsmCorporation.partyName" vtype="maxLength:100" required="true" onvalidation="checkpartyName" enabled="true" class="nui-textbox nui-form-input"  />
			
			<label>国家或地区：</label>
			<input id="tbCsmCorporation.contryRegionCd" name="tbCsmCorporation.contryRegionCd" required="true" class="nui-dictcombobox nui-form-input" 
				dictTypeId="CD000003"   dValue="CHN"/>
				
			<label>区域类型：</label>
			<input id="tbCsmCorporation.areaType" name="tbCsmCorporation.areaType" required="true" 
				onvaluechanged="dataCheck" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0210"/>
			
			<label>客户性质：</label>
			<input id="tbCsmCorporation.corpCustomerTypeCd" name="tbCsmCorporation.corpCustomerTypeCd"  required="true" 
				 class="nui-dictcombobox nui-form-input" onvaluechanged="selectCorpCustomerType" dictTypeId="XD_KHCD0252"  />
		    
		    <!-- <label>客户主体类型：</label> 
			<input id="tbCsmCorporation.mainCustTypeCd" name="tbCsmCorporation.mainCustTypeCd" 
			required="true" class="nui-dictcombobox nui-form-input" dictTypeId="ECIF_KHZTLX0001" />	 -->
			
		    <label id="regirterCode">营业执照：</label>
			<input id="tbCsmCorporation.registrCd"  name="tbCsmCorporation.registrCd"  vtype="maxLength:32;"   onvalidation="checkBusinessLicenseNumUnique" class="nui-textbox nui-form-input"  />
			
			<label>中征码：</label>
			<input id="tbCsmCorporation.middelCode" name="tbCsmCorporation.middelCode" required="true" onvalidation="checkMiddelCodeUnique" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16" />
		    
		    <label>是否信贷客户：</label>
			<input id="tbCsmCorporation.isPotentialCust"  name="tbCsmCorporation.isPotentialCust" required="true" 
				class="nui-dictcombobox nui-form-input" onvaluechanged="dataCheck"  dictTypeId="YesOrNo" />	
			
			<label>是否第三方客户：</label>
			<input id="tbCsmCorporation.isThirdCust"  name="tbCsmCorporation.isThirdCust" required="true" 
				class="nui-dictcombobox nui-form-input" onValuechanged="selectThirdCust" dictTypeId="YesOrNo" />
				
			<label>第三方客户类型：</label> 
			<input id="tbCsmCorporation.thirdCustTypeCd" name="tbCsmCorporation.thirdCustTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD7001" />	
			
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
	    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="add">保存</a>
    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
		var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
	debugger;
	//------------------页面动态控制区------------------//
	//填写统一社会信用代码
	function haveUnifySocietyCreditNum(){
		alert();
	}
	
	//选择是否是第三客户
	function selectThirdCust(){
		var isThirdCust = nui.get("tbCsmCorporation.isThirdCust").getValue();
		nui.get("tbCsmCorporation.thirdCustTypeCd").setEnabled(true);
		if(isThirdCust=="1"){
			nui.get("tbCsmCorporation.thirdCustTypeCd").setRequired(true);
		}else{
			nui.get("tbCsmCorporation.thirdCustTypeCd").setValue("");
			nui.get("tbCsmCorporation.thirdCustTypeCd").setRequired(false);
			nui.get("tbCsmCorporation.thirdCustTypeCd").setEnabled(false);
		}
	}
	
	
	// 客户类型修改,企业客户显示“注册登记号”，事业客户显示"事业法人证书号"
	function selectCorpCustomerType(){
		// 客户类型
		var custType = nui.get("tbCsmCorporation.corpCustomerTypeCd").getValue();
		if(custType=="2"){
			//事业类客户--事业法人证书号暂时不填写，没有营业执照号
			//$("#regirterCode").html("事业法人证书号：");
			nui.get("tbCsmCorporation.registrCd").setEnabled(false);
			nui.get("tbCsmCorporation.registrCd").setValue("");
		} else {
			//企业类、个体工商户
			$("#regirterCode").html("营业执照：");
		}
	}
	
	//区域类型事件
	function dataCheck(){
		var area = nui.get("tbCsmCorporation.areaType").getValue();//境内外标识
		var isPotentialCust = nui.get("tbCsmCorporation.isPotentialCust").getValue();//是否授信客户
		var unifySocietyCreditNum = nui.get("tbCsmCorporation.unifySocietyCreditNum").getValue();//统一社会信用代码
		if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
			nui.get("tbCsmCorporation.orgRegisterCd").setRequired(false);
			nui.get("tbCsmCorporation.registrCd").setRequired(false);
			nui.get("tbCsmCorporation.orgRegisterCd").setEnabled(false);
			nui.get("tbCsmCorporation.registrCd").setEnabled(false);
		}else{
			//nui.get("tbCsmCorporation.registrCd").setRequired(true);
			if(area=="1"){//境内
				nui.get("tbCsmCorporation.orgRegisterCd").setRequired(true);
			}else{//境外
				nui.get("tbCsmCorporation.orgRegisterCd").setRequired(false);
			}
		}
		if('1'==isPotentialCust&&area=="1"){//境内，授信客户为必输
			nui.get("tbCsmCorporation.middelCode").setRequired(true);
		}else{
			nui.get("tbCsmCorporation.middelCode").setRequired(false);
		}
	}
	//---------------合法校验区--------------------//
	//校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e){
	 	var registerCode = nui.get("tbCsmCorporation.registrCd").getValue();
	 	if(null != registerCode && '' != registerCode ){
	 	if (e.isValid) {
	   	      	var json = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0001","1",json);
	   	      	if(null != msg && '' != msg){
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
	   	//isValidUnifyNum(e);
	 	if (e.isValid) {
	   	      	var json = {"certno":unifySocietyCreditNum,"legorg":legorg};
	   	      	msg = exeRule("RCSM_unifySocietyCreditNum","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}else{
					nui.get("tbCsmCorporation.orgRegisterCd").setValue(unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17));
	   	      	}
	   	 	}
	 	}
	 	
	}
	
   	//校验组织机构号码是否唯一 checkpartyName
	function checkOrgRegisterCdUnique(e){
		var registerCode =e.value;
	 	if(null != registerCode && '' != registerCode){
	 	//校验组织机构代码有效
	   	isValidCompID(e);
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	   	      	var json = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0011","1",json);
	   	      	if(null != msg && '' != msg){
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
	//------------------事件操作区-----------------//
	function add(){
		//校验
    var ecifPartyNum;
		form.validate();
        if (form.isValid()==false){
         	return alert("请按规则填写信息");
        }
       	git.mask("form1");
        var o = form.getData();
        	//alert(o);
        partyName=nui.get("tbCsmCorporation.partyName").getValue().trim();
        var json = nui.encode({"tbCsmCorporation":o.tbCsmCorporation,"partyName":partyName});
        	//alert(2);
        $.ajax({
            url: "com.bos.csm.corporation.corporation.GetCorprationEcifCustNo.biz.ext",
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
            	   var json1 = nui.encode({"tbCsmCorporation":o.tbCsmCorporation,"ecifPartyNum":ecifPartyNum});
                $.ajax({
            url: "com.bos.csm.corporation.corporation.addKeyMsgs.biz.ext",
            type: 'POST',
            data: json1,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
            	if (text.msg) {
            		alert(text.msg);
            		return;
            	} else {
            		var url = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId=" 
            			+ text.response.partyId + "&partyNum=" + text.response.partyNum;
            			url += "&cusType=" + text.response.corpCustomerTypeCd+ "&qote=2" + "&ecifPartyNum=" + ecifPartyNum;
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
			            	CloseWindow('ok');
			            }
			        });
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
   		//默认国家中国 地区境内
	function initPage(){
		nui.get("tbCsmCorporation.areaType").setValue("1");
		nui.get("tbCsmCorporation.contryRegionCd").setValue("CHN");
	}
   	initPage();
   	//当“国家或地区”项选择“中国”时，“区域类型”应自动默认为“境内”。
   	function countryCheck(){
   		var country = nui.get("tbCsmCorporation.contryRegionCd").getValue();
   		if(country=="CHN"){
   			nui.get("tbCsmCorporation.areaType").setValue("1");
   		}else{
   			nui.get("tbCsmCorporation.areaType").setValue();
   		}
   	}
</script>
</body>
</html>


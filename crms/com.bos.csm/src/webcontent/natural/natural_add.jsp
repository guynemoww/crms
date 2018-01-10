<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-19 9:03:49
  - Description:
-->
<head>
<title>自然人新建</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<fieldset>
  	<legend>
    	<span>自然人新建</span>
    </legend>
    		<div class="nui-dynpanel" id="table1" columns="4" >
	    	 <label>证件类型：</label>
		     <input id="natural.certType" name="natural.certType" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZJLX0001"  dValue="10" onValuechanged="hideOrShow" />
		     		
		     <label>证件号码：</label>			
		     <input id="natural.certNum"  class="nui-textbox nui-form-input" name="natural.certNum"  required="true"  />
		     
		     <label>客户名称：</label>				
			 <input id="party.partyName" name="party.partyName"   required="true" class="nui-textbox nui-form-input" />		
			 
			 <label class="nui-form-label">国籍：</label>
			 <input id="natural.countrySign" name="natural.countrySign"class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" required="true" dValue="CHN"/>
		     
		     <label>中征码：</label>				
			 <input id="natural.middleCode" name="natural.middleCode"  required="false" class="nui-textbox nui-form-input" vtype="int;"/>		
		     
		     <label>是否授信客户：</label>
			 <input id="party.isPotentialCust" name="party.isPotentialCust" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="1"/>	
			 
			 <label>是否第三方客户：</label>
			<input id="natural.isThirdCust"  name="natural.isThirdCust" required="true" class="nui-dictcombobox nui-form-input" onValuechanged="selectThirdCust" dictTypeId="YesOrNo"/>
			
			<label>第三方客户类型：</label> 
			<input id="natural.thirdCustTypeCd"name="natural.thirdCustTypeCd" required="false" class="nui-dictcombobox nui-form-input"dictTypeId="XD_KHCD7001" />	
		   	
		   	<label id="createDateid">发证日期：</label>
			<input id="natural.certCreateDate" name="natural.certCreateDate" class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" required="false" format="yyyy-MM-dd" onvaluechanged="changeDate(this)"/>
			
			<label id="endDateid">到期日期：</label>
			<input id="natural.certEndDate" name="natural.certEndDate"  class="nui-datepicker nui-form-input" minDate="<%=GitUtil.getBusiDateStr()%>" required="false"  format="yyyy-MM-dd" onvaluechanged="changeDate(this)"/>
	   		
	   		<label id="checkDateid">长期：</label>
			<input id="checkDate"  class="nui-checkbox" required="false" onvaluechanged="checkDate(this)"/>
			 </div>
			<div class="nui-dynpanel" id="table2" columns="4"  style="display:none">
		     <a id="" class="nui-button" iconCls="icon-search"  onclick="checkIdentity()">身份核查</a>
		     <input id="check" name="check" class="nui-textbox nui-form-input"  Enabled="false"/>		
		     </div>
	</fieldset>
			
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="add">保存</a>
	    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	 <!--    	<a id = "ecifSerach" class="nui-button" iconCls="icon-search"  onclick="search()">ECIF</a> -->
		</div>
	</div>
<script type="text/javascript">
	nui.parse();	
	var form = new nui.Form("#form1");
	var legorg= "<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg")%>";
	function init(){
		//字典过滤，过滤掉临时身份证的证件类型
	   var arr = git.getDictDataUnFilter("XD_ZJLX0001",'17');
		nui.get("natural.certType").setData(arr);
	    
	    //字典过滤，只要4的第三方客户类型
	    var arr = git.getDictDataFilter("XD_KHCD7001","4");
		nui.get("natural.thirdCustTypeCd").setData(arr);
		nui.get("natural.isThirdCust").setValue("0"); 
	}
    init();	
	
		function add(){
			//校验
			form.validate();
	        if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;
       		 } 
       		//校验自然人客户是否唯一 
       		var certNum = nui.get("natural.certNum").getValue(); 
       			if(null != certNum && '' != certNum){
			   	//如果有效，接着校验是否唯一
			   	      	var json = {"certNum":certNum,"legorg":legorg};
			   	      	msg = exeRule("RCSM_1006","1",json);
			   	      	if(null != msg && '' != msg){
				   	      nui.alert(msg);
				   	      return;
			              }
			 }
        git.mask("form1");
        var  o =form.getData();
        submit(o);
		}	
		
		function hideOrShow(){
		var certType = nui.get("natural.certType").getValue(); 
			if(certType=="10"){
			$("#table2").css("display","block");
			}else{
			$("#table2").css("display","none");
			}
				
		}

		//身份校验	
		function checkIdentity(){
		nui.get("check").setValue("loading......");
		git.mask("form1");
		var certType = nui.get("natural.certType").getValue();
		var certNum = nui.get("natural.certNum").getValue();
		var partyName = nui.get("party.partyName").getValue();
        var json =nui.encode({"certNum":certNum,"certType":certType,"partyName":partyName}); 
        $.ajax({
		            url: "com.bos.csm.natural.natural.checkIdentityInfo.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            async: false,
		            contentType:'text/json',
		            success: function (text) {
		             if("AAAAAAA"!=text.map.code){
		                git.unmask("form1");
		             	nui.alert("调用总行前置接口失败:"+text.map.msg);
		             	return ;
		             }else{
		             //身份核查成功
		             if("00"==text.map.result){
		             //nui.alert("身份核查成功");
		             nui.get("check").setValue("身份核查通过！");
		             git.unmask("form1");
		             }else{
		             nui.get("check").setValue("身份核查未通过！");
		             //nui.alert("身份核查失败:"+text.map.msg);
		             git.unmask("form1");
		              return ;
		             }
					}
				}	
			});		
		}
		
		// search
	function search(){
	    nui.open({
	        url: nui.context + "/csm/natural/natural_add_search.jsp",
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	           	 }
	        }
	    });
	}
 	//证件验证
	function onValidateCertificateCode(e){
		var certType = nui.get("natural.certType").getValue();
		var certNum = nui.get("natural.certNum").getValue();
		if('101'==certType||'102'==certType){
			var validateMsg=CsmValidateobj.validateIDCard(certNum,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
		//唯一性校验
		if (e.isValid) {
		//------多法人改造------增加按同一法人不允许有证件号码重复的校验
	   	      	var json = {"certNum":certNum,"certType":certType,"legorg":legorg};
	   	      	msg = exeRule("RCSM_00024","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      }
	   	 }
	}
	
	 	function onValidateCertificateCodeNme(e){
        		var certType = nui.get("natural.certType").getValue();
		var certNum = nui.get("natural.certNum").getValue();
		var partyName = nui.get("party.partyName").getValue();
	   	      			if('101'==certType||'102'==certType){
			var validateMsg=CsmValidateobj.validateIDCard(certNum,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
		//唯一性校验
		if (e.isValid) {
				   var json = {"certNum":certNum,"certType":certType,"partyName":partyName,"legorg":legorg};
	   	      	msg = exeRule("RCSM_00025","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	            
	   	      }
	   	 }
	   	     
	} 
	
	
	function submit(o){
		git.mask("form1");
	       var ecifPartyNum;
			o.party.partyName=nui.get("party.partyName").getValue().trim();
			o.natural.partyName=nui.get("party.partyName").getValue().trim();
		        var json = nui.encode(o);
	//调ECIF对私客户基本信息创建与维护查询客户号
 			        $.ajax({
		            url: "com.bos.csm.natural.natural.GetEcifCustNo.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            async: false,
		            contentType:'text/json',
		            success: function (text) {
		             debugger;
		             if("AAAAAAA"!=text.map.msg){
		             	nui.alert("调用ECIF接口失败:"+text.map.msgg);
		               git.unmask("form1");
		             
		             	   return ;
		             }else{
		             ecifPartyNum=text.map.ecifPartyNum;
		             if(ecifPartyNum!=""&&ecifPartyNum!=null){
		             debugger;
		             var json1 = nui.encode({"party":o.party,"ecifPartyNum":ecifPartyNum,"natural":o.natural});
 		        $.ajax({
		            url: "com.bos.csm.natural.natural.addNaturalPerson.biz.ext",
		            type: 'POST',
		            data: json1,
		            cache: false,
		            async: false,
		            contentType:'text/json',
		            success: function (text) {
		            	git.unmask("form1");
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		            	debugger;
				            var url=nui.context + "/csm/natural/natural_tree.jsp?partyId="+ text.response.partyId+"&partyNum="+text.response.partyNum+"&ecifPartyNum="+ecifPartyNum;            			
				            		nui.open({
							            url:url,
							            showMaxButton: true,
							            title: "编辑客户信息",
							            width: 1024,
							            height: 768,
							            state:"max",
							            onload: function(e){
							            	var iframe = this.getIFrameEl();
							            	var text = iframe.contentWindow.document.body.innerText;
							            },
							            ondestroy: function (action) {
							            	CloseWindow('ok')
							                this.getParentBox().search();
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
		                nui.alert(jqXHR.responseText);
		                 return ;
		            }
		        }); 
        	}
        	
        	
        	//选择是否是第三客户
		function selectThirdCust(){
			var istc = nui.get("natural.isThirdCust").getValue();
			if(istc=="0"){
				nui.get("natural.thirdCustTypeCd").setRequired(true);
				nui.get("natural.thirdCustTypeCd").setEnabled(false);
				nui.get("natural.thirdCustTypeCd").setValue("");
			}else{
				nui.get("natural.thirdCustTypeCd").setRequired(true);
				nui.get("natural.thirdCustTypeCd").setEnabled(true);
			}
			form.validate();
		}
		//身份证到期日期长期选择
		function checkDate(e){
			if(e.checked==true){
				nui.get("natural.certEndDate").setValue("9999-12-31");
				nui.get("natural.certEndDate").setEnabled(false);
			}else{
				nui.get("natural.certEndDate").setEnabled(true);
				nui.get("natural.certEndDate").setValue("");
			}
		}
</script>
</body>
</html>
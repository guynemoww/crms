<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-9 15:26:25
  - Description:
-->
<head>
<title>同业客户概况信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<fieldset>
		<legend>
		   <span>概况</span>
		</legend>
		
	    <input id="financialInstitution.partyId" class="nui-hidden" name="financialInstitution.partyId" value="<%=request.getParameter("partyId")%>">
		<div class="nui-dynpanel" columns="4">
			<label>CRMS客户编号</label>
			<input id="financialInstitution.partyNum" class="nui-text" name="financialInstitution.partyNum"/>
		   
		    <label>ECIF客户编号</label>
			<input id="financialInstitution.ecifPartyNum" class="nui-text" name="financialInstitution.ecifPartyNum" />
		   
		    <label>客户名称</label>
		    <input id="financialInstitution.partyName" required="true" class="nui-textbox" name="financialInstitution.partyName" enabled="false" />
		    
		    <label>别名</label>
		    <input id="financialInstitution.englishCustomerName" class="nui-textbox" name="financialInstitution.englishCustomerName" vtype="maxLength:300"/>
		   
		   	<label>是否信贷客户</label>
			<input id="financialInstitution.isPotentialCust" name="financialInstitution.isPotentialCust" required="true" 
			class="nui-dictcombobox nui-form-input"  dictTypeId="YesOrNo" />
			
			<label>区域类型</label>
   	    	<input id="financialInstitution.areaType" name="financialInstitution.areaType" class="nui-dictcombobox" enabled="false"
   	    	 dictTypeId="XD_KHCD0210">
   	    				
   	    	<label>国家或地区</label>
			<input id="financialInstitution.countryBasic" name="financialInstitution.countryBasic"required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003"  />
   	    	
   	    	<label>金融机构类型</label>
		 	<input  id="financialInstitution.financeEnterpriseType" name="financialInstitution.financeEnterpriseType"  class="nui-dictcombobox nui-form-input" enabled="false"
		 			allowInput="false"  dictTypeId="ECIF_JRJGLX01" />
   	    	
   	    	<label>金融许可证机构编码：</label>
   	    	<input id="financial.financialPermitNum" name="financial.financialPermitNum" allowInput="false"  class="nui-textbox nui-form-input"/>
   	    	
		    <label>swift 码</label>
		    <input id="financialInstitution.swiftBicNum" class="nui-textbox" name="financialInstitution.swiftBicNum"enabled="false" />
		</div>
		</fieldset>
		<fieldset>
		<legend>
			<span>证件信息</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
					
			<label>统一社会信用代码</label>
			<input id="financialInstitution.unifySocietyCreditNum" name="financialInstitution.unifySocietyCreditNum" required="false" enabled="true" onvalidation="checkUnifySocietyCreditNum"  class="nui-textbox nui-form-input"/>
			
		 	<label>营业执照</label>
   	    	<input id="financialInstitution.registerCode"enabled="false" name="financialInstitution.registerCode"  class="nui-textbox"/> 
   	    	
   	    	<label>登记注册日期</label>
			<input id="financialInstitution.registerDate" required="true" name="financialInstitution.registerDate" maxDate="<%=com.bos.pub.GitUtil.getBusiDateStr()%>" class="nui-datepicker" />
			
   	    	<label>注册资本币种</label>
			<input id="financialInstitution.registerAssetsCurrencyCd" name="financialInstitution.registerAssetsCurrencyCd"  class="nui-dictcombobox" 
			   valueField="dictID" textField="dictName" dictTypeId="CD000001" required="true" dvalue="CNY"/>
			   
   	    	<label>营业执照登记证到期日</label>
		    <input id="financialInstitution.registerEndDate" required="true" name="financialInstitution.registerEndDate" minDate="<%=com.bos.pub.GitUtil.getBusiDateStr()%>" class="nui-datepicker"  />
   	    	
   	    	<label>长期</label>
			<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />
   	    	
   	    	<label>注册资本</label>
		    <input id="financialInstitution.registerAssets" required="true" name="financialInstitution.registerAssets" class="nui-textbox" vtype="float;maxLength:20" dataType="currency" />
		    
   	    	<label>金融机构类型</label>
			<input id="financialInstitution.financeEnterpriseType" name="financialInstitution.financeEnterpriseType"  class="nui-dictcombobox" 
			   valueField="dictID" textField="dictName" dictTypeId="ECIF_JRJGLX01" required="true" />
			   
   	  		<label>组织机构代码</label>
   	        <input id="financialInstitution.orgRegisterCd"  enabled="false"name="financialInstitution.orgRegisterCd" class="nui-textbox"  />
   	    			
   	        <label>中征码</label>
   	    	<input id="financialInstitution.middleCode" name="financialInstitution.middleCode"  class="nui-textbox" vtype="int;minLength:16;maxLength:16"/>
   	    	
   	    	<label>非现场监管统计机构编码</label>
   	    	<input id="financialInstitution.manageCountNum" name="financialInstitution.manageCountNum"  class="nui-textbox" vtype="maxLength:50"/>
		</div>
		</fieldset>
		<fieldset>
		<legend>
		   <span>注册地址</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
			<label>国家或地区</label>
			<input id="financialInstitution.country" name="financialInstitution.country"required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003"  onvalidation="setNotChina"  onitemclick="nationChange" requiredErrorText="国家地区必选"/>
	
			<label>省/自治区/直辖市</label>
			<input id="financialInstitution.province" name="financialInstitution.province" required="true" valueField="dictid" textField="dictname" class="nui-combobox nui-form-input" onitemclick="provinceChange"/>
	
			<label>市/自治州</label>
			<input id="financialInstitution.city" name="financialInstitution.city" required="true" valueField="dictid" textField="dictname" class="nui-combobox nui-form-input" onitemclick="cityChange"/>
	
			<label>区/县</label>
			<input id="financialInstitution.county" name="financialInstitution.county" required="true" valueField="dictid" textField="dictname" class="nui-combobox nui-form-input" onvaluechanged="setAdress"/>
	
			<label>街道</label>
			<input id="financialInstitution.street" name="financialInstitution.street" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>注册地行政区划</label>
		    <input id="financialInstitution.regAdministrativeDivisions" name="financialInstitution.regAdministrativeDivisions"  required="true" class="nui-textbox nui-form-input" allowInput="false" Enabled="false" />
		    
		    <label>经营地址</label>
		    <input id="financialInstitution.jyAddress" name="financialInstitution.jyAddress"  required="true" class="nui-textbox nui-form-input"  vtype="maxLength:200"/>
		    
		</div>
		</fieldset>
		<fieldset>
		<legend>
		   <span>经营及重要标识</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
		<!-- 
			<label>金融许可证机构编码</label>
		    <input id="financialInstitution.financialPermitNum" name="financialInstitution.financialPermitNum"  class="nui-textbox"  onvalidation="onEnglishAndNumberExceptEmptyValidation"/>
		  -->   
			<label>是否上市公司</label>
			<input id="financialInstitution.listingCorporation" name="financialInstitution.listingCorporation" required="true" class="nui-dictcombobox" 
			   valueField="dictID" textField="dictName" dictTypeId="XD_0002"/>
			<label>是否集团成员</label>
			<input id="financialInstitution.groupCustomer" name="financialInstitution.groupCustomer" class="nui-dictcombobox" enabled="false"
			   valurField="dictID" textField="dictName" dvalue="0" dictTypeId="YesOrNo" />  
			<label id='groupId' >集团客户名称</label>
			<input id="financialInstitution.groupName" name="financialInstitution.groupName"  class="nui-text" />     
		</div>
		</fieldset>
		<%--<fieldset>
		 <legend>
		   <span>系统信息</span>
		 </legend>
		<div class="nui-dynpanel" columns="4">
			<label>登记人：</label>
			<input name="item.userNum" dictTypeId="user" required="false" enabled="false"   class="nui-text nui-form-input"  />
			
			<label>登记机构：</label>
			<input name="item.orgNum" dictTypeId="org" required="false" enabled="false"  class="nui-text nui-form-input"  />
			
			<label>登记日期：</label>
			<input name="financialInstitution.createTime"   required="false"  Enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd hh:mm:ss"/>
			
			<label>更新人员：</label>
			<input class="nui-text nui-form-input" required="false" name="financialInstitution.updateUserNum" dictTypeId="user" enabled="true"/>
			
			<label>更新机构：</label>
			<input name="financialInstitution.updateOrgNum" required="false" Enabled="true" dictTypeId="org" class="nui-text nui-form-input" />
			
			<label>更新日期：</label>
			<input name="financialInstitution.updateTime"  required="false"  Enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd hh:mm:ss"/>
		</div>	
		</fieldset>--%>
	</div>	
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px" 
   		 borderStyle="border:0;">
    	<a  id="btnSave" class="nui-button"  iconCls="icon-save" onclick="update">保存</a>
    	<span style="display:inline-block;width:25px;"></span>
	</div>

	<script type="text/javascript">
		nui.parse();
		git.mask("form1");
		var form = new nui.Form("form1");
		var qote = "<%=request.getParameter("qote")%>" ;
		var partyId = "<%=request.getParameter("partyId")%>" ;
		var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
		var unifySocietyCreditNumEdit;
		var orgRegisterCdEdit;
		//查看进入
		if(qote==1){
		   form.setEnabled(false);
		   nui.get("btnSave").hide();
		}
	
	    //初始化表单数据
	    function formInit(){
	    	var partyId = nui.get("financialInstitution.partyId").getValue();
	    	var param = {"fit":{"partyId":partyId}};
	    	var json = nui.encode(param);
	    	if(partyId!=""){
	    	if(qote!=1){
	    	$.ajax({
	    			url:"com.bos.csm.financialinstitution.financialinstitutioninfo.queryTbCsmFinancialInstitutionEcif.biz.ext",
	    			type:"POST",
	    			data:json,
	    			contentType:'text/json',
	    			success:function(data){
	    				git.unmask("form1");
	    				debugger;
	    				//初始化注册登记证到期日
		               	if(data.map.financialInstitution.registerEndDate=="9999-12-31"){
		             	nui.get("longTime").setChecked(true);
		             	nui.get("financialInstitution.registerEndDate").setEnabled(false);
		               	}else{
		               	nui.get("longTime").setChecked(false);
		               	nui.get("financialInstitution.registerEndDate").setEnabled(true);
		               	}
	    				var typeCd = data.map.financialInstitution.financialInstitutionTypeCd;
	    				orgRegisterCdEdit=data.map.financialInstitution.orgRegisterCd;
    	                unifySocietyCreditNumEdit=data.map.financialInstitution.unifySocietyCreditNum;
	    				form.setData(data.map);
	    				var msg=data.map.msg;
	    				var msgg=data.map.msgg;
	    				var data=data.map;
	    				window.form1Data=form.getData();
	    				git.getDistrictsByParentidEcif(nui.get('financialInstitution.country').getValue(),function(data){
							nui.get('financialInstitution.province').setData(data.items);
						});
						git.getDistrictsByParentidEcif(nui.get('financialInstitution.province').getValue(),function(data){
							nui.get('financialInstitution.city').setData(data.items);
						});
						git.getDistrictsByParentidEcif(nui.get('financialInstitution.city').getValue(),function(data){
							nui.get('financialInstitution.county').setData(data.items);
						});
	    				
	    				//翻译注册地行政区划
            			var district = nui.get("financialInstitution.regAdministrativeDivisions").getValue();
		            		git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
								nui.encode({"dictid":district}), function(text){
									var dictname=text.dictname ;
									nui.get("financialInstitution.regAdministrativeDivisions").setValue(district.substring(0,6));
								});
					/* 			if("AAAAAAA"!=msg){
								 nui.alert(msgg);
								  git.unmask("form1");
								} */
	    			},
	    			error:function(jqXHR, textStatus, errorThrown){
	       	   	  	   git.unmask("form1");
	       	   	  	   nui.alert(jqXHR.responseText);
	       	   		}});
	    	}else{
	    		$.ajax({
	    			url:"com.bos.csm.financialinstitution.financialinstitutioninfo.queryTbCsmFinancialInstitution.biz.ext",
	    			type:"POST",
	    			data:json,
	    			contentType:'text/json',
	    			success:function(data){
	    				git.unmask("form1");
	    				var typeCd = data.financialInstitution.financialInstitutionTypeCd;
	    				orgRegisterCdEdit=data.financialInstitution.orgRegisterCd;
    	                unifySocietyCreditNumEdit=data.financialInstitution.unifySocietyCreditNum;
	    				form.setData(data);
	    				window.form1Data=form.getData();
	    				git.getDistrictsByParentidEcif(nui.get('financialInstitution.country').getValue(),function(data){
							nui.get('financialInstitution.province').setData(data.items);
						});
						git.getDistrictsByParentidEcif(nui.get('financialInstitution.province').getValue(),function(data){
							nui.get('financialInstitution.city').setData(data.items);
						});
						git.getDistrictsByParentidEcif(nui.get('financialInstitution.city').getValue(),function(data){
							nui.get('financialInstitution.county').setData(data.items);
						});
	    				
	    				//翻译注册地行政区划
            			var district = nui.get("financialInstitution.regAdministrativeDivisions").getValue();
		            		git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
								nui.encode({"dictid":district}), function(text){
									var dictname=text.dictname ;
									nui.get("financialInstitution.regAdministrativeDivisions").setValue(district.substring(0,6));
								});
	    			},
	    			error:function(jqXHR, textStatus, errorThrown){
	       	   	  	   git.unmask("form1");
	       	   	  	   nui.alert(jqXHR.responseText);
	       	   		}});
	       	   		}
	       }
	    }
	    
	 formInit();
	//“国家” 选择了“中国”之外的字段，那么 省，市，区县不必输
    function setNotChina(){
    	var belongStation = nui.get("financialInstitution.country").getValue();
    	//alert(belongStation);
    	if(belongStation != "CHN"){
    		nui.get("financialInstitution.province").setRequired(false);
    		nui.get("financialInstitution.city").setRequired(false);
    		nui.get("financialInstitution.county").setRequired(false);
    		
    	}else{
    		nui.get("financialInstitution.province").setRequired(true);
    		nui.get("financialInstitution.city").setRequired(true);
    		nui.get("financialInstitution.county").setRequired(true);
    	}
    }
    
    //国家事件
    function nationChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
			nui.get('financialInstitution.province').setData(data.items);
		});
		nui.get('financialInstitution.province').setValue("");
		nui.get('financialInstitution.city').setValue("");
		nui.get('financialInstitution.county').setValue("");
	}
	//省份事件
	function provinceChange(e){
			git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
				nui.get('financialInstitution.city').setData(data.items);
			});
			nui.get('financialInstitution.city').setValue("");
			nui.get('financialInstitution.county').setValue("");
	}
	//城市 事件
	function cityChange(e){
			git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
				nui.get('financialInstitution.county').setData(data.items);
			});
		
			nui.get('financialInstitution.county').setValue("");
	}
      
      
    //潜在客户选择事件
    function selectPotential(){
    
    
    }  
      
      
      function update(){
	       //校验
	       form.validate();
	       if(form.isValid()==false) {
	       		alert("请完整填写信息!");
	       		return;
	       }
	       git.mask("form1");
	       var o = form.getData();
	       var json = nui.encode(o);
	       $.ajax({
	       	   url:"com.bos.csm.financialinstitution.financialinstitutioninfo.updateTbCsmFinancialInstitutionEcif.biz.ext",//updateTbCsmFinancialInstitution
	       	   type:'POST',
	       	   data:json,
	       	   contentType:'text/json',
	       	   success:function(data){
	       	    git.unmask("form1");
	       	        debugger;
	       	        if("AAAAAAA"!=data.map.msg){
	       	        alert(data.map.msgg);
	       	        }else{
	       	   		git.unmask("form1");
	       	   		if(data.msg){
	       	   		 	alert(data.msg);
	       	   		}else{
	       	   			alert("保存成功");
	       	   		}
	       	   		}
	       	   },
	       	   error:function(jqXHR, textStatus, errorThrown){
	       	   	  git.unmask("form1");
	       	   	  nui.alert(jqXHR.responseText);
	       	   }}); 
	       	   	unifySocietyCreditNum=nui.get("financialInstitution.unifySocietyCreditNum").getValue();
				orgRegisterCd=nui.get("financialInstitution.orgRegisterCd").getValue();
	    }
      
      
      function checkFinancialInstitutionTypeCd(e){
         git.mask("form1");
         var financialInstitutionTypeCd= nui.get("financialInstitution.subjectTypeCd");
         var json = {parentId:e};
   	        $.ajax({
   	      	  	url:"com.bos.csm.pub.getDict.getFinancialInstitutionTypeCd.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	git.unmask("form1");
   	          	 	financialInstitutionTypeCd.setData(text.infos);
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		git.unmask("form1");
   	          		nui.alert(jqXHR.responseText);
   	          }
   	     });
      }
      
    <%--选择行政区划 --%>
	function selectDivisionsCd(e) {
        var btnEdit = this;
        var nationalityCd=nui.get("financialInstitution.country").getValue();
        if(nationalityCd){
          nui.open({
            url: nui.context + "/csm/pub/divisions_code_tree.jsp?nationalityCd="+nationalityCd,
            showMaxButton: true,
            title: "选择行政区划",
            width: 800,
            height: 300,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.decode(data);
                    //provinceCd =  data.item.provinceCd ;
                 	//city =  data.item.city ;
                 	district =  data.item.district ;
                    if (data) {
                    	nui.get("financialInstitution.regAdministrativeDivisions").setText(district);
                    	nui.get("financialInstitution.regAdministrativeDivisions").setValue(district);
                    	git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
						nui.encode({"dictid":district}), function(text){
							var dictname=text.dictname ;
							nui.get("financialInstitution.regAdministrativeDivisions").setText(dictname);
						});
				                    	
				            }
				                }
				            }
				        });  
        
        }else{
        	alert("请先选择国家代码");
        }           
	}
	
	 //非空时，必须输入英文加数字
   	  function onEnglishAndNumberExceptEmptyValidation(e) {
         if (e.isValid) {
          if(null != e.value && "" != e.value){
          	if (isEnglishAndNumber(e.value) == false) {
               e.errorText = "必须输入英文+数字";
               e.isValid = false;
            }
          }
        }
      }
      
       /* 是否英文+数字 */
      function isEnglishAndNumber(v) {
         var re = new RegExp("^[0-9a-zA-Z\_]+$");
         if (re.test(v)) return true;
         return false;
      }
      
      //根据"国家和地区" 设置"行政区划（2011版）名称"是否为必输 
    function setDivisionsCdStatus(){
    	var belongStation = nui.get("financialInstitution.contryRegionCd").getValue();
    	if(belongStation != "CHN"){
    		nui.get("financialInstitution.administrativeDivisionsCd").setRequired(false);
    	}else{
    		nui.get("financialInstitution.administrativeDivisionsCd").setRequired(true);
    	}
    }
	
      
      function selectFinancialType(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=ECIF_JRJGLX01",
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
                        checkAreaType();
                        var LoanCard = true;
                        if(data.dictid.indexOf("1C")!=-1){
                       		LoanCard = false;
                        }else{
                        	LoanCard = true;
                        }
                        nui.get("financialInstitution.loanCardNum").setRequired(LoanCard);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}

	
   	  
   	  //检查地区类型
      function checkAreaType(){
        var areaType=nui.get("financialInstitution.areaType").value;
        var subjectTypeCd=nui.get("financialInstitution.financeEnterpriseType").value;
        var ispc=nui.get("financialInstitution.isPotentialCust").getValue();
        //境外
        if(areaType==2){
			 nui.get("financialInstitution.registerCode").setRequired(false);//营业执照号码非必输
			 nui.get("financialInstitution.orgRegisterCd").setRequired(false);//组织机构代码非必输
			 nui.get("financialInstitution.middleCode").setRequired(false);//中征码非必输
       	//境内
        }else if(areaType==1){
			 nui.get("financialInstitution.orgRegisterCd").setRequired(true);//组织机构代码必输
			 nui.get("financialInstitution.registerCode").setRequired(true);//营业执照号码必输
			 if('1'==ispc){
			  	nui.get("financialInstitution.middleCode").setRequired(true);//中征码必输
			 }else{
			 	nui.get("financialInstitution.middleCode").setRequired(false);//中征码必输
			 }
        }else{
        	nui.get("financialInstitution.registerCode").setRequired(false);//营业执照号码非必输
			nui.get("financialInstitution.orgRegisterCd").setRequired(false);//组织机构代码非必输
			nui.get("financialInstitution.middleCode").setRequired(false);//中征码非必输
        }
        if(areaType==2&&subjectTypeCd=="230000"){
        	nui.get("financialInstitution.swiftBicNum").set({required:true});
        }else{
        	nui.get("financialInstitution.swiftBicNum").set({required:false});
        }
      }
      //设置注册地行政区划
	function setAdress(){
		//为通讯地址赋值
		var districtId = nui.get('financialInstitution.county').getValue();
		git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
						nui.encode({"dictid":districtId}), function(text){
							var dictname=text.dictname ;
							if(districtId){
								nui.get("financialInstitution.regAdministrativeDivisions").setValue(districtId.substring(0,6));
							}
						});
	}
	
	 //校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e){
	 	var registerCode = nui.get("financialInstitution.registerCode").getValue();
	 	var areaType = nui.get("financialInstitution.areaType").getValue();
	 	if(null != registerCode && '' != registerCode && null != areaType && '' != areaType){
	 	if (e.isValid) {
		 	if(areaType=="1"){
		 		var so = window.form1Data;
		 		var s_registerCode;
			 	if(null == so){
			 		s_registerCode=nui.get("financialInstitution.registerCode").getValue();
			 	}else{
			 		s_registerCode =so.financialInstitution.registerCode;
			 	}
			 	if(s_registerCode != registerCode){
		   	      	var json = {"certno":registerCode};
		   	      	msg = exeRule("RCSM_0002","1",json);
		   	      	if(null != msg && '' != msg){
			   	      e.errorText = msg;
		              e.isValid = false;
		   	      	}
	   	      	}
	   	    }
	   	 }
	 	}
   	  }
   	  //校验组织机构号码是否唯一
	function checkOrgRegisterCdUnique(e){
		var registerCode = e.value;
	 	var areaType = nui.get("financialInstitution.areaType").getValue();
	 	if(null != registerCode && '' != registerCode && null != areaType && '' != areaType){
	 	//校验组织机构代码有效
	   	isValidCompID(e);
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
		 	if(areaType=="1"){
		 		var so = window.form1Data;
			 	var s_orgRegisterCode;
			 	if(null == so){
			 		s_orgRegisterCode=e.value;
			 	}else{
			 		s_orgRegisterCode =so.financialInstitution.orgRegisterCd;
			 	}
			 	if(s_orgRegisterCode != registerCode){
		   	      	var json = {"certno":registerCode};
		   	      	msg = exeRule("RCSM_00021","1",json);
		   	      	if(null != msg && '' != msg){
			   	      e.errorText = msg;
		              e.isValid = false;
		   	      	}
	   	      	}
	   	    }
	   	 }
	 	}
	}
	
	//校验中征码是否唯一
	function checkMiddelCodeUnique(e) {
		var registerCode = e.value;
		if (e.isValid) {
			var json = {
				"certno" : registerCode,
				"partyId" : partyId
			};
			msg = exeRule("RCSM_0112", "1", json);
			if (null != msg && '' != msg) {
				e.errorText = msg;
				e.isValid = false;
			}
		}
	}
   	  //设置注册登记证到期日长期
	function setDate(e){
		if(e.checked==true){
		nui.get("financialInstitution.registerEndDate").setValue("9999-12-31");
		nui.get("financialInstitution.registerEndDate").setEnabled(false);
		}else{
		nui.get("financialInstitution.registerEndDate").setEnabled(true);
		nui.get("financialInstitution.registerEndDate").setValue("");
		}
	}
	
	 	   	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e){
		     debugger;
		var unifySocietyCreditNum = e.value;
	 	if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	      e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	 var registerCode=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
	   	//isValidUnifyNum(e);
	 	if (e.isValid) {
	 	     if(unifySocietyCreditNum!=unifySocietyCreditNumEdit){
	   	      	var json = {"certno":unifySocietyCreditNum,"legorg":legorg};
	   	      	msg = exeRule("RCSM_unifySocietyCreditNum","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}else{
					nui.get("financialInstitution.orgRegisterCd").setValue(unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17));
	   	      	}
	   	      	}
	   	      	 if(registerCode!=orgRegisterCdEdit){
	   	      var json1z = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0011","1",json1z);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	      	}
	   	 	}
	 	}
	 	
	}
	</script>
</body>
</html>
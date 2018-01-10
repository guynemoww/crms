<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川s
  - Date: 2015-05-06 11:01:34
  - Description:
-->
<head>
<title>基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<input id="party.partyId" class="nui-hidden nui-form-input" name="party.partyId" value="<%=request.getParameter("partyId")%>" />
			<input id="party.examineState" name="party.examineState" class="nui-hidden nui-form-input"/>
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4">
					
					<label class="nui-form-label">客户编号：</label>
					<input id="party.partyNum" class="nui-text nui-form-input" enabled="false" name="party.partyNum" /> 
					
					<label>ECIF客户编号：</label> 
					<input id="party.ecifPartyNum" name="party.ecifPartyNum" enabled="false" class="nui-textbox nui-form-input" /> 
					
					<label class="nui-form-label">客户名称：</label> 
					<input id="party.partyName"class="nui-textbox nui-form-input" name="party.partyName"required="true" enabled="false"/>
						 
					<label class="nui-form-label">证件类型：</label> 
					<input id="natural.certType" name="natural.certType" class="nui-dictcombobox nui-form-input"dictTypeId="CDKH0002" required="true"enabled="false"/> 
						
					<label class="nui-form-label">证件号码：</label> 
					<input id="natural.certNum"class="nui-textbox nui-form-input" name="natural.certNum"required="true" enabled="false" /> 
						
					<label>中征码：</label>				
			 		<input id="natural.middleCode" name="natural.middleCode"  required="false" class="nui-textbox nui-form-input" vtype="int;"/>		
					
					<label class="nui-form-label">是否信贷客户：</label> 
					<input id="party.isPotentialCust" name="party.isPotentialCust"class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" required="true"  />
						
					<label>是否第三方客户：</label>
					<input id="natural.isThirdCust"  name="natural.isThirdCust" required="true" 
							class="nui-dictcombobox nui-form-input" onValuechanged="selectThirdCust" dictTypeId="YesOrNo" />
							
					<label>第三方客户类型：</label> 
					<input id="natural.thirdCustTypeCd"name="natural.thirdCustTypeCd" required="false"class="nui-dictcombobox nui-form-input"dictTypeId="XD_KHCD7001" />	
				
					<label id="createDateid" style="display: none;">发证日期：</label>
					<input id="natural.certCreateDate" name="natural.certCreateDate" class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" required="false" format="yyyy-MM-dd"  style="display: none;"  onvaluechanged="changeDate(this)"/>
		
					<label id="endDateid" style="display: none;">到期日期：</label>
					<input id="natural.certEndDate" name="natural.certEndDate"  class="nui-datepicker nui-form-input" minDate="<%=GitUtil.getBusiDateStr()%>" required="false"  format="yyyy-MM-dd"  style="display: none;"  onvaluechanged="changeDate(this)"/>
					
					<label id="checkDateid" style="display: none;">长期：</label>
					<input id="checkDate"  class="nui-checkbox" required="false" style="display: none;" onvaluechanged="checkDate(this)"/>
				</div>
			</fieldset>
			<fieldset>
				<legend>
					<span>详细信息</span>
				</legend>
				<input id="natural.naturalPersonTypeCd" class="nui-hidden nui-form-input" name="natural.naturalPersonTypeCd" />
				<div class="nui-dynpanel" columns="4">
				<label class="nui-form-label">居民性质：</label>
					<input id="natural.resdntCharCd" name="natural.resdntCharCd"  class="nui-dictcombobox nui-form-input" dictTypeId="ECIF_JMXZ0001" required="true" dValue="1" enabled="true"/>
					<label class="nui-form-label">性别：</label>
					<input id="natural.genderCd" name="natural.genderCd"  class="nui-dictcombobox nui-form-input"dictTypeId="CDKH0048" required="true" enabled="false"/>
					
					<label class="nui-form-label">出生日期：</label>
					<input id="natural.birthday" name="natural.birthday" class="nui-datepicker nui-form-input"  maxDate="<%=GitUtil.getBusiDateStr()%>" required="true" enabled="false" format="yyyy-MM-dd"/>
					
					<label class="nui-form-label">国籍：</label>
					<input id="natural.countrySign" name="natural.countrySign"class="nui-dictcombobox nui-form-input" dictTypeId="CD000003"required="true" />
					
					<label id="nui-form-label">民族：</label>
					<input id="natural.nation" name="natural.nation" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0046" required="true" />
					
					<label class="nui-form-label">婚姻状况：</label>
					<input id="natural.marriageCd" name="natural.marriageCd"class="nui-dictcombobox nui-form-input"dictTypeId="ECIF_HYZK0001" required="true" />
					
					
					<label class="nui-form-label">是否农户：</label> 
					<input id="natural.isFarmer" name="natural.isFarmer"class="nui-dictcombobox nui-form-input"dictTypeId="YesOrNo" required="true" />
					
					<label class="nui-form-label">户籍所在地：</label> 
					<input id="natural.hukouRegisted" name="natural.hukouRegisted" vtype="maxLength:100"class="nui-textbox nui-form-input"required="true"/>
				<!-- 
				
					<label class="nui-form-label">户籍性质：</label> 
					<input id="natural.hukouProperty" name="natural.hukouProperty"class="nui-dictcombobox nui-form-input"dictTypeId="XD_KHCD2001" required="true" />
				
					<label class="nui-form-label">所属街道派出所：</label> 
					<input id="natural.streetPoliceStation" name="natural.streetPoliceStation" vtype="maxLength:100" class="nui-textbox nui-form-input"required="true" />	
					
					<label class="nui-form-label">健康状况：</label> 
					<input id="natural.healthState" name="natural.healthState"class="nui-dictcombobox nui-form-input"dictTypeId="XD_KHCD2002" required="true"/>	
				 -->		
				 	<label class="nui-form-label">高校名称/毕业院校名称：</label> 
					<input id="natural.schName" name="natural.schName"class="nui-textbox nui-form-input" required="true"/>
				 
					<label class="nui-form-label">最高学历：</label>
					<input id="natural.educationBackgroudCd"class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0045"name="natural.educationBackgroudCd" required="true"/>
					
					<label class="nui-form-label">最高学位：</label>
					<input id="natural.degreeCd" name="natural.degreeCd" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0014" required="true"/>
						
					<label class="nui-form-label">职业：</label>
					<input id="natural.profession" name="natural.profession" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD2003" required="true"/>
						
					<label class="nui-form-label">职称：</label>
					<input id="natural.professionalTitle" name="natural.professionalTitle" 
						class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0066" required="true"/>
						
					<label class="nui-form-label">职务：</label>
					<input id="natural.accountingAssistant" name="natural.accountingAssistant" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD2004" required="true"/>
						
					<label class="nui-form-label">岗位性质：</label>
					<input id="natural.positionProperty" name="natural.positionProperty" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD2005" required="true"/>
					<label class="nui-form-label">单位性质：</label>
					<input id="natural.natureOfUnit" name="natural.natureOfUnit" 
						class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0029" required="true"/>
					<label class="nui-form-label">单位规模：</label>
					<input id="natural.scaleOfUnit" name="natural.scaleOfUnit" 
						class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0025" required="true"/>
						
					<label class="nui-form-label">目前工作持续年限：</label>
					<div>
					<input id="natural.workYears" name="natural.workYears" 
						class="nui-textbox nui-form-input" required="true" vtype="int;maxLength:8;range:1,99999"/>年</div>
						
					<label class="nui-form-label">家庭人口：</label>
					<input id="natural.familyNumber" name="natural.familyNumber" class="nui-textbox nui-form-input" required="true" vtype="int;maxLength:6" />
					
					<!-- 
					<label class="nui-form-label">供养人口：</label>
					<input id="natural.provideForNumber" name="natural.provideForNumber" 
						class="nui-textbox nui-form-input" vtype="int;maxLength:6" required="true"/>
					-->
					<label class="nui-form-label">家庭住址：</label>
					<input id="natural.familyAddress" name="natural.familyAddress"  vtype="maxLength:100" 
						class="nui-textbox nui-form-input"  required="true"/>
						
					<label class="nui-form-label">住宅性质：</label>
					<input id="natural.houseProperty" name="natural.houseProperty" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD2006" required="true"/>
					
					<label class="nui-form-label">家庭电话：</label>
					<input id="natural.familyPhone" name="natural.familyPhone" class="nui-textbox nui-form-input" required="false" vtype="int;maxLength:20"/>
						
					<label class="nui-form-label">通讯地址：</label>      
					<input id="natural.communicationAddress" name="natural.communicationAddress"  vtype="maxLength:100" 
						class="nui-textbox nui-form-input"  required="true"/>
						
					<label class="nui-form-label">通讯地址邮编：</label>
					<input id="natural.communicationAddressCode" name="natural.communicationAddressCode"  vtype="int;maxLength:6;minLength:6" 
						class="nui-textbox nui-form-input"  required="true"/>
					
					<label class="nui-form-label">居住地址邮编：</label>
					<input id="natural.liveAddressCode" name="natural.liveAddressCode"  vtype="int;maxLength:6;minLength:6" 
						class="nui-textbox nui-form-input"  required="true"/>
					<label class="nui-form-label">行业：</label>
					<input id="natural.industry" name="natural.industry"
						class="nui-buttonEdit nui-form-input" dictTypeId="CDKH0095" required="true" allowInput=false onbuttonclick="selectTrade" emptyText="--请选择--" />
					<label class="nui-form-label">手机号码（短信推送）：</label>
					<input id="natural.phoneNumber" name="natural.phoneNumber" 
						class="nui-textbox nui-form-input"  required="true" vtype="int;maxLength:11;minLength:11"/>
					<label class="nui-form-label">短信推送号码是否核对：</label>
					<input id="natural.flgSms" name="natural.flgSms" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="1" />
					
					<label class="nui-form-label">微信号：</label>
					<input id="natural.weixinNum" name="natural.weixinNum" 
						class="nui-textbox nui-form-input"  required="false" vtype="maxLength:30;"/>
						
					<label class="nui-form-label">电子邮箱：</label>
					<input id="natural.email" name="natural.email" 
						class="nui-textbox nui-form-input"  required="false" vtype="email;maxLength:50;"/>
					
					<label class="nui-form-label">工作单位：</label>
					<input id="natural.workUnit" name="natural.workUnit" vtype="maxLength:100"
						class="nui-textbox nui-form-input" required="true"/>
						
					<label class="nui-form-label">单位地址：</label>
					<input id="natural.unitAdress" name="natural.unitAdress"  vtype="maxLength:100" 
						class="nui-textbox nui-form-input" required="false"/>
					
					<label class="nui-form-label">年收入：</label>
					<input id="natural.annualsalary" name="natural.annualsalary" 
						class="nui-textbox" vtype="float;maxLength:20" dataType="currency" />
							
					<label class="nui-form-label">单位电话：</label>
					<input id="natural.unitPhone" name="natural.unitPhone" 
						class="nui-textbox nui-form-input" required="false" vtype="int;maxLength:20"/>
					
					<label class="nui-form-label">单位邮编：</label>
					<input id="natural.unitAddressCode" name="natural.unitAddressCode" 
						class="nui-textbox nui-form-input"/>
						
					<label class="nui-form-label">行业具体描述：</label>
					<input id="natural.industryDesc" name="natural.industryDesc"
						class="nui-textbox nui-form-input"/>
					
					<label class="nui-form-label">联保小组标志：</label>
					<input id="natural.jointGuarantee" name="natural.jointGuarantee" 
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" Enabled="false"/>
				
					<label class="nui-form-label">我行股东标志：</label>
					<input id="natural.stockholderOfBank" name="natural.stockholderOfBank" 
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" Enabled="false"/>
					
					<label class="nui-form-label">是否我行关联方：</label>
					<input id="natural.isBasebankRelaCust" name="natural.isBasebankRelaCust" 
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" Enabled="false"/>
					
					
					 <label>是否集团成员：</label>
					<input id="natural.isGroupCust" name="natural.isGroupCust"  enabled="false"
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
    	
    				<label>所属集团客户名称：</label>
					<input id="natural.attachGroupName" name="natural.attachGroupName"  enabled="false"
						class="nui-text nui-form-input" />
   
					<label class="nui-form-label">黑名单标志：</label>
					<input id="natural.whetherBlackList" name="natural.whetherBlackList" 
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" Enabled="false"/>
						
					<label class="nui-form-label">加入黑名单原因：</label>
					<input id="natural.blackListReason" name="natural.blackListReason" 
						class="nui-textbox nui-form-input" " Enabled="false"/>
						
					<label class="nui-form-label">是否本行员工：</label>
					<input id="natural.isBankEmployee" name="natural.isBankEmployee" required="false" 
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"  />
						<label class="nui-form-label">是否有(营业执照)：</label>
					<input id="natural.ifcertificate" name="natural.ifcertificate" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"  />
					<label class="nui-form-label">是否本地人：</label>
					<input id="natural.natiflag" name="natural.natiflag" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"  />
					
					<!-- 	
					<label class="nui-form-label">在职行员等级：</label>
					<input id="natural.employeeGrade" name="natural.employeeGrade" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0201" Enabled="true"/>
					 -->
 				</div>
			</fieldset>
			<fieldset>
				<legend>
					<span>系统信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">经办机构：</label> 
					<input id="item.orgNum"class="nui-text nui-form-input" name="item.orgNum"dictTypeId="org" />
					
					<label class="nui-form-label">经办人：</label>
					<input id="item.userNum" class="nui-text nui-form-input"name="item.userNum" dictTypeId="user" />
					
					<label class="nui-form-label">经办日期：</label> 
					<input id="natural.createTime" class="nui-text nui-form-input" name="natural.createTime" dateFormat="yyyy-MM-dd" />
				</div>
			</fieldset>
			<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
			    <a id="btnSave" class="nui-button" iconCls="icon-save" onclick="update(1)">保存</a>
			    <a id="btnTest" class="nui-button" iconCls="icon-save" onclick="save(2)">临时保存</a>
			    <a id="btnVali" class="nui-button" iconCls="icon-save" onclick="validationAll">校验完整性</a>
			</div>
		</div>

<script type="text/javascript">
       nui.parse();
    git.mask("form1");
    var form = new nui.Form("#form1");
    var qote = "<%=request.getParameter("qote")%>" ;
    var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>" ;
    debugger;
   
	if(qote==1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	   nui.get("btnTest").hide();
	   nui.get("btnVali").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
	  var json = nui.encode({"natural":{"partyId":"<%=request.getParameter("partyId")%>"}});
	  if(qote!=1){
	  $.ajax({
			  url: "com.bos.csm.natural.natural.getNaturalBaseInfo.biz.ext",//getNaturalBaseInfo
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
			  	 git.unmask("form1");
	                   if(mydata.map.msg!="AAAAAAA"){
	                    	nui.alert("调取ECIF信息失败！:"+mydata.map.msgg);
        	                 return;
	                   }
	                 var o = nui.decode(mydata);
	                 form.setData(o);
	                  if(mydata.party.isPotentialCust=='1'){
	                  nui.get('party.isPotentialCust').setEnabled(false);
	                 } 
	                 if(mydata.party.examineState=='3'){
	                	nui.get('btnTest').setEnabled(false);
	             	}
	                 
	                //通过规则初始化关联情况
			 		var para = {"partyId":'<%=request.getParameter("partyId")%>'};
			 		//联保小组标识
			   	    msg = exeRule("RCSM_1000","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.jointGuarantee').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.jointGuarantee').setValue(1);
			   	    }
			   	    //黑名单标识
			   	    msg = exeRule("RCSM_1003","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.whetherBlackList').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.whetherBlackList').setValue(1);
			   	    }
			   	    //我行股东标识
			   	    para = {"certType":mydata.natural.certType,"certNum":mydata.natural.certNum};
	                msg = exeRule("RCSM_1001","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.stockholderOfBank').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.stockholderOfBank').setValue(1);
			   	    }
			   	    //是否我行关联方
			   	    msg = exeRule("RCSM_1002","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.isBasebankRelaCust').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.isBasebankRelaCust').setValue(1);
			   	    } 
	            }, 
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }     
	  });}else{
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
	                  if(mydata.party.isPotentialCust=='1'){
	                	nui.get('party.isPotentialCust').setEnabled(false);
	                 } 
	                 if(mydata.party.examineState=='3'){
	                	nui.get('btnTest').setEnabled(false);
	             	}
	                 
	                //通过规则初始化关联情况
			 		var para = {"partyId":'<%=request.getParameter("partyId")%>'};
			 		//联保小组标识
			   	    msg = exeRule("RCSM_1000","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.jointGuarantee').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.jointGuarantee').setValue(1);
			   	    }
			   	    //黑名单标识
			   	    msg = exeRule("RCSM_1003","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.whetherBlackList').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.whetherBlackList').setValue(1);
			   	    }
			   	    //我行股东标识
			   	    para = {"certType":mydata.natural.certType,"certNum":mydata.natural.certNum};
	                msg = exeRule("RCSM_1001","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.stockholderOfBank').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.stockholderOfBank').setValue(1);
			   	    }
			   	    //是否我行关联方
			   	    msg = exeRule("RCSM_1002","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('natural.isBasebankRelaCust').setValue(0);
			   	    }else{
			   	    	 nui.get('natural.isBasebankRelaCust').setValue(1);
			   	    } 
	            }, 
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }     
	  });
	  }
	  	//第三方客户类型码值
	 	 var arr = git.getDictDataFilter("XD_KHCD7001",'4');
		 nui.get("natural.thirdCustTypeCd").setData(arr);
	 }
	initForm();
	
	function selectTrade(e) {
        var btnEdit = this;
        nui.open({
        	url : nui.context + "/csm/corporation/csm_other_related_reltree.jsp?sqlName=com.bos.csm.corporation.tbCsmRelatedInfo.industryTree",
            //url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300",
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
	//保存自然人信息
	function update(v){
		//测试页面的数据是否正确
		var o = form.getData();
		var json = nui.encode(o);
		
		form.validate();
        if (form.isValid()==false) {
        	nui.alert("请完整填写信息！");
        	return;
        }
        //校验通过，保存数据
        save(v);
	}
	
	//校验客户完整性
 	function validationAll(){
 		form.validate();
	        if (form.isValid()==false) {
	        	nui.alert("请完整填写信息！");
	        	return;
	        }
 		//校验是否保存信用信息
 		var json = {"partyId":'<%=request.getParameter("partyId")%>'};
   	    msg = exeRule("RCSM_0008","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
   	      		//如果是个体工商户有证的情况下，检验有木有经营信息
   	     if(nui.get("natural.ifcertificate").getValue()=="1"){
   	    
 		var json = {"partyId":'<%=request.getParameter("partyId")%>'};
   	    msg = exeRule("RCSM_1008","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
   	      }
   	    //校验是否保存家庭财务信息
//    	    msg = exeRule("RCSM_0009","1",json);
//    	    if(null != msg && '' != msg){
// 	   	     nui.alert(msg);
// 	   	     return;
//    	     }
   	     
   	    //已婚、初婚、再婚、复婚”时，进行完整性校验时校验关联关系-配偶信息必输。
   	    if(("20,21,22,23").indexOf(nui.get("natural.marriageCd").getValue())!=-1){
   	    	 msg = exeRule("RCSM_00101","1",json);
   	   		 if(null != msg && '' != msg){
	   	     	nui.alert(msg);
	   	     	return;
   	     	}
   	    }
 		//校验通过后，修改客户状态标志，改为3：有效
 		save(3);
 	}
	
	//临时保存客户时的操作 
	function save(v){
		git.mask("form1"); 
		//设置客户状态，1表示正常保存，2表示临时保存
		var examineState=nui.get("party.examineState").getValue();
		//设置客户状态，1表示正常保存，2表示临时保存
		if(examineState=='3'){//如果客户已经通过完整性校验
			nui.get("party.examineState").setValue(3);
		}else{
			nui.get("party.examineState").setValue(v);
		}
        //校验通过，保存数据
        var o = form.getData();
        o.natural.partyName=nui.get("party.partyName").getValue().trim();
        var json = nui.encode(o);
/* 		$.ajax({
			url : "com.bos.csm.natural.natural.GetEcifCustNo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if('3'==v){
					if('0'==text.ret){
						nui.alert("完整性校验通过！");
						//调用押品接口传数据
						json = json+nui.encode({"customertype":"03"});
			      	    $.ajax({
				        	url: "com.bos.csm.pub.custSyn.custSynForColl.biz.ext",
				        	type: 'POST',
				        	data: json,
				        	cache: false,
				        	contentType:'text/json',
				        	success: function (text) {
				        		
				        	}
						});
					}else{
						nui.alert("更新状态失败");
					}
				}else{
					if('0'==text.ret){
						nui.alert("保存成功");
					}else{
						nui.alert("保存失败");
					}				
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		}); */
	$.ajax({
			url : "com.bos.csm.natural.natural.updateNaturalInfoLs.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if('3'==v){
					if('0'==text.ret){
						nui.alert("完整性校验通过！");
						json = json+nui.encode({"customertype":"03"});
			      	    $.ajax({
				        	url: "com.bos.csm.pub.custSyn.custSynForColl.biz.ext",
				        	type: 'POST',
				        	data: json,
				        	cache: false,
				        	contentType:'text/json',
				        	success: function (text) {
				        		
				        	}
						});
					}else{
					if('1'==text.ret){
					  if(text.msg.msg!="AAAAAAA"){
					 nui.alert("调用ECIF接口失败:"+text.msg.msgg);
					
					  }else{
						nui.alert("保存成功");
					}
				  }
				}
			}else{
				if('0'==text.ret){
				nui.alert("保存成功");
				}else{
				nui.alert("保存失败");
				}
			}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
	}
	
	
	//选择是否是第三客户
	function selectThirdCust(){
		if(qote==1){
		
		}else{
			var istc = nui.get("natural.isThirdCust").getValue();
			nui.get("natural.thirdCustTypeCd").setEnabled(true);
			if(istc=="1"){
				nui.get("natural.thirdCustTypeCd").setRequired(true);
			}else{
				nui.get("natural.thirdCustTypeCd").setValue("");
				nui.get("natural.thirdCustTypeCd").setRequired(false);
				nui.get("natural.thirdCustTypeCd").setEnabled(false);
			}
			form.validate();
		}
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
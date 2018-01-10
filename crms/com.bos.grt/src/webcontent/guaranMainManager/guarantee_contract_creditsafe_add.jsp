<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 
  - Date: 
  - Description: 
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>新增合同下的应收账款买断项下的信用保险</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<!-- 履约保证保险主键 -->
		<input name="tbGrtCreditsafe.suretyid" required="false" class="nui-hidden" vtype="maxLength:32" />
		<!-- 保证基本信息主键 -->
		<input name="tbGrtGuaranteeBasic.suretyId" required="false" class="nui-hidden" vtype="maxLength:32" />
		<!-- 保证基本信息 -->
		<input name="tbGrtGuaranteeBasic.partyName" required="false" class="nui-hidden" vtype="maxLength:32"
			id="tbGrtGuaranteeBasic.partyName" />
		<input name="tbGrtSurety.partyId" required="true" class="nui-hidden" id="tbGrtSurety.partyId" />
		<input name="tbConSubcontract.subcontractId" required="false" class="nui-hidden" id="tbConSubcontract.subcontractId"
			value="<%=request.getParameter("subcontractId") %>"/>
		<input name="tbConSubcontract.contractId" required="false" class="nui-hidden" id="tbConSubcontract.contractId"
			value="<%=request.getParameter("contractId") %>"/>
		<div class="nui-dynpanel" columns="4">
			<label>保证类型：</label>
			<input name="tbGrtGuaranteeBasic.guaranteeType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DBCD4001"
				id="tbGrtGuaranteeBasic.guaranteeType" enabled="false" />
			
			<label>公司名称：</label>
			<input name="tbGrtCreditsafe.insuranceCompanyName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:300"
				id="tbGrtCreditsafe.insuranceCompanyName" onbuttonclick="chooiseParty" allowinput="false"/>
			
			<label>主体属性：</label>
			<input name="tbGrtGuaranteeBasic.guaranteerType" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:30"
				id="tbGrtGuaranteeBasic.guaranteerType" enabled="false" showNullItem="true" />
					
		    <label>内部评级：</label>
			<input id="guaranteerEvalResult" required="false" class="nui-textbox nui-form-input" enabled="false"/>
			  
			<label>保证币种：</label>
			<input name="tbGrtGuaranteeBasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3"
				dictTypeId="CD000001" />
				
			<label>保证金额：</label>
			<input name="tbGrtGuaranteeBasic.suretyAmt" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;"
				id="tbGrtGuaranteeBasic.suretyAmt" dataType="currency" enabled="false"/>
			
			<label>单号：</label>
			<input name="tbGrtCreditsafe.policyNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>投保币种：</label>
			<input name="tbGrtCreditsafe.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:20"
				dictTypeId="CD000001"/>
	
			<label>投保金额：</label>
			<input name="tbGrtCreditsafe.insureMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;"
				id="insureMoney" dataType="currency" />
		  
		    <label>生效日期</label>
			<input name="tbGrtCreditsafe.effectiveDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10"
				id="effectiveDate" onvaluechanged="onShengXiaovaluechanged"/>
		 
			<label>到期日期：</label>
			<input name="tbGrtCreditsafe.endDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10"
				id="dueDate" onvaluechanged="dueDatevalueChange" ondrawdate="onDrawDueDate"/>
		
			<label>受益人：</label>
			<input name="tbGrtCreditsafe.beneficiary" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60"/>
		 
			<label>投保人：</label>
			<input name="tbGrtCreditsafe.policyHolder" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
		 
			<label>是否可循环：</label>
			<input name="tbGrtCreditsafe.isCycle" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1"
				dictTypeId="XD_0002" />
		 
			<label>最低赔偿比例(%)：</label>
			<input name="tbGrtCreditsafe.minLossRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:6;" />
			 
			<label>与债务人之间是否存在正相关关系：</label>
			<input name="tbGrtGuaranteeBasic.guaranteerDependence" id="tbGrtGuaranteeBasic.guaranteerDependence" required="true" class="nui-dictcombobox nui-form-input" 
				dictTypeId="XD_0002" vtype="maxLength:2" />
	
			<label>是否存在限制或解除或撤销条款：</label>
			<input name="tbGrtGuaranteeBasic.guaranteeIsLimit" id="tbGrtGuaranteeBasic.guaranteeIsLimit" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2"
				dictTypeId="XD_0002" />
				
			<label>经办用户：</label>
			<input name="tbGrtGuaranteeBasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:10"
				value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
				
			<label>更新人：</label>
			<input name="tbGrtGuaranteeBasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:10"
				value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
				
			<label>经办机构：</label>
			<input name="tbGrtGuaranteeBasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:9"
				value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
				
			<label>更新机构：</label>
			<input name="tbGrtGuaranteeBasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:9"
				value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
					
			<label>创建时间：</label>
			<input name="tbGrtGuaranteeBasic.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10"
				value="<%=com.bos.pub.GitUtil.getBusiDate() %>" enabled="false" />
	
			<label>更新时间：</label>
			<input name="tbGrtGuaranteeBasic.updateTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10"
				value="<%=com.bos.pub.GitUtil.getBusiDate() %>" enabled="false" />
		</div>
	</div>
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	</div>
				
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		//保证类型赋值
		nui.get("tbGrtGuaranteeBasic.guaranteeType").setValue("<%=request.getParameter("sortType") %>");
		
		/**
		 * 保存
		 */
		function save() {
			//保证人名称
			nui.get("tbGrtGuaranteeBasic.partyName").setValue(nui.get("tbGrtCreditsafe.insuranceCompanyName").getValue());
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var subcontractId = "<%=request.getParameter("subcontractId") %>";
			var json=nui.encode(o)+nui.encode({"subcontractId":subcontractId});
			git.mask();
			$.ajax({
		        url: "com.bos.grt.guaranMainManager.guaranteeContract.addGuaranteeContractCreditsafe.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg=="保存成功！"){
		        		nui.alert(text.msg);
		        		CloseWindow("ok");
		        	} else {
		        		CloseWindow("ok");
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
	
		/**
		 * 控制登记日期
		 */
	    function onDrawDate(e) {
	        var date = e.date;//登记日期
	        var Shengxiaodate=nui.get("effectiveDate").getValue();//生效日期
	        var d = new Date();
	        if (date.getTime() > d.getTime()) {
	            e.allowSelect = false;
	        }
	    }
	   
		/**
		 * 控制到期日期
		 */
		function onDrawDueDate(e) {
		    var date = e.date;
		    var d = new Date();
		    if (date.getTime() < d.getTime()) {
		        e.allowSelect = false;
		    }
		}
	  
		/**
		 * 发起日期值发生改变
		 */
		function onRegDateChange(e){
			var Shengxiaodate=nui.get("effectiveDate").getValue();//生效日期
			if(Shengxiaodate!=""){
				if(!CompareRegAndShengXiaoDate()){ //比较登记日期和生效日期
					nui.get("effectiveDate").setValue("");
				}
			}
		}
	   
	
		/**
		 * 生效日期值发生改变
		 */
		function onShengXiaovaluechanged(e){
			if(!CompareDueAndShengXiaoDate()&&nui.get("dueDate").getValue()!=""){//生效日期大于到期日期
				nui.alert("生效日期必须小于或等于到期日期");
				nui.get("dueDate").setValue("");
	  		}
		}
	  
		/**
		 * 到期日期值发生改变
		 */
		function dueDatevalueChange(){
			if(!CompareDueAndShengXiaoDate()){//生效日期大于到期日期
				nui.alert("生效日期必须小于或等于到期日期");
				nui.get("dueDate").setValue("");
			}
		}	  
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(){
	  		var Shengxiaodate=nui.get("effectiveDate").getValue();//生效日期
	  		var dueDate=nui.get("dueDate").getValue();//到期日期
	  		if(nui.parseDate(dueDate)-nui.parseDate(Shengxiaodate)<=0){//到期日期小于生效日期
	  			return false;
	  		}else{
	  			return true;
	  		}
		}
		
		/**
		 * 选择保证公司
		 */
		function chooiseParty(){
			nui.open({
				url: nui.context+"/grt/grtMainManage/grtCus_chooise.jsp",
				title: "选择保证公司", 
				width: 800, 
				height: 500,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					nui.get("tbGrtSurety.partyId").setValue(action[0]);
					nui.get("tbGrtCreditsafe.insuranceCompanyName").setValue(action[2]);	
					nui.get("tbGrtCreditsafe.insuranceCompanyName").setText(action[2]);
					nui.get("tbGrtGuaranteeBasic.guaranteerType").setValue(action[5]);
					getGuaranteerEvalResult(action[0]);
				}
			}); 
		}
		
		/**
		 * 内部评级
		 */
		function getGuaranteerEvalResult(partyId){
		    var o = {"partyId":partyId};
		    var json=nui.encode(o);
			git.mask();
			$.ajax({
		        url: "com.bos.grtpublic.getCsm.getGuaranteerTypeEvalResult.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
                    if(text.map.guaranteerTypeName == "null"){
			        	nui.get("tbGrtGuaranteeBasic.guaranteerType").setText("");
		        	}else{
		        		nui.get("tbGrtGuaranteeBasic.guaranteerType").setText(text.map.guaranteerTypeName);
		        	}
		        	if(text.map.guaranteerType == "null"){
			        	nui.get("tbGrtGuaranteeBasic.guaranteerType").setValue("");
		        	}else{
		        		nui.get("tbGrtGuaranteeBasic.guaranteerType").setValue(text.map.guaranteerType);
		        		//初始保证人合格的默认信息
		        		initGuaranteeQualified("<%=request.getParameter("sortType")%>",text.map.guaranteerType);
		        	}
		        	if(text.map.guaranteerEvalResult == "null"){
		        		nui.get("guaranteerEvalResult").setValue("");
		        	}else{
			        	nui.get("guaranteerEvalResult").setValue(text.map.guaranteerEvalResult);
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		//初始保证的合格信息默认值
		function initGuaranteeQualified(guaranteeType,guaranteerType){
		    var o = {"inTbGrtGuaranteeBasic":{"guaranteeType":guaranteeType,"guaranteerType":guaranteerType}};
		    var json=nui.encode(o);
		   git.mask();
			$.ajax({
		        url: "com.bos.grtpublic.getGrtQualified.getGuaranteeQualifiedTbGrtGuaranteeBasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		            if(text.tbGrtGuaranteeBasic.guaranteerDependence!=""&&text.tbGrtGuaranteeBasic.guaranteerDependence!="null"&&text.tbGrtGuaranteeBasic.guaranteerDependence!=null){
		               //保证人相关性
		               nui.get("tbGrtGuaranteeBasic.guaranteerDependence").setValue(text.tbGrtGuaranteeBasic.guaranteerDependence);
		               nui.get("tbGrtGuaranteeBasic.guaranteerDependence").setEnabled(false);
		            }
		            if(text.tbGrtGuaranteeBasic.guaranteeIsLimit!=""&&text.tbGrtGuaranteeBasic.guaranteeIsLimit!="null"&&text.tbGrtGuaranteeBasic.guaranteeIsLimit!=null){
		            	//包含限制、解除或撤销条款：
		            	nui.get("tbGrtGuaranteeBasic.guaranteeIsLimit").setValue(text.tbGrtGuaranteeBasic.guaranteeIsLimit);
		            	nui.get("tbGrtGuaranteeBasic.guaranteeIsLimit").setEnabled(false);
		            }
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		//根据担保合同查询出担保合同的债权金额显示保证金额
		function getSubContractSuretyAmt(){
			var o = {"tbConSubcontract":{"subcontractId":"<%=request.getParameter("subcontractId") %>"}};
			var json=nui.encode(o);
		    git.mask();
		    $.ajax({
		        url: "com.bos.grtpublic.getGrtConSubcontract.getSubContractSuretyAmt.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		            nui.get("tbGrtGuaranteeBasic.suretyAmt").setValue(text.tbConSubcontract.guaranteeRightMoney);
		            git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		 getSubContractSuretyAmt();
		 
		/**
		 * 关闭
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		function closeok(){
			CloseWindow("ok");
		}
	</script>
</body>
</html>

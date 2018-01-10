<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 
  - Date: 
  - Description: 
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>业务申请-信用保险</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input name="grtGuaranteeBasic.partyId" id="grtGuaranteeBasic.partyId"  class="nui-hidden" />
		<input name="grtGuaranteeBasic.guaranteeType" id="grtGuaranteeBasic.guaranteeType" class="nui-hidden" />
		<input name="bizGrtRel.applyId" id="bizGrtRel.applyId"  class="nui-hidden" />
		<input name="bizGrtRel.suretyType" id="bizGrtRel.suretyType"  class="nui-hidden" />
		<input name="grtCreditsafe.suretyId" id="grtCreditsafe.suretyId"  class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>投保人：</label>
			<input name="grtCreditsafe.policyHolder" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>保险人：</label>
			<input name="grtCreditsafe.insurer" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>受益人：</label>
			<input name="grtCreditsafe.beneficiary" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>保单号：</label>
			<input name=grtCreditsafe.policyNum  required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>赔款转让协议编号：</label>
			<input name="grtCreditsafe.ndemnityNum"  class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>币种：</label>
			<input name="grtCreditsafe.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" />
				
			<label>信用限额：</label>
			<input name="grtCreditsafe.creditLimit" id="creditLimit" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" onvaluechanged="checkValue()"/>
				
			<label>生效日期</label>
			<input name="grtCreditsafe.effectiveDate" required="true" class="nui-datepicker nui-form-input" id="effectiveDate" onvaluechanged="onShengXiaovaluechanged" allowinput="false"/>
		 
			<label>到期日期：</label>
			<input name="grtCreditsafe.endDate" required="true" class="nui-datepicker nui-form-input" id="dueDate" onvaluechanged="dueDatevalueChange" minDate="<%=GitUtil.getBusiDateStr()%>" allowinput="false"/>	
			
			<label>最低偿债比例（%）：</label>
			<input name="grtCreditsafe.minLossRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:6;" />
						
			<label>本次担保金额：</label>
			<input name="grtCreditsafe.guaranteeMoney" id="guaranteeMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" onvaluechanged="checkValue()"/>
		</div>
	</div>
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="saveBtn">保存</a>
		<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
			
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		// 业务申请ID
	    var applyId = "<%=request.getParameter("applyId") %>";
	    // 客户Id
	    var partyId = "<%=request.getParameter("partyId") %>";
	    //抵质押类型
	    var collType = "<%=request.getParameter("collType") %>";
	    
	    //信用保险ID
	    var suretyId="<%=request.getParameter("suretyId") %>";
	    
	    var view = "<%=request.getParameter("view") %>";
	    
	    
	   nui.get("grtGuaranteeBasic.partyId").setValue(partyId);
	   nui.get("grtGuaranteeBasic.guaranteeType").setValue(collType);
	   nui.get("bizGrtRel.applyId").setValue(applyId);
	   nui.get("bizGrtRel.suretyType").setValue(collType);
	   nui.get("grtCreditsafe.suretyId").setValue(suretyId);
		   
		   
	    if(view=="1"){
			form.setEnabled(false);
			nui.get("saveBtn").hide();
			//初始化信息
	    	initPage();
		}else if(view=="0"){
			//初始化信息
	    	initPage();
		}
	    
	    //初始信用保险默认值
		function initPage(){
		   	var json=nui.encode({"grtCreditsafe":{"suretyId":suretyId}});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.guaranMainManager.guaranteeApply.getTbGrtCreditsafe.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		var o=nui.decode(text);
					form.setData(o);
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			<!-- bug4802 -->
			var creditLimit=nui.get("creditLimit").getValue();//信用限额
			var guaranteeMoney=nui.get("guaranteeMoney").getValue();//本次担保金额
			if(Number(guaranteeMoney)>Number(creditLimit)){
				alert("本次担保金额应小于信用限额");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			if(view=="0"){
				//修改
				$.ajax({
			        url: "com.bos.grt.guaranMainManager.guaranteeApply.updateGuaranteeApplyTbGrtCreditsafe.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	nui.alert(text.msg);
			        	CloseWindow("ok");
			        	git.unmask();
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});
			}else{
				//新增
				git.mask();
				$.ajax({
			        url: "com.bos.grt.guaranMainManager.guaranteeApply.addGuaranteeApplyTbGrtCreditsafe.biz.ext",
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
		}
		
		function checkValue(){
			var creditLimit=nui.get("creditLimit").getValue();
			var guaranteeMoney=nui.get("guaranteeMoney").getValue();
			
			if(Number(guaranteeMoney)>Number(creditLimit)){
				alert("信用限额应大于本次担保金额");
				nui.get("creditLimit").setValue("");
				nui.get("guaranteeMoney").setValue("");
			}
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
		 * 生效日期值发生改变
		 */
		function onShengXiaovaluechanged(e){
			if(!CompareDueAndShengXiaoDate()&&nui.get("dueDate").getValue()!=""){//生效日期大于到期日期
				nui.alert("生效日期必须小于到期日期");
				nui.get("dueDate").setValue("");
	  		}
		}
	  
		/**
		 * 到期日期值发生改变
		 */
		function dueDatevalueChange(){
			if(!CompareDueAndShengXiaoDate()){//生效日期大于到期日期
				nui.alert("生效日期必须小于到期日期");
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
		 * 关闭
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
	</script>
</body>
</html>

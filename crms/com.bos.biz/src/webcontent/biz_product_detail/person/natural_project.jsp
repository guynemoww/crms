<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): pc
  - Date: 2016-05-10 11:01:34
  - Description:
-->
<head>
<title>合作项目额度信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<fieldset>
				<input id="tbBizXmxxApply.relateId" class="nui-hidden nui-form-input" name="tbBizXmxxApply.relateId"/>
				<input id="tbBizXmxxApply.createTime" class="nui-hidden nui-form-input" name="tbBizXmxxApply.createTime"/>
				<input id="tbBizXmxxApply.amountDetailId" class="nui-hidden nui-form-input" name="tbBizXmxxApply.amountDetailId" />
				<input id="tbCrdThirdPartyLimit.limitId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.limitId" />
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">客户编号：</label>	
					<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
					
					<label class="nui-form-label">客户名称：</label> 
					<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/> 
				</div>
				<div id="tbCrdThirdP" class="nui-dynpanel" columns="4">	
					<label class="nui-form-label">合作项目名称：</label>
					<input id="tbCrdThirdPartyLimit.itemName" name="tbCrdThirdPartyLimit.itemName" class="nui-buttonEdit nui-form-input" 
						allowInput="false" onbuttonclick="selectCoopItem"  style="width:80%;float:left" />
					
					<label>合作项目类型：</label>
					<input name="tbCrdThirdPartyLimit.itemType" id="tbCrdThirdPartyLimit.itemType"  data="data" valueField="dictID" 
						class="nui-text nui-form-input" dictTypeId="XD_SXYW0227" />
					
					<label class="nui-form-label">合作项目额度起始日：</label>
					<input id="tbCrdThirdPartyLimit.itemBeginDate" name="tbCrdThirdPartyLimit.itemBeginDate" 
						class="nui-text nui-form-input"  />

					<label class="nui-form-label">合作项目额度终止日：</label>
					<input id="tbCrdThirdPartyLimit.itemEndDate" name="tbCrdThirdPartyLimit.itemEndDate" 
						class="nui-text nui-form-input"  />
						
					<label>币种：</label>
					<input name="tbCrdThirdPartyLimit.currencyCd" id="tbCrdThirdPartyLimit.currencyCd" data="data" valueField="dictID" 
						class="nui-text nui-form-input" dictTypeId="CD000001" />
					
					<label>合作项目额度控制方式：</label>
					<input name="tbCrdThirdPartyLimit.crdControlType" id="tbCrdThirdPartyLimit.crdControlType" data="data" valueField="dictID" 
						class="nui-text nui-form-input" dictTypeId="crd_control_type"/>
					
					<label id="nui-form-label">合作项目额度：</label>
					<input id="tbCrdThirdPartyLimit.itemAmt" name="tbCrdThirdPartyLimit.itemAmt" 
						class="nui-text nui-form-input"  dataType="currency" />

					<label class="nui-form-label">最长贷款期限（年）：</label>
					<input id="tbCrdThirdPartyLimit.longestLoanTerm" name="tbCrdThirdPartyLimit.longestLoanTerm" class="nui-text nui-form-input"/>

					<label class="nui-form-label">最高贷款成数：</label> 
					<input id="tbCrdThirdPartyLimit.highestLoanRate" name="tbCrdThirdPartyLimit.highestLoanRate" class="nui-text nui-form-input"/>

					<label class="nui-form-label">划款方向：</label> 
					<input id="tbBizXmxxApply.payDirection" name="tbBizXmxxApply.payDirection" required="true" data="data"
						 class="mini-newcheckbox"  dictTypeId="XD_SXCD8027" />
					<label class="nui-form-label">结算账号：</label> 
					<input id="tbCrdThirdPartyLimit.settleAccno" name="tbCrdThirdPartyLimit.settleAccno"
						class="nui-text nui-form-input" />
						
					<label class="nui-form-label">保证金比例（%）：</label> 
					<input id="tbCrdThirdPartyLimit.depositPercentOne" name="tbCrdThirdPartyLimit.depositPercentOne" class="nui-text nui-form-input"/>

					<label class="nui-form-label">保证金账户归属机构：</label>
					<input id="tbCrdThirdPartyLimit.depositAccOrgOne" name="tbCrdThirdPartyLimit.depositAccOrgOne" class="nui-text nui-form-input" dictTypeId="acc_org"  />
					
					<label class="nui-form-label">保证金账号：</label>
					<input id="tbCrdThirdPartyLimit.depositAccnoOne" name="tbCrdThirdPartyLimit.depositAccnoOne" class="nui-text nui-form-input"  />
					
					<label class="nui-form-label">保证金户名：</label>
					<input id="tbCrdThirdPartyLimit.depositAccnameOne" name="tbCrdThirdPartyLimit.depositAccnameOne" class="nui-text nui-form-input"  />
			
					<label class="nui-form-label">主办行：</label>
					<input id="tbCrdThirdPartyLimit.mainOrgId" name="tbCrdThirdPartyLimit.mainOrgId" class="nui-text"  dictTypeId="org"/>
 				
					<label class="nui-form-label">经办人：</label> 
					<input id="tbCrdThirdPartyLimit.userNum" name="tbCrdThirdPartyLimit.userNum" class="nui-text nui-form-input"   dictTypeId="user"/>

					<label class="nui-form-label">经办日期：</label> 
					<input id="tbCrdThirdPartyLimit.dealDate" class="nui-text nui-form-input"
						name="tbCrdThirdPartyLimit.dealDate" dateFormat="yyyy-MM-dd" />
					<label class="nui-form-label">备注：</label> 
					<input id="tbCrdThirdPartyLimit.itemRemark" name="tbCrdThirdPartyLimit.itemRemark"
						class="nui-text nui-form-input"/>
 				</div>
			</fieldset>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnSave" iconCls="icon-save" onclick="update">保存</a>
		</div>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("#form1");
    var proFlag = "<%=request.getParameter("proFlag")%>" ;
	var partyId = "<%=request.getParameter("partyId")%>";
	var applyId = "<%=request.getParameter("applyId")%>";
	var productType = "<%=request.getParameter("productType")%>";
	if(proFlag!=1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
	  	var json = nui.encode({"partyId":partyId,"applyId":applyId});
		$.ajax({
			  url: "com.bos.bizInfo.person.queryNaturalProject.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	                 var o = nui.decode(mydata);
	                 form.setData(o);
					 nui.get("tbCrdThirdPartyLimit.itemName").setText(o.tbCrdThirdPartyLimit.itemName);
	          }, 
              error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
              }     
		});
	 }
	initForm();
	function update(){
			form.validate();
	        if (form.isValid()==false)
	        	return;    
	       nui.get("btnSave").setEnabled(false);  
	       var o = form.getData();
	       if(o.tbCrdThirdPartyLimit.limitId==null ||o.tbCrdThirdPartyLimit.limitId==''){
	       		nui.alert("请选择合作项目！")
	       		nui.get("btnSave").setEnabled(true);
	       		return;
	       }
	       /* 需求变更20160901 
	       	当保证金比例（%）不为0时，划款方向中“第三方保证金账号”必须勾选；
	       	当保证金比例（%）不为100时，划款方向不能仅勾选“第三方保证金账号”。
	       */
	       if(o.tbCrdThirdPartyLimit.depositPercentOne==''||o.tbCrdThirdPartyLimit.depositPercentOne==null|| 
	       		o.tbCrdThirdPartyLimit.depositPercentOne=='null'){
	       			nui.alert("保证金比例为空！");	
	       			nui.get("btnSave").setEnabled(true);
	       			return;
	       }else{
	       		var dpt1 = o.tbCrdThirdPartyLimit.depositPercentOne;
	       		var payDirection = o.tbBizXmxxApply.payDirection;
	       		//当保证金比例（%）不为0时，划款方向中“第三方保证金账号”必须勾选
	       		if(dpt1 != '0'&& payDirection.indexOf('3')==-1){
	       			nui.alert("	保证金比例不为0，划款方向中“第三方保证金账号”必须勾选");
	       			nui.get("btnSave").setEnabled(true);
	       			return;
	       		}
	       		//当保证金比例（%）不为100时，划款方向不能仅勾选“第三方保证金账号”
	       		if(dpt1 != '100' && dpt1 != '0' && payDirection.indexOf(',')==-1){
	       			nui.alert("	保证金比例（%）不为100，不能仅勾选“第三方保证金账号”");
	       			nui.get("btnSave").setEnabled(true);
	       			return;
	       		}
	       		//不能三个都勾上
	       		if(payDirection=='1,2,3'){
	       			nui.alert("划款方向不允许全部勾选");
	       			nui.get("btnSave").setEnabled(true);
	       			return;
	       		}
	       }
	       o.tbBizXmxxApply.amountDetailId = applyId;//存applyid
	       o.tbBizXmxxApply.projectId = o.tbCrdThirdPartyLimit.limitId;//保存关联键
	        var json = nui.encode(o);
	        $.ajax({
	            url: "com.bos.bizInfo.person.saveThirdProject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
	            		alert("保存成功！");           			
	            		
	            	}
	            	nui.get("btnSave").setEnabled(true);
	            	initForm();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	                nui.get("btnSave").setEnabled(true);
	            }
	        });
	        
		}
		
		//选合作项目额度
		function selectCoopItem(e) {
	        var btnEdit = this;
	        nui.open({
		        url: nui.context + "/biz/biz_product_detail/person/queryCoopItem.jsp?productType="+productType,
		        showMaxButton: true,
		        title: "选择合作项目",
		        width: 1200,
		        height: 600,
		        ondestroy: function (action) {   
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.getData();
		                data = nui.clone(data);
		                if (data) {
	                    	var limitId = data.LIMIT_ID;
	                    	var dsfPartyId = data.PARTY_ID;
	                    	var dsfJson = nui.encode({"partyId":dsfPartyId,"limitId":limitId});
	                    	$.ajax({
					            url: "com.bos.crdApply.crdApply.getThirdPartyCrdByLimitId.biz.ext",
					            type: 'POST',
					            data: dsfJson,
					            cache: false,
					            contentType:'text/json',
					            success: function (text) {
					            	if(text.msg){
					            		alert(text.msg);
					            	} else {    
					            		//防止第三方客户将贷款客户的客户信息覆盖 
					            		var form1 = new nui.Form("#tbCrdThirdP");
					            		form1.setData(text);
					            		nui.get("tbCrdThirdPartyLimit.itemName").setText(text.tbCrdThirdPartyLimit.itemName);
					            		nui.get("tbCrdThirdPartyLimit.limitId").setValue(text.tbCrdThirdPartyLimit.limitId);
					            		nui.get("party.partyNum").setValue(text.party.partyNum);
					            		nui.get("party.partyName").setValue(text.party.partyName);
					            	}
					            },
					            error: function (jqXHR, textStatus, errorThrown) {
					                nui.alert(jqXHR.responseText);
					                nui.get("btnSave").setEnabled(true);
					            }
					        });
		                }
		            }
		        }
		    }); 
		}
</script>
</body>
</html>
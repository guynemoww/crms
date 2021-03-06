<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-09 11:19:31
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>编辑业务申请下的保证</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<!-- 基本信息 -->
		<input type="hidden" name="tbGrtGuaranteeBasic" class="nui-hidden" />
		<input name="tbGrtGuaranteeBasic.suretyId" required="false" class="nui-hidden" vtype="maxLength:32" />
		
		<!-- 担保品信息 -->
		<input type="hidden" name="tbGrtSurety" class="nui-hidden" />
		<input name="tbGrtSurety.suretyId" required="true" class="nui-hidden" enabled="false" value="<%=request.getParameter("suretyId") %>" />
		<input name="tbGrtSurety.partyId" required="true" class="nui-hidden" id="tbGrtSurety.partyId" enabled="false" />
		<!-- 业务申请信息 -->
		<input type="hidden" name="tbBizRelation" class="nui-hidden" />
		<input name="tbBizRelation.bizCustType" required="false" value="<%=request.getParameter("bizCustType") %>" class="nui-hidden" />
		<input name="tbBizRelation.applyId" value="<%=request.getParameter("applyId") %>" required="false" class="nui-hidden" />
		<input name="tbBizRelation.relationId" value="<%=request.getParameter("relationId") %>" required="false" class="nui-hidden" />
		<input name="tbBizRelation.guaAmt" id="tbBizRelation.guaAmt" required="false" class="nui-hidden" />
		<input name="tbBizRelation.reType" id="tbBizRelation.reType" required="false" class="nui-hidden" value="03" />
		<input name="tbBizRelation.guaType" id="tbBizRelation.guaType" required="false" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>保证类型：</label>
			<input name="tbGrtGuaranteeBasic.guaranteeType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DBCD4001"
				id="tbGrtGuaranteeBasic.guaranteeType" enabled="false" />
	
			<label>保证人：</label>
			<input name="tbGrtGuaranteeBasic.partyName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:300"
				id="tbGrtGuaranteeBasic.partyName" onbuttonclick="chooiseParty" enabled="false" allowinput="false"/>
			
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
				id="tbGrtGuaranteeBasic.suretyAmt" dataType="currency" />
			
			<label>与债务人之间是否存在正相关关系：</label>
			<input name="tbGrtGuaranteeBasic.guaranteerDependence" id="tbGrtGuaranteeBasic.guaranteerDependence" required="true" class="nui-dictcombobox nui-form-input" 
				dictTypeId="XD_0002" vtype="maxLength:2" />
		
			<label>是否存在限制或解除或撤销条款：</label>
			<input name="tbGrtGuaranteeBasic.guaranteeIsLimit" id="tbGrtGuaranteeBasic.guaranteeIsLimit" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2"
				dictTypeId="XD_0002" />
			
			<label>经办用户：</label>
			<input name="tbGrtGuaranteeBasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:10"
				dictTypeId="user" enabled="false" />
				
			<label>更新人：</label>
			<input name="tbGrtGuaranteeBasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:10"
				id="tbGrtGuaranteeBasic.updateUser" dictTypeId="user" enabled="false" />
			
			<label>经办机构：</label>
			<input name="tbGrtGuaranteeBasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:9"
				dictTypeId="org" enabled="false" />
			
			<label>更新机构：</label>
			<input name="tbGrtGuaranteeBasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:9"
				id="tbGrtGuaranteeBasic.updateOrg" dictTypeId="org" enabled="false" />
				
			<label>创建时间：</label>
			<input name="tbGrtGuaranteeBasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10"
				dateformat="yyyy-MM-dd" enabled="false" />
	
			<label>更新时间：</label>
			<input name="tbGrtGuaranteeBasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10"
				id="tbGrtGuaranteeBasic.updateTime" dateformat="yyyy-MM-dd" enabled="false" />
		</div>
	</div>
					
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	</div>
			
	<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
	
		/**
		 * 初始化数据
		 */
		function initForm() {
			var json=nui.encode({"tbGrtGuaranteeBasic":{"suretyId":"<%=request.getParameter("suretyId") %>"}});
			var json2=nui.encode({"tbBizRelation":{"relationId":"<%=request.getParameter("relationId") %>","bizCustType":"<%=request.getParameter("bizCustType") %>",
				"applyId":"<%=request.getParameter("applyId") %>"}});
			var json3=nui.encode({"tbGrtSurety":{"suretyId":"<%=request.getParameter("suretyId") %>"}});
			var json4=json+json2+json3;
			git.mask();
			$.ajax({
		        url: "com.bos.grt.guaranMainManager.guaranteeApply.getGuaranteeApplyTbGrtGuaranteeBasic.biz.ext",
		        type: 'POST',
		        data: json4,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		form.setData(text);
		            	//基本信息客户名称赋文本显示值
		        		nui.get("tbGrtGuaranteeBasic.partyName").setText(text.tbGrtGuaranteeBasic.partyName);
		        		getGuaranteerEvalResult(text.tbGrtGuaranteeBasic.partyId);
		        		initGuaranteeQualified();
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		initForm();
	
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			//保证类型
			nui.get("tbBizRelation.guaType").setValue(nui.get("tbGrtGuaranteeBasic.guaranteeType").getValue());
			//保证金额
			nui.get("tbBizRelation.guaAmt").setValue(nui.get("tbGrtGuaranteeBasic.suretyAmt").getValue());
			
			var o=form.getData();
			var json=nui.encode(o);
			git.mask();
			$.ajax({
		        url: "com.bos.grt.guaranMainManager.guaranteeApply.updateGuaranteeApplyTbGrtGuaranteeBasic.biz.ext",
		        type: 'POST',	
		        data: json,	
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
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
		 * 选择保证人
		 */
		function chooiseParty(){
			nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_apply_guaranteer_chooise.jsp",
				title: "选择保证人", 
				width: 800, 
				height: 500,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					nui.get("tbGrtSurety.partyId").setValue(action[0]);
					nui.get("tbGrtGuaranteeBasic.partyName").setValue(action[2]);
					nui.get("tbGrtGuaranteeBasic.partyName").setText(action[2]);
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
		
		/**
		 * 关闭
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
	
		function closeok(){
			CloseWindow("ok");
		}
		
		//初始保证的合格信息默认值
		function initGuaranteeQualified(){
		    var guaranteeType = nui.get("tbGrtGuaranteeBasic.guaranteeType").getValue();
		    var guaranteerType = nui.get("tbGrtGuaranteeBasic.guaranteerType").getValue();
		    var o = {"inTbGrtGuaranteeBasic":{"guaranteeType":guaranteeType,"guaranteerType":guaranteerType}};
		    var json =nui.encode(o);
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
	</script>
</body>
</html>
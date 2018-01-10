<%@page import="com.bos.pub.DictContents"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:33:14
  - Description:
-->
<head>
<title>项目额度信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width: 99.5%; height: auto; overflow: hidden;">
		<input id="tbCrdThirdPartyLimit.thirdLimitId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.thirdLimitId" />
		<input id="tbCrdThirdPartyLimit.limitId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.limitId" />
		<input id="tbCrdThirdPartyLimit.projectId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.projectId" />
		<input id="tbCrdThirdPartyLimit.mainOrgId" name="tbCrdThirdPartyLimit.mainOrgId" class="nui-hidden nui-form-input" />
		<input id="corp.thirdCustTypeCd" name="corp.thirdCustTypeCd" class="nui-hidden nui-form-input" />

		<fieldset>

			<div class="nui-dynpanel" columns="4" id="table1">

				<label class="nui-form-label">客户编号：</label>
				<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum" />

				<label class="nui-form-label">客户名称：</label>
				<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName" />

				<label class="nui-form-label">合作项目类型：</label>
				<input id="tbCrdThirdPartyLimit.itemType" name="tbCrdThirdPartyLimit.itemType" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0227" required="true" onvaluechanged="selectItemType" />

				<label class="nui-form-label" id="item_name_label" name="item_name_label">合作项目名称：</label>
				<input id="tbCrdThirdPartyLimit.itemName" name="tbCrdThirdPartyLimit.itemName" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEstate" />

				<label class="nui-form-label">合作项目额度控制方式：</label>
				<input id="tbCrdThirdPartyLimit.crdControlType" name="tbCrdThirdPartyLimit.crdControlType" class="nui-dictcombobox nui-form-input" dictTypeId="crd_control_type" required="true" onvaluechanged="selectCrdControlType" />

				<label class="nui-form-label">币种：</label>
				<input id="tbCrdThirdPartyLimit.currencyCd" name="tbCrdThirdPartyLimit.currencyCd" class="nui-text nui-form-input" dictTypeId="CD000001" required="true" />

				<label class="nui-form-label">合作项目额度：</label>
				<input id="tbCrdThirdPartyLimit.itemAmt" name="tbCrdThirdPartyLimit.itemAmt" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,1000000000000" dataType="currency1" required="true" />

				<label class="nui-form-label">合作项目额度起始日：</label>
				<input id="tbCrdThirdPartyLimit.itemBeginDate" name="tbCrdThirdPartyLimit.itemBeginDate" class="nui-datepicker nui-form-input" required="true" />

				<label class="nui-form-label">合作项目额度终止日：</label>
				<input id="tbCrdThirdPartyLimit.itemEndDate" name="tbCrdThirdPartyLimit.itemEndDate" class="nui-datepicker nui-form-input" required="true" />

				<label class="nui-form-label">最长贷款期限（年）：</label>
				<input id="tbCrdThirdPartyLimit.longestLoanTerm" name="tbCrdThirdPartyLimit.longestLoanTerm" class="nui-textbox nui-form-input" vtype="int;maxLength:20;range:1,1000" required="true" />

				<label class="nui-form-label">最高贷款成数：</label>
				<input id="tbCrdThirdPartyLimit.highestLoanRate" name="tbCrdThirdPartyLimit.highestLoanRate" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,100" required="true" />

				<label class="nui-form-label">结算账号：</label>
				<input id="tbCrdThirdPartyLimit.settleAccno" name="tbCrdThirdPartyLimit.settleAccno" class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20" onvaluechanged="selectSettleAcc" />

				<label class="nui-form-label">保证金比例（%）：</label>
				<input id="tbCrdThirdPartyLimit.depositPercentOne" name="tbCrdThirdPartyLimit.depositPercentOne" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,100" required="true" onblur="selectAcc" />

				<label class="nui-form-label">保证金账户归属机构：</label>
				<input id="tbCrdThirdPartyLimit.depositAccOrgOne" name="tbCrdThirdPartyLimit.depositAccOrgOne" class="nui-dictcombobox nui-form-input" dictTypeId="acc_org" onvaluechanged="needValid" />

				<label class="nui-form-label">保证金账号：</label>
				<div>
					<input id="tbCrdThirdPartyLimit.depositAccnoOne" name="tbCrdThirdPartyLimit.depositAccnoOne" class="nui-textbox nui-form-input" style="width: 200px" vtype="float;maxLength:20" />
					<a class="nui-button" id="accNOValid" iconCls="icon-tip" onclick="accNOValid" style="display: none;">验证</a>
				</div>

				<label class="nui-form-label">保证金户名：</label>
				<input id="tbCrdThirdPartyLimit.depositAccnameOne" name="tbCrdThirdPartyLimit.depositAccnameOne" enabled="false" class="nui-textbox nui-form-input" />


				<label class="nui-form-label">主办行：</label>
				<input id="tbCrdThirdPartyLimit.orgNum" name="tbCrdThirdPartyLimit.orgNum" class="nui-text nui-form-input" dictTypeId="org" />

				<label class="nui-form-label">经办人：</label>
				<input id="tbCrdThirdPartyLimit.userNum" name="tbCrdThirdPartyLimit.userNum" class="nui-text nui-form-input" dictTypeId="user" />

				<label class="nui-form-label">经办日期：</label>
				<input id="tbCrdThirdPartyLimit.dealDate" name="tbCrdThirdPartyLimit.dealDate" class="nui-text nui-form-input" />

				<label class="nui-form-label">备注：</label>
				<input name="tbCrdThirdPartyLimit.itemRemark" id="tbCrdThirdPartyLimit.itemRemark" class="nui-textarea nui-form-input" />
			</div>
		</fieldset>

		<!-- 
			<fieldset>
		<legend><span>账号一</span></legend>
			
		<div class="nui-dynpanel" columns="4" id="table2">
			<label class="nui-form-label">保证金比例（%）：</label>
			<input id="tbCrdThirdPartyLimit.depositPercentOne" name="tbCrdThirdPartyLimit.depositPercentOne" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,100" required="true" onblur="selectAcc"/>
		
			<label class="nui-form-label">保证金账户归属机构一：</label>
			<input id="tbCrdThirdPartyLimit.depositAccOrgOne" name="tbCrdThirdPartyLimit.depositAccOrgOne" class="nui-dictcombobox nui-form-input" dictTypeId="acc_org"  onvaluechanged="selectAcc1"/>
			
			<label class="nui-form-label">保证金账号一：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnoOne" name="tbCrdThirdPartyLimit.depositAccnoOne" class="nui-textbox nui-form-input" vtype="float;maxLength:20"  onvaluechanged="selectAcc1"/>
			
			<label class="nui-form-label">保证金户名一：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnameOne" name="tbCrdThirdPartyLimit.depositAccnameOne" class="nui-textbox nui-form-input" />
	</div>
			</fieldset>
	<fieldset>
		<legend><span>账号二</span></legend>
			
		<div class="nui-dynpanel" columns="4" id="table3">
			<label class="nui-form-label">保证金比例（%）：</label>
			<input id="tbCrdThirdPartyLimit.depositPercentTwo" name="tbCrdThirdPartyLimit.depositPercentTwo" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,100" onblur="selectAcc"/>
			
			<label class="nui-form-label">保证金账户归属机构二：</label>
			<input id="tbCrdThirdPartyLimit.depositAccOrgTwo" name="tbCrdThirdPartyLimit.depositAccOrgTwo" class="nui-dictcombobox nui-form-input" dictTypeId="acc_org"  onvaluechanged="selectAcc2"/>
			
			<label class="nui-form-label">保证金账号二：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnoTwo" name="tbCrdThirdPartyLimit.depositAccnoTwo" class="nui-textbox nui-form-input"  vtype="float;maxLength:20" onvaluechanged="selectAcc2"/>
			
			<label class="nui-form-label">保证金户名二：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnameTwo" name="tbCrdThirdPartyLimit.depositAccnameTwo" class="nui-textbox nui-form-input" />
	</div>
	</filedset>
			
	<fieldset>
		<legend><span>账号三</span></legend>
			
		<div class="nui-dynpanel" columns="4" id="table4">
		    <label class="nui-form-label">保证金比例（%）：</label>
			<input id="tbCrdThirdPartyLimit.depositPercentThree" name="tbCrdThirdPartyLimit.depositPercentThree" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,100" onblur="selectAcc"/>
			
			<label class="nui-form-label">保证金账户归属机构三：</label>
			<input id="tbCrdThirdPartyLimit.depositAccOrgThree" name="tbCrdThirdPartyLimit.depositAccOrgThree" class="nui-dictcombobox nui-form-input" dictTypeId="acc_org"  onvaluechanged="selectAcc3"/>
			
			<label class="nui-form-label">保证金账号三：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnoThree" name="tbCrdThirdPartyLimit.depositAccnoThree" class="nui-textbox nui-form-input"  vtype="float;maxLength:20" onvaluechanged="selectAcc3"/>
			
			<label class="nui-form-label">保证金户名三：</label>
			<input id="tbCrdThirdPartyLimit.depositAccnameThree" name="tbCrdThirdPartyLimit.depositAccnameThree" class="nui-textbox nui-form-input" />
			</div>
	</filedset>

	<fieldset>
		<div class="nui-dynpanel" columns="4" id="table5">
			<label class="nui-form-label">主办行：</label>
			<input id="tbCrdThirdPartyLimit.orgNum" name="tbCrdThirdPartyLimit.orgNum" class="nui-text nui-form-input"   dictTypeId="org"/>
 			
			<label class="nui-form-label">经办人：</label>
			<input id="tbCrdThirdPartyLimit.userNum" name="tbCrdThirdPartyLimit.userNum" class="nui-text nui-form-input"   dictTypeId="user"/>
 			
 			<label class="nui-form-label">经办日期：</label>
			<input id="tbCrdThirdPartyLimit.dealDate" name="tbCrdThirdPartyLimit.dealDate" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">备注：</label>
			<input name="tbCrdThirdPartyLimit.itemRemark" id="tbCrdThirdPartyLimit.itemRemark"  class="nui-textarea nui-form-input" />
		</div>
	</fieldset>
 -->
		<div class="nui-toolbar" style="text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
			<a class="nui-button" id="crd_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var limitId =
	<%=JspUtil.getParameterHaveSign(request, "limitId")%>
		;//业务申请ID
		var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;//
		var proFlag =
	<%=JspUtil.getParameterHaveSign(request, "proFlag")%>
		;//
		initPage();

		var usedAmt = 0;
		var oldAcc = {};
		//初始化页面
		function initPage() {
			form.loading("数据加载中...");
			nui.get("tbCrdThirdPartyLimit.itemType").setEnabled(false);
			var json = nui.encode({
				"limitId" : limitId,
				"partyId" : partyId
			});
			$
					.ajax({
						url : "com.bos.crdApply.crdApply.getThirdPartyCrdByLimitId.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						cache : false,
						success : function(mydata) {
							form.unmask();
							var o = nui.decode(mydata);
							form.setData(o);
							usedAmt = o.credInfo.USED_AMT;
							initOldAcc(o);
							/*
							1	房屋按揭
							2	汽车按揭
							3	专项机械设备按揭
							4	大数金融
							 */
							if (nui.get("tbCrdThirdPartyLimit.itemType")
									.getValue() == "") {
								if ("2" == o.corp.thirdCustTypeCd) {
									nui.get("tbCrdThirdPartyLimit.itemType")
											.setValue('1');
									nui
											.get(
													"tbCrdThirdPartyLimit.crdControlType")
											.setValue('1');
								} else if ("3" == o.corp.thirdCustTypeCd) {
									nui.get("tbCrdThirdPartyLimit.itemType")
											.setValue('2');
									nui
											.get(
													"tbCrdThirdPartyLimit.crdControlType")
											.setValue('1');
								} else if ("4" == o.corp.thirdCustTypeCd) {
									nui.get("tbCrdThirdPartyLimit.itemType")
											.setValue('1');
									nui
											.get(
													"tbCrdThirdPartyLimit.crdControlType")
											.setValue('1');
								} else if ("5" == o.corp.thirdCustTypeCd) {
									nui.get("tbCrdThirdPartyLimit.itemType")
											.setValue('3');
									nui
											.get(
													"tbCrdThirdPartyLimit.crdControlType")
											.setValue('1');
								} else if ("6" == o.corp.thirdCustTypeCd) {
									nui.get("tbCrdThirdPartyLimit.itemType")
											.setValue('4');
									nui
											.get(
													"tbCrdThirdPartyLimit.crdControlType")
											.setValue('2');
								}
							}
							nui.get("tbCrdThirdPartyLimit.itemName").setValue(
									mydata.tbCrdThirdPartyLimit.itemName);
							nui.get("tbCrdThirdPartyLimit.itemName").setText(
									mydata.tbCrdThirdPartyLimit.itemName);
						}
					});
			//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			if ("1" != proFlag) {
				nui.get("crd_info_save").hide();
				form.setEnabled(false);
			}
		}
		function save() {

			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}

			var itemAmt = nui.get("tbCrdThirdPartyLimit.itemAmt").getValue();
			if (itemAmt < usedAmt) {
				nui.alert("调整额度不能小于已用额度" + usedAmt + "元！");
				return;

			}

			var depositAccnoOne = nui.get(
					"tbCrdThirdPartyLimit.depositAccnoOne").getValue();
			/* 	var depositAccnoTwo = nui.get("tbCrdThirdPartyLimit.depositAccnoTwo").getValue();
				var depositAccnoThree = nui.get("tbCrdThirdPartyLimit.depositAccnoThree").getValue();
				
				if (depositAccnoOne != "" && depositAccnoTwo != "" && depositAccnoOne == depositAccnoTwo){
					nui.alert("【保证金账号一】和【保证金账号二】重复！");
					return;
				}
				if (depositAccnoTwo != "" && depositAccnoThree != "" && depositAccnoTwo == depositAccnoThree){
					nui.alert("【保证金账号二】和【保证金账号三】重复！");
					return;
				}
				if (depositAccnoOne != "" && depositAccnoThree != "" && depositAccnoOne == depositAccnoThree){
					nui.alert("【保证金账号一】和【保证金账号三】重复！");
					return;
				} */

			var begin = nui.get("tbCrdThirdPartyLimit.itemBeginDate")
					.getValue();
			var end = nui.get("tbCrdThirdPartyLimit.itemEndDate").getValue();
			if (begin > end) {
				nui.alert("【额度起始日】不能大于【额度终止日】！");
				return;
			}

			if (nui.get("tbCrdThirdPartyLimit.itemType").getValue() == '1') {
				//selEstate();
			}
			saveCrdInfo();

		}

		//项目名称选择	
		function selectEstate(e) {
			// 01:法人；02：自然人
			var limitId = nui.get("tbCrdThirdPartyLimit.limitId").getValue();
			nui.open({
				url : nui.context
						+ "/crd/crd_apply/estate_info/estate_list.jsp?limitId="
						+ limitId + "&proFlag=" + proFlag + "&partyId="
						+ partyId,
				showMaxButton : true,
				showCloseButton : true,
				title : "选择项目信息",
				width : 800,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						if (data) {
							nui.get("tbCrdThirdPartyLimit.projectId").setValue(
									data.PROJECT_ID);
							nui.get("tbCrdThirdPartyLimit.itemName").setValue(
									data.PROJECT_NAME);
							nui.get("tbCrdThirdPartyLimit.itemName").setText(
									data.PROJECT_NAME);
						} else {
							nui.get("tbCrdThirdPartyLimit.projectId").setValue(
									"");
							nui.get("tbCrdThirdPartyLimit.itemName").setValue(
									"");
							nui.get("tbCrdThirdPartyLimit.itemName")
									.setText("");
						}
					}
				}
			});
			form.validate();
		}

		//项目类型
		function selectItemType() {
			var istc = nui.get("tbCrdThirdPartyLimit.itemType").getValue();
			var thirdCustTypeCd = nui.get("corp.thirdCustTypeCd").getValue();
			nui.get("tbCrdThirdPartyLimit.itemName").setValue("");
			nui.get("tbCrdThirdPartyLimit.itemName").setText("");
			if (istc == "1" && thirdCustTypeCd == "2") {//房屋按揭并且是房地产商
				$("#item_name_label").css("display", "block");//显示
				nui.get("tbCrdThirdPartyLimit.itemName").show();
				nui.get("tbCrdThirdPartyLimit.itemName").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.itemName").setEnabled(true);
				nui.get('table1').refreshTable();
			} else {
				if (istc == "4") {//大数金融
					nui.get("tbCrdThirdPartyLimit.highestLoanRate")
							.setRequired(false);
				}
				nui.get("tbCrdThirdPartyLimit.itemName").hide();
				$("#item_name_label").css("display", "none"); //隐藏
				nui.get("tbCrdThirdPartyLimit.itemName").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.itemName").setEnabled(false);
				nui.get('table1').refreshTable();
			}
			form.validate();
		}

		//额度控制方式
		function selectCrdControlType() {
			var istc = nui.get("tbCrdThirdPartyLimit.crdControlType")
					.getValue();
			if (istc == "0") {
				nui.get("tbCrdThirdPartyLimit.currencyCd").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.itemAmt").setRequired(false);
			} else {
				nui.get("tbCrdThirdPartyLimit.currencyCd").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.itemAmt").setRequired(true);
			}
			form.validate();
		}
		//校验账号
		function selectSettleAcc() {
			var accNo = nui.get("tbCrdThirdPartyLimit.settleAccno").getValue();
			var partyName = nui.get("party.partyName").getValue();
			var accName = validateAcc(accNo);
			if (accName != "failed" && accName != partyName) {
				nui.alert("结算账户名必须于客户名相同，请检查账号！");
				return "failed";
			}
			return accName;
		}

		function selectAcc() {
			selectAcc1();
			// 		selectAcc2();
			// 		selectAcc3();
			form.validate();
		}

		//校验账号
		function selectAcc1() {
			var accName = "";
			var per = nui.get("tbCrdThirdPartyLimit.depositPercentOne")
					.getValue();
			//账号一
			var accOrg = nui.get("tbCrdThirdPartyLimit.depositAccOrgOne")
					.getValue();
			var accNo = nui.get("tbCrdThirdPartyLimit.depositAccnoOne")
					.getValue();
			if (per == 0 || accOrg == "0") {//如果保证金比例为0，或者为其他银行
				nui.get("tbCrdThirdPartyLimit.depositAccOrgOne").setRequired(
						false);
				nui.get("tbCrdThirdPartyLimit.depositAccnoOne").setRequired(
						false);
				nui.get("tbCrdThirdPartyLimit.depositAccnameOne").setRequired(
						false);
				if ("1" == proFlag) {
					nui.get("tbCrdThirdPartyLimit.depositAccnameOne")
							.setEnabled(true);
				}
			} else {
				nui.get("tbCrdThirdPartyLimit.depositAccOrgOne").setRequired(
						true);
				if (accOrg == "1") {
					nui.get("tbCrdThirdPartyLimit.depositAccnoOne")
							.setRequired(true);
					nui.get("tbCrdThirdPartyLimit.depositAccnameOne")
							.setRequired(true);
					nui.get("tbCrdThirdPartyLimit.depositAccnameOne")
							.setEnabled(false);
					accName = validateAcc(accNo);
					if (accName != "failed") {
						nui.get("tbCrdThirdPartyLimit.depositAccnameOne")
								.setValue(accName);
					}
				}
			}
			form.validate();
			return accName;
		}

		/* function selectAcc2(){
			var accName = "";
			var per = nui.get("tbCrdThirdPartyLimit.depositPercentTwo").getValue();
			//账号二
			var accOrg = nui.get("tbCrdThirdPartyLimit.depositAccOrgTwo").getValue();
			var accNo = nui.get("tbCrdThirdPartyLimit.depositAccnoTwo").getValue();
			if(per==0){
				nui.get("tbCrdThirdPartyLimit.depositAccOrgTwo").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnoTwo").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setRequired(false);
				if("1" == proFlag){
					nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setEnabled(true);
				}
			}else if(per>0){//保证金比例>0，机构为绵阳银行
			}
			if(accOrg=="1"){
				nui.get("tbCrdThirdPartyLimit.depositAccnoTwo").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setEnabled(false);
				accName = validateAcc(accNo);
				if(accName != "failed"){
					nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setValue(accName);
				}
			}else{
				nui.get("tbCrdThirdPartyLimit.depositAccOrgTwo").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnoTwo").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setRequired(false);
				if("1" == proFlag){
					nui.get("tbCrdThirdPartyLimit.depositAccnameTwo").setEnabled(true);
				}
			}
			form.validate();
			return accName;
		}
		
		function selectAcc3(){
			var accName = "";
			var per = nui.get("tbCrdThirdPartyLimit.depositPercentThree").getValue();
			//账号三
			var accOrg = nui.get("tbCrdThirdPartyLimit.depositAccOrgThree").getValue();
			var accNo = nui.get("tbCrdThirdPartyLimit.depositAccnoThree").getValue();
			if(per==0){
				nui.get("tbCrdThirdPartyLimit.depositAccOrgThree").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnoThree").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setRequired(false);
				if("1" == proFlag){
				   nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setEnabled(true);
				}
			}else if(per>0){//保证金比例>0，机构为绵阳银行
			}
			if(accOrg=="1"){
				nui.get("tbCrdThirdPartyLimit.depositAccnoThree").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setEnabled(false);
				accName = validateAcc(accNo);
				if(accName != "failed"){
					nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setValue(accName);
				}
			}else{
				nui.get("tbCrdThirdPartyLimit.depositAccOrgThree").setRequired(true);
				nui.get("tbCrdThirdPartyLimit.depositAccnoThree").setRequired(false);
				nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setRequired(false);
				if("1" == proFlag){
				   nui.get("tbCrdThirdPartyLimit.depositAccnameThree").setEnabled(true);
				}
			}
			form.validate();
			return accName;
		} */

		function trim(str) {
			if (str != null & str != "") {
				return str.replace(/(^\s)|(\s*)/g, "").replace(/\s\s*$/, '');
			} else {
				return str;
			}
		}

		function selEstate() {
			var limitId = nui.get("tbCrdThirdPartyLimit.limitId").getValue();
			var json = nui.encode({
				"item" : {
					"limitId" : limitId,
					"_entity" : "com.bos.dataset.crd.TbCrdProjectEstateInfo"
				}
			});
			$.ajax({
				url : "com.bos.pub.crud.getItemBytemplate.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.exception) {
						nui.alert(text.exception.message);
						return;
					} else if (text.item) {
						if (text.item.projectName) {
							nui.get("tbCrdThirdPartyLimit.itemName").setValue(
									text.item.projectName);
							saveCrdInfo();
						} else {
							nui.alert("请输入合作项目名称！");
							return;
						}
					}
				},
				error : function() {
					alert("操作失败！");
				}
			});
		}

		function saveCrdInfo() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}

			if (needValid() == false) {
				return;
			}
			var o = form.getData();

			o.tbCrdThirdPartyLimit.partyId = partyId;
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.crdApply.crdApply.saveThirdPartyCrd.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg); //失败时后台直接返回出错信息
						return;
					}
					initOldAcc(o);
					alert("保存成功！");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function initOldAcc(o) {
			oldAcc.no = o.tbCrdThirdPartyLimit.depositAccnoOne;
			oldAcc.type = o.tbCrdThirdPartyLimit.depositAccOrgOne;
			oldAcc.name = o.tbCrdThirdPartyLimit.depositAccnameOne;
		}

		function accNOValid() {
			var accNo = nui.get("tbCrdThirdPartyLimit.depositAccnoOne")
					.getValue();
			var json = nui.encode({
				"acctInd" : accNo
			});
			form.loading("正在同步保证金户信息，请稍后");
			$
					.ajax({
						url : "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							debugger;
							if (text.code !=
	<%="\"" + DictContents.CORE_SUCCESS + "\""%>
		) {
								nui.alert(text.msg);
							} else {
								oldAccNo = accNo;
								nui
										.get(
												"tbCrdThirdPartyLimit.depositAccnameOne")
										.setValue(
												text.hxresponse.oxd052ResBody.custName);
								initOldAcc({
									"tbCrdThirdPartyLimit" : {
										"depositAccnoOne" : text.hxresponse.oxd052ResBody.custAcctNo,
										"depositAccOrgOne" : "2",
										"depositAccnameOne" : text.hxresponse.oxd052ResBody.custName
									}
								});
							}
							form.unmask();
						},
						error : function(jqXHR, textStatus, errorThrown) {
							form.unmask();
							nui.alert(jqXHR.responseText);
						}
					});
		}
		var init = true;
		function needValid() {
			debugger;
			var accNo = nui.get("tbCrdThirdPartyLimit.depositAccnoOne")
					.getValue();
			var accType = nui.get("tbCrdThirdPartyLimit.depositAccOrgOne")
					.getValue();
			var accName = nui.get("tbCrdThirdPartyLimit.depositAccnameOne")
					.getValue();
			if (!accType || "0" == accType) {
				nui.get("tbCrdThirdPartyLimit.depositAccnameOne").setEnabled(
						true);
				nui.get("accNOValid").hide();
			} else {
				nui.get("accNOValid").show();
				nui.get("tbCrdThirdPartyLimit.depositAccnameOne").setEnabled(
						false);
				if (init) {
					init = false;
					return;
				}
				//没有填账号
				if (!accType || accType == '0' || !accNo || accNo == "") {
					//没有原始数据
				} else if (!oldAcc.no && accNo != '' && accName != "") {
					//没有修改
				} else if (accNo && accType && accName && oldAcc.no == accNo
						&& oldAcc.type == accType && oldAcc.name == accName) {
				} else {
					nui.get("tbCrdThirdPartyLimit.depositAccnameOne").setValue(
							null);
					nui.alert("请在填写账户信息后点击[验证]按钮");
					return false;
				}
			}
			return true;
		}
	</script>
</body>
</html>
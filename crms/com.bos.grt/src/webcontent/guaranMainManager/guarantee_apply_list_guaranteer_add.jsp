<%@page import="java.math.BigDecimal"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn 
  - Date: 2016-05-11
-->

<head>
<title>新增业务申请下的保证--保证人</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width: 100%; height: auto; overflow: hidden;">
		<input name="bizGrtRel.relationId" id="bizGrtRel.relationId" class="nui-hidden" />
		<input name="bizGrtRel.applyId" class="nui-hidden" value="<%=request.getParameter("applyId")%>" />
		<input name="grtGuaranteeBasic.suretyId" id="grtGuaranteeBasic.suretyId" class="nui-hidden" />
		<input name="grtGuaranteeBasic.partyId" id="grtGuaranteeBasic.partyId" class="nui-hidden" />
		<input name="party.zhye" id="party.zhye" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>保证人名称：</label>
			<input name="party.partyId" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:60" id="party.partyId" onbuttonclick="chooiseParty" allowinput="false" />

			<label>证件类型：</label>
			<input id="party.certType" name="party.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" required="true" enabled="false" />

			<label>证件号码：</label>
			<input id="party.certCode" name="party.certCode" class="nui-textbox nui-form-input" required="true" enabled="false" />

			<label>信用等级：</label>
			<input id="party.pj" name="party.pj" class="nui-textbox nui-form-input" required="true" enabled="false" />

			<label>是否担保公司：</label>
			<input name="grtGuaranteeBasic.isGuaranteeCompany" id="grtGuaranteeBasic.isGuaranteeCompany" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" enabled="false" onvaluechanged="isCompany" />

			<label>币种：</label>
			<input name="grtGuaranteeBasic.currencyCd" id="grtGuaranteeBasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" enabled="false" dValue="CNY" />
		</div>

		<div id="isGuaranteeCompany" class="nui-dynpanel" columns="4">
			<label>担保范围：</label>
			<input name="grtGuaranteeBasic.guaranteeWay" id="grtGuaranteeBasic.guaranteeWay" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DBCD4006" />

			<label>担保放大倍数：</label>
			<input name="party.amplifyInd" class="nui-text nui-form-input" id="party.amplifyInd" required="true" enabled="false" />

			<label>担保机构实收资本：</label>
			<input name="party.guarantOrgReal" class="nui-text nui-form-input" id="party.guarantOrgReal" required="true" dataType="currency" enabled="false" />

			<label>已担保金额（元）：</label>
			<input name="party.useAmt" class="nui-textbox nui-form-input" id="party.useAmt" dataType="currency" enabled="false" />

			<label>可用保证金额（元）：</label>
			<input name="party.aviAmt" class="nui-textbox nui-form-input" id="party.aviAmt" dataType="currency" enabled="false" />
		</div>

		<div class="nui-dynpanel" columns="4">
			<label>申请担保金额（元）：</label>
			<input name="grtGuaranteeBasic.suretyAmt" id="grtGuaranteeBasic.suretyAmt" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" required="true" />
		</div>
		
		<label class="nui-hidden">保证担保形式：</label>
		<input name="grtGuaranteeBasic.guaranteeForm" id="grtGuaranteeBasic.guaranteeForm" required="false" class="nui-hidden" />
		
		<div class="nui-toolbar" style="border-bottom: 0; text-align: center;">
			<a class="nui-button" iconCls="icon-save" onclick="save()" id="saveBtn">保存</a>
			<a class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var view =
	<%=JspUtil.getParameterHaveSign(request, "view")%>
		;

		if (view == "1") {//查看
			form.setEnabled(false);
			nui.get("saveBtn").hide();
			initForm();
		} else if (view == "0") {//编辑
			initForm();
		}
		function initForm() {
			var suretyId =
	<%=JspUtil.getParameterHaveSign(request, "suretyId")%>
		;
			var json = nui.encode({
				"suretyId" : suretyId
			});
			git.mask();
			$
					.ajax({
						url : "com.bos.grt.guaranMainManager.guaranteeApply.getGuaranteeApplyTbGrtGuaranteer.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							//缺陷7309：modi by shangmf:增加text.msg!="交易成功",才能进入到else分支
							if (text.code&&text.code!="AAAAAAA") {
								return alert(text.msg);
							} else {
								var partyTypeCd = text.party.partyTypeCd;
								var o = nui.decode(text);
								debugger;
								form.setData(o);
								setGuaranteeInfo(o);
								setPartyInfo(o.grtGuaranteeBasic.partyId);
								git.unmask();
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							nui.alert(jqXHR.responseText);
						}
					});
			nui.get("grtGuaranteeBasic.suretyId").setValue(suretyId);
			nui.get("bizGrtRel.relationId").setValue(
	<%=JspUtil.getParameterHaveSign(request, "relationId")%>
		);
		}

		function setPartyInfo(partyId) {
			var json = nui.encode({
				"partyId" : partyId
			});
			$
					.ajax({
						url : "com.bos.bizProductDetail.bizProductDetail.getPartyByPartyId.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							if ("01" == text.party.partyTypeCd) {//对公客户
								setPartyKeyInfo(text.party.partyName,
										text.party.partyId, "202",
										text.corporation.orgRegisterCd);
							} else {
								setPartyKeyInfo(text.party.partyName,
										text.party.partyId,
										text.nature.certType,
										text.nature.certNum);
							}
						}
					});
		}

		function setPartyKeyInfo(partyName, partyId, certType, certNum) {
			nui.get("party.partyId").setText(partyName);
			nui.get("party.partyId").setValue(partyId);
			nui.get("party.certType").setValue(certType);
			nui.get("party.certCode").setValue(certNum);
		}

		function setGuaranteeInfo(o) {
			nui.get("party.pj").setValue(o.party.pj);
			if (o.party.isGuaranteeCompany == "1") {//担保公司
				nui.get("isGuaranteeCompany").show();
				nui.get("grtGuaranteeBasic.isGuaranteeCompany").setValue(1);
				nui.get("party.amplifyInd").setValue(o.party.amplifyInd);
				nui.get("party.guarantOrgReal")
						.setValue(o.party.guarantOrgReal);
				nui.get("party.zhye").setValue(o.party.zhye);
				nui.get("party.useAmt").setValue(o.party.useAmt);
				nui.get("party.aviAmt").setValue(
						Number(o.party.zhye) * Number(o.party.amplifyInd)
								- Number(o.party.useAmt));
			} else {
				nui.get("isGuaranteeCompany").hide();
				nui.get("grtGuaranteeBasic.isGuaranteeCompany").setValue(0);
				nui.get("grtGuaranteeBasic.guaranteeWay").setValue(null);
			}
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
			var isGuaranteeCompany = nui.get(
					"grtGuaranteeBasic.isGuaranteeCompany").getValue();
			var suretyAmt = nui.get("grtGuaranteeBasic.suretyAmt").getValue();
			var aviAmt = nui.get("party.aviAmt").getValue();
			if (isGuaranteeCompany == "1" && Number(suretyAmt) > Number(aviAmt)) {
				nui.alert("申请担保金额不能大于可用担保金额");
				return;
			}
			var o = form.getData();
			var json = nui.encode(o);
			git.mask();
			$
					.ajax({
						url : "com.bos.grt.guaranMainManager.guaranteeApply.addGuaranteeApplyTbGrtGuaranteer.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							nui.alert(text.msg);
							git.unmask();
							CloseWindow("ok")
						},
						error : function(jqXHR, textStatus, errorThrown) {
							nui.alert(jqXHR.responseText);
						}
					});
		}

		/**
		 * 选择保证人        2015.06.27选择客户的时候对partyId赋值 以便保证基本表插入相应的数据
		 */
		function chooiseParty() {
			var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;
			nui.open({
				url : nui.context
						+ "/grt/manage/chioseParty/querybzr.jsp?partyId="
						+ partyId,
				showMaxButton : true,
				title : "选择保证人",
				width : 1000,
				height : 500,
				ondestroy : function(action) {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					data = nui.clone(data);
					setPartyKeyInfo(data.partyName, data.partyId,
							data.certType, data.certNum);
					queryParty(data.partyId);
				}
			});
		}

		function queryParty(partyId) {
			var json = nui.encode({
				"partyId" : partyId
			});
			$.ajax({
				url : "com.bos.pub.common.getPartyInfo.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					var o = nui.decode(text);
					if(text.code==""||text.code==null){
						alert("调用核心接口获取账户信息发生异常,请联系管理员！");
						nui.get("saveBtn").setEnabled(false);
					}else if(text.code!="AAAAAAA"){
						alert("调用核心接口获取账户信息失败,失败原因："+text.msg);
						nui.get("saveBtn").setEnabled(false);
					}
						setGuaranteeInfo(o);
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function isCompany(e) {
			var com = nui.get("grtGuaranteeBasic.isGuaranteeCompany")
					.getValue();
			if (com == "0") {
				nui.get("grtGuaranteeBasic.guaranteeForm").setValue("02");
				nui.get("grtGuaranteeBasic.guaranteeForm").setEnabled(false);
			} else {
				nui.get("grtGuaranteeBasic.guaranteeForm").setEnabled(true);
			}
		}
	</script>
</body>
</html>

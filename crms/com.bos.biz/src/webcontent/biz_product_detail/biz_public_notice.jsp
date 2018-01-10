<!-- chenchuan 通知书文书送达公共页面 -->
<%@page pageEncoding="UTF-8"%>
<%
	String productType = request.getParameter("productType");

	String[][] configs = {
	//委托贷款
	{ "(委托人)", "(受托银行)", "(借款人)", "02005002", "01013001", "01013010", "02005010" } };
	String[] abcNames = { "(借款人)", "(贷款人)", "" };
	//暂时不用
	/* if (productType != null && !(productType = productType.trim()).isEmpty() && configs.length > 0) {
		for (int i = 0; i < configs.length; i++) {
			for (int j = 3; j < configs[i].length; j++) {
				if (productType.equals(configs[i][j])) {
					abcNames[0] = configs[i][0];
					abcNames[1] = configs[i][1];
					abcNames[2] = configs[i][2];
				}
			}
		}
	} */
%>
<fieldset class="fieldsetnew">
	<legend>
		<span>甲方<%=abcNames[0]%>送达地址和联系方式
		</span>
	</legend>
	<input id="notice.contractId" class="nui-hidden nui-form-input" name="notice.contractId" />
	<input id="notice.uuid" class="nui-hidden nui-form-input" name="notice.uuid" />
	<div class="nui-dynpanel" columns="4">

		<label class="nui-form-label">送达地址：</label>
		<input id="notice.aSendAddr" name="notice.aSendAddr" required="true" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label class="nui-form-label">邮编：</label>
		<input id="notice.aPostcode" name="notice.aPostcode" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:6;minLength:6" />

		<label class="nui-form-label">收件人：</label>
		<input id="notice.aReceiver" name="notice.aReceiver" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label class="nui-form-label">电话：</label>
		<input id="notice.aPhone" name="notice.aPhone" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label class="nui-form-label">电子邮箱：</label>
		<input id="notice.aEmail" name="notice.aEmail" required="false" class="nui-textbox nui-form-input" vtype="email;maxLength:100" />

		<label class="nui-form-label">其他方式：</label>
		<input id="notice.aOther" name="notice.aOther" class="nui-textbox nui-form-input" required="false" vtype="maxLength:500" />

	</div>
</fieldset>

<fieldset class="fieldsetnew">
	<legend>
		<span>乙方<%=abcNames[1]%>送达地址和联系方式
		</span>
	</legend>
	<div class="nui-dynpanel" columns="4">

		<label class="nui-form-label">送达地址：</label>
		<input id="notice.bSendAddr" name="notice.bSendAddr" required="true" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label class="nui-form-label">邮编：</label>
		<input id="notice.bPostcode" name="notice.bPostcode" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:6;minLength:6" />

		<label class="nui-form-label">收件人：</label>
		<input id="notice.bReceiver" name="notice.bReceiver" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label class="nui-form-label">电话：</label>
		<input id="notice.bPhone" name="notice.bPhone" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label class="nui-form-label">电子邮箱：</label>
		<input id="notice.bEmail" name="notice.bEmail" required="false" class="nui-textbox nui-form-input" vtype="email;maxLength:100" />

	</div>
</fieldset>

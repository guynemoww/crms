<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div class="nui-dynpanel" columns="4">
				<label class="nui-form-label">客户名称：</label>
				<input id="party.partyName" name="party.partyName" class="nui-textbox nui-form-input" readonly="true" Enabled="false"/>
				
				<label class="nui-form-label">财报日期：</label>
				<input id="natural.certType" name="natural.certType" class="nui-textbox nui-form-input" readonly="true" Enabled="false"/>
				
				<label class="nui-form-label">销售收入：</label>
				<input id="natural.certNum" name="natural.certNum"  class="nui-textbox nui-form-input"  readonly="true" Enabled="false" />
				
				<label class="nui-form-label">利润总额：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">净利润：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">净现金流量：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">应付款：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">短期借款：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">长期借款：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">总资产：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">总负债：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">流动资产：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">流动负债：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">流动资产：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">净资产：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">资产负债率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">流动比率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">速动比率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">应收账款周转率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">存货周转率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">总资产收益率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">销售净利率：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
				
				<label class="nui-form-label">或有负债：</label>
				<input id="party.isPotentialCust" name="party.isPotentialCust" class="nui-textbox nui-form-input" readonly="true" Enabled="false" />
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			//var form = new nui.Form("#form");
			function queryInit(){
				var o = form.getData();//逻辑流必须返回total
				grid.load(o);
			}
				git.unmask();
			//queryInit();

	 	</script>
	</body>
</html>

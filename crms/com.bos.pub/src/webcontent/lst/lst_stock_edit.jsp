<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<head>
</head>
<body>
	<%
		String actionType = request.getParameter("actionType");
		boolean enabled = "insert".equals(actionType);
	%>
	<div id="form1" style="width: 100%; height: auto; overflow: hidden; text-align: left">
		<!-- hidden域 -->
		<input class="nui-hidden" id="lstStock.rId" name="lstStock.rId" />
		<input class="nui-hidden" id="lstStock.partyName" name="lstStock.partyName" />
		<input class="nui-hidden" id="lstStock.certType" name="lstStock.certType" />
		<input class="nui-hidden" id="lstStock.certNum" name="lstStock.certNum" />
		<input class="nui-hidden" id="actionType" name="actionType" value="<%=actionType%>" />
		<table style="margin: 30px">
			<tr>
				<td style="width: 100px">
					<label>股东客户编号：</label>
				</td>
				<td style="width: 250px">
					<input id="lstStock.partyNum" name="lstStock.partyNum" class="nui-buttonedit" required="true" allowInput="false" enabled="<%=enabled%>" onbuttonclick="<%=enabled ? "onButtonEdit" : ""%>" textName="test2" />
				</td>
				<td style="width: 100px">
					<label>股东客户名称：</label>
				</td>
				<td style="width: 250px">
					<input id="lstStock.custName" name="lstStock.custName" required="true" enabled="false" class="nui-textbox" />
				</td>
			</tr>
			<tr>
				<td>
					<label>股东股权性质：</label>
				</td>
				<td>
					<input id="lstStock.stockNature" name="lstStock.stockNature" class="nui-dictcombobox" required="true" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD0303" />
				</td>
				<td>
					<label>董监事单位情况：</label>
				</td>
				<td>
					<input id="lstStock.direcsitu" name="lstStock.direcsitu" class="nui-dictcombobox" required="true" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD0301" />
				</td>
			</tr>
			<tr>
				<td>
					<label>董监事人员姓名：</label>
				</td>
				<td>
					<input id="lstStock.directorName" name="lstStock.directorName" class="nui-textbox" required="true" />
				</td>
				<td>
					<label>董监事人员职务：</label>
				</td>
				<td>
					<input id="lstStock.drectorPost" name="lstStock.drectorPost" class="nui-dictcombobox" required="true" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD0302" />
				</td>
			</tr>
			<tr>
				<td>
					<label>持股数量(万股)：</label>
				</td>
				<td>
					<input id="lstStock.stockNum" name="lstStock.stockNum" vtype="float;maxLength:20" required="true" class="nui-textbox" />
				</td>
				<td>
					<label>持股比例(%)：</label>
				</td>
				<td>
					<input id="lstStock.stockRate" name="lstStock.stockRate" class="nui-textbox" required="true" vtype="float;maxLength:11;range:0,100" />
				</td>
			</tr>
			<tr>
				<td>
					<label>股权净值(万元)：</label>
				</td>
				<td>
					<input id="lstStock.stockNetval" name="lstStock.stockNetval" vtype="float;maxLength:20" required="true" class="nui-textbox" />
				</td>

			</tr>
			<tr>
				<td>
					<label columnsSpan="all">不良贷款情况说明：</label>
				</td>
				<td colspan="3">
					<input columnsSpan="all" id="lstStock.note" name="lstStock.note" class="nui-textarea" vtype="maxLength:800" style="width: 100%; column-span: all" />
				</td>
			</tr>
			<tr>
				<td>
					<label>最后修改机构：</label>
				</td>
				<td>
					<input id="lstStock.lastchanBankid" name="lstStock.lastchanBankid" enabled="false" class="nui-textbox" />
				</td>
				<td>
					<label>最后修改人：</label>
				</td>
				<td>
					<input id="lstStock.lastchanPerson" name="lstStock.lastchanPerson" enabled="false" class="nui-textbox" />
				</td>

			</tr>
			<tr>
				<td>
					<label>最后修改时间：</label>
				</td>
				<td>
					<input id="lstStock.lastchanDate" name="lstStock.lastchanDate" enabled="false" class="nui-textbox" />
				</td>
			</tr>
		</table>
		<div class="nui-toolbar" style="text-align: center; padding-top: 8px; padding-bottom: 8px;" borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-save" onclick="onOk()">确定</a>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
		</div>

		<script type="text/javascript">
			nui.parse();
			var form = new nui.Form("#form1");

			var rId =
		<%=JspUtil.getParameterHaveSign(request, "rId")%>
			;
			if (rId != '' && rId != null && rId != 'null' && rId != 'undefined') {
				loadData(rId);
			}

			function onButtonEdit() {
				nui.open({
					url : nui.context + "/pub/lst/lst_stock_select_party.jsp",
					title : "选择客户",
					width : 800,
					height : 580,
					allowResize : false,
					ondestroy : function(action) {
						if (action == "ok") {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							data = nui.clone(data); //必须
							debugger;
							if (data) {
								nui.get("lstStock.rId").setValue(data.partyId);
								nui.get("lstStock.partyName").setValue(
										data.partyName);
								nui.get("lstStock.certType").setValue(
										data.certType);
								nui.get("lstStock.certNum").setValue(
										data.certNum);
								nui.get("lstStock.partyNum").setValue(
										data.partyNum);
								nui.get("lstStock.partyNum").setText(
										data.partyNum);
								nui.get("lstStock.custName").setValue(
										data.partyName);
							}
						}
					}
				});
			}

			function saveData() {
				var form = new nui.Form("#form1");
				form.setChanged(false);
				//保存
				var urlStr = "com.bos.pub.lst.lst.addOrUpdateTbLstStock.biz.ext";
				form.validate();
				if (form.isValid() == false)
					return;
				var data = form.getData(false, true);
				var arr = [ {
					"actionType" :
		<%=JspUtil.getParameterHaveSign(request, actionType)%>
			} ];
				arr.push(data);
				var json = nui.encode(data);
				//console.info("json=" + json);
				$.ajax({
					url : urlStr,
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						var returnJson = nui.decode(text);
						if (returnJson.msg == null) {
							CloseWindow("saveSuccess");
						} else {
							nui.alert("保存失败:" + text.msg);
						}
					}
				});
			}

			function loadData(rId) {
				var json = nui.encode({
					"lstStock" : {
						"rId" : rId
					}
				});
				$.ajax({
					url : "com.bos.pub.lst.lst.getLstStock.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(data) {
						//console.info(nui.encode(data));
						if (data.msg) {
							nui.alert(data.msg);
						} else {
							form.setData(data);
							nui.get("lstStock.partyNum").setText(
									data.lstStock.partyNum);
							form.setChanged(false);
						}
					}
				});
			}

			//关闭窗口
			function CloseWindow(action) {
				if (action == "close" && form.isChanged()) {
					if (confirm("数据被修改了，是否先保存？")) {
						saveData();
					}
				}
				if (window.CloseOwnerWindow)
					return window.CloseOwnerWindow(action);
				else
					window.close();
			}

			//确定保存或更新
			function onOk() {
				saveData();
			}

			//取消
			function onCancel() {
				CloseWindow("cancel");
			}
		</script>
</body>
</html>

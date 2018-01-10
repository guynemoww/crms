<%@page import="com.bos.pub.DictContents"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-27 18:52:29
  - Description:
-->
<head>
<title>意见提交</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>


	<%--	<form id="uploadWordForm1" action="com.bos.biz.ExportManager.flow?_eosFlowAction=upload&bizId=<%=request.getParameter("applyId") %>" method="post" enctype="multipart/form-data">
	<legend>
		<span>协办客户经理：</span>
	</legend>
	<table border="0" width="100%" class="nui-form-table">
		<tr>
			<td align="left" class="nui-form-label">
		    	<a class="nui-button"  iconCls="icon-download" onclick="downloadWord" id="biz_advice_up"/>调查报告模板下载</a>
		    	<a class="nui-button"  iconCls="icon-upload" onclick="uploadWord" id="biz_advice_down"/>调查报告及相关文档上传</a>
		    </td>
		</tr>
	</table>
	</form>--%>

	<div id="form2">
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">协办客户经理:</label>
			<input property="editor" id="sugReport" class="nui-buttonEdit" style="width: 100%;" allowInput="false" onbuttonclick="selectUser()" />

			<!-- <label class="nui-form-label">第一责任人：</label>
			<input property="editor" id="firstResponse" class="nui-buttonEdit" style="width: 100%;" allowInput="false" onbuttonclick="selectUser1()" />
 -->
		</div>
	</div>
	<%--<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="sub">提交</a>
	</div>--%>

	<script type="text/javascript">
		nui.parse();
		var proFlag =<%=JspUtil.getParameterHaveSign(request, "proFlag")%>;
		var applyId =<%=JspUtil.getParameterHaveSign(request, "applyId")%>;
		var orgNum =<%=JspUtil.getParameterHaveSign(request, "orgNum")%>;
		var form = new nui.Form("#form2");
		if ("1" != proFlag) {
			form.setEnabled(false);
		}
		initPage();
		function initPage() {
			var json = nui.encode({
				"bizId" : applyId
			});
			$.ajax({
					url : "com.bos.bizInfo.adviceAndFile.getSurveyreportSug.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					cache : false,
					success : function(mydata) {
						var o = nui.decode(mydata);
						nui.get("sugReport").setText(o.sugReport.xbName);
						//nui.get("firstResponse").setText(o.sugReport.firstResponseName);
					}
				});
		}

		function selectUser() {
			nui.open({
						url : nui.context
								+ "/pub/user/select_user.jsp?clear=0&orgMode=random&userMode=manager&orgNum="+orgNum,
						showMaxButton : true,
						title : "协办客户经理",
						width : 800,
						height : 600,
						ondestroy : function(action) {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							save("1", data);
						}
					});
		}

		function selectUser1() {
			nui.open({
				url : nui.context + "/pub/user/select_user.jsp?clear=0&orgNum="
						+ orgNum + "&userMode=manager",
				showMaxButton : true,
				title : "第一责任人",
				width : 800,
				height : 600,
				ondestroy : function(action) {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					save("2", data);
				}
			});
		}

		function save(selectType, row) {
			if (!row) {
				return;
			}
			var json;
			if ("1" == selectType) {
				json = nui.encode({
					"sugReport" : {
						"bizId" : applyId,
						"xbNum" : row.userNum,
						"xbName" : row.userName,
						"xbOrgNum" : row.orgNum,
						"xbOrgName" : row.orgName
					}
				});
			} else if ("2" == selectType) {
				json = nui.encode({
					"sugReport" : {
						"bizId" : applyId,
						"firstResponseNum" : row.userNum,
						"firstResponseName" : row.userName
					}
				});
			}
			$.ajax({
						url : "com.bos.bizInfo.adviceAndFile.saveSurveyreportSug.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						cache : false,
						success : function(mydata) {
							//debugger;
							if ("0" != mydata.msg) {
								alert(mydata.msg);
								return;
							}
							initPage();
						}
					});
		}
	</script>
</body>
</html>
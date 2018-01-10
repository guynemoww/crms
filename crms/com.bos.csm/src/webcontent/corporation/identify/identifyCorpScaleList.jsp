<%@page import="com.bos.pub.web.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-06-29 17:13:15
  - Description:
-->
<%@include file="/common/nui/common.jsp"%>
<head>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-addnew" onclick="createIdentifyFlow">企业规模认定</a>

			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		<input name="item.partyId" class="nui-hidden" />
		<input name="sqlName" class="nui-hidden" value="com.bos.csm.corporation.corporation.getIdentifyCorpScaleList" />
	</div>
	<div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
			<div property="columns">
				<div field="CREATE_DATE" headerAlign="center">创建时间</div>
				<div field="STATUS" headerAlign="center" align="center" allowSort="true" dictTypeId="XD_WFM10001">状态</div>
				<div field="SCALE_CODE" headerAlign="center" allowSort="true" align="center" dictTypeId="CDKH0027">企业规模</div>
				<div field="OLD_SCALE_CODE" headerAlign="center" align="center" allowSort="true" dictTypeId="CDKH0027">认定前企业规模</div>
				<div field="SALEROOM" headerAlign="center" align="right" allowSort="true" dataType="currency">销售额(万元)</div>
				<div field="ASSETS" headerAlign="center" align="right" allowSort="true" dataType="currency">资产总额(万元)</div>
				<div field="EMP_NUM" headerAlign="center" align="right" allowSort="true" dataType="int">员工人数</div>
				<!-- <div field="TRADE_TYPE" headerAlign="center" align="center" allowSort="true">行业类型</div> -->
				<div field="ORG_NUM" headerAlign="center" align="center" dictTypeId="org">经办机构</div>
				<div field="USER_NUM" headerAlign="center" align="center" dictTypeId="user">经办人</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;
		query();
		function query() {
			debugger;
			var data = form.getData();
			if (partyId) {
				data.item.partyId = partyId;
			} else {
				return;
			}
			grid.load(data);
		}

		function reset() {
			var data = grid.getData();
			debugger;
			form.reset();
		}
		function createIdentifyFlow() {
			nui.confirm("是否确定创建企业规模认定流程？", "询问", function(action) {
				if (action != 'ok') {
					return;
				}
				_createIdentifyFlow();
			});

		}

		function _createIdentifyFlow() {
			if (!partyId) {
				alert("没有客户编号无法创建流程");
				return;
			}
			//规模认定时，判断客户是否已保存行业类别
			var json1 = {
				"partyId" : partyId
			};
			msg = exeRule("XFE_0002", "1", json1);
			if (null != msg && '' != msg) {
				nui.alert(msg);
				return;
			}
			msg = exeRule("RCSM_1007", "1", json1);
			if (null != msg && '' != msg) {
				nui.alert(msg);
				return;
			}
			msg = exeRule("RCSM_1014", "1", json1);
			debugger;
			if (null != msg && '' != msg) {
				nui.alert(msg);
				return;
			}
			var json = nui.encode({
				"partyId" : partyId
			});
			$
					.ajax({
						url : "com.bos.csm.corporation.corporation.createIdentifyCorpScale.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							if (data.scale && !data.scale.msg) {
								nui
										.open({
											url : nui.context
													+ "/csm/corporation/identify/identifyCorpScaleTree.jsp?bizId="
													+ data.scale.id
													+ "&wflow=2&edit=1&setValue=true&processInstId="
													+ data.scale.processId,
											title : "企业规模认定 ",
											width : 1024,
											height : 720,
											ondestroy: function(){
												query();
											}
										});
							} else {
								alert(data.scale.msg);
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							nui.alert(jqXHR.responseText);
						}
					});
		}
	</script>
</body>
</html>
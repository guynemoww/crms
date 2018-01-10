<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<fieldset>
  	<legend>
   		<span>预警客户信息</span>
    </legend>
<div id="form1"  style="width:99.5%;height:auto;overflow:hidden;margin-top: 7px;">
	<input type="hidden" name="item" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" style="text-align:center;">
	
		<label>客户编号</label>
		<input name="item.partyNum" required="false" class="nui-text nui-form-input"  style="width:100%;"  />
		
		<label>客户名称</label>
		<input name="item.partyName" required="false"  class="nui-text nui-form-input" style="width:100%;"  />
		
		<label>授信额度</label>
		<input name="item.creditAmt" required="false" class="nui-text nui-form-input" dataType="currency" style="width:100%;"  />

		<label>授信余额</label>
		<input name="item.balance" required="false" class="nui-text nui-form-input" dataType="currency" style="width:100%;" />

		<label>客户等级</label>
		<input name="item.creditRatingCd" required="false" class="nui-text nui-form-input"  style="width:100%;" />

		<label>分类</label>
		<input id="clsResult" name="item.clsResult" required="false" class="nui-text nui-form-input" dictTypeId="XD_FLCD0001"  style="width:100%;" />

		<label id="tWarnLevel">预警级别</label>
		<input id="warnLevel" name="item.warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJJB0001"  style="width:100%;" />

		<div id="tConfirmDate">认定日期</div>
		<input id="confirmDate" name="item.confirmDate" required="false" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />

	</div>
</div>
</fieldset>

<fieldset>
			<legend>
				<span>已有预警信号列表</span>
			</legend>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.ews.warnInfo.queryEwsInfo.biz.ext" 
	dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" pageSize="10" multiSelect="false" sortMode="client">
   <div property="columns">
     <div type="indexcolumn">序号</div>
     <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
     <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true">预警信号类别</div>
     <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001">预警信号来源</div>
     <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号认定日期</div>
     <div field="closeDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号关闭日期</div>
     <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002">预警信号状态</div>
     <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
   </div>
</div>
</fieldset>

</body>

<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		form.setEnabled(false);                                 //表单禁用
		var corpid = "<%=request.getParameter("corpid")%>";    //客户编号
		var type = "<%=request.getParameter("type")%>";        //参与人类型
		var rule ="<%=request.getParameter("rule")%>"; //判断权限
		initForm();

	function initForm() {
		var json = nui.encode({
			partyId : corpid,
			type : type
		});
		$.ajax({
			url : "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				var text = nui.decode(text);
				form.setData(text);
				grid.load({
					partyId : corpid,
					status : "2"
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);

			}
		});
		git.unmask();
	}
</script>
</html>

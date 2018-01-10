<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 10:38:16
  - Description:
-->
<head>
<title>业务批复信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<fieldset><legend> <span>客户业务列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-toolbar">
			<a class="nui-button" id="btnView" onclick="view">查看详情</a>
		</div>
		<div id="grid" class="nui-datagrid"  sortMode="client"
		    url="com.bos.bizApply.bizApply.getHisList.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" allowSort="true" width="" headerAlign="center">客户编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="APPROVAL_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <!-- <div field="BIZ_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div> -->
		        <div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="APPLY_MODE_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0003">申报模式</div>
		        <div field="BIZ_HAPPEN_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0001">业务发生方式</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center">批复起期</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">批复止期</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center"  dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center"dataType="currency">批复金额（元）</div>
		        <div field="CREDIT_USED" allowSort="true" width="" headerAlign="center"dataType="currency">已用金额（元）</div>
		        <div field="CREDIT_AVI" allowSort="true" width="" headerAlign="center"dataType="currency">可用金额（元）</div>
		        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
		        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">经办机构</div>
		    </div>
		</div>
	</div>
</fieldset>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
	}
	function view(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复");
			return false;
		}
		nui.open({
			url:nui.context + "/biz/biz_info/biz_tree.jsp?applyId="+row.APPLY_ID+"&proFlag=-1&processInstId=1",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            //	top.bizConWin = this;
            //	initPage();
            //	nui.get("creat").setEnabled(true);
            }
		});
	}
	//对外查看信息
	function bizView(bizNum){
		var bizFlag = bizNum.substring(0,3);
		var json = nui.encode({"bizNum":bizNum,"bizFlag":bizFlag});
		var jspName;
		$.ajax({
            url: "com.bos.bizPro.BizView.getBizView.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
				var o = nui.decode(mydata);
				if(""==o.bizId || null==o.bizId){
					return;
				}
				if("BIZ" == bizFlag){
					jspName="/biz/biz_info/biz_tree.jsp?applyId="+o.bizId+"&processInstId=0&proFlag=-1";
				}
				if("CON" == bizFlag){
					jspName="/crt/con_info/con_tree.jsp?contractId="+o.bizId+"&processInstId=0&proFlag=-1";
				}
				if("LOA" == bizFlag){
					jspName="/pay/payout_info/pay_tree.jsp?loanId="+o.bizId+"&processInstId=0&proFlag=-1";
				}
				nui.open({
					url:nui.context + jspName,
					showMaxButton:true,
					title:"提示：可点击最大化按钮放大此窗口",
					width:"800",
		            height:"500",
		            ondestroy: function(e) {
		            }
				});
			}
    	});
	}
</script>
</body>
</html>
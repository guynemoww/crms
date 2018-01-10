<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-17 12:42:24
  - Description:
-->
<head>
<title>违约记录</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.natural.natural.illegalList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	     url="com.bos.csm.pub.ibatis.getItemDataObject.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="PARTY_NAME" allowSort="true" headerAlign="center">客户名称</div>
	        <div field="CONTRACT_NUM" allowSort="true" headerAlign="center">合同编号</div>
	        <div field="SUMMARY_NUM" allowSort="true"  headerAlign="center" >借据编号</div>
	        <div field="YQ_AMT" allowSort="true" headerAlign="center"dataType="currency">逾期/垫款金额</div>
	        <div field="OVERDUE_DATE" allowSort="true" headerAlign="center" >逾期/垫款日期</div>
	        <div field="REPAY_DATE" allowSort="true" headerAlign="center" >归还日期</div>
	        <div field="REMARK" allowSort="true" headerAlign="center" >备注</div>
	       	<div field="USER_NUM" allowSort="true" headerAlign="center"dictTypeId="user" >经办人</div>
	        <div field="ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org" >经办机构</div>
	     </div>
	</div>
<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
     }
     init();
	 grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['CONTRACT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].CONTRACT_NUM+ '\');">'+e.data[i]['CONTRACT_NUM']+'</a>';
				e.data[i]['SUMMARY_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].SUMMARY_NUM+ '\');">'+e.data[i]['SUMMARY_NUM']+'</a>';
			}
		});     
     
     
</script>
	
	
</body>
</html>
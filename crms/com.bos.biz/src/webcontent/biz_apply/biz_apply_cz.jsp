<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 11:54:45
  - Description:
-->
<head>
<title>重组</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务重组列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="btnCreate" onclick="add">复议</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyFyBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="true" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="BIZ_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002" >业务性质</div>
		        <div field="APPLY_DATE" allowSort="true" width="" headerAlign="center">申请日期</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001" >币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">申请金额</div>
		        <div field="APPROVE_CONCLUSION" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_WFCD0002">审批结论</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center">审批日期</div>
		    </div>
		</div>
	</div>
</fieldset>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>","bizNum":o.map.bizNum,"approveConclusion":"2"});
	}
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔复议");
			return false;
		}
		nui.get("btnCreate").setEnabled(false);
		
		var json=nui.encode({"applyId":row.APPLY_ID});
		$.ajax({
	        url: "com.bos.conApply.conApply.createSxxyProcess.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
				//git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConCreditInfo.contractId+"&contractType=01",parent);
				git.go(nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+row.APPLY_ID,parent);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
</script>
</body>
</html>
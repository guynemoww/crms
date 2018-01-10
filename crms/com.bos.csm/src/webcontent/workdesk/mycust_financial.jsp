<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-9 12:42:24
  - Description:
-->
<head>
<title>我的客户-同业客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div >
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName"  class="nui-textbox nui-form-input"  />
	
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input"/>
			
			<label>营业执照：</label>
			<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" />
			
			<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd"  class="nui-textbox nui-form-input"/>
			
			<label>中征码：</label>
			<input id="item.middleCode" name="item.middleCode"  class="nui-textbox nui-form-input"/>
   	        
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		   <%-- <a class="nui-button" onclick="exportCust">导出</a>--%>
		    <a class="nui-button" onclick="reset">重置</a>
		</div>
	</div>
	
	<div id="datagrid1" class="nui-datagrid" style="width:99.6%;height:auto;margin-top:10px;"  sortMode="client"
	    url="com.bos.csm.financialinstitution.financialinstitutioninfo.queryFinancialForDesk.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	     	<div type="indexcolumn">序号</div>     
	       	<div field="partyName" allowSort="true" width="" headerAlign="center">客户名称</div>
	 		<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true" >统一社会信用代码</div>
	        <div field="registerCode" headerAlign="center" allowSort="true">营业执照</div>
	        <div field="orgRegisterCd" headerAlign="center" allowSort="true">组织机构代码</div>
	        <div field="middleCode" headerAlign="center" allowSort="true">中征码</div>
	       	<div field="creditRatingCd" headerAlign="center" allowSort="true">客户评级</div>
	        <div field="creditAmt" allowSort="true" width="" headerAlign="center"dataType="currency">客户额度</div>  
	        <div field="availableAmt" allowSort="true" width="" headerAlign="center" dataType="currency">可用客户额度</div>  
	        <div field="mainUserNum" headerAlign="center" allowSort="true"  dictTypeId="user">管户客户经理</div>  
	     </div>
	</div>
</div>
<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	function query(){//对公单一客户查询
       var o = form.getData(false, true);//逻辑流必须返回total
       grid.load(o);
	}
	query();
	
	  grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].partyId+","+e.data[i].partyNum
       				+ '\');return false;" value="'
       				+ e.data[i].partyId
       				+ '">'+e.data[i]['partyName']+'</a>';
       		}
       });
	
	
	function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab_financial.jsp?corpid="
            + partyId+"&partyNum="+partyNum;
       nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
	}
	
	
	function reset() {
		form.setData({});
	}
	
	function exportCust() {
		document.getElementById("exportFrame").src=nui.context+"/com.bos.csm.corp.ExportManager.flow?_eosFlowAction=mycust_to_word";
	}
	
	
</script>
</body>
</html>
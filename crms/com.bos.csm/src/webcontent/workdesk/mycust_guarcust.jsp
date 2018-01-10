<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>我的客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div >   <!-- tabs start -->
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item.custPropertyStatus" id="item.custPropertyStatus" value="02" class="nui-hidden" />
		<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="item.partyName" required="false" class="nui-textbox nui-form-input"  />

		<label>客户编号：</label>
		<input name="item.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>ECIF客户编号：</label>
		<input name="item.ecifPartyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>客户类型：</label>
		<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList"/>
	
	</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		   <%-- <a class="nui-button" onclick="exportCust">导出</a>--%>
		    <a class="nui-button" onclick="reset">重置</a>
		</div>
	</div>
	
	<div id="datagrid1" class="nui-datagrid" style="width:99.6%;height:auto;margin-top:10px;"  sortMode="client"
	    url="com.bos.csm.guarcust.guarCust.queryGuarList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <!--<div type="checkcolumn">选择</div>-->
	        <div type="indexcolumn">序号</div>                   
	       <div field="partyNum" headerAlign="center" allowSort="true" width=""  >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" autoEscape="false" >客户名称</div>
			<div field="ecifPartyNum" headerAlign="center" allowSort="true" >ECIF客户编号</div>
			<div field="corpCustomerTypeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0001">客户类型</div>
	        <div field="createTime" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">创建日期</div>   
	        <div field="creditRatingCd" allowSort="true" width="" headerAlign="center" >信用评级</div>  
	      <div field="defaultState" allowSort="true" width="" headerAlign="center" dictTypeId="XD_PJCD0005">违约状态</div>  
	     </div>
	</div>
</div> 
</center>


<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	function query(){//对公单一客户查询
       var o = form.getData(false, true);//逻辑流必须返回total
       grid.load(o);
       //alert(nui.encode(o));
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
	}
	query();
	
	function reset() {
		form.setData({});
	}
	
	function exportCust() {
		document.getElementById("exportFrame").src=nui.context+"/com.bos.csm.corp.ExportManager.flow?_eosFlowAction=mycust_to_word";
	}
	
	function rendertype(e){
		return nui.getDictText("CDKH0001",e.row.CORP_CUSTOMER_TYPE_CD);
	}
	
	function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab_guarcust.jsp?corpid="
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
</script>
</body>
</html>
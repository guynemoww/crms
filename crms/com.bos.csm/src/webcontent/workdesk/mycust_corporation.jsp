<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): cc
  - Date: 2016-05-10 12:42:24
  - Description:
-->
<head>
<title>我的客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input id="item.examineState" name="item.examineState" value="3" class="nui-hidden nui-form-input"/>
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName"  class="nui-textbox nui-form-input"  />
	
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" />
			
			<label>营业执照：</label>
			<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" />
			
			<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd"  class="nui-textbox nui-form-input"/>
			
			<label>中征码：</label>
			<input id="item.middelCode" name="item.middelCode"  class="nui-textbox nui-form-input"/>

			<label>客户类型：</label>
			<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" />
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		    <a class="nui-button" onclick="reset">重置</a>
		</div>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;margin-top:10px"  sortMode="client"
    url="com.bos.csm.corporation.corporation.queryCorporationForDesk.biz.ext" dataField="items"
    allowAlternating="true" multiSelect="false" showEmptyText="true"
    emptyText="没有查到数据" showReloadButton="false"
    sizeList="[10,20,50,100]" pageSize="10">
    <div property="columns">
        <div type="indexcolumn">序号</div>                
		<div field="partyName" headerAlign="center" allowSort="true" width="15%"autoEscape="false" >客户名称</div>
		<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true" autoEscape="false" >统一社会信用代码</div>
		<div field="registrCd" headerAlign="center" allowSort="true" autoEscape="false" >营业执照</div>
		<div field="orgRegisterCd" headerAlign="center" allowSort="true" autoEscape="false" >组织机构代码</div>
		<div field="middelCode" headerAlign="center" allowSort="true" autoEscape="false" >中征码</div>
		<div field="creditRatingCd" headerAlign="center" allowSort="true">客户评级</div>
		<div field="pfAmt" allowSort="true" width="" headerAlign="center"dataType="currency">批复金额</div>  
        <div field="pfUsed" allowSort="true" width="" headerAlign="center"dataType="currency">批复已用金额</div>
  		<div field="mainUserNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">管户客户经理</div>
     </div>
</div>
<%--<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>--%>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	function query(){//对公单一客户查询
       var o = form.getData();//逻辑流必须返回total
       grid.load(o);
	}
	query();
	 grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			if(null !=e.data[i].partyName && ''!=e.data[i].partyName){
	       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].partyId+","+e.data[i].partyNum+","+e.data[i].corpCustomerTypeCd
	       				+ '\');return false;" value="'
	       				+ e.data[i].partyId
	       				+ '">'+e.data[i]['partyName']+'</a>';
       			}else{
       				e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].partyId+","+e.data[i].partyNum+","+e.data[i].corpCustomerTypeCd
	       				+ '\');return false;" value="'
	       				+ e.data[i].partyId
	       				+ '">'+e.data[i]['englishName']+'</a>';
       			}
       		}
       });
		
	function reset() {
		form.reset();
	}
	
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd;
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
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
            
	}
	
	 
</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-12 10:54:16
  - Description:
-->
<head>
<title>专业担保机构合作协议</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>第三方担保额度</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label class="nui-form-label">客户编号：</label>
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName" />
			
			<label class="nui-form-label">客户评级：</label>
			<input id="partyLimit.creditRating" class="nui-text nui-form-input" name="partyLimit.creditRating"/>
			
			<label class="nui-form-label">额度类型：</label>
			<input id="partyLimit.limitType" name="partyLimit.limitType" class="nui-text nui-form-input" dictTypeId="XD_SXYW0228" />
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">创建协议</a>
		</div>
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.conApply.conApply.getGuarantOrgCon.biz.ext" dataField="guarantOrgs"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">           
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div align="center" field="ZHKHJG" allowSort="true" width="" headerAlign="center" dictTypeId="org">担保基金专用账户开户行</div>
		        <div align="center" field="ZHMC" allowSort="true" width="" headerAlign="center">担保基金专用账户户名</div>
		        <div align="center" field="ZH" allowSort="true" width="" headerAlign="center" >担保基金专用账户账号</div>
		        <div align="center" field="FDBS" allowSort="true" width="" headerAlign="center" >担保放大倍数</div>
		        <div align="center" field="BZJBL" allowSort="true" width="" headerAlign="center" >保证金比例(%)</div>
		        <div align="center" field="STATUS_CD" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_SXCD8003">协议状态</div>
		    </div>
		</div>
	</div>
</fieldset>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	var partyId = "<%=request.getParameter("corpid")%>";
	initPage();
	function initPage(){
		var json = nui.encode({"partyId":partyId});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getPartyCrdByPartyId.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	//项目
            	if("01" == o.party.partyTypeCd && ("2" == o.corp.thirdCustTypeCd || "3" == o.corp.thirdCustTypeCd)){
            		o.partyLimit.limitType = "09";
            	}
            	//担保
            	if("01" == o.party.partyTypeCd && "1" == o.corp.thirdCustTypeCd){
            		o.partyLimit.limitType = "08";
            	}
            	//同业
            	if("05" == o.party.partyTypeCd){
            		o.partyLimit.limitType = "05";
            	}
            	form.setData(o);
				grid.load({"partyId":partyId});
        	}
        });
	}
	function add(){
		//无生效担保额度
		var json = {"partyId":partyId};
   	    msg = exeRule("RCRD_0007","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return;
   	    }
   	    //有在途申请
   	    msg = exeRule("RCONDB_0003","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return;
   	    }
   	    json = nui.encode({"partyId":partyId});
   	    $.ajax({
	        url: "com.bos.conApply.conApply.createDbhtProcess.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	if(data.msg !=null){
            		nui.alert(data.msg); //失败时后台直接返回出错信息
            		nui.get("btnCreate").setEnabled(true);
            		return;
            	}
            	var contractId = data.tbConGuarantOrgInfo.contractId;
            	var processInstId = data.processInstId;
        		git.go(nui.context + "/crt/con_info/con_tree_db.jsp?partyId="+partyId+"&proFlag=1&contractId="+contractId+"&processInstId="+processInstId,parent);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });
	}
</script>
</body>
</html>
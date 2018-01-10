<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zhonghui
  - Date: 2015-05-12 11:53:33
  - Description:
-->
<head>
<title>复议</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务复议列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
<%--		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>--%>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="btnCreate" onclick="add">复议</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyFyBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="BIZ_NUM" allowSort="true" headerAlign="center">业务编号</div>
		        <div field="PARTY_NAME" name="dyname" allowSort="true" headerAlign="center">客户名称</div>
		        <div field="PARTY_NAME" name="jtname" allowSort="true" headerAlign="center">集团客户名称</div>
		        <div field="BIZ_TYPE_FLAG" allowSort="true" headerAlign="center" dictTypeId="XD_SXYW0002" >业务性质</div>
		        <div field="BIZ_HAPPEN_TYPE" name="ywfsfs" allowSort="true" headerAlign="center" dictTypeId="XD_SXYW0001">业务发生方式</div>
		        <div field="APPLY_DATE" allowSort="true" headerAlign="center">申请日期</div>
		        <div field="CURRENCY_CD" allowSort="true" headerAlign="center" dictTypeId="CD000001" >币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" headerAlign="center">申请金额</div>
		        <div field="APPROVE_CONCLUSION" allowSort="true" headerAlign="center"  dictTypeId="XD_WFCD0002">审批结论</div>
		        <div field="VALID_DATE" allowSort="true" headerAlign="center">审批日期</div>
		    </div>
		</div>
	</div>
</fieldset>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	var partyTypeCd = "<%=request.getParameter("partyTypeCd")%>";
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>"<%--,"bizNum":o.map.bizNum--%>,"approveConclusion":"2","becomeEffectiveMark":"03"});
		if(partyTypeCd != '06'){
			grid.hideColumn(grid.getColumn("jtname"));
			grid.hideColumn(grid.getColumn("ywfsfs"));
		}else{
			grid.hideColumn(grid.getColumn("dyname"));
		}
	}
	function add(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复!");
			return false;
		}
		nui.get("btnCreate").setEnabled(false);
		
		var bizType = row.BIZ_TYPE;//grid中无此字段，从后台sql中取得
		var isBankTeamLoan = row.IS_BANK_TEAM_LOAN;
		var applyModeType = row.APPLY_MODE_TYPE;
		if(bizType == '02' || bizType == '03'||(bizType == '01' && isBankTeamLoan=='0' && applyModeType=='01')){
			//规则校验：客户有在途综合授信业务
			 var json = {"partyId":row.PARTY_ID};
	   	    var msg = exeRule("RBIZ_0001","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
	   	    //生效
	   	    msg = exeRule("RBIZ_0002","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    } 
	   	    //集团
	   	    msg = exeRule("RBIZ_0019","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
		}
		
		
		var msg = exeRule("XFE_0003","1",{"approveId":row.APPROVE_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		 var json = {"partyId":row.PARTY_ID};
	   	    msg = exeRule("RBIZ_0001","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
	   	//存在在途复议时不得发起复议
   	    json2 = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0046","1",json2);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }    
	   	
		var json=nui.encode({"approveId":row.APPROVE_ID,"bizHappenType":"02"});
		$.ajax({
	        url: "com.bos.bizApply.bizApply.createBizProcessTz.biz.ext",
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
            	nui.get("btnCreate").setEnabled(true);
				//git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConCreditInfo.contractId+"&contractType=01",parent);
				git.go(nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+data.bizApply.applyId+"&processInstId="+data.processInstId+"&proFlag=1",parent);
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
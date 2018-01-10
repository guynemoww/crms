<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zhonghui
  - Date: 2015-05-12 11:55:15
  - Description:
-->
<head>
<title>调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务调整列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
<%--		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>--%>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="btnCreate" onclick="update">调整</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyTzBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" name="dyname" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="PARTY_NAME" name="jtname" allowSort="true" headerAlign="center">集团客户名称</div>
		        <div field="APPROVAL_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <div field="BIZ_TYPE_FLAG" allowSort="true" class="nui-hidden" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="BIZ_HAPPEN_TYPE" name="ywfsfs" allowSort="true" headerAlign="center" dictTypeId="XD_SXYW0001">业务发生方式</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">批复金额</div>
		        <div field="USED_AMT" allowSort="true" width="" headerAlign="center">已用金额</div>
		        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用金额</div>
		        <div field="APPROVE_CONCLUSION" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_WFCD0002">审批结论</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center" >审批日期</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div>
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
	if(partyTypeCd != '06'){
		grid.hideColumn(grid.getColumn("jtname"));
		grid.hideColumn(grid.getColumn("ywfsfs"));
	}else{
		grid.hideColumn(grid.getColumn("dyname"));
	}
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>",<%--"bizNum":o.map.bizNum,--%>"approveConclusion":"1"});
	}
	function update(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复!");
			return false;
		}
		nui.get("btnCreate").setEnabled(false);
		
		//借新还旧不让调整
		var bizHappenType = row.BIZ_HAPPEN_TYPE;
		/* if(bizHappenType=='06'){
			nui.alert("借新还旧业务不支持调整");
			nui.get("btnCreate").setEnabled(true);
	   		return;
		} */
		
		
		//规则校验：冻结批复不能调整
		var json = {"applyId":row.APPLY_ID};
   	    var msg = exeRule("RBIZ_0024","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    msg = exeRule("XFE_0003", "1" , {"approveId":row.APPROVE_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		//集团成员只能调整低或银团
		//借新还旧的调整不受此限制
		if(bizHappenType=='06'){
			
		}else{
			var bizType = row.BIZ_TYPE;//使用后台sql传来的值
			var isBankTeamLoan = row.IS_BANK_TEAM_LOAN;
			var applyModeType = row.APPLY_MODE_TYPE;
	   	    if(bizType == '02'||bizType == '05' ||(bizType == '01' && isBankTeamLoan=='0' && applyModeType=='01')||
	   	    		(bizType == '04'&& applyModeType=='01')){
	   	    	var json1 = {"partyId":"<%=request.getParameter("partyId")%>"};
	   	    	msg = exeRule("RBIZ_0019","1",json1);
		   	    if(null != msg && '' != msg){
			   		nui.alert("集团成员只能调整低或银团业务");
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    } 
	   	    }
		}
		
   	    
   	    //存在在途业务合同签署申请或调整时不得调整批复
   	    var json2 = {"approveId":row.APPROVE_ID};
   	    msg = exeRule("RBIZ_0037","1",json2);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		//return;
   	    }
   	    //存在在途综合授信协议申请或调整时不得调整批复
   	    json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0038","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //存在在途出账时不得调整批复
   	    json2 = {"approveId":row.APPROVE_ID};
   	    msg = exeRule("RBIZ_0039","1",json2);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		//return;
   	    }
   	    //存在在途调整时不得调整
   	    json2 = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0046","1",json2);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    
		var json=nui.encode({"approveId":row.APPROVE_ID,"bizHappenType":"04"});
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
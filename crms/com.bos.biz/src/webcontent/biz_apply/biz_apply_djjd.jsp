<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 11:57:22
  - Description:
-->
<head>
<title>冻结与解冻</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务冻结与解冻列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
<%--		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>--%>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="dj" onclick="dj">冻结</a>
			<a class="nui-button" id="jd" onclick="jd">解冻</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyDjBizInfo.biz.ext" dataField="bizInfos"
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
		        <div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="STATUS_CD" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003">批复状态</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center" >批复起期</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">批复止期</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">批复金额</div>
		        <div field="USED_AMT" allowSort="true" width="" headerAlign="center">已用金额</div>
		        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用金额</div>
		        <!--<div field="FRZ_REASON" allowSort="true" width="" headerAlign="center">冻结原因</div> -->
		        <!--<div field="FRZ_AMT" allowSort="true" width="" headerAlign="center" >冻结金额</div> -->
		        <!--<div field="FRZ_TYPE" allowSort="true" width="" headerAlign="center"  dictTypeId="CD100003">冻结种类</div> -->
		       	<!--<div field="OPER_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="CDGY0001">自动解冻</div> -->
		        <!-- <div field="FRZ_END_DATE" allowSort="true" width="" headerAlign="center">冻结止期</div> -->
		        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">经办机构</div>
		        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
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
		grid.load({"partyId":"<%=request.getParameter("partyId")%>"<%--,"bizNum":o.map.bizNum--%>});
		
	}
	function dj(){//冻结
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复");
			return;
		}
		if(row.STATUS_CD=='07'){
			nui.alert("该批复已冻结或有在途的冻结流程，无法继续操作！");
			return;
		} 
		var msg = exeRule("XFE_0003","1",{"approveId":row.APPROVE_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return;
   	    }
		var applyId = row.APPLY_ID;//申请ID
		var amountId = row.AMOUNT_ID;
		var useAmt = row.AVAILABLE_AMT;//可以金额 
		var partyNum = row.PARTY_NUM;//客户编号
		var approveId = row.APPROVE_ID;
		var frzNum = row.FRZ_NUM;//冻结编号
		var json = nui.encode({
			"tbBizJdDjFlow":{
			"amountId":amountId,//业务基本信息唯一标识
			"frzNum":frzNum,//冻结编号
			"frzAmt":useAmt,//冻结金额---全部金额冻结
			"frzType":"11",//冻结种类---金额冻结 
			"frzReason":"",//冻结原因
			"happenType":"01",//发生类型(01冻结02解冻)
			"isjd":"0"//发生类型是01冻结的时候用于判断   0:否 ，1：是
			},
			"approveId":approveId//批复ID
		});
		$.ajax({
	        url: "com.bos.irm.irmApply.irmApply.saveIrmDataAnddjInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	if(data.msg == "111"){
            		nui.alert("该批复已冻结或有在途的冻结流程，无法继续操作！"); //失败时后台直接返回出错信息
            		nui.get("dj").setEnabled(true);
            		return;
            	}
            	if(data.msg == "失败"){
            		nui.alert(data.msg);
            		return;
            	}
				git.go(nui.context+"/irm/irm_djjd/djjd_tree.jsp?approveId="+approveId+"&amountId=" + amountId+"&processInstId="+data.processInstId+"&wflow=2");
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });
		<%-- nui.open({
	        url: nui.context + "/biz/biz_info/biz_dj_reason_new.jsp?applyId="+applyId +"&amountId=" + amountId + "&useAmt=" +useAmt+"&partyNum="+partyNum+"&approveId="+approveId,
	        title: "冻结", 
	        width: 800,
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
					grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
	           	 }
	        }
	    }); --%>
	}
	
	function jd(){//解冻
		var row = grid.getSelected();
		git.mask();
		nui.get("jd").setEnabled(false);
		if (null == row) {
			nui.alert("请选择一笔批复");
			git.unmask();
			nui.get("jd").setEnabled(true);
			return;
		}
		if(row.STATUS_CD!='07'){
			nui.alert("该批复未冻结，不能进行解冻操作！");
			git.unmask();
			nui.get("jd").setEnabled(true);
			return;
		}
		var msg = exeRule("XFE_0003","1",{"approveId":row.APPROVE_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		//冻结在途---有冻结信息  而且是未走完流程的流水
		var json = {"amountId":row.AMOUNT_ID};
   	    msg = exeRule("RBIZ_0077","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		git.unmask();
			nui.get("jd").setEnabled(true);
	   		return;
   	    }
		//解冻在途---有解冻信息  而且是未解冻的流水
		var json = {"amountId":row.AMOUNT_ID};
   	    var msg = exeRule("RBIZ_0049","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		git.unmask();
			nui.get("jd").setEnabled(true);
	   		return;
   	    }
   	    //存在生效的综合授信不允许启用非低，非银团的单笔
		var json=nui.encode({"applyId":row.APPLY_ID});
		$.ajax({
	        url: "com.bos.bizApply.bizApply.getBizData.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {	
	    		var bizType = data.bizApply.bizType;
				var isBankTeamLoan = data.bizApply.isBankTeamLoan;
				var applyModeType = data.bizApply.applyModeType;
			    if(bizType == '01' && isBankTeamLoan=='0' && applyModeType=='01'){
				   // var json = {"partyId":nui.get("party.partyId").getValue()};
				   var json = {"partyId":row.PARTY_ID};
	   	   		   var msg = exeRule("RBIZ_0002","1",json);
	   	   	       if(null != msg && '' != msg){
		   		    nui.alert("已有生效综合授信业务，不允许解冻非低风险或非银团单笔业务");
		   		    git.unmask();
					nui.get("jd").setEnabled(true);
		   	    	return;
	   	        	}
		      	}else{
		      	 //解凍初始
				var json1 = nui.encode({
					"tbBizJdDjFlow":{
					"frzNum":row.FRZ_NUM//冻结编号---凍結信息
					}
				});
        		$.ajax({
	        		url: "com.bos.irm.irmApply.irmApply.saveIrmDataAndjDInfo.biz.ext",
	        		type: 'POST',
	        		data: json1,
	        		contentType:'text/json',
	        		cache: false,
	        		success: function (data) {
            			if(data.msg == "失败"){
            				nui.alert(data.msg);
            				git.unmask();
							nui.get("jd").setEnabled(true);
            				return;
            			}
            			git.unmask();
						nui.get("jd").setEnabled(true);
						git.go(nui.context+"/irm/irm_djjd/irmjd_tree.jsp?amountId=" + row.AMOUNT_ID+"&processInstId="+data.processInstId+"&wflow=2");
	        		},
	        		error: function (jqXHR, textStatus, errorThrown) {
	            		alert(jqXHR.responseText);
	            		git.unmask();
						nui.get("jd").setEnabled(true);
	        		}
        		});
		      }
		      git.unmask();
			  nui.get("jd").setEnabled(true);
	        },
	        	error: function (jqXHR, textStatus, errorThrown) {
	        	git.unmask();
				nui.get("jd").setEnabled(true);
	            alert(jqXHR.responseText);
	        }
        });	
        
       
        <%-- var applyId = row.APPLY_ID;
		var amountId = row.AMOUNT_ID;
		var frz_num = row.FRZ_NUM;
		var approveId = row.APPROVE_ID;
        nui.open({
	        url: nui.context + "/biz/biz_info/biz_jd_reason_new.jsp?applyId="+applyId +"&amountId=" + amountId  + "&frz_num=" +frz_num+"&approveId="+approveId,
	        title: "解冻", 
	        width: 800, 
	    	height: 400,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
					grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
	           	 }
	        }
	    }); --%>
		
	}
</script>
</body>
</html>
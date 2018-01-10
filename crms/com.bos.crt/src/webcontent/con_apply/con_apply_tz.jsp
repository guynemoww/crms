<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-29 17:16:31
  - Description:
-->
<head>
<title>主合同调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<fieldset><legend> <span>生效合同信息</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>主合同编号：</label> 
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">合同调整</a>
			<a class="nui-button" id="btnDisable" onclick="disab">合同失效</a>
		</div>
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.conApply.conApply.getApproveCons.biz.ext" dataField="conInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="BIZ_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="CONTRACT_NUM" allowSort="true" width="" headerAlign="center">合同编号</div>
		        <div field="PRODUCT_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="product">业务品种</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
		        <div field="CONTRACT_AMT" allowSort="true" width="" headerAlign="center">合同金额</div>
		        <div field="CON_YU_E" allowSort="true" width="" headerAlign="center">合同已用金额</div>
		        <div field="BEGIN_DATE" allowSort="true" width="" headerAlign="center">合同起期</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">合同止期</div>
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
		grid.load({"partyId":"<%=request.getParameter("partyId")%>","conNum":o.map.contractNum});
	}
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i].CONTRACT_NUM='<a href="#" onclick="clickConNum(\''
   				+ e.data[i].CONTRACT_ID
   				+ '\');return false;" value="'
   				+ e.data[i].contractId
   				+ '">'+e.data[i].CONTRACT_NUM+'</a>';		
   		}
    });
	//合同编号链接
	function clickConNum(contractId){
		git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+contractId+"&contractType=02&proFlag=-1",parent);
	}
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔生效合同");
			return false;
		}
		var msg = exeRule("XFE_0004","1",{"contractId":row.CONTRACT_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //综合授信协议存在在途的调整不允许重复调整
		if(row.BIZ_TYPE == '综合授信协议'){
			var json = {"applyId":row.APPLY_ID};
	   	    msg = exeRule("RCON_0024","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途综合授信调整或单笔批复调整时不得调整综合授信协议
	   	    json = {"applyId":row.APPLY_ID};
	   	    msg = exeRule("RCON_0058","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途合同调整
	   	    json = {"applyId":row.APPLY_ID};
	   	    msg = exeRule("RCON_0059","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
		}else{//单笔
			//合同在途调整校验
			var json = {"amountDetailId":row.AMOUNT_DETAIL_ID};
	   	    var msg = exeRule("RCON_0026","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert("有在途合同调整");
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途综合授信协议协议调整
	   	    var json1 = {"contractId":row.CONTRACT_ID};
	   	    msg = exeRule("RCON_0060","1",json1);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途综合授信调整或单笔批复调整时不得调整业务合同
	   	    json = {"contractId":row.CONTRACT_ID};
	   	    msg = exeRule("RCON_0039","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途出账不得调整业务合同
   	        msg = exeRule("RCON_0040","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
		}
		
		//规则校验：冻结批复不能调整
		json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0024","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //合同下有担保合同调整在途
   	    var json1 = {"contractId":row.CONTRACT_ID};
	   	msg = exeRule("RGRT_0005","1",json1);
	   	if(null != msg && '' != msg){
		   nui.alert(msg);
		   return;
	   	}
   	    
		nui.get("btnCreate").setEnabled(false);
		var json=nui.encode({"contractId":row.CONTRACT_ID,"bizType":row.BIZ_TYPE});
		$.ajax({
	        url: "com.bos.conApply.conApply.tzContractInfo.biz.ext",
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
				git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.conInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,parent);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
	//合同失效
	function disab(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔生效合同");
			return false;
		}
		nui.get("btnDisable").setEnabled(false);
		//综合授信协议不允许手工失效
		if(row.BIZ_TYPE == '综合授信协议'){
	   		nui.alert("综合授信协议不允许手工失效！");
            nui.get("btnDisable").setEnabled(true);
	   		return "1";
		}else{//单笔
			//合同有在途调整不允许手工失效
			var json = {"amountDetailId":row.AMOUNT_DETAIL_ID};
	   	    var msg = exeRule("RCON_0026","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	   			nui.get("btnDisable").setEnabled(true);
		   		return ; 
	   	    }
	   	    
	   	    //存在在途出账不得调整业务合同
	   	    json = {"contractId":row.CONTRACT_ID};
   	        msg = exeRule("RCON_0040","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	   			nui.get("btnDisable").setEnabled(true);
		   		return;
		   		
	   	    }
	   	    //存在未结清借据
	   	    msg = exeRule("RCON_0043","1",json);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert("存在未结清借据");
		   		nui.get("btnDisable").setEnabled(true);
		   		return;
	   	    }
	   	    /* //循环合同不允许手工失效
	   	    msg = exeRule("RCON_0044","1",json);
	   	    if(null != msg && '' != msg){
		   		recounted(row.CONTRACT_ID,'1');
		   		return;
	   	    } */
		}
		 //合同下有担保合同调整在途
   	    var json1 = {"contractId":row.CONTRACT_ID};
	   	msg = exeRule("RGRT_0005","1",json1);
	   	if(null != msg && '' != msg){
		   nui.alert(msg);
		   nui.get("btnDisable").setEnabled(true);
		   return;
	   	}
		//recounted(row.CONTRACT_ID,'2');
		getContractBizType(row.CONTRACT_ID,'2');
	}
	
	function recounted(contractId,flag,bizType){
		var titil = "";
		if(bizType == "01" || bizType == "04")titil = ",合同失效后批复同步失效，失效后将不能恢复"; 
		nui.confirm("是否确认失效"+titil+"?","确定",function(action){
			if(action!="ok") return;
			var json=nui.encode({"contractId":contractId,"flag":flag});
			$.ajax({
		        url: "com.bos.conApply.conApply.disConInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        contentType:'text/json',
		        cache: false,
		        success: function (data) {
	            	if(data.msg !=null){
	            		nui.alert(data.msg); //失败时后台直接返回出错信息
	            		
	            	}else{
	            		nui.alert("更新失败"); //无返回信息
	            		
	            	}
	            	grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
	            	
	            	
	            	
	            	//add by shangmf Begin:20170519 合同失效后通知押品系统
	            	$.ajax({
		        		url: "com.bos.conApply.conSynToCollByWebService.disConSynColl.biz.ext",
		        		type: 'POST',
		        		data: json,
		        		contentType:'text/json',
		        		cache: false,
		        		success: function (data) {
	            			if(data.msg !=null){
	            				nui.alert(data.msg); //失败时后台直接返回出错信息
	            		
	            			}else{
	            				//nui.alert("处理成功，同步押品系统成功！"); //无返回信息
	            		
	            			}
	            			grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
		        		},
		        		error: function (jqXHR, textStatus, errorThrown) {
		            		alert(jqXHR.responseText);
		            		git.unmask();
		        		}
	        		});	
	        		//add by shangmf End:20170519	
	            	  	
	            	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		            git.unmask();
		        }
	        });	
		});
		nui.get("btnDisable").setEnabled(true);
	}
	//获取业务性质
	function getContractBizType(contractId,flag){
		var json=nui.encode({"contractId":contractId});
		$.ajax({
	        url: "com.bos.conApply.conApply.getConInfoBizType.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        if(data.bizType){
	        	recounted(contractId,flag,data.bizType)
	        }else{
	        	alert("业务信息为空，合同失效操作失败！");
	        }
	        	
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
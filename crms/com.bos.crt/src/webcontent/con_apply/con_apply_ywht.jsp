<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-12 10:54:16
  - Description:
-->
<head>
<title>业务合同</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务批复列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>批复编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate"  onclick="add">创建合同</a>
		</div>
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.conApply.conApply.getApproveAndSxxy.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="BIZ_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002" >业务性质</div>
		        <div field="BIZ_HAPPEN_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0001">业务发生方式</div>
		        <div field="PRODUCT_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="product">业务品种</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
		        <div field="DETAIL_AMT" allowSort="true" width="" headerAlign="center"dataType="currency">批复额度</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center">起始日期</div>
		        <!-- <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div> -->
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
		grid.load({"partyId":"<%=request.getParameter("partyId")%>","bizNum":o.map.bizNum});
	}
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i].BIZ_NUM='<a href="#" onclick="clickPfbh(\''+ e.data[i].APPLY_ID+  '\');return false;" value="'+ e.data[i].BIZ_NUM+ '">'+e.data[i].BIZ_NUM+'</a>';		
       		}
    });
	//批复编号链接
	function clickPfbh(applyId){
		git.go(nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+applyId+"&processInstId=0&proFlag=-1",parent);
	}
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复");
			return false;
		}
   	    	var msg = exeRule("XFE_0003","1",{"approveId":row.APPROVE_ID});
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
		   	}
		var json1={"amountDetailId":row.AMOUNT_DETAIL_ID,"applyId":row.APPLY_ID};
		//校验已创建合同不允许重复创建
   	    msg = exeRule("RCON_0002","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    } 
   	    //规则校验：冻结批复不能申请合同
		json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0024","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //存在在途批复调整时不得签合同
   	    msg = exeRule("RBIZ_0046","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert("该批复存在在途业务");
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //在途
   	    msg = exeRule("RCON_0026","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    } 
   	    if(row.PRODUCT_TYPE == '01014001'){
   	    	nui.alert("该业务无合同签约");
	   		return false;
   	    }
   	    /*
   	    //余额为0不可创建合同
   	    msg = exeRule("RCON_0007","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   	//	return "1";
   	    }*/
   	    
   	    
	    nui.get("btnCreate").setEnabled(false);
		var json=nui.encode({"amountDetailId":row.AMOUNT_DETAIL_ID});
		
		$.ajax({
	        url: "com.bos.conApply.conApply.createYwhtProcess.biz.ext",
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
            	 
            	//合同流程创建完毕后,校验面谈面签业务预申请表是否存在该批复编号   该功能暂时屏蔽  
            	 var ht=$('<a>').append(row.BIZ_NUM);
	
            	var json2={"bizNum":ht.text()};
            	
            	msg = exeRule("RCON_0050","1",json2);
		   	    if(null != msg && '' != msg){
			   		nui.confirm(msg,"确认",function(action){
			        	if(action=="ok") {
			        		var partyId="<%=request.getParameter("partyId")%>";
			        		var json = nui.encode({"partyId":partyId,"bussinessType":"con","applyNum":ht.text(),"businessNumber":data.tbConContractInfo.contractNum});
			        		$.ajax({
					            url: "com.bos.conApply.conApply.linkConImage.biz.ext",
					            type: 'POST',
					            data: json,
					            cache: false,
					            contentType:'text/json',
					            success: function (text) {
					            	if(text.msg !=null){
					            		return;
					            	}
					            	git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConContractInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,parent);
					            },
					            error: function (jqXHR, textStatus, errorThrown) {
					                nui.alert(jqXHR.responseText);
					            }
					        });
			        	}else{
			        		git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConContractInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,parent);
			        	}
			        }); 
		   	    }else{
		   	    	git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConContractInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,parent);
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
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 17:35:24
  - Description:
-->
<head>
<title>客户额度申请</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="party.partyId" class="nui-hidden nui-form-input" name="party.partyId" value="<%=request.getParameter("corpid")%>"/>
	<input id="partyLimit.limitId" class="nui-hidden nui-form-input" name="partyLimit.limitId"/>
	<input id="partyLimit.oldLimitId" class="nui-hidden nui-form-input" name="partyLimit.oldLimitId"/>
		
    <div class="nui-dynpanel" columns="4" id="table1">
		<label class="nui-form-label">客户编号：</label>
		<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>
		
		<label class="nui-form-label">客户名称：</label>
		<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName" />
		
		<label class="nui-form-label">客户评级：</label>
		<input id="partyLimit.creditRating" class="nui-text nui-form-input" name="partyLimit.creditRating"/>
		
		<label class="nui-form-label">额度类型：</label>
		<input id="partyLimit.limitType" name="partyLimit.limitType" class="nui-text nui-form-input" dictTypeId="XD_SXYW0228" />
	</div>
	
	<div style="border-bottom:0;text-align:right;margin-top: 10px;">
		<a class="nui-button" id="btnView" onclick="checkInfo('view')" iconCls="icon-zoomin">查看</a>
		<a class="nui-button" id="btnCreate" onclick="checkInfo('add')" iconCls="icon-add">申请</a>
		<a class="nui-button" id="btnTz" onclick="checkInfo('update')" iconCls="icon-edit">调整</a>
	</div>
</div>
<fieldset id="neibu">
	<legend>
		<span>综合授信额度信息</span>
	</legend>
	<div id="dg" class="nui-datagrid" sortMode="client" url="com.bos.crdApply.crdApply.getHisLimitCorp.biz.ext" dataField="limits"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	    	<div type="checkcolumn" ></div>
	        <div field="PARTY_NAME" allowSort="true"  headerAlign="center" align="center" >客户名称</div>
	        <div field="CERT_TYPE" allowSort="true"  headerAlign="center" align="center"  dictTypeId="CDKH0002">证件类型</div>
	        <div field="CERT_NUM" allowSort="true"  headerAlign="center" align="center" >证件号码</div>
	        <div field="LIMIT_TYPE" allowSort="true"  headerAlign="center" align="center"  dictTypeId="XD_SXYW0228">额度类型</div>
	        <div field="CREDIT_AMT" allowSort="true"  headerAlign="center" align="center" >额度总额</div>
	        <div field="USED_AMT" allowSort="true"  headerAlign="center" align="center" >已用额度</div>
	        <div field="AVAILABLE_AMT" allowSort="true"  headerAlign="center" align="center" >可用额度</div>
	        <div field="BEGIN_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">起始日</div>
	        <div field="END_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">到期日</div>
	        <div field="ORG_NUM" allowSort="true"  headerAlign="center" align="center"  dictTypeId="org">经办机构</div>
	        <div field="USER_NUM" allowSort="true"  headerAlign="center" align="center"  dictTypeId="user">经办人</div>
	    </div>
	</div>
</fieldset>

<fieldset id="danbao">
	<legend>
		<span>担保额度信息</span>
	</legend>
	<div id="dsfdb" class="nui-datagrid"   sortMode="client"
		    url="com.bos.crdApply.crdApply.getHisLimitThird.biz.ext" dataField="limits"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		    	<div type="checkcolumn" ></div>
		        <div field="PARTY_NUM" allowSort="true" width="" headerAlign="center">客户编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
				<div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>       
 				<div field="AMPLIFY_IND" allowSort="true" width="" headerAlign="center">担保放大倍数</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">担保额度止期</div>
		        <div field="GUARANT_ORG_REAL" allowSort="true" width="" headerAlign="center" dataType="currency">担保机构实收资本</div>
		        <div field="MAIN_ORG_ID" allowSort="true" width="" headerAlign="center"  dictTypeId="org">主办行</div>
		        <div field="DEAL_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">经办日期</div>
		        <div field="STATUS_CD" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003" >状态</div>
		        <div field="REMARK" allowSort="true" width="" headerAlign="center">备注</div>
		    </div>
		</div>
</fieldset>
<fieldset id="xiangmu">
		<legend>
			<span>项目额度信息</span>
		</legend>
		<hr/>
		<div id="dsfxm" class="nui-datagrid" sortMode="client"
		    url="com.bos.crdApply.crdApply.getHisLimitThird.biz.ext" dataField="limits"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		    	<div type="checkcolumn" ></div>
		        <div field="PARTY_NUM" allowSort="true" width="" headerAlign="center">客户编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="ITEM_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0227">合作项目类型</div>
		        <div field="ITEM_NAME" allowSort="true" width="" headerAlign="center">合作项目名称</div>
		   	    <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
		        <div field="ITEM_AMT" allowSort="true" width="" headerAlign="center" dataType="currency">合作项目额度</div>
		        <div field="ITEM_BEGIN_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">合作项目额度起始日</div>
		        <div field="ITEM_END_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">合作项目额度终止日</div>
		        <div field="LONGEST_LOAN_TERM" allowSort="true" width="" headerAlign="center">最长贷款期限（年）</div>
		        <div field="HIGHEST_LOAN_RATE" allowSort="true" width="" headerAlign="center">最高贷款成数</div>
		        <div field="MAIN_ORG_ID" allowSort="true" width="" headerAlign="center"  dictTypeId="org">主办行</div>
		        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
		        <div field="STATUS_CD" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003" >状态</div>
		        <div field="DEAL_DATE" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">经办日期</div>
		    </div>
		</div>
</fieldset>
<!-- <div style="text-align:center;border:none;margin-top:10px;" >
	<a class="nui-button" id="btnCreate" onclick="checkInfo" iconCls="icon-add">申请</a>
</div> -->
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	
	$("#neibu").css("display","none");//对公 自然人 同业
	$("#danbao").css("display","none");
	$("#xiangmu").css("display","none");
	
	initPage();
	var partyId = "<%=request.getParameter("corpid")%>";
	var partyTypeCd = '01';
	//初始化页面
	function initPage(){
		var json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getPartyCrdByPartyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	o.partyLimit.limitType = o.party.partyTypeCd;
            	form.setData(o);
            	
            	//根据不同客户类型加载不同模块
            	//评级
            	if(o.partyLimit.creditRating){
            		nui.get("partyLimit.creditRating").setValue(o.partyLimit.creditRating);
            	}
            	
          		loadinit(o.party.partyTypeCd);
        	}
        });
	}
	//根据客户类型展示不同列表
	function loadinit(partyTypeCd){
		$("#neibu").css("display","block");
		var myGrid = nui.get("dg");
		myGrid.load({"partyId":partyId,"limitType":partyTypeCd});
		loadPage("09");
		loadPage("08");
		
		if(partyTypeCd=='01'||partyTypeCd=='02'){
		    $("#btnCreate").css("display","none");
		    $("#btnView").css("display","none");
		    $("#btnTz").css("display","none");
		}
		if(partyTypeCd=='6'){//委托方--不允许申请额度
        	$("#btnCreate").css("display","none");
        	$("#btnView").css("display","none");
		    $("#btnTz").css("display","none");
        	$("#table2").css("display","none");
		}
	}
	function loadPage(type){
		var size = 0;
		var json = nui.encode({"partyId":partyId,"limitType":type});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getHisLimitThird.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	size = o.total;
            	if(size > 0){
            		var myGrid;
	            	if(type == "08"){
	            		$("#danbao").css("display","block");
	            		myGrid = nui.get("dsfdb");
	            	}else if(type == "09"){
	            		 $("#xiangmu").css("display","block");
	            		 myGrid = nui.get("dsfxm");
	            	}else{
	            		 $("#neibu").css("display","block");
	            		 myGrid = nui.get("dg");
	            	}
	            	myGrid.load({"partyId":partyId,"limitType":type});
            	}
            	return size;
        	}
        });
		return size;
	}
	var grid = nui.get("dg");
	//发起流程
	function checkInfo(op){
		var rows = grid.getSelected();
		if(op == "update" || op == "view"){
			if(!rows){
				alert("请选择一条记录！");
				return;
			}
			if(op == "update"){
				// 只有调整时才有旧额度ID
				nui.get("partyLimit.oldLimitId").setValue(rows.LIMIT_ID);
			}
			if(op == "view"){
				nui.open({
					url:nui.context+"/crd/crd_apply/crd_tree.jsp?limitId="+rows.LIMIT_ID+"&partyId="+rows.PARTY_ID+"&proFlag=-1",
					title:"查看额度信息",
					state:"max",
					showMaxButton:true,
		        	allowResize:true,
		            ondestroy: function(e) {
		            	initPage();
		            }
				});
            	return;
			}
		}
		var limitType = nui.get("partyLimit.limitType").getValue();
		if(op == "add"){
			//判断相同类型额度
			var ruleid;
			var json;
			var msg;
			if(limitType=='05'){
				ruleid = "RCRD_0010";
				json = {"partyId":partyId,"itemType":"05"};
		   	    msg = exeRule(ruleid,"1",json);
			}
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    }
		}
		//有在途不可再发起流程
		if(limitType=='08'||limitType=='09'){//第三方
			var json = {"partyId":"<%=request.getParameter("corpid")%>"};
	   	    var msg = exeRule("RCRD_0004","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    }
		}else{
			var json = {"partyId":"<%=request.getParameter("corpid")%>"};
	   	    var msg = exeRule("RCRD_0005","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    }
		}
		create();
	}
	//保存基本信息
	function create(){
		//校验
        var o = form.getData();
        //公司客户没有内部评级结果不允许发起客户额度申请
		if(o.partyLimit.creditRating==null ||o.partyLimit.creditRating==''){
			if(o.partyLimit.limitType=='01'){
				nui.alert("公司客户没有内部评级结果不允许发起客户额度申请");
				return;
			}
		}
		var partyNum = nui.get("party.partyNum").getValue();
        o.partyLimit.partyId=nui.get("party.partyId").getValue();
        var json = nui.encode(o);
        
        $.ajax({
            url: "com.bos.crdApply.crdApply.createPartyCrd.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg !=null){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            		return;
            	}
            	nui.open({
					url:
					nui.context+"/crd/crd_apply/crd_tree.jsp?limitId="+text.partyLimit.limitId+"&partyId="+text.partyLimit.partyId+"&processInstId="+text.processInstId+"&proFlag=1&partyTypeCd="+partyTypeCd+"&partyNum="+partyNum,
					title:"第三方额度信息",
					state:"max",
					showMaxButton:true,
		        	allowResize:true,
		            ondestroy: function(e) {
		            	initPage();
		            }
				});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
        
      
	}
</script>
</body>
</html>
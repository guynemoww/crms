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
		    <a class="nui-button" id="btnCreate" onclick="checkInfo('view')" iconCls="icon-zoomin">查看</a>
		    <a class="nui-button" id="btnCreate" onclick="checkInfo('add')" iconCls="icon-add">申请</a>
		    <a class="nui-button" id="btnCreate" onclick="checkInfo('update')" iconCls="icon-edit">调整</a>
		</div>
	</div>
	<fieldset>
		<legend>
			<span>历史额度信息</span>
		</legend>
		
		<div id="dsfdb" class="nui-datagrid"   sortMode="client"
		    url="com.bos.crdApply.crdApply.getHisLimitThird.biz.ext" dataField="limits"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		    	<div type="checkcolumn" ></div>
		        <div align="center" field="PARTY_NUM" allowSort="true"  headerAlign="center">客户编号</div>
		        <div align="center" field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div>
				<div align="center" field="CURRENCY_CD" allowSort="true"  headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>       
 				<div align="center" field="AMPLIFY_IND" allowSort="true"  headerAlign="center">担保放大倍数</div>
		        <div align="center" field="END_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">担保额度止期</div>
		        <div align="center" field="GUARANT_ORG_REAL" allowSort="true"  headerAlign="center" dataType="currency">担保机构实收资本</div>
		        <div align="center" field="MAIN_ORG_ID" allowSort="true"  headerAlign="center"  dictTypeId="org">主办行</div>
		        <div align="center" field="DEAL_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">经办日期</div>
		        <div align="center" field="STATUS_CD" allowSort="true"  headerAlign="center" dictTypeId="XD_SXCD8003" >状态</div>
		        <div align="center" field="REMARK" allowSort="true"  headerAlign="center">备注</div>
		    </div>
		</div>
		
		<div id="dsfxm" class="nui-datagrid" sortMode="client"
		    url="com.bos.crdApply.crdApply.getHisLimitThird.biz.ext" dataField="limits"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		    	<div type="checkcolumn" ></div>
		        <div align="center" field="PARTY_NUM" allowSort="true"  headerAlign="center">客户编号</div>
		        <div align="center" field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div>
		        <div align="center" field="ITEM_TYPE" allowSort="true"  headerAlign="center" dictTypeId="XD_SXYW0227">合作项目类型</div>
		        <div align="center" field="ITEM_NAME" allowSort="true"  headerAlign="center">合作项目名称</div>
		   	    <div align="center" field="CURRENCY_CD" allowSort="true"  headerAlign="center" dictTypeId="CD000001">币种</div>
		        <div align="center" field="ITEM_AMT" allowSort="true"  headerAlign="center" dataType="currency">合作项目额度</div>
		        <div align="center" field="USED_AMT" allowSort="true"  headerAlign="center" dataType="currency">已用合作项目额度</div>
		        <div align="center" field="AVAILABLE_AMT" allowSort="true"  headerAlign="center" dataType="currency">可用合作项目额度</div>
		        <div align="center" field="ITEM_BEGIN_DATE" allowSort="true"  headerAlign="center"  dateFormat="yyyy-MM-dd">合作项目额度起始日</div>
		        <div align="center" field="ITEM_END_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">合作项目额度终止日</div>
		        <div align="center" field="LONGEST_LOAN_TERM" allowSort="true"  headerAlign="center">最长贷款期限（年）</div>
		        <div align="center" field="HIGHEST_LOAN_RATE" allowSort="true"  headerAlign="center">最高贷款成数</div>
		        <div align="center" field="MAIN_ORG_ID" allowSort="true"  headerAlign="center"  dictTypeId="org">主办行</div>
		        <div align="center" field="USER_NUM" allowSort="true"  headerAlign="center" dictTypeId="user">经办人</div>
		        <div align="center" field="STATUS_CD" allowSort="true"  headerAlign="center" dictTypeId="XD_SXCD8003" >状态</div>
		        <div align="center" field="DEAL_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">经办日期</div>
		    </div>
		</div>
	</fieldset>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("dsfxm");
	
	$("#dsfdb").css("display","none");//第三方担保
	$("#dsfxm").css("display","none");//第三方项目
	
	initPage();
	var partyTypeCd = '01';
	var partyId = "<%=request.getParameter("corpid")%>";
	var itemType = null;
	
	//初始化页面
	function initPage(){
		//只查客户信息
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
            	form.setData(o);
            	//根据不同客户类型加载不同模块
            	/*thirdCustTypeCd XD_KHCD7001
            	1	担保公司
				2	房地产商
				3	汽车（4S）销售商
				4	委托方
				5	专项机械设备销售商
				6	大数金融
            	*/
            	//担保
            	if("01" == o.party.partyTypeCd && "1" == o.corp.thirdCustTypeCd){
            		$("#dsfdb").css("display","block");
            		grid = nui.get("dsfdb");
        			nui.get("partyLimit.limitType").setValue("08");
        			grid.load({"partyId":"<%=request.getParameter("corpid")%>","limitType":'08'});
					if("1" == o.corp.thirdCustTypeCd){
						itemType = "8";//字典无此项，用于区分担保额度
	            	}
            	}
            	//项目
            	else if("01" == o.party.partyTypeCd && ("2" == o.corp.thirdCustTypeCd || "3" == o.corp.thirdCustTypeCd || "4" == o.corp.thirdCustTypeCd 
        				|| "5" == o.corp.thirdCustTypeCd || "6" == o.corp.thirdCustTypeCd )){
            		$("#dsfxm").css("display","block");
            		grid = nui.get("dsfxm");
        			nui.get("partyLimit.limitType").setValue("09");
        			grid.load({"partyId":"<%=request.getParameter("corpid")%>","limitType":'09'});
        			//项目细类
	            	if ("2" == o.corp.thirdCustTypeCd||"4"==o.corp.thirdCustTypeCd){//房地产商
	            		itemType = "1";
	            	}else if("3" == o.corp.thirdCustTypeCd){//汽车（4S）销售商
	            		itemType = "2";
	            	}else if("5" == o.corp.thirdCustTypeCd){//专项机械设备销售商
	            		itemType = "3";
	            	}else if("6" == o.corp.thirdCustTypeCd){//大数金融
	            		itemType = "4";
	            	}
            	}
            	
			}
        });
	}

	//发起流程
	function checkInfo(op){
		var rows = grid.getSelected();
		if(op == "update" || op == "view"){
			if(!rows){
				alert("请选择一条记录！");
				return;
			}
			if(op == "update"){
				if (rows.STATUS_CD != "03"){
					alert("只能调整生效的额度！");
					return;
				}
				if (rows.ITEM_TYPE != itemType){
					alert("当前第三方客户类型已改变，请重新申请！");
					return;
				}
				// 只有调整时才有旧额度ID
				nui.get("partyLimit.oldLimitId").setValue(rows.LIMIT_ID);
			}
			if(op == "view"){
				nui.open({
					url:nui.context+"/crd/crd_apply/crd_tree.jsp?limitId="+rows.LIMIT_ID+"&partyId="+rows.PARTY_ID+"&itemType="+rows.ITEM_TYPE+"&proFlag=-1&partyNum="+rows.PARTY_NUM,
					title:"查看第三方额度信息",
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
			if(limitType=='08'){//担保
				ruleid = "RCRD_0010";
				json = {"partyId":partyId,"itemType":"08"};
		   	    msg = exeRule(ruleid,"1",json);
			}else if(limitType=='09'){//项目
				//ruleid = "RCRD_0008";
				//json = {"partyId":partyId,"itemType":itemType};
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
		}
		create(rows);
	}
	
	//保存基本信息
	function create(row){
        var o = form.getData();
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
					url:nui.context+"/crd/crd_apply/crd_tree.jsp?limitId="+text.partyLimit.limitId+"&partyId="+text.partyLimit.partyId+"&itemType="+itemType+"&processInstId="+text.processInstId+"&proFlag=1&partyTypeCd="+partyTypeCd+"&partyNum="+partyNum,
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
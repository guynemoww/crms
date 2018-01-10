<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 11:56:25
  - Description:
-->
<head>
<title>借新还旧</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务循环通/续接贷列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
<%--		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label>借据编号</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input" /> 
			<a class="nui-button"onclick="query">搜索</a>
		</div>--%>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="btnCreate" onclick="update">循环通/续接贷</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyJxhjBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="CONTRACT_NUM" allowSort="true" width="" headerAlign="center">合同编号</div>
		        <div field="SUMMARY_NUM" allowSort="true" width="" headerAlign="center">借据编号</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="SUMMARY_AMT" allowSort="true" width="" headerAlign="center">借据金额</div>
		        <div field="JJYE" allowSort="true" width="" headerAlign="center">借据余额</div>
	        	<div field="SUMMARY_STATUS_CD" allowSort="true"  headerAlign="center" dictTypeId="XD_SXYW0226">借据状态</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div>
		    </div>
		</div>
	</div>
</fieldset>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //业务
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>"<%--,"bizNum":o.map.bizNum,"summaryNum":o.map.summaryNum--%>});
	}
	function update(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔业务!");
			return false;
		}
		nui.get("btnCreate").setEnabled(false);

		//存在在途借新还旧
   	    var json2 = {"summaryId":row.SUMMARY_ID};
   	    msg = exeRule("RBIZ_0061","1",json2);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    msg = exeRule("XFE_0005","1",{"summaryId":row.SUMMARY_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //bizType最好就是01，其他数据全是坑，不要再跳了
    	var json=nui.encode({"amountDetailId":row.AMOUNT_DETAIL_ID,"partyId":row.PARTY_ID,"applyId":row.APPLY_ID,"bizType":"01"});
		$.ajax({
	        url: "com.bos.bizApply.bizApply.createBizInfoJxhj.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
        	    if(null != data.msg){
            		nui.alert(data.msg); //失败时后台直接返回出错信息
            		nui.get("btnCreate").setEnabled(true);
            		return;
            	}
	        	var json=nui.encode({"applyId":data.bizApply.applyId,"summaryId":row.SUMMARY_ID});
				$.ajax({
			        url: "com.bos.bizApply.bizApply.saveBizSummary.biz.ext",
			        type: 'POST',
			        data: json,
			        contentType:'text/json',
			        cache: false,
			        success: function(text){
			        }

	            }),
            	//git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConCreditInfo.contractId+"&contractType=01",parent);
				git.go(nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+data.bizApply.applyId+"&processInstId="+data.processInstId+"&proFlag=1",parent);
            	
	        },
	        error: function (jqXHR, textStatus, errorThrown){
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
	    });
	}
</script>
</body>
</html>
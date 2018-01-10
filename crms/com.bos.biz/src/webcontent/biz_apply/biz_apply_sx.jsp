<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 11:57:22
  - Description:
-->
<head>
<title>批复失效</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>生效批复列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
<%--		<div class="nui-dynpanel" columns="6">
			<label>业务编号：</label> 
			<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>--%>
	
		<div class="nui-toolbar" >
			<a class="nui-button" id="sx" onclick="sx">批复失效</a>
		</div>
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplySxBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="APPROVAL_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <!-- <div field="BIZ_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div> -->
		        <div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">批复金额</div>
		        <div field="USED_AMT" allowSort="true" width="" headerAlign="center">已用金额</div>
		        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用金额</div>
		        <div field="APPROVE_CONCLUSION" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_WFCD0002">审批结论</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center">审批日期</div>
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
	query();
	function query(){
		var o = form.getData();
		grid.load({"partyId":"<%=request.getParameter("partyId")%>"<%--,"bizNum":o.map.bizNum--%>});
	}
	function sx(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔批复");
			return false;
		}
		var approveId = row.APPROVE_ID;
		var applyId = row.APPLY_ID;
		//规则校验有生效借据不让失效
		var json1 = {"approveId":approveId,"applyId":applyId};
   	    var msg = exeRule("RBIZ_0051","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return;
	   	}
		 //存在在途出账时不得调整批复
   	    msg = exeRule("RBIZ_0039","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert('存在在途出账业务');
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //存在在途批复调整时不得调整
   	    msg = exeRule("RBIZ_0046","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert('存在在途批复调整');
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		
		//存在在途业务合同签署申请或调整时不得调整批复
   	    msg = exeRule("RBIZ_0037","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert('存在在途合同申请或调整');
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //存在在途综合授信协议申请或调整时不得失效批复
   	    msg = exeRule("RBIZ_0038","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert('存在在途综合授信协议申请或调整');
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	    //有生效合同时提示，没有时直接失效
   	    msg = exeRule("RBIZ_0052","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.confirm("该笔批复下有生效业务合同，是否强制失效","确认",function(action){
	            if(action!="ok") return;
				pfsx(approveId);
			});
   	    }else{
   	    	pfsx(approveId);
   	    }
   	    
	}
	function pfsx(approveId){
		nui.open({
	        url: nui.context + "/biz/biz_info/biz_sx_reason.jsp?approveId="+approveId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
					grid.load({"partyId":"<%=request.getParameter("partyId")%>"});
	           	 }
	        }
	    });
	}
</script>
</body>
</html>
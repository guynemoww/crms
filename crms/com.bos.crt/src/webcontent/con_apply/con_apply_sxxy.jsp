<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 10:38:16
  - Description:
-->
<head>
<title>综合授信协议</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset><legend> <span>业务批复列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">创建综合授信协议</a>
		</div>
		<div id="grid" class="nui-datagrid"  sortMode="client"
		    url="com.bos.conApply.conApply.getApproveBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="BIZ_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
		        <div field="BIZ_HAPPEN_NATURE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0004">业务发生性质</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">批复额度</div>
		        <div field="CREDIT_TERM" allowSort="true" width="" headerAlign="center">期限</div>
		        <div field="CYCLE_UNIT" allowSort="true" width="" headerAlign="center" dictTypeId="XD_GGCD6009">期限单位</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center">起始日期</div>
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
		grid.load({"partyId":"<%=request.getParameter("partyId")%>","bizNum":""});
	}
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i].BIZ_NUM='<a href="#" onclick="clickPfbh(\''
   				+ e.data[i].APPLY_ID
   				+ '\');return false;" value="'
   				+ e.data[i].BIZ_NUM
   				+ '">'+e.data[i].BIZ_NUM+'</a>';		
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
		nui.get("btnCreate").setEnabled(false);
		var msg = exeRule("XFE_0003","1",{"approveId":row.APPROVE_ID});
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		//校验已有综合授信协议不允许重复创建
		var json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RCON_0001","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
            nui.get("btnCreate").setEnabled(true);
	   		return "1";
   	    }
		//规则校验：冻结批复不能申请综合授信协议
		json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0024","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
		var json=nui.encode({"applyId":row.APPLY_ID});
		$.ajax({
	        url: "com.bos.conApply.conApply.createSxxyProcess.biz.ext",
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
				git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConCreditInfo.contractId+"&contractType=01&proFlag=1&processInstId="+data.processInstId,parent);
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
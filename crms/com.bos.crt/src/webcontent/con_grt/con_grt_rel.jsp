
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:40
  - Description:
-->
<head>
<title>担保合同关联抵质押物信息</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
%>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-save" onclick="insert()" id="link">关联</a>
		<a class="nui-button" iconCls="icon-add" onclick="view" >查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="delete">删除</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.getConSub.biz.ext"
		dataField="conGrts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >质押人名称</div>
			<div field="SURETY_NO" headerAlign="center" allowSort="true" >质押物编号</div>
			<div field="SORT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_YPZL01">质押物类型</div>
			<div field="BZ" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
			<div field="ASSESS_VALUE" headerAlign="center" allowSort="true" >评估价值（元）</div>
			<div field="MORTGAGE_VALUE" headerAlign="center" allowSort="true" >权利价值（元）</div>
			<div field="USED_AMT" headerAlign="center" allowSort="true" >已担保金额（元）</div>
			<div field="SURETY_AMT" headerAlign="center" allowSort="true" >担保确认金额（元）</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	
	var view="<%=request.getParameter("view") %>";
		
	if (view=="1") {
		nui.get("delete").hide();
		nui.get("link").hide();
	} 
	
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId});
	}
	//将抵质押物引入担保合同
	function insert(){
        nui.open({
            url: nui.context + "/crt/con_grt/selSubGrt.jsp?subcontractId="+subcontractId+"&applyId="+applyId+"&subcontractType="+subcontractType,
            title: "选择",
            width: 800,
    		height: 500,
            ondestroy: function (action) {     
				search();
        	}
        }); 
	}
		//将抵质押物引入担保合同
	function insert(){
	
		var rows = nui.get("grid").getData();
		
		var array=new Array();
		for(var i=0;i<rows.length;i++){
			array.push(rows[i].SURETY_NO);
		}
		
		var json = nui.encode({"subcontractId":subcontractId});
		//删除抵质押物与担保合同关联关系
		$.ajax({
	        url: "com.bos.grt.grtManage.mortgageSort.getSubCon.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        	var o = nui.decode(data);
	        	if("" == o.conSub.ifTopSubcon || null == o.conSub.ifTopSubcon){
	        		alert("请先保存合同基本信息！");
	        		return;
	        	}
		        nui.open({
		            url: nui.context + "/crt/con_grt/selSubGrt.jsp?subcontractId="+subcontractId+"&applyId="+applyId+"&subcontractType="+subcontractType+"&array="+array,
		            title: "选择",
		            width: 800,
		    		height: 500,
		            ondestroy: function (action) {     
						search();
		        	}
		        }); 
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	}
	//删除抵质押物与担保合同关联关系
	function remove(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔抵押物信息");
			return false;
		}
		var json = nui.encode({"relationId":row.RELATION_ID});
		//删除抵质押物与担保合同关联关系
		$.ajax({
	        url: "com.bos.grt.conGrt.delSubGrtRel.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        	search();
            	alert("删除关联成功");
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	}
	
	function view(){
        var row = grid.getSelected();
		var v ="1";
        if (row) {
        
        	//改为链接押品系统:
        	var url = 'http://'+'<%=ipStr%>'+':'+'<%=portStr%>'+'/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&cltNo='+row.SURETY_NO+'&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>';
        	//alert(url);
        	window.open(url,'押品信息','押品信息管理', 'height=400,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
        			   
        	<%--var relationId=row.RELATION_ID;
        	nui.open({
	            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+row.SURETY_ID+"&view="+v,
	            showMaxButton: true,
	            title:"押品信息",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	search();
	            }
	        });--%>
        } else {
            alert("请选中一条记录");
        } 
	}
</script>
</body>
</html>
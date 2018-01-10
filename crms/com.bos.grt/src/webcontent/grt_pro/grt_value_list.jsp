<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/common/nui/common.jsp" %>
	<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
	<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
	<title>押品价值信息</title>
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
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.grt.bsCiValue.bsCiValue.queryValueApproval.biz.ext"
			dataField="arrays" allowResize="false" showReloadButton="false" 
			sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client">
			<div property="columns">
				<div field="SURETY_NO" allowSort="true">押品编号</div>
				<div field="SURETY_NAME" allowSort="true">押品名称</div>
				<div field="SORT_TYPE" allowSort="true" dictTypeId="XD_YPZL01">押品种类</div>
				<div field="PARTY_NAME" allowSort="true">抵质押人名称</div>
				<div field="ASSESS_VALUE" allowSort="true">评估价值</div>
				<div field="MORTGAGA_AMT" allowSort="true">最新权利价值</div>
				<div field="CURRENCY_CD" allowSort="true" dictTypeId="CD000001">币种</div>
			</div>
	   	</div>
				
	<script type="text/javascript">
	 	nui.parse();
		var grid = nui.get("grid1");
		var suretyNo ="<%=request.getParameter("suretyNo") %>"; 
		function search() {
	     grid.load({"suretyNo":suretyNo});
	        
	   }
	    search();
	    
	    
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['SURETY_NAME']='<a href="#" onclick="showCollInfo();">'+e.data[i]['SURETY_NAME']+'</a>';
			}
		});
		
		
		function showCollInfo(){
		 	var rows = grid.getSelecteds();
		    var row = grid.getSelected();
		    if(row){
			    var cltNo = row.SURETY_NO;
				var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>&cltNo="+cltNo+"&sceneCode=1";			
				window.open(url);
				return;
		    }
		}
	</script>
</body>
</html>
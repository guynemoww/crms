<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil"%>
<html>
<!-- 
  - Author(s): lenovo
  - Date: 2017-11-02 10:03:45
  - Description:
-->
<head>
<title>存单质押扣划</title>
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
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>合同编号</label>
		<input id="contractNum" name="contractNum"   required="false" class="nui-textbox nui-form-input"/>
		
        <label>借据编号</label>
		<input id="summaryNum" name="summaryNum" required="false" class="nui-textbox nui-form-input"/>
		
		<label>担保合同编号</label>
		<input id="subcontractNum" name="subcontractNum" required="false" class="nui-textbox nui-form-input"/>
		
		 <label>押品编号</label>
		<input id="suretyNo" name="suretyNo" required="false" class="nui-textbox nui-form-input"/>
		
	</div>
</div>
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" >
	<a class="nui-button" id="zykh" onclick="createZykhProcess">质押扣划</a>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.grt.cdzykh.cdzykh.queryCdzykhList.biz.ext"    
	 dataField="data" allowResize="true" showReloadButton="false" showPageSize="true"  pageSize="10" multiSelect="false" sortMode="client"
	 emptyText="没有查到数据" sizeList="[10,15,20,50,100]">
	<div property="columns">
		<div type="checkcolumn">选择</div>
		<div type="indexcolumn" width="50px;">序号</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="CONTRACT_NUM"  headerAlign="center" allowSort="true" >合同编号</div>
		<div field="CONTRACT_AMT"  headerAlign="center" allowSort="true" >合同金额</div>
		<div field="CONTRACT_BEGIN_DATE"  headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">合同起期</div>
		<div field="CONTRACT_END_DATE"  headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">合同止期</div>
		<div field="SUBCONTRACT_NUM"  headerAlign="center" allowSort="true" >担保合同编号</div>
		<div field="SURETY_NO"  headerAlign="center" allowSort="true" >押品编号</div>
		<div field="SURETY_NAME"  headerAlign="center" allowSort="true" >押品名称</div>
		<div field="MORTGAGE_VALUE"  headerAlign="center" allowSort="true" >权利价值</div>
		<div field="SUMMARY_NUM"  headerAlign="center" allowSort="true" >借据编号</div>
		<div field="JJYE" headerAlign="center" allowSort="true" >借据余额</div>
		<div field="SUMMARY_BEGIN_DATE"  headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">借据起期</div>
		<div field="SUMMARY_END_DATE"  headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">借据止期</div>
	</div>
</div>	

	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
    	function init(){
    		git.mask();
    		var data = form.getData(); //获取表单多个控件的数据
	        grid.load(data);
	        grid.on("preload",function(e){
       			if (!e.data || e.data.length < 1){
       				return;
       			}	
       			for (var i=0; i<e.data.length; i++){
   					e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
					e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan(\''+ e.data[i].CONTRACT_ID+ '\',\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['CONTRACT_NUM']+'</a>';
					e.data[i]['SURETY_NAME']='<a href="#" onclick="showCollInfo(\''+ e.data[i].SURETY_NO+ '\');">'+e.data[i]['SURETY_NAME']+'</a>';	
	   			}
	   		});
	   		git.unmask();
    	}
    	init();
    	//创建放款支付申请
		function goToLoan(contractId,partyId){
			nui.open({
            	url:nui.context +"/pay/payout_apply/pay_tab.jsp?contractId="+contractId+"&partyId="+partyId,
            	showMaxButton: true,
            	title: "",
            	width: 1024,
            	height: 768,
            	state:"max",
            	onload: function(e){
	            	var iframe = this.getIFrameEl();
	            },
            	ondestroy:function(e){
            		search();
            	}
  	 	 	});	
		}
    	
    	function showCollInfo(cltNo){
			var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&cltNo="+cltNo+"&sceneCode=1";			
			window.open(url);
			return;
		}
    	function search(){
    		init();
    	}
	 	//重置
		function reset(){
			form.reset();
		}
		//质押扣划
		function createZykhProcess(){
			//选择一行数据
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔借据");
				return;
			}
			//在途校验
			//在途的存单质押扣划--借据
			var loanSummary = row.SUMMARY_NUM;//借据编号
			var json1 = {"summaryNum":loanSummary};
			var msg = "";
   	    	msg = exeRule("RBIZ_0090","1",json1);
   	    	if(null != msg && '' != msg){
	   			nui.alert(msg);
	   			return;
   	   		 }
			git.mask();
			//发起流程---数据准备
			var partyId = row.PARTY_ID;//客户ID
			var suretyNo = row.SURETY_NO;//押品编号
			var json = nui.encode({
				"tbCdZykhApply":{
				"partyId":partyId,
				"loanSummary":loanSummary,
				"suretyNo":suretyNo
				}
			});
			$.ajax({
            	url: "com.bos.grt.cdzykh.cdzykh.CreateProcessForZykhNew.biz.ext",
           		type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
            	async:false,
            	success: function (mydata) {
            		if(mydata.msg){
            			nui.alert(mydata.msg);
            			git.unmask();
            			return;
            		}
            		git.unmask();
					git.go(nui.context+"/grt/cdzykh/cdzykh_tree.jsp?zykhId="+mydata.response.bizId+"&processInstId="+mydata.response.processInstId+"&wflow=2&proFlag=1");
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
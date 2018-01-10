<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>预警信息</title>
</head>
<body style="margin-left:10px;">

<div id="warn">
	<div  style="margin-top: 20px;">	 
	  <fieldset style="margin-top: 10px;">
  	         <legend>  
    	              <span>预警信息</span>
             </legend>
	         <div  class="nui-dynpanel" columns="8"  >
		        <div style="text-align: center;">首次级别认定时间</div>
		        <input name="firstWarnTime" style="width:70%;" class="nui-text"/>
		        <div style="text-align: center;">首次预警级别</div>
		        <input name="firstWarnLevel" style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		        <div style="text-align: center;">目前级别认定时间</div>
		        <input name="currenttWarnTime" style="width:70%;" class="nui-text"/>
		        <div style="text-align: center;">目前预警级别</div>
		        <input name="currentLevel"  style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		    </div>
		   
		    <div style="border-bottom: 1px"> 
		     <div style="margin-top: 20px;text-align: left;">1.当前预警信号</div>
		     <div id="cwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="signalInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div type="indexcolumn"></div>
                     <div field="dictName">信号名称</div> 
                 </div>
             </div>
		  
		     <!-- <div id="feiCaiWu" style="margin-top: 20px;text-align: left;">2.非财务信号</div>
		     <div id="fcwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="signalInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                 <div type="indexcolumn"></div>
                     <div field="dictName">信号名称</div> 
                 </div>
             </div>
		      -->
		     <div style="margin-top: 20px;text-align: left;">2.预案</div>
		     <div>
		       <input name="preservePlan" class="nui-textarea" style="width:100%;" enabled="false"/>
		       <input name="csmSignalId" id="csmSignalId" class="nui-hidden"/>
		     </div>
		     <div style="margin-top: 20px;text-align: left;">3.预案执行情况</div>
		     <div>
		        <input name="executionPlan" id="executionPlan" class="nui-textarea" enabled="false" style="width:100%;" required="false"/>
		     </div>
	 </fieldset>
	</div>
</div>
	

</body>
<script type="text/javascript">
                                                                                           //源地址页面aft/dailyInspect/inspectReport.jsp
    nui.parse();
    var warn = new nui.Form("#warn");                                                //预警部分
 var partyId = "<%=request.getParameter("partyId") %>";
//加载预警模块数据
function setOtherInfo(){
       var json = nui.encode({partyId:partyId});
       $.ajax({
				url: "com.bos.aft.aft_inspect_report.getInspectOtherInfo.biz.ext",
				type: 'POST',
				data: json,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				    //alert(nui.encode(text.otherInfo));
					warn.setData(text.otherInfo);                                      //加载预警数据
					nui.get("cwxh").load({partyId:partyId});         //加载财务类型号
				},
				error: function () {
			        
			    }
		});
}
setOtherInfo();
</script>
</html>
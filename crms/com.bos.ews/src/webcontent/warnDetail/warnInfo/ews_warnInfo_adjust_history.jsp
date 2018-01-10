<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<!-- <div id="form1"  style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label>客户名称</label>
		<input name="partyName" required="false"  class="nui-text nui-form-input" style="width:100%;"  />

		<label>客户编号</label>
		<input name="partyNum" required="false" class="nui-text nui-form-input"  style="width:100%;"  />

		<label id="tWarnLevel">预警级别</label>
		<input id="warnLevel" name="warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJJB0001"  style="width:100%;" />

		<div id="tConfirmDate">认定日期</div>
		<input id="confirmDate" name="confirmDate" required="false" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />

	</div>
</div>
 -->
	<div id="form2"  style="width:99.5%;height:auto;overflow:hidden;margin-top: 7px;">
		<div class="nui-dynpanel" columns="6">
	        <div class="nui-form-label">变更类型</div>
	        <div>
				<input name="signalStatusCd" id="signalStatusCd" class="nui-dictcombobox nui-form-input" filterOp="in" filterStr="1,3,5" required="true" dictTypeId="XD_YJCD0002"/>
			</div>
			
		</div>
		<div style="border-bottom:0;text-align:right;margin-top: 10px;">
			 <a id="btnSave" class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		</div>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnInfo.queryWarnInfoHistory.biz.ext" dataField="warnInfos"
		allowResize="true" showReloadButton="false" showPageSize="false" pageSize="10" multiSelect="false" sortMode="client">
	   <div property="columns">
	     <div type="indexcolumn">序号</div>
	     <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
	     <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001" >预警信号来源</div>
	     <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
	     <div field="holdDate" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd">预警信号认定日期</div>
	     <div field="closeDate" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd">预警信号关闭日期</div>
	     <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002">预警信号状态</div>
	     <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
	     <div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" >经办机构</div>
	     <div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
	   </div>
	</div>

</body>

<script type="text/javascript">
 	nui.parse();
    var form2 = new nui.Form("#form2"); 
	var grid = nui.get("grid1");
	var bizId = "<%=request.getParameter("bizId") %>";
	var partyId = "<%=request.getParameter("corpid") %>";
	var arr = git.getDictDataFilter("XD_YJCD0002","2,3,5");
	nui.get("signalStatusCd").setData(arr);
	nui.get("signalStatusCd").setValue("2");
	
	//var adjustType=[{ "id": "01", "text": "信号生效" },{ "id": "02", "text": "信号关闭" },{ "id": "03", "text": "关闭审核" }];
       //nui.get("adjustType").setData(adjustType);
	function initForm() {  
     	var json=nui.encode({bizId:bizId});
	    $.ajax({      
	            url: "com.bos.ews.csmWarnLevel.getCsmWarnLevelInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	    var text = nui.decode(text);
	            	    form.setData(text.csmWarnLevelInfo);
	            	    //query();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	                
	            }
		});
	
        		
	}
 	//initForm();
 	query();

	//查询变更记录
	function query(){
	   var signalStatusCd = nui.get("signalStatusCd").value;
	   if(signalStatusCd==""){
	       alert("变更类型不能为空！");
	       return;
	   }
	   var queryParam ={"partyId":partyId,"signalStatusCd":signalStatusCd}; 
	   grid.load({queryParam:queryParam});
	}

</script>
</html>

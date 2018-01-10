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




<fieldset>
  	<legend>
   		<span>预警客户信息</span>
    </legend>
<div id="form1"  style="width:99.5%;height:auto;overflow:hidden;margin-top: 7px;">
	<div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label>客户名称</label>
		<input name="partyName" required="false"  class="nui-text nui-form-input" style="width:100%;"  />

		<label>客户编号</label>
		<input name="partyNum" required="false" class="nui-text nui-form-input"  style="width:100%;"  />

		<label id="tWarnLevel">预警级别</label>
		<input id="warnLevel" name="warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJCD0004"  style="width:100%;" />

		<div id="tConfirmDate">认定日期</div>
		<input id="confirmDate" name="confirmDate" required="false" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />

	</div>
</div>
</fieldset>

<fieldset>
  	<legend>
   		<span>级别变更历史记录</span>
    </legend>
	<div class="nui-dynpanel" columns="8" style="text-align:center;">
		
		<div id="tConfirmDate">起始日期</div>
		<input id="startDate" name="confirmDate" required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />
        
        <div id="tConfirmDate">终止日期</div>
		<input id="endDate" name="confirmDate" required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />
        
	</div>
<a class="nui-button" iconCls="icon-search" onclick="query()" id="btnSave">查询</a>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.csmWarnLevel.getLevelEditHistory.biz.ext" dataField="adjusts"
	allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
   <div property="columns">
     <div type="indexcolumn">序号</div>
     <div field="warningLevelCd" headerAlign="center" dictTypeId="XD_YJCD0004" allowSort="true"> 认定的预警级别 </div>
     <div field="adjustDate" headerAlign="center"  allowSort="true">申请变更日期(年-月-日)</div>
     <div field="matterState" headerAlign="center"  allowSort="true">预警事项描述</div>
     <div field="suggestState" headerAlign="center"  allowSort="true">控制措施</div>
     <div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办用户</div>
     <div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org"dateFormat="yyyy-MM-dd">经办机构</div>
   </div>
</div>
</fieldset>
</body>

<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var bizId = "<%=request.getParameter("bizId") %>";                      //"8a70d1f046b75ea80146b776f70d0017";
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
            	    grid.load({bizId:bizId});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                
            }
	});
	
            		
}
initForm(); 

//查询变更记录
function query(){
   var startDate = nui.get("startDate").value;
   var endDate = nui.get("endDate").value;
   grid.load({startTime:startDate,endTime:endDate,bizId:bizId});
}

</script>
</html>

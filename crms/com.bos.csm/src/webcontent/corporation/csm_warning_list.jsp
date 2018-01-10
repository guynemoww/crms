<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<fieldset>
	  <legend>
	    <span>预警管理->预警信号列表</span>
	   </legend>
	    <input type="hidden" name="item" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4" style="text-align:center;">
			<label>客户名称</label>
			<input name="item.partyName" required="false"  class="nui-text nui-form-input"  />
	
			<label>客户编号</label>
			<input name="item.partyNum" required="false" class="nui-text nui-form-input"  />
	
			<label>授信额度</label>
			<input name="item.creditTotalExposure" required="false" class="nui-text nui-form-input" dataType="currency"  />
	
			<label>授信余额</label>
			<input name="item.occupiedExposure" required="false" class="nui-text nui-form-input" dataType="currency"  />
	
			<label>客户等级</label>
			<input name="item.initialCredit" required="false" class="nui-text nui-form-input" />
	
			<label>最新分类</label>
			<input name="" required="false" class="nui-text nui-form-input" />
	
			<label>预警级别</label>
			<input name="item.warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJCD0004" />
	
			<label>认定日期</label>
			<input name="item.confirmDate" required="false" class="nui-text nui-datepicker" dateFormat="yyyy-MM-dd"  />
		</div>
	</fieldset>
</div>
<div style="width:99.5%">
<fieldset>
	  <legend>
	    <span>已有预警信号列表</span>
	   </legend>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnInfo.queryEwsInfo.biz.ext" dataField="csmWarnInfo"
		allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
	     <div property="columns">
	     <div type="indexcolumn"> 序号 </div>
	     <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
	     <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
	     <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
	     <div field="launchDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd"> 信号发起日期 </div>
	     <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd"> 信号认定日期 </div>
	     <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 信号状态 </div>
	     <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
	     </div>
	</div>
</fieldset>
</div>
</center>			
   
</body>

 <script type="text/javascript">
        nui.parse();
        git.mask();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		form.setEnabled(false);                                    //表单禁用
		var corpid = "<%=request.getParameter("partyId") %>";       //客户编号
		git.mask();
		 
		                                          //表单遮罩效果
       
/*初始化方法*/		
function initForm() {
     var json=nui.encode({partyId:corpid});
    
    $.ajax({
            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	    git.unmask();
            	    var text = nui.decode(text);
            	    form.setData(text);
               		status=2;
		            grid.load({partyId:corpid,status:"2"});           /*加载数据*/
            	                                                                                           //取消表单数据
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask();
                nui.alert(jqXHR.responseText);
                
            }
	});		
}
initForm(); 

       

	</script>
</html>

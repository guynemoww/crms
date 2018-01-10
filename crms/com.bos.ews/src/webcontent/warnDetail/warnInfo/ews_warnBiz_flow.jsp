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

  <fieldset>
  	<legend>
    	<span>客户信息</span>
    </legend>
<div id="form1" class="nui-form" style="width:100%;height:auto;overflow:hidden;margin-top: 7px;">
	
	
	<input type="hidden" name="item" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label>客户名称</label>
		<input name="item.partyName" required="false"  class="nui-text nui-form-input" style="width:100%;" />

		<label>客户编号</label>
		<input name="item.partyNum" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label>授信额度</label>
		<input name="item.creditTotalExposure" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label>授信余额</label>
		<input name="item.occupiedExposure" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label>客户等级</label>
		<input id="initialCredit" name="item.initialCredit" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label>最新分类</label>
		<input name="" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label id="tWarnLevel">预警级别</label>
		<input id="warnLevel" name="item.warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJCD0004"  style="width:100%;" />

		<label>认定日期</label>
		<input id="confirmDate" name="item.confirmDate" required="false" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"  style="width:100%;" />
        
        <label>当前变更级别</label>
		<input id="confirmDate" name="item.levelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJCD0004" style="width:100%;" />
        
        <label>预警事项描述（包括预警信息、来源渠道、程度等）</label>
        <input id="matter" class="nui-textarea nui-form-input" required="true" name="item.matterState" style="width:100%;" />
        
        <label>拟采取的控制措施及建议</label>
        <input id="suggestState" class="nui-textarea nui-form-input" required="true" name="item.suggestState" style="width:100%;" />
	</div>

</div>
	</fieldset>
	
	  <fieldset>
  	<legend>
    	<span>本次变更信号列表</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;margin-top: 7px;" url="com.bos.ews.warnInfo.queryWarnInfoInFlow.biz.ext" dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
     <div property="columns">
     <div type="indexcolumn" > 序号 </div>
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
</body>

 <script type="text/javascript">
        nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		form.setEnabled(false);                                    //表单禁用
		var corpid = "<%=request.getParameter("corpid") %>";       //客户编号
		var bizId ="<%=request.getParameter("bizId") %>";          //变更ID
		if(bizId=="null"){
		  bizId=null;
		}
		var status;                                                //预警信号状态
		var queryFlowInfo;                                         //判断是否查询发起信息
		git.mask();                                                //表单遮罩效果
		
		
       
/*初始化方法*/		
function initForm() {
     var json=nui.encode({partyId:corpid,bizId:bizId});
    
    $.ajax({
            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                    var text = nui.decode(text);
                    
            	    if(text.type=="06"){
		                 nui.get("confirmDate").hide();                       //担保客户无预警级别认定日期
		                 nui.get("warnLevel").hide();                         //担保客户无预警级别
	                	 $("#tConfirmDate").css({"display":"none"});          //担保客户无预警级别认定日期
		                 $("#tWarnLevel").css({"display":"none"});            //担保客户无预警级别
		            }
            	    form.setData(text);
               		status=2;
		            grid.load({bizId:bizId});           /*加载数据*/
            	      git.unmask();                                                                                     //取消表单数据
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	});		
}
initForm(); 

       

	</script>
</html>

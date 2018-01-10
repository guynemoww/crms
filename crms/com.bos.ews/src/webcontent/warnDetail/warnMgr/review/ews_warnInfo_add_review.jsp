<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-15 22:01:55
  - Description:预警信号关闭
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Title</title>
</head>
<body>

<!-- <div  style="margin-top:30px;font-weight: bold">预警信号复核列表</div> -->
<fieldset>
  	<legend>
    	<span>预警信号复核列表</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"  dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" url="com.bos.ews.warnMgr.queryWarnInfoInFlow.biz.ext"  pageSize="10" multiSelect="true" sortMode="client">
	 <div property="columns">
		<div type="checkcolumn" >选择</div>
		 <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
         <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
         <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
         <div field="launchDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd"> 信号发起日期 </div>
         <div field="holdDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd"> 信号认定日期 </div>
         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 信号状态 </div>
         <div field="signalState" headerAlign="center" allowSort="true"> 备注 </div>
		</div>
</div>
</fieldset>

<div class="nui-toolbar" style="border-bottom:0;text-align:right;"> 
    <a class="nui-button" iconCls="icon-close" onclick="update()" id="btnSave">关闭信号</a>  
</div>

</body>
<script type="text/javascript">
	 	nui.parse();
	    var grid = nui.get("grid1");
	    var corpid = "<%=request.getParameter("corpid") %>";     //参与人ID
        var bizId = "<%=request.getParameter("bizId") %>";       //流程传递数据
        var bizType = "<%=request.getParameter("bizType") %>";   //
        var processInstId = "<%=request.getParameter("processInstId") %>";         //获取流程ID
        git.mask();                                                                //页面遮罩效果
/*初始化表单数据*/
function initForm() {
    grid.load({bizId:bizId,queryFlowInfo:"y",queryClose:1});
    git.unmask();                                                 //取消页面遮罩效果
            		
}
initForm();



function update(){
  git.mask();
  var object = grid.getSelecteds(); //获取选中对象
  if(object.length==0){
   alert("请选择要关闭的信号！");
   git.unmask();
   return;
  };
  
  $.ajax({
            url:"com.bos.ews.warnInfo.closeWarnInFlow.biz.ext",
            type:'POST',
            data: nui.encode({"warnInfos":object,"status":"3"}),
            cache:false,
            contentType:'text/json',
            success:function(text){
                alert(text.msg);
                initForm();
                git.unmask();
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
                 nui.alert(jqXHR.responseText);
                 git.unmask();
                 initForm();
            }
     })
  git.unmask();
}

	</script>
</html>
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-03-28 22:01:55
  - Description:预警信号关闭
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Title</title>
</head>
<body>

<div  style="margin-top:30px;font-weight: bold">预警信号关闭列表</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"  dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" url="com.bos.ews.warnInfo.queryEwsInfo.biz.ext"  pageSize="10" multiSelect="true" sortMode="client">
	 <div property="columns">
		<div type="indexcolumn" >序号</div>
		 <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
         <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
         <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
         <div field="launchDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd"> 信号发起日期 </div>
         <div field="holdDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd"> 信号认定日期 </div>
         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 信号状态 </div>
         <div field="signalState" headerAlign="center" allowSort="true"> 备注 </div>
		</div>
</div>

<div class="nui-dynpanel" columns="6" id="levelEdit">
    <div>是否发起级别下调：</div>
    <input id="levelComfire" class="nui-dictcombobox" dictTypeId="XD_0002"/>
</div>

<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
    <a class="nui-button" iconCls="icon-goto" onclick="isLevelHold" id="btnSave">保存是否发起级别下调</a>  
</div>

</body>
<script type="text/javascript">
	 	nui.parse();
	    var grid = nui.get("grid1");
	    var corpid = "<%=request.getParameter("corpid") %>";
        var bizId = "<%=request.getParameter("bizId") %>";
        var processInstId = "<%=request.getParameter("processInstId") %>";         //获取流程ID
         var isLevel = "<%=request.getParameter("isLevel") %>";    //获取是否需要发起级别调整
         var partyTypeCd="<%=request.getParameter("partyTypeCd") %>";
         if(partyTypeCd=="06"){
            nui.get("levelEdit").hide();
            nui.get("btnSave").hide();
         }else{
            nui.get("levelComfire").setValue(isLevel);
         }

        git.mask();
function initForm(){
     grid.load({"bizId":bizId,close:"1",queryFlowInfo:"y"});
     git.unmask();
           	
}
initForm();

function isLevelHold(){
    git.mask();
  var rslt = nui.get("levelComfire").value;
  var json = nui.encode({isLevelEdit:rslt,"processInstId":processInstId,bizId:bizId});
   $.ajax({
            url:"com.bos.ews.csmWarnLevel.updateFlowMap.biz.ext",
            type:'POST',
            data: json,
            cache:false,
            contentType:'text/json',
            success:function(text){
                alert(text.msg);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                 nui.alert(jqXHR.responseText);
            }
     });
     git.unmask();
}
/**
function update(){
    var rows = grid.getSelecteds();
    $.ajax({
            url:"com.bos.ews.ews.warningClose.biz.ext",
            type:'POST',
            data: nui.encode({"ob":rows,status:2}),
            cache:false,
            contentType:'text/json',
            success:function(){
                if(text){
                  nui.alert(text);
                }else{
                  var text=nui.decode(text);
                }
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                 nui.alert(jqXHR.responseText);
                 git.unmask();
            }
     })
}      
*/

	</script>
</html>
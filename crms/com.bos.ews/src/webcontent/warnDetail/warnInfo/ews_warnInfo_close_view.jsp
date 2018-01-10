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
<!--  
<div id="title"  style="margin-top:30px;font-weight: bold;font-size: 14px;">预警管理->预警信号关闭</div>

<div id="form1" style="margin-top:20px;width:80%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	
    <div class="nui-dynpanel" columns="4" style="text-align:center;">
    
		<label>客户名称</label>
		<input name="item.partyName" required="false"  class="nui-text nui-form-input"  />

		<label>客户编号</label>
		<input name="item.partyNum" required="false" class="nui-text nui-form-input"  />

		<label>授信额度</label>
		<input name="item.applyExposureAmt" required="false" class="nui-text nui-form-input"  />

		<label>授信余额</label>
		<input name="item.usedExposureAmt" required="false" class="nui-text nui-form-input"  />

		<label>客户等级</label>
		<input name="outEvalResult" required="false" class="nui-text nui-form-input" />

		<label>最新分类</label>
		<input name="" required="false" class="nui-text nui-form-input" />

		<label>预警级别</label>
		<input name="item.warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJCD0004"/>

		<label>认定日期</label>
		<input name="item.confirmDate" required="false" class="nui-text nui-form-input" />

	</div>
</div>
-->
<div  style="margin-top:30px;font-weight: bold">预警信号关闭列表</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"  dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" url="com.bos.ews.warnInfo.queryEwsInfo.biz.ext"  pageSize="10" multiSelect="true" sortMode="client">
	 <div property="columns">
		<div type="indexcolumn" >选择</div>
		 <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
         <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
         <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
         <div field="launchDate"  headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd"> 信号发起日期 </div>
         <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd"> 信号认定日期 </div>
         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 信号状态 </div>
         <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
	    
	</div>
	
</div>
   
<!-- 
    <table  style="margin-top:30px;width:100%;border:1px solid gray ;text-align:center;">
     
      <tr>
              <td style="width:15%;background-color: #cccccc;">
                  <label >预警信号关闭意见</label>
              </td>
              <td style="width:85%;">
                  <input id="updateSuggest"  name="closeSuggest"  required="false" style="width:100%;height:100%;"  class="nui-textarea nui-form-input"  />
              </td>
      </tr>
      
    </table>

 <div id="level" class="nui-dynpanel" columns="2" style="margin-top:20px;text-align:left;border: 0px;">
     <div style="text-align:left;">是否发起预警级别调整</div>
     <input id="startEdit" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
</div>

<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="update()" id="btnSave">提交</a>
	
</div>
 -->

</body>
<script type="text/javascript">
	 	nui.parse();
	    var grid = nui.get("grid1");
	    var corpid = "<%=request.getParameter("corpid") %>";
        var bizId = "<%=request.getParameter("bizId") %>";
        git.mask();
        
//初始化方法        
function initForm() {
     
     grid.load({"bizId":bizId,close:"1",queryFlowInfo:"y"});
     git.unmask();
           	
}
initForm();



/*提交表单数据修改
function update(){
    git.mask();                    //页面这
    var rows = grid.getSelecteds();//获取选中项
    if(rows.length==0){
    alert("请选择要关闭的信号！");
    git.unmask();
    return;
    }                               
    //var closeSuggest= nui.get("updateSuggest").value;
    var startEdit= nui.get("startEdit").value;             
    $.ajax({
            url:"com.bos.ews.warnInfo.closeWarnInfo.biz.ext",
            type:'POST',
            data: nui.encode({"warnInfos":rows,status:3,"corpid":corpid,"startEdit":startEdit}),
            cache:false,
            contentType:'text/json',
            success:function(text){
             
               var node=text.node;//弹出审批页面
               openSubmitView(node);
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
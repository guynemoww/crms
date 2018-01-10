<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-15 22:21:37
  - Description:预警级别调整
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Title</title>
</head>
<body>

<div id="title" style="margin-top:30px;">预警管理->预警级别调整</div>
<!--  
<div style="margin-top:30px;font-weight: bold;">预警信号列表</div>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warning.queryEwsInfo.biz.ext" dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
     <div property="columns">
     <div type="indexcolumn"> 序号 </div>
      <div field="csmEarlyWarningId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
     <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
     <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
     <div field="launchDate" headerAlign="center" allowSort="true"> 信号发起日期 </div>
     <div field="confirmDate" headerAlign="center" allowSort="true"> 信号认定日期 </div>
     <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="EWS_YJXHZT"> 信号状态 </div>
     <div field="signalState" headerAlign="center" allowSort="true"> 备注 </div>
     </div>
</div>
-->
<div  style="margin-top:30px;font-weight: bold;">预警级别调整</div>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" id="entity" name="item._entity" value="com.bos.ews.earlyWarning.WarningLevelAdjust" class="nui-hidden" />
	<input type="hidden" name="item.partyId"  class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" style="text-align:center;width:80%;">
		<label >客户名称</label>
		<input name="item.partyName" required="false"  class="nui-text nui-form-input"  />

		<label >客户编号</label>
		<input name="item.partyNum" required="false" class="nui-text nui-form-input"  />

		<label >预警级别</label>
		<input name="item.earlyWarningLevelCd" id="warningLevel" enabled="false" class="nui-text nui-form-input"  style="width:100%;" dictTypeId="XD_YJCD0004" />

		<label >认定日期</label>
		<input name="item.confirmDate"  class="nui-text " style="width:100%;" enabled="false"/>

		<label >本次预警调整级别</label>
		<input  required="false" id="level" name="item.level" onvaluechanged="valueChange()" required="false"   class="nui-text nui-form-input" style="width:100%;" dictTypeId="XD_YJCD0004"  />

		<label >预警级别上调/下调</label>
		<input  name="item.updateDirection" id="adjustDirection"  required="false" enabled="false" class="nui-text nui-form-input" style="width:100%;" dictTypeId="XD_YJCD0005" />
    </div>
    <div id="shenqing" style="margin-top:40px;width:100%;">
        <table   style="width:90%;height:auto;border:1px solid gray;font-size: 12px;text-align:center;" >
            <tr >
                <td  style="width:20%;background-color:#cccccc;">预警级别调整理由</td>
                <td  style="width:80%;border:1px solid gray;"><input name="item.applySuggest" required="true" class="nui-textarea nui-form-input" style="width:100%;" /></td>
            </tr>
        </table>
    </div>
</div>
</body>

<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		//var grid = nui.get("grid1");
		var corpid = "<%=request.getParameter("corpid") %>";
		var action="<%=request.getParameter("action") %>";//是否为弹出框子页面
	    var date;
	    nui.get("btnSave").setEnabled(false);
	    git.mask();
	    
/*初始化方法*/
function initForm() {
      // var entity=nui.get("entity").value;
       var json=nui.encode({"partyId":corpid});
       $.ajax({
            url: "com.bos.ews.csmWarnLevleInfo.queryCsmWarnLevel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		var text = nui.decode(text);
            	    //alert(text.item.partyName);
               		form.setData(text);                                   //加载数据
                    //grid.load({partyId:corpid});                          //加载数据
                    nui.get("adjustDirection").setValue("");
		            git.unmask();                                         //取消表单遮罩效果
                   //date=text.date;
                   // nui.get("adjustDate").setValue(date);
                    }
                    //git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	 });
	
            		
}
initForm();

/*提交表单数据修改*/
function save(){
  
     form.validate();
     if (form.isValid()==false) {
        alert("请填写调整理由。");
        return;
     }
    
   
   var result=form.getData();
   var json=nui.encode(result);
  
   $.ajax({
            url: "com.bos.ews.csmWarnLevleInfo.addLevelAdjust.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
               if(action=="open"){
            	CloseWindow();
            	}else{
            	initForm();
            	}
            	
            },
             error: function (text) {
                //alert("111");
                nui.alert(text.msg);
                
            }
	});
}

/**
  根据用户选择的调整级别判断，如果用户未选择调整级别或者是调整级别与原级别相同，
  则不能进行级别调整的申请发起。
*/
function valueChange(){

     var level=nui.get("level").value;
     var oldLevel=nui.get("warningLevel").value;
  if(!level){
      nui.get("btnSave").setEnabled(false);
  }else{
     if(!oldLevel){
       nui.get("adjustDirection").setValue("01");
       nui.get("btnSave").setEnabled(true);
     }else if(oldLevel<level){
       nui.get("adjustDirection").setValue("02");
       nui.get("btnSave").setEnabled(true);
     }else if(oldLevel==level){
       nui.get("btnSave").setEnabled(false);
     }else{
      
       nui.get("adjustDirection").setValue("01");
        nui.get("btnSave").setEnabled(true);
     } 
     
       }
    
}

</script>
</html>
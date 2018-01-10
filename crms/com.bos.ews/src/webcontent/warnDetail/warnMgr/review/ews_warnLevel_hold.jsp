<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-25 10:31:55
  - Description:预警信号关闭
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>预警级别认定</title>
</head>
<body>
		
<fieldset>
  	<legend>
   		<span>预警级别认定</span>
    </legend>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<input type="hidden" name="csmWarnLevel" class="nui-hidden" />
	<input type="hidden" name="csmWarnLevel.partyId"  class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" style="text-align:center;width:80%;">
		<label >客户名称</label>
		<input name="csmWarnLevelInfo.partyName" required="false"  class="nui-text nui-form-input" style="width:100%;" />

		<label >客户编号</label>
		<input name="csmWarnLevelInfo.partyNum" required="false" class="nui-text nui-form-input" style="width:100%;" />

		<label >原预警级别</label>
		<input name="csmWarnLevelInfo.warningLevelCd" id="warningLevel" enabled="false" class="nui-dictcombobox nui-form-input"  style="width:100%;" dictTypeId="XD_YJCD0004" />

		<label >原级别认定日期</label>
		<input name="csmWarnLevelInfo.confirmDate"  class="nui-datepicker " style="width:100%;" enabled="false"/>

		<label >本次预警调整级别</label>
		<input  required="false" id="level" name="level" textField="text" valueField="id" class="nui-combobox nui-form-input" style="width:100%;" emptyText="--请选择--" />

		<!-- <label >预警级别上调/下调</label> onvaluechanged="valueChange()" 
		<input  name="updateDirection" id="adjustDirection"  required="false" enabled="false" class="nui-dictcombobox nui-form-input" style="width:100%;" dictTypeId="XD_YJCD0005" />
         -->
    </div>
    
</div>
</fieldset>

<div class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 10px;">
	<a class="nui-button" iconCls="icon-save" onclick="update()" id="btnSave">保存</a>
</div>


</body>
<script type="text/javascript">
	nui.parse();
	    var form = new nui.Form("#form1"); 
		var bizId = "<%=request.getParameter("bizId") %>";  //获取预警情况变更ID
	    var countrys=[{ "id": "01", "text": "红色一级预警" },{ "id": "02", "text": "红色二级预警" },
	                  { "id": "03", "text": "橙色一级预警" },{ "id": "04", "text": "橙色二级预警" },{ "id": "05", "text": "黄色一级预警" },
	                  { "id": "06", "text": "黄色二级预警" },{ "id": "07", "text": "常规预警" }];    //预警级别信息
/*初始化方法*/
function initForm() {
       git.mask();
      // var entity=nui.get("entity").value;
       var json=nui.encode({"bizId":bizId});
       $.ajax({
            url: "com.bos.ews.csmWarnLevel.getCsmWarnLevelInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	 var text = nui.decode(text);
            	  nui.get("level").setData(countrys);
            	  form.setData(text);                                   //加载数据
            	if(text.level){
            		 nui.get("level").setValue(text.level);
            	     git.unmask();
            	    }
            },
            error: function (jqXHR, textStatus, errorThrown) { nn
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	 });
	git.unmask();
            		
}
initForm();
/*
function valueChange(){
     
     var level=nui.get("level").value;
     var oldLevel=nui.get("warningLevel").value;
     if(!oldLevel){
       nui.get("adjustDirection").setValue("01");
       //nui.get("btnSave").setEnabled(true);
     }else if(oldLevel<level){
       nui.get("adjustDirection").setValue("02");
       //nui.get("btnSave").setEnabled(true);
     //}else if(oldLevel==level){
       //nui.get("btnSave").setEnabled(false);
     }else{
       nui.get("adjustDirection").setValue("01");
       // nui.get("btnSave").setEnabled(true);
     } 
     
}
*/
function update(){
    git.mask();
    var level=nui.get("level").value;
    
    if(level=="00"){
       alert("请选择预警级别！");
        git.unmask();
    return;
    }
    //var updateDirection=nui.get("adjustDirection").value;,updateDirection:updateDirection
    var json=nui.encode({level:level,bizId:bizId});
   $.ajax({       
            url: "com.bos.ews.warnMgr.updateLevelInFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                
                nui.alert(text.msg);
                git.unmask();
                
            },
             error: function (text) {
                //alert("111");
                nui.alert(text.msg);
                git.unmask();
                
            }
	});
}      



	</script>
</html>

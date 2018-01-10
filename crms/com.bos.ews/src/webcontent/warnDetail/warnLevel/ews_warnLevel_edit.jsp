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

<fieldset>
  	<legend>
   		<span>预警级别调整</span>
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
		<input  required="false" id="level" name="level" textField="text" valueField="id" class="nui-combobox nui-form-input" emptyText="--请选择--" style="width:100%;" />

		<!-- <label >预警级别上调/下调</label> onvaluechanged="valueChange()" 
		<input  name="updateDirection" id="adjustDirection"  required="false" enabled="false" class="nui-dictcombobox nui-form-input" style="width:100%;" dictTypeId="XD_YJCD0005" />
         -->
    </div>
    
</div>
</fieldset>
<div class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 10px;">
	    <a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">确认</a>
</div>

</body>

<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var corpid = "<%=request.getParameter("corpid") %>";            //获取客户编号
		var bizId = "<%=request.getParameter("bizId") %>";  //获取预警情况变更ID
		var readOnly="<%=request.getParameter("readOnly") %>";
	    git.mask();
	    var processInstId="<%=request.getParameter("processInstId") %>";
	    var countrys;
	    if(readOnly=="1"){
	      form.setEnabled(false);
	      nui.get("btnSave").hide();
	    }
/*初始化方法*/
function initForm() {
     
      // var entity=nui.get("entity").value;
       var json=nui.encode({"partyId":corpid,"bizId":bizId});
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
            	
                var level = text.csmWarnLevelInfo.warningLevelCd;
                switch(level){
                    case "01":
                           countrys=[{ "id": "01", "text": "红色一级预警" },{ "id": "03", "text": "橙色一级预警" },{ "id": "05", "text": "黄色一级预警" },
	                  { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }]; 
                           break;
                    case "02":
                           countrys=[{ "id": "02", "text": "红色二级预警" },{ "id": "04", "text": "橙色二级预警" },{ "id": "06", "text": "黄色二级预警" },
                      { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }];    //预警级别信息
                           break;
                    case "03":
                           countrys=[{ "id": "03", "text": "橙色一级预警" },{ "id": "05", "text": "黄色一级预警" },
	                  { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }]; 
                           break;
                    case "04":
                           countrys=[{ "id": "04", "text": "橙色二级预警" },{ "id": "06", "text": "黄色二级预警" },
                      { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }];    //预警级别信息
                           break;
                    case "05":
                           countrys=[{ "id": "05", "text": "黄色一级预警" },
	                  { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }]; 
                           break;
                    case "06":
                           countrys=[{ "id": "06", "text": "黄色二级预警" },
                      { "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }];    //预警级别信息
                           break;
                    case "07":
                           countrys=[{ "id": "07", "text": "常规预警" },{ "id": "08", "text": "无预警" }]; 
                           break;
                };
                 nui.get("level").setData(countrys);
                 if(text.level){
            		 nui.get("level").setValue(text.level);
            	     git.unmask();
            	    }   
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	 });
	 
	git.unmask();
            		
}
initForm();

/*提交表单数据修改*/
function save(){
    var level=nui.get("level").value;
    if(level=="00"){
    alert("请选择预警级别！");
    git.unmask();
    return;
    }
   // var updateDirection=nui.get("adjustDirection").value; ,updateDirection:updateDirection
    var levelInfo ={level:level};
    var json=nui.encode({levelInfo:levelInfo,bizId:bizId,processInstId:processInstId});
   $.ajax({       
            url: "com.bos.ews.csmWarnLevel.editLevelAdjust.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                nui.alert(text.msg);
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

function valueChange(){

     var level=nui.get("level").value;
     var oldLevel=nui.get("warningLevel").value;
  //if(!level){
     // nui.get("btnSave").setEnabled(false);
  //}else{
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
     
       //}
    
}*/

</script>
</html>
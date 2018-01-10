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

<fieldset>
  	<legend>
    	<span>待关闭的预警信号</span>
    </legend>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"  dataField="csmWarnInfo"
		allowResize="true" showReloadButton="false" showPageSize="false" url="com.bos.ews.warnInfo.queryWarnInfo.biz.ext"  pageSize="20" multiSelect="true" sortMode="client">
		 <div property="columns">
			 <div field="chooise" type="checkcolumn" ></div>
			 <div field="csmSignalId" checked="true" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true">预警信号 </div>
	         <div field="csmwarningtypeid" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true">预警信号类别 </div>
	         <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001">预警信号来源 </div>
	         <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号认定日期 </div>
	         <div field="closeDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号关闭日期 </div>
	         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002">预警信号状态 </div>
	         <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
		 </div>
	</div>
</fieldset>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;margin-top:10px;">
  <input id="tbRewLevelAdjust.oldEarlyWarningLevelCd" name="tbRewLevelAdjust.oldEarlyWarningLevelCd" class="nui-hidden" dictTypeId="XD_YJJB0001"/>
	<div id="level" class="nui-dynpanel" columns="4" >
<!-- 	     <label class="nui-form-label">原级别：</label>
 -->		
		 <!--<label class="nui-form-label">下调级别：</label> -->
	     <label class="nui-form-label">预警级别：</label>
		 <input id="tbRewLevelAdjust.earlyWarningLevelCd" required="true" name="tbRewLevelAdjust.earlyWarningLevelCd" class="nui-combobox" data="warnLevel"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-save" onclick="update()" id="btnSave">确认</a>
	</div>
</div>



</body>
<script type="text/javascript">
	var warnLevel = [
		{ id: '1', text: '红色预警'}, 
		{ id: '2', text: '橙色预警'},
		{ id: '3', text: '黄色预警'},
		{ id: '0', text: '不下调'}
		];
	 	nui.parse();
	 	git.mask();
		var form = new nui.Form("#form");
		var grid = nui.get("grid1");
		var corpid = "<%=request.getParameter("corpid") %>";
	    var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	    var bizId = "<%=request.getParameter("bizId") %>";  
	    grid.load({"partyId":corpid,"status":"2,5"}); 
	    var col = grid.getColumn("chooise");
	    git.unmask();                                                             //取消表单遮罩效果
		//初始化方法	    
		function initForm() {
		     var json=nui.encode({partyId:corpid,bizId:bizId});
		     $.ajax({
		            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (item) {
		                    nui.get("tbRewLevelAdjust.oldEarlyWarningLevelCd").setValue(item.tbRewLevelAdjust.oldEarlyWarningLevelCd);
		                    nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue(item.tbRewLevelAdjust.earlyWarningLevelCd);
		            	    grid.load({"partyId":corpid,"status":"2,5"});
		                    git.unmask();
		             },
		            error: function (text) {
		                git.unmask();
		            }
			 });
			
				//proFlag 1发起人 0非发起人 -1流程详情
				if("1" != proFlag){
					grid.hideColumn(grid.getColumn(0));
					nui.get("btnSave").hide();
					form.setEnabled(false);
				}
				
		}
		initForm();
 
	grid.on("load",function(e){
		for (var i=0; i<e.data.length; i++){
			if(e.data[i].signalStatusCd == "5"){
				 grid.select(grid.getRowByUID(e.data[i]._uid));
			}
    	}
     });

	/*提交表单数据修改*/
	function update(){
	    
	    var rows = grid.getSelecteds();
	    var oldEarlyWarningLevelCd= nui.get("tbRewLevelAdjust.oldEarlyWarningLevelCd").getValue(); 
	    var earlyWarningLevelCd= nui.get("tbRewLevelAdjust.earlyWarningLevelCd").getValue(); 
	    
	    form.validate();
        if (form.isValid()==false){
        	 alert("请按规则填写信息！");
        	 return;
        }
	    if( (earlyWarningLevelCd=="" || earlyWarningLevelCd=="0") && rows.length == 0){
		    alert("请选择要关闭的信号或更改预警级别！");
		    return;
	    }
	    if(oldEarlyWarningLevelCd == "0" && earlyWarningLevelCd != "0"){
	    	alert("预警级别还没有认定，不能进行级别调整！");
	    	return;
	    }
	    
	    //1>2>3>0不认定
	    if(oldEarlyWarningLevelCd == ""  || oldEarlyWarningLevelCd == null){
	         oldEarlyWarningLevelCd = "0";
	    }
	    if(earlyWarningLevelCd != "0" && earlyWarningLevelCd <= oldEarlyWarningLevelCd) {
	    	if(oldEarlyWarningLevelCd == "0"){
	    		oldEarlyWarningLevelCd = "不下调";
	    	}else{
	    		oldEarlyWarningLevelCd = oldEarlyWarningLevelCd;
	    	}
	    	if(oldEarlyWarningLevelCd=="1"){
	    		alert("只能调低级别或不认定，原级别【红色预警】！");
	    	}
	    	if(oldEarlyWarningLevelCd=="2"){
	    		alert("只能调低级别或不认定，原级别【橙色预警】！");
	    	}
	    	if(oldEarlyWarningLevelCd=="3"){
	    		alert("只能调低级别或不认定，原级别【黄色预警】！");
	    	}
		    return;
	    }
	  
	     $.ajax({
	            url:"com.bos.ews.warnInfo.closeWarnInfo.biz.ext",
	            type:'POST',
	            data: nui.encode({"warnInfos":rows,status:3,"corpid":corpid,"earlyWarningLevelCd":earlyWarningLevelCd,"bizId":bizId}),
	            cache:false,
	            contentType:'text/json',
	            success:function(text){
	               alert(text.msg);
	               //var node=text.node;//弹出审批页面
	               //openSubmitView(node); 
	               initForm();
	               git.unmask();
	               
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	                git.unmask();
	            }
	     });
	     git.unmask();
	     
		}      

	</script>
</html>
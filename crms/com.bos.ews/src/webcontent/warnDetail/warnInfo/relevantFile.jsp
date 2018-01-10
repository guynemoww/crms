<%@page pageEncoding="UTF-8"%> 
<html>
<!-- 
  - Author(s): 叶奔
  - Date: 2014-04-04 15:43:13
  - Description:
-->
<head>
<title>上传文件页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;margin-top:23px;">
		<input id="bizNum" class="nui-hidden nui-form-input" name="bizNum"/>
	  <fieldset>
	  	 <legend>
	    	<span>预警事项描述</span>
	     </legend>
	     <input id="matter" class="nui-textarea nui-form-input"  name="matterState" style="height:150px;width:100%"/>
	   </fieldset>
	   
	   <fieldset>
	  	 <legend>
	    	<span>拟采取的控制措施及建议</span>
	     </legend>
	     <input id="suggestState" class="nui-textarea nui-form-input"  name="suggestState" style="height:150px;width:100%" />
	   </fieldset>
	   
		<div class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 7px;">
		     <a class="nui-button" iconCls="icon-save" onclick="startFlow()" id="btnSave">保存</a>
		</div>
	</div>

</body>			
<script type="text/javascript">
 		nui.parse();
	 	var form3 = new nui.Form("#form3");                                   //预警报告表单
	 	var viewFlag=<%=request.getParameter("viewFlag")%>;
	 	var button = "<%=request.getParameter("button") %>";
	 	var party = <%=request.getParameter("party") %>;
	 	var corpType = <%=request.getParameter("corpType") %>;                             //客户种类 1：小企业 2：大中企业 
	 	var processInstId=<%=request.getParameter("processInstId") %>;
	 	var bizId = "<%=request.getParameter("bizId") %>";
	 	var node = <%=request.getParameter("node") %>;
	 	var noEditLevel = "<%=request.getParameter("noEditLevel") %>";
	 	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	    
	   function init(){
		   var json = nui.encode({bizId:bizId});
		    $.ajax({
	            url: "com.bos.ews.util.getParty.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (text) {
	               var adjust = text.adjust;
		           form3.setData(adjust);
		        },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	           });
	           
	           //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			if("null" != proFlag && "1" != proFlag){
				nui.get("btnSave").hide();
				form.setEnabled(false);
			}
	           
	    }
	    init();
	    
	    
	 	function startFlow(){
	 	       var result2;
	 	       
	           form3.validate();
	 	       if(form3.isValid()==false){
                    alert("请完成预警报告的填写！");
                    return;
                 }else{
                    result2 = form3.getData();                                  //获取表单数据
                 }
               var json=nui.encode({"corpid":party.partyId,"otherData":result2,"bizId":bizId,"noEditLevel":noEditLevel});
                $.ajax({                                                         
                      url: "com.bos.ews.warnInfo.saveWarnReport.biz.ext",
                      type: 'POST',
                      data: json,
                      cache: false,
                      contentType:'text/json',
                      success: function (text) {
                               var msg = text.msg;
                               var error = text.errorMsg;
                                   if(error){
                                      alert(error);
                                   }else{
                                      alert(msg);
                                   }
                                    
                      },
                      error: function (text) {
                               nui.alert(text.msg);
                               git.unmask();
                             }
	                  });
	 		}

        
</script>
</html>
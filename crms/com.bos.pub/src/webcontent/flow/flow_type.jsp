<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-10
  - Description:TB_SYS_FLOW_TEST, com.bos.pub.sys.TbSysFlowTest
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" >
	<div class="nui-dynpanel" columns="2">
	
		<label>测算类型：</label>
		<input id="flow" name="" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD03932" onvaluechanged="flowC"/>

		<label>测算日期：</label>
		<input id="moth" name="moth" required="false" class="nui-datepicker nui-form-input"  />


	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">确定</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">取消</a>
</div>
	    
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    initPage();
    function initPage(){
    	var date = new Date();
    	var year = date.getYear()-1;
    	year = year+"-12-31";
    	nui.get("moth").setValue(year);
    }
	function flowC(){
		var flow,moth;
		flow=nui.get("flow").getValue();
		moth=nui.get("moth");
		if(flow=="01"){//自动测算
			initPage();
			nui.get("flow").setValue("01");
			nui.get("moth").setEnabled(false);
		}else{
			nui.get("moth").setEnabled(true);
		}
	}   
	function save() {
		var flow,moth;
		flow=nui.get("flow").getValue();
		moth=nui.get("moth");
		if(flow=="01"){//自动测算
		 	moth.setRequired(true);
		}else{
			moth.setRequired(false);
		}
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var urlString;
		if(nui.get("flow").getValue()=="01"){//自动
			var date = new Date();
	    	var year = date.getYear()-2;
	    	date0 = year+"-12-31";
			var json = nui.encode({"partyId":"<%=request.getParameter("partyId") %>","date":nui.get("moth").getValue(),"date0":date0});
		    $.ajax({
		        url: "com.bos.pub.flow.getCustFinance.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	if("1" == mydata.msg){
		        		alert("自动测算需最近两年新会计准则企业类财报信息，请补充后再进行自动测算");
		        		return;
		        	}
    				nui.open({
			            url: nui.context+"/pub/flow/flow_add1.jsp?flow="+nui.get("flow").getValue()+"&moth="+nui.get("moth").getValue()+"&applyId=<%=request.getParameter("applyId") %>&partyId=<%=request.getParameter("partyId") %>",
			            title: "新增", 
			            width: 850, 
			        	height: 400,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                if(action=="ok"){
			                    CloseWindow("ok");
			                }
			            }
			        });
		        }
			})
		}else{//手动
			nui.open({
		            url: nui.context+"/pub/flow/flow_add.jsp?flow="+nui.get("flow").getValue()+"&moth="+nui.get("moth").getValue()+"&applyId=<%=request.getParameter("applyId") %>&partyId=<%=request.getParameter("partyId") %>",
		            title: "新增", 
		            width: 850, 
		        	height: 400,
		        	allowResize:true,
		        	showMaxButton: true,
		            ondestroy: function (action) {
		                if(action=="ok"){
		                    CloseWindow("ok");
		                }
		            }
		        });
		}
	   
	}
</script>
</body>
</html>

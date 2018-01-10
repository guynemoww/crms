<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品出库</title>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="3">
			<label>请输入出库原因：</label>
			<input id="outReason" name="outReason" required="true" 
			class="nui-combobox nui-form-input"  valueField="dictId" textField="dictName" />	
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
	  		<a class="nui-button" iconCls="icon-save" id="btnsave" onclick="save()">确定</a>
		</div>
		</div>
	</div>
				
	<script type="text/javascript">
	 	nui.parse();
    	var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		var SURETY_KEY_ID = "<%=request.getParameter("SURETY_KEY_ID") %>";
	  	var partyId =  "<%=request.getParameter("partyId") %>";
	 	 var json = nui.encode({parentId:"21",typeId:"XD_SXFS0004"});
		     $.ajax({
		        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: true,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("outReason").setData(text.levels);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
		     
		function save(){
			form.validate();
	        if (form.isValid()==false) {
	        	nui.alert("请完整填写信息！");
	        	return;
	        }
			var outReason = nui.get("outReason").value;
			
			var json=nui.encode({"regCards":{"SURETY_KEY_ID":SURETY_KEY_ID},"partyId":partyId,"outType":"21","reason":outReason});
			$.ajax({
	        	url: "com.bos.grt.regmanage.collateralout.collateralOutStorage.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		if(text.msg !=null){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		return;
	            	}
	        		var o = nui.decode(text);
	        		nui.open({
            			url: nui.context+"/grt/grt_pro/grt_tree.jsp?processInstId="+o.processInstId+"&outId="+o.grtOut.outId+"&proFlag=1&isSrc=2",
            			title: "查看押品", 
            			width: 1200, 
        				height: 600,
        				allowResize:false,
    	    			showMaxButton: false,
	            		ondestroy: function (action) {
	            			CloseWindow("ok");
            	  		}
        			});
	        		
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		
		
		
		
		
	
			
		}
		
	
	</script>
</body>
</html>
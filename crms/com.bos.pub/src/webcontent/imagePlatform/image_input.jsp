<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1"  style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" style="border:none" columns="2">
			<label>业务编号：</label>
			<input id="businessNumber" name="businessNumber" class="nui-textbox nui-form-input" required="true"  />
		</div>
		<div class="nui-toolbar" style="text-align:center;" borderStyle="border:0;">
		    <a class="nui-button" onclick="sunScan(0)" id="pict">补录影像资料</a>
		</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
    //补录影像资料
    function sunScan(v){
    
    	form.validate();
        if (form.isValid()==false) return;
        
    	//获取业务编号
    	var businessNumber = nui.get("businessNumber").getValue();
    	
    	var json =nui.encode({"businessNumber":businessNumber});
    	$.ajax({
            url: "com.bos.pub.image.getImageConfData.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if('1'==text.rtcode){
            	
            		nui.alert("根据业务编号未找到记录，或者当前操作人员权限不够。请核对业务编号以及登陆机构是否有权限补录。");    
            	}else if('0'==text.rtcode){
            	
            		nui.open({
		                url:nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+businessNumber+'&csmNum='+text.map.partyNum+'&partyTypeCd=01&flowModuleType='+text.map.flowModuleType,
		                title: "补录影像信息", 
		                width: 1200,
		        		height: 600,
		        		state:"max",
		                allowResize:true,
		        		showMaxButton: true,
		                onload: function () {
		                },
		                ondestroy: function (action) {
		                }
	            	});
            		
            	}else{
					nui.alert("系统异常，请联系管理员！");            	
            	}
            },
            error: function () {
            	nui.alert("操作失败！");
            }
        });
    }
    
    //打开影像补录页面
    function showImageWins(){
    
       var imageTypeId = nui.get("item.imageTypeId").getValue();
       var imageTypeName = nui.get("item.imageTypeName").getValue();
       var url =nui.context + "/pub/imagePlatform/imageScan.jsp?serialNo="+imageTypeId+"&imageTypeName="+imageTypeName+"&view=0";
       nui.open({
           	url:url,
            title: "补录影像资料", 
            width: 1024, 
        	height: 768,
        	allowResize:false,
        	showMaxButton: false,
        	showCloseButton: false,
        	state:"max",
            ondestroy: function (action) {
              if("submit"==action){
              
            	//获取子窗口对象
            	var iframe = this.getIFrameEl();
	            //获取影像张数
	            var data = iframe.contentWindow.getData();
	            //设置批次号
	            nui.get("item.barCode").setValue(data.batchId);
	            
	            var retcode = data.retcode;
	            if('0' != retcode){
	            	
	            	nui.alert("影像提交失败！");
	            }else{
	            
		            //更新影像张数
                	updateImageNum(data.images);
	            }
              }  	
            }
    	});
    }
    
    function updateImageNum(nums){
    
    	var data = form.getData(); //获取表单多个控件的数据
    	data["map"]=nums;
    	var json=nui.encode(data);
        $.ajax({
            url: "com.bos.pub.image.saveImageNum.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	alert("操作成功!");
            },
            error: function () {
            	nui.alert("操作失败！");
            }
        });
    }
</script>
</body>
</html>

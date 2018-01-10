<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_PACKAGE, com.bos.pub.decision.TbPubGrantPackage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input  id="item.isSrc" name="item.isSrc" class="nui-hidden" value="1" />
	<div class="nui-dynpanel" columns="4">
		<label>影像类型代码：</label>
		<input name="item.imageTypeId" id="imageTypeId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false" />

		<label>影像类型名称：</label>
		<input name="item.imageTypeName" required="true" class="nui-textbox nui-form-input"  />
		
		<label>影像类型父节点代码：</label>
		<input name="item.superiorId" id="item.superiorId" required="false" class="nui-textbox nui-form-input" allowInput="false" enabled="false"/>
		
		<label>影像模板类型：</label>
		<input name="item.imageModelType" id="imageModelType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD9998" enabled="false" />
		
		<%--<label>适用岗位：</label>
		<input name="item.imageControlType" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectPosition"/>
		--%>
		<label>是否叶节点：</label>
		<input name="item.imageRequireStatus" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
		
		<label>资料类型：</label>
		<input name="item.flowModuleType" required="false" class="nui-buttonEdit nui-form-input"  onbuttonclick="selectFlowModuleType"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var  superiorId = "<%=request.getParameter("superiorId") %>";
	    var  imageModelType = "<%=request.getParameter("imageModelType") %>";
	    if(null != superiorId && ''!=superiorId && 'null'!=superiorId){
	    
	    	nui.get("item.superiorId").setValue(superiorId);
	    }
	    nui.get("imageModelType").setValue(imageModelType);
	    //初始化影像节点ID
	    function init(){
	    
	    	if(null == superiorId || ''==superiorId  || 'null'==superiorId){
	    		if('01'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1010");
	    		}else if('02'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1020");
	    		}else if('03'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1030");
	    		}else if('04'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1040");
	    		}else if('05'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1050");
	    		}else if('06'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1060");
	    		}else if('07'==imageModelType){
	    		
	    			nui.get("imageTypeId").setValue("1070");
	    		}
	    	
	    	}else{
	    
				var json=nui.encode({"item":{"superiorId":superiorId}});
				$.ajax({
			            url: "com.bos.pub.image.getMaxCode.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
					    	
					    	//判断是否查询到子节点编号
					    	if(text.items[0]==null){//没有查到
					    		//父节点ID后拼接1
					    		nui.get("imageTypeId").setValue(superiorId+'1');
					    	}else{//查到了
					    	
					    		var tempid = text.items[0].imageTypeId;
					    		tempid =parseInt(tempid)+1;
					    		//var val_pex = tempid.substring(0,tempid.length-1);
					    		//var val_psx = tempid.substring(tempid.length-1);
					    		//val_psx = (Number)(val_psx)+1;
					    		nui.get("imageTypeId").setValue(tempid);
					    	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			            }
				});	
			}	
	    }
	    init();
	    
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
		            url: "com.bos.pub.image.addImageType.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		            		CloseWindow("ok");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
			});
		}


		function selectPosition(e) {
	    	var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/imagePlatform/imageType/select_managed_model.jsp",
	            showMaxButton: false,
	            title: "选择适用岗位",
	            width: 400,
	            height: 450,
	            onload:function(){
	                var ids = btnEdit.getValue();
	                var texts = btnEdit.getText();
	                var data = {
	                   parentNode: window['parentNode'],
	                   ids:ids,
	                   texts:texts
	                };
	                var iframe = this.getIFrameEl();
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.id);
	                        btnEdit.setText(data.text);
	                    }
	                }
	            }
	        });            
	    }
	    function selectFlowModuleType(){
	    		var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/imagePlatform/imageType/select_managed_model1.jsp",
	            showMaxButton: false,
	            title: "选择展示阶段",
	            width: 400,
	            height: 450,
	            onload:function(){
	                var ids = btnEdit.getValue();
	                var texts = btnEdit.getText();
	                var data = {
	                   parentNode: window['parentNode'],
	                   ids:ids,
	                   texts:texts
	                };
	                var iframe = this.getIFrameEl();
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.id);
	                        btnEdit.setText(data.text);
	                    }
	                }
	            }
	        });     
	    }	
	</script>
</body>
</html>

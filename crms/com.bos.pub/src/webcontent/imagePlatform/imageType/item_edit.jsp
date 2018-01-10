<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ljf
  - Date: 2015-06-11

  - Description:影像编辑
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" id="item._entity" value="com.bos.dataset.pub.TbPubImageType" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>影像类型代码：</label>
		<input name="item.imageTypeId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>影像类型名称：</label>
		<input name="item.imageTypeName" required="true" class="nui-textbox nui-form-input"  />
		
		<label>影像类型父节点代码：</label>
		<input name="item.superiorId" id="item.superiorId" required="false" class="nui-textbox nui-form-input" allowInput="false" enabled="false"/>

		<label>影像模板类型：</label>
		<input name="item.imageModelType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD9998" enabled="false"/>
		
		<%--<label>适用岗位：</label>
		<input name="item.imageControlType" id="imageControlType" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectPosition"/>
		--%>
		<label>是否叶节点：</label>
		<input name="item.imageRequireStatus" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
		
		<label>业务阶段：</label>
		<input name="item.flowModuleType" id="flowModuleType" required="false" class="nui-buttonEdit nui-form-input" dictTypeId="XD_GG20099" onbuttonclick="selectFlowModuleType"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		var _entity = nui.get("item._entity").value;
		
function initForm() {
	var json=nui.encode({"item":{"imageTypeId":"<%=request.getParameter("itemId") %>","_entity":_entity}});
	$.ajax({
            url: "com.bos.pub.image.getItem.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		//nui.get("imageControlType").setText(text.item.imageControlType);
            		nui.get("flowModuleType").setText(text.item.flowModuleType);
            		form.setData(text);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
initForm();

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
        url: "com.bos.pub.image.saveItem.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        		$.ajax({
			            url: "com.bos.pub.image.saveImageTypeToImagePlatform.biz.ext",
			            type: 'POST',
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

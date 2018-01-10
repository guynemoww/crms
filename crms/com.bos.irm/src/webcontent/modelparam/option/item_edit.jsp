<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-14

  - Description:TB_IRM_ADJUST_OPTION, com.bos.dataset.irm.TbIrmAdjustOption-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbIrmAdjustOption" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
			<label>选项类别：</label>
		<input name="tbIrmAdjustOption.adjustTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0014" vtype="maxLength:6" onvaluechanged="adjustTypeCdChanged"/>

	
		<label>调整类型：</label>
		<input name="tbIrmAdjustOption.optionTypeCd" id="optionTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0010" vtype="maxLength:6" onvaluechanged="optionTypeCdChanged"/>
		
		
		<label>调整级别：</label>
		<input name="tbIrmAdjustOption.adjustSeries" id="adjustSeries" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:6" onvaluechanged="adjustSeriesChanged" />
		
		<label>最小分值：</label>
		<input name="tbIrmAdjustOption.minValue" id="minValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:16" />

		<label>最大分值：</label>
		<input name="tbIrmAdjustOption.maxValue" id="maxValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:16" />
		
		<label>调整顺序：</label>
		<input name="tbIrmAdjustOption.adjustOrder" required="true" class="nui-textbox nui-form-input" vtype="maxLength:16" />
		<label>调整选项描述：</label>
		<input colspan="3" width="500" name="tbIrmAdjustOption.adjustOptionDescription" required="true" class="nui-textarea nui-form-input" vtype="maxLength:200" />
		
		<label>适用模型编号：</label>
		<input name="tbIrmAdjustOption.modelNumber" id="modelNumber" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectModel" />

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
	git.mask();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbIrmAdjustOption":
		{"aoId":
		"<%=request.getParameter("aoId") %>"}});

	$.ajax({
        url: "com.bos.irm.param.getTbIrmAdjustOption.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        
        	nui.get("modelNumber").setText(text.tbIrmAdjustOption.modelNumber);
        		form.setData(text);
        	}
        	git.unmask();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
            git.unmask();
        }
	});
}
initForm();


function adjustSeriesChanged(e){
	//	var optionTypeCd =nui.get("optionTypeCd").getValue(); 
	//if((optionTypeCd=='01'||optionTypeCd=='02')&&e.value=='03'){
	//	alert("请选择正确的级别");
	//	return;
	//}
	
}
	
function optionTypeCdChanged(e){
	
	//if(e.value=='03'){
	//	nui.get("adjustSeries").setText("违约级");
	//	nui.get("adjustSeries").setValue("03");
	//	nui.get("adjustSeries").setEnabled(false);
	//}else{
	//	nui.get("adjustSeries").setEnabled(true);
	//}
}
    
function adjustTypeCdChanged(e){
	
	if(e.value !='04'){
		
		nui.get("minValue").setEnabled(false);
		nui.get("maxValue").setEnabled(false);
		nui.get("minValue").setRequired(false);
		nui.get("maxValue").setRequired(false);
	}else{
		nui.get("minValue").setEnabled(true);
		nui.get("maxValue").setEnabled(true);
		nui.get("minValue").setRequired(true);
		nui.get("maxValue").setRequired(true);
	}
}	    
	    


function save() {
	git.mask();
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		git.unmask();
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.irm.model.updateTbIrmAdjustOption.biz.ext",
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
        	git.unmask();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
            git.unmask();
        }
	});
}
 function selectModel(e) {
    	var btnEdit = this;
        nui.open({
            url: nui.context + "/irm/modelparam/option/select_managed_model.jsp",
            showMaxButton: false,
            title: "选择调整选项适用模型",
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

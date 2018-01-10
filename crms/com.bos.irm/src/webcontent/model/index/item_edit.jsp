<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12

  - Description:TB_IRM_MODEL_INDEX, com.bos.dataset.irm.TbIrmModelIndex-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbIrmModelIndex" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
	<!--<label>指标类型：</label>-->
<!--		<input	id="type" name="type" required="false" class="nui-dictcombobox nui-form-input" required="true"  dictTypeId="XD_GGCD7702" onvaluechanged="typeChange" vtype="maxLength:80"/>-->

		<label>指标小类：</label>
		<input id="propertyTypeCd" name="tbIrmModelIndex.propertyTypeCd" required="false" data="data" valueField="dictID"  required="false" 
				 dictTypeId="" class="nui-dictcombobox nui-form-input" vtype="maxLength:80"/>
		<!--onvaluechanged="indexTypeChange" -->

		<label>指标名称：</label>
		<input id="indexName" name="tbIrmModelIndex.indexName" required="false"  required="false" dictTypeId=""
				 class="nui-dictcombobox nui-form-input" vtype="maxLength:80" />
		
		<label>指标描述：</label>
		<input name="tbIrmModelIndex.indexDesc" required="false" class="nui-textbox nui-form-input" vtype="maxLength:255" />

		<label>指标权重：</label>
		<input name="tbIrmModelIndex.indexWeight" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1");
    
    var types={
		'01':'XD_GGCD7701',
		'02':'XD_PJCD0019'
		
	};
	 var indextypes={
		'0101':'XD_GGCD7704',
		'0102':'XD_GGCD7705',
		'0103':'XD_GGCD7706',
		'0104':'XD_GGCD7707',
		'0201':'XD_GGCD7708',
		'0202':'XD_GGCD7709',
		'0203':'XD_GGCD7710'
	
	};

	
	//function typeChange(e){
	//	nui.get("indexType").changeDictTypeId(types[this.value]);
	//}
	//function indexTypeChange(e){
	//	nui.get("indexName").changeDictTypeId(indextypes[this.value]);
	//}
    
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbIrmModelIndex":
		{"indexId":
		"<%=request.getParameter("indexId") %>"}});
	$.ajax({
        url: "com.bos.irm.model.getTbIrmModelIndex.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
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

function save() {

	git.mask();
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.irm.model.updateTbIrmModelIndex.biz.ext",
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
	</script>
</body>
</html>

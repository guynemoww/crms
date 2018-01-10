<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12
  - Description:TB_IRM_INDEX_SCORE, com.bos.dataset.irm.TbIrmIndexScore
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<input type="hidden" name="tbIrmIndexScore.indexId"  value="<%=request.getParameter("indexId") %>"   class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">
		<label>档位选项：</label>
		<input name="tbIrmIndexScore.indexNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>档位分值：</label>
		<input name="tbIrmIndexScore.indexScore" required="true" class="nui-textbox nui-form-input"  />
		
		<label>档位计算逻辑：</label>
		<input name="tbIrmIndexScore.indexCalcLogic" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>档位描述：</label>
		<input name="tbIrmIndexScore.indexDesc" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />



	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
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
        url: "com.bos.irm.model.addTbIrmIndexScore.biz.ext",
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

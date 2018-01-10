<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-15

  - Description:TB_IRM_RATING_PARA, com.bos.dataset.irm.TbIrmRatingPara-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbIrmRatingPara" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">

		<label>评级参数类型：</label>
		<input name="tbIrmRatingPara.paraType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_PJCD0018" />

		<label>评级参数内容：</label>
		<input name="tbIrmRatingPara.paraContent" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		
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
	var json=nui.encode({"tbIrmRatingPara":
		{"ratingParaId":
		"<%=request.getParameter("ratingParaId") %>"}});
	$.ajax({
        url: "com.bos.irm.param.getTbIrmRatingPara.biz.ext",
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
		git.unmask();
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.irm.param.updateTbIrmRatingPara.biz.ext",
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

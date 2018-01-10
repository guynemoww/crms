<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-13
  - Description:TB_REPORT_USER_RELATION, com.bos.pub.sys.TbReportUserRelation
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		 
	<div class="nui-dynpanel" columns="2">
		
		<label>员工号：</label>
		<input id="userId" name="tbReportUserRelation.userId" required="false" class="nui-buttonEdit" vtype="maxLength:10" enabled="false"  dictTypeId="user"/>
		<label>报表权限设置：</label>
		<input id="menuIds" name="tbReportUserRelation.menuIds" required="true" class="nui-buttonEdit" vtype="maxLength:32" onbuttonclick="selectCodeList" />
		 
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    nui.get("userId").setValue("<%=request.getParameter("userId") %>");
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	$.ajax({
        url: "com.bos.pub.repPower.addTbReportUserRelation.biz.ext",
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
function initForm() {
	var json=nui.encode({"repEntity":
		{"userId":
		"<%=request.getParameter("userId") %>"}});
	$.ajax({
        url: "com.bos.pub.repPower.getTbReportUserRelation.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        
        	if(text.repEntitys.length==0){
        	} else {
        		var mId="";
        		var mName="";
        		for(var i=0;i<text.repEntitys.length;i++){
        		mId+=text.repEntitys[i].menuId+",";
        		mName+=text.repEntitys[i].menuName+",";
        		}
        		nui.get("menuIds").setValue(mId.substring(0,mId.length-1));
        		nui.get("menuIds").setText(mName.substring(0,mName.length-1));
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();
// 
function selectCodeList(e) {
         var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/reportList/repPower/repCode.jsp",
            showMaxButton: false,
            title: "权限菜单",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
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

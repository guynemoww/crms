<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 张琦
  - Date: 2014-04-1
  - Description:
-->
<head>
<title>授信品种及机构关联</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" class="nui-fit"  style="padding:0px 5px 0px 5px">

<input name="item.productId"  class="nui-hidden"  value="<%=request.getParameter("itemId") %>"/>
<div class="nui-dynpanel" columns="2">
      <label >机构：</label>
	   <input  name="item.orgNum"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" required="true"/>
		<label >授信品种名称：</label>
		<input name=""  allowInput="false" class="nui-buttonEdit"  required="true" enabled="false" value="<%=request.getParameter("productCd") %>" dictTypeId="product"/>
</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="cancel">取消</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	function save(){
		var data = form.getData();
        var json = nui.encode(data);
        nui.ajax({
            url: "com.bos.pub.product.saveOperate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
		            if(text.msg){
		        		alert(text.msg);
		        	} else {
		        		CloseWindow("ok");
		        	}
            },
            error: function () {
            	nui.alert("操作失败，请联系管理员");
            }
        });
	}
	
	function cancel(){
		CloseWindow("cancel");
	}
	
	function getData(id) {
		return nui.get(id).getValue();
	}
	
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
 
</script>

</body>
</html>
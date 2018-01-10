<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s):lujinbin
  - Date: 2013-12-30 10:39:23
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>添加</title>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>机构级别:</label>
		<input name="item.institutionLevel" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6004"/>

		<label>客户类型:</label>
		<input name="item.customerType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CustomerType "/>
		
		<label>资信自动预警参数:</label>
		<input name="item.creditwarningParameter" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CreditAutWarPar"/>
		
		<label>预警阀值:</label>
		<input name="item.warningThreshold" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200"/>
		
		<label>预警指标描述:</label>
		<input name="item.warningIndexdescribe"  required="false" class="nui-textbox nui-form-input" vtype="maxLength:200"/>
		
		
		<label>预警信号接收人：</label>
		<input id="item.userId" name="item.warningSignalreceiver" allowInput="false" class="nui-buttonEdit" dictTypeId="user"  
		onbuttonclick="selectCustManeger" required="true" requiredErrorText="接收人名称不能为空"/>
		
		<label>预警信号等级：</label>
		<input name="item.warningSignallevel" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="WarningSignalLevel" />

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
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
	    return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.earlyWarning.eariyWarningItem.addItem.biz.ext",
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

function selectCustManeger(e) {
           //var btnEdit = this;
        nui.open({
            url: nui.context + "/csm/group/groupCust_teamInfo_queryCust.jsp",
            showMaxButton: true,
            title: "选择接收人",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("item.userId").setValue(data.userId);
                    	//nui.get("item.orgId1").setValue(data.orgId);
                    	//nui.get("item.orgId").setValue(data.orgId);
                    	//nui.get("item.post").setValue(data.post);
                    	//nui.get("item.userRoleCd").setValue(data.post);
                       
                    }
                }
            }
        });   
        }
	</script>
</body>
</html>
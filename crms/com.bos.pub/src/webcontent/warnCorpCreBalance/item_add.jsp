<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-02-18
  - Description:TB_SYS_WARN_CORP_CRE_BALANCE, com.bos.dataset.sys.TbSysWarnCorpCreBalance
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysWarnCorpCreBalance" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>授信余额占比预警阀值：</label>
		<input name="item.balanceRatioLimit" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>合作方类型：</label>
		<input name="item.corpType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0188"/>

		<label>机构编码：</label>
		<input name="item.orgId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>预警指标描述：</label>
		<input name="item.warnIndexDesc" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>预警信号等级：</label>
		<input name="item.warnSignalLevel" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6"  dictTypeId="WarningSignalLevel" />

		<label>预警信号接收人：</label>
		<input id="item.userId" name="item.warnSignalReceiver" required="false" class="nui-buttonEdit" dictTypeId="user"  
		onbuttonclick="selectCustManeger" required="true" requiredErrorText="接收人名称不能为空" />
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
        url: "com.bos.pub.earlyWarning.addparameters.addItemWarnCorpCreBalance.biz.ext",
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

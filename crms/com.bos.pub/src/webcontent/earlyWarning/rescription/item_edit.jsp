<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): luchengchen@git.com.cn
  - Date: 2013-12-27
  - Description:TB_SYS_WARN_RESCRIPTION, com.bos.dataset.sys.TbSysWarnRescription
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysWarnRescription" class="nui-hidden" />
	<input type="hidden" name="item.rescriptId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4"/>
		<label>预警指标类型：</label>
		<input required="true" name="item.rescriptType" 
			class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType" />

		<label>预警指标编码：</label>
		<input name="item.rescriptCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:40" />

		<label>预警指标名称：</label>
		<input name="item.rescriptName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>预警阀值：</label>
		<input name="item.rescriptValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:40" />
		<label>预警阀值类型：</label>
		<input name="item.rescriptvalueType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"/>
		<label>预警阀值单位：</label>
		<input name="item.rescriptvalueUnit" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"/>
		<label>预警信号接收人：</label>
		<input name="item.rescriptReceiver" id="item.rescriptReceiver" allowInput="false" required="true" class="nui-buttonEdit" dictTypeId="user"  
		onbuttonclick="selectCustManeger" requiredErrorText="预警信号接收人不能为空"/>

		<label>预警信号等级：</label>
		<input name="item.rescriptLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40"  dictTypeId="XD_DHCD4006"/>
		
		<label>生效日期：</label>
		<input name="item.rescriptEffectDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" vtype="maxLength:7" />
	<%--	<label>经办机构：</label>
		<input name="item.handlingOrgidId" required="true" class="nui-textbox nui-form-input" vtype="maxLength:10" dictTypeId="org"/>

		<label>经办人员名称：</label>
		<input name="item.handlingUserdId" required="true" class="nui-textbox nui-form-input" vtype="maxLength:10" dictTypeId="user"/>

		<label>经办日期：</label>
		<input name="item.handlingDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" vtype="maxLength:7" />--%>
      	
      	<label>预警指标描述：</label>
		<input name="item.rescriptDesc" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
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

function initForm() {

	var json=nui.encode({"item":{"rescriptId":"<%=request.getParameter("itemId") %>",
		"_entity":"com.bos.dataset.sys.TbSysWarnRescription"}});
		
	$.ajax({
        url: "com.bos.pub.earlyWarning.rescription.getItem.biz.ext",
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

	$.ajax({
        url: "com.bos.pub.TbSysWarnRescription.saveItem.biz.ext",
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
          
        nui.open({
            url: nui.context + "/csm/group/groupCust_teamInfo_queryCust.jsp",
            showMaxButton: true,
            title: "选择预警接收人",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("item.rescriptReceiver").setValue(data.userId);
<%--                    nui.get("item.orgId1").setValue(data.orgId);
                    	nui.get("item.orgId").setValue(data.orgId);
                    	nui.get("item.post").setValue(data.post);--%>
                    
                       
                    }
                }
            }
        });   
        }
	</script>
</body>
</html>

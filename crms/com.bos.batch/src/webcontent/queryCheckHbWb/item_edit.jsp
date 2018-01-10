<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-08-26

  - Description:TB_BATCH_CHECK_HB_WB, TbBatchCheckHbWb-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbBatchCheckHbWb" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	    <label>借据号：</label>
		<input name="tbBatchCheckHbWb.dueSerialno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
	           
	    <label>T24流水借据号：</label>
		<input name="tbBatchCheckHbWb.wbSerialno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
	  
	    <label>币种：</label>
		<input name="tbBatchCheckHbWb.currency" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3"
				dictTypeId="CD000001"/>  
      
		<label>余额类型：</label>
		<input name="tbBatchCheckHbWb.balancetype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
	
		<label>台账发生额：</label>
		<input name="tbBatchCheckHbWb.hbBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>台账交易描述：</label>
		<input name="tbBatchCheckHbWb.hbOccurdirection" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />

		<label>T24流水发生额：</label>
		<input name="tbBatchCheckHbWb.wbBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>T24流水交易描述：</label>
		<input name="tbBatchCheckHbWb.wbOccurdirection" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />

        <label>经办机构：</label>
        <input name="tbBatchCheckHbWb.orgNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:300" id="assessOrg" onbuttonclick="chooiseAssessOrg" />
		
		
		<label>批量日期：</label>
		<input name="tbBatchCheckHbWb.batchdate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>创建时间：</label>
		<input name="tbBatchCheckHbWb.createtime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
		
		<label>更新时间：</label>
		<input name="tbBatchCheckHbWb.updatetime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
		
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

function initForm() {
	var json=nui.encode({"tbBatchCheckHbWb":
		{"serialno":
		"<%=request.getParameter("serialno") %>"}});
	$.ajax({
        url: "com.bos.batch.queryCheckHbWb.getTbBatchCheckHbWb.biz.ext",
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
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.batch.common.updateBusinessHours.biz.ext",
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
 
         /**
		 * 经办机构
		 */
		function chooiseAssessOrg(){
			nui.open({
				url: nui.context+"/grt/grtPublic/grtAssessCsm.jsp",
				title: "选择评估机构", 
				width: 800, 
				height: 600,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action != "ok"){
						nui.get("tbGrtGuarantybasic.assessorgPartyId").setValue(action[0]);
						nui.get("assessOrg").setValue(action[2]);
						nui.get("assessOrg").setText(action[2]);
					}
				}
			});
		}
	</script>
</body>
</html>

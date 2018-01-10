<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 金磊
  - Date: 2014-03-31

  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  class="nui-form">
	<input id="id" name="tbLoanZh.id"  class="nui-hidden nui-form-input" value=""/>
	<div class="nui-dynpanel" columns="4">
				<label>账户账号：</label>
				<input id="zh" name="tbLoanZh.zh" style="float: left;"   required="true" class="nui-textbox nui-form-input"/>
				
				<label>账户类型：</label>
				<input id="zhlx" name="tbLoanZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1208"/>
				
				<label>账户标识：</label>
				<input id="zhbs" name="tbLoanZh.zhbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0219"/>
				
				<label>卡折标志：</label>
				<input id="kzbs" name="tbLoanZh.kzbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0220"/>
				
				<label>账户开户网点号：</label>
				<input id="zhkhjg" name="tbLoanZh.zhkhjg" required="true"  class="nui-textbox nui-form-input"  />
					
				<label>账户开户行名称：</label>
				<input id="zhkhhmc"  name="tbLoanZh.zhkhhmc" required="true"  class="nui-textbox nui-form-input" />
		
				<label>账户名称：</label>
				<input id="zhmc" name="tbLoanZh.zhmc" class="nui-textbox nui-form-input" vtype="maxLength:200" required="true" />
		
				<label>账户币种：</label>
				<input name="tbLoanZh.currencyCd"   class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  required="true" />
		
				<label>账户余额：</label>
				<input name="tbLoanZh.balanceOfAccount" class="nui-textbox nui-form-input" vtype="maxLength:20;" required="true"  dataType="currency"/>
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

//初始化页面
function initForm() {
	var json=nui.encode({"id":"<%=request.getParameter("id") %>"});
	$.ajax({
        url: "com.bos.accInfo.accInfo.getLoanAccInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		//nui.get("openingBankName").setValue(text.tbConAccountInfo.openingBankNo);
        		//nui.get("openingBankName").setText(text.tbConAccountInfo.openingBankName);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

//保存页面信息
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
        url: "com.bos.accInfo.accInfo.saveLoanAccInfo.biz.ext",
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
//查询机构
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
                        nui.get("openingBankNo").setValue(data.orgid);
                        nui.get("openingBankName").setValue(data.orgname);
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

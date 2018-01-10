<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31

  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.pub.product.TbSysTechProduct" class="nui-hidden" />
	<input type="hidden" name="control" class="nui-hidden" />
	<input type="hidden" name="rate" class="nui-hidden" />
	<input type="hidden" name="contract" class="nui-hidden" />
	<input name="control.controlParamId" required="false" class="nui-hidden" vtype="maxLength:32" 
			enabled="false"/>
	<div class="nui-dynpanel" columns="4">
		<label>授信产品控制规则代码：</label>
		<input name="item.productRuleCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>授信产品控制规则名称：</label>
		<input name="item.productRuleName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:100" enabled="false" dictTypeId="product"/>


	</div>
	<br/>
	<div class="nui-dynpanel" columns="4">
		
		<label>币种范围：</label>
		<input id="currencyCdCtrl" name="control.currencyCdCtrl" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:80"  onbuttonclick="selectCurrencyList"/>
		
		<label>最大金额：</label>
		<input name="control.maxAmt" required="true" class="nui-textbox nui-form-input" vtype="float" />
		<label>最小金额：</label>
		<input name="control.minAmt" required="true" class="nui-textbox nui-form-input" vtype="float" />
		<label>最大期限：</label>
		<input name="control.maxTerm" required="true" class="nui-textbox nui-form-input" vtype="int" />

		<label>最大期限单位：</label>
		<input name="control.maxTermUnitCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="请选择"/>

		<label>最小期限：</label>
		<input name="control.minTerm" required="true" class="nui-textbox nui-form-input" vtype="int" />

		<label>最小期限单位：</label>
		<input name="control.minTermUnitCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="请选择"/>
		<label>担保方式：</label>
		<input id="guarantyCdCtrl" name="control.guarantyCdCtrl" required="true" class="nui-buttonEdit nui-form-input"  vtype="maxLength:80"  onbuttonclick="selectCodeList"/>

		<label>还款方式：</label>
		<input id="repayCdCtrl" name="control.repayCdCtrl" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:80" onbuttonclick="selectPayList"/>

		<label>控制参数状态：</label>
		<input name="control.statusCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_GGCD2012" emptyText="请选择"/>
		<label>生效日期：</label>
		<input id="okTime" name="control.validDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:11" />
			<label>经办日期：</label>
		<input name="control.handlingDate" required="true" class="nui-datepicker nui-form-input" enabled="false"/>

		<label>经办机构：</label>
		<input name="control.orgNum" required="false" class="nui-buttonEdit"  vtype="maxLength:9" enabled="false" dictTypeId="org" />

		<label>经办人员：</label>
		<input name="control.userNum" required="false" class="nui-buttonEdit"  vtype="maxLength:10" enabled="false" dictTypeId="user" />

	</div>
	<br/>

</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
 //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }		
function initForm() {
	var json=nui.encode({"item":{"productRuleCd":"<%=request.getParameter("itemId") %>",
		"_entity":"com.bos.pub.product.TbSysTechProduct"}});
	$.ajax({
            url: "com.bos.pub.product.getRule.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		form.setData(text);
            		 nui.get("guarantyCdCtrl").setText(nui.getDictText("XD_SXCD1020",text.control.guarantyCdCtrl));
            		 nui.get("repayCdCtrl").setText(nui.getDictText("XD_SXCD1162",text.control.repayCdCtrl));
            		 	 nui.get("currencyCdCtrl").setText(nui.getDictText("CD000001",text.control.currencyCdCtrl));
            		 
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
	
	 var time;
       var a=new Date();
       //当前时间
      var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
      //生效时间
        time=nui.get("okTime").getValue();
        var time2=time.split(" ");
        var d1=toDate(time2[0]);
		var d2=toDate(time1);
		if(d1>d2){
											var o=form.getData();
									var json=nui.encode(o);
									//nui.alert(json);return;
									$.ajax({
								            url: "com.bos.pub.product.saveRule.biz.ext",
								            type: 'POST',
								            data: json,
								            cache: false,
								            contentType:'text/json',
								            success: function (text) {
								            	if(text.msg){
								            		nui.alert(text.msg);
								            	} else {
								            		initForm();
								            		alert("保存成功");
								            	}
								            },
								            error: function (jqXHR, textStatus, errorThrown) {
								                nui.alert(jqXHR.responseText);
								            }
									});
		}else{
		   alert("生效时间应该晚于当前时间");
		   return;
		}


	

}

var bootPath = "<%=request.getContextPath() %>";
function selectCodeList(e) {
         var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/product/rule/warrant_code.jsp",
            showMaxButton: false,
            title: "选择担保方式",
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
	function selectPayList(e){
			  var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/product/rule/payCode.jsp",
            showMaxButton: false,
            title: "选择还款方式",
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
	function selectCurrencyList(e){
		 var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/product/rule/currency.jsp",
            showMaxButton: false,
            title: "选择币种范围",
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

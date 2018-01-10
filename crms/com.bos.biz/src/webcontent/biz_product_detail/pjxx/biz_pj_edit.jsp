<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lpc
  - Date: 2015-6-3
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbBizPjxxApply.applyDetailId" id="tbBizPjxxApply.applyDetailId" class="nui-hidden" />
	<input name="tbBizPjxxApply.amountDetailId" id="tbBizPjxxApply.amountDetailId" class="nui-hidden" />
	<fieldset>
  		<legend>
    		<span>纸票票信息</span>
    	</legend>
    	<div class="nui-dynpanel" columns="4" id="div_1">
    		<label>合同编号：</label>
			<input id="tbBizPjxxApply.htbh" name="tbBizPjxxApply.htbh" required="true" class="nui-text" />
			<label>汇票号码：</label>
			<input id="tbBizPjxxApply.pjhm" name="tbBizPjxxApply.pjhm" class="nui-text"/>
		</div>
		<div class="nui-dynpanel" columns="4" id="div_2">
			<label>出票人全称：</label>
			<input id="tbBizPjxxApply.cprqc" name="tbBizPjxxApply.cprqc" required="true" class="nui-text" />
			<label>出票人账号：</label>
			<input id="tbBizPjxxApply.cprzh" name="tbBizPjxxApply.cprzh" required="true" class="nui-textbox nui-form-input"  />
			<label>收款人全称：</label>
			<input id="tbBizPjxxApply.skrqc" name="tbBizPjxxApply.skrqc" required="true" class="nui-textbox nui-form-input"  />
			<label>收款人账号：</label>
			<input id="tbBizPjxxApply.skrzh" name="tbBizPjxxApply.skrzh" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" />
			<label>收款人开户行行名：</label>
			<input id="tbBizPjxxApply.payeebankname" name="tbBizPjxxApply.payeebankname" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_SK" />
			<label>收款人开户行行号：</label>
			<input id="tbBizPjxxApply.payeebankno" name="tbBizPjxxApply.payeebankno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" />
			<label>承兑行行名：</label>
			<input id="tbBizPjxxApply.acceptorbankname" name="tbBizPjxxApply.acceptorbankname" required="true" enabled="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_CD" />
			<label>承兑行行号：</label>
			<input id="tbBizPjxxApply.acceptorbankno" name="tbBizPjxxApply.acceptorbankno" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:300" />
			<label>币种：</label>
			<input id="tbBizPjxxApply.currencyCd" dictTypeId="CD000001" name="tbBizPjxxApply.currencyCd" required="true" class="nui-dictcombobox nui-form-input" enabled="false" />
			<label>汇票金额：</label>
			<input id="tbBizPjxxApply.hpje" name="tbBizPjxxApply.hpje"  required="true" class="nui-textbox nui-form-input" dataType="currency"/>
			<label>汇票出票日期：</label>
			<input id="tbBizPjxxApply.hpcprq" name="tbBizPjxxApply.hpcprq" required="true" class="nui-datepicker nui-form-input" onvaluechanged="validateDate" />
			<label>汇票到期日期：</label>
			<input id="tbBizPjxxApply.hpdqrq" name="tbBizPjxxApply.hpdqrq" required="true" class="nui-datepicker nui-form-input" onvaluechanged="validateDate" />
			<label>汇票形式：</label>
			<input id="tbBizPjxxApply.hpxs" name="tbBizPjxxApply.hpxs" required="true" dictTypeId="XD_SXCD1123" class="nui-dictcombobox nui-form-input" enabled="false" />
			<label>禁止背书标记：</label>
			<input id="tbBizPjxxApply.forbidflag" name="tbBizPjxxApply.forbidflag" required="true" dictTypeId="XD_JZBSBJ01" class="nui-dictcombobox nui-form-input" enabled="false" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	var applyDetailId = "<%=request.getParameter("applyDetailId") %>";
	initForm();
	
	function initForm() {
		var json=nui.encode({"applyDetailId":applyDetailId});
		$.ajax({
	            url: "com.bos.bizProductDetail.bizPjxx.getPjxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            async: false, //异步处理
	            success: function (text) {
					git.unmask("form1");
					form.setData(text);
					nui.get("tbBizPjxxApply.acceptorbankname").setText(text.tbBizPjxxApply.acceptorbankname);
					nui.get("tbBizPjxxApply.payeebankname").setText(text.tbBizPjxxApply.payeebankname);
					if(text.tbBizPjxxApply.pjhm && text.tbBizPjxxApply.pjhm.length>26){
						document.getElementById("tbBizPjxxApply.pjhm").style.width="95%";
					}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.bizProductDetail.bizPjxx.updatePjxx.biz.ext",
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
	
	// 收款行 行名行号选择
	function selectPZFHH_SK() {
		nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/queryPZFHH.jsp",
	        showMaxButton: true,
	        title: "查询",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	nui.get("tbBizPjxxApply.payeebankname").setValue(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.payeebankname").setText(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.payeebankno").setValue(data.FBHHHH);
	                }
	            }
	            form.validate();
	        }
	    }); 
    }
    
    // 承兑号 行名行号选择
	function selectPZFHH_CD() {
		nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/queryPZFHH.jsp",
	        showMaxButton: true,
	        title: "查询",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	nui.get("tbBizPjxxApply.acceptorbankname").setValue(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.acceptorbankname").setText(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.acceptorbankno").setValue(data.FBHHHH);
	                }
	            }
	            form.validate();
	        }
	    }); 
    }
</script>
</body>
</html>

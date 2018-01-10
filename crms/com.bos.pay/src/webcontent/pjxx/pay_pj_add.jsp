<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description:票据信息新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="formpj" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbLoanHpAmt.moneyUseId" id="tbLoanHpAmt.moneyUseId" class="nui-hidden" />
	<input name="tbLoanHpAmt.loanId" id="tbLoanHpAmt.loanId" class="nui-hidden" />
	<fieldset>
  		<legend>
    		<span>票据信息</span>
    	</legend>
    	<div class="nui-dynpanel" columns="4" id="tableForm">
			<label>承兑协议编号：</label>
			<input name="conInfo.contractNum" required="true" class="nui-text nui-form-input"/>
			
			<label>出票人全称：</label>
			<input name="accInfo.zhmc" required="true" class="nui-text nui-form-input"/>
			
			<label>出票人账号：</label>
			<input name="accInfo.zh" required="true" class="nui-text nui-form-input"/>
			 
			<label>付款行全称：</label>
			<input name="tbLoanInfo.drweBnkNm" required="true" class="nui-text nui-form-input"  vtype="maxLength:300" />
			
			<label>付款行行号：</label>
			<input name="tbLoanInfo.drweBnkNo" required="true" class="nui-text nui-form-input" vtype="maxLength:60" />
			
			<label>付款行地址：</label>
			<input name="tbLoanInfo.drweBnkAdr" required="true" class="nui-text nui-form-input" vtype="maxLength:300" />
			
			<label>收款人全称：</label>
			<input name="tbLoanHpAmt.pyeAcctNm" id="tbLoanHpAmt.pyeAcctNm" required="true" class="nui-textbox nui-form-input"  onblur="checkLength2(this.value)"/>
			
			<label>收款人账号：</label>
			<input name="tbLoanHpAmt.pyeAcctNo" id="tbLoanHpAmt.pyeAcctNo" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:32"/>
			
			<label>收款人开户银行：</label>
			<input name="tbLoanHpAmt.pyeOpenAcctBnkNm" id="tbLoanHpAmt.pyeOpenAcctBnkNm" required="true" class="nui-textbox nui-form-input"  onblur="checkLength4(this.value)"/>
			
			<label>出票金额：</label>
			<input id="tbLoanHpAmt.loanAmt" name="tbLoanHpAmt.loanAmt"  required="true" class="nui-textbox nui-form-input" dataType="currency" vtype="float;maxLength:20;range:100,100000000000"/>
			
			<label>出票日期：</label>
			<input id="tbLoanInfo.beginDate" name="tbLoanInfo.beginDate" class="nui-text nui-form-input" />
			
			<label>汇票到期日：</label>
			<input id="tbLoanInfo.endDate" name="tbLoanInfo.endDate" class="nui-text nui-form-input" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var formpj = new nui.Form("#formpj");
	var loanId = "<%=request.getParameter("loanId") %>";
	function initForm() {
		var json=nui.encode({"loanId":loanId});
		$.ajax({
	            url: "com.bos.pay.pjxx.queryLoanInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("formpj");
	            	formpj.setData(text);
					if (loanId) {
						nui.get("tbLoanHpAmt.loanId").setValue(loanId); 
					}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("formpj");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	function save() {
		formpj.validate();
		if (formpj.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("formpj");
		var o=formpj.getData();
		o.tbLoanHpAmt.billState='0';//0-正常 1-删除
		o.tbLoanHpAmt.drftExpDt = nui.get("tbLoanInfo.endDate").getValue();
		o.tbLoanHpAmt.issuDt = nui.get("tbLoanInfo.beginDate").getValue();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.pay.pjxx.insertHpxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("formpj");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("formpj");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	function checkLength2(a){//收款人全称
		var ret = maxLength(a,50)
		if(false == ret){
			nui.alert("超过最大字符长度:50");
			nui.get("tbLoanHpAmt.pyeAcctNm").setValue('');
		}
	}
	function checkLength3(a){//收款人账号
		var ret = maxLength(a,32)
		if(false == ret){
			nui.alert("超过最大字符长度:32");
			nui.get("tbLoanHpAmt.pyeAcctNo").setValue('');
		}
	}
	function checkLength4(a){//收款人开户行
		var ret = maxLength(a,60)
		if(false == ret){
			nui.alert("超过最大字符长度:60");
			nui.get("tbLoanHpAmt.pyeOpenAcctBnkNm").setValue('');
		}
	}
	
	function maxLength(a, c) {
        //修改中文所占长度为2，其余字符长度1--begin
        var re = /[^\u4e00-\u9fa5]/;
		var len = 0;
		for(var i=0;i<a.length;i++){
			var t = a.charAt(i);
			if(re.test(t)){
				len = parseInt(len)+1;
			}else{
				len = parseInt(len)+2;//如果是中文，增加2长度
			}
		}
        if(len <= c){
            return true
        } else {
            return false
        }
    }
</script>
</body>
</html>

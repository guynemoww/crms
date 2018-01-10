<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-03-28
  - Description:TB_GRT_BILL, com.bos.dataset.grt.TbGrtBill
-->
<head>
	<title>现金等价物处理逻辑及台账修改</title>
	<%@include file="/common/nui/common.jsp" %>
</head> 
<body>
<center>
	<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<label class="nui-hidden">关联关系ID</label>
		<input name="tbLoanGrtRelation.relationId" value="<%=request.getParameter("relationId") %>" required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input name="checkStatus" value="1" required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input name="natureCd" id="natureCd"  required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input name="tbLoanGrtRelation.guarantyLimitMoney" style="width:200px;" required="false" class="nui-hidden" value="<%=request.getParameter("guarantyLimitMoney") %>" />
		<fieldset>
		  	<legend>
		    	<span>现金等价物信息</span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
		<label>账号：</label>
		<input name="" required="false" class="nui-text" value="<%=request.getParameter("ratioNo") %>"/>
		
		<label>现金等价物类型：</label>
		<input name="" required="false" class="nui-text" value="<%=request.getParameter("guarantyTypeCd") %>"  dictTypeId="XD_DBCD4002"/>
		
		<label>调整前金额：</label>
		<input name="" required="false" class="nui-text" dataType="currency" value="<%=request.getParameter("guarantyLimitMoney") %>"/>		
										
		<label>调整后金额：</label>
		<input name="guateeAmt" id="guateeAmt" required="true" vtype="float" dataType="currency" class="nui-textbox" minValue="0"  decimalPlaces="2" value="<%=request.getParameter("guarantyLimitMoney") %>" maxValue="99999999999999999999999999" onblur="mchanged(this)"/>
		
		<label>调整前比例：</label>
		<input name="" id="beforeRate" required="false" class="nui-text" value=""/>		
		
		<label>调整后比例：</label>
		<input id="changeRate" name="" required="false" class="nui-text" value=""/>		
		</div>
		</fieldset>
		<fieldset>
		  	<legend>
		    	<span>系统信息</span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
		<label>经办人：</label>
		<input name="" required="false" class="nui-text" value="<%=userObject.getUserName() %>"/>
		
		<label>经办机构：</label>
		<input name="" required="false" class="nui-text" value="<%=userObject.getUserOrgName() %>"/>
		
		<input id="loanAmt" required="false" class="nui-hidden" value="<%=request.getParameter("loanAmt") %>"/>
		</div>
		</fieldset>
	</div>
</center>					
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="save1">提交</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>
		    
				
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var guateeAmt = <%=request.getParameter("guarantyLimitMoney") %>;
		var loanAmt = <%=request.getParameter("loanAmt") %>;
		var natureCd = <%=request.getParameter("natureCd") %>;
		
		if("1" == "<%=request.getParameter("view") %>"){
			form.setEnabled(false);
			nui.get("save1").setEnabled(false);
		}
		//初始化
		function initForm(){
			if(guateeAmt!=null && loanAmt!=null){
				//设置调整前担保比例
				var before = nui.get("beforeRate");
				beforeVal = parseInt((Number(guateeAmt)/Number(loanAmt)).toFixed(2)*100);
				before.setValue(beforeVal+"%");
				nui.get("changeRate").setValue(before.value);
			}
			nui.get("natureCd").setValue(natureCd);//设置业务性质(综合授信或者单笔单批)
		}
		initForm();
		
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			nui.get("save1").setEnabled(false);
			git.mask();
			$.ajax({
		        url: "com.bos.pub.standingbook.guarantyaccout.updateCashTbGrt.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		if("01"==text.msg){
		        			nui.alert("现金等价物与放款金额比例低于合同担保比例,已提交调整");
		        			CloseWindow("ok");
		        		}else{
		        			nui.get("save1").setEnabled(true);
		        			nui.alert(text.msg);
		        		}
		        	} else {
		        		nui.alert("已提交调整");
		        		CloseWindow("ok");
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		  
	  //保证金调整触发事件
		function mchanged(e){
			var newAmt = nui.get("guateeAmt");
			var val = Number((newAmt.getValue()).replace(",",""));
			if(val > loanAmt){
				//倘若调整后金额大于借据金额，则修改为借据金额
				newAmt.setValue(loanAmt);
			}
			nui.get("changeRate").setValue(parseInt(((newAmt.getValue()/Number(loanAmt)).toFixed(2))*100)+"%");
		}
		
		/**
		 * 点击关闭按钮，关闭窗口	
		 */
		function closeok(){
			CloseWindow("ok");
		}
		
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
	</script>
</body>
</html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-03-28
  - Description:TB_GRT_BILL, com.bos.dataset.grt.TbGrtBill
-->
<head>
	<title>现金等价物处理逻辑及台账修改复核</title>
	<%@include file="/common/nui/common.jsp" %>
</head> 
<body> 
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<label class="nui-hidden">关联关系ID</label>
		<input name="tbLoanGrtRelation.relationId" value="<%=request.getParameter("relationId") %>" required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input name="tbLoanGrtRelation.guarantyLimitMoney" value="<%=request.getParameter("guarantyLimitMoney") %>" required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input name="natureCd" id="natureCd"  required="false" class="nui-hidden" vtype="maxLength:32"/>
		<input id="checkStatus" name="checkStatus" required="false" class="nui-hidden"/>
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
		<input name="guateeAmt" id="guateeAmt" required="true" vtype="float" dataType="currency" class="nui-text" decimalPlaces="2" value="<%=request.getParameter("changeAmt") %>" onvaluechanged="mchanged"/>
		
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
		<fieldset>
		  	<legend>
		    	<span>系统信息</span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
			<label>复核意见：</label>
			<input class="nui-radiobuttonlist" required="true" id="appr" data="[{'id': '2', 'text': '通过'},{'id': '3', 'text': '退回'}]" name="rad" onvaluechanged="choose(this);"/>
			
			<label id="reject">退回原因：</label>
			<input id="reason" name="tbLoanGrtRelation.refuse" required="false" class="nui-textarea" vtype="maxLength:200" emptyText="请输入退回原因"/>
		</div>
		</fieldset>
	</div>
					
	<div class="nui-toolbar" style="border:0;text-align:right;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="save1">确认</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>
				
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var loanAmt = <%=request.getParameter("loanAmt") %>;
		var natureCd = <%=request.getParameter("natureCd") %>;
		var guateeAmt = <%=request.getParameter("guarantyLimitMoney") %>;
		var changeAmt = <%=request.getParameter("changeAmt") %>;
		
		nui.get("reason").hide();
		$("#reject").css({"display":"none"});
		function choose(e){
			var vv = e.value;
			if("3"==vv){ //退回
				nui.get("reason").setRequired(true);
				nui.get("checkStatus").setValue(vv);
				nui.get("reason").show();
				$("#reject").css({"display":""});
			}else{ //通过 
				nui.get("reason").setRequired(false);
				nui.get("checkStatus").setValue(vv);
				nui.get("reason").hide();
				$("#reject").css({"display":"none"});
			}
		}
		
		//初始化
		function initForm(){
			if(guateeAmt!=null && loanAmt!=null){
				//设置调整前担保比例
				var before = nui.get("beforeRate")
				before.setValue(parseInt(((Number(guateeAmt)/Number(loanAmt)).toFixed(2))*100)+"%");
				nui.get("changeRate").setValue(parseInt(((Number(changeAmt)/Number(loanAmt)).toFixed(2))*100)+"%");
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
			git.mask();
			$.ajax({
		        url: "com.bos.pub.standingbook.guarantyaccout.updateCashTbGrt.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        		CloseWindow("ok");
		        	} else {
		        		nui.alert("已复核");
		        		CloseWindow("ok");
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		  
	  
		/**
		 * 到期日期必须大于当前日期
		 */
		function onDrawDate(e) {
			var date = e.date;
			var d = new Date();
			if (date.getTime() < d.getTime()) {
				e.allowSelect = false;
			}
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

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-03-28
  - Description:TB_GRT_BILL, com.bos.dataset.grt.TbGrtBill
-->
<head>
	<title>现金等价物新增</title>
<%@include file="/common/nui/common.jsp" %>
</head> 
<body>
<center>
<div id="form1" style="width:98.5%;height:auto;overflow:hidden;">
	<label class="nui-hidden">放款明细ID：</label>
	<input name="item.loanDetailId" id="item.loanDetailId" required="false" class="nui-hidden" vtype="maxLength:32"/>
	<fieldset>
	  	<legend>
	    	<span>现金等价物信息</span>
	    </legend>
		<div class="nui-dynpanel" columns="4">
		<label>账号：</label>
		<input name="item.marginAccount" required="false" class="nui-text nui-form-input" vtype="maxLength:30"
			id="item.marginAccount" onblur="marginAccountblur" />
		<label>押品类型：</label>
		<input name="item.sortType" id="item.sortType" required="false" class="nui-text nui-form-input" 
			dictTypeId="XD_DBCD4002" enabled="true"/>
		<label>币种：</label>
		<input name="item.currencyCd" required="false" class="nui-text nui-form-input" 
			id="item.currencyCd" dictTypeId="CD000001" />
		<label>金额：</label>
		<input name="item.marginAmt" required="false" class="nui-text nui-form-input" vtype="float" dataType="currency" 
			id="item.marginAmt" />
		<label>现金等价物类型：</label>
		<input name="item.marginType" required="false" class="nui-text nui-form-input" 
			id="item.marginType" dictTypeId="YP_GLCD0019" />
		<label>开户行：</label>
		<input name="item.openBank" required="false" class="nui-text nui-form-input" 
			id="item.openBank" />
		<label>开户人：</label>
		<input name="item.acctName" required="false" class="nui-text nui-form-input" 
			id="item.acctName" />
		</div>
	</fieldset>
	<fieldset>
	  	<legend>
	    	<span>系统信息</span>
	    </legend>
		<div class="nui-dynpanel" columns="4">
		<label>复核人：</label>
		<input name="item.apvUserNum" id="item.apvUserNum" required="false" class="nui-text" />
		<label>复核机构：</label>
		<input name="item.apvOrgNum" id="item.apvOrgNum" required="false" class="nui-text" />
		<label>复核日期：</label>
		<input name="item.apvTime" required="false" class="nui-datepicker nui-form-input" 
			id="item.apvTime" enabled="false" format="yyyy-MM-dd" />
		</div>
	</fieldset>
	<div class="nui-dynpanel" columns="4">
		<label>复核意见：</label>
		<input class="nui-radiobuttonlist" required="true" id="appr" data="[{'id': '2', 'text': '通过'},{'id': '3', 'text': '退回'}]" name="rad" onvaluechanged="choose(this);"/>
		
		<label id="reject">退回原因：</label>
		<input id="item.refuseReason" name="item.refuseReason" required="false" class="nui-textarea" vtype="maxLength:300" emptyText="请输入退回原因"/>
	</div>
</div>
</center>					
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="save1">确定</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>
		    
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var loanDetailId = "<%=request.getParameter("loanDetailId") %>";
		var contractNum = "<%=request.getParameter("contractNum") %>";
		var sta = "02";
		
		//初始化
		function initForm(){
			git.mask();
			var jsonStr={
				"loanDetailId":loanDetailId
			}
			var json=nui.encode(jsonStr);
			$.ajax({
		        url: "com.bos.pub.standingbook.guarantyaccout.getCashCheckInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	form.setData({"item":nui.decode(text.cash)});
	        		git.unmask();
	        		nui.get("item.apvUserNum").setValue("<%=userObject.getUserName() %>");
					nui.get("item.apvOrgNum").setValue("<%=userObject.getUserOrgName() %>");//设置放款明细ID
					nui.get("item.apvTime").setValue(getDate());//经办时间
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		initForm();
		
		nui.get("item.refuseReason").hide();
		$("#reject").css({"display":"none"});
		function choose(e){
			var vv = e.value;
			if("3"==vv){ //退回
				nui.get("item.refuseReason").setRequired(true);
				nui.get("item.refuseReason").show();
				sta = "03";
				$("#reject").css({"display":""});
			}else{ //通过 
				nui.get("item.refuseReason").setRequired(false);
				nui.get("item.refuseReason").hide();
				sta = "02";
				$("#reject").css({"display":"none"});
			}
		}
		
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var marginAcc = nui.get("item.marginAccount").getValue();
			var margin = form.getData();
			var o={"loanDetailId":loanDetailId,"marginAcc":marginAcc,"status":sta,"item":nui.encode(margin.item),"cash":margin.item,"contractNum":contractNum};
			var json=nui.encode(o);
			nui.get("save1").setEnabled(false);
			git.mask();
			$.ajax({
		        url: "com.bos.pub.standingbook.guarantyaccout.addCashTbGrt.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.get("save1").setEnabled(true);
		        		nui.alert(text.msg);
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
		
		function marginAccountblur(){
			var reg=/^[0-9]*$/;
			//保证金账号
			var marginAccount=nui.get("item.marginAccount").getValue();
			var tempmarginAccount = marginAccount.substring(0,1);
			if(tempmarginAccount != "5"){
				nui.alert("保证金账号必须为5开头！");
				nui.get("save1").setEnabled(false);
				return;
			}else{
			    if(!reg.test(marginAccount)){
			      nui.alert("保证金账号由数字组成！");
			      return;
			    }
				nui.get("save1").setEnabled(true);
				//checkMarinAccount(marginAccount);
			}
		}
		/**
		 * 验证保证金账号
		 */
		function checkMarinAccount(marginAccount){
			var json=nui.encode({"AcctNo":marginAccount});
			git.mask();
			$.ajax({
				url: "com.bos.inter.CallT24Interface.T24Maintain.TDpAcctAllInq.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					//如果返回的数据没有保证金账号，则为不存在的保证金账号！
					if(text.accRs.AgreeMentAcctNo == null){
						nui.alert("账号不存在！");
						nui.get("save1").setEnabled(false);
						//币种
						nui.get("item.currencyCd").setValue("");
						//开户行item.openBank
						var OpenBankname = git.getOrgName(text.accRs.OpenBank);
						nui.get("item.openBank").setValue("");
						//开户人item.acctName
						nui.get("item.acctName").setValue("");
					}else{//否则是存在的保证金账号
						nui.get("save1").setEnabled(true);
					}
					git.unmask();
				},
				error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
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
		//获取当前日期  yyyy-MM-dd
		function getDate(){
		  	var d = new Date();
			return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();//赋于当前日期
		}
	</script>
</body>
</html>

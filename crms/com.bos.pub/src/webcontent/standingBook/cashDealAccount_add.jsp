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
		<input name="item.marginAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30"
			id="item.marginAccount" onblur="marginAccountblur" />
		<label>押品类型：</label>
		<input name="item.sortType" id="item.sortType" required="true" class="nui-dictcombobox nui-form-input" 
			dictTypeId="XD_DBCD4002" enabled="true" onvaluechanged="cashChange" />
		<label>币种：</label>
		<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" 
			id="item.currencyCd" dictTypeId="CD000001" />
		<label>金额：</label>
		<input name="item.marginAmt" required="true" class="nui-textbox nui-form-input" vtype="float" dataType="currency" 
			id="item.marginAmt" />
		<label>保证金类型：</label>
		<input name="item.marginType" required="true" class="nui-dictcombobox nui-form-input" 
			id="item.marginType" dictTypeId="YP_GLCD0019" />
		<label>开户行：</label>
		<input name="item.openBank" required="false" class="nui-textbox nui-form-input" 
			id="item.openBank" />
		<label>开户人：</label>
		<input name="item.acctName" required="false" class="nui-textbox nui-form-input" 
			id="item.acctName" />
		</div>
	</fieldset>
	<fieldset>
	  	<legend>
	    	<span>系统信息</span>
	    </legend>
		<div class="nui-dynpanel" columns="4">
		<label>经办人：</label>
		<input name="item.userNum" required="false" class="nui-text" value="<%=userObject.getUserName() %>"/>
		<label>经办机构：</label>
		<input name="item.orgNum" required="false" class="nui-text" value="<%=userObject.getUserOrgName() %>"/>
		<label>经办日期：</label>
		<input name="item.tradeTime" required="false" class="nui-datepicker nui-form-input" 
			id="item.tradeTime" enabled="false" format="yyyy-MM-dd" />
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
		var loanDetailId = "<%=request.getParameter("loanDetailId") %>";
		//初始化
		function initForm(){
			nui.get("item.currencyCd").setValue("CNY");
			nui.get("item.loanDetailId").setValue(loanDetailId);//设置放款明细ID
			nui.get("item.tradeTime").setValue(getDate());//经办时间
			nui.get("item.sortType").setData(getDictData("XD_DBCD4002","str","01010101,01010102,01010201,01010202,01010203,01010204,01020201,01020202,01020203"));
		}
		initForm();
		
		//押品类型选择事件
		function cashChange(e){
			if("01010101"==e.value || "01010102"==e.value){
				nui.get("item.marginType").setRequired(true);
			}else{
				nui.get("item.marginType").setRequired(false);
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
			var o={"loanDetailId":loanDetailId,"marginAcc":marginAcc,"status":"01","item":nui.encode(margin.item)};
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
		
		function marginAccountblur(){
			checkMarinAccount(marginAccount);
			
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
					}else{//否则是存在的保证金账号
						nui.get("save1").setEnabled(true);
						//币种
						nui.get("item.currencyCd").setValue(text.accRs.Currency);
						//开户行item.openBank
						var OpenBankname = git.getOrgName(text.accRs.OpenBank);
						nui.get("item.openBank").setValue(OpenBankname);
						//开户人item.acctName
						nui.get("item.acctName").setValue(text.accRs.AcctName);
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
		
			//通用获取业务字典方法
	//param1.dictId:业务字典ID; 
	//param2.type:过滤类型(str:指定字符id过滤(多id以","隔开);sub:获取指定字符串子集;top:获取顶级业务字典)
	//param3.指定的字符串(type为top时可以为空,不做处理)
	
	function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr=="}]"?{}:dictStr);
		return dict;
	}
	//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}
	</script>
</body>
</html>

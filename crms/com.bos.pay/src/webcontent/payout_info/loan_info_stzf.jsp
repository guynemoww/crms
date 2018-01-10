<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): pc
  - Date: 2016-06-30 10:03:30
  - Description:受托支付账号信息
-->
<head>
<title>受托支付信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbLoanStzf.stzfId" class="nui-hidden nui-form-input" name ="tbLoanStzf.stzfId"/>
	<input id="tbLoanStzf.createTime" class="nui-hidden nui-form-input" name ="tbLoanStzf.createTime"/>
	<input id="tbLoanStzf.loanId" class="nui-hidden nui-form-input" name ="tbLoanStzf.loanId"/>
	<fieldset>
		<legend>
	    	<span>第三方结算账号</span>
	    </legend>
	    	
		<div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">是否我行账户：</label>
			<input id="tbLoanStzf.sfwhzh" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" name="tbLoanStzf.sfwhzh"  required="true" onvaluechanged="zhChange()"/>
	    	<label class="nui-form-label" id="zhm1">账户名称：</label>
			<input id="tbLoanStzf.zhm1" class="nui-textbox nui-form-input" name="tbLoanStzf.zhm1"  required="true" />
			
			<label class="nui-form-label" id="zh1">账号：</label>	
			<input id="tbLoanStzf.zh1" class="nui-textbox nui-form-input" name="tbLoanStzf.zh1"   required="true" />	
			
			<label class="nui-form-label" id="amt1">转账金额：</label>
			<input id="tbLoanStzf.amt1" class="nui-textbox nui-form-input"   name="tbLoanStzf.amt1"  required="true"   vtype="float;maxLength:20;range:1,100000000000"  dataType="currency" />
		</div>
	</fieldset>
	<fieldset>
		<div class="nui-dynpanel" columns="1" id="thirdAccount">
			<legend>
	    		<span>第三方保证金账号</span>
		    </legend>
		    <div class="nui-dynpanel" columns="4" id="table2">
		    	<label class="nui-form-label">账户名称：</label>
				<input id="tbLoanStzf.zhm2" class="nui-textbox nui-form-input" name="tbLoanStzf.zhm2" onblur="validd1"/>
				
				<label class="nui-form-label">账号：</label>	
				<input id="tbLoanStzf.zh2" class="nui-textbox nui-form-input" name="tbLoanStzf.zh2" onblur="validd1"/>	
				
				<label class="nui-form-label">转账金额：</label>
				<input id="tbLoanStzf.amt2" class="nui-textbox nui-form-input"   name="tbLoanStzf.amt2"  onblur="validd1" vtype="float;maxLength:20;range:1,100000000000"  dataType="currency" />
			</div>		
		</div>
	</fieldset>

	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_stzf" iconCls="icon-save" onclick="save">保存</a>
	</div>

</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var loanId ="<%=request.getParameter("loanId") %>";//业务合同ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var retMsg = "0000";
	var productType = "<%=request.getParameter("productType") %>";//流程中查看标识
	var type = "2"; // 1-二手房  2-其他
	if(productType=='02002003'||productType=='02002004'||productType=='02005003'){
		type = "1";
	}
	//流贷和固贷 不显示保证金账号
	if(productType.substring(0,5)=="01001"||productType.substring(0,5)=="01003"){
		nui.get("thirdAccount").hide();
	}
	
	//是否我行账户信息校验
	function zhChange(){
		var zhxx = nui.get("tbLoanStzf.sfwhzh").getValue();
		if(zhxx == "0"){
			$("#zhm1").css("display","none");
			$("#zh1").css("display","none");
			$("#amt1").css("display","none");
			nui.get("tbLoanStzf.zhm1").setValue("");
			nui.get("tbLoanStzf.zh1").setValue("");
			nui.get("tbLoanStzf.amt1").setValue("");
			nui.get("tbLoanStzf.zhm1").hide();
			nui.get("tbLoanStzf.zh1").hide();
			nui.get("tbLoanStzf.amt1").hide();
		}else{
			$("#zhm1").css("display","block");
			$("#zh1").css("display","block");
			$("#amt1").css("display","block");
			nui.get("tbLoanStzf.zhm1").show();
			nui.get("tbLoanStzf.zh1").show();
			nui.get("tbLoanStzf.amt1").show();
		}
	}
	
	var currencyCd = 'CNY';
	var loanamt =0;
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"loanId":loanId,"type":type});
		$.ajax({
            url: "com.bos.payInfo.stzf.getStzfxx.biz.ext",
            type: 'POST',
            data: json,
        	async: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	var loanstatus = o.retVo.loanStatus;//表主键
            	currencyCd = o.tbLoanInfo.currencyCd;//币种
            	loanamt = o.tbLoanInfo.loanAmt
            	var relFlag = o.relFlag;//1-关联了项目额度 2-未关联项目额度
            	if(relFlag == '2'){//未关联项目额度，受托支付账号信息都是自己填的，二手房的情况
            		return;
            	}else{//关联了项目额度
	            	var payDirection = o.retVo.payDirection;//1-借款人放款账号 ;2-第三方结算账号 ;3-第三方保证金账号
	            	//同时勾选借款人放款账号和第三方结算账号，则第三方结算账号可手输
	            	if(payDirection =='1,2'){
	            		nui.get("tbLoanStzf.zh2").setValue('');
            			nui.get("tbLoanStzf.zhm2").setValue('');
            			nui.get("tbLoanStzf.amt2").setValue('');
	            		nui.get("tbLoanStzf.zh2").setEnabled(false);
            			nui.get("tbLoanStzf.zhm2").setEnabled(false);
            			nui.get("tbLoanStzf.amt2").setEnabled(false);
            			if(productType!='02005001'){//“住房公积金委托贷款——一手房”有例外情况，若划款方向勾选了“第三方结算账号”，结算账号不反显，应手工输入。
            				//不是公积金按揭贷款的 则返显前面输入的账户信息
            				nui.get("tbLoanStzf.zh1").setValue(o.retVo.zh1);
		            		nui.get("tbLoanStzf.zhm1").setValue(o.retVo.zhm1);
	            			nui.get("tbLoanStzf.zh1").setEnabled(false);
		            		nui.get("tbLoanStzf.zhm1").setEnabled(false);
            			}
	            		validd1();
	            		return;
	            	}
	            	
	            	//关联了项目信息所有信息都反显
            		form.setEnabled(false);
            		//把所有框框置为非必输
            	    nui.get("tbLoanStzf.zh1").setRequired(false);
        			nui.get("tbLoanStzf.zhm1").setRequired(false);
        			nui.get("tbLoanStzf.amt1").setRequired(false); 
        			if(productType=='02005001'){
        				nui.get("tbLoanStzf.sfwhzh").setEnabled(true);//是否我行账户可以手输
        			}
            		if(loanstatus!='01'){//提交后反显的情况，不需要根据项目信息定转账金额
            			return;
            		}
            		var amt1 = o.retVo.amt1;
            		var amt2 = o.retVo.amt2;
            		var zh1 = o.retVo.zh1;
            		var zh2 = o.retVo.zh2;
            		var zhm1 = o.retVo.zhm2;
            		var zhm2 = o.retVo.zhm2;
            		var orgone = o.retVo.orgone//0-行外 ;1-行内
            		//给结算账号模块赋值----------------------start--------------
            		if(payDirection.indexOf('2')!=-1){//勾选了第三方结算账户
            			if(amt1=='0'){//第三方结算金额为0
            				//不用给账号赋值
            				nui.get("tbLoanStzf.zh1").setValue('');
	            			nui.get("tbLoanStzf.zhm1").setValue('');
	            			nui.get("tbLoanStzf.amt1").setValue('');
            			}else{
            				if(productType!='02005001'&& productType!='02005010'){//“住房公积金委托贷款——一手房”有例外情况，若划款方向勾选了“第三方结算账号”，结算账号不反显，应手工输入。
	            				nui.get("tbLoanStzf.zh1").setValue(o.retVo.zh1);
		            			nui.get("tbLoanStzf.zhm1").setValue(o.retVo.zhm1);
		            			nui.get("tbLoanStzf.amt1").setValue(o.retVo.amt1);
	            			}else{
	            				nui.get("tbLoanStzf.zh1").setEnabled(true);
		            			nui.get("tbLoanStzf.zhm1").setEnabled(true);
		            			nui.get("tbLoanStzf.amt1").setEnabled(true);
	            				nui.get("tbLoanStzf.zh1").setRequired(true);
		            			nui.get("tbLoanStzf.zhm1").setRequired(true);
		            			nui.get("tbLoanStzf.amt1").setRequired(true);
	            			}
	            		}
            		}else{//没有勾第三方结算账户
            			nui.get("tbLoanStzf.zh1").setValue('');
            			nui.get("tbLoanStzf.zhm1").setValue('');
            			nui.get("tbLoanStzf.amt1").setValue('');
            		}
            		//给结算账号模块赋值----------------------end--------------
            		
            		//给保证金账号模块赋值----------------------start--------------
            		if(zh2 == null || zh2 == ''|| amt2=='0' ||orgone=='0'||payDirection.indexOf('3')==-1){//没有保证金信息或保证金比例为0或保证金账号为行外
            			//不用给账号赋值
            			nui.get("tbLoanStzf.zh2").setValue('');
            			nui.get("tbLoanStzf.zhm2").setValue('');
            			nui.get("tbLoanStzf.amt2").setValue('');
            		}else{//存在保证金信息
            			nui.get("tbLoanStzf.zh2").setValue(o.retVo.zh2);
            			nui.get("tbLoanStzf.zhm2").setValue(o.retVo.zhm2);
            			nui.get("tbLoanStzf.amt2").setValue(o.retVo.amt2);
            		}
            		//给保证金账号模块赋值----------------------end--------------
            	}
				validd1();
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_stzf").hide();
			form.setEnabled(false);
		}
		//当是否我行账户字段不为空时，初始化为1
		if(nui.get("tbLoanStzf.sfwhzh").getValue()==""||nui.get("tbLoanStzf.sfwhzh").getValue()==null){
			nui.get("tbLoanStzf.sfwhzh").setValue("1");
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_stzf").setEnabled(false);
		var o = form.getData();
		
		var zh1 = o.tbLoanStzf.zh1;
		var zhm1 = o.tbLoanStzf.zhm1;
		var zh2 = o.tbLoanStzf.zh2;
		var zhm2 = o.tbLoanStzf.zhm2;
		if(zh1!='' && zh2!=''&&zh1==zh2){
			nui.alert("第三方结算账号与第三方保证金账号不能相同！");
        	nui.get("con_stzf").setEnabled(true);
         	return;
		}
		 if(zh1!='' && zh1!=null){
			checkAcc(zh1,zhm1);
			if(retMsg!='0000'){
	        	nui.alert(retMsg);
	        	nui.get("con_stzf").setEnabled(true);
	         	return;
	        }
		}
		if(zh2!='' && zh2!=null){
			checkAcc(zh2,zhm2);
	        if(retMsg!='0000'){
	        	nui.alert(retMsg);
	        	nui.get("con_stzf").setEnabled(true);
	         	return;
	        }
		} 
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.payInfo.stzf.saveStzf.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_stzf").setEnabled(true);
        		return;
        	}
        	alert("保存成功！");
        	nui.get("con_stzf").setEnabled(true);
        	initPage();
        }});
	}
	//3个输入项只要一个不为空其他两个必输
	function validd1(){
		var mc = nui.get("tbLoanStzf.zhm2").getValue();
		var zh = nui.get("tbLoanStzf.zh2").getValue();
		var amt = nui.get("tbLoanStzf.amt2").getValue();
		if((mc==null || mc=='')&&(zh==null || zh=='')&&(amt==null || amt=='')){
			nui.get("tbLoanStzf.zhm2").setRequired(false);
			nui.get("tbLoanStzf.zh2").setRequired(false);
			nui.get("tbLoanStzf.amt2").setRequired(false);
		}else{
			nui.get("tbLoanStzf.zhm2").setRequired(true);
			nui.get("tbLoanStzf.zh2").setRequired(true);
			nui.get("tbLoanStzf.amt2").setRequired(true);
		}
		nui.get("tbLoanStzf.zhm2").validate();
		nui.get("tbLoanStzf.zh2").validate();
		nui.get("tbLoanStzf.amt2").validate();
	}
	
	//账户名账号校验
	function checkAcc(AcctNo,zhm){
		  retMsg = "0000";
		  var json=nui.encode({"acctInd":AcctNo});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        async: false,
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        		retMsg = "账号查询失败:"+message;
	        		return;
	        	}
	        	
	        	var cusName = text.hxresponse.oxd052ResBody.custName;
	        	if(cusName.trim() != zhm){
	        		retMsg = "账户名'"+zhm+"'与账号'"+AcctNo+"'不匹配!";
	        		return;
	        	}
	        	
	        	var currcd = text.hxresponse.oxd052ResBody.currCode;
	        	if(currcd!=currencyCd){
	        		nui.alert("账户币种和合同币种不匹配!");
	        		nui.get("zhkhjg").setValue('');
	        		nui.get("zhkhjg").validate();
	        		return;
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
		        retMsg= "账号查询失败!";
	            return jqXHR.responseText;
	        }
		});
	}
</script>
</body>
</html>
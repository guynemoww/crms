<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):  陈川
  - Date: 2016-5-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>理财产品新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>理财名称：</label>
			<input name="item.manageMoneyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>理财协议编号：</label>
			<input name="item.treatyNo" id="item.treatyNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>起始日：</label>
			<input name="item.beginDate" id="item.beginDate" required="false" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"onblur="totalMoney"/>
			
			<label>到期日：</label>
			<input name="item.endDate" id="item.endDate" required="false" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"onblur="totalMoney"/>
			
			<label>预期收益率（％）：</label>
			<input name="item.yieldRate" id="yieldRate" required="false" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" onblur="totalMoney"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>购买金额：</label>
			<input name="item.buyPrice" id="buyPrice" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" onblur="totalMoney"/>
			
			<label>收益类型：</label>
			<input name="item.earningsType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0122"/>
			
			<label>到期总金额：</label>
			<input name="item.totalMoney" id="totalMoney" required="false" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" enabled="false"/>
			
			<label>理财交易账户号：</label>
			<input name="item.accountNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30"onvalidation="checkAcc"/>
			
			<label>等级：</label>
			<input name="item.dangerLevel" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0123"/>
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		
	//校验账号
	function checkAcc(e){
		var acc = e.value;
		var json = nui.encode({"acctInd" : acc});
			$.ajax({
			url : "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			async : false,
			success : function(text) {
				if (text.msg != '查询成功'&&text.msg!="账号为空") {
					e.errorText = text.msg ;
	              	e.isValid = false;
				}
				var cusName = text.queryAcc.cstNm.trim();//账户名
				var orgNum = text.queryAcc.rgonCd + text.queryAcc.branchId;//开户机构
			}
		});
	}
		
		
		function checkDate(e){
			var beginDate=nui.get("item.beginDate").getValue();//生效日期
	  		var endDate=nui.get("item.endDate").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("到期日期必须大于起始日期");
					nui.get("item.beginDate").setValue("");
					nui.get("item.endDate").setValue("");
		  		}
	  		}
		}
		
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate,endDate){
	  		if(nui.parseDate(endDate)-nui.parseDate(beginDate)<=0){//到期日期小于生效日期
	  			return false;
	  		}else{
	  			return true;
	  		}
		}
		
		//计算总金额
		function totalMoney(e){
			var buyPrice=nui.get("buyPrice").getValue();		//购买金额
			var yieldRate=nui.get("yieldRate").getValue();		//预期收益率=年利率
			var beginDate=nui.get("item.beginDate").getValue();//起始日
			var endDate=nui.get("item.endDate").getValue();//到期日
			if(buyPrice!=""&&yieldRate!=""&&beginDate!=""&&endDate!=""){
				//两者时间差
				var tt=GetDateDiff(beginDate,endDate);
				var total=accMul(buyPrice,accAdd(1,accDiv(accMul(tt,yieldRate),36000)));
				nui.get("totalMoney").setValue(total.toFixed(2));
			}else{
				nui.get("totalMoney").setValue(buyPrice);
			}
		}

		function GetDateDiff(startDate,endDate){  
		    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
		    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
		    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
		    return  dates;    
		}
		
		//浮点数js的加减乘除
		/**
		 ** 加法函数，用来得到精确的加法结果
		 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
		 ** 调用：accAdd(arg1,arg2)
		 ** 返回值：arg1加上arg2的精确结果
		 **/
		function accAdd(arg1, arg2) {
		    var r1, r2, m, c;
		    try {
		        r1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r1 = 0;
		    }
		    try {
		        r2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r2 = 0;
		    }
		    c = Math.abs(r1 - r2);
		    m = Math.pow(10, Math.max(r1, r2));
		    if (c > 0) {
		        var cm = Math.pow(10, c);
		        if (r1 > r2) {
		            arg1 = Number(arg1.toString().replace(".", ""));
		            arg2 = Number(arg2.toString().replace(".", "")) * cm;
		        } else {
		            arg1 = Number(arg1.toString().replace(".", "")) * cm;
		            arg2 = Number(arg2.toString().replace(".", ""));
		        }
		    } else {
		        arg1 = Number(arg1.toString().replace(".", ""));
		        arg2 = Number(arg2.toString().replace(".", ""));
		    }
		    return (arg1 + arg2) / m;
		}
		
		//给Number类型增加一个add方法，调用起来更加方便。
		Number.prototype.add = function (arg) {
		    return accAdd(arg, this);
		};
		
		/**
		 ** 减法函数，用来得到精确的减法结果
		 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
		 ** 调用：accSub(arg1,arg2)
		 ** 返回值：arg1加上arg2的精确结果
		 **/
		function accSub(arg1, arg2) {
		    var r1, r2, m, n;
		    try {
		        r1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r1 = 0;
		    }
		    try {
		        r2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r2 = 0;
		    }
		    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
		    n = (r1 >= r2) ? r1 : r2;
		    return ((arg1 * m - arg2 * m) / m).toFixed(n);
		}
		
		// 给Number类型增加一个mul方法，调用起来更加方便。
		Number.prototype.sub = function (arg) {
		    return accMul(arg, this);
		};
		
		/**
		 ** 乘法函数，用来得到精确的乘法结果
		 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
		 ** 调用：accMul(arg1,arg2)
		 ** 返回值：arg1乘以 arg2的精确结果
		 **/
		function accMul(arg1, arg2) {
		    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		    try {
		        m += s1.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    try {
		        m += s2.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
		}
		
		// 给Number类型增加一个mul方法，调用起来更加方便。
		Number.prototype.mul = function (arg) {
		    return accMul(arg, this);
		};
		
		/** 
		 ** 除法函数，用来得到精确的除法结果
		 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
		 ** 调用：accDiv(arg1,arg2)
		 ** 返回值：arg1除以arg2的精确结果
		 **/
		function accDiv(arg1, arg2) {
		    var t1 = 0, t2 = 0, r1, r2;
		    try {
		        t1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		    }
		    try {
		        t2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		    }
		    with (Math) {
		        r1 = Number(arg1.toString().replace(".", ""));
		        r2 = Number(arg2.toString().replace(".", ""));
		        return (r1 / r2) * pow(10, t2 - t1);
		    }
		}
		
		//给Number类型增加一个div方法，调用起来更加方便。
		Number.prototype.div = function (arg) {
		    return accDiv(this, arg);
		};
	</script>
</body>
</html>

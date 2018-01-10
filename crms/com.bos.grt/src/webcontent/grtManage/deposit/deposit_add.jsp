<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉 陈川
  - Date: 2016-05-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>存单新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>存单类型：</label>
			<input name="item.depositType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0110"/>
			
			<label>存单账号：</label>
			<input name="item.depositAcc"  class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>存单号：</label>
			<input name="item.depositNo" id="item.depositNo"  class="nui-textbox nui-form-input" vtype="maxLength:80" />
			
			<label>开户行全称：</label>
			<input name="item.openBank" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
			
			<label>存入日：</label>
			<input name="item.beginDate" id="item.beginDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate()"onblur="totalMoney"/>
			
			<label>到期日：</label>
			<input name="item.endDate" id="item.endDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowinput="false"  onvaluechanged="checkDate()"onblur="totalMoney"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" />
			
			<label>存单面额：</label>
			<input name="item.depositAmt"id="buyPrice" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"onblur="totalMoney"/>
			
			<label>存单利率（%）：</label>
			<input name="item.depositRate" id="yieldRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:15;"onblur="totalMoney"/>
		
			<label>到期总金额：</label>
			<input name="item.expireTotalAmt" id="totalMoney" enabled="false"required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" />
			
			<label>备注说明：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		
		function checkDate(){
			var beginDate=nui.get("item.beginDate").getValue();
			var endDate=nui.get("item.endDate").getValue();
			
			if(beginDate!=""&&endDate!=""){
				if(!CompareDueAndShengXiaoDate(beginDate,endDate)){
					nui.alert("到期日与存入日不能为同一天且存入日不能早于到期日");
					nui.get("item.beginDate").setValue("");
					nui.get("item.endDate").setValue("");
				}
			}
		}
		
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate,endDate){
	  		if(nui.parseDate(beginDate)-nui.parseDate(endDate)>=0){//到期日期小于生效日期
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

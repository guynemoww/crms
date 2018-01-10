<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>贴息信息页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbBizTx.txId" name="tbBizTx.txId" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.txjg1" name="tbBizTx.txjg1" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.zhlx1" name="tbBizTx.zhlx1" class="nui-hidden nui-form-input" />	
	<input name="tbBizTx.qxdw" id="tbBizTx.qxdw"  class="nui-hidden nui-form-input" />
	
	
	<div class="nui-dynpanel" columns="4">
		<label>贴息方式：</label>
		<input id="tbBizTx.txfs" name="tbBizTx.txfs" class="nui-dictcombobox nui-form-input"
			 onvaluechanged="selectFs" required="true" dictTypeId="XD_TXFS0001"/>
	</div>	
	<div id="txbldv" class="nui-dynpanel" columns="4">
		<label>贴息比例(%)：</label>
		<input id="tbBizTx.txbl" name="tbBizTx.txbl" class="nui-textbox nui-form-input" required="true"  vtype="negative;maxLength:11;range:0,100"/>
	</div>	
	<div id="gdjedv" class="nui-dynpanel" columns="4">	
		<label>固定金额(元)：</label>
		<input id="tbBizTx.gdje" name="tbBizTx.gdje" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div id="xedv" class="nui-dynpanel" columns="4">					
		<label>限额：</label>
		<input id="tbBizTx.xe" name="tbBizTx.xe" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div class="nui-dynpanel" columns="4">					
		<label>期限(月)：</label>
		<input name="tbBizTx.qx"  id="tbBizTx.qx" required="true" vtype="int;maxLength:4" class="nui-textbox nui-form-input"/>
		<label class="nui-hidden">预收贴息金额：</label>
		<input id="tbBizTx.ystxje" name="tbBizTx.ystxje" class="nui-hidden nui-form-input" vtype="float;maxLength:18" />
	</div>	
	<div class="nui-dynpanel" columns="4">		
		<label>贴息主体：</label>
		<div style="width:100%">
			<input id="tbBizTx.txzt1" name="tbBizTx.txzt1" class="nui-buttonEdit nui-form-input" required="true" 
				allowInput="false" onbuttonclick="selectTxzt"  style="width:80%;float:left"  emptyText="--请选择--" dictTypeId="org"/>
		</div>
		<label>贴息账号：</label>
		<input name="tbBizTx.txzh1" class="nui-textbox nui-form-input" readonly="true"  id="tbBizTx.txzh1"  required="true" />																
	</div>	
	
	<div class="nui-dynpanel" columns="4">
		<label>贴息标识：</label>
		<input id="tbBizTx.txMark" name="tbBizTx.txMark" class="nui-dictcombobox nui-form-input"
			 onvaluechanged="selectTxMark" required="true" dictTypeId="XD_0002"/>
		<label>协议号：</label>
		<input id="tbBizTx.xyh1" name="tbBizTx.xyh1" class="nui-textbox nui-form-input" vtype="maxLength:30" required="true" />
	</div>	
	
	<div class="nui-dynpanel" columns="4" id="txDateDv" >
			<label id="txBeginDateLab">贴息起始日期：</label>
			<input name="tbBizTx.txBeginDate" required="true" minDate="<%=GitUtil.getBusiDateStr() %>" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="tbBizTx.txBeginDate" allowinput="false"  onvaluechanged="dateChange" />
			
			<label id="txEndDateLab">贴息截止日期：</label>
			<input name="tbBizTx.txEndDate"  required="true" minDate="<%=GitUtil.getBusiDateStr() %>" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="tbBizTx.txEndDate" allowinput="false" onvaluechanged="dateChange"/>
		</div>	
	
	<div class="nui-dynpanel" columns="4" id="txZhDv">
		<label>贴息账户名称：</label>
		<input id="tbBizTx.txZhName" name="tbBizTx.txZhName" class="nui-textbox nui-form-input" required="true" />
	</div>	
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var txId = "<%=request.getParameter("txId") %>";
	var view = "<%=request.getParameter("view") %>";//1-查看  0-修改
	var retMsg = "0000";
	initPage();
	function initPage(){
        var json = nui.encode({"txId":txId});
		$.ajax({
            url: "com.bos.bizProductDetail.bizTx.getXwTx.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	//期限默认月
				nui.get("tbBizTx.qxdw").setValue('04');
				//按钮要赋值text
				nui.get("tbBizTx.txzt1").setText(o.tbBizTx.txzt1);
				//贴息方式 只有01按比例贴息
				nui.get("tbBizTx.txfs").setData(getDictData("XD_TXFS0001","str","01"));
				nui.get("tbBizTx.txfs").setValue("01");
			}
        });
        if('1'==view){
        	form.setEnabled(false);
        	nui.get("btnCreate").setEnabled(false);
        }
	}
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
       /* 
        //贴息方式选择按固定金额贴息时，只能选择一个贴息主体
        //贴息主体和贴息账户校验
        var txzt1 = o.tbBizTx.txzt1;
        var txzh1 = o.tbBizTx.txzh1;
        checkAcc(txzh1,txzt1);
        if(retMsg!='0000'){
        	nui.alert(retMsg);
        	nui.get("btnCreate").setEnabled(true);
         	return;
        }
        
        //在校验时给贴息机构赋值了所以要再取一次
        o = form.getData();
        */
        //贴息方式处理---
        var txfs= nui.get("tbBizTx.txfs").getValue();
		if(txfs=="01"){//按比例贴息
			nui.get("tbBizTx.gdje").setValue('');
			nui.get("tbBizTx.xe").setValue('');
			o.tbBizTx.gdje = null;
			o.tbBizTx.xe = null;
		}else if(txfs=="02"){//选择固定金额贴息
			nui.get("tbBizTx.txbl").setValue('');
			nui.get("tbBizTx.xe").setValue('');
			o.tbBizTx.txbl = null;
			o.tbBizTx.xe = null;
		}else if(txfs=="03"){//选择限额+比例
			nui.get("tbBizTx.gdje").setValue('');
			o.tbBizTx.gdje = null;
		}
		//贴息方式处理---end
        
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizProductDetail.bizTx.saveBizTx.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	nui.alert("保存成功!");
	        	CloseWindow("ok");
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
    //贴息方式选择按比例贴息，仅展示贴息比例信息项；
    //选择固定金额贴息，仅展示固定金额信息项；
    //选择限额+比例，展示贴息比例和限额两个信息项
    function selectFs(e){
		var txfs= nui.get("tbBizTx.txfs").getValue();
		if(txfs=="01"){//按比例贴息
			$("#txbldv").css("display","block");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","none");
		}else if(txfs=="02"){//选择固定金额贴息
			$("#txbldv").css("display","none");
			$("#gdjedv").css("display","block");
			$("#xedv").css("display","none");
		}else if(txfs=="03"){//选择限额+比例
			$("#txbldv").css("display","block");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","block");
		}else{//新增时页面初始化或选择空值
			$("#txbldv").css("display","none");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","none");
		}
	}
	
	//账户名账号校验
	function checkAcc(AcctNo,zhm){
		  retMsg = "0000";
		  var json=nui.encode({"acctInd":AcctNo});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        async: false,
	        success: function (text) {
	        	var message = text.msg;
	        	if(message != '查询成功'){
	        		retMsg = "贴息账号查询失败";
	        		return;
	        	}else{
	        		var cusName = text.queryAcc.cstNm;
		        	cusName = cusName.trim();
		        	
		        	if(cusName != zhm){
		        		retMsg= "账户名与账号不匹配!";
		        		return ;
		        	}
		        	//给机构赋值
		        	var orgid = text.queryAcc.rgonCd+text.queryAcc.branchId;
		        	nui.get("tbBizTx.txjg1").setValue(orgid);
		        	
		        	//账户类型
		        	var zhbs = text.queryAcc.storeCd;
		        	if(zhbs=='0'){
		        		zhbs = '12';
		        	}else if (zhbs == '4'){
		        		zhbs = '60';
		        	}else{
		        		retMsg="不支持的账户类型"+zhbs;
		        		return;
		        	}
		        	nui.get("tbBizTx.zhlx1").setValue(zhbs);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
		        retMsg= "账号查询失败!";
	            return jqXHR.responseText;
	        }
		});
	}
	
	
	//选择贴息主体
	function selectTxzt(){
		var btnEdit = this;
        nui.open({
	        url: nui.context + "/biz/biz_product_detail/person/selectTxzt.jsp",
	        showMaxButton: true,
	        title: "选择贴息主体",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
	                 	if(data.ACC_TYPE =='0'){
	                    	nui.get("tbBizTx.zhlx1").setValue('12');
	                    }else if(data.ACC_TYPE =='4'){
	                    	nui.get("tbBizTx.zhlx1").setValue('60');
	                    }else{
	                    	nui.alert("贴息账号必须为公司账户或者内部户");
	                    	return;
	                    }
                    	btnEdit.setValue(data.TIEXI_ZT);
	                    btnEdit.setText(data.TIEXI_ZT);
	                    nui.get("tbBizTx.txzh1").setValue(data.TIEXI_ZH);
	                    nui.get("tbBizTx.txzh1").validate();
	                    nui.get("tbBizTx.txjg1").setValue(data.TIEXI_ORG_NUM);
	                    nui.get("tbBizTx.txzt1").setValue(data.TIEXI_ORG_NUM);
	                }
	            }
	        }
	    }); 
	}
	
	//日期校验 
	function dateChange(){
		var beginDate=nui.get("tbBizTx.txBeginDate").getValue();//贴息起始日期
	  	var endDate=nui.get("tbBizTx.txEndDate").getValue();//贴息截止日期
	  	if(beginDate!=""&&endDate!=""){
	  		if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//起始日期大于截止日期
				nui.alert("截止日期必须大于起始日期");
				nui.get("tbBizTx.txBeginDate").setValue("");
				nui.get("tbBizTx.txEndDate").setValue("");
		 	}
	  	}
	}
	
	/**
	 * 比较截止日期和起始日期
	*/
	function CompareDueAndShengXiaoDate(beginDate,endDate){
	  if(nui.parseDate(endDate)-nui.parseDate(beginDate)<=0){//到期日期小于生效日期
	    return false;
	  }else{
	  	return true;
	  }
	}
	/**
	*贴息标识为是的时候 
	*贴息起始日日期 必输
	*贴息截止日期 必输
	*贴息账户名称 必输 
	*/
	function selectTxMark(){
		var txMark = nui.get("tbBizTx.txMark").getValue();//贴息标识 
		if(txMark=='0'){//贴息标识---否
			$("#txDateDv").css("display","none");
			$("#txZhDv").css("display","none");
			nui.get("tbBizTx.txBeginDate").setValue("");
			nui.get("tbBizTx.txEndDate").setValue("");
			nui.get("tbBizTx.txZhName").setValue("");
		}else if(txMark=='1'){//贴息标识---是
			$("#txDateDv").css("display","block");
			$("#txZhDv").css("display","block");
		}
	}
	//隐藏下来框中的某个值(getDictData(),contactStr())
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
		var dict = nui.decode(dictStr);
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
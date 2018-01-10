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
	<input id="tbConLoanTx.txId" name="tbConLoanTx.txId" class="nui-hidden nui-form-input" />
	<input id="tbConLoanTx.txjg1" name="tbConLoanTx.txjg1" class="nui-hidden nui-form-input" />
	<input id="tbConLoanTx.zhlx1" name="tbConLoanTx.zhlx1" class="nui-hidden nui-form-input" />	
	<input name="tbConLoanTx.qxdw" id="tbConLoanTx.qxdw"  class="nui-hidden nui-form-input" />
	<input name="tbConLoanTx.bglx" id="tbConLoanTx.bglx"  class="nui-hidden nui-form-input" />
	<input name="tbConLoanTx.changeId" id="tbConLoanTx.changeId"  class="nui-hidden nui-form-input" />
	
	
	<div class="nui-dynpanel" columns="4">
		<label>贴息方式：</label>
		<input id="tbConLoanTx.txfs" name="tbConLoanTx.txfs" class="nui-text nui-form-inpu"
			 onvaluechanged="selectFs" required="true" dictTypeId="XD_TXFS0001" />
	</div>	
	<div id="txbldv" class="nui-dynpanel" columns="4">
		<label>贴息比例(%)：</label>
		<input id="tbConLoanTx.txbl" name="tbConLoanTx.txbl" class="nui-textbox nui-form-input" required="true"  vtype="negative;maxLength:11;range:0,100"/>
	</div>	
	<div id="gdjedv" class="nui-dynpanel" columns="4">	
		<label>固定金额(元)：</label>
		<input id="tbConLoanTx.gdje" name="tbConLoanTx.gdje" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div id="xedv" class="nui-dynpanel" columns="4">					
		<label>限额：</label>
		<input id="tbConLoanTx.xe" name="tbConLoanTx.xe" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div class="nui-dynpanel" columns="4">					
		<label>期限(月)：</label>
		<input name="tbConLoanTx.qx"  id="tbConLoanTx.qx" required="true" vtype="int;maxLength:4" class="nui-textbox nui-form-input"/>
	</div>	
	<div class="nui-dynpanel" columns="4">		
		<label>贴息主体：</label>
		<div style="width:100%">
			<input id="tbConLoanTx.txzt1" name="tbConLoanTx.txzt1" class="nui-buttonEdit nui-form-input" required="true" 
				allowInput="false" onbuttonclick="selectTxzt"  style="width:80%;float:left"  emptyText="--请选择--" />
		</div>
		<label>贴息账号：</label>
		<input name="tbConLoanTx.txzh1" class="nui-textbox nui-form-input" readonly="true"  id="tbConLoanTx.txzh1"  required="true" />																
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
            url: "com.bos.aft.conLoanChange.getConTx.biz.ext",
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
				nui.get("tbConLoanTx.qxdw").setValue('04');
				//按钮要赋值text
				nui.get("tbConLoanTx.txzt1").setText(o.tbConLoanTx.txzt1)
				selectFs();
				
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
        var txzt1 = o.tbConLoanTx.txzt1;
        var txzh1 = o.tbConLoanTx.txzh1;
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
        var txfs= nui.get("tbConLoanTx.txfs").getValue();
		if(txfs=="01"){//按比例贴息
			nui.get("tbConLoanTx.gdje").setValue('');
			nui.get("tbConLoanTx.xe").setValue('');
			o.tbConLoanTx.gdje = null;
			o.tbConLoanTx.xe = null;
		}else if(txfs=="02"){//选择固定金额贴息
			nui.get("tbConLoanTx.txbl").setValue('');
			nui.get("tbConLoanTx.xe").setValue('');
			o.tbConLoanTx.txbl = null;
			o.tbConLoanTx.xe = null;
		}else if(txfs=="03"){//选择限额+比例
			nui.get("tbConLoanTx.gdje").setValue('');
			o.tbConLoanTx.gdje = null;
		}
		//贴息方式处理---end
        
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.aft.conLoanChange.saveConTx.biz.ext",
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
    function selectFs(){
		var txfs= nui.get("tbConLoanTx.txfs").getValue();
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
		        	nui.get("tbConLoanTx.txjg1").setValue(orgid);
		        	
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
		        	nui.get("tbConLoanTx.zhlx1").setValue(zhbs);
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
	                    	nui.get("tbConLoanTx.zhlx1").setValue('12');
	                    }else if(data.ACC_TYPE =='4'){
	                    	nui.get("tbConLoanTx.zhlx1").setValue('60');
	                    }else{
	                    	nui.alert("贴息账号必须为公司账户或者内部户");
	                    	return;
	                    }
                    	btnEdit.setValue(data.TIEXI_ZT);
	                    btnEdit.setText(data.TIEXI_ZT);
	                    nui.get("tbConLoanTx.txzh1").setValue(data.TIEXI_ZH);
	                    nui.get("tbConLoanTx.txzh1").validate();
	                    nui.get("tbConLoanTx.txjg1").setValue(data.TIEXI_ORG_NUM);
	                }
	            }
	        }
	    }); 
	}
</script>
</body>
</html>
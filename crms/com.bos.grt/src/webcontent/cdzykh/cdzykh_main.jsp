<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lenovo
  - Date: 2017-11-02 10:03:45
  - Description:
-->
<head>
<title>质押扣划</title>
</head>
<body>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="LOAN_ORG" class="nui-hidden" name="LOAN_ORG"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			
			<label class="nui-form-label">借据编号：</label>	
			<input id="SUMMARY_NUM" class="nui-text nui-form-input" name="SUMMARY_NUM" dictTypeId="user"/>
			
			<label class="nui-form-label">借据状态：</label>
			<input id="SUMMARY_STATUS_CD" class="nui-text nui-form-input" name="SUMMARY_STATUS_CD" dictTypeId="XD_SXYW0226" />
			
			<label class="nui-form-label">借据金额：</label>
			<input id="SUMMARY_AMT" class="nui-text nui-form-input" name="SUMMARY_AMT" dataType="currency"/>
			
			<label class="nui-form-label">借据余额：</label>
			<input id="JJYE" class="nui-text nui-form-input" name="JJYE"/>
			
			<label class="nui-form-label">借据起期：</label>
			<input id="SUMMARY_BEGIN_DATE" name="SUMMARY_BEGIN_DATE"  class="nui-datepicker" enabled="false" dateFormat="yyyy-MM-dd"/>
			
			<label class="nui-form-label">借据止期：</label>
			<input id="SUMMARY_END_DATE" name="SUMMARY_END_DATE" class="nui-datepicker" enabled="false" dateFormat="yyyy-MM-dd"/>
			
			<label class="nui-form-label">合同编号：</label>
			<input id="CONTRACT_NUM" class="nui-text nui-form-input" name="CONTRACT_NUM" />
			
			<label class="nui-form-label">借款人名称：</label>
			<input id="PARTY_NAME" class="nui-text nui-form-input" name="PARTY_NAME" />
			
			<label class="nui-form-label">存单质押编号：</label>
			<input id="SURETY_NO" class="nui-text nui-form-input" name="SURETY_NO"/>
			
			<label class="nui-form-label">存单冻结编号：</label>
			<input id="FRENUM" name="FRENUM" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">存单账号：</label>
			<input id="CUACNO" name="CUACNO" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">存单币种：</label>
			<input id="CD_CURRENCY_CD" name="CD_CURRENCY_CD" class="nui-text nui-form-input" dictTypeId="CD000001"/>
			
			<label class="nui-form-label">存单权利价值：</label>
			<input id="MORTGAGE_VALUE" name="MORTGAGE_VALUE" class="nui-text nui-form-input" dataType="currency"/> 
			
			<!-- <label class="nui-form-label">存单已担保金额：</label>
			<input id="TOTAL_AMT" name="TOTAL_AMT" class="nui-text nui-form-input" dataType="currency"/> -->
			
			<label class="nui-form-label">存单已扣划金额：</label>
			<input id="TOTAL_KH_AMT" name="TOTAL_KH_AMT" class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">借据本息合计：</label>
			<input id="BXHJ_AMT" name="BXHJ_AMT" class="nui-text nui-form-input"  required="true" dataType="currency" vtype="float;maxLength:26;"/>
			
			<label class="nui-form-label">本次还款金额：</label>
			<input id="KH_AMT" name="KH_AMT" class="nui-textbox nui-form-input" required="true" dataType="currency" vtype="float;maxLength:26;" />
			
			<label class="nui-form-label">申请日期：</label>
			<input id="APPLY_DATE" name="APPLY_DATE" class="nui-datepicker" enabled="false" dateFormat="yyyy-MM-dd"/>
		</div>
		</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" id="bxhj" onclick="bxhj()">本息查询</a>
			<a class="nui-button" iconCls="icon-search" id="save" onclick="save()">保存</a>
		</div>
	<script type="text/javascript">
    	nui.parse();
    	var zykhId = "<%=request.getParameter("zykhId")%>";
    	var proFlag = "<%=request.getParameter("proFlag")%>";
    	var form = new nui.Form("#form"); 
    	function initPage(){
    		var json = nui.encode({"zykhId":zykhId});
			$.ajax({
            	url: "com.bos.grt.cdzykh.cdzykh.getCdzykhInfo.biz.ext",
           		type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
            	async:false,
            	success: function (mydata) {
            		if(mydata.data[0]){
            			var o = nui.decode(mydata.data[0]);
            			form.setData(o);
            		}
				}
        	});
        	
        	//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			if("1" != proFlag){
				nui.get("bxhj").hide();
				nui.get("save").hide();
				form.setEnabled(false);
			}
    	}
    	initPage();
    	//保存扣划信息
    	function save(){
    		form.validate();
			if (form.isValid() == false) {
				nui.alert("请先点击本息查询按钮获取该借据当前期应还本息合计总金额，再手工输入本次还款金额！");
				return;
			}
			var o = form.getData();
			//本次还款金额必须小于本息合计金额 
    		var bxhjAmt = o.BXHJ_AMT;//本息合计金额 
			var khAmt = o.KH_AMT;//本次还款金额 	
			if(parseFloat(khAmt)>parseFloat(bxhjAmt)){
				nui.alert("本次还款金额["+khAmt+"]必须小于等于该借据当前期应还本息合计金额["+bxhjAmt+"]");
				return;
			}
    		//本次还款金额必须小于存单已担保金额 
    		//var totalAmt = o.TOTAL_AMT;//存单已担保金额
    		var mortgageAmt = o.MORTGAGE_VALUE;//存单权利价值
    		var totalKhAmt = o.TOTAL_KH_AMT;//存单已扣划金额
    		var aviAmt = parseFloat(mortgageAmt)-parseFloat(totalKhAmt); //存单剩余的可扣划金额=存单权利价值-已扣划金额
    		if(parseFloat(khAmt)>parseFloat(aviAmt)){
				nui.alert("本次还款金额["+khAmt+"]必须小于等于存单当前可扣划金额["+aviAmt+"]，【存单权利价值】减去【存单已扣划金额】即为存单当前可扣划金额");
				return;
			}
			var tbCdZykhApply = {
		        "zykhId":zykhId,
		        "khAmt":khAmt,
		        "bxhjAmt":bxhjAmt
	        };
	        var json = nui.encode(tbCdZykhApply);
	        $.ajax({
            	url: "com.bos.grt.cdzykh.cdzykh.saveCdZykhApply.biz.ext",
           		type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
            	async:false,
            	success: function (mydata) {
            		nui.alert("保存成功");
            		initPage();
				}
        	});
    	}
    	//查询借据本息合计
    	function bxhj(){
    		var o = form.getData();
    		var summaryNum = o.SUMMARY_NUM;//借据编号
    		git.mask();
    		var json = nui.encode({"summaryNum":summaryNum});
			$.ajax({
            	url: "com.bos.grt.cdzykh.cdzykh.cdzykhBxcx.biz.ext",
           		type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
            	async:false,
            	success: function (mydata) {
            		if("1"==mydata.msg){//调用成功
            			var crePayQueryRq = mydata.crePayQueryRq;
            			if("00000"==crePayQueryRq.baseVO.errCod){//本息查询成功
            				git.unmask();	
            				nui.alert("本息查询成功，该借据应还本息合计金额:"+crePayQueryRq.totPrnItr);
            				nui.get("BXHJ_AMT").setValue(crePayQueryRq.totPrnItr);
            			}else{//本息查询失败 
            				git.unmask();
            				nui.alert("交易失败[提示：银承与国内保函等表外业务未发生垫款的情况下，不能调用核算本息查询，请撤销本次交易请求流程。如果不是该种情况，请联系管理员！]，核算返回失败信息："+crePayQueryRq.baseVO.errMsg);
            			}
            		}else{//调用失败
            			git.unmask();
            			nui.alert(mydata.msg);
            		}
				}
        	});
    	}
    </script>
</body>
</html>
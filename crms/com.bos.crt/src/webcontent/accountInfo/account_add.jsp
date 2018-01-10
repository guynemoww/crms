<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-31
  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%-- <script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script> --%>
</head>
<body>
<div id="form1" style="width:96%;height:90%;overflow:hidden; text-align:center;margin: 1px;" >
	<input id="id" name="tbConZh.id"  class="nui-hidden nui-form-input" value=""/>
	<input id="zhbs" name="tbConZh.zhbs"  class="nui-hidden nui-form-input" value="11"/>
	<fieldset>
		  	<legend>
		   		<span>账户信息</span>
		    </legend>
			<div class="nui-dynpanel" columns="4">
				<label>货币代号：</label>
				<input id="hbdh" name="hbdh" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100001"   dValue="01"/>
				<label>钞汇标志：</label>
				<input id="chbz" name="chbz" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100002"   dValue="0"/>
				
				<label>账户类型：</label>
				<input id="zhlx" name="tbConZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZHLX10001"/>
				
				<label>账号：</label>
				<div style="width:123%">
					<input id="zh" name="tbConZh.zh"  style="width:68%;float:left"  required="true" class="nui-textbox nui-form-input" onblur="accChange(this.value)"/>
					<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
				</div>
				
				<label>账户名称：</label>
				<input style="width:123%" id="zhmc" name="tbConZh.zhmc" required="true" class="nui-text nui-form-input" allowInput="false"/>
				
				<label>卡折标志：</label>
				<input id="kzbs" name="tbConZh.kzbs" required="true"  allowInput="false" class="nui-text nui-form-input" dictTypeId="XD_SXYW0220" />
				
				<label>开户行：</label>
				<input id="zhkhjg"  name="tbConZh.zhkhjg"  class="nui-text nui-form-input"  required="true"  allowInput="false"/>
				
				<label>账户状态：</label>
				<input id="accStatus"  name="tbConZh.accStatus"  class="nui-text nui-form-input" required="true"  allowInput="false"/>

			</div>
	</fieldset>		
	<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="add">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
		
	    
			
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	//nui.get("add").hide();
	//nui.get("updNum").hide();
	var contractId = "<%=request.getParameter("contractId")%>";
	var partyName = "";
	var currencyCd = "CNY";
	var productType = "";
	var oldzh ="";
	//进来先查询客户姓名、币种
	var json = nui.encode({"contractId":contractId});
	 $.ajax({
        url: "com.bos.accInfo.accInfo.getPartyNameByContractId.biz.ext",
        type: 'POST',
        data: json,
        async: false,
        cache: false,
        contentType:'text/json',
        success: function (mydata) {
        	var o = nui.decode(mydata);
        	partyName = o.party.partyName;
        	currencyCd = o.conInfo.currencyCd;
        	productType = o.conInfo.productType;
        	oldzh = o.tbConZh.zh;
		}
    });
		if(currencyCd == "CNY"){
    		nui.get("hbdh").setEnabled(false);
    		nui.get("chbz").setEnabled(false);
    	}
	function save() {
	debugger;
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		var data=form.getData();
		var tbConZh = data.tbConZh; 
		var reg = /[\(]/g;
		var reg1 = /[\)]/g;
		var reg2 = /[\（]/g;
		var reg3 = /[\）]/g;
		partyName = partyName.replace(reg,"");
		partyName = partyName.replace(reg1,"");
		partyName = partyName.replace(reg2,"");
		partyName = partyName.replace(reg3,"");
		partyName = partyName.trim();
		var zhmc = tbConZh.zhmc;
		zhmc = zhmc.replace(reg,"");
		zhmc = zhmc.replace(reg1,"");
		zhmc = zhmc.replace(reg2,"");
		zhmc = zhmc.replace(reg3,"");
		zhmc  = zhmc.trim();
		//放款账户0和还款账户1户名必须是借款人
		if(tbConZh.zhlx=='0'){
		//借新还旧放款账户账户名称可以不是本人账户名称
				var json = {"contractId":contractId};
		   	    msg = exeRule("RCONJXHJ_0001","1",json);
		   	    if(null != msg && '' != msg){
					if(zhmc!=partyName){
						nui.alert("放款账户账户名称必须是本人");
						return;
					}
				}
		}
		if(tbConZh.zhlx=='1'){
		//借新还旧第一还款账户账户名称可以不是本人账户名称
				var json = {"contractId":contractId};
		   	    msg = exeRule("RCONJXHJ_0001","1",json);
		   	    if(null != msg && '' != msg){
			   	     if(zhmc!=partyName){
					nui.alert("第一还款账户账户名称必须是本人");
					return;
					}
		   	    }
		}
		
		tbConZh.contractId = contractId;
		var json=nui.encode({"tbConZh":tbConZh});
		nui.get("add").setEnabled(false);
		$.ajax({
	        url: "com.bos.accInfo.accInfo.saveAccInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg=='0'){
	        		alert("保存成功!");
	        		CloseWindow("ok");
	        	}else{
	        		alert("保存失败："+text.msg);
	        		nui.get("add").setEnabled(true);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
	}

    function getAccInfo(){
    	//return;
    	var AcctNo = nui.get("zh").getValue();
    	if(AcctNo == null || AcctNo == ''){
    		alert("请输入账号！");
    		return;
    	}
    	AcctNo = AcctNo.trim();
    	oldzh = AcctNo;
    	var CurrCode = nui.get("hbdh").getValue();
    	var CashFlag = nui.get("chbz").getValue();
    	CurrCode = CurrCode.trim();
    	CashFlag = CashFlag.trim();
    	 var json=nui.encode({"acctInd":AcctNo, "currCode": CurrCode , "cashFlag" :CashFlag});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        	 	nui.alert(message);
	        		return;
	        	}
	        	//合同币种与账户币种必须一样的校验
	        	var currcd = text.hxresponse.oxd052ResBody.currCode;
	        	if(currcd!=currencyCd){
	        		nui.alert("账户币种和合同币种不匹配!");
	        		nui.get("zhkhjg").setValue('');
	        		nui.get("zhkhjg").validate();
	        		return;
	        	}
	        	
	        	
	        	//var orgid = text.queryAcc.acctRgonCd+text.queryAcc.acctBrId;
	        	var orgid = text.hxresponse.oxd052ResBody.openBrch;
	        	nui.get("zhkhjg").setValue(orgid);
	        	nui.get("zhkhjg").validate();
	        	
	        	//去掉空格
	        	debugger;
	        	var cusName = text.hxresponse.oxd052ResBody.custName;
	        	cusName = cusName.trim();
	        	
	        	nui.get("zh").setValue(AcctNo);
	        	nui.get("zhmc").setValue(cusName);
	        	//账户标识
	        	//var zhbs = text.queryAcc.acctTp;
	        //	var zhbs = text.queryAcc.storeCd;
	        	//if(zhbs=='0'){
	        	//	zhbs = '12';
	        	//}else if (zhbs=='1'){
	        	//	zhbs = '11';
	        	//}else if (zhbs == '4'){
	        	//	zhbs = '60';
	        	//}else{
	        	//	nui.alert("不支持的账户类型!");
	        	//	nui.get("zhkhjg").setValue('');
	        	//	nui.get("zhkhjg").validate();
	        	//	return;
	        //	}
	        	
	        	//nui.get("zhbs").setValue(zhbs);
        		var status = text.hxresponse.oxd052ResBody.acctStat1;
	        	if(status == 'A'){
	        		nui.get("accStatus").setValue("正常");
	        	}
        		if(status == 'C'){
	        		nui.get("accStatus").setValue("销户");
	        	}
	        	if(status == 'D'){
	        		nui.get("accStatus").setValue("久悬户");
	        	}
	        	if(status == 'I'){
	        		nui.get("accStatus").setValue("转营业外收入");
	        	}
        		//622367开头为卡
        		if(AcctNo.substring(0,6)=='622367'){
        			nui.get("kzbs").setValue('11');
        			nui.get("kzbs").validate();
        		}else{
        			nui.get("kzbs").setValue('20');
        			nui.get("kzbs").validate();
        		}
	        	
	        	
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    }
    //账号变更时
   function accChange(a){
	   if(a==oldzh){
	   	return;
	   }
		nui.get("zhkhjg").setValue('');
		nui.get("zhkhjg").validate();
		nui.get("accStatus").setValue('');
		nui.get("zhkhjg").validate();
		nui.get("kzbs").setValue('');
        nui.get("kzbs").validate();
   }
   function selectEmpOrg(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    
	                    if (data) {
	                    
	                    	if(data.auditbankno==null || data.auditbankno=='' || data.auditbankno == 'null'){
	                    		nui.alert("该机构无对应核算机构!");
	                    	}
	                    	
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });            
	    }
	</script>
</body>
</html>

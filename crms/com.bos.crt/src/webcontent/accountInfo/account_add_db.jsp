<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-31
  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form1" style="width:96%;height:90%;overflow:hidden; text-align:center;margin: 10px;" >
	<input id="id" name="tbConZh.id"  class="nui-hidden nui-form-input" value=""/>
	<input id="zhbs" name="tbConZh.zhbs"  class="nui-hidden nui-form-input" value=""/>
	<fieldset>
		  	<legend>
		   		<span>账户信息</span>
		    </legend>
			<div class="nui-dynpanel" columns="4">
				<label>账户类型：</label>
				<input id="zhlx" name="tbConZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1208"/>
				
				<label>账号：</label>
				<div style="width:123%">
					<input id="zh" name="tbConZh.zh"  style="width:68%;float:left"  required="true" class="nui-textbox nui-form-input" onblur="accChange(this.value)"/>
					<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
				</div>
				
				<label>账户名称：</label>
				<input id="zhmc" name="tbConZh.zhmc" class="nui-text nui-form-input" />
				
				<label>卡折标志：</label>
				<input id="kzbs" name="tbConZh.kzbs" required="true"  class="nui-text nui-form-input" dictTypeId="XD_SXYW0220"/>
				
				<label>开户行：</label>
				<input id="zhkhjg"  name="tbConZh.zhkhjg"  class="nui-dictcombobox nui-form-input" dictTypeId="org" required="false" enabled="false" emptyText="" />
				
				<label>账户状态：</label>
				<input id="accStatus"  name="tbConZh.accStatus"  class="nui-text nui-form-input"/>

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
        	nui.get("zhlx").setData(getDictData('XD_SXCD1208','str','3'));
        	oldzh = o.tbConZh.zh;
		}
    });
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var data=form.getData();
		var tbConZh = data.tbConZh;
		
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

	//账号重复性校验
    
    function getAccInfo(){
    	//return;
    	var AcctNo = nui.get("zh").getValue();
    	if(AcctNo == null || AcctNo == ''){
    		alert("请输入账号！");
    		return;
    	}
    	AcctNo = AcctNo.trim();
    	oldzh = AcctNo;
    	 var json=nui.encode({"acctInd":AcctNo});
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
	        		nui.get("zhkhjg").setValue('');
	        		nui.get("zhkhjg").validate();
	        		return;
	        	}
	        	
	        	//var orgid = text.queryAcc.rgonCd+text.queryAcc.branchId;
	        	var orgid = text.hxresponse.oxd052ResBody.openBrch; 
	        	nui.get("zhkhjg").setValue(orgid);
	        	nui.get("zhkhjg").validate();
	        	
	        	//去掉空格//去掉空格
	        	var cusName = text.hxresponse.oxd052ResBody.custName;
	        	cusName = cusName.trim();
	        	nui.get("zh").setValue(AcctNo);
	        	nui.get("zhmc").setValue(cusName);
	        	//账户标识
	        	//var zhbs = text.queryAcc.acctTp;
	        	//var zhbs = text.queryAcc.storeCd;
	        	//if(zhbs=='0'){
	        	//	zhbs = '12';
	        	//}else if (zhbs=='1'){
	        	//	zhbs = '11';
	        	//}else if (zhbs == '4'){
	        	//	zhbs = '60';
	        //	}
	        	/* 专用户不用校验这个
	        	else{
	        		nui.alert("不支持的账户类型!");
	        		nui.get("zhkhjg").setValue('');
	        		nui.get("zhkhjg").validate();
	        		return;
	        	} */
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
        		if(AcctNo.substring(0,6)){
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

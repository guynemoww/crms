<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	<input id="id" name="tbLoanZh.id"  class="nui-hidden nui-form-input" value=""/>
	<fieldset>
		  	<legend>
		   		<span>帐户信息</span>
		    </legend>
			<div class="nui-dynpanel" columns="4">
				<label>账户账号：</label>
				<input id="zh" name="tbLoanZh.zh" style="float: left;"   required="true" class="nui-textbox nui-form-input"/>
				
				<label>账户类型：</label>
				<input id="zhlx" name="tbLoanZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1208"/>
				
				<label>账户标识：</label>
				<input id="zhbs" name="tbLoanZh.zhbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0219"/>
				
				<label>卡折标志：</label>
				<input id="kzbs" name="tbLoanZh.kzbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0220"/>
				
				<label>账户开户网点号：</label>
				<input id="zhkhjg" name="tbLoanZh.zhkhjg" required="true"  class="nui-textbox nui-form-input"  />
					
				<label>账户开户行名称：</label>
				<input id="zhkhhmc"  name="tbLoanZh.zhkhhmc" required="true"  class="nui-textbox nui-form-input" />
		
				<label>账户名称：</label>
				<input id="zhmc" name="tbLoanZh.zhmc" class="nui-textbox nui-form-input" vtype="maxLength:200" required="true" />
		
				<label>账户币种：</label>
				<input name="tbLoanZh.currencyCd"   class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  required="true" />
		
				<label>账户余额：</label>
				<input name="tbLoanZh.balanceOfAccount" class="nui-textbox nui-form-input" vtype="maxLength:20;" required="true"  dataType="currency"/>
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
	var loanId = "<%=request.getParameter("loanId")%>";
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var data=form.getData();
		var tbLoanZh = data.tbLoanZh;
		tbLoanZh.loanId = loanId;
		var json=nui.encode({"tbLoanZh":tbLoanZh});
		nui.get("add").setEnabled(false);
		$.ajax({
	        url: "com.bos.accInfo.accInfo.saveLoanAccInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        
            	alert("保存成功!");
	        	/* if(text.msg == "02"){
	        		nui.alert("所录帐户不属于该申请客户,添加帐号成功！");
	        		CloseWindow("ok");
	        	}else if(text.msg == "01"){
	        		nui.alert("该客户没有ecif编号,添加帐号失败！");
	        	}else if(text.msg == "03"){
	        		nui.alert("添加帐号失败！");
	        	} else {
	        		CloseWindow("ok");
	        	} */
	        	nui.get("add").setEnabled(true);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	});
}

	//账号重复性校验
	//compareAccount();
	function compareAccount(){
		var loanId= nui.get("loanId").getValue();
	    var json=nui.encode({"accountInfo":{"loanId":loanId}});
		  nui.get("add").setEnabled(false);
		  $.ajax({
	        url: "com.bos.conAccountInfo.compareAccount.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var type ="01,02,03,04,05,06,07";
	        	var bizAccountTypeCd = type.split(",");
	        	//alert(nui.encode(text.accountInfos));
	        	if(nui.encode(text.accountInfos) != "[]"){
	        		var str = "";
	        		for(var j = 0;j<7;j++){
	        			for(var i = 0;i<text.accountInfos.length;i++){
	        			//alert(text.accountInfos[i].bizAccountTypeCd);
	        				if(text.accountInfos[i].bizAccountTypeCd == bizAccountTypeCd[j]){
	        					delete bizAccountTypeCd[j];
								
	        				}
	        				//alert(str);
	        			}
	        		}
	        		//alert(bizAccountTypeCd);
	        		for(var j = 0;j<bizAccountTypeCd.length;j++){
	        			if(bizAccountTypeCd[j]!="" && bizAccountTypeCd[j]!=null){
		        			str+=bizAccountTypeCd[j]+",";
	        			}
	        		}
	        		nui.get("bizAccountTypeCd").setData(getDictData('XD_SXCD1208','str',str));
	        	}else
	        			nui.get("bizAccountTypeCd").setData(getDictData('XD_SXCD1208','str',"01,02,03,04,05,06,07"));
	        	
	        	nui.get("add").setEnabled(true);
	        	 <%--if(text.accountInfos.accountId !=null && text.tbLoanZh.accountId !=''){
			       alert("该类型账号已经在该笔合同中存在，不能重复添加！");
		           nui.get("bizAccountTypeCd").setValue('');
			       return;
			    }--%>
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    }
    //查询机构
	function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        nui.get("openingBankNo").setValue(data.orgid);
                        nui.get("openingBankName").setValue(data.orgname);
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                        
                    }
                }
            }
        });            
    }
    
    function getaccountNum(){
    	var AcctNo = nui.get("accountNum").getValue();
    	if(AcctNo == null || AcctNo == null){
    		alert("请输入帐号！");return;
    	}
    	 var json=nui.encode({"AcctNo":AcctNo});
		  $.ajax({
	        url: "com.bos.CRMSInterface.CallInterfaceBiz.acctQuery.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        //{"accRs":{},"message":"0;","reMsg":"主账号不存在"}
	        	// alert(nui.encode(text));
	        	var message = text.message.split(";");
	        	
	        	var json = null;
	        	var msg="";
	        	for(var i=0;i<message.length;i++){
	        	
		        	 if(message[i] == "0"){
		        	 	alert(text.reMsg);
		        	 	return;
		        	 }
		        	 //借贷标志
					if(message[i] == "1"){
						msg+="该帐号借贷标志，";
					}
					//冻结
					if(message[i] == "2"){
						msg+="该帐号冻结.";
					}
					//休眠户
					if(message[i] == "3"){
						msg+="该帐号休眠户，";
					}
					//久悬户
					if(message[i] == "4"){
						msg+="该帐号久悬户，";
					}
					//定期账户
					if(message[i] == "5"){
						msg+="该帐号定期账户.";
					}
					//活期账户
					if(message[i] == "7"){
						msg+="以23，25开头的活期帐号不允许放款.";
					}	
					//内部账号
					if(message[i] == "6"){
						msg+="内部账号.";
					}
	        	}
	        	if(msg != ""){
	        		alert(msg);
	        		
					//form.setData(json);
	        	}
				//活期账户
			
				if(text.message=="8;" ||text.message==null || text.message == "1;" || text.message == "1;8;" 
				|| text.message==""|| text.message == "1;2;" || text.message=="2;8;"|| text.message=="1;2;8;" 
				|| text.message=="2;" || text.message=="5;"){					//alert("该帐号冻结.!");
					//alert(text.accRs.CustId);
					json = {tbLoanZh:
											{loanId:nui.get("loanId").getValue(),
											accountNum:text.accRs.AgreeMentAcctNo,
											accountName:text.accRs.ShortName,
											balanceOfAccount:text.accRs.OnLineClearedBal,
											currencyCd:text.accRs.Currency,
											openingBankName:text.accRs.OpenBank,
											openingBankNo:text.accRs.OpenBank,
											CustId:text.accRs.CustId}};
				form.setData(json);
				//alert(text.accRs.OpenBank);
				var name = git.getOrgName(text.accRs.OpenBank);
				nui.get("tbLoanZh.openingBankName").setValue(name);
				nui.get("add").show();
				nui.get("selNum").hide();
				nui.get("updNum").show();
				nui.get("accountNum").setEnabled(false);
				//针对人民币银团贷款（包括行内行外银团），放款账户，本金、利息还款账户，
				//校验只能录入98内部账户。只需要提示"必须录入98内部账户"，不要拦截。校验方法：98开头的账号。
				var json=nui.encode({"loanId":"<%=request.getParameter("loanId")%>"});
				$.ajax({
			        url: "com.bos.crt.conAccountInfo.queryBankAccount.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	
			        	if(text.msg == "02" && nui.get("accountNum").getValue().substring(0,2) != "98"){
			        		nui.alert("该笔合同是人民币银团贷款，必须录入98内部账户！");
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});	
				}	
<%--CateGory	类别代码
ShortName	户名缩写
Currency	币种
AcctType	账户类型
OnlineActualBal	当前实际余额
MasterAcctNo	主账户
OpenBank	开户行--%>
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    }
    function updNum(){
    	nui.get("selNum").show();
				nui.get("updNum").hide();
				nui.get("accountNum").setEnabled(true);
				nui.get("add").hide();
			var	json = {tbLoanZh:
											{loanId:nui.get("loanId").getValue(),
											accountNum:"",
											accountName:"",
											balanceOfAccount:"",
											currencyCd:"",
											openingBankName:"",
											openingBankNo:""}};
				form.setData(json);
    }
	</script>
</body>
</html>

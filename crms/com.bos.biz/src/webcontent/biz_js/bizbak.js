$(function() {
	
//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
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
	
	
	
	
	
	//业务明细品种页面初始化方法
var Type;
function productInit(amountDetailId,bizType,bizNature,type,path,json,showtype){
	//git.mask("form1");
	Type = type;
	if(bizNature=="1"){
		nui.get("bizSgDetail.loanAmt").name="bizSgDetail.creditTotalExposure";
	}
	if(bizNature=="2"){
		path="com.bos.biz.limitApply.getBizDetailAndTbir.biz.ext";
		json = nui.encode({"bizAmDetail/amountDetailId":amountDetailId,"bizType":bizType});
		nui.get("bizSgDetail.approveDetailId").name="bizSgDetail.amountDetailId";
		nui.get("bizSgDetail.loanAmt").name="bizSgDetail.exposureLoanAmt";
		 //proFlag
		//获得批复下的综合授信业务品种信息
			if(Type!="null"&&Type!=null&&Type!=""){
				path="com.bos.biz.bizApprove.getApproveDetailAndTbir.biz.ext";
				nui.get("bizSgDetail.approveDetailId").name="bizSgDetail.approveDetailId";
				nui.get("bizSgDetail.loanAmt").name="bizSgDetail.approveExposureLoanAmt";
				json = nui.encode({"DetailId":amountDetailId,"bizType":bizType,"bizNature":bizNature});
				if(Type=="0"){
					nui.get("btnCreate").hide();
					form.setEnabled(false);
				}
			}
		}
		//同业
		else if(bizNature=="3"){
				path="com.bos.financial.Amount.getAmountDetailAndTbir.biz.ext";
				json = nui.encode({"bizAmDetail/amountDetailId":amountDetailId,"bizType":bizType});
				nui.get("bizSgDetail.approveDetailId").name="bizSgDetail.amountDetailId";
				nui.get("bizSgDetail.loanAmt").name="bizSgDetail.exposureLoanAmt";
				//proFlag
				//获得批复下的同业品种信息
				if(Type!="null"&&Type!=null&&Type!=""){
					path="com.bos.financial.financialProcess.getFinancialApvDetailAndTbir.biz.ext";
					nui.get("bizSgDetail.approveDetailId").name="bizSgDetail.approveDetailId";
					nui.get("bizSgDetail.loanAmt").name="bizSgDetail.approveExposureLoanAmt";
					json = nui.encode({"DetailId":amountDetailId,"bizType":bizType,"bizNature":bizNature});
					if(Type=="0"){
						nui.get("btnCreate").hide();
						form.setEnabled(false);
					}
				}
		}else{
				nui.get("bizSgDetail.loanAmt").setEnabled(false);
		}
	 //proFlag
	 //单笔查询批复明细信息和利率信息
	
		if(Type!=""&&Type!=null&&Type!="null"){
			if(bizNature=="1"){
				path="com.bos.biz.pro.getApproveInfo.getApproveSgDetailAndTbir.biz.ext";
				if(Type=="0"){
					nui.get("btnCreate").hide();
					form.setEnabled(false);
				}
			}
		}
	
	//查看功能
	if(showtype=="2"){
		nui.get("btnCreate").hide();
		form.setEnabled(false);
	}	
        $.ajax({
            url: path,
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                
                  //如果业务性质是综合授信，品种是进口信用证
                if(o.bizSgDetail.productType=="10300401"&&bizNature=="2"){
                	if(o.bizSgProType.documentaryType=="02"){
                		$("#cycle").css("display","");
						$("#unit").css("display","");
                		nui.get("bizSgProType.creditTeam").show();
					 	nui.get("bizSgProType.cycleUnit").show();
                	}
                }
                
                form.setData(o);
                
                //如果利率信息中其他利率约定有值的话隐藏基准利率代码和上下浮动百分比
                if(o.tbir.otherRateAgreement){
                	$("#baseRateKey").css("display","none");
					nui.get("tbConLoanIntRate.baseRateKey").hide();
					
					$("#rateFloatProportion").css("display","none");
					nui.get("tbConLoanIntRate.rateFloatProportion").hide();
						
					nui.get('table1').refreshTable();
                }
                
                //品种默认期限单位为月
                if(o.bizSgDetail.cycleUnit==null||o.bizSgDetail.cycleUnit==""){
                	nui.get("bizSgDetail.cycleUnit").setValue("04");
                }
                
                form.setIsValid(true);
                //nui.get("bizSgDetail.currencyCd").setValue("156");
                window['formData'] = o;
                if(o.bizSgDetail.shareLimitGroupApplId){
                	nui.get("bizSgDetail.loanAmt").setEnabled(false);
                }
                if(o.bizSgDetail.productType=="10400101"){
                	nui.get("bizSgProType.partyName").setText(o.bizSgProType.partyName);
                }
                //品种是保函类品种或者是法人透支贷款时不显示还款方式字段
                if(o.bizSgDetail.productType=="10900101"||o.bizSgDetail.productType=="10900102"||o.bizSgDetail.productType=="10100401"||o.bizSgDetail.productType=="10500101"||o.bizSgDetail.productType=="10700101"||o.bizSgDetail.productType=="10300401"){
                	$("#repaymentType").css("display","none");
                	nui.get("bizSgDetail.repaymentType").hide();
                	if(o.bizSgDetail.productType=="10100401"||o.bizSgDetail.productType=="10700101"){
                		nui.get('table2').refreshTable();
                	}else{
                		nui.get('table1').refreshTable();
                	}
                }
                if(o.bizSgDetail.productType=="10200102"||o.bizSgDetail.productType=="10200101"){
                	var dailiParty = o.bizSgProType.partyId;
                	if(dailiParty!=""&&dailiParty!=null){
                		var json3 = nui.encode({"partyId":dailiParty});
				        $.ajax({
				            url: "com.bos.biz.pub.GetForCsm.getCustInfoByPartyId.biz.ext",
				            type: 'POST',
				            data: json3,
				            cache: false,
				            contentType:'text/json',
				            cache: false,
				            success: function (mydata) {
				            	nui.get("bizSgProType.partyId").setText(mydata.party.partyName);
				            }
				       });
                	}
                }
                
                git.unmask("form1");
            },
             error: function (jqXHR, textStatus, errorThrown) {
             	git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
            
        });
}


//业务明细品种页面保存方法
function productSave(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
		nui.get("btnCreate").setEnabled(false);
        var productValue=nui.get("bizSgDetail.productType").value;
        var savepath="com.bos.biz.bizApply.saveBizDetail.biz.ext";
        nui.get("entity").setValue(nui.getDictText("bizTypeTable",productValue));
        if(bizNature=="2"){
        	nui.get("entity").setValue(nui.getDictText("amountTypeTable",productValue));
        	var a = nui.get("entity").value;
        	savepath = "com.bos.biz.limitApply.saveAmountDetailAndTbir.biz.ext";
        	 //proFlag
        	 //保存批复下的综合授信业务品种信息
        	if(Type!="null"&&Type!=""&&Type!=null){
        		savepath = "com.bos.biz.bizApprove.saveAppProductDetail.biz.ext";
        	}
        }
         //proFlag
        //单笔修改保存批复明细信息和利率信息
        if(bizNature=="1"&&(Type!="null"&&Type!=""&&Type!=null)){
			savepath="com.bos.biz.pro.saveApproveInfo.saveApproveSgDetailAndTbir.biz.ext";
		}
		if(bizNature=="3"){
			nui.get("entity").setValue(nui.getDictText("financialTypeTable",productValue));
			savepath="com.bos.financial.Amount.saveAmountDetailAndTbir.biz.ext";
			if(Type!="null"&&Type!=""&&Type!=null){
        		savepath = "com.bos.financial.financialProcess.saveFinancialApvProductDetail.biz.ext";
        	}
		}
        var o = form.getData();
        
        //如果贷款品种是项目类校验项目信息必输(除并购贷款)
       	if(o.bizSgDetail.productType=='10100201'||o.bizSgDetail.productType=='10100202'||o.bizSgDetail.productType=='10100203'||o.bizSgDetail.productType=='10100204'||o.bizSgDetail.productType=='10100205'||o.bizSgDetail.productType=='10100701'){
       		var productInfo =nui.get("grid1");
			if(productInfo.getData().length<1){
				nui.alert("请填写项目信息");
				nui.get("btnCreate").setEnabled(true);
				return;
			}
       	}
       	//判断项目贷款评级信息
       	if(o.bizSgDetail.productType=='10100201'||o.bizSgDetail.productType=='10100202'||o.bizSgDetail.productType=='10100203'||o.bizSgDetail.productType=='10100204'||o.bizSgDetail.productType=='10100205'||o.bizSgDetail.productType=='10100701'||o.bizSgDetail.productType=='10100206'){
       		var productInfo =nui.get("grid1");
       		var ps = productInfo.getData();
       		for(var i=0;i<+ps.length;i++){
       			if(ps[i].professionalTypeCd==''||ps[i].professionalTypeCd==null){
       				alert("项目："+ps[i].projectName+" 未发起专业贷款标示判断");
       				nui.get("btnCreate").setEnabled(true);
       				return;
       			}
       		}

       	}
       	//如果贷款品种是汇票信息类
       	//if(o.bizSgDetail.productType=='10200101'||o.bizSgDetail.productType=='10200102'){
       		//var productInfo =nui.get("grid1");
       		//if(productInfo.getData().length<1){
       			//nui.alert("请填写汇票信息");
       			//nui.get("btnCreate").setEnabled(true);
				//return;
       		//}
       //}
       	
       //如果其他利率约定有值的话清空基准利率代码和上下浮动百分比
        if(o.tbir.otherRateAgreement){
        	o.tbir.baseRateKey=null;
        	o.tbir.rateFloatProportion=null;
        }else{
        	o.tbir.otherRateAgreement=null;
        }
        
        var json = nui.encode(o);
        
        $.ajax({
            url:savepath,
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	//alert(nui.encode(text));
            	if(text.msg){
            		if(text.msg=="1"){
            			CloseWindow("ok");
            		}else{
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		nui.get("btnCreate").setEnabled(true);
	            	}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
}

//是否显示涉农贷款种类
function checkAgriculture(){
	var Agriculture = nui.get("bizYesno.isAgricultureLoans").value;
	if(Agriculture=="0"){
		$("#agroLoanType").css("display","none");
		nui.get("bizYesno.agroLoanType").hide();
		nui.get('table1').refreshTable();
		nui.get("bizYesno.agroLoanType").setValue("");
		nui.get("bizYesno.agroLoanType").setText("");
	}else{
		$("#agroLoanType").css("display","");
		nui.get("bizYesno.agroLoanType").show();
		nui.get('table1').refreshTable();
		nui.get("bizYesno.agroLoanType").setValue("");
		nui.get("bizYesno.agroLoanType").setText("");
	}
}

//是否中投保担保
function checkisSteelTrade(){
		var isSteelTrade = nui.get("bizYesno.isSteelTrade").value;
		if(isSteelTrade=="1"){
		//alert(1);
			$("#isInsuredGuarantee").css("display","");
			nui.get("bizYesno.isInsuredGuarantee").show();
			nui.get('table1').refreshTable();
		}else{
		//alert(2);
			$("#isInsuredGuarantee").css("display","none");
			nui.get("bizYesno.isInsuredGuarantee").hide();
			nui.get('table1').refreshTable();
		}
}

//是否供应链
function checkwhetherScf(){
		var whetherScfCd = nui.get("bizYesno.whetherScfCd").value;
		if(whetherScfCd=="1"){
		//alert(1);
			$("#scfBusiness").css("display","");
			nui.get("bizYesno.scfBusiness").show();
			getScfBusiness();
			nui.get('table1').refreshTable();
		}else{
		//alert(2);
			$("#scfBusiness").css("display","none");
			nui.get("bizYesno.scfBusiness").hide();
			nui.get('table1').refreshTable();
		}
}

	function selectTrade(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
//客户信息
function getCustmer(){
		var json = nui.encode({"applyId":bizId});
        $.ajax({
            url: "com.bos.biz.pro.ProRelatedDocuments.viewCustomer.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                if(o.msg){
                	nui.alert(o.msg);
                }else{
                	nui.open({
            			url: nui.context + o.url,
            			title: "客户信息", 
            			width: 1024, 
        				height: 600,
        				allowResize:true,
        				showMaxButton: true,
           				ondestroy: function (action) {
                		
            			}
        			});
                }
            }
        });
}

//批复信息
function getApprove(){
	nui.open({
            url: nui.context + '/com.bos.biz.checkForNoticeApprove.flow?applyId='+originalApplyId+'&approveType='+approveType,
            title: "批复信息",
            width: 800,
            height: 600,
            ondestroy: function (action) {            
                
        	}
       	 }); 
}

//借据信息
function getSummary(){
	var json = nui.encode({"applyId":bizId});
	$.ajax({
        url: "com.bos.biz.Extension.getSummaryInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
        	 if(mydata.msg){
        	 	nui.alert(mydata.msg);return;
        	 }
			 nui.open({
		           url: nui.context + "/pay/pay_duebill_tittle.jsp?loanNum="+mydata.loanSummary.loanNum+"&loanDetailId="+mydata.loanSummary.loanDetailId+"&flag=1",
		           title: "借据信息",
		           width: 800,
				   height: 600
			});
		}
	});
}

//合同信息
function getContract(){
	nui.open({
		url:nui.context + "/biz/getContractInfoByPartyId.jsp?partyId="+corpId,
		title: "合同列表",
            width: 1100,
            height: 450,
            onload:function(){
            	top.bizConWin = this;
            },
            ondestroy: function (action) {            
                
        }
	});
}

});	

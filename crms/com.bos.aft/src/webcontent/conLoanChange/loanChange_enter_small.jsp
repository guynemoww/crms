<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-08-04
  - Description:
-->
<head>
<title>贷后变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>可变更的借据信息</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	
		<div class="nui-dynpanel" columns="6">
		
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<!-- <a class="nui-button"onclick="query">查询</a> -->
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
			<label class="nui-form-label">贷后变更类型：</label>
			<input name="loanChangeType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHBG0001" id="loanChangeType" 
					style="margin-right:5px;height:21px;width:13%"/>
			<a class="nui-button" id="btnCreate" onclick="add" style="margin-right:27px;height:21px">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.conLoanChange.findLoanList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
		        <div field="SUMMARY_NUM" headerAlign="center" align="center" >借据编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
				<div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务种类</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center">借据止期</div>
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var loanChangeType = nui.get("loanChangeType");
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //借据列表
	
	var busDate;
	
	query();
	function query(){
	
		var json = nui.encode({});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getBusDate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
				//alert(mydata.busDate);
            	//var o = nui.decode(mydata);
            	//form.setData(o);
            	busDate = mydata.busDate;
			}
        });
	
		var o = form.getData();
		grid.load(o);
		//grid.load({"partyName":o.map.partyName,"contractNum":o.map.contractNum,"summaryNum":o.map.summaryNum});
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			//客户链接
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM
       				+ '\');return false;" value="'
       				+ e.data[i].PARTY_ID
       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
       				
       			//合同链接
	       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['CONTRACT_NUM']+'</a>';
	       				
       			//借据链接
       			e.data[i]['SUMMARY_NUM']='<a href="#" onclick="goToLoanSum();return false;" value="'
       				+ e.data[i].contractDetailId
       				+ '">'+e.data[i]['SUMMARY_NUM']+'</a>';
       		}
       });
	}
	
	function goToLoan(e){
		var row=grid.getSelected();
		//alert(row.CONTRACT_ID);
		nui.open({
            url:nui.context +"/crt/con_info/con_tree.jsp?contractId="+row.CONTRACT_ID+"&contractType=02&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function goToLoanSum(e){
		var row=grid.getSelected();
		//alert(row.CONTRACT_ID);
		nui.open({
            url:nui.context +"/pay/payout_info/pay_tree.jsp?loanId="+row.LOAN_ID+"&processInstId=0&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	/* function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		//alert(ps[0]+"--"+ps[1]);
		var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
            + partyId+"&partyNum="+partyNum+"&qote=1";
        nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
	} */
	
	function clickCust(partyId,partyNum){
		var ps = partyId.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var url = nui.context +  "/csm/loan/loan_tree.jsp?partyId="
	        + partyId+"&qote=1&partyNum="+partyNum;
	     //查看
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "查看客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	 	 });
	}
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		
		if(loanChangeType.getValue()=="") {
			return nui.alert("请选择贷后变更类型");
		}
		
		if(row.PRODUCT_TYPE=="01006001"|| row.PRODUCT_TYPE=="01006002" 
		|| row.PRODUCT_TYPE=="01006010" //村镇银行贴现产品
		) {
			return nui.alert("汇票贴现不可做贷后变更！");
		}
		
		if((row.PRODUCT_TYPE=="01008001" || row.PRODUCT_TYPE=="01008010") && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银行承兑汇票不可做贷后变更！");
		}
		if(row.PRODUCT_TYPE=="01008002" && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银承通不可做贷后变更！");
		}
			if(row.PRODUCT_TYPE!="01013001" || row.PRODUCT_TYPE!="01013010" ) {
		if(loanChangeType.getValue()=="17"){
					return nui.alert("不是对公委托贷款！");
		
		}
		}
		
			if(loanChangeType.getValue()=="16"){
		
		if( row.TINGXI_STATUS=="01" ){
			return nui.alert("停息贷款不可做利息调整");
		}
		if(row.REPAY_TYPE=="1200"){
					return nui.alert("一次性还本付息到期前不能做利息调整");
		
		}
		
		}
		
		if(row.SUMMARY_STATUS_CD=="03") {
			if(loanChangeType.getValue()=="02") {
				return nui.alert("逾期借据不能做还款方式变更！");
			} 
			if(loanChangeType.getValue()=="06") {
				return nui.alert("逾期借据不能做期限变更！");
			}
			if(loanChangeType.getValue()=="10" && row.JJYQBJ > 0) {
				return nui.alert("该笔借据有逾期本金，不能做还本计划变更！");
			}
			/* if(loanChangeType.getValue()=="11") {
				return nui.alert("逾期借据不能做提前还款！");
			} */
		} 
		
		if(loanChangeType.getValue()=="03") {
			if(row.REPAY_TYPE=="0300" || row.REPAY_TYPE=="0400") {
				return nui.alert("阶段性还款方式不能做约定还款日变更！");
			}
		}
		
		if(loanChangeType.getValue()=="04" && row.PRODUCT=="2" && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("仅贷款业务能办理还款账号变更！");
		}
		
		if(loanChangeType.getValue()=="08") {
			if(row.BIZ_TYPE!="04") {
				return nui.alert("小贷才可做贴息主体变更！");
			} 
			if(row.TIEXI_STATUS==null || row.TIEXI_STATUS=="") {
				return nui.alert("贴息状态为空，请联系系统管理员！");
			}
			if(row.TIEXI_STATUS!="01") {
				return nui.alert("没有原贴息主体信息，无法变更！");
			}
		} 
		
		if(loanChangeType.getValue()=="09") {
			if(row.BIZ_TYPE!="04") {
				return nui.alert("小贷才可做贴息、暂停贴息！");
			}
			if(row.TIEXI_STATUS==null || row.TIEXI_STATUS=="") {
				return nui.alert("贴息状态为空，请联系系统管理员！");
			}
		} 
		
		if(loanChangeType.getValue()=="14") {
			if(row.TINGXI_STATUS==null || row.TINGXI_STATUS=="") {
				return nui.alert("停息状态为空，请联系系统管理员！");
			}
		}
		
		if(loanChangeType.getValue()=="02" && row.REPAY_TYPE=="21") {
			return nui.alert("还款方式为预收息的不能调整");
		}
		
		if(loanChangeType.getValue()=="02" && row.TINGXI_STATUS=="01") {
			return nui.alert("停息贷款不可做还款方式变更");
		}
		
		if(loanChangeType.getValue()=="06" && row.REPAY_TYPE=="21") {
			return nui.alert("预付息贷款不能做期限变更");
		}
		
		if(loanChangeType.getValue()=="06") {
			if(row.END_DATE <= busDate) {
				return nui.alert("营业日期小于到期日才可做期限变更！");
			}
		}
		
		if(loanChangeType.getValue()=="10") {
			if(row.REPAY_TYPE!="1400" && row.REPAY_TYPE!="1410") {
				return nui.alert("只有[按还本计划表还息按还本计划表还本]，[按周期还息按还本计划表还本]，才可进行还本计划表变更");
			}
		}
		
		/* if(loanChangeType.getValue()!="12" && row.PRODUCT_TYPE=="01007007") {
			return nui.alert("进口信用证只能做信用证修改");
		}
		
		if(loanChangeType.getValue()!="13" && row.PRODUCT_TYPE=="01007008") {
			return nui.alert("进口保函只能做保函修改");
		}  */
		
		if(row.PRODUCT_TYPE.substr(0,5)=="01007") {
			if(loanChangeType.getValue()!="12" && loanChangeType.getValue()!="13" && loanChangeType.getValue()!="06") {
				return nui.alert("国际业务只能做信用证修改、保函修改和展期！");
			}
		}
		
		if(loanChangeType.getValue()=="12" && row.PRODUCT_TYPE!="01007007") {
			return nui.alert("只有进口信用证才能做信用证修改");
		}
		
		if(loanChangeType.getValue()=="13" && row.PRODUCT_TYPE!="01007008") {
			return nui.alert("只有进口保函才能做保函修改");
		} 
		
		if(loanChangeType.getValue()=="12" && row.BEGIN_DATE == busDate) {
			return nui.alert("放款当天不能做信用证修改！");
		}
		
		if(loanChangeType.getValue()=="13" && row.BEGIN_DATE == busDate) {
			return nui.alert("放款当天不能做保函修改！");
		}
		
		
		if(row.TINGXI_STATUS=="01" && (loanChangeType.getValue()=="11" || loanChangeType.getValue()=="15" || loanChangeType.getValue()=="18" )){
		return nui.alert("停息不能做提请还款、合作商代偿、指定账号还款！");
		
		}
		
		nui.get("btnCreate").setEnabled(false);
		
		//var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"summaryId":row.SUMMARY_ID,"loanChangeType":"01"});
		var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"summaryId":row.SUMMARY_ID,"loanChangeType":loanChangeType.getValue(),"isSmall":"1"});
		$.ajax({  
	        url: "com.bos.aft.conLoanChange.createConLoanChange.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	if(data.flag){
                	return nui.alert(data.flag);
                }
            	nui.open({//传值到tree页面
		            url: nui.context+"/aft/conLoanChange/loanChange_tree.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&summaryId="+row.SUMMARY_ID+"&loanChangeType="+loanChangeType.getValue()+"&processInstId="+data.processInstId+"&proFlag=1",
		            showMaxButton: false,
		            title: "贷后变更",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	query();//重新刷新页面
		            }
	        	});
	        	if(data.msg){
	        		if(data.msg != "创建成功！") {
	        			alert(data.msg);
	        		}
                }
            	//git.go(nui.context+"/aft/conLoanChange/loanChange_tree.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&summaryId="+row.SUMMARY_ID+"&loanChangeType=01",parent); 
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
	
	//初始化自然人证件类型
	function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	
    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-06
  - Description:TB_AFT_AFTER_LOAN_INFO, com.bos.dataset.aft.TbAftAfterLoanInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="借据明细查询" >
<center>
 <form id="form1" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6" id="table1">
		<label>借款人名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input" />
		
		<label>合同号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input" />
		
		<label>借据号：</label>
		<input name="map/businessNum" required="false" class="nui-textbox nui-form-input" />
		
		<label>业务性质：</label>
		<input id="map/productTypeCd" name="map/productTypeCd" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_GGCD033" />
		
		<label>担保方式：</label>
		<input name="map/mainSuretyMode" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDZC0005" />
		
		<label>归属机构：</label>
		<input id ="map/orgNum" name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
		
		<label>业务产品：</label>
		<input id="map/productType" name="map/productType" class="nui-buttonEdit nui-form-input"
		allowInput="false" onbuttonclick="selectProduct" />
		
		<label>贷款投向：</label>
		<input id="map/loanDirection" name="map/loanDirection" required="false" class="nui-buttonEdit"  onbuttonclick="selectLoanDirection" />
		
		<label>币种：</label>
		<input id="map/currencyType" name="map/currencyType" required="false" class="nui-dictcombobox nui-form-input"   data="currencyCode" onvaluechanged="selectCurrency"/>
		
		<label>信用等级：</label>
		<input id="map/creditRatingCd" name="map/creditRatingCd" required="false" class="nui-dictcombobox nui-form-input"   data="creditRatingCode"  />
		
		<label>分类：</label>
		<input id="map/classificationResultCd" name="map/classificationResultCd" required="false" class="nui-dictcombobox nui-form-input"  data="classificationResult" />
		
		<div  id="ChnLoanAmt">
		<label>贷款发放额（万元）：</label>
		</div>
		<div  id="ChnLoanAmt2">
		<input name="map/loanAmtMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/loanAmtMax" required="false" class="nui-textbox nui-form-input"  style="width: 38%;"/>
		</div>
		<div id="UsdLoanAmt">
		<label>贷款发放额（万美元）：</label>
		</div>
		<div id="UsdLoanAmt2">
		<input name="map/usdLoanAmtMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/usdLoanAmtMax" required="false" class="nui-textbox nui-form-input"  style="width: 38%;"/>
		</div>
		
		<label>企业规模：</label>
		<input name="map/shbackEnterpriseSizeCd" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0025" />
		
		<label>企业控股类型：</label>
		<input name="map/bizHappenType" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0160"/>
		
		
		<div  id="ChnAmt">
		<label>借据余额（万元）：</label>
		</div>
		<div  id="ChnAmt2">
		<input name="map/chnAmtMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/chnAmtMax" required="false" class="nui-textbox nui-form-input"  style="width: 38%;"/>
		</div>
		<div id="UsdAmt">
		<label>借据余额（万美元）：</label>
		</div>
		<div id="UsdAmt2">
		<input name="map/usdAmtMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/usdAmtMax" required="false" class="nui-textbox nui-form-input"  style="width: 38%;"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:35px" borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	  <%--  <a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
    </div>
 </form>
 	

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.pub.ledgerMsg.getLedgerMessageList.biz.ext"
	dataField="ledData"
	allowResize="true" showReloadButton="false" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
			<div type="checkcolumn">选择</div>
        <div field="bizNum" headerAlign="center"  allowSort="true" width="9%">批复号</div>
		<div field="contractNum" headerAlign="center"  allowSort="true" width="9%">合同号</div>
		<div field="businessNum" headerAlign="center"  allowSort="true" width="7%">借据号</div>
		<div field="partyName" headerAlign="center"  allowSort="true" width="7%">借款人名称</div>
		<div field="certificateCode" headerAlign="center"  allowSort="true" width="7%">借款人组织代码</div>
		<div field="productTypeName" headerAlign="center"  allowSort="true" width="4%" dictTypeId="XD_GGCD033">业务性质</div>
		<div field="productName" headerAlign="center"  allowSort="true" width="7%" >业务产品</div>
		<div field="mainSuretyModeName" headerAlign="center"  allowSort="true" width="4%" dictTypeId="CDZC0005">担保方式</div>
		<div field="loanDirectionName" headerAlign="center"  allowSort="true" width="5%" dictTypeId="XD_CDKH2011">贷款投向</div>
		<div field="currencyName" headerAlign="center"  allowSort="true" width="4%" dictTypeId="CD000001">币种</div>
		<div field="chnLoanAmt" headerAlign="center"  allowSort="true" width="7%">借据发放额(万元）</div>
		<div field="usdLoanAmt" headerAlign="center"  allowSort="true" width="7%">借据发放额(万美元）</div>
		<div field="chnAmt" headerAlign="center"  allowSort="true" width="7%">借据余额(万元）</div>
		<div field="usdAmt" headerAlign="center"  allowSort="true" width="7%">借据余额(万美元）</div>
		<div field="loanActualPaymentDate" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd" width="5%">借据放款日</div>
		<div field="loanActualMaturity" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd" width="5%">借据到期日</div>
		<div field="creditRatingCd" headerAlign="center"  allowSort="true" width="3%">信用等级</div>
		<div field="classificationResultName" headerAlign="center"  allowSort="true" width="3%" dictTypeId="XD_FLCD0001">分类</div>
		<div field="orgname" headerAlign="center"  allowSort="true" width="5%">归属机构</div>
	</div>		
		<!-- 
		<div field="approvalNum" headerAlign="center"  allowSort="true" >批复编号</div>
		<div field="subcontractNum" headerAlign="center"  allowSort="true" >担保合同编号</div>
		<div field="cardNum" headerAlign="center"  allowSort="true" >权证编号</div>
		<div field="loanDirectionTwo" headerAlign="center"  allowSort="true"  dictTypeId=XD_KHCD0092>贷款投向2002版本</div>
		<div field="normalbalance" headerAlign="center"  allowSort="true" >借据正常余额（当日）</div>
		<div field="dullbalance" headerAlign="center"  allowSort="true" >呆滞余额</div>
		<div field="badbalance" headerAlign="center"  allowSort="true" >呆账余额</div>
		<div field="interestbalance1" headerAlign="center"  allowSort="true" >表内欠息余额</div>
		<div field="interestbalance2" headerAlign="center"  allowSort="true" >表外欠息余额</div>
		<div field="overduebalance1" headerAlign="center"  allowSort="true" >三个月内逾期余额</div>
		<div field="overduebalance2" headerAlign="center"  allowSort="true" >表外欠息余额</div>
		<div field="depositAmount" headerAlign="center"  allowSort="true" >保证金金额</div>
		<div field="repaymentType" headerAlign="center"  allowSort="true" dictTypeId="XD_SXCD1162">还款方式</div>
		<div field="ratioMargin" headerAlign="center"  allowSort="true" >保证金比例</div>
		<div field="subName" headerAlign="center"  allowSort="true" >担保人名称</div>
		<div field="orgNum" headerAlign="center"  allowSort="true"  dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center"  allowSort="true" dictTypeId="user">经办人</div>
		 -->		

		</div>
	</div>
	</center>
</div>
</div>				
    <script type="text/javascript">
    var currencyCode = [{ id: '01', text: '本外币折人民币' }, { id: '02', text: '人民币'}, { id: '03', text: '外币折美元'}, { id: '04', text: '外币折人民币'}];
 	var creditRatingCode = [{ id: '', text: '--请选择--' },{ id: 'AAA', text: 'AAA' },{ id: 'AA+', text: 'AA+' },{ id: 'AA', text: 'AA' },{ id: 'AA-', text: 'AA-' },{ id: 'A+', text: 'A+' },
							{ id: 'A', text: 'A' },{ id: 'A-', text: 'A-' },{ id: 'BBB+', text: 'BBB+' },{ id: 'BBB', text: 'BBB' },{ id: 'BBB-', text: 'BBB-' },
							{ id: 'BB', text: 'BB' },{ id: 'B', text: 'B' },{ id: 'CCC', text: 'CCC' },{ id: 'CC', text: 'CC' },
							{ id: 'C', text: 'C' },{ id: 'D', text: 'D' },{ id: '未评级', text: '未评级' }];
	var classificationResult = [{ id: '', text: '--请选择--' },{ id: '01', text: '正常' }, { id: '02', text: '关注'}, { id: '03', text: '次级'}, { id: '04', text: '可疑'}, { id: '05', text: '损失'}, { id: 'no', text: '未分类'}];
 	var tjkj=[{ id: '', text: '--请选择--' },{ id: 'YWXZ', text: '业务性质' },{ id: 'YWCP', text: '业务产品' },{ id: 'DBFS', text: '担保方式' },
			  { id: 'DKTX', text: '贷款投向' },{ id: 'BZ', text: '币种' },{ id: 'XYDJ', text: '信用等级' },
			  { id: 'FXFL', text: '分类' },{ id: 'QYGM', text: '企业规模' },{ id: 'QYKG', text: '企业控股类型' },
			  { id: 'GSJG', text: '归属机构' },{ id: 'KHMC', text: '客户名称' },{ id: 'DKFFE', text: '贷款发放额' }];
 	
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    var chnLoanAmt =  grid.getColumn(11);
    var usdLoanAmt =  grid.getColumn(12);
	var chnAmt =  grid.getColumn(13);
    var usdAmt =  grid.getColumn(14);
	 grid.hideColumn(usdAmt);
	grid.hideColumn(usdLoanAmt);
    nui.get("map/currencyType").setValue("01");
 	nui.get("map/creditRatingCd").setValue("");
 	nui.get("map/classificationResultCd").setValue("");
 	nui.get("map/loanDirection").setText("--请选择--");
 	nui.get("map/orgNum").setText("--请选择--");
 	nui.get("map/productType").setText("--请选择--");
    //search();
    $("#UsdLoanAmt").css("display","none");
	$("#UsdLoanAmt2").css("display","none");
	$("#ChnLoanAmt").css("display","");
	$("#ChnLoanAmt2").css("display","");
	$("#UsdAmt").css("display","none");
	$("#UsdAmt2").css("display","none");
	$("#ChnAmt").css("display","");
	$("#ChnAmt2").css("display","");
	nui.get('table1').refreshTable();		
    function reset(){
    	
		form.reset();
	}
	
	
	function selectCurrency(){
		 var currency=nui.get("map/currencyType").getValue();
	    if(currency=="03"){
	    	$("#UsdLoanAmt").css("display","");
			$("#UsdLoanAmt2").css("display","");
			$("#ChnLoanAmt").css("display","none");
			$("#ChnLoanAmt2").css("display","none");
			$("#UsdAmt").css("display","");
			$("#UsdAmt2").css("display","");
			$("#ChnAmt").css("display","none");
			$("#ChnAmt2").css("display","none");
	    }else{
	    	$("#UsdLoanAmt").css("display","none");
			$("#UsdLoanAmt2").css("display","none");
			$("#ChnLoanAmt").css("display","");
			$("#ChnLoanAmt2").css("display","");
			$("#UsdAmt").css("display","none");
			$("#UsdAmt2").css("display","none");
			$("#ChnAmt").css("display","");
			$("#ChnAmt2").css("display","");
	    }
	    nui.get('table1').refreshTable();
	}
	function search() {
	git.mask("tabs1");
		 var currency=nui.get("map/currencyType").getValue();
	    if(currency=="03"){
       		grid.showColumn(usdAmt);
			grid.showColumn(usdLoanAmt);
	 		grid.hideColumn(chnAmt);
			grid.hideColumn(chnLoanAmt);
			
    	}else{
    		grid.hideColumn(usdAmt);
			grid.hideColumn(usdLoanAmt);
	 		grid.showColumn(chnAmt);
			grid.showColumn(chnLoanAmt);
			
    	}
    	
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
        onDrawSummaryCell(grid)	;
    }
	 grid.on("preload",function(e){//客户信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
   				+ e.data[i].partyId
   				+ '\');return false;" value="'
   				+ e.data[i].partyId
   				+ '">'+e.data[i]['partyName'] +'</a>';
   		}
   });
		function clickCust(partyId){//客户信息链接
	     var json = nui.encode({"partyId":partyId});
        $.ajax({
            url: "com.bos.pub.uitl.viewCust.biz.ext",
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
        				state:"max",
        				allowResize:true,
        				showMaxButton: true,
           				ondestroy: function (action) {
                		
            			}
        			});
                }
            }
        });
	}
	
	
    grid.on("preload",function(e){// 合同信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   		if(e.data[i]['contractNum']!='--'){
   			e.data[i]['contractNum']='<a href="#" onclick="clickContract(\''
   				+ e.data[i].contractId
   				+ '\');return false;" value="'
   				+ e.data[i].contractId
   				+ '">'+e.data[i]['contractNum']+'</a>';
   				}
   		}
   });
	  function clickContract(contractId){// 合同信息链接
	       var contractId = contractId;
		nui.open({
			url:nui.context + "/crt/view/contract_main.jsp?contractId="+contractId
														  +"&wflow=1",
			title: "合同信息",
	        width: 1000,
			height: 500,
			state:"max",
	        ondestroy: function (action) {            
	                
	        }
		});
	}
	
	    grid.on("preload",function(e){// 借据信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			if(e.data[i]['loanNum']!='--'){
   			e.data[i]['businessNum']='<a href="#" onclick="clickLoan(\''
   				+ e.data[i].loanNum
   				+ '\');return false;" value="'
   				+ e.data[i].businessNum
   				+ '">'+e.data[i]['businessNum']+'</a>';}
   		}
   });
	  function clickLoan(loanNum){// 借据信息链接
	  var loanNum = loanNum;
  		var row = grid.getSelected();
		var flag="1";
		nui.open({
			url:nui.context + "/pay/pay_duebill_tittle.jsp?loanNum="+loanNum
														  +"&flag=" + flag  +"&loanDetailId="+row.loandetailid,
			title: "借据信息",
	        width: 1000,
			height: 500,
			state:"max",
	        ondestroy: function (action) {            
	                
	        }
		});
	}
	
	    grid.on("preload",function(e){// 担保合同信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['subcontractNum']='<a href="#" onclick="clickSub(\''
   				+ e.data[i].subcontractId
   				+ '\');return false;" value="'
   				+ e.data[i].subcontractId
   				+ '">'+e.data[i]['subcontractNum']+'</a>';
   		}
   });
	  function clickSub(subcontractId){// 担保合同信息链接
	    if(subcontractId=="undefined"){
	   alert("暂无担保合同信息");
	   return;
	  }
	      nui.open({
                    url: nui.context+"/pub/LedgerMsg/ledgerSub.jsp?subcontractId="
	        + subcontractId+"&view=1",
                    title: "编辑", 
                    width: 800,
            		height: 600,
                    allowResize:true,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
                   	 	}
                    }
                });
	}
	
	 grid.on("preload",function(e){//批复信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['bizNum']='<a href="#" onclick="getApprove(\''
   				+ e.data[i].applyId
   				+ '\');return false;" value="'
   				+ e.data[i].bizNum
   				+ '">'+e.data[i]['bizNum'] +'</a>';
   		}
   });
//批复信息
function getApprove(e){
	nui.open({
            url: nui.context + '/com.bos.biz.checkForNoticeApprove.flow?applyId='+e,
            title: "批复信息",
            width: 800,
            height: 600,
            state:"max",
            ondestroy: function (action) {            
                
        	}
       	 }); 
}
	
	
	
  //选择机构
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
                    btnEdit.setValue(data.orgid);
                    btnEdit.setText(data.orgname);
                }
            }
        }
    });            
}
  
//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.businessNum != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=224";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
    
        //币种
function selectCurrcyCd(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择字典项",
            width: 300,
            height: 500,
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
	//担保方式
	function selectSuretyMode(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_SXCD1020",
            title: "选择字典项",
            width: 300,
            height: 500,
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
	function selectLoanDirection(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_CDKH2011",
            title: "选择字典项",
            width: 300,
            height: 500,
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
	
	 function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            var rows = e.data;
            var total = 0;
            var time="";
            var currency=nui.get("map/currencyType").getValue();
    			if(currency=="03"){
    				 if (e.field == "bizNum") {
            	 			e.cellHtml = "借据余额总计(万美元）: ";
            			}
            		if (e.field == "contractNum") {
            				if(rows.length>0){
            					var row=rows[0];
            					total=row.usdAmtSum;
            				}
            	 			e.cellHtml =  total;
            			}
            		if (e.field == "loanActualPaymentDate") {
            	 			e.cellHtml = "采集日期: ";
            			}
            		if (e.field == "loanActualMaturity") {
            				if(rows.length>0){
            					var row=rows[0];
            					time=row.myTime;
            				}
            	 			e.cellHtml =  time;
            			}
    			}else{
    				if (e.field == "bizNum") {
            				
            	 			e.cellHtml = "借据余额总计(万元）:";
            			}
           			 if (e.field == "contractNum") {
            				if(rows.length>0){
            					var row=rows[0];
            					total=row.chnAmtSum;
            				}
            	 			e.cellHtml = total;
            			}
            		if (e.field == "loanActualPaymentDate") {
            	 			e.cellHtml = "采集日期: ";
            			}
            		if (e.field == "loanActualMaturity") {
            				if(rows.length>0){
            					var row=rows[0];
            					time=row.myTime;
            				}
            	 			e.cellHtml =  time;
            			}
				}
               
            
        }
        
        
        //产品树
	function selectProduct(e) {
        var btnEdit = this;
         var productTypeCd=nui.get("map/productTypeCd").getValue();
        nui.open({
            url: nui.context + "/pub/LedgerMsg/LedStatistical/select_product_tree.jsp?productTypeCd="+productTypeCd,
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
	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择经办机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
	</script>
</body>
</html>

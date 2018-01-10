<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>合同信息查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="合同信息查询" >
<center>
<form id="form1" class="nui-form"action="" method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		
		<label>借款人：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		
		<label>合同号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input"  />
		
		<label>借据号：</label>
		<input name="map/loanNum" required="false" class="nui-textbox nui-form-input"  />

		<div>
		<label>合同金额：</label>
		</div>
		<div>
	
		<input name="map/contractTotalAmt1" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>-
		<input name="map/contractTotalAmt2" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>
		</div>
		<div>
		<label>合同已用金额：</label>
		</div>
		<div>
		<input name="map/availableAmt1" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>-
		<input name="map/availableAmt2" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>
		</div>
		<div>
		<label>借据金额：</label>
		</div>
		<div>
		<input name="map/loanAmt1" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>-
		<input name="map/loanAmt2" required="false" class="nui-textbox nui-form-input"  style="width: 38%;" vtype="float" minValue="0"  decimalPlaces="2" maxValue="99999999999999999999999999"/>
		</div>

		<div>
		<label>贷款实际发放日期：</label>
		</div>
		<div>
		<input name="map/loanActualPaymentDate1" id="laidstart" required="false" value="" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd" onvaluechanged="onregDueDate"/>~
		<input name="map/loanActualPaymentDate2" id="laidend" required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd" onvaluechanged="onDueDate"/>
		</div>		

		<label>贷款实际到期日期：</label>
		<div>
		<input name="map/loanActualMaturity1" id="removestart" required="false" value="" class="nui-datepicker nui-form-input"  onvaluechanged="onregDueDate1"/>~
		<input name="map/loanActualMaturity2" id="removeend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate1"/>
		</div>	
		
		<label>合同状态：</label>
		<input name="map/statuscd" required="false" class="nui-dictcombobox nui-form-input"  allowInput="false" dictTypeId="XD_SXCD1106"/>
		
		<label>经办人：</label>
		<input name="map/userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/>
	
		<label>经办机构：</label>
		<input name="map/orgid" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
	</div>
	
	
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:49px" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="downloadExcel" type="submit" />导出信息</a> --%>
	</div>
 </form>
	

	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.entrustedloan.GetContractAccountList.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>	
			<div field="partyName" headerAlign="center" allowSort="true" >借款人</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同号</div>
			<div field="loanNum" headerAlign="center" allowSort="true" >借据号</div>
			<div field="contractTotalAmt" headerAlign="center" allowSort="true"  dataType="currency">合同金额</div>
			<div field="availableAmt" headerAlign="center" allowSort="true"  dataType="currency">合同已用金额</div>
			<div field="concurrencycd" headerAlign="center" allowSort="true" dictTypeId="CD000001">合同币种</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">借据金额</div>
			<div field="summcurrencycd" headerAlign="center" allowSort="true" dictTypeId="CD000001">借据币种</div>
		    <div field="loanActualPaymentDate" headerAlign="center" allowSort="true" >实际发放日期</div>
			<div field="loanActualMaturity" headerAlign="center" allowSort="true" >实际到期日期</div>
			<div field="contractStatus" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1106">合同状态</div>			
			<div field="orgname" headerAlign="center" allowSort="true">经办机构</div>
			<div field="empname" headerAlign="center" allowSort="true" >经办人</div>
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
    	git.mask();
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask();
        });
    }
    search();
    
    function reset(){
		form.reset();
		//search();
	}
		//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.contractNum != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");//accountManager.flow
				//com.primeton.example.excel.empManager.flow
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=232";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
		grid.on("preload",function(e){//客户信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['partyName']='<a href="#" onclick="getCustmer(\''
   				+ e.data[i].partyId
   				+ '\');return false;" value="'
   				+ e.data[i].partyId
   				+ '">'+e.data[i]['partyName'] +'</a>';
   		}
   });
	   //客户信息
	function getCustmer(partyId){
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
	
	grid.on("preload",function(e){// 借据信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			if(e.data[i]['loanNum']!='--'){
   			e.data[i]['loanNum']='<a href="#" onclick="getSummary(\''
   				+ e.data[i].loanNum
   				+ '\');return false;" value="'
   				+ e.data[i].loanSummaryId
   				+ '">'+e.data[i]['loanNum']+'</a>';}
   		}
   });
	function getSummary(loanNum){
		var loanNum = loanNum;
		var flag="1";
		var row = grid.getSelected();
		nui.open({
			url:nui.context + "/pay/pay_duebill_tittle.jsp?loanNum="+loanNum
														  +"&flag=" + flag +"&loanDetailId="+row.loanDetailId,
			title: "借据信息",
	        width: 1000,
			height: 500,
			state:"max",
	        ondestroy: function (action) {            
	                
	        }
		});
	}
	grid.on("preload",function(e){// 合同信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			if(e.data[i]['contractNum']!='--'){
   			e.data[i]['contractNum']='<a href="#" onclick="getContract(\''
   				+ e.data[i].contractId
   				+ '\');return false;" value="'
   				+ e.data[i].contractId
   				+ '">'+e.data[i]['contractNum']+'</a>';}
   		}
   });
	function getContract(contractId){
		var contractId = contractId;
		nui.open({
		    url:nui.context + "/crt/view/contract_main.jsp?contractId="+contractId,
			title: "合同信息",
	        width: 1000,
			height: 500,
			state:"max",
	        ondestroy: function (action) {            
	                
	        }
		});
	}
	
	  	function selectCodeList(e) {
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
    
       
//选择人员（无权限）
function selectEmp(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/pub/standingBook/select_employee.jsp",
        showMaxButton: true,
        title: "选择经办人",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
}

	
	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	// 日期判断
	function onregDueDate1(){
		var removestart = nui.get("removestart").getValue();//起始日期
		var removeend = nui.get("removeend").getValue();//截止日期
			if(removeend==""){//截止日期为空
			nui.get("removeend").setValue(removestart);
			}
		if(removestart!=""&&removeend!=""){
			if(nui.parseDate(removestart)-nui.parseDate(removeend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removestart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate1(){
		var removestart = nui.get("removestart").getValue();//起始日期
		var removeend = nui.get("removeend").getValue();//截止日期
	
		if(removestart!=""&&removeend!=""){
			if(nui.parseDate(removestart)-nui.parseDate(removeend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removeend").setValue(removestart);
				return false;
			}
		}else{
	
			return true;
		}
	}
////////////////////日期判断结束
	
	</script>
</body>
</html>
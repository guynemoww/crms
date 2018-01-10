<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>合同使用情况查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="合同使用情况查询" >
<center>
<form id="form1" class="nui-form"action="" method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		
		<label>客户编号：</label>
		<input name="map/partyNum" required="false" class="nui-textbox nui-form-input"  />
		
		<label>客户名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		
		<label>合同编号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input"  />

		<label>所属机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
	
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:49px" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="downloadExcel" type="submit" />导出信息</a> --%>
	</div>
 </form>
	

	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px" 
		url="com.bos.pub.standingbook.conuseinfo.conuseinfo.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同号</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="loanApplyNo" headerAlign="center" allowSort="true" >放款编号</div>
			<div field="loanNum" headerAlign="center" allowSort="true" >借据编号</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">放款金额（元）</div>
			<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">未还金额(元)</div>
			<div field="startDate" headerAlign="center" allowSort="true" >起息日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
		    <div field="orgname" headerAlign="center" allowSort="true" >所属机构</div>
		    <div field="empname" headerAlign="center" allowSort="true" >创建人</div>	
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	search();
	//初始化页面
    function search() {
    	git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
    //search();
    
    function reset(){
		form.reset();
		//search();
	}
		//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.partyNum != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");//accountManager.flow
				//com.primeton.example.excel.empManager.flow
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=222";
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
   			if(e.data[i]['loanNum']!='--')
   			{
   			e.data[i]['loanNum']='<a href="#" onclick="getSummary(\''
   				+ e.data[i].loanNum
   				+ '\');return false;" value="'
   				+ e.data[i].loanSummaryId
   				+ '">'+e.data[i]['loanNum']+'</a>';}
   		}
   });
	function getSummary(loanNum){
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
   				+ e.data[i].loanSummaryId
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
	</script>
</body>
</html>
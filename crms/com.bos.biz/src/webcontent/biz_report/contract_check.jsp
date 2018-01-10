<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>

<head>
<title>合同审查基本信息</title>
</head> 
<body>
	<div  id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input  class="nui-hidden nui-form-input" name="contractCheckInfo"/>
		<input class="nui-hidden nui-form-input" name="contractCheckInfo.contractDetailId" id="conDetailId"/>
		<input class="nui-hidden nui-form-input" name="contractCheckInfo.contractId" id="contractId"/>
		<input class="nui-hidden nui-form-input" name="contractCheckInfo.businessType" id="productCd"/>
		<div class="nui-dynpanel" columns="4">
				<label class="nui-form-label">客户编号：</label>
				<input  class="nui-text nui-form-input" name="contractCheckInfo.partyNum"style="width: 54%;" / >
				
				<label class="nui-form-label">客户名称：</label>
				<input  class="nui-text nui-form-input" name="contractCheckInfo.partyName"  style="width: 54%;" />
			
				<label class="nui-form-label">合同编号：</label>
				<a href="#" onclick="getContract1()" id="contractNum"><span ></span></a>
				
				<label class="nui-form-label">业务编号：</label>
				<input  name="contractCheckInfo.bizNum" class="nui-text nui-form-input" style="width: 54%;" />
				
				<label class="nui-form-label">纸质合同编号：</label>
				<input  name="contractCheckInfo.contractManualNum"  class="nui-text nui-form-input"   style="width: 54%;"  />
				<label class="nui-form-label">合同币种：</label>
				<input name="contractCheckInfo.currencyCd" class="nui-text nui-form-input" style="width: 54%;" dictTypeId="CD000001"/>
					
				<label class="nui-form-label">合同金额：</label>
				<input class="nui-text nui-form-input" name="contractCheckInfo.contractTotalAmt"  style="width: 54%;" />
				
				<label for="isteam$text">期限：</label>
				<input class="nui-text nui-form-input" name="contractCheckInfo.contractTerm" required="true" style="width: 54%;" />
				
				<label for="isteam$text">起始日期：</label>
				<input  class="nui-text nui-form-input" name="contractCheckInfo.startDate" required="true" style="width: 54%;"  />
				
				<label for="isteam$text">到期期限：</label>
				<input  class="nui-text nui-form-input" name="contractCheckInfo.expirationDate" required="true" style="width: 54%;"  />
				
				<label for="isteam$text"><a href="#" onclick="clickDownload()">合同明细下载</a></label>
		</div>
	</div>

	<iframe name="x" id="x" style="display:none;"></iframe>
	<div  style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="clickDownload()">下载</a>
		<a class="nui-button" iconCls="icon-search" onclick="viewContract()">预览</a>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" onselectionchanged="onSelectionChanged"
	 url="com.bos.crt.queryContract.queryContractList.biz.ext" dataField="cons" allowResize="true"  
	 multiSelect="false" pageSize="5" sortMode="client">	
		<div property="columns">
		<div type="checkcolumn">选择</div>
		<div type="indexcolumn">序号</div>
		<div field="contractNum" headerAlign="center"   >合同编号</div>
		<div field="partyName" headerAlign="center"  >客户名称</div>
	    <div field="bizType"  align="center" dictTypeId="XD_SXCD1038">合同性质</div>
	    <div field="bizHappenType"  align="center"  dictTypeId="XD_SXCD1039">合同发生性质</div> 
		<div field="productType" headerAlign="center" dictTypeId="product"  renderer="productType">授信品种</div>
		<div field="contractStatusCd" dictTypeId="XD_SXCD1106"  >合同状态</div>
		<div field="contractStatusCd" dictTypeId="XD_SXCD1106"  renderer="contractStatusCd">操作</div>
		<div field="startDate" headerAlign="center"  >起始日期</div>
		<div field="expirationDate" headerAlign="center"  >到期日期</div>
		<div field="currencyCd" dictTypeId="CD000001" >币种</div>
		<div field="contractTotalAmt" headerAlign="center"  dataType="currency" >合同金额</div>
		<div field="occupiedAmt" headerAlign="center"  dataType="currency" >已发放金额</div>
		<div field="availableAmt" headerAlign="center"  dataType="currency" >合同已用金额</div>
	</div>
</div>
<script type="text/javascript">
     nui.parse();
	 var form = new nui.Form("#form1");
	 git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	 var grid = nui.get("grid1");
	 function query(){/* 查询合同信息 */
	  	var contractId='<%=request.getParameter("bizId")%>';
	  	//var contractId="10002030";
		var json = nui.encode({"contractCheckInfo/contractId":contractId});
        $.ajax({
            url: "com.bos.crt.queryContract.checkQueryCuntractInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                form.setData(o);
                $("#contractNum").html(o.contractCheckInfo.contractNum);
                form.setIsValid(true);
                search(contractId);
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText); 
                git.unmask();
            }
        });
	 }
	 //query();
		//初始化页面
    function search(contractId) {
		var data = {"con/contractStatusCd":"CS220"};
        grid.load(data);
    }
    //查看合同信息
	function getContract1(){
		var contractId = nui.get("contractId").getValue();
		var conDetailId = nui.get("conDetailId").getValue();
		var productCd = nui.get("productCd").getValue();
		nui.open({
			url:nui.context +"/crt/view/contract_main.jsp?contractId="+contractId+"&conDetailId="+conDetailId+"&productCd="+productCd,
			title: "合同信息", width: 1000, height: 600,
            onload: function () {
            },
            ondestroy: function (action) {
                  grid.reload();
            }

		});
	}
	//法人透支提交T24
	function getContractT24(){
		var contractId = nui.get("contractId").getValue();
		var json = nui.encode({"conId":contractId});//com.bos.crt.tdpacctcapaaa.tdpacctsend.acctSendToT24
		$.ajax({
	        url: "com.bos.crt.tdpacctcapaaa.tdpacctsend.acctSendToT24.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	       		nui.alert(text.msg);
	        	git.unmask();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	            git.unmask();
	        }
		});
	}
	function contractStatusCd(e){
		if(e.row.contractStatusCd == "CS220")
			return '<a href="#" onclick="getContract(\''+e.row.contractId+'\,'+e.row.platformPartyId+'\,'+e.row.contractNatureCd+'\')">合同生效</a>';
			
		return "——";		
	}
	function productType(e){
		if(e.row.productType == "10100401" && e.row.contractStatusCd == "CS300")
			return '<a href="#" onclick="getContractT24(\''+e.row.contractId+'\,'+e.row.platformPartyId+'\,'+e.row.contractNatureCd+'\')">发送T24</a>';
			
		return git.getProductName(e.row.productType);
	}
	// 合同生效
	function getContract(e){
		var str = e.split(",");
		var json = nui.encode({"params":str[0]});//com.bos.comm.crd.CrdMaintain.platformCalculates	
		$.ajax({
            url: "com.bos.crt.contractDetail.submitConAdvUpd.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (text) {
				grid.reload();
				if(str[2] == "02"){
						var json1 = nui.encode({"partyId":str[1]});
						$.ajax({
				            url: "com.bos.comm.crd.CrdMaintain.platformCalculates.biz.ext",
				            type: 'POST',
				            data: json1,
				            contentType:'text/json',
				            cache: false,
				            success: function (text) {
								git.unmask();
				            },
				            error: function (jqXHR, textStatus, errorThrown) {
				                alert("异常");
				                git.unmask();
				            }
				        });
				  }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
        });	
	}	
	//合同模板下载
	function clickDownload(){
		document.getElementById('x').src="com.bos.crt.downloanContractMain.downloanContractMain.biz.ext2";
		return;
	}
	
	function viewContract(){
		var contractId = nui.get("contractId").getValue();
		var conDetailId = nui.get("conDetailId").getValue();
		var productCd = nui.get("productCd").getValue();
		//window.open(nui.context +"/documnetView.jsp");
		var json = nui.encode({"contractCheckInfo/contractId":contractId});
		$.ajax({
	            url: "com.bos.crt.downloanContractMain.downloanContractMain.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	//alert("filePath:"+mydata.swfPath);
	                if(mydata.swfPath){
	                	debugger;
	            		nui.open({
						url:nui.context +"/crt/view/contract_view.jsp?filePath="+mydata.swfPath,
						//url:nui.context +"/documnetView.jsp",
						title: "合同信息预览", width: 1000, height: 600,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
	            	}
	            	//nui.get("url").setValue(mydata.map);
	            	//git.unmask();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
       	});	
	}
</script>  
</body>
</html>
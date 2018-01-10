<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-20 15:29:50
  - Description:
-->
<head>
<title>生效合同信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath %>/biz/biz_js/biz.js"></script>
</head>
<body>

	<div  id="form" class="nui-form" style="width:auto;height:auto;overflow:hidden;margin: 6px 6px;" >
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName"  class="nui-textbox nui-form-input"  />
	
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="item.certNum" name="item.certNum"  class="nui-textbox nui-form-input"/>
			
			<label>合同编号：</label>
			<input id="item.contractNum" name="item.contractNum" class="nui-textbox nui-form-input" vtype="maxLength:20" />
			
			<label>合同状态：</label>
			<input id="item.status" name="item.status"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8003"  allowInput="false" />
			
			<label>业务品种：</label>
			<input id="item.productType" name="item.productType" class="nui-buttonEdit nui-form-input"
			allowInput="false" onbuttonclick="selectProduct" emptyText="--请选择--" dictTypeId="product"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" allowAlternating="true"
		 url="com.bos.conPub.conPub.getConApvList.biz.ext" dataField="conInfos" allowResize="true"  
		 multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div field="contractNum" headerAlign="center"align="center"  width="150" >合同编号</div>
			<div field="bizTypeFlag" headerAlign="center"align="center" dictTypeId="XD_SXYW0002"   >业务性质</div>
			<div field="partyName" headerAlign="center" align="center" >客户名称</div>
			<div field="certType" headerAlign="center"  align="center" dictTypeId="CDKH0002" >证件类型</div>
			<div field="certNum" headerAlign="center"  align="center" >证件号码</div>
			<div field="productType" headerAlign="center" dictTypeId="product" align="center" >业务品种</div>
			<div field="beginDate" headerAlign="center" align="center" >合同起期</div>
			<div field="endDate" headerAlign="center" align="center" >合同止期</div>
			<div field="currencyCd" dictTypeId="CD000001" headerAlign="center"width="50" align="center" >币种</div>
			<div field="contractAmt" headerAlign="center" dataType="currency" align="right" >合同金额</div>
			<div field="conYuE" headerAlign="center" dataType="currency" align="right" >合同已用金额</div>
		</div>
	</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form"); 
	var grid = nui.get("grid");
// 	var status =[
// 	{"id":"03","text":"已生效"},
// 	{"id":"05","text":"到期失效"}
// 	];	
	//向空间设置初始值
  	nui.get("item.status").setValue("03");
  	//向空间设置业务字典
    nui.get("item.status").setData(getDictData("XD_SXCD8003","str","03,05"));
    nui.get("item.status").setEnabled(true);
    function reset(){
    	form.reset();
    	//重置时默认合同状态为已生效(避免合同状态条件为空查询出所有的数据)
    	nui.get("item.status").setValue("03");
    }
    
   function query(){
   	   var data = form.getData();
       grid.load(data);
	}
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['contractNum']='<a href="#" onclick="goToLoan();return false;" value="'
   				+ e.data[i].contractDetailId
   				+ '">'+e.data[i]['contractNum']+'</a>';
   		}
  	 });
	query();
	//创建放款支付申请
	function goToLoan(e){
		var row=grid.getSelected();
		nui.open({
            url:nui.context +"/pay/payout_apply/pay_tab.jsp?contractId="+row.contractId+"&partyId="+row.partyId,
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            },
            ondestroy:function(e){
            	query();
            }
  	 	 });	
	}
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
</script>
</body>
</html>
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

<fieldset style="width:auto;height:auto;overflow:hidden;margin: 6px 6px;" >
	<legend>
		<span>内部银团合同</span>
	</legend>
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
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.conPub.conPub.getConApvList2.biz.ext" dataField="conInfos" allowResize="true"  
		 multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div field="contractNum" headerAlign="center"align="center"   >合同编号</div>
			<div field="partyName" headerAlign="center" align="center" >客户名称</div>
			<div field="certType" headerAlign="center"  align="center" dictTypeId="CDKH0002" >证件类型</div>
			<div field="certNum" headerAlign="center"  align="center" >证件号码</div>
			<div field="productType" headerAlign="center" dictTypeId="product" align="center" >业务品种</div>
			<div field="beginDate" headerAlign="center" align="center" >合同起期</div>
			<div field="endDate" headerAlign="center" align="center" >合同止期</div>
			<div field="currencyCd" dictTypeId="CD000001" headerAlign="center"width="50" align="center" >币种</div>
			<div field="contractAmt" headerAlign="center" dataType="currency" align="right"  >承诺金额</div>
			<div field="jjye" headerAlign="center" dataType="currency" align="right"  >已出账金额</div>
			<%--<div field="conBalance" headerAlign="center" dataType="currency" align="right"  >合同已用金额</div>--%>
		</div>
	</div>
</fieldset>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form"); 
	var grid = nui.get("grid");
	
	nui.get("item.status").setValue("03");
    nui.get("item.status").setData(getDictData("XD_SXCD8003","str","03,05"));
    
    function reset(){
    	form.reset();
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
            }
  	 	 });	
	}
</script>
</body>
</html>
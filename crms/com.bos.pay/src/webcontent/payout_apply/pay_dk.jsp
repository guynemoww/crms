<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 19:20:27
  - Description:
-->
<head>
<title>垫款处理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>合同编号：</label>
		<input name="contractNum" id="contractNum" class="nui-textbox nui-form-input"/>
		<label>借据编号：</label>
		<input name="summaryNum" id="summaryNum" class="nui-textbox nui-form-input"/>
		<label>交易日期：</label>
		<div colspan="1" >
			<input name="tradeDate1" id="tradeDate1" class="nui-datepicker nui-form-input" style="width:100px;" format="yyyyMMdd"/>
			至
			<input name="tradeDate2" id="tradeDate2" class="nui-datepicker nui-form-input" style="width:100px;" format="yyyyMMdd"/>
		</div>
		<label>处理状态：</label>
		<input id="dealFlag" name="dealFlag" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_DKCL0001" />
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" onclick="reset()">重置</a>
	</div>
</div>
    <!--<div><h4>垫款出账流水</h4></div>-->
    <!-- <div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
	    <a class="nui-button" id="" onclick="dkcz">手工处理失败流水</a>
	</div> -->
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.payInfo.PayInfo.getDkList.biz.ext" dataField="dks"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	        <!--<div type="checkcolumn" >选择</div>
	        <div type="indexcolumn">序号</div>   
	        <div field="TRADEDATE" allowSort="true"  headerAlign="center">交易日期</div>
	        <div field="BILLNUM" allowSort="true"  headerAlign="center">票据编号</div>
	        <div field="ADVAMT" allowSort="true"  headerAlign="center" dataType="currency">垫款金额</div>
	        <div field="CONTRACTNUM" allowSort="true"  headerAlign="center" >合同编号</div>
			<div field="ADVACCNUM" allowSort="true"  headerAlign="center" >垫款账号</div>-->
			<div field="TRADEDATE" allowSort="true"  headerAlign="center">交易日期</div>
	        <div field="TRADEORG" allowSort="true"  headerAlign="center" dictTypeId="org">交易机构</div>
	        <div field="CONTRACT_NUM" allowSort="true"  headerAlign="center">合同编号</div>
	        <div field="SUMMARY_NUM" allowSort="true"  headerAlign="center">借据编号</div>
	        <!--<div field="ACCAGRNUM" allowSort="true"  headerAlign="center" >协议编号</div>-->
			<!--<div field="YWBH" allowSort="true"  headerAlign="center" >业务编号</div>-->
			<div field="ADVTYPE" allowSort="true"  headerAlign="center" >发生类型</div>
			<div field="ADVACCNUM" allowSort="true"  headerAlign="center" >垫款账号</div>
			<div field="ADVACCNAME" allowSort="true"  headerAlign="center" >垫款户名</div>
			<!-- <div field="ADVORG" allowSort="true"  headerAlign="center" dictTypeId="org"> 垫款机构</div> -->
			<div field="ADVAMT" allowSort="true"  headerAlign="center" dataType="currency">垫款金额</div>
			<div field="CURRENCY" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">垫款币种</div>
			<div field="DEALFLAG" allowSort="true"  headerAlign="center"  dictTypeId="XD_DKCL0001">处理状态</div>
			<div field="MSG" allowSort="true"  headerAlign="center" >处理结果</div>
			<div field="PAYACCNUM" allowSort="true"  headerAlign="center" >还款账号</div>
			<div field="PAYACCNAME" allowSort="true"  headerAlign="center" >还款户名</div>
			<!--<div field="PAYACCTYPE" allowSort="true"  headerAlign="center" >还款账户类型</div>-->
			<!--<div field="PAYORG" allowSort="true"  headerAlign="center" dictTypeId="org">还款机构</div>-->
	     </div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	initPage();
	//var arr = git.getDictDataFilter("XD_RZCD0001", "1,2");
	//nui.get("dealFlag").setData(arr);
	function initPage(){
		var bizDate="<%=GitUtil.getBusiDateStr()%>";
		nui.get("tradeDate1").setValue(bizDate);
		nui.get("tradeDate2").setValue(bizDate);
		var dealFlag = nui.get("dealFlag").getValue();
		var summaryNum = nui.get("summaryNum").getValue();
		var contractNum = nui.get("contractNum").getValue();
		var dateBegin = nui.get("tradeDate1").getValue();
		var dateEnd = nui.get("tradeDate2").getValue();
		grid.load({"dealFlag":dealFlag,"summaryNum":summaryNum,"dateBegin":dateBegin,"dateEnd":dateEnd,"contractNum":contractNum});
	}
	function search(){
		var dealFlag = nui.get("dealFlag").getValue();
		var summaryNum = nui.get("summaryNum").getValue();
		var contractNum = nui.get("contractNum").getValue();
		var dateBegin = nui.get("tradeDate1").getValue();
		var dateEnd = nui.get("tradeDate2").getValue();
		grid.load({"dealFlag":dealFlag,"summaryNum":summaryNum,"dateBegin":dateBegin,"dateEnd":dateEnd,"contractNum":contractNum});
	}
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			if(null==e.data[i]['CONTRACT_NUM']||e.data[i]['CONTRACT_NUM']==""){
   			}else{
   				e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan();return false;" value="'
   					+ e.data[i].contract_id
   					+ '">'+e.data[i]['CONTRACT_NUM']+'</a>';
   			}
   		}
  	 });
	function goToLoan(e){
		var row=grid.getSelected();
		nui.open({
            url:nui.context +"/pay/payout_apply/pay_tab.jsp?contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID,
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
	function dkcz(){
	        //日期不等于营业日期不能垫款
			//var bizDate="<%=GitUtil.getBusiDateYYYYMMDD()%>";
			//if(bizDate!=row.TRADEDATE){
				//nui.alert("交易日期与营业日期不符，不能手工处理垫款！");
				//return false;
			//}
	        nui.confirm("确定手工处理垫款吗？","确认",function(action){
	            if(action!="ok") return;
	            git.mask();
	            $.ajax({
	            	url: "com.bos.payInfo.PayInfo.sgdkNew.biz.ext",
		            type: 'POST',
		            data: '',
		            cache: false,
		            contentType:'text/json',
	                success: function (text) {
	                 if (text.msg) {
	                    nui.alert(text.msg);
	        			initPage();
	                  }
	                  git.unmask();
	                },
	                error: function () {
	                   nui.alert("操作失败！");
	                }
	            });
	            git.unmask();
	        }); 
	}
</script>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<center>
<div id="form1" style="width:90%;height:auto;overflow:hidden; text-align:left">

	<fieldset>
  	<legend>
    	<span>基本客户信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    	<label>客户编号：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("partyNum")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>
    	
   		<label>客户名称：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("partyName")%>" value="<%=request.getParameter("")%>" class="nui-text nui-form-input" vtype="maxLength:30"/>

    	<label>组织机构代码：</label>
		<input id="orgCertificate" value="<%=request.getParameter("orgCertificate")%>"  readonly="true"  class="nui-text nui-form-input" vtype="maxLength:30"/>
    	
   		<label>工商营业执照：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("personCertificate")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>

    	<label>客户类型：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("partyTypeCd")%>"  class="nui-text nui-form-input" dictTypeId="XD_PJCD0015" vtype="maxLength:30"/>
    	
   		<label>省市区：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("companyaddress")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>

    	<label>登记机构：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("registerOrg")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>
    	
   		<label>贷款卡编码：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("loanCardNum")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>

    	<label>注册币种：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("registerAssetsCurrencyCd")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>
    	
   		<label>注册资本：</label>
		<input id=""  readonly="true" value="<%=request.getParameter("registerAssets")%>"  class="nui-text nui-form-input" vtype="maxLength:30"/>
		
   		</div>
    </fieldset>
    
    <fieldset>
  	<legend>
    	<span>资本构成信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    		
    		<label>出资方名称：</label>
			<input id="" readonly="true" value="<%=request.getParameter("naturalPartyName")%>" class="nui-text nui-form-input"  vtype="maxLength:30"/>
<!--
    		<label>贷款卡编码：</label>
			<input id=""  readonly="true"   class="nui-text nui-form-input"  vtype="maxLength:30"/>
-->	
    		<label>组织机构代码：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("natureCertificate")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>币种：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("registerAssetCurrencyCd")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>出资金额：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("investmentAmt1")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
   		<span>对外投资信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
	   		
	   		<label>被投资单位名称：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("investmentCustomerName")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
<!-- 
    		<label>贷款卡编码：</label>
			<input id=""  readonly="true"   class="nui-text nui-form-input"  vtype="maxLength:30"/>
 -->
    		<label>组织机构代码：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("outsideCertificate")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>币种：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("registerAsseCurrencyCd")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>出资金额：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("investmentAmt")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
    	
    	</div>
    </fieldset>
    
     <fieldset>
  	<legend>
   		<span>提示信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
<!-- 	   		
	   		<label>是否在其他支行有未结清业务：</label>
			<input id=""  readonly="true"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
 -->
    		<label>是否存在欠息：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("ifinterest")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>是否存在不良业务：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("ifnotgood")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>

    		<label>是否存在垫款：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("advanceflag")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
<!-- 
    		<label>客户所在关联集团成员是否在其他分支机构有未结清业务：</label>
			<input id=""  readonly="true"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
 -->
    		<label>是否黑名单：</label>
			<input id=""  readonly="true" value="<%=request.getParameter("ifblack")%>"  class="nui-text nui-form-input"  vtype="maxLength:30"/>
<!--
    		<label>客户所在关联集团成员是否在其他分支机构有未结清业务：</label>
			<input id=""  readonly="true"   class="nui-text nui-form-input"  vtype="maxLength:30"/>
 -->
    	</div>
    </fieldset>

     <fieldset>
	  	<legend>
	   		<span>授信额度信息</span>
	    </legend>
	    	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
					url="com.bos.pub.standingbook.guarantyaccout.GetTbCsmLimitList.biz.ext"
					dataField="tbCsmLimits"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
					<div property="columns" >
							<div type="checkcolumn" >选择</div>
							<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
							<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
							<div field="limitNum" headerAlign="center" allowSort="true" >额度编号</div>
							<div field="bizHappenType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1039">发生方式</div>
							<div field="startDate" headerAlign="center" allowSort="true" >额度起始日</div>
							<div field="endDate" headerAlign="center" allowSort="true" >额度到期日</div>
							<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
							<div field="creditAmt" headerAlign="center" allowSort="true"  dataType="currency">全额额度总金额</div>
							<div field="creditExposure" headerAlign="center" allowSort="true"  dataType="currency">敞口额度总金额</div>
							<!-- 
							<div field="availableExposure" headerAlign="center" allowSort="true"  dataType="currency">敞口额度可用金额</div>
							<div field="occupiedExposure" headerAlign="center" allowSort="true"  dataType="currency">敞口额度占用金额</div>
							<div field="spareInvokeExposure" headerAlign="center" allowSort="true"  dataType="currency">备用敞口启用金额</div>
							<div field="balanceAmt" headerAlign="center" allowSort="true"  dataType="currency">全额额度余额</div>
							<div field="availableAmt" headerAlign="center" allowSort="true"  dataType="currency">全额额度可用金额</div>
							<div field="occupiedAmt" headerAlign="center" allowSort="true"  dataType="currency">全额额度占用金额</div>
							<div field="balanceExposure" headerAlign="center" allowSort="true"  dataType="currency">敞口额度余额</div>
							 -->
							<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">管户机构</div>
					</div>
			</div>
    </fieldset>

    <fieldset>
	  	<legend>
	    <span>未终结合同信息</span>
	    </legend>
	    表内合同信息
	    	<div id="grid2" class="nui-datagrid" style="width:100%;height:auto" 
					url="com.bos.pub.standingbook.guarantyaccout.TbCsmOnContractList.biz.ext"
					dataField="tbCsmOnEntrys"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
					<div property="columns" >
							<div type="checkcolumn" >选择</div>
							<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
							<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
							<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">合同币种</div>
							<div field="contractTotalAmt" headerAlign="center" allowSort="true"  dataType="currency">合同金额</div>
							<div field="availableAmt" headerAlign="center" allowSort="true"  dataType="currency">合同已用金额</div>
							<div field="sumbalance" headerAlign="center" allowSort="true"  dataType="currency">后三类余额</div>
							<div field="sumoverduebalance" headerAlign="center" allowSort="true"  dataType="currency">逾期余额</div>			
							<div field="suminterestbalance" headerAlign="center" allowSort="true"  dataType="currency">欠息余额</div>																			
							<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org">管户机构</div>
					</div>
			</div>
		    表外合同信息
	    	<div id="grid4" class="nui-datagrid" style="width:100%;height:auto" 
					url="com.bos.pub.standingbook.guarantyaccout.TbCsmOffContractList.biz.ext"
					dataField="tbCsmOnEntrys"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
					<div property="columns" >
							<div type="checkcolumn" >选择</div>
							<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
							<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
							<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">合同币种</div>
							<div field="contractTotalAmt" headerAlign="center" allowSort="true"  dataType="currency">合同金额</div>
							<div field="availableAmt" headerAlign="center" allowSort="true"  dataType="currency">合同已用金额</div>
							<div field="" headerAlign="center" allowSort="true" >保证金总额</div>																		
							<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">管户机构</div>
					</div>
			</div>		
		</fieldset>
    	    <form id="form2" action="" method="post" class="nui-hiden" style="height:auto;overflow:hidden;">
			<input name="tbCsmLimit.partyId" value="<%=request.getParameter("partyId") %>" required="false" class="nui-hidden" vtype="maxLength:32" />
			<input name="tbCsmLimit.limitNum" required="false" class="nui-hidden"  vtype="maxLength:32" />
			</form>
    	    <form id="form3" action="" method="post" class="nui-hiden" style="height:auto;overflow:hidden;">
				<input name="tbCsmOnEntry.partyId" value="<%=request.getParameter("partyId") %>" required="false" class="nui-hidden" vtype="maxLength:32" />
			</form>
    	    <form id="form4" action="" method="post" class="nui-hiden" style="height:auto;overflow:hidden;">
				<input name="tbCsmOffEntry.partyId" value="<%=request.getParameter("partyId") %>" required="false" class="nui-hidden" vtype="maxLength:32" />
			</form>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("#form2"); 
    var form2 = new nui.Form("#form3"); 
    var form4 = new nui.Form("#form4"); 
	var grid = nui.get("grid1");
	var grid2 = nui.get("grid2");
	var grid4 = nui.get("grid4");
	    function search() {
		var data = form.getData();
		var data2 = form2.getData();	
		var data4 = form4.getData();	
        grid.load(data);
        grid2.load(data2);
        grid4.load(data4);
    }
    search();
</script>
</body>
</html>
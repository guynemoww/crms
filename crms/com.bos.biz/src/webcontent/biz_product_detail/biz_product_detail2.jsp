<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>业务明细页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<% 
  String pAddress ="/"+(String)request.getAttribute("jspName")+"?stepFlag=biz";
  System.out.println("pAddress=========="+pAddress);
 %>
<center>
 <div id="panel1" class="nui-panel" title="业务申请品种明细"
	style="width:99.5%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amountDetail.amountDetailId" class="nui-hidden nui-form-input" name="amountDetail.amountDetailId"/>
	<input id="amountDetail.oldDetailId" class="nui-hidden nui-form-input" name="amountDetail.oldDetailId"/>
	<input id="productDetail.applyDetailId" class="nui-hidden nui-form-input" name="productDetail.applyDetailId"/>
	<input id="productDetail.wtxmId" class="nui-hidden nui-form-input" name="productDetail.wtxmId"/>
	<input id="tbBizProductInfo.entityName" class="nui-hidden nui-form-input" name="tbBizProductInfo.entityName"/>
	<input id="tbBizProductInfo.productCd" class="nui-hidden nui-form-input" name="tbBizProductInfo.productCd"/>
		<div class="nui-dynpanel" columns="1"  id="base">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4" id="base1"> 
					<label>业务品种：</label>
					<input id="amountDetail.productType" name="amountDetail.productType" dictTypeId="product" class="nui-text nui-form-input" />
					
					<label>币种：</label>
					<input name="amountDetail.currencyCd" id="currencyCd" required="true" data="data" valueField="dictID"  onvaluechanged="bzchange"
					class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" enabled="false" emptyText="--请选择--" />
					
					<label>申请金额：</label>
					<input id="amountDetail.detailAmt" name="amountDetail.detailAmt"  vtype="float;maxLength:20;range:100,100000000000" required="true" class="nui-textbox nui-form-input" dataType="currency"  onblur="calcuMoney" />
					
					<label id="hl">汇率：</label>
					<input id="amountDetail.exchangeRate" name="amountDetail.exchangeRate" required="true" vtype="float;maxLength:10" class="nui-text nui-form-input" />
			
					<label id="zsrmbje">折算人民币金额：</label>
					<input id="amountDetail.rmbAmt" name="amountDetail.rmbAmt" vtype="float;maxLength:20" required="true" class="nui-text nui-form-input" dataType="currency"/>
					
					<!-- <label>申报止期：</label>
					<input id="amountDetail.endDate" name="amountDetail.endDate" class="nui-datepicker nui-form-input"  required="true" />
					 -->
					<label id="hkfs">还款方式：</label>
					<input id="amountDetail.repaymentType" name="amountDetail.repaymentType" required="true" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1162" emptyText="--请选择--" onvaluechanged="conRpTpChg"/>
					
					<label id="schkqc">首次还本期次：</label>
					<input id="amountDetail.firstRepayTerm" name="amountDetail.firstRepayTerm" required="true" data="data" class="nui-textbox nui-form-input" vtype="int;maxLength:4;range:2,10000"/>
					
					<label id="tqhkwyj">提前还款是否收取违约金：</label>
					<input id="amountDetail.prepaymentPenalty" name="amountDetail.prepaymentPenalty" required="true"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--"/>
					
					<label id="hkly">还款来源：</label>
					<input id="amountDetail.payment" name="amountDetail.payment" required="true" data="data" class="nui-textarea nui-form-input" />
					
					<label id="loanuse">贷款用途：</label>
					<input name="amountDetail.loanUse" id="amountDetail.loanUse" required="true" data="data" class="nui-textarea nui-form-input"/>
					
					<label id="edxhbz">额度循环标志：</label>
					<input id="amountDetail.cycleInd" name="amountDetail.cycleInd" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
					
					<label id="htxhbz">合同循环标志：</label>
					<input name="amountDetail.cycleIndCon" id="amountDetail.cycleIndCon" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" onvaluechanged="conCycleChg"/>
				</div>
				<div class="nui-dynpanel" columns="1" id="base2">
				  	<jsp:include page="<%=pAddress %>"/>
				</div>
				<div id="sqrq" class="nui-dynpanel" columns="4">
					<label>申请日期：</label>
					<input name="amountDetail.applyDate" id="amountDetail.applyDate"  class="nui-text nui-form-input"/>
				</div>
			</fieldset>
		</div>
			
		<div class="nui-dynpanel" columns="1" id="table1">
			<fieldset>
				<legend>
					<span>利率信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_public_rate.jsp"%>
			</fieldset>
		</div>
		<div class="nui-dynpanel" columns="1" id="dkll">
			<fieldset>
				<legend>
					<span>垫款利率信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_dkll_rate.jsp"%>
			</fieldset>
		</div>
		<div class="nui-dynpanel" columns="1"  id="project">
			<fieldset>
				<legend>
					<span>项目信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_project.jsp"%>
			</fieldset>
		</div>
		<div class="nui-dynpanel" columns="1" id="wy">
				<%@include file="/biz/biz_product_detail/wyxx/biz_wy.jsp"%>
		</div>
	</div>
 </div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var amountDetailId = "<%=request.getAttribute("amountDetailId") %>";//额度明细ID
	var bizType = "<%=request.getAttribute("bizType") %>";//01-单笔  02-综合授信
	var proFlag = "<%=request.getAttribute("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var productType = "<%=request.getAttribute("productType") %>";
	//只有流贷有合同循环标志
	if(productType=='01001001'||productType=='01001002'||productType=='01001003'
		||productType=='01001005'||productType=='01001006'||productType=='01001040'
		||productType=='01001041'||productType=='01001042'){
	}else{
		$("#htxhbz").css("display","none");
		nui.get("amountDetail.cycleIndCon").hide();
	}
	
	if(bizType=='01'){//单笔额度循环标志不显示
		$("#edxhbz").css("display","none");
		nui.get("amountDetail.cycleInd").hide();
	}else{
		$("#sqrq").css("display","none");
	}
	//汇票贴现不需要贷款用途,却要还款方式
	if(productType=='01006001' || productType=='01006002' 
	|| productType=='01006010' //村镇银行贴现产品
	){
		$("#loanuse").css("display","none");
		$("#tqhkwyj").css("display","none");
		$("#hkly").css("display","none");
		nui.get("amountDetail.loanUse").hide();
		nui.get("amountDetail.prepaymentPenalty").hide();
		nui.get("amountDetail.payment").hide();
	}
	//汇率
	function bzchange(){
		var bz = nui.get("currencyCd").getValue();
		if(bz=='CNY'||bz==''||bz==null){
			$("#hl").css("display","none");
			$("#zsrmbje").css("display","none");
			nui.get("amountDetail.exchangeRate").hide();
			nui.get("amountDetail.rmbAmt").hide();
		}
		nui.get("base1").refreshTable();
	}
	if(productType=='01008001' ||productType=='01008010' ||productType=='01008002'||productType=='01009001'||productType=='01009002' || productType=='01009010' ||productType=='01007008'
		||productType=='01010001'||productType=='01011001'||productType=='01012001'||productType=='01004001'
		||productType=='01007001'||productType=='01007002'||productType=='01007003'||productType=='01007004'
		||productType=='01007005'||productType=='01007006'||productType=='01007007'||productType=='01014001'){
		$("#table1").css("display","none");
	}
	$("#dkll").css("display","none");
	if(productType=='01008001' ||productType=='01008010' ||productType=='01008002'||productType=='01009001'||productType=='01009002' ||productType=='01009010'||productType=='01007008'
		||productType=='01010001'||productType=='01007007'||productType=='01006001'||productType=='01006002'
		|| productType=='01006010' //村镇银行贴现产品
		){
		$("#dkll").css("display","block");
	}
	//还款信息
	if(productType=='01008001'||productType=='01008010' ||productType=='01008002'||productType=='01009001'||productType=='01009002' ||productType=='01009010'||productType=='01007008'
		||productType=='01010001'||productType=='01007007'||productType=='01011001'||productType=='01012001'){
		$("#hkfs").css("display","none");
		$("#schkqc").css("display","none");
		$("#tqhkwyj").css("display","none");
		nui.get("amountDetail.repaymentType").hide();
		nui.get("amountDetail.firstRepayTerm").hide();
		nui.get("amountDetail.prepaymentPenalty").hide();
	}
	//还款来源
	if(productType=='01007008'){
		$("#hkly").css("display","none");
		nui.get("amountDetail.payment").hide();
	}
	$("#wy").css("display","none");
	if(productType=='01001005'||productType=='01003005'){
		$("#wy").css("display","block");
		var jsonwy = nui.decode({"amountDetailId":amountDetailId});
		var gridwy = nui.get("gridwy");
		gridwy.load(jsonwy);
	}
	
	$("#project").css("display","none");
	if(productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'
		||productType=='01003003'||productType=='01003004'||productType=='01003006'||productType=='01005001'||productType=='01003005'
	 	||productType=='01003007'||productType=='01003009'||productType=='01003011'||productType=='01003012'
	 	||productType=='01003013'||productType=='01003015'||productType=='01003016'
	 	||productType=='01003050' //村镇银行固定资产产品
	 	){
		$("#project").css("display","block");
		var json = nui.decode({"amountDetailId":amountDetailId});
		var gridpj = nui.get("gridxm");
		gridpj.load(json);
	}
	
	initPage();
	function initPage(){
        var json = nui.encode({"amountDetailId":"<%=request.getAttribute("amountDetailId")%>"});
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.getProductDetail.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
				//委托贷款查询客户
				if(productType=='01013001' || productType=='01013010'){
					if(o.productDetail.wtxmId != null && o.productDetail.wtxmId!=''){
						var json=nui.encode({"wtxmId":o.productDetail.wtxmId});
						$.ajax({
				            url: "com.bos.bizInfo.person.queryWtrXx.biz.ext",
				            type: 'POST',
				            data: json,
				            cache: false,
				            async: false,
				            contentType:'text/json',
				            success: function (text) {
				          		nui.get("productDetail.wtr").setValue(text.wtxm.PARTY_ID);
				          		nui.get("productDetail.wtr").setText(text.wtxm.PARTY_NAME);
				          		nui.get("wtxmmc1").setValue(text.wtxm.ENTRUST_PROJECT_NAME);
					        	nui.get("zjlx").setValue(text.wtxm.CERT_TYPE);
					        	nui.get("zjhm").setValue(text.wtxm.CERT_NUM);
				            }
			           })
					}
				}
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if(proFlag=="0"){
			form.setEnabled(false);
		}
	}
	
	//动态列表点击新增
	function addfp(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"periodsNumber":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function removefp(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.fpId){
            		var json = nui.encode({"fpId":row.fpId});
	            	$.ajax({
			            url: "com.bos.bizInfo.bizInfo.delFp.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    //动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"periodsNumber":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.repayPlanId){
            		var json = nui.encode({"repayPlanId":row.applyDetailId,"deleteType":"5"});
	            	$.ajax({
			            url: "com.bos.conInfo.conContractInfo.deleteRepayPlan.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
    function calcuMoney(){
    	var detailAmt =  nui.get("amountDetail.detailAmt").getValue();
    	var exchangeRate = nui.get("amountDetail.exchangeRate").getValue();
    	nui.get("amountDetail.rmbAmt").setValue(parseFloat(detailAmt)*parseFloat(exchangeRate));
 	}
 	//汇票贴现中付息标志改变触发事件
 	function sffx(){
		var sffx = nui.get("productDetail.sfxyfx").getValue();
		if(sffx=='1'){
			nui.get("sq1").show();
			nui.get('base2').refreshTable();
		}else{
			nui.get("sq1").hide();
			nui.get('base2').refreshTable();
		}
	}
	//只有阶段性还款方式才显示首次还本期次
 	function conRpTpChg(){
		var hkfs = nui.get("amountDetail.repaymentType").getValue();
		if(hkfs=='0300'||hkfs=='0400'){
			$("#schkqc").css("display","block");
			nui.get("amountDetail.firstRepayTerm").show();
		}else{
			$("#schkqc").css("display","none");
			nui.get("amountDetail.firstRepayTerm").hide();
			nui.get("amountDetail.firstRepayTerm").setValue('');
		}
		nui.get("base1").refreshTable();
	}
</script>
</body>
</html>
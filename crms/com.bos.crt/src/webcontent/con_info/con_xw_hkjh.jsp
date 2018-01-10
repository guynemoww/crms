<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>不规则还款计划</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.4%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="cs">
			<label class="nui-form-label">年利率：</label>
			<input id="yearrate" class="nui-text nui-form-input" name="yearrate"/>
			<label class="nui-form-label">期次：</label>
			<input id="hkqc" class="nui-text nui-form-input" name="hkqc"/>
			<label class="nui-form-label">结息周期：</label>
			<input id="jxzq"  name="jxzq"  class="nui-text nui-form-input" dictTypeId="XD_SXCD1018" />
		</div>
		<div class="nui-dynpanel" columns="4" id="show">
			<label class="nui-form-label">还款金额：</label>
			<input id="totalAmt" class="nui-text nui-form-input" name="totalAmt"/>
			<label class="nui-form-label">本金：</label>
			<input id="bj" class="nui-text nui-form-input" name="bj"/>
			<label class="nui-form-label">利息总额：</label>
			<input id="totalLx" class="nui-text nui-form-input" name="totalLx"/>
			<label class="nui-form-label">总期次：</label>
			<input id="totalTerm" class="nui-text nui-form-input" name="totalTerm"/>
			<label class="nui-form-label">贷款起期：</label>
			<input id="dkqq" class="nui-text nui-form-input" name="dkqq" dateFormat="yyyy-MM-dd"/>
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="hkjhdiv">
		</div>
		<div id="hkjh" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.bizInfo.bizInfo.getHkjhList.biz.ext" dataField="hkjhs"
			allowResize="true" showReloadButton="false" allowCellEdit="true" 
		    allowCellSelect="true" showPager="false"
			sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="100" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" >选择</div>
            	<div type="indexcolumn" width="40px">序号</div>
				<div field="END_DATE" headerAlign="center">还款时间
					<input property="editor" class="nui-datepicker" onvaluechanged="dataChange(1)"/>
				</div>
				<div field="DAYS" headerAlign="center">天数
					<input property="editor" class="nui-text"/>
				</div>
				<div field="AMT" headerAlign="center">还款金额
					<input property="editor" class="nui-text"  />
				</div>
				<div field="BJ" headerAlign="center">本金
					<input property="editor" class="nui-text"  />
				</div>
				<div field="LX" headerAlign="center">利息
					<input property="editor" class="nui-text" />
				</div>
				<div field="SYBJ" headerAlign="center">剩余本金
					<input property="editor" class="nui-text" />
				</div>
			</div>
		</div>	
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var proFlag =  "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	if(proFlag!="1"){
		form.setEnabled(false);
		var grid1= nui.get("hkjh");
		grid1.setEnabled(false);
	}
	$("#cs").css("display","none");
	var amountDetailId = "<%=request.getParameter("amountDetailId") %>";
	initPage();
	function initDataTable(){
    	var bj = nui.get("bj").getValue();
		var hkqc = nui.get("hkqc").getValue();
		var yearrate = nui.get("yearrate").getValue();
		var jxzq = nui.get("jxzq").getValue();
		var dkqq = nui.get("dkqq").getValue();
		var json = nui.encode({"bj":bj,"hkqc":hkqc,"yearrate":yearrate,"jxzq":jxzq,"dkqq":dkqq,"amountDetailId":amountDetailId});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.saveHkjhList.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.resultMap.resultMsg !='1'){
            		nui.alert(mydata.resultMap.resultMsg);
            	}else{
            		nui.get("totalAmt").setValue(mydata.resultMap.totalAmt);
	            	nui.get("totalLx").setValue(mydata.resultMap.totalLx);
	            	nui.get("totalTerm").setValue(mydata.resultMap.totalTerm);
            	}
            	loadformhkjh();
			}
        });
	}
	function initPage(){
		var json = nui.encode({"amountDetailId":amountDetailId});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.initHkjhPageForCon.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	initDataTable();
			}
        });
		
	}
	
	function loadformhkjh(){
		var json = nui.decode({"amountDetailId":amountDetailId});
		var grid = nui.get("hkjh");
		grid.load(json);
	}
	//重新开始
	function refresh(){
		var json = nui.encode({"amountDetailId":amountDetailId});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.delHkjhList.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	initDataTable();
			}
        });
	}
	
	function dataChange(a){
		nui.get("hkjh").commitEdit();
		var hkjh = nui.get("hkjh").getSelected();
		var o = form.getData();
		o.hkjh = hkjh;
		o.hkjh.qc = o.hkjh.QC;
		o.hkjh.endDate = hkjh.END_DATE;
		o.hkjh.days = hkjh.DAYS;
		o.hkjh.amt = hkjh.AMT;
		o.hkjh.bj = hkjh.BJ;
		o.hkjh.lx = hkjh.LX;
		o.hkjh.sybj = hkjh.SYBJ;
		o.hkjh.bz1 = hkjh.BZ1;
		o.hkjh.bz2 = hkjh.BZ2;
		o.hkjh.bz3 = hkjh.BZ3;
		o.hkjh.amountDetailId = hkjh.AMOUNT_DETAIL_ID;
		o.a = a;
		var bj = nui.get("bj").getValue();
		var hkqc = nui.get("hkqc").getValue();
		var yearrate = nui.get("yearrate").getValue();
		var jxzq = nui.get("jxzq").getValue();
		var dkqq = nui.get("dkqq").getValue();
		o.bj = bj;
		o.hkqc = hkqc;
		o.yearrate = yearrate;
		o.jxzq = jxzq;
		o.dkqq = dkqq;
		var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.updateHkjhFlg.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	loadformhkjh();
            	if(mydata.resultMap.resultMsg =='1'){
            		nui.get("totalAmt").setValue(mydata.resultMap.totalAmt);
	            	nui.get("totalLx").setValue(mydata.resultMap.totalLx);
	            	nui.get("totalTerm").setValue(mydata.resultMap.totalTerm);
            	}else{
            		nui.alert(mydata.resultMap.resultMsg);
            	}
			}
        });
	}
	
	
	//动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.QC){
            		var bj = nui.get("bj").getValue();
					var hkqc = nui.get("hkqc").getValue();
					var yearrate = nui.get("yearrate").getValue();
					var jxzq = nui.get("jxzq").getValue();
					var dkqq = nui.get("dkqq").getValue();
            		var json = nui.encode({"delqc":row.QC,"bj":bj,"hkqc":hkqc,"yearrate":yearrate,"jxzq":jxzq,"dkqq":dkqq});
	            	$.ajax({
			            url: "com.bos.bizInfo.bizInfo.delSingleHkjh.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
			            	loadformhkjh();
			            	nui.get("hkqc").setValue(hkqc-1);
						}
	        		});
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
</script>
</body>
</html>
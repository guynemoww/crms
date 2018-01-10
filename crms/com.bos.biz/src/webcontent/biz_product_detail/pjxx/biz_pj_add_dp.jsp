<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description: 票据信息新增
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		System.out.println("pAddress==========biz/biz_product_detail/pjxx/biz_pj_add_dp.jsp");
		System.out.println("ecifPartyNum=========="+request.getParameter("ecifPartyNum"));
	%>
	<div id="cxlist" style="width: 99.5%; height: auto; overflow: hidden;">
		<fieldset>
			<legend>
				<span>电票信息</span>
			</legend>
			<label class="nui-hidden">额度明细编号 ：</label>
			<input name="amountDetailId" id="amountDetailId" class="nui-hidden" vtype="maxLength:100" />
			<label class="nui-hidden">业务类型 ：</label>
			<input name="busiType" id="busiType" required="true" enabled="false" class="nui-hidden nui-form-input" vtype="maxLength:100" />
			<input name="contractNum" id="contractNum" required="true" enabled="false" class="nui-hidden nui-form-input" vtype="maxLength:100" />
			<input name="contractId" id="contractId" class="nui-hidden nui-form-input" />
			<div class="nui-dynpanel" columns="6">
				<label>客户编号 ：</label>
				<input name="custNo" id="custNo" required="true" class="nui-text" />
				<label>业务接收方行号：</label>
				<input name="toBankNo" id="toBankNo" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
				<label>出票日期 ：</label>
				<input name="acptDt" id="acptDt" required="true" value="<%=GitUtil.getBusiDateYYYYMMDD()%>" class="nui-text" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;padding-top:10px;">
			<a class="nui-button" iconCls="icon-search" onclick="init()">查询</a>
		</div>
		<div id="dplist" class="nui-datagrid" style="width: 100%; height: auto" url="com.primeton.ecds.client.CallECDS.ECDS021013.biz.ext" dataField="responseOut.tbBizPjxxApply" allowResize="false"
			showReloadButton="false" allowCellEdit="false" sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="pjhm" headerAlign="center" allowSort="true">汇票号码</div>
				<div field="cprqc" headerAlign="center" allowSort="true">出票人全称</div>
				<div field="skrqc" headerAlign="center" allowSort="true">收款人全称</div>
				<div field="pjhm" headerAlign="center" allowSort="true">票据号码</div>
				<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
				<div field="hpje" headerAlign="center" allowSort="true">汇票金额</div>
				<div field="hpxs" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票形式</div>
				<div field="hpcprq" headerAlign="center" allowSort="true">汇票出票日期</div>
				<div field="hpdqrq" headerAlign="center" allowSort="true">汇票到期日期</div>
			</div>
		</div>
		<div class="nui-toolbar1" style="border: 0; text-align: right; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-save" onclick="save()">确定</a>
		</div>
	</div>
	<script type="text/javascript">
 	nui.parse();
 	var cxlist = new nui.Form("#cxlist")
	var amountDetailId = "<%=request.getParameter("amountDetailId")%>"; // 额度明细ID
	var contractNum = "<%=request.getParameter("contractNum")%>"; // 合同编号
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>"; //ECIF客户号 
	var contractId = "<%=request.getParameter("contractId")%>"; // 合同Id
	nui.get("amountDetailId").setValue(amountDetailId);
	nui.get("busiType").setValue("1"); // 查询类型默认为1-银承
	nui.get("custNo").setValue(ecifPartyNum); // 客户编号
	nui.get("custNo").validate();
	nui.get("contractNum").setValue(contractNum); // 合同编号
	nui.get("contractId").setValue(contractId); // 合同Id
	
	initAoAcctNo();
	
	function init() {
		var grid = nui.get("dplist");
		var data = cxlist.getData();
		grid.load(data);
	}
	
	// 查询业务接收方行号
	function initAoAcctNo() {
		git.mask("cxlist");
		var json = nui.encode({"type":"3"});
		$.ajax({
			url: "com.bos.comm.pub.orgRel.orgRel.biz.ext",
			type: 'POST',
			data: json,
			contentType:'text/json',
			async: false, //异步处理
			success: function (mydata) {
				git.unmask("cxlist");
				nui.get("toBankNo").setValue(mydata.orgRel[0].BANKNO);
			},
			error : function(mydata) {
				git.unmask("cxlist");
				nui.alert("操作失败！");
			}
		});
		
		init();
	}
	
	
	// 保存电票 
	function save() {
		var grid = nui.get("dplist");
		var rows = grid.getSelecteds();
		if (null == rows) {
			nui.alert("请选择票据信息！");
			return false;
		}
		//检查票据是否存在
		for (var i = 0; i < rows.length; i++) {
			var json = {"pjhm" : rows[i].pjhm};
			var msg = exeRule("RBIZ_0065", "1", json);
			if (null != msg && '' != msg) {
				nui.alert(msg);
				return;
			}
		}
		// 检查信息是否完整
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].amountDetailId == null) {nui.alert("操作失败！");	return;}
			if (rows[i].pjhm == null) {nui.alert("汇票号码为空,操作失败！");return;}
			if (rows[i].hpje == null) {nui.alert("汇票金额为空,操作失败！");return;}
			if (rows[i].hpcprq == null) {nui.alert("出票日期为空,操作失败！");return;}
			if (rows[i].hpdqrq == null) {nui.alert("到期日期为空,操作失败！");return;}
			if (rows[i].cprqc == null) {nui.alert("出票人全称为空,操作失败！");return;}
			if (rows[i].cprzh == null) {nui.alert("出票人账号为空,操作失败！");return;}
			if (rows[i].remitterbankname == null) {nui.alert("出票行行名为空,操作失败！");return;}
			if (rows[i].remitterbankno == null) {nui.alert("出票行行号为空,操作失败！");return;}
			if (rows[i].skrqc == null) {nui.alert("收款人名称为空,操作失败！");return;}
			if (rows[i].skrzh == null) {nui.alert("收款人账号为空,操作失败！");return;}
			if (rows[i].payeebankname == null) {nui.alert("收款人开户行行名为空,操作失败！");return;}
			if (rows[i].payeebankno == null) {nui.alert("收款人开户行行号为空,操作失败！");return;}
			if (rows[i].acceptorbankname == null) {nui.alert("承兑行行名为空,操作失败！");return;}
			if (rows[i].acceptorbankno == null) {nui.alert("承兑行行号为空,操作失败！");return;}
			if (rows[i].billid == null) {nui.alert("电票ID为空,操作失败！");return;}
			if (rows[i].forbidflag == null) {nui.alert("禁止背书标记为空,操作失败！");return;}
			if (rows[i].htbh == null||rows[i].htbh == ""||rows[i].htbh == "null") {nui.alert("合同编号为空,操作失败 ");return;}
		}
		var json = nui.encode({"tbBizPjxxApply" : rows})+nui.encode({"contractId":contractId});
		debugger;
		nui.confirm("确定添加吗？","确认",	function(action) {
			if (action != "ok") {
				return;
			}
			git.mask("cxlist");
			$.ajax({
					url : "com.bos.bizProductDetail.bizPjxx.insertPjxxdp.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						git.unmask("cxlist");
						if (text.msg) {
							nui.alert(text.msg);
							return;
						}
						CloseWindow('ok');
					},
					error : function(text) {
						git.unmask("cxlist");
						nui.alert("操作失败！");
					}
			});
		});
	}
	</script>
</body>
</html>

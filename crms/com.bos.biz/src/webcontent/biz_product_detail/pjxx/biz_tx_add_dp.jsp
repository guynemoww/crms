<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description: 贴现信息新增
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<% 
  System.out.println("pAddress==========biz/biz_product_detail/pjxx/biz_tx_add_dp.jsp");
  System.out.println("ecifPartyNum=========="+request.getParameter("ecifPartyNum"));
 %>
<body>
<div id="cxlist" style="width: 99.5%; height: auto; overflow: hidden;">
	<fieldset>
		<legend>
			<span>电票信息</span>
		</legend>
		<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.comm.pub.orgRel.selectOrgRel" />
		<label class="nui-hidden">额度明细编号 ：</label>
		<input name="amountDetailId" id="amountDetailId" class="nui-hidden" vtype="maxLength:100" />
		<label class="nui-hidden">业务类型 ：</label>
		<input name="busiType" id="busiType" required="true" enabled="false" class="nui-hidden nui-form-input" vtype="maxLength:100" />
		<div class="nui-dynpanel" columns="6" id="tableForm">
			<label>客户编号 ：</label>
			<input name="custNo" id="custNo" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />		
			<label>业务接收方行号：</label>
			<input name="toBankNo" id="toBankNo" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			<label>入账账号：</label>
			<input name="aoAcctNo" id="aoAcctNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;padding-top:10px;">
		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	</div>
</dir>
<div>
	<div id="dplist" class="nui-datagrid" style="width: 100%; height: auto"
		url="com.primeton.ecds.client.CallECDS.ECDS021013.biz.ext" dataField="responseOut.tbBizTxxxApply"
		allowResize="false" showReloadButton="false" allowCellEdit="false" sizeList="[10,15,20,50,100]" multiSelect="true"
		pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="billno" headerAlign="center" allowSort="true">汇票号码</div>
			<div field="billamt" headerAlign="center" allowSort="true">票面金额</div>
			<div field="takeoutacname" headerAlign="center" allowSort="true">出票人名称</div>
			<div field="benename" headerAlign="center" allowSort="true">收款人名称</div>
			<div field="billtype" headerAlign="center" allowSort="true" dictTypeId="XD_SXYW0203">汇票类型</div>
			<!-- <div field="billmodel" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票模式</div> -->
			<div field="billbegindate" headerAlign="center" allowSort="true">出票日期</div>
			<div field="billenddate" headerAlign="center" allowSort="true">到期日期</div>
			<div field="interate" headerAlign="center" allowSort="true">利率</div>
			<div field="forbidflag" headerAlign="center" allowSort="true" dictTypeId="XD_JZBSBJ01">禁止背书标记</div>
			<div field="aoAcctNo" headerAlign="center" allowSort="true" >入账账号</div>
		</div>
	</div>
	<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	</div>
</div>

<script type="text/javascript">
 	nui.parse();
    var formtx = new nui.Form("#cxlist");
	var amountDetailId = "<%=request.getParameter("amountDetailId")%>"; // 明细ID
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>"; // ECIF客户号
	var contractId = "<%=request.getParameter("contractId") %>"; // 合同ID
	var org_num  = "<%=GitUtil.getCurrentOrgCd()%>"; // 当前机构
	nui.get("amountDetailId").setValue(amountDetailId);
	nui.get("busiType").setValue("2"); // 贴现 
	nui.get("custNo").setValue(ecifPartyNum);
	var interate = "";
	initRate(); // 利率
	initAoAcctNo(); // 业务接收方行号
	search();
	
	function search() {
		var grid = nui.get("dplist");
		var data = new nui.Form("#cxlist").getData();
		grid.load(data);
	}
	
	// 利率
	function initRate() {
		var json = nui.encode({"contractId" : contractId});
		$.ajax({
			url : "com.bos.bizProductDetail.bizPjxx.getLoanRate.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			async: false, //异步处理
			success : function(o) {
				if (o.loanrate.yearRate) {
					interate=o.loanrate.yearRate; 
				}else{
					nui.alert("未查询到利率信息！");
				}
			},
			error : function(text) {
				nui.alert("操作失败！");
			}
		});
	}
	
	// 查询业务接收方行号
	function initAoAcctNo() {
		git.mask("cxlist");
		var json = nui.encode({"type":"5"});
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
	}
	
	// 添加电票
	function save() {
		var grid = nui.get("dplist");
		var rows = grid.getSelecteds();
		if (null == rows) {
			nui.alert("请选择票据信息！");
			return false;
		}
		// 检查信息是否完整
		for(var i = 0;i < rows.length;i++){
			if(rows[i].amountDetailId==null){nui.alert("操作失败！");return false;}
			if(rows[i].billno==null){nui.alert("汇票号码为空,操作失败！");return false;}
			if(rows[i].billamt==null){nui.alert("汇票金额为空,操作失败！");return false;}
			if(rows[i].billbegindate==null){nui.alert("出票日期为空,操作失败！");return false;}
			if(rows[i].billenddate==null){nui.alert("到期日期为空,操作失败！");return false;}
			if(rows[i].takeoutacname==null){nui.alert("出票人全称为空,操作失败！");return false;}
			if(rows[i].takeoutacbankno==null){nui.alert("出票人账号为空,操作失败！");return false;}
			if(rows[i].takeoutacbankname==null){nui.alert("出票行行名为空,操作失败！");return false;}
			if(rows[i].takeoutacno==null){nui.alert("出票行行号为空,操作失败！");return false;}
			if(rows[i].benename==null){nui.alert("收款人名称为空,操作失败！");return false;}
			if(rows[i].beneno==null){nui.alert("收款人账号为空,操作失败！");return false;}
			if(rows[i].benebankname==null){nui.alert("收款人开户行行名为空,操作失败！");return false;}
			if(rows[i].benebankno==null){nui.alert("收款人开户行行号为空,操作失败！");return false;}
			if(rows[i].billbankname==null){nui.alert("承兑行行名为空,操作失败！");return false;}
			if(rows[i].billbankno==null){nui.alert("承兑行行号为空,操作失败！");return false;}
			if(rows[i].forbidflag==null){nui.alert("禁止背书标记为空,操作失败！");return false;}
			if(rows[i].onlinemark==null){nui.alert("线上清算标准为空,操作失败！");return false;}
			if(rows[i].interate==null){nui.alert("贴现利率为空,操作失败！");return false;}
			if(rows[i].interate==null){nui.alert("贴现利率为空,操作失败！");return false;}
			if(rows[i].interate*1!=interate*1){nui.alert("贴现利率与申请利率不同,操作失败！");return false;}
		}
		//检查票据是否存在
		for(var i = 0;i < rows.length;i++){
			var json = {"billno":rows[i].billno};
			var msg = exeRule("RBIZ_0066","1",json);
			if(null != msg && '' != msg){
				nui.alert(msg);
				return false;
			}
		}
		
		var json = nui.encode({"tbBizTxxxApply" : rows});
		nui.confirm("确定添加吗？", "确认", function(action) {
			if (action != "ok"){
				return;
			}
			$.ajax({
				url : "com.bos.bizProductDetail.bizPjxx.InsertTxxxdp.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
						return;
					}
					var json = nui.decode({"amountDetailId" : amountDetailId});
					CloseWindow('ok');
				},
				error : function(text) {
					nui.alert("操作失败！");
				}
			});
		});
	}
</script>
</body>
</html>

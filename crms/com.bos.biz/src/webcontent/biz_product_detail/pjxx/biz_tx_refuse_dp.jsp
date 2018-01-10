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
		<a class="nui-button" iconCls="icon-search" onclick="init()">查询</a>
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
				<div field="billmodel" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票模式</div>
				<div field="billbegindate" headerAlign="center" allowSort="true">出票日期</div>
				<div field="billenddate" headerAlign="center" allowSort="true">到期日期</div>
				<div field="forbidflag" headerAlign="center" allowSort="true" dictTypeId="XD_JZBSBJ01">禁止背书标记</div>
			</div>
		</div>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-save" onclick="refuse()">拒绝</a>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	var cxlist = new nui.Form("#cxlist");
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>";
	nui.get("busiType").setValue("2"); // 查询类型为 2-贴现
	nui.get("custNo").setValue(ecifPartyNum); // 客户编号
	initAoAcctNo();
	cxlist.validate();
	init();
	
	function init(){
		var grid = nui.get("dplist");
		var data = new nui.Form("#cxlist").getData();
		grid.load(data);
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
	
	// 拒绝 
	function refuse() {
		var grid = nui.get("dplist");
		var rows = grid.getSelecteds();
		if (null == rows) {
			nui.alert("请选择票据信息！");
			return false;
		}
		//检查票据是否存在
		var billnos = new Array();
		for(var i = 0;i < rows.length;i++){
			billnos[i] = rows[i].billno;
			var json = {"billno":rows[i].billno};
			var msg = exeRule("RBIZ_0066","1",json);
			if(null != msg && '' != msg){
				nui.confirm(rows[i].billno+"已在申请中,确定拒绝吗？", "确认", function(action) {if (action != "ok"){return;}});
			}
		}
		var json = nui.encode({"billnos" : billnos,"busiType" : 2});
		nui.confirm("确定拒绝吗？", "确认", function(action) {
			if (action != "ok") {
				return;
			}
			$.ajax({
				url : "com.primeton.ecds.client.CallECDS.ECDS021009.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
						return;
					}
					nui.alert("操作成功！");
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

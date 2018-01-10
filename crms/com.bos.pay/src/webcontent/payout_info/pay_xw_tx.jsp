<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>贴息信息页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input name="tbLoanInfo.loanId" class="nui-hidden nui-form-input"  />
		<div class="nui-dynpanel" columns="4">					
			<label>贴息止期：</label>
			<input id="tbLoanInfo.txzq" name="tbLoanInfo.txzq"  required="true" class="nui-datepicker nui-form-input"  allowInput="false" dataType="date"/>
		</div>	
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	</div>
	
	<div id="form1" style="width: 100%; height: auto" class="nui-form">
		<div class="nui-toolbar"
			style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
			<a class="nui-button" id="tx_view" iconCls="icon-node"
				onclick="edittx(1)">查看</a> 
		</div>

		<div id="gridtx" class="nui-datagrid" style="width: 100%; height: auto"
			url="com.bos.bizProductDetail.bizTx.getTxList.biz.ext"
			dataField="txs" allowResize="false" showReloadButton="false"
			allowCellEdit="false" sizeList="[10,15,20,50,100]"
			multiSelect="false" pageSize="15" sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="txfs" headerAlign="center" allowSort="true"
					dictTypeId="XD_TXFS0001">贴息方式</div>
				<div field="txbl" headerAlign="center" allowSort="true">贴息比例（%）</div>
				<div field="gdje" headerAlign="center" allowSort="true">固定金额（元）</div>
				<div field="xe" headerAlign="center" allowSort="true">限额</div>
				<div field="qx" headerAlign="center" allowSort="true">期限（月）</div>
				<div field="txzt1" headerAlign="center" allowSort="true">贴息主体</div>
				<div field="txzh1" headerAlign="center" allowSort="true">贴息账号</div>
			</div>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var gridtx = nui.get("gridtx");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var loanId = "<%=request.getParameter("loanId") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var applyId = "";
    var json = nui.encode({"loanId":loanId});
	$.ajax({
        url: "com.bos.payInfo.PayInfo.getApplyIdByLoanId.biz.ext",
        type: 'POST',
        data: json,
        async:false,
        cache: false,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
    		applyId = mydata.applyId;
        	var json2 = {"applyId":applyId};	
    		gridtx.load(json2);
		}
    });
	initPage();
	function initPage(){
    	$.ajax({
            url: "com.bos.payInfo.PayTx.getTxzqLoan.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
			}
        });
		
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if(proFlag=="0" ||proFlag=="-1" ){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
		}
	}
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.payInfo.PayTx.saveTxzq.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.msg){
	            	nui.alert(mydata.msg);
            	}else{
    				nui.alert("保存成功!");
    				initPage();
            	}
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
	//编辑贴息信息
	function edittx(v) {
		var row = gridtx.getSelected();
		if (row) {
			nui.open({
				url : nui.context + "/biz/biz_product_detail/person/biz_tx_edit.jsp?txId=" + row.txId + "&view=" + v,
				title : "编辑",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = row;
					//iframe.contentWindow.SetData(data);
				},
				ondestroy : function(action) {
					if (action == "ok") {
						var json3 = nui.decode({ "applyId" : applyId });
						gridtx.load(json3);
					}
				}
			});
		} else {
			alert("请选择项目信息！");
		}
	}
</script>
</body>
</html>
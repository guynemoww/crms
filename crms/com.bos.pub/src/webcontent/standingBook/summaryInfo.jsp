<%@page pageEncoding="UTF-8"%>
<html>

<head>
<title>借据台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:hidden;">
<div title="借据台账" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>合同编号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input"  />
		<label>借据编号：</label>
		<input name="map/relativeserialno" required="false" class="nui-textbox nui-form-input"  />				
		<label>所属机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
		
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit" />导出</a>--%>
   </div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.pub.standingbook.batchHandBook.getBatchHandBook.biz.ext"
		dataField="batch"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="relativeserialno" headerAlign="center" allowSort="true" >业务借据号</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="occurdate" headerAlign="center" allowSort="true" >发生日期</div>
			<div field="dcurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="occursubject" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1210">交易描述</div>
			<div field="occurdirection" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1212">交易描述产生方式</div>
			<div field="actualsum" headerAlign="center" allowSort="true" dataType="currency">总发生金额（元）</div>
			<div field="orgname" headerAlign="center" allowSort="true" >登记机构</div>
			<div field="inputdate" headerAlign="center" allowSort="true" >登记日期</div>
			<div field="orgname" headerAlign="center" allowSort="true" >经办机构</div>
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
    	git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
   // search();
    
    function reset(){
		form.reset();
		search();
	}
	 //导出
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.relativeserialno != null) return true;
		});
		
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=226";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
    }
    
    	  	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
	</script>
</body>
</html>
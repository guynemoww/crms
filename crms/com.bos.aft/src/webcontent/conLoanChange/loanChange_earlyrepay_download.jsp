<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-08-27
  - Description:
-->
<head>
<title>提前还款通知书打印</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>提前还款列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<a class="nui-button"onclick="query">查询</a>
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.conLoanChange.findEarlyRepayList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <!-- <div type="indexcolumn" width="50px;">序号</div> -->
		        <div field="CHANGE_NUM" headerAlign="center" align="center">还款通知书编号</div>
		        <div field="SUMMARY_NUM" align="center" headerAlign="center" >借据编号</div> 
		        <div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
				<div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
		        <div field="REPAY_CAPITAL" headerAlign="center" align="right" dataType="currency">提前归还本金</div>
				<div field="YHZCLX" headerAlign="center" align="right" dataType="currency">正常利息</div>
				<div field="YHTQLX" headerAlign="center" align="right" dataType="currency">拖欠利息</div>
				<div field="YHFX" headerAlign="center" align="right" dataType="currency">罚息</div>
				<div field="REPAY_AMT" headerAlign="center" align="right" dataType="currency">本息合计</div>
				<div field="USER_NUM" headerAlign="center" align="center" dictTypeId="user">经办人</div>
				<div field="ORG_NUM" headerAlign="center" align="center" dictTypeId="org">经办机构</div>
				<div field="CHANGE_DATE" headerAlign="center" align="center">交易日期</div>
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  
	query();
	function query(){
		var o = form.getData();
		grid.load(o);
	}
	
	function clickDownload(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		
		var json = nui.encode({"map":{"changeId":row.CHANGE_ID,"reportName":'/aft/loanChange_earlyrepay.docx'}});
		$.ajax({
            url: "com.bos.aft.conLoanChange.printLoanChange.biz.ext",
            //url: "com.bos.biz.print.printApproveXw.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "还款信息预览", width: 1000, height: 600,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
       	});	
	}
	
	//初始化自然人证件类型
	function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	

</script>
</body>
</html>
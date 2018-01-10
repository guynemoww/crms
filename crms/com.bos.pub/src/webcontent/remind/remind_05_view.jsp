<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2014-08-25 14:12:10
  - Description:逾期/垫款提示
-->
<head>
<title>逾期/垫款提示</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />
		
		<label>提示日期：</label>
		<div>
			<input id="item.stDate" name="item.stDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />-
			<input id="item.enDate" name="item.enDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
		</div>
		<label>处理状态：</label>
		<div>
			<input name="item.remindStatus" id="item.remindStatus" class="nui-dictcombobox nui-form-input" dictTypeId="YP_YJCD0002"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.getRemindInfoByType.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true"  >客户名称</div>
		<div field="TELEPHONE" headerAlign="center" allowSort="true">联系人电话</div>
		<div field="RECEIPT_NUM" headerAlign="center" allowSort="true" >合同/借据编号</div>
		<div field="NEW_SUMMARY_AMT" headerAlign="center" allowSort="true" dataType="currency">合同/借据金额</div>
		<div field="NEW_JJYQBJ" headerAlign="center" allowSort="true" dataType="currency">逾期/垫款本金</div>
		<div field="NEW_ARREAR_ITR" headerAlign="center" allowSort="true" dataType="currency">逾期利息</div>
		<div field="NEW_PUNISH_ITR" headerAlign="center" allowSort="true" dataType="currency">罚息</div>
		<div field="NEW_END_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >贷款到期日</div>
		<div field="NEW_YQTS" headerAlign="center" allowSort="true" dataType="int" >逾期天数</div>
	</div>
</div> 		
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	var remindType="<%=request.getParameter("remindType") %>";
	var exsql = "com.bos.pub.remind.select_"+remindType+"_id";
	//初始化提醒状态
	var arr = git.getDictDataFilter("YP_YJCD0002","01,02,03");
	nui.get("item.remindStatus").setData(arr);
	nui.get("item.remindStatus").setValue("01");
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
       			var res = getReceiptInfo(e.data[i]['RECEIPT_NUM']);
       			e.data[i]['RECEIPT_NUM']=res.RECEIPT_NUM;
       			e.data[i]['NEW_SUMMARY_AMT']=res.RECEIPT_AMT;
       			e.data[i]['RECEIPT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].RECEIPT_NUM+ '\');">'+e.data[i].RECEIPT_NUM+'</a>';	
       		}
    });
	function query(){
		git.mask("form1");
		var o = form.getData(); //获取表单多个控件的数据
		o.remindType = remindType;
		grid.load(o);
		git.unmask("form1");
	}
	query();
    function reset(){
		form.reset();
	}
	function toDo(remindId,status){
		
		//更新提示状态
		var json = nui.encode({"remindId":remindId,"remindStatus":status});
		nui.ajax({
	        url: "com.bos.pub.Remind.updateRemindInfoStatus.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	               grid.reload();
	            }
	        }
	    });
	}
	

	//获取动态数据
	function getReceiptInfo(bizNum){
		var result;
		//更新提示状态
		var json = nui.encode({"summaryNum":bizNum});
		nui.ajax({
	        url: "com.bos.pub.Remind.getReceiptNum.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	result = text.result;
	        }
	    });
		return result;
	}
</script>
</body>
</html>
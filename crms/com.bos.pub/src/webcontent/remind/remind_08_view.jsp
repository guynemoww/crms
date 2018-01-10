<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s):ljf
  - Date: 2014-08-25 14:12:10
  - Description:证件到期提醒
-->
<head>
<title>证件到期提示</title>
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
		<div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">机构名称</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
		<div field="CERT_TYPE" headerAlign="center" allowSort="true"  dictTypeId="CDKH0002">证件类型</div>
		<div field="CERT_NUM" headerAlign="center" allowSort="true">证件号码</div>
		<div field="CERT_END_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">证件到期日</div>
		<div field="TO_END_DAYS" headerAlign="center" allowSort="true" dataType="int">距离证件到期日（天）</div>
		<div field="CERT_STATUS" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0223">证件状态</div>
		<div field="CUST_USER_MANAGER" headerAlign="center" allowSort="true" dictTypeId="user">管户客户经理</div>
		<!-- <div field="op" headerAlign="center" allowSort="true" dictTypeId="user" >操作</div> -->
	</div>
</div> 			
<script type="text/javascript">
	nui.parse();
	var currDate = "<%=GitUtil.getBusiDateYYYYMMDD()%>";
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
       			e.data[i]['TO_END_DAYS'] = getDiffDays(e.data[i]['CERT_END_DATE']);
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
       			if(e.data[i]['CERT_TYPE'] == "203"){//营业执照
       				e.data[i]['CERT_TYPE'] = "营业执照";
       			}
       			var end_date = e.data[i]['CERT_END_DATE'];
       			end_date = end_date.replace("-","").replace("-","");
       			if(parseInt(currDate) > parseInt(end_date)){
       				e.data[i]['CERT_STATUS'] = "02";//"失效";
       			}else{
       				e.data[i]['CERT_STATUS'] = "01";//"生效";
       			}
       			
       		}
       });
	function query(){
		var o = form.getData(); //获取表单多个控件的数据
		o.remindType = remindType;
		grid.load(o);
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
	//计算距离到期天数
	function getDiffDays(endDate){
		var result;
		//更新提示状态
		var json = nui.encode({"endDate":endDate});
		nui.ajax({
	        url: "com.bos.pub.Remind.getDiffDays.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	result = text.days;
	        }
	    });
		return result;
	}
</script>
</body>
</html>
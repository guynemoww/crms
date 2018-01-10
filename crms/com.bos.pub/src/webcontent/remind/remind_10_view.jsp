<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2014-08-25 14:12:10
  - Description:
-->
<head>
<title>查看提示信息</title>
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
		<div field="REMIND_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0015">提示类型</div>
		<div field="REMIND_STATUS" headerAlign="center" allowSort="true" dictTypeId="YP_YJCD0002" >处理状态</div>
		<div field="CREATE_DATE" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd HH:mm:ss">提示日期</div>
		<div field="GUAR_DESC" headerAlign="center" allowSort="true" >提示描述</div>
		<div field="op" headerAlign="center" allowSort="true" dictTypeId="user" >操作</div>
	</div>
</div> 
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	var remindType="<%=request.getParameter("remindType") %>";
	var exsql = "com.bos.pub.remind.select_"+remindType+"_id";
	//初始化提醒状态
	var arr = git.getDictDataFilter("YP_YJCD0002","01,02");
	nui.get("item.remindStatus").setData(arr);
	nui.get("item.remindStatus").setValue("01");
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
       			var status = e.data[i]['remindStatus'];
	   			if('02'==status){
	   				e.data[i]['op']='<a href="#" onclick="toDo(\''+e.data[i].remindId+'\',\'01\');">转未处理</a>';	
	   			}else{
	       			e.data[i]['op']=' <a href="#" onclick="toDo(\''+e.data[i].remindId+'\',\'02\');">已处理</a>';	
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
</script>
</body>
</html>
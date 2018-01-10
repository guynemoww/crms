<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>我的客户-自然人</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<input name="sqlName"  value="com.bos.csm.naturaler.naturalPerson.acquireNaturalList"class="nui-hidden"  />
		<input id="item.orgNum" name="item.orgNum"  value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>"  class="nui-hidden"/>
		<input id="item.userNum" name="item.userNum"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" class="nui-hidden"/>
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input"  />
	
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType"  required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"   />
			
			<label>证件号码：</label>
			<input id="item.certNum" name="item.certNum" required="false"  class="nui-textbox nui-form-input"/>

			<label>是否通过完整性校验：</label>
			<input id="item.examineState" name="item.examineState"enabled="false" value="是" class="nui-textbox nui-form-input"    />
			
			<label>是否信贷客户：</label>
			<input id="item.isPotentialCust" name="item.isPotentialCust" enabled="false" value="是"class="nui-textbox nui-form-input" />
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0" >
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
		</div>
</div>		
		
	<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">获取业务权</a>
		</div>
	</div>
		
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext"  dataField="items"  allowAlternating="true"
		allowResize="true" showReloadButton="false" 
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	    <div property="columns">
			<div type="checkcolumn" ></div>
	        <div field="partyName" allowSort="true" width="" headerAlign="center">客户名称</div>
	        <div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
	        <div field="certNum" headerAlign="center" allowSort="true"width="15%">证件号码</div>
	        <div field="middelCode" headerAlign="center" allowSort="true">中征码</div>
	       	<div field="xfCreditRatingCd" headerAlign="center" allowSort="true">消费性评级</div>
	       	<div field="jyCreditRatingCd" headerAlign="center" allowSort="true">经营性评级</div>
			<div field="pfAmt" allowSort="true"  headerAlign="center"dataType="currency">批复金额</div>  
        	<div field="pfBalance" allowSort="true"  headerAlign="center"dataType="currency">批复已用金额</div>
	        <div field="mainUserNum" allowSort="true"  headerAlign="center" dictTypeId="user">管户客户经理</div>  
	        <div field="mainOrgNum" allowSort="true"  headerAlign="center" dictTypeId="org">机构名称</div>  
	     </div>
	</div>
</div> 
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	init();
	function init(){//对公单一客户查询
		//字典过滤，过滤掉202的证件类型
	   var arr = git.getDictDataUnFilter("CDKH0002",'202');
	   nui.get("item.certType").setData(arr);
	}
	function query(){
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
       var o = form.getData();//逻辑流必须返回total
       grid.load(o);
	}
	
	
	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
	});
    
		function add() {
			var row = grid.getSelected();
			if(!row){
				return alert("请选择一条记录");
			}
				var json = nui.encode({"partyId" : row.partyId});
				$.ajax({
					url : "com.bos.csm.natural.natural.acquireNatural.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						if(text.msg){
							nui.alert(text.msg);
						}else{
							nui.alert("获取成功");
						}
						query();
					},
					error : function() {
						nui.alert("操作失败！");
					}
				});
		}
    function reset() {
		nui.get("item.partyName").setValue();
		nui.get("item.certType").setValue();
		nui.get("item.certNum").setValue();
	}
</script>
</body>
</html>
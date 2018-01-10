<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-17 15:56:38
  - Description:
-->
<head>
<title>集团明细页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<fieldset><legend> <span>集团成员列表</span> </legend>
		<div id="grid" class="nui-datagrid"  sortMode="client"
		    url="com.bos.bizInfo.bizDetail.getGroupMember.biz.ext" dataField="members"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false" allowCellEdit="true"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="false" onselectionchanged="selectPo"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" allowSort="true"  width="" headerAlign="center">成员客户编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">成员客户名称</div>
		       	<div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">成员管户经理</div>
		        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">成员所在机构</div>
		    </div>
		    </div>
		<div id="grp" class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="toGroupMember" iconCls="icon-add" onclick="toGroupMember()">发送至成员行新增</a>
		</div>
		
		
		<fieldset><legend> <span>成员业务信息</span> </legend>
		<div id="grid2" class="nui-datagrid"  sortMode="client"
		    url="com.bos.bizInfo.bizDetail.getMemberBiz.biz.ext" dataField="memberBiz"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false" allowCellEdit="true"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="false"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" allowSort="true"  width="" headerAlign="center">成员客户编号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">成员客户名称</div>
		        <div field="BIZ_NUM" allowSort="true" width="" headerAlign="center">成员授信业务编号</div>
				<div field="CURRENCY_CD" allowSort="true" width="" dictTypeId="CD000001" headerAlign="center">币种</div>
				<div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">成员授信额度</div>
				<div field="CREDIT_TERM" allowSort="true" width="" headerAlign="center">期限（月）</div>
				<div field="APPLY_DATE" allowSort="true" width="" headerAlign="center">报送日期</div>
		       	<div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
		        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">经办机构</div>
		        <div field="STATUS_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003">成员授信状态</div>
		    </div>
		</div>
			<div id="grp2" class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
				<a class="nui-button" id="toGroupMember1" iconCls="icon-add" onclick="toGroupMemberTz()">发送至成员行调整</a>
				<a class="nui-button" id="viewGroupMember1" iconCls="icon-add" onclick="viewGroupMemberBiz()">查看</a>
			</div>
	</div>
</fieldset>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var grid2 = nui.get("grid2");
	var applyId = "<%=request.getParameter("applyId")%>";
	var partyId = "<%=request.getParameter("partyId")%>";
	var proFlag = "<%=request.getParameter("proFlag")%>";
	initPage();
	if("1" != proFlag){
		$("#grp").css("display","none");
		$("#toGroupMember1").css("display","none");
	}
	function initPage(){
		grid.load({"partyId":partyId});
	}
	//集团成员  发送流程
	function toGroupMember(){
		var row = grid.getSelected();
        if (row){
        	var partyTypeCd = row.PARTY_TYPE_CD;
        	if(partyTypeCd == '02'){//个人客户。需要选贷种
        		nui.open({
					url:nui.context + "/biz/biz_product_detail/person/jt_indv_product_add.jsp?partyId="+row.PARTY_ID,
					showMaxButton:true,
					title:"提示：可点击最大化按钮放大此窗口",
					width:"800",
		            height:"500",
		            ondestroy: function(e) {
		            	if(e!="ok"){//直接关闭窗口不选贷种
							return;
			           	 }
		             	var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.getData();
		                data = nui.clone(data);
		            	row.PRODUCT_TYPE = data;
		            	var json = nui.encode({"groupBizInfo":row});
				    	$.ajax({
						    url: "com.bos.bizInfo.bizDetail.toMemberCreate.biz.ext",
						    type: 'POST',
						    data: json,
						    contentType:'text/json',
						    cache: false,
						    success: function (mydata) {
						    if(mydata.msg){
						    	nui.alert(mydata.msg);
						    	return ;
						    }
						    	nui.alert("发送成功");
					    		loadgrid2('1',row.PARTY_ID);
							}
						})
		            }
				});
        	}else{//对公客户
        		//规则校验：客户有在途综合授信
				var json = {"partyId":row.PARTY_ID,"partyTypeCd":row.PARTY_TYPE_CD};
		   	    var msg = exeRule("RBIZ_0001","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在在途的综合授信业务");
			   		return;
		   	    }
		   	     //存在在途的单笔业务申请，不能进行综合授信申请
		   	     msg = exeRule("RBIZ_0044","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在在途的单笔业务");
			   		return;
		   	    }
		   	    //有生效综合授信
		   	    msg = exeRule("RBIZ_0002","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在生效综合授信业务，请选择下方列表业务发起调整！");
			   		return;
		   	    } 
		   	    //无生效评级不让申请
		   	    msg = exeRule("RBIZ_0027","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
				var json = nui.encode({"groupBizInfo":row});
		    	$.ajax({
				    url: "com.bos.bizInfo.bizDetail.toMemberCreate.biz.ext",
				    type: 'POST',
				    data: json,
				    contentType:'text/json',
				    cache: false,
				    success: function (mydata) {
				    if(mydata.msg){
				    	nui.alert(mydata.msg);
				    	return ;
				    }
				    	nui.alert("发送成功");
				    	loadgrid2('2',row.PARTY_ID);
					}
				})
        	}
        }else{
        	alert("请选择要发送的集团成员信息");
            return;
        }
	}
	//查看成员业务信息
	function viewGroupMemberBiz(){
		var row = grid2.getSelected();
        if (row) {
        	var applyId = row.APPLY_ID;
        	if(""==applyId){
        		alert("成员未添加业务信息");
        		return;
        	}
    		nui.open({
	            url: nui.context + "/biz/biz_info/biz_tree.jsp?applyId="+applyId+"&processInstId=0&proFlag=-1",
	            showMaxButton: true,
	            title: "统一成员授信信息",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	
	            }
	        });
        }else{
        	alert("请选择要一条成员业务信息");
            return;
        }
	}
	//选中成员时触发方法
	function selectPo(){
		var row = grid.getSelected();
		var partyTypeCd = row.PARTY_TYPE_CD;
		if(partyTypeCd == '02'){
			loadgrid2('1',row.PARTY_ID);
		}else{
			loadgrid2('2',row.PARTY_ID);
		}
		
	}
	
	function toGroupMemberTz(){
		var row = grid2.getSelected();
        if (row){
        	//未生效的不能调整
        	if(row.STATUS_TYPE!='03'&&row.STATUS_TYPE!='09'){
        		nui.alert("在途业务不允许调整");
        		return;
        	}
		   	nui.get("toGroupMember1").setEnabled(false);
        	//存在在途业务合同签署申请或调整时不得调整批复
	   	    var json2 = {"approveId":row.APPROVE_ID};
	   	    msg = exeRule("RBIZ_0037","1",json2);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("toGroupMember1").setEnabled(true);
		   		return;
	   	    }
	   	    //存在在途出账时不得调整批复
	   	    msg = exeRule("RBIZ_0039","1",json2);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("toGroupMember1").setEnabled(true);
		   		return;
	   	    }
        	
        	var partyTypeCd = row.PARTY_TYPE_CD;
        	if(partyTypeCd == '02'){//个人客户。
        		/* 不需要此校验
        		//规则校验：客户有在途个人业务
				var json = {"applyId":row.APPLY_ID};
		   	    var msg = exeRule("RBIZ_0046","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在在途的业务申请或调整");
			   		return;
		   	    } */
            	var json = nui.encode({"groupBizInfo":row});
		    	$.ajax({
				    url: "com.bos.bizInfo.bizDetail.indvMemberUpdate.biz.ext",
				    type: 'POST',
				    data: json,
				    contentType:'text/json',
				    cache: false,
				    success: function (mydata) {
				    if(mydata.msg){
				    	nui.alert(mydata.msg);
				    	return ;
				    }
				    	nui.alert("发送成功");
			    		loadgrid2('1',row.PARTY_ID);
					}
				})
        	}else{//对公客户
        		//对公单笔不允许调整
        		if(row.BIZ_TYPE=='01'){
        			nui.alert("对公成员单笔不允许调整，只能发送至成员行新增");
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
        		}
        		
        		//规则校验：客户有在途综合授信
				var json = {"partyId":row.PARTY_ID,"partyTypeCd":row.PARTY_TYPE_CD,"applyId":row.APPLY_ID};
		   	    var msg = exeRule("RBIZ_0001","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在在途的综合授信业务");
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
		   	    }
		   	     //存在在途的单笔业务申请，不能进行综合授信调整
		   	     msg = exeRule("RBIZ_0044","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("存在在途的单笔业务");
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
		   	    } 
		   	    //规则校验：冻结批复不能调整
		   	    var msg = exeRule("RBIZ_0024","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
		   	    }
		   	    //存在在途综合授信协议申请或调整时不得调整批复
		   	    msg = exeRule("RBIZ_0038","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
		   	    }
		   	    //存在在途调整时不得调整
		   	    msg = exeRule("RBIZ_0046","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
		   			nui.get("toGroupMember1").setEnabled(true);
			   		return;
		   	    }
				var json = nui.encode({"groupBizInfo":row});
		    	$.ajax({
				    url: "com.bos.bizInfo.bizDetail.corpMemberUpdate.biz.ext",
				    type: 'POST',
				    data: json,
				    contentType:'text/json',
				    cache: false,
				    success: function (mydata) {
				    if(mydata.msg){
				    	nui.alert(mydata.msg);
		   				nui.get("toGroupMember1").setEnabled(true);
				    	return ;
				    }
				    	nui.alert("发送成功");
				    	loadgrid2('2',row.PARTY_ID);
					}
				})
        	}
        	
		   	nui.get("toGroupMember1").setEnabled(true);
        }else{
        	alert("请选择要发送的集团成员业务信息");
            return;
        }
	}
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
       		}
       });
	grid2.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
       		}
       });
       
      
     function loadgrid2(a,partyId){
     	var showFlag = '1';//个人客户默认
     	var json = nui.encode({"partyId":partyId});
     	var proFlag1 = proFlag ;
     	if(proFlag1 !='-1'){
     		proFlag1 = '1'
     	}
     	if(a=='2'){//对公客户
     		$.ajax({
				    url: "com.bos.bizApply.groupApply.getZhsxCount.biz.ext",
				    type: 'POST',
				    data: json,
				    contentType:'text/json',
				    cache: false,
				    success: function (mydata) {
				    	if(mydata.count>0){
				    		showFlag = '2';
				    	}
				    	grid2.load({"partyId":partyId,"proFlag":proFlag1,"showFlag":showFlag});
					}
				})	
     	}else{
     		grid2.load({"partyId":partyId,"proFlag":proFlag1,"showFlag":showFlag});
     	}
     } 
       
</script>
</body>
</html>
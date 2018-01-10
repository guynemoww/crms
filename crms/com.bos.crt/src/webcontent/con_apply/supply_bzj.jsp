<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-29 17:16:31
  - Description:
-->
<head>
<title>主合同调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<center>
<fieldset><legend> <span>生效合同信息</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form"><div class="nui-dynpanel" columns="8">
						
						<label>客户名称：</label>
						<input id="map.partyName" name="map.partyName" class="nui-textbox nui-form-input"/>
						<label>证件类型：</label>
						<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>
						<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  /> 
						<label>合同编号：</label>			
						<input id="map.contractNum"  class="nui-textbox nui-form-input" name="map.contractNum"  />
						<label>贷款品种：</label>			
 						<input id="map.productType"  name="map.productType"  class="nui-combobox"    data="products"   />
					 
						
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
												
						<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
 						
					</div>
	
						<input name="map.partyId" class="nui-hidden nui-form-input" value="<%=request.getParameter("partyId")%>" />
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">追加保证金</a>
			<!-- <a class="nui-button" iconCls="icon-add" onclick="view()" id="addBtn">查看追加保证金记录</a> -->
 		</div>
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.conApply.conApply.getsupplyBzj.biz.ext" dataField="conInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
		        <div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
				<div field="CURRENCY_CD" dictTypeId="CD000001" headerAlign="center"width="50" align="center" >币种</div>
				<div field="CONTRACT_AMT" headerAlign="center" dataType="currency" align="right" >合同金额</div>
				<div field="CON_YU_E" headerAlign="center" dataType="currency" align="right" >合同已用金额</div>
 	 			<div field="BEGIN_DATE" headerAlign="center" align="center" >合同起期</div>
				<div field="END_DATE" headerAlign="center" align="center" >合同止期</div>
				<div field="BZJJE" headerAlign="center" dataType="currency" align="right" >保证金金额</div>
		    </div>
		</div>
	</div>
</fieldset>
</center>
<script type="text/javascript">
 		//var products = [{ id: '01008001', text: '银行承兑汇票' },{ id: '01008010', text: '北川富民银行承兑汇票' },{ id: '01008002', text: '银承通' }, { id: '01009001', text: '融资性保函'},{ id: '01009002', text: '非融资性保函'},{ id: '01007014', text: '国际保函'},{ id: '01007013', text: '信用证'}];
		//var products = [{ id: '01008001', text: '银行承兑汇票' },{ id: '01008010', text: '北川富民银行承兑汇票' },{ id: '01008002', text: '银承通' }, { id: '01009', text: '国内保函'},{ id: '01007014', text: '国际保函'},{ id: '01007013', text: '信用证'}];
		var products = [{ id: '01008001', text: '银行承兑汇票' },{ id: '01008002', text: '银承通' },{ id: '01008010', text: '富民银行承兑汇票' }];
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	var partyId="<%=request.getParameter("partyId")%>";
	query();
	function query(){
		var o = form.getData();
		grid.load(o);
					git.unmask();
		
	}
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i].CONTRACT_NUM='<a href="#" onclick="clickConNum(\''
   				+ e.data[i].CONTRACT_ID
   				+ '\');return false;" value="'
   				+ e.data[i].contractId
   				+ '">'+e.data[i].CONTRACT_NUM+'</a>';
   						
   				if(null !=e.data[i].partyName && ''!=e.data[i].partyName){
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
       			}else{
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
       			}
   				
   		}
    });
    
    function view(){
   		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔生效合同");
			return false;
		}
		nui.open({
            url: nui.context+"/crt/con_grt/con_bzj_list2.jsp?contractId="+row.CONTRACT_ID+"&subcontractTypeCd=03&partyId="+partyId+"&contractType=02&proFlag=0&xgbz=1",
            showMaxButton: true,
            title: "保证金追加历史记录",
            width: 900,
            height: 400,
            ondestroy: function(e) {
            	search();
            }
        });
	}
    
	//合同编号链接
	function clickConNum(contractId){
	 	var url=nui.context+"/crt/con_info/con_tree.jsp?contractId="+contractId+"&contractType=02&proFlag=-1";
	 	nui.open({
	            url:url,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
		//git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+contractId+"&contractType=02&proFlag=-1",parent);
	}
	
	 	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd;
             nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
            
	}
	 
		function reset() {
			form.reset();
			query();
		}
	 
	
	 
	function add(){
	debugger;
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔生效合同");
			return false;
		}
		//综合授信协议存在在途的调整不允许重复调整
		if(row.BIZ_TYPE == '综合授信协议'){
			var json = {"applyId":row.APPLY_ID};
	   	    var msg = exeRule("RCON_0024","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
		}else{//单笔
			//合同在途调整校验
			var json = {"amountDetailId":row.AMOUNT_DETAIL_ID};
			
	   	    var msg = exeRule("RCON_0026","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert("有在途合同调整或补足保证金流程");
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途综合授信调整或单笔批复调整时不得调整业务合同
	   	    json = {"contractId":row.CONTRACT_ID};
	   	    msg = exeRule("RCON_0039","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
	            nui.get("btnCreate").setEnabled(true);
		   		return "1";
	   	    }
	   	    //存在在途出账不得调整业务合同
   	        msg = exeRule("RCON_0040","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("btnCreate").setEnabled(true);
		   		return;
	   	    }
		}
		
		//规则校验：冻结批复不能调整
		json = {"applyId":row.APPLY_ID};
   	    msg = exeRule("RBIZ_0024","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return;
   	    }
   	   	//合同下有担保合同调整在途
   	    var json1 = {"contractId":row.CONTRACT_ID};
	   	msg = exeRule("RGRT_0005","1",json1);
	   	if(null != msg && '' != msg){
		   nui.alert(msg);
		   return;
	   	}
		nui.get("btnCreate").setEnabled(false);
		var json=nui.encode({"contractId":row.CONTRACT_ID,"bizType":row.BIZ_TYPE});
		$.ajax({
	        url: "com.bos.conApply.conApply.tzSupplybzjinfo.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	if(data.msg !=null){
            		nui.alert(data.msg); //失败时后台直接返回出错信息
            		nui.get("btnCreate").setEnabled(true);
            		return;
            	}
            	nui.get("btnCreate").setEnabled(true);
	            	nui.open({
		            url:nui.context+"/crt/con_info/supply_bzj_tree1.jsp?contractId="+data.conInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,
		            showMaxButton: true,
		            title: "",
		            width: 1024,
		            height: 768,
		            state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            },
		            ondestroy: function (action) {
		                query();
		            }
		        });	    
				//git.go(nui.context+"/crt/con_info/supply_bzj_tree.jsp?contractId="+data.conInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID+"&proFlag=1&processInstId="+data.processInstId,parent);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}

	
	
</script>
</body>
</html>
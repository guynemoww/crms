<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2015-5-19 8:26:27
  - Description:
-->
<head>
<title>白名单客户信息维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%; height: auto;">
<div title="白名单客户" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		
		<label>客户名称：</label>
		<input name="item.cusName" class="nui-textbox nui-form-input"/>
		
		<!-- <label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002" /> -->
		
		<label>ECIF客户编号：</label>			
		<input id="item.ecifPartyNum"  name="item.ecifPartyNum"  class="nui-textbox nui-form-input"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
	    <a class="nui-button"  iconCls="icon-search" onclick="queryInit()">查询</a>
	    <a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
	</div>
</div>

<div style="width:99.5%">
	<div class="nui-toolbar toolbar-add">
		<a id="zhunru"  class="nui-button" iconCls="icon-add" onclick="zhunru(0)" >准入</a>
	    <a id ="edit" class="nui-button" iconCls="icon-zoomin" onclick="zhunru(1)">移除</a>
<!-- 	    <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.natural.natural.getWhiteCustomerList.biz.ext" dataField="items"  allowAlternating="true"
	allowResize="true" showReloadButton="false"  
	sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn"></div>  
	        <div field="cusName" allowSort="true"headerAlign="center" >客户名称</div>
	        <div field="ecifPartyNum" allowSort="true" headerAlign="center" >ECIF客户编号</div>     
	        <div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
<!-- 			<div field="rate" allowSort="true" headerAlign="center" >利率</div> -->
			<div field="totalLimit"  allowSort="true" headerAlign="center" >最高额度</div>
			<div field="jobRank"  allowSort="true" headerAlign="center"  dictTypeId="XD_KHCD2004">职级</div>
			<div field="income"  allowSort="true" headerAlign="center" >收入</div>
			<div field="manageUser" dictTypeId="user" allowSort="true" headerAlign="center" >管户人</div>
			<div field="manageOrg"  allowSort="true" headerAlign="center" >管户机构</div>
			<div field="cusStatus"  allowSort="true" headerAlign="center" dictTypeId="XD_KHZT0001">客户状态</div>
	     </div>
</div>

</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");	
    queryInit();
    function queryInit() {
		var o = form.getData();//逻辑流必须返回total
		grid.load(o);
	} 
			function zhunru(v) {
			var flag="1";
	 		var rows=grid.getSelecteds();
	 		var json=nui.encode({"item":rows});
		if (v==1) {
		nui.confirm("确定要删除吗？","确认",function(action){
			if(action!="ok") return;
			var rows=grid.getSelecteds();
			//var row = grid.getSelected();
			if (rows.length <= 0) {
				alert("至少选择一条记录");
				return;
			}
				for(var i=0;i<rows.length;i++){
				if(rows[i].cusStatus!="02"){
				nui.alert("该客户还不是白名单客户不用移除");
						return;
				}
				}
			$.ajax({
		            url: "com.bos.csm.natural.natural.getNumberDelWhiteCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		          		create(text.msg,"whitdel",flag);
		          		if(flag=="1"){
		      	var url = "/csm/natural/white_natural_tree.jsp?number="
						+text.msg + "&qote=" + v+ "&del=" + v;
	
				nui.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "白名单流程",
							width : 1024,
							height : 768,
							state : "max",
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
								//alert(text);
							},
							ondestroy : function(action) {
								queryInit();
							}
						});
						}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		             	git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		           }
			});
			});
			}else{
			$.ajax({
		            url: "com.bos.csm.natural.natural.getNumberWhiteCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		          		create(text.msg,"whitadd",flag);
		          			if(flag=="1"){ 
		      	var url = "/csm/natural/white_natural_tree.jsp?number="
						+ text.msg + "&qote=" + v+ "&del=" + v;
				nui.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "白名单流程",
							width : 1024,
							height : 768,
							state : "max",
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
								//alert(text);
							},
							ondestroy : function(action) {
								queryInit();
							}
						});
						} 
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		             	git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		           }
			});
			}
		//生成32的添加白名单流程主键或者删除主键（删除时更新数据）
		}
		

	  /* grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				if(e.data[i]['cusStatus']!='01'){
				debugger;
				e.data[i]['cusName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['cusName']+'</a>';
				}
			}
		});  */

//Excel批量导入
 	function importExcel(){
		nui.open({
			url:nui.context + "/csm/natural/import_white_customer.jsp",
			title: "Excel 批量导入", 
			width: 800, 
			height: 500,
			allowResize:true,
			showMaxButton: true,
			ondestroy: function (action) {
				if(action=="ok"){
					queryInit();
				} 
			}
		});
 	}
 	
	function reset() {
		form.reset();
		queryInit();
	}

	function edit(v) {
	
		var row = grid.getSelected();
		debugger;
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		if(v=='2'){//失效
		var updateTime="<%=com.bos.pub.GitUtil.getBusiDate()%>";
		var json=nui.encode({"item":{"customerId":row.customerId,"cusStatus":"03","updateTime":updateTime}});
			$.ajax({
		            url: "com.bos.csm.natural.natural.updateWhiteCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		            	if(text.msg){
		            		alert(text.msg);
		            	} else {
		            		alert("保存成功!");
		            		queryInit();
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		             	git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		           }
			});
		}else{
			nui.open({
				url: nui.context + "/csm/natural/white_customer_edit.jsp?customerId="+ row.customerId + "&qote="+v+"",
				title: "客户信息", 
				width: 800, 
				height: 500,
				allowResize:true,
				showMaxButton: true,
				ondestroy: function (action) {
					if(action=="ok"){
						queryInit();
					} 
				}
			});
		}
	}


	function add() {
		nui.open({
			url : nui.context + "/csm/natural/natural_add.jsp",
			showMaxButton : true,
			title : "添加自然人",
			width : 820,
			height : 440,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
			},
			ondestroy : function(action) {
				queryInit();
			}
		});
	}
	//创建流程
	function create(applyId,biz,flag){
	      flag="1";
			var json=nui.encode({"applyId":applyId,"biz":biz});
	        $.ajax({
	            url: "com.bos.bizApply.bizApply.CreateAddWhiteProcess.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg !=null){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		flag="0";
	            		return;
	            	}
	
			        //面谈面签结束
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        }); 
	}
	//ECIF同步接口
	function SynchronizationEcif() {
		nui.confirm("确定要同步吗？","同步确认",function(action){
			if(action!="ok") return;
			var rows=grid.getSelecteds();
			//var row = grid.getSelected();
			if (rows.length <= 0) {
				alert("至少选择一条记录");
				return;
			}
			if(rows.length == 1){
				var json = nui.encode({"items":rows[0]});
			}else{
				var json = nui.encode({"items":rows});
			}
			git.mask();
			$.ajax({
				url : "com.bos.csm.inteface.ecif.synchronizationEcif1.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask();
					if (text.errMsg) {
						alert("ECIF提示信息："+text.errMsg);
					} else if(text.code=='AAAAAAA'){
						alert("同步成功!");
						queryInit();
					} 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask();
					nui.alert(jqXHR.responseText);
				}
			});
   		}); 
	}
	
</script>
</body>
</html>
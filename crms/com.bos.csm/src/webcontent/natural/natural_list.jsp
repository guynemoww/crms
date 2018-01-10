<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2015-5-19 8:26:27
  - Description:
-->
<head>
<title>自然人信息维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%; height: auto;">
<div title="自然人客户" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>客户编号：</label>
		<input name="item.partyNum" class="nui-textbox nui-form-input"/>
		
		<label>客户名称：</label>
		<input name="item.partyName" class="nui-textbox nui-form-input"/>
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002" />
		
		<label>证件号码：</label>			
		<input id="item.certNum"  name="item.certNum"  class="nui-textbox nui-form-input"/>
		
		<label>是否农户：</label>
		<input id="item.isFarmer" name="item.isFarmer" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
		
		<label>是否信贷客户：</label>
		<input id="item.isPotentialCust" name="item.isPotentialCust" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
		
		<label>手机号码：</label>			
		<input id="item.phoneNumber"  name="item.phoneNumber"  class="nui-textbox nui-form-input"/>
		
		<label>单位地址：</label>			
		<input id="item.unitAdress"  name="item.unitAdress"  class="nui-textbox nui-form-input"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
	    <a class="nui-button"  iconCls="icon-search" onclick="queryInit()">查询</a>
	    <a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
	</div>
</div>

<div style="width:99.5%">
	<div class="nui-toolbar toolbar-add">
		<a id="add"  class="nui-button" iconCls="icon-add" onclick="add()" >增加</a>
		<a id="editCust"  class="nui-button" iconCls="icon-edit" onclick="edit(2)" >编辑</a>
	    <a id ="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	    <!-- <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.natural.natural.getNaturalPersonList.biz.ext" dataField="items"  allowAlternating="true"
	allowResize="true" showReloadButton="false"  
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>  
	       	<div field="partyNum" allowSort="true"headerAlign="center">客户编号</div>
	        <div field="partyName" allowSort="true"headerAlign="center" >客户名称</div>
	        <div field="certType" allowSort="true" headerAlign="center" dictTypeId="XD_ZJLX0001">证件类型</div>       
	        <div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
	        <div field="isFarmer" allowSort="true" headerAlign="center" dictTypeId="YesOrNo">是否农户</div>
	        <div field="isPotentialCust" allowSort="true" headerAlign="center" dictTypeId="YesOrNo">是否信贷客户</div>
	        <div field="isThirdCust" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否第三方客户</div>
			<div field="thirdCustTypeCd" headerAlign="center"  allowSort="true" dictTypeId="XD_KHCD7001">第三方客户类型</div>
			<div field="phoneNumber" allowSort="true" headerAlign="center" >手机号码</div>
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
		
		//字典过滤，过滤掉202的证件类型
	    var arr = git.getDictDataUnFilter("CDKH0002",'202');
		nui.get("item.certType").setData(arr);
	}

	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
		});


	function reset() {
		form.reset();
		queryInit();
	}

	function edit(v) {
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		var url = nui.context + "/csm/natural/natural_tree.jsp?partyId="
				+ row.partyId + "&qote="+v+"&partyNum=" + row.partyNum;
		//编辑
		nui.open({
			url : url,
			showMaxButton : true,
			title : "客户信息",
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

	//ECIF同步接口
	function SynchronizationEcif() {
		var row = grid.getSelected();
		if(!row){
			return alert("请选择一条记录");
		}
		
		var json = nui.encode({
			"partyId" : row.partyId,
			"ecifPartyNum" : row.ecifPartyNum
		});
		git.mask();
		$.ajax({
			url : "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask();
				if (text.errMsg) {
					alert(text.errMsg);
				} else {
					if (text.ecifNum) {
						alert("同步成功!当前客户ECIF编号为:" + text.ecifNum);
					} else {
						alert("当前客户暂无ECIF信息");
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask();
				nui.alert(jqXHR.responseText);
			}
		});

	}
</script>
</body>
</html>
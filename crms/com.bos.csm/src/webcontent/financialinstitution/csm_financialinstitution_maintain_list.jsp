<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): cc
  - Date: 2015-4-27 10:58:51
  - Description: 同业客户维护
-->
<head>
<title>同业客户列表</title>
<%@include file="/common/nui/common.jsp" %>
</head> 

<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="同业客户" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">

	<div class="nui-dynpanel" columns="6">
			<label>区域类型：</label>
   	    	<input id="item.areaType" name="item.areaType" class="nui-dictcombobox" dictTypeId="XD_KHCD0210" required="false" />
			
			<label>客户名称：</label>
			<input id="item.partyName" class="nui-textbox nui-form-input" name="item.partyName"/>
			
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false"/>
   	    	
   	    	<label>营业执照：</label>
   	    	<input id="item.registercode" class="nui-textbox" name="item.registercode" vtype="maxLength:150">
   	    	
   	    	<label>组织机构代码：</label>
   	    	<input id="item.orgregistercd" class="nui-textbox" name="item.orgregistercd" vtype="maxLength:150">
   	      
   	        <label>swift码：</label>
   	        <input id="item.swiftBicNum" class="nui-textbox" name="item.swiftBicNum" vtype="maxLength:30">
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:none;" >
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>

<div style="width:99.5%">
<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px;">
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">增加</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit(2)">编辑</a>
		<a id = "query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<!-- <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
</div>
</div>    

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.financialinstitution.financialinstitutioninfo.getFinancialList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="PARTYNAME" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="UNIFYSOCIETYCREDITNUM" headerAlign="center" allowSort="true" >统一社会信用代码</div>
		<div field="AREATYPE" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0210" >区域类型</div>
		<div field="REGISTERCODE" headerAlign="center" allowSort="true" >营业执照</div>
		<div field="ORGREGISTERCD" headerAlign="center" allowSort="true" >组织机构代码</div>
		<div field="IS_POTENTIAL_CUST" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否信贷客户</div>
		<div field="SWIFTBICNUM" headerAlign="center" allowSort="true" >swift码</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");

    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				e.data[i]['PARTYNAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTYNAME']+'</a>';
			}
	});
   
    function reset(){
		form.reset();
	}
	
	/**
	*新增同业客户
	*/
	function add(){
		nui.open({
	            url: nui.context + "/csm/financialinstitution/csm_financialinstitution_keyinfo.jsp",
	            showMaxButton: true,
	            title: "新增客户信息",
	            width: 800,
	            height: 400,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
	            ondestroy: function (action) {
	                search();
	            }
      	 	 });
	}
    
	
	
	 //ECIF同步接口
     function SynchronizationEcif(){
     		 var row = grid.getSelected();
     		 if(!row){
     		 	return alert("请选择一条记录");
     		 }
     		 var json = nui.encode({"partyId":row.PARTY_ID});
	     	$.ajax({
				url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				if (text.errMsg) {
					alert(text.errMsg);
				} else {
					if (text.ecifNum) {
						alert("同步成功!当前客户ECIF编号为:" + text.ecifNum);
					} else {
						alert("同步成功!");
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask();
				nui.alert(jqXHR.responseText);
			}
		});
	}
	//编辑客户信息
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
	        var url = nui.context+"/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="+row.PARTY_ID+"&qote="+v+"&partyNum="+row.PARTYNUM;
			//修改
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "编辑客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                //search();
	            }
      	 	 });
        } else {
            alert("请选中一条记录");
        }
        
    }

</script>
</body>

</html>
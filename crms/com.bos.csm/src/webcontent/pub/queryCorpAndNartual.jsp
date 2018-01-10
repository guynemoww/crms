<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-8-26 16:17:00
  - Description:股东和对外股权投资选择自然人和公司页面
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" class="nui-form"style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.csm.pub.getNaturalAndCorp.getPartyList" />
	<div class="nui-dynpanel" id="table1" columns="4">
		<label>客户类型：</label>
		<input id="item.custType"name="item.custType" required="true" class="nui-dictcombobox nui-form-input" onvaluechanged="init" dictTypeId="XD_KHCD1001" />
		<label>客户名称：</label>
		<input id=item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
		</div>
		
		<div id="n_1" class="nui-dynpanel" columns="4">
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" />
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" />
			</div>  
		
		<div id="c_1" class="nui-dynpanel" columns="4">
		<label>营业执照：</label>
		<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" required="false"/>
		<label>组织机构代码：</label>
		<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input"/>
		<label>统一社会信用代码：</label>
		<input id="item.unifySocietyCreditNum"  name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input"/>
		</div>

	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true"  headerAlign="center" >CRMS客户编号</div> 
	        <div field="partyName" allowSort="true" width="20%" headerAlign="center" >客户名称</div> 
	       	<div field="unifySocietyCreditNum"name="unifySocietyCreditNum" allowSort="true" width="" headerAlign="center" >统一社会信用代码</div> 
	        <div field="registrCd" name="registrCd" allowSort="true" width="" headerAlign="center" >营业执照</div> 
	        <div field="orgRegisterCd" name="orgRegisterCd" allowSort="true" width="" headerAlign="center" >组织机构代码</div>
	        <div field="certType" name="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002" >证件类型</div> 
	        <div field="certNum" name="certNum" allowSort="true" width="" headerAlign="center" >证件号码</div> 
	       	<div field="orgNum" allowSort="true"  headerAlign="center" dictTypeId="org">机构名称</div> 
		    <div field="userNum" allowSort="true"  headerAlign="center" dictTypeId="user">管户客户经理</div>
		</div>
</div>

	<div id="tools" class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="selected">选中</a>
	    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
		

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	// 传递进来的股东类型作为隐藏参数
	var custType = "<%=request.getParameter("stockholderTypeCd") %>";
	if(custType!='null'){
		if(custType=="6"){//自然人
		 	nui.get("item.custType").setValue("02");
		}else{
			nui.get("item.custType").setValue("01");
		}
 		nui.get("item.custType").setEnabled(false);
 	}else{
 		nui.get("item.custType").setValue("01");
 		nui.get("item.custType").setEnabled(true);
 	}
    init();	
    function init(){
 	//字典过滤，过滤掉202的证件类型
	    var arr = git.getDictDataUnFilter("CDKH0002",'202');
		nui.get("item.certType").setData(arr);
		var arr = git.getDictDataFilter("XD_KHCD1001",'01,02');
		nui.get("item.custType").setData(arr);
		var custType=nui.get("item.custType").getValue();
	   	if(custType=="02"){//自然人
	   		grid.hideColumn(grid.getColumn("registrCd"));
	     	grid.hideColumn(grid.getColumn("orgRegisterCd"));
	     	grid.hideColumn(grid.getColumn("unifySocietyCreditNum"));
	     	grid.showColumn(grid.getColumn("certType"));
	     	grid.showColumn(grid.getColumn("certNum"));
	     	$("#c_1").css("display","none");
	     	$("#n_1").css("display","block");
	  	 }else{//对公
	  	 	grid.hideColumn(grid.getColumn("certType"));
	     	grid.hideColumn(grid.getColumn("certNum"));
		    grid.showColumn(grid.getColumn("unifySocietyCreditNum"));
	   		grid.showColumn(grid.getColumn("registrCd"));
	     	grid.showColumn(grid.getColumn("orgRegisterCd"));
	       	$("#c_1").css("display","block");
	     	$("#n_1").css("display","none");
	  	 }
	  	 nui.get("datagrid1").hide();
	  	 nui.get("tools").hide();
	}
    function query(){
    	form.validate();
        if (form.isValid()==false){
        	 return alert("请选择客户类型");
        }
       var o = form.getData();
       grid.load(o);
       nui.get("datagrid1").show();
	   nui.get("tools").show();
    }
    
    function selected() {
      var row = grid.getSelected();
        if (row) {
            CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
    
    function getData(){
    var row = grid.getSelected();
      if (row) {
            return row;
        } else {
            return null;
        }
    }
    
    function reset(){
		form.reset();
		nui.get("item.custType").setValue(stockHolderTypeCd);
		query();
	}
</script>
</body>
</html>
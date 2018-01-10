<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-12-10 16:17:00
  - Description:他项权证选择抵质押人
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;" >
	<input id="item.partyId" name="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>客户类型：</label>			
		<input id="item.partyTypeCd" name="item.partyTypeCd" class="nui-combobox" data="partyTypeCd" value='01' onvaluechanged="changePartyType"/>
		
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
	</div>
	<div id="companyInfo" class="nui-dynpanel" columns="4">
		<label>统一社会信用代码：</label>
		<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" />
		
		<label>营业执照：</label>
		<input id="item.registrCd" name="item.registrCd" class="nui-textbox nui-form-input" />
		     			
		<label>组织机构代码：</label>			
		<input id="item.orgRegisterCd" name="item.orgRegisterCd" class="nui-textbox nui-form-input"  />
	</div>
		
	<div id="naturePersonInfo" class="nui-dynpanel" columns="4">	
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid" dictTypeId="CDKH0002"  allowInput="false" />
		     			
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input"   />
	</div>


<div style="width:99.5%">
	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.grt.grtMainMargin.grtPublic.queryCusParty.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true"  headerAlign="center" >CRMS客户编号</div>                
	        <div field="partyName" allowSort="true"  width="20%" headerAlign="center" >客户名称</div> 
	        <div field="unifySocietyCreditNum" allowSort="true"  headerAlign="center" >统一社会信用代码</div> 
	        <div field="registrCd" allowSort="true"  headerAlign="center" >营业执照</div> 
	        <div field="orgRegisterCd" allowSort="true"  headerAlign="center" >组织机构代码</div> 
	       	<div field="orgNum" allowSort="true"  headerAlign="center" dictTypeId="org">机构名称</div> 
		    <div field="userNum" allowSort="true"  headerAlign="center" dictTypeId="user">管户客户经理</div>  
	     </div>
</div>

<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.grt.grtMainMargin.grtPublic.queryCusParty.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true"  headerAlign="center" >CRMS客户编号</div>               
	        <div field="partyName" allowSort="true"  width="20%" headerAlign="center" >客户名称</div> 
	        <div field="certType" allowSort="true"  headerAlign="center" dictTypeId="CDKH0002" >证件类型</div> 
	        <div field="certNum" allowSort="true"  headerAlign="center" >证件号码</div>
	       	<div field="orgNum" allowSort="true"  headerAlign="center" dictTypeId="org">机构名称</div> 
		    <div field="userNum" allowSort="true"  headerAlign="center" dictTypeId="user">管户客户经理</div>  
	     </div>
</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    <a class="nui-button"  onclick="selected()">确认</a>
	</div>
</div>

<script type="text/javascript">
	var partyTypeCd = [{ id: '01', text: '对公客户' }, { id: '02', text: '自然人'}];
	nui.parse();
	var partyId="<%=request.getParameter("partyId")%>";
	nui.get("item.partyId").setValue(partyId);
	// 传递进来的股东类型作为隐藏参数

	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var grid2 = nui.get("datagrid2");
	
	nui.get("datagrid2").hide();
    
    //query();
    
    function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.certType").setData(text.levels);
	            custFlag = true;
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    //init();	
    
    function query(){
    	var partyTypeCd = nui.get("item.partyTypeCd").getValue();
    	if(partyTypeCd=='02'){
    		query2();
    		return;
    	}
 		git.mask();
    	form.validate();
        if (form.isValid()==false){
        	 git.unmask(); 
        	 return;
        }
        
       var o = form.getData();
       grid.load ( o, function (text) {
	    	if(text.msg){
	    		nui.alert(text.msg);
	    	} else {
	    		nui.get("datagrid1").show();
				nui.get("dataConfirm").show();
	    	}
	    } );
       git.unmask();
    }
    
      function query2(){
 		git.mask();
    	form.validate();
        if (form.isValid()==false){
        	 git.unmask(); 
        	 return;
        }
        
       var o = form.getData();
       grid2.load ( o, function (text) {
	    	if(text.msg){
	    		nui.alert(text.msg);
	    	} else {
	    		nui.get("datagrid2").show();
				nui.get("dataConfirm").show();
				
	    	}
	    } );
       git.unmask();
    }
    
    function selected() {
    	var partyTypeCd = nui.get("item.partyTypeCd").getValue();
    	var row=null;
    	if(partyTypeCd=='01'){
    		row = grid.getSelected();
    	}else{
    		row = grid2.getSelected();
    	}
        if (row) {
           	CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
    
    function getData(){
    	var row=null;
    	var partyTypeCd = nui.get("item.partyTypeCd").getValue();
    	if(partyTypeCd=='01'){
    		row = grid.getSelected();
    	}else{
    		row = grid2.getSelected();
    	}
      if (row) {
            return row;
        } else {
            return null;
        }
    }
    
    function reset(){
		form.reset();
		//nui.get("item.partyTypeCd").setValue(stockHolderTypeCd);
		query();
	}
    
    
    function changePartyType(){
    	var partyTypeCd = nui.get("item.partyTypeCd").getValue();
		if(partyTypeCd == '01'){
			$("#naturePersonInfo").css("display","none");
			$("#companyInfo").css("display","block");
			//nui.get("item.registrCd").setValue("");//对公 清除证件号码数据 防止查询有误
			//nui.get("item.orgRegisterCd").setValue("");
			
			if(nui.get("datagrid2")){
				nui.get("datagrid2").hide();
				nui.get("datagrid1").show();
				query();
			}
		}else{
			init();
			//nui.get("item.certType").setValue("");//对公 清除证件号码数据 防止查询有误
			//nui.get("item.certNum").setValue("");
			nui.get("datagrid1").hide();
			nui.get("datagrid2").show();
			$("#naturePersonInfo").css("display","block");
			$("#companyInfo").css("display","none");
			query2();
		}
    }
</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-25 16:17:00
  - Description:联保小组引入自然人
  成员
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
<%@page
	import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;" >
	<input name="item.partyTypeCd" id="item.partyTypeCd" class="nui-hidden" value="04"/>
	<div class="nui-dynpanel" columns="4">
		<label>客户类型：</label>
		<input id=item.partyType" name="item.partyType"  dValue="02"class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001" enabled="false"/>
	
		<label>客户名称：</label>
		<input id=item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
		     			
		<label>证件号码：</label>			
		<input id="item.certCode" name="item.certCode" class="nui-textbox nui-form-input"   />
	
	</div>
</div>

<div style="width:100%">
	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>


<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.getNaturalAndCorp.getCorpAndNatural2.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >CRMS客户编号</div> 
<!-- 	        <div field="ecifPartyNum" allowSort="true" width="20%" headerAlign="center" >ECIF客户编号</div>     -->
	        <div field="partyName" allowSort="true" width="" headerAlign="center" >客户名称</div> 
	        <div field="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002" >证件类型</div> 
	        <div field="certCode" allowSort="true" width="" headerAlign="center" >证件号码</div> 
<!-- 	        <div field="partyTypeCd" allowSort="true" width="" headerAlign="center" dictTypeId="XD_KHCD1001" >客户类型</div> -->
	     	 <div field="orgNum" allowSort="true" width="" headerAlign="center" dictTypeId="org" >机构名称</div> 
	     	 <div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user" >管户客户经理</div> 
	     </div>
</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    <a class="nui-button"  onclick="selected()">选中</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	
	// 传递进来的股东类型作为隐藏参数
	var stockHolderTypeCd = "<%=request.getParameter("stockholderTypeCd") %>";
	nui.get("item.partyTypeCd").setValue(stockHolderTypeCd);
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	
	grid.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
			if(e.data[i]['partyName']==null){
				e.data[i]['partyName'] = e.data[i]['englishName'];
			}
		}
	});
    
    
    function query(){
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
	    		//alert("123");
	    		nui.get("datagrid1").show();
				nui.get("dataConfirm").show();
	    	}
	    } );
       git.unmask();
    }
    
    function selected() {
      var row = grid.getSelected();
        if (row) {
           /*  if(!row.contryRegionCd||!row.certType||!row.certCode||!row.partyTypeCd){
            	alert("此客户必要信息不全,请重新选择");
				return;
            }else{ */
            	CloseWindow("ok");
            /* } */
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
		nui.get("item.partyTypeCd").setValue(stockHolderTypeCd);
		query();
	}
    
</script>
</body>
</html>
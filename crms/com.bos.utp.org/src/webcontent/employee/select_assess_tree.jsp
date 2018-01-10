<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>评估机构</title>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
</head>
<body>
<div id="form1" style="width:80%;height:auto;overflow:hidden;" >
	<input id="partyId" name="partyId" class="nui-hidden"  />
	<div id="naturePersonInfo" class="nui-dynpanel" columns="4">	
		<label>评估机构名称：</label>
		<input id="partyName" name="partyName" class="nui-textbox nui-form-input"  />
		     			
		<label>机构号码：</label>			
		<input id="orgRegisterCd" name="orgRegisterCd" class="nui-textbox nui-form-input"   />
	</div>
</div>

</div style="width:99.5%">
	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
<div>
<!-- com.bos.utp.org.organization.queryAllAssessOrg -->
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.utp.org.organization.queryAllAssessOrg.biz.ext" dataField="arrays"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center" >评估机构名称</div> 
	        <div field="ORG_REGISTER_CD" allowSort="true" width="" headerAlign="center" >机构号码</div> 
	     </div>
</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    <a class="nui-button"  onclick="selected()">选中</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	
	var form = new nui.Form("#form1");
	
	query();
	
	
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
	    		nui.get("datagrid1").show();
				nui.get("dataConfirm").show();
			}
	    } );
       git.unmask();
    }
    
    function getData(){
    	var row=null;
    	row = grid.getSelected();
	      if (row) {
	            return row;
	        } else {
	            return null;
	        }
    }
    
    function reset(){
		form.reset();
		query();
	}
	
	function selected() {
    	row = grid.getSelected();
        if (row) {
           	CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
</script>

</body>
</html>
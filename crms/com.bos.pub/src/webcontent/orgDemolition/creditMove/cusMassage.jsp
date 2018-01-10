<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 
  - Description:
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<br/>
<div align="center">
<div id="form1" style="width:90%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="6">
			<label>所属机构：</label>
			<input id="orgNum" name="orgNum" allowInput="false" class="nui-buttonEdit" style value="<%=request.getParameter("oldOrgNum") %>" dictTypeId="org" enabled="false"/>
			<label>所属用户：</label>
			<input id="" name="userNum" allowInput="false" class="nui-buttonEdit"  value="<%=request.getParameter("oldUserNum") %>" dictTypeId="user" enabled="false"/>
			<label>客户名称：</label>
			<input id="" name="partyName"  class="nui-textbox"/>
			
		</div>
</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-search" onclick="query()">查询</a>
    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" iconCls="icon-save" id="dataConfirm"  onclick="selected()">保存</a>
</div>


<br />


<div id="datagrid1"    class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.orgDemolition.getCustomter.biz.ext" dataField="cus"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false" pageSize="20"
	    sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true" width="20%" headerAlign="center" >客户名称</div>                
	        <div field="orgNum" allowSort="true" width="" headerAlign="center" dictTypeId="org">所属机构</div> 
	        <div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">所属用户</div>
	         <div field="userPlacingCd" allowSort="true" width="" headerAlign="center" dictTypeId="CsmTeamAuthorityType">管理权限</div>      
	        
	     </div>
	</div>
<!-- 
<div id="dataConfirm"  class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  onclick="selected()">选中</a>
</div>
 -->

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	

    
    function query(){
    git.mask();
 var orgNum;
orgNum=nui.get("orgNum").getValue();
if(orgNum==""){
alert("请选中机构");
return;

}else{
    	form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
        grid.load ( o, function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		//alert("123");
            		nui.get("datagrid1").show();
					nui.get("dataConfirm").show();
					git.unmask();
            	}
            } );
       }
    
    }
   query(); 
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
    
    
  </script>
  
  
</body>
</html>
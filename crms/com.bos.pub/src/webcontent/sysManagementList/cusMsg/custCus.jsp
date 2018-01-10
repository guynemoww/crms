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
<div id="form1" style="width:50%;height:auto;overflow:hidden;" >
<input id="oldOrgNum"  class="nui-hidden"   />
		<div class="nui-dynpanel" columns="2">
			<label>客户名称：</label>
			<input id="orgNum" name="partyName"  class="nui-textbox nui-form-input" />
			<label>组织机构代码：</label>
			<input id="userNum" name="orgnNum"  class="nui-textbox nui-form-input" />
		</div>
</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"   onclick="query()">查询</a>
    <a class="nui-button"   onclick="CloseWindow()">返回</a>
</div>


<br />


<div id="datagrid1"    class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.sysManagementList.getcustCus.biz.ext" dataField="cus"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true" width="20%" headerAlign="center" >客户姓名</div>                
	        <div field="certificateCode" allowSort="true" width="" headerAlign="center" >组织机构代码</div>
	     </div>
	</div>
<div id="dataConfirm"  class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  onclick="selected()">选中</a>
</div>


<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	

    
    function query(){
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
					
            	}
            } );
    
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
    
   
  </script>
  
  
</body>
</html>
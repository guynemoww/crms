<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 
  - Description:
-->
<head>
<title>查询客户经理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.customerSelect" />
		<div class="nui-dynpanel" columns="4">
			<label>所属机构：</label>
			<input id="item.orgId" name="item.orgCode" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" value="<%=request.getParameter("oldOrgNum") %>" dictTypeId="org" enabled="false"/>
			<!-- 
			<label>用户编号：</label>
			<input id="" name="teamItem.empId"  class="nui-textbox"/>
			-->
			<label>用户名称：</label>
			<input id="" name="item.empName"  class="nui-textbox"/>
		</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" iconCls="icon-search"   onclick="query()">查询</a>
    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" iconCls="icon-save" id="dataConfirm"  onclick="selected()">保存</a>
	</div>
</div>


<!--  
<div id="datagrid1"    class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="teamItems"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false" pageSize="10"
	    sizeList="[10,20,50,100]">
	    <input id="groupTeams" class="nui-hidden nui-form-input" name="groupTeams"/>
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="userId" allowSort="true" width="20%" headerAlign="center" >用户编号</div>                
	        <div field="empName" allowSort="true" width="" headerAlign="center" >用户名称</div> 
	        <div field="orgCode" allowSort="true" width="" headerAlign="center" dictTypeId="org">机构名称</div>   
	        <div field="post" allowSort="true" width="" headerAlign="center" >岗位名称</div>  
	     </div>
	</div>
-->	
	
	<div id="datagrid1"  class="nui-datagrid" style="width:99.5%;height:auto;"sortMode="client"
		url="com.bos.csm.pub.ibatis.getItem.biz.ext"
		dataField="items"allowAlternating="true" 
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client" onselectionchanged="" >
	<div property="columns">
			<div type="checkcolumn">选择</div>
	        <div field="userId" allowSort="true"  headerAlign="center" >用户编号</div>                
	        <div field="empName" allowSort="true" headerAlign="center" >用户名称</div> 
	        <div field="orgCode" allowSort="true" width="25%" headerAlign="center" dictTypeId="org">机构名称</div>   
	        <div field="post" allowSort="true" width="35%" headerAlign="center" >岗位名称</div> 
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
	
function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
    function query(){
   		 git.mask();
		var orgId;
		orgId=nui.get("item.orgId").getValue();
		if(orgId==""){
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
    
    query();
  </script>
  
  
</body>
</html>
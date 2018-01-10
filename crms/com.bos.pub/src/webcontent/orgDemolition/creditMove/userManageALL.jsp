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
<br/>
<div align="right">
<div id="form1" style="width:90%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="4">
			<label>用户名称：</label>
			<input id="" name="teamItem.empName"  class="nui-textbox"/>
		</div>
</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search"   onclick="query()">查询</a>
    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" iconCls="icon-save" id="dataConfirm"  onclick="selected()">保存</a>
</div>


<br />


<div id="datagrid1"    class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.orgDemolition.queryUser.biz.ext" dataField="teamItems"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false" pageSize="20"
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
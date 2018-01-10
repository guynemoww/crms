<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 夏群
  - Date: 2013-12-10 16:17:00
  - Description:
-->
<head>
<title>查询客户经理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div align="center">
<div id="form1" style="width:50%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="2">
			<label>所属机构：</label>
			<input id="teamItem.orgCode" name="teamItem.orgCode" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
			
			<label>用户名称：</label>
			<input id="teamItem.empName" name="teamItem.empName" class="nui-textbox nui-form-input" />
			
			<label>用户编号：</label>
			<input id="teamItem.userId" name="teamItem.userId"  class="nui-textbox nui-form-input" />
			
			<%--<label>用户岗位：</label>
			<input id="teamItem.post" name="teamItem.post" data="data" valueField="dictID" textField="dictName" 
				class="nui-dictcombobox nui-form-input" value="" dictTypeId="CustManagerPost" />--%>
		</div>
		
</div>
</div>

<div style="width:99.5%">
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0" >
		    <a class="nui-button"   onclick="query()">查询</a>
		    <a class="nui-button"   onclick="CloseWindow()">返回</a>
		</div>
</div>


<div id="datagrid1"    class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.corporation.corporation.queryCustManagerTeam.biz.ext" dataField="teamItems"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="userId" allowSort="true" width="20%" headerAlign="center" >用户编号</div>                
	        <div field="empName" allowSort="true" width="" headerAlign="center" >用户名称</div> 
	        <div field="orgCode" allowSort="true" width="" headerAlign="center">机构名称</div>   
	        <div field="post" allowSort="true" width="" headerAlign="center" >岗位名称</div>  
	     </div>
	</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="text-align:right;border:0px;padding-right:20px;" >
	    <a class="nui-button"  onclick="selected()">选中</a>
	</div>
</div>
</center>
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
       git.unmask();
    
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
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
<br/>
<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="6">
			<label>所属机构：</label>
			<input id="teamItem.orgCode" name="teamItem.orgCode" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
			
			<label>用户名称：</label>
			<input id="teamItem.empName" name="teamItem.empName" class="nui-textbox nui-form-input" />
			
			<label>用户编号：</label>
			<input id="teamItem.userId" name="teamItem.userId"  class="nui-textbox nui-form-input" />
		<%--	
			<label>用户岗位：</label>
			<input id="teamItem.post" name="teamItem.post" data="data" valueField="dictID" textField="dictName" 
				class="nui-dictcombobox nui-form-input" value="" dictTypeId="CustManagerPost" />--%>
		</div>
</div>
<div style="99.5%">
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px" >
		    <a class="nui-button" iconCls="icon-search"  onclick="query()">查询</a>
		    <a class="nui-button"  iconCls="icon-reset" onclick="CloseWindow()">返回</a>
	</div>
</div>




<div id="datagrid1"    class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.guar.guarGroup.queryGroupCustCustManagerTeam.biz.ext" dataField="teamItems"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="15">
	    <input id="groupTeams" class="nui-hidden nui-form-input" name="groupTeams"/>
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="userId" allowSort="true" width="20%" headerAlign="center" >用户编号</div>                
	        <div field="empName" allowSort="true" width="" headerAlign="center" >用户名称</div> 
	        <div field="orgCode" allowSort="true" width="" headerAlign="center" dictTypeId="org" >机构名称</div>   
	        <%--<div field="post" allowSort="true" width="" headerAlign="center" >岗位名称</div>  --%>
	     </div>
	</div>
<div style="99.5%">	
<div id="dataConfirm"  class="nui-toolbar" style="text-align:right;border:0;padding-right:20px" >
    <a class="nui-button"  onclick="selected()">选中</a>
</div>
</div>

<script type="text/javascript">
	nui.parse();

	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	
	var userNum  = "<%=request.getParameter("userNum") %>";
	var orgName  = "<%=request.getParameter("orgName") %>";
	var userName = "<%=request.getParameter("userName") %>";
	var orgNum = "<%=request.getParameter("orgNum") %>";
	
	function initFormValue(){
		if(userName!="null"){
			nui.get("teamItem.empName").setValue(userName);
		}else if(orgName!="null"){
			nui.get("teamItem.orgCode").setValue(orgNum);
			nui.get("teamItem.orgCode").setText(orgName);
		}else if(userNum!="null"){
			nui.get("teamItem.userId").setValue(userNum);
		}
		query();
	}
	initFormValue();
	
		
function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/orgDemolition/creditMove/select_all_org_tree.jsp",
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
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
    function query(){
 		//_alert(nui.get("teamItem.orgCode").getValue());
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
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%
	String orgMng = ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_orgMng");
%>
<head>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/utp/org/icons/icon.css"/>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js"></script>
</head>
<body>
  <div class="list">
    <div id="form1" style="padding:5px;">
	  <table style="width:100%;table-layout:fixed;" id="table1" class="table" >
		<tr>
			<td class="tit">人员姓名：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[1].empname" style="width:80%" vtype="maxLength:50"/>
				<input class="nui-hidden" name="criteria._expr[1]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all"/>
			</td>
			<td class="tit">人员代码：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[2].empcode" style="width:80%" vtype="maxLength:30"/>
				<input class="nui-hidden" name="criteria._expr[2]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[2]._likeRule" value="all"/>
			</td>
			<td rowspan="2" class="btn-wrap">
				<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
			</td>
		</tr>		
		<tr>
			<td class="tit">用户登录名：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[3].userid" style="width:80%" vtype="maxLength:30"/>
				<input class="nui-hidden" name="criteria._expr[3]._op" value="="/>
			</td>
			<td class="tit">所属机构：</td>
			<td>
				<input name="criteria._expr[4].orgid" textName="orgname" class="nui-buttonedit searchbox" onbuttonclick="selectOrg" style="width:80%" allowinput="false"/>
				<input class="nui-hidden" name="criteria._expr[4]._op" value="="/>
			</td>
		</tr>				
	  </table>
    </div>
  </div>
</div>
<div class="nui-fit" style="padding:5px;">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;" 
	    url="com.bos.git.crm.app.marketmg.interview.InterviewInput.queryEmployee.biz.ext" dataField="treeNodes" onrowdblclick="ok" sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn"></div>                
	        <div field="empname" width="120" headerAlign="center" allowSort="true">人员姓名</div>    
	        <div field="empcode" width="120" headerAlign="center" allowSort="true">人员代码</div>    
	     </div>
	</div>
</div>
<div id="toolBar" class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="ok">确定</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;" onclick="cancel">关闭</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
    
    grid.load();
	
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
        grid.load(formData);
	}
	
	function resetForm(){
		form.reset();
	}
	var bootPath = "<%=request.getContextPath() %>";
	
	 //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 350,
            ondestroy: function(action){
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
	
	function reset() {
		form.reset();
	}
	
	var data = {};
	
	 function ok(){
	   CloseWindow("ok");
    }
    
    function GetData(){
    	var row = grid.getSelected();
	 	if(row){
	 		data = {
	 			id:row.empid,
	 			text:row.empname
	 		}
	 	}
    	return data;
    }
    
    function cancel() {
    	CloseWindow("cancel");
    }
</script>

</body>
</html>

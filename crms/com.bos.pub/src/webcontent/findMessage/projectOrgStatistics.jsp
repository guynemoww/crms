<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-19
  - Description:TB_PUNISH_MESSAGE, com.bos.pub.sys.TbPunishMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" >
	
	<div class="nui-dynpanel" columns="6">

		<label>计分人对象机构：</label>
		<input  name="map/scoreOrgNumber" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />

		<label>计分人机构：</label>
		<input  name="map/orgNum" required="false"  class="nui-buttonEdit"  onbuttonclick="selectEmpOrg"/>
		<label>计分项目名称：</label>
		<input id="projectName" name="map/scoreProjectName"  required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>计分事项：</label>
		<input id="projectMatter" name="map/scoreMatter" required="false" class="nui-TextArea" vtype="maxLength:60"/>
		
	</div>
 </form>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.openOrder.projectOrgStatistics.biz.ext"
	dataField="projectOrgs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="SCORE_ORG_NAME" headerAlign="center" allowSort="true" >计分机构</div>
		<div field="peonum" headerAlign="center" allowSort="true" >人数</div>
		<div field="ordernum" headerAlign="center" allowSort="true" >单子数</div>
		<div field="shouldscoring" headerAlign="center" allowSort="true" >应计分</div>
		<div field="realscoring" headerAlign="center" allowSort="true" >累计计分</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
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
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
      //导出
    function exportEmp()
    {
     var forms = document.getElementById("form1");
		     forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=08";
		     forms.submit();
    }
	</script>
</body>
</html>

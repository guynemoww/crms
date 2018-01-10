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

		<label>计分人姓名：</label>
		<input id="xingming" name="tbPunishMessage/scoreName" class="nui-textbox nui-form-input"  />

		<label>计分人工号：</label>
		<input id="gonghao" name="tbPunishMessage/scoreNumber" required="false"  class="nui-textbox nui-form-input"  />

		<label>计分人机构名称：</label>
		<input id="orgName" name="tbPunishMessage/scoreOrgNumber" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />

		<label>处罚措施：</label>
		<input name="tbPunishMessage/punishMeasure" required="false" class="nui-textarea nui-form-input" vtype="maxLength:60" />

	</div>
 </form>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.openOrder.getPunishStatistics.biz.ext"
	dataField="tbPunishMessages"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="SCORE_ORG_NAME" headerAlign="center" allowSort="true" >计分人机构</div>
		<div field="SCORE_NUMBER" headerAlign="center" allowSort="true" >计分人工号</div>
		<div field="SCORE_NAME" headerAlign="center" allowSort="true" >计分人姓名</div>
		<div field="PUNISH_MEASURE" headerAlign="center" allowSort="true" >处罚措施</div>
		<div field="PUNISH_OPINION" headerAlign="center" allowSort="true" >意见</div>
		<div field="ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="USER_NUM" headerAlign="center" allowSort="true" dictTypeId="user">经办用户</div>
		<div field="ORG_TIME" headerAlign="center" allowSort="true">经办时间</div>
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
	
    
   function selectCustManeger(e) {
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
        function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "pub/findMessage/punishXiangQing.jsp?punishId="+row.PUNISH_ID+"&view="+v,
                title: "编辑", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
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
		     forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=07";
		     forms.submit();
    }
	</script>
</body>
</html>

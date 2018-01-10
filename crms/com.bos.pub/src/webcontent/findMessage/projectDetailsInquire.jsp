<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-10
  - Description:TB_ORDER_MESSAGE, com.bos.pub.sys.TbOrderMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" style="width:150%;height:auto" >
	
	<div class="nui-dynpanel" columns="6">
	
		<label>计分人姓名：</label>
		<input name="tbOrderMessage/scoreName" required="false" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectCustManeger"  />

		<label>计分人工号：</label>
		<input name="tbOrderMessage/scoreNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>计分人机构名称：</label>
		<input name="tbOrderMessage/scoreOrgName" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />


		<label>应计分：</label>
		<input name="tbOrderMessage/shouldTheScoring" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>状态：</label>
		<input name="tbOrderMessage/state" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_ZT002"   />

		<label>经办日期：</label>
		<input name="tbOrderMessage/time" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>是否双倍计分：</label>
		<input name="tbOrderMessage/whetherDoubleScore" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_0002"/>

		<label>实计分：</label>
		<input name="tbOrderMessage/realScoring" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>计分人部门：</label>
		<input name="tbOrderMessage/scoreDepartment" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_BM0023" emptyText="请选择"/>

	</div>
 </form>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;width:150%;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
</div>
	   <div class="nui-toolbar" style="border-bottom:0;width:150%;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
</div> 
<div id="grid1" class="nui-datagrid" style="width:150%;height:auto" 
	url="com.bos.pub.openOrder.getTbOrderMessageList.biz.ext"
	dataField="tbOrderMessages"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		
		<div field="scoreName" headerAlign="center" allowSort="true" >计分人姓名</div>
		<div field="scoreNumber" headerAlign="center" allowSort="true" >计分人工号</div>
		<div field="scoreOrgName" headerAlign="center" allowSort="true" >计分人机构名称</div>
		<div field="scoreOrgNumber" headerAlign="center" allowSort="true" >计分人机构编号</div>
		<div field="scoreProjectName" headerAlign="center" allowSort="true" >计分项目名称</div>
		<div field="scoreMatter" headerAlign="center" allowSort="true" >计分事项</div>
		<div field="scoreDepartment" headerAlign="center" allowSort="true" dictTypeId="XD_BM0023" >计分人部门</div>
		<div field="shouldTheScoring" headerAlign="center" allowSort="true" >应计分</div>
		<div field="realScoring" headerAlign="center" allowSort="true" >实计分</div>
		<div field="specificConditionDescribe" headerAlign="center" allowSort="true" >具体情况描述</div>
		<div field="state" headerAlign="center" allowSort="true" dictTypeId="XD_ZT002">状态</div>
		<div field="time" headerAlign="center" allowSort="true" >经办日期</div>
		<div field="userNum" headerAlign="center" allowSort="true" >经办用户工号</div>
		<div field="orgPeopleName" headerAlign="center" allowSort="true" dictTypeId="user">经办人姓名</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="whetherDoubleScore" headerAlign="center" allowSort="true" dictTypeId="XD_0002" >是否双倍计分</div>
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
	function edit(v) {
        var row = grid.getSelected();
        if (row) {
		              nui.open({
		                url: nui.context +"/pub/openOrder/item_edit.jsp?scoreMessageId="+row.scoreMessageId+"&view="+v,
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
       
    function selectCustManeger(e) {
     var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/openOrder/customer.jsp",
            showMaxButton: true,
            title: "选择接收人",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                     //   alert(nui.encode(data));
                    	  btnEdit.setValue(data.empName);
                        btnEdit.setText(data.empName);
                    }
                }
            }
        });   
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
		     forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=06";
		     forms.submit();
    }   

	</script>
</body>
</html>

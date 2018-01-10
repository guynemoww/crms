<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-17
  - Description:TB_ORDER_MESSAGE, com.bos.pub.sys.TbOrderMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		<label>计分人机构名称：</label>
		<input name="map/scoreOrgNumber" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />
		<label>计分人部门：</label>
		<input name="map/scoreDepartment" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_BM0023" emptyText="请选择"/>
		<label>计分人工号：</label>
		<input name="map/scoreNumber" required="false"  class="nui-textbox nui-form-input"  />
		<label>计分人姓名：</label>
		<input  name="map/scoreName" required="false"  class="nui-textbox nui-form-input" />
	</div>
 </form>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
</div>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.openOrder.projectStatistics.biz.ext"
	dataField="projectStatistics"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="PROJECT_NAME" headerAlign="center" allowSort="true" >计分项目名称</div>
		<div field="onemonth" headerAlign="center" allowSort="true" >一月份累计计分</div>
		<div field="twomonth" headerAlign="center" allowSort="true" >二月份累计计分</div>
		<div field="threemonth" headerAlign="center" allowSort="true" >三月份累计计分</div>
		<div field="fourmonth" headerAlign="center" allowSort="true" >四月份累计计分</div>
		<div field="fivemonth" headerAlign="center" allowSort="true" >五月份累计计分</div>
		<div field="sixmonth" headerAlign="center" allowSort="true" >六月份累计计分</div>
		<div field="sevenmonth" headerAlign="center" allowSort="true" >七月份累计计分</div>
		<div field="eightmonth" headerAlign="center" allowSort="true" >八月份累计计分</div>
		<div field="ninemonth" headerAlign="center" allowSort="true" >九月份累计计分</div>
		<div field="tenmonth" headerAlign="center" allowSort="true" >十月份累计计分</div>
		<div field="elevenmonth" headerAlign="center" allowSort="true" >十一月份累计计分</div>
		<div field="twelvemonth" headerAlign="center" allowSort="true" >十二月份累计计分</div>

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
		     forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=04";
		     forms.submit();
    }
     
	</script>
</body>
</html>

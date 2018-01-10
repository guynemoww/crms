<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-3-17
  日志查询
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>日志查询</title>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>日志类型：</label>
		<input name="log_type" class="nui-dictcombobox nui-form-input" dictTypeId="pub_log_type" />
		<label>经办日期：</label>
		<div colspan="1" >
			<input name="create_dbtime0" class="nui-datepicker nui-form-input"     style="width:100px;" />
			<input name="create_dbtime9" class="nui-datepicker nui-form-input"     style="width:100px;" />
		</div>
		<label>经办人：</label>
		<input id="create_user" name="create_user" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/>

		<label>经办机构：</label>
		<input id="create_org" name="create_org" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" onclick="reset()">重置</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.pub.log.getLogList.biz.ext" dataField="logs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn">选择</div>
		<div field="USER_NUM" headerAlign="center" name="xxxx" allowSort="true" dictTypeId="user">经办人</div>
		<div field="ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="CREATE_DBTIME" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">经办时间</div>
		<div field="LOG_IP" headerAlign="center" allowSort="true" >客户端IP</div>
		<div field="LOG_TYPE" headerAlign="center" allowSort="true" dictTypeId="pub_log_type" autoEscape="false">日志类型</div>
		<%--<div field="LOG_TYPE_HTML" headerAlign="center" allowSort="true" autoEscape="false">日志类型</div>--%>
		<div field="LOG_DESC" headerAlign="center" allowSort="true">日志描述</div>
		<%--<div field="BIZ_TYPE" headerAlign="center" allowSort="true" dictTypeId="pub_log_biz_type" autoEscape="false">业务类型</div>
		<div field="BIZ_NUM_HTML" headerAlign="center" allowSort="true" autoEscape="false">业务编号</div>
		<div field="TASK_ID" headerAlign="center" allowSort="true" >任务ID</div>--%>
	</div>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");


function search() {
	var data = form.getData(); //获取表单多个控件的数据
    grid.load({map:data});
}
search();

function reset(){
	form.reset();
	search();
}

//选择机构
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
//选择人员（无权限）
function selectEmp(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/utp/org/employee/select_employee.jsp",
        showMaxButton: true,
        title: "选择人员",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode || '');
                    btnEdit.setText(data.empname || '');
                }
            }
        }
    });            
}

function openDetail(logType,logId,createTime) {
    nui.open({
        url: nui.context+"/pub/log/log_detail.jsp?logId="+logId,
        title: "查看明细("+nui.formatDate(nui.parseDate(createTime),'yyyy-MM-dd HH:mm:ss')+")", 
        width: 800, 
    	height: 500,
    	allowResize:true,
    	showModal:true,
    	showMaxButton: true,
        ondestroy: function (action) {
            if(action=="ok"){
            }
        }
    });
}
function openDetailBiz(logType,logId,createTime) {
    nui.open({
        url: nui.context+"/pub/log/log_detail_biz.jsp?logId="+logId,
        title: "查看明细("+nui.formatDate(nui.parseDate(createTime),'yyyy-MM-dd HH:mm:ss')+")", 
        width: 800, 
    	height: 500,
    	allowResize:true,
    	showModal:true,
    	showMaxButton: true,
        ondestroy: function (action) {
            if(action=="ok"){
            }
        }
    });
}
	</script>
</body>
</html>

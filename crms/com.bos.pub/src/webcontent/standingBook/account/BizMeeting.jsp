<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>保付加签业务台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="贷审会会议台帐查询" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
			<label>贷审会模式：</label>
			<input name="map/meetingMode" required="false"data="data" valueField="dictID" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD2004"/>
				
			<label>会议编号：</label>
			<input name="map/meetingNum"  class="nui-textbox nui-form-input" vtype="maxLength:32" />

			<label>会议日期：</label>
			<input name="map/meetingDate" id="map/meetingDate"  class="nui-datepicker nui-form-input" vtype="maxLength:32"  />
			
			<label>机构名称：</label>
			<input name="map/handingOrgNum" id="map/handingOrgNum" required="false" class="nui-buttonEdit nui-form-input" onbuttonclick="selectCodeList(this)" />
			
			<label>成员名称：</label>
			<input name="map/empname"  class="nui-textbox nui-form-input" vtype="maxLength:32" />

	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	 <%--<a class="nui-button"  iconCls="icon-download" onclick="downloadExcel()" type="submit">导出</a>		--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetbizMeetingList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="meetingModeName" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD2004">贷审会模式</div>
		<div field="meetingNum" headerAlign="center" allowSort="true">会议编号</div>
		<div field="meetingDate" headerAlign="center" allowSort="true">会议时间</div>
		<div field="orgname" headerAlign="center" allowSort="true">机构名称</div>
		<div field="empName" headerAlign="center" allowSort="true">成员名称</div>
		<div field="posiName" headerAlign="center" allowSort="true">成员岗位</div>
		<div field="conclusion" headerAlign="center" allowSort="true">成员结论</div>

	</div>
</div>
	</center>
</div>
</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	    function search() {
	    git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
    //search();
	
    
    function reset(){
		form.reset();
	}
			   
		function selectDictItems(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择字典",
            width: 300,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	} 
	
      
    
	//选择经办机构
	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择经办机构",
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
        url: nui.context + "/pub/standingBook/select_employee.jsp",
        showMaxButton: true,
        title: "选择经办人",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
}
    	
	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
////////////////////日期判断结束
			//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.meetingNum != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");//accountManager.flow
				//com.primeton.example.excel.empManager.flow
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=221";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	</script>
</body>
</html>

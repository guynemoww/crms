<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-13
  - Description:TB_SYS_BAL_CONTROL, com.bos.pub.sys.TbSysBalControl
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>机构编码：</label>
		<input name="tbSysBalControl.orgnizastionCd" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrgCode"/>

		<label>机构名称：</label>
		<input name="tbSysBalControl.orgnizastionName" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
	
		<label>机构总限额：</label>
		<input name="tbSysBalControl.orgnizistionBal" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		
		<label>生效日期：</label>
		<input name="tbSysBalControl.effectiveDate" required="false" class="nui-datepicker nui-form-input" />


		
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.orgLimit.getTbSysBalControlList.biz.ext"
	dataField="tbSysBalControls"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="orgnizastionCd" headerAlign="center" allowSort="true" >机构编码</div>
		<div field="orgnizastionName" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
		<div field="orgnizastionLevle" headerAlign="center" allowSort="true" dictTypeId="CDZZ0002">机构层级</div>
		<div field="handingUserId" headerAlign="center" allowSort="true" dictTypeId="user">经办人员</div>
		<div field="orgnizistionBal" headerAlign="center" allowSort="true" >机构总限额</div>
		<div field="effectiveDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >生效日期</div>
		
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
	
    function add() {
        nui.open({
            url: "pub/orgLimit/item_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "pub/orgLimit/item_edit.jsp?controlId="+row.controlId+"&view="+v,
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
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbSysBalControl":{"controlId":
            		row.controlId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.orgLimit.delTbSysBalControl.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
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
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
      function selectEmpOrgCode(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/orgLimit/org_code_tree.jsp",
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
                        btnEdit.setText(data.orgcode);
                    }
                }
            }
        });            
    }
    
	</script>
</body>
</html>

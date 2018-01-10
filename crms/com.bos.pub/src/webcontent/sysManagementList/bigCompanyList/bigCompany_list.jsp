<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-05-04
  - Description:TB_SYS_BIG_COMPANY_LIST, com.bos.pub.sys.TbSysBigCompanyList
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<B>大优质企业名单制管理名单</B>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">

		<label>授权机构：</label>
		<input name="tbSysBigCompanyList.grantOrgCode" required="false" class="nui-buttonEdit" vtype="maxLength:10" onbuttonclick="selectEmpOrg" />

		<label>证件编码：</label>
		<input id="orgn" name="tbSysBigCompanyList.orgCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" id="add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" id="edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" id="edit1"onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" id="remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.sysManagementList.getTbSysBigCompanyListList.biz.ext"
	dataField="tbSysBigCompanyLists"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="custName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="custermer" headerAlign="center" allowSort="true" >集团客户名称</div>
		<div field="grantOrgCode" headerAlign="center" allowSort="true" dictTypeId="org">授权机构</div>
		<div field="orgCode" headerAlign="center" allowSort="true" >证件编码</div>
		 <div field="codeType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002"  >证件类型</div>
		 <div field="applyCount" headerAlign="center" allowSort="true"   >审批金额</div>
		  <div field="isList" headerAlign="center" allowSort="true"   dictTypeId="XD_0002">是否已纳入存量客户名单</div>
		   <div field="mark" headerAlign="center" allowSort="true"   >备注</div>
		</div>
	</div>
		 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	function getIsRole(){
				$.ajax({
                     url: "com.bos.pub.sysManagementList.getIsRole.biz.ext",
	                type: 'POST',
	                cache: false,
	                async:false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}else{
                    		if(text.roleFlag != '1'){
                    			nui.get("add").hide();
                    			nui.get("edit").hide();
                    			nui.get("remove").hide();
                    		}
                    	}
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
	}
	getIsRole();
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
            url: "pub/sysManagementList/bigCompanyList/bigCompany_add.jsp",
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
                url: "pub/sysManagementList/bigCompanyList/bigCompany_edit.jsp?id="+row.id+"&view="+v,
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
            	var json=nui.encode({"tbSysBigCompanyList":{"id":
            		row.id,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.sysManagementList.delTbSysBigCompanyList.biz.ext",
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
// 担保方式代码
function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_SXCD1020",
            title: "选择字典项",
            width: 200,
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
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url:  nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
	                    	if(data.orgid=="10001"){
	                     		 btnEdit.setValue("BS001");
		                    }else{
		                   		 btnEdit.setValue(data.orgcode);
		                    }
	                        	btnEdit.setText(data.orgname);
                }
            }
            }
        });            
    }
	</script>
</body>
</html>

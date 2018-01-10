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
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">

		<label>状态：</label>
		<input name="tbOrderMessage.state" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_ZT002" emptyText="请选择"/>
		<label>计分项目名称：</label>
		<input name="tbOrderMessage.scoreProjectName" required="false"  class="nui-buttonEdit" onbuttonclick="selectProject"/>
		<label>计分人姓名：</label>
		<input id="xingming" name="tbOrderMessage.scoreName" required="false"  class="nui-buttonEdit" onbuttonclick="selectCustManeger" />
		<label>计分人机构名称：</label>
		<input id="orgName" name="tbOrderMessage.scoreOrgName" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" />
		<label>经办时间：</label>
		<input id="state" name="tbOrderMessage.time" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:60" />
		
		
	
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
	<a class="nui-button" iconCls="icon-edit" onclick="miss()">失效变更</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.openOrder.getTbOrderMessageList.biz.ext"
	dataField="tbOrderMessages"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="scoreProjectName" headerAlign="center" allowSort="true" >计分项目名称</div>
		<div field="state" headerAlign="center" allowSort="true" dictTypeId="XD_ZT002">状态</div>
		<div field="scoreName" headerAlign="center" allowSort="true" dictTypeId="user">计分人姓名</div>
		<div field="scoreNumber" headerAlign="center" allowSort="true" >计分人工号</div>
		<div field="scoreOrgName" headerAlign="center" allowSort="true" >计分人机构名称</div>
		<div field="scoreOrgNumber" headerAlign="center" allowSort="true" >计分人机构编号</div>
		<div field="realScoring" headerAlign="center" allowSort="true" >实计分</div>
		<div field="orgPeopleName" headerAlign="center" allowSort="true" dictTypeId="user">经办人姓名</div>
		<div field="userNum" headerAlign="center" allowSort="true" >经办人工号</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办人机构</div>
		<div field="time" headerAlign="center" allowSort="true" >经办时间</div>

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
            url: nui.context +"/pub/openOrder/item_add.jsp",
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
         if(v==0){// 编辑
		             var state=row.state;
		         if(state=="01"||state=="02"||state=="05"||state=="08"){// 未复核的开单
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
		         }else{
		            alert("此开单不能修改");
		         }
		         
		         
         
         }else{//  查看
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
             
         
         }
       
           
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbOrderMessage":{"scoreMessageId":
            		row.scoreMessageId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.openOrder.delTbOrderMessage.biz.ext",
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
    //失效变更
function  miss(){
     var row = grid.getSelected();
        var state=row.state;
        if (row) {// 复核驳回 申辩复核成功 失效 已处罚
         if(state=="05"||state=="07"||state=="09"||state=="11"){
            		 nui.confirm("确定失效变更吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbOrderMessage":{"scoreMessageId":
            		row.scoreMessageId,version:row.version,"state":"09"}});
                $.ajax({
                     url: "com.bos.pub.openOrder.updateState.biz.ext",
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
        }else{
          alert("不能对此状态失效变更");
        }
           
        }else {
            alert("请选中一条记录");
        }

}

function selectProject(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/openOrder/selectProjectMatter.jsp",
            showMaxButton: true,
            title: "选择计分项目",
            width: 750,
            height: 450,
            ondestroy: function (action) {            
                if (action =="ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.projectName);
                        btnEdit.setText(data.projectName);
                    }
                }
            }
        });            
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
                        btnEdit.setValue(data.orgname);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
   
	</script>
</body>
</html>

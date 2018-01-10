<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08
  - Description:TB_PUB_GRANT_TABLE, com.bos.pub.decision.TbPubGrantTable
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>授权表标识：</label>
		<input name="tbPubGrantTable.pind" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>授权表名称：</label>
		<input name="tbPubGrantTable.pname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		
		<label>对应规则分类：</label>
		<input id="tbPubGrantTable.tid" name="tbPubGrantTable.tid" required="false" 
			valueField="pid" textField="pname" data="grant_packages" class="nui-combobox nui-form-input" vtype="maxLength:32" />

		<label>授权表状态：</label>
		<input name="tbPubGrantTable.pstatus" required="false" class="nui-dictcombobox nui-form-input" 
			dictTypeId="pub_grant_table_status" vtype="maxLength:20" />
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<span class="separator"></span>
	<a class="nui-button" onclick="editRule()">编辑授权表规则</a>
	<span class="separator"></span>
	<a class="nui-button" onclick="editCol()">编辑授权表表项</a>
	<span class="separator"></span>
	<a class="nui-button" onclick="statusNormal()">生效</a>
	<a class="nui-button" onclick="statusAbNormal()">失效</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getTbPubGrantTableList.biz.ext"
	dataField="tbPubGrantTables"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<!--<div field="pid" headerAlign="center" allowSort="true" >授权表编号</div>-->
		<div field="pind" headerAlign="center" allowSort="true" >授权表标识</div>
		<div field="pname" headerAlign="center" allowSort="true" >授权表名称</div>
		<div field="pstatus" headerAlign="center" allowSort="true" dictTypeId="pub_grant_table_status">授权表状态</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createtime" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">创建时间</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	var grant_packages=[];
	function initPackages() {
		var data={};
		data.item={};
		data.page={length:100};
		data.item._entity="com.bos.pub.decision.TbPubGrantPackage";
		data.item.ptype="01";
		var json=nui.encode(data);
		$.ajax({
		    url: "com.bos.pub.TbPubGrantRule.getItemList.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
		    contentType:'text/json',
		    success: function (text) {
		    	if (text.msg) {
			    	alert(text.msg);
		    		return;
		    	}
		    	grant_packages=text.items || [];
		    	if(grant_packages.length>0) {
		    		for (var i=grant_packages.length; i>0; i--) {
		        		grant_packages[i]=grant_packages[i-1];
		        	}
		        	grant_packages[0]={};
		            grant_packages[0].pid="";
		            grant_packages[0].pname="--请选择--";
		    	}
		    	nui.get("tbPubGrantTable.tid").setData(grant_packages);
		    	if (grant_packages.length>0) {
		    		nui.get("tbPubGrantTable.tid").setValue(grant_packages[1].pid);
		    	}
		    	search();
		    },
		    error: function () {
		    	alert("获取规则分类失败！");
		    }
		});
	}
	initPackages();
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		nui.get("tbPubGrantTable.tid").setData(grant_packages);
    	if (grant_packages.length>0) {
    		nui.get("tbPubGrantTable.tid").setValue(grant_packages[0].pid);
    	}
	}
    function statusNormal() {
    	var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	if (row.pstatus != '0') {
    		alert("只有状态为“未生效”的记录能进行生效操作！");
    		return;
    	}
    	var data=nui.clone(row);
    	data.pstatus='1';
    	var json=nui.encode({tbPubGrantTable:data});
    	$.ajax({
            url: "com.bos.pub.decision.updateTbPubGrantTable.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		search();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
    }
    
    function statusAbNormal() {
    	var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	if (row.pstatus != '1') {
    		alert("只有状态为“生效”的记录能进行失效操作！");
    		return;
    	}
    	var data=nui.clone(row);
    	data.pstatus='2';
    	var json=nui.encode({tbPubGrantTable:data});
    	$.ajax({
            url: "com.bos.pub.decision.updateTbPubGrantTable.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		search();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
    }
	
    function add() {
    	var packageid=nui.get("tbPubGrantTable.tid").getValue();
    	if (!packageid) {
			nui.alert("请选择规则分类");
			return;
		}
        nui.open({
            url: "pub/grant/pack/table/item_add.jsp?tid="+packageid,
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
    
      function editRule() {
            var row = grid.getSelected();
            if (!row) {
            	alert("请选中一条记录");
            	return;
            }
            if (row.pstatus != '0') {
	    		alert("只有状态为“未生效”的记录能进行此操作！");
	    		return;
	    	}
            nui.open({
                url: nui.context+"/pub/grant/pack/table_rule/item_list.jsp?itemId="+row.pid,
                title: "规则列表", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        //grid.reload();
                        //参数编辑完成后无需任何操作
               	 	}
                }
            });
        }
        
        function editCol() {
            var row = grid.getSelected();
            if (!row) {
            	alert("请选中一条记录");
            	return;
            }
            if (row.pstatus == '2') {
	    		alert("状态为“失效”的记录不能进行此操作！");
	    		return;
	    	}
            nui.open({
                url: nui.context+"/pub/grant/pack/table_col/item_list.jsp?itemId="+row.pid,
                title: "表项列表", 
                width: 1200,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        //grid.reload();
                        //参数编辑完成后无需任何操作
               	 	}
                }
            });
        }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "pub/grant/pack/table/item_edit.jsp?pid="+row.pid+"&view="+v,
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
            	var json=nui.encode({"tbPubGrantTable":{"pid":
            		row.pid,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.decision.delTbPubGrantTable.biz.ext",
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

	</script>
</body>
</html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_RULE, com.bos.pub.decision.TbPubGrantRule
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>规则名称：</label>
		<input name="item.rname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则分类：</label>
		<input id="item.pid" name="item.pid" style="width:250px;"
			valueField="pid" textField="pname" data="grant_packages" class="nui-combobox nui-form-input" />

		<label>规则类别：</label>
		<input id="item.rtype" name="item.rtype" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_rule_type" />

		<label>规则状态：</label>
		<input name="item.rstatus" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="put_grant_pack_status" />

		<!--<label>适用机构：</label>
		<input name="item.rorg" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />-->
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
	<a class="nui-button" onclick="statusCopy()">复制</a>
	<a class="nui-button" onclick="statusNormal()">生效</a>
	<a class="nui-button" onclick="statusAbNormal()">失效</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getTbGrantRuleByPidList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="rname" headerAlign="center" allowSort="true" >规则名称</div>
		<!--<div field="rnote" headerAlign="center" allowSort="true" >规则说明</div>-->
		<div field="rtype" headerAlign="center" allowSort="true"  dictTypeId="pub_grant_rule_type" >规则类别</div>
		<!--<div field="rlevel" headerAlign="center" allowSort="true" >规则优先级(整数)</div>-->
		<div field="rstatus" headerAlign="center" allowSort="true"  dictTypeId="put_grant_pack_status" >规则状态</div>
		<!--<div field="rversion" headerAlign="center" allowSort="true" >版本号</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>-->
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">创建时间</div>
		<div field="rorgNum" headerAlign="center" allowSort="true" dictTypeId="org">适用机构</div>
		</div>
	</div>

    <script type="text/javascript">
 	nui.parse();
	var grant_packages=[];
	function initPackages() {
		var data={};
		data.item={};
		data.page={length:100};
		data.item._entity="com.bos.pub.decision.TbPubGrantPackage";
		data.item.ptype="<%=request.getParameter("ptype") %>";
		if ('null' == data.item.ptype)
			data.item.ptype="01";
		var rtype="<%=request.getParameter("rtype") %>";
		if ('null' != rtype && rtype && rtype.length == 2) {
			data.item.rtype=rtype;
		}
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
		    	nui.get("item.pid").setData(grant_packages);
		    	if (grant_packages.length>0) {
		    		nui.get("item.pid").setValue(grant_packages[1].pid);
		    	}
		    	search();
		    },
		    error: function () {
		    	alert("获取规则分类失败！");
		    }
		});
	}
	initPackages();

    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var rtype="<%=request.getParameter("rtype") %>";
		if ('null' != rtype && rtype && rtype.length == 2) {
			nui.get('item.rtype').setValue(rtype);
			nui.get('item.rtype').setEnabled(false);
		}
		var data = form.getData(); //获取表单多个控件的数据
		data.item.ptype="<%=request.getParameter("ptype") %>";
		if ('null' == data.item.ptype)
			data.item.ptype="01";
		//alert(nui.encode(data));
        grid.load(data);
    }
    
    function reset(){
		form.reset();
		nui.get("item.pid").setData(grant_packages);
    	if (grant_packages.length>0) {
    		nui.get("item.pid").setValue(grant_packages[0].pid);
    	}
	}
	
    function add() {
    	var packageid=nui.get("item.pid").getValue();
    	if (!packageid) {
			nui.alert("请选择规则分类");
			return;
		}
        nui.open({
            url: nui.context+"/pub/grant/pack/rule/item_add.jsp?pid="+packageid,
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
        	if (v != 1 && row.rstatus == '4') {
	    		alert("待生效,以发起生效流程不可编辑操作！");
	    		return;
	    	}
        	if (v != 1 && row.rstatus == '3') {
	    		alert("已失效的记录不能进行编辑操作！");
	    		return;
	    	}
	    	if (v != 1 && (row.rstatus == '2' && row.rtype == '04')) {
	    		alert("已生效的记录不能进行编辑操作！");
	    		return;
	    	}
        	if (v != 1 && row.rstatus == '2') {
	    		//已生效的记录，可进入常量修改模式
	    		nui.open({
	                url: nui.context+"/pub/grant/pack/rule/rule_edit.jsp?type=grant&rid="+row.rid+"&pid="
	                	+row.pid
	                	+"&view=const",
	                title: (v==1 ? "查看" : "编辑"), 
	                width: 800,
	        		height: 500,
	                allowResize:true,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                },
	                ondestroy: function (action) {
	                    if(action=="ok"){
	               	 	}
	                }
	            });
	    		return;
	    	}
            nui.open({
                url: nui.context+"/pub/grant/pack/rule/item_edit.jsp?itemId="+row.rid+"&view="+v,
                title: (v==1 ? "查看" : "编辑"), 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
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
    
    function statusNormal() {
    	var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	if (row.rstatus != '1') {
    		alert("只有状态为“未生效”的记录能进行生效操作！");
    		return;
    	}
    	var data=nui.clone(row);
    	data.rstatus='2';
    	var json=nui.encode({item:data});
    	$.ajax({
            url: "com.bos.pub.decision.createProcess.biz.ext",
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
    	if (row.rstatus != '2') {
    		alert("只有状态为“生效”的记录能进行失效操作！");
    		return;
    	}
    	var data=nui.clone(row);
    	data.rstatus='3';
    	var json=nui.encode({item:data});
    	$.ajax({
            url: "com.bos.pub.decision.saveRule.biz.ext",
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
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"rid":
            		row.rid}});
                $.ajax({
                     url: "com.bos.pub.decision.delRule.biz.ext",
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
    //复制一条规则
	function statusCopy(){
		var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	
    	var json=nui.encode({'rule':row});
    	$.ajax({
                     url: "com.bos.pub.decision.copyRule.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                    	nui.alert("复制成功");
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
	}
	</script>
</body>
</html>

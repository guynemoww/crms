<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-31
  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.decision.TbPubGrantRule" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>规则编号：</label>
		<input name="item.rid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>规则名称：</label>
		<input name="item.rname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则分类：</label>
		<input id="item.pid" name="item.pid" required="true" 
			valueField="pid" textField="pname" data="grant_packages" class="nui-combobox nui-form-input" vtype="maxLength:32" />

		<label>规则类别：</label>
		<input name="item.rtype" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_rule_type" />

		<label>规则状态：</label>
		<input name="item.rstatus" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="put_grant_pack_status" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getRuleList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" onrowdblclick="save"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="rname" headerAlign="center" allowSort="true" >规则名称</div>
		<!--<div field="rnote" headerAlign="center" allowSort="true" >规则说明</div>-->
		<div field="rtype" headerAlign="center" allowSort="true"  dictTypeId="pub_grant_rule_type" >规则类别</div>
		<!--<div field="rlevel" headerAlign="center" allowSort="true" >规则优先级(整数)</div>-->
		<div field="rstatus" headerAlign="center" allowSort="true"  dictTypeId="put_grant_pack_status" >规则状态</div>
		<!--<div field="rversion" headerAlign="center" allowSort="true" >版本号</div>
		<div field="userNun" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>-->
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createtime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">创建时间</div>
		<div field="rorg" headerAlign="center" allowSort="true" dictTypeId="org">适用机构</div>
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
		    	nui.get("item.pid").setData(grant_packages);
		    	if (grant_packages.length>0) {
		    		nui.get("item.pid").setValue(grant_packages[0].pid);
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
			search();
		}
		
		function save(){
			var row = grid.getSelected();
			if (!row) {
				nui.alert("请选中一条记录");
				return;
			}
        	CloseWindow("ok");
		}
		
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context + "/pub/grant/pack/rule/item_edit.jsp?itemId="+row.rid+"&view="+v,
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
	                        grid.reload();
                   	 	}
                    }
                });
            } else {
                alert("请选中一条记录");
            }
            
        }
        
      
	</script>
</body>
</html>

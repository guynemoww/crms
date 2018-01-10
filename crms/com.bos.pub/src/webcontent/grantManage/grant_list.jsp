<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:对公授权维护
-->
<head>
<title>对公授权维护</title>
</head>
<body>

<div id="form1" class="nui-form" style="overflow:hidden;" >
	<input name="item.legOrg" class="nui-hidden" value = "<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg") %>" />
		<div class="nui-dynpanel" columns="6">
			<label>机构名称：</label>
			<input id="item.orgname" name="item.orgname"  class="nui-textbox nui-form-input"/>
			<label>授信品种名称：</label>
			<input id="item.productType" name="item.productType" class="nui-textbox nui-form-input"  />
			<label>是否低：</label>
			<input id="item.isLow" name="item.isLow" dictTypeId="XD_0002" class="nui-dictcombobox"  />
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
</div>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" iconCls="icon-add" onclick="editRow(1)" plain="false">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="editRow(2)" plain="false">编辑</a>
	    <a class="nui-button" iconCls="icon-remove" onclick="removeRow()" plain="false">删除</a>
	    <a class="nui-button"  onclick="uploadExcel" plain="false">导入</a>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" 
url="com.bos.csm.pub.ibatis.getItem.biz.ext" idField="id"
allowResize="true" pageSize="10"  dataField="items" multiSelect="false" sizeList="[10,20,50,100]"  allowAlternating="true" >

<div property="columns"> 
    <div type="checkcolumn">选择</div>
    <div field="orgname" allowResize="false"  headerAlign="center" allowSort="true" >机构名称</div>
    <div field="productName" allowResize="false"  headerAlign="center" allowSort="true" >授信品种</div>
    <div field="isLow" allowResize="false"  headerAlign="center" allowSort="true" dicttypeid="XD_0002" >是否低</div>
    <div field="guarType" allowResize="false"  headerAlign="center" allowSort="true"  renderer="onGuarTypeRenderer" >担保类型</div>
    <div field="maxAmt"  allowSort="true" >权限金额(万元)</div>            
    <div field="authLv" allowResize="false"  headerAlign="center" allowSort="true"  renderer="onAuthLvRenderer" >权限级别</div>
    <div field="posiname" allowResize="false"  headerAlign="center" allowSort="true" >岗位名称</div>
<!--     <div field="personLv" allowResize="false"  headerAlign="center" allowSort="true" renderer="onPersonLvRenderer">审批官级别</div> -->
</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1"); 
	//搜索事件
	function search() {       
        var o = form.getData();
        o.sqlName="com.bos.pub.grantManage.grant.select_grant_id";
        grid.load(o);
    }
    search();
    function reset(){
		form.reset();
	}
	
	//新增/编辑事件
	function editRow(v){
		var title="新增";
		var grantId=null;
		if('2'==v){
			title="编辑";
			var row = grid.getSelected();
			if(null==row || ''==row){
			
				nui.alert("请选择记录");
				return;
			}else{
				grantId = row.grantId;
			}
		}
		nui.open({
            url: nui.context + "/pub/grantManage/grant_edit.jsp?grantId="+grantId+"&v="+v,
            title: title, 
            width: 800, 
        	height: 400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    grid.reload();
                }
            }
        });
	
	}
	
	
	//删除事件
	function removeRow() {
     
     	var row = grid.getSelected();
		if(row){
			var json = {"item":row};
	        grid.loading("删除中，请稍后......");
	        nui.ajax({
	            url: "com.bos.pub.grantManage.grantManage.deleteGrant.biz.ext",
	            type: 'POST',
	            data: json,
	            success: function (text) {
	            	if(null != text.msg && ''!=text.msg){
	            	
	            		alert(text.msg);
	            	}else{
	            	
		                grid.reload();
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	            }
	        });
		}else{
			nui.alert("请选择记录");
			return;
		}
    }
    
    //
  	function uploadExcel(){
  	
  		nui.open({
            url: nui.context + "/pub/grantManage/grant_export.jsp",
            title: "导入", 
            width: 600, 
        	height: 200,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                    grid.reload();
            }
        });
  	}
  	
  	var GuarTypes = [{ id: 1, text: '信用' }, { id: 2, text: '优质担保'}, { id: 3, text: '普通担保'}, { id: 4, text: '保证'}, { id: 5, text: '全部'}]; 
  	function onGuarTypeRenderer(e){
  	
  		for (var i = 0, l = GuarTypes.length; i < l; i++) {
            var g = GuarTypes[i];
            if (g.id == e.value) return g.text;
        }
        return "";
  	}
  	
  	var AuthLvs = [{ id: 1, text: '总行审批' }, { id: 2, text: '分行行审批'}, { id: 3, text: '支行审批'}]; 
  	function onAuthLvRenderer(e){
  		
  		for (var i = 0, l = AuthLvs.length; i < l; i++) {
            var g = AuthLvs[i];
            if (g.id == e.value) return g.text;
        }
        return "";
  	}
  	
  	var personLvs =[
		{"id":"1","text":"一级审批官"},
		{"id":"2","text":"二级审批官"},
		{"id":"3","text":"三级审批官"},
		{"id":"4","text":"四级审批官"},
		{"id":"5","text":"五级审批审"},
		{"id":"6","text":"六级审批官"},
		{"id":"7","text":"七级审批官"},
		{"id":"8","text":"八级审批官"}
		];
  	function onPersonLvRenderer(e){
  		
  		for (var i = 0, l = personLvs.length; i < l; i++) {
            var g = personLvs[i];
            if (g.id == e.value) return g.text;
        }
        return "";
  	}
</script>
</html>
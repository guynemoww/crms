<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): sdl
  - Date: 2017-03-29 10:34:01
  - Description:机构维护
-->
<head>
<title>机构维护</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
	<div title="机构列表" >
	<div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
		<!-- 数据实体的名称 -->
    	<input class="nui-hidden" name="criteria/_entity" value="com.bos.utp.dataset.organization.OmOrganization">
    	<!-- 查询条件 -->
    	<input class="nui-hidden" name="criteria._expr[0]._op" value="="/>
		<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all"/>
		<input class="nui-hidden" name="criteria._expr[1]._op" value="like"/>
		<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all"/>
		<!-- 排序字段 -->
        <input class="nui-hidden" name="criteria._orderby[0]._property" value="orgcode">
        <input class="nui-hidden" name="criteria._orderby[0]._sort" value="asc">
		<div class="nui-dynpanel" columns="4">
			<label>机构行号：</label>
			<input name="criteria._expr[0].orgcode" required="false" class="nui-textbox nui-form-input"  />
			<label>机构名称：</label>
			<input name="criteria._expr[1].orgname" class="nui-textbox nui-form-input" required="false"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a id="addCust" class="nui-button"  iconCls="icon-add" onclick="add()">增加</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit(2)">编辑</a>
		<a id="queryCust" class="nui-button" iconCls="icon-node" onclick="edit(1)">查看</a>
		<a id="upload" class="nui-button" onclick="upload()">导入/导出</a>		
	</div>
	
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"sortMode="client"
		 url="com.bos.utp.org.organization.getOrgList.biz.ext" dataField="omorganizations"
		allowResize="true" showReloadButton="false"    allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="orgcode" headerAlign="center" allowSort="true" autoEscape="false" width="10%">机构行号</div>
			<div field="orgname" headerAlign="center" allowSort="true"align="center" width="20%">机构名称</div>
			<div field="status" headerAlign="center" align="center"allowSort="true" dictTypeId="CDZZ0004" width="10%">机构状态</div>
			<div field="orglevel" headerAlign="center" align="center"allowSort="true" dictTypeId="XD_GGCD6004" width="10%">机构级别</div>
			<div field="linktel" headerAlign="center" align="center"allowSort="true" width="25%">联系电话</div>
			<div field="orgaddr" headerAlign="center" align="center"allowSort="true" width="25%">联系地址</div>
		</div>
	</div>
	</div>
</div>	
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	//查询
	function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    //重置
    function reset(){
		form.reset();
	}
	//新增
	function add(){
		nui.open({
            url: nui.context + '/utp/org/organization/org_newAdd.jsp',
            showMaxButton: true,
            title: "添加机构信息",
            width: 800,
            height: 400,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
              if(action=="ok"){
              	search();
              }
            }
        });
	}
    //v=1查看 v=2修改
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
	       	var url = nui.context + "/utp/org/organization/org_edit.jsp?id="+row.orgid+"&view="+v;
			nui.open({
	            url: url,
	            showMaxButton: false,
	            title: "机构详细信息",
	            width: 800,
            	height: 400,
	            onload: function () {
              		var iframe = this.getIFrameEl();
              		var data = row;
              		//直接从页面获取，不用去后台获取
              		//iframe.contentWindow.setData(data);
              },
              ondestroy: function (action) {
	    	   	search();
	    	  }
      	  });	
        } else{
        	alert("请选中一条记录");
        }
    }
    //导入
    function upload(){
    	nui.open({
	            url: "com.primeton.example.excel.empManager.flow",
	            showMaxButton: true,
	            title: "导入/导出机构信息",
	            width: 800,
	            height: 400,
	            //state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                search();
	            }
      	  });	
    }
</script>
</body>
</html>
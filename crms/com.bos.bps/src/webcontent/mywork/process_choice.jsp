<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>

<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-30 09:28:44
  - Description:
-->
<head>
<title>选择例外流程</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-splitter" style="width:100%;height:85%;">
    <div size="50%" showCollapseButton="false" style="padding:5px;">
	    <div id="form1" style="width:100%;height:auto;overflow:hidden;display: none;">
			<input type="hidden" name="_entity" value="com.bos.bps.dataset.bps.TbWfmProcessmapping" class="nui-hidden" />
			<div class="nui-dynpanel" columns="4">
			<label>模板定义名称：</label>
			<input name="productType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>模板版本号：</label>
			<input name="templageVersion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

	
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;display: none;" 
	    	borderStyle="border:0;">
	    	<a class="nui-button" iconCls="icon-search" onclick="search(null)">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
        <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.bps.util.TbWfmProcessMapping.getTbWfmProcessmappingWhereOrgUserid.biz.ext"
		dataField="items"
		multiSelect="true"
		allowResize="true" showReloadButton="false"
		sizeList="[10]"  pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="productType" headerAlign="center" width="35%"  allowSort="true" >模板定义名称</div>
			<div field="templateName" headerAlign="center" width="35%" allowSort="true" >模板定义ID</div>
			<div field="templageVersion" headerAlign="center"  width="20%" allowSort="true" >模板版本号</div>
		</div>
		</div>
    </div>
    <div showCollapseButton="false" style="padding:5px;">
       <div id="grid2" class="nui-datagrid" 
       style="width:100%;height:auto" url="com.bos.bps.op.WorkFlowManager.getExceptionProcess.biz.ext"
        dataField="retitems"
		multiSelect="true"
		allowResize="true" showReloadButton="false"
		sizeList="[10]"  pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="productType" headerAlign="center" width="35%" allowSort="true" >模板定义名称</div>
			<div field="templateName" headerAlign="center" width="35%" allowSort="true" >模板定义ID</div> 
			<div field="templageVersion" headerAlign="center" width="20%" allowSort="true" >模板版本号</div>
		</div>
		</div>
    </div>        
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
    <a class="nui-button" iconCls="icon-ok" onclick="move()">移入</a>
	<a class="nui-button" iconCls="icon-ok" onclick="del()">移除</a>
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('close')">关闭</a>
</div>
</body>
</html>		
<script type="text/javascript" >
	nui.parse();
	//保存grid1的所有数据集合
	var grid1Json=[];
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var grid1 = nui.get("grid2");
    function search() {
    alert("1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load({'item':data,'pageAll':{'js':1},'agentfromid':'<%=request.getParameter("agentfromid") %>','orgcode':'<%=request.getParameter("orgcode") %>'});
    }
    function initGird(){
    	var data = form.getData(); //获取表单多个控件的数据
       	 grid.load({'item':data,'pageAll':{'pageIndex':0,'pageSize':99999},'agentfromid':'<%=request.getParameter("agentfromid") %>','orgcode':'<%=request.getParameter("orgcode") %>'},init);
    }
    //初始化左边框数据
    function init(){
    	var json=<%=request.getParameter("json") %>;
		var griddata=grid.getData();
		for(var i=0;i<json.length;i++){
			for(var y=0;y<griddata.length;y++){
				if(json[i].templateName==griddata[y].templateName){
					grid1Json[grid1Json.length]=griddata[y];
				}
			}
		}
		grid1.load({"gettotal":grid1Json.length,"items":grid1Json});
		var data = form.getData(); //获取表单多个控件的数据
		grid.load({'item':data,'pageAll':{'js':1},'agentfromid':'<%=request.getParameter("agentfromid") %>','orgcode':'<%=request.getParameter("orgcode") %>'});
    }
    initGird();
    
    
    function reset(){
		form.reset();
	}
	//保存时执行返回参数
	function save(){
		CloseWindow({"result":"ok","data":grid1Json});
	}
	//移入方法
	function move(){
	var gridgrids=grid.getSelecteds();
	if(gridgrids.length!=0){
	//获取grid的选中值和grid1的所有值
	var griddata=nui.decode(gridgrids);
	var grid1data=nui.decode(grid1.getData());
	//循环匹配重复
	for(var i=0;i<griddata.length;i++){
	    var bool=true;
		if(grid1data.length==0){
			grid1Json=griddata;
			grid.deselectAll();
			grid1.load({"gettotal":grid1Json.length,"items":grid1Json});
			return;
		}
		for(var y=0;y<grid1data.length;y++){
			if(griddata[i].processMappingId==grid1data[y].processMappingId){
				bool=false;
			}
		}
		if(bool){
			grid1Json[grid1Json.length]=griddata[i];
		}
	}
		
		grid.deselectAll();
		grid1.load({"gettotal":grid1Json.length,"items":grid1Json});
		}else{
		nui.alert("请在左边至少选择一条数据");
	}
	}
	//移除 移入的
	function del(){
	//获取grid1的选中值和grid1的所有值
	var data=[];
	var datalen=0;
	var getdata=grid1.getSelecteds();
		if(getdata.length!=0){
		for(var i=0;i<grid1Json.length;i++){
		var bol=true;
			for(var y=0;y<getdata.length;y++){
					if(grid1Json[i].templateName==getdata[y].templateName&&getdata[y].templageVersion==getdata[y].templageVersion){
						bol=false;
					}	
				}
				if(bol){
					data[datalen]=grid1Json[i];
					datalen++;
				}
		}
		grid1Json=data;
			grid1.load({"gettotal":grid1Json.length,"items":grid1Json});
	}else{
		nui.alert("请在右边至少选择一条数据");
	}
	}
</script>
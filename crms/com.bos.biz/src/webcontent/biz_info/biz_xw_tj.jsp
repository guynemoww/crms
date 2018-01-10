<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>审批条件</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.4%;height:auto;overflow:hidden;">
		<div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="sptjdiv">
			<a class="nui-button" iconCls="icon-add" onclick="add('sptj')">增加</a>
			<a class="nui-button" iconCls="icon-remove" onclick="remove('sptj')">删除</a>
		</div>
		<div id="sptj" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.bizInfo.bizInfo.getSptjList.biz.ext" dataField="sptjs"
			allowResize="true" showReloadButton="false" allowCellEdit="true" 
		    allowCellSelect="true"
			sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" >选择</div>
				<div field="attachedDesc" headerAlign="center">条件
					<input property="editor" class="nui-textbox" vtype="string;maxLength:200"/>
				</div>
			</div>
		</div>	
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" id="addbt" onclick="create">保存</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var applyId = "<%=request.getParameter("applyId") %>";
	initPage();
	function initPage(){
		var jsontj = nui.decode({"applyId":applyId});
		var gridtj = nui.get("sptj");
		gridtj.load(jsontj);
		if(proFlag=='-1'){
			nui.get("sptjdiv").hide();
			nui.get("addbt").hide();
		}
	}
	
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
        var json1 =nui.get("sptj").getChanges();
        if(json1[0]){
         debugger; 
          var lenth=json1[0].attachedDesc;
        var length =lenth.replace(/[^u0000-u00ff]/g,"aaa").length;
        if(length>1000){
         nui.alert("填写信息过长"); 
/*          nui.alert(length); */
         nui.get("btnCreate").setEnabled(true);
         return;
        }
        
        }
        o.sptjs = nui.get("sptj").getChanges();
        o.applyId=applyId;
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.saveSptj.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	alert("保存成功!");
            	var jsontj = nui.decode({"applyId":applyId});
				var gridtj = nui.get("sptj");
            	initPage();
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"periodsNumber":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.attachedId){
            		var json = nui.encode({"attachedId":row.attachedId});
	            	$.ajax({
			            url: "com.bos.bizInfo.bizInfo.delTj.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
			            	initPage();
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
</script>
</body>
</html>
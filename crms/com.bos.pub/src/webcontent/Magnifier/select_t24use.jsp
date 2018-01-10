<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-14
  - Description:TB_SCORE_PROJECT_MESSAGE, com.bos.pub.sys.TbScoreProjectMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2" style="text-align:center;">
		<label>行业名称：</label>
		<input id="dirName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" style="width:130px"/>
		<label>行业代码：</label>
		<input id="dirKey" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" style="width:130px"/>
	</div>
	<div class="nui-toolbar" style="text-align:center;" 
	    borderStyle="border-left:0;border-bottom:0;border-right:0;" >
	    <a class="nui-button" iconCls="icon-search" onclick="search()" >查询</a>
	    <a class="nui-button"  iconCls="icon-reset" onclick="reset()" >重置</a>
	</div> 
</div>
<div class="nui-fit">
    <ul id="tree1" class="nui-tree" style="width:100%;height:100%;" 
		showTreeIcon="true" textField="NAME" idField="LOAN_DIR_KEY" parentField="superiorBranchCode" expandOnLoad="false"
		onnodeclick="nodeclick" dataField="t24Uses" url="com.bos.pub.Magnifier.getT24UseMagnifierMassageTree.biz.ext">
	</ul>
</div> 
<div class="nui-toolbar" style="text-align:center;" 
    borderStyle="border-left:0;border-bottom:0;border-right:0;" >
    <a class="nui-button" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" onclick="CloseWindow('clear')">清空</a>
</div> 
	    
<%--<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.Magnifier.getT24UseMagnifierMassage.biz.ext"
	dataField="t24Uses"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="LOAN_DIR_NAME" headerAlign="center" allowSort="true" >行业名称</div>
		<div field="LOAN_DIR_KEY" headerAlign="center" allowSort="true" >行业代码</div>
		</div>
		
	</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick=selected()>确定</a>
        <span style="display:inline-block;width:25px;"></span>
</div>	--%>
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
    var tree = nui.get("tree1");
	//var grid = nui.get("grid1");
	
	var currentNode = null;
	
    function search() {
		//var data = form.getData(); //获取表单多个控件的数据
        //grid.load(data);
        
        var dirName = nui.get("dirName").getValue();
        var dirKey = nui.get("dirKey").getValue();
        if(dirName == "" && dirKey == ""){
            tree.clearFilter();
        } else {
            tree.filter(function (node) {
                var text = node.NAME ? node.NAME : "";
                if(dirName == "" && dirKey != "") {
                	if ( text.indexOf(dirKey) != -1) {
	                    return true;
	                }
                } else if (dirName != "" && dirKey == "") {
                	if ( text.indexOf(dirName) != -1) {
	                    return true;
	                }
                } else if (dirName != "" && dirKey != "") {
                	if (text.indexOf(dirName) != -1 && text.indexOf(dirKey) != -1) {
	                    return true;
	                }
                }
            });
        }
    }
    //search();
    
    function reset(){
		form.reset();
	}
	
    
  	function selected() {
      	//var row = grid.getSelected();
        //if (row) {
            //CloseWindow("ok");
        //} else {
            //alert("请选中一条记录");
        //}
  	} 
    
   
 	function getData(){
    	return currentNode;
    }
    
    function nodeclick(e) {
		currentNode = e.node;
	}
    
    function save(){
		if (!currentNode) {
			nui.alert("请选择一条记录");
			return;
		}
		CloseWindow("ok");
	}
	</script>
</body>
</html>

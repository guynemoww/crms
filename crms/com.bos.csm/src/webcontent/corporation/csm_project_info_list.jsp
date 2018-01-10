<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2013-11-25
  - Description:TB_CSM_PROJECT_INFO, com.bos.dataset.csm.TbCsmProjectInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmProjectInfo" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div  class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		<%--<a id="view" class="nui-button" style="margin-right:20px;height:21px" iconCls="icon-node" onclick="check">查看结果报告</a>--%>
		<a id="insert" class="nui-button" iconCls="icon-add" onclick="insert()">引入</a>
		<a id="image" class="nui-button" iconCls="icon-zoomin" onclick="image()">影像资料</a>
		
	</div>
</div>
				
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.corporation.Project.queryProjectsByPartyId.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client" allowAlternating="true">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="projectName" headerAlign="center" allowSort="true" >项目名称</div>
		<div field="projectType" headerAlign="center" allowSort="true" dictTypeId="CDXY0049">项目类型</div>
		<div field="projectLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_XMCD0001">项目级别</div>
		<div field="projectAddress" headerAlign="center" allowSort="true" >项目地点</div>
		<div field="projectTotalAmt" headerAlign="center" allowSort="true" >项目总投资</div>
		<div field="careerMyamt" headerAlign="center" allowSort="true" >自有资金</div>
		<div field="careerMyamtPercent" headerAlign="center" allowSort="true" >自有资金比例（%）</div>
		<!-- 
		<div field="projectStartDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd" >项目开工日</div>
		<div field="projectEndDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd" >项目竣工日</div>
		 
		<%--<div field="creditRatingCd" headerAlign="center" allowSort="true" >项目评级结果</div>--%>
		<%--<div field="state" headerAlign="center" allowSort="true" >备注</div>--%>
		<div field="createTime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd hh:mm:ss">创建时间</div>
		-->
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
    var partyId = "<%=request.getParameter("partyId") %>";
    var partyNum = "<%=request.getParameter("partyNum") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var relType = "<%=request.getParameter("relType")%>" ;//引入类型，动态查询条件

	nui.get("insert").hide();
	if(qote==1){//查看
		nui.get("add").hide();
	    nui.get("edit").hide();
	    nui.get("remove").hide();
	}else if(qote==3){
		nui.get("add").hide();
	    nui.get("edit").hide();
	    nui.get("remove").hide();
	    nui.get("insert").show();
	}
    function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
		if(relType == "thirdCrd"){
			data.item.thirdCrd = "true";
		}
        grid.load(data);
        git.unmask();
    }
    search();
	
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_project_info_add.jsp?partyId="+partyId,
            title: "新增", 
            width: 900, 
        	height: 550,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    git.mask();
                    search();
                }
            }
        });
    }
    
    function edit(v) {
    	var title = '编辑';
    	if('1'==v){
    		title = '查看';
    	}
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_project_info_edit.jsp?itemId="+row.projectId+"&view="+v+"&projectType="+row.projectType,
                title: title, 
                width: 900,
        		height: 550,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
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
            	git.mask();
            	var json=nui.encode({"item":{"projectId":
            		row.projectId,
					"_entity":"com.bos.dataset.csm.TbCsmProjectInfo"}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delItem.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                      	git.mask();
                      	 search();
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    function getData(){
  	  var data=nui.encode(grid.getSelected());
      return data;
    }
    var data;
    function insert(){
    	 var row = grid.getSelected();
    	 if(row){
    	 	data = row.projectId;
    		CloseWindow("ok");
    	 }else{
    	 	nui.alert("请选中一条记录");
    	 }
    }
	function check(){
   			var row = grid.getSelected();
   			if(row){
				nui.open({
		            url:  nui.context + "/irm/financialCustom/financial_view_report.jsp?bizId="+row.iraApplyId,
		            title: "查看评级报告",
		            onload: function () {
		            },
	            	ondestroy: function (action) {
	            		grid.reload();
	            	}
	        	});
   			}else{
   				return alert("请选择一条评级信息");
   			}
   	}
   
   	
   	function image(){
   			var row = grid.getSelected();
   			var url;
   			if(row){
   				if(qote=="1"){
				url="/pub/imagePlatform/item_tree.jsp?businessNumber="+row.projectId+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=12"+"&view="+qote;
				}else{
				url="/pub/imagePlatform/item_tree.jsp?businessNumber="+row.projectId+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=12";
				}
				nui.open({
		            url:  nui.context +url,
		            title: "影像资料",
		            width: 1200,
        			height: 800,
		            onload: function () {
		            },
	            	ondestroy: function (action) {
	            		grid.reload();
	            	}
	        	});
   			}else{
   				return alert("请选择一条项目信息");
   			}
   	
   	}
</script>
</body>
</html>

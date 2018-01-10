<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 
  - Date: 2015-8-26 16:17:00
  - Description:
-->
<head>
<title>查询项目详情</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1"  style="width:99.5%;height:auto;" >
	<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.crdApply.estateInfo.getEstateList" />
	<input id="item.LIMIT_ID" class="nui-hidden nui-form-input" name="item.LIMIT_ID" value="<%=request.getParameter("limitId")%>"/>
</div>

<div style="width:99.5%">
		<div class="nui-toolbar" style="width:99.5%;border-bottom:none;text-align:left;">
		<a id="image" class="nui-button" iconCls="icon-zoomin" onclick="viewImg()">影像资料</a>
	    <a id="rel" class="nui-button" iconCls="icon-add" onclick="rel()">引入</a>
	    <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	    <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	    <a id="view" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	    <a id="delete" class="nui-button" iconCls="icon-remove" onclick="del()">删除</a>
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
			<div field="PROJECT_NAME" headerAlign="center" allowSort="true" >项目名称</div>
			<div field="PROJECT_TYPE" headerAlign="center" allowSort="true" dictTypeId="CDXY0049">项目类型</div>
			<div field="PROJECT_LEVEL_CD" headerAlign="center" allowSort="true" dictTypeId="XD_XMCD0001">项目级别</div>
			<div field="PROJECT_ADDRESS" headerAlign="center" allowSort="true" >项目地点</div>
			<div field="PROJECT_TOTAL_AMT" headerAlign="center" allowSort="true" >项目总投资</div>
			<div field="CAREER_MYAMT" headerAlign="center" allowSort="true" >自有资金</div>
			<div field="CAREER_MYAMT_PERCENT" headerAlign="center" allowSort="true" >自有资金比例(%)</div>
	     </div>
</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    	<a id="sel" class="nui-button" iconCls="icon-save"  onclick="sel(1)">选中</a>
    	<a class="nui-button"  iconCls="icon-close" onclick="sel(0)">关闭</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	var limitId ="<%=request.getParameter("limitId")%>";//业务申请ID
	var proFlag ="<%=request.getParameter("proFlag")%>";//
	var partyId ="<%=request.getParameter("partyId")%>";//
    //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
	if("1" != proFlag){
		nui.get("add").hide();
		nui.get("edit").hide();
		nui.get("delete").hide();
		nui.get("sel").hide();
		nui.get("rel").hide();
	}
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	query();
	
    function query(){
       var o = form.getData();
       grid.load(o);
       git.unmask();
    }
    
	grid.on("load",function(e){
		for (var i=0; i<e.data.length; i++){
		    grid.select(grid.getRowByUID(e.data[i]._uid));
    	}
     });
    
    function sel(v) {
      var row = grid.getSelected();
      if(v == 0){
          CloseWindow("ok");
      }else{
        if (row) {
            CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
      }
    } 
    
    function getData(){
       var row = grid.getSelected();
      if (row) {
            return row;
        } else {
            return null;
        }
    }
    
    function reset(){
		form.reset();
		query();
	}
    
    function viewImg(){
    	var rows = grid.getSelected();
        if (!rows) {
           alert("请选中一条记录");
           return;
        }
        var view = 0;
        if("1" != proFlag){
        	view = 1;
        }
        nui.open({
            url: nui.context + "/pub/imagePlatform/item_tree.jsp?businessNumber="+rows.PROJECT_ID+"&csmNum="+rows.PARTY_NUM+"&partyTypeCd=01&flowModuleType=12&view="+view,
            title: "影响资料",
            state:"max",
			showMaxButton:true,
        	allowResize:true,
            onload: function(e){
            },
            ondestroy: function (action) {
            }
        });
    }
  	
	function add(){
		var len = grid.getData().length;
		if(len>0){
			alert("记录已存在，请编辑或删除当前记录！");
			return;
		}
		
	    nui.open({
	        url: nui.context + "/csm/corporation/csm_project_info_add.jsp?partyId="+partyId+"&relType=thirdCrd",
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (text) {
	       		var iframe = this.getIFrameEl();
	            var projectId = iframe.contentWindow.data;
	            if(projectId!=null && projectId!=''){
	            	addCrdProjectRel(projectId);
		        }
	            query();
	        }
	    });
	}
  
    function del(){
    	var row = grid.getSelected();
        if (!row) {
           alert("请选中一条记录");
           return;
        }
        nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	    	git.mask();
    		var json=nui.encode({"item":{"relId":row.REL_ID,
			"_entity":"com.bos.dataset.crd.TbCrdProjectRel"}});
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
	                query();
	            },
	            error: function () {
	            	git.unmask();
	            	alert("操作失败！");
	            }
	        });
	    });
    	return;
    	
    }
    
    function edit(v) {
    	var title = '编辑';
    	if('1'==v){
    		title = '查看';
    	}
        var row = grid.getSelected();
        if (row) {
            nui.open({
            	url: nui.context+"/csm/corporation/csm_project_info_edit.jsp?itemId="+row.PROJECT_ID+"&view="+v,
                title: title, 
                width: 900,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                         query();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
	//引入
	function rel(){
		var len = grid.getData().length;
		if(len>0){
			alert("记录已存在，请编辑或删除当前记录！");
			return;
		}
		 nui.open({
	            url: nui.context + "/csm/corporation/csm_project_info_list.jsp?partyId="+partyId+"&qote=3&relType=thirdCrd",
	            title: "引入客户项目信息", 
	            width: 800, 
	        	height: 500,
	        	allowResize:true,
	        	showMaxButton: true,
	            ondestroy: function (data) {
					 var iframe = this.getIFrameEl();
	                 var projectId = iframe.contentWindow.data;
	                if(projectId!=null && projectId!=''){
	                	addCrdProjectRel(projectId);
	                }
	                query();
		        }
	       	 });
	}
	
	//建立项目与额度关系
	function addCrdProjectRel(projectId){
	    var json =nui.encode({"limitId":limitId,"projectId":projectId});
	    $.ajax({
	        url: "com.bos.crdApply.crdApply.saveCrdProjectRel.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	if(mydata.msg!=null && mydata.msg!=''){
	        		nui.alert(mydata.msg);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	
	}
	
	document.onunload = function(){
		alert("22");
	};
</script>
</body>
</html>
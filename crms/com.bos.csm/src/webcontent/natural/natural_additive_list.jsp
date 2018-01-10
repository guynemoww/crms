<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-06-21 12:42:24
  - Description:
-->
<head>
<title>附加信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.natural.natural.additiveList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>
	<div  class="nui-toolbar" style="border-bottom:0;text-align:left;">
	<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>  
	<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a> 
	<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(0)">查看</a> 
	<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a></div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	     url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn" width="10%">选择</div>
	        <div field="partyNum" allowSort="true" headerAlign="center">客户编号</div>
	        <div field="partyName" allowSort="true" headerAlign="center">客户名称</div>
	        <div field="title" allowSort="true"  headerAlign="center" >标题</div>
	        <div field="detailInfo" allowSort="true" headerAlign="center" width="50%">详细信息</div>
	     </div>
	</div>
<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		
		if(qote==1){
		   nui.get("add").hide();
		   nui.get("edit").hide();
		   nui.get("remove").hide();
		}
		
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
     }
     
     init();
	
	function add() {
            nui.open({
                url:nui.context + "/csm/natural/natural_additive_info.jsp?partyId="+partyId,
                title: "新增", 
                width: 800, 
            	height: 300,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
                    }
                }
            });
        }
	
	function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/natural/natural_additive_info.jsp?id="+row.id+"&qote="+v,
                title: "查看编辑", 
                width: 800,
        		height: 300,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
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
            	var json=nui.encode({"item":{"id":
            		row.id,
					"_entity":"com.bos.dataset.csm.TbCsmAdditiveInfo"}});
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
                        grid.reload();
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
	
</script>
	
	
</body>
</html>
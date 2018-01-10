<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-5-16
  - Description:TB_CSM_IMPORNANT_EVENT, com.bos.dataset.csm.TbCsmImpornantEvent
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmImpornantEvent" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div id="crud"  class="nui-toolbar" style="border:0;text-align:left;padding-right:20px">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
 		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div type="indexcolumn" headerAlign="center">序号</div>
		<div id="dgsjlx" name="dgsjlx" field="eventTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0234">事件类型</div>
		<div id="grsjlx" name="grsjlx" field="eventTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0334">事 件 类 型</div>
		<div field="happenDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">发生日期</div>
		<div field="eventState" headerAlign="center" allowSort="true" >事件描述及原因</div>
		<!-- <div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss"  >登记时间</div> -->
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
    var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var zdsjlx = "<%=request.getParameter("zdsjlx")%>" ;
	var zdsjbz = "<%=request.getParameter("zdsjbz")%>" ;
	
	var partyTypeCd = "<%=request.getParameter("partyTypeCd") %>";
	var dic="";
	if(partyTypeCd=="02"){
	 
		grid.hideColumn(grid.getColumn("dgsjlx"));
		dic="XD_KHCD0334";
	
	}else{
		grid.hideColumn(grid.getColumn("grsjlx"));
		dic="XD_KHCD0234";

	}
	
		
	if(zdsjbz==1){
		   	nui.get("query").show();
	}else{
		   	nui.get("query").hide();
	
	}
		
	if(qote==1){
		nui.get("add").hide();
	   	nui.get("edit").hide();
	   	nui.get("remove").hide();
		nui.get("query").hide(); 	   	
	}else{
		nui.get("query").show(); 
	}	
	
    function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_impornant_event_add.jsp?partyId="+partyId+"&zdsjlx="+zdsjlx+"&dic="+dic,
            title: "新增", 
            width: 800, 
        	height: 400,
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
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_impornant_event_edit.jsp?itemId="+row.importantEventId+"&view="+v+"&dic="+dic,
                title: "编辑", 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
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
            	var json=nui.encode({"item":{"importantEventId":
            		row.importantEventId,
					"_entity":"com.bos.dataset.csm.TbCsmImpornantEvent"}});
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

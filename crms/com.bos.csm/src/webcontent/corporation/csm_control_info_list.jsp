<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): cc
  - Date: 2016-05-15
  - Description:对公客户实际控制人信息
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="partyId" id="partyId" class="nui-hidden" />
	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.corporation.TbCsmControlInfo.queryTbCsmControlInfo.biz.ext" dataField="controls"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="actualControllerType" headerAlign="center" allowSort="true" dictTypeId="CDKH0034">实际控制人类型</div>
		<div field="partyName" headerAlign="center" allowSort="true" >实际控制人名称</div>
		<div field="cerType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002" >证件类型</div>
		<div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="controlMethod" headerAlign="center" allowSort="true"  dictTypeId="XD_SJCZR000" >控制方式</div>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		
	if(qote==1){
		nui.get("add").hide();
	   	nui.get("edit").hide();
	   	nui.get("remove").hide();
	}
	if (partyId) {
		nui.get("partyId").setValue(partyId);
	}
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    	git.unmask();
    }
    search();
	
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_control_info_add.jsp?partyId="+partyId,
            title: "新增", 
            width: 800, 
        	height: 300,
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
        var title="编辑";
        if('1'==v){
         	title = "查看";
        }
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_control_info_edit.jsp?id="+row.id+"&qote="+v+"&ctlType="+row.actualControllerType
                +"&partyId="+partyId,
                title: title, 
                width: 800,
        		height: 300,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
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
            	var json=nui.encode({"id":row.id});
                $.ajax({
                     url: "com.bos.csm.corporation.TbCsmControlInfo.delTbCsmControlInfo.biz.ext",
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
                    	nui.alert("删除操作失败！");
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

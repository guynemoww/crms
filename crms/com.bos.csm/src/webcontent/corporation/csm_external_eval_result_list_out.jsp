<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): wangyanli
  - Date: 2013-11-18 16:54:20
  - Description:
-->
<head>
<title>评级信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div title="外部评级" >
	<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<input name="item._entity" value="com.bos.dataset.csm.TbCsmExternalEvalResult" class="nui-hidden" />
		<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	    	<div  class="nui-toolbar" style="border:0;text-align:left">
				<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
				<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
				<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
				<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto" ，
				url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="outEvalResult" headerAlign="center" allowSort="true" >外部评级结果</div>
					<div field="resultFromOrg" headerAlign="center" allowSort="true" >评级机构</div>
					<div field="evalDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >评级日期</div>
					<div field="startDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">起始日期</div>
					<div field="endDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >到期日期</div>
					<div field="isValid" headerAlign="center" allowSort="true"  >是否有效评级</div>
					<div field="userNum" allowSort="true" headerAlign="center"  dictTypeId="user">经办人</div>    
		      		<div field="orgNum" allowSort="true" headerAlign="center" dictTypeId="org">经办机构</div>    
				</div>
			</div>
	    
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
    var nowDate= nui.parseDate('<%=com.bos.pub.GitUtil.getBusiDate()%>');
     
	grid.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
			if(nui.parseDate(e.data[i].startDate)<=nowDate&&nowDate<=nui.parseDate(e.data[i].endDate)){
				e.data[i]['isValid'] = '是';
			}else{
				e.data[i]['isValid'] = '否';
			}
		}
	});
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_external_eval_result_add.jsp?partyId="+partyId,
            title: "新增", 
            width: 800, 
        	height: 500,
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
                url: nui.context + "/csm/corporation/csm_external_eval_result_edit.jsp?itemId="+row.externalId+"&view="+v,
                title: "编辑", 
                width: 800,
        		height: 500,
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
            	var json=nui.encode({"item":{"externalId":
            		row.externalId,
					"_entity":"com.bos.dataset.csm.TbCsmExternalEvalResult"}});
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
                      	 init();
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
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:客户资质信息
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAptitudeInfo" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit"  class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
    
</div>

<div id="grid1" class="nui-datagrid" style="width:99%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="authenticationName" headerAlign="center" allowSort="true" >认证名称</div>
		<div field="aptitudeLevelCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0036">资质等级</div>
		<div field="aptitudeAuthenticationUnit" headerAlign="center" allowSort="true" >资质颁发单位名称</div>
		<div field="aptitudeStartDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">资质颁发日期</div>
		<div field="aptitudeEndDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">资质到期日期</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">登记时间</div>
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
            url: nui.context + "/csm/corporation/csm_aptitude_info_add.jsp?partyId="+partyId,
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
                url: nui.context + "/csm/corporation/csm_aptitude_info_edit.jsp?itemId="+row.aptitudeId+"&view="+v,
                title: "编辑", 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    search();
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
            	var json=nui.encode({"item":{"aptitudeId":
            		row.aptitudeId,
					"_entity":"com.bos.dataset.csm.TbCsmAptitudeInfo"}});
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
                        search();
                    },
                    error: function () {
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

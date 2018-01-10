<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAddress" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
</div>
<div  class="nui-toolbar" style="border-bottom:0;">
	<a id = "add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a id = "edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a id = "query" class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a id = "remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="addressTypeCd" headerAlign="center" allowSort="true"  dictTypeId="CDDZ0002" >地址类型</div>
		<div field="nationalityCd" headerAlign="center" allowSort="true" dictTypeId="CD000003">国家</div>
		<div field="streetAddress" headerAlign="center" allowSort="true" >街道地址/乡/镇/村</div>
		<div field="linkmanName" headerAlign="center" allowSort="true" >联系人</div>
		<%--<div field="fax" headerAlign="center" allowSort="true" >传真</div>--%>
		<div field="mobilePhone" headerAlign="center" allowSort="true" >手机</div>
		<div field="telephone" headerAlign="center" allowSort="true" >电话</div>
		<%--<div field="zipNum" headerAlign="center" allowSort="true" >邮编</div>--%>
	</div>
</div>
		
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var cType = "<%=request.getParameter("cType") %>";
	
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
        git.setDistrictsByIds('district1,district2,district3')
        git.unmask();
    }
    search();
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_address_add.jsp?partyId="+partyId+"&cType="+cType,
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                	git.mask();
                    grid.reload();
                    git.unmask();
                }
            }
        });
    }
        
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_address_edit.jsp?itemId="+row.addressId+"&view="+v+"&cType="+cType,
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
                        grid.reload();
                        git.unmask();
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
            	var json=nui.encode({"item":{"addressId":
            		row.addressId,
					"_entity":"com.bos.dataset.csm.TbCsmAddress"}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delItem.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                    	git.mask();
                        grid.reload();
                        git.unmask();
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

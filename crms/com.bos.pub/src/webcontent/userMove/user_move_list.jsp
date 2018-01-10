<%@page pageEncoding="UTF-8" %>
<html>

<head>
<%@page
	import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@include file="/common/nui/common.jsp" %>
</head>

<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="移交记录" >

	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
	<div class="nui-dynpanel" columns="4">
		<label>用户名称：</label>
		<input name="map.userName" required="false" class="nui-textbox nui-form-input"/>
		
		<label>用户编号：</label>
		<input id="map.userNum" name="map.userNum" required="false" class="nui-textbox nui-form-input"  />
        
        <label class="nui-form-label">原所在机构：</label> 
		<input id="map.originalOrgNum"name="map.originalOrgNum"allowInput="false" class="nui-buttonEdit"onbuttonclick="selectOrg" />
		
		
		<label class="nui-form-label">目标机构：</label> 
		<input id="map.targetOrgNum" name="map.targetOrgNum"allowInput="false" class="nui-buttonEdit"onbuttonclick="selectOrg" />	
					
	</div>
	
<div class="nui-toolbar" style="text-align:right;border:none" >
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>

	<div style="width:100%">	
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-add" onclick="userMove()">用户移交</a>
		</div>    
	</div>


<div id="grid1" class="nui-datagrid"sortMode="client"
	url="com.bos.pub.userMove.userMove.findUserMoveList.biz.ext"
	dataField="items" allowAlternating="true" multiSelect="false"	
	showEmptyText="true"emptyText="没有查到数据"
	allowResize="true"
	sizeList="[10,15,20,50,100]" pageSize="10" >
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="USER_NUM" headerAlign="center"  align="center"allowSort="true" >用户编号</div>
		<div field="USER_NAME" headerAlign="center" align="center" allowSort="true" >用户名称</div>
		<div field="TARGET_ORG_NUM" headerAlign="center" align="center" allowSort="true"  dictTypeId="org">目标机构</div>
		<div field="ORIGINAL_ORG_NUM" headerAlign="center"  align="center"allowSort="true" dictTypeId="org">原所在机构</div>
		<div field="HANDLE_ORG_NUM" headerAlign="center"  align="center"allowSort="true" dictTypeId="org">经办机构</div>
		<div field="HANDLE_USER_NUM" headerAlign="center"  align="center"allowSort="true" dictTypeId="user">经办人</div>
		<div field="MOVE_DATE" headerAlign="center" align="center" allowSort="true">经办日期</div>
		</div>
	</div>
</div>
</div>			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
    	git.mask();
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask();
        });
    }
    search();
    
    function reset(){
		form.reset();
	}
	

    // 客户移交
     function userMove() {
        nui.open({
            url: "<%=request.getContextPath() %>/pub/userMove/user_move_add.jsp",
            title: "用户移交", 
            width: 900, 
        	height: 300,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                    search();
            }
        });
    }
   
		//机构选择
		function selectOrg() {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/sys/select_org_tree.jsp",
						showMaxButton : true,
						title : "选择机构",
						width : 350,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.GetData();
								data = nui.clone(data);
								if (data) {
									self.orglevel = data.orglevel;
									btnEdit.setValue(data.orgcode);
									btnEdit.setText(data.orgname);
								}
							}
						}
					});
		}
	
	
	</script>
</body>
</html>

<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-10-25 13:49:23
  - Description:
-->
<head>
<title>黑名单信息列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<%--下面是查询条件div--%>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="blacklist.partyId" class="nui-hidden nui-form-input"/>
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input id="blacklist.partyName" name="blacklist.partyName" class="nui-textbox nui-form-input" />
	
		<label>CRMS客户编号：</label>
		<input id="blacklist.partyNum" name="blacklist.partyNum"  class="nui-textbox nui-form-input" />
	
		<label>ECIF客户编号：</label>
		<input id="blacklist.EcifPartyNum" name="blacklist.EcifPartyNum" class="nui-textbox nui-form-input"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset">重置</a>
	</div>
</div>	




<div style="width:99.5%;margin-top:10px">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="removeOut">移出</a>
	</div>
</div>	
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.blacklist.blacklist.queryBlackList.biz.ext" dataField="blacklists"
	    allowAlternating="true" multiSelect="true" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" 
	     onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns" >
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true"  headerAlign="center" autoEscape="false">客户名称</div>
	        <div field="partyNum" allowSort="true" headerAlign="center" autoEscape="false">CRMS客户编号</div>
	        <div field="EcifPartyNum" allowSort="true" headerAlign="center" autoEscape="false">ECIF客户编号</div>
	        <div field="status" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0248" autoEscape="false">黑名单状态</div>
	        
	        <div field="blackListReasonCd" allowSort="true" renderer="onGenderReasonCd" dictTypeId="CDKH0010" headerAlign="center" renderer="rendertype">纳入理由</div>
	     	<div field="createTime" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">纳入黑名单时间</div>
	     	<div field="isCrms" allowSort="true"  headerAlign="center" dictTypeId="XD_0002">是否本系统客户</div>
	     </div>
	 </div>
</div>
</div> <!-- tabs end -->
</center>
</body>
<script type="text/javascript">
	//初始化nui
	nui.parse();
	git.mask();
	//获取form对象
	var form = new nui.Form("#form1");
	//获取列表对象
	var grid = nui.get("datagrid1");
	//查询事件
	function query(){//黑名单客户查询
       var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
	}
	query();
	
	//重置查询条件
	function reset() {
		form.setData({});
	}

  //修改一条记录    
    function removeOut() {
     
        var rows = grid.getSelecteds();
        var blackListIds = [];
            for(var i=0; i<rows.length;i++){
				blackListIds[i] = rows[i].blackListId ;// 黑名单表的主键
            }
            if (rows.length > 0) {
        	nui.confirm("确定移出吗？","确认",function(action){
            	if(action!="ok") return;
            		git.mask();
            	var json=nui.encode({"blackListIds":blackListIds});
                $.ajax({
                     url: "com.bos.csm.blacklist.blacklist.delBlackCusts.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if(text.msg){
		            		alert(text.msg);
		            		return;
		            	} else {
							//此处应该有判断，判断是否走流程。客户经理提交走流程，其他人不走流程。
							if(text.flag){
								var node = text.node;
								openSubmitView(node);
							}else{
								alert("移出成功！");
							}
		            	}
                    	
                        git.mask();
                        query();
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
</html>
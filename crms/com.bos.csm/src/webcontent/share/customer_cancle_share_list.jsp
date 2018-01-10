<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):陈川
  - Date: 2015-06-10
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="客户列表" >
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.share.share.businessList"/>
	<input name="item.userNum"  class="nui-hidden nui-form-input" value="<%=((UserObject)session.getAttribute("userObject")).getUserId() %>"/>
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="item.partyName" required="false" class="nui-textbox nui-form-input"/>
		<label>客户类型：</label>
		<input name="item.partyTypeCd" id="item.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001"/>
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  allowInput="false" />
		<label>证件号码：</label>			
		<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" />
		 
		<label>中征码：</label>
		<input name="item.middleCode" id="item.middleCode" class="nui-textbox nui-form-input" />
		 
	</div>

<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
    <a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-add" onclick="move()">客户共享取消</a>
<!-- 			<a class="nui-button" iconCls="icon-zoomin" onclick="moveHistory()">历史共享记录</a>		 -->
	</div>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext"
	dataField="items" allowAlternating="true" 
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client" onselectionchanged="onSelectionChanged">
	<div property="columns">
		<div type="checkcolumn" ></div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
		<div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="middleCode" headerAlign="center" allowSort="true" >中征码</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">共享客户经理</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">共享机构</div>
		<div field="userPlacingCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0187">管理权限</div>
		<div field="createTime" headerAlign="center" allowSort="true" >共享日期</div>
		</div>
	</div>
</div>
</div>			

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	
	function query() {
		//字典过滤
		var arr = git.getDictDataFilter("XD_KHCD1001", '01,02,05');
		nui.get("item.partyTypeCd").setData(arr);
		grid.load(form.getData());
	}
	query();
	
	function move() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var rows = grid.getSelecteds();
		if (rows.length <= 0) {
				alert("至少选择一条记录");
				return;
		}
		nui.confirm("确认要将指定的用户取消共享？","客户取消共享提醒",
						function(action) {
							if (action != "ok") {
								return;
							}
							var json = nui.encode({"rows" : rows});
							$.ajax({
												url : "com.bos.csm.transfer.share.addCustomerCancelShare.biz.ext",
												type : 'POST',
												data : json,
												cache : false,
												contentType : 'text/json',
												success : function(text) {
													if (text.msg) {
														nui.alert(text.msg);
															query();
													}
												},
												error : function(jqXHR,
														textStatus, errorThrown) {
													nui.alert(jqXHR.responseText);
												}
											});
						});

	}
	
	//客户取消共享历史记录
     function moveHistory() {
        nui.open({
            url:  nui.context+"/csm/share/customer_cancle_share_history.jsp",
            title: "取消共享历史记录", 
            width: 1300, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
            }
        });
    }
	
	
	function onSelectionChanged() {
		var row = grid.getSelected();
		//判断在途业务
		if (row) {
			
	        var json1 = {"partyId":row.partyId,"userNum":row.userNum,"orgNum":row.orgNum};
	   
	   	    //判断流程中的业务
	   	    msg = exeRule("PUB_FLOW_CUS","1",json1);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     grid.deselect (row);
		   	     return;
	   	    }
	   	     msg = exeRule("PUB_LOAN_CUS","1",json1);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     grid.deselect (row);
		   	     return;
	   	    }
	   	      msg = exeRule("PUB_CON_CUS","1",json1);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     grid.deselect (row);
		   	     return;
	   	    }
	   	      msg = exeRule("PUB_BIZ_CUS","1",json1);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     grid.deselect (row);
		   	     return;
	   	    }
//	   	    msg = exeRule("PUB_0001","1",json1);
// 	   	    //有未结清
// 	   	    if(null != msg && '' != msg){
// 	   	    	//将是否保留业务权默认是,并且不可修改
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0002","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0003","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0004","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0005","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0006","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0007","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0008","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0009","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0010","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0011","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0012","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0013","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
// 	   	    msg = exeRule("PUB_0014","1",json1);
// 	   	    if(null != msg && '' != msg){
// 		   	     nui.alert(msg);
// 		   	     grid.deselect (row);
// 		   	     return;
// 	   	    }
		}
		
		
	}
	function reset(){
		form.reset();
	}
</script>
</body>
</html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 甘泉
  - Date: 2015-06-10
  - Description:

-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@include file="/common/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.share.share.shareCancelList"/>
	<input name="item.userNum"  class="nui-hidden nui-form-input" value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
	<input name="item.orgNum"  class="nui-hidden nui-form-input" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode") %>"/>
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="item.partyName" required="false" class="nui-textbox nui-form-input"/>
		<label>客户类型：</label>
		<input id="item.partyTypeCd" name="item.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001"  allowInput="false" />
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" required="false" />
		<label>中征码：</label>			
		<input id="item.middleCode" name="item.middleCode" class="nui-textbox nui-form-input" required="false" />
	</div>
				
<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext"
	dataField="items" allowAlternating="true" 
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client" onselectionchanged="onSelectionChanged">
	<div property="columns">
		<div type="indexcolumn" headerAlign="center" allowSort="true" >序号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
		<div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">申请共享客户经理</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">申请共享机构</div>
		<div field="typeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0187">管理权限</div>
		<div field="chanelDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">取消时间</div>
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
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       				
       			e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''
	       				+ e.data[i].partyId+'\');return false;" value="'
	       				+ e.data[i].partyId
	       				+ '">'+e.data[i]['partyName']+'</a>';
       			
       		}
    });

    // 取消共享
     function addCus() {
        nui.open({
            url: "<%=request.getContextPath() %>/csm/share/customer_cancel_share_add.jsp",
            title: "取消共享", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
   

	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onSelectionChanged() {
		var row = grid.getSelected();
		if (row) {
			var json = "{orgCode:"+nui.get("oldOrgNum").getValue()+",userNum:"+nui.get("oldUserNum").getValue()+",partyId:"+row.partyId+"}";
			$.ajax({
	            url: "com.bos.csm.pub.check.checkParty.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	       			git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            		grid.deselect (row);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
	        });
		}
	}
	</script>
</body>
</html>

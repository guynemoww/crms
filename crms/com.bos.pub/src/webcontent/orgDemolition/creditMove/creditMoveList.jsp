<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-04-15
  - Description:TB_SYS_BUSINESS_TRANSFER, com.bos.pub.sys.TbSysBusinessTransfer
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="业务/客户移交" >
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		
		<label>客户名称：</label>
		<input name="tbSysBusinessTransfer.partyName" required="false" class="nui-textbox nui-form-input"/>
		<label>客户编号：</label>
		<input name="tbSysBusinessTransfer.partyNum" required="false" class="nui-textbox nui-form-input"/>
		<label>移交状态：</label>
		<input name="tbSysBusinessTransfer.repFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD7493" emptyText="请选择"/>
		<label>移交性质：</label>
		<input name="tbSysBusinessTransfer.transferType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD234" emptyText="请选择"/>
		<label>移交类型：</label>
		<input name="tbSysBusinessTransfer.status" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GG3920" emptyText="请选择"/>
		<label>移交时间：</label>
		<div>
		<input name="tbSysBusinessTransfer.handingDate1" id="removestart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate1"/>~
		<input name="tbSysBusinessTransfer.handingDate2" id="removeend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate1"/>
		</div>
	</div>
</div>
				
<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-goto" onclick="add()">业务移交</a>
	<a class="nui-button" iconCls="icon-add" onclick="addCus()">客户移交</a>
	<a class="nui-button" iconCls="icon-user" onclick="addUser(1)">用户移交</a>
	
	<!-- 
	<a class="nui-button" iconCls="icon-add" onclick="addUser(2)">机构拆并</a>
	 -->
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.orgDemolition.getTbSysBusinessTransferList.biz.ext"
	dataField="tbSysBusinessTransfers"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="approvalNum" headerAlign="center" allowSort="true" >批复编号</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<!-- 
		<div field="manageTeamStatus" headerAlign="center" allowSort="true" dictTypeId="CsmTeamAuthorityType">管理团队性质</div>
		 -->
		<div field="newOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">目标机构</div>
		<div field="newUserNum" headerAlign="center" allowSort="true" dictTypeId="user">目标用户</div>
		<div field="oldOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">原开户行机构</div>
		<div field="oldUserNum" headerAlign="center" allowSort="true" dictTypeId="user">原用户</div>
		<!-- 
		<div field="repFlag" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD7493">移交状态</div>
		 -->
		<div field="transferType" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD234">移交性质</div>
		<div field="status" headerAlign="center" allowSort="true" dictTypeId="XD_GG3920">移交类型</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
		<div field="handingDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd HH:mm:ss">经办日期</div>
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
	// 业务移交
    function add() {
        nui.open({
            url: "<%=request.getContextPath() %>/pub/orgDemolition/creditMove/creditMoveAdd.jsp",
            title: "业务移交", 
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
    // 客户移交
     function addCus() {
        nui.open({
            url: "<%=request.getContextPath() %>/pub/orgDemolition/cusMove/cusMoveAdd.jsp",
            title: "客户移交", 
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
    // 用户移交
     function addUser(v) {
        nui.open({
            url: "<%=request.getContextPath() %>/pub/orgDemolition/userMove/userMoveAdd.jsp?v="+v,
            title: "用户或机构移交", 
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
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "<%=request.getContextPath() %>/pub/orgDemolition/creditMove/creditMoveEdit.jsp?orgcbAppId="+row.orgcbAppId+"&view="+v,
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
        if(row.transferType=="01"){
         alert("全部移交不能删除业务");
        return;
        }else{
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbSysBusinessTransfer":{"orgcbAppId":
            		row.orgcbAppId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.orgDemolition.delTbSysBusinessTransfer.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
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
        }
        	
        } else {
            alert("请选中一条记录");
        }
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
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
	function onregDueDate1(){
		var laidstart = nui.get("removestart").getValue();//起始日期
		var laidend = nui.get("removeend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("removeend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removestart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate1(){
		var laidstart = nui.get("removestart").getValue();//起始日期
		var laidend = nui.get("removeend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removeend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}	
////////////////////日期判断结束
	</script>
</body>
</html>

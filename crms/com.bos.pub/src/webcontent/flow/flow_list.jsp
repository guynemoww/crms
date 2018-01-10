<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-10
  - Description:TB_SYS_FLOW_TEST, com.bos.pub.sys.TbSysFlowTest
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="partyId" class="nui-hidden" name="tbSysFlowTest.partyId"/>
	<input id="applyId" class="nui-hidden" name="tbSysFlowTest.applyId"/>
	<div class="nui-dynpanel" columns="4">
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()" id="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)" >查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="remove">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.flow.getTbSysFlowTestList.biz.ext"
	dataField="tbSysFlowTests"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="testType" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD03932">测算类型</div>
		<div field="newFlowFundLoan" headerAlign="center" allowSort="true" >新增流动资金贷款额度</div>
<%--		<div field="borrowerOperateFund" headerAlign="center" allowSort="true" >借款人营运资金量</div>
		<div field="nowFlowFundLoan" headerAlign="center" allowSort="true" >现有流动资金贷款</div>
		<div field="otherApplyOperateFund" headerAlign="center" allowSort="true" >其他渠道提供的营运资金</div>--%>
		<div field="testDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">测算日期</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">测算机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">测算人</div>
	</div>
</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var applyId="<%=request.getParameter("applyId") %>";//业务申请ID
	var proFlag="<%=request.getParameter("proFlag") %>";
	if(proFlag!="1"){   //BUG #4865 
		nui.get("add").hide();
		nui.get("remove").hide();
	} 
	nui.get("applyId").setValue(applyId);
    function search() {
    var pjson=nui.encode({"bizApply":{"applyId":
            		applyId}});
                $.ajax({
                     url: "com.bos.pub.flow.getPartyId.biz.ext",
	                type: 'POST',
	                data: pjson,
	                cache: false,
	                contentType:'text/json',
	                async: true, //异步处理
                    success: function (text) {
                    	nui.get("partyId").setValue(text.bizApply1.partyId);
                    	var data = form.getData(); //获取表单多个控件的数据
        				grid.load(data);
                    		return;
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
					
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context+"/pub/flow/flow_type.jsp?applyId="+nui.get("applyId").getValue()+"&partyId="+nui.get("partyId").getValue(),
            title: "测算类型", 
            width: 500, 
        	height: 300,
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
                url: nui.context+"/pub/flow/flow_edit.jsp?testFlowNo="+row.testFlowNo+"&view="+v,
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
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbSysFlowTest":{"testFlowNo":
            		row.testFlowNo,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.flow.delTbSysFlowTest.biz.ext",
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
        } else {
            alert("请选中一条记录");
        }
    }

	</script>
</body>
</html>

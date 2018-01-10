<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25
  - Description:TB_ACC_CUSTOMER_FINANCE, AccCustomerFinance
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:90%;height:auto;overflow:hidden; text-align:left">
<input type="hidden" name="accCustomerFinance._entity" value="com.bos.dataset.acc.TbAccCustomerFinance" class="nui-hidden" />
<input type="hidden" name="accCustomerFinance.partyId" value="<%=request.getParameter("applyId")%>"  class="nui-hidden" />
</div>

<div class="nui-toolbar" style="border-bottom:0;">
	<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.acc.acccustomerfinance.getAccCustomerFinanceList.biz.ext"
	dataField="accCustomerFinances"allowAlternating="true"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="financeDeadline" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >财务报表日期</div>
		<div field="financeTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCCD0001">财务报表类型</div>	
		<div field="caliberCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0071">财务报表口径</div>
		<div field="customerTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCCD0002">财务报表类别</div>
		<div field="auditedInd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCD0003">是否经过审计</div>
		<div field="financeStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCCD0004">财务报表状态</div>	
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">操作人员</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">操作机构</div>
		<div field="updateTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >操作日期</div>
	</div>
</div>
		
    <script type="text/javascript">
    nui.parse();
    var proFlag = "<%=request.getParameter("proFlag")%>";
    var cusType = "<%=request.getParameter("cusType")%>";
    //与业务挂钩，applyId存在客户财务报表partyId下面
    var partyId = "<%=request.getParameter("applyId")%>";
    if(proFlag!=1){
		nui.get("add").hide();
	    nui.get("edit").hide();
	    nui.get("remove").hide();
	}
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context +"/acc/acccustomerfinance/acccustomerfinance_add.jsp?partyId="+partyId+"&cusType="+cusType,
            showMaxButton: true,
            title: "新增", 
            width: 1024,
	        height: 540,
            ondestroy: function (action) {
               // if(action=="ok"){
                    search();
               // }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
          if(row.financeStatusCd!='01' && v!='1'){
            alert('只能修改未生效的财报');
            return;
          }
          var title ="编辑";
          if('1'==v){
          	title="查看";
          }
            nui.open({
                url:nui.context + "/acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+row.financeId+"&view="+v+"&reportType="+row.customerTypeCd
                +"&financeTypeCd="+row.financeTypeCd+"&cusType="+cusType,
                showMaxButton: true,
                title: title, 
                width: 1024,
	            height: 540,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
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
            if(row.financeStatusCd!='01'){
            alert('只能删除未生效的财报');
            return;
           }
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"accCustomerFinance":{"financeId":
            		row.financeId,version:row.version}});
                $.ajax({
                     url: "com.bos.acc.acccustomerfinance.delAccCustomerFinance.biz.ext",
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

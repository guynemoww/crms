<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-06-24 08:41:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>保证金</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input name="bizGrtRel.applyId"  class="nui-hidden"  id="bizGrtRel.applyId"  value="<%=request.getParameter("applyId") %>" />
	</div>
	
			
	<div id="panel1" class="nui-panel" title="保证金"
		style="width:100%;height:auto;" showToolbar="false"
		showCollapseButton="true" showFooter="false" allowResize="false">
		
	<a class="nui-button" iconCls="icon-add" id="biz_single_cash_add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" id="biz_single_cash_edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" id="biz_single_cash_view" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" id="biz_single_cash_del" onclick="remove()">删除</a>

		<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
		    url="com.bos.grt.grtMainManage.grtGuarantee.queryCashDeposit.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="false"allowAlternating="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
			<div property="columns">
		        <div type="checkcolumn">选择</div>
		        <div type="indexcolumn">序号</div>
		        <div field="MARGIN_SORT" allowSort="true"  headerAlign="center" dictTypeId="XD_YWDB0134">保证金类型</div>
		        <div field="OPEN_BANK" allowSort="true"  headerAlign="center" dictTypeId="org">开户行</div>
		        <div field="ACCT_NAME" allowSort="true"  headerAlign="center">账户名称</div>
		        <div field="CURRENCY_CD" allowSort="true"  headerAlign="center" dictTypeId="CD000001">币种</div>
		        <div field="ACC_BALANCE" allowSort="true" width="" headerAlign="center">保证金金额</div>   
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div>
<!-- 		        <div field="IS_JIXI" allowSort="true" headerAlign="center" dictTypeId="XD_0002">是否计息</div> -->
		        <div field="MARGIN_RATE" allowSort="true" width="" headerAlign="center" >执行利率(%)</div>       
		     </div>
		</div>

	    	
	</div>

<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	
	var form = new nui.Form("#form1");
	
	//客户ID
	var partyId="<%=request.getParameter("partyId")%>";
	//业务ID
    var applyId="<%=request.getParameter("applyId") %>";
    //抵押/质押/保证金
    var collType="<%=request.getParameter("collType") %>";
    
    var proFlag = "<%=request.getParameter("proFlag") %>";
    
    initPage();
	function initPage(){
		//质押在业务申请担保方式为02，在抵质押方式为03
		var json = nui.encode({"applyId":applyId,"guarantyType":"05"});
		$.ajax({
            url: "com.bos.bizInfo.bizGrt.getBizGrtType.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	var ifFlag = o.ifFlag;
            	if('0' == ifFlag){
    		    	nui.get("biz_single_cash_add").hide();
			    	nui.get("biz_single_cash_edit").hide();
			    	nui.get("biz_single_cash_view").hide();  
			    	nui.get("biz_single_cash_del").hide();
			    }	
    	        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
				if(proFlag!='1'){
			    	nui.get("biz_single_cash_add").hide();
			    	nui.get("biz_single_cash_edit").hide();
			    	nui.get("biz_single_cash_del").hide();
			    }
			    reload();
			}
        });
	}
	
	function reload(){
		git.mask();
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
	}
	
	function add(){
		nui.open({
            url: nui.context + "/grt/guaranMainManager/guarantee_apply_cash_deposit_add.jsp?partyId="+partyId+"&applyId="+applyId+"&collType="+collType,
            showMaxButton: true,
            title: "添加保证金",
            width: 950,
            height: 400,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
                reload();
            }
        });
	}
	
	function edit(v){
		var row = grid.getSelected();
        var title1;
        if(v == "0"){
        	title1 = "编辑";
        }else if(v == "1"){
        	title1 = "查看";
        }
        if (row) {
        	var suretyKeyId=row.SURETY_KEY_ID;
			nui.open({
	        	url: nui.context + "/grt/guaranMainManager/guarantee_apply_cash_deposit_edit.jsp?view="+v+"&suretyKeyId="+suretyKeyId,
	                title: title1, 
	                width: 950,
	        		height: 400,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                },
	                ondestroy: function (action) {
	                    reload();
	                }
	            });        	
        } else {
            nui.alert("请选中一条记录");
        }
	}
	
	function remove(){
		var row = grid.getSelected();
	 	if (row) {
			nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"suretyId":row.SURETY_ID,"relationId":row.RELATION_ID,"suretyKeyId":row.SURETY_KEY_ID});
            	$.ajax({
		            url: "com.bos.grt.grtMainManage.grtGuarantee.deleteCashDeposit.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	nui.alert(text.msg); 
		            	reload();
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
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
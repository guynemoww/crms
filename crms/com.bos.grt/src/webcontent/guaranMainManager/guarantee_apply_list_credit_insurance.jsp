<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-06-26 13:54:11
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>信用保险查询列表</title>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
	<input name="applyId" class="nui-hidden" value="<%=request.getParameter("applyId") %>" />
	<input name="partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>" />
	<input name="collType" class="nui-hidden" value="<%=request.getParameter("collType") %>" />
</div>
	
<div id="panel1" class="nui-panel" title="信用保险"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">  
	
	<a class="nui-button" iconCls="icon-add" onclick="add()" id="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="edit">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)" id="view">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="remove">删除</a>
	
	 
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.grt.guaranMainManager.guaranteeApply.getGuaranteeApplyTbGrtCreditsafe.biz.ext"
	dataField="grtCreditsafes"
	allowResize="false" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns"> 
		<div type="checkcolumn" >选择</div>
		<div type="indexcolumn">序号</div>
		<!-- <div field="SURETY_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0131">保证类型</div> -->
		<div field="POLICY_HOLDER" headerAlign="center" allowSort="true" >投保人</div>
		<div field="CURRENCY_CD" allowSort="true"  headerAlign="center" dictTypeId="CD000001">币种</div>
		<div field="CREDIT_LIMIT" headerAlign="center" allowSort="true" dataType="currency">信用限额</div>
		<div field="BEGIN_DATE" headerAlign="center" allowSort="true" >生效日期</div>
		<div field="END_DATE" headerAlign="center" allowSort="true" >到期日期</div>
		<div field="GUARANTEE_MONEY" allowSort="true" width="" headerAlign="center"dataType="currency">本次担保金额</div>
	</div>
</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    
    // 业务申请ID
    var applyId = "<%=request.getParameter("applyId") %>";
    // 客户Id
    var partyId = "<%=request.getParameter("partyId") %>";
    //抵质押类型
    var collType = "<%=request.getParameter("collType") %>";
    
    var proFlag = "<%=request.getParameter("proFlag") %>";
    initPage();
	function initPage(){
		//质押在业务申请担保方式为02，在抵质押方式为03
		var json = nui.encode({"applyId":applyId,"guarantyType":"01"});
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
			    	nui.get("add").hide();
			    	nui.get("edit").hide();
			    	nui.get("view").hide();
			    	nui.get("remove").hide();
			    }	
    	        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
				if(proFlag!='1'){
			    	nui.get("add").hide();
			    	nui.get("edit").hide();
			    	nui.get("remove").hide();
			    }
			    search();
			}
        });
	}
    
    function add() {
    	nui.open({
			url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_creditsafe_add.jsp?applyId="+applyId+"&partyId="+partyId+"&collType="+collType,
			title: "新增信用保险",
			width: 800, 
			height: 400,
			allowResize:false,
        	allowDrag: false,
			showMaxButton: false,
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
        	//标题
        	var contitle2;
        	if("0" == v){
        		contitle2 = "编辑";
        	}else if("1" == v){
        		contitle2 = "查看";
        	}
        	var suretyId=row.SURETY_ID;
        	nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_creditsafe_add.jsp?applyId="+applyId+"&partyId="+partyId+"&collType="+collType+"&view="+v+"&suretyId="+suretyId,
				title: contitle2+"信用保险",
				width: 800, 
				height: 400,
				allowResize:false,
	        	allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action=="ok"){
						search();
					}
				}
			}); 
        } else {
            nui.alert("请选中一条记录");
        }
        
    }
    
    function remove() {
    	var row = grid.getSelected();
        if (row) {    
	    	 nui.confirm("确定删除吗？","确认",function(action){
	        	if(action!="ok") return;
    			var relationId=row.RELATION_ID;
	        	var suretyId=row.SURETY_ID;
	        	var json=nui.encode({"grtCreditsafe":{"suretyId":suretyId},"bizGrtRel":{"relationId":relationId}});
				git.mask();
				$.ajax({
		        	url: "com.bos.grt.guaranMainManager.guaranteeApply.delGuaranteeApplyTbGrtCreditsafe.biz.ext",
		        	type: 'POST',
		        	data: json,
		        	cache: false,
		        	contentType:'text/json',
		        	success: function (text) {
		        		nui.alert(text.msg);
		        		search();
		        		git.unmask();
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

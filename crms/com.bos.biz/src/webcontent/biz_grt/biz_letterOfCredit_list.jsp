<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 吕健豪
  - Date: 2014-03-30 16:18:58
  - Description:
-->
<head>
<title>备用信用证</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<!--<input name="item.applyId" id="item.applyId" class="nui-hidden"  />-->
</div>

<div id="panel1" class="nui-panel" title="备用信用证"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.biz.Collateral.getCollateralList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
	        <div field="lcEfficientName" allowSort="true" width="" headerAlign="center">开证行名称</div>
	        <div field="lcApplicantName" allowSort="true" width="" headerAlign="center" >开证申请人名称</div>
	        <div field="beneficiaryName" allowSort="true" width="" headerAlign="center" >受益人名称</div>       
	        <div field="lcAmount" allowSort="true" width="" headerAlign="center" dataType="currency">信用证金额</div>   
	        <div field="availableAmt" allowSort="true" width="" headerAlign="center" dataType="currency">可用金额</div>   
	        <div field="occupiedAmt" allowSort="true" width="" headerAlign="center" dataType="currency">已用金额</div>   
	        <div field="debtAmount" allowSort="true" width="" headerAlign="center">本次占金额</div>
	     </div>
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-add" id="add" onclick="add">增加</a>
    	<a class="nui-button" iconCls="icon-edit" id="edit" onclick="edit(0)">编辑</a>
		<!--<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>-->
		<a class="nui-button" iconCls="icon-remove" id="remove" onclick="remove()">删除</a>
	</div>
</div>


<script type="text/javascript">
 
	nui.parse();
	var grid = nui.get("datagrid1");
	 <%--grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			//alert(nui.encode(e.data[i]['tbCsmParty']));
       			if (e.data[i]['GUARANTY_USE_AMT'] > 0)
       				e.data[i]['rate']=e.data[i]['GUARANTY_AMT']/e.data[i]['GUARANTY_USE_AMT'];
       			if (e.data[i]['rate']>0)
       				e.data[i]['rate']=new Number(e.data[i]['rate']).toPrecision(4);
       		}
       });
    function getInit() {
		nui.get("datagrid1").load({bizId:<%=request.getParameter("bizId") %>});
	}
	getInit();--%>
	
	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	<%--var bizId = "<%=request.getParameter("bizId") %>";--%>
		
    function search() {
		<%--if (bizId) {
			nui.get("item.applyId").setValue(bizId);
		}--%>
		var data = form.getData(); //获取表单多个控件的数据
         grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"04"}});
    }
    search();
		nui.get("add").hide();
	  	nui.get("edit").hide();
	  	nui.get("remove").hide();
	      if("<%=request.getParameter("type") %>"=="04"){
    	nui.get("add").hide()
    	nui.get("edit").show();
  	  	nui.get("remove").show();
  	  	check("0204");
    }
    else if("<%=request.getParameter("type") %>"=="0"){
  	  nui.get("add").hide();
  	  nui.get("edit").hide();
  	  nui.get("remove").hide();
  	  check("0204");
    }
    else if("<%=request.getParameter("type")%>"=="0606"||"<%=request.getParameter("type")%>"=="01"||"<%=request.getParameter("type")%>"=="null"){
    	nui.get("add").show();
  	 	nui.get("edit").show();
  	  	nui.get("remove").show();
  	  	check("0204");
    }
    check("0204");
    
    function check(colType){
    	var json= nui.encode({"applyId":"<%=request.getParameter("bizId") %>"});
    	$.ajax({
	            url: "com.bos.biz.Collateral.getCollateralGuaranty.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		
	            		if(text.col.indexOf(colType)>=0){
	            			nui.get("add").show();
					  	 	nui.get("edit").show();
					  	  	nui.get("remove").show();
	            			return true;
	            		}else{
	            			nui.get("add").hide();
  	  						nui.get("edit").hide();
  	 						nui.get("remove").hide();
	            			return false;
	            		}
	            		
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
    	
    	
    
    }
		
	
    //var bizid = document.getElementById(bizid);
	function add(){
		nui.open({
            url: nui.context + '/biz/biz_grt/biz_letterOfCredit_add.jsp?bizId=<%=request.getParameter("bizId")%>',
            showMaxButton: true,
            title: "添加关联关系",
            width: 950,
            height: 550,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            	//alert(text);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                 grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"04"}});
                }
            }
        });
	}
	
	function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/biz/biz_grt/biz_letterOfCredit_view.jsp?&relationId="+row.relationId+"&type=<%=request.getParameter("type") %>",
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
                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"04"}});
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
            	var json=nui.encode({"item":{"relationId":
            		row.relationId,
					"_entity":"com.bos.dataset.biz.TbBizGrtRelation"}});
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
                       grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"04"}});
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

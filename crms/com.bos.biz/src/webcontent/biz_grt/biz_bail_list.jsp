<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 吕健豪
  - Date: 2014-03-30 16:18:58
  - Description:
-->
<head>
<title>单一客户质押</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<!--<input name="item.applyId" id="item.applyId" class="nui-hidden"  />-->
</div>


<div id="panel1" class="nui-panel" title="质押"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="false">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
	    url="com.bos.biz.Collateral.getCollateralList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="false"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true" width="" headerAlign="center">质押人名称</div>
	        <div field="sortType" allowSort="true" width="" dictTypeId="XD_DBCD4002" headerAlign="center">质押物类型</div>
	        <div field="assessCost" allowSort="true" width="" headerAlign="center" dataType="currency">评估价值</div>
	        <div field="mybankSetAmt" allowSort="true" width="" headerAlign="center" dataType="currency">我行已设定担保额</div>       
	        <div field="occupationAmount" allowSort="true" width="" headerAlign="center" dataType="currency">担保债权金额</div>   
	        <div field="mortgageRate" allowSort="true" width="" headerAlign="center">质押率(%)</div>
	        <div field="guaMoneyProportion" allowSort="true" width="" headerAlign="center">担保比例(%)</div>
	        <div field="suretyId" name="suretyId" allowSort="true" width="" headerAlign="center">押品ID</div>
	     </div>
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-add" id="biz_single_pledge_add" onclick="addAppGerenal">增加</a>
    	<a class="nui-button" iconCls="icon-add" id="biz_single_pledge_insert" onclick="insert">引入</a>
    	<a class="nui-button" iconCls="icon-edit" id="biz_single_pledge_edit" onclick="edit(0)">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" id="biz_single_pledge_remove" onclick="remove()">删除</a>
	</div>
</div>
</br>
<div id="panel1" class="nui-panel" title="最高额担保合同关联"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="false">
		<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
	    url="com.bos.biz.Collateral.getMaxloanconList.biz.ext" dataField="loanconList"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="false"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
			<div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true"  width="" headerAlign="center">担保人名称</div>
	        <div field="subcontractNum" allowSort="true"  width="" headerAlign="center">担保合同编号</div>
	        <div field="guaranteeRightMoney" allowSort="true"  width="" headerAlign="center">担保合同担保债权金额</div>
		</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" iconCls="icon-add" id="biz_single_highPle_insert" onclick="insert2">引入</a>
	    	<a class="nui-button" iconCls="icon-edit" onclick="edit2(1)">查看</a>
			<a class="nui-button" iconCls="icon-remove" id="biz_single_highPle_remove" onclick="remove2()">删除</a>
		</div>
</div>
<script type="text/javascript">
 
	nui.parse();
	var grid = nui.get("datagrid1");
	grid.hideColumn(grid.getColumn("suretyId"));
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
	
	<%--var bizId = "<%=request.getParameter("bizId") %>";--%>
	var proFlag = "<%=request.getParameter("proFlag") %>";
    function search() {
        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
    }
    search();
    
    var grid2 = nui.get("datagrid2");
    function search2() {
		<%--if (bizId) {
			nui.get("item.applyId").setValue(bizId);
		}--%>
        grid2.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","bizCustType":"01","reType":"02"}});
    }
    search2();
	
	 <%--nui.get("biz_single_pledge_add").hide();
  	  nui.get("biz_single_pledge_edit").hide();
  	  nui.get("biz_single_pledge_remove").hide();
  	  // nui.get("biz_single_pledge_insert").hide();
  	    nui.get("biz_single_highPle_edit").hide();
  	  nui.get("biz_single_highPle_remove").hide();
  	   nui.get("biz_single_highPle_insert").hide();
  	  
    if("<%=request.getParameter("type") %>"=="04"){
    	nui.get("biz_single_pledge_add").hide()
    	nui.get("biz_single_pledge_edit").show();
  	  	nui.get("biz_single_pledge_remove").show();
  	  	check("0202");
    }
    else if("<%=request.getParameter("type") %>"=="0"){
  	  nui.get("biz_single_pledge_add").hide();
  	  nui.get("biz_single_pledge_edit").hide();
  	  nui.get("biz_single_pledge_remove").hide();
  	  check("0202");
    }
    else if("<%=request.getParameter("type")%>"=="0606"||"<%=request.getParameter("type")%>"=="01"||"<%=request.getParameter("type")%>"=="null"){
    	nui.get("biz_single_pledge_add").show();
  	 	nui.get("biz_single_pledge_edit").show();
  	  	nui.get("biz_single_pledge_remove").show();
  	  	check("0202");
    }--%>
    check("020201");
    
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
	            		
	            	} else {
	            		if(proFlag=="0"){
	            			nui.get("biz_single_pledge_add").hide();
  	  						nui.get("biz_single_pledge_edit").hide();
  	 						nui.get("biz_single_pledge_remove").hide();
  	 						nui.get("biz_single_pledge_insert").hide();
  	  						nui.get("biz_single_highPle_remove").hide();
  	   						nui.get("biz_single_highPle_insert").hide();
	            		}else{
		            		if(text.col.indexOf(colType)>=0){
		            			
		            			nui.get("biz_single_pledge_add").show();
						  	 	nui.get("biz_single_pledge_edit").show();
						  	  	nui.get("biz_single_pledge_remove").show();
						  	  	nui.get("biz_single_pledge_insert").show();
	  	 						nui.get("biz_single_highPle_remove").show();
	  	  						nui.get("biz_single_highPle_insert").show();
		            			return true;
		            		}else{
		            			nui.get("biz_single_pledge_add").hide();
	  	  						nui.get("biz_single_pledge_edit").hide();
	  	 						nui.get("biz_single_pledge_remove").hide();
	  	 						nui.get("biz_single_pledge_insert").hide();
	  	  						nui.get("biz_single_highPle_remove").hide();
	  	   						nui.get("biz_single_highPle_insert").hide();
		            			return false;
		            		}
	            		}
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
    	
    	
    
    }
    
    //贷款申请时增加押品，在确定客户后选择押品类型
    
    function addAppGerenal(){
    	 nui.open({
			url:nui.context+"/grt/collateralparameter/colllsortparameter/selectCollateralSortInfoTree.jsp?collType=020201",
			title:"请选择押品分类",
			width:320,
			height:360,
			allowResize: false,
			showMaxButton: true,
			ondestroy:function(action){
				var iframe = this.getIFrameEl();
				var data = iframe.contentWindow.GetData();
				data = nui.clone(data);
				if (data) {
					sortType = data.sortType;
					parentSortType = data.parentSortType;
					localGeneral(sortType,parentSortType);
				}
				grid.reload();
			}
		});
    }
    
    //确定押品分类后开始填写概况信息
    function localGeneral(sortType,parentSortType){
    	//01010101保证金活期
    	//01010102保证金定期 
    	if("01010101" == sortType || "01010102" == sortType){
			nui.open({
				url: git.toUrlParam(nui.context+"/grt/grtMainMargin/applyMargin_add.jsp?sortType="+sortType+"&parentSortType="+parentSortType+"&applyId=<%=request.getParameter("bizId") %>&type=<%=request.getParameter("type") %>&collType=020201&reType=02&bizCustType=01&bizCustType=01&partyId="+"<%=request.getParameter("partyId")%>"),
				title: "新增保证金", 
				width: 800, 
				height: 500,
				allowResize: false,
				showMaxButton: false,
				ondestroy: function (action) {
					search();
				}
			}); 
    	}else{
    		nui.open({
				url: git.toUrlParam(nui.context+"/grt/grtMainManage/grt_apply_colmanage_add.jsp?sortType="+sortType+"&parentSortType="+parentSortType+"&applyId=<%=request.getParameter("bizId") %>&type=<%=request.getParameter("type") %>&collType=020201&reType=02&bizCustType=01"),
				title:"新增押品信息",
				width:800,
				height:500,
				allowResize: false,
				showMaxButton: true,
				ondestroy:function(action){
					if("ok"==action){
						search();
					}
				}
			});
		}
    }
    
    
    
    //var bizid = document.getElementById(bizid);
	/*function add(){
		nui.open({
            url: nui.context + '/grt/manage/TbGrtGeneralAppinfo/TbGrtGeneralAppinfo_add.jsp?bizId=<%=request.getParameter("bizId")%>&collType=020201&reType=02&bizCustType=01',
            showMaxButton: true,
            title: "添加押品信息",
            width: 950,
            height: 550,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            	//alert(text);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                  grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
                }
            }
        });
		//self.location.href=nui.context + "/biz/cus_grt1.jsp?bizId=<%=request.getParameter("bizId")%>"; 
	}*/
	
	function insert(v) {
            nui.open({
                url: nui.context + "/grt/grtMainManage/grt_apply_colmanage_import.jsp?applyId=<%=request.getParameter("bizId") %>&type=<%=request.getParameter("type") %>&collType=020201&reType=02&bizCustType=01",
                title: "引入押品", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
               	 	}
                }
            });
    }
	function insert2() {
            nui.open({
                url: nui.context + "/biz/biz_grt/selectMaxLoancon.jsp?applyId=<%=request.getParameter("bizId") %>&subcontractTypeCd=02&bizCustType=01&reType=02&partyId=<%=request.getParameter("partyId") %>",
                title: "引入最高额担保合同", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    //var data = row;
                },
                ondestroy: function (action) {
                	search2();
                }
            });
    }
	
	//编辑押品信息
	function edit(v) {
        var row = grid.getSelected();
        var title1;
        if(v == "0"){
        	title1 = "编辑";
        }else if(v == "1"){
        	title1 = "查看";
        }
        if (row) {
        	if("01010101" == row.sortType || "01010102" == row.sortType){
        		nui.open({
	                url: nui.context + "/grt/grtMainMargin/applyMargin_edit.jsp?&relationId="+row.relationId+"&suretyId="+row.suretyId+"&occupationAmount="+row.occupationAmount+"&type=<%=request.getParameter("type") %>&bizCustType=01&guaMoneyProportion=" +row.guaMoneyProportion+"&view="+v,
	                title: title1, 
	                width: 800,
	        		height: 500,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                },
	                ondestroy: function (action) {
	                    if(action=="ok"){
	                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
	               	 	}
	                }
	            });
        	}else{
	        	nui.open({
	                url: nui.context + "/grt/grtMainManage/grt_apply_colmanage_edit.jsp?&relationId="+row.relationId+"&suretyId="+row.suretyId+"&occupationAmount="+row.occupationAmount+"&type=<%=request.getParameter("type") %>&bizCustType=01"+"&sortType="+row.sortType+"&parentSortType="+row.parentSortType+"&guaMoneyProportion="+row.guaMoneyProportion+"&view="+v,
	                title: title1, 
	                width: 800,
	        		height: 500,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                },
	                ondestroy: function (action) {
	                    if(action=="ok"){
	                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
	               	 	}
	                }
	            });
        	}
            
        } else {
            nui.alert("请选中一条记录");
        }
        
    }
    
    function edit2(v) {
        var row = grid2.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/grt/grtMainManage/topSubcontractView/security_sub_contract_list.jsp?&subcontractId="+row.subcontractId+"&view="+v,
                title: "查看", 
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
                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
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
                       grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
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
    
    function remove2() {
        var row = grid2.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"maxloanconId":row.maxloanconId,"bizCustType":"01"}});
                $.ajax({
                     url: "com.bos.biz.Collateral.deleteMaxloancon.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                       	search2();
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

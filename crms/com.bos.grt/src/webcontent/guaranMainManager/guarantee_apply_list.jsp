<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-11 13:54:11
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>保证人</title>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
	<input name="bizGrtRel.applyId" required="true" class="nui-hidden" value="<%=request.getParameter("applyId") %>" />
</div>

<div id="panel1" class="nui-panel" title="保证人"style="width:100%;height:auto;" showToolbar="false"showCollapseButton="true" showFooter="false" allowResize="true">   

	<a class="nui-button" iconCls="icon-add" onclick="add()" id="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="edit">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)" id="view">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="remove">删除</a>
	
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" allowAlternating="true"
	url="com.bos.grt.guaranMainManager.guaranteeApply.getGuaranteeApplyTbGrtGuaranteeBasicList.biz.ext"
	dataField="guaranteeBasicsList"allowResize="false" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="5" sortMode="client">
	<div property="columns"> 
		<div type="checkcolumn" >选择</div>
		<div type="indexcolumn">序号</div>
		<!-- <div field="GUARANTEE_TYPE" allowSort="true"  headerAlign="center" dictTypeId="XD_YWDB0131">保证类型</div> bug5607-->
		<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证人名称</div>
		<div field="USED_GUARANTEE_LIMIT" allowSort="true"  headerAlign="center"dataType="currency">已担保金额</div>
		<div field="SURETY_AMT" allowSort="true" width="" headerAlign="center"dataType="currency">申请担保金额</div>
	</div>
</div>

</div>
<br>
<div id="panel2" class="nui-panel" title="最高额担保合同关联"style="width:100%;height:auto;" showToolbar="false"showCollapseButton="true" showFooter="false" allowResize="false">
	
	    	<a class="nui-button" iconCls="icon-add" id="biz_gs_dy_maxLoan_insert" onclick="insertMaxLoan">引入</a>
	    	<a class="nui-button" iconCls="icon-zoomin"  id="biz_gs_dy_maxLoan_view" onclick="editMaxLoan(1)">查看</a>
			<a class="nui-button" iconCls="icon-remove" id="biz_gs_dy_maxLoan_remove" onclick="removeMaxLoan()">删除</a>
		
	<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.bizInfo.bizGrt.getMaxloanconList.biz.ext" dataField="maxLoanBizs"allowAlternating="true"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="false"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="5">
	    <div property="columns">
			<div type="checkcolumn">选择</div>
			<div type="indexcolumn">序号</div>
	        <div field="PARTY_NAME" allowSort="true"  headerAlign="center">担保人名称</div>
	        <div field="SUBCONTRACT_NUM" allowSort="true"  headerAlign="center">担保合同编号</div>
	        <div field="ZGBJXE" allowSort="true"  headerAlign="center"dataType="currency">担保合同金额</div>
	        <div field="AVI_AMT" allowSort="true"  headerAlign="center"dataType="currency">可用担保金额</div>
	        <div field="BEGIN_DATE" allowSort="true" headerAlign="center">担保额度起期</div>
	       	<div field="END_DATE" allowSort="true"   headerAlign="center" >担保额度止期</div>
		</div>
		</div>

</div>
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	grid.hideColumn(grid.getColumn("suretyId"));
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        
         grid.on("preload",function(e){
	    	if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
			}
	    });
    }
    
    // 押品类型
    var collType = "<%=request.getParameter("collType") %>";
    // 业务申请Id
    var applyId = "<%=request.getParameter("applyId") %>";
    //客户ID
    var partyId = "<%=request.getParameter("partyId") %>";
    
    var proFlag = "<%=request.getParameter("proFlag") %>";
    
    initPage();
	function initPage(){
		//质押在业务申请担保方式为02，在抵质押方式为03
		var json = nui.encode({"applyId":applyId,"guarantyType":"04"});
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
			    	nui.get("biz_gs_dy_maxLoan_insert").hide();
			    	nui.get("biz_gs_dy_maxLoan_view").hide();
			    	nui.get("biz_gs_dy_maxLoan_remove").hide();
			    }	
    	        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
				if(proFlag!='1'){
			    	nui.get("add").hide();
			    	nui.get("edit").hide();
			    	nui.get("remove").hide();
			    	nui.get("biz_gs_dy_maxLoan_insert").hide();
			    	nui.get("biz_gs_dy_maxLoan_remove").hide();
			    }
			    search();
			}
        });
	}

    
    function add() {
    	nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_guaranteer_add.jsp?applyId="+applyId+"&partyId="+partyId,
				title: "新增保证人", 
				width: 800, 
				height: 400,
				allowResize:false,
	        	allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action=="ok"){
						/* var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.currentNode;
	                    data = nui.clone(data); */
						search();
					}
				}
			}); 
		}
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
        	//标题
        	var contitle;
        	var contitle2;
        	if("0" == v){
        		contitle2 = "编辑";
        	}else if("1" == v){
        		contitle2 = "查看";
        	}
        	nui.open({
					url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_guaranteer_add.jsp?suretyId="+row.SURETY_ID+"&view="+v+"&relationId="+row.RELATION_ID+"&partyId="+partyId,
					title: contitle2+"保证人", 
					width: 800, 
					height: 400,
					allowResize:false,
					showMaxButton: false,
					ondestroy: function (action) {
						if (action == "ok") {
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
        	//删除保证--保证人信息
        	nui.confirm("确定删除吗？","确认",function(action){
        		if(action!="ok") return;
        		var json=nui.encode({"suretyId":row.SURETY_ID,"relationId":row.RELATION_ID});
            	$.ajax({
            	    url: "com.bos.grt.guaranMainManager.guaranteeApply.delGuaranteeApplyTbGrtGuaranteer.biz.ext",
            	    type: 'POST',
            	    data: json,
            	    cache: false,
            	    contentType:'text/json',
            	    success: function (text) {
            	    	nui.alert(text.msg);
            	        search();
            	    },
            	    error: function () {
            	    	nui.nui.alert("操作失败！");
            	    }
            	});
        	});
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    function insert2() {
            nui.open({
                url: nui.context + "/biz/biz_info_view/selectMaxLoancon.jsp?applyId=<%=request.getParameter("bizId") %>&subcontractTypeCd=03&bizCustType="+bizCustType+"&reType=03"+"&partyId="+"<%=request.getParameter("partyId") %>",
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
    
     function edit2(v) {
        var row = grid2.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/grt/grtMainManage/topSubcontractView/list_03_sub_contract.jsp?&subcontractId="+row.subcontractId+"&view="+v,
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
                    	search2();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function remove2() {
        var row = grid2.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"maxloanconId":row.maxloanconId,"bizCustType":bizCustType}});
                $.ajax({
                     url: "com.bos.biz.Collateral.deleteMaxloancon.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
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
    
	//最高额担保合同
    var grid2 = nui.get("datagrid2");
    search2();
    
    function search2() {
        grid2.load({"applyId":"<%=request.getParameter("applyId") %>","guarantyType":collType});
        
         grid2.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['SUBCONTRACT_NUM']='<a href="#" onclick="editMaxLoan(\''+ e.data[i].SUBCONTRACT_ID+ '\');">'+e.data[i]['SUBCONTRACT_NUM']+'</a>';
			}
		});
    }
    
    function insertMaxLoan() {
            nui.open({
                url: nui.context + "/biz/biz_grt/selectMaxLoanconBZR.jsp?guarantyType="+collType+"&applyId="+applyId+"&partyId="+partyId,
                title: "引入最高额担保合同", 
                width: 1000,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    search2();
                },
                ondestroy: function (action) {
                	search2();
                }
            });
    }
    
     function editMaxLoan(SUBCONTRACT_ID) {
        var row = grid2.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/crt/con_grt/con_bzr_tab.jsp?applyId="+applyId+"&subcontractId="+row.SUBCONTRACT_ID+"&view=1",
                title: "查看", 
                width: 900,
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
                        grid.load({"item":{"applyId":"<%=request.getParameter("bizId") %>","_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":collType}});
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function removeMaxLoan() {
        var row = grid2.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"maxloanconId":row.MAXLOANCON_ID}});
                $.ajax({
                     url: "com.bos.bizInfo.bizGrt.deleteMaxloancon.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
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

<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-12 16:42:12
  - Description:
-->
<head>
<title>单一客户质押</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<input name="bizGrtRel.relationId" id="bizGrtRel.relationId" class="nui-hidden" />
</div>


<%--<div id="panel1" class="nui-panel" title="质押"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="false">

    	<a class="nui-button" iconCls="icon-add" id="biz_gs_zy_add" onclick="add()">增加</a>
    	<a class="nui-button" iconCls="icon-add" id="biz_gs_zy_insert" onclick="insert">引入</a>
    	<a class="nui-button" iconCls="icon-edit" id="biz_gs_zy_edit" onclick="edit(0)">编辑</a>
		<a class="nui-button" iconCls="icon-zoomin"  id="biz_gs_zy_view" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" id="biz_gs_zy_remove" onclick="remove()">删除</a>

	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
	    url="com.bos.grt.grtMainManage.grtApply.getCollateralList.biz.ext" dataField="items"allowAlternating="true"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
	        <div field="PARTY_NAME" allowSort="true" headerAlign="center">抵质押人名称</div>
	        <div field="SORT_TYPE" allowSort="true" dictTypeId="XD_YPZL01" headerAlign="center" >抵质押物类型</div>
	        <div field="SURETY_NO" allowSort="true" dictTypeId="XD_YWDB02" headerAlign="center" >抵质押物编号</div>
	       	<div field="OWNERSHIP_NUM" allowSort="true" headerAlign="center" >权属证编号</div>
	        <div field="ASSESS_VALUE" allowSort="true"  headerAlign="center" dataType="currency">评估价值(元)</div> 
	        <div field="MORTGAGE_VALUE" allowSort="true"  headerAlign="center" dataType="currency">权利价值(元)</div>  
	        <div field="USED_AMT" allowSort="true"  headerAlign="center" dataType="currency">已担保金额(元)</div>       
	     </div>
	</div>
<br>
</div>--%>

<div id="panel2" class="nui-panel" title="最高额担保合同关联"style="width:100%;height:auto;" showToolbar="false"allowAlternating="true"
	showCollapseButton="true" showFooter="false" allowResize="false">
	   
	    	<a class="nui-button" iconCls="icon-add" id="biz_gs_zy_maxLoan_insert" onclick="insertMaxLoan">引入</a>
	    	<a class="nui-button" iconCls="icon-zoomin"  id="biz_gs_zy_maxLoan_view" onclick="editMaxLoan(1)">查看</a>
			<a class="nui-button" iconCls="icon-remove" id="biz_gs_zy_maxLoan_remove" onclick="removeMaxLoan()">删除</a>
	
		<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.bizInfo.bizGrt.getMaxloanconList.biz.ext" dataField="maxLoanBizs"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
			<div type="checkcolumn">选择</div>
			<div type="indexcolumn">序号</div>
	        <div field="PARTY_NAME" allowSort="true"  width="" headerAlign="center">担保人名称</div>
	        <div field="CERT_TYPE" allowSort="true"  headerAlign="center"dictTypeId="CDKH0002">证件类型</div>
	       	<div field="CERT_NUM" allowSort="true"  headerAlign="center">证件号码</div>
	        <div field="SUBCONTRACT_NUM" allowSort="true"  width="" headerAlign="center">担保合同编号</div>
	        <div field="ZGBJXE" allowSort="true"  width="" headerAlign="center"dataType="currency">担保合同金额</div>
	        <div field="AVI_AMT" allowSort="true"  width="" headerAlign="center"dataType="currency">可用担保金额</div>
		</div>
		</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	
	//客户ID
	var partyId="<%=request.getParameter("partyId")%>";
	//业务ID
    var applyId="<%=request.getParameter("applyId") %>";
    //抵押/质押
    var collType="<%=request.getParameter("collType") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";
	
	initPage();
	function initPage(){
		//质押在业务申请担保方式为02，在抵质押方式为03
		var json = nui.encode({"applyId":applyId,"guarantyType":"03"});
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
    		    	//nui.get("biz_gs_zy_add").hide();
			    	//nui.get("biz_gs_zy_insert").hide();
			    	//nui.get("biz_gs_zy_edit").hide();  
			    	//nui.get("biz_gs_zy_view").hide();
			    	//nui.get("biz_gs_zy_remove").hide();
			    	nui.get("biz_gs_zy_maxLoan_insert").hide();
			    	nui.get("biz_gs_zy_maxLoan_view").hide();
			    	nui.get("biz_gs_zy_maxLoan_remove").hide();
			    	nui.alert("该申请担保方式不为质押！");
			    }	
    	        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
				if(proFlag!='1'){
			    	//nui.get("biz_gs_zy_add").hide();
			    	//nui.get("biz_gs_zy_insert").hide();
			    	//nui.get("biz_gs_zy_edit").hide();
			    	//nui.get("biz_gs_zy_remove").hide();
			    	nui.get("biz_gs_zy_maxLoan_insert").hide();
			    	nui.get("biz_gs_zy_maxLoan_remove").hide();
			    }
			    search();
            	
			}
        });
	}
    function search() {
        //grid.load({"applyId":applyId,"collType":collType});
    }
    
    function add(){
    	var partyId;
    	var sortType;
    	nui.open({
	        url: nui.context + "/grt/manage/chioseParty/queryParty.jsp?partyId="+"<%=request.getParameter("partyId")%>",
	        showMaxButton: true,
	        title: "选择抵质押人",
	        width: 1000,
	        height: 500,
	        ondestroy: function (action) {
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
						partyId=data.partyId;
						nui.open({
						   url:nui.context+"/grt/collateralparameter/colllsortparameter/selectCollateralSortInfoTree.jsp?collType="+collType,
						   title:"请选择押品分类",
						   width:350,
						   height:400,
						   allowResize: false,
				           showMaxButton: true,
						   ondestroy:function(action){
						    	var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.GetData();
								data = nui.clone(data);
								if (data) {
									sortType = data.sortType;
									parentSortType = data.parentSortType;
									//collType = data.collType;
									localGeneral(sortType,parentSortType,collType,partyId);
								}
								grid.reload();
						    }
						  }); 
	                }
	            }else{
	            	search();
	            }
	        }
	    }); 
    }
    
    function localGeneral(sortType,parentSortType,collType,partyId){
    	nui.open({
		   url:nui.context+"/grt/collateralparameter/colllsortparameter/addGrtList.jsp?partyId="+partyId+"&sortType="+sortType+"&applyId="+applyId+"&collType="+collType,
		   title:"押品列表",
		   width:800,
		   height:600,
		   state:"max",
		   allowResize: false,
           showMaxButton: true,
		   ondestroy:function(action){
			   if (action == "ok") {
					search();
			   }
				search();
		    }
		  }); 
    }
    
	
	function insert(v) {
            nui.open({
                url: nui.context + "/grt/grtMainManage/grt_apply_colmanage_import.jsp?partyId="+partyId+"&applyId="+applyId+"&collType="+collType,
                title: "引入押品", 
                width: 1200,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
    }
	function insert2() {
            nui.open({
                url: nui.context + "/biz/biz_info_view/selectMaxLoancon.jsp?applyId=applyId&subcontractTypeCd=02&bizCustType=01&reType=02&partyId=<%=request.getParameter("partyId") %>",
                title: "引入最高额担保合同", 
                width: 800,
        		height: 300,
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
        	var relationId=row.RELATION_ID;
        	nui.open({
	            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+row.SURETY_ID+"&relationId="+relationId+"&applyId="+applyId+"&view="+v+"&collType="+row.COLL_TYPE,
	            showMaxButton: true,
	            title: title1+"押品信息",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	search();
	            }
	        });
        } else {
            alert("请选中一条记录");
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
                        grid.load({"item":{"applyId":applyId,"_entity":"com.bos.dataset.biz.TbBizGrtRelation","reType":"02"}});
               	 	}
                }
            });
        } else {
            nui.alert("请选中一条记录");
        }
        
    }
	
	//这里的删除只是删除押品与业务申请的关联关系 ,并非删除真正的押品
	function remove() {
        var row = grid.getSelected();
        if (row) {    
	    	 nui.confirm("确定删除吗？","确认",function(action){
	        	if(action!="ok") return;
	        	var json=nui.encode({"bizGrt":{"relationId":row.RELATION_ID}});
	            $.ajax({
	                 url: "com.bos.grt.grtMainManage.grtApply.delApplyTbGrtGuarantybasic.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
	                success: function (text) {
	                   nui.alert(text.msg);
	                   search();
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
//最高额担保合同
    var grid2 = nui.get("datagrid2");
    search2();
    
    function search2() {
        grid2.load({"applyId":"<%=request.getParameter("applyId") %>","guarantyType":collType});
    }
    
    function insertMaxLoan() {
            nui.open({
                url: nui.context + "/biz/biz_grt/selectMaxLoancon.jsp?guarantyType="+collType+"&applyId="+applyId+"&partyId="+partyId,
                title: "引入最高额担保合同", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    //var data = row;
                    search2();
                },
                ondestroy: function (action) {
                	search2();
                }
            });
    }
    
     function editMaxLoan(v) {
        var row = grid2.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/crt/con_grt/con_zy_tab.jsp?applyId="+applyId+"&subcontractId="+row.SUBCONTRACT_ID+"&view="+v,
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
                    	if (text.msg) {
                    		nui.alert(text.msg);
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
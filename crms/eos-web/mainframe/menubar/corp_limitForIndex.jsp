<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>综合授信额度台账</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data"
	 style="width:99.8%;margin-bottom:5px;height:auto;overflow:hidden;">
			<input name="map/userNum" class="nui-hidden nui-text" value="<%=session.getAttribute("userId") %>"/>
	</form>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  
	    url="com.bos.crd.LimitShow.corpLimit0.biz.ext" dataField="items" idField="partyId"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" allowCellEdit="false" allowCellSelect="false" 
	    sizeList="[10,20,50,100]" pageSize="6" onshowrowdetail="onShowRowDetail" onselectionchanged="onSelectionChanged">
	    <div property="columns">
	        <div type="expandcolumn" >选择</div>
	        <!--<div field="partyId" allowSort="true"  headerAlign="center" > 客户ID</div> -->
	        <div field="partyName" allowSort="true"  headerAlign="center" > 客户名称</div> 
	        <div field="bizType" allowSort="true"  headerAlign="center"  dictTypeId="XD_SXCD1038">业务性质</div> 
	        <div field="partyNum" allowSort="true"  headerAlign="center" > 客户编号</div> 
	        <!--<div field="limitNum" allowSort="true"  headerAlign="center" > 额度编号</div>-->
	        <div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001">额度币种</div>
	        <div field="creditAmt" allowSort="true"  headerAlign="center" dataType="currency">授信总额度</div>
	        <div field="creditExposure" allowSort="true" headerAlign="center" dataType="currency">敞口总额度</div>       
	        <div field="availableAmt" allowSort="true"  headerAlign="center" dataType="currency">闭口可用额度</div>
	        <div field="availableExposure" allowSort="true"  headerAlign="center" dataType="currency"> 敞口可用额度</div>
	        <div field="spareExposure" allowSort="true"  headerAlign="center" dataType="currency"> 备用额度</div>
	        <div field="startDate" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd">额度生效日期</div>  
	        <div field="endDate" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd">额度到期日期</div>       
	        <div field="creditTerm" allowSort="true"  headerAlign="center" > 期限（月）</div>
	        <div field="statusCd" allowSort="true" dictTypeId="XD_EDCD0004" headerAlign="center" >额度状态</div>
	        <div field="cycleInd" allowSort="true" dictTypeId="YesOrNo" headerAlign="center" >是否循环</div>
	        <!--<div field="isAllowCameo" allowSort="true"  headerAlign="center" dictTypeId="YesOrNo"> 是否允许串用</div>-->
	     </div>
	</div>

	<div id="detailGrid_Form" class="nui-form" style="display:none;">

        <div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;"  allowAlternating="true"
            url="com.bos.crd.LimitShow.corpLimit1.biz.ext" 
            
            sizeList="[10,20,50,100]" pageSize="10" 
            sortMode="client" allowCellWrap="false"
        	idField="data" onselectionchanged="onSelectionChanged1">
            
            <div property="columns">
            	<div type="checkcolumn">选择</div>
		        <div field="partyName" allowSort="true"  headerAlign="center" > 客户名称</div> 
                <div field="bizType" width="80" allowSort="true"  headerAlign="center" dictTypeId="XD_SXCD1038" >业务性质</div> 
		        <div field="partyNum" allowSort="true"  headerAlign="center" > 客户编号</div> 
		        <div field="currencyCd" width="80" allowSort="true" headerAlign="center" dictTypeId="CD000001">额度币种</div>
		        <div field="creditAmt" allowSort="true"  headerAlign="center" dataType="currency">全额额度总金额</div>
		        <div field="occupiedAmt" allowSort="true"  headerAlign="center" dataType="currency">全额额度占用金额</div>
		        <div field="availableAmt" allowSort="true"  headerAlign="center" dataType="currency">全额额度可用金额</div>
		        <div field="creditExposure" allowSort="true" headerAlign="center" dataType="currency">敞口额度总金额</div>       
		        <div field="occupiedExposure" allowSort="true"  headerAlign="center" dataType="currency"> 占用敞口额度</div>
		        <div field="availableExposure" allowSort="true"  headerAlign="center" dataType="currency"> 可用敞口额度</div>
		        <div field="spareExposure" allowSort="true"  headerAlign="center" dataType="currency"> 备用额度</div>
		        <div field="startDate" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd">额度生效日期</div>  
		        <div field="endDate" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd">额度到期日期</div>       
		        <div field="statusCd" allowSort="true" dictTypeId="XD_EDCD0004" headerAlign="center" >额度状态</div>
		        <div field="creditTerm" allowSort="true"  headerAlign="center" > 期限（月）</div>
            </div>
        </div>    
    </div> 
	
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var datagrid1 = nui.get("datagrid1");
	var datagrid2 = nui.get("datagrid2");
	var detailGrid_Form = document.getElementById("detailGrid_Form");
	datagrid1.load();
	datagrid2.load();
	
	//根据集团/非集团和业务性质进行不同操作 
	function onSelectionChanged(){
        var row = datagrid1.getSelected();
        var limitView = nui.get("limitView");  //查看分项额度
        var contractView = nui.get("contractView");
        //集团客户id为"-1" 或业务性质为"01"不允许查看分项额度
        	limitView.disable();
        	contractView.disable();
        if( row.gPartyId != "-1" && row.bizType=="综合授信业务"){
             limitView.enable();
        }else if(row.bizType=="单笔业务"){
        	contractView.enable();
        }
	}
	//子表查询按钮空值
	function onSelectionChanged1(){
        var row = datagrid2.getSelected();
        var limitView1 = nui.get("limitView1");  //查看分项额度
        var contractView1 = nui.get("contractView1");
        //业务性质为"-2" 不允许查看分项额度
        	limitView1.disable();
        	contractView1.disable();
        if( row.bizType=="02"){
             limitView1.enable();
        }else if(row.bizType=="01"){
        	contractView1.enable();
        }
	}
	
	function query(){//对公单一客户查询
       var o = form.getData(false, true);//逻辑流必须返回total
       datagrid1.load(o);
	}
	query();
	
	function reset() {
		form.reset();
		query();
	}
	
	function onShowRowDetail(e) {
            var grid1 = e.sender;
            var row = e.record;
            //alert(row.partyId);
            if(row.gPartyId == "-1"){
	            var td = grid1.getRowDetailCellEl(row);
	            td.appendChild(detailGrid_Form);
	            detailGrid_Form.style.display = "block";
	
	            datagrid2.load({ gPartyId: row.partyId });
	            //datagrid2.load({ userNum: row.userNum });
            }
    }
    
       
	//查看分项额度
	function limitView() {
        var row = datagrid1.getSelected();
        //alert(row.gPartyId);
        if (row) {
            nui.open({
                url: "crd/crdshow/corp_limit_show.jsp?limitId="+row.limitId+"&statusCd="+row.statusCd,
                title: "查看分项额度", 
                width: 1200,
        		height: 600,
        		state:"max",
                ondestroy: function (action) {            
		                grid.reload();
		        }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    //单笔单批查看合同
	function contractView() {
        var row = datagrid1.getSelected();
        if (row) {
            nui.open({
                url: "crd/crdshow/contract_under_corplimit.jsp?partyId="+row.partyId+"&productType=" + row.productType+"&approveId=" + row.approveId,
                title: "查看单笔单批合同", 
                width: 1200,
        		height:750,
        		state:"max",
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
    
    //子表查看分项额度
    function limitView1() {
        var row = datagrid2.getSelected();
        if (row) {
            nui.open({
                url: "crd/crdshow/corp_limit_show.jsp?limitId="+row.limitId+"&statusCd="+row.statusCd,
                title: "查看分项额度", 
                width: 1200,
        		height: 600,
        		state:"max",
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
    
    //查看子表单笔单批合同
    function contractView1() {
        var row = datagrid2.getSelected();
        if (row) {
            nui.open({
                url: "crd/crdshow/contract_under_corplimit.jsp?partyId="+row.partyId+"&productType=" + row.productType+"&approveId=" + row.approveId,
                title: "查看单笔单批合同", 
                width: 1200,
        		height:750,
        		state:"max",
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
    
    //选择经办机构
    function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择经办机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
    //选择经办人
    function selectEmp(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/pub/standingBook/select_employee.jsp",
        showMaxButton: true,
        title: "选择经办人",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
	}

//导出
    function exportEmp()
    {
     var form = document.getElementById("form1");
		     form.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=23";
		     form.submit();
    }
</script>
</body>
</html>
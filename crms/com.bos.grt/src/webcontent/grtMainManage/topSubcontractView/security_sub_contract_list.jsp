<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-26
  - Description:TB_CON_SUBCONTRACT, com.bos.dataset.crt.TbConSubcontract
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-edit" onclick="view1()">查看</a>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.grtMainManage.subcontractView.getGrtTopSubContract.biz.ext"
		dataField="tbConSubcontract" allowResize="true" showReloadButton="false" 
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="subcontractNum" headerAlign="center" allowSort="true" >抵押合同编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >抵押人名称</div>
			<div field="ifTopSubcon" allowSort="true"  headerAlign="center"  dictTypeId="YesOrNo">是否最高额担保</div>
			<div field="subcontractAmt"  headerAlign="center" dataType="currency" align="right" >担保合同金额</div>
			<div field="bz" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
			<div field="subcontractStatus" allowSort="true"  headerAlign="center" dictTypeId="XD_SXCD8003">担保合同状态</div>
			</div>
	</div>
	<div id="table_1" style="display:block">
	    <fieldset>
		  	<legend>
		   		 <span>抵押物列表</span>
		    </legend>
		    <div class="nui-toolbar" style="border-bottom:0;text-align:left;">
				<a class="nui-button" iconCls="icon-edit" id="editGrt1" onclick="editGrt(1)">查看</a>
			</div>
			<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
			url="com.bos.grt.grtMainManage.subcontractView.getTopSubContractList.biz.ext"
			dataField="contractGuarantys"
			    allowAlternating="true" multiSelect="false" pageSize="15" showEmptyText="true"
			    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true">
			        <div property="columns">
			        <div type="checkcolumn">选择</div> 
			        <div field="subcontractNum" allowSort="true" headerAlign="center">抵押合同编号</div>      
			        <div field="suretyNum" allowSort="true" headerAlign="center">抵押品编号</div>
			        <div field="partyName" allowSort="true" headerAlign="center">抵押人名称</div>
			        <div field="guaranteeRightMoney" headerAlign="center" dataType="currency" align="right">担保债权金额</div>
			        <div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品类别</div>
			        <div field="qualifiedResult" headerAlign="center" allowSort="true" dictTypeId="XD_0002">押品合格性</div>
			        <div field="grtStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1219">担保品状态</div>
			     </div>
			</div>
		</fieldset>
	</div>	

<script type="text/javascript">
 	nui.parse();
    var form1 = new nui.Form("#form1");
	var grid1 = nui.get("grid1");
	var grid2 = nui.get("datagrid2");
	
	//业务ID
    var applyId="<%=request.getParameter("applyId") %>";
	
	//var data = form.getData(); //获取表单多个控件的数据
	var subcontractId='<%=request.getParameter("subcontractId")%>';
		
	$("#table_1").css("display","none");
	
	//初始化查询
   function search1() {
		var json = {"tbConSubcontract/subcontractId":subcontractId};
        grid1.load(json);
    }
    search1();
    
    function search2() {
    	var rowSelect = grid1.getSelected();
		var json = {"contractGuaranty":{"subcontractId":rowSelect.subcontractId,"contractId":rowSelect.contractId}};
        grid2.load(json);
    }
   	
    //查看抵押合同
    function view1() {
        var row = grid1.getSelected();
        if (row) {
             	nui.open({
                url: nui.context +"/crt/con_grt/con_dy_info.jsp?contractType="+row.subcontractType+"&view=1&applyId="+applyId+"&subcontractId="+subcontractId,
                title: "查看合同", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search1();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    //按照所选择担保合同显示担保物列表
	function onSelectionChanged() {
		var row = grid1.getSelected();
   		var operationType=row.operationType; //operationType:01 引入  02 新增
   		if(operationType =='02'){
   			search2();
   			$("#table_1").css("display","block");
   		}else if(operationType =='01'){
   			$("#table_1").css("display","block");
   			search2();
   		}else{
   			search2();
   			$("#table_1").css("display","block");
   		}
   		
    }
    
    /**************************************************押品信息****************************************************/
    var sortType;
    var rankTypeName;
    var parantSortType;
    
	/**
	 * 编辑抵押品信息
	 */    
    function editGrt(v){
    	var row = grid2.getSelected();
    	var title1;
    	if(v == "0"){
    		title1 = "编辑";
    	}else if(v == "1"){
    		title1 = "查看";
    	}
        if (row) {
            var reg=/^[0-9]*$/;
        	if(row.sortType==""||!reg.test(row.sortType)){
                nui.alert("供应链的非现金等价物押品明细请到原系统查看!");
                return;
            }
        	nui.open({
            	url: nui.context+"/grt/grtMainManage/grt_contract_colmanage_edit.jsp?sortType="+row.sortType+"&suretyId="+
            		row.suretyId+"&ifOtherCommon="+row.ifOtherCommon+"&parentSortType="+row.parentSortType+"&view="+v+
            		"&relationId="+row.relationId+"&subcontractId="+row.subcontractId,
            	title: title1+"押品", 
            	width: 800, 
        		height: 500,
        		allowResize:false,
    	    	showMaxButton: false,
	            ondestroy: function (action) {
	            	search2();
	            	
            	}
        	}); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
        
  </script>
</body>
</html>

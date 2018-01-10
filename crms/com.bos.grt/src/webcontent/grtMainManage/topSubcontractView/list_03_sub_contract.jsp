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
		dataField="tbConSubcontract" allowResize="true" showReloadButton="false" onselectionchanged="onSelectionChanged"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="subcontractNum" headerAlign="center" allowSort="true" >保证合同编号</div>
			<div field="subcontractManualNum" headerAlign="center" allowSort="true">保证合同纸质编号</div>
			<div field="guatorTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1221">保证形式</div>
			<div field="ifTopGuaranty" allowSort="true"  headerAlign="center"  dictTypeId="YesOrNo">是否最高额担保</div>
			<div field="guaranteeRightMoney" headerAlign="center" dataType="currency" align="right">担保债权金额</div>
			<div field="currencyCd" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
			<div field="subcontractStatusCd" allowSort="true" headerAlign="center" dictTypeId="XD_SXCD1219">担保合同状态</div>
			</div>
	</div>

<div id="table_1" style="display:block">
    <fieldset>
	  	<legend>
	   		 <span>保证物列表</span>
	    </legend>
    <div class="nui-toolbar" style="border-bottom:0;text-align:left;">
			<a class="nui-button" iconCls="icon-edit" onclick="editGrt(1)">查看</a>
	</div>	
	<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	url="com.bos.grt.grtMainManage.subcontractView.getTopSubContractGuarantee.biz.ext"
	dataField="contractGuarantees"
		    allowAlternating="true" multiSelect="false" pageSize="15" showEmptyText="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true">
		        <div property="columns">
		        <div type="checkcolumn">选择</div> 
		        <div field="subcontractNum" allowSort="true"  headerAlign="center">保证合同编号</div>      
		        <div field="partyName" allowSort="true"  headerAlign="center">保证人名称</div>
		        <div field="guaranteeType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4001">担保品类别</div>
		        <div field="guaranteeRightMoney" headerAlign="center" allowSort="true" dataType="currency">保证金额</div>
		        <div field="qualifiedResult" headerAlign="center" allowSort="true" dictTypeId="XD_0002">担保品合格性</div>
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
	
	$("#table_1").css("display","none");
	
	//初始化查询
   function search1() {
		//var data = form.getData(); //获取表单多个控件的数据
		var subcontractId='<%=request.getParameter("subcontractId")%>';
		var json = {"tbConSubcontract/subcontractId":subcontractId};
        grid1.load(json);
    }
    search1();
    
    function search2() {
    	var rowSelect = grid1.getSelected();
		var json = {"contractGuarantee":{"subcontractId":rowSelect.subcontractId}};
        grid2.load(json);
    }
    
    //查看保证合同
    function view1() {
        var row = grid1.getSelected();
        if (row) {
             	nui.open({
                url: nui.context + "/crt/view/subContract/apply_top_view_03_sub_contract.jsp?subcontractId="+row.subcontractId,
                title: "查看保证合同", 
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
                        search1();
               	 	}
                }
            });
        } else {
            nui.alert("请选中一条记录");
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
   			search2();
   			$("#table_1").css("display","block");
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
        	if(row.guaranteeType =='020406'){
        		
	        	nui.open({
            	url: nui.context+"/grt/guaranMainManager/guarantee_contract_guaranteer_edit.jsp?sortType="+row.sortType+"&suretyId="+
            		row.suretyId+"&ifOtherCommon="+row.ifOtherCommon+"&parentSortType="+row.parentSortType+"&view="+v+
            		"&relationId="+row.relationId+"&subcontractId="+row.subcontractId,
            	title: title1+"保证人", 
            	width: 800, 
        		height: 600,
        		allowResize:false,
    	    	showMaxButton: false,
	            ondestroy: function (action) {
	            	search2();
	            	
            	}
        		});
        	}else if(row.guaranteeType =='020405'){
        		
	        	nui.open({
	            	url: nui.context+"/grt/guaranMainManager/guarantee_contract_creditsafe_edit.jsp?sortType="+row.sortType+"&suretyId="+
	            		row.suretyId+"&ifOtherCommon="+row.ifOtherCommon+"&parentSortType="+row.parentSortType+"&view="+v+
	            		"&relationId="+row.relationId+"&subcontractId="+row.subcontractId,
	            	title: title1+"应收账款买断项下的信用保险", 
	            	width: 800, 
	        		height: 600,
	        		allowResize:false,
	    	    	showMaxButton: false,
		            ondestroy: function (action) {
		            	search2();
		            	
	            	}
	        	});
        	}else{
        		
	        	nui.open({
	            	url: nui.context+"/grt/guaranMainManager/guarantee_contract_basic_edit.jsp?sortType="+row.sortType+"&suretyId="+
	            		row.suretyId+"&ifOtherCommon="+row.ifOtherCommon+"&parentSortType="+row.parentSortType+"&view="+v+
	            		"&relationId="+row.relationId+"&subcontractId="+row.subcontractId,
	            	title: title1+"押品", 
	            	width: 800, 
	        		height: 600,
	        		allowResize:false,
	    	    	showMaxButton: false,
		            ondestroy: function (action) {
		            	search2();
		            	
	            	}
	        	});
        	}
        	
        } else {
            nui.alert("请选中一条记录");
        }
    } 
   	</script>
</body>
</html>

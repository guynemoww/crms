<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>保管台账</title>
<%@include file="/common/nui/common.jsp" %>
	<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="保管台账">
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.3%;height:auto;overflow:hidden;margin-bottom:5px;">
	<div class="nui-dynpanel" columns="6">
		<label>借款人：</label>
		<input name="map/bpartyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		
		<label>押品分类：</label>
		<input name="map/sortType" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCollSortTree" vtype="maxLength:32" />
	
		<label>信封编号：</label>
		<input name="map/mailerNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>担保人：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

		<label>权证编号：</label>
		<input name="map/registerCertino" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
	
		<label>借款合同编号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>经办人：</label>
		<input name="map/userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/>
		
		<label>权证状态：</label>
		<input name="map/cardState" id="map/cardState" required="false" class="nui-dictcombobox" dictTypeId="YP_GLCD0008" vtype="maxLength:32" />
	
		<label>担保合同编号：</label>
		<input name="map/subcontractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>经办机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
		
		<label>权证类型：</label>
		<input name="map/cardType" required="false" class="nui-dictcombobox"  dictTypeId="YP_GLCD0200" vtype="maxLength:32" />
	
		<label> </label>
		<input name="" required="false" class="nui-hidden"  dictTypeId="YP_GLCD0200" vtype="maxLength:32" />

		<label>入库日期：</label>
		<div>
		<input name="map/laidstart" id="laidstart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate"/>~
		<input name="map/laidend" id="laidend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate"/>
		</div>
		
		<label>出库日期：</label>
		<div>
		<input name="map/removestart" id="removestart" required="false" value="" class="nui-datepicker nui-form-input" onvaluechanged="onregDueDate1"/>~
		<input name="map/removeend" id="removeend" required="false" class="nui-datepicker nui-form-input" onvaluechanged="onDueDate1"/>
		</div>
		
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;padding-right:45px;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<%--<a class="nui-button"  iconCls="icon-download"  onclick="downloadExcel()" type="submit"/>导 出</a>--%>
</div>
</form> 
<div id="grid1" class="nui-datagrid" style="width:99.3%;height:auto" 
	url="com.bos.pub.standingbook.guarantyaccout.GetGrtAccList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn">选择</div>
		<div field="contractNum" headerAlign="center" allowSort="true" >贷款合同号</div>
		<div field="bpartyName" headerAlign="center" allowSort="true" >借款人</div>
		<div field="subcontractNum" headerAlign="center" allowSort="true" >担保合同号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >担保人</div>
		<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品分类</div>
		<div field="suretyNum" headerAlign="center" allowSort="true" >抵质押物编号</div>
		<div field="assessCost" headerAlign="center" allowSort="true" dataType="currency">评估价值</div>
		<div field="assessOrg" headerAlign="center" allowSort="true" >评估机构</div>
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种(评估价值)</div>						
	    <div field="cardType" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0200">权证类型</div>
		<div field="registerCertino" headerAlign="center" allowSort="true" >权证编号</div>		
		<div field="laidUpDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">入库日期</div>		
		<div field="sotckRemovalDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">出库日期</div>
		<div field="cardRegDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">权证登记时间</div>
		<div field="sotckRemovalDate1" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">权证出借日期</div>
		<div field="cardInRevertDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">权证归还日期</div>
		<div field="cardState" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0008">权证状态</div>
		<div field="mailerNum" headerAlign="center" allowSort="true" >信封编号</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">客户经理</div>
		
      </div>
   </div>
	</center>
</div>
</div>		
    <script type="text/javascript">
    
    //201410131127
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    nui.get("map/cardState").setData(getDictData('YP_GLCD0008','str','0100,0200,0301,0302,0303'));
			
    function search() {
    	git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
    //search();
    
    function reset(){
		form.reset();
	}
	
	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	// 日期判断
	function onregDueDate1(){
		var removestart = nui.get("removestart").getValue();//起始日期
		var removeend = nui.get("removeend").getValue();//截止日期
			if(removeend==""){//截止日期为空
			nui.get("removeend").setValue(removestart);
			}
		if(removestart!=""&&removeend!=""){
			if(nui.parseDate(removestart)-nui.parseDate(removeend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removestart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate1(){
		var removestart = nui.get("removestart").getValue();//起始日期
		var removeend = nui.get("removeend").getValue();//截止日期
	
		if(removestart!=""&&removeend!=""){
			if(nui.parseDate(removestart)-nui.parseDate(removeend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("removeend").setValue(removestart);
				return false;
			}
		}else{
	
			return true;
		}
	}
////////////////////日期判断结束
	
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
    
    //选择押品类型
	function selectCollSortTree(e){
	   var btnEdit = this;
	   nui.open({
		   		url:nui.context+"/grt/collateralparameter/colllsortparameter/selectCollateralSortTree.jsp",
		   		title:"请选择押品类型",
		   		width:350,
		   		height:400,
		   		ondestroy:function(action){
		      		if (action == "ok") {
                  		var iframe = this.getIFrameEl();
                  		var data = iframe.contentWindow.GetData();
                  		data = nui.clone(data);
                 		if (data) {
                     		btnEdit.setValue(data.sortType);
                     		btnEdit.setText(data.sortName);
                  		}
              		}
		  		}
	    	});
   		}
    
    
//选择人员（无权限）
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
                    btnEdit.setValue(data.empid);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
}

	
	 
   function query(){
   		var data = form.getData();
        //grid.load(data);
       grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['contractNum']='<a href="#" onclick="goToLoan(\''+"con"+","+e.data[i]['contractId']+'\');return false;">'+e.data[i]['contractNum']+'</a>';
       				
       		   //押品基本信息 
   		            e.data[i]['suretyNum']='<a href="#" onclick="goToLoan(\''
       				+"gua"+","+e.data[i]['sortType']+","+e.data[i]['suretyId']+","+e.data[i]['ifOtherCommon']+","+e.data[i]['parentSortType']+'\');return false;" value="'
       				+ e.data[i].contractDetailId
       				+ '">'+e.data[i]['suretyNum']+'</a>';
       		  //查看担保合同信息
       		  e.data[i]['subcontractNum']='<a href="#" onclick="goToLoan(\''+"subCon"+","+e.data[i]['subcontractId']+","+e.data[i]['subcontractTypeCd']+'\');return false;">'+e.data[i]['subcontractNum']+'</a>';
       		}
       });
      
	}
	query(); 
	
	function goToLoan(str){
	    if(str=="") return ;
	    var flag = str.split(",")[0];
		if("con"==flag){
		 var strs = str.split(",");
		 contractId = strs[1];	
		nui.open({
            url:nui.context +"/pub/standingBook/contract/contract_tab_list.jsp?contractId="+contractId,/* 跳转合同界面 */
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
		}else if("subCon"==flag){
		   var strs = str.split(",");
		   var subcontractId = strs[1];
		   var subcontractTypeCd = strs[2];
		  
		   //查看抵押合同
		   if("01"==subcontractTypeCd){
		   nui.open({
                url: nui.context + "/crt/view/subContract/security_sub_contract_view.jsp?subcontractId="+subcontractId,
                title: "查看抵押合同", 
                width: 900,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                      search();  
               	 	}
                }
            });
		   }
		   if("02"==subcontractTypeCd){
		   nui.open({
                url: nui.context + "/crt/view/subContract/view_02_sub_contract.jsp?subcontractId="+subcontractId,
                title: "查看质押合同", 
                width: 900,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                          search();  
               	 	}
                }
            });
		   }
		}else{//连接到押品详细信息
		 var strs = str.split(",");
		 sortType = strs[1];
		 suretyId = strs[2];
		 ifOtherCommon = strs[3];
		 parentSortType = strs[4];
		nui.open({
		  	url: nui.context+"/grt/grtMainManage/grt_outer_colmanage_list_edit.jsp?sortType="+sortType+"&suretyId="+
	             suretyId+"&ifOtherCommon="+ifOtherCommon+"&parentSortType="+parentSortType+"&view=1",
            showMaxButton: true,
            title: "查看押品信息",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
		}
		
	} 
	


	 
	
	
		//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.partyName != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=21";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	</script>
</body>
</html>

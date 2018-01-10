<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-06
  - Description:TB_AFT_AFTER_LOAN_INFO, com.bos.dataset.aft.TbAftAfterLoanInfo
-->
<!-- 重写Group中Span样式 -->
<style>
	fieldset span {
		font-size:13px;
		font-weight:bold;
	}
</style>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
 <form id="form1" class="nui-form"action=""style="width:99.1%;overflow:hidden;margin-bottom:5px;" method="post" enctype="multipart/form-data" >
	<%--<div columns="6"><strong> <font size="3px" color="red"></font> </strong> </div >--%>
	<fieldset style="width:98%">
  	<legend align="center">
    <span>第一步：筛选出需纳入统计的信贷借据</span>
    </legend >
    	<div  class="nui-dynpanel" columns="6" style="margin-top:10px" >
		<label>业务性质：</label>
		<input id="map/productTypeCd" name="map/productTypeCd" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_GGCD033" />
		
		<label>担保方式：</label>
		<input name="map/mainSuretyMode" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDZC0005" />
		
		<label>归属机构：</label>
		<input id ="map/orgNum" name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
		
		<label>业务产品：</label>
		<input id="map/productType" name="map/productType" class="nui-buttonEdit nui-form-input"
		allowInput="false" onbuttonclick="selectProduct" />
		
		<label>贷款投向：</label>
		<input id="map/loanDirection" name="map/loanDirection" required="false" class="nui-buttonEdit"  onbuttonclick="selectLoanDirection" />
		
		<label>币种：</label>
		<input id="map/currencyType" name="map/currencyType" required="false" class="nui-dictcombobox nui-form-input"   data="currencyCode" />
		
		<label>信用等级：</label>
		<input id="map/creditRatingCd" name="map/creditRatingCd" required="false" class="nui-dictcombobox nui-form-input"   data="creditRatingCode"  />
		
		<label>分类：</label>
		<input id="map/classificationResultCd" name="map/classificationResultCd" required="false" class="nui-dictcombobox nui-form-input"  data="classificationResult" />
		
		<label>贷款发放额：</label>
		<div>
		<input name="map/loanAmtMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/loanAmtMax" required="false" class="nui-textbox nui-form-input"  style="width: 37%;"/>
		</div>
		
		<label>企业规模：</label>
		<input name="map/shbackEnterpriseSizeCd" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0025" />
		
		<label>企业控股类型：</label>
		<input name="map/bizHappenType" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0160"/>
		
	</div>
    </fieldset>
	
	<%--<div columns="6"><strong> <font size="3px" color="red">第二步：选择所需的统计口径</font ></strong>  </div >--%>
	<fieldset style="width:98%">
	  	<legend align="center">
	    <span>第二步：选择所需的统计口径</span>
	    </legend>
	    
		<div  class="nui-dynpanel" columns="6" style="margin-top:10px"  >
			<label>统计口径:</label>
			<input id="kouJ" name="map/kouJing" required="ture" required="false" class="nui-dictcombobox nui-form-input"  data="tjkj" />
		</div>
    </fieldset>
</div>
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:35px" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
</div>
 </form>
				

<div id="grid1" class="nui-datagrid" style="width:99.1%;height:auto;overflow:auto;"
	url="com.bos.pub.ledgerMsg.getLedStatisticalList.biz.ext"
	dataField="ledData"
	allowResize="true" showReloadButton="false" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="indexcolumn" width="40px">序号</div>
	    <div field="YWCP" headerAlign="center" allowSort="true" >统计口径</div>
	    <div field="YWXZ" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD033">统计口径</div>
	    <div field="DBFS" headerAlign="center" allowSort="true" dictTypeId="CDZC0005">统计口径</div>
	    <div field="DKTX" headerAlign="center" allowSort="true" dictTypeId="XD_CDKH2011">统计口径</div>
	    <div field="BZ" headerAlign="center" allowSort="true" dictTypeId="CD000001">统计口径</div>
	    <div field="XYDJ" headerAlign="center" allowSort="true" >统计口径</div>
	    <div field="FXFL" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">统计口径</div>
	    <div field="QYGM" headerAlign="center" allowSort="true" dictTypeId="CDKH0025">统计口径</div>
	    <div field="QYKG" headerAlign="center" allowSort="true" dictTypeId="CDKH0028">统计口径</div>
	    <div field="GSJG" headerAlign="center" allowSort="true" >统计口径</div>
	    <div field="KHMC" headerAlign="center" allowSort="true" >统计口径</div>
	    <div field="DKFFE" headerAlign="center" allowSort="true" >统计口径</div>
	    <div field="chnAmt" headerAlign="center" allowSort="true" >借据余额（万元）</div>
	    <div field="chnAmtGb" headerAlign="center" allowSort="true" >占比（%）</div>
	    <div field="chnAmtSum" headerAlign="center" allowSort="true">总计（万元）</div>
	    <div field="usdAmt" headerAlign="center" allowSort="true">借据余额（万美元）</div>
	    <div field="usdAmtGb" headerAlign="center" allowSort="true" >占比（%）</div>
	    <div field="usdAmtSum" headerAlign="center" allowSort="true" >总计（万美元）</div>
	    
		</div>
	</div>
</center>
 <script type="text/javascript">
 	var currencyCode = [{ id: '01', text: '本外币折人民币' }, { id: '02', text: '人民币'}, { id: '03', text: '外币折美元'}, { id: '04', text: '外币折人民币'}];
 	var creditRatingCode = [{ id: '', text: '--请选择--' },{ id: 'AAA', text: 'AAA' },{ id: 'AA+', text: 'AA+' },{ id: 'AA', text: 'AA' },{ id: 'AA-', text: 'AA-' },{ id: 'A+', text: 'A+' },
							{ id: 'A', text: 'A' },{ id: 'A-', text: 'A-' },{ id: 'BBB+', text: 'BBB+' },{ id: 'BBB', text: 'BBB' },{ id: 'BBB-', text: 'BBB-' },
							{ id: 'BB', text: 'BB' },{ id: 'B', text: 'B' },{ id: 'CCC', text: 'CCC' },{ id: 'CC', text: 'CC' },
							{ id: 'C', text: 'C' },{ id: 'D', text: 'D' },{ id: '未评级', text: '未评级' }];
	var classificationResult = [{ id: '', text: '--请选择--' },{ id: '01', text: '正常' }, { id: '02', text: '关注'}, { id: '03', text: '次级'}, { id: '04', text: '可疑'}, { id: '05', text: '损失'}, { id: 'no', text: '未分类'}];
 	var tjkj=[{ id: '', text: '--请选择--' },{ id: 'YWXZ', text: '业务性质' },{ id: 'YWCP', text: '业务产品' },{ id: 'DBFS', text: '担保方式' },
			  { id: 'DKTX', text: '贷款投向' },{ id: 'BZ', text: '币种' },{ id: 'XYDJ', text: '信用等级' },
			  { id: 'FXFL', text: '分类' },{ id: 'QYGM', text: '企业规模' },{ id: 'QYKG', text: '企业控股类型' },
			  { id: 'GSJG', text: '归属机构' },{ id: 'KHMC', text: '客户名称' },{ id: 'DKFFE', text: '贷款发放额' }];
 	
 	nui.parse();
 	nui.get("map/currencyType").setValue("01");
 	nui.get("map/creditRatingCd").setValue("");
 	nui.get("map/classificationResultCd").setValue("");
 	nui.get("map/loanDirection").setText("--请选择--");
 	nui.get("map/orgNum").setText("--请选择--");
 	nui.get("map/productType").setText("--请选择--");
 	nui.get("kouJ").setValue("YWXZ");
 	
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var YWCP =  grid.getColumn(1);
	var YWXZ =  grid.getColumn(2);
	var DBFS =  grid.getColumn(3);
	var DKTX =  grid.getColumn(4);
	var BZ =  grid.getColumn(5);
	var XYDJ =  grid.getColumn(6);
	var FXFL =  grid.getColumn(7);
	var QYGM =  grid.getColumn(8);
	var QYKG =  grid.getColumn(9);
	var GSJG =  grid.getColumn(10);
	var KHMC =  grid.getColumn(11);
	var DKFFE =  grid.getColumn(12);
	 var chnAmt =  grid.getColumn(13);
	 var chnAmtGb =  grid.getColumn(14);
	 var chnAmtSum =  grid.getColumn(15);
	 var usdAmt =  grid.getColumn(16);
	 var usdAmtGb =  grid.getColumn(17);
	 var usdAmtSum =  grid.getColumn(18);
	 grid.hideColumn(usdAmt);
	 grid.hideColumn(usdAmtGb);
	 grid.hideColumn(usdAmtSum);
	 grid.hideColumn(chnAmtSum);
	 	showGroup();
    function search() {
    git.mask("form1");
    var currency=nui.get("map/currencyType").getValue();
    	if(currency=="03"){
       		grid.showColumn(usdAmt);
       		grid.showColumn(usdAmtGb);
     		grid.hideColumn(chnAmtSum);
	 		grid.hideColumn(chnAmt);
	 		grid.hideColumn(chnAmtGb);
	 		grid.hideColumn(usdAmtSum);
    	}else{
    		grid.hideColumn(usdAmt);
       		grid.hideColumn(usdAmtGb);
     		grid.hideColumn(chnAmtSum);
	 		grid.showColumn(chnAmt);
	 		grid.showColumn(chnAmtGb);
	 		grid.hideColumn(usdAmtSum);
    	}
    	showGroup();
    	var kouj=nui.get("kouJ").getValue();
    	if(kouj=""){
    		nui.get("kouJ").setValue("YWXZ");
    	}
   	 var data = form.getData(); //获取表单多个控件的数据
     grid.load(data,function(){
     git.unmask("form1");
     });
	onDrawSummaryCell(grid)	;
    }
    //search();
    
    function reset(){
		form.reset();
	}
	
  var k;
//导出
    function exportEmp()
    {
    var rows = grid.findRows(function(row){
   	 	if(row.groupName != null) return true;
	});
	if(rows != null && rows.length > 0) {
     var form = document.getElementById("form1");
     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=223";
     form.submit();
    }else {
			alert('没有要导出的记录');
		}
    }
    
    
    //币种
function selectCurrcyCd(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择币种",
            width: 300,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	function selectLoanDirection(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_CDKH2011",
            title: "选择贷款投向",
            width: 300,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	//产品树
	function selectProduct(e) {
        var btnEdit = this;
         var productTypeCd=nui.get("map/productTypeCd").getValue();
        nui.open({
            url: nui.context + "/pub/LedgerMsg/LedStatistical/select_product_tree.jsp?productTypeCd="+productTypeCd,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
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
    
     function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            var rows = e.data;
            var total = 0;
            var time="";
            var currency=nui.get("map/currencyType").getValue();
    			if(currency=="03"){
    				 if (e.field == "usdAmt") {
            				if(rows.length>0){
            					var row=rows[0];
            					total=row.usdAmtSum;
            				}
            	 			e.cellHtml = "总计(万美元）: "+ total;
            			}
            		if (e.field == "usdAmtGb") {
            				if(rows.length>0){
            					var row=rows[0];
            					time=row.myTime;
            				}
            	 			e.cellHtml = "采集日期: "+ time;
            			}
    			}else{
           			 if (e.field == "chnAmt") {
            				if(rows.length>0){
            					var row=rows[0];
            					total=row.chnAmtSum;
            				}
            	 			e.cellHtml = "总计(万元）: "+ total;
            			}
            		if (e.field == "chnAmtGb") {
            				if(rows.length>0){
            					var row=rows[0];
            					time=row.myTime;
            				}
            	 			e.cellHtml = "采集日期: "+ time;
            			}
				}
               
            
        }
        
        function showGroup(){
        	var kouJ=nui.get("kouJ").getValue();
        	if(kouJ=="YWCP"){
        		grid.showColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="YWXZ"){
        		grid.hideColumn(YWCP);
        		grid.showColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="DBFS"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.showColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="DKTX"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.showColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="BZ"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.showColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="XYDJ"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.showColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="FXFL"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.showColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="QYGM"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.showColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="QYKG"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.showColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="GSJG"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.showColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="KHMC"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.showColumn(KHMC);
        		grid.hideColumn(DKFFE);
        	}else if(kouJ=="DKFFE"){
        		grid.hideColumn(YWCP);
        		grid.hideColumn(YWXZ);
        		grid.hideColumn(DBFS);
        		grid.hideColumn(DKTX);
        		grid.hideColumn(BZ);
        		grid.hideColumn(XYDJ);
        		grid.hideColumn(FXFL);
        		grid.hideColumn(QYGM);
        		grid.hideColumn(QYKG);
        		grid.hideColumn(GSJG);
        		grid.hideColumn(KHMC);
        		grid.showColumn(DKFFE);
        	}
        	
        }
	</script>
	
				
   
</body>
</html>
	
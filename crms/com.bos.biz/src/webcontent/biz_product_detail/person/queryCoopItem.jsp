<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): pc
  - Date: 2013-12-10 16:17:00
  - Description:选择合作项目
-->
<head>
<title>查询合作项目</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<br/>
<div id="form1" style="width:80%;height:auto;overflow:hidden;" >
	<input id="item.partyId" name="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
		
		<label>合作项目名称：</label>			
		<input id="item.itemName" name="item.itemName" class="nui-textbox nui-form-input"  />
	</div>

	</div style="width:99.5%">
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	<div>
	<div id="dsfxm" class="nui-datagrid"   sortMode="client"
	    url="com.bos.bizInfo.person.queryThirdProjectLimit.biz.ext" dataField="thirdLimits"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
		    <div type="checkcolumn">选择</div>
	        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
	        <div field="ITEM_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0227">合作项目类型</div>
	        <div field="PROJECT_NAME" allowSort="true" width="" headerAlign="center">合作项目名称</div>
	   	    <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
	        <div field="CREDIT_AMT" allowSort="true" width="" headerAlign="center">合作项目额度</div>
	        <div field="USED_AMT" allowSort="true" width="" headerAlign="center">已用合作项目额度</div>
	        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用合作项目额度</div>
	        <div field="ITEM_BEGIN_DATE" allowSort="true" width="" headerAlign="center" >合作项目额度起始日</div>
	        <div field="ITEM_END_DATE" allowSort="true" width="" headerAlign="center">合作项目额度终止日</div>
	        <div field="MAIN_ORG_ID" allowSort="true" width="" headerAlign="center"  dictTypeId="org">主办行</div>
	        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
	        <div field="DEAL_DATE" allowSort="true" width="" headerAlign="center">经办日期</div>
	    </div>
	</div>
	<div style="width:99.5%">
		<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
	    <a class="nui-button"  onclick="selected()">确认</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("dsfxm");
	var itemType = '';
	var productType = "<%=request.getParameter("productType")%>";
	if('02002003'==productType ||'02002004'==productType||'02002005'==productType
	||'02002010'==productType||'02002011'==productType){//个人住房
		itemType = '1';
	}else if('02004001'==productType ||'02004002'==productType||'02003012'==productType){
		itemType = '2';
	}
    function query(){
 		git.mask();
    	form.validate();
        if (form.isValid()==false){
        	 git.unmask(); 
        	 return;
        }
        
       var o = form.getData();
       o.item.itemType = itemType;
       grid.load(o);
       git.unmask();
    }
    
    
    function selected() {
    	var row = grid.getSelected();
        if (row) {
        	if(row.AVIFLAG == '1'){
        		nui.alert("合作项目额度已到期");
        		return;
        	}
           	CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
    
    function getData(){
    	var row = grid.getSelected();
      	if (row) {
            return row;
        } else {
            return null;
        }
    }
    
    function reset(){
		form.reset();
	}
</script>
</body>
</html>
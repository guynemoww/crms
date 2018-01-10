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
		<label>委托人：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002" />
		     			
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input"   />
		
		<label>委托项目名称：</label>			
		<input id="item.entrustProjectName" name="item.entrustProjectName" class="nui-textbox nui-form-input"  />
	</div>

	</div style="width:99.5%">
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button"  iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	<div>
	<div id="wtr" class="nui-datagrid"   sortMode="client"
	    url="com.bos.bizInfo.person.queryWtrList.biz.ext" dataField="wtrs"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
		    <div type="checkcolumn">选择</div>
	        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">委托人</div>
	        <div field="CERT_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
	        <div field="CERT_NUM" allowSort="true" width="" headerAlign="center">证件号码</div>
	   	    <div field="ENTRUST_PROJECT_NAME" allowSort="true" width="" headerAlign="center">委托项目名称</div>
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
	var grid = nui.get("wtr");
    function query(){
 		git.mask();
    	form.validate();
        if (form.isValid()==false){
        	 git.unmask(); 
        	 return;
        }
        
       var o = form.getData();
       grid.load(o);
       git.unmask();
    }
    
    
    function selected() {
    	var row = grid.getSelected();
        if (row) {
        	if(row.EXAMINE_STATE!='3'){
        		nui.alert("客户未校验完整性，请补充客户信息后重新引入");
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
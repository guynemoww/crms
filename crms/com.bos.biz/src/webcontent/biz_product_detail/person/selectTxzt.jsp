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
<div id="form1" style="width:80%;height:auto;overflow:hidden;" >
	<input id="item.partyId" name="item.partyId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>贴息主体：</label>			
		<input id="item.tiexiZt" name="item.tiexiZt" class="nui-textbox nui-form-input"  />
	</div>

	</div style="width:99.5%">
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div id="txzt" class="nui-datagrid"   sortMode="client"
	    url="com.bos.bizInfo.bizDetail.queryTxztList.biz.ext" dataField="txs"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
		    <div type="checkcolumn">选择</div>
	        <div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">机构名称</div>
	        <div field="TIEXI_ZT" allowSort="true" width="" headerAlign="center">贴息主体</div>
	        <div field="TIEXI_ZH" allowSort="true" width="" headerAlign="center">贴息账号</div>
	    </div>
	</div>
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    	<a class="nui-button"  onclick="selected()">确认</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("txzt");
	query();
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
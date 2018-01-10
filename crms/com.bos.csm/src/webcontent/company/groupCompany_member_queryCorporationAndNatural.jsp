<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2015-5-5 16:17:00
  - Description:
-->
<head>
<title>查询可以引入的成员</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" class="nui-form"style="width:99.5%;height:auto;overflow:hidden;" >
		<input id="sqlName" name="sqlName" value="com.bos.csm.party.partyList" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName"   class="nui-textbox nui-form-input"  />

			<label>证件类型：</label>
			<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			     			
			<label>证件号码：</label>			
			<input id="item.certCode" name="item.certCode" class="nui-textbox nui-form-input"   />
			<input id="item.examineState" name="item.examineState" value="3" class="nui-hidden nui-form-input"   />
			<input id="item.isPotentialCust" name="item.isPotentialCust" value="1" class="nui-hidden nui-form-input"   />
		</div>

	<div class="nui-toolbar" style="text-align:right;border:none;padding-right:20px;" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"pageSize="10"
	    sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >CRMS客户编号</div> 
	        <div field="partyName" allowSort="true" width="" headerAlign="center" >客户名称</div> 
	        <div field="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>       
	        <div field="certNum" allowSort="true" width="" headerAlign="center" >证件号码</div>
	        <div field="orgNum" allowSort="true" width="" headerAlign="center" dictTypeId="org">机构名称</div> 
	     </div>
</div>

	<div id="dataConfirm" class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="selected">选中</a>
	    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
    var arr = git.getDictDataFilter("CDKH0002",'202');
		nui.get("item.certType").setData(arr);
		nui.get("item.certType").setValue("202");
    function query(){
 		git.mask();
    	form.validate();
        if (form.isValid()==false){
        	 git.unmask(); 
        	 return;
        }
        
       var o = form.getData();
        grid.load ( o, function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		nui.get("datagrid1").show();
					nui.get("dataConfirm").show();
				<%--	nui.get("cxresult").show();--%>
            	}
            } );
       git.unmask();
    }
    
    query();
    function reset(){
    	form.reset();
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
    
  </script>
  
  
</body>
</html>
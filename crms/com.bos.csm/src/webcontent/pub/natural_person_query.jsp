<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 夏群
  - Date: 2013-12-10 16:17:00
  - Description:高管选择引入页面
-->
<head>
<title>查询自然人</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<br/>
<div id="form1" style="width:80%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="2">
			<label>自然人姓名：</label>
			<input id="natural.partyName" name="natural.partyName"   class="nui-textbox nui-form-input"  />
			
			<label>CRMS客户编号：</label>
			<input id="natural.partyNum" name="natural.partyNum" class="nui-textbox nui-form-input" />
			
			<label>ECIF客户编号：</label>
			<input id="natural.ecifPartyNum" name="natural.ecifPartyNum" class="nui-textbox nui-form-input" />
			
		</div>
</div>
<div style="width:99.5%">
	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="addNatural()">新增自然人</a>
	</div>
</div>
<div id="datagrid1"   class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.getNaturalPerson.queryNaturalPerson.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]">
	    <input id="corps" class="nui-hidden nui-form-input" name="corps"/>
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >CRMS客户编号</div> 
	        <div field="ecifPartyNum" allowSort="true" width="20%" headerAlign="center" >ECIF客户编号</div>                
	        <div field="partyName" allowSort="true" width="" headerAlign="center" >自然人姓名</div> 
	        <div field="contryRegionCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000003" >国家和地区</div> 
	      	<div field="certType" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>   
	        <div field="certCode" allowSort="true" width="" headerAlign="center" >证件号码</div>
	     </div>
</div>

<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;padding-left;" >
    <a class="nui-button"  onclick="selected()">选中</a>
</div>



<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	<%--nui.get("cxresult").hide();--%>
	

    
    
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
            		//alert("123");
            		nui.get("datagrid1").show();
					nui.get("dataConfirm").show();
				
					
            	}
            } );
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
    
    function addNatural(){
    	 nui.open({
	            url:nui.context + "/csm/naturalperson/naturalperson.jsp",
	            showMaxButton: true,
	            title: "新增自然人",
	            width: 800,
	            height: 600,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
    }
    
    
  </script>
  
  
</body>
</html>
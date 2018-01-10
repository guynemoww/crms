<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>我的客户-自然人</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName"  class="nui-textbox nui-form-input"  />
	
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_ZJLX0001"   />
			
			<label>证件号码：</label>
			<input id="item.certNum" name="item.certNum"  class="nui-textbox nui-form-input"/>
			
			<label>中征码：</label>
			<input id="item.middelCode" name="item.middelCode"  class="nui-textbox nui-form-input"/>
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0" >
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		   <%-- <a class="nui-button" onclick="exportCust">导出</a>--%>
		    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
		</div>
</div>		
		
	<!-- <div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">获取业务权</a>
		</div>
	</div> -->
		
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.natural.natural.queryNaturalPersonFordesk.biz.ext"  dataField="items"  allowAlternating="true"
		allowResize="true" showReloadButton="false"  
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>                  
	        <div field="partyName" allowSort="true" width="" headerAlign="center">客户名称</div>
	        <div field="certType" headerAlign="center" allowSort="true" dictTypeId="XD_ZJLX0001">证件类型</div>
	        <div field="certNum" headerAlign="center" allowSort="true">证件号码</div>
	        <div field="middelCode" headerAlign="center" allowSort="true">中征码</div>
	       	<div field="xfCreditRatingCd" headerAlign="center" allowSort="true">消费性评级</div>
	       	<div field="jyCreditRatingCd" headerAlign="center" allowSort="true">经营性评级</div>
			<div field="pfAmt" allowSort="true" width="" headerAlign="center"dataType="currency">批复金额</div>  
        	<div field="pfBalance" allowSort="true" width="" headerAlign="center"dataType="currency">批复已用金额</div>
	        <div field="mainUserNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">管户客户经理</div>  
	     </div>
	</div>
</div> 
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	function query(){//对公单一客户查询
       var o = form.getData(false, true);//逻辑流必须返回total
       grid.load(o);
     }
	query();
      grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].partyId+","+e.data[i].partyNum
       				+ '\');return false;" value="'
       				+ e.data[i].partyId
       				+ '">'+e.data[i]['partyName']+'</a>';
       		}
       });
	
	
	function add(){
		nui.open({
            url: nui.context + '/csm/workdesk/acquire_natural_list.jsp',
            showMaxButton: true,
            title: "自然人客户",
            width: 1200,
            height: 460,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function () {
              	query();
            }
        });
	}
	
	function reset() {
		form.reset();
	}
	
	
	
	function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab_private.jsp?corpid="
            + partyId+"&partyNum="+partyNum;
        nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
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
	
	//初始化自然人证件类型
	function init(){
 		//字典过滤，过滤掉202的证件类型
	    //var arr = git.getDictDataUnFilter("CDKH0002",'202');
		var arr = git.getDictDataFilter("CDKH0002","101,102,110,121,122,123,132,140,150,151,153,199");
		nui.get("item.certType").setData(arr);
	}
    init();	
</script>
</body>
</html>
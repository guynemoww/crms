<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-08
  - Description:
-->
<head>
<title>重点检查</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>重点检查合同列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<input name="map.transContractNum" id="map.transContractNum" required="false" class="nui-hidden nui-form-input" />
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			<!-- <input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" /> -->
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<!-- <a class="nui-button"onclick="query">查询</a> -->
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.otherCheck.findContractList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" headerAlign="center" align="center">客户编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002">证件类型</div>
		        <div field="CERT_NUM" align="center" headerAlign="center" >证件号码</div>  
		        <div field="CONTRACT_NUM" align="center" headerAlign="center" >合同编号</div> 
		        <div field="CONTRACT_AMT" headerAlign="center" align="right" dataType="currency">合同金额</div>
				<div field="CON_YU_E" headerAlign="center" align="right" dataType="currency">合同已用金额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center">合同起期</div>
				<div field="END_DATE" headerAlign="center" align="center">合同止期</div> 
				<div field="CREATE_DATE" headerAlign="center" align="center">上次重点检查日期</div>
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //合同列表
	query();
	function query(){
		var transContractNum=nui.get("map.transContractNum");
		var contractNum="<%=request.getParameter("contractNum")%>";
		if(null!=contractNum&&"null"!=contractNum&&""!=contractNum){
			transContractNum.setValue(contractNum);
		}
		var o = form.getData();
		grid.load(o);
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			if(null !=e.data[i].PARTY_NAME && ''!=e.data[i].PARTY_NAME){
	       			//客户链接
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
	       			//合同链接
	       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['CONTRACT_NUM']+'</a>';
	       				
       			}else{
       			
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['ENGLISH_NAME']+'</a>';
       			}
       		}
       });
	}
	
	function goToLoan(e){
		var row=grid.getSelected();
		nui.open({
            url:nui.context +"/crt/con_info/con_tree.jsp?contractId="+row.CONTRACT_ID+"&contractType=02&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		var partyTypeCd = ps[3];
        if(partyTypeCd=="01") {
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd+"&qote=1";
        }else {
	        var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&qote=1";
        }
        
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
            },
            ondestroy: function (action) {
                query();
            }
  	  });	
            
	}
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔合同");
			return false;
		}
		nui.get("btnCreate").setEnabled(false);
		
		var json=nui.encode({"partyId":row.PARTY_ID,"contractId":row.CONTRACT_ID,"checkType":"p"});
		$.ajax({
	        url: "com.bos.aft.otherCheck.createCheckInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	if(data.flag){
                	return nui.alert(data.flag);
                }
            	nui.open({//传值到tree页面
		            url: nui.context+"/aft/otherCheck/check_tree.jsp?checkId="+data.tbAftPointCheck.checkId+"&partyId="+row.PARTY_ID+"&checkType=p"+"&processInstId="+data.processInstId+"&proFlag=1&partyNum="+row.PARTY_NUM+"&partyTypeCd="+row.PARTY_TYPE_CD, 
		            /* url: nui.context+"/aft/otherCheck/check_tree.jsp?checkId="+data.tbAftPointCheck.checkId+"&partyId="+row.PARTY_ID+"&checkType=p", */
		            showMaxButton: false,
		            title: "重点检查",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	query();//重新刷新页面
		            }
	        	});
	        	if(data.msg){
                	if(data.msg != "创建成功！") {
	        			alert(data.msg);
	        		}
                }
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
	
	//初始化自然人证件类型
	/* function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	 */
    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>
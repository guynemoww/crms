<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-26
  - Description:
-->
<head>
<title>合同变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>可变更的合同信息</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	
		<div class="nui-dynpanel" columns="6">
		
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>营业执照：</label>
			<input name="map.registrCd" id="map.registrCd" required="false" class="nui-textbox nui-form-input"  />
	
			<label>组织机构代码：</label>
			<input name="map.orgRegisterCd" id="map.orgRegisterCd" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>中征码：</label>
			<input name="map.middelCode" id="map.middelCode" required="false" class="nui-textbox nui-form-input" />
			
			<a class="nui-button"onclick="query">查询</a>
		</div>
	
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
			<label class="nui-form-label">合同变更类型：</label>
			<input name="conChangeType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHBG0001" id="conChangeType" 
					style="margin-right:5px;height:21px;width:13%"/>
			<a class="nui-button" id="btnCreate" onclick="add" style="margin-right:27px;height:21px">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.conLoanChange.findConList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
				<div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务种类</div>
				<div field="CONTRACT_AMT" headerAlign="center" align="right" dataType="currency">贷款金额</div>
				<div field="CON_BALANCE" headerAlign="center" align="right" dataType="currency">贷款余额</div>
				<!--<div field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002">五级分类</div>
		        <div field="CERT_NUM" headerAlign="center" align="center">贷款形态</div>   -->
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var conChangeType = nui.get("conChangeType");
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //合同列表
	query();
	function query(){
		var o = form.getData();
		grid.load(o);
		//grid.load({"partyName":o.map.partyName,"contractNum":o.map.contractNum});
	}
	function add(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔合同");
			return false;
		}
		
		if(conChangeType.getValue()=="05") {
			return nui.alert("担保方式变更需求确认中。。。。。。");
		}
		
		if(conChangeType.getValue()=="09") {
			return nui.alert("贴息调整需求确认中。。。。。。");
		}
		
		if(conChangeType.getValue()=="") {
			return nui.alert("请选择合同变更类型");
		}
		
		if(conChangeType.getValue()=="11") {
			return nui.alert("合同变更中无提前还款，提前还款在借据变更中");
		}

		if(conChangeType.getValue()=="02" && row.REPAYMENT_TYPE=="1200") {
			return nui.alert("还款方式为到期一次性还本付息的不能调整");
		}
		
		nui.get("btnCreate").setEnabled(false);
		
		//var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"conChangeType":"01"});//传值到创建方法
		var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"conChangeType":conChangeType.getValue()});//传值到创建方法
		$.ajax({  
	        url: "com.bos.aft.conLoanChange.createConLoanChange.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
	           	nui.open({//传值到tree页面
		            url: nui.context+"/aft/conLoanChange/conChange_tree.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&conChangeType="+conChangeType.getValue()+"&processInstId="+data.processInstId+"&proFlag=1",
		            showMaxButton: false,
		            title: "合同变更",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	query();//重新刷新页面
		            }
	        	});
	        	if(data.msg){
                	alert(data.msg);
                }
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
	
	//初始化自然人证件类型
	function init(){
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
    init();	
</script>
</body>
</html>
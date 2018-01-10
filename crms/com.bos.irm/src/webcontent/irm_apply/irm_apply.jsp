<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 11:12:44
  - Description:
  irm/singleCustom/creditRate/eval_corp_new.jsp
-->
<head>
<title>评级申请</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<fieldset>
  	<legend>
    	<span>基本信息</span>
    </legend>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">客户编号：</label>
			<input name="party.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">客户名称：</label>
			<input name="party.partyName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
 	
 
 
		</div>
	</div>
	</fieldset>
		<!--<label>注：若要发起评级，请先在财务报表模块录入客户年度财务报表；若确实无年报，请继续评级发起流程。</label>-->
		 
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
 		
		<div id="div2" >
		<label class="nui-form-label"> </label>
		<a class="nui-checkbox"   id="choose" onclick="">企业成立未满 1 年或从事主要经营活动未满 1 年 </a>   
		<a class="nui-button" iconCls="icon-add"   id="create" onclick="add">发起评级</a>
		<a class="nui-button" iconCls="icon-edit" onclick="viewHistory">查看</a>
		</div> 
		<div id="div1" >
		    <label class="nui-form-label">评级类型：</label>
             <input id="pjlx"   class="nui-combobox" style="width:10%;"  data="pjlxs" required="true" />  
 		<a class="nui-button" iconCls="icon-add"   id="create" onclick="add">发起评级</a>
		<a class="nui-button" iconCls="icon-edit" onclick="viewHistory">查看</a>
             	</div>
             	
 		</div> 
  
		<fieldset>
			<legend>
				<span>评级历史信息</span>
			</legend>
			<div id="grid" class="nui-datagrid" style="width:99.6%;height:auto;margin-top:10px"  sortMode="client"
			    url="com.bos.irm.irmApply.irmApply.getHisIrm.biz.ext" dataField="ratingResults"
			    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
			    emptyText="没有查到数据" showReloadButton="false"
			    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
			    sizeList="[10,20,50,100]" pageSize="10">
			    <div property="columns">
			    	<div type="checkcolumn"> 选择 </div>
			    	<div align="center" field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
			        <div align="center" field="RATING_DT" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">评级日期</div>
			        <div align="center" field="RATING_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_PJCD0111">评级类型</div>
			        <div align="center" field="CREDIT_RATING_CD" allowSort="true" width="" headerAlign="center">信用等级</div>
			        <div align="center" field="EFFECTIVE_START_DT" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">起始日期</div>
			        <div align="center" field="EFFECTIVE_END_DT" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">到期日期</div>
			        <div align="center" field="RATING_STATE" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003">是否有效评级</div>
			        <div align="center" field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
			        <div align="center" field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">经办机构</div> 
 			    </div>
			</div>
		</fieldset>
	</div>	
<script type="text/javascript">
 		var pjlxs = [{ id: '1', text: '消费性' }, { id: '2', text: '经营性'}];
	nui.parse();
//	git.mask();
	var partyId="<%=request.getParameter("corpid") %>";//参与人id
	var form = new nui.Form("#form1");
	var form2 = new nui.Form("#div1");
	var corpCustomerTypeCd;//对公客户类型
	
	var grid = nui.get("#grid");
	var partyTypeCd;
	$("#div1").css("display","none");
	$("#div2").css("display","block");
	
	init();//页面初始化
	function init(){
 		
		var json = nui.encode({"partyId":partyId});
      	nui.ajax({
	        url: "com.bos.irm.irmApply.irmApply.getPartyInfoByPartyId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        async:false,
	        contentType:'text/json',
	        success: function (text) {
	       		var o = nui.decode(text);
	       		partyTypeCd=o.party.partyTypeCd; 
	            form.setData(o);
	        }
	    });
	    //通过partyID查询公司客户的信息
	    nui.ajax({
			url : 'com.bos.irm.irmApply.irmApply.getCorporationInfoByPartyId.biz.ext',
			type : 'POST',	    
	    	data : json,
	    	cache : false,
	    	async : false,
	    	contentType : 'text/json',
	    	success : function(text){
	    		var o = nui.decode(text);
	    		debugger;
	    		corpCustomerTypeCd = o.corporation.corpCustomerTypeCd; 
	    	}
	    });
	    
	    grid.load({"partyId":partyId});
	    if(partyTypeCd=="02"){
	    	$("#div1").css("display","block");
	    	$("#div2").css("display","none");
	    	
	    
	    }
    }
	function add(){
		form2.validate();
	    if (form2.isValid()==false){
	        nui.alert("请选择评级类型！");
	       	return;  
	    }
 		nui.get("create").setEnabled(false);
		var choose =nui.get("choose").getValue();
			if(partyTypeCd=="02"){
				var pjlx=nui.get("pjlx").getValue();
			}else{
				var pjlx="3";
			}
		var json = {"partyId":partyId,"pjlx":pjlx};
   	    	msg = exeRule("PJSQ_0001","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("create").setEnabled(true);
		   		return;
	   	}
	   	msg = exeRule("XFE_0002","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return;
	   	}
	   	//发起评级之前 需要先做企业规模认定
   	    if(partyTypeCd=='01' && corpCustomerTypeCd!='3'){//企业规模认定只对企业客户，对私、个体工商户不需要
   	   	 	var json = {"partyId":partyId};
		 	var msg = exeRule("RBIZ_0071","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		nui.get("create").setEnabled(true);
		   		return;
	   	    }
		}
	   	
 		var json = nui.encode({"partyId":partyId,"choose":choose,"pjlx":pjlx});
      	nui.ajax({
	        url: "com.bos.irm.irmApply.irmApply.createIrmApply1.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        async:false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        		nui.get("create").setEnabled(true);
	        		return;
	        	}
        		nui.get("create").setEnabled(true);
            	git.go(nui.context+"/irm/irm_apply/irm_tree.jsp?iraApplyId="+text.irmApply.iraApplyId+"&processInstId="+text.processInstId+"&proFlag=1"+"&pjlx="+pjlx,parent);
	        }
	        
	    });
    }
    function viewHistory(){
  		var row = grid.getSelected();
   		if(row){
   			 if(row.IRA_APPLY_ID){
   			 }else{
   			return alert("没有可查看的评级报告");
   			}   			
   			nui.open({
		        url:  nui.context + "/irm/financialCustom/financial_view_report_jj.jsp?bizId="+row.IRA_APPLY_ID,
		        title: "查看评级报告",
		        state:"max",
		        onload: function () {
		        },
	            ondestroy: function (action) {
	            }
	        });	
   		}else{
   			return alert("请选择一条评级信息");
   		}
    }
		function check(){

   		}
</script>
</body>
</html>
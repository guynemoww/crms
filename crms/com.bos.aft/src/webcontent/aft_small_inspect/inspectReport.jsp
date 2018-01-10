<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查报告</title>
</head>
<body style="margin-left:10px;">
<fieldset style="margin-top: 10px;">
    <legend>
        <span>检查方式</span>
    </legend>
    <div id="editform" class="nui-dynpanel" columns="4"  >
		<label>检查方式：</label>
		<input name="inspectWayCd" id="inspectWayCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  onhidepopup="hidepopup" required="true" /> 
		<label>检查时间/约见时间：</label>
		<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
		
		<label>接待人员姓名：</label>
		<input name="receptionName" id="receptionName" class="nui-textbox nui-form-input"/>
		<label>接待人员职务：</label>
		<input name="receptionPost" id="receptionPost" class="nui-textbox nui-form-input"/>
		<div id="inspectMess" colspan="4">  </div>
	</div>
</fieldset>

<fieldset  style="margin-top: 10px;">
    <legend>
        <span>审批意见落实情况（除交叉销售情况）</span>
    </legend>

	<div id="condition" class="nui-dynpanel" columns="2"  >
		<label>授信资金的流向符合审批要求是否落实：</label>
		<input name="capitalFlowsCon" id="capitalFlowsCon" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
	</div>
</fieldset>
	
<fieldset  style="margin-top: 10px;">
    <legend>
        <span>审批意见书中关于授信后管理要求的落实情况</span>
        <a  id="addBtn" class="nui-button" iconCls="icon-add" onclick="btnAdd(1)" style="float: right;">点击此处填写</a>
    </legend>
	
	<div id="datagrid1" class="nui-datagrid" showPager="false"
        url="" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns"> 
       		 <!--ComboBox：本地数据-->         
            <div type="comboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="50"  align="left" headerAlign="left">是否落实
	                <input property="editor" class="nui-combobox" style="width:50%;" data="Genders" />                
	        </div> 
            <div field="approvalInfo" width="120" headerAlign="center">平台整体审批条件
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="notImpReason" width="120" headerAlign="center">未落实原因描述
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="someMeasure" width="120" headerAlign="center">相关措施
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
        </div>
     </div>	
</fieldset>

<fieldset style="margin-top: 10px;">
    <legend>
        <span>特定管理要求的落实情况（如涉及）：</span>
        <a  id="addBtn2" class="nui-button" iconCls="icon-add" onclick="btnAdd(2)" style="float: right;">点击此处填写</a>
    </legend>
	
	<div id="datagrid2" class="nui-datagrid" showPager="false" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns"> 
       		 <!--ComboBox：本地数据-->         
            <div type="comboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="100"  align="center" headerAlign="center">是否落实
	                <input property="editor" class="nui-combobox" style="width:100%;" data="Genders" />                
	        </div> 
            <div field="approvalInfo" width="120" headerAlign="center">平台整体审批条件
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="notImpReason" width="120" headerAlign="center">未落实原因描述
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="someMeasure" width="120" headerAlign="center">相关措施
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
        </div>
     </div>	
</fieldset>
  <!--  <label><span style="font-weight:bold;">交叉销售情况（请在此简述对于授信企业的交叉销售情况，包括审批意见要求的审批要求以外的，覆盖我行结算量、金融产品和服务的使用情况等）:</span></label>
    <div class="" style="border-bottom:0;text-align:left;padding-top: 10px;padding-bottom:10px;" >
		<label><span>审批意见书中关于交叉销售的落实情况：</span></label>
		<a  id="addBtn3" class="nui-button" iconCls="icon-add" onclick="btnAdd(3)">增加一行</a>
	</div>
   -->	
	<!-- <div id="datagrid3" class="nui-datagrid" showPager="false"
        url="" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns">  -->
       		 <!--ComboBox：本地数据        
            <div type="comboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="100"  align="center" headerAlign="center">是否落实
	                <input property="editor" class="nui-combobox" style="width:100%;" data="Genders" />                
	        </div> 
            <div field="approvalInfo" width="120" headerAlign="center">平台整体审批条件
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="notImpReason" width="120" headerAlign="center">未落实原因描述
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="someMeasure" width="120" headerAlign="center">相关措施
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div> </div>
     </div>	 --> 
       
    <!-- <label><span style="font-weight:bold;">审批要求以外的交叉销售情况:</span></label>
     <div>
	      <input class="nui-textarea nui-form-input" name="acrossSaleCon" id="acrossSaleCon" style="width:100%;"/>
	 </div>-->
<fieldset  style="margin-top: 10px;">
    <legend>
        <span>企业财务情况:</span>
    </legend>
	<div id="finaceCondition" class="nui-dynpanel" columns="4"  >
		<div style="margin-top: 10px;">企业现金流是否正常:</div>
		<input colspan="3" name="isCashFlowNormal" id="isCashFlowNormal" class="mini-dictradiogroup nui-form-input" style="margin-bottom: 5px;" dictTypeId="YesOrNo" /> 
		<div style="margin-top: 10px;">资产负债率过高，较年初上升幅度超过20个百分点:</div>
		<input name="finChgInd01" id="finChgInd01" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<label>原因/相关措施</label>
		<input name="finChgInd01Remark" id="finChgInd01Remark" class="nui-textbox nui-form-input" style="margin-bottom: 10px;" />
		<div style="margin-top: 10px;">流动比率、速动比率等过低，较年初下降幅度超过20个百分点:</div>
		<input  name="finChgInd02" id="finChgInd02" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<label>原因/相关措施</label>
		<input name="finChgInd02Remark" id="finChgInd02Remark" class="nui-textbox nui-form-input" style="margin-bottom: 10px;"/>
		<div style="margin-top: 10px;">存货金额激增，存货周转速度大幅减慢(变化幅度超过30%):</div>
		<input name="finChgInd03" id="finChgInd03" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<label>原因/相关措施</label>
		<input name="finChgInd03Remark" id="finChgInd03Remark" class="nui-textbox nui-form-input" style="margin-bottom: 10px;"/>
		<div style="margin-top: 10px;">主营业务收入（销售收入）比上年同期下降幅度超过20%</div>
		<input name="finChgInd04" id="finChgInd04" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<label>原因/相关措施</label>
		<input name="finChgInd04Remark" id="finChgInd04Remark" class="nui-textbox nui-form-input" style="margin-bottom: 10px;"/>
		<div style="margin-top: 10px;">获利能力大幅下降，利润下降超过20%或出现亏损</div>
		<input name="finChgInd05" id="finChgInd05" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<label>原因/相关措施</label>
		<input name="finChgInd05Remark" id="finChgInd05Remark" class="nui-textbox nui-form-input" style="margin-bottom: 10px;"/>
	</div>
</fieldset>
<fieldset  style="margin-top: 10px;">
    <legend>
        <span>其它需重点说明的问题（借款人及担保人存在其它需说明的异常情况、抵质押物查询结果等）:</span>
    </legend>
	    <input class="nui-textarea nui-form-input" name="otherImportantProblem"  id="otherImportantProblem" style="width:100%;"/>
</fieldset>
<fieldset style="margin-top: 10px;">
    <legend>
        <span>检查意见:</span>
    </legend>
     
	 <div id="form1">
		<div class="nui-dynpanel" columns="5">
				<div style="text-align:left"><label>履行能力/综合评价1</label></div>                           
				<input name="pfAbility" id="pfAbility" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"  colspan="4"/>
				<div style="text-align:left"><label>检查结论</label></div>
				<input name="pfConclusion" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0005" colspan="4"/>
				<div style="text-align:left"><label>客户资产分类是否调整</label></div>
				<div name="pfPropertyAdjust" required="true" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" onvaluechanged="valuechanged" id="pfPropertyAdjust" colspan="4">
				</div>
				<div style="text-align:left"><label>调整类型</label></div>	
				<input name="pfAdjustType" class="nui-dictcombobox nui-form-input" id="pfAdjustType" dictTypeId="XD_DHCD4001" colspan="4"/>
				<div style="text-align:left"><label>调整理由</label></div>
				<input name="pfAdjustReason" width="50%" class="nui-textarea nui-form-input"  value="" id="pfAdjustReason" colspan="4"/>
	            <div style="text-align:left"><label>控制措施</label></div> 
				<input name="pfRiskcontrol" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0006" colspan="4"/>
		</div>		
	</div>		
</fieldset>


<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>责任管理体系</span>
    </legend>
 <div id="form" style="width:99.5%;height:auto;overflow:hidden;margin-top: 10px;">
		    <input id="postId" class="nui-hidden nui-form-input"  name="postId" value=<%=request.getParameter("bizId")%> />
			<div class="nui-dynpanel" columns="2">
			
			<label class="nui-form-label" id="scfBusiness">岗位责任人（评审审批） ：</label>
			<input id="postName3" name="postName3" style="width: 100%;" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectUser(postName3)" required="true" />
			
			</div>
		
</div>
</fieldset>	


<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>报告评分</span>
    </legend>
    <div style="text-align: right;">
         <label sytle="width:40%;">当前报告所得评分：</label>
         <input id="reportNumber" name="postName3" style="width: 55%;"  class="nui-comboBox nui-form-input"  textField="text" valueField="id"
         emptyText="--请选择--" data="[{'id':'1','text':'一分'},{'id':'2','text':'二分'},{'id':'3','text':'三分'},{'id':'4','text':'四分'},{'id':'5','text':'五分'}]" />
    </div>
</fieldset>	   
    <!-- <a href="#" onclick="clickDownload()">贷后检查报告下载</a> -->
	<div class="nui-toolbar"  id="saveBtn" style="border-bottom:0;text-align:right;margin-top: 20px;">
		 <a class="nui-button" id="btnSave" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>
	<iframe name="x" id="x" style="display:none;"></iframe>
</body>
<script type="text/javascript">
	var Genders = [{ id: 'y', text: '是' }, { id: 'n', text: '否'}];
	var callback="<%=request.getParameter("callback") %>";
	if("<%=request.getParameter("callback") %>"=="y"){
			$("#saveBtn").hide();
			$("#addBtn").hide();
			$("#addBtn2").hide();
			$("#addBtn3").hide();
			$("#datagrid1").attr("allowCellEdit","false");
			$("#datagrid2").attr("allowCellEdit","false");
			//$("#datagrid3").attr("allowCellEdit","false");
			$(".nui-textarea").attr("allowInput","false");
			$(".nui-textbox").attr("allowInput","false");
			$(".nui-datepicker").attr("enabled","false");
			$(".nui-dictcombobox").attr("enabled","false");
			$(".nui-combobox").attr("enabled","false");
			$(".mini-dictradiogroup").attr("enabled","false");
		}
	var inspectWayCd = [
				{id:"1",text:"现场检查"},
				{id:"2",text:"约见检查"},
				{id:"3",text:"其他检查方式"}
			];
	var receptionType = [
				{id:"1",text:"企业接待人员"},
				{id:"2",text:"实际控制人或主要股东"},
				{id:"3",text:"财务管理人员"},
				{id:"4",text:"其他"}
			];
	nui.parse();
	git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	var form = new nui.Form("#editform");
	var grid=nui.get("datagrid1");
	var grid2=nui.get("datagrid2");
	//var grid3=nui.get("datagrid3");
	var condition=new nui.Form("#condition");
	var finaceCondition=new nui.Form("#finaceCondition");
	var form1=new nui.Form("#form1");
	var param=<%=request.getParameter("param") %>;
	var processInstId=<%=request.getParameter("processInstId") %>;
	
	 //查询人员信息
	function selectUser(i) {
			nui.open({
            url: nui.context + "/aft/postMgr/pro_post_responsible_member.jsp?bizId="+param.partyId+"&responsiblePersonType=8",
            title: "选择人员信息",
            width: 1000,
            height: 600,
            ondestroy: function (action){
                getUserName(i);
        	}
        });     
	}
	
	function getUserName(i){
	 	 $.ajax({
	            url: "com.bos.aft.choosePost.getUserNames.biz.ext",
	            type: 'POST',
	            data: nui.encode({"bizId":param.partyId,"responsiblePersonType":"8"}),
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	 nui.get(i).setText(text.userNames);
	            	 nui.get(i).setValue(text.userNames);
	            }
	        });
	 
	 }
	
	
	//查询客户信息。
	function queryCorp(){
	    getUserName("postName3");
		var json=nui.encode({"param":param});
        $.ajax({
            url: "com.bos.aft.aft_small_inspect.querySmbInspectDetail.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	form.setData(mydata.smbInspectDetail);
            	if(nui.get("inspectWayCd").getValue()!=""){
            		hidepopup();
            		var inspectform=new nui.Form("#inspectform");
            		    inspectform.setData(mydata.smbInspectDetail);
            	}
            	condition.setData(mydata.smbInspectDetail);
            	form1.setData(mydata.smbInspectDetail);
            	finaceCondition.setData(mydata.smbInspectDetail);
            	//nui.get("acrossSaleCon").setValue(mydata.smbInspectDetail.acrossSaleCon);
            	nui.get("otherImportantProblem").setValue(mydata.smbInspectDetail.otherImportantProblem);
            	grid.loadData(mydata.smbConOuts1);
            	grid2.loadData(mydata.smbConOuts2);
            	//grid3.loadData(mydata.smbConOuts3);
            
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
        });
	}
	
	queryCorp();
	function hidepopup(){/* 检查方式改变时发生 */
			var inspectWayCd = nui.get("inspectWayCd").getValue();/* 客户编号 */
			var htm1="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label >检查地点：</label>"+
					"<input name='inspectPlace' id='inspectPlace' class='nui-textbox nui-form-input'/>"+ 
					"<label>接待人员类型：</label>"+
					"<input name='receptionTypeCd' id='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010'/> "+
					"</div>";
			var htm2="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label>接待人员类型：</label>"+
					"<input name='receptionTypeCd' id='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010'/> "+
					"</div>";
			var htm3="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label>其他检查方式：</label>"+
					"<input name='otherInspectWay' id='otherInspectWay' class='nui-textbox nui-form-input'/> "+
					"</div>";
		    if(inspectWayCd==1){
		    	$("#inspectMess").html(htm1);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }else if(inspectWayCd==2){
		    	$("#inspectMess").html(htm2);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }else if(inspectWayCd==3){
		    	$("#inspectMess").html(htm3);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }
			
      }	
	function btnAdd(a){
			
		    if(a==1){
		    	var index=grid.totalCount;
		    	var newRow = { name: "New Row" };
		    	grid.addRow(newRow, index);
		    }else if(a==2){
		    	var index2=grid2.totalCount;
		    	var newRow2 = { name: "New Row" };
		    	grid2.addRow(newRow2, index2);
		    }//else if(a==3){
		    	//var index3=grid3.totalCount;
		    	//var newRow3 = { name: "New Row" };
		    	//grid3.addRow(newRow3, index3);
		  //  }
            
		}
	function btnSave(){
		nui.get("btnSave").setEnabled(false);
		if(nui.get("inspectWayCd").getValue()==""){
			alert("请选择检查方式！");
			nui.get("btnSave").setEnabled(true);
			return;
		}
		var inspectform=new nui.Form("#inspectform");
		inspectformData=inspectform.getData();
		formData=form.getData();
		conditionData=condition.getData();
		form1Data=form1.getData();
		finaceConditionData=finaceCondition.getData();
		gridData=grid.findRows();
		grid2Data=grid2.findRows();
		//grid3Data=grid3.findRows();
		//var acrossSaleCon=nui.get("acrossSaleCon").getValue();
		var otherImportantProblem=nui.get("otherImportantProblem").getValue();
		//,"acrossSaleCon":acrossSaleCon
		var json=nui.encode({"param":param,"otherImportantProblem":otherImportantProblem,"inspectformData":inspectformData,"formData":formData,"conditionData":conditionData,"form1Data":form1Data,"finaceConditionData":finaceConditionData,"gridData":gridData,"grid2Data":grid2Data,"processInstId":processInstId});//,"grid3Data":grid3Data
		$.ajax({
            url: "com.bos.aft.aft_small_inspect.updateSmbInspectDetail.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	
            	alert(mydata.msg);
            	nui.get("btnSave").setEnabled(true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    nui.get("btnSave").setEnabled(true);
            }
        });	
	}
	 function valuechanged(){
			var pfPropertyAdjust = nui.get("pfPropertyAdjust");
			var pfAdjustType = nui.get("pfAdjustType");
			var pfAdjustReason = nui.get("pfAdjustReason");
			var i = pfPropertyAdjust.getValue();
			if(i == 1){
				pfAdjustType.setEnabled(true);
				pfAdjustReason.setEnabled(true);
			}else{
				pfAdjustType.setEnabled(false);
				pfAdjustReason.setEnabled(false);
				pfAdjustType.setValue("");
				pfAdjustReason.setValue("");
			}
		}
	function clickDownload(){
		document.getElementById('x').src="com.bos.aft.aft_small_inspect.downloadSmallBusiReport.biz.ext2?smId="+param.smId+"&partyId="+param.partyId+"&processId="+processInstId;
		return;
	}
</script>
</html>
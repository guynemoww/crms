<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): cc
  - Date: 2016-06-01 10:36:15
  - Description:
-->
<head>
<title>保证合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
	    <input id="subContract.subcontractId" name="subContract.subcontractId" class="nui-hidden nui-form-input" />
	    <input id="tbConAttachedInfo.attached" name="tbConAttachedInfo.attached" class="nui-hidden nui-form-input"/>
	    <input id="subContract.contractId" name="subContract.contractId" class="nui-hidden nui-form-input"/>
	<fieldset>
			<legend>
				   <span>基本信息</span>
			</legend>
	<div  class="nui-dynpanel" columns="4">
	    <label>保证合同编号：</label>
		<input name="subContract.subcontractNum" id="subContract.subcontractNum" class="nui-text nui-form-input" />
		
<%--		<label>保证人名称：</label>
		<input id="subContract.partyName" name="subContract.partyName"  class="nui-text nui-form-input"  style="width:86%;" />--%>
		
	    <label>保证合同纸质编号：</label>
		<input name="subContract.paperConNum"  id="subContract.paperConNum" class="nui-textbox nui-form-input" />
		
		<label>是否最高额：</label>
		<input name="subContract.ifTopSubcon" id="ifTopSubcon" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" required="true" onvaluechanged="changeStatus"/>
		
		<label>币种：</label>
		<input name="subContract.bz"  id="subContract.bz" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true"  allowInput="false" enabled="false"/>
		
		<label id="zgbjxeLab">保证最高本金限额：</label>
		<input id="subContract.zgbjxe" name="subContract.zgbjxe"  class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="currency"/>
		
		<label id="beginDateLab">保证额度起期：</label>
		<input name="subContract.beginDate" id="subContract.beginDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" onvaluechanged="checkDate()"/>
	    
	    <label id="endDateLab">保证额度止期：</label>
		<input name="subContract.endDate" id="subContract.endDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false"  minDate="<%=GitUtil.getBusiDateStr()%>" onvaluechanged="checkDate()"/>
		
		<label>保证人股权变更达到（）%时，应取得债权人的书面同意：</label>
		<input name="subContract.changePercent"  class="nui-textbox nui-form-input"  vtype="float;range:1,100;maxLength:8" />
		
		<!-- <label>支付违约金比例(%)：</label>
		<input name="subContract.zfwyjbl"  class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8" required="true"/>
  -->   </div>
    <div class="nui-dynpanel" columns="4" id="dbxs">  
      <label class="nui-form-label">保证担保方式：</label>
          <input id="subContract.proguarantyWay" name="subContract.proguarantyWay" data="data" valueField="dictID" 
           class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0006"  required="true" />
      <label class="nui-form-label">保证担保类型：</label>
          <input id="subContract.proguarantyType" name="subContract.proguarantyType" data="data" valueField="dictID" 
           class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0007"  required="true" />
      <label class="nui-form-label">保证担保形式：</label>
          <input id="subContract.proguarantyForm" name="subContract.proguarantyForm" data="data" valueField="dictID" 
           class="nui-dictcombobox nui-form-input" dictTypeId="XD_DBCD4005"  required="true" />
    </div>
  </fieldset>
  
  		<div class="nui-dynpanel" columns="1" id="table6">
				<fieldset>
					<legend>
						<span>仲裁方式</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_zcfs.jsp"%>
				</fieldset>
			</div>

			<div class="nui-dynpanel" columns="1" id="table7">
				<fieldset>
					<legend>
						<span>协议签署</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_xyqs.jsp"%>
				</fieldset>
			</div>
  
  <fieldset>
		  	<legend>
		   		<span>补充条款</span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
		   <label>补充条款：</label>
			<input name="tbConAttachedInfo.addClause" class="nui-textArea nui-form-input" vtype="maxLength:2000" colspan="3"/>
		</div>
  </fieldset>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;text-align: right;" borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-save" id="addEdit" onclick="save()">确认</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	
	var contractType="<%=request.getParameter("contractType")%>";
	
	var view="<%=request.getParameter("view") %>";
		
	var applyId="<%=request.getParameter("applyId")%>";
	
	var subcontractId="<%=request.getParameter("subcontractId") %>";
	
	var conSubconId="<%=request.getParameter("conSubconId")%>";
	
	var contractId="<%=request.getParameter("contractId")%>";
	
	if (view=="1") {
		form.setEnabled(false);
		nui.get("addEdit").hide();
	} 
	
    initForm();
	//初始化查询
	function initForm(){
		var json=nui.encode({"subContract":{"subcontractId":subcontractId},"contractId":contractId});
		$.ajax({
	        url: "com.bos.grt.conGrt.getGrtConInfo.biz.ext",    
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        		var guarantyType = nui.encode(text.contractInfo.guarantyType);//从主合同表里取出担保方式字段
					var o = nui.decode(text);
	        		form.setData(o);
	        		nui.get("subContract.bz").setValue("CNY");
	        		if(contractType=="01"){
	        			nui.get("ifTopSubcon").setValue("1");
	        			nui.get("ifTopSubcon").setEnabled(false);
	        		}
	        		var num=nui.get("subContract.subcontractNum").getValue()
	        		var paperConNum=nui.get("subContract.paperConNum").getValue();
	        		if(paperConNum==""){
	        			nui.get("subContract.paperConNum").setValue(nui.get("subContract.subcontractNum").getValue());
	        		}
	        		nui.get("subContract.contractId").setValue(contractId);
	        		if(guarantyType.indexOf("04") == -1){//担保方式如果没有保证04则隐藏字段
	        			nui.get("dbxs").hide();
	        		}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
	}
	//保存
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		var o = form.getData();/* form所有信息 */
		o.tbConAttachedInfo.contractId = o.subContract.subcontractId;
		var json = nui.encode(o);
		
		$.ajax({
	        url: "com.bos.grt.conGrt.saveGrtCon.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	alert(contractType);
				if("02" == contractType && "1"==o.subContract.ifTopSubcon){
	    	        nui.open({
			            url: nui.context + "/crt/con_grt/saveConSubRel.jsp?subcontractId="+subcontractId+"&conSubconId="+conSubconId,
			            title: "选择",
			            width: 800,
			    		height: 500,
			            ondestroy: function (action) {     
			                	if (action == "ok") {
				                    var iframe = this.getIFrameEl();
				                    var data = iframe.contentWindow.GetData();
				                    data = nui.clone(data);
				                    if (data) {
				        				var json = nui.encode({"conSubcontractRel":{"conSubconId":conSubconId,"suretyAmt":data.suretyAmt}});
										//将担保物关联到抵质押合同
										$.ajax({
									        url: "com.bos.grt.conGrt.saveConGrtRelAmt.biz.ext",
									        type: 'POST',
									        data: json,
									        contentType:'text/json',
									        cache: false,
									        success: function (data) {
								            	//CloseWindow("ok");
								            	alert("保存成功");
									        },
									        error: function (jqXHR, textStatus, errorThrown) {
									            alert(jqXHR.responseText);
									        }
								        });
				                    }
				                }
			        	}
			        });
				}else{
	                	alert("保存成功");
	                 }
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
		
	}
	
	function changeStatus(e){
		if(e.value=="1"){
			nui.get("subContract.zgbjxe").show();
			nui.get("subContract.zgbjxe").setRequired(true);
			$("#zgbjxeLab").show();
			
			nui.get("subContract.beginDate").show();
			nui.get("subContract.beginDate").setRequired(true);
			$("#beginDateLab").show();
			
			nui.get("subContract.endDate").show();
			nui.get("subContract.endDate").setRequired(true);
			$("#endDateLab").show();
			
			form.validate();
		}else{
			nui.get("subContract.zgbjxe").hide();
			nui.get("subContract.zgbjxe").setValue("");
			$("#zgbjxeLab").hide();
			
			nui.get("subContract.beginDate").hide();
			nui.get("subContract.beginDate").setValue("");
			$("#beginDateLab").hide();
			
			nui.get("subContract.endDate").hide();
			nui.get("subContract.endDate").setValue("");
			$("#endDateLab").hide();
		}
	}
	
	function checkDate(){
			var beginDate=nui.get("subContract.beginDate").getValue();
			var endDate=nui.get("subContract.endDate").getValue();
			
			if(beginDate!=""&&endDate!=""){
				if(!CompareDueAndShengXiaoDate(beginDate,endDate)){
					nui.alert("起期和止期不能为同一天且止期要大于起期");
					nui.get("subContract.beginDate").setValue("");
					nui.get("subContract.endDate").setValue("");
				}
			}
		}
		
	/**
	 * 比较到期日期和生效日期
	 */
	function CompareDueAndShengXiaoDate(beginDate,endDate){
  		if(nui.parseDate(beginDate)-nui.parseDate(endDate)>=0){//到期日期小于生效日期
  			return false;
  		}else{
  			return true;
  		}
	}
	
	</script>
</body>
</html>
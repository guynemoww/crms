<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:15
  - Description:
-->
<head>
<title>抵押合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
	    <input id="subContract.subcontractId" name="subContract.subcontractId" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	    <input id="tbConAttachedInfo.attached" name="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	<fieldset>
			<legend>
				   <span>基本信息</span>
			</legend>
	<div  class="nui-dynpanel" columns="4">
	    <label>抵押合同编号：</label>
		<input name="subContract.subcontractNum" id="subContract.subcontractNum" class="nui-text nui-form-input" />
		
		<label>抵押人名称：</label>
		<input id="subContract.partyName" name="subContract.partyName"  class="nui-text nui-form-input"  style="width:86%;" />
		
	    <label>抵押合同纸质编号：</label>
		<input name="subContract.paperConNum"  id="subContract.paperConNum" class="nui-textbox nui-form-input" />
		
		<label>是否最高额抵押：</label>
		<input name="subContract.ifTopSubcon" id="ifTopSubcon" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" required="true" onvaluechanged="selectChange"/>
		
		<label>币种：</label>
		<input name="subContract.bz" id="subContract.bz" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true"  allowInput="false" enabled="false"/>
		
		<label id="subcontractAmtLab">抵押合同金额：</label>
		<input id="subContract.subcontractAmt" name="subContract.subcontractAmt"  class="nui-textbox nui-form-input" vtype="maxLength:20" required="true" dataType="currency"/>
		
		<label id="zgbjxeLab">抵押最高本金限额：</label>
		<input id="subContract.zgbjxe" name="subContract.zgbjxe"  class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="currency"/>
		
		<label id="beginDateLab">抵押额度起期：</label>
		<input name="subContract.beginDate" id="subContract.beginDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" onvaluechanged="dateChange"/>
	    
	    <label id="endDateLab">抵押额度止期：</label>
		<input name="subContract.endDate" id="subContract.endDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" onvaluechanged="dateChange"/>
		
		<label>几个工作日内办理登记：</label>
		<input name="subContract.jggzrbldj"  class="nui-textbox nui-form-input"  vtype="int;maxLength:20" required="true"/>
		
		<!-- <label id="zfwyjblLab">支付违约金比例(%)：</label>
		<input name="subContract.zfwyjbl" id="subContract.zfwyjbl" class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8" required="true"/>
  -->   </div>
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
		   		<span></span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
		   <label>补充条款：</label>
			<input name="tbConAttachedInfo.addClause" class="nui-textArea nui-form-input" vtype="maxLength:2000"  colspan="3"/>
		</div>
  </fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;text-align: right;" borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-save" id="addEdit" onclick="save()">确认</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	//contractType : 01 关联综合授信协议  		02  关联业务合同
	var contractType="<%=request.getParameter("contractType")%>";
	
	var view="<%=request.getParameter("view") %>";
		
	var applyId="<%=request.getParameter("applyId")%>";
	
	var conSubconId="<%=request.getParameter("conSubconId")%>";
	
	var subcontractId="<%=request.getParameter("subcontractId") %>";
	
	if (view=="1") {
		form.setEnabled(false);
		nui.get("addEdit").hide();
	} 
	
    initForm();
	//初始化查询
	function initForm(){
		var json=nui.encode({"subContract":{"subcontractId":"<%=request.getParameter("subcontractId") %>"}});
		$.ajax({
	        url: "com.bos.grt.conGrt.getGrtConInfo.biz.ext",    
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
					var o = nui.decode(text);
	        		form.setData(o);
	        		if(contractType=="01"){
	        			nui.get("ifTopSubcon").setValue("1");
	        			nui.get("ifTopSubcon").setEnabled(false);
	        			nui.get("subContract.subcontractAmt").setEnabled(false);
	        			//nui.get("subContract.zfwyjbl").setEnabled(false);
	        		}
	        		nui.get("subContract.bz").setValue("CNY");
	        		
	        		var paperConNum=nui.get("subContract.paperConNum").getValue();
	        		if(paperConNum==""){
	        			nui.get("subContract.paperConNum").setValue(nui.get("subContract.subcontractNum").getValue());
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
	
	//是否最高额抵押改变
	function selectChange(e){
		if(e.value=='1'){
			nui.get("subContract.zgbjxe").show();
			nui.get("subContract.zgbjxe").setRequired(true);
			$("#zgbjxeLab").show();
			
			nui.get("subContract.beginDate").show();
			nui.get("subContract.beginDate").setRequired(true);
			$("#beginDateLab").show();
			
			nui.get("subContract.endDate").show();
			nui.get("subContract.endDate").setRequired(true);
			$("#endDateLab").show();
			
			//nui.get("subContract.zfwyjbl").hide();
			//nui.get("subContract.zfwyjbl").setValue("");
			//$("#zfwyjblLab").hide();
			
			$("#subcontractAmtLab").hide();
			nui.get("subContract.subcontractAmt").hide();
			nui.get("subContract.subcontractAmt").setValue("");
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
			
			
			$("#subcontractAmtLab").show();
			nui.get("subContract.subcontractAmt").show();
			
			//nui.get("subContract.zfwyjbl").show();
			//$("#zfwyjblLab").show();
		}
		
		form.validate();
	}
	
	function dateChange(){
		var beginDate=nui.get("subContract.beginDate").getValue();//生效日期
  		var endDate=nui.get("subContract.endDate").getValue();//到期日期
  		if(beginDate!=""&&endDate!=""){
  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
				nui.alert("起期必须小于止期");
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
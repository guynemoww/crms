<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:15
  - Description:
-->
<head>
<title>保证合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
	 	<input id="subContractT.subcontractId" name="subContractT.subcontractId" class="nui-hidden nui-form-input" />
		    <input id="subContractT.id" name="subContractT.id" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	<fieldset>
		<legend>
			<span>基本信息</span>
		</legend>
	<div  class="nui-dynpanel" columns="4">
	
		<label>保证合同编号：</label>
		<input name="subContractT.subcontractNum" id="subContractT.subcontractNum" class="nui-text nui-form-input" />
		
	    <label>保证合同纸质编号：</label>
		<input name="subContractT.paperConNum"  id="subContractT.paperConNum" class="nui-textbox nui-form-input" />
		
		<label>是否最高额：</label>
		<input name="subContractT.ifTopSubcon" id="subContractT.ifTopSubcon" class="nui-dictcombobox nui-form-input"enabled ="false" vtype="maxLength:6" dictTypeId="XD_0002" required="true" />
		
		<label>币种：</label>
		<input name="subContractT.bz"  id="subContractT.bz" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true"  allowInput="false" enabled="false"/>

	</div>
	
		<div id="subcontractAmt" class="nui-dynpanel" columns="4">
		<label>保证合同金额：</label>
		<input id="subContractT.subcontractAmt" name="subContractT.subcontractAmt"  class="nui-textbox nui-form-input" vtype="maxLength:20" required="true" dataType="currency"/>
		</div>
	
		<div  id="zgbjxe"  class="nui-dynpanel" columns="4">
	 	<label>保证最高本金限额：</label>
		<input id="subContractT.zgbjxe" name="subContractT.zgbjxe"  class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="currency"/>
		
		<label>保证可用金额：</label>
		<input id="subContractT.aviAmt" name="subContractT.aviAmt"  class="nui-textbox nui-form-input" enabled ="false" vtype="maxLength:20" dataType="currency"/>
	
		<label id="beginDateLab">保证额度起期：</label>
		<input name="subContractT.beginDate" id="subContractT.beginDate"  required="true"class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" onvaluechanged="checkDate()"/>
	    
	    <label id="endDateLab">保证额度止期：</label>
		<input name="subContractT.endDate" id="subContractT.endDate"   required="true"class="nui-datepicker nui-form-input" format="yyyy-MM-dd"minDate="<%=GitUtil.getBusiDateStr()%>" allowInput="false" onvaluechanged="checkDate()"/>
		</div>
		
			
		<div  class="nui-dynpanel" columns="4">
		
		<label>保证人股权变更达到（）%时，应取得债权人的书面同意：</label>
		<input name="subContractT.changePercent"  class="nui-textbox nui-form-input"  vtype="float;range:1,100;maxLength:8" />
		
		
		<label>支付违约金比例(%)：</label>
		<input name="subContractT.zfwyjbl"  class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8" required="true"/>
    	</div>
  </fieldset>
  
  		<fieldset>
		<legend>
			<span>保证人关联信息</span>
		</legend>
		
				<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="editCust" class="nui-button" iconCls="icon-zoomin"
						onclick="look()">查看</a>
						<a id="addCust" style="margin-left: 5px" class="nui-button"
							iconCls="icon-add" onclick="insert()">关联</a> 
						<a  id="rmove"  class="nui-button"  style="margin-left: 5px"
							iconCls="icon-remove" onclick="remove()">删除</a> 
					</div>
		
  		<div id="grid" class="nui-datagrid"  style="heigth:30%;width:100%"
			url="com.bos.grt.subContractManage.subContract.findBZRList.biz.ext"
				dataField="conGrts" 	allowAlternating="true" multiSelect="false"pageSize="5"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"allowCellEdit="true"
				allowCellSelect="true">
			<div property="columns">
				<div type="checkcolumn" >选择</div>
				<div field="SUBCONTRACT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0131">保证类型</div>
				<div field="PARTY_NAME" headerAlign="center" allowSort="true"width="15%" >保证人名称</div>
				<div field="CERT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="CDKH0002">证件类型</div>
				<div field="CERT_CODE" allowSort="true"  headerAlign="center"  >证件号码</div>
				<div field="SURETY_AMT" allowSort="true"  headerAlign="center"  dataType="currency">本次担保金额</div>
			</div>
		</div>
    </fieldset>
    
    
    	<fieldset>
		<legend>
			<span>主合同关联信息</span>
		</legend>
		
		<div class="nui-toolbar"style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
			<a id="editCon" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a> 
		</div>
		
  			<div id="grid2" class="nui-datagrid" sortMode="client" style="heigth:30%;width:100%"
				url="	com.bos.grt.subContractManage.subContract.findContarctList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="5">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="CONTRACT_NUM" headerAlign="center" allowSort="true" >合同编号</div>
					<div field="CURRENCY_CD" allowSort="true"  headerAlign="center"  dictTypeId="CD000001" >币种</div>
					<div field="PRODUCT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="product" >业务品种</div>
					<div field="BEGIN_DATE" headerAlign="center" allowSort="true" >合同起期</div>
					<div field="END_DATE" headerAlign="center" allowSort="true" >合同止期</div>
					<div field="CONTRACT_AMT" allowSort="true"  headerAlign="center"  dataType="currency" >合同金额</div>
					<div field="CON_YU_E" allowSort="true"  headerAlign="center"  dataType="currency" >合同已用金额</div>
					<div field="SURETY_AMT" allowSort="true"  headerAlign="center"  dataType="currency" >本次担保金额</div>
				</div>
			</div>
    </fieldset>
    
    <div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;text-align: right;" borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-save" id="addEdit" onclick="save()">保存</a>
		<!--  <a class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>-->
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	
	var grid = nui.get("grid");
	var grid2 = nui.get("grid2");
	
	var subConTId="<%=request.getParameter("subConTId") %>";
	var proFlag="<%=request.getParameter("proFlag") %>";
	var subcontractId;
	if (proFlag=="0"||proFlag=="-1") {
		form.setEnabled(false);
		nui.get("addEdit").hide();
		nui.get("editCon").hide();
		nui.get("addCust").hide();
		nui.get("rmove").hide();
	} 
	
    initForm();
	//初始化查询
	function initForm(){
		var json=nui.encode({"subConTId":"<%=request.getParameter("subConTId") %>"});
		$.ajax({
	        url: "com.bos.grt.subContractManage.subContract.findSubConInfo.biz.ext",    
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
					var o = nui.decode(text);
					subcontractId=text.subContractT.subcontractId;
					grid.load({"subcontractId":text.subContractT.subcontractId});
					grid2.load({"subConTId":subConTId});
	        		form.setData(o);
	        		if (text.subContractT.ifTopSubcon=="1") {//最高额
						nui.get("subcontractAmt").hide();
					}else{//非最高额
						nui.get("zgbjxe").hide();
						nui.get("editCon").hide();
					}
	        		nui.get("subContractT.bz").setValue("CNY");
	        		var paperConNum=nui.get("subContractT.paperConNum").getValue();
	        		if(paperConNum==""){
	        			nui.get("subContractT.paperConNum").setValue(nui.get("subContractT.subcontractNum").getValue());
	        		}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
	}
	
		grid2.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				  e.data[i]['CONTRACT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].CONTRACT_NUM+ '\');">'+e.data[i]['CONTRACT_NUM']+'</a>';
			}
		});
	
	//保存
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		
		//判断担保合同是否关联了在途的合同
		var para = {"subcontractId" : subcontractId};
		msg = exeRule("SUBCON_0002", "1", para);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		
		var o = form.getData();
		var json = nui.encode(o);
		
		$.ajax({
	        url: "com.bos.grt.subContractManage.subContract.updateSubContractT.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        		return;
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
			nui.get("subContractT.zgbjxe").show();
			nui.get("subContractT.zgbjxe").setRequired(true);
			$("#zgbjxeLab").show();
			
			nui.get("subContractT.beginDate").show();
			nui.get("subContractT.beginDate").setRequired(true);
			$("#beginDateLab").show();
			
			nui.get("subContractT.endDate").show();
			nui.get("subContractT.endDate").setRequired(true);
			$("#endDateLab").show();
			
			form.validate();
		}else{
			nui.get("subContractT.zgbjxe").hide();
			nui.get("subContractT.zgbjxe").setValue("");
			$("#zgbjxeLab").hide();
			
			nui.get("subContractT.beginDate").hide();
			nui.get("subContractT.beginDate").setValue("");
			$("#beginDateLab").hide();
			
			nui.get("subContractT.endDate").hide();
			nui.get("subContractT.endDate").setValue("");
			$("#endDateLab").hide();
		}
	}
			//仲裁方式变更时触发
	function arbiTypeChange(e){
		if('02'==e.value){//如果选仲裁，显示仲裁模块,并将“其他”项隐藏并清空
			$("#zcdiv").css("display","block");
			$("#otherediv").css("display","none");
		}else if('03'==e.value){//如果选其他，显示其他模块,并将“仲裁”隐藏并清空
			$("#zcdiv").css("display","none");
			$("#otherediv").css("display","block");
		}else{//选诉讼或请选择，将“其他”及“仲裁”项隐藏并清空
			$("#otherediv").css("display","none");
			$("#zcdiv").css("display","none");
		}
	}
	
	
	function checkDate(){
			var beginDate=nui.get("subContractT.beginDate").getValue();
			var endDate=nui.get("subContractT.endDate").getValue();
			
			if(beginDate!=""&&endDate!=""){
				if(!CompareDueAndShengXiaoDate(beginDate,endDate)){
					nui.alert("起期和止期不能为同一天且止期要大于起期");
					nui.get("subContractT.beginDate").setValue("");
					nui.get("subContractT.endDate").setValue("");
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
	
	function thirdType(e){
		if(e.value==""){
			nui.get("tbConAttachedInfo.cHoldCount").setEnabled(false);
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(false);
			form.validate();
		}else{
			nui.get("tbConAttachedInfo.cHoldCount").setEnabled(true);
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(true);
			form.validate();
		}
	}
	
	
		function insert(){
		
		var rows = nui.get("grid").getData();
		var array=new Array();
		for(var i=0;i<rows.length;i++){
			array.push(rows[i].PARTY_ID);
		}
		
    	        nui.open({
		            url: nui.context + "/grt/subContractManage/sel_bzr_list.jsp?subcontractId="+subcontractId+"&subcontractType=04"+"&array="+array,
		            title: "选择",
		            width: 800,
		    		height: 500,
		            ondestroy: function (action) {
		            initForm();
		        	}
		        });
	}
	
	
	
	function edit(){
		var row = grid2.getSelected();
		if(row==null){
			alert("请选择一条记录");
			return false;
		}
    	 nui.open({
					url : nui.context + "/grt/subContractManage/con_sub_edit.jsp?id=" + row.ID + "&suretyAmt="
							+ row.SURETY_AMT+"&contractAmt="+row.CONTRACT_AMT+"&conYuE="+row.CON_YU_E,		            title: "选择",
		            width: 400,
		    		height: 400,
		            ondestroy: function (action) {
		           		 initForm();
		        	}
		 });
	}
	//删除抵质押物与担保合同关联关系
	function remove(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔抵押物信息");
			return false;
		}
		var json = nui.encode({"row" : row});		//删除抵质押物与担保合同关联关系
		$.ajax({
	        url: "com.bos.grt.subContractManage.subContract.delSubGrtRelT.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        	initForm();
            	alert("删除成功！");
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	}
		function look(){
        var row = grid.getSelected();
		var v ="1";
        if (row) {		   
        	var relationId=row.RELATION_ID;
        	nui.open({
        		url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_guaranteer_add.jsp?suretyId="+row.SURETY_ID+"&view="+v,
	            showMaxButton: true,
	            title:"押品信息",
	            width: 800,
	            height: 500,
	            ondestroy: function(e) {
	            }
	        });
        } else {
            alert("请选中一条记录");
        } 
	}
	
	</script>
</body>
</html>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:15
  - Description:
-->
<head>
<title>保证金合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
		<input id="subContractT.id" name="subContractT.id" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	    <input id="subContractT.subcontractId" name="subContractT.subcontractId" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	<fieldset>
		<legend>
			<span>基本信息</span>
		</legend>
	<div  class="nui-dynpanel" columns="4">
	  	<label>保证金协议编号：</label>
		<input name="subContractT.subcontractNum" enabled="false"class="nui-textbox nui-form-input"  vtype="int;maxLength:20;range:1,1000" />
		
	   <label>几日内存入保证金：</label>
		<input name="subContractT.jrncrbzj" required="true" class="nui-textbox nui-form-input"  vtype="int;maxLength:20;range:1,1000" />
		
		<label>币种：</label>
		<input name="subContractT.bz" id="subContractT.bz"   class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" allowInput="false" enabled="false"/>
		
		<label>保证金金额：</label>
		<input name="subContractT.bzjje"  enabled="false" name="subContract.bzjje"  class="nui-textbox nui-form-input" vtype="maxLength:20;range:0,100000000000" dataType="currency"/>
		
		<label>保证金比例（%）：</label>
		<input name="subContractT.bzjbl"  enabled="false" class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8" />
			
	    <label>保证金类型：</label>
		<input id="subContractT.bzjlx" name="subContractT.bzjlx" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0134" onvaluechanged="marginTypeChange"/>
		
		<label id="beginDateLab">保证金起始日：</label>
		<input name="subContractT.beginDate" id="subContractT.beginDate" required="true"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" onvaluechanged="dateChange"/>
	    
		<label id="endDateLab">保证金到期日：</label>
		<input name="subContractT.endDate" id="subContractT.endDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" minDate="<%=GitUtil.getBusiDateStr()%>" onvaluechanged="dateChange"/>
		
		<label>按年利率计息（%）：</label>
		<input name="subContractT.anlljx" id="subContractT.anlljx" class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8"/>
		
		<label>其他计收利息方式：</label>
		<input name="subContractT.qtjsxfs"  class="nui-textbox nui-form-input" vtype="maxLength:300" required="false"/>
		
		<label>保证金结息周期：</label>
		<input name="subContractT.bzjjxzq"  class="nui-dictcombobox nui-form-input" required="true"  dictTypeId="XD_YWDB0138" />
		
		<label>若保证金价值下降，应在几日内通知客户补充保证金：</label>
		<input name="subContractT.jrtzkh"  class="nui-textbox nui-form-input" vtype="int;maxLength:6;range:1,1000" required="true"/>
		
		<label>违约金比例（%）：</label>
		<input name="subContractT.zfwyjbl"  class="nui-textbox nui-form-input" required="true" vtype="float;range:0,100;maxLength:8" />
    </div>
  </fieldset>
  
  		<fieldset>
		<legend>
			<span>保证金关联信息</span>
		</legend>
		
				<div class="nui-toolbar"style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="viewCust" class="nui-button" iconCls="icon-zoomin"onclick="look()">查看</a> 
				</div>
		
  			<div id="grid" class="nui-datagrid" sortMode="client" style="heigth:30%;width:100%"
				url="com.bos.grt.subContractManage.subContract.findBzjList.biz.ext"
				dataField="conGrts" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="5">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div type="indexcolumn">序号</div>
					<div field="MARGIN_SORT" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0134">保证金类型</div>
					<div field="OPEN_BANK" headerAlign="center" allowSort="true" dictTypeId="org">开户行</div>
					<div field="ACCT_NAME" headerAlign="center" allowSort="true" >开户人</div>
					<div field="MARGIN_ACCOUNT" headerAlign="center" allowSort="true" >保证金账号</div>
					<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="ACC_BALANCE" headerAlign="center" allowSort="true" >保证金金额</div>
					<div field="END_DATE" headerAlign="center" allowSort="true" >到期日期</div>
<!-- 					<div field="IS_JIXI" headerAlign="center" allowSort="true" dictTy	peId="XD_0002">是否计息</div> -->
					<div field="MARGIN_RATE" headerAlign="center" allowSort="true" >执行利率(%)</div>
				</div>
			</div>
    </fieldset>

    
    	<fieldset>
		<legend>
			<span>主合同关联信息</span>
		</legend>
		<!--
				<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="editCon"
						class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a> 
				</div>
		  -->		
				<div id="grid2" class="nui-datagrid" sortMode="client"style="heigth:30%;width:100%"
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
	        		nui.get("subContractT.bz").setValue("CNY");
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
		var rows=grid.getData();
		if(rows.length>0){
			if(rows[0].MARGIN_SORT!=nui.get("subContractT.bzjlx").getValue()){
				return alert("保证金协议的保证金类型必须与关联的保证金类型一致");
			}
		}
		
		//判断担保合同是否关联了在途的合同
		var para = {"subcontractId" : subcontractId};
		msg = exeRule("SUBCON_0002", "1", para);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		
		var o = form.getData();/* form所有信息 */
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.grt.subContractManage.subContract.updateSubContractT.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				if (text.msg) {
					alert(text.msg);
					return;
				} else {
					alert("保存成功！");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function marginTypeChange(e){
		if (e.value == '02') { //活期
			nui.get("subContractT.beginDate").setValue("");
			nui.get("subContractT.endDate").setValue("");
			nui.get("subContractT.endDate").hide();
			nui.get("subContractT.beginDate").hide();
			nui.get("subContractT.anlljx").setRequired(false);
			$("#beginDateLab").hide();
			$("#endDateLab").hide();
		} else { //定期
			nui.get("subContractT.beginDate").show();
			nui.get("subContractT.endDate").show();
			nui.get("subContractT.anlljx").setRequired(true);
			$("#beginDateLab").show();
			$("#endDateLab").show();
		}
	}

	function dateChange() {
		var beginDate = nui.get("subContractT.beginDate").getValue();//生效日期
		var endDate = nui.get("subContractT.endDate").getValue();//到期日期
		if (beginDate != "" && endDate != "") {
			if (!CompareDueAndShengXiaoDate(beginDate, endDate)) {//生效日期大于到期日期
				nui.alert("起期必须小于止期");
				nui.get("subContractT.beginDate").setValue("");
				nui.get("subContractT.endDate").setValue("");
			}
		}
	}

	/**
	 * 比较到期日期和生效日期
	 */
	function CompareDueAndShengXiaoDate(beginDate, endDate) {
		if (nui.parseDate(beginDate) - nui.parseDate(endDate) >= 0) {//到期日期小于生效日期
			return false;
		} else {
			return true;
		}
	}

	function look() {
		var row = grid.getSelected();
		if (row) {
			var suretyKeyId = row.SURETY_KEY_ID;
			nui.open({
				url : nui.context + "/grt/guaranMainManager/guarantee_apply_cash_deposit_edit.jsp?view=1&suretyKeyId="
						+ suretyKeyId,
				title : "查看",
				width : 950,
				height : 550,
				allowResize : false,
				showMaxButton : true,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = row;
				},
				ondestroy : function(action) {
					reload();
				}
			});
		} else {
			nui.alert("请选中一条记录");
		}
	}
</script>
</body>
</html>
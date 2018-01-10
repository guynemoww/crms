<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:15
  - Description:
-->
<head>
<title>抵质押合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
%>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
		    <input id="subContractT.id" name="subContractT.id" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	    <input id="subContractT.subcontractId" name="subContractT.subcontractId" class="nui-hidden nui-form-input" vtype="maxLength:32"/>
	<!--   <input id="tbConAttachedInfo.attached" name="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" vtype="maxLength:32"/> -->  
	<fieldset>
		<legend>
			<span>基本信息</span>
		</legend>
	<div  class="nui-dynpanel" columns="4">
	    <label>抵质押合同编号：</label>
		<input name="subContractT.subcontractNum" id="subContractT.subcontractNum" class="nui-text nui-form-input" />
		
		<label>抵质押人名称：</label>
		<input id="subContractT.partyName" name="subContractT.partyName"  class="nui-text nui-form-input"   />
		
	    <label>抵质押合同纸质编号：</label>
		<input name="subContractT.paperConNum"  id="subContractT.paperConNum" class="nui-textbox nui-form-input" />
		
		<label>是否最高额抵质押：</label>
		<input name="subContractT.ifTopSubcon" id="subContractT.ifTopSubcon" enabled="false" class="nui-dictcombobox nui-form-input" enabled ="false"vtype="maxLength:6" dictTypeId="XD_0002" required="true" />
		
		<label>币种：</label>
		<input name="subContractT.bz" id="subContractT.bz" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true"  allowInput="false" enabled="false"/>
		
			</div>
		
		<div id="subcontractAmt" class="nui-dynpanel" columns="4">
		<label>抵质押合同金额：</label>
		<input id="subContractT.subcontractAmt" name="subContractT.subcontractAmt"  class="nui-textbox nui-form-input" vtype="maxLength:20" required="true" dataType="currency"/>
		</div>
			
		<div id="zgbjxe"class="nui-dynpanel" columns="4">
		<label>抵质押最高本金限额：</label>
		<input id="subContractT.zgbjxe" name="subContractT.zgbjxe"  class="nui-textbox nui-form-input" vtype="maxLength:20" dataType="currency"/>
		
		<label id="zgbjxeLab">抵质押可用金额：</label>
		<input id="subContractT.aviAmt" name="subContractT.aviAmt"  class="nui-textbox nui-form-input" enabled ="false" vtype="maxLength:20" dataType="currency"/>
		
		<label id="beginDateLab">抵质押额度起期：</label>
		<input name="subContractT.beginDate" id="subContractT.beginDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" onvaluechanged="dateChange"/>
	    
	    <label id="endDateLab">抵质押额度止期：</label>
		<input name="subContractT.endDate" id="subContractT.endDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" vtype="maxLength:20" minDate="<%=GitUtil.getBusiDateStr()%>" onvaluechanged="dateChange"/>
		</div>
		
		<div  class="nui-dynpanel" columns="4">
		
		<label>几个工作日内办理登记：</label>
		<input name="subContractT.jggzrbldj"  class="nui-textbox nui-form-input"  vtype="int;maxLength:20" required="true"/>
		
		<label id="zfwyjblLab">支付违约金比例(%)：</label>
		<input name="subContractT.zfwyjbl" id="subContractT.zfwyjbl" class="nui-textbox nui-form-input"  vtype="float;range:0,100;maxLength:8" required="true"/>
    </div>
  </fieldset>
  <!--  
  <fieldset>
		<legend>
			<span>仲裁方式</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">争议解决方式：</label>
			<input id="tbConAttachedInfo.arbitrateType"  name="tbConAttachedInfo.arbitrateType" data="data" valueField="dictID" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0209" emptyText="--请选择--"  onvaluechanged="arbiTypeChange"/>
		</div>
		<div id="zcdiv" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">仲裁委员会名称：</label>	
			<input id="tbConAttachedInfo.arbitrateName" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateName" required="true" />	
				
			<label class="nui-form-label">仲裁委员会地点：</label>
			<input id="tbConAttachedInfo.arbitrateAddress" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateAddress" required="true" />
		</div>
	
		<div id="otherediv" class="nui-dynpanel">
			<label class="nui-form-label">其它方式：</label>	
			<input id="tbConAttachedInfo.other" class="nui-textbox nui-form-input" name="tbConAttachedInfo.other" required="true" />	
		</div>
  </fieldset>
  <fieldset>
		<legend>
			<span>协议签署</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">甲方持有份数：</label>	
			<input id="tbConAttachedInfo.aHoldCount" class="nui-textbox nui-form-input" name="tbConAttachedInfo.aHoldCount" required="true" vtype="int;range:1,100;maxLength:4"/>	
					
			<label class="nui-form-label">乙方持有份数：</label>
			<input id="tbConAttachedInfo.bHoldCount" class="nui-textbox nui-form-input" name="tbConAttachedInfo.bHoldCount" required="true" vtype="int;range:1,100;maxLength:4"/>
		
		
			<label class="nui-form-label">第三方类型：</label>	
			<input id="tbConAttachedInfo.cType" class="mini-newcheckbox nui-form-input" name="tbConAttachedInfo.cType" data="data" valueField="dictID"
				  dictTypeId="XD_SXYW0231"  onvaluechanged="thirdType"/>	
					
			<label class="nui-form-label">第三方持有份数：</label>
			<input id="tbConAttachedInfo.cHoldCount" class="nui-textbox nui-form-input" name="tbConAttachedInfo.cHoldCount" vtype="int;maxLength:4" enabled="false"/>
			
			<label class="nui-form-label">共计份数：</label>
			<input id="tbConAttachedInfo.totalCount" class="nui-text nui-form-input" name="tbConAttachedInfo.totalCount"/>
		</div>
  </fieldset>
  <fieldset>
		  	<legend>
		   		<span></span>
		    </legend>
		<div class="nui-dynpanel" columns="4">
		   <label>补充条款：</label>
			<input name="tbConAttachedInfo.addClause" class="nui-textArea nui-form-input" vtype="maxLength:2000"  colspan="3"/>
		</div>
  </fieldset>
  -->
  		<fieldset>
		<legend>
			<span>抵质押品关联信息</span>
		</legend>
		
				<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="viewCust" class="nui-button" iconCls="icon-zoomin"
							onclick="look()">查看</a> 
						<a id="addCust" style="margin-left: 5px" class="nui-button"
							iconCls="icon-add" onclick="insert()">关联</a>
						<a  id="rmove"  class="nui-button"  style="margin-left: 5px"
							iconCls="icon-remove" onclick="remove()">删除</a> 
					</div>
		
  			<div id="grid" class="nui-datagrid" sortMode="client" style="heigth:30%;width:100%"
				url="com.bos.grt.subContractManage.subContract.findDbList.biz.ext"
				dataField="conGrts" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="5">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="COLL_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0131">抵质押类型</div>
					<div field="PARTY_NAME" headerAlign="center" allowSort="true" >抵质押人名称</div>
					<div field="SURETY_NO" headerAlign="center" allowSort="true" >抵质押物编号</div>
					<div field="SORT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_YPZL01">抵质押物类型</div>
					<div field="BZ" allowSort="true"  headerAlign="center"  dictTypeId="CD000001" >币种</div>
					<div field="ASSESS_VALUE" headerAlign="center" allowSort="true" dataType="currency" >评估价值（元）</div>
					<div field="MORTGAGE_VALUE" headerAlign="center" allowSort="true"dataType="currency"  >权利价值（元）</div>
					<div field="USED_AMT" headerAlign="center" allowSort="true" dataType="currency" >已担保金额（元）</div>
					<div field="SURETY_AMT" headerAlign="center" allowSort="true" dataType="currency" >本次担保金额（元）</div>
				</div>
			</div>
    </fieldset>

    
    	<fieldset>
		<legend>
			<span>主合同关联信息</span>
		</legend>
		
				<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="editCon"
						class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a> 
				</div>
				
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
	var subconractType = "<%=request.getParameter("subconractType")%>";

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
	        		if (text.subContractT.ifTopSubcon=="1") {
						nui.get("subcontractAmt").hide();
					}else{
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
					alert("保存成功");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}

	//仲裁方式变更时触发
	function arbiTypeChange(e) {
		if ('02' == e.value) {//如果选仲裁，显示仲裁模块,并将“其他”项隐藏并清空
			$("#zcdiv").css("display", "block");
			$("#otherediv").css("display", "none");
		} else if ('03' == e.value) {//如果选其他，显示其他模块,并将“仲裁”隐藏并清空
			$("#zcdiv").css("display", "none");
			$("#otherediv").css("display", "block");
		} else {//选诉讼或请选择，将“其他”及“仲裁”项隐藏并清空
			$("#otherediv").css("display", "none");
			$("#zcdiv").css("display", "none");
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

	function thirdType(e) {
		if (e.value == "") {
			nui.get("tbConAttachedInfo.cHoldCount").setEnabled(false);
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(false);
			form.validate();
		} else {
			nui.get("tbConAttachedInfo.cHoldCount").setEnabled(true);
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(true);
			form.validate();
		}
	}

	function insert() {
		var rows = nui.get("grid").getData();
		var array = new Array();
		for (var i = 0; i < rows.length; i++) {
			array.push(rows[i].SURETY_NO);
		}
		nui.open({
			url : nui.context + "/grt/subContractManage/sel_grt_list.jsp?subcontractId=" + subcontractId + "&array="
					+ array+ "&subconractType="+ subconractType,
			title : "选择",
			width : 950,
			height : 450,
			ondestroy : function(action) {
				initForm();
			}
		});
	}

	function edit() {
		var row = grid2.getSelected();
		if (row == null) {
			alert("请选择一条记录");
			return false;
		}
		nui.open({
					url : nui.context + "/grt/subContractManage/con_sub_edit.jsp?id=" + row.ID + "&suretyAmt="
							+ row.SURETY_AMT+"&contractAmt="+row.CONTRACT_AMT+"&conYuE="+row.CON_YU_E,
					title : "选择",
					width : 400,
					height : 400,
					ondestroy : function(action) {
						initForm();
					}
				});
	}
	//删除抵质押物与担保合同关联关系
	function remove() {
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔抵押物信息");
			return false;
		}
		
		if(row.MORTGAGE_STATUS=='09'){
			nui.alert("已入库的抵质押品，不能删除");
			return false;
		}
		var json = nui.encode({"row" : row});
		//删除抵质押物与担保合同关联关系
		$.ajax({
			url : "com.bos.grt.subContractManage.subContract.delSubGrtRelT.biz.ext",
			type : 'POST',
			data : json,
			contentType : 'text/json',
			cache : false,
			success : function(data) {
				initForm();
				alert("删除成功！");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			}
		});
	}

	function look() {
		var row = grid.getSelected();
		var v = "1";
		if (row) {
			//add by shangmf:改为链接押品系统
        	var url = 'http://'+'<%=ipStr%>'+':'+'<%=portStr%>'+'/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&cltNo='+row.SURETY_NO+'&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>';
        	//alert(url);
        	window.open(url,'押品信息','押品信息管理', 'height=400,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
			<%--nui.open({
				url : nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId=" + row.SURETY_ID + "&view="
						+ v,
				showMaxButton : true,
				title : "押品信息",
				width : 800,
				height : 500,
				state : "max",
				ondestroy : function(e) {
				}
			});--%>
		} else {
			alert("请选中一条记录");
		}
	}
</script>
</body>
</html>
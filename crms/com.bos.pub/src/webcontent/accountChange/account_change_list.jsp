<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-13 8:26:27
  - Description:
-->
<head>
<title>账务调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="账务调整">
				<div id="form1" class="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.pub.accountChange.accountChangeList" />
					<div class="nui-dynpanel" columns="6">
						<label>客户名称：</label>
						<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
						
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002" />
						
						<label>证件号码：</label>			
						<input id="item.certNum"  name="item.certNum"  class="nui-textbox nui-form-input"/>
						
						<label>合同编号：</label>			
						<input id="item.contractNum"   name="item.contractNum"class="nui-textbox nui-form-input" />
						
						<label>借据编号：</label>
						<input id="item.summaryNum" name="item.summaryNum"class="nui-textbox nui-form-input" />
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					</div>
				</div>	
		
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-contract" onclick="add()">账务调整</a>
			<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="query()">查看</a>
		</div>
				
		<div id="datagrid1" class="nui-datagrid"style="width:100%; height: auto" allowAlternating="true"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="orgNum" allowSort="true"  headerAlign="center" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true"  headerAlign="center">客户名称</div>
						<div field="contractNum" allowSort="true"  headerAlign="center">合同编号</div>
						<div field="summaryNumLink" allowSort="true"  headerAlign="center">借据编号</div>
						<div field="productType" allowSort="true"  headerAlign="center" dictTypeId="product">贷款品种</div>
						<div field="summaryCurrencyCd" allowSort="true"  headerAlign="center"dictTypeId="CD000001">币种</div>
						<div field="summaryAmt" allowSort="true"  headerAlign="center"dataType="currency">借据金额</div>
						<div field="jjye" allowSort="true"  headerAlign="center"dataType="currency">借据余额</div>
						<div field="beginDate" allowSort="true"  headerAlign="center">借据起期</div>
						<div field="endDate" allowSort="true"  headerAlign="center">借据止期</div>
						<div field="yqts" allowSort="true"  headerAlign="center">逾期天数</div>
						<div field="jjyqbj" allowSort="true"  headerAlign="center"dataType="currency">逾期本金</div>
						<div field="normalItr" allowSort="true"  headerAlign="center"dataType="currency">正常利息</div>
						<div field="arrearItr" allowSort="true"  headerAlign="center"dataType="currency">拖欠利息</div>
						<div field="punishItr" allowSort="true"  headerAlign="center"dataType="currency">罚息</div>
						<div field="userNum" allowSort="true"  headerAlign="center" dictTypeId="user">经办人</div>
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}
	
	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['summaryNumLink']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});

	//查看借据	
// 	  function query() {
// 	  	var row=grid.getSelected();
// 	  	if(!row){
// 	  		return alert("请选择一条记录");
// 	  	}
// 	  	bizView3231(row.summaryNum);
//     }
    //查看调整历史记录
    function query() {
    	var row=grid.getSelected();
   		var summaryNum="";
    	if(row){
    	    summaryNum=row.summaryNum;
    	}
	  	nui.open({
				url:nui.context + "/pub/accountChange/account_change_history.jsp?summaryNum="+summaryNum,
				showMaxButton:true,
				title:"账务调整历史记录",
				width: 850,
	            height: 400,
	            ondestroy: function(e) {
	            }
			});
    }
      function add() {
	  	var row=grid.getSelected();
	  	if(!row){
	  		return alert("请选择一条记录");
	  	}
	  	nui.open({
				url:nui.context + "/pub/accountChange/account_change_add.jsp?summaryNum="+row.summaryNum,
				showMaxButton:true,
				title:"账务调整",
				width: 800,
	            height: 400,
	            ondestroy: function(e) {
	            }
			});
    }
	
	</script>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="撤销流程">
			<div id="form" style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px"
				class="nui-form">
				<div class="nui-dynpanel" columns="4">
					<label>客户编号：</label> 
					<input name="map.bizNum" id="map.bizNum" required="false" class="nui-textbox nui-form-input" />
					<label>客户名称：</label> 
					<input name="map.customerName" id="map.customerName" required="false"class="nui-textbox nui-form-input" />
				</div>
				
			<div class="nui-toolbar"
				style="text-align: center; padding-top: 5px; padding-right: 25px;"
				borderStyle="border:0;">
				<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
			</div>	
			
			<div class="nui-toolbar"
					style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
					 <a id="editCust"
						class="nui-button" iconCls="icon-edit" onclick="cancel()">撤销</a> 
			</div>
		</div>
			
			<div id="grid" class="nui-datagrid" sortMode="client"
				url="com.bos.csm.corporation.flowCancel.findCancelList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="10">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div type="indexcolumn" headerAlign="center">序号</div>
					<div field="PARTY_NUM" headerAlign="center" align="center" allowSort="true" >客户编号</div>
					<div field="PARTY_NAME" headerAlign="center" align="center" allowSort="true" >客户名称</div>
					<div field="NUM" headerAlign="center" align="center" allowSort="true" >业务编号</div>
					<div field="FLAG" headerAlign="center" align="center" allowSort="true" >业务类型</div>
					<div field="BIZ_DATE" headerAlign="center" align="center" allowSort="true" >经办日期</div>
					<div field="STATUS" headerAlign="center" align="center" allowSort="true"dictTypeId="XD_SXCD8003" >流程状态</div>
					<div field="ORG_NUM" headerAlign="center" align="center"dictTypeId="org">经办机构</div>
					<div field="USER_NUM" headerAlign="center" align="center" allowSort="true" dictTypeId="user">经办人</div>
				</div>
			</div>
	</div>
</div>			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form");
    var grid = nui.get("grid"); //借据列表
    query();
	function query() {
// 		form.validate();
// 		if (form.isValid() == false) {
// 			nui.alert("请填写客户编号");
// 			return;
// 		}
			var o = form.getData();
			grid.load(o);

	} 
	init();
	function init(){
		var clsDict = [
        	{"dictname":"--请选择--","dictid":"0"},
        	{"dictname":"评级申请","dictid":"1"},
        	{"dictname":"额度申请","dictid":"2"},
        	{"dictname":"业务申请","dictid":"3"},
        	{"dictname":"合同申请","dictid":"4"},
        	{"dictname":"出账申请","dictid":"5"},
        	{"dictname":"贷后申请","dictid":"6"},
        ];
        nui.get("map.bizType").setData(clsDict);
	}
	
	 function cancel() {
		var row = grid.getSelected();
		if (row) {
			var json1= {"bizId":row.BIZID};
				msg = exeRule("PUB_BIZ_FlOW", "1", json1);
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
			var json= nui.encode({"row":row});
			$.ajax({
            url: "com.bos.csm.corporation.flowCancel.flowCancel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (text) {
            		nui.alert(text.msg);
							query();
				},
			error : function() {
							nui.alert("操作失败！");
						}
    		});
    	}else{
    		alert("请选中一条记录");
    	}
    }
	 function reset(){
		form.reset();
		query();
	}
	</script>
</body>
</html>

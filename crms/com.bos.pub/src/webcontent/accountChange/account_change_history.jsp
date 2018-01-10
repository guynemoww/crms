<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-11-25
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.pub.TbPubAccountChange" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>借据编号：</label>
		<input name="item.summaryNum"id="item.summaryNum" required="false" class="nui-textbox nui-form-input"/>
		<label>经办机构：</label> 
		<input name="item.orgNum" id="item.orgNum"  class="nui-buttonEdit" onbuttonclick="selectEmpOrgs"  />
	</div>
	<div class="nui-toolbar" style="padding-top:5px;padding-bottom:5px;padding-right:20px;text-align:right;"   borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:300px" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="summaryNum" headerAlign="center" allowSort="true">借据编号</div>
		<div field="amt" headerAlign="center" allowSort="true"dataType="currency">退还多收利息金额</div>
		<div field="changeReason" headerAlign="center" allowSort="true" >调整原因</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
		<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org">经办机构</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">调整日期</div>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
    var grid = nui.get("grid1");
	var summaryNum = "<%=request.getParameter("summaryNum") %>";
	if(summaryNum){
    		nui.get("item.summaryNum").setValue(summaryNum);
    }
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});
    function selectEmpOrgs(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context
					+ "/pub/orgDemolition/creditMove/select_all_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 400,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					data = nui.clone(data);
					if (data) {
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}
    function reset(){
		form.reset();
	}
	

</script>
</body>
</html>

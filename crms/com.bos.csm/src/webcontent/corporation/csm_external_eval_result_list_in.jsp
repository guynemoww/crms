<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-15 11:12:44
  - Description:
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
			<input name="party.partyNum" required="false" enabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">客户名称：</label>
			<input name="party.partyName" required="false"enabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
 	
		</div>
	</div>
	</fieldset>
		<fieldset>
			<legend>
				<span>评级历史信息</span>
			</legend>
			
			<div id="crud" class="nui-toolbar" style="border-bottom:0;text-align:left">
				<a class="nui-button" iconCls="icon-zoomin" onclick="viewHistory">查看</a>
			</div>
			<div id="grid" class="nui-datagrid"   sortMode="client"
			    url="com.bos.irm.irmApply.irmApply.getHisIrm.biz.ext" dataField="ratingResults"
			    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
			    emptyText="没有查到数据" showReloadButton="false"
			    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
			    sizeList="[10,20,50,100]" pageSize="10">
			    <div property="columns">
			    	<div type="checkcolumn"> 选择 </div>
			    	<div field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div>
			        <div field="RATING_DT" allowSort="true"  headerAlign="center">评级日期</div>
			       	<div field="RATING_TYPE" name="RATING_TYPE" allowSort="true"  headerAlign="center"dictTypeId="XD_PJCD0111">贷款性质</div>
			        <div field="CREDIT_RATING_CD" allowSort="true"  headerAlign="center">信用等级</div>
			        <div field="EFFECTIVE_START_DT" allowSort="true"  headerAlign="center">起始日期</div>
			        <div field="EFFECTIVE_END_DT" allowSort="true"  headerAlign="center">到期日期</div>
			        <div field="RATING_STATE" allowSort="true"  headerAlign="center" dictTypeId="XD_SXCD8003">是否有效评级</div>
			        <div field="USER_NUM" allowSort="true"  headerAlign="center" dictTypeId="user">经办人</div>
			        <div field="ORG_NUM" allowSort="true"  headerAlign="center" dictTypeId="org">经办机构</div>
			    </div>
			</div>
		</fieldset>
<script type="text/javascript">
	nui.parse();
	var partyId="<%=request.getParameter("partyId") %>";//参与人id
	var form = new nui.Form("#form1");
	var grid = nui.get("#grid");
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
	            form.setData(o);
	            
	            if(text.party.partyTypeCd=="02"){//在自然人展示评级类型
	           	 	grid.showColumn(grid.getColumn("RATING_TYPE"));
	            }else{
	            	grid.hideColumn(grid.getColumn("RATING_TYPE"));
	            }
	        }
	    });
	    grid.load({"partyId":partyId});
    }
    
    function viewHistory(){
  		var row = grid.getSelected();
   		if(row){
   			 if(!row.IRA_APPLY_ID){
   				return alert("没有可查看的评级报告");
   			}   			
   			nui.open({
		        url:  nui.context + "/irm/financialCustom/financial_view_report_jj.jsp?bizId="+row.IRA_APPLY_ID,
		        title: "查看评级报告",
		        state : "max",
		        onload: function () {
		        },
	            ondestroy: function (action) {
	            }
	        });	
   		}else{
   			return alert("请选择一条评级信息");
   		}
    }
</script>
</body>
</html>
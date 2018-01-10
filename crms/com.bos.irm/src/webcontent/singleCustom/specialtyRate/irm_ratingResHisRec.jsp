<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户内部评级信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form2" style="width:100%;height:auto;overflow:hidden;">
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.irm.getProfessionalRateInfo.queryRatingResHisRec.biz.ext" 
		dataField="resultRec"
		allowAlternating="true"
		multiSelect="false"
		sizeList="[10,20,50,100]" pagesize="10">
	     <div property="columns">
	     <div type="checkcolumn"> 选择 </div>
	     <div field="partyNum" headerAlign="center"  allowSort="true"> 客户编号 </div>
	     <div field="partyName" headerAlign="center"  allowSort="true"> 客户名称 </div>
	     <div field="partyId" headerAlign="center" visible="false" allowSort="true"> 客户ID </div>
	     <div field="iraApplyId" headerAlign="center" visible="false" allowSort="true">评级申请ID </div>
	     <div field="initialCredit" headerAlign="center" allowSort="true"> 信用等级 </div>
	     <div field="modelName" headerAlign="center" allowSort="true"> 评级模型 </div>
	     <div field="ratingModelVer" headerAlign="center" allowSort="true"> 评级模型版本 </div>
	     <div field="ratingDt" headerAlign="center"  allowSort="true"> 评级日期 </div>
	     <div field="effectiveStartDt" headerAlign="center" allowSort="true"> 评级有效期起始日</div>
	     <div field="effectiveEndDt" headerAlign="center"  allowSort="true"> 评级有效期截止日</div>
	     <div field="projectId" headerAlign="center" allowSort="true" > 项目编号 </div>
	     <div field="projectName" headerAlign="center" allowSort="true"> 项目名称 </div>
	     <div field="ratingState" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">评级结果是否有效</div>
	     <div field="empname" headerAlign="center" allowSort="true"  > 评级发起人 </div>
	     <div field="orgname" headerAlign="center" allowSort="true" > 评级发起机构 </div>
	</div> 
</div>
	   	<div  class="nui-toolbar"  style="border-bottom:0;text-align:right;margin-top: 20px;">
	   		<a id="save"  class="nui-button" iconCls="icon-check" onclick="check()">查看结果报告</a>
	   	</div>



<script type="text/javascript">

		nui.parse();
	    var grid1 = nui.get("grid");
		var corpid = "<%=request.getParameter("partyId")%>";			//客户ID
			
   		grid1.load({"partyId":corpid});
   		
   		function check(){
   			var row = grid1.getSelected();
   			if(row){
   			   	if(row.iraApplyId){
   				}else{
   					return alert("没有可查看的评级报告");
   				}   			
   				nui.open({
		            url:  nui.context + "/irm/financialCustom/financial_view_report.jsp?bizId="+row.iraApplyId,
		            title: "查看评级报告",
		            onload: function () {
		            },
	            	ondestroy: function (action) {
	            		grid1.reload();
	            	}
	        	});	
   			}else{
   				return alert("请选择一条评级信息");
   			}
   		}
       
</script>
</body>
</html>

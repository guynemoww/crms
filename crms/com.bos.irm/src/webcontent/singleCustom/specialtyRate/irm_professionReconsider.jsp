<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>复评重检</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>

<b>&nbsp;评级记录</b>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.irm.getProfessionalRateInfo.queryCusRatInfo.biz.ext" 
		 dataField="reconRat" allowAlternating="true" multiSelect="false" sizeList="[10,20,50,100]" pagesize="10">
	     <div property="columns">
	     <div type="checkcolumn"> 选择 </div>
	     <div field="iraApplyId" headerAlign="center"  allowSort="true"> 申报编号 </div>
	     <div field="ratingState" headerAlign="center"  allowSort="true" dictTypeId="YesOrNo">评级结果是否有效 </div>
	     <div field="projectId" headerAlign="center"  visible="false"   allowSort="true"> 项目ID</div>
	     <div field="partyTypeCd" headerAlign="center"  visible="false"   allowSort="true"> 客户类型</div>
	     <div field="professionalTypeCd" headerAlign="center"  visible="false"   allowSort="true"> 专业贷款类型</div>
	     <!--  div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_XHLY0002"> 审批机构 </div>
	     <div field="launchDate" headerAlign="center" allowSort="true"> 审批日期 </div> -->
	     <div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" > 经办机构 </div>
	     <div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user" > 经办人员 </div>
	</div> 
</div>

<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
	borderStyle="border:0;">
	<!-- a class="nui-button" id="btnJudge"  onclick="reconsider()">复评</a>  -->
    <a class="nui-button" id="btnCreate" onclick="repeatCheck">重检</a>
</div>


<script type="text/javascript">

	nui.parse();
	git.mask();
    var grid = nui.get("grid");
	var partyId = "<%=request.getParameter("corpid")%>";			//客户ID
	var rateType ="<%=request.getParameter("rateType") %>";			//评级类型
	var json = nui.encode({"partyId":partyId});
	
    function initForm(){
		grid.load({"partyId":partyId,"rateType":rateType});
		git.unmask();
    }	
   initForm(); 
   git.unmask();
   /* 重检 */
   function repeatCheck(){
   		var row;
   		var partyTypeCd ;
	    row = grid.getSelected();
	    partyTypeCd =row.partyTypeCd ;
	    if(partyTypeCd == '04' ){
	    	return alert('同业客户不能发起评级更新');
	    }
	    if(row){
	    	git.mask();
				var json=nui.encode({"rowData":row});
				 nui.ajax({
	                url: "com.bos.irm.getProfessionalRateInfo.createFlowRateUpdate.biz.ext", //创建评级更新流程
	                data:json,
	                type:"POST",
	                contentType:'text/json',
	                success: function (text) {
	                	grid.reload();
						git.unmask();
						if(text.flag != "false"){
		                	//跳转到评级页面
		                	var applyId = text.applyId;
		                	var p = nui.decode(text);
		                	var node = p.node;
		                	var flowType = '02';	//更新流程
		                	var url=nui.context+"/irm/singleCustom/creditRate/eval_corp_tree2.jsp?projectId="+row.projectId+"&partyId="+row.partyId+"&applyId="+applyId+"&flowType="+flowType+"&node="+nui.encode(node);
							git.go(url);
						}else{
							return alert(text.msg);
						}
	                },
	                error: function (jqXHR, textStatus, errorThrown) {
	                    alert(jqXHR.responseText);
	                    grid.reload();
	                    git.unmask();
	                }
	            });	
	    }else{
	    	alert("请选择一条记录");
	    }
   }
 
       
	
</script>
</body>
</html>

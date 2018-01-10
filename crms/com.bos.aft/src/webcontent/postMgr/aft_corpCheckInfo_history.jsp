<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>客户贷后检查报告历史记录</title>
</head>
<body>
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>客户贷后检查报告历史记录</span>
    </legend>
	<div id="datagrid1" class="nui-datagrid" idField="id" showPager="false" url="com.bos.aft.checkReport.queryCheckHistory.biz.ext"
	     dataField="reportInfos" allowCellEdit="true" allowCellSelect="true" multiSelect="false" editNextOnEnterKey="true" >
        <div property="columns"> 
            <div type="checkcolumn">选择</div>
			<div field="inspectDate" >检查时间</div>
			<div field="inspectWayCd" dictTypeId="XD_DHCD0009" >检查方式</div>
			<div field="inspectPlace" >约见地点</div>
			<div field="receptionName" >接待人员姓名</div>
			<div field="receptionPost">接待人员职务</div>
			<div field="userNum" dictTypeId="user">客户经理工号</div>
			<div field="orgNum" dictTypeId="org">经办行</div>
		</div>
	</div>
</fieldset> 
		 <a class="nui-button" iconCls="icon-node" style="text-align: right;float: right;" onclick="queryCheckReportInfo" >查看明细</a>


	<script type="text/javascript">
		nui.parse();
		var datagrid1 = nui.get("datagrid1");
		var partyId ="<%=request.getParameter("partyId") %>";
   function query(){
		   datagrid1.load({"partyId":partyId});
		}
		query();
	
   //查看报告明细		
   function queryCheckReportInfo(){
        git.mask();                                                        //页面遮罩
        var row = datagrid1.getSelected();
        var url;
       // var rule;                                                        //判断是否察看权
        if(row){                                                         //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           if(row.isPeanuts==1){
                  url=nui.context+"/aft/dailyInspect/newSmlCheckReport.jsp?bizId="+row.liId+"&partyId="+partyId+"&onlyRead=1";
           }else{
                  url=nui.context+"/aft/dailyInspect/csmInspectReportHistory.jsp?bizId="+row.liId+"&partyId="+partyId+"&onlyRead=1";
           }
           //git.go(url);
           nui.open({
	            url:url,
	            showMaxButton: true,
	            title: "贷后检查报告查询",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	        });
           
        }else {
            alert("请选中一条记录");
        }
        git.unmask();
    }
	</script>
</body>
</html>
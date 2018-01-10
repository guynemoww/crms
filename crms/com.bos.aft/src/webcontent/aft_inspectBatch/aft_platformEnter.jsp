<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>平台客户列表</title>
</head>
<body style="margin-left:10px;">
	<!-- 
	<div id="datagrid" class="nui-datagrid" style="width:100%;height:auto;" allowResize="true" idField="id"
        url="com.bos.aft.aft_inspectBatch.queryPfCorpList.biz.ext"  dataField="pfCorpList">
        <div property="columns">
        	<div type="checkcolumn" >选择</div>
            <div field="partyName" width="120" headerAlign="center" allowSort="true">平台客户名称</div>                
            <div field="partyNum" width="100" allowSort="true"  headerAlign="center">客户编号</div>            
            <div field="platFormCsmType" width="100" headerAlign="center" dictTypeId="PlatformTypeCd">客户类型</div>
            <div field="managementWayCd" width="100" headerAlign="center" dictTypeId="CsmGroupManagement">管理方式</div>                                    
            <div field="customerNatureCd" width="100" headerAlign="center" dictTypeId="CsmGroupType">客户性质</div>
        </div>
    </div> 
     -->
     <div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="partyName"  class="nui-text nui-form-input" />
			<label>客户编码：</label>
			<input name="partyNum"  class="nui-text nui-form-input"/>
			<label>ECIF客户编号：</label>
			<input name="ecifPartyNum"  class="nui-text nui-form-input"/>
			<label>客户类型：</label>
			<input name="partyTypeCd"  class="nui-text nui-form-input" dictTypeId="XD_KHCD0219"/>
		</div>
	</div>
    <div class="nui-toolbar" style="text-align:right;padding-top:5px;margin-bottom:15px;"  
	    borderStyle="border:0;">
		<a class="nui-button" onclick="save" id="button1">创建平台客户主办客户经理检查</a>
	</div> 
	<script type="text/javascript">
		nui.parse();
		var partyId="<%=request.getParameter("corpid") %>";
		var form=new nui.Form("form1");
		var exit=0;
		function query(){/* 数据加载 */
		    git.mask();
			var json = nui.encode({"param":{"partyId":partyId}});
		    nui.ajax({
                url: "com.bos.aft.dailyInspect.queryCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.corpInfo);
                	exit=text.exit;
                	git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });      			
		}
		query();
		function save(){
		    if(exit==1){
		      alert("为政府融资平台，不能发起检查！");
		      return;
		    }
			var json=nui.encode({"partyId":partyId});
			nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryPfCorp.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	//alert(text.pfId);
                	//if(text.pfId){
                	
                	
                	var param=nui.encode({"partyId":partyId,"pfId":text.pfId});
                	var url=nui.context+"/aft/aft_inspectBatch/aft_platformInspect_tree.jsp?param="+param;
                	git.go(url);
                	//}else{
                	 //alert("该平台客户的检查流程未走完！");
                	
                	//}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            }); 	
		}
	</script>
</body>
</html>
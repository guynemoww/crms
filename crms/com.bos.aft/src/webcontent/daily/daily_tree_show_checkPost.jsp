<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
-->
<head>
<title>客户日常检查信息</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="客户日常检查信息" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
			   <!-- <div id="fright"> -->
		        <iframe id="exportFrame2" src="" style="width: 98%;height: 40px;border: 1px solid Skyblue;" frameborder="0" align="top" ></iframe>
	             <!--</div> -->
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	//var alcInfoId="<%=request.getParameter("bizId") %>";
	var bizId="<%=request.getParameter("bizId") %>";
	var json=nui.encode({"bizId":bizId});
	var inspect ;                             //贷后检查报告
	var partyId;                              //参与人ID
	var party;                                //参与人实体
	var inspectRate;                          //贷后检查频率
	//var lastAlcInfoId="";
	$.ajax({
            url: "com.bos.aft.aft_inspect_report.getInspectInfo.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (text) {
            	inspect = text.inspect;
            	partyId = inspect.partyId;
            	party = text.party;
            	inspectRate = text.inspectRate.setRate;
             document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId"+bizId;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        });
        
      //  alert("inspect:"+nui.encode(inspect));
    
    /* var param=nui.encode({"alcInfoId":alcInfoId,"corpid":partyId,"lastAlcInfoId":lastAlcInfoId});*/
	var menudata = [
		{id:"大众检查报告审核", text:"大中检查报告审核", //expanded:true, 
			children:[
			{id:"客户情况",text:"客户情况",url:"/aft/dailyInspect/inspectBasicInfo.jsp?partyId="+partyId},
		    {id:"检查报告",text:"检查报告",url:"/aft/dailyInspect/newCheckReport.jsp?partyId="+partyId+"&bizId="+bizId+"&onlyRead=1&score=1"}
			//{id:"客户综合评分",text:"客户综合评分",url:"/aft/dailyInspect/customerComprehensiveGrade.jsp?param="+param}
			]},
		//{id:"填写债项级信息",text:"填写债项级信息",url:"/aft/dailyInspect/risk_debt_info.jsp?"},
		//{id:"信贷资产检查报告",text:"信贷资产检查报告",url:"/aft/dailyInspect/aft_norBusiInspectReport.jsp?param="+param},
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&image=loanover"+"&loanOverId="+bizId+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+bizId+"&button=1"}
			]}

	];
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmadd') {
			ecm('add');
			return;
		}
		if (e.node['id']=='ecmview') {
			ecm('view');
			return;
		}
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	nodeclick({"node":menudata[0].children[0]});
	
</script>
<script type="text/javascript">
nui.parse();
//{"custnum":"<%=request.getParameter("corpid") %>","op":op}
function ecm(op) {
		var json = nui.encode({"custnum":"12345","op":op});
        $.ajax({
            url: "com.bos.csm.corp.customerinfo.ecm.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                window.open(o.url);
            }
        });
}
</script>
</body>
</html>
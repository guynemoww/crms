<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>分类客户列表</title>
		<%@include file="/common/nui/common.jsp"%>
	</head>
	<body>
		<center>
		<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left" style="width:100%;height:91%;border:0;"></div>
		</center>
		<script>
			nui.parse();
			var tabs = nui.get("tabs");
			var claMethod = "<%=request.getParameter("sortMold")%>";
			var orgDegree = "<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgdegree") %>";
			var smallUrl_quar = nui.context+"/risk/cust/risk_cust_list_multi.jsp?claMethod="+claMethod+"&custFlag=small";
			var smallUrl = nui.context+"/risk/cust/risk_cust_list.jsp?claMethod="+claMethod+"&custFlag=small";
			var bigUrl = nui.context+"/risk/cust/risk_cust_list.jsp?claMethod="+claMethod+"&custFlag=big";
			var loanUrl_quar = nui.context+"/risk/cust/risk_cust_list_multi.jsp?claMethod="+claMethod+"&custFlag=mloan";
			var loanUrl = nui.context+"/risk/cust/risk_cust_list.jsp?claMethod="+claMethod+"&custFlag=mloan";
			
			var tabsData = [];
			if(orgDegree == "1"){
				if('usual' == claMethod){//日常调整
					tabsData.push({title:"小企业、自然人", url:smallUrl, showCloseButton:false,refreshOnClick:true});
				}else{//季度调整
					tabsData.push({title:"小企业、自然人", url:smallUrl_quar, showCloseButton:false,refreshOnClick:true});
				}
				tabsData.push({title:"非小企业、自然人", url:bigUrl, showCloseButton:false,refreshOnClick:true});
			}else{
				if('usual' == claMethod){//日常调整
					tabsData.push({title:"信贷中心", url:loanUrl_quar, showCloseButton:false,refreshOnClick:true});
				}else{
					tabsData.push({title:"信贷中心", url:loanUrl_quar, showCloseButton:false,refreshOnClick:true});
				}
			}
			
			tabs.setTabs(tabsData);
			$("#tabs").show();
			
			function getData(){
		   		var tabs2 = nui.get("tabs");
		   	 	var tab = tabs2.getTab(tabs.activeIndex);
		   	 	var iframe = tabs2.getTabIFrameEl(tab);
		   	 	var selectdata = iframe.contentWindow.getSelectedData();
		   	 	selectdata = nui.clone(selectdata);
		    	return selectdata;
    		}
	 		
		</script>
	</body>
</html>

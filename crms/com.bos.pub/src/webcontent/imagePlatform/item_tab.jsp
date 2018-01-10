<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>影像展示列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
		nui.parse();
		git.mask();
		var tabs = nui.get("tabs");
		var imageTypeId = "<%=request.getParameter("imageTypeId") %>";
		var loanOverId = "<%=request.getParameter("loanOverId") %>";
	    var imageTypeName="<%=request.getParameter("imageTypeName") %>";
	    var applyId= "<%=request.getParameter("applyId") %>";
		var businessNumber = "<%=request.getParameter("businessNumber") %>";
		var csmNum  ="<%=request.getParameter("csmNum") %>";
		var flag = "<%=request.getParameter("flag") %>";
		var view="<%=request.getParameter("view") %>";
		var showGuarantee="<%=request.getParameter("showGuarantee") %>";
		//哪个流程阶段
		var image="<%=request.getParameter("image") %>";
		//是哪个根节点
		var flowModuleType="<%=request.getParameter("flowModuleType") %>";
		//树节点是否是自然人信息 1是
		var natural="<%=request.getParameter("natural") %>";
		var partyTypeCd="<%=request.getParameter("partyTypeCd") %>";
		
		var guaranteeNumber;
		var tempArray;
		var temp;
		<%--alert("aaaa:"+flowModuleType);--%>
		//var temp1;
		function initTab(){
				<%--var json = nui.encode({"getGuaranteeNumber":{"applyId":businessNumber}})--%>
				
				var json = nui.encode({"applyId":applyId})
				  $.ajax({
	                     url: "com.bos.pub.image.getGuaranteeNumber.biz.ext",
		                type: 'POST',
		                data: json,
		                async:false,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                   		guaranteeNumber = text.getGuaranteeNumbers;
			                
	                    },
	                    error: function () {
	                    	nui.alert("初始化失败！");
	                    }
	                });
					//tempArray = new Array(guaranteeNumber.length+1);
					//temp1= {title:"客户", url:nui.context+"/pub/imagePlatform/item_list.jsp?imageTypeId="+ imageTypeId+"&imageTypeName="+imageTypeName+"&businessNumber="+businessNumber+"&flag="+flag+"&view="+view+"&csmNum="+csmNum+"&loanOverId="+loanOverId, showCloseButton:false};
				
					//tempArray.push(temp1);
					temp="";
					if(guaranteeNumber.length>0&&showGuarantee=='1'){
						for(var i =0;i<guaranteeNumber.length;i++){
							if(i == guaranteeNumber.length-1){
							
								<%--temp = temp+'{title:"担保人'+(i+1)+'", url:nui.context+"/pub/imagePlatform/item_list.jsp?imageTypeId='+ imageTypeId+'&imageTypeName='+ imageTypeName+'&businessNumber='+guaranteeNumber[i].GUARANTEENUMBER+'&flag='+flag+'&view=1&csmNum='+guaranteeNumber[i].GUARANTEENUMBER+'&loanOverId='+loanOverId+'&natural='+natural+'", showCloseButton:false}';--%>
								temp = temp+'{id:"guarantee",title:"担保人:'+guaranteeNumber[i].PARTYNAME+'", url:nui.context+"/pub/imagePlatform/item_tree.jsp?businessNumber='+guaranteeNumber[i].GUARANTEENUMBER+'&csmNum='+guaranteeNumber[i].GUARANTEENUMBER+'&partyTypeCd='+guaranteeNumber[i].partyTypeCd+'", showCloseButton:false}';
							}else{
								temp = temp+'{id:"guarantee",title:"担保人:'+guaranteeNumber[i].PARTYNAME+'", url:nui.context+"/pub/imagePlatform/item_tree.jsp?businessNumber='+guaranteeNumber[i].GUARANTEENUMBER+'&csmNum='+guaranteeNumber[i].GUARANTEENUMBER+'&partyTypeCd='+guaranteeNumber[i].partyTypeCd+'", showCloseButton:false};';
							}
<%--							temp = {title:"担保人"+(i+1), url:nui.context+"/irm/modelparam/ratingmapping/item_list.jsp?imageTypeId="+ imageTypeId+"&imageTypeName="+ imageTypeName+"&businessNumber="+businessNumber+"&flag="+flag+"&view="+view+"&csmNum="+guaranteeNumber[i].GUARANTEENUMBER+"&loanOverId="+loanOverId, showCloseButton:false};--%>
						//	alert(nui.encode(temp));
				//			tempArray.push(temp);
							//alert("aa"+temp);
						}
						
					}
					if(temp != ""){
						temp= '{title:"客户信息", url:nui.context+"/pub/imagePlatform/item_list.jsp?imageTypeId='+ imageTypeId+'&imageTypeName='+imageTypeName+'&businessNumber='+businessNumber+'&flag='+flag+'&view='+view+'&csmNum='+csmNum+'&loanOverId='+loanOverId+'&image='+image+'&flowModuleType='+flowModuleType+'&natural='+natural+'&partyTypeCd='+partyTypeCd+'", showCloseButton:false};'+temp;
					}else{
							temp= '{title:"客户信息", url:nui.context+"/pub/imagePlatform/item_list.jsp?imageTypeId='+ imageTypeId+'&imageTypeName='+imageTypeName+'&businessNumber='+businessNumber+'&flag='+flag+'&view='+view+'&csmNum='+csmNum+'&loanOverId='+loanOverId+'&image='+image+'&flowModuleType='+flowModuleType+'&natural='+natural+'&partyTypeCd='+partyTypeCd+'", showCloseButton:false}';
					}
						
					
		}
	
		initTab();

		var array =  temp.split(";");
		tabs.setTabs(nui.decode("["+array+"]"));
		$("#tabs").show();
		git.unmask();
</script>
</body>
</html>
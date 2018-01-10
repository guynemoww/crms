<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject,java.util.Map"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<link href="inc/master.css" rel="stylesheet" type="text/css"/>

</head>
<body>	
	<%
		IUserObject user = CommonUtil.getIUserObject();
		Map map = user.getAttributes();
		String orglevel = (String)map.get("orglevel");
	%>
		
		<!-- 这个div控制时间位置 -->
		<div id="data">
				
		</div>
		<div class="conR axis">
			<div class="axCon" id="htm">
				
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:left;margin-left:550px;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="queryExcl()">打印</a>
		</div>
		<input id="tytitle" value="同意提示" style="display: none;"/>
</body>
<script type="text/javascript">
nui.parse();
var begin=0;
var total=0;
var dCount=30;
var cx = 36;
var cy = 16;
function initData(){
		var json =nui.encode({"processInstId":<%=request.getParameter("processInstId")%>,"begin":begin});
		$.ajax({
	        url: "com.bos.bps.util.TbWfmWorkItemInstance.queryTbWfmWorkitemByProcessId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	//翻页条件
	        	begin=text.begin;
	        	total=text.total;
	        	
	        	
	        	var s_data='';
	        	
	        	//存放拼接的内容
	        	var s_centext='';
	        	
	        	//先循环拼接参与意见
	        	var wios = text.wios;
	        	for(var i=0;i<wios.length;i++){
	        	
	        		//这里拼接左边的时间
	        		s_data+='<span style="position:absolute;top:'+dCount+'px;left:28px;">'+wios[i].createTime+'</span>';
	        		dCount=dCount+120;
	        		//这里拼接右边的内容以及中间的时间节点
	        		s_centext+='<a href=""><img src="images/circleT.gif" style="position:absolute;top:'+cx+'px;left:148px;"/></a>';
	        		s_centext+='<div  class="axBl axBr" style="margin-top:'+cy+'px;';
	        		s_centext+='background:#F1F1FB;width:485px;';
	        		s_centext+='"><div  class="axTxt"><div  class="border">';
	        		s_centext+='<div  style="width:460px;height:90px;float:left;border:1px solid #bcbbbb;border-right:0;border-left:0;padding:4px 6px 8px 6px;"><ul>';
					s_centext+='<li class="bold">参与岗位：'+wios[i].activityinstname+'</li>';
					s_centext+='<li>提出人名称：'+wios[i].operatorName+'</li>';
					
					var opinionName = wios[i].opinionType;
					if("1"==opinionName){
						opinionName = "参与";
					}else if("2"==opinionName){
						opinionName = "挂起";
					}else{
						opinionName = "终止";
					}
					s_centext+='<li>意见类型：'+opinionName+'</li>';
					var opinion=wios[i].opinion;
					if(opinion!=null&&opinion!=""){
						if(opinion.length>20){
							s_centext+='<li title="'+opinion+'">意见：'+opinion.substring(0, 20)+'......<a href="#" style="text-decoration:none;color:33aacc;" onclick="copytitle(\''+ opinion+ '\');">[复制]</a></li>';	
						}else{
							s_centext+='<li>意见：'+opinion+'</li>';	
						} 
					}
					s_centext+='<li style="line-height:19px;"></li>';
	        		s_centext+='</ul></div></div></div></div>';
	        		cx=cx+120;
	        	}
	        	//历史过程意见
	        	var items = text.items;
	        	for(var i=0;i<items.length;i++){
	        		//这里拼接左边的时间
	        		s_data+='<span style="position:absolute;top:'+dCount+'px;left:28px;">'+items[i].performtime+'</span>';
	        		dCount=dCount+120;
	        		//这里拼接右边的内容以及中间的时间节点
	        		s_centext+='<a href=""><img src="images/circleT.gif" style="position:absolute;top:'+cx+'px;left:148px;"/></a>';
	        		s_centext+='<div  class="axBl axBr" style="margin-top:'+cy+'px;';
	        		if('99'==items[i].conclusion){
	        			s_centext+='background:#eee;width:485px;';
	        		}
	        		s_centext+='"><div  class="axTxt"><div  class="border">';
	        		s_centext+='<div  style="width:460px;height:90px;float:left;border:1px solid #bcbbbb;border-right:0;border-left:0;padding:4px 6px 8px 6px;"><ul>';
					s_centext+='<li class="bold">操作岗位：'+items[i].orgName+'-'+items[i].postName+' <font style="color:red">[操作人：'+items[i].userName+']</font></li>';
					
					//下一岗位
					var nextPostName = items[i].nextPostName;
					//下一操作人
					var nextUserName = items[i].nextUsersName;
					var nextUserNum = items[i].nextUsersNum;
					if(null == nextPostName || ''==nextPostName || 'undefined'==nextPostName){
						nextPostName = "结束";
						nextUserName = "无";
					}else{
						nextPostName = items[i].nextOrgName+'-'+nextPostName;
					}
					if(nextUserName.length>10){
						s_centext+='<li>下一操作岗位：'+nextPostName+' <font style="color:red">[操作人：<span title="'+nextUserName+'">'+nextUserName.substring(0, 10)+'('+nextUserNum.substring(0, 10)+')......</span>]</font></li>';
					}else{
					
						s_centext+='<li>下一操作岗位：'+nextPostName+' <font style="color:red">[操作人：'+nextUserName+'('+nextUserNum+')]</font></li>';
					}
					s_centext+='<li>意见结论：'+nui.getDictText("XD_WFCD0002",items[i].conclusion)+'</li>';
					var opinion=items[i].opinion;
					if(opinion!=null&&opinion!=""){
						if(opinion.length>20){
							s_centext+='<li title="'+opinion+'">意见：'+opinion.substring(0, 20)+'......<a href="#" style="text-decoration:none;color:33aacc;" onclick="copytitle(\''+ opinion+ '\');">[复制]</a></li>';	
						}else{
							s_centext+='<li>意见：'+opinion+'</li>';	
						} 
					}
					s_centext+='<li style="line-height:19px;">状态：'+nui.getDictText("XD_WFCD0003",items[i].status)+'</li>';
				    s_centext+='</ul></div></div></div></div>';
	        		
	        		cx=cx+120;
	        	}
	        	$("#data").html(s_data);
	        	$("#htm").html(s_centext);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
}


$(document).ready(function(){
	
		initData();
});

$(function(){

	//绑定事件
	$(window).bind("scroll", function(e){ 
		//当滚动条滚动时
		if($(document).scrollTop()>=$(document).height()-$(window).height()){
			
			begin = begin+5;
			
			if(begin<total){
				var json =nui.encode({"processInstId":<%=request.getParameter("processInstId")%>,"begin":begin});
				$.ajax({
			        url: "com.bos.bps.util.TbWfmWorkItemInstance.queryTbWfmWorkitemByProcessId.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        
			        	//翻页条件
			        	begin=text.begin;
			        	total=text.total;
			        	
			        	var items = text.items;
			        	var s_data='';
			        	
			        	//存放拼接的内容
			        	var s_centext='';
			        	for(var i=0;i<items.length;i++){
			        		//这里拼接左边的时间
			        		s_data+='<span style="position:absolute;top:'+dCount+'px;left:28px;">'+items[i].performtime+'</span>';
			        		dCount=dCount+120;
			        		//这里拼接右边的内容以及中间的时间节点
			        		s_centext+='<a href=""><img src="images/circleT.gif" style="position:absolute;top:'+cx+'px;left:148px;"/></a>';
			        		s_centext+='<div  class="axBl axBr" style="margin-top:'+cy+'px;';
			        		if('99'==items[i].conclusion){
			        			s_centext+='background:#eee;width:485px;';
			        		}
	        				s_centext+='"><div  class="axTxt"><div  class="border">';
			        		s_centext+='<div  style="width:460px;height:90px;float:left;border:1px solid #bcbbbb;border-right:0;border-left:0;padding:4px 6px 8px 6px;"><ul>';
							s_centext+='<li class="bold">操作岗位：'+items[i].orgName+'-'+items[i].postName+' <font style="color:red">[操作人：'+items[i].userName+']</font></li>';
							
							//下一岗位
							var nextPostName = items[i].nextPostName;
							//下一操作人
							var nextUserName = items[i].nextUsersName;
							var nextUserNum = items[i].nextUsersNum;
							if(null == nextPostName || ''==nextPostName || 'undefined'==nextPostName){
								nextPostName = "结束";
								nextUserName = "无";
							}else{
								nextPostName = items[i].nextOrgName+'-'+nextPostName;
							}
							if(nextUserName.length>10){
					
								s_centext+='<li>下一操作岗位：'+nextPostName+' <font style="color:red">[操作人：<span title="'+nextUserName+'">'+nextUserName.substring(0, 10)+'('+nextUserNum.substring(0, 10)+')......</span>]</font></li>';
							}else{
							
								s_centext+='<li>下一操作岗位：'+nextPostName+' <font style="color:red">[操作人：'+nextUserName+'('+nextUserNum+')]</font></li>';
							}
							s_centext+='<li>意见结论：'+nui.getDictText("XD_WFCD0002",items[i].conclusion)+'</li>';
							var opinion=items[i].opinion;
							if(opinion!=null&&opinion!=""){
								if(opinion.length>20){
								
									s_centext+='<li title="'+opinion+'">意见：'+opinion.substring(0, 20)+'......<a href="#" style="text-decoration:none;color:33aacc;" onclick="copytitle(\''+ opinion+ '\');">[复制]</a></li>';	
									
								}else{
									s_centext+='<li>意见：'+opinion+'</li>';	
								}
							}
							s_centext+='<li style="line-height:19px;">状态：'+nui.getDictText("XD_WFCD0003",items[i].status)+'</li>';
						    s_centext+='</ul></div></div></div></div>';
			        		
			        		cx=cx+120;
			        	}
			        	
			        	var data=$("#data").html();
			        	var htm=$("#htm").html();
			        	
			        	$("#data").html(data+s_data);
			        	$("#htm").html(htm+s_centext);
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
			    });
			}
		}
	}); 
})

function copytitle(opinion){
	var inputty = document.getElementById("tytitle");
	inputty.style.display="";
	inputty.value = opinion;
	inputty.select();
	document.execCommand("copy");
	inputty.style.display="none"; 
}

function queryExcl(){
	var json =nui.encode({"processInstId":<%=request.getParameter("processInstId")%>,"printType":"SPLC"});
	$.ajax({
        url: "com.bos.pay.LoanSummary.printWorkflow.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if (text.swfPath) {
								nui
										.open({
											url : nui.context
													+ "/biz/biz_report/contract_view.jsp?filePath="
													+ text.swfPath,
											title : "贷款审批历史记录",
											width : 1000,
											height : 500,
											onload : function() {
											},
											ondestroy : function(action) {
												grid.reload();
											}

										});
							} else {
								alert("无打印信息!");
							}
        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
     });
}

function printCopUser(){//userNum
	
}
</script>
</html>
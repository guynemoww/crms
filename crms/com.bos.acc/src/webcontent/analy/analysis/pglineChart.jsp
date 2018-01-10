<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="java.util.Map"%>
<%@include file="/common/nui/common.jsp"%>
<%
	Object rootObj = XpathUtil.getDataContextRoot("request", pageContext);
	//获取折线图标题
	String titles = XpathUtil.getObjectByXpath(rootObj,"titles").toString();
	//获取X轴数据
	String xjson = XpathUtil.getObjectByXpath(rootObj,"xjson").toString();
	//获取图对应数据
	Object[] resultList = (Object[])XpathUtil.getObjectByXpath(rootObj,"resultList");
	//获取Y轴数据单位
	String yunit = XpathUtil.getObjectByXpath(rootObj,"yunit").toString();
	//获取显示图表类型  line, spline, area, areaspline, column, bar, pie , scatter
	String seriesType = XpathUtil.getObjectByXpath(rootObj,"seriesType").toString();
	
	
	
	//设置自定义刻度
	String tickInterval=XpathUtil.getObjectByXpath(rootObj,"tickInterval").toString();
	
	//设置导出的文件名
	String exportfileName=XpathUtil.getObjectByXpath(rootObj,"exportfileName").toString();
	
	//设置图表宽度
	String chartWidth=XpathUtil.getObjectByXpath(rootObj,"chartWidth").toString();
	
	
	//设置图表高度
	String chartHeight=XpathUtil.getObjectByXpath(rootObj,"chartHeight").toString();
	
	//动态设置导出图表图片的url请求
	String url=XpathUtil.getObjectByXpath(rootObj,"url").toString();
	
	
 %>
<!-- 
  - Author(s): 谭凯
  - Date: 2013-12-03 20:07:33
  - Description: define by 折线图、曲线图等........
-->
<style type="text/css">
#container {
	position: absolute;
	width: 49.9%;
	height: 15%;
	z-index: 1;
	left: 0px;
}
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=titles %></title>
<script type="text/javascript" src="acc/analy/js/jquery.min.js"></script>
<script type="text/javascript" src="acc/analy/js/highcharts.src.js"></script>
<script type="text/javascript" src="acc/analy/js/exporting.js"></script>
<script type="text/javascript">
			  $.ajax({
	            url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	alert(1);
	               
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask();
	                nui.alert(jqXHR.responseText);
	            }
		     });
		

			
		  /* 图表变量统一配置参数 begin 刘子良(2014/01/09修改)*/
			var tickInterval=<%=tickInterval %>;//设置自定义刻度
			var exportfileName='<%=exportfileName %>';//设置导出的文件名
			var chartWidth=<%=chartWidth %> ; //设置图表宽度
			var chartHeight=<%=chartHeight %> ; //设置图表高度
			//$.urlFun('<%=url %>');//动态设置导出图表图片的url请求
		  /* 图表变量统一配置参数 end */
		
			var chart;
			var titles = '<%=titles %>';
			var xjson = <%=xjson %>;
			
			$(document).ready(function() {
				
				chart = new Highcharts.Chart({
					//刘子良  2014/01/02 	begin
					exporting: {
						enabled:false,//默认为可用，当设置为false时，图表的打印及导出功能失效
						buttons:{
							printButton:{    //配置打印按钮                         
								width:50,                            
								symbolSize:20,                            
								borderWidth:2,                           
								borderRadius:0,                            
								hoverBorderColor:'red',                            
								height:30,                            
								symbolX:25,                            
								symbolY:15,                            
								x:-80,                            
								y:0                        
							},
							exportButton:{    //配置导出按钮                            
								width:50,                            
								symbolSize:20,                            
								borderWidth:2,                            
								borderRadius:0,                           
								hoverBorderColor:'red',                            
								height:30,                            
								symbolX:25,                            
								symbolY:15,                            
								x:-10,                            
								y:0                    
							}
							
						},
						filename:exportfileName,//导出的文件名                   
						type:'image/png',//导出的文件类型              
						width:800    //导出的文件宽度 
					},
					
					//刘子良  2014/01/02 	end
					chart: {
						renderTo: 'container',
						defaultSeriesType: '<%=seriesType %>',
						reflow:true,
						width:chartWidth,
						height:chartHeight
					},
					/*begin 刘子良  2014/01/02 修改了 href: "http://www.highcharts.com" 超链接的问题*/
					 credits:{//右下角的文本   
		             enabled: false,   
		             position: {//位置设置   
		                align: 'right',   
		                x: -10,   
		                y: -10   
		             },   
		             href: "#",//点击文本时的链接   
		             style: {   
		                color:'blue'   
		             },   
		             text: ""//显示的内容   
		             },   
					/*end*/
					lang:{
        				exportButtonTitle:'导出PDF',
        				printButtonTitle:'打印报表'
					},
					title: {
						text: titles
					},
					yAxis: {//刘子良 修改  2014/01/02[自定义刻度]
						tickInterval: tickInterval,  //自定义刻度
					    //max:10,//纵轴的最大值   
					    //min: 0,//纵轴的最小值  
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
					        	var showVal=this.value;
					            return this.value+'<%=yunit %>';
							}
						}
					}, xAxis: {
						categories: xjson
					},
					tooltip: {
						crosshairs: true,
						shared: true
					},
					series: [ 
						<%for (int i = 0 ; i < resultList.length ; i ++  ) {
							Map obj = (Map)resultList[i];
						%>
							{
								name: '<%=obj.get("serieName").toString() %>' ,
								data: <%=obj.get("datas").toString() %> 
							}
							<%if(i == resultList.length) break; else { %>
								,
							<% }%>
						<%} %>
					]
				});
				
				
			});
			//jQuery.exportChart('http://127.0.0.1:7070/exportImge/export');
				
		</script>
</head>
<body>
	<div
		style="width: 99.5%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black">
		<fieldset>
			<legend>
				<span>图表1</span>
			</legend>
			<div id="container"
				style="width: 100%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black"></div>
		</fieldset>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />

		<fieldset>
			<legend>
				<span>列表</span>
			</legend>
			<div
				style="width: 100%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black">
				<%@include
					file="/acc/acccustomerfinance/acccustomerfinance_list.jsp"%>
			</div>
		</fieldset>
	</div>
</body>


</html>



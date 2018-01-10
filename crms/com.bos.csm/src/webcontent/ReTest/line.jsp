<%@page pageEncoding="UTF-8"%>
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
<script language="javascript" src="drawline.js" ></script>
<style>
	body{
		overflow:auto;
	}
	 
	 .odiv { font-weight: bold;   font-size:13px; }
	
	
	
</style>

</head>
<html>
<body>
	<div id="form1" style="width:10%;position:absolute;text-align:left;top:0px;left:0px;font-size:12.5px;">
		<fieldset>
		  <legend>
		    <span>图形说明</span>
		  </legend>
				<lable>1.<span style="color:#ff4500;font-weight: bold;">————</span>股东关系</lable>&nbsp;&nbsp;
				<lable>2.<span style="color:lightgreen;font-weight: bold;">————</span>高管关系</lable>&nbsp;&nbsp;
				<lable>3.<span style="color:#6A5ACD;font-weight: bold;">————</span>担保关系</lable>&nbsp;&nbsp;
				<lable>4.<span style="color:DodgerBlue;font-weight: bold;">————</span>对外投资</lable>&nbsp;&nbsp;
				<%--<input type="button" value="展示下一层" style="margin-left:5px;" onclick="showNextLine()"/>--%>
		</fieldset>
	</div>
	<div id="mainDiv"  style="text-align:middle;overflow:auto">
		<div id="<%=request.getParameter("partyId") %>" style="border:1px solid black;position:absolute;width:95px;height:60px;" >
			
		</div>
	</div>
</body>
</html>


<script language="JavaScript">
//param 1:left
//param 2:top

</script>



<script language="JavaScript">
//���ڴ˷�����ʹ�÷�ʽ
	//param 1:left����㣩
	//param 2:top ����㣩
	//param 3:left���յ㣩
	//param 4:top ���յ㣩
	//draw.drawArrow(150,130,250,130,"red");//��������
	
	//prama1 数据源
	//prama2 行数
	//开始画关系视图，每行调用一次
	//CreateCust(JsonData,1);
	//CreateCust(JsonData2,2);
	//CreateCust(JsonData3,3);
	
	
	//--------------------------------------画图功能---------------------------------------------------------
	//可以使用不同的线条颜色表示不同的--关系（线色数据源）
	var lineColor = {"20120":"#ff4500","10500":"#ff4500","20110":"#1E9OFF","11002":"#6A5ACD","20400":"#6A5ACD","10701":"#cccc"};
	//可以使用不同的线条颜色表示不同的--客户（线色数据源）
	var custColor = {"1":"orange","2":"red","3":"blue","4":"green"};

	//主Div
	var MainDiv  = document.getElementById("mainDiv");

	//获取浏览区工作区域Width
	var winWidth = window.screen.width;

	
	//初始化画线工具
	var draw = new FlowGraphic(MainDiv);

	//测试数据源
	<%--var JsonData = [
	{partyName:"客户a",partyId:"1",partyId2:"mainParty",type:"1"},
	{partyName:"客户b",partyId:"2",partyId2:"mainParty",type:"2"},
	{partyName:"客户c",partyId:"3",partyId2:"mainParty",type:"3"},
	{partyName:"客户d",partyId:"4",partyId2:"mainParty",type:"4"},
	{partyName:"客户e",partyId:"5",partyId2:"mainParty",type:"2"},
	];
	
	var JsonData2 = [
	{partyName:"客户A",partyId:"6",partyId2:"1",type:"2"},
	{partyName:"客户B",partyId:"7",partyId2:"2",type:"3"},
	{partyName:"客户C",partyId:"8",partyId2:"3",type:"2"},
	{partyName:"客户D",partyId:"9",partyId2:"4",type:"1"},
	{partyName:"客户E",partyId:"10",partyId2:"5",type:"4"},
	];

	var JsonData3 = [
	{partyName:"客户A",partyId:"11",partyId2:"6",type:"2"},
	{partyName:"客户B",partyId:"12",partyId2:"6",type:"4"},
	//{partyName:"客户C",partyId:"13",partyId2:"6"},
	//{partyName:"客户D",partyId:"14",partyId2:"6"},
	//{partyName:"客户E",partyId:"15",partyId2:"6"},
	//{partyName:"客户A",partyId:"16",partyId2:"6"},
	{partyName:"客户B",partyId:"17",partyId2:"7",type:"1"},
	{partyName:"客户C",partyId:"18",partyId2:"7",type:"2"},
	//{partyName:"客户D",partyId:"19",partyId2:"7"},
	//{partyName:"客户E",partyId:"20",partyId2:"7"},
	//{partyName:"客户A",partyId:"21",partyId2:"7"},
	//{partyName:"客户B",partyId:"22",partyId2:"7"},
	{partyName:"客户C",partyId:"23",partyId2:"8",type:"4"},
	{partyName:"客户D",partyId:"24",partyId2:"8",type:"4"},
	//{partyName:"客户E",partyId:"25",partyId2:"8"},
	//{partyName:"客户A",partyId:"26",partyId2:"8"},
	//{partyName:"客户B",partyId:"27",partyId2:"8"},
	//{partyName:"客户C",partyId:"28",partyId2:"8"},
	//{partyName:"客户D",partyId:"29",partyId2:"9"},
	{partyName:"客户E",partyId:"30",partyId2:"9",type:"2"},
	{partyName:"客户A",partyId:"31",partyId2:"9",type:"3"},
	//{partyName:"客户B",partyId:"32",partyId2:"9"},
	//{partyName:"客户C",partyId:"33",partyId2:"9"},
	{partyName:"客户D",partyId:"34",partyId2:"10",type:"1"},
	{partyName:"客户E",partyId:"35",partyId2:"10",type:"4"}
	];--%>

	
	
	//主客户样式
	var mainObj = document.getElementById("<%=request.getParameter("partyId") %>");
	mainObj.innerHTML = "当前客户";
	mainObj.style.backgroundColor="#ff7f50";
	mainObj.style.textAlign="center";
	mainObj.style.left=(winWidth/2)-(mainObj.offsetWidth/2)-12+'px'; 
	mainObj.style.top='20px';  
	mainObj.style.position='absolute';
	

	//默认样式数据源 可修改
	var SouceData = {border:"1px solid #FF0000",width:"95",height:"60"};

	//生成的Div样式特定样式，考虑是否使用
	var VisType   = {"A":{style:"",onclick:""}};

	//Div背景色（可用于区分客户类型）
	var backGround = {"01":"#ff4500","02":"#1e90ff","03":"#00ff7f","04":"#87cdfa","05":"#87cdfa","06":"#87cdfa"};

	//prama1 数据源
	//prama2 行数
	//开始画关系视图，每行调用一次
	//CreateCust(JsonData,1);
	//CreateCust(JsonData2,2);
	//CreateCust(JsonData3,3);
	
	//记录每次画出的客户
	var partys;
	

	//获取X轴坐标（div对象,是否子节点）
	function getPointX(obj,type){
		if(type=="node"){
			return parseInt(obj.style.left.replace(/[^0-9]/ig,""))+parseInt(obj.style.width.replace(/[^0-9]/ig,"")/2)
		}else{
			return parseInt(obj.style.left.replace(/[^0-9]/ig,""))+(obj.offsetWidth/2);
		}
		
	}

	//获取Y轴坐标（div对象,是否子节点）
	function getPointY(obj,type){
		if(type=="node"){
			return parseInt(obj.style.top.replace(/[^0-9]/ig,"")-3);
		}else{
			return parseInt(obj.style.top.replace(/[^0-9]/ig,""))+obj.offsetHeight-2;
		}
		
	}
	
	//已经处理过的客户
	var createdCust = new Array();
	
	//创建每行的视图
	//custs:行客户数据源
	//lineNum:行数
	
	var lineIndex = 1;
	function CreateCust(custs,lineNum){
		num = custs.length+1;		
		//每次使用新的记录
		partys = null;
		partys = new Array();
		var index = 0;
		for(var i =0;i<custs.length;i++){
			//判断是否处理过
			if(LineContains(createdCust,custs[i].relaPartyId)==false){
				//记录此次的客户
				partys[i] = custs[i].relaPartyId
					//生成Div
				var obj = document.createElement("DIV");
				obj.id = custs[i].relaPartyId; //记录客户主键
				obj.innerHTML = custs[i].partyName;
				obj.name = custs[i].relaPartyId;//记录name 防止线层级错误
				
				//obj.style.backgroundColor = backGround[custs[i].partyTypeCd];
				obj.style.backgroundColor = "skyblue";
				obj.key = custs[i].otherRelatedPartyId;//记录主键，方便扩展
				obj.style.textAlign="center";
				
				if(lineNum==1){
					obj.style.left=(winWidth/num)+((index)*145)+'px';  //Divleft 定位 根据列
				}else{
					obj.style.left=(winWidth/num)+((index+1)*120)+'px';  //Divleft 定位 根据列
				}
				index++;
				obj.className="odiv";
				obj.style.top=160*lineNum+'px';  // Div height定位 根据行
				//obj.style.border=SouceData.border; // 
				obj.style.position='absolute'; // 修改为绝对定位
				obj.style.width=SouceData.width+'px'; // 获取公共数据定义的width
				obj.style.height=SouceData.height+'px'; //获取公共数据定义的width
				//获取画线坐标
				var Pobjs = document.getElementsByName(custs[i].partyId);
				var Pobj ;//上级节点
				
				if(Pobjs.length>1){
					Pobj = Pobjs[Pobjs.length-1]
				}else{
					Pobj = Pobjs[0];
				}
				
				//alert(getPointX(obj));
				//lineColor[custs[i].type];根据不同的关系，画出不同颜色的线
				//alert(getPointX(obj,"node")+"-"+getPointY(obj,"node")+"-"+getPointX(Pobj)+"-"+getPointY(Pobj)+"-"+lineColor[custs[i].relatedCd]);
				draw.drawArrow(getPointX(obj,"node"),getPointY(obj,"node"),getPointX(Pobj),getPointY(Pobj),lineColor[custs[i].relatedCd]);//开始画线
				
				MainDiv.appendChild(obj);
				//createdCust记录处理过的客户
				createdCust[createdCust.length] = custs[i].relaPartyId;
				lineIndex++;
			}
			
		}
		 console.log(nui.encode(partys));
	}
	
	//判断数组中是否包含此元素
	function LineContains(arr,element){
		for(var i = 0;i<arr.length;i++){
			if(arr[i]==element){
				return true;
			}
		}
		return false;
	}

	//-------------------------------------------获取数据源，开始画图---------------------------------------------
	var partyId = "<%=request.getParameter("partyId") %>";
	
	//是否开始画图（如果查询到下面没有关系节点，则为False）
	var StarFlag = true;
	
	//循环开始画图
	
		
	//第一层
	function initFrist(){
		var json  = nui.encode({"partyId":partyId});
		
		$.ajax({
            url: "com.bos.csm.pub.Relationship.getNextMenmer.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.tbCsmOtherRelatedPartys){
            		//第一行视图
            		CreateCust(text.tbCsmOtherRelatedPartys,1);
					//alert("画图完毕");
            	}else{
            		StarFlag = false;
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
		
	}
	
	//第二层
	function initLine(lineNum){
		git.mask();
		var json = nui.encode({"partys":partys});
		$.ajax({
            url: "com.bos.csm.pub.Relationship.getNextLineMenmer.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            success: function (text) {
           		git.unmask();
            	//第lineNum行视图
            	if(text.Menmers.length!=0){
            		CreateCust(text.Menmers,lineNum);
            	}else{
            		StarFlag = false;
            	}
            	
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask();
                nui.alert(jqXHR.responseText);
            }
        });
		
	}
	
	//第一行
	initFrist(); 
	var lineNum = 1;//默认第二行开始画图
	
	function showNextLine(){
		lineNum++;
		if(lineNum<=6){
			initLine(lineNum);
			if(StarFlag==false){
				alert("已经没有下级关系了");
				return;
			}
		}else{
				alert("无法在扩展下级关系");
				return;
		}
		console.log(lineIndex+"个Div");
	}
	
</script>



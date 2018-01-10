<%@page import="java.util.Calendar"%>
<%@page import="com.bos.pub.DateUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/nui/common.jsp" %>
<html>
<head>
<title>影像集中扫描页面</title>
<SCRIPT LANGUAGE = "JavaScript" >
	
	
	var items;
	/**
	*设置工具栏
	**/
	function settoolbar()
	{
		var xml = "<?xml version='1.0' encoding='GB2312' ?>"+
		"<root>"+
			"<head>"+
				"<transcode>td0037</transcode>"+
			"</head>"+
			"<body>"+
				"<param>11111111111111111100000111000011</param>"+
				"<param>110011000000000000011111000</param>"+
				"<param></param>"+
			"</body>"+
		"</root>";
		SunScan.CommOcxFunction(xml);
	}
	
	/**
	*设置工具栏
	**/
	function settoolbarAll()
	{
		var xml = "<?xml version='1.0' encoding='GB2312' ?>"+
		"<root>"+
			"<head>"+
				"<transcode>td0037</transcode>"+
			"</head>"+
			"<body>"+
				"<param>11111111111111111111110111001011</param>"+
				"<param>111111111101111111111111111</param>"+
				"<param>00000000</param>"+
			"</body>"+
		"</root>";
		SunScan.CommOcxFunction(xml);
	}
	/**
	*	隐藏 界面窗口
	**/
	function hideControl(param){
		var xml ="<?xml version='1.0' encoding='GB2312' ?>"+
				"<root>"+
			  "<head>"+
				"<transcode>td0033</transcode>"+
			"</head>"+
			"<body>"+
			 "<param>"+ param +"</param>"+
			 "</body>"+
			"</root>";
			SunScan.CommOcxFunction(xml);
	
	}

// 	/**
// 	*	修改影像图片名称
// 	**/
// 	function modifyName(){
// 		var xml ="<?xml version='1.0' encoding='GB2312' ?>"+
// 				"<root>"+
// 			  "<head>"+
// 				"<transcode>td0008</transcode>"+
// 			"</head>"+
// 			"<body>"+
// 			 "<param showname=’显示名’ fileface=’’ fileform=’’ md5=’’ reserve=’备注字段’filetype=’’ formrec=’’>"+"001-00001-fr"+"</param>"+
// 			 "</body>"+
// 			"</root>";
// 			SunScan.CommOcxFunction(xml);
	
// 	}	
	
	/**
	*	缩略图默认显示第一张
	**/
	function setControlParam(){
		var xml = "<?xml version='1.0' encoding='GB2312' ?>"+
		"<root>"+
			"<head>"+
				"<transcode>td0010</transcode>"+
			"</head>"+
			"<body>"+
				"<param name='isqryshowfirst'>1</param>"+
				"<param name='isaddshowfirst'>0</param>"+
				"<param name='rotatesave'>1</param>"+
			"</body>"+
		"</root>";
	
		SunScan.CommOcxFunction(xml);
	}


/**
*将旧影像系统中的资料，通过接口，根据业务流水号、文档部件、查询日期，查询出来后，
*导入到新的影像控件当中。以满足数据迁移。
*businessNumber：业务流水号
*/	
function importOldImage(businessNumber){

	var json = nui.encode({"businessNumber":"1234567890"});
	$.ajax({
	        url: "com.bos.pub.image.queryOldImage.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        success: function (text) {
	        	if(null != text.msg && '1'==text.msg){
	        	
		        	SunScan.CommOcxFunction(text.rtxml);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});

}	

/**
*生成影像业务信息树，以及目录树
*imageIP：影像存储IP地址
*imagePort:端口
*docId：批次号
*docName：批次名称
*tranDate：业务日期
*vflag：操作类型 0-新增影像操作 1-修改影像操作（控件会先查询） 2-只读查询影像操作 3-删除批次操作。
*data：目录树的查询条件
**/	
function addRemoteAuth(cj,vflag,data)
{
	var json = nui.encode(data);
	$.ajax({
	        url: "com.bos.pub.image.getImageTypesById.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		nui.alert(text.msg);
	        	} else {
	        		items = text.items;
	        		showSunScan(cj,vflag,items);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
}

/**
*生成影像业务信息树，以及目录树
*imageIP：影像存储IP地址
*imagePort:端口
*docId：批次号
*docName：批次名称
*tranDate：业务日期
*vflag：操作类型 0-新增影像操作 1-修改影像操作（控件会先查询） 2-只读查询影像操作 3-删除批次操作。
*items：目录树集合
**/	
function showSunScan(json,vflag,items){
	//objpart='"+json.objpart+"'
 	var tradeinfo ="<?xml version='1.0' encoding='GB2312'?>"
					+"<root>"
					+"<business appid='"+json.appid+"' tradecode='"+json.tradecode+"' optiontype='"+vflag+"' tradedate='"+json.tranDate+"' tradeuser='"+json.tradeuser+"' objname='"+json.objname+"' user='"+json.user+"' pwd='"+json.pwd+"'"
					+" objtoken='' isenc='0' organ='' ip='"+json.ip+"' port='"+json.port+"' dsname=''>"
					+"<node theme='0001'>"
					+"<bsinf attr='BUSI_SERIAL_NO' disp='"+json.docName+"' isquery='1'>"+json.docId+"</bsinf>"
					+"</node></business></root>";
					
	var tree = "<?xml version='1.0' encoding='GB2312'?>"
				+"<root>"
				+"<tree appid='"+json.appid+"' tradecode='"+json.tradecode+"' theme='0001'>"
				+"<node name='BUSI_SERIAL_NO' desc='"+json.docName+"' isimage='1' barcode='' forms='11011' attri='0' isauth='11111' issuc='0'>";
				//拼接子节点
				if(null != items && items.length>0){
				
					for(var i=0;i<items.length;i++){
						var item = items[i];
						tree= tree+"<node name='"+item.imageDocumentTypeId+"' desc='"+item.imageDocumentName+"' isimage='2' barcode='' forms='' attri='1' isauth='11111' issuc='0'/>"
					}
					//使终拼接老数据节点
					//tree= tree+"<node name='888888' desc='老数据' isimage='2' barcode='' forms='' attri='1' isauth='11111' issuc='0'/>"
				}
				tree=tree+"</node></tree></root>";	
/* 
	SunScan.ShowSunScan(tradeinfo,tree); */ 
	
	var TRADEXML = "<?xml version='1.0' encoding='GB2312'?>" +
									"<root>" +
											"<business appid='"+json.appid+"' tradecode='"+json.tradecode+"'  optiontype='"+vflag+"' " +
											"tradedate='"+json.tranDate+"' tradeuser='"+json.tradeuser+"' " +
										"objname='"+json.objname+"' user='"+json.user+"' pwd='"+json.pwd+"' " +
										"objtoken='' isenc='0' organ='' " +
											"ip='"+json.ip+"' port='"+json.port+"' dsname=''>" + 
											"<node theme='0001'>" +
												"<bsinf attr='BUSI_SERIAL_NO' disp='批次号' isquery='1'></bsinf>" +
												//"<bsinf attr='CONTENT_ID' disp='批次号' isquery='1'>"+json.docId+"</bsinf>" +
												//"<bsinf attr='BATCH_ID' disp='批次号' isquery='1'>2016090714E50655-548C-371E-0226-23D5A1FBD5D3-9</bsinf>" +												 
												"<bsinf attr='CREATEDATE' disp='业务开始日期' isquery='1'>"+json.tranDatee+"</bsinf>" +
											"</node>" +
											"</business>" +
										"</root>";
			var TRADEXMLDOC = "<?xml version='1.0' encoding='GB2312'?>" +
									"<root>" +
											"<business appid='"+json.appid+"' tradecode='"+json.tradecode+"'  optiontype='"+vflag+"' " +
											"tradedate='"+json.tranDate+"' tradeuser='"+json.tradeuser+"' " +
										"objname='"+json.objname+"' user='"+json.user+"' pwd='"+json.pwd+"' " +
										"objtoken='' isenc='0' organ='' " +
											"ip='"+json.ip+"' port='"+json.port+"' dsname=''>" + 
											"<node theme='0001'>" +
												"<bsinf attr='CONTENT_ID' disp='批次号' isquery='1'>"+json.docId+"</bsinf>" +
												//"<bsinf attr='BATCH_ID' disp='批次号' isquery='1'>2016090714E50655-548C-371E-0226-23D5A1FBD5D3-9</bsinf>" +												 
												"<bsinf attr='CREATEDATE' disp='业务开始日期' isquery='1'>"+json.tranDatee+"</bsinf>" +
											"</node>" +
											"</business>" +
										"</root>";
			var TRADEXMLCX = "<?xml version='1.0' encoding='GB2312'?>" +
									"<root>" +
											"<business appid='"+json.appid+"' tradecode='"+json.tradecode+"'  optiontype='"+vflag+"' " +
											"tradedate='"+json.tranDate+"' tradeuser='"+json.tradeuser+"' " +
										"objname='"+json.objname+"' user='"+json.user+"' pwd='"+json.pwd+"' " +
										"objtoken='' isenc='0' organ='' " +
											"ip='"+json.ip+"' port='"+json.port+"' dsname=''>" + 
											"<node theme='0001'>" +
												"<bsinf attr='CONTENT_ID' disp='批次号' isquery='1'>"+json.docId+"</bsinf>" +
												//"<bsinf attr='BATCH_ID' disp='批次号' isquery='1'>2016090714E50655-548C-371E-0226-23D5A1FBD5D3-9</bsinf>" +												 
												"<bsinf attr='CREATEDATE' disp='业务开始日期' isquery='1'>"+json.tranDatee+"</bsinf>" +
											"</node>" +
											"</business>" +
										"</root>";
	var TREEXML = "<?xml version='1.0' encoding='GB2312'?>" +
								"<root>" +
									"<tree appid='Y002' tradecode='JKDS' theme='0001'>" +
										"<node name='IMAGE' desc='影像' isimage='2' barcode='' forms='' attri='0' isauth='11111' issuc='0'>" ; 
//											"<node name='K001' desc='机构信用代码证' isimage='2' barcode='' forms='0021000001005000' attri='1' isauth='11111' issuc='0'/>" +
//											"<node name='K002' desc='基本户开户许可证' isimage='2' barcode='' forms='0021000001002000' attri='1' isauth='11111' issuc='0'/>" +
//											"<node name='K003' desc='外商投资批准证书' isimage='2' barcode='' forms='0021000001003000' attri='1' isauth='11111' issuc='0'/>" +
//											"<node name='K004' desc='营业执照' isimage='2' barcode='' forms='0021000001006000' attri='1' isauth='11111' issuc='0'/>" +
//											"<node name='OTHER' desc='未分类节点' isimage='2' barcode='' forms='' attri='1' isauth='11111' issuc='0'/>" +
							 			//拼接子节点
											if(null != items && items.length>0){
											
												for(var i=0;i<items.length;i++){
													var item = items[i];
													TREEXML= TREEXML+"<node name='"+item.imageDocumentTypeId+"' desc='"+item.imageDocumentName+"' isimage='2' barcode='' forms='' attri='1' isauth='11111' issuc='0'/>"
												}
												//使终拼接老数据节点
												//tree= tree+"<node name='888888' desc='老数据' isimage='2' barcode='' forms='' attri='1' isauth='11111' issuc='0'/>"
											}
									TREEXML=TREEXML+"</node></tree></root>";
									debugger;
	var doc=json.docId;
	if(vflag=='0'){
	SunScan.ShowSunScan(TRADEXML, TREEXML);
	}else{
	SunScan.ShowSunScan(TRADEXMLDOC, TREEXML);
	} 
}

//获取影像个数
function getImageNum(docId,imageType){

	var xml = "<?xml version='1.0' encoding='GB2312' ?>"+
		"<root>"+
			"<head>"+
				"<transcode>td0041</transcode>"+
			"</head>"+
			"<body>"+
				"<param>"+docId+"</param>"+
				"<param>"+imageType+"</param>"+
				"<param></param>"+
			"</body>"+
		"</root>";
				debugger;
	var retXml = SunScan.CommOcxFunction(xml);
	retXml = retXml.substr(retXml.indexOf("item")+5,1);
	return retXml;

}

//获取批次号
function returnTrade(){

var xml ="<?xml version='1.0' encoding='GB2312'?>"+ 
	"<root>"+
		"<head>"+
			"<transcode>td0021</transcode>"+ 
		"</head>"+
		"<body>"+
		"</body>"+
	"</root>";
	
	var rtxml = SunScan.CommOcxFunction(xml);
	rtxml =rtxml.substring(rtxml.indexOf("batchid")+9,rtxml.indexOf("createdate")-2);
	return rtxml
	
}

var imageJson={};

//提交影像
function Submit()
{
	var xml ="<?xml version='1.0' encoding='GB2312'?>"+
			"<root>"+
				"<head>"+
					"<transcode>td0003</transcode>"+
				"</head>"+
				"<body>" +
				"</body>"+
			"</root>";
	var rtxml = SunScan.CommOcxFunction(xml);
		debugger;
	var retcode = rtxml.substr(rtxml.indexOf("respcode")+9,1);
	var batchIds =rtxml.substring(rtxml.indexOf("batchid"));
    var batchId =batchIds.substring(9,batchIds.indexOf("respinfo")-2);
	//获取批次号
	//var batchId = returnTrade();
	//获取影像张数
	var images={};
	if(null !=items){
		for(var i=0;i<items.length;i++){
			var item = items[i];
			var num =getImageNum("1",item.imageDocumentTypeId);
			images["n"+item.imageDocumentTypeId]=num;
		}
	
	}
	imageJson={"retcode":retcode,"batchId":batchId,"images":images};
	CloseWindow("submit");
}

function getData(){

	//var json={"n1050111":1,"n1050112":2};
	return imageJson;
}

</SCRIPT>
</head>
<body>
<OBJECT ID=SunScan codeBase=SunScan.cab WIDTH=100% HEIGHT=94%
     CLASSID="CLSID:da9a1a4f-435c-4bcf-836a-f354af7dfb36">
 </OBJECT>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
 <a id="sumbitBtn" class="nui-button"  onclick="Submit()">提交</a>
 <a class="nui-button"  onclick="CloseWindow('cancel')">关闭</a>
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 <a class="nui-button"  onclick="hideControl(1)">上一张</a>
 <a class="nui-button"  onclick="hideControl(2)">下一张</a>
<!--  <a class="nui-button"  onclick="modifyName()">影片名称修改</a> -->
</div>
 <SCRIPT LANGUAGE="JavaScript" FOR="window" EVENT="onLoad()">
 	   	nui.parse();
      	SunScan.CreateSunScan(nui.getDictText("XD_IMCD0001","appid"), nui.getDictText("XD_IMCD0001","tradecode"));
       	
       	var cj={};
       	 cj["ip"]=nui.getDictText("XD_IMCD0001","ip");
       	cj["port"]=nui.getDictText("XD_IMCD0001","port");
 
/*  		cj["ip"] = "172.16.221.90";
 		cj["port"] = "8023"; */
       	cj["appid"]=nui.getDictText("XD_IMCD0001","appid");
       	cj["tradecode"]=nui.getDictText("XD_IMCD0001","tradecode");
       	cj["tradeuser"]=nui.getDictText("XD_IMCD0001","tradeuser");
       	cj["user"]=nui.getDictText("XD_IMCD0001","user");
       	cj["pwd"]=nui.getDictText("XD_IMCD0001","pwd");
		//var imageIP = "172.20.12.54";//nui.getDictText("TASKCENTER_SYSPARAMS","IMAGE_IP");
		//var imagePort ="8021";// nui.getDictText("TASKCENTER_SYSPARAMS","IMAGE_PORT");
		//获取父窗口的列表树信息
			var superiorId = "<%=request.getParameter("serialNo")%>";
			var barCode = "<%=request.getParameter("barCode")%>";
			var createTime = "<%=request.getParameter("createTime")%>";
			debugger;
		var data = window.Owner.form.getData();
		//批次号
		var operType="1";
		//var docId =data.item.barCode;
		if("undefined"==barCode){
		barCode="";
		}
		var docId =barCode;
		if(null == docId || ''==docId){
		
			operType="0";
		}
		//批次名称
		var docName = "<%=request.getParameter("imageTypeName") %>";
		//交换日期
		//var curdate = data.item.tranDate;
		var curdate = createTime;
		if("undefined"==curdate){
			curdate = '<%=GitUtil.getBusiDateStr()%>';
		}
		var tranDate =curdate.replace(/-/g,"");// data.returnMessage.BODY.TranDate;
 		var tranDatees=tranDate.substring(0,8); 
<%-- 	    var tranDatee='<%=DateUtil.DateToString(Calendar.getInstance().getTime(), "yyyyMMdd") %>'; --%>
         var tranDatee='<%=GitUtil.getBusiDateYYYYMMDD()%>';

		//影像存储部件名称
		var objname ="XYZ";//data.item.imageControlType;XYZ
		var objpart = "";//objname.split("_")[0]+"_PART";
 		//赋给json,统一传值
		cj["docId"]=docId;
		cj["docName"]=docName;
		cj["tranDate"]=tranDate;
		cj["objname"]=objname;
		cj["objpart"]=objpart;
		cj["tranDatee"]=tranDatees;
		//获取迁移标志
		var ismove = "0";//data.item.ismove;
		//获取业务流水号
		var businessNumber = data.item.businessNumber;
		data.item.imageTypeId=superiorId;
		cj["businessNumber"]=businessNumber;
		//扫描、查看标识
		var view ="<%=request.getParameter("view") %>";
	   	if(view =='0'){
	   		settoolbarAll();//设置工具栏
	   		//增加批次
			addRemoteAuth(cj,operType,data);
			//导入旧影像资料
			if('0'==operType && '1'==ismove){//新增批次，并且是迁移数据
			
				importOldImage(businessNumber);
			}
			setControlParam();//设置控件参数，默认显示第一张缩略图
	   	}else{
	   		nui.get("sumbitBtn").hide();//隐藏提交按钮
	   		setControlParam();//设置控件参数，默认显示第一张缩略图
	   		settoolbar();//设置工具栏
	    	addRemoteAuth(cj,2,data);//加载树
	   	}
	   	
	  	//hideControl(15);//隐藏树节点窗口
	  	hideControl(16);//隐藏批注窗口
  </SCRIPT>
</body>
</html>

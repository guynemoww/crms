//isSmall为true时表示右方的小格子，此时width属性可以不需要输入
//isRequired为true时表示无论是否配置，都会显示
//isDefault为true时表示未配置时默认要显示的界面
//小窗口宽度49.7%正好
//大窗口宽度99.5%
/*var portalsForPower=[
	{id:'info_workList', text:'待办统计', url:'/mainframe/menubar/View3.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
	{id:'info_notice', text:'公告栏', url:'/mainframe/menubar/View1.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
	{id:'info_market', text:'市场信息', url:'/mainframe/menubar/View2.jsp', width: '99.5%',height: '33%' ,isSmall: true, isRequired: true},
	{id:'basicRate_list', text:'利率信息', url:'/mainframe/menubar/basicRate_list_view.jsp', width: '99.5%', height: '250', isSmall: false, isDefault: true},
	{id:'exchangeRate_list', text:'汇率信息', url:'/mainframe/menubar/exchangeRateView.jsp', width: '49.7%', height: '250', isSmall: false, isDefault: true},
	
	{id:'loanAfterList', text:'信息提醒', url:'/mainframe/menubar/loanAfterListView.jsp', width: '99.5%', height:'250',isDefault: true, isRequired: true},
	{id:'mycust_corporation', text:'单一客户', url:'/csm/workdesk/mycust_corporation.jsp', width: '99.5%', height: '50%', isSmall: false, isDefault: false, isRequired: false},
	{id:'test_img',text:'柱状图',url:'/mainframe/menubar/testImg.jsp',width:'49.7%',height:'250', isSmall: false, isDefault: true, isRequired: true}
];*/
//放款P1035，贷后P1038，授信P1016，客户经理P1001


//修改首页，根据岗位配置获得Portals（待办默认一直存在）
var portals =[
	{id:'info_notice', text:'公告栏', url:'/mainframe/menubar/View1.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
	{id:'info_workList', text:'待办统计', url:'/mainframe/menubar/View3.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
	{id:'info_market', text:'市场信息', url:'/mainframe/menubar/View2.jsp', width: '99.5%',height: '33%' ,isSmall: true, isRequired: true},
	{id:'info_workCount', text:'待办任务', url:'/mainframe/menubar/workingListView.jsp', width: '99.5%', height: '250', isSmall: false, isDefault: true, isRequired: true}
];

var FK = [
	{id:'exchangeRate_list', text:'汇率信息', url:'/mainframe/menubar/exchangeRateView.jsp', width: '49.7%', height: '250', isSmall: false, isDefault: true,isRequired: true},
	{id:'basicRate_list', text:'利率信息', url:'/mainframe/menubar/basicRate_list_view.jsp', width: '49.7%', height: '250', isSmall: false, isDefault: true,isRequired: true}
];

var DH = [
	{id:'loanAfter_list1', text:'贷款到期提醒', url:'/pub/loanAfter/loanAfterList.jsp?infoTypeCd=04', width: '99.5%', height: '250', isSmall: false, isDefault: true,isRequired: true},
	{id:'loanAfter_list2', text:'贷款逾期提醒', url:'/pub/loanAfter/loanAfterList.jsp?infoTypeCd=05', width: '99.5%', height: '250', isSmall: false, isDefault: true,isRequired: true}
];

/*var SX = [
	{id:'exchangeRate_list', text:'授信汇率信息', url:'/mainframe/menubar/exchangeRateView.jsp', width: '49.7%', height: '250', isSmall: false, isDefault: true,isRequired: true},
	{id:'basicRate_list', text:'授信利率信息', url:'/mainframe/menubar/basicRate_list_view.jsp', width: '49.7%', height: '250', isSmall: false, isDefault: true,isRequired: true}
]*/

//客户经理首页
var KHJL = [
	{id:'loanAfter_list1', text:'提示列表', url:'/pub/remind/remind_total.jsp', width: '99.5%', height: '250', isSmall: false, isDefault: true,isRequired: true}
	//{id:'exchangeRate_list', text:'还款计划', url:'/mainframe/menubar/payoutidinfo_list.jsp', width: '99.5%', height: '250', isSmall: false, isDefault: true,isRequired: true}
];






//行长需要特殊处理
var GovernorFlag = "false";//默认非行长
/*
	行长配置
	coords  	鼠标区域范围映射坐标
	linkUrl 	功能路径
	text    	标题
*/  
var GovernorPortals = [
	{id:"QueryCust",coords:"131,71,305,115",linkUrl:"/pub/standingBook/partyinfo_list.jsp",text:"客户查询"},
	{id:"QueryApproval",coords:"64,151,240,194",linkUrl:"/biz/biz_account_inquiry/biz_inApprove_query.jsp"},
	{id:"QueryAfterLoan",coords:"36,237,211,284",linkUrl:"/aft/file/aft_singleCsm_list.jsp",text:"贷后查询"},
	{id:"QueryReport",coords:"26,323,209,369",linkUrl:"/pub/detailList/rptlist.jsp",text:"报表查询"}
	
];

var index_bg_src = "/mainframe/images/bg_index2.jpg";

var All = {"P1035":FK,"P1038":DH,"P1001":KHJL,"P1501":KHJL,"P1401":KHJL};


//初始化配置文件，首页（index2.jsp）调用
function initIndex(){
	$.ajax({
            url: "com.bos.pub.standingbook.operconfig.getPosition.biz.ext",
            type: 'POST',
            data: null,
            async:false,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
            	if(text.positionMap){
            		console.log(nui.encode((text.positionMap)["org"+text.userOrgId]));
            		initPortals(text.positionMap["org"+text.userOrgId]);
            	}else{
            		console.log("暂无岗位");
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
}  

//根据岗位初始化Portals
function initPortals(powerList){
	var pl = "P1038,P1035,P1001,P1084,P1501,P1401";
	
	//判断岗位
		for(var key in powerList){
			if(pl.indexOf(key)>=0){
				if(key=="P1084"){//行长的特殊处理
					/*
						右侧小窗口配置保留
					*/
					portals =[
						{id:'info_notice', text:'公告栏', url:'/mainframe/menubar/View1.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
						{id:'info_workList', text:'待办统计', url:'/mainframe/menubar/View3.jsp', width: '99.5%', height: '33%', isSmall: true, isRequired: true},
						{id:'info_market', text:'市场信息', url:'/mainframe/menubar/View2.jsp', width: '99.5%',height: '33%' ,isSmall: true, isRequired: true}
					];
					GovernorFlag = "true";//行长标识
					console.log("行长登陆");
					return;
				}
				for(var j = 0;j<All[key].length;j++){
					var element = All[key][j];
					var addFlag;
					//判断portals中是否存在
					for(var k = 0;k<portals.length;k++){
						addFlag = false;
						if(portals[k].id==element.id){
							addFlag == true;
						}
					}
					if(addFlag!=true){
						portals[portals.length] = element;
					}
				}
		 	}
		}
		//console.log(nui.encode(portals));
}








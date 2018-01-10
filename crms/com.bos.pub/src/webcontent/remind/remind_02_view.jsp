<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2014-08-25 14:12:10
  - Description:贷后日常检查
-->
<head>
<title>日常检查提示</title>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>

</head>
<body>
<%
IUserObject user = CommonUtil.getIUserObject();
String orgdegree =(String)user.getAttributes().get("orgdegree");
 %>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />
		
		<label>提示日期：</label>
		<div>
			<input id="item.stDate" name="item.stDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" /> -
			<input id="item.enDate" name="item.enDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
		</div>
		<label>处理状态：</label>
		<div>
			<input name="item.remindStatus" id="item.remindStatus" class="nui-dictcombobox nui-form-input" dictTypeId="YP_YJCD0002"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.getRemindInfoByType.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true"  >客户名称</div>
		<div field="CRD_LIMIT" headerAlign="center" allowSort="true" dataType="currency">授信额度</div>
		<div field="CRD_BALANCE" headerAlign="center" allowSort="true" dataType="currency">授信余额</div>
		<div field="LAST_TIME_CHECKDATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">上次检查日期</div>
		<div field="END_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >本次检查日期</div>
		<div field="TO_END_DAYS" headerAlign="center" allowSort="true"  >距离检查到期日（天）</div>		
		<div field="op" headerAlign="center" allowSort="true">操作</div>
		<div field="TEAM_USER_NUM" headerAlign="center" visible="false" allowSort="true">管户经理</div>
	</div>
</div> 		
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	var remindType="<%=request.getParameter("remindType") %>";
	var exsql = "com.bos.pub.remind.select_"+remindType+"_id";
	//初始化提醒状态
	var arr = git.getDictDataFilter("YP_YJCD0002","01,02,03");
	nui.get("item.remindStatus").setData(arr);
	nui.get("item.remindStatus").setValue("01");
	//获取当前登陆人员的id
	var userId ="<%=((UserObject)session.getAttribute("userObject")).getUserId() %>";
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
	   			e.data[i]['TO_END_DAYS'] = getDiffDays(e.data[i]['END_DATE']);
				if(e.data[i]['TEAM_USER_NUM'] == userId){
					//如果管户经理是当前登录人 则可以操作
					e.data[i]['op']='<a href="#" onclick="toGo(\''+e.data[i].PARTY_ID+'\');">跳转至日常检查</a>';
				}
       		}
    });
	function query(){
		var o = form.getData(); //获取表单多个控件的数据
		o.remindType = remindType;
		grid.load(o);
	}
	query();
    function reset(){
		form.reset();
	}
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd;
            
            
             nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
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
            
	}
	function toGo(partyId){
	
		var url = "/aft/normalCheck/normalCheck_enter.jsp?partyId="+partyId;
	
		if('2'=='<%=orgdegree%>'){
			url="/aft/normalCheck/normalCheck_small_enter.jsp?partyId="+partyId;
		}
		 nui.open({
            url: nui.context+url,
            title: "日常检查", 
            width: 1024,
    		height: 768,
    		state:"max",
            allowResize:true,
    		showMaxButton: true,
            ondestroy: function (action) {
            }
        });
	}
	function toDo(remindId,status){
		
		//更新提示状态
		var json = nui.encode({"remindId":remindId,"remindStatus":status});
		nui.ajax({
	        url: "com.bos.pub.Remind.updateRemindInfoStatus.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	               grid.reload();
	            }
	        }
	    });
	}
	//计算距离到期天数
	function getDiffDays(endDate){
		var result;
		//更新提示状态
		var json = nui.encode({"endDate":endDate});
		nui.ajax({
	        url: "com.bos.pub.Remind.getDiffDays.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	result = text.days;
	        }
	    });
		return result;
	}
</script>
</body>
</html>
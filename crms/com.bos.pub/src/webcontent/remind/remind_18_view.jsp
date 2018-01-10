<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<html>
<!-- 
  - Author(s): zhangfahui
  - Date: 2015-12-30 14:12:10
  - Description:保险到期提示
-->
<head>
<title>保险到期提示</title>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
 %>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>提示日期：</label>
		<div>
			<input id="item.apply_date" name="item.stDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />-
			<input id="item.enDate" name="item.enDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
		</div>
		<label>处理状态：</label>
		<div>
			<input name="item.stsCd" id="item.stsCd" class="nui-dictcombobox nui-form-input" dictTypeId="YP_YJCD0002"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.getYpWarning.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<%--<div type="checkcolumn" >选择</div>--%>
		<div type="indexcolumn">序号</div>
		<div field="INSUR_NO" headerAlign="center"  allowSort="true"  >保单号</div>
		<div field="INSUR_NAME" headerAlign="center"  allowSort="true" >保险公司名称</div>
		<div field="INSUR_AMT" headerAlign="center"  allowSort="true" >保险金额</div>
		<div field="INSUR_END_DT" headerAlign="center"  allowSort="true"  dateFormat="yyyy-MM-dd" >保险到期日期</div>
		<div field="OPR_USER_NAME" headerAlign="center"  allowSort="true" >经办人</div>
		<div field="op" headerAlign="center" allowSort="true">操作</div>
		<div field="TEAM_USER_NUM" headerAlign="center" visible="false" allowSort="true">管户经理</div>
		
	</div>
</div> 		
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	var remindType="<%=request.getParameter("remindType") %>";
	//初始化提醒状态
	var arr = git.getDictDataFilter("YP_YJCD0002","01,02");
	nui.get("item.stsCd").setData(arr);
	nui.get("item.stsCd").setValue("01");
	//获取当前登陆人员的id
	var userId ="<%=((UserObject)session.getAttribute("userObject")).getUserId() %>";
	
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
  			for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
  				if(e.data[i]['TEAM_USER_NUM'] == userId){
					//如果管户经理是当前登录人 则可以操作
					e.data[i]['op']='<a href="#" onclick="toDo();">操作</a>';	
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
	function toDo(){
		
		var rows = grid.getSelecteds();
		    var row = grid.getSelected();
		    if(row){
			    var cltNo = row.SURETY_NO;
				var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.insurance.BCinsuranceMaintainForWarn.flow?creditFlag=1&cltNo="+cltNo+"&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>";
				window.open(url);
				return;
		    }
		
	}
</script>
</body>
</html>
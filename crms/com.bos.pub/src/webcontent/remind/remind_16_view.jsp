'<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<html>
<!-- 
  - Author(s): zhangfahui
  - Date: 2015-12-30 14:12:10
  - Description:微信银行贷款申请业务提示
-->
<head>
<title>微信贷款申请提示</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<%
IUserObject user = CommonUtil.getIUserObject();
String orgdegree =(String)user.getAttributes().get("orgdegree");
 %>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>申请机构：</label>
		<input id="item.applyOrg" name="item.applyOrg" class="nui-textbox nui-form-input" />
		
		<label>提示日期：</label>
		<div>
			<input id="item.apply_date" name="item.stDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />-
			<input id="item.enDate" name="item.enDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
		</div>
		<label>处理状态：</label>
		<div>
			<input name="item.operateStatus" id="item.operateStatus" class="nui-dictcombobox nui-form-input" dictTypeId="YP_YJCD0002"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.getWxLoanApplyInfo.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<%--<div type="checkcolumn" >选择</div>--%>
		<div type="indexcolumn">序号</div>
		<div field="APPLY_ORG" headerAlign="center" width="6%" allowSort="true"  >申请机构</div>
		<div field="CUST_NAME" headerAlign="center" width="6%" allowSort="true" >客户名称</div>
		<div field="SEXUAL" headerAlign="center" width="3%" allowSort="true"  dictTypeId="CDKH0048">性别</div>
		<div field="ID_CARD" headerAlign="center" width="10%" allowSort="true" >身份证号</div>
		<div field="MOBILE" headerAlign="center" width="6%" allowSort="true" >手机号码</div>
		<div field="ADDRESS" headerAlign="center" allowSort="true"  >地址</div>
		<div field="LOAN_AMT" headerAlign="center" width="6%" allowSort="true" dataType="currency">贷款金额</div>
		<div field="APPLY_DATE" headerAlign="center" width="6%" allowSort="true" dateFormat="yyyy-MM-dd">申请日期</div>
		<div field="LOAN_TERM" headerAlign="center" width="6%" allowSort="true" >贷款期限（月）</div>
		<div field="LOAN_USE" headerAlign="center" width="6%" allowSort="true">贷款用途</div>
		<div field="LOAN_TYPE" headerAlign="center" allowSort="true">贷款类型</div>
		<div field="ORG_NAME" headerAlign="center" allowSort="true">机构名称</div>
		<div field="ORG_ADDR" headerAlign="center" allowSort="true">机构地址</div>
		<div field="ORG_TEL" headerAlign="center" allowSort="true">机构电话</div>
		<div field="op" headerAlign="center" allowSort="true">操作</div>
	</div>
</div> 		
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	var remindType="<%=request.getParameter("remindType") %>";
	//初始化提醒状态
	var arr = git.getDictDataFilter("YP_YJCD0002","01,02,03");
	nui.get("item.operateStatus").setData(arr);
	nui.get("item.operateStatus").setValue("01");
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			var status = e.data[i]['OPERATE_STATUS'];
	   			if('02'==status){
	   				e.data[i]['op']='<a href="#" onclick="toDo(\''+e.data[i].WX_ID+'\',\'01\');">转未处理</a>';	
	   			}else{
	       			e.data[i]['op']=' <a href="#" onclick="toDo(\''+e.data[i].WX_ID+'\',\'02\');">已处理</a>';	
	   			}
       		}
    });
	function query(){
		var o = form.getData(); //获取表单多个控件的数据
		grid.load(o);
	}
	query();
    function reset(){
		form.reset();
	}
	function toDo(wxId,status){
		
		//更新提示状态
		var json = nui.encode({"wxId":wxId,"operateStatus":status});
		nui.ajax({
	        url: "com.bos.pub.Remind.UpdateWxLoanApplyStatus.biz.ext",
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
</script>
</body>
</html>
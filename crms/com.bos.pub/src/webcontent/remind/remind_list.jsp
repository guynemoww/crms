<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): ljf
  - Date: 2015-05-26
  - Description:提示列表维护
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="item.remindType" name="item.remindType"  class="nui-hidden"/>
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />
		
		<%--<label>提示信息状态：</label>
		<input id="item.remindStatus" name="item.remindStatus"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_DHCD0014"  />
		--%>
		<label>提示日期：</label>
		<div>
			<input id="item.stDate" name="item.stDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />-
			<input id="item.enDate" name="item.enDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button"  iconCls="icon-edit" onclick="view()">查看</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.queryRemindInfo.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" onrowdblclick="view()"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<%--<div field="partyNumstr" headerAlign="center" allowSort="true" >客户编号</div>--%>
		<div field="partyName" headerAlign="center" allowSort="true"  >客户名称</div>
		<div field="remindType" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0015">提示类型代码</div>
		<div field="createDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd HH:mm:ss" >提示日期</div>
		<div field="remindStatus" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0014">提示信息状态</div>
	</div>
</div> 
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var remindType = '<%=request.getParameter("remindType") %>';
	nui.get("item.remindType").setValue(remindType);
	function search() {
		var o = form.getData(); //获取表单多个控件的数据
		grid.load(o);
		<%--grid.on("preload",function(e){//客户信息链接
	   		if (!e.data || e.data.length < 1)
	   			return;
	   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	   			e.data[i]['partyNumstr']='<a href="#" onclick="clickCust(\''
	   				+ e.data[i].partyId+','+e.data[i].partyTypeCd
	   				+ '\');return false;" value="'
	   				+ e.data[i].partyId
	   				+ '">'+e.data[i]['partyNum'] +'</a>';
	   		}
	    });--%>
    }
    search();
	
    
    function clickCust(partyId,partyTypeCd){//客户信息链接
    
    	var url = "/csm/corporation/csm_corporation_tree.jsp";
    	//对公客户
    	if('01' == partyTypeCd){
    	
    	
    	}else{
    	
    	}
    
	    var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyTypeCd="+partyTypeCd;
        git.go(infourl,parent);
	}
    function reset(){
		form.reset();
	}
	
	function view(){
		var row=grid.getSelected();
		if(row){
		
			//更新提示状态
			var json = nui.encode({"remindId":row.remindId});
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
		               openRemindView(row);
		            }
		        }
		    });
		}else{
			alert("请选中一条记录");
		}
	}
	function openRemindView(row){
		//路径前部分，拼接上提示类型，再接_view.jsp
		var remindType= row.remindType;
		var url = "pub/remind/remind_"+remindType+"_view.jsp";
		nui.open({
                url: url+"?remindId="+row.remindId+"&remindType="+remindType,
                title: "", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                   
                },
                ondestroy: function (action) {
                	search();
                }
            });
	}
	
</script>
</body>
</html>

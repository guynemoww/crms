<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-08-25 14:12:10
  - Description:预警信号提示
-->
<head>
<title>预警信号提示</title>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />
		
		<label>提示日期：</label>
		<div>
			<input id="item.stDate" name="item.stDate"  
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />-
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
		<div field="TEAM_ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
		<div field="WARNING_LEVEL" headerAlign="center" allowSort="true"  dictTypeId="XD_YJJB0001">预警级别</div>
		<div field="COGNIZANCE_TIME" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">认定时间</div>
		<div field="TEAM_USER_NUM" headerAlign="center" allowSort="true" dictTypeId="user" >经办人</div>
		<div field="REMARK" headerAlign="center" >备注</div>
		<div field="op" headerAlign="center" allowSort="true" dictTypeId="user" >操作</div>
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
	   			var status = e.data[i]['REMIND_STATUS'];
	   			if(e.data[i]['REMARK']){
	   				e.data[i]['REMARK']='<font color=red >'+e.data[i]['REMARK']+'</font>';
	   			}
	   			
	   			if(e.data[i]['TEAM_USER_NUM'] == userId){
					//如果管户经理是当前登录人 则可以操作
					if('01'==status){
	   				e.data[i]['op']=
	   				'<a href="#" onclick="toDetail(\''+e.data[i].PARTY_ID+'\');">预警详情</a> '+
	   				'<a href="#" onclick="toHandle(\''+e.data[i].PARTY_ID+'\',\''+e.data[i].REMIND_ID+'\',\'02\');">预警确认</a>';	
		   			}else if('02'==status){
		   				e.data[i]['op']=
		   				'<a href="#" onclick="toDetail(\''+e.data[i].PARTY_ID+'\');">预警详情</a> '+
		   				'<a href="#" onclick="toDo(\''+e.data[i].REMIND_ID+'\',\'01\');">转未处理</a>';	
		   			}else{
		   				e.data[i]['op']='<a href="#" onclick="toDetail(\''+e.data[i].PARTY_ID+'\');">预警详情</a> ';
		   			}
				}else{
					e.data[i]['op']='<a href="#" onclick="toDetail(\''+e.data[i].PARTY_ID+'\');">预警详情</a> ';
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
	//查看预警详情
	function toDetail(partyId){
	
		 nui.open({
	            url:nui.context + "/pub/remind/remind_06_detail.jsp?partyId="+partyId,
	            showMaxButton: true,
	            title: "",
	            width: 800,
	            height: 400,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
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
	function toHandle(partyId,remindId,status){
		//更新提示状态
		//var json = nui.encode({"remindId":remindId,"remindStatus":status});
		//nui.ajax({
	        //url: "com.bos.pub.Remind.updateRemindInfoStatus.biz.ext",
	        //type: 'POST',
	        //data: json,
	        //cache: false,
		    //async:false,        
	        //contentType:'text/json',
	        //success: function (text) {
	        	//if(text.msg){
	        		//alert(text.msg);
	        	//} else {
	               //grid.reload();
	           // }
	        //}
	    //});
	  var bizId;
	  var json = nui.encode({partyId:partyId});                                   //赋值参与人ID
	  $.ajax({      
            url: "com.bos.ews.commonUtil.checkWarnAdjust.biz.ext",           //该逻辑流用于查询是否存在未用的业务ID，如果不存在则新建ID用于后续操作中作为业务ID使用
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            		if(text.flag){
            			return nui.alert(text.flag);
            			git.unmask();
            		}else {
            			  bizId = text.bizId; 
                          var node=text.node;
                          var json1 = nui.encode({partyId:partyId,bizId:bizId,remindId:remindId,remindStatus:status});  
            			  $.ajax({      
            			  		url: "com.bos.pub.Remind.updateRemind06Detail.biz.ext",           //该逻辑流用于查询是否存在未用的业务ID，如果不存在则新建ID用于后续操作中作为业务ID使用
           						type: 'POST',
            					data: json1,
            					cache: false,
            					contentType:'text/json',
            			  		success: function(){
            			  			if(text.msg){
            			  				alert(text.msg);
            			  			}else{
            			  				var url=nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree_add.jsp?bizId="+bizId+"&corpid="+partyId+"&node="+nui.encode(node)+"&processInstId="+text.processInstId+"&proFlag=1";
                         			 	git.go(url);
            			  			}
            			  		},
            			  		error: function (jqXHR, textStatus, errorThrown) {
                					nui.alert(jqXHR.responseText);
		            			}	
            			  
            			  });
            		}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
		            }
			});
	}
</script>
</body>
</html>
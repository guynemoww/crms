<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>

<%@page import="commonj.sdo.DataObject"%>
<%@page import="java.util.Map"%>
<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-29 16:35:40
  - Description:
-->
<head>
<title>代理新增</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<%		
		IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
		String username=user.getUserName(); 
		boolean rolebool=false;
		Map<String,Object> map=user.getAttributes();
		String orgcode=(String)map.get("orgcode");
		DataObject [] obj=(DataObject [])map.get("roles");
		for(int i=0;i<obj.length;i++){
			if(obj[i].getString("roleid").equals("eosadmin")){
				rolebool=true;
			}
		}
%>
<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
	
	<input name="agent.agenposition" id="agenposition"  class="nui-hidden"  />
	<input id="agentfromid"  class="nui-hidden"  />
	<input name="agent.agentitem" enabled="false" id="agentitem" required="false" class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">
		<label>委托人：</label>
		<input name="agent.agentfrom" id="agentfrom" required="true"  onbuttonclick="selectEntrust" class="nui-buttonedit nui-form-input"  />

		<label>代理类型：</label>
		<input name="agent.agenttype" required="true"   data="[{'id':'ALL','text':'完全代理'}]" class="nui-dictcombobox nui-form-input" />

		<label>代理人：</label>
		<input name="agent.agentto" id="agentto" onbuttonclick="selectAgent" required="true" class="nui-buttonedit nui-form-input"  />
		
		<label>生效时间：</label>
		<input name="agent.starttime" id="starttime" required="true"  class="nui-datepicker" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true" />

		<label>增加例外流程：</label>
		<input onclick="selectProcess"  required="false" class="nui-button" text="点击选择例外流程"/>
		
		<label>终止时间：</label>
		<input name="agent.endtime" id="endtime" required="true" class="nui-datepicker" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"/>
		<label>显示例外流程名：</label>
       	<div id="grid2" class="nui-datagrid" 
     	  style="width:80%;height:245px;" 
      	  dataField="items"
			showPageSize="false" showPageInfo="false" pageSize="99999" showPager="false">
			<div property="columns">
				<div field="productType" headerAlign="center" width="100%" allowSort="true" >模板定义名称</div>
			</div>
		</div>   
		<label>代理原因：</label>
		<input name="agent.agentreason" style="height:245px;" required="true" class="nui-textarea nui-form-input"/>
	</div>
</div>
<div class="nui-toolbar" style="border-bottom:5;text-align:center;" >
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
</body>
</html>
  <script type="text/javascript">
  //[{'id':'ALL','text':'完全代理'},{'id':'PART','text':'委托代理'}]
  
  $(document).ready(function(){
  // 在这里写你的代码...
  	//这里判断是否属于管理
  	if(!<%=rolebool %>){
  		//如果不是管理 就不能选择委托人，委托人只能是当前登录
  		var agentfrom=nui.get("agentfrom");
  		agentfrom.setValue(<%=userid %>);
  		agentfrom.setText('<%=username %>');
  		nui.get("agenposition").setValue('<%=orgcode %>');
  		nui.get("agentfromid").setValue(<%=userid %>);
  		agentfrom.setEnabled(false);
  	}
  });
	 	nui.parse();

	    var form = new nui.Form("#form1");
	    //保存方法
		function save() {

			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			
			if(nui.get("endtime").getValue()<nui.get("starttime").getValue())
			{
				nui.alert("终止时间应该大于生效时间!");
				return;
			}
			if(<%=rolebool %>){

		      		  o.agent["status"]='3';
		      		  o.agent["operator"]=nui.get("agentto").getValue();
		      		  submt(o);
		      		  var json=nui.encode(o);
		      			  $.ajax({
			                    url: "com.bos.bps.op.WorkFlowManager.createAgent.biz.ext",
				                type: 'POST',
				                data: json,
				                cache: false,
				                contentType:'text/json',
			                    success: function (text) {
			                      	  search();
			                    },
			                    error: function () {
			                    	nui.alert("操作失败！");
			                    }
		                	});

			}else{
				      o.agent["status"]='2';
		      		  o.agent["operator"]=nui.get("agentto").getValue();
		      		  submt(o);
			}
			
		}
		//提交
		function submt(o){
		var json=nui.encode(o);
			$.ajax({
		        url: "com.bos.bps.op.WorkFlowManager.createAgentLocal.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		nui.alert("保存成功！");
		        		CloseWindow("ok");
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		//弹出选择委托人
		function selectEntrust(){
			var btnEdit = this;
	        nui.open({
	            url: nui.context + "/bps/mywork/userogr_trre.jsp",
	            title: "选择委托人",
	            width: 800,
	            height: 450,
	            ondestroy: function (action) {   
					 if (action!="close"&&action.result == "ok") {
					 	nui.get("agentfrom").setValue(action.data.empcode);
					 	nui.get("agentfrom").setText(action.data.empname);
					 	//这里是把机构id存到岗位id字段中，加错了字段
					 	nui.get("agenposition").setValue(action.data.orgcode);
					 	nui.get("agentfromid").setValue(action.data.empcode);
					 	//这里是清空例外流程，防止出现不属于委托人的流程
					 	nui.get("grid2").setData([]);
					 	nui.get("agentitem").setValue("");
					 }
	        	}
	        });  
		}
		//弹出选择代理人
		function selectAgent(){
			var btnEdit = this;
	        nui.open({
	            url: nui.context + "/bps/mywork/userogr_trre.jsp",
	            title: "选择代理人",
	            width: 800,
	            height: 450,
	            ondestroy: function (action) {   
					 if (action!="close"&&action.result == "ok") {
					 	nui.get("agentto").setValue(action.data.empcode);
					 	nui.get("agentto").setText(action.data.empname);
					 }
	        	}
	        });  
		}
		//弹出选择例外流程
		function selectProcess(e){
					
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整后再选择例外流程");
				return;
			}
	        var btnEdit = this;
	        var json=nui.encode(getJson());
	        nui.open({
	            url: nui.context + "/bps/mywork/process_choice.jsp?json="+json+"&agentfromid="+nui.get("agentfromid").getValue()+"&orgcode="+nui.get("agenposition").getValue(),
	            title: "选择例外流程",
	            width: 800,
	            height: 450,
	            ondestroy: function (action) {   
	                if (action!="close"&&action.result == "ok") {
	            		var str="";
						for(var i=0;i<action.data.length;i++){
							str+=action.data[i].templateName+",";
						}
	                    nui.get("agentitem").setValue(str);
	                    nui.get("grid2").setData(action.data);
	                }
	        	}
	        });            
		}
		//将例外流程分割组装成json
		function getJson(){
			var jsons=[];
			var agentitem=nui.get("agentitem").getValue();
			var agentitems=agentitem.split(',');
			for(var i=0;i<agentitems.length-1;i++){
				var json={};
				json["templateName"]=agentitems[i];
				jsons[i]=json
			}
			return jsons;
		}
	</script>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 
  - Date: 
  - Description:押品价值审核
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
	<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
	<title>押品价值审核</title>
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
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="押品价值审核" >
	    <div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
	    	<div class="nui-dynpanel" columns="4">
				<label>押品编号：</label>
				<input name="suretyNo"  class="nui-textbox nui-form-input" />
				<label>押品种类：</label>
				<input name="sortType"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_YPZL01"  allowInput="false"/>
		  	    <label>押品名称：</label>
				<input name="suretyName"  class="nui-textbox nui-form-input" />
				<label>抵质押人名称：</label>
				<input name="partyName"  class="nui-textbox nui-form-input" />
		    </div>
	    
	    			
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
   			 borderStyle="border:0;">
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>	
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-ok" id = "ok" onclick="onSubmit()">提交</a>
		</div>
	
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.grt.bsCiValue.bsCiValue.bsCiValueApproval.biz.ext" allowAlternating="true"
			dataField="arrays" allowResize="false" showReloadButton="false" 
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div field="SURETY_NO" allowSort="true">押品编号</div>
				<div field="SURETY_NAME" allowSort="true">押品名称</div>
				<div field="SORT_TYPE" allowSort="true" dictTypeId="XD_YPZL01">押品种类</div>
				<div field="PARTY_NAME" allowSort="true">抵质押人名称</div>
				<div field="MORTGAGA_AMT" allowSort="true">最新权利价值</div>
				<div field="MORTGAGE_VALUE" allowSort="true">权利价值</div>
				<div field="ASSESS_VALUE" allowSort="true">评估价值</div>
				<div field="MYBANK_AFFIRM_VALUE" allowSort="true">认定价值</div>
				<div field="ASSESS_DATE" allowSort="true" format="yyyy-MM-dd">认定时间</div>
				<div field="CURRENCY_CD" allowSort="true" dictTypeId="CD000001">币种</div>
			</div>
   		</div>
	</div>
</div>			
	<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
	    function search() {
			var data = form.getData(); //获取表单多个控件的数据
	        grid.load(data);
	    }
	    search();
	    
	    grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['SURETY_NAME']='<a href="#" onclick="showCollInfo();">'+e.data[i]['SURETY_NAME']+'</a>';
			}
		});
		
	    function reset(){
			form.reset();
		}
		
		function showCollInfo(){
		 	var rows = grid.getSelecteds();
		    var row = grid.getSelected();
		    if(row){
			    var cltNo = row.SURETY_NO;
				var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&cltNo="+cltNo+"&sceneCode=1";			
				window.open(url);
				return;
		    }
		}
		/**
		 * 提交流程
		 */
	    function onSubmit() {
	    nui.get("ok").disable();
		   var rows = grid.getSelecteds();
		   var row = grid.getSelected();
		  var json=nui.encode({"regCards":rows,"partyId":row.PARTYID,"bizId":row.SURETY_NO});
				//保存业务信息
	       $.ajax({
		        	url: "com.bos.grt.regmanage.collateralout.collateralValue.biz.ext",
		        	type: 'POST',
		        	data: json,
		        	cache: false,
		        	contentType:'text/json',
		        	success: function (text) {
		        		if(text.msg !=null){
		            		nui.alert(text.msg); //失败时后台直接返回出错信息
		            		return;
		            	}
		            	var o = nui.decode(text);
						nui.open({
	            			url: nui.context+"/grt/grt_pro/grt_value_tree.jsp?processInstId="+o.processInstId+"&outId="+o.grtOut.outId+"&proFlag=1&isSrc=2",
	            			title: "查看押品", 
	            			width: 1200, 
	        				height: 600,
	        				allowResize:false,
	    	    			showMaxButton: false,
		            		ondestroy: function (action) {
		            			search();
	            	  		}
	        			});

		        	},
		        	error: function (jqXHR, textStatus, errorThrown) {
		            	nui.alert(jqXHR.responseText);
		        	}
				});
			}
	</script>
</body>
</html>